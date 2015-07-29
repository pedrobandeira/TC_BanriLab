/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;


import br.com.banrilab.dao.ReservaAtmsDaoInterface;
import br.com.banrilab.entidades.Atms;
import br.com.banrilab.entidades.ReservaAtms;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class ReservaAtmsBean implements Serializable {
    private ReservaAtms reservaAtm = new ReservaAtms();
    @EJB
    private ReservaAtmsDaoInterface reservaAtmsDao;
    //@EJB
    //private AtmsDaoInterface atmsDao;
    
    //private Login login;
    
    //private AtmsBean atmsBean;
    
    private List<ReservaAtms> reservasAtms = new ArrayList<>();
    
    public ReservaAtmsBean() {
        //atmsBean = new AtmsBean();
    }   

    public String adicionarReserva() {
        System.out.println("Entrou no add reserva BEAN");
        System.out.println("ATM: "+reservaAtm.getAtm().getNome());
        System.out.println("ID ATM: "+reservaAtm.getAtm().getId());
        System.out.println("DISPONIVEL: "+reservaAtm.getAtm().isDisponivel());
        System.out.println("RESERVAVEL: "+reservaAtm.getAtm().isReservavel());
        if (reservaAtm.getAtm().isDisponivel() && reservaAtm.getAtm().isReservavel()) {
           System.out.println("Entrou no IF");
            //this.reservaAtm.setDono(login.getUsuarioLogado())
           //this.atmsBean.setAtm(reservaAtm.getAtm());
           //this.atmsBean.getAtm().setDisponivel(false);
           
           this.reservaAtm.getAtm().setDisponivel(false);
          // this.atmsBean.setAtm(this.reservaAtm.getAtm());
           //this.atmsBean.adicionarAtm();
           this.reservaAtmsDao.addAtms(this.reservaAtm.getAtm());
           this.reservaAtm.setDataInicio(retornaDataAtual());
           reservaAtmsDao.addReservaAtms(reservaAtm);
           this.reservaAtm.setId(null);
           this.reservaAtm.setAtm(null);
           this.reservaAtm.setFinalidade(null);
           this.reservaAtm.setDono(null);
           this.reservaAtm.setHomologacao(null);
           this.reservaAtm.setDataInicio(null);
           this.reservaAtm.setDataFim(null);
          
        }
        return "atms";
    }
    
    public String removerReserva(ReservaAtms r) {
        this.reservaAtm = r;
        this.reservaAtm.getAtm().setDisponivel(true);
        this.reservaAtmsDao.addAtms(reservaAtm.getAtm());
        reservaAtmsDao.removeReservaAtms(this.reservaAtm);
        this.reservaAtm.setId(null);
        this.reservaAtm.setAtm(null);
        this.reservaAtm.setFinalidade(null);
        this.reservaAtm.setDono(null);
        this.reservaAtm.setHomologacao(null);
        this.reservaAtm.setDataInicio(null);
        this.reservaAtm.setDataFim(null);
        
        return "atms";
    }
    
    public String carregarReserva(ReservaAtms r) {
        this.reservaAtm = r;
        return "editarreserva";
    }
    
    public String carregarAtm(Atms a) {
        this.reservaAtm.setAtm(a);
        if (a.isReservavel()) {
            if (a.isDisponivel()){
                return "reservaratm";
            } else if (!(a.isDisponivel())) {
                return "editarreserva";
            }
        }
        return "atms";
    }
    
    public String fecharEditar () {
        this.reservaAtm.setId(null);
        this.reservaAtm.setAtm(null);
        this.reservaAtm.setFinalidade(null);
        this.reservaAtm.setDono(null);
        this.reservaAtm.setHomologacao(null);
        this.reservaAtm.setDataInicio(null);
        this.reservaAtm.setDataFim(null);
        return "atms";
    }
    
    public ReservaAtms getReservaAtm() {
        return reservaAtm;
    }

    public void setReservaAtm(ReservaAtms reservaAtm) {
        this.reservaAtm = reservaAtm;
    }

    public ReservaAtmsDaoInterface getReservaAtmsDao() {
        return reservaAtmsDao;
    }

    public void setReservaAtmsDao(ReservaAtmsDaoInterface reservaAtmsDao) {
        this.reservaAtmsDao = reservaAtmsDao;
    }

    public List<ReservaAtms> getReservasAtms() {
        return reservasAtms;
    }

    public void setReservasAtms(List<ReservaAtms> reservasAtms) {
        this.reservasAtms = reservasAtms;
    }
    
    public Date retornaDataAtual() {
        Date data = new Date();
        return data;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.reservaAtm);
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
        final ReservaAtmsBean other = (ReservaAtmsBean) obj;
        if (!Objects.equals(this.reservaAtm, other.reservaAtm)) {
            return false;
        }
        return true;
    }
    
    
}
