/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;

import br.com.banrilab.entidades.CartoesCredito;
import java.io.Serializable;
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
public class CartoesCreditoBean implements Serializable {
    private CartoesCredito cartaoCredito = new CartoesCredito();
    @EJB
    private br.com.banrilab.dao.CartoesCreditoDaoInterface cartaoCreditoDao;
    
    private List<CartoesCredito> cartoesCredito = new ArrayList<>();
    
    public CartoesCreditoBean() {
    }
    
    public String adicionarCartaoCredito() {
        cartaoCredito.setDisponivel(true);
        cartaoCreditoDao.addCartaoCredito(cartaoCredito);
        limpaCampos();
        return "cartoescredito";
    }
    
    public String removerCartaoCredito(CartoesCredito c) {
        this.cartaoCredito = c;
        cartaoCreditoDao.removeCartaoCredito(this.cartaoCredito);
        limpaCampos();
        return "cartoescredito";
    }
    
    public String carregarCartaoCredito(CartoesCredito c) {
        this.cartaoCredito = c;
        return "editarcartaocredito";
    }
    
    public String fecharEditar () {
        limpaCampos();
        return "cartoescredito";
    }
    
    public CartoesCredito getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(CartoesCredito c) {
        this.cartaoCredito = c;
    }
    
    public String exibirBandeira(CartoesCredito c) {
        if (c.getBandeira() == 1) {
            return "MasterCard";
        } else if (c.getBandeira() == 2) {
            return "Visa";
        } 
        return "Não cadastrado";
    }
    
    public String exibirDisponibilidade(CartoesCredito c) {
        if (c.isReservavel()) {
            if (c.isDisponivel()) {
                return "Disponível";
            }
            return "Reservado";
        }
        return "Não reservável";
    }
    
    public void limpaCampos() {
        this.cartaoCredito.setId(null);
        this.cartaoCredito.setNumero(null);
        this.cartaoCredito.setBandeira(null);
        this.cartaoCredito.setNome(null);
        this.cartaoCredito.setDisponivel(true);
        this.cartaoCredito.setReservavel(true);
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.cartaoCredito);
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
        final CartoesCreditoBean other = (CartoesCreditoBean) obj;
        if (!Objects.equals(this.cartaoCredito, other.cartaoCredito)) {
            return false;
        }
        return true;
    }

    public List<CartoesCredito> getCartoesCredito() {
        this.cartoesCredito = cartaoCreditoDao.getCartoesCredito();
        return cartoesCredito;
    }

    public void setCartoesContas(List<CartoesCredito> cartoesCredito) {
        this.cartoesCredito = cartoesCredito;
    }
}
