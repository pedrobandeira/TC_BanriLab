/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;

import br.com.banrilab.dao.ServidoresDao;
import br.com.banrilab.entidades.Servidores;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Pedro
 */
@ManagedBean
@SessionScoped
public class ServidoresBean {
    private Servidores servidor = new Servidores();
    private ServidoresDao servidorDao = new ServidoresDao();
    private List<Servidores> servidores = new ArrayList<>();
    
    public ServidoresBean() {
    }
    
    public String adicionarServidor() {
        servidorDao.addServidor(servidor);
        return "servidores";
    }
    
    public String removerServidor() {
        servidorDao.removeServidor(servidor);
        return "servidores";
    }
    
    public Servidores getServidor() {
        return servidor;
    }

    public void setServidor(Servidores servidor) {
        this.servidor = servidor;
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
