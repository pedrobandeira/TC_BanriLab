/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.entidades;


/**
 *
 * @author Pedro
 */
public class Login {
    private boolean loginErro;
    private Usuarios usuarioLogado = new Usuarios();
    private boolean loginRealizado;

    public Login() {
        this.loginErro = false;
        this.loginRealizado = false;
    }

    public boolean isLoginRealizado() {
        return loginRealizado;
    }

    public void setLoginRealizado(boolean loginRealizado) {
        this.loginRealizado = loginRealizado;
    }
  
    
    public boolean isLoginErro() {
        return loginErro;
    }

    public void setLoginErro(boolean loginErro) {
        this.loginErro = loginErro;
    }

    public Usuarios getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuarios usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
    
}
