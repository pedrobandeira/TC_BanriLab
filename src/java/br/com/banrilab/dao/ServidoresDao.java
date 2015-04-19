/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.Servidores;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pedro
 */
@Stateless
public class ServidoresDao {
    @PersistenceContext(unitName = "BanriLabPU2")
    EntityManager entityManager;
    
    public void addServidor (Servidores s) {
        
        // EntityManager entityManager = new HibernateUtil().getEntityManager();
        
        if(s.getId() == null)
            entityManager.persist(s);
        else
            entityManager.merge(s);
        
    }
    
    
    public void removeServidor (Servidores s) {
        //EntityManager entityManager = new HibernateUtil().getEntityManager();

        Servidores servidorARemover = entityManager.merge(s);
        entityManager.remove(servidorARemover);
 
    }
    
    public List<Servidores> getServidores() {
       
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Servidores.class));
        return entityManager.createQuery(cq).getResultList();
    }

    
}
