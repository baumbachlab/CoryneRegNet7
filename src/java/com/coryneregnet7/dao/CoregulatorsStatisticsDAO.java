/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.CoregulatorsStatistics;
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
public class CoregulatorsStatisticsDAO extends GenericDAO {

    public CoregulatorsStatistics findById(Integer id) {
        try {
            CoregulatorsStatistics coregulatorsStatistics = new CoregulatorsStatistics();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("CoregulatorsStatistics.findById");
            query.setInteger("id", id);
            coregulatorsStatistics = (CoregulatorsStatistics) query.uniqueResult();
            this.tx.commit();
            return coregulatorsStatistics;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<CoregulatorsStatistics> findByTypeAndDatabase(String type, String databse) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("CoregulatorsStatistics.findByTypeAndDatabase");
            query.setString("type", type);
            query.setString("database", databse);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public ArrayList<CoregulatorsStatistics> findByRefStatistics(Integer idRefStatistics) {
        try {
            ArrayList lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("CoregulatorsStatistics.findByRefStatistics");
            query.setInteger("idRefStatistics", idRefStatistics);
            lista = (ArrayList) query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<CoregulatorsStatistics> findByOrganism(Integer organismid) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("CoregulatorsStatistics.findByOrganism");
            query.setInteger("organismid", organismid);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<CoregulatorsStatistics> listAll() {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("CoregulatorsStatistics.findAll");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

}
