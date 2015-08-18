/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.ReservaUsuarios;
import br.com.banrilab.entidades.Usuarios;
import java.util.List;

/**
 *
 * @author Pedro
 */
public interface ReservaUsuariosDaoInterface {
    public void addReservaUsuarios (ReservaUsuarios r);
    public void addUsuarios (Usuarios u);
    public void removeReservaUsuarios (ReservaUsuarios r);
    public List<ReservaUsuarios> getReservasUsuarios();
}
