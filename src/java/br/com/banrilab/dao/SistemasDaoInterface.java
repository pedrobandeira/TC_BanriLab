/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.Sistemas;
import java.util.List;

/**
 *
 * @author Pedro
 */
public interface SistemasDaoInterface {
    public void addSistema (Sistemas s);
    public void removeSistema (Sistemas s);
    public List<Sistemas> getSistemas();
}
