/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.CartoesContas;
import java.util.List;

/**
 *
 * @author Pedro
 */
public interface CartoesContasDaoInterface {
    public void addCartaoConta (CartoesContas c);
    public void removeCartaoConta (CartoesContas c);
    public List<CartoesContas> getCartoesContas();
}
