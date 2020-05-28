/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.Genome;
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
public class GenomeDAO extends GenericDAO {

    public Genome findById(Integer id) {
        try {
            Genome genome = new Genome();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Genome.findById");
            query.setInteger("id", id);
            genome = (Genome) query.uniqueResult();
            this.tx.commit();
            return genome;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public Genome findByOrganism(Integer organism) {
        try {
            Genome genome = new Genome();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Genome.findByOrganism");
            query.setInteger("organism", organism);
            genome = (Genome) query.uniqueResult();
            this.tx.commit();
            return genome;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public Genome findByGbffFile(String gbffFile) {
        try {
            Genome genome = new Genome();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Genome.findByGbffFile");
            query.setString("gbffFile", gbffFile);
            genome = (Genome) query.uniqueResult();
            this.tx.commit();
            return genome;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public Genome findByGenomeName(String genomeName) {
        try {
            Genome genome = new Genome();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Genome.findByGenomeName");
            query.setString("genomeName", genomeName);
            genome = (Genome) query.uniqueResult();
            this.tx.commit();
            return genome;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    //findByOrganismTypeHash
    public List<Object[]> findByOrganismTypeHash(String type) {
        try {
            List lista = new ArrayList<Object[]>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Genome.findByOrganismTypeHash");
            query.setString("type", type);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<Object[]> findByOrganismTypeHashRna(String type) {
        try {
            List lista = new ArrayList<Object[]>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Genome.findByOrganismTypeHashRna");
            query.setString("type", type);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    //findByOrganismNotTypeHashRna    
    public List<Object[]> findByOrganismNotTypeHashRna(String type) {
        try {
            List lista = new ArrayList<Object[]>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Genome.findByOrganismNotTypeHashRna");
            query.setString("type", type);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
    
    public List<Genome> findByOrgnismType(String type) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Genome.findByOrganismType");
            query.setString("type", type);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<String> bringNamesByOrganismType(String type) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Genome.bringNamesByOrganismType");
            query.setString("type", type);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<Genome> listAll() {
        try {
            Genome genome = new Genome();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Genome.findAll");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    //findAllHash
    public List<Object[]> findAllHash() {
        try {
            Genome genome = new Genome();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Genome.findAllHash");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

//    public Long bringNumberOfGenomes() {
//        Long numberGenomes;
//        this.session = getSession();
//        this.tx = this.session.beginTransaction();
//        Query query = session.getNamedQuery("Genome.bringNumberOfGenomes");
//        numberGenomes = (Long) query.uniqueResult();
//        this.tx.commit();
//        return numberGenomes;
//    }
}
