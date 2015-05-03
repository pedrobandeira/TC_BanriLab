/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;

import br.com.banrilab.dao.EquipamentosAdicionaisDao;
import br.com.banrilab.entidades.EquipamentosAdicionais;
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
public class EquipamentosAdicionaisBean implements Serializable {
    private EquipamentosAdicionais equipamentoAdicional = new EquipamentosAdicionais();
    @EJB
    private EquipamentosAdicionaisDao equipDao;
    
    private List<EquipamentosAdicionais> equipamentosAdicionais = new ArrayList<>();
    
    public EquipamentosAdicionaisBean() {
    }
    
    public String adicionarEquipamentoAdicional() {
        equipDao.addEquipamentodicional(equipamentoAdicional);
        this.equipamentoAdicional.setId(null);
        this.equipamentoAdicional.setDescricao(null);
        this.equipamentoAdicional.setNome(null);
        this.equipamentoAdicional.setPatrimonio(null);
        return "equipamentosadicionais";
    }
    
    public String removerEquipamentoAdicional(EquipamentosAdicionais e) {
        this.equipamentoAdicional = e;
        equipDao.removeEquipamentoAdicional(this.equipamentoAdicional);
        this.equipamentoAdicional.setId(null);
        this.equipamentoAdicional.setDescricao(null);
        this.equipamentoAdicional.setNome(null);
        this.equipamentoAdicional.setPatrimonio(null);  
        return "equipamentosadicionais";
    }
    
    public String carregarEquipamentoAdicional(EquipamentosAdicionais e) {
        this.equipamentoAdicional = e;
        return "editarequipamentoadicional";
    }
    
    public String fecharEditar () {
        this.equipamentoAdicional.setId(null);
        this.equipamentoAdicional.setDescricao(null);
        this.equipamentoAdicional.setNome(null);
        this.equipamentoAdicional.setPatrimonio(null);
        return "equipamentosadicionais";
    }
    
    public EquipamentosAdicionais getEquipamentoAdicional() {
        return equipamentoAdicional;
    }

    public void setEquipamentoAdicional(EquipamentosAdicionais e) {
        this.equipamentoAdicional = e;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.equipamentoAdicional);
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
        final EquipamentosAdicionaisBean other = (EquipamentosAdicionaisBean) obj;
        if (!Objects.equals(this.equipamentoAdicional, other.equipamentoAdicional)) {
            return false;
        }
        return true;
    }

    public List<EquipamentosAdicionais> getEquipamentosAdicionais() {
        this.equipamentosAdicionais = equipDao.getEquipamentosAdicionais();
        return equipamentosAdicionais;
    }

    public void setEquipamentosAdicionais(List<EquipamentosAdicionais> equipamentosAdicionais) {
        this.equipamentosAdicionais = equipamentosAdicionais;
    }
}
