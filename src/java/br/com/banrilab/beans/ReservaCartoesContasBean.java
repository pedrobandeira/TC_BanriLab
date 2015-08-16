/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;


import br.com.banrilab.dao.ReservaCartoesContasDaoInterface;
import br.com.banrilab.entidades.CartoesContas;
import br.com.banrilab.entidades.ReservaCartoesContas;
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
public class ReservaCartoesContasBean implements Serializable {
    private ReservaCartoesContas reservaCartao = new ReservaCartoesContas();
    @EJB
    private ReservaCartoesContasDaoInterface reservaCartoesDao;
    
    private List<ReservaCartoesContas> reservasCartoesContas = new ArrayList<>();
    
    
    public ReservaCartoesContasBean() {
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
           reservaCartoesDao.addReservaCartoesContas(reservaCartao);
           this.reservaCartao.getCartaoConta().setDisponivel(false);
           this.reservaCartao.getCartaoConta().setReserva(reservaCartao);
    
           this.reservaCartoesDao.addCartoesContas(this.reservaCartao.getCartaoConta());
           limpaCampos();
          
       // }
        return "cartoescontas";
    }
    
    public void limpaCampos() {
        this.reservaCartao.setId(null);
        this.reservaCartao.setCartaoConta(null);
        this.reservaCartao.setFinalidade(null);
        this.reservaCartao.setDono(null);
        this.reservaCartao.setHomologacao(null);
        this.reservaCartao.setDataInicio(null);
        this.reservaCartao.setDataFim(null);
    }
    
    public String removerReserva() {
        //this.reservaAtm = r;
        this.reservaCartao.getCartaoConta().setDisponivel(true);
        this.reservaCartoesDao.addCartoesContas(reservaCartao.getCartaoConta());
        reservaCartoesDao.removeReservaCartoesContas(this.reservaCartao);
        limpaCampos();
        return "cartoescontas";
    }
    
    public void carregarReserva(ReservaCartoesContas r) {
        this.reservaCartao = r;
        //return "editarreserva";
    }
    
    public String carregarCartaoConta(CartoesContas c) {
        this.reservaCartao.setCartaoConta(c);
        
        if (c.isReservavel()) {
            if (c.isDisponivel()){
                return "reservarcartaoconta";
            } else if (!(c.isDisponivel())) {
                carregarReserva(this.reservaCartao.getCartaoConta().getReserva());
                return "reservarcartaoconta";
            }
        }
        return "cartoescontas";
    }
    
    public boolean verificaDono(CartoesContas c) {
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
        return "cartoescontas";
    }
    
    public ReservaCartoesContas getReservaCartoesContas() {
        return reservaCartao;
    }

    public void setReservaCartoesContas(ReservaCartoesContas reservaCartao) {
        this.reservaCartao = reservaCartao;
    }

    public ReservaCartoesContasDaoInterface getReservaCartoesContasDao() {
        return reservaCartoesDao;
    }

    public void setReservaCartoesContasDao(ReservaCartoesContasDaoInterface reservaCartoesDao) {
        this.reservaCartoesDao = reservaCartoesDao;
    }

    public List<ReservaCartoesContas> getReservasCartoesContas() {
        return reservasCartoesContas;
    }

    public void setReservasCartoesContas(List<ReservaCartoesContas> reservasCartoesContas) {
        this.reservasCartoesContas = reservasCartoesContas;
    }
    
    public Date retornaDataAtual() {
        Date data = new Date();
        return data;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.reservaCartao);
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
        final ReservaCartoesContasBean other = (ReservaCartoesContasBean) obj;
        if (!Objects.equals(this.reservaCartao, other.reservaCartao)) {
            return false;
        }
        return true;
    }
    
    
}
