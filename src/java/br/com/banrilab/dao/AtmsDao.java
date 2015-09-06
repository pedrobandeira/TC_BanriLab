/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.Atms;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pedro
 */
@Stateless
public class AtmsDao implements AtmsDaoInterface {
    @PersistenceContext(unitName = "BanriLabPU2")
    EntityManager entityManager;
    
    @Override
    public void addAtm (Atms a) {
        
        // EntityManager entityManager = new HibernateUtil().getEntityManager();
        System.out.println("Entrou no DAO. Nome ATM: "+a.getNome());
        if(a.getId() == null)
            entityManager.persist(a);
        else
            entityManager.merge(a);
        
    }
    
    
    @Override
    public void removeAtm (Atms a) {
        //EntityManager entityManager = new HibernateUtil().getEntityManager();

        Atms atmARemover = entityManager.merge(a);
        entityManager.remove(atmARemover);
 
    }
    
    @Override
    public List<Atms> getAtms() {
       
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Atms.class));
        cq.orderBy(entityManager.getCriteriaBuilder().asc(cq.from(Atms.class).get("nome")));
        return entityManager.createQuery(cq).getResultList();
    }

}
