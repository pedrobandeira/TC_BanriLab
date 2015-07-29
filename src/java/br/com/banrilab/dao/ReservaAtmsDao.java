/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.Atms;
import br.com.banrilab.entidades.ReservaAtms;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pedro
 */
@Stateless
public class ReservaAtmsDao implements ReservaAtmsDaoInterface {
    
    @PersistenceContext(unitName = "BanriLabPU2")
    EntityManager entityManager;

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
    public void removeReservaAtms(ReservaAtms r) {
        ReservaAtms reservaARemover = entityManager.merge(r);
        entityManager.remove(reservaARemover);
    }

    @Override
    public List<ReservaAtms> getReservasAtms() {
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ReservaAtms.class));
        return entityManager.createQuery(cq).getResultList();
    }
    
}
