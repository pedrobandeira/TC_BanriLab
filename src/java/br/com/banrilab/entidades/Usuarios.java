/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.entidades;

import br.com.banrilab.converters.SampleEntity;
import java.io.Serializable;
import java.util.Objects;
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
@Table(name="usuarios")
public class Usuarios implements Serializable,SampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String matricula;
    private String nome;
    private String senha;
    private String email;
    private Integer perfil;
    private boolean disponivel;
    // perfís: 1 - Admin Lab, 2 - Coord Testes, 3 - Analista Teste, 4 - Testador, 5 - Desenvolvedor
    @OneToOne (mappedBy = "usuario")
    private ReservaUsuarios reserva;

    public Usuarios() {
        this.disponivel = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPerfil() {
        return perfil;
    }

    public void setPerfil(Integer perfil) {
        this.perfil = perfil;
    }

    public ReservaUsuarios getReserva() {
        return reserva;
    }

    public void setReserva(ReservaUsuarios reserva) {
        this.reserva = reserva;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Usuarios other = (Usuarios) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
