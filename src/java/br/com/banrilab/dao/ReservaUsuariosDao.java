/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.ReservaUsuarios;
import br.com.banrilab.entidades.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pedro
 */
@Stateless
public class ReservaUsuariosDao implements ReservaUsuariosDaoInterface {
    
    @PersistenceContext(unitName = "BanriLabPU2")
    EntityManager entityManager;

    @Override
    public void addReservaUsuarios(ReservaUsuarios r) {
        if(r.getId() == null)
            entityManager.persist(r);
        else
            entityManager.merge(r);
    }

    @Override
    public void addUsuarios(Usuarios u) {
        if(u.getId() == null)
            entityManager.persist(u);
        else
            entityManager.merge(u);
    }
    
    @Override
    public void removeReservaUsuarios(ReservaUsuarios r) {
        ReservaUsuarios reservaARemover = entityManager.merge(r);
        entityManager.remove(reservaARemover);
    }

    @Override
    public List<ReservaUsuarios> getReservasUsuarios() {
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ReservaUsuarios.class));
        return entityManager.createQuery(cq).getResultList();
    }
    
}

