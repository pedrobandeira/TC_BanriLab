/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;

import br.com.banrilab.dao.HomologacoesDao;
import br.com.banrilab.dao.HomologacoesDaoInterface;
import br.com.banrilab.entidades.Homologacoes;
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
    
}