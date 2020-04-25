/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.RnaInteraction;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariana
 */
public class RnaInteractionDAO extends GenericDAO{
    
   
    public RnaInteraction findById(Integer id) {
        try {
            RnaInteraction rnaInteraction = new RnaInteraction();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RnaInteraction.findById");
            query.setInteger("id", id);
            rnaInteraction = (RnaInteraction) query.uniqueResult();
            this.tx.commit();
            return rnaInteraction;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }
    
    
        public List<RnaInteraction> findByMrna(Integer id) {
        try {
              List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RnaInteraction.findByMrna");
            query.setInteger("mrna", id);
           lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<RnaInteraction> listAll() {
        try {
            RnaInteraction gene = new RnaInteraction();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RnaInteraction.findAll");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    
}
