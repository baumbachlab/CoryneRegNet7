/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.BsJobResult;
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
public class BsJobResultDAO extends GenericDAO{
    
   
    public BsJobResult findById(Integer id) {
        try {
            BsJobResult run = new BsJobResult();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("BsJobResult.findById");
            query.setInteger("id", id);
            run = (BsJobResult) query.uniqueResult();
            this.tx.commit();
            return run;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<BsJobResult> listAll() {
        try {
            BsJobResult gene = new BsJobResult();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("BsJobResult.findAll");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    //query.setString("locusTag", locusTag);    
    public List<BsJobResult> findByJob(Integer job) {
        try {
            BsJobResult gene = new BsJobResult();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("BsJobResult.findByJob");
            query.setInteger("job", job); 
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
}
