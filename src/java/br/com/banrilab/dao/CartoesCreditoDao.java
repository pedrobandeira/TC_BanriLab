/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.CartoesCredito;
import br.com.banrilab.entidades.ReservaCartoesCredito;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pedro
 */
@Stateless
public class CartoesCreditoDao implements CartoesCreditoDaoInterface {
    @PersistenceContext(unitName = "BanriLabPU2")
    EntityManager entityManager;

    @Override
    public void addCartaoCredito(CartoesCredito c) {
        if(c.getId() == null)
            entityManager.persist(c);
        else
            entityManager.merge(c);
    }

    @Override
    public void removeCartaoCredito(CartoesCredito c) {
        CartoesCredito cartaoARemover = entityManager.merge(c);
        entityManager.remove(cartaoARemover);
    }

    @Override
    public List<CartoesCredito> getCartoesCredito() {
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(CartoesCredito.class));
        cq.orderBy(entityManager.getCriteriaBuilder().asc(cq.from(CartoesCredito.class).get("bandeira")), entityManager.getCriteriaBuilder().asc(cq.from(CartoesCredito.class).get("nome")));
        return entityManager.createQuery(cq).getResultList();
    }
    
     @Override
    public void removeReservaCartaoCredito(ReservaCartoesCredito r) {
        ReservaCartoesCredito reservaARemover = entityManager.merge(r);
        entityManager.remove(reservaARemover);
    }

    @Override
    public List<ReservaCartoesCredito> getReservasCartoesCredito() {
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ReservaCartoesCredito.class));
        return entityManager.createQuery(cq).getResultList();
    }
}
