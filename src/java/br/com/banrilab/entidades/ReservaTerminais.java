/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.entidades;

import br.com.banrilab.converters.SampleEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Pedro
 */
@Entity
@Table(name="reserva_terminais")
public class ReservaTerminais implements Serializable, SampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private Terminais terminal;
    
    @ManyToOne
    private Homologacoes homologacao;
    
    @ManyToOne
    private Usuarios dono;
    
    @ManyToOne
    private ReservaUsuarios testador;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataInicio;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFim;
    
    private String finalidade;
    
    
    public ReservaTerminais() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Terminais getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminais terminal) {
        this.terminal = terminal;
    }

    public Homologacoes getHomologacao() {
        return homologacao;
    }

    public void setHomologacao(Homologacoes homologacao) {
        this.homologacao = homologacao;
    }

    public Usuarios getDono() {
        return dono;
    }

    public void setDono(Usuarios dono) {
        this.dono = dono;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    public ReservaUsuarios getTestador() {
        return testador;
    }

    public void setTestador(ReservaUsuarios testador) {
        this.testador = testador;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final ReservaTerminais other = (ReservaTerminais) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

 

    @Override
    public String toString() {
        return "br.com.banrilab.entidades.ReservaTerminais[ id=" + id + " ]";
    }
    
}
