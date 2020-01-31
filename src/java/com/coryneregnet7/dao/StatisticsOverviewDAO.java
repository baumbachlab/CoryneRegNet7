/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.StatisticsOverview;
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
public class StatisticsOverviewDAO extends GenericDAO {

    public StatisticsOverview findById(Integer id) {
        try {
            StatisticsOverview statisticsOverview = new StatisticsOverview();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("StatisticsOverview.findById");
            query.setInteger("id", id);
            statisticsOverview = (StatisticsOverview) query.uniqueResult();
            this.tx.commit();
            return statisticsOverview;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public StatisticsOverview findByTypeAndDatabase(String type, String databse) {
        try {
            StatisticsOverview statisticsOverview = new StatisticsOverview();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("StatisticsOverview.findByTypeAndDatabase");
            query.setString("type", type);
            query.setString("database", databse);
            statisticsOverview = (StatisticsOverview) query.uniqueResult();
            this.tx.commit();
            return statisticsOverview;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public StatisticsOverview findByOrganism(Integer organismid) {
        try {
            StatisticsOverview statisticsOverview = new StatisticsOverview();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("StatisticsOverview.findByOrganism");
            query.setInteger("organismid", organismid);
            statisticsOverview = (StatisticsOverview) query.uniqueResult();
            this.tx.commit();
            return statisticsOverview;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<StatisticsOverview> listAll() {
        try {
            StatisticsOverview gene = new StatisticsOverview();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("StatisticsOverview.findAll");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

}
