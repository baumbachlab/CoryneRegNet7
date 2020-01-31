/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.Gene;
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
public class RegulatoryInteractionDAO extends GenericDAO {

    public RegulatoryInteraction findById(Integer id) {
        try {
            RegulatoryInteraction gene = new RegulatoryInteraction();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findById");
            query.setInteger("id", id);
            gene = (RegulatoryInteraction) query.uniqueResult();
            this.tx.commit();
            return gene;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<RegulatoryInteraction> findRegulates(Integer correspondentTranscriptionFactor) {
        try {
            List<RegulatoryInteraction> regulatoryInteractions = new ArrayList<RegulatoryInteraction>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findRegulates");
            query.setInteger("correspondentTranscriptionFactor", correspondentTranscriptionFactor);
            regulatoryInteractions = query.list();
            this.tx.commit();
            return regulatoryInteractions;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<RegulatoryInteraction> findByTG(Integer correspondentTargetGene) {
        try {
            List<RegulatoryInteraction> regulatoryInteractions = new ArrayList<RegulatoryInteraction>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findRegulatedBy");
            query.setInteger("correspondentTargetGene", correspondentTargetGene);
            regulatoryInteractions = query.list();
            this.tx.commit();
            return regulatoryInteractions;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<Gene> findByDistinctTG() {
        try {
            List<Gene> genes = new ArrayList<Gene>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findByDistinctTG");
            genes = query.list();
            this.tx.commit();
            return genes;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<Gene> findByOrganismAndDistinctTG(Integer organismId) {
        try {
            List<Gene> genes = new ArrayList<Gene>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findByOrganismAndDistinctTG");
            query.setInteger("organismId", organismId);
            genes = query.list();
            this.tx.commit();
            return genes;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<Gene> findByDistinctTF() {
        try {
            List<Gene> genes = new ArrayList<Gene>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findByDistinctTF");
            genes = query.list();
            this.tx.commit();
            return genes;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<Gene> findByOrganismDistinctTF(Integer organismId) {
        try {
            List<Gene> genes = new ArrayList<Gene>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findByOrganismDistinctTF");
            query.setInteger("organismId", organismId);
            genes = query.list();
            this.tx.commit();
            return genes;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<Gene> findByOrganismTFAndRole(Integer organismId, int tf, String role) {
        try {
            List<Gene> genes = new ArrayList<Gene>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findByOrganismTFAndRole");
            query.setInteger("organismId", organismId);
            query.setInteger("tf", tf);
            query.setString("role", role);
            genes = query.list();
            this.tx.commit();
            return genes;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
    
        public List<Gene> findByOrganismTF(Integer organismId, Integer tf) {
        try {
            List<Gene> genes = new ArrayList<Gene>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findByOrganismTF");
            query.setInteger("organismId", organismId);
            query.setInteger("tf", tf);
            genes = query.list();
            this.tx.commit();
            return genes;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
    
//    public List<Gene> findByOrganismSigma(Integer organismId, int tf, String isSigma) {
//        try {
//            List<Gene> genes = new ArrayList<Gene>();
//            this.session = getSession();
//            this.tx = this.session.beginTransaction();
//            Query query = session.getNamedQuery("RegulatoryInteraction.findByOrganismSigma");
//            query.setInteger("organismId", organismId);
//            query.setInteger("tf", tf);
//            query.setString("isSigma", isSigma);
//            genes = query.list();
//            this.tx.commit();
//            return genes;
//        } catch (Exception E) {
//            System.out.println("Exception = " + E.toString());
//            return null;
//        }
//    }

    public List<RegulatoryInteraction> findByOrganismAndGene(Integer organismId, Integer id) {
        try {
            List<RegulatoryInteraction> ris = new ArrayList<RegulatoryInteraction>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findByOrganismAndGene");
            query.setInteger("organismId", organismId);
            query.setInteger("id", id);
            ris = query.list();
            this.tx.commit();
            return ris;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<RegulatoryInteraction> listAll() {
        try {
            RegulatoryInteraction gene = new RegulatoryInteraction();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findAll");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public Long bringNumberOfRegulatoryInteractions() {
        Long numberRegulatoryInteractions;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("RegulatoryInteraction.bringNumberOfRegulatoryInteractions");
        numberRegulatoryInteractions = (Long) query.uniqueResult();
        this.tx.commit();
        return numberRegulatoryInteractions;
    }

    public Long bringNumberOfRIsByOrganism(Integer organismId) {
        Long numberRegulatoryInteractions;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("RegulatoryInteraction.bringNumberOfRIsByOrganism");
        query.setInteger("organismId", organismId);
        numberRegulatoryInteractions = (Long) query.uniqueResult();
        this.tx.commit();
        return numberRegulatoryInteractions;
    }

    public Long bringNumberOfRIsByRole(String role) {
        Long numberRegulatoryInteractions;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("RegulatoryInteraction.bringNumberOfRIsByRole");
        query.setString("role", role);
        numberRegulatoryInteractions = (Long) query.uniqueResult();
        this.tx.commit();
        return numberRegulatoryInteractions;
    }

    public Long bringNumberOfRIsByOrganismAndRole(String role, Integer organismId) {
        Long numberRegulatoryInteractions;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("RegulatoryInteraction.bringNumberOfRIsByOrganismAndRole");
        query.setInteger("organismId", organismId);
        query.setString("role", role);
        numberRegulatoryInteractions = (Long) query.uniqueResult();
        this.tx.commit();
        return numberRegulatoryInteractions;
    }

    public Long bringNumberOfRegulationsOfTg(Integer gene) {
        Long numberRegulationsOfTg;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("RegulatoryInteraction.bringNumberOfRegulationsOfTg");
        query.setInteger("gene", gene);
        numberRegulationsOfTg = (Long) query.uniqueResult();
        this.tx.commit();
        return numberRegulationsOfTg;
    }

    public Long bringNumberOfRegulationsOfTf(Integer gene) {
        Long numberRegulationsOfTg;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("RegulatoryInteraction.bringNumberOfRegulationsOfTf");
        query.setInteger("gene", gene);
        numberRegulationsOfTg = (Long) query.uniqueResult();
        this.tx.commit();
        return numberRegulationsOfTg;
    }

    public Long bringNumberOfRIsOfOrganism(Integer organismId) {
        Long numberRegulatoryInteractions;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("RegulatoryInteraction.bringNumberOfRIsOfOrganism");
        query.setInteger("organismId", organismId);
        numberRegulatoryInteractions = (Long) query.uniqueResult();
        this.tx.commit();
        return numberRegulatoryInteractions;
    }

    public Long bringNumberOfRegulators() {
        Long numberOfRegulators;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("RegulatoryInteraction.bringNumberOfRegulators");
        numberOfRegulators = (Long) query.uniqueResult();
        this.tx.commit();
        return numberOfRegulators;
    }

    public Long bringNumberOfRegulatorsOfOrganism(Integer organismId) {
        Long numberOfRegulators;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("RegulatoryInteraction.bringNumberOfRegulatorsOfOrganism");
        query.setInteger("organismId", organismId);
        numberOfRegulators = (Long) query.uniqueResult();
        this.tx.commit();
        return numberOfRegulators;
    }

    public Long bringNumberOfRegulatedGenes() {
        Long numberOfRegulatedGenes;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("RegulatoryInteraction.bringNumberOfRegulatedGenes");
        numberOfRegulatedGenes = (Long) query.uniqueResult();
        this.tx.commit();
        return numberOfRegulatedGenes;
    }
    
    public Long bringNumberOfRegulatedGenesOfOrganism(Integer organismId) {
        Long numberOfRegulatedGenes;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("RegulatoryInteraction.bringNumberOfRegulatedGenesOfOrganism");
        query.setInteger("organismId", organismId);
        numberOfRegulatedGenes = (Long) query.uniqueResult();
        this.tx.commit();
        return numberOfRegulatedGenes;
    }

    public List<RegulatoryInteraction> findByGenome(Integer genomeId) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findByGenome");
            query.setInteger("genome", genomeId);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public ArrayList<RegulatoryInteraction> findByOrganism(Integer organism) {
        try {
            ArrayList lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findByOrganism");
            query.setInteger("organism", organism);
            lista = (ArrayList) query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<Gene> findByGenomeDistinct(Integer genomeId) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findByGenomeDistinct");
            query.setInteger("genome", genomeId);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<RegulatoryInteraction> findByGenomeAndName(Integer genomeId, String name) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findByGenomeAndName");
            query.setInteger("genome", genomeId);
            query.setString("locusTag", name);
            query.setString("altLocusTag", name);
            query.setString("proteinId", name);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public Long bringNumberOfCoregulators(String locusTag) {
        Long numberOfCoregulators;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("RegulatoryInteraction.bringNumberOfCoregulators");
        query.setString("locusTag", locusTag);
        numberOfCoregulators = (Long) query.uniqueResult();
        this.tx.commit();
        return numberOfCoregulators;
    }

    public List<Gene> findTFbyGenome(Integer genomeId) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findTFbyGenome");
            query.setInteger("genome", genomeId);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public RegulatoryInteraction findByTfTg(Integer tfId, Integer tgId) {
        try {
            RegulatoryInteraction ri = new RegulatoryInteraction();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findByTfTgId");
            query.setInteger("tfId", tfId);
            query.setInteger("tgId", tgId);
            ri = (RegulatoryInteraction) query.uniqueResult();
            this.tx.commit();
            return ri;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<RegulatoryInteraction> findByTfTgMultiple(Integer tfId, Integer tgId) {
        try {
            List<RegulatoryInteraction> ri = new ArrayList<>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findByTfTgId");
            query.setInteger("tfId", tfId);
            query.setInteger("tgId", tgId);
            ri = query.list();
            this.tx.commit();
            return ri;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    
    public List<RegulatoryInteraction> bringRisByTfTg(Integer tfId, Integer tgId) {
        try {
            RegulatoryInteraction ri = new RegulatoryInteraction();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findByTfTgId");
            query.setInteger("tfId", tfId);
            query.setInteger("tgId", tgId);
            List ris = query.list();
            this.tx.commit();
            return ris;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    //, @NamedQuery(name = "RegulatoryInteraction.findByTfTgRole", query = "SELECT r FROM RegulatoryInteraction r WHERE r.correspondentTranscriptionFactor.locusTag = :tfId and r.correspondentTargetGene.locusTag = :tgId and r.role = :role")
        public RegulatoryInteraction findByTfTgRole(String tfId, String tgId, String role) {
        try {
            RegulatoryInteraction ri = new RegulatoryInteraction();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findByTfTgRole");
            query.setString("tfId", tfId);
            query.setString("tgId", tgId);
            query.setString("role", role);
            ri = (RegulatoryInteraction)query.uniqueResult();
            this.tx.commit();
            return ri;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }
    
    
    public List<String> bringRoleByTf(Integer transcriptionFactor) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.bringRoleByTf");
            query.setInteger("transcriptionFactor", transcriptionFactor);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<Gene> findTgsOfTf(Integer id) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findTgsOfTf");
            query.setInteger("id", id);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<Gene> findTFsOfTg(Integer id) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findTFsOfTg");
            query.setInteger("id", id);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<RegulatoryInteraction> findByTF(Integer correspondentTF) {
        try {
            List<RegulatoryInteraction> regulatoryInteractions = new ArrayList<RegulatoryInteraction>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteraction.findByTF");
            query.setInteger("correspondentTranscriptionFactor", correspondentTF);
            regulatoryInteractions = query.list();
            this.tx.commit();
            return regulatoryInteractions;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }
}
