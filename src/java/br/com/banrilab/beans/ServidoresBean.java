/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;

import br.com.banrilab.dao.ServidoresDao;
import br.com.banrilab.dao.ServidoresDaoInterface;
import br.com.banrilab.entidades.ReservaServidores;
import br.com.banrilab.entidades.Servidores;
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
public class ServidoresBean implements Serializable {

    private Servidores servidor = new Servidores();
    @EJB
    private ServidoresDaoInterface servidorDao;

    private List<Servidores> servidores = new ArrayList<>();

    public ServidoresBean() {
    }

    public String adicionarServidor() {
        this.servidor.setDisponivel(true);
        servidorDao.addServidor(servidor);
        limpaCampos();
        return "servidores";
    }

    public String removerServidor(Servidores s) {
        this.servidor = s;
        for (ReservaServidores reserva : servidorDao.getReservasServidores()) {
            if (reserva.getServidor().equals(s)) {
            servidorDao.removeReservaServidor(reserva);
            }
        }
        servidorDao.removeServidor(this.servidor);
        limpaCampos();
        return "servidores";
    }

    public String carregarServidor(Servidores s) {
        this.servidor = s;
        return "editarservidor";
    }

    public String fecharEditar() {
        limpaCampos();
        return "servidores";
    }

    public Servidores getServidor() {
        return servidor;
    }

    public void setServidor(Servidores s) {
        this.servidor = s;
    }

    public String exibirDisponibilidade(Servidores s) {
        if (s.isReservavel()) {
            if (s.isDisponivel()) {
                return "Disponível";
            }
            if (s.getReserva() != null) {
                if (!(s.getReserva().getDono() == null)) {
                    if (!(s.getReserva().getDono().getNome().isEmpty())) {
                        return s.getReserva().getDono().getNome();
                    } 
                }
                if (!(s.getReserva().getHomologacao() == null)) {
                    return s.getReserva().getHomologacao().getAnalista().getNome();
                }
            }
        }
        return "Não reservável";

    }

    public void limpaCampos() {
        this.servidor.setId(null);
        this.servidor.setDescricao(null);
        this.servidor.setModelo(null);
        this.servidor.setNome(null);
        this.servidor.setPatrimonio(null);
        this.servidor.setDisponivel(true);
        this.servidor.setReservavel(true);
        this.servidor.setReserva(null);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.servidor);
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
        final ServidoresBean other = (ServidoresBean) obj;
        if (!Objects.equals(this.servidor, other.servidor)) {
            return false;
        }
        return true;
    }

    public List<Servidores> getServidores() {
        this.servidores = servidorDao.getServidores();
        return servidores;
    }

    public void setServidores(List<Servidores> servidores) {
        this.servidores = servidores;
    }
}
