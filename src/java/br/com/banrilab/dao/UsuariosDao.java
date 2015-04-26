/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

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
public class UsuariosDao {
    @PersistenceContext(unitName = "BanriLabPU2")
    EntityManager entityManager;
    
    public void addUsuario (Usuarios u) {
        
        if(u.getId() == null)
            entityManager.persist(u);
        else
            entityManager.merge(u);
        
    }
    
    
    public void removeUsuario (Usuarios u) {
        //EntityManager entityManager = new HibernateUtil().getEntityManager();

        Usuarios usuarioARemover = entityManager.merge(u);
        entityManager.remove(usuarioARemover);
 
    }
    
    public List<Usuarios> getUsuarios() {
       
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Usuarios.class));
        return entityManager.createQuery(cq).getResultList();
    }

    
}
