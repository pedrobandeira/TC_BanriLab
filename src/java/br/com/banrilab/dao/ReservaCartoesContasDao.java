/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.CartoesContas;
import br.com.banrilab.entidades.ReservaCartoesContas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pedro
 */
@Stateless
public class ReservaCartoesContasDao implements ReservaCartoesContasDaoInterface {
    
    @PersistenceContext(unitName = "BanriLabPU2")
    EntityManager entityManager;

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
    public void removeReservaCartoesContas(ReservaCartoesContas r) {
        ReservaCartoesContas reservaARemover = entityManager.merge(r);
        entityManager.remove(reservaARemover);
    }

    @Override
    public List<ReservaCartoesContas> getReservasCartoesContas() {
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ReservaCartoesContas.class));
        return entityManager.createQuery(cq).getResultList();
    }   
}
