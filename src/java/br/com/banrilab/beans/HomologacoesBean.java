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
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


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
    
    private List<String> destinatariosEmail = new ArrayList<>();
    private String conteudoEmail;
    private String assuntoEmail;
    
    private HistoricoHomologacaoCiclos historicoCiclos = new HistoricoHomologacaoCiclos();
    
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
            this.homologacao.setStatus(1);
            homologacaoDao.addHomologacao(homologacao);
            for (Usuarios coordenador: homologacaoDao.getEquipeCoordenadores()) {
                destinatariosEmail.add(coordenador.getEmail());
                System.out.println("email: "+coordenador.getEmail());
            }
            assuntoEmail = "Notificação sistema BanriLab: Nova homologação solicitada";
            conteudoEmail = "Prezado coordenador(a) de testes: "
                    + "\n"
                    + "\n"
                    +"Uma nova homologação foi solicitada no sistema BanriLab e necessita de sua atenção. "
                    + "\n"
                    + "\n"
                    + "Solicitante: "+homologacao.getSolicitante().getNome()
                    + "\n"
                    + "\n"
                    + "Sistema: "+homologacao.getSistema().getNome()
                    + "\n"
                    + "\n"
                    + "Versão: "+homologacao.getVersaoSistema()
                    + "\n"
                    + "\n"
                    + "\n"
                    + "Para ver maiores detalhes e para fazer a liberação da homologação, acesse a ferramenta BanriLab.";
            //enviaEmail();
        } else {
            homologacaoDao.addHomologacao(homologacao);
        }
        limpaCampos();
        return "homologacoes";
    }
    
    public String cancelarHomologacao() {
        this.homologacao.setStatus(5);
	this.homologacao.setDataFim(retornaDataAtual());
	this.homologacao.setAutorizador(carregaUsuarioAtivo());
        homologacaoDao.addHomologacao(homologacao);
        destinatariosEmail.add(homologacao.getSolicitante().getEmail());       
        assuntoEmail = "Notificação sistema BanriLab: Homologação cancelada";
        conteudoEmail = "Prezado analista de sistemas, "
                    + "\n"
                    + "\n"
                    +"Uma homologação solicitada por você no sistema BanriLab foi cancelada. "
                    + "\n"
                    + "\n"
                    + "Sistema: "+homologacao.getSistema().getNome()
                    + "\n"
                    + "\n"
                    + "Versão: "+homologacao.getVersaoSistema()
                    + "\n"
                    + "\n"
                    + "Cancelada por: "+homologacao.getAutorizador().getNome()
                    + "\n"
                    + "\n"
                    + "\n"
                    + "Para ver maiores detalhes ou para solicitar uma nova homologação, acesse a ferramenta BanriLab.";
        //enviaEmail();
        limpaCampos();
        return "homologacoes";
    }
    
    public String liberarHomologacao() {
        this.homologacao.setStatus(2);
        this.homologacao.setDataAutorizacao(retornaDataAtual());
	this.homologacao.setAutorizador(carregaUsuarioAtivo());
        this.homologacao.setCiclo(1);
        homologacaoDao.addHomologacao(homologacao);
        destinatariosEmail.add(homologacao.getAnalista().getEmail());
        assuntoEmail = "Notificação sistema BanriLab: Nova homologação autorizada";
        conteudoEmail = "Prezado analista de testes "+homologacao.getAnalista().getNome()+":"
                    + "\n"
                    + "\n"
                    +"Uma homologação foi autorizada e designada para você no sistema BanriLab. "
                    + "\n"
                    + "\n"
                    + "Solicitante: "+homologacao.getSolicitante().getNome()
                    + "\n"
                    + "\n"
                    + "Sistema: "+homologacao.getSistema().getNome()
                    + "\n"
                    + "\n"
                    + "Versão: "+homologacao.getVersaoSistema()
                    + "\n"
                    + "\n"
                    + "Autorizador: "+homologacao.getAutorizador().getNome()
                    + "\n"
                    + "\n"
                    + "\n"
                    + "Para ver maiores detalhes ou para fazer a abertura da homologação, acesse a ferramenta BanriLab.";
        //enviaEmail();
        limpaCampos();
        return "homologacoes";
    }
    
    public String abrirHomologacao() {
        this.homologacao.setStatus(3);
        this.homologacao.setDataAbertura(retornaDataAtual());
        homologacaoDao.addHomologacao(homologacao);
        if (!homologacao.getReservasTestadores().isEmpty()) {
        String ambienteHomologacao = "";
        String testadores = "";
        if (verificaPossuiReservaTestadores()) {
            for (ReservaUsuarios testador: homologacao.getReservasTestadores()) {
                testadores = testadores+"\n"+testador.getUsuario().getNome();
            }
        }
        if (verificaPossuiReservaServidores()) {
            ambienteHomologacao = "Servidores: ";
            for (ReservaServidores r: homologacao.getReservasServidores()) {
                if (r.getTestador() == null) {
                    ambienteHomologacao = ambienteHomologacao+"\n "+r.getServidor().getNome()+" alocado para todos os testadores";
                } else {
                    ambienteHomologacao = ambienteHomologacao+"\n "+r.getServidor().getNome()+" alocado para "+r.getTestador().getUsuario().getNome();
                }
            }
        }
        if (verificaPossuiReservaTerminais()) {
            ambienteHomologacao = ambienteHomologacao+"\n\n Terminais: ";
            for (ReservaTerminais r: homologacao.getReservasTerminais()) {
                if (r.getTestador() == null) {
                    ambienteHomologacao = ambienteHomologacao+"\n "+r.getTerminal().getNome()+" alocado para todos os testadores";
                } else {
                    ambienteHomologacao = ambienteHomologacao+"\n "+r.getTerminal().getNome()+" alocado para "+r.getTestador().getUsuario().getNome();
                }
            }
        }
        if (verificaPossuiReservaAtms()) {
            ambienteHomologacao = ambienteHomologacao+"\n\n ATMs: ";
            for (ReservaAtms r: homologacao.getReservasAtms()) {
                if (r.getTestador() == null) {
                    ambienteHomologacao = ambienteHomologacao+"\n "+r.getAtm().getNome()+" alocado para todos os testadores";
                } else {
                    ambienteHomologacao = ambienteHomologacao+"\n "+r.getAtm().getNome()+" alocado para "+r.getTestador().getUsuario().getNome();
                }
            }
        }
        if (verificaPossuiReservaEquipamentosAdicionais()) {
            ambienteHomologacao = ambienteHomologacao+"\n\n Equipamentos adicionais: ";
            for (ReservaEquipamentosAdicionais r: homologacao.getReservasEquipamentosAdicionais()) {
                if (r.getTestador() == null) {
                    ambienteHomologacao = ambienteHomologacao+"\n "+r.getEquipamento().getNome()+" alocado para todos os testadores";
                } else {
                    ambienteHomologacao = ambienteHomologacao+"\n "+r.getEquipamento().getNome()+" alocado para "+r.getTestador().getUsuario().getNome();
                }
            }
        }
        if (verificaPossuiReservaCartoesContas()) {
            ambienteHomologacao = ambienteHomologacao+"\n\n Cartões de contas: ";
            for (ReservaCartoesContas r: homologacao.getReservasCartoesContas()) {
                if (r.getTestador() == null) {
                    ambienteHomologacao = ambienteHomologacao+"\n "+r.getCartaoConta().getNome()+" alocado para todos os testadores";
                } else {
                    ambienteHomologacao = ambienteHomologacao+"\n "+r.getCartaoConta().getNome()+" alocado para "+r.getTestador().getUsuario().getNome();
                }
            }
        }
        if (verificaPossuiReservaCartoesCredito()) {
            ambienteHomologacao = ambienteHomologacao+"\n\n Cartões de crédito: ";
            for (ReservaCartoesCredito r: homologacao.getReservasCartoesCreditos()) {
                if (r.getTestador() == null) {
                    ambienteHomologacao = ambienteHomologacao+"\n "+r.getCartaoCredito().getNome()+" alocado para todos os testadores";
                } else {
                    ambienteHomologacao = ambienteHomologacao+"\n "+r.getCartaoCredito().getNome()+" alocado para "+r.getTestador().getUsuario().getNome();
                }
            }
        }
        for (ReservaUsuarios reservaTestador: homologacao.getReservasTestadores()) {
            destinatariosEmail.add(reservaTestador.getUsuario().getEmail());
            System.out.println("email: "+reservaTestador.getUsuario().getEmail());
        }
            DateFormat formataData = DateFormat.getDateInstance(); 

            assuntoEmail = "Notificação sistema BanriLab: Nova homologação em andamento";
            conteudoEmail = "Prezado testador(a):"
                    + "\n"
                    + "\n"
                    +"Uma homologação foi aberta no sistema BanriLab e a execução de testes foi designada a você. "
                    + "\n"
                    + "\n"
                    + "Solicitante: "+homologacao.getSolicitante().getNome()
                    + "\n"
                    + "\n"
                    + "Sistema: "+homologacao.getSistema().getNome()
                    + "\n"
                    + "\n"
                    + "Versão: "+homologacao.getVersaoSistema()
                    + "\n"
                    + "\n"
                    + "Data início de testes: "+formataData.format(homologacao.getDataInicioExecucao())
                    + "\n"
                    + "\n"
                    + "Data prevista para fim de testes: "+formataData.format(homologacao.getDataFim())
                    + "\n"
                    + "\n"
                    + "Analista de testes responsável: "+homologacao.getAnalista().getNome()
                    + "\n"
                    + "\n"
                    + "Testadores: "
                    + testadores
                    + "\n"
                    + "\n"
                    + "Ambiente reservado para testes: "
                    + "\n"
                    + "\n"
                    + ambienteHomologacao
                    + "\n"
                    + "\n"
                    + "\n"
                    + "Para ver maiores detalhes sobre a homologação, acesse a ferramenta BanriLab.";
           //enviaEmail();
        }        
        limpaCampos();
        return "homologacoes";
    }
    
    public String encerrarCicloHomologacao() {
        List<HistoricoHomologacaoCiclos> his = new ArrayList<>();
        his = homologacao.getHistoricoCiclos();
        historicoCiclos.setCiclo(homologacao.getCiclo());
        historicoCiclos.setDataFimCiclo(retornaDataAtual());
        historicoCiclos.setHomologacao(homologacao);
        homologacaoDao.addHistoricoHomologacaoCiclo(historicoCiclos);
        his.add(historicoCiclos);
        homologacao.setCiclo(homologacao.getCiclo()+1);
        homologacao.setHistoricoCiclos(his);
        homologacaoDao.addHomologacao(homologacao);
        return "editarhomologacao";
    }
    
    public String encerrarHomologacao() {
        this.homologacao.setStatus(4);
        this.homologacao.setDataFim(retornaDataAtual());
        boolean cicloJaEncerrado = false;
        for(HistoricoHomologacaoCiclos hist : homologacao.getHistoricoCiclos()) {
            if (hist.getHomologacao().equals(homologacao) && hist.getCiclo().equals(homologacao.getCiclo())) {
                cicloJaEncerrado = true;
            }
        }
        if (!cicloJaEncerrado) {
            List<HistoricoHomologacaoCiclos> his = new ArrayList<>();
            his = homologacao.getHistoricoCiclos();
            historicoCiclos.setCiclo(homologacao.getCiclo());
            historicoCiclos.setDataFimCiclo(retornaDataAtual());
            historicoCiclos.setHomologacao(homologacao);
            homologacaoDao.addHistoricoHomologacaoCiclo(historicoCiclos);
            his.add(historicoCiclos);
            homologacao.setHistoricoCiclos(his);
        }
        if (!(homologacao.getReservasServidores().isEmpty())) {
            for (ReservaServidores reservaServidor: homologacao.getReservasServidores()) {
                reservaServidor.getServidor().setDisponivel(true);
                reservaServidor.getServidor().setReserva(null);
                homologacaoDao.addServidor(reservaServidor.getServidor());
            }
            //homologacao.getReservasServidores().clear();
        }
        if (!(homologacao.getReservasAtms().isEmpty())) {
            for (ReservaAtms reservaAtm: homologacao.getReservasAtms()) {
                reservaAtm.getAtm().setDisponivel(true);
                reservaAtm.getAtm().setReserva(null);
                homologacaoDao.addAtms(reservaAtm.getAtm());
            }
            //homologacao.getReservasAtms().clear();
        }
        if (!(homologacao.getReservasTerminais().isEmpty())) {
            for (ReservaTerminais reservaTerminal: homologacao.getReservasTerminais()) {
                reservaTerminal.getTerminal().setDisponivel(true);
                reservaTerminal.getTerminal().setReserva(null);
                homologacaoDao.addTerminais(reservaTerminal.getTerminal());
            }
            //homologacao.getReservasTerminais().clear();
        }
        if (!(homologacao.getReservasEquipamentosAdicionais().isEmpty())) {
            for (ReservaEquipamentosAdicionais reservaEqp: homologacao.getReservasEquipamentosAdicionais()) {
                reservaEqp.getEquipamento().setDisponivel(true);
                reservaEqp.getEquipamento().setReserva(null);
                homologacaoDao.addEquipamentosAdicionais(reservaEqp.getEquipamento());
            }
            //homologacao.getReservasEquipamentosAdicionais().clear();
        }
        if (!(homologacao.getReservasCartoesCreditos().isEmpty())) {
            for (ReservaCartoesCredito reservaCartaoCredito: homologacao.getReservasCartoesCreditos()) {
                reservaCartaoCredito.getCartaoCredito().setDisponivel(true);
                reservaCartaoCredito.getCartaoCredito().setReserva(null);
                homologacaoDao.addCartoesCredito(reservaCartaoCredito.getCartaoCredito());
            }
            //homologacao.getReservasCartoesCreditos().clear();
        }
        if (!(homologacao.getReservasCartoesContas().isEmpty())) {
            for (ReservaCartoesContas reservaCartaoConta: homologacao.getReservasCartoesContas()) {
                reservaCartaoConta.getCartaoConta().setDisponivel(true);
                reservaCartaoConta.getCartaoConta().setReserva(null);
                homologacaoDao.addCartoesContas(reservaCartaoConta.getCartaoConta());
            }
            //homologacao.getReservasCartoesContas().clear();
        }
        if (!(homologacao.getReservasTestadores().isEmpty())) {
            for (ReservaUsuarios reservaTestador: homologacao.getReservasTestadores()) {
                reservaTestador.getUsuario().setDisponivel(true);
                reservaTestador.getUsuario().setReserva(null);
                homologacaoDao.addUsuarios(reservaTestador.getUsuario());
            }
            //homologacao.getReservasTestadores().clear();
        }
        homologacaoDao.addHomologacao(homologacao);
        for (Usuarios coordenador: homologacaoDao.getEquipeCoordenadores()) {
                destinatariosEmail.add(coordenador.getEmail());
            }
        for (ReservaUsuarios reservaTestador: homologacao.getReservasTestadores()) {
                destinatariosEmail.add(reservaTestador.getUsuario().getEmail());
                System.out.println("email: "+reservaTestador.getUsuario().getEmail());
            }
        destinatariosEmail.add(homologacao.getSolicitante().getEmail());
        assuntoEmail = "Notificação sistema BanriLab: Homologação concluída";
        conteudoEmail = "Prezado colaborador: "
                    + "\n"
                    + "\n"
                    +"Uma homologação foi concluída no sistema BanriLab. "
                    + "\n"
                    + "\n"
                    + "Solicitante: "+homologacao.getSolicitante().getNome()
                    + "\n"
                    + "\n"
                    + "Sistema: "+homologacao.getSistema().getNome()
                    + "\n"
                    + "\n"
                    + "Versão: "+homologacao.getVersaoSistema()
                    + "\n"
                    + "\n"
                    + "Autorizador: "+homologacao.getAutorizador().getNome()
                    + "\n"
                    + "\n"
                    + "Analista de testes: "+homologacao.getAnalista().getNome()
                    + "\n"
                    + "\n"
                    + "\n"
                    + "Para ver maiores detalhes, acesse a ferramenta BanriLab.";
        //enviaEmail();
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
        if (verificaAutorizadasEmAndamento(homologacao) && (homologacao.getAnalista().equals(carregaUsuarioAtivo())))
            return "editarhomologacao";
	return "visualizarhomologacao";
    }
    
    public boolean verificaPossuiAlgumaReservaEquipamento() {
        if (!verificaPossuiReservaAtms() && !verificaPossuiReservaCartoesContas()
                && !verificaPossuiReservaCartoesCredito() && !verificaPossuiReservaEquipamentosAdicionais()
                && !verificaPossuiReservaServidores() && !verificaPossuiReservaTerminais())
            return false;
        return true;
    }
    
    public boolean verificaPossuiAlgumaReservaEquipamentoOuTestador() {
        if (verificaPossuiAlgumaReservaEquipamento() || verificaPossuiReservaTestadores())
            return true;
        return false;
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
    
    public String retornaReservaTestadores() {
        return "homologacaotestadores";
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
    
    public String retornaEditarHomologacao() {
        return "editarhomologacao";
    }
    
    public String retornaAbrirHomologacao() {
        return "abrirhomologacao";
    }
    
    public String retornaTestadoresEquipamentos() {
        return "homologacaotestadoresequipamentos";
    }
    
    public String retornaVisualizarAmbienteHomologacao() {
        return "visualizarambientehomologacao";
    }
    
    public String retornaVisualizarDatasHomologacao() {
        return "visualizardatashomologacao";
    }
    
    public boolean verificaPossuiReservaTestadores() {
        if (this.homologacao.getReservasTestadores().isEmpty()) {
            return false;
        }
        return true;
    }
    
    public boolean verificaPossuiReservaServidores() {
        if (this.homologacao.getReservasServidores().isEmpty()) {
            return false;
        }
        return true;
    }
    
    public boolean verificaPossuiReservaTerminais() {
        if (this.homologacao.getReservasTerminais().isEmpty()) {
            return false;
        }
        return true;
    }
    
    public boolean verificaPossuiReservaAtms() {
        if (this.homologacao.getReservasAtms().isEmpty()) {
            return false;
        }
        return true;
    }
    
    public boolean verificaPossuiReservaEquipamentosAdicionais() {
        if (this.homologacao.getReservasEquipamentosAdicionais().isEmpty()) {
            return false;
        }
        return true;
    }
    
    public boolean verificaPossuiReservaCartoesContas() {
        if (this.homologacao.getReservasCartoesContas().isEmpty()) {
            return false;
        }
        return true;
    }
    
    public boolean verificaPossuiReservaCartoesCredito() {
        if (this.homologacao.getReservasCartoesCreditos().isEmpty()) {
            return false;
        }
        return true;
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
        if (h.getStatus() == null) return "Sem status";
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
    
    public boolean verificaEmAndamentoConcluidas (Homologacoes h) {
        if (((h.getStatus() == 4) || (h.getStatus() == 3))) 
                return true;
        return false;
    }
    
    public boolean verificaEmAndamento(Homologacoes h) {
        if ((h.getStatus() == 3) && (carregaUsuarioAtivo().equals(h.getAnalista()))) 
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
        this.homologacao.setDataInicioExecucao(null);
        this.historicoCiclos.setCiclo(null);
        this.historicoCiclos.setDataFimCiclo(null);
        this.historicoCiclos.setHomologacao(null);
        this.historicoCiclos.setId(null);
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
           reserva.setFinalidade("Homologação "+homologacao.getSistema().getNome()+" "+homologacao.getVersaoSistema());
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
    public String adicionarAlocacaoTestadorServidor(ReservaServidores r) {
        
        List<ReservaServidores> reservasServidores = new ArrayList<ReservaServidores>();
        reservasServidores = homologacao.getReservasServidores();
        reservasServidores.remove(r);
        reservasServidores.add(r);
        homologacao.setReservasServidores(reservasServidores);
        homologacaoDao.addReservaServidores(r);
        return "homologacaotestadoresequipamentos";
    }
    
    public String adicionarReservaAtmHomologacao(Atms a) {
           List<ReservaAtms> reservasAtms = new ArrayList<ReservaAtms>();
           ReservaAtms reserva = new ReservaAtms();
           reserva.setDataFim(homologacao.getDataFim());
           reserva.setDataInicio(retornaDataAtual());
           reserva.setFinalidade("Homologação "+homologacao.getSistema().getNome()+" "+homologacao.getVersaoSistema());
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
    
    public String adicionarAlocacaoTestadorAtm(ReservaAtms r) {
        List<ReservaAtms> reservasAtms = new ArrayList<ReservaAtms>();
        reservasAtms = homologacao.getReservasAtms();
        reservasAtms.remove(r);
        reservasAtms.add(r);
        homologacao.setReservasAtms(reservasAtms);
        homologacaoDao.addReservaAtms(r);
        return "homologacaotestadoresequipamentos";
    }
    
    public String adicionarReservaTerminalHomologacao(Terminais t) {
           List<ReservaTerminais> reservasTerminais = new ArrayList<ReservaTerminais>();
           ReservaTerminais reserva = new ReservaTerminais();
           reserva.setDataFim(homologacao.getDataFim());
           reserva.setDataInicio(retornaDataAtual());
           reserva.setFinalidade("Homologação "+homologacao.getSistema().getNome()+" "+homologacao.getVersaoSistema());
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
    
    public String adicionarAlocacaoTestadorTerminal(ReservaTerminais r) {
        List<ReservaTerminais> reservasTerminais = new ArrayList<ReservaTerminais>();
        reservasTerminais = homologacao.getReservasTerminais();
        reservasTerminais.remove(r);
        reservasTerminais.add(r);
        homologacao.setReservasTerminais(reservasTerminais);
        homologacaoDao.addReservaTerminais(r);
        return "homologacaotestadoresequipamentos";
    }
    public String adicionarReservaEquipamentoAdicionalHomologacao(EquipamentosAdicionais e) {
           List<ReservaEquipamentosAdicionais> reservasEquipamentosAdicionais = new ArrayList<ReservaEquipamentosAdicionais>();
           ReservaEquipamentosAdicionais reserva = new ReservaEquipamentosAdicionais();
           reserva.setDataFim(homologacao.getDataFim());
           reserva.setDataInicio(retornaDataAtual());
           reserva.setFinalidade("Homologação "+homologacao.getSistema().getNome()+" "+homologacao.getVersaoSistema());
           reserva.setHomologacao(homologacao);
           reserva.setEquipamento(e);
           reservasEquipamentosAdicionais = homologacao.getReservasEquipamentosAdicionais();
           reservasEquipamentosAdicionais.add(reserva);
           homologacao.setReservasEquipamentosAdicionais(reservasEquipamentosAdicionais);
           homologacaoDao.addReservaEquipamentosAdicionais(reserva);
           reserva.getEquipamento().setDisponivel(false);
           reserva.getEquipamento().setReserva(reserva);
    
           homologacaoDao.addEquipamentosAdicionais(reserva.getEquipamento());
          
        return "homologacaoequipamentosadicionais";
    }
    
    public String adicionarAlocacaoTestadorEquipamentoAdicional(ReservaEquipamentosAdicionais r) {
        List<ReservaEquipamentosAdicionais> reservasEquipamentosAdicionais = new ArrayList<ReservaEquipamentosAdicionais>();
        reservasEquipamentosAdicionais = homologacao.getReservasEquipamentosAdicionais();
        reservasEquipamentosAdicionais.remove(r);
        reservasEquipamentosAdicionais.add(r);
        homologacao.setReservasEquipamentosAdicionais(reservasEquipamentosAdicionais);
        homologacaoDao.addReservaEquipamentosAdicionais(r);
        return "homologacaotestadoresequipamentos";
    }
    
    public String adicionarReservaCartaoContaHomologacao(CartoesContas c) {
           List<ReservaCartoesContas> reservasCartoesContas = new ArrayList<ReservaCartoesContas>();
           ReservaCartoesContas reserva = new ReservaCartoesContas();
           reserva.setDataFim(homologacao.getDataFim());
           reserva.setDataInicio(retornaDataAtual());
           reserva.setFinalidade("Homologação "+homologacao.getSistema().getNome()+" "+homologacao.getVersaoSistema());
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
    
    public String adicionarAlocacaoTestadorCartaoConta(ReservaCartoesContas r) {
        List<ReservaCartoesContas> reservasCartoesContas = new ArrayList<ReservaCartoesContas>();
        reservasCartoesContas = homologacao.getReservasCartoesContas();
        reservasCartoesContas.remove(r);
        reservasCartoesContas.add(r);
        homologacao.setReservasCartoesContas(reservasCartoesContas);
        homologacaoDao.addReservaCartoesContas(r);
        return "homologacaotestadoresequipamentos";
    }
    
    public String adicionarReservaCartaoCreditoHomologacao(CartoesCredito c) {
           List<ReservaCartoesCredito> reservasCartoesCredito = new ArrayList<ReservaCartoesCredito>();
           ReservaCartoesCredito reserva = new ReservaCartoesCredito();
           reserva.setDataFim(homologacao.getDataFim());
           reserva.setDataInicio(retornaDataAtual());
           reserva.setFinalidade("Homologação "+homologacao.getSistema().getNome()+" "+homologacao.getVersaoSistema());
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
    
    public String adicionarAlocacaoTestadorCartaoCredito(ReservaCartoesCredito r) {
        List<ReservaCartoesCredito> reservasCartoesCredito = new ArrayList<ReservaCartoesCredito>();
        reservasCartoesCredito = homologacao.getReservasCartoesCreditos();
        reservasCartoesCredito.remove(r);
        reservasCartoesCredito.add(r);
        homologacao.setReservasCartoesCreditos(reservasCartoesCredito);
        homologacaoDao.addReservaCartoesCredito(r);
        return "homologacaotestadoresequipamentos";
    }
    
    public String adicionarReservaTestadorHomologacao(Usuarios u) {
           List<ReservaUsuarios> reservasTestadores = new ArrayList<ReservaUsuarios>();
           ReservaUsuarios reserva = new ReservaUsuarios();
           reserva.setDataFim(homologacao.getDataFim());
           reserva.setDataInicio(retornaDataAtual());
           reserva.setFinalidade("Homologação "+homologacao.getSistema().getNome()+" "+homologacao.getVersaoSistema());
           reserva.setHomologacao(homologacao);
           reserva.setUsuario(u);
           reservasTestadores = homologacao.getReservasTestadores();
           reservasTestadores.add(reserva);
           homologacao.setReservasTestadores(reservasTestadores);
           homologacaoDao.addReservaUsuarios(reserva);
           reserva.getUsuario().setDisponivel(false);
           reserva.getUsuario().setReserva(reserva);
    
           homologacaoDao.addUsuarios(reserva.getUsuario());
          
        return "homologacaotestadores";
    }
    
    public void enviaEmail() {
        Properties props = new Properties();
            /** Parâmetros de conexão com servidor Gmail */
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication("banrilab@gmail.com", "banrilab1397");
                             }
                        });

            /** Ativa Debug para sessão */
            session.setDebug(true);

            try {

                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress("banrilab@gmail.com")); //Remetente
                  String listaEmails = "";
                  for (String email: destinatariosEmail) {
                      listaEmails = email+", "+listaEmails;
                      System.out.println("lista mails: "+listaEmails);
                  }
                  Address[] toUser = InternetAddress //Destinatário(s)
                             .parse(listaEmails);  

                  message.setRecipients(Message.RecipientType.TO, toUser);
                  message.setSubject(assuntoEmail);//Assunto
                  message.setText(conteudoEmail);
                  /**Método para enviar a mensagem criada*/
                  Transport.send(message);
                  destinatariosEmail.clear();
                  conteudoEmail = "";
                  assuntoEmail = "";
                  System.out.println("Feito!!!");

             } catch (MessagingException e) {
                  throw new RuntimeException(e);
            }
    }
}