/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;

import br.com.banrilab.dao.UsuariosDao;
import br.com.banrilab.entidades.Login;
import br.com.banrilab.entidades.Usuarios;
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
public class UsuariosBean implements Serializable {
    private Usuarios usuario = new Usuarios();
    @EJB
    private UsuariosDao usuarioDao;
    
    private List<Usuarios> usuarios = new ArrayList<>();
    
    private Login login = new Login();
    
    
    public UsuariosBean() {
    }
    
    public String adicionarUsuario() {
        if (usuario.getId() == null) {
            usuario.setSenha(usuario.getMatricula());
        }
        usuarioDao.addUsuario(usuario);
        this.usuario.setId(null);
        this.usuario.setMatricula(null);
        this.usuario.setNome(null);
        this.usuario.setPerfil(null);
        this.usuario.setSenha(null);
        return "usuarios";
    }
    
    public String removerUsuario(Usuarios u) {
        this.usuario = u;
        usuarioDao.removeUsuario(this.usuario);
        this.usuario.setId(null);
        this.usuario.setMatricula(null);
        this.usuario.setNome(null);
        this.usuario.setPerfil(null);
        this.usuario.setSenha(null); 
        return "usuarios";
    }
    
    public String carregarUsuario(Usuarios u) {
        this.usuario = u;
        return "editarusuario";
    }
    
    public String fecharEditar () {
        this.usuario.setId(null);
        this.usuario.setMatricula(null);
        this.usuario.setNome(null);
        this.usuario.setPerfil(null);
        this.usuario.setSenha(null);
        return "usuarios";
    }
    
    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios u) {
        this.usuario = u;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.usuario);
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
        final UsuariosBean other = (UsuariosBean) obj;
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }

    public List<Usuarios> getUsuarios() {
        this.usuarios = usuarioDao.getUsuarios();
        return usuarios;
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }
    
    public String validaLogin() {
        
        String matricula = usuario.getMatricula();
        String senha = usuario.getSenha();
        this.usuario.setMatricula(null);
        this.usuario.setSenha(null);
        this.login.setLoginErro(false);
       
        List<Usuarios> listaUsuarios = new ArrayList<>();
        listaUsuarios = usuarioDao.getUsuarios();
      
        for (Usuarios user: listaUsuarios) {
            if (user.getMatricula().equals(matricula) && user.getSenha().equals(senha)) {
                this.login.setLoginErro(false);
                login.setUsuarioLogado(user);
                return "index"; }
        }
        this.login.setLoginErro(true);
        return "login";
    }
    
    public boolean exibeAlertaLogin() {
        return login.isLoginErro();
    }
    
    public String exibeNomeUsuarioLogado() {
        System.out.println("Nome: "+login.getUsuarioLogado().getNome());
        return login.getUsuarioLogado().getNome();
    }
    
    public String logout() {
        login.setUsuarioLogado(null);
        return "login";
    }
    
    public void limpaAlertaLogin() {
        login.setLoginErro(false);
    }
}
