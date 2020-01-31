/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

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
public class StatisticsOverviewTest {

    public static void main(String[] args) {

        OrganismDAO organismDAO = new OrganismDAO();
        GeneDAO geneDAO = new GeneDAO();
        RegulatoryInteractionDAO regulatoryInteractionDAO = new RegulatoryInteractionDAO();
        BindingSiteDAO bindingSiteDAO = new BindingSiteDAO();
        StatisticsOverviewDAO soDAO = new StatisticsOverviewDAO();
        StatisticsOverview statisticsOverview = new StatisticsOverview();
        

//        //Experimental database
        Long numberOfGenes = geneDAO.bringNumberOfModelGenes("model");
        System.out.println("Total number of genes: " + numberOfGenes);
//        Long numberOfGenomes = organismDAO.bringNumberOfModelOrganisms("model");
//        //System.out.println("Total number of genomes: " + numberOfGenomes);
//        Long numberOfProteins = geneDAO.bringNumberOfModelProteins("model");
//        //System.out.println("Total number of proteins: " + numberOfProteins);
//        Long numberOfRegulatoryInteractions = regulatoryInteractionDAO.bringNumberOfRegulatoryInteractions();
//        //System.out.println("Total number of regulatory interactions: " + numberOfRegulatoryInteractions);
//        Long numberOfRegulators = regulatoryInteractionDAO.bringNumberOfRegulators();
//        //System.out.println("Total number of regulators: " + numberOfRegulators);
//        Long numberOfRegulatedGenes = regulatoryInteractionDAO.bringNumberOfRegulatedGenes();
//        //System.out.println("Total number of regulated genes: " + numberOfRegulatedGenes);
//        Long numberOfBindingSites = bindingSiteDAO.bringNumberOfExperimentalBindSites();
//        //System.out.println("Total number of binding sites: " + numberOfBindingSites);
//        Long numberOfHmmProfiles = geneDAO.bringNumberOfModelHmmProfiles("model");
//        //System.out.println("Total number of numberOfHmmProfiles: " + numberOfHmmProfiles);
////        statisticsOverview.setBindingMotifs(bindingSiteDAO.bringNumberOfExperimentalBindSites() == null ? null : Math.toIntExact(bindingSiteDAO.bringNumberOfExperimentalBindSites()));
////        statisticsOverview.setDatabase("experimental");
////        statisticsOverview.setGenes(geneDAO.bringNumberOfModelGenes("model") == null ? null : Math.toIntExact(geneDAO.bringNumberOfModelGenes("model")));
////        statisticsOverview.setNumGenomes(organismDAO.bringNumberOfModelOrganisms("model") == null ? null : Math.toIntExact(organismDAO.bringNumberOfModelOrganisms("model")));
////        statisticsOverview.setHmmProfiles(geneDAO.bringNumberOfModelHmmProfiles("model") == null ? null : Math.toIntExact(geneDAO.bringNumberOfModelHmmProfiles("model")));
////        statisticsOverview.setRegulatedGenes(regulatoryInteractionDAO.bringNumberOfRegulatedGenes() == null ? null : Math.toIntExact(regulatoryInteractionDAO.bringNumberOfRegulatedGenes()));
////        statisticsOverview.setRegulations(regulatoryInteractionDAO.bringNumberOfRegulatoryInteractions() == null ? null : Math.toIntExact(regulatoryInteractionDAO.bringNumberOfRegulatoryInteractions()));
////        statisticsOverview.setRegulators(regulatoryInteractionDAO.bringNumberOfRegulators() == null ? null : Math.toIntExact(regulatoryInteractionDAO.bringNumberOfRegulators()));
////        statisticsOverview.setProteins(geneDAO.bringNumberOfModelProteins("model") == null ? null : Math.toIntExact(geneDAO.bringNumberOfModelProteins("model")));
////        try {
////            soDAO.save(statisticsOverview);
////        } catch (Exception E) {
////            System.out.println(E);
////        }
////
        numberOfGenes = geneDAO.bringNumberOfGenes();
        System.out.println("Total number of genes: " + numberOfGenes);
//////        numberOfGenomes = organismDAO.bringNumberOfOrganisms();
//////        //System.out.println("Total number of genomes: " + numberOfGenomes);
//////        numberOfProteins = geneDAO.bringNumberOfProteins();
//////        //System.out.println("Total number of proteins: " + numberOfProteins);
//////
//////        //System.out.println("Total number of regulators: " + totalNumberOfRegulators);
//////        //System.out.println("Total number of regulated genes: " + totalNumberOfRegulatedGenes);
//////        numberOfBindingSites = bindingSiteDAO.bringNumberOfBindSites();
//////        //System.out.println("Total number of binding sites: " + numberOfBindingSites);
//////        numberOfHmmProfiles = geneDAO.bringNumberOfHmmProfiles();
//////        //System.out.println("Total number of numberOfHmmProfiles: " + numberOfHmmProfiles);
//////        //Predicted database
////        PredictedRegulatoryInteractionDAO predictedRegulatoryInteractionDAO = new PredictedRegulatoryInteractionDAO();
////        Long numberOfRegulatedGenes = regulatoryInteractionDAO.bringNumberOfRegulatedGenes() + predictedRegulatoryInteractionDAO.bringNumberOfPredictedRegulatedGenes();
////        Long numberOfRegulatoryInteractions = regulatoryInteractionDAO.bringNumberOfRegulatoryInteractions() + predictedRegulatoryInteractionDAO.bringNumberOfPredictedRegulatoryInteractions();
////        Long numberOfRegulators = regulatoryInteractionDAO.bringNumberOfRegulators() + predictedRegulatoryInteractionDAO.bringNumberOfRegulators();
////        statisticsOverview.setBindingMotifs(bindingSiteDAO.bringNumberOfBindSites() == null ? null : Math.toIntExact(bindingSiteDAO.bringNumberOfBindSites()));
////        statisticsOverview.setDatabase("predicted");
////        statisticsOverview.setGenes(geneDAO.bringNumberOfGenes() == null ? null : Math.toIntExact(geneDAO.bringNumberOfGenes()));
////        statisticsOverview.setNumGenomes(organismDAO.bringNumberOfOrganisms() == null ? null : Math.toIntExact(organismDAO.bringNumberOfOrganisms()));
////        statisticsOverview.setHmmProfiles(geneDAO.bringNumberOfHmmProfiles() == null ? null : Math.toIntExact(geneDAO.bringNumberOfHmmProfiles()));
////        statisticsOverview.setRegulatedGenes(numberOfRegulatedGenes == null ? null : Math.toIntExact(numberOfRegulatedGenes));
////        statisticsOverview.setRegulations(numberOfRegulatoryInteractions == null ? null : Math.toIntExact(numberOfRegulatoryInteractions));
////        statisticsOverview.setRegulators(numberOfRegulators == null ? null : Math.toIntExact(numberOfRegulators));
////        statisticsOverview.setProteins(geneDAO.bringNumberOfProteins() == null ? null : Math.toIntExact(geneDAO.bringNumberOfProteins()));
////        try {
////            soDAO.save(statisticsOverview);
////        } catch (Exception E) {
////            System.out.println(E);
////        }
    }

    public void save() {
        StatisticsOverviewDAO soDAO = new StatisticsOverviewDAO();
        StatisticsOverview statisticsOverview = new StatisticsOverview();

        statisticsOverview.setBindingMotifs(11);
        statisticsOverview.setDatabase("predicted");
        statisticsOverview.setGenes(100);
        statisticsOverview.setNumGenomes(2);
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
