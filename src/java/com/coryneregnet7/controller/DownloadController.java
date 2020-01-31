/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.controller;

import com.coryneregnet7.dao.OrganismDAO;
import com.coryneregnet7.dao.PredictedRegulatoryInteractionDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.model.Organism;
import com.coryneregnet7.model.PredictedRegulatoryInteraction;
import com.coryneregnet7.model.RegulatoryInteraction;
import com.coryneregnet7.processing.output.CytoscapeFile;
import com.coryneregnet7.processing.output.OperonsFile;
import com.coryneregnet7.processing.output.RegulationsFile;
import com.coryneregnet7.processing.output.ZipFiles;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

        if (searchType == 0) {
            if (organismExp == 0) {
                zipFileName = "TemplateOrganismsFiles.zip";
                zipFile += zipFileName;
                File fileToCheck = new File(zipFile);

                if (!fileToCheck.exists()) {
                    regFileNames = regFiles.regulationsFileAllOrganisms("model");
                    for (String regFileName : regFileNames) {
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

                    zipFiles.zipFile(filesToZip, zipFile);
                }
            } else {

                Organism organism = orgDAO.findById(organismExp);
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

                    zipFiles.zipFile(filesToZip, zipFile);
                }
                //System.out.println("Exists");
            }
        } else {
            if (organismPred == 0) {
                zipFileName = "AllOrganismsFiles.zip";
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
                    }

                    zipFiles.zipFile(filesToZip, zipFile);
                }
            } else {
                Organism organism = orgDAO.findById(organismPred);
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

                    cytoscapeFileName += ".sif";
                    filesToZip.add(cytoscapeFileName);

                    zipFiles.zipFile(filesToZip, zipFile);
                }
            }
        }

        model.addAttribute("zipFileName", zipFileName);
        return "download";
    }
}
