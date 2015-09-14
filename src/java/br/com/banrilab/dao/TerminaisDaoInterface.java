/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.ReservaTerminais;
import br.com.banrilab.entidades.Terminais;
import java.util.List;

/**
 *
 * @author Pedro
 */
public interface TerminaisDaoInterface {
    public void addTerminal (Terminais t);
    public void removeTerminal (Terminais t);
    public List<Terminais> getTerminais();
    public void removeReservaTerminal (ReservaTerminais r);
    public List<ReservaTerminais> getReservasTerminais();
}
