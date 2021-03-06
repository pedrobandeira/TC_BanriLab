/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.EquipamentosAdicionais;
import br.com.banrilab.entidades.ReservaEquipamentosAdicionais;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pedro
 */
@Stateless
public class EquipamentosAdicionaisDao implements EquipamentosAdicionaisDaoInterface {
    @PersistenceContext(unitName = "BanriLabPU2")
    EntityManager entityManager;
    
    @Override
    public void addEquipamentoAdicional (EquipamentosAdicionais e) {
        
        // EntityManager entityManager = new HibernateUtil().getEntityManager();
        
        if(e.getId() == null)
            entityManager.persist(e);
        else
            entityManager.merge(e);
        
    }
    
    
    @Override
    public void removeEquipamentoAdicional (EquipamentosAdicionais e) {
        //EntityManager entityManager = new HibernateUtil().getEntityManager();

        EquipamentosAdicionais eqpARemover = entityManager.merge(e);
        entityManager.remove(eqpARemover);
 
    }
    
    @Override
    public List<EquipamentosAdicionais> getEquipamentosAdicionais() {
       
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(EquipamentosAdicionais.class));
        cq.orderBy(entityManager.getCriteriaBuilder().asc(cq.from(EquipamentosAdicionais.class).get("nome")));
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public void removeReservaEquipamentoAdicional(ReservaEquipamentosAdicionais r) {
        ReservaEquipamentosAdicionais reservaARemover = entityManager.merge(r);
        entityManager.remove(reservaARemover);
    }

    @Override
    public List<ReservaEquipamentosAdicionais> getReservasEquipamentosAdicionais() {
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ReservaEquipamentosAdicionais.class));
        return entityManager.createQuery(cq).getResultList();
    }
}
