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
public class CartoesContasDao implements CartoesContasDaoInterface{
    @PersistenceContext(unitName = "BanriLabPU2")
    EntityManager entityManager;

    @Override
    public void addCartaoConta(CartoesContas c) {
        if(c.getId() == null)
            entityManager.persist(c);
        else
            entityManager.merge(c);
    }

    @Override
    public void removeCartaoConta(CartoesContas c) {
        CartoesContas cartaoARemover = entityManager.merge(c);
        entityManager.remove(cartaoARemover);
    }

    @Override
    public List<CartoesContas> getCartoesContas() {
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(CartoesContas.class));
        cq.orderBy(entityManager.getCriteriaBuilder().asc(cq.from(CartoesContas.class).get("agencia")), entityManager.getCriteriaBuilder().asc(cq.from(CartoesContas.class).get("nome")));
        return entityManager.createQuery(cq).getResultList();
    }
    
    @Override
    public void removeReservaCartaoConta(ReservaCartoesContas r) {
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
