/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.Atms;
import br.com.banrilab.entidades.ReservaAtms;
import java.util.List;

/**
 *
 * @author Pedro
 */
public interface ReservaAtmsDaoInterface {
    public void addReservaAtms (ReservaAtms r);
    public void addAtms (Atms a);
    public void removeReservaAtms (ReservaAtms r);
    public List<ReservaAtms> getReservasAtms();
    
}
