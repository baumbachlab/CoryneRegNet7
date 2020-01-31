/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.GeneOperon;
import org.hibernate.Query;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariana
 */
public class GeneOperonDAO extends GenericDAO {

    public GeneOperon findById(Integer id) {
        try {
            GeneOperon genome = new GeneOperon();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("GeneOperon.findById");
            query.setInteger("id", id);
            genome = (GeneOperon) query.uniqueResult();
            this.tx.commit();
            return genome;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public GeneOperon findByGene(Integer gene) {
        try {
            GeneOperon operon = new GeneOperon();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("GeneOperon.findByGene");
            query.setInteger("gene", gene);
            operon = (GeneOperon) query.uniqueResult();
            this.tx.commit();
            return operon;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }
    
    public List<GeneOperon> findByGeneMultiple(Integer gene) {
        try {
            List<GeneOperon> operon;
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("GeneOperon.findByGene");
            query.setInteger("gene", gene);
            operon = query.list();
            this.tx.commit();
            return operon;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<GeneOperon> findByGeneNameLocusTag(String gene) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("GeneOperon.findByGeneNameLocusTag");
            query.setString("alternativeLocusTag", gene);
            query.setString("locusTag", gene);
            query.setString("geneName", gene);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<GeneOperon> findByGeneNameLocusTagOfModelOrganism(String gene, String type) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("GeneOperon.findByGeneNameLocusTagOfModelOrganism");
            query.setString("alternativeLocusTag", gene);
            query.setString("locusTag", gene);
            query.setString("geneName", gene);
            query.setString("type", type);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<GeneOperon> findByOrganismGene(Integer organism, String gene) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("GeneOperon.findByOrganismGene");
            query.setInteger("organism", organism);
            query.setString("alternativeLocusTag", gene);
            query.setString("locusTag", gene);
            query.setString("geneName", gene);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<GeneOperon> findByOperon(Integer operon) {
        try {
            List lista = new ArrayList<Integer>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("GeneOperon.findByOperon");
            query.setInteger("operon", operon);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public GeneOperon findFirstGeneOfOperon(Integer operon, Integer pos) {
        try {
            GeneOperon geneOperon = new GeneOperon();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("GeneOperon.findFirstGeneOfOperon");
            query.setInteger("operon", operon);
            query.setInteger("pos", pos);
            geneOperon = (GeneOperon) query.uniqueResult();;
            this.tx.commit();
            return geneOperon;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<GeneOperon> findByOrganism(Integer organism) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("GeneOperon.findByOrganism");
            query.setInteger("organism", organism);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
    
    public List<Integer> findOperonsByOrganism(Integer organism) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("GeneOperon.findOperonsByOrganism");
            query.setInteger("organism", organism);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<GeneOperon> findModelOperons(String type) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("GeneOperon.findModelOperons");
            query.setString("type", type);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<GeneOperon> listAll() {
        try {
            GeneOperon genome = new GeneOperon();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("GeneOperon.findAll");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    //countOpByGenome
    public Long countOpByGenome(Integer genomeId) {
        Long numberGenes;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("GeneOperon.countOpByGenome");
        query.setInteger("genomeId", genomeId);
        numberGenes = (Long) query.uniqueResult();
        this.tx.commit();
        return numberGenes;
    }
    
}
