/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.EquipamentosAdicionais;
import java.util.List;

/**
 *
 * @author Pedro
 */
public interface EquipamentosAdicionaisInterface {
    public void addEquipamentodicional (EquipamentosAdicionais e);
    public void removeEquipamentoAdicional (EquipamentosAdicionais e);
    public List<EquipamentosAdicionais> getEquipamentosAdicionais();
}
