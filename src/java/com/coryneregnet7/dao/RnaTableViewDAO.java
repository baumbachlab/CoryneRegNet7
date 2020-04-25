/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.RnaTableView;
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
public class RnaTableViewDAO extends GenericDAO{
    
   
    public RnaTableView findById(Integer id) {
        try {
            RnaTableView rnaTableView = new RnaTableView();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RnaTableView.findById");
            query.setInteger("id", id);
            rnaTableView = (RnaTableView) query.uniqueResult();
            this.tx.commit();
            return rnaTableView;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<RnaTableView> listAll() {
        try {
            RnaTableView gene = new RnaTableView();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RnaTableView.findAll");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
    
     public List<RnaTableView> findByGenome(Integer genome) {
        try {
            List list = new ArrayList<>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RnaTableView.findByGenome");
            query.setInteger("genome", genome);
            list = query.list();
            this.tx.commit();
            return list;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
     
     public List<RnaTableView> findByType(String type) {
        try {
            List list = new ArrayList<>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RnaTableView.findByType");
            query.setString("type", type);
            list = query.list();
            this.tx.commit();
            return list;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    
}
