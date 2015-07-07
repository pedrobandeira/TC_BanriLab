/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;

import br.com.banrilab.dao.ServidoresDao;
import br.com.banrilab.dao.ServidoresDaoInterface;
import br.com.banrilab.entidades.Servidores;
import java.io.Serializable;
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
        this.servidor.setId(null);
        this.servidor.setDescricao(null);
        this.servidor.setModelo(null);
        this.servidor.setNome(null);
        this.servidor.setPatrimonio(null);
        this.servidor.setDisponivel(true);
        this.servidor.setReservavel(false);
        return "servidores";
    }
    
    public String removerServidor(Servidores s) {
        this.servidor = s;
        servidorDao.removeServidor(this.servidor);
        this.servidor.setId(null);
        this.servidor.setDescricao(null);
        this.servidor.setModelo(null);
        this.servidor.setNome(null);
        this.servidor.setPatrimonio(null);
        this.servidor.setDisponivel(true);
        this.servidor.setReservavel(false);
        return "servidores";
    }
    
    public String carregarServidor(Servidores s) {
        this.servidor = s;
        return "editarservidor";
    }
    
    public String fecharEditar () {
        this.servidor.setId(null);
        this.servidor.setDescricao(null);
        this.servidor.setModelo(null);
        this.servidor.setNome(null);
        this.servidor.setPatrimonio(null);
        this.servidor.setDisponivel(true);
        this.servidor.setReservavel(false);
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
            return "Reservado";
        }
        return "Não reservável";
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
