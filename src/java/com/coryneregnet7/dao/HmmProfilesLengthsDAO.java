/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.HmmProfilesLengths;
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
public class HmmProfilesLengthsDAO extends GenericDAO {

    public HmmProfilesLengths findById(Integer id) {
        try {
            HmmProfilesLengths hmmProfilesLengths = new HmmProfilesLengths();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("HmmProfilesLengths.findById");
            query.setInteger("id", id);
            hmmProfilesLengths = (HmmProfilesLengths) query.uniqueResult();
            this.tx.commit();
            return hmmProfilesLengths;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<HmmProfilesLengths> findByTypeAndDatabase(String type, String databse) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("HmmProfilesLengths.findByTypeAndDatabase");
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

    public ArrayList<HmmProfilesLengths> findByRefStatistics(Integer idRefStatistics) {
        try {
            ArrayList lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("HmmProfilesLengths.findByRefStatistics");
            query.setInteger("idRefStatistics", idRefStatistics);
            lista = (ArrayList) query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<HmmProfilesLengths> findByOrganism(Integer organismid) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("HmmProfilesLengths.findByOrganism");
            query.setInteger("organismid", organismid);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<HmmProfilesLengths> listAll() {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("HmmProfilesLengths.findAll");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

}
