/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;

import br.com.banrilab.dao.HomologacoesDao;
import br.com.banrilab.dao.HomologacoesDaoInterface;
import br.com.banrilab.entidades.Atms;
import br.com.banrilab.entidades.CartoesContas;
import br.com.banrilab.entidades.CartoesCredito;
import br.com.banrilab.entidades.EquipamentosAdicionais;
import br.com.banrilab.entidades.Homologacoes;
import br.com.banrilab.entidades.ReservaAtms;
import br.com.banrilab.entidades.ReservaCartoesContas;
import br.com.banrilab.entidades.ReservaCartoesCredito;
import br.com.banrilab.entidades.ReservaEquipamentosAdicionais;
import br.com.banrilab.entidades.ReservaServidores;
import br.com.banrilab.entidades.ReservaTerminais;
import br.com.banrilab.entidades.Servidores;
import br.com.banrilab.entidades.Terminais;
import br.com.banrilab.entidades.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Pedro
 */
@ManagedBean
@SessionScoped
public class HomologacoesBean implements Serializable {
    private Homologacoes homologacao = new Homologacoes();
    @EJB
    private HomologacoesDaoInterface homologacaoDao;
    
    private List<Homologacoes> homologacoes = new ArrayList<>();
	private List<Homologacoes> homologacoesAbertas = new ArrayList<>();
    
    public HomologacoesBean() {
    }
    
    public Usuarios carregaUsuarioAtivo() {
        HttpSession httpsession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Usuarios usuarioSessao = (Usuarios) httpsession.getAttribute("usuario");
        return usuarioSessao;
    }
    
    public String adicionarHomologacao() {
        System.out.println("entrou no add bean");
        if (this.homologacao.getId() == null) {
            this.homologacao.setDataSolicitacao(retornaDataAtual());
			this.homologacao.setSolicitante(carregaUsuarioAtivo());
        }
        homologacaoDao.addHomologacao(homologacao);
        limpaCampos();
        return "homologacoes";
    }
    
    public String cancelarHomologacao() {
        this.homologacao.setStatus(5);
	this.homologacao.setDataFim(retornaDataAtual());
	this.homologacao.setAutorizador(carregaUsuarioAtivo());
        homologacaoDao.addHomologacao(homologacao);
        limpaCampos();
        return "homologacoes";
    }
    
    public String liberarHomologacao() {
        this.homologacao.setStatus(2);
		this.homologacao.setDataAutorizacao(retornaDataAtual());
		this.homologacao.setAutorizador(carregaUsuarioAtivo());
        homologacaoDao.addHomologacao(homologacao);
        limpaCampos();
        return "homologacoes";
    }
    
    public String removerHomologacao(Homologacoes h) {
        this.homologacao = h;
        homologacaoDao.removeHomologacao(this.homologacao);
        limpaCampos();
        return "homologacoes";
    }
    
    public String carregarHomologacao(Homologacoes h) {
        this.homologacao = h;
        return "editarhomologacao";
    }
	
    public String visualizarHomologacao(Homologacoes h) {
        this.homologacao = h;
	return "visualizarhomologacao";
    }
    
    public String retornaReservaAmbiente() {
        return "homologacaoambiente";
    }
    
    public String retornaReservaServidores() {
        return "homologacaoservidores";
    }
    
    public String retornaReservaAtms() {
        return "homologacaoatms";
    }
    
    public String retornaReservaTerminais() {
        return "homologacaoterminais";
    }
    
    public String retornaReservaEquipamentosAdicionais() {
        return "homologacaoequipamentosadicionais";
    }
    
    public String retornaReservaCartoesContas() {
        return "homologacaocartoescontas";
    }
    
    public String retornaReservaCartoesCredito() {
        return "homologacaocartoescredito";
    }
    public String fecharEditar () {
        limpaCampos();
        return "homologacoes";
    }
    
    public Homologacoes getHomologacao() {
        return homologacao;
    }

    public void setHomologacao(Homologacoes h) {
        this.homologacao = h;
    }
    
    public String exibirStatus(Homologacoes h) {
        if (h.getStatus() == 1) return "Solicitada";
        if (h.getStatus() == 2) return "Autorizada";
        if (h.getStatus() == 3) return "Em andamento";
        if (h.getStatus() == 4) return "Concluída";
        return "Cancelada";
    }
    
    public boolean verificaSolicitadas (Homologacoes h) {
        if ((h.getStatus() == 1) && (carregaUsuarioAtivo().equals(h.getSolicitante()))) return true;
        return false;
    }
	
	public boolean verificaSolicitadasAutorizadas (Homologacoes h) {
        if ((h.getStatus() == 1) || (h.getStatus() == 2)) return true;
        return false;
    }
	
	public boolean verificaAutorizadasEmAndamento (Homologacoes h) {
        if (((h.getStatus() == 2) || (h.getStatus() == 3)) && (carregaUsuarioAtivo().equals(h.getAnalista()))) 
                return true;
        return false;
    }
	
	public Date retornaDataAtual() {
        Date data = new Date();
        return data;
    }
    
    public void limpaCampos() {
        this.homologacao.setAnalista(null);
        this.homologacao.setAutorizador(null);
        this.homologacao.setCiclo(null);
        this.homologacao.setDataFim(null);
        this.homologacao.setDataAbertura(null);
        this.homologacao.setDataAutorizacao(null);
        this.homologacao.setDataSolicitacao(null);
        this.homologacao.setId(null);
        this.homologacao.setReservasAtms(null);
        this.homologacao.setReservasCartoesContas(null);
        this.homologacao.setReservasCartoesCreditos(null);
        this.homologacao.setReservasEquipamentosAdicionais(null);
        this.homologacao.setReservasServidores(null);
        this.homologacao.setReservasTerminais(null);
        this.homologacao.setReservasTestadores(null);
        this.homologacao.setSistema(null);
        this.homologacao.setSolicitante(null);
        this.homologacao.setStatus(null);
        this.homologacao.setVersaoSistema(null);
        this.homologacao.setRequisitos(null);
        this.homologacao.setObservacoes(null);
    }

    public List<Homologacoes> getHomologacoes() {
        this.homologacoes = homologacaoDao.getHomologacoes();
        return homologacoes;
    }

    public void setHomologacoes(List<Homologacoes> homologacoes) {
        this.homologacoes = homologacoes;
    }

	public List<Homologacoes> getHomologacoesAbertas() {
        this.homologacoesAbertas = homologacaoDao.getHomologacoesAbertas();
        return homologacoesAbertas;
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.homologacao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HomologacoesBean other = (HomologacoesBean) obj;
        if (!Objects.equals(this.homologacao, other.homologacao)) {
            return false;
        }
        return true;
    }
    
    public String adicionarReservaServidorHomologacao(Servidores s) {
           List<ReservaServidores> reservasServidores = new ArrayList<ReservaServidores>();
           ReservaServidores reserva = new ReservaServidores();
           reserva.setDataFim(homologacao.getDataFim());
           reserva.setDataInicio(retornaDataAtual());
           reserva.setFinalidade("Homologação "+homologacao.getSistema().getNome()+" versão "+homologacao.getVersaoSistema());
           reserva.setHomologacao(homologacao);
           reserva.setServidor(s);
           reservasServidores = homologacao.getReservasServidores();
           reservasServidores.add(reserva);
           homologacao.setReservasServidores(reservasServidores);
           homologacaoDao.addReservaServidores(reserva);
           reserva.getServidor().setDisponivel(false);
           reserva.getServidor().setReserva(reserva);
    
           homologacaoDao.addServidor(reserva.getServidor());
          
        return "homologacaoservidores";
    }
    
    public String adicionarReservaAtmHomologacao(Atms a) {
           List<ReservaAtms> reservasAtms = new ArrayList<ReservaAtms>();
           ReservaAtms reserva = new ReservaAtms();
           reserva.setDataFim(homologacao.getDataFim());
           reserva.setDataInicio(retornaDataAtual());
           reserva.setFinalidade("Homologação "+homologacao.getSistema().getNome()+" versão "+homologacao.getVersaoSistema());
           reserva.setHomologacao(homologacao);
           reserva.setAtm(a);
           reservasAtms = homologacao.getReservasAtms();
           reservasAtms.add(reserva);
           homologacao.setReservasAtms(reservasAtms);
           homologacaoDao.addReservaAtms(reserva);
           reserva.getAtm().setDisponivel(false);
           reserva.getAtm().setReserva(reserva);
    
           homologacaoDao.addAtms(reserva.getAtm());
          
        return "homologacaoatms";
    }
    
    public String adicionarReservaTerminalHomologacao(Terminais t) {
           List<ReservaTerminais> reservasTerminais = new ArrayList<ReservaTerminais>();
           ReservaTerminais reserva = new ReservaTerminais();
           reserva.setDataFim(homologacao.getDataFim());
           reserva.setDataInicio(retornaDataAtual());
           reserva.setFinalidade("Homologação "+homologacao.getSistema().getNome()+" versão "+homologacao.getVersaoSistema());
           reserva.setHomologacao(homologacao);
           reserva.setTerminal(t);
           reservasTerminais = homologacao.getReservasTerminais();
           reservasTerminais.add(reserva);
           homologacao.setReservasTerminais(reservasTerminais);
           homologacaoDao.addReservaTerminais(reserva);
           reserva.getTerminal().setDisponivel(false);
           reserva.getTerminal().setReserva(reserva);
    
           homologacaoDao.addTerminais(reserva.getTerminal());
          
        return "homologacaoterminais";
    }
    
    public String adicionarReservaEquipamentoAdicionalHomologacao(EquipamentosAdicionais e) {
           List<ReservaEquipamentosAdicionais> reservasEquipamentosAdicionais = new ArrayList<ReservaEquipamentosAdicionais>();
           ReservaEquipamentosAdicionais reserva = new ReservaEquipamentosAdicionais();
           reserva.setDataFim(homologacao.getDataFim());
           reserva.setDataInicio(retornaDataAtual());
           reserva.setFinalidade("Homologação "+homologacao.getSistema().getNome()+" versão "+homologacao.getVersaoSistema());
           reserva.setHomologacao(homologacao);
           reserva.setEquipamentoAdicional(e);
           reservasEquipamentosAdicionais = homologacao.getReservasEquipamentosAdicionais();
           reservasEquipamentosAdicionais.add(reserva);
           homologacao.setReservasEquipamentosAdicionais(reservasEquipamentosAdicionais);
           homologacaoDao.addReservaEquipamentosAdicionais(reserva);
           reserva.getEquipamentoAdicional().setDisponivel(false);
           reserva.getEquipamentoAdicional().setReserva(reserva);
    
           homologacaoDao.addEquipamentosAdicionais(reserva.getEquipamentoAdicional());
          
        return "homologacaoequipamentosadicionais";
    }
    
    public String adicionarReservaCartaoContaHomologacao(CartoesContas c) {
           List<ReservaCartoesContas> reservasCartoesContas = new ArrayList<ReservaCartoesContas>();
           ReservaCartoesContas reserva = new ReservaCartoesContas();
           reserva.setDataFim(homologacao.getDataFim());
           reserva.setDataInicio(retornaDataAtual());
           reserva.setFinalidade("Homologação "+homologacao.getSistema().getNome()+" versão "+homologacao.getVersaoSistema());
           reserva.setHomologacao(homologacao);
           reserva.setCartaoConta(c);
           reservasCartoesContas = homologacao.getReservasCartoesContas();
           reservasCartoesContas.add(reserva);
           homologacao.setReservasCartoesContas(reservasCartoesContas);
           homologacaoDao.addReservaCartoesContas(reserva);
           reserva.getCartaoConta().setDisponivel(false);
           reserva.getCartaoConta().setReserva(reserva);
    
           homologacaoDao.addCartoesContas(reserva.getCartaoConta());
          
        return "homologacaocartoescontas";
    }
    
    public String adicionarReservaCartaoCreditoHomologacao(CartoesCredito c) {
           List<ReservaCartoesCredito> reservasCartoesCredito = new ArrayList<ReservaCartoesCredito>();
           ReservaCartoesCredito reserva = new ReservaCartoesCredito();
           reserva.setDataFim(homologacao.getDataFim());
           reserva.setDataInicio(retornaDataAtual());
           reserva.setFinalidade("Homologação "+homologacao.getSistema().getNome()+" versão "+homologacao.getVersaoSistema());
           reserva.setHomologacao(homologacao);
           reserva.setCartaoCredito(c);
           reservasCartoesCredito = homologacao.getReservasCartoesCreditos();
           reservasCartoesCredito.add(reserva);
           homologacao.setReservasCartoesCreditos(reservasCartoesCredito);
           homologacaoDao.addReservaCartoesCredito(reserva);
           reserva.getCartaoCredito().setDisponivel(false);
           reserva.getCartaoCredito().setReserva(reserva);
    
           homologacaoDao.addCartoesCredito(reserva.getCartaoCredito());
          
        return "homologacaocartoescredito";
    }
}