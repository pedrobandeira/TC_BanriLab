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
public interface CartoesCreditoDaoInterface {
    public void addCartaoCredito (CartoesCredito c);
    public void removeCartaoCredito (CartoesCredito c);
    public List<CartoesCredito> getCartoesCredito();
    public void removeReservaCartaoCredito (ReservaCartoesCredito r);
    public List<ReservaCartoesCredito> getReservasCartoesCredito();
}
