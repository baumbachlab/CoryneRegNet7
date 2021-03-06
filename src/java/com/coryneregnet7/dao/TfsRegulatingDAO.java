/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.TfsRegulating;
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
public class TfsRegulatingDAO extends GenericDAO {

    public TfsRegulating findById(Integer id) {
        try {
            TfsRegulating tfsRegulating = new TfsRegulating();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("TfsRegulating.findById");
            query.setInteger("id", id);
            tfsRegulating = (TfsRegulating) query.uniqueResult();
            this.tx.commit();
            return tfsRegulating;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<TfsRegulating> findByTypeAndDatabase(String type, String databse) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("TfsRegulating.findByTypeAndDatabase");
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

    public List<TfsRegulating> findByOrganism(Integer organismid) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("TfsRegulating.findByOrganism");
            query.setInteger("organismid", organismid);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public ArrayList<TfsRegulating> findByRefStatistics(Integer idRefStatistics) {
        try {
            ArrayList lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("TfsRegulating.findByRefStatistics");
            query.setInteger("idRefStatistics", idRefStatistics);
            lista = (ArrayList) query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<TfsRegulating> listAll() {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("TfsRegulating.findAll");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

}
