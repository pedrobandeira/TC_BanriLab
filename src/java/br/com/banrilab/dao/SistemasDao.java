/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.Sistemas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pedro
 */
@Stateless
public class SistemasDao implements SistemasDaoInterface {
    @PersistenceContext(unitName = "BanriLabPU2")
    EntityManager entityManager;

    @Override
    public void addSistema(Sistemas s) {
        if(s.getId() == null)
            entityManager.persist(s);
        else
            entityManager.merge(s);
    }

    @Override
    public void removeSistema(Sistemas s) {
        Sistemas sistemaARemover = entityManager.merge(s);
        entityManager.remove(sistemaARemover);
    }

    @Override
    public List<Sistemas> getSistemas() {
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Sistemas.class));
        return entityManager.createQuery(cq).getResultList();
    }
    
}
