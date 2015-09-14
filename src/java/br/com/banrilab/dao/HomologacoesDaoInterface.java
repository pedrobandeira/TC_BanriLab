/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.Atms;
import br.com.banrilab.entidades.CartoesContas;
import br.com.banrilab.entidades.CartoesCredito;
import br.com.banrilab.entidades.EquipamentosAdicionais;
import br.com.banrilab.entidades.HistoricoHomologacaoCiclos;
import br.com.banrilab.entidades.Homologacoes;
import br.com.banrilab.entidades.ReservaAtms;
import br.com.banrilab.entidades.ReservaCartoesContas;
import br.com.banrilab.entidades.ReservaCartoesCredito;
import br.com.banrilab.entidades.ReservaEquipamentosAdicionais;
import br.com.banrilab.entidades.ReservaServidores;
import br.com.banrilab.entidades.ReservaTerminais;
import br.com.banrilab.entidades.ReservaUsuarios;
import br.com.banrilab.entidades.Servidores;
import br.com.banrilab.entidades.Terminais;
import br.com.banrilab.entidades.Usuarios;
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
    public void addServidor (Servidores s);
    public void addReservaServidores (ReservaServidores r);
    public void addReservaCartoesContas (ReservaCartoesContas r);
    public void addCartoesContas (CartoesContas c);
    public void addReservaCartoesCredito (ReservaCartoesCredito r);
    public void addCartoesCredito (CartoesCredito c);
    public void addReservaEquipamentosAdicionais (ReservaEquipamentosAdicionais r);
    public void addEquipamentosAdicionais (EquipamentosAdicionais s);
    public void addReservaTerminais (ReservaTerminais r);
    public void addTerminais (Terminais t);
    public void addReservaUsuarios (ReservaUsuarios r);
    public void addUsuarios (Usuarios u);
    public void addReservaAtms (ReservaAtms r);
    public void addAtms (Atms a);
    public List<Usuarios> getEquipeCoordenadores();
    public void addHistoricoHomologacaoCiclo (HistoricoHomologacaoCiclos h);
    public void removeHistoricoHomologacaoCiclo (HistoricoHomologacaoCiclos h);
    public List<HistoricoHomologacaoCiclos> getHistoricoHomologacoesCiclos(Homologacoes h);
}
