/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Homologous;
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
public class HomologousDAO extends GenericDAO {
    
    
   
    public Object save(Homologous homologous) {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            this.session.save(homologous);
            tx.commit();
            return homologous;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public Homologous findById(Integer id) {
        try {
            Homologous homologous = new Homologous();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Homologous.findById");
            query.setInteger("id", id);
            homologous = (Homologous) query.uniqueResult();
            this.tx.commit();
            return homologous;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }
    
    public Long bringByModelAndTargetGenomes(Integer modelGenomeId, Integer targetGenomeId) {
        try {
            List<Homologous> homologous;
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Homologous.bringByModelAndTargetGenomes");
            query.setInteger("modelGenomeId", modelGenomeId);
            query.setInteger("targetGenomeId", targetGenomeId);
            Long num = (Long) query.uniqueResult();
            this.tx.commit();
            return num;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }
    
     public List findByModelAndTargetGenomes(Integer modelGenomeId, Integer targetGenomeId) {
        try {
            List<Homologous> homologous;
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Homologous.findByModelAndTargetGenomes");
            query.setInteger("modelGenomeId", modelGenomeId);
            query.setInteger("targetGenomeId", targetGenomeId);
            homologous = query.list();
            this.tx.commit();
            return homologous;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<Homologous> listAll() {
        try {
            Homologous homologous = new Homologous();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Homologous.findAll");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public Homologous findByPair(Integer geneOne, Integer geneTwo) {
        try {
            Homologous homologous = new Homologous();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Homologous.findByPair");
            query.setInteger("geneOne", geneOne);
            query.setInteger("geneTwo", geneTwo);
            homologous = (Homologous) query.uniqueResult();
            this.tx.commit();
            return homologous;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }
    
    public Homologous findByGeneOrganism(Integer gene, Integer organismId) {
        try {
            Homologous homologous = new Homologous();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Homologous.findByGeneOrganism");
            query.setInteger("gene", gene);
            query.setInteger("organismId", organismId);
            homologous = (Homologous) query.uniqueResult();
            this.tx.commit();
            return homologous;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }
    //findByGenome
      public List<Homologous> findByGenome(Integer genomeId) {
        try {
            
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Homologous.findByGenome");
            query.setInteger("genomeId", genomeId);
            List<Homologous> homologous = query.list();
            this.tx.commit();
            return homologous;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }
    
    //findByGeneGenomeNotParalogous
    public Homologous findByGeneGenomeNotParalogous(Integer gene, Integer genomeId) {
        try {
            Homologous homologous = new Homologous();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Homologous.findByGeneGenomeNotParalogous");
            query.setInteger("gene", gene);
            query.setInteger("genomeId", genomeId);
            //query.setInteger("genomeId2", genomeId);
            homologous = (Homologous) query.uniqueResult();
            this.tx.commit();
            return homologous;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }
    
    
    public Homologous findByGeneGenome(Integer gene, Integer genomeId) {
        try {
            Homologous homologous = new Homologous();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Homologous.findByGeneGenome");
            query.setInteger("gene", gene);
            query.setInteger("genomeId", genomeId);
            homologous = (Homologous) query.uniqueResult();
            this.tx.commit();
            return homologous;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }
    
    public List<Homologous> findByGeneGenomeMultiple(Integer gene, Integer genomeId) {
        try {
            List<Homologous> homologous;
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Homologous.findByGeneGenome");
            query.setInteger("gene", gene);
            query.setInteger("genomeId", genomeId);
            homologous = query.list();
            this.tx.commit();
            return homologous;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }
    
    public List<Homologous> findByGene(Integer gene) {
        try {
           
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Homologous.findByGene");
            query.setInteger("gene", gene);
            List<Homologous> list =  query.list();
            this.tx.commit();
            return list;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

}
