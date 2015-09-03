/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.entidades;

import br.com.banrilab.converters.SampleEntity;
import java.io.Serializable;
import java.util.Date;
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
@Table(name="reserva_atms")
public class ReservaAtms implements Serializable, SampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private Atms atm;
    
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
    
    
    public ReservaAtms() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Atms getAtm() {
        return atm;
    }

    public void setAtm(Atms atm) {
        this.atm = atm;
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
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaAtms)) {
            return false;
        }
        ReservaAtms other = (ReservaAtms) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.banrilab.entidades.ReservaAtms[ id=" + id + " ]";
    }
    
}
