/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;

import br.com.banrilab.dao.TerminaisDao;
import br.com.banrilab.dao.TerminaisDaoInterface;
import br.com.banrilab.entidades.Terminais;
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
public class TerminaisBean implements Serializable {
    private Terminais terminal = new Terminais();
    @EJB
    private TerminaisDaoInterface terminalDao;
    
    private List<Terminais> terminais = new ArrayList<>();
    
    public TerminaisBean() {
    }
    
    public String adicionarTerminal() {
        this.terminal.setDisponivel(true);
        terminalDao.addTerminal(terminal);
        limpaCampos();
        return "terminais";
    }
    
    public String removerTerminal(Terminais t) {
        this.terminal = t;
        terminalDao.removeTerminal(this.terminal);
        limpaCampos();
        return "terminais";
    }
    
    public String carregarTerminal(Terminais t) {
        this.terminal = t;
        return "editarterminal";
    }
    
    public String fecharEditar () {
        limpaCampos();
        return "terminais";
    }
    
    public Terminais getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminais t) {
        this.terminal = t;
    }
        
    public String exibirDisponibilidade(Terminais t) {
        if (t.isReservavel()) {
            if (t.isDisponivel()) {
                return "Disponível";
            }
            if (t.getReserva() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                if (!(t.getReserva().getDono() == null)) {
                    if (!(t.getReserva().getDono().getNome().isEmpty())) {
                        return "Reservado para " + t.getReserva().getDono().getNome() + " até " + sdf.format(t.getReserva().getDataFim());
                    } 
                }
                if (!(t.getReserva().getHomologacao() == null)) {
                    return ("Reservado para "+t.getReserva().getFinalidade()+" até "+ sdf.format(t.getReserva().getDataFim()));
                }
            }
        }
        return "Não reservável";
    }
    
    public void limpaCampos() {
        this.terminal.setId(null);
        this.terminal.setDescricao(null);
        this.terminal.setNome(null);
        this.terminal.setPatrimonio(null);
        this.terminal.setDisponivel(true);
        this.terminal.setReservavel(true);
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.terminal);
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
        final TerminaisBean other = (TerminaisBean) obj;
        if (!Objects.equals(this.terminal, other.terminal)) {
            return false;
        }
        return true;
    }

    public List<Terminais> getTerminais() {
        this.terminais = terminalDao.getTerminais();
        return terminais;
    }

    public void setTerminais(List<Terminais> terminais) {
        this.terminais = terminais;
    }
}
