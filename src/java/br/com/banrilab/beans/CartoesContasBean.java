/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;

import br.com.banrilab.entidades.CartoesContas;
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
public class CartoesContasBean implements Serializable {
    private CartoesContas cartaoConta = new CartoesContas();
    @EJB
    private br.com.banrilab.dao.CartoesContasDaoInterface cartaoContaDao;
    
    private List<CartoesContas> cartoesContas = new ArrayList<>();
    
    public CartoesContasBean() {
    }
    
    public String adicionarCartaoConta() {
        
        cartaoConta.setDisponibilidade(1);
        cartaoContaDao.addCartaoConta(cartaoConta);
        this.cartaoConta.setId(null);
        this.cartaoConta.setAgencia(null);
        this.cartaoConta.setConta(null);
        this.cartaoConta.setNome(null);
        this.cartaoConta.setDisponibilidade(null);
        return "cartoescontas";
    }
    
    public String removerCartaoConta(CartoesContas c) {
        this.cartaoConta = c;
        cartaoContaDao.removeCartaoConta(this.cartaoConta);
        this.cartaoConta.setId(null);
        this.cartaoConta.setAgencia(null);
        this.cartaoConta.setConta(null);
        this.cartaoConta.setNome(null);
        this.cartaoConta.setDisponibilidade(null); 
        return "cartoescontas";
    }
    
    public String carregarCartaoConta(CartoesContas c) {
        this.cartaoConta = c;
        return "editarcartaoconta";
    }
    
    public String fecharEditar () {
        System.out.println("Entrou no fechar editar");
        this.cartaoConta.setId(null);
        this.cartaoConta.setAgencia(null);
        this.cartaoConta.setConta(null);
        this.cartaoConta.setNome(null);
        this.cartaoConta.setDisponibilidade(null); 
        return "cartoescontas";
    }
    
    public CartoesContas getCartaoConta() {
        return cartaoConta;
    }

    public void setCartaoConta(CartoesContas c) {
        this.cartaoConta = c;
    }
    
    public String exibirStatus(CartoesContas c) {
        if (c.getDisponibilidade() == 1) {
            return "Disponível";
        } else if (c.getDisponibilidade() == 2) {
            return "Reservado";
        } else if (c.getDisponibilidade() == 3) {
            return "Não reservável";
        } 
        return "Não cadastrado";
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
