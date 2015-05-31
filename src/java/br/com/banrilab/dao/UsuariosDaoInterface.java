/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.Usuarios;
import java.util.List;

/**
 *
 * @author Pedro
 */
public interface UsuariosDaoInterface {
    public void addUsuario (Usuarios u);
    public void removeUsuario (Usuarios u);
    public List<Usuarios> getUsuarios();
    public List<Usuarios> getEquipeTestes();
    public List<Usuarios> getEquipeDesenvolvimento();
    public List<Usuarios> getEquipeAdminLaboratorio();
}
