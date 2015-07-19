/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;

import br.com.banrilab.dao.SistemasDaoInterface;
import br.com.banrilab.entidades.Sistemas;
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
public class SistemasBean implements Serializable {
    private Sistemas sistema = new Sistemas();
    @EJB
    private SistemasDaoInterface sistemaDao;
    
    private List<Sistemas> sistemas = new ArrayList<>();
    
    public SistemasBean() {
    }
    
    public String adicionarSistema() {
        System.out.println("Entrou no addBean");
        //System.out.println("Nome do responsavel: "+sistema.getResponsavel().getNome());
        sistemaDao.addSistema(sistema);
        this.sistema.setId(null);
        this.sistema.setDescricao(null);
        this.sistema.setResponsavel(null);
        this.sistema.setNome(null);
        this.sistema.setVersao(null);
        return "sistemas";
    }
    
    public String removerSistema(Sistemas s) {
        this.sistema = s;
        sistemaDao.removeSistema(this.sistema);
        this.sistema.setId(null);
        this.sistema.setDescricao(null);
        this.sistema.setResponsavel(null);
        this.sistema.setNome(null);
        this.sistema.setVersao(null);
        return "sistemas";
    }
    
    public String carregarSistema(Sistemas s) {
        this.sistema = s;
        return "editarsistema";
    }
    
    public String fecharEditar () {
        this.sistema.setId(null);
        this.sistema.setDescricao(null);
        this.sistema.setResponsavel(null);
        this.sistema.setNome(null);
        this.sistema.setVersao(null);
        return "sistemas";
    }

    public Sistemas getSistema() {
        return sistema;
    }

    public void setSistema(Sistemas sistema) {
        this.sistema = sistema;
    }

    public SistemasDaoInterface getSistemaDao() {
        return sistemaDao;
    }

    public void setSistemaDao(SistemasDaoInterface sistemaDao) {
        this.sistemaDao = sistemaDao;
    }

    public List<Sistemas> getSistemas() {
        this.sistemas = sistemaDao.getSistemas();
        return sistemas;
    }

    public void setSistemas(List<Sistemas> sistemas) {
        this.sistemas = sistemas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.sistema);
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
        final SistemasBean other = (SistemasBean) obj;
        if (!Objects.equals(this.sistema, other.sistema)) {
            return false;
        }
        return true;
    }
    
}
