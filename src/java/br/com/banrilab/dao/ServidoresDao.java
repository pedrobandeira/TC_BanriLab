/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.banrilab.dao;

import br.com.banrilab.entidades.Servidores;
import br.com.banrilab.util.HibernateUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Pedro
 */
public class ServidoresDao {
    private Session session;
    private Transaction transaction;
    
    public void addServidor (Servidores s) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
        
            Servidores servidor = new Servidores();
            servidor.setPatrimonio(s.getPatrimonio());
            servidor.setNome(s.getNome());
            servidor.setModelo(s.getModelo());
            servidor.setDescricao(s.getDescricao());
        
            session.save(servidor);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void atualizaServidor (Servidores s) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
        
            
            session.update(s);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void removeServidor (Servidores s) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
        
            
            session.delete(s);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public List<Servidores> getServidores() {
  
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        Criteria cri = session.createCriteria(Servidores.class);
        List listaServidores = cri.list();

        session.close();
        
        return listaServidores;
    }

    
}
