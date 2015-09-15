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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Pedro
 */
@Stateless
public class HomologacoesDao implements HomologacoesDaoInterface {
    @PersistenceContext(unitName = "BanriLabPU2")
    EntityManager entityManager;
    
    @Override
    public void addHomologacao (Homologacoes h) {
        
        // EntityManager entityManager = new HibernateUtil().getEntityManager();
        System.out.println("entrou no add dao");
        if(h.getId() == null)
            entityManager.persist(h);
        else
            entityManager.merge(h);
    }
    
    @Override
    public void removeHomologacao (Homologacoes h) {
        //EntityManager entityManager = new HibernateUtil().getEntityManager();

        Homologacoes homologacaoARemover = entityManager.merge(h);
        entityManager.remove(homologacaoARemover);
    }
    
    @Override
    public List<Homologacoes> getHomologacoes() {
       
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Homologacoes.class));
        cq.orderBy(entityManager.getCriteriaBuilder().desc(cq.from(Homologacoes.class).get("dataSolicitacao")));
        return entityManager.createQuery(cq).getResultList();
    }
	
    @Override
    public List<Homologacoes> getHomologacoesAbertas() {
       
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Homologacoes> c = cb.createQuery(Homologacoes.class);
        Root<Homologacoes> homologacoes = c.from(Homologacoes.class);
        c.where(homologacoes.get("status").in("1","2","3"));
        c.orderBy(cb.desc(homologacoes.get("dataSolicitacao")));
        
        TypedQuery q = entityManager.createQuery(c);
        return q.getResultList();
    }
	
    @Override
    public void addReservaServidores(ReservaServidores r) {
        if(r.getId() == null)
            entityManager.persist(r);
        else
            entityManager.merge(r);
    }

    @Override
    public void addServidor(Servidores s) {
        if(s.getId() == null)
            entityManager.persist(s);
        else
            entityManager.merge(s);
    }

        @Override
    public void addReservaAtms(ReservaAtms r) {
        if(r.getId() == null)
            entityManager.persist(r);
        else
            entityManager.merge(r);
    }

    @Override
    public void addAtms(Atms a) {
        if(a.getId() == null)
            entityManager.persist(a);
        else
            entityManager.merge(a);
    }
    
    @Override
    public void addReservaCartoesContas(ReservaCartoesContas r) {
        if(r.getId() == null)
            entityManager.persist(r);
        else
            entityManager.merge(r);
    }

    @Override
    public void addCartoesContas(CartoesContas c) {
        if(c.getId() == null)
            entityManager.persist(c);
        else
            entityManager.merge(c);
    }
    
    @Override
    public void addReservaCartoesCredito(ReservaCartoesCredito r) {
        if(r.getId() == null)
            entityManager.persist(r);
        else
            entityManager.merge(r);
    }

    @Override
    public void addCartoesCredito(CartoesCredito c) {
        if(c.getId() == null)
            entityManager.persist(c);
        else
            entityManager.merge(c);
    }
    
    @Override
    public void addReservaEquipamentosAdicionais(ReservaEquipamentosAdicionais r) {
        if(r.getId() == null)
            entityManager.persist(r);
        else
            entityManager.merge(r);
    }

    @Override
    public void addEquipamentosAdicionais(EquipamentosAdicionais e) {
        if(e.getId() == null)
            entityManager.persist(e);
        else
            entityManager.merge(e);
    }
    
    @Override
    public void addReservaTerminais(ReservaTerminais r) {
        if(r.getId() == null)
            entityManager.persist(r);
        else
            entityManager.merge(r);
    }

    @Override
    public void addTerminais(Terminais t) {
        if(t.getId() == null)
            entityManager.persist(t);
        else
            entityManager.merge(t);
    }
    
    @Override
    public void addReservaUsuarios(ReservaUsuarios r) {
        if(r.getId() == null)
            entityManager.persist(r);
        else
            entityManager.merge(r);
    }

    @Override
    public void addUsuarios(Usuarios u) {
        if(u.getId() == null)
            entityManager.persist(u);
        else
            entityManager.merge(u);
    }
    
    @Override
    public List<Usuarios> getEquipeCoordenadores() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuarios> c = cb.createQuery(Usuarios.class);
        Root<Usuarios> usuarios = c.from(Usuarios.class);
        c.where(usuarios.get("perfil").in("2") );
        c.orderBy(cb.asc(usuarios.get("nome")));
        
        TypedQuery q = entityManager.createQuery(c);
        return q.getResultList();
    }

    @Override
    public void addHistoricoHomologacaoCiclo(HistoricoHomologacaoCiclos h) {
        if(h.getId() == null)
            entityManager.persist(h);
        else
            entityManager.merge(h);
    
    }

    @Override
    public void removeHistoricoHomologacaoCiclo(HistoricoHomologacaoCiclos h) {
        HistoricoHomologacaoCiclos historicoARemover = entityManager.merge(h);
        entityManager.remove(historicoARemover);
    }

    @Override
    public List<HistoricoHomologacaoCiclos> getHistoricoHomologacoesCiclos(Homologacoes h) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<HistoricoHomologacaoCiclos> c = cb.createQuery(HistoricoHomologacaoCiclos.class);
        Root<HistoricoHomologacaoCiclos> historico = c.from(HistoricoHomologacaoCiclos.class);
        c.where(historico.get("homologacao").in(h));
        c.orderBy(cb.desc(historico.get("dataFimCiclo")));
        
        TypedQuery q = entityManager.createQuery(c);
        return q.getResultList();
    }
    @Override
    public void removeReservaAtms(ReservaAtms r) {
        ReservaAtms reservaARemover = entityManager.merge(r);
        entityManager.remove(reservaARemover);
    }
    
    @Override
    public void removeReservaCartoesContas(ReservaCartoesContas r) {
        ReservaCartoesContas reservaARemover = entityManager.merge(r);
        entityManager.remove(reservaARemover);
    }
    
    @Override
    public void removeReservaCartoesCredito(ReservaCartoesCredito r) {
        ReservaCartoesCredito reservaARemover = entityManager.merge(r);
        entityManager.remove(reservaARemover);
    }
    
    @Override
    public void removeReservaEquipamentosAdicionais(ReservaEquipamentosAdicionais r) {
        ReservaEquipamentosAdicionais reservaARemover = entityManager.merge(r);
        entityManager.remove(reservaARemover);
    }
    
    @Override
    public void removeReservaServidores(ReservaServidores r) {
        ReservaServidores reservaARemover = entityManager.merge(r);
        entityManager.remove(reservaARemover);
    }
    
    @Override
    public void removeReservaTerminais(ReservaTerminais r) {
        ReservaTerminais reservaARemover = entityManager.merge(r);
        entityManager.remove(reservaARemover);
    }

    @Override
    public void removeReservaUsuarios(ReservaUsuarios r) {
        ReservaUsuarios reservaARemover = entityManager.merge(r);
        entityManager.remove(reservaARemover);
    }


  
}