/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.Homologacoes;
import java.util.List;

/**
 *
 * @author Pedro
 */
public interface HomologacoesDaoInterface {
    public void addHomologacao (Homologacoes h);
    public void removeHomologacao (Homologacoes h);
    public List<Homologacoes> getHomologacoes();
    public List<Homologacoes> getHomologacoesAbertas();
    
}
