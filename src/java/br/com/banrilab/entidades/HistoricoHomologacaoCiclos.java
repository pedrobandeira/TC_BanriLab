/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Pedro
 */
@Entity
@Table (name = "historico_homologacao_ciclos")
public class HistoricoHomologacaoCiclos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Homologacoes homologacao;
    private Integer ciclo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFimCiclo;

    public HistoricoHomologacaoCiclos() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Homologacoes getHomologacao() {
        return homologacao;
    }

    public void setHomologacao(Homologacoes homologacao) {
        this.homologacao = homologacao;
    }

    public Integer getCiclo() {
        return ciclo;
    }

    public void setCiclo(Integer ciclo) {
        this.ciclo = ciclo;
    }

    public Date getDataFimCiclo() {
        return dataFimCiclo;
    }

    public void setDataFimCiclo(Date dataFimCiclo) {
        this.dataFimCiclo = dataFimCiclo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final HistoricoHomologacaoCiclos other = (HistoricoHomologacaoCiclos) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
}
