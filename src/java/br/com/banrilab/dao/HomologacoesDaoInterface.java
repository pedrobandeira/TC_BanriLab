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
import br.com.banrilab.entidades.HistoricoReservaAtms;
import br.com.banrilab.entidades.HistoricoReservaCartoesContas;
import br.com.banrilab.entidades.HistoricoReservaCartoesCredito;
import br.com.banrilab.entidades.HistoricoReservaEquipamentosAdicionais;
import br.com.banrilab.entidades.HistoricoReservaServidores;
import br.com.banrilab.entidades.HistoricoReservaTerminais;
import br.com.banrilab.entidades.HistoricoReservaUsuarios;
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
    public void removeReservaAtms (ReservaAtms r);
    public void removeReservaCartoesContas (ReservaCartoesContas r);
    public void removeReservaCartoesCredito (ReservaCartoesCredito r);
    public void removeReservaEquipamentosAdicionais (ReservaEquipamentosAdicionais r);
    public void removeReservaServidores (ReservaServidores r);
    public void removeReservaTerminais (ReservaTerminais r);
    public void removeReservaUsuarios (ReservaUsuarios r);
    public void addHistoricoReservaAtms (HistoricoReservaAtms h);
    public List<HistoricoReservaAtms> getHistoricoReservasAtms(Homologacoes h);
    public void addHistoricoReservaServidores (HistoricoReservaServidores h);
    public List<HistoricoReservaServidores> getHistoricoReservasServidores(Homologacoes h);
    public void addHistoricoReservaTerminais (HistoricoReservaTerminais h);
    public List<HistoricoReservaTerminais> getHistoricoReservasTerminais(Homologacoes h);
    public void addHistoricoReservaEquipamentosAdicionais (HistoricoReservaEquipamentosAdicionais h);
    public List<HistoricoReservaEquipamentosAdicionais> getHistoricoReservasEquipamentosAdicionais(Homologacoes h);
    public void addHistoricoReservaCartoesContas (HistoricoReservaCartoesContas h);
    public List<HistoricoReservaCartoesContas> getHistoricoReservasCartoesContas(Homologacoes h);
    public void addHistoricoReservaCartoesCredito (HistoricoReservaCartoesCredito h);
    public List<HistoricoReservaCartoesCredito> getHistoricoReservasCartoesCredito(Homologacoes h);
    public void addHistoricoReservaUsuario (HistoricoReservaUsuarios h);
    public List<HistoricoReservaUsuarios> getHistoricoReservasUsuarios(Homologacoes h);
}
