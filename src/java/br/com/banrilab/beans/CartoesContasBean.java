/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;

import br.com.banrilab.entidades.CartoesContas;
import br.com.banrilab.entidades.ReservaCartoesContas;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Pedro
 */

@ManagedBean
@SessionScoped
public class CartoesContasBean implements Serializable {
    private CartoesContas cartaoConta = new CartoesContas();
    @EJB
    private br.com.banrilab.dao.CartoesContasDaoInterface cartaoContaDao;
    
    private List<CartoesContas> cartoesContas = new ArrayList<>();
    
    public CartoesContasBean() {
    }
    
    public String adicionarCartaoConta() {
        
        cartaoConta.setDisponivel(true);
        cartaoContaDao.addCartaoConta(cartaoConta);
        limpaCampos();
        return "cartoescontas";
    }
    
    public String removerCartaoConta(CartoesContas c) {
        this.cartaoConta = c;
        for (ReservaCartoesContas reserva : cartaoContaDao.getReservasCartoesContas()) {
            if (reserva.getCartaoConta().equals(c)) {
            cartaoContaDao.removeReservaCartaoConta(reserva);
            }
        }
        cartaoContaDao.removeCartaoConta(this.cartaoConta);
        limpaCampos();
        return "cartoescontas";
    }
    
    public String carregarCartaoConta(CartoesContas c) {
        this.cartaoConta = c;
        return "editarcartaoconta";
    }
    
    public String fecharEditar () {
        System.out.println("Entrou no fechar editar");
        limpaCampos(); 
        return "cartoescontas";
    }
    
    public CartoesContas getCartaoConta() {
        return cartaoConta;
    }

    public void setCartaoConta(CartoesContas c) {
        this.cartaoConta = c;
    }
    
    public String exibirDisponibilidade(CartoesContas c) {
        if (c.isReservavel()) {
            if (c.isDisponivel()) {
                return "Disponível";
            }
            if (c.getReserva() != null) {
                if (!(c.getReserva().getDono() == null)) {
                    if (!(c.getReserva().getDono().getNome().isEmpty())) {
                        return c.getReserva().getDono().getNome();
                    } 
                }
                if (!(c.getReserva().getHomologacao() == null)) {
                    return c.getReserva().getHomologacao().getAnalista().getNome();
                }
            }
        }
        return "Não reservável";

    }
    
    public void limpaCampos() {
        this.cartaoConta.setId(null);
        this.cartaoConta.setAgencia(null);
        this.cartaoConta.setConta(null);
        this.cartaoConta.setNome(null);
        this.cartaoConta.setDisponivel(true);
        this.cartaoConta.setReservavel(true);
        this.cartaoConta.setReserva(null);
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.cartaoConta);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CartoesContasBean other = (CartoesContasBean) obj;
        if (!Objects.equals(this.cartaoConta, other.cartaoConta)) {
            return false;
        }
        return true;
    }

    public List<CartoesContas> getCartoesContas() {
        this.cartoesContas = cartaoContaDao.getCartoesContas();
        return cartoesContas;
    }

    public void setCartoesContas(List<CartoesContas> cartoesContas) {
        this.cartoesContas = cartoesContas;
    }
}
