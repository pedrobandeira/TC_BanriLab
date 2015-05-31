/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.Servidores;
import java.util.List;

/**
 *
 * @author Pedro
 */
public interface ServidoresDaoInterface {
    public void addServidor (Servidores s);
    public void removeServidor (Servidores s);
    public List<Servidores> getServidores();
    
}
