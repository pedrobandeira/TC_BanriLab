/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;

import br.com.banrilab.dao.EquipamentosAdicionaisDaoInterface;
import br.com.banrilab.entidades.EquipamentosAdicionais;
import br.com.banrilab.entidades.ReservaEquipamentosAdicionais;
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
public class EquipamentosAdicionaisBean implements Serializable {
    private EquipamentosAdicionais equipamentoAdicional = new EquipamentosAdicionais();
    @EJB
    private EquipamentosAdicionaisDaoInterface equipDao;
    
    private List<EquipamentosAdicionais> equipamentosAdicionais = new ArrayList<>();
    
    public EquipamentosAdicionaisBean() {
    }
    
    public String adicionarEquipamentoAdicional() {
        this.equipamentoAdicional.setDisponivel(true);
        equipDao.addEquipamentoAdicional(equipamentoAdicional);
        limpaCampos();
        return "equipamentosadicionais";
    }
    
    public String removerEquipamentoAdicional(EquipamentosAdicionais e) {
        this.equipamentoAdicional = e;
        for (ReservaEquipamentosAdicionais reserva : equipDao.getReservasEquipamentosAdicionais()) {
            if (reserva.getEquipamento().equals(e)) {
            equipDao.removeReservaEquipamentoAdicional(reserva);
            }
        }
        equipDao.removeEquipamentoAdicional(this.equipamentoAdicional);
        limpaCampos();
        return "equipamentosadicionais";
    }
    
    public String carregarEquipamentoAdicional(EquipamentosAdicionais e) {
        this.equipamentoAdicional = e;
        return "editarequipamentoadicional";
    }
    
    public String fecharEditar () {
        limpaCampos();
        return "equipamentosadicionais";
    }
    
    public EquipamentosAdicionais getEquipamentoAdicional() {
        return equipamentoAdicional;
    }

    public void setEquipamentoAdicional(EquipamentosAdicionais e) {
        this.equipamentoAdicional = e;
    }
        
    public String exibirDisponibilidade(EquipamentosAdicionais e) {
        if (e.isReservavel()) {
            if (e.isDisponivel()) {
                return "Disponível";
            }
            if (e.getReserva() != null) {
                if (!(e.getReserva().getDono() == null)) {
                    if (!(e.getReserva().getDono().getNome().isEmpty())) {
                        return e.getReserva().getDono().getNome();
                    } 
                }
                if (!(e.getReserva().getHomologacao() == null)) {
                    return e.getReserva().getHomologacao().getAnalista().getNome();
                }
            }
        }
        return "Não reservável";

    }
    
    public void limpaCampos() {
        this.equipamentoAdicional.setId(null);
        this.equipamentoAdicional.setDescricao(null);
        this.equipamentoAdicional.setNome(null);
        this.equipamentoAdicional.setPatrimonio(null);
        this.equipamentoAdicional.setDisponivel(true);
        this.equipamentoAdicional.setReservavel(true);
        this.equipamentoAdicional.setReserva(null);
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
