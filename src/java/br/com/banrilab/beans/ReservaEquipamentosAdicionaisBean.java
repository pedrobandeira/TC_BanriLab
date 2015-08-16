/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;

import br.com.banrilab.dao.ReservaAtmsDaoInterface;
import br.com.banrilab.dao.ReservaEquipamentosAdicionaisDaoInterface;
import br.com.banrilab.dao.ReservaServidoresDaoInterface;
import br.com.banrilab.entidades.Atms;
import br.com.banrilab.entidades.EquipamentosAdicionais;
import br.com.banrilab.entidades.ReservaAtms;
import br.com.banrilab.entidades.ReservaEquipamentosAdicionais;
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
public class ReservaEquipamentosAdicionaisBean implements Serializable {
    private ReservaEquipamentosAdicionais reservaEquipamentoAdicional = new ReservaEquipamentosAdicionais();
    @EJB
    private ReservaEquipamentosAdicionaisDaoInterface reservaEquipamentosAdicionaisDao;
    private List<ReservaEquipamentosAdicionais> reservasEquipamentosAdicionais = new ArrayList<>();
    
    
    public ReservaEquipamentosAdicionaisBean() {
        
    }   

    public Usuarios carregaUsuarioAtivo() {
        HttpSession httpsession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Usuarios usuarioSessao = (Usuarios) httpsession.getAttribute("usuario");
        return usuarioSessao;
    }
    
    public String adicionarReserva() {
        
           this.reservaEquipamentoAdicional.setDono(carregaUsuarioAtivo());
           
           this.reservaEquipamentoAdicional.setDataInicio(retornaDataAtual());
           reservaEquipamentosAdicionaisDao.addReservaEquipamentosAdicionais(reservaEquipamentoAdicional);
           this.reservaEquipamentoAdicional.getEquipamentoAdicional().setDisponivel(false);
           this.reservaEquipamentoAdicional.getEquipamentoAdicional().setReserva(reservaEquipamentoAdicional);
    
           this.reservaEquipamentosAdicionaisDao.addEquipamentosAdicionais(this.reservaEquipamentoAdicional.getEquipamentoAdicional());
           limpaCampos();
          
        return "equipamentosadicionais";
    }
    
    public void limpaCampos() {
        this.reservaEquipamentoAdicional.setId(null);
        this.reservaEquipamentoAdicional.setEquipamentoAdicional(null);
        this.reservaEquipamentoAdicional.setFinalidade(null);
        this.reservaEquipamentoAdicional.setDono(null);
        this.reservaEquipamentoAdicional.setHomologacao(null);
        this.reservaEquipamentoAdicional.setDataInicio(null);
        this.reservaEquipamentoAdicional.setDataFim(null);
    }
    
    public String removerReserva() {
        //this.reservaAtm = r;
        this.reservaEquipamentoAdicional.getEquipamentoAdicional().setDisponivel(true);
        this.reservaEquipamentosAdicionaisDao.addEquipamentosAdicionais(reservaEquipamentoAdicional.getEquipamentoAdicional());
        reservaEquipamentosAdicionaisDao.removeReservaEquipamentosAdicionais(this.reservaEquipamentoAdicional);
        limpaCampos();
        return "equipamentosadicionais";
    }
    
    public void carregarReserva(ReservaEquipamentosAdicionais r) {
        this.reservaEquipamentoAdicional = r;
        //return "editarreserva";
    }
    
    public String carregarEquipamentoAdicional(EquipamentosAdicionais e) {
        this.reservaEquipamentoAdicional.setEquipamentoAdicional(e);
        
        if (e.isReservavel()) {
            if (e.isDisponivel()){
                return "reservarequipamentoadicional";
            } else if (!(e.isDisponivel())) {
                carregarReserva(this.reservaEquipamentoAdicional.getEquipamentoAdicional().getReserva());
                return "reservarequipamentoadicional";
            }
        }
        return "equipamentosadicionais";
    }
    
    public boolean verificaDono(EquipamentosAdicionais e) {
        
        if (e.getReserva() == null) {
            if (e.isReservavel()) {
                return true;
            } else return false; 
        }
        if ((e.isDisponivel() && e.isReservavel()) || (carregaUsuarioAtivo().equals(e.getReserva().getDono()))) {
            return true;
        }
        return false;
    }
    
    public String fecharEditar () {
        limpaCampos();
        return "equipamentosadicionais";
    }
    
    public ReservaEquipamentosAdicionais getReservaEquipamentoAdicional() {
        return reservaEquipamentoAdicional;
    }

    public void setReservaEquipamentoAdicional(ReservaEquipamentosAdicionais reservaEquipamentoAdicional) {
        this.reservaEquipamentoAdicional = reservaEquipamentoAdicional;
    }

    public ReservaEquipamentosAdicionaisDaoInterface getReservaEquipamentosAdicionaisDao() {
        return reservaEquipamentosAdicionaisDao;
    }

    public void setReservaEquipamentosAdicionaisDao(ReservaEquipamentosAdicionaisDaoInterface reservaEquipamentosAdicionaisDao) {
        this.reservaEquipamentosAdicionaisDao = reservaEquipamentosAdicionaisDao;
    }

    public List<ReservaEquipamentosAdicionais> getReservasEquipamentosAdicionais() {
        return reservasEquipamentosAdicionais;
    }

    public void setReservasEquipamentosAdicionais(List<ReservaEquipamentosAdicionais> reservasEquipamentosAdicionais) {
        this.reservasEquipamentosAdicionais = reservasEquipamentosAdicionais;
    }
    
    public Date retornaDataAtual() {
        Date data = new Date();
        return data;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.reservaEquipamentoAdicional);
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
        final ReservaEquipamentosAdicionaisBean other = (ReservaEquipamentosAdicionaisBean) obj;
        if (!Objects.equals(this.reservaEquipamentoAdicional, other.reservaEquipamentoAdicional)) {
            return false;
        }
        return true;
    }

       
    
}

