/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Pedro
 */
@Entity
@Table(name="homologacoes")
public class Homologacoes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Sistemas sistema;
    @OneToMany (mappedBy = "homologacao")
    private List<ReservaAtms> reservasAtms;
    @OneToMany (mappedBy = "homologacao")
    private List<ReservaCartoesContas> reservasCartoesContas;
    @OneToMany (mappedBy = "homologacao")
    private List<ReservaCartoesCredito> reservasCartoesCreditos;
    @OneToMany (mappedBy = "homologacao")
    private List<ReservaEquipamentosAdicionais> reservasEquipamentosAdicionais;
    @OneToMany (mappedBy = "homologacao")
    private List<ReservaServidores> reservasServidores;
    @OneToMany (mappedBy = "homologacao")
    private List<ReservaTerminais> reservasTerminais;
    @ManyToOne
    private Usuarios solicitante;
    @ManyToOne
    private Usuarios autorizador;
    @ManyToOne
    private Usuarios analista;
    @OneToMany (mappedBy = "homologacao")
    private List<ReservaUsuarios> reservasTestadores;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataInicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFim;
    
    private Integer status; // 1 - Solicitada, 2 - Autorizada, 3 - Em andamento, 4 - Concluida, 5 - Cancelada
    private Integer ciclo;
    private String requisitos;
    private String observacoes; 
    private String versaoSistema;
    
    public Homologacoes() {
        this.ciclo = 1;
        this.status = 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ReservaAtms> getReservasAtms() {
        return reservasAtms;
    }

    public void setReservasAtms(List<ReservaAtms> reservasAtms) {
        this.reservasAtms = reservasAtms;
    }

    public List<ReservaCartoesContas> getReservasCartoesContas() {
        return reservasCartoesContas;
    }

    public void setReservasCartoesContas(List<ReservaCartoesContas> reservasCartoesContas) {
        this.reservasCartoesContas = reservasCartoesContas;
    }

    public List<ReservaCartoesCredito> getReservasCartoesCreditos() {
        return reservasCartoesCreditos;
    }

    public void setReservasCartoesCreditos(List<ReservaCartoesCredito> reservasCartoesCreditos) {
        this.reservasCartoesCreditos = reservasCartoesCreditos;
    }

    public List<ReservaEquipamentosAdicionais> getReservasEquipamentosAdicionais() {
        return reservasEquipamentosAdicionais;
    }

    public void setReservasEquipamentosAdicionais(List<ReservaEquipamentosAdicionais> reservasEquipamentosAdicionais) {
        this.reservasEquipamentosAdicionais = reservasEquipamentosAdicionais;
    }

    public List<ReservaServidores> getReservasServidores() {
        return reservasServidores;
    }

    public void setReservasServidores(List<ReservaServidores> reservasServidores) {
        this.reservasServidores = reservasServidores;
    }

    public List<ReservaTerminais> getReservasTerminais() {
        return reservasTerminais;
    }

    public void setReservasTerminais(List<ReservaTerminais> reservasTerminais) {
        this.reservasTerminais = reservasTerminais;
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

    public Sistemas getSistema() {
        return sistema;
    }

    public void setSistema(Sistemas sistema) {
        this.sistema = sistema;
    }

    public Usuarios getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Usuarios solicitante) {
        this.solicitante = solicitante;
    }

    public Usuarios getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(Usuarios autorizador) {
        this.autorizador = autorizador;
    }

    public Usuarios getAnalista() {
        return analista;
    }

    public void setAnalista(Usuarios analista) {
        this.analista = analista;
    }

    public List<ReservaUsuarios> getReservasTestadores() {
        return reservasTestadores;
    }

    public void setReservasTestadores(List<ReservaUsuarios> reservasTestadores) {
        this.reservasTestadores = reservasTestadores;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCiclo() {
        return ciclo;
    }

    public void setCiclo(Integer ciclo) {
        this.ciclo = ciclo;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getVersaoSistema() {
        return versaoSistema;
    }

    public void setVersaoSistema(String versaoSistema) {
        this.versaoSistema = versaoSistema;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.id);
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
        final Homologacoes other = (Homologacoes) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
