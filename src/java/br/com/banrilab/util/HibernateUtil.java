/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;


/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Pedro
 */
public class HibernateUtil {
    @PersistenceUnit(unitName="BanriLabPU2")
    private EntityManagerFactory factory;

    public EntityManager getEntityManager() {
        return factory.createEntityManager();
    } 

    

}
