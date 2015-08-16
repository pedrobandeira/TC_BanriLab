/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.EquipamentosAdicionais;
import br.com.banrilab.entidades.ReservaEquipamentosAdicionais;
import br.com.banrilab.entidades.ReservaServidores;
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
public class ReservaEquipamentosAdicionaisDao implements ReservaEquipamentosAdicionaisDaoInterface {
    
    @PersistenceContext(unitName = "BanriLabPU2")
    EntityManager entityManager;

    @Override
    public void addReservaEquipamentosAdicionais(ReservaEquipamentosAdicionais r) {
        if(r.getId() == null)
            entityManager.persist(r);
        else
            entityManager.merge(r);
    }

    @Override
    public void addEquipamentosAdicionais(EquipamentosAdicionais e) {
        if(e.getId() == null)
            entityManager.persist(e);
        else
            entityManager.merge(e);
    }
    
    @Override
    public void removeReservaEquipamentosAdicionais(ReservaEquipamentosAdicionais r) {
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
