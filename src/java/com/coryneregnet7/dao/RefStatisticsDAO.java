/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.RefStatistics;
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
public class RefStatisticsDAO extends GenericDAO {

    public RefStatistics findById(Integer id) {
        try {
            RefStatistics refStatistics = new RefStatistics();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RefStatistics.findById");
            query.setInteger("id", id);
            refStatistics = (RefStatistics) query.uniqueResult();
            this.tx.commit();
            return refStatistics;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public RefStatistics findByTypeAndDatabase(String type, String databse) {
        try {
            RefStatistics refStatistics = new RefStatistics();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RefStatistics.findByTypeAndDatabase");
            query.setString("type", type);
            query.setString("database", databse);
            refStatistics = (RefStatistics) query.uniqueResult();
            this.tx.commit();
            return refStatistics;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public RefStatistics findByOrganism(Integer organismid) {
        try {
            RefStatistics refStatistics = new RefStatistics();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RefStatistics.findByOrganism");
            query.setInteger("organismid", organismid);
            refStatistics = (RefStatistics) query.uniqueResult();
            this.tx.commit();
            return refStatistics;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<RefStatistics> listAll() {
        try {
            RefStatistics gene = new RefStatistics();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RefStatistics.findAll");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

}
