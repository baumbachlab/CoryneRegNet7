/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.RnaInteraction;
import com.coryneregnet7.model.SmallRna;
import java.math.BigInteger;
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
public class RnaInteractionDAO extends GenericDAO {

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

    public List<RnaInteraction> findByGenome(Integer id) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RnaInteraction.findByGenome");
            query.setInteger("genome", id);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<Gene> findByGenomeInPosition(Integer startPosition, Integer endPosition, Integer genome) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RnaInteraction.findByGenomeInPosition");
            query.setInteger("startPosition", startPosition);
            query.setInteger("endPosition", endPosition);
            query.setInteger("genome", genome);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<SmallRna> findByGenomeInPositionSrna(Integer startPosition, Integer endPosition, Integer genome) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RnaInteraction.findByGenomeInPositionSrna");
            query.setInteger("startPosition", startPosition);
            query.setInteger("endPosition", endPosition);
            query.setInteger("genome", genome);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    //bringDistinctRnaByGenome
    public List<Gene> bringDistinctRnaByGenome(Integer id) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RnaInteraction.bringDistinctRnaByGenome");
            query.setInteger("genome", id);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    //findBySrna
    public List<RnaInteraction> findBySrna(Integer id) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RnaInteraction.findBySrna");
            query.setInteger("srna", id);
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

    //bringAll
    public Long bringAll() {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RnaInteraction.bringAll");
            Long num = (Long) query.uniqueResult();
            this.tx.commit();
            return num;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    //bringDistinctMrna
    public Long bringDistinctMrna() {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RnaInteraction.bringDistinctMrna");
            Long num = (Long) query.uniqueResult();
            this.tx.commit();
            return num;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
}
