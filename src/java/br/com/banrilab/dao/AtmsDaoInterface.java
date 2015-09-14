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
public interface AtmsDaoInterface {
    public void addAtm (Atms a);
    public void removeAtm (Atms a);
    public List<Atms> getAtms();
    public void removeReservaAtms (ReservaAtms r);
    public List<ReservaAtms> getReservasAtms();
}
