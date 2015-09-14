/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 *
 * @author Pedro
 */
@Entity
@Table(name="atms")
public class Atms implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer patrimonio;
    private String nome;
    private String modelo;
    private boolean depositario;
    private boolean talonadora;
    private boolean disponivel;
    private boolean reservavel;
    @OneToOne (mappedBy = "atm")
    private ReservaAtms reserva;
    
    public Atms() {
        this.reservavel = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReservaAtms getReserva() {
        return reserva;
    }

    public void setReserva(ReservaAtms reserva) {
        this.reserva = reserva;
    }

    public Integer getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Integer patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    public boolean isDepositario() {
        return depositario;
    }

    public void setDepositario(boolean depositario) {
        this.depositario = depositario;
    }

    public boolean isTalonadora() {
        return talonadora;
    }

    public void setTalonadora(boolean talonadora) {
        this.talonadora = talonadora;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public boolean isReservavel() {
        return reservavel;
    }

    public void setReservavel(boolean reservavel) {
        this.reservavel = reservavel;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Atms other = (Atms) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
