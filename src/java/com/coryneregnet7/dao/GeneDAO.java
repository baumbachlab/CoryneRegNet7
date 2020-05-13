/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.Gene;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

//teste!

/**
 *
 * @author mariana
 */
public class GeneDAO extends GenericDAO {

    public Gene findById(Integer id) {
        try {
            Gene gene = new Gene();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findById");
            query.setInteger("id", id);
            gene = (Gene) query.uniqueResult();
            this.tx.commit();
            return gene;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<Gene> findByLocusTagMultiple(String locusTag) {
        try {
            List<Gene> genes = new ArrayList<Gene>();
            Gene gene = new Gene();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByLocusTag");
            query.setString("locusTag", locusTag);
            genes = query.list();
            this.tx.commit();
            return genes;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public Gene findByLocusTag(String locusTag) {
        try {
            Gene gene = new Gene();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByLocusTag");
            query.setString("locusTag", locusTag);
            gene = (Gene) query.uniqueResult();
            this.tx.commit();
            return gene;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public Gene findByAltLocusTag(String alternativeLocusTag) {
        try {
            Gene gene = new Gene();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByAltLocusTag");
            query.setString("alternativeLocusTag", alternativeLocusTag);
            gene = (Gene) query.uniqueResult();
            this.tx.commit();
            return gene;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<Gene> findByPromoterRegion(Integer promoterRegion) {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByPromoterRegion");
            query.setInteger("promoterRegion", promoterRegion);
            List<Gene> gene = query.list();
            this.tx.commit();
            return gene;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<Gene> findByGenomeLocusTagProteinIdMultiple(Integer genome, String geneId) {
        try {
            List<Gene> genes = new ArrayList<Gene>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByGenomeLocusTagProteinId");
            query.setInteger("genome", genome);
            query.setString("locusTag", geneId);
            query.setString("proteinId", geneId);
            genes = query.list();
            this.tx.commit();
            return genes;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public Gene findByGenomeLocusTagProteinId(Integer genome, String geneId) {
        try {
            Gene gene = new Gene();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByGenomeLocusTagProteinId");
            query.setInteger("genome", genome);
            query.setString("locusTag", geneId);
            query.setString("proteinId", geneId);
            gene = (Gene) query.uniqueResult();
            this.tx.commit();
            return gene;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    //findByGenomeName
    public List<Gene> findByGenomeName(Integer genome, String name) {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByGenomeName");
            query.setInteger("genome", genome);
            query.setString("name", name);
            List<Gene> gene = query.list();
            this.tx.commit();
            return gene;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public Gene findByGenomeNameUnique(Integer genome, String name) {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByGenomeName");
            query.setInteger("genome", genome);
            query.setString("name", name);
            Gene gene = (Gene) query.uniqueResult();
            this.tx.commit();
            return gene;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public Gene findByGenomeLocusTagProteinId(Integer genome, String locusTag, String proteinId) {
        try {
            Gene gene = new Gene();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByGenomeLocusTagProteinId");
            query.setInteger("genome", genome);
            query.setString("locusTag", locusTag);
            query.setString("proteinId", proteinId);
            gene = (Gene) query.uniqueResult();
            this.tx.commit();
            return gene;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<Gene> findByOrganismGene(Integer organism, String gene) {
        try {
            List<Gene> genes = new ArrayList<Gene>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByOrganismGene");
            query.setInteger("organism", organism);
            query.setString("alternativeLocusTag", gene);
            query.setString("locusTag", gene);
            query.setString("geneName", gene);
            genes = query.list();
            this.tx.commit();
            return genes;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<Gene> findAllByLocusTagGeneName(String gene) {
        try {
            List<Gene> genes = new ArrayList<Gene>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findAllByLocusTagGeneName");
            query.setString("alternativeLocusTag", gene);
            query.setString("locusTag", gene);
            query.setString("geneName", gene);
            genes = query.list();
            this.tx.commit();
            return genes;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<Gene> findGenesOfModelsByLocusTagGeneName(String gene, String type) {
        try {
            List<Gene> genes = new ArrayList<Gene>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findGenesOfModelsByLocusTagGeneName");
            query.setString("alternativeLocusTag", gene);
            query.setString("locusTag", gene);
            query.setString("geneName", gene);
            query.setString("type", type);
            genes = query.list();
            this.tx.commit();
            return genes;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public Gene findGeneByLocusTagOrGeneName(Integer organismId, String gene) {
        try {
            Gene g = new Gene();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findGeneByLocusTagOrGeneName");
            query.setString("gene", gene);
            query.setInteger("organismId", organismId);
            g = (Gene) query.uniqueResult();
            this.tx.commit();
            return g;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public Gene findByGenomeLocusTag(Integer genome, String locusTag) {
        try {
            Gene gene = new Gene();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByGenomeLocusTag");
            query.setInteger("genome", genome);
            query.setString("locusTag", locusTag);
            gene = (Gene) query.uniqueResult();
            this.tx.commit();
            return gene;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    //findByGenomeOldLocusTag
    public Gene findByGenomeOldLocusTag(Integer genome, String altLocusTag) {
        try {
            Gene gene = new Gene();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByGenomeOldLocusTag");
            query.setInteger("genome", genome);
            query.setString("altLocusTag", altLocusTag);
            gene = (Gene) query.uniqueResult();
            this.tx.commit();
            return gene;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<Gene> findByGenomeOldLocusTagMultiple(Integer genome, String altLocusTag) {
        try {
            List<Gene> gene;
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByGenomeOldLocusTag");
            query.setInteger("genome", genome);
            query.setString("altLocusTag", altLocusTag);
            gene = query.list();
            this.tx.commit();
            return gene;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<Gene> findTranscriptionFactors() {
        try {
            List list = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findTranscriptionFactors");
            list = query.list();
            this.tx.commit();
            return list;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<Gene> findTranscriptionFactorsModelOrganisms() {
        try {
            List list = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findTranscriptionFactorsModelOrganisms");
            query.setString("model", "model");
            list = query.list();
            this.tx.commit();
            return list;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public Long findNumberOfTFsModelOrganisms() {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findNumberOfTFsModelOrganisms");
            query.setString("model", "model");
            Long num = (Long) query.uniqueResult();
            this.tx.commit();
            return num;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public Long findNumberOfTFs() {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findNumberOfTFs");
            Long num = (Long) query.uniqueResult();
            this.tx.commit();
            return num;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public Long findNumberOfTFsByOrganism(Integer organismId) {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findNumberOfTFsByOrganism");
            query.setInteger("organismId", organismId);
            Long num = (Long) query.uniqueResult();
            this.tx.commit();
            return num;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    //bringPromoterRegionSize
    public Long bringPromoterRegionSize(Integer genome) {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.bringPromoterRegionSize");
            query.setInteger("genome", genome);
            Long num = (Long) query.uniqueResult();
            this.tx.commit();
            return num;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public Long findNumberOfTFsModelOrganismsByRole(String role) {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findNumberOfTFsModelOrganismsByRole");
            query.setString("model", "model");
            query.setString("role", role);
            Long num = (Long) query.uniqueResult();
            this.tx.commit();
            return num;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public Long findNumberOfTFsByRole(String role) {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findNumberOfTFsByRole");
            query.setString("role", role);
            Long num = (Long) query.uniqueResult();
            this.tx.commit();
            return num;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public Long findNumberOfTFsByOrganismAndRole(String role, Integer organismId) {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findNumberOfTFsByOrganismAndRole");
            query.setString("role", role);
            query.setInteger("organismId", organismId);
            Long num = (Long) query.uniqueResult();
            this.tx.commit();
            return num;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<Gene> listAll() {
        try {
            Gene gene = new Gene();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findAll");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
    
    public List<Gene> findByNoLocusTag() {
        try {
            Gene gene = new Gene();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByNoLocusTag");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<Gene> findModels(String type) {
        try {
            Gene gene = new Gene();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findModels");
            query.setString("type", type);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<Gene> listAllGenesOfOrganism(Integer id) {
        try {
            Gene gene = new Gene();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.listAllGenesOfOrganism");
            query.setInteger("id", id);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public Long bringNumberOfGenes() {
        Long numberGenes;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("Gene.bringNumberOfGenes");
        numberGenes = (Long) query.uniqueResult();
        this.tx.commit();
        return numberGenes;
    }

    public List<Gene> findByHmmLogo() {
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("Gene.findByHmmLogo");
        List<Gene> list = query.list();
        this.tx.commit();
        return list;
    }

    public List<Gene> findByHmmLogoDotPng() {
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("Gene.findByHmmLogoDotPng");
        List<Gene> list = query.list();
        this.tx.commit();
        return list;
    }

    public Long bringNumberOfModelGenes(String type) {
        Long numberGenes;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("Gene.bringNumberOfModelGenes");
        query.setString("type", type);
        numberGenes = (Long) query.uniqueResult();
        this.tx.commit();
        return numberGenes;
    }

    public Long bringNumberOfGenesOfOrganism(Integer id) {
        Long numberGenes;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("Gene.bringNumberOfGenesOfOrganism");
        query.setInteger("id", id);
        numberGenes = (Long) query.uniqueResult();
        this.tx.commit();
        return numberGenes;
    }

    public Long bringNumberOfProteinsOfOrganism(Integer id) {
        Long numberProteins;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("Gene.bringNumberOfProteinsOfOrganism");
        query.setInteger("id", id);
        numberProteins = (Long) query.uniqueResult();
        this.tx.commit();
        return numberProteins;
    }

    public Long bringNumberOfProteins() {
        Long numberProteins;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("Gene.bringNumberOfProteins");
        numberProteins = (Long) query.uniqueResult();
        this.tx.commit();
        return numberProteins;
    }

    public Long bringNumberOfModelProteins(String type) {
        Long numberProteins;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("Gene.bringNumberOfModelProteins");
        query.setString("type", type);
        numberProteins = (Long) query.uniqueResult();
        this.tx.commit();
        return numberProteins;
    }

    public Long bringNumberOfModelHmmProfiles(String type) {
        Long numberHmmProfiles;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("Gene.bringNumberOfModelHmmProfiles");
        query.setString("type", type);
        numberHmmProfiles = (Long) query.uniqueResult();
        this.tx.commit();
        return numberHmmProfiles;
    }

    public Long bringNumberOfHmmProfiles() {
        Long numberHmmProfiles;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("Gene.bringNumberOfHmmProfiles");
        numberHmmProfiles = (Long) query.uniqueResult();
        this.tx.commit();
        return numberHmmProfiles;
    }

    public Long bringNumberOfHmmProfilesOfOrganism(Integer organismId) {
        Long numberHmmProfiles;
        this.session = getSession();
        this.tx = this.session.beginTransaction();
        Query query = session.getNamedQuery("Gene.bringNumberOfHmmProfilesOfOrganism");
        query.setInteger("organismId", organismId);
        numberHmmProfiles = (Long) query.uniqueResult();
        this.tx.commit();
        return numberHmmProfiles;
    }

    public List<Gene> findByGenomeLocusTagEndPositionReverse(Integer genome, String locusTag, BigInteger promoterStart,
            BigInteger promoterEnd) {
        // "SELECT g FROM Gene g WHERE g.genome = :genome and g.locusTag != :locusTag and g.endPosition < :promoterEnd and g.endPosition > :promoterStart ")
        try {
            Gene gene = new Gene();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByGenomeLocusTagEndPositionReverse");
            query.setInteger("genome", genome);
            query.setString("locusTag", locusTag);
            query.setBigInteger("promoterStart", promoterStart);
            query.setBigInteger("promoterEnd", promoterEnd);
            List<Gene> list = query.list();
            this.tx.commit();
            return list;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<Gene> findByGenomeLocusTagStartPositionReverse(Integer genome, String locusTag, BigInteger promoterStart,
            BigInteger promoterEnd) {
        // "SELECT g FROM Gene g WHERE g.genome = :genome and g.locusTag != :locusTag and g.startPosition > :promoterStart and g.startPosition < :promoterEnd")
        try {
            Gene gene = new Gene();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByGenomeLocusTagStartPositionReverse");
            query.setInteger("genome", genome);
            query.setString("locusTag", locusTag);
            query.setBigInteger("promoterEnd", promoterEnd);
            query.setBigInteger("promoterStart", promoterStart);
            List<Gene> list = query.list();
            this.tx.commit();
            return list;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<Gene> findByGenomeLocusTagStartPositionForward(Integer genome, String locusTag, BigInteger promoterStart,
            BigInteger promoterEnd) {
        // "SELECT g FROM Gene g WHERE g.genome = :genome and g.locusTag != :locusTag and g.startPosition > :promoterStart and g.startPosition < :promoterEnd")
        try {
            Gene gene = new Gene();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByGenomeLocusTagStartPositionForward");
            query.setInteger("genome", genome);
            query.setString("locusTag", locusTag);
            query.setBigInteger("promoterEnd", promoterEnd);
            query.setBigInteger("promoterStart", promoterStart);
            List<Gene> list = query.list();
            this.tx.commit();
            return list;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<Gene> findByGenomeLocusTagEndPositionForward(Integer genome, String locusTag, BigInteger promoterStart,
            BigInteger promoterEnd) {
        // "SELECT g FROM Gene g WHERE g.genome = :genome and g.locusTag != :locusTag and g.startPosition > :promoterStart and g.startPosition < :promoterEnd")
        try {
            Gene gene = new Gene();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByGenomeLocusTagEndPositionForward");
            query.setInteger("genome", genome);
            query.setString("locusTag", locusTag);
            query.setBigInteger("promoterEnd", promoterEnd);
            query.setBigInteger("promoterStart", promoterStart);
            List<Gene> list = query.list();
            this.tx.commit();
            return list;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<Gene> findByGenome(Integer genome) {
        try {
            List list = new ArrayList<>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByGenome");
            query.setInteger("genome", genome);
            list = query.list();
            this.tx.commit();
            return list;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<Gene> findByOrganism(Integer organism) {
        try {
            List list = new ArrayList<>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByOrganism");
            query.setInteger("organism", organism);
            list = query.list();
            this.tx.commit();
            return list;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<Gene> findByGenomeRoleMinBsNum(Integer genome, Integer minNOfHMMs) {
        try {
            List list = new ArrayList<>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByGenomeRoleMinBsNum");
            query.setInteger("genome", genome);
            query.setInteger("minNOfHMMs", minNOfHMMs);
            list = query.list();
            this.tx.commit();
            return list;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<Gene> findByOrganismRoleMinBsNum(Integer organism, Integer minNOfHMMs) {
        try {
            List list = new ArrayList<>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByOrganismRoleMinBsNum");
            query.setInteger("organism", organism);
            query.setInteger("minNOfHMMs", minNOfHMMs);
            list = query.list();
            this.tx.commit();
            return list;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public Integer findByGenomeMax(Integer genome) {
        try {
            Integer gene = 0;
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByGenomeMax");
            query.setInteger("genome", genome);
            gene = (Integer) query.uniqueResult();
            this.tx.commit();
            return gene;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public Integer findByGenomeMin(Integer genome) {
        try {
            Integer gene = 0;
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findByGenomeMin");
            query.setInteger("genome", genome);
            gene = (Integer) query.uniqueResult();
            this.tx.commit();
            return gene;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    //findIfPredictedTfTg
    public Gene findIfPredictedTfTg(Integer geneId) {
        try {
            Gene gene = new Gene();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findIfPredictedTfTg");
            query.setInteger("geneId", geneId);
            gene = (Gene) query.uniqueResult();
            this.tx.commit();
            return gene;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    //findIfExperimentalTfTg
    public Gene findIfExperimentalTfTg(Integer geneId) {
        try {
            Gene gene = new Gene();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("Gene.findIfExperimentalTfTg");
            query.setInteger("geneId", geneId);
            gene = (Gene) query.uniqueResult();
            this.tx.commit();
            return gene;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
}
