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
import com.coryneregnet7.dao.GenesRegulatedBySrnasViewDAO;
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
import com.coryneregnet7.dao.RnaCoregulatingViewDAO;
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
import com.coryneregnet7.model.GenesRegulatedBySrnasView;
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
import com.coryneregnet7.model.RnaCoregulatingView;
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
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
        List<Object[]> genomesRna = genomeDAO.findByOrganismTypeHashRna("experimental");
        //findByOrganismTypeHashRna

        List<OrganismItem> items = bringItems(genomes);
        List<OrganismItem> itemsRna = bringItems(genomesRna);

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

        ///itemsRna
        model.addAttribute("numberOfHmmProfiles", numberOfHmmProfiles);
        model.addAttribute("numberOfBindingSites", numberOfBindingSites);
        model.addAttribute("totalNumberOfRegulatedGenes", numberOfRegulatedGenes);
        model.addAttribute("totalNumberOfRegulators", numberOfRegulators);
        model.addAttribute("totalNumberOfRegulatoryInteractions", numberOfRegulatoryInteractions);
        model.addAttribute("numberOfProteins", numberOfProteins);
        model.addAttribute("numberOfGenes", numberOfGenes);
        model.addAttribute("numberOfGenomes", numberOfGenomes);
        //   model.addAttribute("organisms", organisms);
        model.addAttribute("items", items);
        model.addAttribute("itemsRna", itemsRna);
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
        List<Object[]> genomesRna = genomeDAO.findByOrganismTypeHashRna("experimental");
        //findByOrganismTypeHashRna

        List<OrganismItem> items = bringItems(genomes);
        List<OrganismItem> itemsRna = bringItems(genomesRna);

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
            numberOfGenesByRna, numberOfRegulatoryRnas, numberOfRegulationsSrna, itemsRna};

        return returnObj;
    }

    @RequestMapping("searchPredictedData")
    public String searchPredictedData(Model model) {
        //  model.addAttribute("testString", "blablabla");
        System.out.println("***********************Predicted DATA");
        System.out.println("==================================================================================================");

        OrganismDAO organismDAO = new OrganismDAO();
        GenomeDAO genomeDAO = new GenomeDAO();
        List<Object[]> genomes = genomeDAO.findAllHash();
        List<Object[]> genomesRna = genomeDAO.findByOrganismNotTypeHashRna("experimental");
        //findByOrganismTypeHashRna

        List<OrganismItem> items = bringItems(genomes);
        List<OrganismItem> itemsRna = bringItems(genomesRna);

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
        // model.addAttribute("organisms", organisms);
        model.addAttribute("items", items);
        model.addAttribute("itemsRna", itemsRna);
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
        List<Object[]> genomesRna = genomeDAO.findByOrganismNotTypeHashRna("experimental");
        //findByOrganismTypeHashRna

        List<OrganismItem> items = bringItems(genomes);
        List<OrganismItem> itemsRna = bringItems(genomesRna);

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
            numberOfGenesByRna, numberOfRegulatoryRnas, numberOfRegulationsSrna, itemsRna};

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
    public String dataSearch(Model model, Integer organism, String gene, String searchType, String geneRna,
            String geneListSelect, String srnaListSelect, Integer organismRna, String organismSearchRna) throws InterruptedException {

        //String organismSearchRna
        System.out.println("geneListSelect " + geneListSelect);
        System.out.println("rnaListSelect " + srnaListSelect);

        if (geneListSelect != null && !geneListSelect.equals("wildcards")) {
            gene = geneListSelect;
        }

        if (srnaListSelect != null && !srnaListSelect.equals("wildcards")) {
            gene = srnaListSelect;

        }

        if (gene == null) {
            return "index";
        }

        gene = gene.trim();
        if (gene.contains(" ")) {
            int position = gene.indexOf(" ");
            gene = gene.substring(0, position);
        }

        if (gene.equals("all")) {
            gene = "";
        }

        System.out.println("TF-RNA: " + geneRna);
        if (searchType == null) {
            return "index";
        }

        organism = Integer.parseInt(organism.toString());
        System.out.println("Organism: " + organism);
        System.out.println("OrganismRNA: " + organismRna);

        System.out.println("Gene: " + gene);
        System.out.println("searchType " + searchType);

        GeneDAO geneDAO = new GeneDAO();
        List<Gene> genes = new ArrayList<>();
        GeneOperonDAO goDAO = new GeneOperonDAO();
        GenomeDAO genomeDAO = new GenomeDAO();
        Genome genome = new Genome();

        if (geneRna.equals("rna")) {
            organism = organismRna;
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
                System.out.println("organism " + organism);
                genome = genomeDAO.findByOrganism(organism);
                System.out.println("genome " + genome);
                if (gene.isEmpty()) {
                    //search by genome and type. 

                    if (searchType.equals("predicted")) {

                        rnaTableView = rnaTableViewDAO.findByGenome(genome.getId());
                    } else {
                        rnaTableView = rnaTableViewDAO.findByGenomeType(genome.getId(), "experimental");
                    }
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
                    model.addAttribute("itemsRna", (List<OrganismItem>) features[13]);

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
                        System.out.println("Genes is null!!!!!!!!!!!!!");

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

                        model.addAttribute("numberOfSmallRnas", features[9]);
                        model.addAttribute("numberOfSmallRnas", features[10]);
                        model.addAttribute("numberOfSmallRnas", features[11]);
                        model.addAttribute("numberOfSmallRnas", features[12]);
                        model.addAttribute("itemsRna", (List<OrganismItem>) features[13]);

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
                        model.addAttribute("numberOfSmallRnas", features[9]);
                        model.addAttribute("numberOfSmallRnas", features[10]);
                        model.addAttribute("numberOfSmallRnas", features[11]);
                        model.addAttribute("numberOfSmallRnas", features[12]);
                        model.addAttribute("itemsRna", (List<OrganismItem>) features[13]);
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
                    model.addAttribute("numberOfSmallRnas", features[9]);
                    model.addAttribute("numberOfSmallRnas", features[10]);
                    model.addAttribute("numberOfSmallRnas", features[11]);
                    model.addAttribute("numberOfSmallRnas", features[12]);
                    model.addAttribute("itemsRna", (List<OrganismItem>) features[13]);
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
                        model.addAttribute("numberOfSmallRnas", features[9]);
                        model.addAttribute("numberOfSmallRnas", features[10]);
                        model.addAttribute("numberOfSmallRnas", features[11]);
                        model.addAttribute("numberOfSmallRnas", features[12]);
                        model.addAttribute("itemsRna", (List<OrganismItem>) features[13]);
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
                        model.addAttribute("numberOfSmallRnas", features[9]);
                        model.addAttribute("numberOfSmallRnas", features[10]);
                        model.addAttribute("numberOfSmallRnas", features[11]);
                        model.addAttribute("numberOfSmallRnas", features[12]);
                        model.addAttribute("itemsRna", (List<OrganismItem>) features[13]);
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
                    model.addAttribute("itemsRna", (List<OrganismItem>) features[9]);
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
                    model.addAttribute("itemsRna", (List<OrganismItem>) features[9]);
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
    public String whichNetwork(Model model, Integer organism, Integer organismRna, String searchType,
            String gene, String goBackTo, String geneListSelect, String srnaListSelect,
            String layoutType, String geneRna) throws InterruptedException {

        if (searchType == null) {
            return "index";
        }

        System.out.println("GRN:");
        System.out.println("--------------- Organism: " + organism);
        System.out.println("--------------- OrganismRna: " + organismRna);
        System.out.println("--------------- SearchType: " + searchType);
        System.out.println("--------------- Gene: " + gene);
        System.out.println("--------------- geneListSelect: " + geneListSelect);
        System.out.println("--------------- srnaListSelect: " + srnaListSelect);
        //System.out.println("Gooooooooooooooo networkVisualization!!!!!!!!!!!!!!!!!!!!!!!");
        //System.out.println("gene: " + gene);
        //System.out.println("goBackTo: " + goBackTo);
        //System.out.println("layoutType: " + layoutType);
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
        String entireNetwork = "false";

        RnaRegulationViewDAO rnaRegDAO = new RnaRegulationViewDAO();
        List<RnaRegulationView> rnaRegList = new LinkedList<>();

        if (geneRna.equals("rna")) {
            o = organismDAO.findById(organismRna);
            if (gene.isEmpty()) {
                gene = srnaListSelect.trim();
                //System.out.println("GENE:" + gene + "-----");
            }

        } else {
            if (gene.isEmpty()) {
                gene = geneListSelect.trim();
                //System.out.println("GENE:" + gene + "-----");
            }
        }

        if (gene.equals("all")) {
            gene = "";
        }

        //System.out.println("---1----");
        if (gene == null || gene.isEmpty()) {
            entireNetwork = "true";
            System.out.println("Gene is empty!!! -->> Experimental + Predicted data");

            //System.out.println("---2----");
            if (geneRna.equals("rna")) {
                //System.out.println("---3----");
                organism = organismRna;
                genome = genomeDAO.findByOrganism(organism);
                ris = riDAO.findByOrganism(organism);
                pris = priDAO.findByOrganism(organism);
                //rnaRegList = rnaRegDAO.findByGenome(genome.getId());

                if (o.getType().equals("model")) {
                    //System.out.println("---8----");
                    regulatoryInteractionViews = riViewDAO.findByGenome(genome.getId());
                    regulationsView = riView.getRegulationViewList(regulatoryInteractionViews);
                    //ris = (ArrayList<RegulatoryInteraction>) riDAO.findByOrganismAndGene(organism, g.getId());
                    cytoscapeFileName = cytoscapeFile.experimentalRegSif(ris, organism, gene);
                    if (genome.getId() == 1226) {
                        //findByGenomeRank
                        //System.out.println("---4----");
                        rnaRegList = (List<RnaRegulationView>) rnaRegDAO.findByGenomeRank(genome.getId(), 6);
                    }
                } else {
                    //System.out.println("---9----");
                    //System.out.println("Predicted TRN of organism");
                    //pris = (ArrayList<PredictedRegulatoryInteraction>) priDAO.findByOrganismAndGene(organism, g.getId());
                    predictedRegulatoryInteractionViews = priViewDAO.findByGenome(genome.getId());
                    regulationsView = priView.getRegulationViewList(predictedRegulatoryInteractionViews);
                    cytoscapeFileName = cytoscapeFile.predictedRegSif(pris, organism, gene);
                    rnaRegList = (List<RnaRegulationView>) rnaRegDAO.findByGenome(genome.getId());
                }
                genesInfo = gInfoDAO.getGeneInfoTreeByGenome(genome.getId());

                for (int i = 0; i < rnaRegList.size(); i++) {
                    //System.out.println("---6----");
                    sRNA = rnaRegList.get(i).getSrna();
                    srnasInfo.put(sRNA.getLocusTag(), sRNA);
                }

                //rnaRegList is still missing on the cytoscape file
                //cytoscapeFileName = cytoscapeFile.sRnaRegSif(rnaRegList, organism, gene);
            } else {
                //System.out.println("---7----");
                ris = riDAO.findByOrganism(organism);
                pris = priDAO.findByOrganism(organism);
                //rnaRegList = rnaRegDAO.findByGenome(genome.getId());

                if (o.getType().equals("model")) {
                    //System.out.println("---8----");
                    regulatoryInteractionViews = riViewDAO.findByGenome(genome.getId());
                    regulationsView = riView.getRegulationViewList(regulatoryInteractionViews);
                    //ris = (ArrayList<RegulatoryInteraction>) riDAO.findByOrganismAndGene(organism, g.getId());
                    cytoscapeFileName = cytoscapeFile.experimentalRegSif(ris, organism, gene);
                } else {
                    //System.out.println("---9----");
                    //System.out.println("Predicted TRN of organism");
                    //pris = (ArrayList<PredictedRegulatoryInteraction>) priDAO.findByOrganismAndGene(organism, g.getId());
                    predictedRegulatoryInteractionViews = priViewDAO.findByGenome(genome.getId());
                    regulationsView = priView.getRegulationViewList(predictedRegulatoryInteractionViews);
                    cytoscapeFileName = cytoscapeFile.predictedRegSif(pris, organism, gene);
                }
                genesInfo = gInfoDAO.getGeneInfoTreeByGenome(genome.getId());
            }

            //srnalist = srnaDAO.findByGenome(genome.getId());
////            if (pris != null && !pris.isEmpty()) {
////                for (PredictedRegulatoryInteraction pri11 : pris) {
////                    System.out.println("pri11: " + pri11.getHomologousTranscriptionFactor().toString2());
////                }
////            }
        } else {
            if (geneRna.equals("rna")) {
                organism = organismRna;
                genome = genomeDAO.findByOrganism(organism);

                sRNA = srnaDAO.findByLocusTagAndOrganism(organism, gene);

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
                //System.out.println(rnaRegList);
                genesInfo = gInfoDAO.getGeneInfoTreeByGenome(genome.getId());
                srnasInfo.put(sRNA.getLocusTag(), sRNA);
                cytoscapeFileName = cytoscapeFile.sRnaRegSif(rnaRegList, organism, gene);
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

                genesInfo = gInfoDAO.getGeneInfoTreeByGenome(genome.getId());
                srnalist = srnaDAO.findByGenome(genome.getId());
                for (int i = 0; i < srnalist.size(); i++) {
                    srnasInfo.put(srnalist.get(i).getLocusTag(), srnalist.get(i));
                }
            }
        }
        //System.out.println("---10----");
        operons = govDAO.getOperonsTreeByGenome(genome.getId());

        System.out.println("cytoscapeFileName: " + cytoscapeFileName);
        cytoscapeFileName += ".sif";
        System.out.println("cytoscapeFileName: " + cytoscapeFileName);

        //  System.out.println("regulationsView: " + regulationsView);
        //   System.out.println("rnaRegList: " + rnaRegList);
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
        model.addAttribute("geneRna", geneRna);
        model.addAttribute("entireNetwork", entireNetwork);

        //if (layoutType.equals("fast")) {
        return "networkDinamicVisualization-fast";
        //} else {
        //  return "networkDinamicVisualization";
        //}
    }

    @RequestMapping("NetworkSelectedGenes")
    public String NetworkSelectedGenes(Model model, Integer organism, Integer organismRna, String searchType,
            String goBackTo, String geneListSelect, String srnaListSelect,
            String layoutType, String geneRna) throws InterruptedException {

        //fagC and friends
        //String[] geneListArray = {"cg0012", "cg0001", "cg0009"};
        //String[] geneList = {"Cp1002B_RS00130", "Cp1002B_RS06905", "Cp1002B_RS06620", "Cp1002B_RS02945", "Cp1002B_RS06175"};
        //String[] geneList = {"JK_RS09260", "JK_RS05690", "JK_RS00525", "JK_RS05905", "JK_RS10995"};
        // String[] geneList = {"cg0589", "cg2103", "cg3253", "cg2173", "cg2309"};
        //String[] geneList = {"CKV68_RS06760", "CKV68_RS02225", "CKV68_RS01925", "CKV68_RS06035", "CKV68_RS02720"};
        //String[] geneList = {"DIP_RS16485", "DIP_RS18250", "DIP_RS18535", "DIP_RS23775", "DIP_RS19000"};
        //String[] geneList = {"CE_RS03550", "CE_RS09055", "CE_RS09360", "CE_RS13790", "CE_RS09980"};
        //inositol
        /*
        inositol cg:
         */
//        String[] geneList = {"cg0201", "cg0204", "cg0205", "cg0699", "cg0910", "cg1543", "cg2090",
//            "cg2298", "cg2964", "cg3137", "cg3323", "cg3391", "cg3392"};
        /*
        all sRNA-regulated genes in Cp
//         */
//        String[] geneList = {"Cp1002B_RS00040", "Cp1002B_RS00130", "Cp1002B_RS00395", "Cp1002B_RS00685", "Cp1002B_RS00695",
//            "Cp1002B_RS01305", "Cp1002B_RS01770", "Cp1002B_RS02295", "Cp1002B_RS02490", "Cp1002B_RS02630",
//            "Cp1002B_RS03145", "Cp1002B_RS03310", "Cp1002B_RS03820", "Cp1002B_RS03840", "Cp1002B_RS03915",
//            "Cp1002B_RS04025", "Cp1002B_RS04165", "Cp1002B_RS04255", "Cp1002B_RS04740", "Cp1002B_RS05795",
//            "Cp1002B_RS05995", "Cp1002B_RS06230", "Cp1002B_RS06430", "Cp1002B_RS06525", "Cp1002B_RS06620",
//            "Cp1002B_RS06645", "Cp1002B_RS06695", "Cp1002B_RS06745", "Cp1002B_RS06805", "Cp1002B_RS07010",
//            "Cp1002B_RS07590", "Cp1002B_RS07725", "Cp1002B_RS07805", "Cp1002B_RS08305", "Cp1002B_RS08320",
//            "Cp1002B_RS08590", "Cp1002B_RS08715", "Cp1002B_RS09225", "Cp1002B_RS09230", "Cp1002B_RS09510",
//            "Cp1002B_RS09680", "Cp1002B_RS09905	"
//        };
        //                       marZ             gatR       ltbR     marR
        // String[] geneList = {"Cp1002B_RS05995", "Cp1002B_RS06840", "Cp1002B_RS08445", "Cp1002B_RS08875"};
        //  String[] geneList = {"cg2378", "cg2115", "cg1486", "cg1324"};
        // String[] geneList = {"CKV68_RS02900"};  //kein gatR, lbtR,marR
        //   String[] geneList = {"DIP_RS19175", "DIP_RS18315", "DIP_RS16830", "DIP_RS16330"};
        // String[] geneList = {"JK_RS03865", "JK_RS05740", "JK_RS06310", "JK_RS10540"};
        // String[] geneList = {"CE_RS10235", "CE_RS09115", "CE_RS07145", "CE_RS06390"};
        //fprA and fpr1
        //String[] geneList = {"CE_RS12835", "CE_RS13095"};
        // String[] geneList = {"cg3049", "cg3119"};
        //  String[] geneList = { "JK_RS01235"};
        //String[] geneList = {"", ""}
        //  String[] geneList = {"DIP_RS21440"};
        //   String[] geneList = {"Cp1002B_RS03820"};
        //TFS: 
        //String[] geneList ={"cg1765", "cg1765", "cg0156", "cg0454", "cg0709", "cg1765", "cg3224", "cg0156", "cg2502", "cg2109", "cg1846", "cg1704", "cg0878", "cg2109", "cg1271", "cg1846", "cg2114", "cg0709", "cg1410", "cg0090", "cg2109", "cg1861", "cg2200", "cg3097", "cg2092", "cg1765", "cg1032", "cg0702", "cg2115", "cg0741", "cg1053", "cg1547", "cg0215", "cg0337", "cg3261", "cg3285", "cg0371", "cg1053", "cg0371", "cg2910", "cg2114", "cg3261", "cg0741", "cg3285", "cg0696", "cg2831", "cg0986", "cg2766", "cg1765", "cg0862", "cg2092", "cg0012", "cg1053", "cg2152", "cg1211", "cg1425", "cg0878", "cg1765", "cg0019", "cg0019", "cg2627", "cg0993", "cg3097", "cg0862", "cg3097", "cg0862", "cg0019", "cg1340", "cg1817", "cg1935", "cg0878", "cg3285", "cg0350", "cg2102", "cg0090", "cg0463", "cg1308", "cg1340", "cg1340", "cg1340", "cg0800", "cg3082", "cg2114", "cg1324", "cg3224", "cg0800", "cg0695", "cg2910", "cg0309", "cg3097", "cg1547", "cg1935", "cg0156", "cg0090", "cg1765", "cg1846", "cg1271", "cg2831", "cg1324", "cg0051", "cg2200", "cg1846", "cg1585", "cg2103", "cg0019", "cg2092", "cg0156", "cg3082", "cg1765", "cg0051", "cg1425", "cg2118", "cg3352", "cg2309", "cg0371", "cg1846", "cg1861", "cg1585", "cg0146", "cg2615", "cg1410", "cg2516", "cg1324", "cg2115", "cg0090", "cg0696", "cg0696", "cg1340", "cg3352", "cg3224", "cg0702", "cg3315", "cg2092", "cg2615", "cg1120", "cg2114", "cg1211", "cg0800", "cg3388", "cg0986", "cg0800", "cg2118", "cg1340", "cg1765", "cg1486", "cg0463", "cg2500", "cg3202", "cg0090", "cg2114", "cg0371", "cg2500", "cg2627", "cg2109", "cg1738", "cg2737", "cg1765", "cg2641", "cg3247", "cg1053", "cg0090", "cg2114", "cg0337", "cg2112", "cg2200", "cg2888", "cg1831", "cg0862", "cg0019", "cg2544", "cg1486", "cg2200", "cg1324", "cg0709", "cg2888", "cg1765", "cg2502", "cg0709", "cg2092", "cg0463", "cg2936", "cg1831", "cg2965", "cg0876", "cg1218", "cg0012", "cg3315", "cg0317"};
        //String[] geneList ={"DIP_RS19435","DIP_RS18315","DIP_RS16830","DIP_RS21035"};
        // String[] geneList={"JK_RS05100","JK_RS05690","JK_RS00525","JK_RS01670"};
        //genes regulated by TFs and sRNAs 
        //String[] geneList={"cg0012","cg0018","cg0040","cg0042","cg0043","cg0044","cg0046","cg0052","cg0053","cg0054","cg0085","cg0088","cg0095","cg0096","cg0097","cg0103","cg0114","cg0115","cg0116","cg0117","cg0118","cg0119","cg0133","cg0134","cg0136","cg0142","cg0143","cg0144","cg0146","cg0147","cg0156","cg0159","cg0160","cg0184","cg0197","cg0198","cg0199","cg0201","cg0203","cg0204","cg0205","cg0207","cg0210","cg0223","cg0228","cg0229","cg0230","cg0246","cg0247","cg0282","cg0297","cg0304","cg0306","cg0307","cg0310","cg0311","cg0315","cg0318","cg0319","cg0337","cg0340","cg0341","cg0344","cg0345","cg0346","cg0350","cg0378","cg0397","cg0404","cg0405","cg0413","cg0441","cg0445","cg0446","cg0448","cg0451","cg0452","cg0454","cg0456","cg0463","cg0464","cg0465","cg0468","cg0469","cg0471","cg0497","cg0498","cg0501","cg0502","cg0504","cg0516","cg0517","cg0518","cg0519","cg0522","cg0523","cg0524","cg0532","cg0533","cg0544","cg0545","cg0561","cg0567","cg0568","cg0589","cg0590","cg0591","cg0607","cg0612","cg0614","cg0616","cg0617","cg0635","cg0636","cg0639","cg0642","cg0644","cg0676","cg0679","cg0681","cg0690","cg0695","cg0697","cg0701","cg0707","cg0709","cg0710","cg0713","cg0714","cg0715","cg0717","cg0718","cg0719","cg0720","cg0721","cg0723","cg0737","cg0738","cg0740","cg0741","cg0742","cg0754","cg0755","cg0760","cg0762","cg0763","cg0766","cg0767","cg0768","cg0769","cg0770","cg0771","cg0781","cg0790","cg0791","cg0794","cg0795","cg0797","cg0798","cg0800","cg0802","cg0811","cg0812","cg0814","cg0858","cg0875","cg0876","cg0878","cg0879","cg0892","cg0898","cg0899","cg0922","cg0927","cg0928","cg0936","cg0938","cg0948","cg0949","cg0952","cg0957","cg0961","cg0977","cg0980","cg0993","cg1010","cg1011","cg1012","cg1032","cg1033","cg1037","cg1038","cg1039","cg1040","cg1041","cg1052","cg1053","cg1054","cg1056","cg1059","cg1061","cg1062","cg1064","cg1065","cg1066","cg1069","cg1080","cg1081","cg1082","cg1083","cg1091","cg1109","cg1111","cg1120","cg1132","cg1133","cg1139","cg1140","cg1141","cg1142","cg1143","cg1145","cg1147","cg1151","cg1153","cg1179","cg1182","cg1211","cg1212","cg1215","cg1216","cg1218","cg1225","cg1245","cg1246","cg1247","cg1266","cg1268","cg1269","cg1271","cg1272","cg1279","cg1280","cg1283","cg1284","cg1288","cg1289","cg1290","cg1292","cg1294","cg1295","cg1296","cg1299","cg1300","cg1301","cg1307","cg1308","cg1309","cg1310","cg1311","cg1318","cg1322","cg1324","cg1325","cg1333","cg1337","cg1338","cg1340","cg1341","cg1342","cg1343","cg1344","cg1353","cg1354","cg1355","cg1358","cg1359","cg1360","cg1362","cg1363","cg1364","cg1365","cg1366","cg1367","cg1368","cg1369","cg1375","cg1376","cg1379","cg1387","cg1397","cg1398","cg1409","cg1410","cg1411","cg1413","cg1414","cg1418","cg1421","cg1424","cg1425","cg1426","cg1427","cg1432","cg1437","cg1453","cg1486","cg1487","cg1488","cg1543","cg1547","cg1555","cg1559","cg1560","cg1568","cg1570","cg1571","cg1572","cg1580","cg1582","cg1583","cg1585","cg1586","cg1588","cg1623","cg1640","cg1642","cg1647","cg1650","cg1651","cg1652","cg1653","cg1656","cg1665","cg1671","cg1686","cg1687","cg1688","cg1689","cg1696","cg1701","cg1705","cg1706","cg1708","cg1709","cg1726","cg1730","cg1734","cg1737","cg1738","cg1739","cg1746","cg1753","cg1760","cg1761","cg1762","cg1763","cg1764","cg1765","cg1767","cg1768","cg1769","cg1773","cg1774","cg1778","cg1779","cg1780","cg1784","cg1785","cg1787","cg1789","cg1790","cg1791","cg1799","cg1806","cg1812","cg1813","cg1814","cg1815","cg1816","cg1817","cg1831","cg1832","cg1834","cg1840","cg1844","cg1845","cg1846","cg1847","cg1848","cg1850","cg1869","cg1870","cg1871","cg1881","cg1930","cg1935","cg1943","cg1956","cg1992","cg1998","cg2034","cg2052","cg2076","cg2078","cg2079","cg2080","cg2092","cg2096","cg2102","cg2109","cg2112","cg2114","cg2115","cg2117","cg2118","cg2119","cg2120","cg2121","cg2126","cg2136","cg2138","cg2139","cg2140","cg2141","cg2148","cg2149","cg2152","cg2158","cg2177","cg2181","cg2182","cg2183","cg2184","cg2192","cg2194","cg2200","cg2201","cg2202","cg2204","cg2206","cg2214","cg2218","cg2247","cg2260","cg2261","cg2262","cg2263","cg2267","cg2277","cg2279","cg2291","cg2301","cg2311","cg2330","cg2332","cg2334","cg2342","cg2361","cg2363","cg2364","cg2365","cg2366","cg2381","cg2383","cg2397","cg2402","cg2404","cg2405","cg2406","cg2408","cg2409","cg2410","cg2411","cg2429","cg2437","cg2445","cg2449","cg2487","cg2489","cg2500","cg2502","cg2514","cg2515","cg2536","cg2537","cg2538","cg2540","cg2542","cg2543","cg2560","cg2572","cg2612","cg2613","cg2616","cg2617","cg2619","cg2620","cg2626","cg2627","cg2628","cg2629","cg2630","cg2631","cg2634","cg2635","cg2638","cg2640","cg2642","cg2643","cg2644","cg2645","cg2661","cg2674","cg2675","cg2677","cg2683","cg2687","cg2703","cg2704","cg2705","cg2708","cg2720","cg2732","cg2733","cg2743","cg2762","cg2766","cg2777","cg2780","cg2781","cg2787","cg2796","cg2800","cg2811","cg2812","cg2824","cg2831","cg2833","cg2836","cg2837","cg2838","cg2840","cg2843","cg2844","cg2846","cg2873","cg2876","cg2883","cg2886","cg2888","cg2891","cg2893","cg2895","cg2905","cg2911","cg2912","cg2925","cg2929","cg2931","cg2932","cg2933","cg2935","cg2936","cg2937","cg2938","cg2939","cg2940","cg2949","cg2950","cg2953","cg2954","cg2963","cg2965","cg2966","cg2984","cg3011","cg3047","cg3048","cg3068","cg3078","cg3082","cg3083","cg3085","cg3096","cg3097","cg3098","cg3099","cg3100","cg3107","cg3112","cg3113","cg3114","cg3115","cg3116","cg3117","cg3118","cg3119","cg3125","cg3126","cg3132","cg3138","cg3139","cg3141","cg3142","cg3156","cg3169","cg3170","cg3177","cg3178","cg3179","cg3181","cg3182","cg3202","cg3219","cg3221","cg3226","cg3236","cg3237","cg3240","cg3247","cg3251","cg3254","cg3264","cg3281","cg3284","cg3285","cg3286","cg3299","cg3315","cg3320","cg3321","cg3322","cg3327","cg3330","cg3335","cg3344","cg3351","cg3352","cg3353","cg3354","cg3357","cg3360","cg3362","cg3364","cg3366","cg3372","cg3374","cg3375","cg3382","cg3386","cg3388","cg3389","cg3390","cg3394","cg3395","cg3404","cg3405","cg3422","cg3423","cg3424","cg3429"};
        //String[] geneList = {"DIP_RS11520","DIP_RS12055","DIP_RS12535","DIP_RS14315","DIP_RS14355","DIP_RS14430","DIP_RS15610","DIP_RS16200","DIP_RS16650","DIP_RS16660","DIP_RS17725","DIP_RS18360","DIP_RS18915","DIP_RS19435","DIP_RS20075","DIP_RS21285"};
        String[] geneList = {"DIP_RS19435","DIP_RS16945","DIP_RS13175"};
        //String[] geneList = {"JK_RS00285", "JK_RS00390", "JK_RS00405", "JK_RS00925", "JK_RS00955", "JK_RS01515", "JK_RS01960", "JK_RS02865", "JK_RS02870", "JK_RS05010", "JK_RS05100", "JK_RS07345", "JK_RS07350", "JK_RS07360", "JK_RS07470", "JK_RS07760", "JK_RS08650", "JK_RS08760", "JK_RS08820", "JK_RS09155"};
       // String[] geneList = {"Cp1002B_RS00130", "Cp1002B_RS06745", "Cp1002B_RS06805", "Cp1002B_RS08590"};
       // String[] geneList = {"CKV68_RS05460","CKV68_RS05710","CKV68_RS06760"};
        
        
        List<String> geneListArray = new ArrayList<String>(Arrays.asList(geneList));

        System.out.println("NetworkSelectedGenes");

        System.out.println("GRN:");
        System.out.println("--------------- Organism: " + organism);
        System.out.println("--------------- OrganismRna: " + organismRna);
        System.out.println("--------------- SearchType: " + searchType);
        //System.out.println("--------------- Gene: " + gene);
        System.out.println("--------------- geneListSelect: " + geneListSelect);
        System.out.println("--------------- srnaListSelect: " + srnaListSelect);
        //System.out.println("Gooooooooooooooo networkVisualization!!!!!!!!!!!!!!!!!!!!!!!");
        //System.out.println("gene: " + gene);
        //System.out.println("goBackTo: " + goBackTo);
        //System.out.println("layoutType: " + layoutType);
        //System.out.println("Which network?");
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        ArrayList<RegulationView> regulationsView = new ArrayList<>();
        ArrayList<RegulationView> regulationsViews = new ArrayList<>();
        ArrayList<RegulatoryInteraction> ris = new ArrayList<>();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        ArrayList<PredictedRegulatoryInteraction> pris = new ArrayList<>();

        GeneOperonViewDAO govDAO = new GeneOperonViewDAO();
        GeneInfoViewDAO gInfoDAO = new GeneInfoViewDAO();
        //GeneOperonView operons = new GeneOperonView();
        ArrayList<BindingSite> bss = new ArrayList<>();
        //GeneInfo geneInfo = new GeneInfo();
        TreeMap<String, GeneInfo> genesInfo = new TreeMap<>();
        TreeMap<String, GeneInfo> genesInfos = new TreeMap<>();
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
        String entireNetwork = "false";

        RnaRegulationViewDAO rnaRegDAO = new RnaRegulationViewDAO();
        List<RnaRegulationView> rnaRegList = new LinkedList<>();
        List<RnaRegulationView> rnaRegLists = new LinkedList<>();

        List<String> regulatedRegulatingGenes = new ArrayList<>();
        for (String gene : geneListArray) {

            List<TableView> tableViews;
            TableViewDAO tableViewDAO = new TableViewDAO();
            tableViews = tableViewDAO.findByLocusTagGeneName(gene);
            for (TableView tableView : tableViews) {
                String regulates = tableView.getRegulatedGenes();
                String regulatedBy = tableView.getRegulatedBy();
                String rnasRegulating = tableView.getRnasLocus();

                if (regulates != null && !regulates.isEmpty()) {
                    String regulatesArray[] = regulates.split(";");
                    for (String string : regulatesArray) {
                        string = string.replace(",A", "").replace(",R", "").trim();
                        regulatedRegulatingGenes.add(string);
                    }
                }

                if (regulatedBy != null && !regulatedBy.isEmpty()) {
                    String regulatedByArray[] = regulatedBy.split(";");
                    for (String string : regulatedByArray) {
                        regulatedRegulatingGenes.add(string.replace(",A", "").replace(",R", "").trim());
                    }
                }

            }
        }

        System.out.println(regulatedRegulatingGenes.toString());
        for (String regulatedRegulatingGene : regulatedRegulatingGenes) {
            if (!geneListArray.contains(regulatedRegulatingGene)) {
                geneListArray.add(regulatedRegulatingGene);
            }
        }
//        
        // geneListArray.addAll(regulatedRegulatingGenes);

        for (String gene : geneListArray) {
            g = geneDAO.findGeneByLocusTagOrGeneName(organism, gene);
            System.out.println("----------------------------------g: " + g);
            if (g != null) {
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

                genesInfo = gInfoDAO.getGeneInfoTreeByGenome(genome.getId());
                for (Map.Entry<String, GeneInfo> entry : genesInfo.entrySet()) {
                    String key = entry.getKey();
                    GeneInfo value = entry.getValue();
                    genesInfos.put(key, value);
                }
                srnalist = srnaDAO.findByGenome(genome.getId());
                for (int i = 0; i < srnalist.size(); i++) {
                    srnasInfo.put(srnalist.get(i).getLocusTag(), srnalist.get(i));
                }

                for (int i = 0; i < rnaRegList.size(); i++) {
                    rnaRegLists.add(rnaRegList.get(i));
                }
                for (int i = 0; i < regulationsView.size(); i++) {
                    regulationsViews.add(regulationsView.get(i));
                }
            }

        }
        //System.out.println("---10----");
        operons = govDAO.getOperonsTreeByGenome(genome.getId());

        System.out.println("cytoscapeFileName: " + cytoscapeFileName);
        cytoscapeFileName += ".sif";
        System.out.println("cytoscapeFileName: " + cytoscapeFileName);

        //  System.out.println("regulationsView: " + regulationsView);
        //   System.out.println("rnaRegList: " + rnaRegList);
        if (regulationsViews.isEmpty() && rnaRegLists.isEmpty()) {
            model.addAttribute("o", o);
            model.addAttribute("type", searchType);
            model.addAttribute("message", "No regulation is known for " + " in this organism so far.");
            return "networkEmpty";
        }

        System.out.println("Goooooooooooooooo networkkkkkkkkkkkkkkkkkk");

        for (int i = 0; i < rnaRegLists.size(); i++) {
            System.out.println("rnaRegLists: " + rnaRegLists.get(i));
        }

        System.out.println("\n\n");

        for (int i = 0; i < regulationsView.size(); i++) {
            System.out.println("regulationsView: " + regulationsView.get(i));
        }

        //rnaRegList srnasInfo
        model.addAttribute("srnasInfo", srnasInfo);
        model.addAttribute("rnaRegList", rnaRegLists);
        model.addAttribute("goBackTo", goBackTo);
        model.addAttribute("typeOfGoBack", "normal");
        model.addAttribute("type", searchType);
        //model.addAttribute("nodes", nodes);
        model.addAttribute("cytoscapeFileName", cytoscapeFileName);
        model.addAttribute("o", o);
        model.addAttribute("operons", operons);
        //model.addAttribute("geneOpInfo", geneOpInfo);
        model.addAttribute("genesInfo", genesInfos);
        model.addAttribute("regulationsView", regulationsViews);
        model.addAttribute("geneRna", geneRna);
        model.addAttribute("entireNetwork", entireNetwork);

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
            model.addAttribute("numberOfSmallRnas", features[9]);
            model.addAttribute("numberOfSmallRnas", features[10]);
            model.addAttribute("numberOfSmallRnas", features[11]);
            model.addAttribute("numberOfSmallRnas", features[12]);
            model.addAttribute("itemsRna", (List<OrganismItem>) features[13]);
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
            model.addAttribute("numberOfSmallRnas", features[9]);
            model.addAttribute("numberOfSmallRnas", features[10]);
            model.addAttribute("numberOfSmallRnas", features[11]);
            model.addAttribute("numberOfSmallRnas", features[12]);
            model.addAttribute("itemsRna", (List<OrganismItem>) features[13]);
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
            //System.out.println(geneInfoViewsSet1);
            geneInfo.put(geneInfoViewsSet1.getGeneName(), geneInfoViewsSet1.getGeneInfo());
        }

        return geneInfo;
    }

//// 
    @RequestMapping("networkDinamicVisualizationOperons")
    public String networkDinamicVisualizationOperons(Model model, Integer organism, String searchType, String[] interestGenes, String role) throws InterruptedException {
        String[] geneListArray = {"cg0012", "cg0001", "cg0009"};
        interestGenes = geneListArray;

        System.out.println("\n\n------------------------------------");
        System.out.println("--------------- Organism: " + organism);
        System.out.println("--------------- SearchType: " + searchType);
        System.out.println("interestGenes " + interestGenes.toString());
        for (int i = 0; i < interestGenes.length; i++) {
            System.out.println("gene: " + interestGenes[i]);
        }
        System.out.println("------------------------------------\n\n");
        //System.out.println("Gooooooooooooooo networkVisualization!!!!!!!!!!!!!!!!!!!!!!!");
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
        GeneOperonViewDAO govDAO = new GeneOperonViewDAO();
        operons = govDAO.getOperonsTreeByGenome(genome.getId());

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
                rnaRegList = rnaRegDAO.findByTg(g.getId());

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
                    //System.out.println("operon: " + operon.getName() + " " + genesOfOp);

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

            for (int i = 0; i < rnaRegList.size(); i++) {
                srnasInfo.put(rnaRegList.get(i).getSrna().getLocusTag(), rnaRegList.get(i).getSrna());
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

    @RequestMapping("whichStatistics")
    public String whichStatistics(Model model, String type) {

        return "whichStatistics";
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

    @RequestMapping("sRNAStatistics")
    public String sRNAStatistics(Model model, String type) {

        if (type == null) {
            return "databaseStatistics";
        }
        //sRNA type
        SmallRnaDAO sRnaDAO = new SmallRnaDAO();
        Long ncRnaExperimental = sRnaDAO.bringFunctionalByType("experimental", false);
        //System.out.println("ncRnaExperimental " + ncRnaExperimental);
        Long ncRnaPredicted = sRnaDAO.bringFunctionalByNotType("experimental", false);
        //System.out.println("ncRnaPredicted " + ncRnaPredicted);

        Long funcRnaExperimental = sRnaDAO.bringFunctionalByType("experimental", true);
        //System.out.println("funcRnaExperimental " + funcRnaExperimental);
        Long funcRnaPredicted = sRnaDAO.bringFunctionalByNotType("experimental", true);
        //System.out.println("funcRnaPredicted " + funcRnaPredicted);
        Long ncRNA = ncRnaExperimental + ncRnaPredicted;
        //System.out.println("ncRNA: " + ncRNA);
        Long funcRNA = funcRnaExperimental + funcRnaPredicted;
        //System.out.println("funcRNA: " + funcRNA);

        //distribuition of smallRnas regulating a gene. 
        GenesRegulatedBySrnasViewDAO dao = new GenesRegulatedBySrnasViewDAO();
        List<GenesRegulatedBySrnasView> sRNARegAGene = dao.findByGenome(0);

//coRegAGene
        TreeMap<Integer, Integer> numOfCoregulators = new TreeMap<>();
        ArrayList<CoregulatorsStatistics> coRegAGene = new ArrayList<>();
        CoregulatorsStatisticsDAO csDAO = new CoregulatorsStatisticsDAO();
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
            }
        }
        numOfCoregulators.put(coRegRange, numOfTFs);

        ///////////////////-------------------------
        //model.addAttribute("distancesFromStartSite", distancesFromStartSite);
        model.addAttribute("numOfCoregulators", numOfCoregulators);
        model.addAttribute("sRNARegAGene", sRNARegAGene);
        model.addAttribute("ncRnaExperimental", ncRnaExperimental);
        model.addAttribute("ncRnaPredicted", ncRnaPredicted);
        model.addAttribute("funcRnaExperimental", funcRnaExperimental);
        model.addAttribute("funcRnaPredicted", funcRnaPredicted);
        model.addAttribute("type", type);
        return "sRNAStatistics";
    }

    @RequestMapping("quantitiesOfRegulatorAndRegulationTypes")
    public String quantitiesOfRegulatorAndRegulationTypes(Model model, String type) {

        if (type == null) {
            return "databaseStatistics";
        }

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

    @RequestMapping("quantitiesOfsRNATypes")
    public String quantitiesOfsRNATypes(Model model, String type) {

        if (type == null) {
            return "databaseStatistics";
        }

        LinkedHashMap<String, ArrayList<Long>> organismsRegulators = new LinkedHashMap<>();
        ArrayList<Long> sRNAsArray = new ArrayList<>();
        ArrayList<Genome> genomes = new ArrayList<>();
        LinkedHashMap<String, Integer> organismsId = new LinkedHashMap<>();
        GenomeDAO genomeDAO = new GenomeDAO();

        //sRNA type
        SmallRnaDAO sRnaDAO = new SmallRnaDAO();
        Long ncRnaExperimental = sRnaDAO.bringByExperimentalType();
        Long ncRnaBsrd = sRnaDAO.bringByBsrdType();
        Long ncRnaCmsearch = sRnaDAO.bringByCmsearchType();
        Long ncRnaGLASSgo = sRnaDAO.bringByGLASSgoType();
        Long ncRNATypes = ncRnaBsrd + ncRnaCmsearch + ncRnaExperimental + ncRnaGLASSgo;

        sRNAsArray.add(ncRnaExperimental);
        sRNAsArray.add(ncRnaBsrd);
        sRNAsArray.add(ncRnaCmsearch);
        sRNAsArray.add(ncRnaGLASSgo);
        sRNAsArray.add(ncRNATypes);
        organismsRegulators.put("all", sRNAsArray);

        genomes = (ArrayList<Genome>) genomeDAO.listAll();
        //Retrive the sRNA types
        if (genomes != null) {
            for (int i = 0; i < genomes.size(); i++) {
                sRNAsArray = new ArrayList<>();

                //sRNA type
                ncRnaExperimental = sRnaDAO.bringByExperimentalTypeGenome(genomes.get(i).getId());
                ncRnaBsrd = sRnaDAO.bringByBsrdTypeGenome(genomes.get(i).getId());
                ncRnaCmsearch = sRnaDAO.bringByCmsearchTypeGenome(genomes.get(i).getId());
                ncRnaGLASSgo = sRnaDAO.bringByGLASSgoTypeGenome(genomes.get(i).getId());
                ncRNATypes = ncRnaBsrd + ncRnaCmsearch + ncRnaExperimental + ncRnaGLASSgo;

                if (ncRNATypes > 0) {
                    sRNAsArray.add(ncRnaExperimental);
                    sRNAsArray.add(ncRnaBsrd);
                    sRNAsArray.add(ncRnaCmsearch);
                    sRNAsArray.add(ncRnaGLASSgo);
                    sRNAsArray.add(ncRNATypes);
                    organismsId.put(genomes.get(i).getOrganism().getGenera() + " " + genomes.get(i).getOrganism().getSpecies()
                            + " " + genomes.get(i).getOrganism().getStrain(), genomes.get(i).getOrganism().getId());
                    organismsRegulators.put(genomes.get(i).getOrganism().getGenera() + " " + genomes.get(i).getOrganism().getSpecies()
                            + " " + genomes.get(i).getOrganism().getStrain(), sRNAsArray);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : organismsId.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + ": " + value);
        }

        for (Map.Entry<String, ArrayList<Long>> entry : organismsRegulators.entrySet()) {
            String key = entry.getKey();
            ArrayList<Long> value = entry.getValue();
            System.out.println(key + ": " + value);
        }

        model.addAttribute("organismsRegulators", organismsRegulators);
        model.addAttribute("organismsId", organismsId);
        model.addAttribute("type", type);
        return "quantitiesOfsRNATypes";
    }

    @RequestMapping("quantitiesOfsRNATypesTest")
    public String quantitiesOfsRNATypesTest(Model model, String type) {

        if (type == null) {
            return "databaseStatistics";
        }

        LinkedHashMap<String, ArrayList<Long>> organismsRegulators = new LinkedHashMap<>();
        ArrayList<Long> sRNAsArray = new ArrayList<>();
        ArrayList<Genome> genomes = new ArrayList<>();
        LinkedHashMap<String, Integer> organismsId = new LinkedHashMap<>();
        GenomeDAO genomeDAO = new GenomeDAO();

        //sRNA type
        SmallRnaDAO sRnaDAO = new SmallRnaDAO();
        Long ncRnaExperimental = sRnaDAO.bringByExperimentalType();
        Long ncRnaBsrd = sRnaDAO.bringByBsrdType();
        Long ncRnaCmsearch = sRnaDAO.bringByCmsearchType();
        Long ncRnaGLASSgo = sRnaDAO.bringByGLASSgoType();
        Long ncRNATypes = ncRnaBsrd + ncRnaCmsearch + ncRnaExperimental + ncRnaGLASSgo;

        sRNAsArray.add(ncRnaExperimental);
        sRNAsArray.add(ncRnaBsrd);
        sRNAsArray.add(ncRnaCmsearch);
        sRNAsArray.add(ncRnaGLASSgo);
        sRNAsArray.add(ncRNATypes);
        organismsRegulators.put("all", sRNAsArray);

        genomes = (ArrayList<Genome>) genomeDAO.listAll();
        //Retrive the sRNA types
        if (genomes != null) {
            for (int i = 0; i < genomes.size(); i++) {
                sRNAsArray = new ArrayList<>();

                //sRNA type
                ncRnaExperimental = sRnaDAO.bringByExperimentalTypeGenome(genomes.get(i).getId());
                ncRnaBsrd = sRnaDAO.bringByBsrdTypeGenome(genomes.get(i).getId());
                ncRnaCmsearch = sRnaDAO.bringByCmsearchTypeGenome(genomes.get(i).getId());
                ncRnaGLASSgo = sRnaDAO.bringByGLASSgoTypeGenome(genomes.get(i).getId());
                ncRNATypes = ncRnaBsrd + ncRnaCmsearch + ncRnaExperimental + ncRnaGLASSgo;

                if (ncRNATypes > 0) {
                    sRNAsArray.add(ncRnaExperimental);
                    sRNAsArray.add(ncRnaBsrd);
                    sRNAsArray.add(ncRnaCmsearch);
                    sRNAsArray.add(ncRnaGLASSgo);
                    sRNAsArray.add(ncRNATypes);
                    organismsId.put(genomes.get(i).getOrganism().getGenera() + " " + genomes.get(i).getOrganism().getSpecies()
                            + " " + genomes.get(i).getOrganism().getStrain(), genomes.get(i).getOrganism().getId());
                    organismsRegulators.put(genomes.get(i).getOrganism().getGenera() + " " + genomes.get(i).getOrganism().getSpecies()
                            + " " + genomes.get(i).getOrganism().getStrain(), sRNAsArray);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : organismsId.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + ": " + value);
        }

        for (Map.Entry<String, ArrayList<Long>> entry : organismsRegulators.entrySet()) {
            String key = entry.getKey();
            ArrayList<Long> value = entry.getValue();
            System.out.println(key + ": " + value);
        }

        model.addAttribute("organismsRegulators", organismsRegulators);
        model.addAttribute("organismsId", organismsId);
        model.addAttribute("type", type);
        return "quantitiesOfsRNATypesTest";
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

    @RequestMapping("sRNAsRegulatingAGene")
    public String sRNAsRegulatingAGene(Model model, String type) throws InterruptedException {

        if (type == null) {
            return "databaseStatistics";
        }

        LinkedHashMap<String, TreeMap<Integer, Integer>> sRNAsRegAGene = new LinkedHashMap<>();
        TreeMap<Integer, Integer> sRNAsRegAGeneAux = new TreeMap<>();
        ArrayList<Genome> genomes = new ArrayList<>();
        LinkedHashMap<String, Integer> organismsId = new LinkedHashMap<>();
        GenomeDAO genomeDAO = new GenomeDAO();
        Long numTfs = new Long(0);
        Integer numTgs = 0;
        GenesRegulatedBySrnasViewDAO dao = new GenesRegulatedBySrnasViewDAO();
        ArrayList<GenesRegulatedBySrnasView> sRNARegAGene = new ArrayList<>();
        TfsRegulatingDAO tfsRegDAO = new TfsRegulatingDAO();

        genomes = (ArrayList<Genome>) genomeDAO.listAll();
        sRNARegAGene = (ArrayList<GenesRegulatedBySrnasView>) dao.findByGenome(0);
        for (GenesRegulatedBySrnasView sRNARegulating : sRNARegAGene) {
            sRNAsRegAGeneAux.put(sRNARegulating.getNumSrnas(), sRNARegulating.getNumGenes());
        }
        sRNAsRegAGene.put("all", sRNAsRegAGeneAux);

        if (genomes != null) {
            for (int i = 0; i < genomes.size(); i++) {
                sRNAsRegAGeneAux = new TreeMap<>();

                sRNARegAGene = new ArrayList<>();
                sRNARegAGene = (ArrayList<GenesRegulatedBySrnasView>) dao.findByGenome(genomes.get(i).getId());
                if (!sRNARegAGene.isEmpty()) {
                    organismsId.put(genomes.get(i).getOrganism().getGenera() + " " + genomes.get(i).getOrganism().getSpecies()
                            + " " + genomes.get(i).getOrganism().getStrain(), genomes.get(i).getOrganism().getId());
                    for (GenesRegulatedBySrnasView sRNARegulating : sRNARegAGene) {
                        sRNAsRegAGeneAux.put(sRNARegulating.getNumSrnas(), sRNARegulating.getNumGenes());
                    }
                    sRNAsRegAGene.put(genomes.get(i).getOrganism().getGenera() + " " + genomes.get(i).getOrganism().getSpecies()
                            + " " + genomes.get(i).getOrganism().getStrain(), sRNAsRegAGeneAux);
                }
            }
        }
        model.addAttribute("sRNAsRegAGene", sRNAsRegAGene);
        model.addAttribute("organismsId", organismsId);
        model.addAttribute("type", type);
        return "sRNAsRegulatingAGene";
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
        TreeMap<Integer, Integer> allCoregulatorsOfOrganism = new TreeMap<>();
        ArrayList<CoregulatorsStatistics> coRegAGene = new ArrayList<>();
        CoregulatorsStatisticsDAO csDAO = new CoregulatorsStatisticsDAO();

        if (type.equals("experimental")) {
            organisms = (ArrayList<Organism>) organismDAO.findByType("model");
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

    @RequestMapping("sRnaCoregulatorsStatistics")
    public String sRnaCoregulatorsStatistics(Model model, String type) {

        if (type == null) {
            return "databaseStatistics";
        }

        ArrayList<Genome> genomes = new ArrayList<>();
        LinkedHashMap<String, Integer> organismsId = new LinkedHashMap<>();
        GenomeDAO genomeDAO = new GenomeDAO();
        LinkedHashMap<String, TreeMap<Integer, Integer>> numOfsRnaCoregulators = new LinkedHashMap<>();
        Long numOfGeneCoregulators = new Long(0);
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        TreeMap<Integer, Integer> allCoregulatorsOfOrganism = new TreeMap<>();

        if (type.equals("experimental")) {
            genomes = (ArrayList<Genome>) genomeDAO.findByOrgnismType("model");
        } else {
            genomes = (ArrayList<Genome>) genomeDAO.listAll();
        }

        RnaCoregulatingViewDAO rnaCoregulatingDAO = new RnaCoregulatingViewDAO();
        List<RnaCoregulatingView> lisCoregRna = rnaCoregulatingDAO.findByGenome(0);
        for (RnaCoregulatingView coRAG : lisCoregRna) {
            allCoregulatorsOfOrganism.put(coRAG.getCoRegRnas(), coRAG.getCount());
        }
        //numOfCoregulators.put(coRAG.getNumCoregulators(), coRAG.getNumTfs());
        numOfsRnaCoregulators.put("all", allCoregulatorsOfOrganism);

        if (genomes != null) {
            for (int i = 0; i < genomes.size(); i++) {
                allCoregulatorsOfOrganism = new TreeMap<>();
                lisCoregRna = new ArrayList<>();
                lisCoregRna = rnaCoregulatingDAO.findByGenome(genomes.get(i).getId());
                if (!lisCoregRna.isEmpty()) {
                    //System.out.println("coRegAGene is not empty");
                    organismsId.put(genomes.get(i).getOrganism().getGenera() + " "
                            + genomes.get(i).getOrganism().getSpecies() + " "
                            + genomes.get(i).getOrganism().getStrain(), genomes.get(i).getId());
                    for (RnaCoregulatingView coRAG : lisCoregRna) {
                        allCoregulatorsOfOrganism.put(coRAG.getCoRegRnas(), coRAG.getCount());
                    }
                    numOfsRnaCoregulators.put(genomes.get(i).getOrganism().getGenera() + " "
                            + genomes.get(i).getOrganism().getSpecies() + " "
                            + genomes.get(i).getOrganism().getStrain(), allCoregulatorsOfOrganism);
                }
            }
        }
        //System.out.println(numOfCoregulators);
        model.addAttribute("numOfsRnaCoregulators", numOfsRnaCoregulators);
        model.addAttribute("organismsId", organismsId);
        model.addAttribute("type", type);
        return "sRnaCoregulatorsStatistics";
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
