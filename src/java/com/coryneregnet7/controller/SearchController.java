/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.controller;

import com.coryneregnet7.dao.BindingSiteDAO;
import com.coryneregnet7.dao.BsJobResultDAO;
import com.coryneregnet7.dao.CoregulatorsStatisticsDAO;
import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GeneInfoViewDAO;
import com.coryneregnet7.dao.GeneOperonDAO;
import com.coryneregnet7.dao.GeneOperonViewDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.HmmProfilesLengthsDAO;
import com.coryneregnet7.dao.HomologousDAO;
import com.coryneregnet7.dao.JobDAO;
import com.coryneregnet7.dao.OperonDAO;
import com.coryneregnet7.dao.OrganismDAO;
import com.coryneregnet7.dao.PredictedRegulatoryInteractionDAO;
import com.coryneregnet7.dao.PredictedRegulatoryInteractionViewDAO;
import com.coryneregnet7.dao.RegulatorsRegulationsDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.dao.RegulatoryInteractionViewDAO;
import com.coryneregnet7.dao.RnaInteractionDAO;
import com.coryneregnet7.dao.RnaRegulationViewDAO;
import com.coryneregnet7.dao.RnaTableViewDAO;
import com.coryneregnet7.dao.SmallRnaDAO;
import com.coryneregnet7.dao.StatisticsOverviewDAO;
import com.coryneregnet7.dao.TableViewDAO;
import com.coryneregnet7.dao.TfsRegulatingDAO;
import com.coryneregnet7.model.BindingSite;
import com.coryneregnet7.model.BsJobResult;
import com.coryneregnet7.model.CoregulatorsStatistics;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.GeneInfoView;
import com.coryneregnet7.model.GeneOperon;
import com.coryneregnet7.model.GeneOperonView;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.HmmProfilesLengths;
import com.coryneregnet7.model.Homologous;
import com.coryneregnet7.model.Job;
import com.coryneregnet7.model.Operon;
import com.coryneregnet7.model.Organism;
import com.coryneregnet7.model.PredictedRegulatoryInteraction;
import com.coryneregnet7.model.PredictedRegulatoryInteractionView;
import com.coryneregnet7.model.RegulatorsRegulations;
import com.coryneregnet7.model.RegulatoryInteraction;
import com.coryneregnet7.model.RegulatoryInteractionView;
import com.coryneregnet7.model.RnaInteraction;
import com.coryneregnet7.model.RnaRegulationView;
import com.coryneregnet7.model.RnaTableView;
import com.coryneregnet7.model.SmallRna;
import com.coryneregnet7.model.StatisticsOverview;
import com.coryneregnet7.model.TableView;
import com.coryneregnet7.model.TfsRegulating;
import com.coryneregnet7.processing.GetHmmLogo;
import com.coryneregnet7.processing.SearchBindingSites;
import com.coryneregnet7.processing.output.CytoscapeFile;
import com.coryneregnet7.test.GeneOperonViewTest;
import com.coryneregnet7.test.RnaTableViewTest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import org.jboss.weld.util.collections.ArraySet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author mariana & Doglas
 */
@Controller
public class SearchController {

    @RequestMapping("searchExperimentalData")
    public String searchExperimentalData(Model model, String error) {
        //  model.addAttribute("testString", "blablabla");
        //System.out.println("***********************EXPERIMENTAL DATA");
        //System.out.println("==================================================================================================");
        OrganismDAO organismDAO = new OrganismDAO();
        GenomeDAO genomeDAO = new GenomeDAO();
        //List<Organism> organisms = organismDAO.findByType("model");
        List<Object[]> genomes = genomeDAO.findByOrganismTypeHash("model");

        List<OrganismItem> items = bringItems(genomes);

        StatisticsOverviewDAO soDAO = new StatisticsOverviewDAO();
        StatisticsOverview so = soDAO.findByTypeAndDatabase("database", "experimental");
        Integer numberOfGenes = 0;
        Integer numberOfGenomes = 0;
        Integer numberOfProteins = 0;
        Integer numberOfRegulatoryInteractions = 0;
        Integer numberOfRegulators = 0;
        Integer numberOfRegulatedGenes = 0;
        Integer numberOfBindingSites = 0;
        Integer numberOfHmmProfiles = 0;
        Integer numberOfSmallRnas = 0;
        Integer numberOfGenesByRna = 0;
        Integer numberOfRegulatoryRnas = 0;
        Integer numberOfRegulationsSrna = 0;

        if (so != null) {

            numberOfGenes = so.getGenes();
            //System.out.println("Total number of genes: " + numberOfGenes);
            numberOfGenomes = so.getNumGenomes();
            //System.out.println("Total number of genomes: " + numberOfGenomes);
            numberOfProteins = so.getProteins();
            //System.out.println("Total number of proteins: " + numberOfProteins);
            numberOfRegulatoryInteractions = so.getRegulations();
            //System.out.println("Total number of regulatory interactions: " + totalNumberOfRegulatoryInteractions);
            numberOfRegulators = so.getRegulators();
            //System.out.println("Total number of regulators: " + totalNumberOfRegulators);
            numberOfRegulatedGenes = so.getRegulatedGenes();
            //System.out.println("Total numbergetSmallRnas of regulated genes: " + totalNumberOfRegulatedGenes);
            numberOfBindingSites = so.getBindingMotifs();
            //System.out.println("Total number of binding sites: " + numberOfBindingSites);
            numberOfHmmProfiles = so.getHmmProfiles();
            //System.out.println("Total number of numberOfHmmProfiles: " + numberOfHmmProfiles);
            numberOfSmallRnas = so.getSmallRnas();

            numberOfGenesByRna = so.getGenesByRna();

            numberOfRegulatoryRnas = so.getRegulatoryRnas();

            numberOfRegulationsSrna = so.getRegulationsSrna();
        }

        ///
        model.addAttribute("numberOfHmmProfiles", numberOfHmmProfiles);
        model.addAttribute("numberOfBindingSites", numberOfBindingSites);
        model.addAttribute("numberOfRegulatedGenes", numberOfRegulatedGenes);
        model.addAttribute("numberOfRegulators", numberOfRegulators);
        model.addAttribute("numberOfRegulatoryInteractions", numberOfRegulatoryInteractions);
        model.addAttribute("numberOfProteins", numberOfProteins);
        model.addAttribute("numberOfGenes", numberOfGenes);
        model.addAttribute("numberOfGenomes", numberOfGenomes);
        //   model.addAttribute("organisms", organisms);
        model.addAttribute("items", items);
        model.addAttribute("numberOfSmallRnas", numberOfSmallRnas);
        model.addAttribute("numberOfGenesByRna", numberOfGenesByRna);
        model.addAttribute("numberOfRegulatoryRnas", numberOfRegulatoryRnas);
        model.addAttribute("numberOfRegulationsSrna", numberOfRegulationsSrna);
        return "searchExperimentalData";
    }

    public List<OrganismItem> bringItems(List<Object[]> genomes) {
        List<OrganismItem> items = new ArrayList<>();
        for (Object[] genome : genomes) {
            // System.out.println("=======>  " + (Integer) genome[0] + " " + (String) genome[1] + " " + (String) genome[2]
            //         + " " + (String) genome[3] + " " + (String) genome[4]);
            items.add(new OrganismItem((Integer) genome[0], (String) genome[2] + " " + (String) genome[3] + " "
                    + (String) genome[4] + " (" + (String) genome[1] + ")"));

        }

        return items;
    }

    public Object[] bringSearchExperimentalFeatures() {
        OrganismDAO organismDAO = new OrganismDAO();
        GeneDAO geneDAO = new GeneDAO();
        RegulatoryInteractionDAO regulatoryInteractionDAO = new RegulatoryInteractionDAO();
        GenomeDAO genomeDAO = new GenomeDAO();
        BindingSiteDAO bindingSiteDAO = new BindingSiteDAO();
        List<Object[]> genomes = genomeDAO.findByOrganismTypeHash("model");

        List<OrganismItem> items = bringItems(genomes);
//        for (Organism organism : organisms) {
//            System.out.println("-------------->>" + organism.toString());
//        }
        StatisticsOverviewDAO soDAO = new StatisticsOverviewDAO();
        StatisticsOverview so = soDAO.findByTypeAndDatabase("database", "experimental");
        Integer numberOfGenes = 0;
        Integer numberOfGenomes = 0;
        Integer numberOfProteins = 0;
        Integer numberOfRegulatoryInteractions = 0;
        Integer numberOfRegulators = 0;
        Integer numberOfRegulatedGenes = 0;
        Integer numberOfBindingSites = 0;
        Integer numberOfHmmProfiles = 0;
        Integer numberOfSmallRnas = 0;
        Integer numberOfGenesByRna = 0;
        Integer numberOfRegulatoryRnas = 0;
        Integer numberOfRegulationsSrna = 0;

        if (so != null) {

            numberOfGenes = so.getGenes();
            //System.out.println("Total number of genes: " + numberOfGenes);
            numberOfGenomes = so.getNumGenomes();
            //System.out.println("Total number of genomes: " + numberOfGenomes);
            numberOfProteins = so.getProteins();
            //System.out.println("Total number of proteins: " + numberOfProteins);
            numberOfRegulatoryInteractions = so.getRegulations();
            //System.out.println("Total number of regulatory interactions: " + totalNumberOfRegulatoryInteractions);
            numberOfRegulators = so.getRegulators();
            //System.out.println("Total number of regulators: " + totalNumberOfRegulators);
            numberOfRegulatedGenes = so.getRegulatedGenes();
            //System.out.println("Total number of regulated genes: " + totalNumberOfRegulatedGenes);
            numberOfBindingSites = so.getBindingMotifs();
            //System.out.println("Total number of binding sites: " + numberOfBindingSites);
            numberOfHmmProfiles = so.getHmmProfiles();
            //System.out.println("Total number of numberOfHmmProfiles: " + numberOfHmmProfiles);

            numberOfSmallRnas = so.getSmallRnas();

            numberOfGenesByRna = so.getGenesByRna();

            numberOfRegulatoryRnas = so.getRegulatoryRnas();

            numberOfRegulationsSrna = so.getRegulationsSrna();
        }

        Object[] returnObj = {numberOfHmmProfiles, numberOfBindingSites, numberOfRegulatedGenes, numberOfRegulators,
            numberOfRegulatoryInteractions, numberOfProteins, numberOfGenes, numberOfGenomes, items, numberOfSmallRnas,
            numberOfGenesByRna, numberOfRegulatoryRnas, numberOfRegulationsSrna};

        return returnObj;
    }

    @RequestMapping("searchPredictedData")
    public String searchPredictedData(Model model) {
        //  model.addAttribute("testString", "blablabla");
        System.out.println("***********************Predicted DATA");
        System.out.println("==================================================================================================");

        OrganismDAO organismDAO = new OrganismDAO();
        List<Organism> organisms = organismDAO.listAll();
        GenomeDAO genomeDAO = new GenomeDAO();
        List<Object[]> genomes = genomeDAO.findAllHash();

        List<OrganismItem> items = bringItems(genomes);

        StatisticsOverviewDAO soDAO = new StatisticsOverviewDAO();
        StatisticsOverview so = soDAO.findByTypeAndDatabase("database", "predicted");
        Integer numberOfGenes = 0;
        Integer numberOfGenomes = 0;
        Integer numberOfProteins = 0;
        Integer numberOfRegulatoryInteractions = 0;
        Integer numberOfRegulators = 0;
        Integer numberOfRegulatedGenes = 0;
        Integer numberOfBindingSites = 0;
        Integer numberOfHmmProfiles = 0;
        Integer numberOfSmallRnas = 0;
        Integer numberOfGenesByRna = 0;
        Integer numberOfRegulatoryRnas = 0;
        Integer numberOfRegulationsSrna = 0;
        //novel atributtes: numberOfSmallRnas,
        //numberOfGenesByRna, numberOfRegulatoryRnas, numberOfRegulationsSrna

        if (so != null) {

            numberOfGenes = so.getGenes();
            //System.out.println("Total number of genes: " + numberOfGenes);
            numberOfGenomes = so.getNumGenomes();
            //System.out.println("Total number of genomes: " + numberOfGenomes);
            numberOfProteins = so.getProteins();
            //System.out.println("Total number of proteins: " + numberOfProteins);
            numberOfRegulatoryInteractions = so.getRegulations();
            //System.out.println("Total number of regulatory interactions: " + totalNumberOfRegulatoryInteractions);
            numberOfRegulators = so.getRegulators();
            //System.out.println("Total number of regulators: " + totalNumberOfRegulators);
            numberOfRegulatedGenes = so.getRegulatedGenes();
            //System.out.println("Total number of regulated genes: " + totalNumberOfRegulatedGenes);
            numberOfBindingSites = so.getBindingMotifs();
            //System.out.println("Total number of binding sites: " + numberOfBindingSites);
            numberOfHmmProfiles = so.getHmmProfiles();
            //System.out.println("Total number of numberOfHmmProfiles: " + numberOfHmmProfiles);
            numberOfSmallRnas = so.getSmallRnas();

            numberOfGenesByRna = so.getGenesByRna();

            numberOfRegulatoryRnas = so.getRegulatoryRnas();

            numberOfRegulationsSrna = so.getRegulationsSrna();

        }

        model.addAttribute("numberOfHmmProfiles", numberOfHmmProfiles);
        model.addAttribute("numberOfBindingSites", numberOfBindingSites);
        model.addAttribute("totalNumberOfRegulatedGenes", numberOfRegulatedGenes);
        model.addAttribute("totalNumberOfRegulators", numberOfRegulators);
        model.addAttribute("totalNumberOfRegulatoryInteractions", numberOfRegulatoryInteractions);
        model.addAttribute("numberOfProteins", numberOfProteins);
        model.addAttribute("numberOfGenes", numberOfGenes);
        model.addAttribute("numberOfGenomes", numberOfGenomes);
        model.addAttribute("organisms", organisms);
        model.addAttribute("items", items);
        model.addAttribute("numberOfSmallRnas", numberOfSmallRnas);
        model.addAttribute("numberOfGenesByRna", numberOfGenesByRna);
        model.addAttribute("numberOfRegulatoryRnas", numberOfRegulatoryRnas);
        model.addAttribute("numberOfRegulationsSrna", numberOfRegulationsSrna);

        return "searchPredictedData";
    }

    public Object[] bringSearchPredictedFeatures() {
        OrganismDAO organismDAO = new OrganismDAO();
        GeneDAO geneDAO = new GeneDAO();
        RegulatoryInteractionDAO regulatoryInteractionDAO = new RegulatoryInteractionDAO();
        PredictedRegulatoryInteractionDAO predictedRegulatoryInteractionDAO = new PredictedRegulatoryInteractionDAO();
        BindingSiteDAO bindingSiteDAO = new BindingSiteDAO();
        // List<Organism> organisms = organismDAO.listAll();

        GenomeDAO genomeDAO = new GenomeDAO();
        List<Object[]> genomes = genomeDAO.findAllHash();

        List<OrganismItem> items = bringItems(genomes);
//        for (Organism organism : organisms) {
//            System.out.println("-------------->>" + organism.toString());
//        }
        StatisticsOverviewDAO soDAO = new StatisticsOverviewDAO();
        StatisticsOverview so = soDAO.findByTypeAndDatabase("database", "predicted");
        Integer numberOfGenes = 0;
        Integer numberOfGenomes = 0;
        Integer numberOfProteins = 0;
        Integer numberOfRegulatoryInteractions = 0;
        Integer numberOfRegulators = 0;
        Integer numberOfRegulatedGenes = 0;
        Integer numberOfBindingSites = 0;
        Integer numberOfHmmProfiles = 0;
        Integer numberOfSmallRnas = 0;
        Integer numberOfGenesByRna = 0;
        Integer numberOfRegulatoryRnas = 0;
        Integer numberOfRegulationsSrna = 0;

        if (so != null) {

            numberOfGenes = so.getGenes();
            //System.out.println("Total number of genes: " + numberOfGenes);
            numberOfGenomes = so.getNumGenomes();
            //System.out.println("Total number of genomes: " + numberOfGenomes);
            numberOfProteins = so.getProteins();
            //System.out.println("Total number of proteins: " + numberOfProteins);
            numberOfRegulatoryInteractions = so.getRegulations();
            //System.out.println("Total number of regulatory interactions: " + totalNumberOfRegulatoryInteractions);
            numberOfRegulators = so.getRegulators();
            //System.out.println("Total number of regulators: " + totalNumberOfRegulators);
            numberOfRegulatedGenes = so.getRegulatedGenes();
            //System.out.println("Total number of regulated genes: " + totalNumberOfRegulatedGenes);
            numberOfBindingSites = so.getBindingMotifs();
            //System.out.println("Total number of binding sites: " + numberOfBindingSites);
            numberOfHmmProfiles = so.getHmmProfiles();
            //System.out.println("Total number of numberOfHmmProfiles: " + numberOfHmmProfiles);
            numberOfSmallRnas = so.getSmallRnas();

            numberOfGenesByRna = so.getGenesByRna();

            numberOfRegulatoryRnas = so.getRegulatoryRnas();

            numberOfRegulationsSrna = so.getRegulationsSrna();

        }

        Object[] returnObj = {numberOfHmmProfiles, numberOfBindingSites, numberOfRegulatedGenes, numberOfRegulators,
            numberOfRegulatoryInteractions, numberOfProteins, numberOfGenes, numberOfGenomes, items, numberOfSmallRnas,
            numberOfGenesByRna, numberOfRegulatoryRnas, numberOfRegulationsSrna};

        return returnObj;
    }

    //rnaInfo
    @RequestMapping("rnaInfo")
    public String rnaInfo(Model model, String locusTag, String type) {
        //  model.addAttribute("testString", "blablabla");
        SmallRna srna = new SmallRna();
        SmallRnaDAO srnaDAO = new SmallRnaDAO();
        srna = srnaDAO.findByLocusTag(locusTag);

        List<RnaInteraction> rnaInteractions;
        RnaInteractionDAO rnaInteractionDAO = new RnaInteractionDAO();
        rnaInteractions = rnaInteractionDAO.findBySrna(srna.getId());

        System.out.println("TYPE: " + type);
        model.addAttribute("srna", srna);
        model.addAttribute("type", type);
        model.addAttribute("rnaInteractions", rnaInteractions);
        return "rnaInfo";
    }

    @RequestMapping("dataSearch")
    public String dataSearch(Model model, Integer organism, String gene, String searchType, String geneRna) throws InterruptedException {

        System.out.println("TF-RNA: " + geneRna);
        if (searchType == null) {
            return "index";
        }

        organism = Integer.parseInt(organism.toString());
        System.out.println("Organism: " + organism);
        System.out.println("Gene: " + gene);
        System.out.println("searchType " + searchType);

        GeneDAO geneDAO = new GeneDAO();
        List<Gene> genes = new ArrayList<>();
        GeneOperonDAO goDAO = new GeneOperonDAO();
        GenomeDAO genomeDAO = new GenomeDAO();
        Genome genome = new Genome();

        if (geneRna.equals("rna")) {
            //search rnas by genome. 
            SmallRnaDAO sRnaDAO = new SmallRnaDAO();

            RnaTableViewDAO rnaTableViewDAO = new RnaTableViewDAO();
            List<RnaTableView> rnaTableView = new ArrayList<>();

            //search in all genomes
            if (organism == 0) {
                //by type
                if (gene.isEmpty()) {
                    if (searchType.equals("predicted")) {
                        rnaTableView = rnaTableViewDAO.listAll();
                    } else {
                        rnaTableView = rnaTableViewDAO.findByType("experimental");
                    }
                } else {

                    if (searchType.equals("predicted")) {
                        //search for locus_tag in general.
                        rnaTableView = rnaTableViewDAO.findByLocusTag(gene);
                    } else {
                        //search for locus_tag and type.
                        rnaTableView = rnaTableViewDAO.findByLocusTagType(gene, "experimental");

                    }
                }

                //search a specific genome. 
            } else {
                genome = genomeDAO.findByOrganism(organism);
                if (gene.isEmpty()) {
                    //search by genome and type. 

                    rnaTableView = rnaTableViewDAO.findByGenome(genome.getId());
                } else {
                    //search by gene name and genome
                    //findByGenomeLocusTag
                    rnaTableView = rnaTableViewDAO.findByGenomeLocusTag(genome.getId(), gene);

                }
            }

            model.addAttribute("type", searchType);
            model.addAttribute("rnaTableView", rnaTableView);
            return "rna";
        }

        RegulationView regView = new RegulationView();
        List<TableView> tableViews = new ArrayList<TableView>();
        TableViewDAO tableViewDAO = new TableViewDAO();

        if (searchType.equals("experimental")) {

            //if all organisms
            if (organism == 0) {
                System.out.println("TESTE1.");
                //if gene is empty
                if (gene.equals("")) {
                    //System.out.println("IF >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>vazio");
                    Object[] features = bringSearchExperimentalFeatures();
                    //searchExperimentalData(model, "Please, choose a gene!");
                    model.addAttribute("numberOfHmmProfiles", features[0]);
                    model.addAttribute("numberOfBindingSites", features[1]);
                    model.addAttribute("numberOfRegulatedGenes", features[2]);
                    model.addAttribute("numberOfRegulators", features[3]);
                    model.addAttribute("numberOfRegulatoryInteractions", features[4]);
                    model.addAttribute("numberOfProteins", features[5]);
                    model.addAttribute("numberOfGenes", features[6]);
                    model.addAttribute("numberOfGenomes", features[7]);
                    //   model.addAttribute("organisms", organisms);
                    model.addAttribute("items", (List<OrganismItem>) features[8]);

                    model.addAttribute("numberOfSmallRnas", features[9]);

                    model.addAttribute("numberOfSmallRnas", features[10]);

                    model.addAttribute("numberOfSmallRnas", features[11]);

                    model.addAttribute("numberOfSmallRnas", features[12]);

                    model.addAttribute("message", "Please, choose a gene or an organism!");
                    return "searchExperimentalData";

                    //if gene is not empty
                } else {
                    //System.out.println("IF >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>nao vazio");
                    tableViews = new ArrayList<TableView>();
                    tableViewDAO = new TableViewDAO();
                    tableViews = tableViewDAO.findByLocusTagGeneNameModel(gene);

                    //System.out.println("genes: " + genes);
                    if (tableViews.isEmpty()) {
                        //  System.out.println("Genes is null!!!!!!!!!!!!!");

                        Object[] features = bringSearchExperimentalFeatures();
                        //searchExperimentalData(model, "Gene not found! Please try again!");
                        model.addAttribute("numberOfHmmProfiles", features[0]);
                        model.addAttribute("numberOfBindingSites", features[1]);
                        model.addAttribute("numberOfRegulatedGenes", features[2]);
                        model.addAttribute("numberOfRegulators", features[3]);
                        model.addAttribute("numberOfRegulatoryInteractions", features[4]);
                        model.addAttribute("numberOfProteins", features[5]);
                        model.addAttribute("numberOfGenes", features[6]);
                        model.addAttribute("numberOfGenomes", features[7]);
                        model.addAttribute("items", (List<OrganismItem>) features[8]);
                        model.addAttribute("message", "This gene identifier was not found in the searched organism, please try locus tag.");
                        return "searchExperimentalData";
                    }
                    //geneOperons = goDAO.findByGeneNameLocusTagOfModelOrganism(gene, "model");
                }

                //if an organism is selected
            } else {

                //if gene is empty
                if (gene.equals("")) {
                    //System.out.println("ELSE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>vazio");
                    tableViews = new ArrayList<TableView>();
                    tableViewDAO = new TableViewDAO();
                    genome = genomeDAO.findByOrganism(organism);
                    tableViews = tableViewDAO.findByGenome(genome.getId());

                    //if gene is not empty
                } else {
                    //System.out.println("ELSE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> nao vazio");
                    // genes = geneDAO.findByOrganismGene(organism, gene);
                    tableViews = new ArrayList<TableView>();
                    tableViewDAO = new TableViewDAO();
                    genome = genomeDAO.findByOrganism(organism);
                    tableViews = tableViewDAO.findByLocusTagGeneNameGenome(gene, genome.getId());

                    if (tableViews.isEmpty()) {
                        ///System.out.println("Genes is null!!!!!!!!!!!!!");

                        Object[] features = bringSearchExperimentalFeatures();
                        //searchExperimentalData(model, "Gene not found! Please try again!");
                        model.addAttribute("numberOfHmmProfiles", features[0]);
                        model.addAttribute("numberOfBindingSites", features[1]);
                        model.addAttribute("numberOfRegulatedGenes", features[2]);
                        model.addAttribute("numberOfRegulators", features[3]);
                        model.addAttribute("numberOfRegulatoryInteractions", features[4]);
                        model.addAttribute("numberOfProteins", features[5]);
                        model.addAttribute("numberOfGenes", features[6]);
                        model.addAttribute("numberOfGenomes", features[7]);
                        model.addAttribute("items", (List<OrganismItem>) features[8]);
                        model.addAttribute("message", "This gene identifier was not found in the searched organism, please try locus tag.");
                        return "searchExperimentalData";
                    }
                }
            }

            //if predicted:     
        } else {

            //if all organisms
            if (organism == 0) {

                //if gene is empty
                if (gene.equals("")) {
                    //System.out.println("IF >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>vazio");
                    Object[] features = bringSearchPredictedFeatures();
                    //searchExperimentalData(model, "Please, choose a gene!");
                    model.addAttribute("numberOfHmmProfiles", features[0]);
                    model.addAttribute("numberOfBindingSites", features[1]);
                    model.addAttribute("totalNumberOfRegulatedGenes", features[2]);
                    model.addAttribute("totalNumberOfRegulators", features[3]);
                    model.addAttribute("totalNumberOfRegulatoryInteractions", features[4]);
                    model.addAttribute("numberOfProteins", features[5]);
                    model.addAttribute("numberOfGenes", features[6]);
                    model.addAttribute("numberOfGenomes", features[7]);
                    model.addAttribute("items", (List<OrganismItem>) features[8]);
                    model.addAttribute("message", "Please, choose a gene or an organism!");

                    return "searchPredictedData";

                    //if gene is not empty
                } else {
                    tableViews = new ArrayList<TableView>();
                    tableViewDAO = new TableViewDAO();
                    tableViews = tableViewDAO.findByLocusTagGeneName(gene);
                    //System.out.println("IF >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>nao vazio");
                    //genes = geneDAO.findAllByLocusTagGeneName(gene);
                    if (tableViews.isEmpty()) {
                        //System.out.println("Genes is null!!!!!!!!!!!!!");

                        Object[] features = bringSearchPredictedFeatures();
                        //searchExperimentalData(model, "Gene not found! Please try again!");
                        model.addAttribute("numberOfHmmProfiles", features[0]);
                        model.addAttribute("numberOfBindingSites", features[1]);
                        model.addAttribute("totalNumberOfRegulatedGenes", features[2]);
                        model.addAttribute("totalNumberOfRegulators", features[3]);
                        model.addAttribute("totalNumberOfRegulatoryInteractions", features[4]);
                        model.addAttribute("numberOfProteins", features[5]);
                        model.addAttribute("numberOfGenes", features[6]);
                        model.addAttribute("numberOfGenomes", features[7]);
                        model.addAttribute("items", (List<OrganismItem>) features[8]);
                        model.addAttribute("message", "This gene identifier was not found in the searched organism, please try locus tag.");

                        return "searchPredictedData";
                    }
                    //geneOperons = goDAO.findByGeneNameLocusTag(gene);
                }

                //if a organism is selected
            } else {

                //if gene is empty
                if (gene.equals("")) {
                    //System.out.println("ELSE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>vazio");
                    tableViews = new ArrayList<TableView>();
                    tableViewDAO = new TableViewDAO();
                    genome = genomeDAO.findByOrganism(organism);
                    tableViews = tableViewDAO.findByGenome(genome.getId());

                    //if gene is not empty
                } else {
                    tableViews = new ArrayList<TableView>();
                    tableViewDAO = new TableViewDAO();
                    genome = genomeDAO.findByOrganism(organism);
                    System.out.println("genome " + genome);
                    System.out.println("genome " + genome.getClass());
                    System.out.println("gene " + gene);
                    System.out.println("gene " + gene.getClass());
                    tableViews = tableViewDAO.findByLocusTagGeneNameGenome(gene, genome.getId());
                    //System.out.println("ELSE >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> nao vazio");
                    //genes = geneDAO.findByOrganismGene(organism, gene);
                    if (tableViews.isEmpty()) {
                        //System.out.println("Genes is null!!!!!!!!!!!!!");

                        Object[] features = bringSearchPredictedFeatures();
                        searchExperimentalData(model, "Gene not found! Please try again!");
                        model.addAttribute("numberOfHmmProfiles", features[0]);
                        model.addAttribute("numberOfBindingSites", features[1]);
                        model.addAttribute("totalNumberOfRegulatedGenes", features[2]);
                        model.addAttribute("totalNumberOfRegulators", features[3]);
                        model.addAttribute("totalNumberOfRegulatoryInteractions", features[4]);
                        model.addAttribute("numberOfProteins", features[5]);
                        model.addAttribute("numberOfGenes", features[6]);
                        model.addAttribute("numberOfGenomes", features[7]);
                        model.addAttribute("items", (List<OrganismItem>) features[8]);
                        model.addAttribute("message", "This gene identifier was not found in the searched organism, please try locus tag.");

                        return "searchPredictedData";
                    }
                    //geneOperons = goDAO.findByOrganismGene(organism, gene);
                }
            }
        }

        System.out.println("tableViews " + tableViews.size());

//        for (TableView tableView : tableViews) {
//            System.out.println(tableView.toString());
//        }
        String entriesFound = "";
        int genesFound = tableViews.size();
        System.out.println("GENES FOUND " + genesFound);

        if (genesFound == 0) {
            entriesFound = "No entries ";
            //System.out.println("================ " + genesFound);
        } else if (genesFound == 1) {
            entriesFound = genesFound + " entry";
        } else {
            entriesFound = genesFound + " entries";
        }
        System.out.println("entriesFound FOUND " + entriesFound);

        //System.out.println("GOooooooooooooooooooooooooooooooooooo screen!!!!!!!!!!!");
        model.addAttribute("genesFound", genesFound);
        model.addAttribute("entriesFound", entriesFound);
//        model.addAttribute("regulatedBy", regulatedBy);
//        model.addAttribute("regulates", regulates);
//        model.addAttribute("operons", operons);
//        model.addAttribute("genes", genes);
        model.addAttribute("tableViews", tableViews);

        if (searchType.equals("experimental")) {
            return "experimentalData";
        } else {
            return "predictedData";
        }
    }

    @RequestMapping("geneInfo")
    public String geneInfo(Model model, String locusTag, String type) throws IOException, ParseException, InterruptedException {

        if (locusTag == null) {
            return "index";
        }
//        System.out.println("==================================================================================================");
//        System.out.println("==================================================================================================");

        //System.out.println("Gene info: " + type);
        //System.out.println(locusTag.toString());
        GeneDAO geneDAO = new GeneDAO();
        Gene gene = geneDAO.findByLocusTag(locusTag);

        if (gene != null) {

            RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
            ArrayList<RegulationView> regulates = new ArrayList<>();
            ArrayList<RegulationView> regulatedBy = new ArrayList<>();
            RegulatoryInteraction riAux = new RegulatoryInteraction();
            List<RegulatoryInteraction> risAux = new ArrayList<>();
            List<RegulatoryInteraction> ris = new ArrayList<>();
            List<BindingSite> bss = new ArrayList<>();
            BindingSiteDAO bindingSiteDAO = new BindingSiteDAO();
            GeneOperonDAO geneOperonDAO = new GeneOperonDAO();
            GeneOperon geneOperon = new GeneOperon();
            Hashtable<Gene, String> operonRegulatedGenes = new Hashtable<Gene, String>();
            OperonDAO opDAO = new OperonDAO();
            String autoregulator = "";
            OrganismDAO organismDAO = new OrganismDAO();
            List<Organism> organisms = new ArrayList<Organism>();
            List<PredictedRegulatoryInteraction> pris = new ArrayList<PredictedRegulatoryInteraction>();
            RegulationView regView = new RegulationView();
            ArrayList<RegulationView> regulationsView = new ArrayList<RegulationView>();
            PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
            String hmmLogo = "";
            String hmmProfile = "";
            String directoryPath = "";
            GetHmmLogo getHmmLogo = new GetHmmLogo();
            String[] stoDirectory;
            Operon operon = new Operon();
            HomologousDAO homDAO = new HomologousDAO();
            ArrayList<Homologous> homologous = new ArrayList<>();
            ArrayList<GeneDbType> orthologous = new ArrayList<>();
            ArrayList<GeneDbType> paralogous = new ArrayList<>();
            GeneDbType geneDbType = new GeneDbType();

            homologous = (ArrayList<Homologous>) homDAO.findByGene(gene.getId());
            for (int i = 0; i < homologous.size(); i++) {
                //System.out.println("homologous: " + homologous.get(i).getGeneOne().getLocusTag());
                //System.out.println("homologous: " + homologous.get(i).getGeneTwo().getLocusTag());
                if (homologous.get(i).getGeneOne().getId().equals(gene.getId())) {
                    //System.out.println("homologous.get(i).getGeneTwo().getGenome().getOrganism().getId(): " + homologous.get(i).getGeneTwo().getGenome().getOrganism().getId());
                    //System.out.println("gene.getGenome().getOrganism().getId(): " + gene.getGenome().getOrganism().getId());
                    if (homologous.get(i).getGeneTwo().getGenome().getOrganism().getId().equals(gene.getGenome().getOrganism().getId())) {
                        geneDbType = new GeneDbType();
                        geneDbType.setGene(homologous.get(i).getGeneTwo());
                        if (homologous.get(i).getGeneTwo().getGenome().getOrganism().getType().equals("model")) {
                            geneDbType.setDbType("experimental");
                        } else {
                            geneDbType.setDbType("predicted");
                        }
                        paralogous.add(geneDbType);
                    } else {
                        geneDbType = new GeneDbType();
                        geneDbType.setGene(homologous.get(i).getGeneTwo());
                        if (homologous.get(i).getGeneTwo().getGenome().getOrganism().getType().equals("model")) {
                            geneDbType.setDbType("experimental");
                        } else {
                            geneDbType.setDbType("predicted");
                        }
                        orthologous.add(geneDbType);
                    }
                } else {
                    //System.out.println("homologous.get(i).getGeneTwo().getGenome().getOrganism().getId(): " + homologous.get(i).getGeneTwo().getGenome().getOrganism().getId());
                    //System.out.println("gene.getGenome().getOrganism().getId(): " + gene.getGenome().getOrganism().getId());
                    if (homologous.get(i).getGeneOne().getGenome().getOrganism().getId().equals(gene.getGenome().getOrganism().getId())) {
                        geneDbType = new GeneDbType();
                        geneDbType.setGene(homologous.get(i).getGeneOne());
                        if (homologous.get(i).getGeneOne().getGenome().getOrganism().getType().equals("model")) {
                            geneDbType.setDbType("experimental");
                        } else {
                            geneDbType.setDbType("predicted");
                        }
                        paralogous.add(geneDbType);
                    } else {
                        geneDbType = new GeneDbType();
                        geneDbType.setGene(homologous.get(i).getGeneOne());
                        if (homologous.get(i).getGeneOne().getGenome().getOrganism().getType().equals("model")) {
                            geneDbType.setDbType("experimental");
                        } else {
                            geneDbType.setDbType("predicted");
                        }
                        orthologous.add(geneDbType);
                    }
                }
            }

            geneOperon = geneOperonDAO.findByGene(gene.getId());
            if (geneOperon != null) {
                operon = opDAO.findById(geneOperon.getGeneOperonPK().getOperon());
            } else {
                operon = null;
            }

            if (type.equals("experimental")) {
                //System.out.println("EXPERIMENTALLLLLLL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                organisms = organismDAO.findByType("model");
            } else {
                //System.out.println("Predict!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                organisms = organismDAO.listAll();
            }

            //recover regulated by:
            ris = riDAO.findByTG(gene.getId());
            //System.out.println("gene.getId() " + gene.getId());
            //System.out.println("gene.getLocus() " + gene.getLocusTag());
            //System.out.println("ris.size(): " + ris.size());
            for (RegulatoryInteraction ri : ris) {
                //System.out.println("ri: " + ri.getCorrespondentTranscriptionFactor());
                //System.out.println("###############################################model");
                regView = new RegulationView();
                regView.setId(ri.getId());
                regView.setTargetGene(ri.getCorrespondentTargetGene());
                regView.setTranscriptionFactor(ri.getCorrespondentTranscriptionFactor());
                if (!ri.getRole().equals("")) {
                    //System.out.println("ri.getRole(): " + ri.getRole());
                    regView.setRole(ri.getRole().charAt(0) + "");
                } else {
                    regView.setRole("-");
                }
                regView.setEvidence(ri.getEvidence());
                if (ri.getPmid().equals("")) {
                    regView.setPmid("-");
                } else {
                    regView.setPmid(ri.getPmid());
                }

                // System.out.println("--------------------- Model regulatedby: " + ri.toString());
                bss = bindingSiteDAO.findByRegularotyInteraction(ri.getId());
                String bssRI = "-";
                for (int i = 0; i < bss.size(); i++) {
                    if (i == 0) {
                        bssRI = bss.get(i).getSequence();
                    } else {
                        bssRI += ", " + bss.get(i).getSequence();
                    }
                }
                regView.setBindingSite(bssRI);
                regulationsView.add(regView);
            }

            String evidence = "";
            if (type.equals("predicted")) {
                //System.out.println("###############################################predicted");
                pris = priDAO.findByTG(gene.getId());
                for (PredictedRegulatoryInteraction pri : pris) {
                    //System.out.println("pri " + pri.toString());
                    regView = new RegulationView();
                    regView.setId(pri.getId());
                    regView.setTargetGene(pri.getHomologousTargetGene());
                    regView.setTranscriptionFactor(pri.getHomologousTranscriptionFactor());
                    if (!pri.getRegulatoryInteraction().getRole().equals("")) {
                        //System.out.println("ri.getRole(): " + pri.getRegulatoryInteraction().getRole());
                        regView.setRole(pri.getRegulatoryInteraction().getRole().charAt(0) + "");
                    } else {
                        regView.setRole("-");
                    }
                    geneOperon = geneOperonDAO.findByGene(pri.getRegulatoryInteraction().getCorrespondentTargetGene().getId());
                    if (geneOperon != null) {
                        //System.out.println("*****************Operon: " + geneOperon.toString());
                        evidence = "predicted - operon ref: " + pri.getRegulatoryInteraction().getCorrespondentTranscriptionFactor().getLocusTag() + " --> " + pri.getRegulatoryInteraction().getCorrespondentTargetGene().getLocusTag();
                    } else {
                        // System.out.println("****************Não operon");
                        evidence = "predicted - ref: " + pri.getRegulatoryInteraction().getCorrespondentTranscriptionFactor().getLocusTag() + " --> " + pri.getRegulatoryInteraction().getCorrespondentTargetGene().getLocusTag();
                    }
                    regView.setEvidence(evidence);
                    if (pri.getPredictedBindingSite() != null && !pri.getPredictedBindingSite().getSequence().isEmpty()) {
                        regView.setBindingSite(pri.getPredictedBindingSite().getSequence());
                    } else {
                        regView.setBindingSite("-");
                    }
                    regView.setpValue(pri.getHtgPvalue());
                    regView.setModelOrganism(pri.getRegulatoryInteraction().getCorrespondentTargetGene().getGenome().getOrganism());
                    regulationsView.add(regView);
                    //System.out.println("--------------------- predicted regulatedby: " + pri.toString());
                }
            }

            regulatedBy = regulationsView;

            RnaRegView reg = new RnaRegView();
            //create object similar to regulations view with sRNA regulations
            ArrayList<RnaRegView> rnaRegViews = new ArrayList<>();
            RnaInteractionDAO rDAO = new RnaInteractionDAO();
            List<RnaInteraction> rnaInteraction = rDAO.findByMrna(gene.getId());
            for (RnaInteraction rnaInteraction1 : rnaInteraction) {

                //Integer id,  Gene targetGene,            SmallRna smallRna, String role, String evidence, BigDecimal copraPvalue)
                reg = new RnaRegView(Integer.SIZE, rnaInteraction1.getMrna(), rnaInteraction1.getSrna(), "", "predicted", rnaInteraction1.getCopraPvalue());
                rnaRegViews.add(reg);
                System.out.println(rnaInteraction1.getSrna());
            }

//        for (int x = 0; x < regulatedBy.size(); x++) {
//            System.out.println("11111111111111 ---- Regulated by: " + regulatedBy.get(x).toString());
//        }
            //retrive the TGs regulated by a specific TF
            regulationsView = new ArrayList<RegulationView>();
            ris = riDAO.findRegulates(gene.getId());
            Long coregulatorsAux;
            String opName = "";
            if (!ris.isEmpty()) {
                autoregulator = "Unknown";
            }
            coregulatorsAux = new Long(0);
            for (RegulatoryInteraction ri : ris) {
                //System.out.println("###############################################model");
                coregulatorsAux = riDAO.bringNumberOfCoregulators(ri.getCorrespondentTargetGene().getLocusTag()) - 1;
                geneOperon = geneOperonDAO.findByGene(ri.getCorrespondentTargetGene().getId());
                opName = "-";
                if (geneOperon != null) {
                    int op = geneOperon.getGeneOperonPK().getOperon();
                    opName = opDAO.findById(op).getName();
                }
                regView = new RegulationView();
                regView.setId(ri.getId());
                regView.setTargetGene(ri.getCorrespondentTargetGene());
                regView.setTranscriptionFactor(ri.getCorrespondentTranscriptionFactor());
                if (!ri.getRole().equals("")) {
                    regView.setRole(ri.getRole().charAt(0) + "");
                } else {
                    regView.setRole("-");
                }
                regView.setEvidence(ri.getEvidence());
                if (ri.getPmid().equals("")) {
                    regView.setPmid("-");
                } else {
                    regView.setPmid(ri.getPmid());
                }
                regView.setCoregulators(coregulatorsAux);
                regView.setOperon(opName);
                //System.out.println("--------------------- Model regulatedby: " + ri.toString());
                bss = bindingSiteDAO.findByRegularotyInteraction(ri.getId());
                String bssRI = "-";
                for (int i = 0; i < bss.size(); i++) {
                    if (i == 0) {
                        bssRI = bss.get(i).getSequence();
                    } else {
                        bssRI += ", " + bss.get(i).getSequence();
                    }
                }
                regView.setBindingSite(bssRI);
                regulationsView.add(regView);
                //System.out.println("--------------------- Model regulates: " + ri.toString());
                if (ri.getCorrespondentTargetGene().equals(ri.getCorrespondentTranscriptionFactor())) {
                    autoregulator = "Yes";
                }
            }

            if (type.equals("predicted")) {
                //System.out.println("###############################################model");
                pris = priDAO.findRegulates(gene.getId());
                evidence = "";
                for (PredictedRegulatoryInteraction pri : pris) {
                    coregulatorsAux += priDAO.bringNumberOfCoregulators(pri.getHomologousTargetGene().getLocusTag()) - 1;
                    //System.out.println("Number of coregulators: " + coregulatorsAux);
                    geneOperon = new GeneOperon();
                    geneOperon = geneOperonDAO.findByGene(pri.getHomologousTargetGene().getId());
                    opName = "-";
                    if (geneOperon != null) {
                        int op = geneOperon.getGeneOperonPK().getOperon();
                        opName = opDAO.findById(op).getName();
                        //System.out.println("*********************Predicted operon: " + geneOperon.toString());
                    }
                    regView = new RegulationView();
                    regView.setId(pri.getId());
                    regView.setTargetGene(pri.getHomologousTargetGene());
                    regView.setTranscriptionFactor(pri.getHomologousTranscriptionFactor());
                    if (pri.getRegulatoryInteraction().getRole().equals("")) {
                        regView.setRole("-");
                    } else {
                        regView.setRole(pri.getRegulatoryInteraction().getRole().charAt(0) + "");
                    }
                    geneOperon = geneOperonDAO.findByGene(pri.getRegulatoryInteraction().getCorrespondentTargetGene().getId());
                    if (geneOperon != null) {
                        //System.out.println("*****************Operon: " + geneOperon.toString());
                        evidence = "predicted - operon ref: " + pri.getRegulatoryInteraction().getCorrespondentTranscriptionFactor().getLocusTag() + " --> " + pri.getRegulatoryInteraction().getCorrespondentTargetGene().getLocusTag();
                    } else {
                        evidence = "predicted - ref: " + pri.getRegulatoryInteraction().getCorrespondentTranscriptionFactor().getLocusTag() + " --> " + pri.getRegulatoryInteraction().getCorrespondentTargetGene().getLocusTag();
                    }
                    regView.setEvidence(evidence);
                    if (pri.getPredictedBindingSite() != null && !pri.getPredictedBindingSite().getSequence().isEmpty()) {
                        regView.setBindingSite(pri.getPredictedBindingSite().getSequence());
                    } else {
                        regView.setBindingSite("-");
                    }
                    regView.setCoregulators(coregulatorsAux);
                    regView.setOperon(opName);
                    regView.setModelOrganism(pri.getRegulatoryInteraction().getCorrespondentTargetGene().getGenome().getOrganism());
                    regView.setpValue(pri.getHtgPvalue());
                    regulationsView.add(regView);
                    //System.out.println("--------------------- Predicted regulates: " + pri.toString());
//                bindingSitesRegulates.add(pri.getPredictedBindingSite());
                }

            }

            regulates = regulationsView;

            if (gene != null && gene.getHmmLogo() != null && !gene.getHmmLogo().equals("")) {
                hmmLogo = gene.getHmmLogo();
                System.out.println("****************************************");
                System.out.println("gene.getHmmLogo() => " + gene.getHmmLogo());
                System.out.println("****************************************");

                File tempFile = new File(gene.getHmmLogo());
                boolean exists = tempFile.exists();
                System.out.println("gene.getHmmLogo() => " + exists);
                System.out.println("****************************************");

                //System.out.println("HMM Logo path: " + hmmLogo);
            } else if (gene != null && gene.getHmmProfile() != null && !gene.getHmmProfile().equals("")) {
                hmmProfile = gene.getHmmProfile();
                //System.out.println("-------------------------" + hmmProfile);
                hmmProfile = hmmProfile.replace(".hmm", ".sto");
                //System.out.println("------------");
                //System.out.println("hmmProfile => " + hmmProfile);
                String startPath = System.getProperty("user.dir");
                stoDirectory = startPath.split("/");
                //directoryPath = ("/" + stoDirectory[stoDirectory.length - 7] + "/" + stoDirectory[stoDirectory.length - 6] + "/database/CoryneRegNet7/web/images");
                directoryPath = ("/home/ubuntu/database/CoryneRegNet7/web/images");
                //directoryPath = "/home/doglas/Dropbox/Doutorado/CoryneRegNet7/bkps/pos-submissao/CoryneRegNet7-git-version/web/images";
                //System.out.println("hmmProfile => " + hmmProfile);
                //System.out.println("directoryPath => " + directoryPath);
                hmmLogo = getHmmLogo.getLogo(hmmProfile, directoryPath);

                //System.out.println("New HMM Logo path: " + hmmLogo);
                if (!hmmLogo.startsWith("Skyalign")) {
                    gene.setHmmLogo(hmmLogo);
                    geneDAO.update(gene);
                } else {
                    hmmLogo = "Skyalign is not responding in this moment, please try again in a few minutes.";
                }
            }

            System.out.println("hmmLogo: " + hmmLogo);

            //System.out.println("Gooooooooooooooooooooooooooooo View!!!!!!!!");
            String[] hmmLogoAux = hmmLogo.split("/");
            String hmmLogoName = hmmLogoAux[hmmLogoAux.length - 1];
            //System.out.println("hmmLogoName: " + hmmLogoName);
            model.addAttribute("organisms", organisms);
            model.addAttribute("hmmLogoName", hmmLogoName);
            model.addAttribute("autoregulator", autoregulator);
            model.addAttribute("operonRegulatedGenes", operonRegulatedGenes);
            model.addAttribute("regulatedBy", regulatedBy);
            model.addAttribute("regulates", regulates);
            model.addAttribute("type", type);
            model.addAttribute("gene", gene);
            model.addAttribute("operon", operon);
            model.addAttribute("orthologous", orthologous);
            model.addAttribute("paralogous", paralogous);
            model.addAttribute("rnaRegViews", rnaRegViews);
            return "geneInfo";
        } else {
            System.out.println("É UM RNA! ");
            return "geneInfo";
        }

    }

    @RequestMapping("operonInfo")
    public String operonInfo(Model model, String name, String type) {
////        System.out.println("==================================================================================================");
////        System.out.println("==================================================================================================");
////
////        System.out.println(operon.toString());
        OperonDAO opDAO = new OperonDAO();
        Operon operon = new Operon();
        GeneOperonDAO goDAO = new GeneOperonDAO();
        GeneDAO geneDAO = new GeneDAO();
        ArrayList<Gene> genes = new ArrayList<>();
        Gene firstGene = new Gene();

        operon = opDAO.findByName(name);
        //System.out.println("operonName: " + name);
        //System.out.println("operon: " + operon);
        List<GeneOperon> genesOperon = goDAO.findByOperon(operon.getId());

        for (GeneOperon go : genesOperon) {
            genes.add(geneDAO.findById(go.getGeneOperonPK().getGene()));
            if (genes.size() == 1) {
                firstGene = genes.get(0);
            }
            //System.out.println(go.getPos());
        }

        //System.out.println("type: " + type);
        model.addAttribute("type", type);
        model.addAttribute("firstGene", firstGene);
        model.addAttribute("genes", genes);
        model.addAttribute("genesOperon", genesOperon);
        model.addAttribute("operon", operon);
        return "operonInfo";
    }

    @RequestMapping("organismInfo")
    public String organismInfo(Model model, Integer id, String type) {
        System.out.println("==================================================================================================");
        System.out.println("==================================================================================================");

        System.out.println("ID: " + id);
        System.out.println("type: " + type);
        //System.out.println(id);
        OrganismDAO organismDAO = new OrganismDAO();
        Organism organism = organismDAO.findById(id);
        System.out.println(organism.toString());
        GenomeDAO genomeDAO = new GenomeDAO();
        Genome genome = genomeDAO.findByOrganism(organism.getId());
        //System.out.println(genome.toString());
        GeneDAO geneDAO = new GeneDAO();
        RegulatoryInteractionDAO regulatoryInteractionDAO = new RegulatoryInteractionDAO();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        BindingSiteDAO bindingSiteDAO = new BindingSiteDAO();

        StatisticsOverviewDAO soDAO = new StatisticsOverviewDAO();
        StatisticsOverview so = soDAO.findByOrganism(organism.getId());
        Integer numberOfGenes = 0;
        Integer numberOfGenomes = 0;
        Integer numberOfProteins = 0;
        Integer numberOfRegulatoryInteractions = 0;
        Integer numberOfRegulators = 0;
        Integer numberOfRegulatedGenes = 0;
        Integer numberOfBindingSites = 0;
        Integer numberOfHmmProfiles = 0;

        if (so != null) {

            numberOfGenes = so.getGenes();
            //System.out.println("Total number of genes: " + numberOfGenes);
            numberOfGenomes = so.getNumGenomes();
            //System.out.println("Total number of genomes: " + numberOfGenomes);
            numberOfProteins = so.getProteins();
            //System.out.println("Total number of proteins: " + numberOfProteins);
            numberOfRegulatoryInteractions = so.getRegulations();
            //System.out.println("Total number of regulatory interactions: " + totalNumberOfRegulatoryInteractions);
            numberOfRegulators = so.getRegulators();
            //System.out.println("Total number of regulators: " + totalNumberOfRegulators);
            numberOfRegulatedGenes = so.getRegulatedGenes();
            //System.out.println("Total number of regulated genes: " + totalNumberOfRegulatedGenes);
            numberOfBindingSites = so.getBindingMotifs();
            //System.out.println("Total number of binding sites: " + numberOfBindingSites);
            numberOfHmmProfiles = so.getHmmProfiles();
            //System.out.println("Total number of numberOfHmmProfiles: " + numberOfHmmProfiles);
        }
//        Long numberOfHmmProfiles = geneDAO.bringNumberOfHmmProfilesOfOrganism(id);
        //System.out.println("Total number of numberOfHmmProfiles: " + numberOfHmmProfiles);
        List<Gene> genes = geneDAO.listAllGenesOfOrganism(id);
        List<GeneOperon> geneOperons = new ArrayList<>();
        GeneOperonDAO geneOpDAO = new GeneOperonDAO();
        Hashtable<Integer, String> operons = new Hashtable<Integer, String>();
        OperonDAO opDAO = new OperonDAO();

        geneOperons = geneOpDAO.findByOrganism(id);
        for (GeneOperon geneOperon : geneOperons) {
            if (geneOperon != null) {
                //System.out.println("--------------- GeneOperon is not null");
                //System.out.println("Geneoperon: " + geneOperon.toString());

                int op = geneOperon.getGeneOperonPK().getOperon();
                String opName = opDAO.findById(op).getName();
                //System.out.println("Operon: " + opName);
                operons.put(geneOperon.getGeneOperonPK().getGene(), opName);
            } else {
                // System.out.println("GENE-OPERON É NULO");
            }
        }

        //System.out.println("Gooooooooooooooo View!!!!!!!!!!!!!!!!!!!!!!!");
        model.addAttribute("operons", operons);
        model.addAttribute("genes", genes);
        model.addAttribute("numberOfHmmProfiles", numberOfHmmProfiles);
        model.addAttribute("numberOfBindingSites", numberOfBindingSites);
        model.addAttribute("numberOfRegulatedGenes", numberOfRegulatedGenes);
        model.addAttribute("numberOfRegulators", numberOfRegulators);
        model.addAttribute("numberOfRegulatoryInteractions", numberOfRegulatoryInteractions);
        model.addAttribute("numberOfProteins", numberOfProteins);
        model.addAttribute("numberOfGenes", numberOfGenes);
        model.addAttribute("genome", genome);
        model.addAttribute("type", type);
        model.addAttribute("organism", organism);
        return "organismInfo";
    }

    @RequestMapping("networkDinamicVisualization")
    public String networkDinamicVisualization(Model model, Integer organism, String searchType, String gene, String goBackTo, String interestGenes) throws InterruptedException {
        System.out.println("--------------- Organism: " + organism);
        //System.out.println("--------------- SearchType: " + searchType);
        // System.out.println("Gooooooooooooooo networkVisualization!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("interestGenes: " + interestGenes);
        System.out.println("gene: " + gene);

        if (gene.isEmpty()) {

            model.addAttribute("organism", organism);
            model.addAttribute("searchType", searchType);
            model.addAttribute("gene", gene);
            model.addAttribute("goBackTo", goBackTo);
            return "whichNetwork";
        }
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        ArrayList<RegulationView> regulationsView = new ArrayList<>();
        ArrayList<RegulatoryInteraction> ris = new ArrayList<>();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        ArrayList<PredictedRegulatoryInteraction> pris = new ArrayList<>();
        RegulationView regView = new RegulationView();
        BindingSiteDAO bindingSiteDAO = new BindingSiteDAO();
        ArrayList<BindingSite> bss = new ArrayList<>();
        GeneInfo geneInfo = new GeneInfo();
        TreeMap<String, GeneInfo> genesInfo = new TreeMap<>();
        OperonDAO opDAO = new OperonDAO();
        GeneOperonDAO geneOpDAO = new GeneOperonDAO();
        TreeMap<String, ArrayList<String>> operons = new TreeMap<>();
        //TreeMap<String, ArrayList<String>> geneOpInfo = new TreeMap<>();
        Operon operon = new Operon();
        GeneOperon geneOperon = new GeneOperon();
        ArrayList<String> opInfo = new ArrayList<>();
        ArrayList<GeneOperon> genesOperon = new ArrayList<>();
        ArrayList<String> genesOfOp = new ArrayList<>();
        OrganismDAO organismDAO = new OrganismDAO();
        Organism o = organismDAO.findById(organism);
        ArrayList<String> sigmas = new ArrayList<>();
        BigDecimal newBI = BigDecimal.ONE;
        newBI = newBI.negate();
        CytoscapeFile cytoscapeFile = new CytoscapeFile();
        String cytoscapeFileName = "";
        String[] hubsConnected;
        Long regulatorsDensityTf;
        Long regulatorsDensityTg;
        Gene g = new Gene();
        GeneDAO geneDAO = new GeneDAO();
        //ArrayList<Gene> nodes = new ArrayList<>();
        String role = new String();
        Gene targetGene;
        ArrayList<Gene> targetGenes = new ArrayList<>();

        if (gene == null || gene.isEmpty()) {
            //System.out.println("It's empty");
            ris = riDAO.findByOrganism(organism);
            pris = priDAO.findByOrganism(organism);
        } else {
            g = geneDAO.findGeneByLocusTagOrGeneName(organism, gene);
            //System.out.println("----------------------------------g: " + g);
            if (g == null) {
                //System.out.println("Genes is null!!!!!!!!!!!!!");

                if (searchType.equals("experimental")) {
                    Object[] features = bringSearchExperimentalFeatures();
                    searchExperimentalData(model, "Gene not found! Please try again!");
                    model.addAttribute("numberOfHmmProfiles", features[0]);
                    model.addAttribute("numberOfBindingSites", features[1]);
                    model.addAttribute("totalNumberOfRegulatedGenes", features[2]);
                    model.addAttribute("totalNumberOfRegulators", features[3]);
                    model.addAttribute("totalNumberOfRegulatoryInteractions", features[4]);
                    model.addAttribute("numberOfProteins", features[5]);
                    model.addAttribute("numberOfGenes", features[6]);
                    model.addAttribute("numberOfGenomes", features[7]);
                    model.addAttribute("organisms", (List<Organism>) features[8]);
                    model.addAttribute("message", "This gene identifier was not found in the searched organism, please try locus tag.");
                    return "searchExperimentalData";
                } else {
                    Object[] features = bringSearchPredictedFeatures();
                    searchExperimentalData(model, "Gene not found! Please try again!");
                    model.addAttribute("numberOfHmmProfiles", features[0]);
                    model.addAttribute("numberOfBindingSites", features[1]);
                    model.addAttribute("totalNumberOfRegulatedGenes", features[2]);
                    model.addAttribute("totalNumberOfRegulators", features[3]);
                    model.addAttribute("totalNumberOfRegulatoryInteractions", features[4]);
                    model.addAttribute("numberOfProteins", features[5]);
                    model.addAttribute("numberOfGenes", features[6]);
                    model.addAttribute("numberOfGenomes", features[7]);
                    model.addAttribute("organisms", (List<Organism>) features[8]);
                    model.addAttribute("message", "This gene identifier was not found in the searched organism, please try locus tag.");
                    return "searchPredictedData";
                }

            }
            ris = (ArrayList<RegulatoryInteraction>) riDAO.findByOrganismAndGene(organism, g.getId());
            pris = (ArrayList<PredictedRegulatoryInteraction>) priDAO.findByOrganismAndGene(organism, g.getId());
        }

        if (ris != null && !ris.isEmpty()) {
            //System.out.println("É experimental!!!!!!!!!!!!!!!!!!!!!");
            for (RegulatoryInteraction ri : ris) {
                //System.out.println("###############################################model");
                regView = new RegulationView();
                regView.setId(ri.getId());
                regView.setTargetGene(ri.getCorrespondentTargetGene());
                role = new String();
                if (ri.getCorrespondentTranscriptionFactor().getRole() != null) {
//                System.out.println("-----------------------------------------------------");
//                System.out.println("TF: " + ri.getCorrespondentTranscriptionFactor().getName());
//                System.out.println("TF: " + ri.getCorrespondentTranscriptionFactor().getLocusTag());
//                System.out.println("Tfrole: " + ri.getCorrespondentTranscriptionFactor().getRole());

                    if (ri.getCorrespondentTranscriptionFactor().getRole().equals("R")) {
                        ri.getCorrespondentTranscriptionFactor().setRole("Repressor");
//System.out.println("New role: " + role);
                    } else if (ri.getCorrespondentTranscriptionFactor().getRole().equals("A")) {
                        ri.getCorrespondentTranscriptionFactor().setRole("Activator");
                        //System.out.println("New role: " + role);
                    } else if (ri.getCorrespondentTranscriptionFactor().getRole().isEmpty()) {
                        sigmas.add(ri.getCorrespondentTranscriptionFactor().getName());
                        ri.getCorrespondentTranscriptionFactor().setRole("Sigma");
                    }
                    //System.out.println("-----------------------------------------------");
                }

                regView.setTranscriptionFactor(ri.getCorrespondentTranscriptionFactor());
//                if (nodes.size() == 0) {
////                System.out.println("ri.getCorrespondentTranscriptionFactor().getLocusTag(): " + ri.getCorrespondentTranscriptionFactor().getLocusTag());
////                System.out.println("ri.getCorrespondentTranscriptionFactor().getName(): " + ri.getCorrespondentTranscriptionFactor().getName());
////                System.out.println("ri.getCorrespondentTranscriptionFactor().getRole(): " + ri.getCorrespondentTranscriptionFactor().getRole());
//                    nodes.add(ri.getCorrespondentTranscriptionFactor());
//                } else if (!nodes.contains(ri.getCorrespondentTranscriptionFactor())) {
////                System.out.println("ri.getCorrespondentTranscriptionFactor().getLocusTag(): " + ri.getCorrespondentTranscriptionFactor().getLocusTag());
////                System.out.println("ri.getCorrespondentTranscriptionFactor().getName(): " + ri.getCorrespondentTranscriptionFactor().getName());
////                System.out.println("ri.getCorrespondentTranscriptionFactor().getRole(): " + ri.getCorrespondentTranscriptionFactor().getRole());
//                    nodes.add(ri.getCorrespondentTranscriptionFactor());
//                }

//                targetGene = new Gene();
//                targetGene = ri.getCorrespondentTargetGene();
//                if (targetGenes.size() == 0) {
//                    targetGenes.add(targetGene);
//                } else if (!targetGenes.contains(targetGene)) {
//                    targetGenes.add(targetGene);
//                }
                if (!ri.getRole().isEmpty()) {
                    regView.setRole(ri.getRole().charAt(0) + "");
                } else {
                    regView.setRole("S");
                    //System.out.println("regView.getRole(): " + regView.getRole());
                }

                //System.out.println("ri -> "+ ri.getRole()+" // "+ri.getId());
                regView.setEvidence(ri.getEvidence());
                regView.setPmid(ri.getPmid());
                // System.out.println("--------------------- Model regulatedby: " + ri.toString());
                bss = (ArrayList<BindingSite>) bindingSiteDAO.findByRegularotyInteraction(ri.getId());
                String bssRI = "-";
                for (int i = 0; i < bss.size(); i++) {
                    if (i == 0) {
                        bssRI = bss.get(i).getSequence();
                    } else {
                        bssRI += ", " + bss.get(i).getSequence();
                    }
                }
                regView.setBindingSite(bssRI);
                regView.setpValue(newBI);
                regulatorsDensityTf = new Long(0);
                regulatorsDensityTg = new Long(0);
                regulatorsDensityTf = riDAO.bringNumberOfRegulationsOfTf(ri.getCorrespondentTranscriptionFactor().getId());
//                if (regulatorsDensityTf >= 10) {
//                    regulatorsDensityTg = riDAO.bringNumberOfRegulationsOfTf(ri.getCorrespondentTargetGene().getId());
//                    if (regulatorsDensityTg >= 10) {
//                        regView.setRegulatorsDensity(regulatorsDensityTf + regulatorsDensityTg);
//                    } else {
//                        regView.setRegulatorsDensity(new Long(0));
//                    }
//                } else {
//                    regView.setRegulatorsDensity(new Long(0));
//                }
                //System.out.println("pValue: " + newBI.toString());
                regulationsView.add(regView);
            }
            cytoscapeFileName = cytoscapeFile.experimentalRegSif(ris, organism, gene);
        }

//        if (targetGenes != null) {
//            for (int i = 0; i < targetGenes.size(); i++) {
//                if (!nodes.contains(targetGenes.get(i))) {
//                    nodes.add(targetGenes.get(i));
//                } //else {
////                System.out.println("-----------------------------------------------");
////                System.out.println("This target is a TF also: " + targetGenes.get(i).getLocusTag());
////                System.out.println("TG name: " + targetGenes.get(i).getName());
////                System.out.println("------------------------------------------------");
//                //}
//            }
//        }
        if ((pris != null) && (searchType.equals("predicted"))) {
            System.out.println("It is predicted!!!!!!!!!1");
            ArrayList<RegulationView> checkRepetedRegulations = new ArrayList<>();
            boolean isTheSame = false;

            for (PredictedRegulatoryInteraction pri : pris) {
                //System.out.println(pri.toString());
                isTheSame = false;
                if (!pri.getPredictedRole().isEmpty()) {
                    regView.setRole(pri.getPredictedRole().charAt(0) + "");
                    System.out.println("-------------------------" + pri.getPredictedRole().charAt(0) + "");
                } else {
                    System.out.println(pri.getHomologousTranscriptionFactor() + " --> " + pri.getHomologousTargetGene());
                    System.out.println("Predicted role is empty!!");
                    regView.setRole("S");
                }

                for (RegulationView regAux : checkRepetedRegulations) {
                    if (regAux.getTranscriptionFactor().equals(pri.getHomologousTranscriptionFactor())) {
                        //System.out.println("pri.getHomologousTranscriptionFactor(): " + pri.getHomologousTranscriptionFactor());
                        //System.out.println("regAux.getTranscriptionFactor(): " + regAux.getTranscriptionFactor());
                        if (regAux.getTargetGene().equals(pri.getHomologousTargetGene())) {
                            //System.out.println("regAux.getTargetGene(): " + regAux.getTargetGene());
                            //System.out.println("pri.getHomologousTargetGene(): " + pri.getHomologousTargetGene());
                            if (regAux.getRole().equals(regView.getRole())) {
                                //System.out.println("regAux.getRole(): " + regAux.getRole());
                                //System.out.println("regView.getRole(): " + regView.getRole());
                                //System.out.println("pri.getInteractionPvalue(): " + pri.getInteractionPvalue());
                                //System.out.println("regAux.getpValue(): " + regAux.getpValue());
                                if (pri.getInteractionPvalue().compareTo(regAux.getpValue()) == -1) {
                                    //System.out.println(pri.getInteractionPvalue() + " < " + (regAux.getpValue()));
                                    //System.out.println("New regulatory interaction has a small p-value");
                                    isTheSame = true;
                                    checkRepetedRegulations.remove(regAux);
                                    regAux.setpValue(pri.getInteractionPvalue());
                                    checkRepetedRegulations.add(regAux);
                                    break;
                                } else {
                                    //System.out.println(pri.getInteractionPvalue() + " >= " + (regAux.getpValue()));
                                    //System.out.println("Old regulatory interaction has a small p-value");
                                    isTheSame = true;
                                    break;
                                }
                            }
                        }
                    } else {
                        for (RegulationView checkRepetedRegulation : checkRepetedRegulations) {
                            regulationsView.add(checkRepetedRegulation);
                        }
                        checkRepetedRegulations = new ArrayList<>();
                    }
                }

                if (!isTheSame) {
                    regView = new RegulationView();
                    regView.setId(pri.getId());
                    regView.setTargetGene(pri.getHomologousTargetGene());
                    if (pri.getHomologousTranscriptionFactor() != null) {
                        //System.out.println("pTfrole: " + pri.getHomologousTranscriptionFactor().getRole());
                        if (pri.getHomologousTranscriptionFactor().getRole().equals("R")) {
                            pri.getHomologousTranscriptionFactor().setRole("Repressor");
                            //System.out.println("New role: " + pri.getHomologousTranscriptionFactor().getRole());
                        } else if (pri.getHomologousTranscriptionFactor().getRole().equals("A")) {
                            pri.getHomologousTranscriptionFactor().setRole("Activator");
                            //System.out.println("New role: " + pri.getHomologousTranscriptionFactor().getRole());
                        } else if (pri.getHomologousTranscriptionFactor().getRole().isEmpty()) {
                            sigmas.add(pri.getHomologousTranscriptionFactor().getName());
                            pri.getHomologousTranscriptionFactor().setRole("Sigma");
                            //System.out.println("New role: " + pri.getHomologousTranscriptionFactor().getRole());
                        }
                    }
                    regView.setTranscriptionFactor(pri.getHomologousTranscriptionFactor());
//                if (pri.getRegulatoryInteraction().getRole().equals("")) {
//                    regView.setRole("-");
//                } else {
//                    regView.setRole(pri.getRegulatoryInteraction().getRole().charAt(0) + "");
//                }
                    //regView.setRole(pri.getRegulatoryInteraction().getRole().charAt(0) + "");
                    if (pri.getPredictedBindingSite() != null && !pri.getPredictedBindingSite().getSequence().isEmpty()) {
                        regView.setBindingSite(pri.getPredictedBindingSite().getSequence());
                    } else {
                        regView.setBindingSite("-");
                    }
                    regView.setModelOrganism(pri.getRegulatoryInteraction().getCorrespondentTargetGene().getGenome().getOrganism());
                    regView.setpValue(pri.getInteractionPvalue());
                    checkRepetedRegulations.add(regView);
                    //regulationsView.add(regView);
                }
            }

            if (!checkRepetedRegulations.isEmpty()) {
                for (RegulationView checkRepetedRegulation : checkRepetedRegulations) {
                    regulationsView.add(checkRepetedRegulation);
                }
            }

            //System.out.println("o.getType(): " + o.getType());
            if (o.getType().equals("target")) {
                cytoscapeFileName = cytoscapeFile.predictedRegSif(pris, organism, gene);
            }
        }

        g = new Gene();
        Set<Gene> genes = new ArraySet<>();
        for (RegulationView rv : regulationsView) {
            //    System.out.println(rv.toString());
            g = geneDAO.findById(rv.getTranscriptionFactor().getId());
            genes.add(g);
            g = geneDAO.findById(rv.getTargetGene().getId());
            genes.add(g);
        }
        for (Gene actualGene : genes) {
            //System.out.println("gene: " + gene);
            geneOperon = geneOpDAO.findByGene(actualGene.getId());
            operon = new Operon();
            operon.setId(0);
            operon.setName("");
            operon.setOrientation("");
            Integer posGeneOp = 0;
            if (geneOperon != null && !geneOperon.equals("")) {
                operon = opDAO.findById(geneOperon.getGeneOperonPK().getOperon());
                opInfo = new ArrayList<>();
                opInfo.add(operon.getName());
                //System.out.println("operon_name: " + operon.getName());
                String pos = Integer.toString(geneOperon.getPos());
                //System.out.println("pos: " + pos);
                opInfo.add(pos);
                genesOperon = (ArrayList<GeneOperon>) geneOpDAO.findByOperon(operon.getId());
                genesOfOp = new ArrayList<>();
                for (GeneOperon geneOp : genesOperon) {
                    //System.out.println("geneOp: " + geneOp);
                    g = geneDAO.findById(geneOp.getGeneOperonPK().getGene());
                    if (!g.getName().equals("")) {
                        //System.out.println("gene: " + g.getName());
                        genesOfOp.add(g.getLocusTag() + "+" + g.getName());
                    } else {
                        //System.out.println("gene: " + g.getLocusTag());
                        genesOfOp.add(g.getLocusTag() + "+" + g.getLocusTag());
                    }
                }
                //System.out.println("operon: " + operon.getName() + " " + genesOfOp);
                operons.put(operon.getName(), genesOfOp);
                posGeneOp = geneOperon.getPos();
            }

            geneInfo = new GeneInfo();
            if (actualGene.getRole() != null) {
                if (actualGene.getRole().equals("R")) {
                    actualGene.setRole("Repressor");
                } else if (actualGene.getRole().equals("A")) {
                    actualGene.setRole("Activator");
                } else if (actualGene.getRole().equals("D")) {
                    actualGene.setRole("Dual");
                } else if (sigmas.contains(actualGene.getName())) {
                    //System.out.println("Sigma!!!!!!!!!!!!");
                    // System.out.println(gene.getName());
                    actualGene.setRole("Sigma");
                    //   Thread.sleep(5000);
                }
            }

            //System.out.println("name: " + gene.getLocusTag());
            //actualGene.setProduct("");
            geneInfo.setGene(actualGene);
            //System.out.println("opname: " + operon.getName());
            geneInfo.setOperon(operon.getName());
            //System.out.println("posGeneOp: " + posGeneOp);
            geneInfo.setPos(posGeneOp);

            if (!actualGene.getName().isEmpty()) {
                genesInfo.put(actualGene.getName(), geneInfo);
            } else {
//                geneInfo = new GeneInfo();
//                if (gene.getRole() != null) {
//                    if (gene.getRole().equals("R")) {
//                        gene.setRole("Repressor");
//                    } else if (gene.getRole().equals("A")) {
//                        gene.setRole("Activator");
//                    }
//                }
//
//                geneInfo.setGene(gene);
//                geneInfo.setOperon(operon.getName());
//                geneInfo.setPos(posGeneOp);
                genesInfo.put(actualGene.getLocusTag(), geneInfo);
            }
        }

        //    if (gene == null || gene.isEmpty()) {
//        } else {
//            g = new Gene();
//            g = geneDAO.findByLocusTag(gene);
//            cytoscapeFileName = cytoscapeFile.cytoscapeOfInterestGeneFile(g.getId());
//        }
        System.out.println("cytoscapeFileName: " + cytoscapeFileName);
        cytoscapeFileName += ".sif";
        System.out.println("cytoscapeFileName: " + cytoscapeFileName);

        if (regulationsView.isEmpty()) {
            model.addAttribute("o", o);
            return "networkEmpty";
        }

        model.addAttribute("goBackTo", goBackTo);
        model.addAttribute("typeOfGoBack", "normal");
        model.addAttribute("type", searchType);
        //model.addAttribute("nodes", nodes);
        model.addAttribute("cytoscapeFileName", cytoscapeFileName);
        model.addAttribute("o", o);
        model.addAttribute("operons", operons);
        //model.addAttribute("geneOpInfo", geneOpInfo);
        model.addAttribute("genesInfo", genesInfo);
        model.addAttribute("regulationsView", regulationsView);
        return "networkDinamicVisualization";
    }

    @RequestMapping("whichNetwork")
    public String whichNetwork(Model model, Integer organism, String searchType, String gene, String goBackTo, String layoutType, String geneRna) throws InterruptedException {

        if (searchType == null) {
            return "index";
        }

        System.out.println("--------------- Organism: " + organism);
        System.out.println("--------------- SearchType: " + searchType);
        System.out.println("Gooooooooooooooo networkVisualization!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("gene: " + gene);
        System.out.println("goBackTo: " + goBackTo);
        System.out.println("layoutType: " + layoutType);

        //System.out.println("Which network?");
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        ArrayList<RegulationView> regulationsView = new ArrayList<>();
        ArrayList<RegulatoryInteraction> ris = new ArrayList<>();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        ArrayList<PredictedRegulatoryInteraction> pris = new ArrayList<>();

        GeneOperonViewDAO govDAO = new GeneOperonViewDAO();
        GeneInfoViewDAO gInfoDAO = new GeneInfoViewDAO();
        //GeneOperonView operons = new GeneOperonView();
        ArrayList<BindingSite> bss = new ArrayList<>();
        //GeneInfo geneInfo = new GeneInfo();
        TreeMap<String, GeneInfo> genesInfo = new TreeMap<>();
        TreeMap<String, SmallRna> srnasInfo = new TreeMap<>();
        List<SmallRna> srnalist = new ArrayList<SmallRna>();
        SmallRnaDAO srnaDAO = new SmallRnaDAO();

        OperonDAO opDAO = new OperonDAO();
        TreeMap<String, ArrayList<String>> operons = new TreeMap<>();
        OrganismDAO organismDAO = new OrganismDAO();
        Organism o = organismDAO.findById(organism);
        GenomeDAO genomeDAO = new GenomeDAO();
        Genome genome = genomeDAO.findByOrganism(organism);
        ArrayList<String> sigmas = new ArrayList<>();
        BigDecimal newBI = BigDecimal.ONE;
        newBI = newBI.negate();
        CytoscapeFile cytoscapeFile = new CytoscapeFile();
        String cytoscapeFileName = "";
        //String[] hubsConnected;

        Gene g = new Gene();
        SmallRna sRNA = new SmallRna();
        GeneDAO geneDAO = new GeneDAO();
        String role = new String();
        Set<Gene> genes = new ArraySet<>();
        ArrayList infos = new ArrayList();
        RegulatoryInteractionViewDAO riViewDAO = new RegulatoryInteractionViewDAO();
        RegulatoryInteractionView riView = new RegulatoryInteractionView();
        List<RegulatoryInteractionView> regulatoryInteractionViews;
        PredictedRegulatoryInteractionViewDAO priViewDAO = new PredictedRegulatoryInteractionViewDAO();
        PredictedRegulatoryInteractionView priView = new PredictedRegulatoryInteractionView();
        List<PredictedRegulatoryInteractionView> predictedRegulatoryInteractionViews;

        RnaRegulationViewDAO rnaRegDAO = new RnaRegulationViewDAO();
        List<RnaRegulationView> rnaRegList = new LinkedList<>();

        if (gene == null || gene.isEmpty()) {
            System.out.println("Gene is empty!!! -->> Experimental + Predicted data");
            ris = riDAO.findByOrganism(organism);
            pris = priDAO.findByOrganism(organism);
            //rnaRegList = rnaRegDAO.findByGenome(genome.getId());

            if (o.getType().equals("model")) {
                regulatoryInteractionViews = riViewDAO.findByGenome(genome.getId());
                regulationsView = riView.getRegulationViewList(regulatoryInteractionViews);
                //ris = (ArrayList<RegulatoryInteraction>) riDAO.findByOrganismAndGene(organism, g.getId());
                cytoscapeFileName = cytoscapeFile.experimentalRegSif(ris, organism, gene);
            } else {
                //System.out.println("Predicted TRN of organism");
                //pris = (ArrayList<PredictedRegulatoryInteraction>) priDAO.findByOrganismAndGene(organism, g.getId());
                predictedRegulatoryInteractionViews = priViewDAO.findByGenome(genome.getId());
                regulationsView = priView.getRegulationViewList(predictedRegulatoryInteractionViews);
                cytoscapeFileName = cytoscapeFile.predictedRegSif(pris, organism, gene);
            }

            operons = govDAO.getOperonsTreeByGenome(genome.getId());
            genesInfo = gInfoDAO.getGeneInfoTreeByGenome(genome.getId());

            //srnalist = srnaDAO.findByGenome(genome.getId());
////            if (pris != null && !pris.isEmpty()) {
////                for (PredictedRegulatoryInteraction pri11 : pris) {
////                    System.out.println("pri11: " + pri11.getHomologousTranscriptionFactor().toString2());
////                }
////            }
        } else {

            if (geneRna.equals("rna")) {
                sRNA = srnaDAO.findByLocusTag(gene);

                System.out.println("sRNA: " + sRNA);
                if (sRNA == null) {
                    //System.out.println("Genes is null!!!!!!!!!!!!!");
                    model = returnToSearch(model, searchType);
                    if (searchType.equals("experimental")) {
                        return "searchExperimentalData";
                    } else {
                        return "searchPredictedData";
                    }
                }

                rnaRegList = (List<RnaRegulationView>) rnaRegDAO.findBySrna(sRNA.getId());
                System.out.println(rnaRegList);
                genesInfo = gInfoDAO.getGeneInfoTreeByGenome(genome.getId());
                srnasInfo.put(sRNA.getLocusTag(), sRNA);

            } else {
                g = geneDAO.findGeneByLocusTagOrGeneName(organism, gene);
                //System.out.println("----------------------------------g: " + g);
                if (g == null) {
                    //System.out.println("Genes is null!!!!!!!!!!!!!");
                    model = returnToSearch(model, searchType);
                    if (searchType.equals("experimental")) {
                        return "searchExperimentalData";
                    } else {
                        return "searchPredictedData";
                    }
                }
                ris = (ArrayList<RegulatoryInteraction>) riDAO.findByOrganismAndGene(organism, g.getId());
                pris = (ArrayList<PredictedRegulatoryInteraction>) priDAO.findByOrganismAndGene(organism, g.getId());
                rnaRegList = rnaRegDAO.findByTg(g.getId());

                if (o.getType().equals("model")) {
                    regulatoryInteractionViews = riViewDAO.findByGene(g.getId());
                    regulationsView = riView.getRegulationViewList(regulatoryInteractionViews);
                    //ris = (ArrayList<RegulatoryInteraction>) riDAO.findByOrganismAndGene(organism, g.getId());
                    cytoscapeFileName = cytoscapeFile.experimentalRegSif(ris, organism, gene);
                } else {
                    //System.out.println("Predicted TRN of organism");
                    //pris = (ArrayList<PredictedRegulatoryInteraction>) priDAO.findByOrganismAndGene(organism, g.getId());
                    predictedRegulatoryInteractionViews = priViewDAO.findByGene(g.getId());
                    regulationsView = priView.getRegulationViewList(predictedRegulatoryInteractionViews);
                    cytoscapeFileName = cytoscapeFile.predictedRegSif(pris, organism, gene);
                }

                operons = govDAO.getOperonsTreeByGenome(genome.getId());
                genesInfo = gInfoDAO.getGeneInfoTreeByGenome(genome.getId());
                srnalist = srnaDAO.findByGenome(genome.getId());
                for (int i = 0; i < srnalist.size(); i++) {
                    srnasInfo.put(srnalist.get(i).getLocusTag(), srnalist.get(i));
                }
            }
        }

        System.out.println("cytoscapeFileName: " + cytoscapeFileName);
        cytoscapeFileName += ".sif";
        System.out.println("cytoscapeFileName: " + cytoscapeFileName);

        if (regulationsView.isEmpty() && rnaRegList.isEmpty()) {
            model.addAttribute("o", o);
            model.addAttribute("type", searchType);
            model.addAttribute("message", "No regulation is known for " + gene + " in this organism so far.");
            return "networkEmpty";
        }

        //rnaRegList srnasInfo
        model.addAttribute("srnasInfo", srnasInfo);
        model.addAttribute("rnaRegList", rnaRegList);
        model.addAttribute("goBackTo", goBackTo);
        model.addAttribute("typeOfGoBack", "normal");
        model.addAttribute("type", searchType);
        //model.addAttribute("nodes", nodes);
        model.addAttribute("cytoscapeFileName", cytoscapeFileName);
        model.addAttribute("o", o);
        model.addAttribute("operons", operons);
        //model.addAttribute("geneOpInfo", geneOpInfo);
        model.addAttribute("genesInfo", genesInfo);
        model.addAttribute("regulationsView", regulationsView);

        //if (layoutType.equals("fast")) {
        return "networkDinamicVisualization-fast";
        //} else {
        //  return "networkDinamicVisualization";
        //}
    }

    public Model returnToSearch(Model model, String searchType) {
        if (searchType.equals("experimental")) {
            Object[] features = bringSearchExperimentalFeatures();
            searchExperimentalData(model, "Gene not found! Please try again!");
            model.addAttribute("numberOfHmmProfiles", features[0]);
            model.addAttribute("numberOfBindingSites", features[1]);
            model.addAttribute("totalNumberOfRegulatedGenes", features[2]);
            model.addAttribute("totalNumberOfRegulators", features[3]);
            model.addAttribute("totalNumberOfRegulatoryInteractions", features[4]);
            model.addAttribute("numberOfProteins", features[5]);
            model.addAttribute("numberOfGenes", features[6]);
            model.addAttribute("numberOfGenomes", features[7]);
            model.addAttribute("items", (List<OrganismItem>) features[8]);
            model.addAttribute("message", "This gene identifier was not found in the searched organism, please try locus tag.");
            return model;

        } else {
            Object[] features = bringSearchPredictedFeatures();
            searchExperimentalData(model, "Gene not found! Please try again!");
            model.addAttribute("numberOfHmmProfiles", features[0]);
            model.addAttribute("numberOfBindingSites", features[1]);
            model.addAttribute("totalNumberOfRegulatedGenes", features[2]);
            model.addAttribute("totalNumberOfRegulators", features[3]);
            model.addAttribute("totalNumberOfRegulatoryInteractions", features[4]);
            model.addAttribute("numberOfProteins", features[5]);
            model.addAttribute("numberOfGenes", features[6]);
            model.addAttribute("numberOfGenomes", features[7]);
            model.addAttribute("items", (List<OrganismItem>) features[8]);
            model.addAttribute("message", "This gene identifier was not found in the searched organism, please try locus tag.");
            return model;
        }
    }

    public TreeMap<String, GeneInfo> getGeneInfoObj() {
        GeneInfoViewDAO geneInfoViewDAO = new GeneInfoViewDAO();
        List<GeneInfoView> geneInfoViews = geneInfoViewDAO.findByGenome(1226);

//        ArraySet<GeneInfoView> geneInfoViewsSet = new ArraySet<>(geneInfoViews);
        TreeMap<String, GeneInfo> geneInfo = new TreeMap();

        String geneNames = "";
        for (GeneInfoView geneInfoViewsSet1 : geneInfoViews) {
            System.out.println(geneInfoViewsSet1);
            geneInfo.put(geneInfoViewsSet1.getGeneName(), geneInfoViewsSet1.getGeneInfo());
        }

        return geneInfo;
    }

    public TreeMap<String, ArrayList<String>> getOperonsObject(Integer genome) {
        GeneOperonViewTest geneOperonView = new GeneOperonViewTest();
        GeneOperonViewDAO geneOperonViewDAO = new GeneOperonViewDAO();
        List<GeneOperonView> geneOperonViews = geneOperonViewDAO.findByGenome(genome);

        ArraySet<GeneOperonView> geneOperonViewsSet = new ArraySet<>(geneOperonViews);
        TreeMap<String, ArrayList<String>> operons = new TreeMap();

        String geneNames = "";
        ArrayList<String> genes = null;
        for (GeneOperonView geneOperonViewsSet1 : geneOperonViewsSet) {
            System.out.println(geneOperonViewsSet1);

            if (operons.containsKey(geneOperonViewsSet1.getOperonName())) {
                geneNames = geneOperonView.getGeneNames(geneOperonViewsSet1.getGeneName(), geneOperonViewsSet1.getLocusTag());
                genes.add(geneNames);
                operons.put(geneOperonViewsSet1.getOperonName(), genes);
            } else {
                genes = new ArrayList<String>();
                geneNames = geneOperonView.getGeneNames(geneOperonViewsSet1.getGeneName(), geneOperonViewsSet1.getLocusTag());
                genes.add(geneNames);
                operons.put(geneOperonViewsSet1.getOperonName(), genes);
            }

        }
        return operons;
    }

//// 
    @RequestMapping("networkDinamicVisualizationOperons")
    public String networkDinamicVisualizationOperons(Model model, Integer organism, String searchType, String[] interestGenes, String role) throws InterruptedException {
        //System.out.println("--------------- Organism: " + organism);
        // System.out.println("--------------- SearchType: " + searchType);
        // System.out.println("Gooooooooooooooo networkVisualization!!!!!!!!!!!!!!!!!!!!!!!");
        // System.out.println("gene: " + gene);
        //System.out.println("role: " + role);

        if (interestGenes == null) {
            return "index";
        }

        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        ArrayList<RegulationView> regulationsView = new ArrayList<>();
        ArrayList<RegulatoryInteraction> ris = new ArrayList<>();
        ArrayList<RegulatoryInteraction> risInterestGenes = new ArrayList<>();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        List<PredictedRegulatoryInteraction> pris = new ArrayList<>();
        List<PredictedRegulatoryInteraction> prisInterestGenes = new ArrayList<>();
        RegulationView regView = new RegulationView();
        BindingSiteDAO bindingSiteDAO = new BindingSiteDAO();
        ArrayList<BindingSite> bss = new ArrayList<>();
        GeneInfo geneInfo = new GeneInfo();
        TreeMap<String, GeneInfo> genesInfo = new TreeMap<>();
        OperonDAO opDAO = new OperonDAO();
        GeneOperonDAO geneOpDAO = new GeneOperonDAO();
        TreeMap<String, ArrayList<String>> operons = new TreeMap<>();
        TreeMap<String, ArrayList<String>> geneOpInfo = new TreeMap<>();
        Operon operon = new Operon();
        GeneOperon geneOperon = new GeneOperon();
        ArrayList<String> opInfo = new ArrayList<>();
        ArrayList<GeneOperon> genesOperon = new ArrayList<>();
        ArrayList<String> genesOfOp = new ArrayList<>();
        OrganismDAO organismDAO = new OrganismDAO();
        Organism o = organismDAO.findById(organism);
        ArrayList<String> sigmas = new ArrayList<>();
        BigDecimal newBI = BigDecimal.ONE;

        newBI = newBI.negate();
        CytoscapeFile cytoscapeFile = new CytoscapeFile();
        String cytoscapeFileName = "";
        String[] hubsConnected;
        Long regulatorsDensityTf;
        Long regulatorsDensityTg;
        Gene g = new Gene();
        GeneDAO geneDAO = new GeneDAO();
        //ArrayList<Gene> nodes = new ArrayList<>();
        //String role = new String();
        Gene targetGene;
        ArrayList<Gene> targetGenes = new ArrayList<>();
        Operon op = new Operon();
        GeneInfoViewDAO gInfoDAO = new GeneInfoViewDAO();

        //For sRNA
        SmallRnaDAO srnaDAO = new SmallRnaDAO();
        RnaRegulationViewDAO rnaRegDAO = new RnaRegulationViewDAO();
        List<RnaRegulationView> rnaRegList = new LinkedList<>();
        SmallRna sRNA = new SmallRna();
        GenomeDAO genomeDAO = new GenomeDAO();
        Genome genome = genomeDAO.findByOrganism(organism);
        TreeMap<String, SmallRna> srnasInfo = new TreeMap<>();

        if (role.equals("X")) {
            sRNA = srnaDAO.findByLocusTag(interestGenes[0]);

            System.out.println("sRNA: " + sRNA);
            if (sRNA == null) {
                //System.out.println("Genes is null!!!!!!!!!!!!!");
                model = returnToSearch(model, searchType);
                if (searchType.equals("experimental")) {
                    return "searchExperimentalData";
                } else {
                    return "searchPredictedData";
                }
            }

            rnaRegList = (List<RnaRegulationView>) rnaRegDAO.findBySrna(sRNA.getId());
            System.out.println(rnaRegList);
            genesInfo = gInfoDAO.getGeneInfoTreeByGenome(genome.getId());
            srnasInfo.put(sRNA.getLocusTag(), sRNA);

        } else {

            //System.out.println(interestGenes);
            for (String interestGene : interestGenes) {
                System.out.println("interestGene: " + interestGene);
                g = geneDAO.findGeneByLocusTagOrGeneName(organism, interestGene);
                risInterestGenes = (ArrayList<RegulatoryInteraction>) riDAO.findByOrganismAndGene(organism, g.getId());
                prisInterestGenes = (ArrayList<PredictedRegulatoryInteraction>) priDAO.findByOrganismAndGene(organism, g.getId());

                for (RegulatoryInteraction risInterestGene : risInterestGenes) {
                    //System.out.println(risInterestGene.toString());
                    if (!ris.contains(risInterestGene)) {
                        ris.add(risInterestGene);
                    }
                }

                for (PredictedRegulatoryInteraction prisInterestGene : prisInterestGenes) {
                    //System.out.println(prisInterestGene.toString());
                    if (!pris.contains(prisInterestGene)) {
                        pris.add(prisInterestGene);
                    }
                }
            }

            for (RegulatoryInteraction ri : ris) {
                //System.out.println("###############################################model");
                regView = new RegulationView();
                regView.setId(ri.getId());
                regView.setTargetGene(ri.getCorrespondentTargetGene());
                //role = new String();
                if (ri.getCorrespondentTranscriptionFactor().getRole() != null) {
                    ri.getCorrespondentTranscriptionFactor().setRole(ri.getCorrespondentTranscriptionFactor().getRole());
                }

                regView.setTranscriptionFactor(ri.getCorrespondentTranscriptionFactor());
                targetGene = new Gene();
                targetGene = ri.getCorrespondentTargetGene();
                if (targetGenes.size() == 0) {
                    targetGenes.add(targetGene);
                } else if (!targetGenes.contains(targetGene)) {
                    targetGenes.add(targetGene);
                }

                regView.setRole(ri.getRole());
                regView.setEvidence(ri.getEvidence());
                regView.setPmid(ri.getPmid());
                // System.out.println("--------------------- Model regulatedby: " + ri.toString());
                bss = (ArrayList<BindingSite>) bindingSiteDAO.findByRegularotyInteraction(ri.getId());
                String bssRI = "-";
                for (int i = 0; i < bss.size(); i++) {
                    if (i == 0) {
                        bssRI = bss.get(i).getSequence();
                    } else {
                        bssRI += ", " + bss.get(i).getSequence();
                    }
                }
                regView.setBindingSite(bssRI);
                regView.setpValue(newBI);
                regulationsView.add(regView);
            }

            if (searchType.equals("predicted")) {
                ArrayList<RegulationView> checkRepetedRegulations = new ArrayList<>();
                boolean isTheSame = false;

                for (PredictedRegulatoryInteraction pri : pris) {
                    isTheSame = false;
                    //System.out.println("TF: " + pri.getHomologousTranscriptionFactor().getLocusTag());
                    //System.out.println("TF role: " + pri.getHomologousTranscriptionFactor().getRole());
                    //System.out.println("RI role: " + pri.getPredictedRole());
                    regView.setRole(pri.getPredictedRole());
                    for (RegulationView regAux : checkRepetedRegulations) {
                        if (regAux.getTranscriptionFactor().equals(pri.getHomologousTranscriptionFactor())) {
                            if (regAux.getTargetGene().equals(pri.getHomologousTargetGene())) {
                                if (regAux.getRole().equals(pri.getPredictedRole())) {
                                    if (pri.getInteractionPvalue().compareTo(regAux.getpValue()) == -1) {
                                        //System.out.println(pri.getInteractionPvalue() + " < " + (regAux.getpValue()));
                                        //System.out.println("New regulatory interaction has a small p-value");
                                        isTheSame = true;
                                        checkRepetedRegulations.remove(regAux);
                                        regAux.setpValue(pri.getInteractionPvalue());
                                        checkRepetedRegulations.add(regAux);
                                        break;
                                    } else {
                                        //System.out.println(pri.getInteractionPvalue() + " >= " + (regAux.getpValue()));
                                        //System.out.println("Old regulatory interaction has a small p-value");
                                        isTheSame = true;
                                        break;
                                    }
                                }
                            }
                        } else {
                            for (RegulationView checkRepetedRegulation : checkRepetedRegulations) {
                                regulationsView.add(checkRepetedRegulation);
                            }
                            checkRepetedRegulations = new ArrayList<>();
                        }
                    }

                    if (!isTheSame) {
                        regView = new RegulationView();
                        regView.setRole(pri.getPredictedRole());
                        regView.setId(pri.getId());
                        regView.setTargetGene(pri.getHomologousTargetGene());
                        pri.getHomologousTranscriptionFactor().setRole(pri.getHomologousTranscriptionFactor().getRole());
                        regView.setTranscriptionFactor(pri.getHomologousTranscriptionFactor());
                        if (pri.getPredictedBindingSite() != null && !pri.getPredictedBindingSite().getSequence().isEmpty()) {
                            regView.setBindingSite(pri.getPredictedBindingSite().getSequence());
                        } else {
                            regView.setBindingSite("-");
                        }
                        regView.setModelOrganism(pri.getRegulatoryInteraction().getCorrespondentTargetGene().getGenome().getOrganism());
                        regView.setpValue(pri.getInteractionPvalue());
                        checkRepetedRegulations.add(regView);
                        //System.out.println("checkRepetedRegulations.size(): " + checkRepetedRegulations.size());
                        //regulationsView.add(regView);
                    }
                }

                if (!checkRepetedRegulations.isEmpty()) {
                    for (RegulationView checkRepetedRegulation : checkRepetedRegulations) {
                        regulationsView.add(checkRepetedRegulation);
                    }
                }

                if (interestGenes.length > 1) {
                    String firstGene = interestGenes[0];
                    //System.out.println("firstGene: " + firstGene);
                    Gene myGene = geneDAO.findGeneByLocusTagOrGeneName(organism, firstGene);
                    GeneOperon geneOp = new GeneOperon();
                    geneOp = geneOpDAO.findByGene(myGene.getId());
                    op = opDAO.findById(geneOp.getGeneOperonPK().getOperon());

                    if (o.getType().equals("model")) {
                        //System.out.println("FUI 1");
                        cytoscapeFileName = cytoscapeFile.experimentalRegSif(ris, organism, op.getName());
                    } else {
                        //System.out.println("FUI 2");
                        cytoscapeFileName = cytoscapeFile.predictedRegSif((ArrayList<PredictedRegulatoryInteraction>) pris, organism, op.getName());
                    }

                } else {
                    if (o.getType().equals("model")) {
                        //System.out.println("FUI 3");
                        cytoscapeFileName = cytoscapeFile.experimentalRegSif(ris, organism, interestGenes[0]);
                    } else {
                        //System.out.println("FUI 4");
                        cytoscapeFileName = cytoscapeFile.predictedRegSif((ArrayList<PredictedRegulatoryInteraction>) pris, organism, interestGenes[0]);
                    }
                }
            } else {
                if (interestGenes.length > 1) {
                    Gene myGene = geneDAO.findGeneByLocusTagOrGeneName(organism, interestGenes[0]);
                    GeneOperon geneOp = new GeneOperon();
                    geneOp = geneOpDAO.findByGene(myGene.getId());
                    op = opDAO.findById(geneOp.getGeneOperonPK().getOperon());
                    cytoscapeFileName = cytoscapeFile.experimentalRegSif(ris, organism, op.getName());
                } else {
                    cytoscapeFileName = cytoscapeFile.experimentalRegSif(ris, organism, interestGenes[0]);
                }
                //cytoscapeFile = cytoscapeFile.experimentalRegSif(ris, organism, searchType);
            }

            g = new Gene();
            List<Gene> genesList = geneDAO.findByOrganism(organism);

            for (Gene actualGene : genesList) {
                //System.out.println("gene: " + actualGene);

                geneOperon = geneOpDAO.findByGene(actualGene.getId());
                operon = new Operon();
                operon.setId(0);
                operon.setName("");
                operon.setOrientation("");
                Integer posGeneOp = 0;
                if (geneOperon != null && !geneOperon.equals("")) {
                    operon = opDAO.findById(geneOperon.getGeneOperonPK().getOperon());
                    opInfo = new ArrayList<>();
                    opInfo.add(operon.getName());
                    //System.out.println("operon_name: " + operon.getName());
                    String pos = Integer.toString(geneOperon.getPos());
                    //System.out.println("pos: " + pos);
                    opInfo.add(pos);
                    genesOperon = (ArrayList<GeneOperon>) geneOpDAO.findByOperon(operon.getId());
                    genesOfOp = new ArrayList<>();
                    for (GeneOperon geneOp : genesOperon) {
                        //System.out.println("geneOp: " + geneOp);
                        g = geneDAO.findById(geneOp.getGeneOperonPK().getGene());
                        if (!g.getName().equals("")) {
                            //System.out.println("gene: " + g.getName());
                            genesOfOp.add(g.getLocusTag() + "+" + g.getName());
                        } else {
                            //System.out.println("gene: " + g.getLocusTag());
                            genesOfOp.add(g.getLocusTag() + "+" + g.getLocusTag());
                        }
                    }
                    //System.out.println("operon: " + operon.getName() + " " + genesOfOp);
                    operons.put(operon.getName(), genesOfOp);
                    if (!actualGene.getName().equals("")) {
                        geneOpInfo.put(actualGene.getName(), opInfo);
                    } else {
                        geneOpInfo.put(actualGene.getLocusTag(), opInfo);
                    }
                    posGeneOp = geneOperon.getPos();
                }

                geneInfo = new GeneInfo();
                actualGene.setProduct("");
                geneInfo.setGene(actualGene);
                geneInfo.setOperon(operon.getName());
                geneInfo.setPos(posGeneOp);

                if (!actualGene.getName().isEmpty()) {
                    genesInfo.put(actualGene.getName(), geneInfo);
                } else {
                    genesInfo.put(actualGene.getLocusTag(), geneInfo);
                }
            }
        }

        cytoscapeFileName += ".sif";
        model.addAttribute("srnasInfo", srnasInfo);
        model.addAttribute("rnaRegList", rnaRegList);
        model.addAttribute("typeOfGoBack", "newScreen");
        model.addAttribute("type", searchType);
        //model.addAttribute("nodes", nodes);
        model.addAttribute("cytoscapeFileName", cytoscapeFileName);
        model.addAttribute("o", o);
        model.addAttribute("operons", operons);
        model.addAttribute("geneOpInfo", geneOpInfo);
        model.addAttribute("genesInfo", genesInfo);
        model.addAttribute("regulationsView", regulationsView);
        return "networkDinamicVisualization";
    }

    @RequestMapping("bindingSite")
    public String bindingSite(Model model, Integer selectedOrganism,
            Integer organismId, Integer minNOfHMMs,
            String evalue, Integer bsSearchType,
            Integer id, String type) throws IOException {

        if (id == null) {
            return "index";
        }

        System.out.println("--------------------------------");
        System.out.println("--------------- selectedOrganism: " + selectedOrganism);
        System.out.println("--------------- organism: " + organismId);
        System.out.println("--------------- Minimal number of Binding Sites: " + minNOfHMMs);
        System.out.println("--------------- e-value cutOff: " + evalue);
        System.out.println("--------------- bsSearchType: " + bsSearchType);
        System.out.println("--------------- GeneID: " + id + "\n\n");
        System.out.println("--------------- Type: " + type + "\n\n");
        GeneDAO geneDAO = new GeneDAO();
        Gene targetGene = geneDAO.findById(id);
        Gene gene = targetGene;
        ArrayList<Organism> organisms = new ArrayList<>();
        ArrayList<BindingSiteRegulationPrediction> bsRegPredictions = new ArrayList<>();
        String transferenceFolder = System.getProperty("user.dir");
        SearchBindingSites searchBSites = new SearchBindingSites();
        Organism organism = new Organism();
        OrganismDAO organismDAO = new OrganismDAO();
        Object[] bsPredictedInfo = new Object[2];
        Integer usedHMMs = 0;
        int upStreamPos = 0;
        String upStreamRegion = "";
        HomologousDAO homolgDAO = new HomologousDAO();
        List<Homologous> homologs = new ArrayList<>();

        System.out.println("--------------------------------");
        System.out.println("--------------------------------");
        System.out.println("--------------------------------");
        //Search In the upstream sequence of this gene
        if (bsSearchType == 0) {
            if (selectedOrganism == 0) {
                System.out.println("bsSearchType == 0 | selectedOrganism == 0");
                organisms = (ArrayList<Organism>) organismDAO.listAll();
            } else {
                System.out.println("bsSearchType == 0 | selectedOrganism != 0");
                Organism organismAux = organismDAO.findById(selectedOrganism);
                organisms.add(organismAux);
            }

            GeneOperon geneOperon = new GeneOperon();
            GeneOperonDAO geneOperonDAO = new GeneOperonDAO();
            geneOperon = geneOperonDAO.findByGene(gene.getId());
            System.out.println("GENE op " + geneOperon);
            Operon operon = new Operon();
            OperonDAO operonDAO = new OperonDAO();
            if (geneOperon != null) {
                operon = operonDAO.findById(geneOperon.getGeneOperonPK().getOperon());
                geneOperon = (GeneOperon) geneOperonDAO.findFirstGeneOfOperon(operon.getId(), 1);
                System.out.println("==============================>>>>>>>>>>>>>>>>GeneOperon: " + geneOperon);
                gene = geneDAO.findById(geneOperon.getGeneOperonPK().getGene());
            } else {
                System.out.println("gene op é nulo!!");
            }

            System.out.println("callHMMERThisGene");
            bsPredictedInfo = searchBSites.callHMMERThisGene(transferenceFolder, targetGene, organisms, evalue, minNOfHMMs);
            usedHMMs = (Integer) bsPredictedInfo[0];
            bsRegPredictions = (ArrayList<BindingSiteRegulationPrediction>) bsPredictedInfo[1];
            if (gene != null && gene.getSearchSpace() != null) {
                upStreamPos = gene.getSearchSpace() - 20;
                upStreamRegion = "-" + upStreamPos + ".." + "20";
            } else {
                upStreamRegion = "No promoter region found!";
            }
        } else {
            System.out.println("callHMMERThisRegulator");
            if (gene.getBsNumber() == null) {
                System.out.println("It's nullllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");

            } else {
                bsRegPredictions = searchBSites.callHMMERThisRegulator(transferenceFolder, gene, evalue, organismId);
                organism = organismDAO.findById(organismId);
            }

        }

//        for (BindingSitePredictionInfo bspi : predictionInfoBSs) {
//            System.out.println("------------->>>> " + bspi);
//        }
//        for (BindingSiteRegulationPrediction bspi : bsRegPredictions) {
//            System.out.println("*****************   " + bspi);
//        }
        //System.out.println("Gooooooooooooooo bindingSite!!!!!!!!!!!!!!!!!!!!!!!");
        //System.out.println("----------------------------- go to view");
        model.addAttribute("usedHMMs", usedHMMs);
        model.addAttribute("type", type);
        model.addAttribute("upStreamRegion", upStreamRegion);
        model.addAttribute("minNOfHMMs", minNOfHMMs);
        model.addAttribute("bsSearchType", bsSearchType);
        model.addAttribute("organism", organism);
        model.addAttribute("evalue", evalue);
        model.addAttribute("gene", targetGene);
        model.addAttribute("bsRegPredictions", bsRegPredictions);

        // System.out.println("--------------------------------");
        // System.out.println("--------------------------------");
        // System.out.println("--------------------------------");
        return "bindingSite";
    }

    @RequestMapping("preBindingSite")
    public String preBindingSite(Model model, Integer selectedOrganism,
            Integer organismId, Integer minNOfHMMs,
            String evalue, Integer bsSearchType,
            Integer id, String type, Integer currentJob) throws IOException {

        //selectedOrganism = organismId;
        System.out.println("--------------------------------");
        System.out.println("--------------- currentJob=" + currentJob);
        System.out.println("--------------- selectedOrganism: " + selectedOrganism);
        System.out.println("--------------- organism: " + organismId);
        System.out.println("--------------- Minimal number of Binding Sites: " + minNOfHMMs);
        System.out.println("--------------- e-value cutOff: " + evalue);
        System.out.println("--------------- bsSearchType: " + bsSearchType);
        System.out.println("--------------- GeneID: " + id + "\n\n");
        System.out.println("--------------- Type: " + type + "\n\n");

        if (id == null && currentJob == null) {
            return "index";
        }

        GeneDAO geneDAO = new GeneDAO();
        Gene gene;

        JobDAO jobDAO = new JobDAO();
        Job job = new Job();

        //its new
        if (currentJob == null) {
            Gene targetGene = geneDAO.findById(id);
            gene = targetGene;
            job.setStatus("new");
            job.setSelectedOrganism(selectedOrganism);
            job.setBsSearchType(bsSearchType);
            job.setEvalue(evalue);
            job.setMinHmm(minNOfHMMs);
            job.setGene(gene);
            job.setOrganismId(organismId);
            job.setType(type);
            job = (Job) jobDAO.save(job);

        } else {
            job = jobDAO.findById(currentJob);
            System.out.println("JOB: " + job);

            if (job.getSelectedOrganism() != null) {
                selectedOrganism = job.getSelectedOrganism();
            }
            organismId = job.getOrganismId();
            minNOfHMMs = job.getMinHmm();
            evalue = job.getEvalue();
            bsSearchType = job.getBsSearchType();
            gene = job.getGene();
            type = job.getType();

            System.out.println("--------------------------------");
            System.out.println("--------------- selectedOrganism: " + selectedOrganism);
            System.out.println("--------------- organism: " + organismId);
            System.out.println("--------------- Minimal number of Binding Sites: " + minNOfHMMs);
            System.out.println("--------------- e-value cutOff: " + evalue);
            System.out.println("--------------- bsSearchType: " + bsSearchType);
            System.out.println("--------------- GeneID: " + id + "\n\n");
            System.out.println("--------------- Type: " + type + "\n\n");

        }

        Integer jobNumber = job.getId();

        ArrayList<Organism> organisms = new ArrayList<>();
        ArrayList<BindingSiteRegulationPrediction> bsRegPredictions = new ArrayList<>();
        String transferenceFolder = System.getProperty("user.dir");
        SearchBindingSites searchBSites = new SearchBindingSites();
        Organism organism = new Organism();
        OrganismDAO organismDAO = new OrganismDAO();
        Object[] bsPredictedInfo = new Object[2];
        Integer usedHMMs = 0;
        int upStreamPos = 0;
        String upStreamRegion = "";
        HomologousDAO homolgDAO = new HomologousDAO();
        List<Homologous> homologs = new ArrayList<>();

        System.out.println("--------------------------------");
        System.out.println("--------------------------------");
        System.out.println("--------------------------------");
        //Search In the upstream sequence of this gene
        if (bsSearchType == 0) {
            if (selectedOrganism == 0) {
                System.out.println("bsSearchType == 0 | selectedOrganism == 0");
                organisms = (ArrayList<Organism>) organismDAO.listAll();
            } else {
                System.out.println("bsSearchType == 0 | selectedOrganism != 0");
                Organism organismAux = organismDAO.findById(selectedOrganism);
                organisms.add(organismAux);
            }

            GeneOperon geneOperon = new GeneOperon();
            GeneOperonDAO geneOperonDAO = new GeneOperonDAO();
            geneOperon = geneOperonDAO.findByGene(gene.getId());
            System.out.println("GENE op " + geneOperon);
            Operon operon = new Operon();
            OperonDAO operonDAO = new OperonDAO();
            if (geneOperon != null) {
                operon = operonDAO.findById(geneOperon.getGeneOperonPK().getOperon());
                geneOperon = (GeneOperon) geneOperonDAO.findFirstGeneOfOperon(operon.getId(), 1);
                System.out.println("==============================>>>>>>>>>>>>>>>>GeneOperon: " + geneOperon);
                gene = geneDAO.findById(geneOperon.getGeneOperonPK().getGene());
            } else {
                System.out.println("gene op é nulo!!");
            }

            //System.out.println("callHMMERThisGene");
            //bsPredictedInfo = searchBSites.callHMMERThisGene(transferenceFolder, targetGene, organisms, evalue, minNOfHMMs);
            //usedHMMs = (Integer) bsPredictedInfo[0];
            //bsRegPredictions = (ArrayList<BindingSiteRegulationPrediction>) bsPredictedInfo[1];
            if (gene != null && gene.getSearchSpace() != null) {
                upStreamPos = gene.getSearchSpace() - 20;
                upStreamRegion = "-" + upStreamPos + ".." + "20";
            } else {
                upStreamRegion = "No promoter region found!";
            }
        } else {
            System.out.println("callHMMERThisRegulator");
            if (gene.getBsNumber() == null) {
                System.out.println("It's nullllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");

            } else {
                //bsRegPredictions = searchBSites.callHMMERThisRegulator(transferenceFolder, gene, evalue, organismId);
                organism = organismDAO.findById(organismId);
            }

        }

        if (job.getStatus().equals("Ready")) {
            BsJobResultDAO jobResultDAO = new BsJobResultDAO();
            List<BsJobResult> results = new ArrayList<BsJobResult>();
            results = jobResultDAO.findByJob(job.getId());
            System.out.println("========================================");
            System.out.println("RESULTS: " + "----JOB: " + job.getId());

            BindingSiteRegulationPrediction bsPrediction = new BindingSiteRegulationPrediction();
            bsRegPredictions = new ArrayList<>();
            for (BsJobResult result : results) {
                System.out.println(result.toString());
                bsPrediction = new BindingSiteRegulationPrediction(result);
                bsRegPredictions.add(bsPrediction);
            }
            System.out.println("========================================");
            model.addAttribute("bsRegPredictions", bsRegPredictions);
            model.addAttribute("usedHMMs", job.getUsedHmms());
        }

        model.addAttribute("organisms", organisms);
        //m
        model.addAttribute("type", type);
        model.addAttribute("upStreamRegion", upStreamRegion);
        model.addAttribute("minNOfHMMs", minNOfHMMs);
        model.addAttribute("bsSearchType", bsSearchType);
        model.addAttribute("selectedOrganism", selectedOrganism);
        model.addAttribute("organismId", organismId);
        model.addAttribute("evalue", evalue);
        model.addAttribute("gene", gene);
        model.addAttribute("jobNumber", jobNumber);
        model.addAttribute("jobStatus", job.getStatus());
        //

        // System.out.println("--------------------------------");
        // System.out.println("--------------------------------");
        // System.out.println("--------------------------------");
        return "bindingSite";
    }

    @Async
    @RequestMapping("/predictBindingSite")
    @ResponseBody
    public void predictBindingSite(Integer jobNumber) throws IOException {

        GeneDAO geneDAO = new GeneDAO();

        Job job = new Job();
        JobDAO jobDAO = new JobDAO();
        job = jobDAO.findById(jobNumber);

        job.setStatus("In progress");
        jobDAO.update(job);
        System.out.println(job.toString());

        Integer selectedOrganism = job.getSelectedOrganism();
        Integer organismId = job.getOrganismId();
        Integer minNOfHMMs = job.getMinHmm();
        String evalue = job.getEvalue();
        Integer bsSearchType = job.getBsSearchType();
        Gene gene = job.getGene();
        String type = job.getType();

        System.out.println("--------------------------------");
        System.out.println("--------------- selectedOrganism: " + selectedOrganism);
        System.out.println("--------------- organism: " + organismId);
        System.out.println("--------------- Minimal number of Binding Sites: " + minNOfHMMs);
        System.out.println("--------------- e-value cutOff: " + evalue);
        System.out.println("--------------- bsSearchType: " + bsSearchType);
        System.out.println("--------------- GeneID: " + gene.getId() + "\n\n");
        System.out.println("--------------- Type: " + type + "\n\n");

        ArrayList<Organism> organisms = new ArrayList<>();
        ArrayList<BindingSiteRegulationPrediction> bsRegPredictions = new ArrayList<>();
        String transferenceFolder = System.getProperty("user.dir");
        SearchBindingSites searchBSites = new SearchBindingSites();
        Organism organism = new Organism();
        OrganismDAO organismDAO = new OrganismDAO();
        Object[] bsPredictedInfo = new Object[2];
        Integer usedHMMs = 0;
        int upStreamPos = 0;
        String upStreamRegion = "";
        HomologousDAO homolgDAO = new HomologousDAO();
        List<Homologous> homologs = new ArrayList<>();

        System.out.println("--------------------------------");
        System.out.println("--------------------------------");
        System.out.println("--------------------------------");
        //Search In the upstream sequence of this gene
        if (bsSearchType == 0) {
            if (selectedOrganism == 0) {
                System.out.println("bsSearchType == 0 | selectedOrganism == 0");
                organisms = (ArrayList<Organism>) organismDAO.listAll();
            } else {
                System.out.println("bsSearchType == 0 | selectedOrganism != 0");
                Organism organismAux = organismDAO.findById(selectedOrganism);
                organisms.add(organismAux);
            }

            GeneOperon geneOperon = new GeneOperon();
            GeneOperonDAO geneOperonDAO = new GeneOperonDAO();
            geneOperon = geneOperonDAO.findByGene(gene.getId());
            System.out.println("GENE op " + geneOperon);
            Operon operon = new Operon();
            OperonDAO operonDAO = new OperonDAO();
            if (geneOperon != null) {
                operon = operonDAO.findById(geneOperon.getGeneOperonPK().getOperon());
                geneOperon = (GeneOperon) geneOperonDAO.findFirstGeneOfOperon(operon.getId(), 1);
                System.out.println("==============================>>>>>>>>>>>>>>>>GeneOperon: " + geneOperon);
                gene = geneDAO.findById(geneOperon.getGeneOperonPK().getGene());
            } else {
                System.out.println("gene op é nulo!!");
            }

            System.out.println("callHMMERThisGene");
            bsPredictedInfo = searchBSites.callHMMERThisGene(transferenceFolder, gene, organisms, evalue, minNOfHMMs);
            usedHMMs = (Integer) bsPredictedInfo[0];
            bsRegPredictions = (ArrayList<BindingSiteRegulationPrediction>) bsPredictedInfo[1];
            if (gene != null && gene.getSearchSpace() != null) {
                upStreamPos = gene.getSearchSpace() - 20;
                upStreamRegion = "-" + upStreamPos + ".." + "20";
            } else {
                upStreamRegion = "No promoter region found!";
            }
        } else {
            System.out.println("callHMMERThisRegulator");
            if (gene.getBsNumber() == null) {
                System.out.println("It's nullllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll");

            } else {
                bsRegPredictions = searchBSites.callHMMERThisRegulator(transferenceFolder, gene, evalue, organismId);
                organism = organismDAO.findById(organismId);
            }

        }

        BsJobResult jobResult = new BsJobResult();
        BsJobResultDAO jobResultDAO = new BsJobResultDAO();
        List<BsJobResult> results = new ArrayList<BsJobResult>();

        System.out.println("=====================BindingSiteRegulationPredictions: ");
        for (BindingSiteRegulationPrediction bsRegPrediction : bsRegPredictions) {
            System.out.println(bsRegPrediction.toString());
            jobResult = new BsJobResult(bsRegPrediction);
            jobResult.setJob(job);
            jobResultDAO.save(jobResult);
            results.add(jobResult);
        }

        System.out.println("==============================\n\n");
        System.out.println("=====================results: ");
        for (BsJobResult result : results) {
            System.out.println(result.toString());
        }
        System.out.println("==============================");

        job.setStatus("Ready");
        job.setUsedHmms(usedHMMs);
        jobDAO.update(job);
        System.out.println("ready");
    }

    @RequestMapping("databaseStatistics")
    public String databaseStatistics(Model model, String type) {

        return "databaseStatistics";
    }

    @RequestMapping("statistics")
    public String statistics(Model model, String type) {

        if (type == null) {
            return "databaseStatistics";
        }

        GeneDAO geneDAO = new GeneDAO();
        Integer totalNumOfTfs;
        Integer numOfActivators;
        Integer numOfRepressors;
        Integer numOfDuals;
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
//        List<Gene> genes = riDAO.findByDistinctTG();
//        List<Gene> pGenes = priDAO.findByDistinctTG();
        TreeMap<Integer, Integer> tfsRegAGene = new TreeMap<>();
        TreeMap<Integer, Integer> numOfCoregulators = new TreeMap<>();
        TreeMap<Integer, Integer> hmmProfilesLen = new TreeMap<>();
        //  Long numTfs = new Long(0);
        // Integer numTgs = 0;
        // List<Gene> tfGenes = new ArrayList<>();
        // Long numOfGeneCoregulators = new Long(0);
        //  Integer hmmLength = 0;
        //  Integer freqHmmLen = 0;
        //  BindingSiteDAO bsDAO = new BindingSiteDAO();
        //  TreeMap<Integer, Integer> distancesFromStartSite = new TreeMap<>();
        ArrayList<BindingSite> bss = new ArrayList<>();
        //  ArrayList<PredictedRegulatoryInteraction> pris = new ArrayList<>();
        //  int distance;
        RegulatorsRegulations regulatorsRegulation = new RegulatorsRegulations();
        RegulatorsRegulationsDAO rrDAO = new RegulatorsRegulationsDAO();
        ArrayList<TfsRegulating> tfsReg = new ArrayList<>();
        TfsRegulatingDAO tfsRegDAO = new TfsRegulatingDAO();
        ArrayList<CoregulatorsStatistics> coRegAGene = new ArrayList<>();
        CoregulatorsStatisticsDAO csDAO = new CoregulatorsStatisticsDAO();

        //Quantities
        regulatorsRegulation = rrDAO.findByTypeAndDatabase("database", type);
        ArrayList<RegulatorsRegulations> reTest = (ArrayList<RegulatorsRegulations>) rrDAO.listAll();
        System.out.println(reTest.size());
        for (int i = 0; i < reTest.size(); i++) {
            if (reTest.get(i).getDatabase().equals("experimental") && reTest.get(i).getGenome() != null) {
                System.out.println(reTest.get(i).getGenome().getOrganism().getGenera() + " " + reTest.get(i).getGenome().getOrganism().getSpecies() + " " + reTest.get(i).getGenome().getOrganism().getStrain());
            }
        }
        System.out.println(regulatorsRegulation);
        if (regulatorsRegulation != null) {
            totalNumOfTfs = regulatorsRegulation.getNumActivators() + regulatorsRegulation.getNumDual() + regulatorsRegulation.getNumRepressors();
            numOfActivators = regulatorsRegulation.getNumActivators();
            numOfDuals = regulatorsRegulation.getNumDual();
            numOfRepressors = regulatorsRegulation.getNumRepressors();
        } else {
            totalNumOfTfs = 0;
            numOfActivators = 0;
            numOfDuals = 0;
            numOfRepressors = 0;
        }

        //tfsRegAGene
        tfsReg = (ArrayList<TfsRegulating>) tfsRegDAO.findByTypeAndDatabase("database", type);
        for (TfsRegulating tfsRegulating : tfsReg) {
            tfsRegAGene.put(tfsRegulating.getNumTf(), tfsRegulating.getNumTg());
        }

        //coRegAGene
        coRegAGene = (ArrayList<CoregulatorsStatistics>) csDAO.findByTypeAndDatabase("database", type);
        Integer coRegRange = 0;
        Integer numOfTFs = 0;
        for (CoregulatorsStatistics coRAG : coRegAGene) {
            if (coRAG.getNumCoregulators() < (coRegRange + 1) * 10) {
                numOfTFs += coRAG.getNumTfs();
            } else if (coRegRange == 7) {
                numOfTFs += coRAG.getNumTfs();
            } else {
                numOfCoregulators.put(coRegRange, numOfTFs);
                System.out.println("numOfTFs: " + numOfTFs);
                numOfTFs = coRAG.getNumTfs();
                if (coRAG.getNumCoregulators() < 20) {
                    coRegRange = 1;
                } else if (coRAG.getNumCoregulators() < 30) {
                    coRegRange = 2;
                } else if (coRAG.getNumCoregulators() < 40) {
                    coRegRange = 3;
                } else if (coRAG.getNumCoregulators() < 50) {
                    coRegRange = 4;
                } else if (coRAG.getNumCoregulators() < 60) {
                    coRegRange = 5;
                } else if (coRAG.getNumCoregulators() < 70) {
                    coRegRange = 6;
                } else {
                    coRegRange = 7;
                }
//                System.out.println("");
//                System.out.println("-----");
//                System.out.println("coRegRange: " + coRegRange);
            }
            //numOfCoregulators.put(coRAG.getNumCoregulators(), coRAG.getNumTfs());
        }

        numOfCoregulators.put(coRegRange, numOfTFs);
        //System.out.println("numOfTFs: " + numOfTFs);

//        for (Map.Entry<Integer, Integer> entry : numOfCoregulators.entrySet()) {
//            Integer key = entry.getKey();
//            Integer value = entry.getValue();
//
//            System.out.println(key + " => " + value);
//        }
//HmmLen        
        ArrayList<HmmProfilesLengths> hmmLens = new ArrayList<>();
        HmmProfilesLengthsDAO hmmDAO = new HmmProfilesLengthsDAO();

        hmmLens = (ArrayList<HmmProfilesLengths>) hmmDAO.findByTypeAndDatabase("database", type);
        for (HmmProfilesLengths hmmLen : hmmLens) {
            hmmProfilesLen.put(hmmLen.getProfileLengthRange(), hmmLen.getFrequency());
        }

        ///////////////////-------------------------
        //model.addAttribute("distancesFromStartSite", distancesFromStartSite);
        model.addAttribute("numOfCoregulators", numOfCoregulators);
        model.addAttribute("tfsRegAGene", tfsRegAGene);
        model.addAttribute("numOfActivators", numOfActivators);
        model.addAttribute("numOfRepressors", numOfRepressors);
        model.addAttribute("numOfDuals", numOfDuals);
        model.addAttribute("totalNumOfTfs", totalNumOfTfs);
        model.addAttribute("type", type);
        model.addAttribute("hmmProfilesLen", hmmProfilesLen);
        return "statistics";
    }

    @RequestMapping("quantitiesOfRegulatorAndRegulationTypes")
    public String quantitiesOfRegulatorAndRegulationTypes(Model model, String type) {

        if (type == null) {
            return "databaseStatistics";
        }

//        Integer numOfActivators;
//        Integer numOfRepressors;
//        Integer numOfDuals;
//        Integer activations;
//        Integer repressions;
        Integer numOfTfs;
        Integer numOfRis;
        LinkedHashMap<String, ArrayList<Integer>> organismsRegulators = new LinkedHashMap<>();
        LinkedHashMap<String, ArrayList<Integer>> organismsRegulations = new LinkedHashMap<>();
        ArrayList<Integer> regulatorsArray = new ArrayList<>();
        ArrayList<Organism> organisms = new ArrayList<>();
        LinkedHashMap<String, Integer> organismsId = new LinkedHashMap<>();
        OrganismDAO organismDAO = new OrganismDAO();
        RegulatorsRegulationsDAO rrDAO = new RegulatorsRegulationsDAO();
        RegulatorsRegulations regulatorsRegulation = new RegulatorsRegulations();
//        for (int i = 0; i < tfsRegAGeneModel.size(); i++) {
//            System.out.println("tfsRegAGeneModel.get(i): " + tfsRegAGeneModel.get(i));
//        }

        if (type.equals("experimental")) {
            organisms = (ArrayList<Organism>) organismDAO.findByType("model");
        } else {
            organisms = (ArrayList<Organism>) organismDAO.listAll();
        }

        regulatorsRegulation = rrDAO.findByTypeAndDatabase("database", type);
        if (regulatorsRegulation != null) {
            numOfTfs = regulatorsRegulation.getNumActivators() + regulatorsRegulation.getNumDual() + regulatorsRegulation.getNumRepressors();
            regulatorsArray.add(regulatorsRegulation.getNumActivators());
            regulatorsArray.add(regulatorsRegulation.getNumDual());
            regulatorsArray.add(regulatorsRegulation.getNumRepressors());
            regulatorsArray.add(numOfTfs);
            organismsRegulators.put("all", regulatorsArray);
            regulatorsArray = new ArrayList<>();

            numOfRis = regulatorsRegulation.getNumActivations() + regulatorsRegulation.getNumRepressions();
            regulatorsArray.add(regulatorsRegulation.getNumActivations());
            regulatorsArray.add(regulatorsRegulation.getNumRepressions());
            regulatorsArray.add(numOfRis);
            organismsRegulations.put("all", regulatorsArray);
            //            numOfRis = regulatorsRegulation.getNumActivations() + regulatorsRegulation.getNumRepressions();
            //            regulatorsArray.add(regulatorsRegulation.getNumActivations());
            //            regulatorsArray.add(regulatorsRegulation.getNumRepressions());
            //            regulatorsArray.add(numOfRis);
            //            organismsRegulations.put("all", regulatorsArray);
            //            regulatorsArray = new ArrayList<>();
        }

        //Retrive the role of each TF
        if (organisms != null) {
            for (int i = 0; i < organisms.size(); i++) {
                regulatorsArray = new ArrayList<>();

                regulatorsRegulation = rrDAO.findByOrganism(organisms.get(i).getId());
                if (regulatorsRegulation != null) {
                    organismsId.put(organisms.get(i).getGenera() + " " + organisms.get(i).getSpecies() + " " + organisms.get(i).getStrain(), organisms.get(i).getId());
                    numOfTfs = regulatorsRegulation.getNumActivators() + regulatorsRegulation.getNumDual() + regulatorsRegulation.getNumRepressors();
                    regulatorsArray.add(regulatorsRegulation.getNumActivators());
                    regulatorsArray.add(regulatorsRegulation.getNumDual());
                    regulatorsArray.add(regulatorsRegulation.getNumRepressors());
                    regulatorsArray.add(numOfTfs);
                    organismsRegulators.put(organisms.get(i).getGenera() + " " + organisms.get(i).getSpecies()
                            + " " + organisms.get(i).getStrain(), regulatorsArray);
                    regulatorsArray = new ArrayList<>();

                    numOfRis = regulatorsRegulation.getNumActivations() + regulatorsRegulation.getNumRepressions();
                    regulatorsArray.add(regulatorsRegulation.getNumActivations());
                    regulatorsArray.add(regulatorsRegulation.getNumRepressions());
                    regulatorsArray.add(numOfRis);
                    organismsRegulations.put(organisms.get(i).getGenera() + " " + organisms.get(i).getSpecies()
                            + " " + organisms.get(i).getStrain(), regulatorsArray);
                }
            }
        }

//        System.out.println(organismsRegulators);
//        System.out.println(organismsRegulations);
//  model.addAttribute("testString", "blablabla");
        model.addAttribute("organismsRegulators", organismsRegulators);
        model.addAttribute("organismsRegulations", organismsRegulations);
        model.addAttribute("organismsId", organismsId);
        model.addAttribute("type", type);
        return "quantitiesOfRegulatorAndRegulationTypes";
    }

    @RequestMapping("tFsRegulatingAGene")
    public String tFsRegulatingAGene(Model model, String type) throws InterruptedException {

        if (type == null) {
            return "databaseStatistics";
        }

        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
//        List<Gene> genes = riDAO.findByDistinctTG();
//        List<Gene> pGenes = priDAO.findByDistinctTG();
        LinkedHashMap<String, TreeMap<Integer, Integer>> tfsRegAGene = new LinkedHashMap<>();
        TreeMap<Integer, Integer> tfsRegAGeneAux = new TreeMap<>();
        ArrayList<Organism> organisms = new ArrayList<>();
        LinkedHashMap<String, Integer> organismsId = new LinkedHashMap<>();
        OrganismDAO organismDAO = new OrganismDAO();
        Long numTfs = new Long(0);
        Integer numTgs = 0;
        ArrayList<TfsRegulating> tfsReg = new ArrayList<>();
        TfsRegulatingDAO tfsRegDAO = new TfsRegulatingDAO();

        if (type.equals("experimental")) {
            organisms = (ArrayList<Organism>) organismDAO.findByType("model");
//            System.out.println("Before search");
//            tfsReg = (ArrayList<TfsRegulating>) tfsRegDAO.findByTypeAndDatabase("database", type);
//            System.out.println("After search");
//            System.out.println(tfsReg);
//            for (TfsRegulating tfsRegulating : tfsReg) {
//                tfsRegAGeneAux.put(tfsRegulating.getNumTf(), tfsRegulating.getNumTg());
//            }
//            tfsRegAGene.put("all", tfsRegAGeneAux);
        } else {
            organisms = (ArrayList<Organism>) organismDAO.listAll();
        }
        tfsReg = (ArrayList<TfsRegulating>) tfsRegDAO.findByTypeAndDatabase("database", type);
        for (TfsRegulating tfsRegulating : tfsReg) {
            tfsRegAGeneAux.put(tfsRegulating.getNumTf(), tfsRegulating.getNumTg());
        }
        tfsRegAGene.put("all", tfsRegAGeneAux);

        if (organisms != null) {
            for (int i = 0; i < organisms.size(); i++) {
                tfsRegAGeneAux = new TreeMap<>();
                tfsReg = new ArrayList<>();
                tfsReg = (ArrayList<TfsRegulating>) tfsRegDAO.findByOrganism(organisms.get(i).getId());
                if (!tfsReg.isEmpty()) {
                    organismsId.put(organisms.get(i).getGenera() + " " + organisms.get(i).getSpecies() + " " + organisms.get(i).getStrain(), organisms.get(i).getId());
                    for (TfsRegulating tfsRegulating : tfsReg) {
                        tfsRegAGeneAux.put(tfsRegulating.getNumTf(), tfsRegulating.getNumTg());
                    }
                    tfsRegAGene.put(organisms.get(i).getGenera() + " " + organisms.get(i).getSpecies()
                            + " " + organisms.get(i).getStrain(), tfsRegAGeneAux);
                }
            }
        }

//            System.out.println(tfsRegAGene.get("all"));
//        for (Organism organism : organisms) {
//            System.out.println(tfsRegAGene.get(organism.getGenera() + " " + organism.getSpecies()
//                    + " " + organism.getStrain()));
//        }
        //System.out.println(tfsRegAGene);
        model.addAttribute("tfsRegAGene", tfsRegAGene);
        model.addAttribute("organismsId", organismsId);
        model.addAttribute("type", type);
        return "tFsRegulatingAGene";
    }

    @RequestMapping("coregulatorsStatistics")
    public String coregulatorsStatistics(Model model, String type) {

        if (type == null) {
            return "databaseStatistics";
        }

        ArrayList<Organism> organisms = new ArrayList<>();
        LinkedHashMap<String, Integer> organismsId = new LinkedHashMap<>();
        OrganismDAO organismDAO = new OrganismDAO();
        LinkedHashMap<String, TreeMap<Integer, Integer>> numOfCoregulators = new LinkedHashMap<>();
        Long numOfGeneCoregulators = new Long(0);
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
//        List<Gene> genes = riDAO.findByDistinctTG();
//        List<Gene> pGenes = priDAO.findByDistinctTG();
//        List<Gene> tfGenes = new ArrayList<>();
//        List<Gene> coregulators = new ArrayList<>();
//        List<Gene> coregulatorsOfTF = new ArrayList<>();
        TreeMap<Integer, Integer> allCoregulatorsOfOrganism = new TreeMap<>();
        ArrayList<CoregulatorsStatistics> coRegAGene = new ArrayList<>();
        CoregulatorsStatisticsDAO csDAO = new CoregulatorsStatisticsDAO();

        if (type.equals("experimental")) {
            organisms = (ArrayList<Organism>) organismDAO.findByType("model");
//            System.out.println("Before search");
//            coRegAGene = (ArrayList<CoregulatorsStatistics>) csDAO.findByTypeAndDatabase("database", type);
//            System.out.println("After search");
//            System.out.println(coRegAGene);
//            for (CoregulatorsStatistics coRAG : coRegAGene) {
//                allCoregulatorsOfOrganism.put(coRAG.getNumCoregulators(), coRAG.getNumTfs());
//            }
//            numOfCoregulators.put("all", allCoregulatorsOfOrganism);
        } else {
            organisms = (ArrayList<Organism>) organismDAO.listAll();
        }
        coRegAGene = (ArrayList<CoregulatorsStatistics>) csDAO.findByTypeAndDatabase("database", type);
        Integer coRegRange = 0;
        Integer numOfTFs = 0;
        for (CoregulatorsStatistics coRAG : coRegAGene) {

            if (coRAG.getNumCoregulators() < (coRegRange + 1) * 10) {
                numOfTFs += coRAG.getNumTfs();
            } else if (coRegRange == 7) {
                numOfTFs += coRAG.getNumTfs();
            } else {
                allCoregulatorsOfOrganism.put(coRegRange, numOfTFs);
                //System.out.println("numOfTFs: " + numOfTFs);
                numOfTFs = coRAG.getNumTfs();
                if (coRAG.getNumCoregulators() < 20) {
                    coRegRange = 1;
                } else if (coRAG.getNumCoregulators() < 30) {
                    coRegRange = 2;
                } else if (coRAG.getNumCoregulators() < 40) {
                    coRegRange = 3;
                } else if (coRAG.getNumCoregulators() < 50) {
                    coRegRange = 4;
                } else if (coRAG.getNumCoregulators() < 60) {
                    coRegRange = 5;
                } else if (coRAG.getNumCoregulators() < 70) {
                    coRegRange = 6;
                } else {
                    coRegRange = 7;
                }
//                System.out.println("");
//                System.out.println("-----");
//                System.out.println("coRegRange: " + coRegRange);
            }
        }
        //numOfCoregulators.put(coRAG.getNumCoregulators(), coRAG.getNumTfs());
        allCoregulatorsOfOrganism.put(coRegRange, numOfTFs);

        numOfCoregulators.put("all", allCoregulatorsOfOrganism);

        if (organisms != null) {
            for (int i = 0; i < organisms.size(); i++) {
                allCoregulatorsOfOrganism = new TreeMap<>();
                coRegAGene = new ArrayList<>();
                coRegAGene = (ArrayList<CoregulatorsStatistics>) csDAO.findByOrganism(organisms.get(i).getId());
                coRegRange = 0;
                if (!coRegAGene.isEmpty()) {
                    //System.out.println("coRegAGene is not empty");
                    organismsId.put(organisms.get(i).getGenera() + " " + organisms.get(i).getSpecies() + " " + organisms.get(i).getStrain(), organisms.get(i).getId());
                    for (CoregulatorsStatistics coRAG : coRegAGene) {
                        //System.out.println("coRAG: " + coRAG);
                        if (coRAG.getNumCoregulators() < (coRegRange + 1) * 10) {
                            numOfTFs += coRAG.getNumTfs();
                        } else if (coRegRange == 7) {
                            numOfTFs += coRAG.getNumTfs();
                        } else {
                            allCoregulatorsOfOrganism.put(coRegRange, numOfTFs);
                            //System.out.println("numOfTFs: " + numOfTFs);
                            numOfTFs = coRAG.getNumTfs();
                            if (coRAG.getNumCoregulators() < 20) {
                                coRegRange = 1;
                            } else if (coRAG.getNumCoregulators() < 30) {
                                coRegRange = 2;
                            } else if (coRAG.getNumCoregulators() < 40) {
                                coRegRange = 3;
                            } else if (coRAG.getNumCoregulators() < 50) {
                                coRegRange = 4;
                            } else if (coRAG.getNumCoregulators() < 60) {
                                coRegRange = 5;
                            } else if (coRAG.getNumCoregulators() < 70) {
                                coRegRange = 6;
                            } else {
                                coRegRange = 7;
                            }
                        }
                    }
                }
                allCoregulatorsOfOrganism.put(coRegRange, numOfTFs);
                numOfCoregulators.put(organisms.get(i).getGenera() + " " + organisms.get(i).getSpecies()
                        + " " + organisms.get(i).getStrain(), allCoregulatorsOfOrganism);
            }
        }
        //System.out.println(numOfCoregulators);
        model.addAttribute("numOfCoregulators", numOfCoregulators);
        model.addAttribute("organismsId", organismsId);
        model.addAttribute("type", type);
        return "coregulatorsStatistics";
    }

    public TreeMap distanceFromStartSite(TreeMap<Integer, Integer> distancesFromStartSite, Integer distance) {

        Integer freq;
        Integer range;
        if (distance < 38) {
            range = 0;
        } else if (distance < 96) {
            range = 1;
        } else if (distance < 154) {
            range = 2;
        } else if (distance < 212) {
            range = 3;
        } else if (distance < 270) {
            range = 4;
        } else if (distance < 328) {
            range = 5;
        } else if (distance < 386) {
            range = 6;
        } else if (distance < 444) {
            range = 7;
        } else if (distance < 502) {
            range = 8;
        } else {
            range = 9;
        }

        if (!distancesFromStartSite.isEmpty() && distancesFromStartSite.containsKey(range)) {
            freq = distancesFromStartSite.get(range) + 1;
        } else {
            freq = 1;
        }

        distancesFromStartSite.put(range, freq);

        return distancesFromStartSite;
    }

    public TreeMap hmmProfLenObject(TreeMap<Integer, Integer> hmmProfilesLenOrganism, int hmmLength) {

        Integer freqHmmLen;
        Integer range;
        if (hmmLength < 13) {
            range = 0;
        } else if (hmmLength < 21) {
            range = 1;
        } else if (hmmLength < 29) {
            range = 2;
        } else if (hmmLength < 37) {
            range = 3;
        } else if (hmmLength < 46) {
            range = 4;
        } else if (hmmLength < 60) {
            range = 5;
        } else {
            range = 6;
            //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            //System.out.println("This is bigger (hmmLength): " + hmmLength);
        }

        if (!hmmProfilesLenOrganism.isEmpty() && hmmProfilesLenOrganism.containsKey(range)) {
            freqHmmLen = hmmProfilesLenOrganism.get(range) + 1;
        } else {
            freqHmmLen = 1;
        }

        hmmProfilesLenOrganism.put(range, freqHmmLen);

        return hmmProfilesLenOrganism;
    }

    @RequestMapping("hmmProfLenStatistics")
    public String hmmProfLenStatistics(Model model, String type) {

        if (type == null) {
            return "databaseStatistics";
        }

        ArrayList<Organism> organisms = new ArrayList<>();
        LinkedHashMap<String, Integer> organismsId = new LinkedHashMap<>();
        OrganismDAO organismDAO = new OrganismDAO();
        LinkedHashMap<String, TreeMap<Integer, Integer>> hmmProfilesLen = new LinkedHashMap<>();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        List<Gene> tfGenes = new ArrayList<>();
        TreeMap<Integer, Integer> hmmProfilesLenOrganism = new TreeMap<>();
        Integer hmmLength = 0;
        ArrayList<HmmProfilesLengths> hmmLens = new ArrayList<>();
        HmmProfilesLengthsDAO hmmDAO = new HmmProfilesLengthsDAO();

        if (type.equals("experimental")) {
            organisms = (ArrayList<Organism>) organismDAO.findByType("model");
//            System.out.println("Before search");
//            hmmLens = (ArrayList<HmmProfilesLengths>) hmmDAO.findByTypeAndDatabase("database", type);
//            System.out.println("After search");
//            System.out.println(hmmLens);
//            for (HmmProfilesLengths hmmLen : hmmLens) {
//                hmmProfilesLenOrganism.put(hmmLen.getProfileLengthRange(), hmmLen.getFrequency());
//            }
//            hmmProfilesLen.put("all", hmmProfilesLenOrganism);
        } else {
            organisms = (ArrayList<Organism>) organismDAO.listAll();
        }
        hmmLens = (ArrayList<HmmProfilesLengths>) hmmDAO.findByTypeAndDatabase("database", type);
        for (HmmProfilesLengths hmmLen : hmmLens) {
            hmmProfilesLenOrganism.put(hmmLen.getProfileLengthRange(), hmmLen.getFrequency());
        }
        hmmProfilesLen.put("all", hmmProfilesLenOrganism);

        if (organisms != null) {
            for (int i = 0; i < organisms.size(); i++) {
                hmmProfilesLenOrganism = new TreeMap<>();
                hmmLens = new ArrayList<>();
                hmmLens = (ArrayList<HmmProfilesLengths>) hmmDAO.findByOrganism(organisms.get(i).getId());
                if (!hmmLens.isEmpty()) {
                    organismsId.put(organisms.get(i).getGenera() + " " + organisms.get(i).getSpecies() + " " + organisms.get(i).getStrain(), organisms.get(i).getId());
                    for (HmmProfilesLengths hmmLen : hmmLens) {
                        hmmProfilesLenOrganism.put(hmmLen.getProfileLengthRange(), hmmLen.getFrequency());
                    }
                    hmmProfilesLen.put(organisms.get(i).getGenera() + " " + organisms.get(i).getSpecies()
                            + " " + organisms.get(i).getStrain(), hmmProfilesLenOrganism);
                }
            }
        }
        model.addAttribute("hmmProfilesLen", hmmProfilesLen);
        model.addAttribute("organismsId", organismsId);
        model.addAttribute("type", type);
        return "hmmProfLenStatistics";
    }

}
