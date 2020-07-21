/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.controller;

import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.OrganismDAO;
import com.coryneregnet7.dao.PredictedRegulatoryInteractionDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.dao.RnaRegulationViewDAO;
import com.coryneregnet7.dao.SmallRnaDAO;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Organism;
import com.coryneregnet7.model.PredictedRegulatoryInteraction;
import com.coryneregnet7.model.RegulatoryInteraction;
import com.coryneregnet7.model.RnaRegulationView;
import com.coryneregnet7.model.SmallRna;
import com.coryneregnet7.processing.output.CytoscapeFile;
import com.coryneregnet7.processing.output.OperonsFile;
import com.coryneregnet7.processing.output.RegulationsFile;
import com.coryneregnet7.processing.output.RnaFile;
import com.coryneregnet7.processing.output.ZipFiles;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author doglas
 */
@Controller
public class DownloadController {

    @RequestMapping("processToDownalod")
    public String processToDownalod(Model model) {
        //  model.addAttribute("testString", "blablabla");
        OrganismDAO organismDAO = new OrganismDAO();
        List<Organism> modelOrganisms = organismDAO.findByType("model");
        List<Organism> organisms = organismDAO.listAll();

        model.addAttribute("organisms", organisms);
        model.addAttribute("modelOrganisms", modelOrganisms);
        return "processToDownalod";
    }

    @RequestMapping("download")
    public String download(Model model, int searchType, Integer organismPred, Integer organismExp) throws FileNotFoundException, IOException {

        RegulationsFile regFiles = new RegulationsFile();
        System.out.println("organismExp: " + organismExp);
        System.out.println("organismPred: " + organismPred);
        System.out.println("searchType: " + searchType);
        List<String> regFileNames = new ArrayList<>();
        List<String> rnaFileNames = new ArrayList<>();
        OperonsFile opFiles = new OperonsFile();
        List<String> operonsFilesNames = new ArrayList<>();
        List<String> filesToZip = new ArrayList<>();
        CytoscapeFile cytoscapeFile = new CytoscapeFile();
        String cytoscapeFileName = "";
        ZipFiles zipFiles = new ZipFiles();
        File file = new File(System.getProperty("user.dir"));
        String zipFile = file.getAbsolutePath() + "/";
        String zipFileName = "";
        List<Organism> organisms = new ArrayList<>();
        OrganismDAO orgDAO = new OrganismDAO();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        ArrayList<RegulatoryInteraction> ris = new ArrayList<>();
        ArrayList<PredictedRegulatoryInteraction> pris = new ArrayList<>();
        RnaFile rnaFileCreator = new RnaFile();
        RnaRegulationViewDAO rnaRegDAO = new RnaRegulationViewDAO();
        List<RnaRegulationView> rnaRegList = new LinkedList<>();
        GenomeDAO genomeDAO = new GenomeDAO();

        //EXPERIMENTAL
        if (searchType == 0) {

            //ALL GENOMES
            if (organismExp == 0) {
                zipFileName = "TemplateOrganismsFiles.zip";
                zipFile += zipFileName;
                System.out.println(zipFileName);
                File fileToCheck = new File(zipFile);

                if (!fileToCheck.exists()) {
                    regFileNames = regFiles.regulationsFileAllOrganisms("model");
                    for (String regFileName : regFileNames) {
                        System.out.println("REG NAMES: " + regFileName);
                        regFileName += ".csv";
                        filesToZip.add(regFileName);
                    }
                    operonsFilesNames = opFiles.operonsFileAllOrganisms("model");
                    for (String operonsFileName : operonsFilesNames) {
                        operonsFileName += ".csv";
                        filesToZip.add(operonsFileName);
                    }

                    organisms = orgDAO.findByType("model");
                    for (Organism organism : organisms) {
                        ris = new ArrayList<>();
                        ris = riDAO.findByOrganism(organism.getId());
                        cytoscapeFileName = cytoscapeFile.experimentalRegSif(ris, organism.getId(), "");
                        cytoscapeFileName += ".sif";
                        filesToZip.add(cytoscapeFileName);
                    }

                    //CREATE HERE.
                    rnaFileNames = rnaFileCreator.bringRnaFiles("model");
                    filesToZip.addAll(rnaFileNames);

                    zipFiles.zipFile(filesToZip, zipFile);
                }

                //BY GENOME
            } else {

                Organism organism = orgDAO.findById(organismExp);
                Genome genome = genomeDAO.findByOrganism(organismExp);

                zipFileName = organism.getGenera().charAt(0) + "_" + organism.getSpecies().charAt(0) + "_" + organism.getStrain() + ".zip";
                System.out.println("zipFileName: " + zipFileName);
                zipFile += zipFileName;
                File fileToCheck = new File(zipFile);

                if (!fileToCheck.exists()) {
                    //System.out.println("Not Exists");
                    regFileNames = regFiles.regulationsFileByOrganism(organismExp);
                    for (String regFileName : regFileNames) {
                        regFileName += ".csv";
                        filesToZip.add(regFileName);
                    }
                    operonsFilesNames = opFiles.operonsFileByOrganism(organismExp);
                    for (String operonsFileName : operonsFilesNames) {
                        operonsFileName += ".csv";
                        filesToZip.add(operonsFileName);
                    }

                    ris = riDAO.findByOrganism(organismExp);
                    cytoscapeFileName = cytoscapeFile.experimentalRegSif(ris, organismExp, "");
                    cytoscapeFileName += ".sif";
                    filesToZip.add(cytoscapeFileName);

                    try {
                        rnaFileNames.add(rnaFileCreator.bringRnaFile(genome.getId()));
                        if (organism.getType().equals("model")) {

                            rnaFileCreator.bringRnaFileExperimental(genome.getId());
                        }
                        filesToZip.addAll(rnaFileNames);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("there are no srnas for this strain.");
                    }

                    zipFiles.zipFile(filesToZip, zipFile);
                }
                //System.out.println("Exists");
            }

            //PREDICTED+EXPERIMENTAL.        
        } else {
            //ALL GENOMES
            if (organismPred == 0) {
                zipFileName = "AllOrganismsFiles.zip";
                // String rnaFile = zipFile;
                // String rnaRegFile = zipFile;
                zipFile += zipFileName;
                File fileToCheck = new File(zipFile);

                if (!fileToCheck.exists()) {
                    regFileNames = regFiles.regulationsFileAllOrganisms("predict");
                    for (String regFileName : regFileNames) {
                        regFileName += ".csv";
                        filesToZip.add(regFileName);
                    }
                    operonsFilesNames = opFiles.operonsFileAllOrganisms("predict");
                    for (String operonsFileName : operonsFilesNames) {
                        operonsFileName += ".csv";
                        filesToZip.add(operonsFileName);
                    }

                    organisms = orgDAO.listAll();
                    for (Organism organism : organisms) {
                        if (organism.getType().equals("model")) {
                            ris = new ArrayList<>();
                            ris = riDAO.findByOrganism(organism.getId());
                            cytoscapeFileName = cytoscapeFile.experimentalRegSif(ris, organism.getId(), "");
                        } else {
                            pris = new ArrayList<>();
                            pris = priDAO.findByOrganism(organism.getId());
                            cytoscapeFileName = cytoscapeFile.predictedRegSif(pris, organism.getId(), "");
                        }

                        cytoscapeFileName += ".sif";
                        filesToZip.add(cytoscapeFileName);

                        Genome genome = genomeDAO.findByOrganism(organism.getId());
                        rnaRegList = (List<RnaRegulationView>) rnaRegDAO.findByGenome(genome.getId());
                        if (rnaRegList.size() > 0) {
                            cytoscapeFileName = cytoscapeFile.sRnaRegSif(rnaRegList, organism.getId(), "");
                            filesToZip.add(cytoscapeFileName);

                        }
                    }

                    try {
                        rnaFileNames = rnaFileCreator.bringRnaFiles("all");
                        filesToZip.addAll(rnaFileNames);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("there are no srnas for this strain.");
                    }

                    zipFiles.zipFile(filesToZip, zipFile);
                }

                //BY GENOME    
            } else {
                Organism organism = orgDAO.findById(organismPred);
                Genome genome = genomeDAO.findByOrganism(organismPred);

                zipFileName = organism.getGenera().charAt(0) + "_" + organism.getSpecies().charAt(0) + "_" + organism.getStrain() + ".zip";
                System.out.println("zipFileName: " + zipFileName);
                zipFile += zipFileName;
                File fileToCheck = new File(zipFile);

                if (!fileToCheck.exists()) {
                    regFileNames = regFiles.regulationsFileByOrganism(organismPred);
                    for (String regFileName : regFileNames) {
                        regFileName += ".csv";
                        filesToZip.add(regFileName);
                    }
                    operonsFilesNames = opFiles.operonsFileByOrganism(organismPred);
                    for (String operonsFileName : operonsFilesNames) {
                        operonsFileName += ".csv";
                        filesToZip.add(operonsFileName);
                    }

                    //Organism organism = orgDAO.findById(organismPred);
                    if (organism.getType().equals("model")) {
                        ris = new ArrayList<>();
                        ris = riDAO.findByOrganism(organism.getId());
                        cytoscapeFileName = cytoscapeFile.experimentalRegSif(ris, organism.getId(), "");
                    } else {
                        pris = new ArrayList<>();
                        pris = priDAO.findByOrganism(organism.getId());
                        cytoscapeFileName = cytoscapeFile.predictedRegSif(pris, organism.getId(), "");
                    }

                    try {
                        //rna files:

                        SmallRnaDAO srnaDAO = new SmallRnaDAO();
                        List<SmallRna> rnas = srnaDAO.findByGenome(genome.getId());
                        //System.out.println("RNAS? "+rnas);
                        if (rnas != null && !rnas.isEmpty()) {
                            rnaFileNames.add(rnaFileCreator.bringRnaFile(genome.getId()));
                            rnaFileNames.add(rnaFileCreator.bringRnaRegFile(genome.getId()));

                            filesToZip.addAll(rnaFileNames);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("there are no srnas for this strain.");
                    }

                    cytoscapeFileName += ".sif";
                    filesToZip.add(cytoscapeFileName);

                    rnaRegList = (List<RnaRegulationView>) rnaRegDAO.findByGenome(genome.getId());
                    if (rnaRegList.size() > 0) {
                        cytoscapeFileName = cytoscapeFile.sRnaRegSif(rnaRegList, organism.getId(), "");
                        filesToZip.add(cytoscapeFileName);
                    }

                    zipFiles.zipFile(filesToZip, zipFile);
                }
            }
        }
        //System.out.println(zipFileName);
        model.addAttribute("zipFileName", zipFileName);
        return "download";
    }
}
