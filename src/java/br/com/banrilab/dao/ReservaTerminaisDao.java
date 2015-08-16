/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.ReservaServidores;
import br.com.banrilab.entidades.ReservaTerminais;
import br.com.banrilab.entidades.Servidores;
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
public class ReservaTerminaisDao implements ReservaTerminaisDaoInterface {
    
    @PersistenceContext(unitName = "BanriLabPU2")
    EntityManager entityManager;

    @Override
    public void addReservaTerminais(ReservaTerminais r) {
        if(r.getId() == null)
            entityManager.persist(r);
        else
            entityManager.merge(r);
    }

    @Override
    public void addTerminais(Terminais t) {
        if(t.getId() == null)
            entityManager.persist(t);
        else
            entityManager.merge(t);
    }
    
    @Override
    public void removeReservaTerminais(ReservaTerminais r) {
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

