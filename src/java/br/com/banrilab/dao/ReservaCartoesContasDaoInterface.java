/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.CartoesContas;
import br.com.banrilab.entidades.ReservaAtms;
import br.com.banrilab.entidades.ReservaCartoesContas;
import java.util.List;

/**
 *
 * @author Pedro
 */
public interface ReservaCartoesContasDaoInterface {
    public void addReservaCartoesContas (ReservaCartoesContas r);
    public void addCartoesContas (CartoesContas c);
    public void removeReservaCartoesContas (ReservaCartoesContas r);
    public List<ReservaCartoesContas> getReservasCartoesContas();
    
}
