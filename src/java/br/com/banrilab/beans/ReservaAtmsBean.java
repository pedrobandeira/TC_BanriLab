/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;


import br.com.banrilab.dao.ReservaAtmsDaoInterface;
import br.com.banrilab.entidades.Atms;
import br.com.banrilab.entidades.ReservaAtms;
import br.com.banrilab.entidades.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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

    public Usuarios carregaUsuarioAtivo() {
        HttpSession httpsession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Usuarios usuarioSessao = (Usuarios) httpsession.getAttribute("usuario");
        return usuarioSessao;
    }
    
    public String adicionarReserva() {
        
       // if (reservaAtm.getAtm().isDisponivel() && reservaAtm.getAtm().isReservavel()) {
           this.reservaAtm.setDono(carregaUsuarioAtivo());
           
           this.reservaAtm.setDataInicio(retornaDataAtual());
           reservaAtmsDao.addReservaAtms(reservaAtm);
           this.reservaAtm.getAtm().setDisponivel(false);
           this.reservaAtm.getAtm().setReserva(reservaAtm);
    
           this.reservaAtmsDao.addAtms(this.reservaAtm.getAtm());
           limpaCampos();
          
       // }
        return "atms";
    }
    
    public void limpaCampos() {
        this.reservaAtm.setId(null);
        this.reservaAtm.setAtm(null);
        this.reservaAtm.setFinalidade(null);
        this.reservaAtm.setDono(null);
        this.reservaAtm.setHomologacao(null);
        this.reservaAtm.setDataInicio(null);
        this.reservaAtm.setDataFim(null);
    }
    
    public String removerReserva() {
        //this.reservaAtm = r;
        this.reservaAtm.getAtm().setDisponivel(true);
        this.reservaAtmsDao.addAtms(reservaAtm.getAtm());
        reservaAtmsDao.removeReservaAtms(this.reservaAtm);
        limpaCampos();
        return "atms";
    }
    
    public void carregarReserva(ReservaAtms r) {
        this.reservaAtm = r;
        //return "editarreserva";
    }
    
    public String carregarAtm(Atms a) {
        this.reservaAtm.setAtm(a);
        
        if (a.isReservavel()) {
            if (a.isDisponivel()){
                return "reservaratms";
            } else if (!(a.isDisponivel())) {
                carregarReserva(this.reservaAtm.getAtm().getReserva());
                return "reservaratms";
            }
        }
        return "atms";
    }
    
    public boolean verificaDono(Atms a) {
        
        if ((a.isDisponivel() && a.isReservavel()) || (carregaUsuarioAtivo().equals(a.getReserva().getDono()))) {
            return true;
        }
        return false;
    }
    
    public String fecharEditar () {
        limpaCampos();
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
    
    
   // @Schedule(minute="*/1", hour="*")
    /*public void verificaReservasAtms(){
        System.out.println("Rodando job de reserva atms...");
        List<ReservaAtms> listaReservas = new ArrayList<>();
        listaReservas = reservaAtmsDao.getReservasAtms();
        for (ReservaAtms reserva: listaReservas) {
            if (reserva.getDataFim().before(retornaDataAtual())) {
                reserva.getAtm().setDisponivel(true);
                reservaAtmsDao.addAtms(reserva.getAtm());
                reservaAtmsDao.removeReservaAtms(reserva);
            }
        }
    }*/
    
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
