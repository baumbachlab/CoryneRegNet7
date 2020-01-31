/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.output;

import com.coryneregnet7.dao.BindingSiteDAO;
import com.coryneregnet7.dao.GeneOperonDAO;
import com.coryneregnet7.dao.HomologousDAO;
import com.coryneregnet7.dao.OperonDAO;
import com.coryneregnet7.dao.OrganismDAO;
import com.coryneregnet7.dao.PredictedRegulatoryInteractionDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.model.BindingSite;
import com.coryneregnet7.model.GeneOperon;
import com.coryneregnet7.model.Homologous;
import com.coryneregnet7.model.Operon;
import com.coryneregnet7.model.Organism;
import com.coryneregnet7.model.PredictedRegulatoryInteraction;
import com.coryneregnet7.model.RegulatoryInteraction;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author doglas
 */
public class RegulationsFile {
//
//    public String regulationsFileAllModelOrganisms() throws IOException {
//
//        OrganismDAO organismDAO = new OrganismDAO();
//        List<Organism> organisms = organismDAO.findByType("model");
//        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
//        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
//        ArrayList<RegulatoryInteraction> ris = new ArrayList<>();
//        ArrayList<PredictedRegulatoryInteraction> pris = new ArrayList<>();
//        GeneOperonDAO geneOpDAO = new GeneOperonDAO();
//        GeneOperon geneOp = new GeneOperon();
//        OperonDAO opDAO = new OperonDAO();
//        Operon operon = new Operon();
//        BindingSiteDAO bsDAO = new BindingSiteDAO();
//        List<BindingSite> bss = new ArrayList<>();
//        File file = null;
//        String zipFile = "/home/doglas/NetBeansProjects/CoryneRegNet7/web/resources/regulationsModelOrganisms.zip";
//        String zipFileName = "regulationsModelOrganisms.zip";
//        String tfName, tgName, bindingSite, isSigmaFactor, operonName;
//        ZipFiles zipFiles = new ZipFiles();
//
//        for (Organism organism : organisms) {
//            ris = riDAO.findByOrganism(organism.getId());
//            String fileName = organism.getGenera().charAt(0) + "" + organism.getSpecies().charAt(0) + "" + organism.getStrain() + "_regulations";
//            System.out.println(fileName);
//            file = new File("/home/doglas/NetBeansProjects/CoryneRegNet7/web/resources/" + fileName + ".csv");
//
//            if (file.exists()) {
//                System.out.println("File exists!!!!!!!!!!!!");
//            } else {
//                PrintWriter printWriter = null;
//
//                try {
//                    printWriter = new PrintWriter(file);
//                    printWriter.println("TF_locusTag,TF_altLocusTag,TF_name,TF_role,TG_locusTag,TG_altLocusTag,TG_name,Operon,Binding_site,Role,Is_sigma_factor,Evidence,PMID,Source");
//                    for (RegulatoryInteraction ri : ris) {
//                        if (ri.getCorrespondentTranscriptionFactor().getName().isEmpty() || ri.getCorrespondentTranscriptionFactor().getName() == "''" || ri.getCorrespondentTranscriptionFactor().getName() == "''") {
//                            tfName = ri.getCorrespondentTranscriptionFactor().getLocusTag();
//                        } else {
//                            tfName = ri.getCorrespondentTranscriptionFactor().getName();
//                        }
//
//                        if (ri.getCorrespondentTargetGene().getName().isEmpty() || ri.getCorrespondentTargetGene().getName() == "''" || ri.getCorrespondentTargetGene().getName() == "''") {
//                            tgName = ri.getCorrespondentTargetGene().getLocusTag();
//                        } else {
//                            tgName = ri.getCorrespondentTargetGene().getName();
//                        }
//
//                        geneOp = new GeneOperon();
//                        geneOp = geneOpDAO.findByGene(ri.getCorrespondentTargetGene().getId());
//                        operonName = "";
//                        if (geneOp != null) {
//                            operon = opDAO.findById(geneOp.getGeneOperonPK().getOperon());
//                            operonName = operon.getName();
//                        }
//
//                        bss = new ArrayList<>();
//                        bss = bsDAO.findByRegularotyInteraction(ri.getId());
//                        bindingSite = "";
//                        if (bss != null) {
//
//                            for (int i = 0; i < bss.size(); i++) {
//                                if (i == 0) {
//                                    bindingSite = bss.get(i).getSequence();
//                                } else {
//                                    bindingSite += ";" + bss.get(i).getSequence();
//                                }
//                            }
//                        }
//
//                        if (ri.getCorrespondentTranscriptionFactor().getRole().isEmpty() || ri.getCorrespondentTranscriptionFactor().getRole().equals("''") || ri.getCorrespondentTranscriptionFactor().getRole().equals("")) {
//                            isSigmaFactor = "yes";
//                        } else {
//                            isSigmaFactor = "no";
//                        }
//
//                        printWriter.println(ri.getCorrespondentTranscriptionFactor().getLocusTag() + "," + ri.getCorrespondentTranscriptionFactor().getAlternativeLocusTag() + "," + tfName + "," + ri.getCorrespondentTranscriptionFactor().getRole() + "," + ri.getCorrespondentTargetGene().getLocusTag() + "," + ri.getCorrespondentTargetGene().getAlternativeLocusTag() + "," + tgName + "," + operonName + "," + bindingSite + "," + ri.getRole() + "," + isSigmaFactor + "," + ri.getEvidence() + "," + ri.getPmid() + "," + ri.getSource());
//                    }
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } finally {
//                    if (printWriter != null) {
//                        printWriter.close();
//                    }
//                }
//            }
//        }
//        zipFiles.zipFile(file, zipFile);
//        return zipFileName;
//    }

    public List regulationsFileAllOrganisms(String type) throws FileNotFoundException {

        OrganismDAO organismDAO = new OrganismDAO();
        List<Organism> organisms = new ArrayList();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        ArrayList<RegulatoryInteraction> ris = new ArrayList<>();
        ArrayList<PredictedRegulatoryInteraction> pris = new ArrayList<>();
        GeneOperonDAO geneOpDAO = new GeneOperonDAO();
        GeneOperon geneOp = new GeneOperon();
        OperonDAO opDAO = new OperonDAO();
        Operon operon = new Operon();
        BindingSiteDAO bsDAO = new BindingSiteDAO();
        List<BindingSite> bss = new ArrayList<>();
        File file;
        String tfName, tgName, bindingSite, isSigmaFactor, operonName, evidence;
        String fileName = "";
        List<String> regFilesList = new ArrayList<>();

        if (type.equals("model")) {
            organisms = organismDAO.findByType("model");
        } else {
            organisms = organismDAO.listAll();
        }

        for (Organism organism : organisms) {
            System.out.println("organism: " + organism.getGenera() + " " + organism.getSpecies() + " " + organism.getStrain());
            fileName = organism.getGenera().charAt(0) + "_" + organism.getSpecies().charAt(0) + "_" + organism.getStrain() + "_regulations";
            fileName = fileName.replace("/", "-");
            fileName = fileName.replace(" ", "_");
            //System.out.println(fileName);

            file = new File(System.getProperty("user.dir") + "/" + fileName + ".csv");
            regFilesList.add(fileName);
            System.out.println("file: " + file);

            if (file.exists()) {
                System.out.println("File exists!!!!!!!!!!!!");
            } else {
                PrintWriter printWriter = null;

                try {
                    printWriter = new PrintWriter(file);
                    printWriter.println("TF_locusTag,TF_altLocusTag,TF_name,TF_role,TG_locusTag,TG_altLocusTag,TG_name,Operon,Binding_site,Role,Is_sigma_factor,Evidence,PMID,Source");

                    if (organism.getType().equals("model")) {
                        ris = riDAO.findByOrganism(organism.getId());

                        for (RegulatoryInteraction ri : ris) {
                            if (ri.getCorrespondentTranscriptionFactor().getName().isEmpty() || ri.getCorrespondentTranscriptionFactor().getName() == "''" || ri.getCorrespondentTranscriptionFactor().getName() == "''") {
                                tfName = ri.getCorrespondentTranscriptionFactor().getLocusTag();
                            } else {
                                tfName = ri.getCorrespondentTranscriptionFactor().getName();
                            }

                            if (ri.getCorrespondentTargetGene().getName().isEmpty() || ri.getCorrespondentTargetGene().getName() == "''" || ri.getCorrespondentTargetGene().getName() == "''") {
                                tgName = ri.getCorrespondentTargetGene().getLocusTag();
                            } else {
                                tgName = ri.getCorrespondentTargetGene().getName();
                            }

                            geneOp = new GeneOperon();
                            geneOp = geneOpDAO.findByGene(ri.getCorrespondentTargetGene().getId());
                            operonName = "";
                            if (geneOp != null) {
                                operon = opDAO.findById(geneOp.getGeneOperonPK().getOperon());
                                operonName = operon.getName();
                            }

                            bss = new ArrayList<>();
                            bss = bsDAO.findByRegularotyInteraction(ri.getId());
                            bindingSite = "";
                            if (bss != null) {

                                for (int i = 0; i < bss.size(); i++) {
                                    if (i == 0) {
                                        bindingSite = bss.get(i).getSequence();
                                    } else {
                                        bindingSite += ";" + bss.get(i).getSequence();
                                    }
                                }
                            }

                            if (ri.getCorrespondentTranscriptionFactor().getRole().isEmpty() || ri.getCorrespondentTranscriptionFactor().getRole().equals("''") || ri.getCorrespondentTranscriptionFactor().getRole().equals("")) {
                                isSigmaFactor = "yes";
                            } else {
                                isSigmaFactor = "no";
                            }

                            System.out.println("bindingSite:" + bindingSite);
                            printWriter.println(ri.getCorrespondentTranscriptionFactor().getLocusTag() + "," + ri.getCorrespondentTranscriptionFactor().getAlternativeLocusTag() + "," + tfName + "," + ri.getCorrespondentTranscriptionFactor().getRole() + "," + ri.getCorrespondentTargetGene().getLocusTag() + "," + ri.getCorrespondentTargetGene().getAlternativeLocusTag() + "," + tgName + "," + operonName + "," + bindingSite + "," + ri.getRole() + "," + isSigmaFactor + "," + ri.getEvidence() + "," + ri.getPmid() + "," + ri.getSource());
                        }
                    } else {
                        pris = priDAO.findByOrganism(organism.getId());
                        for (PredictedRegulatoryInteraction pri : pris) {
                            if (pri.getHomologousTranscriptionFactor().getName().isEmpty() || pri.getHomologousTranscriptionFactor().getName() == "''" || pri.getHomologousTranscriptionFactor().getName() == "''") {
                                tfName = pri.getHomologousTranscriptionFactor().getLocusTag();
                            } else {
                                tfName = pri.getHomologousTranscriptionFactor().getName();
                            }

                            if (pri.getHomologousTargetGene().getName().isEmpty() || pri.getHomologousTargetGene().getName() == "''" || pri.getHomologousTargetGene().getName() == "''") {
                                tgName = pri.getHomologousTargetGene().getLocusTag();
                            } else {
                                tgName = pri.getHomologousTargetGene().getName();
                            }

                            geneOp = new GeneOperon();
                            geneOp = geneOpDAO.findByGene(pri.getHomologousTargetGene().getId());
                            operonName = "";
                            if (geneOp != null) {
                                operon = opDAO.findById(geneOp.getGeneOperonPK().getOperon());
                                operonName = operon.getName();
                            }

                            if (pri.getPredictedBindingSite() == null) {
                                bindingSite = "";
                            } else {
                                bindingSite = pri.getPredictedBindingSite().getSequence();
                            }

                            if (pri.getHomologousTranscriptionFactor().getRole().isEmpty() || pri.getHomologousTranscriptionFactor().getRole().equals("''") || pri.getHomologousTranscriptionFactor().getRole().equals("")) {
                                isSigmaFactor = "yes";
                            } else {
                                isSigmaFactor = "no";
                            }

                            if (pri.getHomologousTranscriptionFactor().getName().isEmpty() || pri.getHomologousTranscriptionFactor().getName().equals("''") || pri.getHomologousTranscriptionFactor().getName().equals("")) {
                                if (pri.getHomologousTargetGene().getName().isEmpty() || pri.getHomologousTargetGene().getName().equals("''") || pri.getHomologousTargetGene().getName().equals("")) {
                                    evidence = pri.getRegulatoryInteraction().getCorrespondentTranscriptionFactor().getLocusTag() + " -> " + pri.getRegulatoryInteraction().getCorrespondentTargetGene().getLocusTag();
                                } else {
                                    evidence = pri.getRegulatoryInteraction().getCorrespondentTranscriptionFactor().getLocusTag() + " -> " + pri.getRegulatoryInteraction().getCorrespondentTargetGene().getName();
                                }
                            } else {
                                if (pri.getHomologousTargetGene().getName().isEmpty() || pri.getHomologousTargetGene().getName().equals("''") || pri.getHomologousTargetGene().getName().equals("")) {
                                    evidence = pri.getRegulatoryInteraction().getCorrespondentTranscriptionFactor().getName() + " -> " + pri.getRegulatoryInteraction().getCorrespondentTargetGene().getLocusTag();
                                } else {
                                    evidence = pri.getRegulatoryInteraction().getCorrespondentTranscriptionFactor().getName() + " -> " + pri.getRegulatoryInteraction().getCorrespondentTargetGene().getName();
                                }
                            }

                            System.out.println("bindingSite:" + bindingSite);
                            printWriter.println(pri.getHomologousTranscriptionFactor().getLocusTag() + "," + pri.getHomologousTranscriptionFactor().getAlternativeLocusTag() + "," + tfName + "," + pri.getHomologousTranscriptionFactor().getRole() + "," + pri.getHomologousTargetGene().getLocusTag() + "," + pri.getHomologousTargetGene().getAlternativeLocusTag() + "," + tgName + "," + operonName + "," + bindingSite + "," + pri.getPredictedRole() + "," + isSigmaFactor + "," + evidence + "," + pri.getRegulatoryInteraction().getPmid() + "," + "CoryneRegNet7");
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    if (printWriter != null) {
                        printWriter.close();
                    }
                }
            }
        }
        return regFilesList;
    }

    public List regulationsFileByOrganism(int organismId) throws FileNotFoundException {

        OrganismDAO organismDAO = new OrganismDAO();
        Organism organism = organismDAO.findById(organismId);
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        ArrayList<RegulatoryInteraction> ris = new ArrayList<>();
        ArrayList<PredictedRegulatoryInteraction> pris = new ArrayList<>();
        GeneOperonDAO geneOpDAO = new GeneOperonDAO();
        GeneOperon geneOp = new GeneOperon();
        OperonDAO opDAO = new OperonDAO();
        Operon operon = new Operon();
        BindingSiteDAO bsDAO = new BindingSiteDAO();
        List<BindingSite> bss = new ArrayList<>();
        File file;
        String tfName, tgName, bindingSite, isSigmaFactor, operonName, evidence;
        List<String> regFilesList = new ArrayList();

        System.out.println("organism: " + organism.getGenera() + "_" + organism.getSpecies() + "_" + organism.getStrain());
        String fileName = organism.getGenera().charAt(0) + "_" + organism.getSpecies().charAt(0) + "_" + organism.getStrain() + "_regulations";
        fileName = fileName.replace("/", "-");
        fileName = fileName.replace(" ", "_");
        System.out.println(fileName);
        file = new File(System.getProperty("user.dir") + "/" + fileName + ".csv");
        regFilesList.add(fileName);

        if (file.exists()) {
            System.out.println("File exists!!!!!!!!!!!!");
        } else {
            PrintWriter printWriter = null;

            try {
                printWriter = new PrintWriter(file);
                printWriter.println("TF_locusTag,TF_altLocusTag,TF_name,TF_role,TG_locusTag,TG_altLocusTag,TG_name,Operon,Binding_site,Role,Is_sigma_factor,Evidence,PMID,Source");

                if (organism.getType().equals("model")) {
                    ris = riDAO.findByOrganism(organism.getId());

                    for (RegulatoryInteraction ri : ris) {
                        if (ri.getCorrespondentTranscriptionFactor().getName().isEmpty() || ri.getCorrespondentTranscriptionFactor().getName() == "''" || ri.getCorrespondentTranscriptionFactor().getName() == "''") {
                            tfName = ri.getCorrespondentTranscriptionFactor().getLocusTag();
                        } else {
                            tfName = ri.getCorrespondentTranscriptionFactor().getName();
                        }

                        if (ri.getCorrespondentTargetGene().getName().isEmpty() || ri.getCorrespondentTargetGene().getName() == "''" || ri.getCorrespondentTargetGene().getName() == "''") {
                            tgName = ri.getCorrespondentTargetGene().getLocusTag();
                        } else {
                            tgName = ri.getCorrespondentTargetGene().getName();
                        }

                        geneOp = new GeneOperon();
                        geneOp = geneOpDAO.findByGene(ri.getCorrespondentTargetGene().getId());
                        operonName = "";
                        if (geneOp != null) {
                            operon = opDAO.findById(geneOp.getGeneOperonPK().getOperon());
                            operonName = operon.getName();
                        }

                        bss = new ArrayList<>();
                        bss = bsDAO.findByRegularotyInteraction(ri.getId());
                        bindingSite = "";
                        if (bss != null) {

                            for (int i = 0; i < bss.size(); i++) {
                                if (i == 0) {
                                    bindingSite = bss.get(i).getSequence();
                                } else {
                                    bindingSite += ";" + bss.get(i).getSequence();
                                }
                            }
                        }

                        if (ri.getCorrespondentTranscriptionFactor().getRole().isEmpty() || ri.getCorrespondentTranscriptionFactor().getRole().equals("''") || ri.getCorrespondentTranscriptionFactor().getRole().equals("")) {
                            isSigmaFactor = "yes";
                        } else {
                            isSigmaFactor = "no";
                        }

                        printWriter.println(ri.getCorrespondentTranscriptionFactor().getLocusTag() + "," + ri.getCorrespondentTranscriptionFactor().getAlternativeLocusTag() + "," + tfName + "," + ri.getCorrespondentTranscriptionFactor().getRole() + "," + ri.getCorrespondentTargetGene().getLocusTag() + "," + ri.getCorrespondentTargetGene().getAlternativeLocusTag() + "," + tgName + "," + operonName + "," + bindingSite + "," + ri.getRole() + "," + isSigmaFactor + "," + ri.getEvidence() + "," + ri.getPmid() + "," + ri.getSource());
                    }
                } else {
                    pris = priDAO.findByOrganism(organism.getId());
                    for (PredictedRegulatoryInteraction pri : pris) {
                        if (pri.getHomologousTranscriptionFactor().getName().isEmpty() || pri.getHomologousTranscriptionFactor().getName() == "''" || pri.getHomologousTranscriptionFactor().getName() == "''") {
                            tfName = pri.getHomologousTranscriptionFactor().getLocusTag();
                        } else {
                            tfName = pri.getHomologousTranscriptionFactor().getName();
                        }

                        if (pri.getHomologousTargetGene().getName().isEmpty() || pri.getHomologousTargetGene().getName() == "''" || pri.getHomologousTargetGene().getName() == "''") {
                            tgName = pri.getHomologousTargetGene().getLocusTag();
                        } else {
                            tgName = pri.getHomologousTargetGene().getName();
                        }

                        geneOp = new GeneOperon();
                        geneOp = geneOpDAO.findByGene(pri.getHomologousTargetGene().getId());
                        operonName = "";
                        if (geneOp != null) {
                            operon = opDAO.findById(geneOp.getGeneOperonPK().getOperon());
                            operonName = operon.getName();
                        }

                        if (pri.getHomologousTranscriptionFactor().getRole().isEmpty() || pri.getHomologousTranscriptionFactor().getRole().equals("''") || pri.getHomologousTranscriptionFactor().getRole().equals("")) {
                            isSigmaFactor = "yes";
                        } else {
                            isSigmaFactor = "no";
                        }

                        if (pri.getHomologousTranscriptionFactor().getName().isEmpty() || pri.getHomologousTranscriptionFactor().getName().equals("''") || pri.getHomologousTranscriptionFactor().getName().equals("")) {
                            if (pri.getHomologousTargetGene().getName().isEmpty() || pri.getHomologousTargetGene().getName().equals("''") || pri.getHomologousTargetGene().getName().equals("")) {
                                evidence = pri.getRegulatoryInteraction().getCorrespondentTranscriptionFactor().getLocusTag() + " -> " + pri.getRegulatoryInteraction().getCorrespondentTargetGene().getLocusTag();
                            } else {
                                evidence = pri.getRegulatoryInteraction().getCorrespondentTranscriptionFactor().getLocusTag() + " -> " + pri.getRegulatoryInteraction().getCorrespondentTargetGene().getName();
                            }
                        } else {
                            if (pri.getHomologousTargetGene().getName().isEmpty() || pri.getHomologousTargetGene().getName().equals("''") || pri.getHomologousTargetGene().getName().equals("")) {
                                evidence = pri.getRegulatoryInteraction().getCorrespondentTranscriptionFactor().getName() + " -> " + pri.getRegulatoryInteraction().getCorrespondentTargetGene().getLocusTag();
                            } else {
                                evidence = pri.getRegulatoryInteraction().getCorrespondentTranscriptionFactor().getName() + " -> " + pri.getRegulatoryInteraction().getCorrespondentTargetGene().getName();
                            }
                        }

                        if (pri.getPredictedBindingSite() == null) {
                            bindingSite = "";
                        } else {
                            bindingSite = pri.getPredictedBindingSite().getSequence();
                        }

                        printWriter.println(pri.getHomologousTranscriptionFactor().getLocusTag() + "," + pri.getHomologousTranscriptionFactor().getAlternativeLocusTag() + "," + tfName + "," + pri.getHomologousTranscriptionFactor().getRole() + "," + pri.getHomologousTargetGene().getLocusTag() + "," + pri.getHomologousTargetGene().getAlternativeLocusTag() + "," + tgName + "," + operonName + "," + bindingSite + "," + pri.getPredictedRole() + "," + isSigmaFactor + "," + evidence + "," + pri.getRegulatoryInteraction().getPmid() + "," + "CoryneRegNet7");
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (printWriter != null) {
                    printWriter.close();
                }
            }
        }

        return regFilesList;
    }
}
