/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.Homologacoes;
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
        return entityManager.createQuery(cq).getResultList();
    }
	/*
	public List<Homologacoes> getHomologacoesSolicitadas() {
      
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Homologacoes> c = cb.createQuery(Homologacoes.class);
        Root<Homologacoes> homologacoes = c.from(Homologacoes.class);
        c.where(homologacoes.get("status").in("1") );

        TypedQuery q = entityManager.createQuery(c);
        return q.getResultList();
		
    }*/
	/*
	public List<Homologacoes> getHomologacoesAutorizadas() {
       
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Homologacoes> c = cb.createQuery(Homologacoes.class);
        Root<Homologacoes> homologacoes = c.from(Homologacoes.class);
        c.where(homologacoes.get("status").in("2") );

        TypedQuery q = entityManager.createQuery(c);
        return q.getResultList();
    }*/
	/*
	public List<Homologacoes> getHomologacoesEmAndamento() {
       
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Homologacoes> c = cb.createQuery(Homologacoes.class);
        Root<Homologacoes> homologacoes = c.from(Homologacoes.class);
        c.where(homologacoes.get("status").in("3") );

        TypedQuery q = entityManager.createQuery(c);
        return q.getResultList();
    }*/
	/*
	public List<Homologacoes> getHomologacoesConcluidas() {
       
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Homologacoes> c = cb.createQuery(Homologacoes.class);
        Root<Homologacoes> homologacoes = c.from(Homologacoes.class);
        c.where(homologacoes.get("status").in("4") );

        TypedQuery q = entityManager.createQuery(c);
        return q.getResultList();
    }*/
	/*
	public List<Homologacoes> getHomologacoesCanceladas() {
       
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Homologacoes> c = cb.createQuery(Homologacoes.class);
        Root<Homologacoes> homologacoes = c.from(Homologacoes.class);
        c.where(homologacoes.get("status").in("5") );

        TypedQuery q = entityManager.createQuery(c);
        return q.getResultList();
    } */
	
    @Override
    public List<Homologacoes> getHomologacoesAbertas() {
       
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Homologacoes> c = cb.createQuery(Homologacoes.class);
        Root<Homologacoes> homologacoes = c.from(Homologacoes.class);
        c.where(homologacoes.get("status").in("1","2","3"));

        TypedQuery q = entityManager.createQuery(c);
        return q.getResultList();
    }
	/*
	public List<Homologacoes> getHomologacoesFechadas() {
       
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Homologacoes> c = cb.createQuery(Homologacoes.class);
        Root<Homologacoes> homologacoes = c.from(Homologacoes.class);
        c.where(homologacoes.get("status").in("4","5") );

        TypedQuery q = entityManager.createQuery(c);
        return q.getResultList();
    } */

    
}