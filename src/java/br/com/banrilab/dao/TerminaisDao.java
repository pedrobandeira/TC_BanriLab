/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.ReservaTerminais;
import br.com.banrilab.entidades.Terminais;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pedro
 */
@Stateless
public class TerminaisDao implements TerminaisDaoInterface {
    @PersistenceContext(unitName = "BanriLabPU2")
    EntityManager entityManager;
    
    @Override
    public void addTerminal (Terminais t) {
        
        // EntityManager entityManager = new HibernateUtil().getEntityManager();
        
        if(t.getId() == null)
            entityManager.persist(t);
        else
            entityManager.merge(t);
        
    }
    
    
    @Override
    public void removeTerminal (Terminais t) {
        //EntityManager entityManager = new HibernateUtil().getEntityManager();

        Terminais terminalARemover = entityManager.merge(t);
        entityManager.remove(terminalARemover);
 
    }
    
    @Override
    public List<Terminais> getTerminais() {
       
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Terminais.class));
        cq.orderBy(entityManager.getCriteriaBuilder().asc(cq.from(Terminais.class).get("nome")));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public void removeReservaTerminal(ReservaTerminais r) {
        ReservaTerminais reservaARemover = entityManager.merge(r);
        entityManager.remove(reservaARemover);
    }

    @Override
    public List<ReservaTerminais> getReservasTerminais() {
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ReservaTerminais.class));
        return entityManager.createQuery(cq).getResultList();
    }
}
