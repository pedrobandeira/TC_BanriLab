/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.EquipamentosAdicionais;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pedro
 */
@Stateless
public class EquipamentosAdicionaisDao {
    @PersistenceContext(unitName = "BanriLabPU2")
    EntityManager entityManager;
    
    public void addEquipamentodicional (EquipamentosAdicionais e) {
        
        // EntityManager entityManager = new HibernateUtil().getEntityManager();
        
        if(e.getId() == null)
            entityManager.persist(e);
        else
            entityManager.merge(e);
        
    }
    
    
    public void removeEquipamentoAdicional (EquipamentosAdicionais e) {
        //EntityManager entityManager = new HibernateUtil().getEntityManager();

        EquipamentosAdicionais eqpARemover = entityManager.merge(e);
        entityManager.remove(eqpARemover);
 
    }
    
    public List<EquipamentosAdicionais> getEquipamentosAdicionais() {
       
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(EquipamentosAdicionais.class));
        return entityManager.createQuery(cq).getResultList();
    }

}
