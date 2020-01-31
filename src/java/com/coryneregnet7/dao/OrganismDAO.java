/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Organism;
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
public class OrganismDAO extends GenericDAO {

    public Organism findById(Integer id) {
        try {
            Organism genome = new Organism();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Organism.findById");
            query.setInteger("id", id);
            genome = (Organism) query.uniqueResult();
            this.tx.commit();
            return genome;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<Organism> listAll() {
        try {
            Organism genome = new Organism();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Organism.findAll");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public Long bringNumberOfOrganisms() {
        Long numberGenomes;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("Genome.bringNumberOfOrganisms");
        numberGenomes = (Long) query.uniqueResult();
        this.tx.commit();
        return numberGenomes;
    }

    public Long bringNumberOfModelOrganisms(String type) {
        Long numberGenomes;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("Genome.bringNumberOfModelOrganisms");
        query.setString("type", type);
        numberGenomes = (Long) query.uniqueResult();
        this.tx.commit();
        return numberGenomes;
    }

    public List<Organism> findByType(String type) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Organism.findByType");
            query.setString("type", type);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
    
        
    public List<String> bringGenera(String type) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Organism.bringGeneraByType");
            query.setString("type", type);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
    
        public List<Organism> findByName(String genera, String species, String strain, String type) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Organism.findByName");
            query.setString("genera", genera);
            query.setString("species", species);
            query.setString("strain", strain);
            query.setString("type", type);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
    


}
