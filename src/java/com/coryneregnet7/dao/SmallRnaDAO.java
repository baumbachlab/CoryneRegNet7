/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.SmallRna;
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
public class SmallRnaDAO extends GenericDAO {

    public SmallRna findById(Integer id) {
        try {
            SmallRna run = new SmallRna();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.findById");
            query.setInteger("id", id);
            run = (SmallRna) query.uniqueResult();
            this.tx.commit();
            return run;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<SmallRna> listAll() {
        try {
            SmallRna gene = new SmallRna();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.findAll");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

     public List<SmallRna> findByGenome(Integer genome) {
        try {
            SmallRna gene = new SmallRna();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.findByGenome");
            query.setInteger("genome", genome);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
     
       public List<SmallRna> findByType(String type) {
        try {
            SmallRna gene = new SmallRna();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.findByType");
            query.setString("type", type);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
    
    
    //findByLocusTag
    public SmallRna findByLocusTag(String locusTag) {
        try {
            SmallRna run = new SmallRna();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.findByLocusTag");
            query.setString("locusTag", locusTag);
            run = (SmallRna) query.uniqueResult();
            this.tx.commit();
            return run;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public SmallRna findRepeated(Integer startPosition, Integer endPosition, String orientation, Integer genome) {
        try {
            SmallRna run = new SmallRna();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.findRepeated");
            query.setInteger("startPosition", startPosition);
            query.setInteger("endPosition", endPosition);
            query.setString("orientation", orientation);
            query.setInteger("genome", genome);
            run = (SmallRna) query.uniqueResult();
            this.tx.commit();
            return run;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public SmallRna findRepeatedPos(Integer startPosition, Integer endPosition, Integer genome) {
        try {
            SmallRna run = new SmallRna();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.findRepeatedPos");
            query.setInteger("startPosition", startPosition);
            query.setInteger("endPosition", endPosition);
            query.setInteger("genome", genome);
            run = (SmallRna) query.uniqueResult();
            this.tx.commit();
            return run;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

}
