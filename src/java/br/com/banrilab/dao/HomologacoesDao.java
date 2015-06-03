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

    
}
