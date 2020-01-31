/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.BindingSite;
import com.coryneregnet7.model.RegulatoryInteraction;
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
public class BindingSiteDAO extends GenericDAO {

    public BindingSite findById(Integer id) {
        try {
            BindingSite bindingSite = new BindingSite();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("BindingSite.findById");
            query.setInteger("id", id);
            bindingSite = (BindingSite) query.uniqueResult();
            this.tx.commit();
            return bindingSite;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<BindingSite> listAll() {
        try {
            BindingSite bindingSite = new BindingSite();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("BindingSite.findAll");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public Long bringNumberOfBindSites() {
        Long numberOfBindSites;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("BindingSite.bringNumberOfBindSites");
        numberOfBindSites = (Long) query.uniqueResult();
        this.tx.commit();
        return numberOfBindSites;
    }

    public Long bringNumberOfExperimentalBindSitesOfOrganism(Integer organismId) {
        Long numberOfBindSites;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("BindingSite.bringNumberOfExperimentalBindSitesOfOrganism");
        query.setInteger("organismId", organismId);
        numberOfBindSites = (Long) query.uniqueResult();
        this.tx.commit();
        return numberOfBindSites;
    }

    public List<BindingSite> findByRegularotyTF(Integer id) {
        try {
            BindingSite bindingSite = new BindingSite();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("BindingSite.findByRegularotyTF");
            query.setInteger("tf", id);
            lista = query.list();
            this.tx.commit();
            return lista;

        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<BindingSite> findByRegularotyInteractionGenome(Integer id) {
        try {
            BindingSite bindingSite = new BindingSite();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("BindingSite.findByRegularotyInteractionGenome");
            query.setInteger("genome", id);
            lista = query.list();
            this.tx.commit();
            return lista;

        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public Long bringNumberOfExperimentalBindSites() {
        Long numberOfBindSites;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("BindingSite.bringNumberOfExperimentalBindSites");
        numberOfBindSites = (Long) query.uniqueResult();
        this.tx.commit();
        return numberOfBindSites;
    }

    public List<BindingSite> findModelsByTG(String targetGene) {
        try {
            BindingSite bindingSite = new BindingSite();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("BindingSite.findModelsByTG");
            query.setString("targetGene", targetGene);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<BindingSite> findModelsByTF(String tf) {
        try {
            BindingSite bindingSite = new BindingSite();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("BindingSite.findModelsByTF");
            query.setString("tf", tf);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<BindingSite> findByRegularotyInteraction(Integer ri) {
        try {
            BindingSite bindingSite = new BindingSite();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("BindingSite.findByRegularotyInteraction");
            query.setInteger("ri", ri);
            lista = query.list();
            this.tx.commit();
            return lista;

        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<BindingSite> findByType(String type) {
        try {
            BindingSite bindingSite = new BindingSite();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("BindingSite.findByType");
            query.setString("type", type);
            lista = query.list();
            this.tx.commit();
            return lista;

        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<BindingSite> findByModelRole(String role1, String role2) {
        try {
            BindingSite bindingSite = new BindingSite();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("BindingSite.findByModelRole");
            query.setString("role1", role1);
            query.setString("role2", role2);
            lista = query.list();
            this.tx.commit();
            return lista;

        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<BindingSite> findByTypeAndRoleTest(String type, String role) {
        try {
            BindingSite bindingSite = new BindingSite();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("BindingSite.findByTypeAndRoleTest");
            query.setString("type", type);
            query.setString("role", role);
            lista = query.list();
            this.tx.commit();
            return lista;

        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<RegulatoryInteraction> findRiWithBsByGenome(Integer id) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("BindingSite.findRiWithBsByGenome");
            query.setInteger("genome", id);
            lista = query.list();
            this.tx.commit();
            return lista;

        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<BindingSite> findByOrganism(Integer organism) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("BindingSite.findByOrganism");
            query.setInteger("organism", organism);
            lista = query.list();
            this.tx.commit();
            return lista;

        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
}
