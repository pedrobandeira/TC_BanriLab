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
public class AtmsDao {
    @PersistenceContext(unitName = "BanriLabPU2")
    EntityManager entityManager;
    
    public void addAtm (Atms a) {
        
        // EntityManager entityManager = new HibernateUtil().getEntityManager();
        
        if(a.getId() == null)
            entityManager.persist(a);
        else
            entityManager.merge(a);
        
    }
    
    
    public void removeAtm (Atms a) {
        //EntityManager entityManager = new HibernateUtil().getEntityManager();

        Atms atmARemover = entityManager.merge(a);
        entityManager.remove(atmARemover);
 
    }
    
    public List<Atms> getAtms() {
       
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Atms.class));
        return entityManager.createQuery(cq).getResultList();
    }

}
