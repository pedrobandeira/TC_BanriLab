/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;

import br.com.banrilab.dao.ReservaServidoresDaoInterface;
import br.com.banrilab.dao.ReservaTerminaisDaoInterface;
import br.com.banrilab.entidades.ReservaServidores;
import br.com.banrilab.entidades.ReservaTerminais;
import br.com.banrilab.entidades.Servidores;
import br.com.banrilab.entidades.Terminais;
import br.com.banrilab.entidades.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
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
public class ReservaTerminaisBean implements Serializable {
    private ReservaTerminais reservaTerminal = new ReservaTerminais();
    @EJB
    private ReservaTerminaisDaoInterface reservaTerminaisDao;
    private List<ReservaTerminais> reservasTerminais = new ArrayList<>();
    
    
    public ReservaTerminaisBean() {
        
    }   

    public Usuarios carregaUsuarioAtivo() {
        HttpSession httpsession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Usuarios usuarioSessao = (Usuarios) httpsession.getAttribute("usuario");
        return usuarioSessao;
    }
    
    public String adicionarReserva() {
        
           this.reservaTerminal.setDono(carregaUsuarioAtivo());
           
           this.reservaTerminal.setDataInicio(retornaDataAtual());
           reservaTerminaisDao.addReservaTerminais(reservaTerminal);
           this.reservaTerminal.getTerminal().setDisponivel(false);
           this.reservaTerminal.getTerminal().setReserva(reservaTerminal);
    
           this.reservaTerminaisDao.addTerminais(this.reservaTerminal.getTerminal());
           limpaCampos();
          
        return "terminais";
    }
    
    public void limpaCampos() {
        this.reservaTerminal.setId(null);
        this.reservaTerminal.setTerminal(null);
        this.reservaTerminal.setFinalidade(null);
        this.reservaTerminal.setDono(null);
        this.reservaTerminal.setHomologacao(null);
        this.reservaTerminal.setDataInicio(null);
        this.reservaTerminal.setDataFim(null);
    }
    
    public String removerReserva() {
        //this.reservaAtm = r;
        this.reservaTerminal.getTerminal().setDisponivel(true);
        this.reservaTerminaisDao.addTerminais(reservaTerminal.getTerminal());
        reservaTerminaisDao.removeReservaTerminais(this.reservaTerminal);
        limpaCampos();
        return "terminais";
    }
    
    public void carregarReserva(ReservaTerminais r) {
        this.reservaTerminal = r;
        //return "editarreserva";
    }
    
    public String carregarTerminal(Terminais t) {
        this.reservaTerminal.setTerminal(t);
        
        if (t.isReservavel()) {
            if (t.isDisponivel()){
                return "reservarterminal";
            } else if (!(t.isDisponivel())) {
                carregarReserva(this.reservaTerminal.getTerminal().getReserva());
                return "reservarterminal";
            }
        }
        return "terminais";
    }
    
    public boolean verificaDono(Terminais t) {
        
        if (t.getReserva() == null) {
            if (t.isReservavel()) {
                return true;
            } else return false; 
        }
        if ((t.isDisponivel() && t.isReservavel()) || (carregaUsuarioAtivo().equals(t.getReserva().getDono()))) {
            return true;
        }
        return false;
    }
    
    public String fecharEditar () {
        limpaCampos();
        return "terminais";
    }
    
    public ReservaTerminais getReservaTerminal() {
        return reservaTerminal;
    }

    public void setReservaTerminal(ReservaTerminais reservaTerminal) {
        this.reservaTerminal = reservaTerminal;
    }

    public ReservaTerminaisDaoInterface getReservaTerminaisDao() {
        return reservaTerminaisDao;
    }

    public void setReservaTerminaisDao(ReservaTerminaisDaoInterface reservaTerminaisDao) {
        this.reservaTerminaisDao = reservaTerminaisDao;
    }

    public List<ReservaTerminais> getReservasTerminais() {
        return reservasTerminais;
    }

    public void setReservasTerminais(List<ReservaTerminais> reservasTerminais) {
        this.reservasTerminais = reservasTerminais;
    }
    
    public Date retornaDataAtual() {
        Date data = new Date();
        return data;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.reservaTerminal);
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
        final ReservaTerminaisBean other = (ReservaTerminaisBean) obj;
        if (!Objects.equals(this.reservaTerminal, other.reservaTerminal)) {
            return false;
        }
        return true;
    }
     
}

