/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.statistics;

import com.coryneregnet7.test.*;
import com.coryneregnet7.dao.BindingSiteDAO;
import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.OrganismDAO;
import com.coryneregnet7.dao.PredictedRegulatoryInteractionDAO;
import com.coryneregnet7.dao.StatisticsOverviewDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.model.StatisticsOverview;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Organism;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author mariana
 */
public class StatisticsOverviewCacl {

    public static void main(String[] args) {
        
        StatisticsOverviewCacl soCalc = new StatisticsOverviewCacl();
        soCalc.calculate();
    }

    public void calculate() {

        OrganismDAO organismDAO = new OrganismDAO();
        GeneDAO geneDAO = new GeneDAO();
        RegulatoryInteractionDAO regulatoryInteractionDAO = new RegulatoryInteractionDAO();
        BindingSiteDAO bindingSiteDAO = new BindingSiteDAO();
        StatisticsOverviewDAO soDAO = new StatisticsOverviewDAO();
        StatisticsOverview statisticsOverview = new StatisticsOverview();
        GenomeDAO gDAO = new GenomeDAO();
        ArrayList<Genome> genomes = (ArrayList<Genome>) gDAO.listAll();

        statisticsOverview = (StatisticsOverview) soDAO.findByTypeAndDatabase("database", "experimental");
        if (statisticsOverview != null) {
            statisticsOverview.setBindingMotifs(bindingSiteDAO.bringNumberOfExperimentalBindSites() == null ? null : Math.toIntExact(bindingSiteDAO.bringNumberOfExperimentalBindSites()));
            statisticsOverview.setGenes(geneDAO.bringNumberOfModelGenes("model") == null ? null : Math.toIntExact(geneDAO.bringNumberOfModelGenes("model")));
            statisticsOverview.setNumGenomes(organismDAO.bringNumberOfModelOrganisms("model") == null ? null : Math.toIntExact(organismDAO.bringNumberOfModelOrganisms("model")));
            statisticsOverview.setHmmProfiles(geneDAO.bringNumberOfModelHmmProfiles("model") == null ? null : Math.toIntExact(geneDAO.bringNumberOfModelHmmProfiles("model")));
            statisticsOverview.setRegulatedGenes(regulatoryInteractionDAO.bringNumberOfRegulatedGenes() == null ? null : Math.toIntExact(regulatoryInteractionDAO.bringNumberOfRegulatedGenes()));
            statisticsOverview.setRegulations(regulatoryInteractionDAO.bringNumberOfRegulatoryInteractions() == null ? null : Math.toIntExact(regulatoryInteractionDAO.bringNumberOfRegulatoryInteractions()));
            statisticsOverview.setRegulators(regulatoryInteractionDAO.bringNumberOfRegulators() == null ? null : Math.toIntExact(regulatoryInteractionDAO.bringNumberOfRegulators()));
            statisticsOverview.setProteins(geneDAO.bringNumberOfModelProteins("model") == null ? null : Math.toIntExact(geneDAO.bringNumberOfModelProteins("model")));
            try {
                soDAO.update(statisticsOverview);
            } catch (Exception E) {
                System.out.println(E);
            }
        } else {
            statisticsOverview = new StatisticsOverview();
            statisticsOverview.setBindingMotifs(bindingSiteDAO.bringNumberOfExperimentalBindSites() == null ? null : Math.toIntExact(bindingSiteDAO.bringNumberOfExperimentalBindSites()));
            statisticsOverview.setDatabase("experimental");
            statisticsOverview.setGenes(geneDAO.bringNumberOfModelGenes("model") == null ? null : Math.toIntExact(geneDAO.bringNumberOfModelGenes("model")));
            statisticsOverview.setNumGenomes(organismDAO.bringNumberOfModelOrganisms("model") == null ? null : Math.toIntExact(organismDAO.bringNumberOfModelOrganisms("model")));
            statisticsOverview.setHmmProfiles(geneDAO.bringNumberOfModelHmmProfiles("model") == null ? null : Math.toIntExact(geneDAO.bringNumberOfModelHmmProfiles("model")));
            statisticsOverview.setRegulatedGenes(regulatoryInteractionDAO.bringNumberOfRegulatedGenes() == null ? null : Math.toIntExact(regulatoryInteractionDAO.bringNumberOfRegulatedGenes()));
            statisticsOverview.setRegulations(regulatoryInteractionDAO.bringNumberOfRegulatoryInteractions() == null ? null : Math.toIntExact(regulatoryInteractionDAO.bringNumberOfRegulatoryInteractions()));
            statisticsOverview.setRegulators(regulatoryInteractionDAO.bringNumberOfRegulators() == null ? null : Math.toIntExact(regulatoryInteractionDAO.bringNumberOfRegulators()));
            statisticsOverview.setProteins(geneDAO.bringNumberOfModelProteins("model") == null ? null : Math.toIntExact(geneDAO.bringNumberOfModelProteins("model")));
            statisticsOverview.setType("database");
            try {
                soDAO.save(statisticsOverview);
            } catch (Exception E) {
                System.out.println(E);
            }
        }

//        numberOfGenes = geneDAO.bringNumberOfGenes();
//        //System.out.println("Total number of genes: " + numberOfGenes);
//        numberOfGenomes = organismDAO.bringNumberOfOrganisms();
//        //System.out.println("Total number of genomes: " + numberOfGenomes);
//        numberOfProteins = geneDAO.bringNumberOfProteins();
//        //System.out.println("Total number of proteins: " + numberOfProteins);
//
//        //System.out.println("Total number of regulators: " + totalNumberOfRegulators);
//        //System.out.println("Total number of regulated genes: " + totalNumberOfRegulatedGenes);
//        numberOfBindingSites = bindingSiteDAO.bringNumberOfBindSites();
//        //System.out.println("Total number of binding sites: " + numberOfBindingSites);
//        numberOfHmmProfiles = geneDAO.bringNumberOfHmmProfiles();
//        //System.out.println("Total number of numberOfHmmProfiles: " + numberOfHmmProfiles);
//        //Predicted database
        PredictedRegulatoryInteractionDAO predictedRegulatoryInteractionDAO = new PredictedRegulatoryInteractionDAO();
        Long numberOfRegulatedGenes = regulatoryInteractionDAO.bringNumberOfRegulatedGenes() + predictedRegulatoryInteractionDAO.bringNumberOfPredictedRegulatedGenes();
        Long numberOfRegulatoryInteractions = regulatoryInteractionDAO.bringNumberOfRegulatoryInteractions() + predictedRegulatoryInteractionDAO.bringNumberOfPredictedRegulatoryInteractions();
        Long numberOfRegulators = regulatoryInteractionDAO.bringNumberOfRegulators() + predictedRegulatoryInteractionDAO.bringNumberOfRegulators();

        statisticsOverview = (StatisticsOverview) soDAO.findByTypeAndDatabase("database", "predicted");
        if (statisticsOverview != null) {
            statisticsOverview.setBindingMotifs(bindingSiteDAO.bringNumberOfBindSites() == null ? null : Math.toIntExact(bindingSiteDAO.bringNumberOfBindSites()));
            statisticsOverview.setGenes(geneDAO.bringNumberOfGenes() == null ? null : Math.toIntExact(geneDAO.bringNumberOfGenes()));
            statisticsOverview.setNumGenomes(organismDAO.bringNumberOfOrganisms() == null ? null : Math.toIntExact(organismDAO.bringNumberOfOrganisms()));
            statisticsOverview.setHmmProfiles(geneDAO.bringNumberOfHmmProfiles() == null ? null : Math.toIntExact(geneDAO.bringNumberOfHmmProfiles()));
            statisticsOverview.setRegulatedGenes(numberOfRegulatedGenes == null ? null : Math.toIntExact(numberOfRegulatedGenes));
            statisticsOverview.setRegulations(numberOfRegulatoryInteractions == null ? null : Math.toIntExact(numberOfRegulatoryInteractions));
            statisticsOverview.setRegulators(numberOfRegulators == null ? null : Math.toIntExact(numberOfRegulators));
            statisticsOverview.setProteins(geneDAO.bringNumberOfProteins() == null ? null : Math.toIntExact(geneDAO.bringNumberOfProteins()));
            try {
                soDAO.update(statisticsOverview);
            } catch (Exception E) {
                System.out.println(E);
            }
        } else {
            statisticsOverview = new StatisticsOverview();
            statisticsOverview.setDatabase("predicted");
            statisticsOverview.setBindingMotifs(bindingSiteDAO.bringNumberOfBindSites() == null ? null : Math.toIntExact(bindingSiteDAO.bringNumberOfBindSites()));
            statisticsOverview.setGenes(geneDAO.bringNumberOfGenes() == null ? null : Math.toIntExact(geneDAO.bringNumberOfGenes()));
            statisticsOverview.setNumGenomes(organismDAO.bringNumberOfOrganisms() == null ? null : Math.toIntExact(organismDAO.bringNumberOfOrganisms()));
            statisticsOverview.setHmmProfiles(geneDAO.bringNumberOfHmmProfiles() == null ? null : Math.toIntExact(geneDAO.bringNumberOfHmmProfiles()));
            statisticsOverview.setRegulatedGenes(numberOfRegulatedGenes == null ? null : Math.toIntExact(numberOfRegulatedGenes));
            statisticsOverview.setRegulations(numberOfRegulatoryInteractions == null ? null : Math.toIntExact(numberOfRegulatoryInteractions));
            statisticsOverview.setRegulators(numberOfRegulators == null ? null : Math.toIntExact(numberOfRegulators));
            statisticsOverview.setProteins(geneDAO.bringNumberOfProteins() == null ? null : Math.toIntExact(geneDAO.bringNumberOfProteins()));
            statisticsOverview.setType("database");
            try {
                soDAO.save(statisticsOverview);
            } catch (Exception E) {
                System.out.println(E);
            }
        }

        for (Genome genome : genomes) {
            statisticsOverview = new StatisticsOverview();
            statisticsOverview = soDAO.findByOrganism(genome.getOrganism().getId());

            if (statisticsOverview != null) {
                if (genome.getOrganism().getType().equals("target")) {
                    statisticsOverview.setBindingMotifs(predictedRegulatoryInteractionDAO.bringNumberOfBSs(genome.getOrganism().getId()) == null ? null : Math.toIntExact(predictedRegulatoryInteractionDAO.bringNumberOfBSs(genome.getOrganism().getId())));
                    statisticsOverview.setRegulatedGenes(predictedRegulatoryInteractionDAO.bringNumberOfRegulatedGenesOfOrganism(genome.getOrganism().getId()) == null ? null : Math.toIntExact(predictedRegulatoryInteractionDAO.bringNumberOfRegulatedGenesOfOrganism(genome.getOrganism().getId())));
                    statisticsOverview.setRegulations(predictedRegulatoryInteractionDAO.bringNumberOfPRIsOfOrganism(genome.getOrganism().getId()) == null ? null : Math.toIntExact(predictedRegulatoryInteractionDAO.bringNumberOfPRIsOfOrganism(genome.getOrganism().getId())));
                    statisticsOverview.setRegulators(predictedRegulatoryInteractionDAO.bringNumberOfRegulatorsOfOrganism(genome.getOrganism().getId()) == null ? null : Math.toIntExact(predictedRegulatoryInteractionDAO.bringNumberOfRegulatorsOfOrganism(genome.getOrganism().getId())));
                } else {
                    statisticsOverview.setBindingMotifs(bindingSiteDAO.bringNumberOfExperimentalBindSitesOfOrganism(genome.getOrganism().getId()) == null ? null : Math.toIntExact(bindingSiteDAO.bringNumberOfExperimentalBindSitesOfOrganism(genome.getOrganism().getId())));
                    statisticsOverview.setRegulatedGenes(regulatoryInteractionDAO.bringNumberOfRegulatedGenesOfOrganism(genome.getOrganism().getId()) == null ? null : Math.toIntExact(regulatoryInteractionDAO.bringNumberOfRegulatedGenesOfOrganism(genome.getOrganism().getId())));
                    statisticsOverview.setRegulations(regulatoryInteractionDAO.bringNumberOfRIsOfOrganism(genome.getOrganism().getId()) == null ? null : Math.toIntExact(regulatoryInteractionDAO.bringNumberOfRIsOfOrganism(genome.getOrganism().getId())));
                    statisticsOverview.setRegulators(regulatoryInteractionDAO.bringNumberOfRegulatorsOfOrganism(genome.getOrganism().getId()) == null ? null : Math.toIntExact(regulatoryInteractionDAO.bringNumberOfRegulatorsOfOrganism(genome.getOrganism().getId())));
                }

                statisticsOverview.setGenes(geneDAO.bringNumberOfGenesOfOrganism(genome.getOrganism().getId()) == null ? null : Math.toIntExact(geneDAO.bringNumberOfGenesOfOrganism(genome.getOrganism().getId())));
                statisticsOverview.setHmmProfiles(geneDAO.bringNumberOfHmmProfilesOfOrganism(genome.getOrganism().getId()) == null ? null : Math.toIntExact(geneDAO.bringNumberOfHmmProfilesOfOrganism(genome.getOrganism().getId())));
                statisticsOverview.setProteins(geneDAO.bringNumberOfProteinsOfOrganism(genome.getOrganism().getId()) == null ? null : Math.toIntExact(geneDAO.bringNumberOfProteinsOfOrganism(genome.getOrganism().getId())));

                try {
                    soDAO.update(statisticsOverview);
                } catch (Exception E) {
                    System.out.println(E);
                }
            } else {
                statisticsOverview = new StatisticsOverview();
                if (genome.getOrganism().getType().equals("target")) {
                    statisticsOverview.setBindingMotifs(predictedRegulatoryInteractionDAO.bringNumberOfBSs(genome.getOrganism().getId()) == null ? null : Math.toIntExact(predictedRegulatoryInteractionDAO.bringNumberOfBSs(genome.getOrganism().getId())));
                    statisticsOverview.setRegulatedGenes(predictedRegulatoryInteractionDAO.bringNumberOfRegulatedGenesOfOrganism(genome.getOrganism().getId()) == null ? null : Math.toIntExact(predictedRegulatoryInteractionDAO.bringNumberOfRegulatedGenesOfOrganism(genome.getOrganism().getId())));
                    statisticsOverview.setRegulations(predictedRegulatoryInteractionDAO.bringNumberOfPRIsOfOrganism(genome.getOrganism().getId()) == null ? null : Math.toIntExact(predictedRegulatoryInteractionDAO.bringNumberOfPRIsOfOrganism(genome.getOrganism().getId())));
                    statisticsOverview.setRegulators(predictedRegulatoryInteractionDAO.bringNumberOfRegulatorsOfOrganism(genome.getOrganism().getId()) == null ? null : Math.toIntExact(predictedRegulatoryInteractionDAO.bringNumberOfRegulatorsOfOrganism(genome.getOrganism().getId())));
                    statisticsOverview.setDatabase("predicted");
                } else {
                    statisticsOverview.setBindingMotifs(bindingSiteDAO.bringNumberOfExperimentalBindSitesOfOrganism(genome.getOrganism().getId()) == null ? null : Math.toIntExact(bindingSiteDAO.bringNumberOfExperimentalBindSitesOfOrganism(genome.getOrganism().getId())));
                    statisticsOverview.setRegulatedGenes(regulatoryInteractionDAO.bringNumberOfRegulatedGenesOfOrganism(genome.getOrganism().getId()) == null ? null : Math.toIntExact(regulatoryInteractionDAO.bringNumberOfRegulatedGenesOfOrganism(genome.getOrganism().getId())));
                    statisticsOverview.setRegulations(regulatoryInteractionDAO.bringNumberOfRIsOfOrganism(genome.getOrganism().getId()) == null ? null : Math.toIntExact(regulatoryInteractionDAO.bringNumberOfRIsOfOrganism(genome.getOrganism().getId())));
                    statisticsOverview.setRegulators(regulatoryInteractionDAO.bringNumberOfRegulatorsOfOrganism(genome.getOrganism().getId()) == null ? null : Math.toIntExact(regulatoryInteractionDAO.bringNumberOfRegulatorsOfOrganism(genome.getOrganism().getId())));
                    statisticsOverview.setDatabase("predicted");
                }

                statisticsOverview.setGenes(geneDAO.bringNumberOfGenesOfOrganism(genome.getOrganism().getId()) == null ? null : Math.toIntExact(geneDAO.bringNumberOfGenesOfOrganism(genome.getOrganism().getId())));
                statisticsOverview.setHmmProfiles(geneDAO.bringNumberOfHmmProfilesOfOrganism(genome.getOrganism().getId()) == null ? null : Math.toIntExact(geneDAO.bringNumberOfHmmProfilesOfOrganism(genome.getOrganism().getId())));
                statisticsOverview.setProteins(geneDAO.bringNumberOfProteinsOfOrganism(genome.getOrganism().getId()) == null ? null : Math.toIntExact(geneDAO.bringNumberOfProteinsOfOrganism(genome.getOrganism().getId())));
                statisticsOverview.setType("strain");
                statisticsOverview.setGenome(genome);
                try {
                    soDAO.save(statisticsOverview);
                } catch (Exception E) {
                    System.out.println(E);
                }
            }
        }

    }

    public void save() {
        StatisticsOverviewDAO soDAO = new StatisticsOverviewDAO();
        StatisticsOverview statisticsOverview = new StatisticsOverview();

        statisticsOverview.setBindingMotifs(11);
        statisticsOverview.setDatabase("predicted");
        statisticsOverview.setGenes(100);
//        statisticsOverview.setGenomes(2);
        statisticsOverview.setHmmProfiles(13);
        statisticsOverview.setRegulatedGenes(7);
        statisticsOverview.setRegulations(12);
        statisticsOverview.setRegulators(4);
        statisticsOverview.setProteins(99);
        try {
            soDAO.save(statisticsOverview);
        } catch (Exception E) {
            System.out.println(E);
        }
    }

    public void update() {
        StatisticsOverviewDAO rDAO = new StatisticsOverviewDAO();
        StatisticsOverview statisticsOverview = rDAO.findById(1);
        System.out.println("Before update: " + statisticsOverview);
        statisticsOverview.setDatabase("predicted");
        rDAO.update(statisticsOverview);
        statisticsOverview = rDAO.findById(1);
        System.out.println("After update: " + statisticsOverview);

    }

    public void delete() {
        StatisticsOverviewDAO rDAO = new StatisticsOverviewDAO();
        ArrayList<StatisticsOverview> statisticsOverview = new ArrayList<>();

        statisticsOverview = (ArrayList<StatisticsOverview>) rDAO.listAll();
        for (StatisticsOverview regulatorsRegulation : statisticsOverview) {
            System.out.println("Before update: " + regulatorsRegulation.toString());
        }

        StatisticsOverview so = rDAO.findById(1);
        rDAO.delete(so);

        statisticsOverview = (ArrayList<StatisticsOverview>) rDAO.listAll();
        for (Iterator<StatisticsOverview> it = statisticsOverview.iterator(); it.hasNext();) {
            so = it.next();
            System.out.println("After update: " + so.toString());
        }
    }

}
