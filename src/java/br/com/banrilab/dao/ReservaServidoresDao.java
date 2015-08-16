/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

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
public class ReservaServidoresDao implements ReservaServidoresDaoInterface {
    
    @PersistenceContext(unitName = "BanriLabPU2")
    EntityManager entityManager;

    @Override
    public void addReservaServidores(ReservaServidores r) {
        if(r.getId() == null)
            entityManager.persist(r);
        else
            entityManager.merge(r);
    }

    @Override
    public void addServidores(Servidores s) {
        if(s.getId() == null)
            entityManager.persist(s);
        else
            entityManager.merge(s);
    }
    
    @Override
    public void removeReservaServidores(ReservaServidores r) {
        ReservaServidores reservaARemover = entityManager.merge(r);
        entityManager.remove(reservaARemover);
    }

    @Override
    public List<ReservaServidores> getReservasServidores() {
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ReservaServidores.class));
        return entityManager.createQuery(cq).getResultList();
    }
    
   // @Schedule(minute="*/1", hour="*") 
    /*public void verificaReservasAtms(){
        System.out.println("Rodando job de reserva atms...");
        
        }*/
    
}

