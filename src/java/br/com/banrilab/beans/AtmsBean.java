/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;

import br.com.banrilab.dao.AtmsDao;
import br.com.banrilab.dao.AtmsDaoInterface;
import br.com.banrilab.entidades.Atms;
import br.com.banrilab.entidades.ReservaAtms;
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
public class AtmsBean implements Serializable {
    private Atms atm = new Atms();
    @EJB
    private AtmsDaoInterface atmDao;
    
    private List<Atms> atms = new ArrayList<>();
    
    public AtmsBean() {
    }
    
    
    public String adicionarAtm() {
        this.atm.setDisponivel(true);
        atmDao.addAtm(atm);
        limpaCampos();
        return "atms";
    }
    
    public String removerAtm(Atms a) {
        this.atm = a;
        for (ReservaAtms reserva : atmDao.getReservasAtms() ) {
            if (reserva.getAtm().equals(a)) {
            atmDao.removeReservaAtms(reserva);
            }
        }
        atmDao.removeAtm(this.atm);

        limpaCampos();
        return "atms.xhtml?faces-redirect=true";
    }
    
    public void limpaCampos() {
        this.atm.setId(null);
        this.atm.setModelo(null);
        this.atm.setNome(null);
        this.atm.setPatrimonio(null);  
        this.atm.setDepositario(false);
        this.atm.setTalonadora(false);
        this.atm.setDisponivel(true);
        this.atm.setReservavel(true);
        this.atm.setReserva(null);
    }
    
    public String carregarAtm(Atms a) {
        this.atm = a;
        //if (this.atm.isDisponivel()) {
            return "editaratm";
        //} else return "atms";
    }
    
    public String fecharEditar () {
        limpaCampos();
        return "atms";
    }
    
    public Atms getAtm() {
        return atm;
    }

    public void setAtm(Atms a) {
        this.atm = a;
    }
        
    public String exibirDisponibilidade(Atms a) {
        if (a.isReservavel()) {
            if (a.isDisponivel()) {
                return "Disponível";
            }
            if (a.getReserva() != null) {
                if (!(a.getReserva().getDono() == null)) {
                    if (!(a.getReserva().getDono().getNome().isEmpty())) {
                        return a.getReserva().getDono().getNome();
                    } 
                }
                if (!(a.getReserva().getHomologacao() == null)) {
                    return a.getReserva().getHomologacao().getAnalista().getNome();
                }
            }
        }
        return "Não reservável";

    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.atm);
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
        final AtmsBean other = (AtmsBean) obj;
        if (!Objects.equals(this.atm, other.atm)) {
            return false;
        }
        return true;
    }

    public List<Atms> getAtms() {
        this.atms = atmDao.getAtms();
        return atms;
    }

    public void setAtms(List<Atms> atms) {
        this.atms = atms;
    }
    
    public String exibirDepositario(Atms a) {
        if (a.isDepositario()) {
            return "glyphicon glyphicon-ok";
        }
            return "glyphicon glyphicon-minus";
    }
    
    public String exibirTalonadora(Atms a) {
        if (a.isTalonadora()) {
            return "glyphicon glyphicon-ok";
        }
            return "glyphicon glyphicon-minus";
    }
    
    
}
