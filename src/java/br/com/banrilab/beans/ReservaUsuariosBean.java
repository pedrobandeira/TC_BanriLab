/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;

import br.com.banrilab.dao.ReservaUsuariosDaoInterface;
import br.com.banrilab.entidades.ReservaUsuarios;
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
public class ReservaUsuariosBean implements Serializable {
    private ReservaUsuarios reservaUsuario = new ReservaUsuarios();
    @EJB
    private ReservaUsuariosDaoInterface reservaUsuariosDao;
    private List<ReservaUsuarios> reservasUsuarios = new ArrayList<>();
    
    
    public ReservaUsuariosBean() {
        
    }   

    public Usuarios carregaUsuarioAtivo() {
        HttpSession httpsession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Usuarios usuarioSessao = (Usuarios) httpsession.getAttribute("usuario");
        return usuarioSessao;
    }
    
    public void adicionarReserva() {
        
           this.reservaUsuario.setDataInicio(retornaDataAtual());
           reservaUsuariosDao.addReservaUsuarios(reservaUsuario);
           this.reservaUsuario.getUsuario().setDisponivel(false);
           this.reservaUsuario.getUsuario().setReserva(reservaUsuario);
    
           this.reservaUsuariosDao.addUsuarios(this.reservaUsuario.getUsuario());
           limpaCampos();
          
    }
    
    public void limpaCampos() {
        this.reservaUsuario.setId(null);
        this.reservaUsuario.setUsuario(null);
        this.reservaUsuario.setHomologacao(null);
        this.reservaUsuario.setDataInicio(null);
        this.reservaUsuario.setDataFim(null);
    }
    
    public void removerReserva() {
        //this.reservaAtm = r;
        this.reservaUsuario.getUsuario().setDisponivel(true);
        this.reservaUsuario.getUsuario().setReserva(null);
        this.reservaUsuariosDao.addUsuarios(reservaUsuario.getUsuario());
        reservaUsuariosDao.removeReservaUsuarios(this.reservaUsuario);
        limpaCampos();
        
    }
    
    public void carregarReserva(ReservaUsuarios r) {
        this.reservaUsuario = r;
        //return "editarreserva";
    }
    
    public String carregarUsuario(Usuarios u) {
        
        if (u.getPerfil() == 4) {
            if (u.isDisponivel()){
                limpaCampos();
                this.reservaUsuario.setUsuario(u);
                return "reservarusuario";
            } else if (!(u.isDisponivel())) {
                this.reservaUsuario.setUsuario(u);
                carregarReserva(this.reservaUsuario.getUsuario().getReserva());
                return "reservarusuario";
            }
        }
        return "usuarios";
    }
    
    public boolean verificaDono(Usuarios u) {
        
        if (u.getReserva() == null) {
            if (u.getPerfil() == 4) {
                return true;
            } else return false; 
        }
        if ((u.isDisponivel() && (u.getPerfil() == 4)) || (carregaUsuarioAtivo().equals(u.getReserva().getHomologacao().getAnalista()))) {
            return true;
        }
        return false;
    }
    
    public String fecharEditar () {
        limpaCampos();
        return "usuarios";
    }
    
    public ReservaUsuarios getReservaUsuario() {
        return reservaUsuario;
    }

    public void setReservaUsuario(ReservaUsuarios reservaUsuario) {
        this.reservaUsuario = reservaUsuario;
    }

    public ReservaUsuariosDaoInterface getReservaUsuariosDao() {
        return reservaUsuariosDao;
    }

    public void setReservaUsuariosDao(ReservaUsuariosDaoInterface reservaUsuariosDao) {
        this.reservaUsuariosDao = reservaUsuariosDao;
    }

    public List<ReservaUsuarios> getReservasUsuarios() {
        return reservasUsuarios;
    }

    public void setReservasUsuarios(List<ReservaUsuarios> reservasUsuarios) {
        this.reservasUsuarios = reservasUsuarios;
    }
    
    public Date retornaDataAtual() {
        Date data = new Date();
        return data;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.reservaUsuario);
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
        final ReservaUsuariosBean other = (ReservaUsuariosBean) obj;
        if (!Objects.equals(this.reservaUsuario, other.reservaUsuario)) {
            return false;
        }
        return true;
    }

}


