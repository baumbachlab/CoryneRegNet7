/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.BindingSite;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.PredictedRegulatoryInteraction;
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
public class PredictedRegulatoryInteractionDAO extends GenericDAO {

    public PredictedRegulatoryInteraction findById(Integer id) {
        try {
            PredictedRegulatoryInteraction gene = new PredictedRegulatoryInteraction();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findById");
            query.setInteger("id", id);
            gene = (PredictedRegulatoryInteraction) query.uniqueResult();
            this.tx.commit();
            return gene;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<PredictedRegulatoryInteraction> listAll() {
        try {
            PredictedRegulatoryInteraction gene = new PredictedRegulatoryInteraction();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findAll");
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
        Query query = session.getNamedQuery("PredictedRegulatoryInteraction.bringNumberOfRegulatoryInteractions");
        numberRegulatoryInteractions = (Long) query.uniqueResult();
        this.tx.commit();
        return numberRegulatoryInteractions;
    }

    public Long bringNumberOfRIsByRole(String role) {
        Long numberRegulatoryInteractions;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("PredictedRegulatoryInteraction.bringNumberOfRIsByRole");
        query.setString("role", role);
        numberRegulatoryInteractions = (Long) query.uniqueResult();
        this.tx.commit();
        return numberRegulatoryInteractions;
    }

    public Long bringNumberOfRIsByOrganism(Integer organismId) {
        Long numberRegulatoryInteractions;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("PredictedRegulatoryInteraction.bringNumberOfRIsByOrganism");
        query.setInteger("organismId", organismId);
        numberRegulatoryInteractions = (Long) query.uniqueResult();
        this.tx.commit();
        return numberRegulatoryInteractions;
    }

    public Long bringNumberOfPredictedRegulatoryInteractions() {
        Long numberRegulatoryInteractions;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("PredictedRegulatoryInteraction.bringNumberOfPredictedRegulatoryInteractions");
        numberRegulatoryInteractions = (Long) query.uniqueResult();
        this.tx.commit();
        return numberRegulatoryInteractions;
    }

    public Long bringNumberOfRegulators() {
        Long numberOfRegulators;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("PredictedRegulatoryInteraction.bringNumberOfRegulators");
        numberOfRegulators = (Long) query.uniqueResult();
        this.tx.commit();
        return numberOfRegulators;
    }

    public Long bringNumberOfRIsByOrganismAndRole(String role, Integer organismId) {
        Long numberRegulatoryInteractions;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("PredictedRegulatoryInteraction.bringNumberOfRIsByOrganismAndRole");
        query.setInteger("organismId", organismId);
        query.setString("role", role);
        numberRegulatoryInteractions = (Long) query.uniqueResult();
        this.tx.commit();
        return numberRegulatoryInteractions;
    }

    public Long bringNumberOfPredictedRegulators() {
        Long numberOfRegulators;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("PredictedRegulatoryInteraction.bringNumberOfPredictedRegulators");
        numberOfRegulators = (Long) query.uniqueResult();
        this.tx.commit();
        return numberOfRegulators;
    }

    public Long bringNumberOfPredictedRegulatedGenes() {
        Long numberOfRegulatedGenes;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("PredictedRegulatoryInteraction.bringNumberOfPredictedRegulatedGenes");
        numberOfRegulatedGenes = (Long) query.uniqueResult();
        this.tx.commit();
        return numberOfRegulatedGenes;
    }

    public List<PredictedRegulatoryInteraction> findRegulates(Integer transcriptionFactor) {
        try {
            List<PredictedRegulatoryInteraction> pRegulatoryInteractions = new ArrayList<PredictedRegulatoryInteraction>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findRegulates");
            query.setInteger("transcriptionFactor", transcriptionFactor);
            pRegulatoryInteractions = query.list();
            this.tx.commit();
            return pRegulatoryInteractions;
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
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findByDistinctTG");
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
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findByOrganismAndDistinctTG");
            query.setInteger("organismId", organismId);
            genes = query.list();
            this.tx.commit();
            return genes;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<BindingSite> findBindingSitesOfOrganism(Integer organismId) {
        try {
            List<BindingSite> bss = new ArrayList<>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findBindingSitesOfOrganism");
            query.setInteger("organismId", organismId);
            bss = query.list();
            this.tx.commit();
            return bss;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public Long bringNumberOfBSs(Integer organismId) {
        Long numberRegulatoryInteractions;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("PredictedRegulatoryInteraction.bringNumberOfBSs");
        query.setInteger("organismId", organismId);
        numberRegulatoryInteractions = (Long) query.uniqueResult();
        this.tx.commit();
        return numberRegulatoryInteractions;
    }

    public Long bringNumberOfPRIsOfOrganism(Integer organismId) {
        Long numberRegulatoryInteractions;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("PredictedRegulatoryInteraction.bringNumberOfPRIsOfOrganism");
        query.setInteger("organismId", organismId);
        numberRegulatoryInteractions = (Long) query.uniqueResult();
        this.tx.commit();
        return numberRegulatoryInteractions;
    }

    public Long bringNumberOfRegulatorsOfOrganism(Integer organismId) {
        Long numberOfRegulators;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("PredictedRegulatoryInteraction.bringNumberOfRegulatorsOfOrganism");
        query.setInteger("organismId", organismId);
        numberOfRegulators = (Long) query.uniqueResult();
        this.tx.commit();
        return numberOfRegulators;
    }

    public Long bringNumberOfRegulatedGenesOfOrganism(Integer organismId) {
        Long numberOfRegulatedGenes;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("PredictedRegulatoryInteraction.bringNumberOfRegulatedGenesOfOrganism");
        query.setInteger("organismId", organismId);
        numberOfRegulatedGenes = (Long) query.uniqueResult();
        this.tx.commit();
        return numberOfRegulatedGenes;
    }

    public List<PredictedRegulatoryInteraction> findActivationsAndRepressionsPRIsWithBS() {
        try {
            List<PredictedRegulatoryInteraction> pris = new ArrayList<>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findActivationsAndRepressionsPRIsWithBS");
            pris = query.list();
            this.tx.commit();
            return pris;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<PredictedRegulatoryInteraction> findByOrganismActivationsAndRepressionsPRIsWithBS(Integer organismId) {
        try {
            List<PredictedRegulatoryInteraction> pris = new ArrayList<>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findByOrganismActivationsAndRepressionsPRIsWithBS");
            query.setInteger("organismId", organismId);
            pris = query.list();
            this.tx.commit();
            return pris;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<BindingSite> findPredictedBindingSites() {
        try {
            List<BindingSite> bss = new ArrayList<>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findPredictedBindingSites");
            bss = query.list();
            this.tx.commit();
            return bss;
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
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findByDistinctTF");
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
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findByOrganismDistinctTF");
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
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findByOrganismTFAndRole");
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

    public Long bringNumberOfRegulationsOfTg(Integer gene) {
        Long numberRegulationsOfTg;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("PredictedRegulatoryInteraction.bringNumberOfRegulationsOfTg");
        query.setInteger("gene", gene);
        numberRegulationsOfTg = (Long) query.uniqueResult();
        this.tx.commit();
        return numberRegulationsOfTg;
    }

    public Long bringNumberOfRegulationsOfTf(Integer gene) {
        Long numberRegulationsOfTg;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("PredictedRegulatoryInteraction.bringNumberOfRegulationsOfTf");
        query.setInteger("gene", gene);
        numberRegulationsOfTg = (Long) query.uniqueResult();
        this.tx.commit();
        return numberRegulationsOfTg;
    }

    public List<PredictedRegulatoryInteraction> findByTG(Integer targetGene) {
        try {
            List<PredictedRegulatoryInteraction> pRegulatoryInteractions = new ArrayList<PredictedRegulatoryInteraction>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findRegulatedBy");
            query.setInteger("targetGene", targetGene);
            pRegulatoryInteractions = query.list();
            this.tx.commit();
            return pRegulatoryInteractions;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public Long bringNumberOfCoregulators(String locusTag) {
        Long numberOfCoregulators;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("PredictedRegulatoryInteraction.bringNumberOfCoregulators");
        query.setString("locusTag", locusTag);
        numberOfCoregulators = (Long) query.uniqueResult();
        this.tx.commit();
        return numberOfCoregulators;
    }

    public ArrayList<PredictedRegulatoryInteraction> findByOrganism(Integer organism) {
        try {
            ArrayList lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findByOrganism");
            query.setInteger("organism", organism);
            lista = (ArrayList) query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
    
      public List<PredictedRegulatoryInteraction> findByOrganismList(Integer organism) {
        try {
            ArrayList lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findByOrganism");
            query.setInteger("organism", organism);
            lista = (ArrayList) query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public PredictedRegulatoryInteraction findByTfTg(Integer tfId, Integer tgId) {
        try {
            PredictedRegulatoryInteraction pri = new PredictedRegulatoryInteraction();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findByTfTgId");
            query.setInteger("tfId", tfId);
            query.setInteger("tgId", tgId);
            pri = (PredictedRegulatoryInteraction) query.uniqueResult();
            this.tx.commit();
            return pri;
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
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findByGenomeDistinct");
            query.setInteger("genome", genomeId);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public ArrayList<BindingSite> findByHtf(Integer homologousTranscriptionFactor) {
        try {
            ArrayList lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findByHtf");
            query.setInteger("homologousTranscriptionFactor", homologousTranscriptionFactor);
            lista = (ArrayList) query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<String> bringRoleByTf(Integer homologousTranscriptionFactor) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.bringRoleByTf");
            query.setInteger("homologousTranscriptionFactor", homologousTranscriptionFactor);
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
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findTgsOfTf");
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
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findTFsOfTg");
            query.setInteger("id", id);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
    
    //bringNumByModelTargetGenome
    public Long bringNumByModelTargetGenome(Integer templateGenome, Integer targetGenome) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.bringNumByModelTargetGenome");
            //SELECT p.regulatoryInteraction FROM PredictedRegulatoryInteraction p WHERE p.regulatoryInteraction.correspondentTargetGene.genome = :templateGenome and p.homologousTranscriptionFactor.genome =:targetGenome
            query.setInteger("templateGenome", templateGenome);
            query.setInteger("targetGenome", targetGenome);
            Long size = (Long) query.uniqueResult();
            this.tx.commit();
            return size;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
    
    //findNumByModelTargetGenome
     public List<PredictedRegulatoryInteraction> findNumByModelTargetGenome(Integer templateGenome, Integer targetGenome) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findNumByModelTargetGenome");
            //SELECT p.regulatoryInteraction FROM PredictedRegulatoryInteraction p WHERE p.regulatoryInteraction.correspondentTargetGene.genome = :templateGenome and p.homologousTranscriptionFactor.genome =:targetGenome
            query.setInteger("templateGenome", templateGenome);
            query.setInteger("targetGenome", targetGenome);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<RegulatoryInteraction> findAllRIByModelTargetGenome(Integer templateGenome, Integer targetGenome) {
        try {
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findAllRIByModelTargetGenome");
            //SELECT p.regulatoryInteraction FROM PredictedRegulatoryInteraction p WHERE p.regulatoryInteraction.correspondentTargetGene.genome = :templateGenome and p.homologousTranscriptionFactor.genome =:targetGenome
            query.setInteger("templateGenome", templateGenome);
            query.setInteger("targetGenome", targetGenome);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<PredictedRegulatoryInteraction> findByOrganismAndGene(Integer organismId, Integer id) {
        try {
            List<PredictedRegulatoryInteraction> ris = new ArrayList<PredictedRegulatoryInteraction>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("PredictedRegulatoryInteraction.findByOrganismAndGene");
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
}
