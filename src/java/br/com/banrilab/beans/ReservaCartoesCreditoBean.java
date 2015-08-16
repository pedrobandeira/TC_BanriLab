/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;

import br.com.banrilab.dao.ReservaCartoesCreditoDaoInterface;
import br.com.banrilab.entidades.CartoesCredito;
import br.com.banrilab.entidades.ReservaCartoesCredito;
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
public class ReservaCartoesCreditoBean implements Serializable {
    private ReservaCartoesCredito reservaCartao = new ReservaCartoesCredito();
    @EJB
    private ReservaCartoesCreditoDaoInterface reservaCartoesDao;
    
    private List<ReservaCartoesCredito> reservasCartoesCredito = new ArrayList<>();
    
    
    public ReservaCartoesCreditoBean() {
        //atmsBean = new AtmsBean();
    }   

    public Usuarios carregaUsuarioAtivo() {
        HttpSession httpsession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Usuarios usuarioSessao = (Usuarios) httpsession.getAttribute("usuario");
        return usuarioSessao;
    }
    
    public String adicionarReserva() {
        
       // if (reservaAtm.getAtm().isDisponivel() && reservaAtm.getAtm().isReservavel()) {
           this.reservaCartao.setDono(carregaUsuarioAtivo());
           
           this.reservaCartao.setDataInicio(retornaDataAtual());
           reservaCartoesDao.addReservaCartoesCredito(reservaCartao);
           this.reservaCartao.getCartaoCredito().setDisponivel(false);
           this.reservaCartao.getCartaoCredito().setReserva(reservaCartao);
    
           this.reservaCartoesDao.addCartoesCredito(this.reservaCartao.getCartaoCredito());
           limpaCampos();
          
       // }
        return "cartoescredito";
    }
    
    public void limpaCampos() {
        this.reservaCartao.setId(null);
        this.reservaCartao.setCartaoCredito(null);
        this.reservaCartao.setFinalidade(null);
        this.reservaCartao.setDono(null);
        this.reservaCartao.setHomologacao(null);
        this.reservaCartao.setDataInicio(null);
        this.reservaCartao.setDataFim(null);
    }
    
    public String removerReserva() {
        //this.reservaAtm = r;
        this.reservaCartao.getCartaoCredito().setDisponivel(true);
        this.reservaCartoesDao.addCartoesCredito(reservaCartao.getCartaoCredito());
        reservaCartoesDao.removeReservaCartoesCredito(this.reservaCartao);
        limpaCampos();
        return "cartoescredito";
    }
    
    public void carregarReserva(ReservaCartoesCredito r) {
        this.reservaCartao = r;
        //return "editarreserva";
    }
    
    public String carregarCartaoCredito(CartoesCredito c) {
        this.reservaCartao.setCartaoCredito(c);
        
        if (c.isReservavel()) {
            if (c.isDisponivel()){
                return "reservarcartaocredito";
            } else if (!(c.isDisponivel())) {
                carregarReserva(this.reservaCartao.getCartaoCredito().getReserva());
                return "reservarcartaocredito";
            }
        }
        return "cartoescredito";
    }
    
    public boolean verificaDono(CartoesCredito c) {
        if (c.getReserva() == null) {
            if (c.isReservavel()) {
                return true;
            } else return false; 
        }
        if ((c.isDisponivel() && c.isReservavel()) || (carregaUsuarioAtivo().equals(c.getReserva().getDono()))) {
            return true;
        }
        return false;
    }
    
    public String fecharEditar () {
        limpaCampos();
        return "cartoescredito";
    }
    
    public ReservaCartoesCredito getReservaCartoesCredito() {
        return reservaCartao;
    }

    public void setReservaCartoesCredito(ReservaCartoesCredito reservaCartao) {
        this.reservaCartao = reservaCartao;
    }

    public ReservaCartoesCreditoDaoInterface getReservaCartoesCreditoDao() {
        return reservaCartoesDao;
    }

    public void setReservaCartoesCreditoDao(ReservaCartoesCreditoDaoInterface reservaCartoesDao) {
        this.reservaCartoesDao = reservaCartoesDao;
    }

    public List<ReservaCartoesCredito> getReservasCartoesCredito() {
        return reservasCartoesCredito;
    }

    public void setReservasCartoesCredito(List<ReservaCartoesCredito> reservasCartoesCredito) {
        this.reservasCartoesCredito = reservasCartoesCredito;
    }
    
    public Date retornaDataAtual() {
        Date data = new Date();
        return data;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.reservaCartao);
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
        final ReservaCartoesCreditoBean other = (ReservaCartoesCreditoBean) obj;
        if (!Objects.equals(this.reservaCartao, other.reservaCartao)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
