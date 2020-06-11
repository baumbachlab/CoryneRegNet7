/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.output;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GeneOperonDAO;
import com.coryneregnet7.dao.OperonDAO;
import com.coryneregnet7.dao.OrganismDAO;
import com.coryneregnet7.dao.PredictedRegulatoryInteractionDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.GeneOperon;
import com.coryneregnet7.model.Operon;
import com.coryneregnet7.model.Organism;
import com.coryneregnet7.model.PredictedRegulatoryInteraction;
import com.coryneregnet7.model.RegulatoryInteraction;
import com.coryneregnet7.model.RnaRegulationView;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author doglas
 */
public class CytoscapeFile {

    public static void main(String[] args) {

        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        //ArrayList<RegulatoryInteraction> ris = (ArrayList<RegulatoryInteraction>) riDAO.findByOrganismAndGene(801, 768105);
        GeneDAO geneDAO = new GeneDAO();
        Gene gene = geneDAO.findById(768105);
        ArrayList<RegulatoryInteraction> ris = (ArrayList<RegulatoryInteraction>) riDAO.listAll();
        ArrayList<PredictedRegulatoryInteraction> pris = (ArrayList<PredictedRegulatoryInteraction>) priDAO.listAll();
        CytoscapeFile experimentalRegSif = new CytoscapeFile();
        experimentalRegSif.predictedRegSif(pris, 802, "");
    }

//    public String cytoscapeFile(int organismId, String geneInterest) {
//
//        OrganismDAO organismDAO = new OrganismDAO();
//        Organism organism = organismDAO.findById(organismId);
//        String cytoscapeFileName = "";
//        List<String> cytoscapeFilesList = new ArrayList<>();
//        File file;
//        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
//        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
//        List<Gene> tfs = new ArrayList<>();
//        List<Gene> tgs = new ArrayList<>();
//        GeneDAO geneDAO = new GeneDAO();
//        Gene gene = new Gene();
//        String geneName = "";
//
//        if (geneInterest != null && !geneInterest.isEmpty()) {
//            gene = geneDAO.findByLocusTag(geneInterest);
//            if (gene.getName() != null && !gene.getName().isEmpty()) {
//                geneName = gene.getName();
//            } else {
//                geneName = gene.getLocusTag();
//            }
//
//            System.out.println("organism: " + gene.getGenome().getOrganism().getGenera() + " " + gene.getGenome().getOrganism().getSpecies() + " " + gene.getGenome().getOrganism().getStrain() + "_" + geneName);
//            cytoscapeFileName = gene.getGenome().getOrganism().getGenera().charAt(0) + "" + gene.getGenome().getOrganism().getSpecies().charAt(0) + "" + gene.getGenome().getOrganism().getStrain() + "_" + geneName + "_cytoscape_file";
//
//        } else {
//            System.out.println("organism: " + organism.getGenera() + " " + organism.getSpecies() + " " + organism.getStrain());
//            cytoscapeFileName = organism.getGenera().charAt(0) + "" + organism.getSpecies().charAt(0) + "" + organism.getStrain() + "_cytoscape_file";
//        }
//
//        System.out.println(cytoscapeFileName);
//        file = new File(System.getProperty("user.dir") + cytoscapeFileName + ".sif");
//        String genes;
//        List<RegulatoryInteraction> ris = new ArrayList<>();
//        List<PredictedRegulatoryInteraction> pris = new ArrayList<>();
//
//        if (file.exists()) {
//            System.out.println("File exists!!!!!!!!!!!!");
//        } else {
//            PrintWriter printWriter = null;
//
//            try {
//                printWriter = new PrintWriter(file);
//                if (organism.getType().equals("model")) {
//
//                    if (geneInterest != null && !geneInterest.isEmpty()) {
//                        ris = riDAO.findByOrganismAndGene(organismId, gene.getId());
//                    } else {
//                        ris = riDAO.findByOrganism(organismId);
//                    }
//
//                    for (int i = 0; i < ris.size(); i++) {
//
//                    }
//
////                    tfs = riDAO.findByOrganismDistinctTF(organismId);
////                    for (Gene tf : tfs) {
////                        tgs = new ArrayList<>();
////                        tgs = riDAO.findByOrganismTF(organismId, tf.getId());
////                        genes = "";
////                        if (tgs.size() > 0) {
////                            genes = regulatedGenes(tgs);
////                            if (tf.getName() != null && !tf.getName().isEmpty()) {
////                                printWriter.println(tf.getName() + "\t" + "Activation" + "\t" + genes);
////                            } else {
////                                printWriter.println(tf.getLocusTag() + "\t" + "Activation" + "\t" + genes);
////                            }
////                        }
////
////                        tgs = new ArrayList<>();
////                        tgs = riDAO.findByOrganismTFAndRole(organismId, tf.getId(), "R");
////                        genes = "";
////                        if (tgs.size() > 0) {
////                            genes = regulatedGenes(tgs);
////                            if (tf.getName() != null && !tf.getName().isEmpty()) {
////                                printWriter.println(tf.getName() + "\t" + "Repression" + "\t" + genes);
////                            } else {
////                                printWriter.println(tf.getLocusTag() + "\t" + "Repression" + "\t" + genes);
////                            }
////                        }
////
////                        String empty = "";
////                        tgs = new ArrayList<>();
////                        tgs = riDAO.findByOrganismTFAndRole(organismId, tf.getId(), empty);
////                        System.out.println("----------------------------FOUND:");
////                        System.out.println(tgs);
////                        genes = "";
////                        if (tgs.size() > 0) {
////                            genes = regulatedGenes(tgs);
////                            if (tf.getName() != null && !tf.getName().isEmpty()) {
////                                printWriter.println(tf.getName() + "\t" + "Sigma factor" + "\t" + genes);
////                            } else {
////                                printWriter.println(tf.getLocusTag() + "\t" + "Sigma factor" + "\t" + genes);
////                            }
////                        }
////                    }
////                } else {
////
////                    tfs = priDAO.findByOrganismDistinctTF(organismId);
////                    for (Gene tf : tfs) {
////                        tgs = new ArrayList<>();
////                        tgs = priDAO.findByOrganismTFAndRole(organismId, tf.getId(), "A");
////                        genes = "";
////                        if (tgs.size() > 0) {
////                            genes = regulatedGenes(tgs);
////                            if (tf.getName() != null && !tf.getName().isEmpty()) {
////                                printWriter.println(tf.getName() + "\t" + "Activation" + "\t" + genes);
////                            } else {
////                                printWriter.println(tf.getLocusTag() + "\t" + "Activation" + "\t" + genes);
////                            }
////                        }
////
////                        tgs = new ArrayList<>();
////                        tgs = priDAO.findByOrganismTFAndRole(organismId, tf.getId(), "R");
////                        genes = "";
////                        if (tgs.size() > 0) {
////                            genes = regulatedGenes(tgs);
////                            if (tf.getName() != null && !tf.getName().isEmpty()) {
////                                printWriter.println(tf.getName() + "\t" + "Repression" + "\t" + genes);
////                            } else {
////                                printWriter.println(tf.getLocusTag() + "\t" + "Repression" + "\t" + genes);
////                            }
////                        }
////
////                        tgs = new ArrayList<>();
////                        tgs = priDAO.findByOrganismTFAndRole(organismId, tf.getId(), "''");
////                        genes = "";
////                        if (tgs.size() > 0) {
////                            genes = regulatedGenes(tgs);
////                            if (tf.getName() != null && !tf.getName().isEmpty()) {
////                                printWriter.println(tf.getName() + "\t" + "Sigma factor" + "\t" + genes);
////                            } else {
////                                printWriter.println(tf.getLocusTag() + "\t" + "Sigma factor" + "\t" + genes);
////                            }
////                        }
////                    }
//                }
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } finally {
//                if (printWriter != null) {
//                    printWriter.close();
//                }
//            }
//        }
//
//        return cytoscapeFileName;
//    }
    public String experimentalRegSif(ArrayList<RegulatoryInteraction> ris, Integer organismId, String geneInterest) {

        ArrayList<ArrayList<String>> regulations = new ArrayList<ArrayList<String>>();
        ArrayList<String> reglation = new ArrayList<>();
        boolean contains;
        String tf;
        String tg;
        String role;
        OrganismDAO organismDAO = new OrganismDAO();
        Organism organism = organismDAO.findById(organismId);
        //System.out.println(organism.toString());
        String cytoscapeFileName = "";
        List<String> cytoscapeFilesList = new ArrayList<>();
        File file;
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        List<Gene> tfs = new ArrayList<>();
        List<Gene> tgs = new ArrayList<>();
        GeneDAO geneDAO = new GeneDAO();
        Gene gene = new Gene();
        String geneName = "";
        String strainName = organism.getStrain().replace(" ", "_");
        strainName = strainName.replace("/", "-");

//        System.out.println("---------------------------");
//        System.out.println("---------------------------");
//        System.out.println("---------------------------");
//        System.out.println("geneInterest: " + geneInterest);
//        System.out.println("organismId "+organismId);
//        System.out.println("organism "+organism.toString());
//        for (RegulatoryInteraction ri : ris) {
//            System.out.println("RIS => "+ri.toString());
//        }
//        
//        System.out.println("------------");
//        System.out.println("------------");
//        System.out.println("------------");
        if (geneInterest != null && !geneInterest.isEmpty()) {

            cytoscapeFileName = organism.getGenera().charAt(0) + "_" + organism.getSpecies().charAt(0) + "_" + strainName + "_" + geneInterest;

        } else {
            //System.out.println("organism: " + organism.getGenera().charAt(0) + "_" + organism.getSpecies().charAt(0) + "_" + organism.getStrain());
            cytoscapeFileName = organism.getGenera().charAt(0) + "_" + organism.getSpecies().charAt(0) + "_" + strainName;
        }

        cytoscapeFileName = cytoscapeFileName.replace("/", "-");

        //System.out.println(cytoscapeFileName);
        //System.out.println(System.getProperty("user.dir"));
        file = new File(System.getProperty("user.dir") + "/" + cytoscapeFileName + ".sif");
        //System.out.println(file.getAbsolutePath());
        String genes;

        if (file.exists()) {
            System.out.println("File exists!!!!!!!!!!!!");
        } else {
            PrintWriter printWriter = null;

            try {
                printWriter = new PrintWriter(file);

                for (int i = 0; i < ris.size(); i++) {
                    contains = false;
                    if (!ris.get(i).getCorrespondentTranscriptionFactor().getName().isEmpty()) {
                        tf = ris.get(i).getCorrespondentTranscriptionFactor().getName();
                    } else {
                        tf = ris.get(i).getCorrespondentTranscriptionFactor().getLocusTag();
                    }

                    if (!ris.get(i).getCorrespondentTargetGene().getName().isEmpty()) {
                        tg = ris.get(i).getCorrespondentTargetGene().getName();
                    } else {
                        tg = ris.get(i).getCorrespondentTargetGene().getLocusTag();
                    }

                    role = ris.get(i).getRole();

                    //System.out.println("regulations.size(): " + regulations.size());
                    for (int j = 0; j < regulations.size(); j++) {
                        reglation = new ArrayList<>();
                        reglation = regulations.get(j);
                        //System.out.println("reglation:" + reglation.get(0));
                        if (reglation.get(0).equals(tf) && reglation.get(1).equals(role)) {
                            contains = true;
                            regulations.remove(reglation);
                            reglation.add(tg);
                            regulations.add(reglation);
                        }
                    }

                    if (contains == false) {
                        reglation = new ArrayList<>();
                        reglation.add(tf);
                        reglation.add(role);
                        reglation.add(tg);
                        regulations.add(reglation);
                    }
                    reglation = new ArrayList<>();
                }

                String regInfo;
                for (int i = 0; i < regulations.size(); i++) {
                    //System.out.println(regulations.get(i));
                    regInfo = "";
                    for (int j = 0; j < regulations.get(i).size(); j++) {
                        if (j > 1) {
                            regInfo += "\t" + regulations.get(i).get(j);
                        } else if (j == 0) {
                            regInfo = regulations.get(i).get(j);
                        } else if (j == 1) {
                            role = "";
                            if (regulations.get(i).get(j).equals("A")) {
                                role = "Activation";
                            } else if (regulations.get(i).get(j).equals("R")) {
                                role = "Repression";
                            } else if (regulations.get(i).get(j).equals("D")) {
                                role = "Dual";
                            } else {
                                role = "Sigma";
                            }
                            regInfo += "\t" + role;
                        }
                    }
                    //System.out.println(regInfo);
                    printWriter.println(regInfo);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (printWriter != null) {
                    printWriter.close();
                }
            }
        }

        return cytoscapeFileName;
    }

    public String predictedRegSif(ArrayList<PredictedRegulatoryInteraction> pris, Integer organismId, String geneInterest) {

        ArrayList<ArrayList<String>> regulations = new ArrayList<ArrayList<String>>();
        ArrayList<String> reglation = new ArrayList<>();
        boolean contains;
        String tf;
        String tg;
        String role;
        OrganismDAO organismDAO = new OrganismDAO();
        Organism organism = organismDAO.findById(organismId);
        String cytoscapeFileName = "";
        List<String> cytoscapeFilesList = new ArrayList<>();
        File file;
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        List<Gene> tfs = new ArrayList<>();
        List<Gene> tgs = new ArrayList<>();
        GeneDAO geneDAO = new GeneDAO();
        Gene gene = new Gene();
        String geneName = "";
        String strainName = organism.getStrain().replace(" ", "_");
        strainName = strainName.replace("/", "-");

        //System.out.println("geneInterest: " + geneInterest);
        if (geneInterest != null && !geneInterest.isEmpty()) {

            //System.out.println("organism: " + gene.getGenome().getOrganism().getGenera().charAt(0) + "_" + gene.getGenome().getOrganism().getSpecies().charAt(0) + "_" + gene.getGenome().getOrganism().getStrain() + "_" + geneName);
            cytoscapeFileName = organism.getGenera().charAt(0) + "_" + organism.getSpecies().charAt(0) + "_" + strainName + "_" + geneInterest;

        } else {
            //System.out.println("organism: " + organism.getGenera().charAt(0) + "_" + organism.getSpecies().charAt(0) + "_" + organism.getStrain());
            cytoscapeFileName = organism.getGenera().charAt(0) + "_" + organism.getSpecies().charAt(0) + "_" + strainName;
        }

        cytoscapeFileName = cytoscapeFileName.replace("/", "-");
        //System.out.println(cytoscapeFileName);
        //System.out.println(System.getProperty("user.dir"));
        file = new File(System.getProperty("user.dir") + "/" + cytoscapeFileName + ".sif");
        //System.out.println(file.getAbsolutePath());
        String genes;

        if (file.exists()) {
            System.out.println("File exists!!!!!!!!!!!!");
        } else {
            PrintWriter printWriter = null;

            try {
                printWriter = new PrintWriter(file);

                for (int i = 0; i < pris.size(); i++) {
                    contains = false;
                    if (!pris.get(i).getHomologousTranscriptionFactor().getName().isEmpty()) {
                        tf = pris.get(i).getHomologousTranscriptionFactor().getName();
                    } else {
                        tf = pris.get(i).getHomologousTranscriptionFactor().getLocusTag();
                    }

                    if (!pris.get(i).getHomologousTargetGene().getName().isEmpty()) {
                        tg = pris.get(i).getHomologousTargetGene().getName();
                    } else {
                        tg = pris.get(i).getHomologousTargetGene().getLocusTag();
                    }

                    role = pris.get(i).getPredictedRole();

                    //System.out.println("regulations.size(): " + regulations.size());
                    for (int j = 0; j < regulations.size(); j++) {
                        reglation = new ArrayList<>();
                        reglation = regulations.get(j);
                        //System.out.println("reglation:" + reglation.get(0));
                        if (reglation.get(0).equals(tf) && reglation.get(1).equals(role)) {
                            contains = true;
                            regulations.remove(reglation);
                            reglation.add(tg);
                            regulations.add(reglation);
                            break;
                        }
                    }

                    if (contains == false) {
                        reglation = new ArrayList<>();
                        reglation.add(tf);
                        reglation.add(role);
                        reglation.add(tg);
                        regulations.add(reglation);
                    }
                    reglation = new ArrayList<>();
                }

                String regInfo;
                for (int i = 0; i < regulations.size(); i++) {
                    //System.out.println(regulations.get(i));
                    regInfo = "";
                    for (int j = 0; j < regulations.get(i).size(); j++) {
                        if (j > 1) {
                            regInfo += "\t" + regulations.get(i).get(j);
                        } else if (j == 0) {
                            regInfo = regulations.get(i).get(j);
                        } else if (j == 1) {
                            role = "";
                            if (regulations.get(i).get(j).equals("A")) {
                                role = "Activation";
                            } else if (regulations.get(i).get(j).equals("R")) {
                                role = "Repression";
                            } else if (regulations.get(i).get(j).equals("D")) {
                                role = "Dual";
                            } else {
                                role = "Sigma";
                            }
                            regInfo += "\t" + role;
                        }
                    }
                    //System.out.println(regInfo);
                    printWriter.println(regInfo);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (printWriter != null) {
                    printWriter.close();
                }
            }
        }

        return cytoscapeFileName;
    }

    public String sRnaRegSif(List<RnaRegulationView> rnaRegList, Integer organismId, String geneInterest) {

        ArrayList<ArrayList<String>> regulations = new ArrayList<ArrayList<String>>();
        ArrayList<String> reglation = new ArrayList<>();
        boolean contains;
        String sRNA;
        String tg;
        String role;
        OrganismDAO organismDAO = new OrganismDAO();
        Organism organism = organismDAO.findById(organismId);
        String cytoscapeFileName = "";
        List<String> cytoscapeFilesList = new ArrayList<>();
        File file;
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        List<Gene> tfs = new ArrayList<>();
        List<Gene> tgs = new ArrayList<>();
        GeneDAO geneDAO = new GeneDAO();
        Gene gene = new Gene();
        String geneName = "";
        String strainName = organism.getStrain().replace(" ", "_");
        strainName = strainName.replace("/", "-");

        //System.out.println("geneInterest: " + geneInterest);
        if (geneInterest != null && !geneInterest.isEmpty()) {

            //System.out.println("organism: " + gene.getGenome().getOrganism().getGenera().charAt(0) + "_" + gene.getGenome().getOrganism().getSpecies().charAt(0) + "_" + gene.getGenome().getOrganism().getStrain() + "_" + geneName);
            cytoscapeFileName = organism.getGenera().charAt(0) + "_" + organism.getSpecies().charAt(0) + "_" + strainName + "_" + geneInterest;

        } else {
            //System.out.println("organism: " + organism.getGenera().charAt(0) + "_" + organism.getSpecies().charAt(0) + "_" + organism.getStrain());
            cytoscapeFileName = organism.getGenera().charAt(0) + "_" + organism.getSpecies().charAt(0) + "_" + strainName;
        }
        contains = true;

        cytoscapeFileName = cytoscapeFileName.replace("/", "-");
        cytoscapeFileName += "_rna";
        //System.out.println(cytoscapeFileName);
        //System.out.println(System.getProperty("user.dir"));
        file = new File(System.getProperty("user.dir") + "/" + cytoscapeFileName + ".sif");
        //System.out.println(file.getAbsolutePath());
        String genes;

        //if (file.exists()) {
        if (!file.exists()) {
            System.out.println("File exists!!!!!!!!!!!!");
        } else {
            PrintWriter printWriter = null;

            try {
                printWriter = new PrintWriter(file);

                for (int i = 0; i < rnaRegList.size(); i++) {
                    contains = false;
                    sRNA = rnaRegList.get(i).getSrna().getLocusTag();

                    if (!rnaRegList.get(i).getTg().getName().isEmpty()) {
                        tg = rnaRegList.get(i).getTg().getName();
                    } else {
                        tg = rnaRegList.get(i).getTg().getLocusTag();
                    }

                    System.out.println("------------sRNA: " + sRNA);
                    System.out.println("------------TG: " + tg);
                    System.out.println("-");
                    System.out.println("-");

                    //System.out.println("regulations.size(): " + regulations.size());
                    for (int j = 0; j < regulations.size(); j++) {
                        reglation = new ArrayList<>();
                        reglation = regulations.get(j);
                        System.out.println("regulations.get(j): " + regulations.get(j));
                        System.out.println("sRNA:" + reglation.get(0));
                        System.out.println("role:" + reglation.get(1));
                        System.out.println("TG: " + tg + "\n");
                        System.out.println("=");
                        System.out.println("=");
                        if (reglation.get(0).equals(sRNA)) {
                            System.out.println("tem!");
                            contains = true;
                            System.out.println("OLD REGULATION: "+reglation);
                            regulations.remove(reglation);
                            reglation.add(tg);
                            regulations.add(reglation);
                            System.out.println("NOVEL REGULATION: "+reglation);
                            break;
                        }else{
                            System.out.println("nao tem!");
                        }
                    }

                    if (contains == false) {
                        System.out.println("add new regulation item. ");
                        reglation = new ArrayList<>();
                        reglation.add(sRNA);
                        reglation.add("sRNA");
                        reglation.add(tg);
                        regulations.add(reglation);
                    }
                    reglation = new ArrayList<>();
                }

                String regInfo;
                for (int i = 0; i < regulations.size(); i++) {
                    //System.out.println(regulations.get(i));
                    regInfo = "";
                    for (int j = 0; j < regulations.get(i).size(); j++) {
                        if (j > 1) {
                            regInfo += "\t" + regulations.get(i).get(j);
                        } else if (j == 0) {
                            regInfo = regulations.get(i).get(j);
                        } else if (j == 1) {
                            role = regulations.get(i).get(j);
                            regInfo += "\t" + role;
                        }
                    }
                    //System.out.println(regInfo);
                    printWriter.println(regInfo);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (printWriter != null) {
                    printWriter.close();
                }
            }
        }

        return cytoscapeFileName;
    }
//
//    public String regulatedGenes(List<Gene> tgs) {
//
//        String genes = "";
//        for (int i = 0; i < tgs.size(); i++) {
//            if (i == tgs.size() - 1) {
//                if (tgs.get(i).getName() != null && !tgs.get(i).getName().isEmpty()) {
//                    genes += tgs.get(i).getName();
//                } else {
//                    genes += tgs.get(i).getLocusTag();
//                }
//            } else {
//                if (tgs.get(i).getName() != null && !tgs.get(i).getName().isEmpty()) {
//                    genes += tgs.get(i).getName() + "\t";
//                } else {
//                    genes += tgs.get(i).getLocusTag() + "\t";
//                }
//            }
//        }
//        return genes;
//    }

}
