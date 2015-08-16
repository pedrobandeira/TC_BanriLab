/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.CartoesCredito;
import br.com.banrilab.entidades.ReservaCartoesCredito;
import java.util.List;

/**
 *
 * @author Pedro
 */
public interface ReservaCartoesCreditoDaoInterface {
    public void addReservaCartoesCredito (ReservaCartoesCredito r);
    public void addCartoesCredito (CartoesCredito c);
    public void removeReservaCartoesCredito (ReservaCartoesCredito r);
    public List<ReservaCartoesCredito> getReservasCartoesCredito();
}
