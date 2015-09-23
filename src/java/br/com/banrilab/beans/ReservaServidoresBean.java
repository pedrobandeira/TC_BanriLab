/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;

import br.com.banrilab.dao.ReservaAtmsDaoInterface;
import br.com.banrilab.dao.ReservaServidoresDaoInterface;
import br.com.banrilab.entidades.Atms;
import br.com.banrilab.entidades.ReservaAtms;
import br.com.banrilab.entidades.ReservaServidores;
import br.com.banrilab.entidades.Servidores;
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
public class ReservaServidoresBean implements Serializable {
    private ReservaServidores reservaServidor = new ReservaServidores();
    @EJB
    private ReservaServidoresDaoInterface reservaServidoresDao;
    private List<ReservaServidores> reservasServidores = new ArrayList<>();
    
    
    public ReservaServidoresBean() {
        
    }   

    public Usuarios carregaUsuarioAtivo() {
        HttpSession httpsession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Usuarios usuarioSessao = (Usuarios) httpsession.getAttribute("usuario");
        return usuarioSessao;
    }
    
    public String adicionarReserva() {
        
           this.reservaServidor.setDono(carregaUsuarioAtivo());
           
           this.reservaServidor.setDataInicio(retornaDataAtual());
           reservaServidoresDao.addReservaServidores(reservaServidor);
           this.reservaServidor.getServidor().setDisponivel(false);
           this.reservaServidor.getServidor().setReserva(reservaServidor);
    
           this.reservaServidoresDao.addServidores(this.reservaServidor.getServidor());
           limpaCampos();
          
        return "servidores";
    }
    
    
    public void limpaCampos() {
        this.reservaServidor.setId(null);
        this.reservaServidor.setServidor(null);
        this.reservaServidor.setFinalidade(null);
        this.reservaServidor.setDono(null);
        this.reservaServidor.setHomologacao(null);
        this.reservaServidor.setDataInicio(null);
        this.reservaServidor.setDataFim(null);
    }
    
    public String removerReserva() {
        
        this.reservaServidor.getServidor().setDisponivel(true);
        this.reservaServidor.getServidor().setReserva(null);
        this.reservaServidoresDao.addServidores(reservaServidor.getServidor());
        reservaServidoresDao.removeReservaServidores(this.reservaServidor);
        limpaCampos();
        return "servidores";
    }
    
    public void carregarReserva(ReservaServidores r) {
        this.reservaServidor = r;
        //return "editarreserva";
    }
    
    public String carregarServidor(Servidores s) {
        
        if (s.isReservavel()) {
            if (s.isDisponivel()){
                limpaCampos();
                this.reservaServidor.setServidor(s);
                return "reservarservidor";
            } else if (!(s.isDisponivel())) {
                this.reservaServidor.setServidor(s); 
                carregarReserva(this.reservaServidor.getServidor().getReserva());
                return "reservarservidor";
            }
        }
        return "servidores";
    }
    
    public boolean verificaDono(Servidores s) {
        
        if (s.getReserva() == null) {
            if (s.isReservavel()) {
                return true;
            } else {
                return false;
            }
        }
        if (s.getReserva().getHomologacao() == null) {
            if ((s.isDisponivel() && s.isReservavel()) || (carregaUsuarioAtivo().equals(s.getReserva().getDono()))) {
                return true;
            }
            return false;
        } else if (carregaUsuarioAtivo().equals(s.getReserva().getHomologacao().getAnalista())) {
            return true;
        }
        return false;
    }
    
    public String fecharEditar () {
        limpaCampos();
        return "servidores";
    }
    
    public ReservaServidores getReservaServidor() {
        return reservaServidor;
    }

    public void setReservaServidor(ReservaServidores reservaServidor) {
        this.reservaServidor = reservaServidor;
    }

    public ReservaServidoresDaoInterface getReservaServidoresDao() {
        return reservaServidoresDao;
    }

    public void setReservaServidoresDao(ReservaServidoresDaoInterface reservaServidoresDao) {
        this.reservaServidoresDao = reservaServidoresDao;
    }

    public List<ReservaServidores> getReservasServidores() {
        return reservasServidores;
    }

    public void setReservasServidores(List<ReservaServidores> reservasServidores) {
        this.reservasServidores = reservasServidores;
    }
    
    public Date retornaDataAtual() {
        Date data = new Date();
        return data;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.reservaServidor);
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
        final ReservaServidoresBean other = (ReservaServidoresBean) obj;
        if (!Objects.equals(this.reservaServidor, other.reservaServidor)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}

