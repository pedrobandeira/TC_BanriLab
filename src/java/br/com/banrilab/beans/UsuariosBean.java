/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.beans;

import br.com.banrilab.dao.UsuariosDao;
import br.com.banrilab.dao.UsuariosDaoInterface;
import br.com.banrilab.entidades.Login;
import br.com.banrilab.entidades.ReservaUsuarios;
import br.com.banrilab.entidades.Usuarios;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class UsuariosBean implements Serializable {
    private Usuarios usuario = new Usuarios();
    @EJB
    private UsuariosDaoInterface usuarioDao;
    
    private List<Usuarios> usuarios = new ArrayList<>();
    private List<Usuarios> equipeTestes = new ArrayList<>();
    private List<Usuarios> equipeAnalistas = new ArrayList<>();
    private List<Usuarios> equipeCoordenadores = new ArrayList<>();
    private List<Usuarios> equipeDesenvolvimento = new ArrayList<>();
    private List<Usuarios> equipeAdmLaboratorio = new ArrayList<>();
    

    private Login login = new Login();
    private FacesContext fc;
    private HttpSession session;
    
    
    public UsuariosBean() {
    }
    
    public String adicionarUsuario() {
        if (usuario.getId() == null) {
            usuario.setDisponivel(true);
            usuario.setSenha(usuario.getMatricula());
        }
        usuarioDao.addUsuario(usuario);
        limpaCampos();
        return "usuarios";
    }
    
    public String removerUsuario(Usuarios u) {
        
        this.usuario = u;
        for (ReservaUsuarios reserva : usuarioDao.getReservasUsuarios()) {
            if (reserva.getUsuario().equals(u)) {
            usuarioDao.removeReservaUsuario(reserva);
            }
        }
        usuarioDao.removeUsuario(this.usuario);
        limpaCampos();
        return "usuarios";
    }
    
    public String carregarUsuario(Usuarios u) {
        this.usuario = u;
        return "editarusuario";
    }
    
    public String fecharEditar () {
        limpaCampos();
        return "usuarios";
    }
    public String exibirPerfil(Usuarios u) {
        if (u.getPerfil() == 1) {
            return "Admin Laboratório";
        } else if (u.getPerfil() == 2) {
            return "Coordenador de testes";
        } else if (u.getPerfil() == 3) {
            return "Analista de testes";
        } else if (u.getPerfil() == 4) {
            return "Testador";
        } else if (u.getPerfil() == 5) {
            return "Desenvolvedor";
        } 
        return "Não cadastrado";
    }
    
    public String exibirPerfilUsuarioLogado() {
        return exibirPerfil(login.getUsuarioLogado());
    }
    
    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios u) {
        this.usuario = u;
    }
    
    public String exibirDisponibilidade(Usuarios u) {
            if (u.isDisponivel()) {
                return "Disponível";
            }
            if (u.getReserva() != null) {
                if (!(u.getReserva().getHomologacao() == null)) {
                    return u.getReserva().getHomologacao().getAnalista().getNome();
                }
            }
        return "Não reservável";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.usuario);
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
        final UsuariosBean other = (UsuariosBean) obj;
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }

    public List<Usuarios> getUsuarios() {
        this.usuarios = usuarioDao.getUsuarios();
        return usuarios;
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuarios> getEquipeTestes() {
        this.equipeTestes = usuarioDao.getEquipeTestes();
        return equipeTestes;
    }

    public List<Usuarios> getEquipeDesenvolvimento() {
        this.equipeDesenvolvimento = usuarioDao.getEquipeDesenvolvimento();
        return equipeDesenvolvimento;
    }

    public List<Usuarios> getEquipeAdmLaboratorio() {
        this.equipeAdmLaboratorio = usuarioDao.getEquipeAdminLaboratorio();
        return equipeAdmLaboratorio;
    }

    public List<Usuarios> getEquipeAnalistas() {
        this.equipeAnalistas= usuarioDao.getEquipeAnalistas();
        return equipeAnalistas;
    }

    public void setEquipeAnalistas(List<Usuarios> equipeAnalistas) {
        this.equipeAnalistas = equipeAnalistas;
    }

    public List<Usuarios> getEquipeCoordenadores() {
        this.equipeCoordenadores = usuarioDao.getEquipeCoordenadores();
        return equipeCoordenadores;
    }

    public void setEquipeCoordenadores(List<Usuarios> equipeCoordenadores) {
        this.equipeCoordenadores = equipeCoordenadores;
    }
    
    public boolean isTestador(Usuarios usuario) {
        if (usuario.getPerfil() == 4) {
            return true;
        }
        return false;
    }
    
    
    
    public void limpaCampos() {
        this.usuario.setId(null);
        this.usuario.setMatricula(null);
        this.usuario.setNome(null);
        this.usuario.setPerfil(null);
        this.usuario.setSenha(null);
        this.usuario.setEmail(null);
    }
    
    
    public String validaLogin() {
        
        String matricula = usuario.getMatricula();
        String senha = usuario.getSenha();
        this.usuario.setMatricula(null);
        this.usuario.setSenha(null);
        this.login.setLoginErro(false);
        
        this.fc = FacesContext.getCurrentInstance();
        this.session = (HttpSession) fc.getExternalContext().getSession(false);
       
        List<Usuarios> listaUsuarios = new ArrayList<>();
        listaUsuarios = usuarioDao.getUsuarios();
      
        for (Usuarios user: listaUsuarios) {
            if (user.getMatricula().equals(matricula) && user.getSenha().equals(senha)) {
                this.login.setLoginErro(false);
                login.setUsuarioLogado(user);
                login.setLoginRealizado(true);
                session.setAttribute("usuario", login.getUsuarioLogado());
                session.setAttribute("id", login.getUsuarioLogado().getId());
                session.setAttribute("perfil", login.getUsuarioLogado().getPerfil());
                System.out.println("Perfil user logado: "+login.getUsuarioLogado().getPerfil());
                switch(login.getUsuarioLogado().getPerfil()) {
                    case 1: return "banrilab/adminlab/index";
                    case 2: return "banrilab/coordenadortestes/index";
                    case 3: return "banrilab/analistatestes/index";
                    case 4: return "banrilab/testador/index";   
                    case 5: return "banrilab/desenvolvedor/index";    
                }
            }
        }
        this.login.setLoginErro(true);
        return "login";
    }
    
    public boolean exibeAlertaLogin() {
        return login.isLoginErro();
    }
    
    public String exibeNomeUsuarioLogado() {
        System.out.println("Nome: "+login.getUsuarioLogado().getNome());
        return login.getUsuarioLogado().getNome();
    }
    
    public String logout() {
        login.setUsuarioLogado(null);
        session.setAttribute("id", null);
        return "/login";
    }
    
    public void limpaAlertaLogin() {
        login.setLoginErro(false);
    }
    
}
