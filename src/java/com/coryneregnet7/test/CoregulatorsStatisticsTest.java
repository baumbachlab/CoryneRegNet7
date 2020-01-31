/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.OrganismDAO;
import com.coryneregnet7.dao.PredictedRegulatoryInteractionDAO;
import com.coryneregnet7.dao.CoregulatorsStatisticsDAO;
import com.coryneregnet7.dao.RefStatisticsDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.model.CoregulatorsStatistics;
import com.coryneregnet7.model.Gene;
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
public class CoregulatorsStatisticsTest {

    public static void main(String[] args) {

        CoregulatorsStatisticsDAO csDAO = new CoregulatorsStatisticsDAO();
        CoregulatorsStatistics coregulatorsStatistics = new CoregulatorsStatistics();
        RefStatisticsDAO refStatisticsDAO = new RefStatisticsDAO();
        RefStatistics refStatistics = refStatisticsDAO.findById(133);

        ArrayList<Genome> genomes = new ArrayList<>();
        GenomeDAO genomeDAO = new GenomeDAO();
        LinkedHashMap<String, TreeMap<Long, Integer>> numOfCoregulators = new LinkedHashMap<>();
        Long numOfGeneCoregulators = new Long(0);
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        List<Gene> genes = riDAO.findByDistinctTG();
        List<Gene> pGenes = priDAO.findByDistinctTG();
        List<Gene> tfGenes = new ArrayList<>();
        List<Gene> coregulators = new ArrayList<>();
        List<Gene> coregulatorsOfTF = new ArrayList<>();
        TreeMap<Long, Integer> allCoregulatorsOfOrganism = new TreeMap<>();
        RefStatisticsDAO rsDAO = new RefStatisticsDAO();

        //System.out.println("---------------------------------------");
        coregulators = new ArrayList<>();
        allCoregulatorsOfOrganism = new TreeMap<>();
        tfGenes = riDAO.findByDistinctTF();
        for (int k = 0; k < tfGenes.size(); k++) {
            //System.out.println("tfGenes: " + tfGenes.get(k));
            genes = riDAO.findTgsOfTf(tfGenes.get(k).getId());
            coregulatorsOfTF = new ArrayList<>();
            for (int x = 0; x < genes.size(); x++) {
                //System.out.println("TG: " + genes.get(x));
                coregulators = new ArrayList<>();
                coregulators = riDAO.findTFsOfTg(genes.get(x).getId());
                //System.out.println("coregulators: " + coregulators.size());
                if (coregulatorsOfTF.isEmpty()) {
                    for (Gene coregulator : coregulators) {
                        //System.out.println("coregulator: " + coregulator);
                        coregulatorsOfTF.add(coregulator);
                    }
                } else {
                    for (Gene coregulator : coregulators) {
                        if (!coregulatorsOfTF.contains(coregulator)) {
                            //System.out.println("coregulator: " + coregulator);
                            coregulatorsOfTF.add(coregulator);
                        }
                    }
                }
            }

            //System.out.println("allCoregulators.size(): " + coregulatorsOfTF.size());
            numOfGeneCoregulators = new Long(coregulatorsOfTF.size() - 1);
            if (allCoregulatorsOfOrganism.isEmpty()) {
                allCoregulatorsOfOrganism.put(numOfGeneCoregulators, 1);
            } else {
                if (allCoregulatorsOfOrganism.containsKey(numOfGeneCoregulators)) {
                    allCoregulatorsOfOrganism.put(numOfGeneCoregulators, allCoregulatorsOfOrganism.get(numOfGeneCoregulators) + 1);
                } else {
                    allCoregulatorsOfOrganism.put(numOfGeneCoregulators, 1);
                }
            }
            //System.out.println("key: " + numOfGeneCoregulators + " value: " + allCoregulatorsOfOrganism.get(numOfGeneCoregulators));
        }

        refStatistics = refStatisticsDAO.findByTypeAndDatabase("database", "experimental");
        System.out.println("refStatistics: " + refStatistics.toString());
        for (Map.Entry<Long, Integer> coRegAGene : allCoregulatorsOfOrganism.entrySet()) {
            System.out.println("All experiemntal database:");
            System.out.println("Key: " + coRegAGene.getKey());
            System.out.println("Value: " + coRegAGene.getValue());

            coregulatorsStatistics.setNumTfs(coRegAGene.getValue());
            coregulatorsStatistics.setNumCoregulators(coRegAGene.getKey() == null ? null : Math.toIntExact(coRegAGene.getKey()));
            coregulatorsStatistics.setIdRefStatistics(refStatistics);

            //coregulatorsStatistics.setDatabase(type);
            try {
                csDAO.save(coregulatorsStatistics);
            } catch (Exception E) {
                System.out.println(E);
            }
        }

        coregulators = new ArrayList<>();
        tfGenes = new ArrayList<>();
        tfGenes = priDAO.findByDistinctTF();
        if (tfGenes == null) {
            System.out.println("No predicted TFS???");
        }
        System.out.println("Let's start");

        for (int k = 0; k < tfGenes.size(); k++) {
            System.out.println("tfGenes: " + tfGenes.get(k));
            genes = priDAO.findTgsOfTf(tfGenes.get(k).getId());
            coregulatorsOfTF = new ArrayList<>();
            for (int x = 0; x < genes.size(); x++) {
                //System.out.println("TG: " + genes.get(x));
                coregulators = new ArrayList<>();
                coregulators = priDAO.findTFsOfTg(genes.get(x).getId());
                //System.out.println("coregulators: " + coregulators.size());
                if (coregulatorsOfTF.isEmpty()) {
                    for (Gene coregulator : coregulators) {
                        coregulatorsOfTF.add(coregulator);
                    }
                } else {
                    for (Gene coregulator : coregulators) {
                        if (!coregulatorsOfTF.contains(coregulator)) {
                            coregulatorsOfTF.add(coregulator);
                        }
                    }
                }
            }
            //System.out.println("allCoregulators.size(): " + coregulatorsOfTF.size());
            numOfGeneCoregulators = new Long(coregulatorsOfTF.size() - 1);
            if (allCoregulatorsOfOrganism.containsKey(numOfGeneCoregulators)) {
                allCoregulatorsOfOrganism.put(numOfGeneCoregulators, allCoregulatorsOfOrganism.get(numOfGeneCoregulators) + 1);
            } else {
                allCoregulatorsOfOrganism.put(numOfGeneCoregulators, 1);
            }
            //System.out.println("key: " + numOfGeneCoregulators + " value: " + allCoregulatorsOfOrganism.get(numOfGeneCoregulators));
        }

        refStatistics = refStatisticsDAO.findByTypeAndDatabase("database", "predicted");
        System.out.println("refStatistics: " + refStatistics.toString());
        for (Map.Entry<Long, Integer> coRegAGene : allCoregulatorsOfOrganism.entrySet()) {
            System.out.println("All predicted database:");
            System.out.println("Key: " + coRegAGene.getKey());
            System.out.println("Value: " + coRegAGene.getValue());

            coregulatorsStatistics.setNumTfs(coRegAGene.getValue());
            coregulatorsStatistics.setNumCoregulators(coRegAGene.getKey() == null ? null : Math.toIntExact(coRegAGene.getKey()));
            coregulatorsStatistics.setIdRefStatistics(refStatistics);

            //coregulatorsStatistics.setDatabase(type);
            try {
                csDAO.save(coregulatorsStatistics);
            } catch (Exception E) {
                System.out.println(E);
            }
        }

        genomes = (ArrayList<Genome>) genomeDAO.listAll();

        if (genomes != null) {
            for (int i = 0; i < genomes.size(); i++) {
                refStatistics = refStatisticsDAO.findByOrganism(genomes.get(i).getOrganism().getId());
                System.out.println("refStatistics: " + refStatistics.toString());
                System.out.println("Organism: " + genomes.get(i).getOrganism().toString());
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
                        refStatisticsDAO.save(refStatistics);
                        System.out.println("Salvei 3 " + refStatistics);
                    } catch (Exception E) {
                        System.out.println(E);
                    }
                }
                coregulators = new ArrayList<>();
                allCoregulatorsOfOrganism = new TreeMap<>();
                tfGenes = riDAO.findByOrganismDistinctTF(genomes.get(i).getOrganism().getId());
                for (int k = 0; k < tfGenes.size(); k++) {
                    //System.out.println("tfGenes: " + tfGenes.get(k));
                    genes = riDAO.findTgsOfTf(tfGenes.get(k).getId());
                    coregulatorsOfTF = new ArrayList<>();
                    for (int x = 0; x < genes.size(); x++) {
                        //System.out.println("TG: " + genes.get(x));
                        coregulators = new ArrayList<>();
                        coregulators = riDAO.findTFsOfTg(genes.get(x).getId());
                        //System.out.println("coregulators: " + coregulators.size());
                        if (coregulatorsOfTF.isEmpty()) {
                            for (Gene coregulator : coregulators) {
                                //      System.out.println("coregulator: " + coregulator);
                                coregulatorsOfTF.add(coregulator);
                            }
                        } else {
                            for (Gene coregulator : coregulators) {
                                if (!coregulatorsOfTF.contains(coregulator)) {
                                    //        System.out.println("coregulator: " + coregulator);
                                    coregulatorsOfTF.add(coregulator);
                                }
                            }
                        }
                    }

//                    System.out.println("allCoregulators.size(): " + coregulatorsOfTF.size());
                    numOfGeneCoregulators = new Long(coregulatorsOfTF.size() - 1);
                    if (allCoregulatorsOfOrganism.isEmpty()) {
                        allCoregulatorsOfOrganism.put(numOfGeneCoregulators, 1);
                    } else {
                        if (allCoregulatorsOfOrganism.containsKey(numOfGeneCoregulators)) {
                            allCoregulatorsOfOrganism.put(numOfGeneCoregulators, allCoregulatorsOfOrganism.get(numOfGeneCoregulators) + 1);
                        } else {
                            allCoregulatorsOfOrganism.put(numOfGeneCoregulators, 1);
                        }
                    }
                    //                  System.out.println("key: " + numOfGeneCoregulators + " value: " + allCoregulatorsOfOrganism.get(numOfGeneCoregulators));
                }

                if (genomes.get(i).getOrganism().getType().equals("target")) {
                    coregulators = new ArrayList<>();
                    tfGenes = new ArrayList<>();
                    tfGenes = priDAO.findByOrganismDistinctTF(genomes.get(i).getOrganism().getId());
                    for (int k = 0; k < tfGenes.size(); k++) {
                        //                    System.out.println("tfGenes: " + tfGenes.get(k));
                        genes = priDAO.findTgsOfTf(tfGenes.get(k).getId());
                        coregulatorsOfTF = new ArrayList<>();
                        for (int x = 0; x < genes.size(); x++) {
                            //                      System.out.println("TG: " + genes.get(x));
                            coregulators = new ArrayList<>();
                            coregulators = priDAO.findTFsOfTg(genes.get(x).getId());
                            //                    System.out.println("coregulators: " + coregulators.size());
                            if (coregulatorsOfTF.isEmpty()) {
                                for (Gene coregulator : coregulators) {
                                    coregulatorsOfTF.add(coregulator);
                                }
                            } else {
                                for (Gene coregulator : coregulators) {
                                    if (!coregulatorsOfTF.contains(coregulator)) {
                                        coregulatorsOfTF.add(coregulator);
                                    }
                                }
                            }
                        }
                        //              System.out.println("allCoregulators.size(): " + coregulatorsOfTF.size());
                        numOfGeneCoregulators = new Long(coregulatorsOfTF.size() - 1);
                        if (allCoregulatorsOfOrganism.containsKey(numOfGeneCoregulators)) {
                            allCoregulatorsOfOrganism.put(numOfGeneCoregulators, allCoregulatorsOfOrganism.get(numOfGeneCoregulators) + 1);
                        } else {
                            allCoregulatorsOfOrganism.put(numOfGeneCoregulators, 1);
                        }
                        //            System.out.println("key: " + numOfGeneCoregulators + " value: " + allCoregulatorsOfOrganism.get(numOfGeneCoregulators));
                    }
                }

                if (!allCoregulatorsOfOrganism.isEmpty()) {

                    for (Map.Entry<Long, Integer> coRegAGene : allCoregulatorsOfOrganism.entrySet()) {
                        System.out.println("Key: " + coRegAGene.getKey());
                        System.out.println("Value: " + coRegAGene.getValue());

                        coregulatorsStatistics.setNumTfs(coRegAGene.getValue());
                        coregulatorsStatistics.setNumCoregulators(coRegAGene.getKey() == null ? null : Math.toIntExact(coRegAGene.getKey()));
                        coregulatorsStatistics.setIdRefStatistics(refStatistics);

                        //coregulatorsStatistics.setDatabase(type);
                        try {
                            csDAO.save(coregulatorsStatistics);
                        } catch (Exception E) {
                            System.out.println(E);
                        }
                    }
                }
            }
        }
    }

    public void save() {

        CoregulatorsStatisticsDAO csDAO = new CoregulatorsStatisticsDAO();
        CoregulatorsStatistics coregulatorsStatistics = new CoregulatorsStatistics();
        RefStatisticsDAO refStatisticsDAO = new RefStatisticsDAO();
        RefStatistics refStatistics = refStatisticsDAO.findById(133);

        coregulatorsStatistics.setNumTfs(12);
        coregulatorsStatistics.setNumCoregulators(5);
        coregulatorsStatistics.setIdRefStatistics(refStatistics);

        //coregulatorsStatistics.setDatabase(type);
        try {
            csDAO.save(coregulatorsStatistics);
        } catch (Exception E) {
            System.out.println(E);
        }
    }

    public void update() {
        CoregulatorsStatisticsDAO rDAO = new CoregulatorsStatisticsDAO();
        CoregulatorsStatistics coregulatorsStatistics = rDAO.findById(1);
        System.out.println("Before update: " + coregulatorsStatistics);
        coregulatorsStatistics.setNumTfs(22);
        rDAO.update(coregulatorsStatistics);
        coregulatorsStatistics = rDAO.findById(1);
        System.out.println("After update: " + coregulatorsStatistics);

    }

    public void delete() {
        CoregulatorsStatisticsDAO rDAO = new CoregulatorsStatisticsDAO();
        ArrayList<CoregulatorsStatistics> coregulatorsStatistics = new ArrayList<>();

        coregulatorsStatistics = (ArrayList<CoregulatorsStatistics>) rDAO.listAll();
        for (CoregulatorsStatistics regulatorsRegulation : coregulatorsStatistics) {
            System.out.println("Before update: " + regulatorsRegulation.toString());
        }

        CoregulatorsStatistics coregulatorsStatistic = rDAO.findById(1);
        rDAO.delete(coregulatorsStatistic);

        coregulatorsStatistics = (ArrayList<CoregulatorsStatistics>) rDAO.listAll();
        for (Iterator<CoregulatorsStatistics> it = coregulatorsStatistics.iterator(); it.hasNext();) {
            coregulatorsStatistic = it.next();
            System.out.println("After update: " + coregulatorsStatistic.toString());
        }
    }

}
