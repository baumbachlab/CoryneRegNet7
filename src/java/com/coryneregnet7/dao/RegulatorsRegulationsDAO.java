/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.RegulatorsRegulations;
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
public class RegulatorsRegulationsDAO extends GenericDAO {

    public RegulatorsRegulations findById(Integer id) {
        try {
            RegulatorsRegulations regulatorsRegulations = new RegulatorsRegulations();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatorsRegulations.findById");
            query.setInteger("id", id);
            regulatorsRegulations = (RegulatorsRegulations) query.uniqueResult();
            this.tx.commit();
            return regulatorsRegulations;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public RegulatorsRegulations findByTypeAndDatabase(String type, String databse) {
        try {
            RegulatorsRegulations regulatorsRegulations = new RegulatorsRegulations();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatorsRegulations.findByTypeAndDatabase");
            query.setString("type", type);
            query.setString("database", databse);
            regulatorsRegulations = (RegulatorsRegulations) query.uniqueResult();
            this.tx.commit();
            return regulatorsRegulations;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public RegulatorsRegulations findByOrganism(Integer organismid) {
        try {
            RegulatorsRegulations regulatorsRegulations = new RegulatorsRegulations();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatorsRegulations.findByOrganism");
            query.setInteger("organismid", organismid);
            regulatorsRegulations = (RegulatorsRegulations) query.uniqueResult();
            this.tx.commit();
            return regulatorsRegulations;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<RegulatorsRegulations> listAll() {
        try {
            RegulatorsRegulations gene = new RegulatorsRegulations();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatorsRegulations.findAll");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

}
