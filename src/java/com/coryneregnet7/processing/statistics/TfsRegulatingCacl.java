/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.statistics;

import com.coryneregnet7.test.*;
import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.OrganismDAO;
import com.coryneregnet7.dao.PredictedRegulatoryInteractionDAO;
import com.coryneregnet7.dao.RefStatisticsDAO;
import com.coryneregnet7.dao.TfsRegulatingDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.TfsRegulating;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Organism;
import com.coryneregnet7.model.RefStatistics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author mariana
 */
public class TfsRegulatingCacl {

    public void calculate() {
        /**
         * It need to be adapted to check if the refStatistics exists if
         * (refStatistics == null) { refStatistics = new RefStatistics();
         * refStatistics.setType("strain"); if
         * (genomes.get(i).getOrganism().getType().equals("model")) {
         * refStatistics.setDatabase("experimental"); } else {
         * refStatistics.setDatabase("predicted"); }
         * refStatistics.setGenome(genomes.get(i)); try {
         * rsDAO.save(refStatistics); System.out.println("Salvei 3 " +
         * refStatistics); } catch (Exception E) { System.out.println(E); } }
         */

//
//TfsRegulatingTest rrTest = new TfsRegulatingTest();
        //rrTest.delete();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        List<Gene> genes = riDAO.findByDistinctTG();
        List<Gene> pGenes = priDAO.findByDistinctTG();
        TreeMap<Long, Integer> tfsRegAGeneAux = new TreeMap<>();
        ArrayList<Genome> genomes = new ArrayList<>();
        GenomeDAO genomeDAO = new GenomeDAO();
        Long numTfs = new Long(0);
        Integer numTgs = 0;
        TfsRegulatingDAO trDAO = new TfsRegulatingDAO();
        TfsRegulating tfsRegulating = new TfsRegulating();
        ArrayList<TfsRegulating> tfsRegulatingAux = new ArrayList<>();
        RefStatisticsDAO rsDAO = new RefStatisticsDAO();
        RefStatistics refStatistics = new RefStatistics();
        String type;

// System.out.println("i==0");
        // System.out.println("experimental");
        for (int k = 0; k < genes.size(); k++) {
            //    System.out.println("gene: " + genes.get(k));
            numTfs = riDAO.bringNumberOfRegulationsOfTg(genes.get(k).getId());
            //  System.out.println("numTFs: " + numTfs);
            if (tfsRegAGeneAux.isEmpty()) {
                numTgs++;
                tfsRegAGeneAux.put(numTfs, numTgs);
            } else {
                if (tfsRegAGeneAux.containsKey(numTfs)) {
                    numTgs = tfsRegAGeneAux.get(numTfs) + 1;
                    tfsRegAGeneAux.put(numTfs, numTgs);
                } else {
                    tfsRegAGeneAux.put(numTfs, 1);
                }
            }
        }

        refStatistics = rsDAO.findByTypeAndDatabase("database", "experimental");
        if (refStatistics == null) {
            refStatistics = new RefStatistics();
            refStatistics.setType("database");
            refStatistics.setDatabase("experimental");
            try {
                rsDAO.save(refStatistics);
                System.out.println("Salvei 1 " + refStatistics);

            } catch (Exception E) {
                System.out.println(E);
            }
        }

        tfsRegulatingAux = trDAO.findByRefStatistics(refStatistics.getId());
        if (tfsRegulatingAux != null) {
            for (TfsRegulating tfsRegulating1 : tfsRegulatingAux) {
                if (tfsRegulating1 != null) {
                    trDAO.delete(tfsRegulating1);
                }
            }
        }

        for (Map.Entry<Long, Integer> tfsRegAGene : tfsRegAGeneAux.entrySet()) {
            System.out.println("Key: " + tfsRegAGene.getKey());
            System.out.println("Value: " + tfsRegAGene.getValue());

            tfsRegulating = new TfsRegulating();
            tfsRegulating.setNumTf(tfsRegAGene.getKey() == null ? null : Math.toIntExact(tfsRegAGene.getKey()));
            tfsRegulating.setNumTg(tfsRegAGene.getValue());
            tfsRegulating.setIdRefStatistics(refStatistics);

            try {
                trDAO.save(tfsRegulating);
            } catch (Exception E) {
                System.out.println(E);
            }
        }

        for (int k = 0; k < pGenes.size(); k++) {
            //    System.out.println("pGene: " + pGenes.get(k));
            numTfs = priDAO.bringNumberOfRegulationsOfTg(pGenes.get(k).getId());
            //  System.out.println("numTFs: " + numTfs);
            if (tfsRegAGeneAux.containsKey(numTfs)) {
                numTgs = tfsRegAGeneAux.get(numTfs) + 1;
                tfsRegAGeneAux.put(numTfs, numTgs);
            } else {
                tfsRegAGeneAux.put(numTfs, 1);
            }
        }

        refStatistics = rsDAO.findByTypeAndDatabase("database", "predicted");
        if (refStatistics == null) {
            refStatistics = new RefStatistics();
            refStatistics.setType("database");
            refStatistics.setDatabase("predicted");
            try {
                rsDAO.save(refStatistics);
                System.out.println("Salvei 2 " + refStatistics);
            } catch (Exception E) {
                System.out.println(E);
            }
        }

        tfsRegulatingAux = trDAO.findByRefStatistics(refStatistics.getId());
        if (tfsRegulatingAux != null) {
            for (TfsRegulating tfsRegulating1 : tfsRegulatingAux) {
                if (tfsRegulating1 != null) {
                    trDAO.delete(tfsRegulating1);
                }
            }
        }

        for (Map.Entry<Long, Integer> tfsRegAGene : tfsRegAGeneAux.entrySet()) {
            System.out.println("---Key: " + tfsRegAGene.getKey());
            System.out.println("---Value: " + tfsRegAGene.getValue());

            tfsRegulating = new TfsRegulating();
            System.out.println("---2");
            System.out.println("---tfsRegAGene.getKey() " + tfsRegAGene.getKey());
            System.out.println("---tfsRegAGene.getKey() == null " + tfsRegAGene.getKey() == null);
            Integer num = tfsRegAGene.getKey().intValue();
            System.out.println("---num " + num);
            tfsRegulating.setNumTf(num);
            System.out.println("---3");
            tfsRegulating.setNumTg(tfsRegAGene.getValue());
            tfsRegulating.setIdRefStatistics(refStatistics);

            try {
                trDAO.save(tfsRegulating);
            } catch (Exception E) {
                System.out.println(E);
            }
        }

        genomes = (ArrayList<Genome>) genomeDAO.listAll();

        if (genomes != null) {

            for (int i = 0; i < genomes.size(); i++) {

                refStatistics = rsDAO.findByOrganism(genomes.get(i).getOrganism().getId());
                if (refStatistics == null) {
                    refStatistics = new RefStatistics();
                    refStatistics.setType("strain");
                    if (genomes.get(i).getOrganism().getType().equals("model")) {
                        refStatistics.setDatabase("experimental");
                    } else {
                        refStatistics.setDatabase("predicted");
                    }
                    refStatistics.setGenome(genomes.get(i));
                    try {
                        rsDAO.save(refStatistics);
                        System.out.println("Salvei 3 " + refStatistics);
                    } catch (Exception E) {
                        System.out.println(E);
                    }
                }

                tfsRegAGeneAux = new TreeMap<>();
                genes = riDAO.findByOrganismAndDistinctTG(genomes.get(i).getOrganism().getId());
                numTgs = 0;
                for (int k = 0; k < genes.size(); k++) {
                    // System.out.println("gene: " + genes.get(k));
                    numTfs = riDAO.bringNumberOfRegulationsOfTg(genes.get(k).getId());
                    //System.out.println("numTFs: " + numTfs);
                    if (tfsRegAGeneAux.isEmpty()) {
                        numTgs++;
                        tfsRegAGeneAux.put(numTfs, numTgs);
                    } else {
                        if (tfsRegAGeneAux.containsKey(numTfs)) {
                            numTgs = tfsRegAGeneAux.get(numTfs) + 1;
                            tfsRegAGeneAux.put(numTfs, numTgs);
                        } else {
                            tfsRegAGeneAux.put(numTfs, 1);
                        }
                    }
                }

                if (genomes.get(i).getOrganism().getType().equals("target")) {
                    pGenes = priDAO.findByOrganismAndDistinctTG(genomes.get(i).getOrganism().getId());
                    numTgs = 0;
                    //System.out.println("pGenes.size(): " + pGenes.size());
                    for (int k = 0; k < pGenes.size(); k++) {
                        //System.out.println("pGene: " + pGenes.get(k));
                        numTfs = priDAO.bringNumberOfRegulationsOfTg(pGenes.get(k).getId());
                        //System.out.println("numTFs: " + numTfs);
                        if (tfsRegAGeneAux.containsKey(numTfs)) {
                            numTgs = tfsRegAGeneAux.get(numTfs) + 1;
                            tfsRegAGeneAux.put(numTfs, numTgs);
                        } else {
                            tfsRegAGeneAux.put(numTfs, 1);
                        }
                    }
                }

                tfsRegulatingAux = trDAO.findByRefStatistics(refStatistics.getId());
                if (tfsRegulatingAux != null) {
                    for (TfsRegulating tfsRegulating1 : tfsRegulatingAux) {
                        if (tfsRegulating1 != null) {
                            trDAO.delete(tfsRegulating1);
                        }
                    }
                }
                if (!tfsRegAGeneAux.isEmpty()) {

                    for (Map.Entry<Long, Integer> tfsRegAGene : tfsRegAGeneAux.entrySet()) {
                        System.out.println("Key: " + tfsRegAGene.getKey());
                        System.out.println("Value: " + tfsRegAGene.getValue());

                        tfsRegulating = new TfsRegulating();
                        tfsRegulating.setNumTf(tfsRegAGene.getKey() == null ? null : Math.toIntExact(tfsRegAGene.getKey()));
                        tfsRegulating.setNumTg(tfsRegAGene.getValue());
                        tfsRegulating.setIdRefStatistics(refStatistics);

                        try {
                            trDAO.save(tfsRegulating);
                        } catch (Exception E) {
                            System.out.println(E);
                        }
                    }
                }
            }
        }
    }

    public void save() {
        TfsRegulatingDAO trDAO = new TfsRegulatingDAO();
        TfsRegulating tfsRegulating = new TfsRegulating();
        tfsRegulating.setNumTf(1);
        tfsRegulating.setNumTg(15);

        RefStatisticsDAO rsDAO = new RefStatisticsDAO();
        RefStatistics rs = rsDAO.findById(3);
        tfsRegulating.setIdRefStatistics(rs);

        try {
            trDAO.save(tfsRegulating);
        } catch (Exception E) {
            System.out.println(E);
        }
    }

    public void update() {
        TfsRegulatingDAO rDAO = new TfsRegulatingDAO();
        TfsRegulating tfsRegulating = rDAO.findById(1);
        System.out.println("Before update: " + tfsRegulating);
        tfsRegulating.setNumTg(100);
        rDAO.update(tfsRegulating);
        tfsRegulating = rDAO.findById(1);
        System.out.println("After update: " + tfsRegulating);

    }

    public void delete() {
        TfsRegulatingDAO rDAO = new TfsRegulatingDAO();
        ArrayList<TfsRegulating> tfsRegulating = new ArrayList<>();

        tfsRegulating = (ArrayList<TfsRegulating>) rDAO.listAll();
        for (TfsRegulating regulatorsRegulation : tfsRegulating) {
            System.out.println("Before delete: " + regulatorsRegulation.toString());
        }

        TfsRegulating tfsAux = rDAO.findById(1);
        rDAO.delete(tfsAux);

        tfsRegulating = (ArrayList<TfsRegulating>) rDAO.listAll();
        for (TfsRegulating regulatorsRegulation : tfsRegulating) {
            System.out.println("After delete: " + regulatorsRegulation.toString());
        }
    }

}
