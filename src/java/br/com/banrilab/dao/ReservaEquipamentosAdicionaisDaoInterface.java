/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.EquipamentosAdicionais;
import br.com.banrilab.entidades.ReservaEquipamentosAdicionais;
import java.util.List;

/**
 *
 * @author Pedro
 */
public interface ReservaEquipamentosAdicionaisDaoInterface {
    public void addReservaEquipamentosAdicionais (ReservaEquipamentosAdicionais r);
    public void addEquipamentosAdicionais (EquipamentosAdicionais s);
    public void removeReservaEquipamentosAdicionais (ReservaEquipamentosAdicionais r);
    public List<ReservaEquipamentosAdicionais> getReservasEquipamentosAdicionais();
}
