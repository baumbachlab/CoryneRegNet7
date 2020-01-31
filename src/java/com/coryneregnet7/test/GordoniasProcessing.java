/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.BindingSiteDAO;
import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.HomologousDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.model.BindingSite;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Homologous;
import com.coryneregnet7.model.RegulatoryInteraction;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariana
 */
public class GordoniasProcessing {

    public static void main(String[] args) {
        GordoniasProcessing gp = new GordoniasProcessing();
        gp.setBsPositions();
    }

    public void setBsPositions() {

        BufferedReader br = null;
        FileReader fr = null;

        try {

            String[] fileNames = {"all-homologs-results_genome_glxR.txt","all-homologs-results_genome_glxR_lcpR_all.txt"
                                  ,"all-homologs-results_genome_glxR_lcpR_only_homologous.txt", "all-homologs-results_genome_only_lcpR.txt"};
         //   String[] fileNames = {"all-homologs-results_genome_glxR.txt"};
            for (String names : fileNames) {

                fr = new FileReader("/home/mariana/Dropbox/Doutorado/Gordonia_polyisoprenivorans_VH2/analysis/hmmer/hmmer_results/" + names);
                br = new BufferedReader(fr);

                String sCurrentLine;

                FileWriter fileWriter = new FileWriter("/home/mariana/Dropbox/Doutorado/Gordonia_polyisoprenivorans_VH2/analysis/hmmer/hmmer_results/pos-" + names);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println("gp_gene" + "\t" + "cg_gene" + "\t" + "blast_evalue" + "\t" + "predicted_bs" + "\t" + "hmmer_evalue"
                        + "\t" + "hmmer_bitscore" + "\t" + "start_position" + "\t" + "end_position");
                
                boolean firstLine = true;
                while ((sCurrentLine = br.readLine()) != null) {
                    if (firstLine) {
                        firstLine = false;
                    } else {
                        //System.out.println(sCurrentLine);
                        String[] splitedLine = sCurrentLine.split("\t");
                        String gpGene = splitedLine[0];
                        String cgGene = splitedLine[1];
                        String blastEvalue = splitedLine[2];
                        String predictedBs = splitedLine[3];
                        String hmmerEvalue = splitedLine[4];
                        String hmmerBitscore = splitedLine[5];
                        String startPosition = splitedLine[6];
                        String endPosition = splitedLine[7];
                        String genome = splitedLine[8];

//                        for (int i = 0; i < splitedLine.length; i++) {
//                            System.out.println("splitedLine[" + i + "] => " + splitedLine[i]);
//                        }
//                        System.out.println("\n");
//                        System.out.println("");
                        Gene gene = new Gene();
                        GeneDAO geneDAO = new GeneDAO();
                        gene = geneDAO.findByLocusTag(gpGene);

                        BigInteger promoterStart = gene.getPromoterRegion().getInitialPosition();
                        BigInteger promoterEnd = gene.getPromoterRegion().getEndPosition();
                        String geneOrientation = gene.getOrientation();

                        BigInteger bsIniPos;
                        BigInteger bsEndPos;
                        if (geneOrientation.equals("forward")) {

                            bsIniPos = promoterStart.add(new BigInteger(startPosition)).subtract(BigInteger.ONE);
                            bsEndPos = promoterStart.add(new BigInteger(endPosition)).subtract(BigInteger.ONE);

                        } else {

                            bsIniPos = promoterEnd.add(new BigInteger(startPosition)).subtract(BigInteger.ONE);
                            bsEndPos = promoterEnd.add(new BigInteger(endPosition)).subtract(BigInteger.ONE);

                        }

                        startPosition = bsIniPos.toString();
                        endPosition = bsEndPos.toString();

                        //System.out.println("");
                        System.out.println(gpGene + "\t" + cgGene + "\t" + blastEvalue + "\t" + predictedBs + "\t" + hmmerEvalue
                                + "\t" + hmmerBitscore + "\t" + startPosition + "\t" + endPosition + "\t" + genome);
                        printWriter.print(gpGene + "\t" + cgGene + "\t" + blastEvalue + "\t" + predictedBs + "\t" + hmmerEvalue
                                + "\t" + hmmerBitscore + "\t" + startPosition + "\t" + endPosition + "\t" + genome+"\n");
                    }
////                    
                }

                printWriter.close();
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

    }

    public void processHmmerResults() {
        BufferedReader br = null;
        FileReader fr = null;
        String file = "";

        try {

            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

    }

    public void process2() {
        System.out.println("REGULATORY INTERACTION TEST");

        RegulatoryInteractionTest pt = new RegulatoryInteractionTest();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        List<RegulatoryInteraction> ri = new ArrayList<>();

//
        GeneDAO gDAO = new GeneDAO();
        Gene geneTF = gDAO.findByGenomeLocusTag(1028, "cg0350");
        System.out.println("TF " + geneTF);
        ri = riDAO.findByTF(geneTF.getId());
        System.out.println("RI SIZE: " + ri.size());
        System.out.println("TF\tHomologous TF\tTG\tTG name\tHomologous TG\tHomologous TG name\tCg Binding site\tCg RI Role\tPValue\tEvalue\tGenome");
        for (RegulatoryInteraction regulatoryInteraction : ri) {

            List<BindingSite> bsList = new ArrayList<BindingSite>();
            BindingSiteDAO bsDAO = new BindingSiteDAO();
            bsList = bsDAO.findByRegularotyInteraction(regulatoryInteraction.getId());

            Homologous homologous = new Homologous();
            List<Homologous> homologousTgList = new ArrayList<>();
            HomologousDAO homologousDAO = new HomologousDAO();
            //homologousTfList = homologousDAO.findByGene(regulatoryInteraction.getCorrespondentTranscriptionFactor().getId());
            homologousTgList = homologousDAO.findByGene(regulatoryInteraction.getCorrespondentTargetGene().getId());

            String homologousTgString = "";

            String homologousTgName = "";
            String homologousTgGenome = "";
            String homologousTgEvalue = "";
            String homologousTgPvalue = "";

            if (homologousTgList.size() != 0) {

                for (Homologous homologous1 : homologousTgList) {

                    if (homologous1.getGeneOne().getLocusTag().equals(regulatoryInteraction.getCorrespondentTargetGene().getLocusTag())) {
                        homologousTgString = homologousTgString + ", " + homologous1.getGeneTwo().getLocusTag();
                        homologousTgName = homologous1.getGeneTwo().getName();
                        homologousTgGenome = homologous1.getGeneTwo().getGenome().getGenomeName();

                    } else {
                        homologousTgString = homologousTgString + ", " + homologous1.getGeneOne().getLocusTag();
                        homologousTgName = homologous1.getGeneOne().getName();
                        homologousTgGenome = homologous1.getGeneOne().getGenome().getGenomeName();
                    }

                    homologousTgEvalue = homologousTgList.get(0).getEvalue().toString();
                    homologousTgPvalue = homologousTgList.get(0).getPvalue().toString();

                    homologousTgString = homologousTgString.replaceFirst(",", "");

                    System.out.println("glxR" + "\t"
                            //+ homologousTfString +"\t"        
                            + "lcrpVH2" + "\t"
                            + regulatoryInteraction.getCorrespondentTargetGene().getLocusTag() + "\t"
                            + regulatoryInteraction.getCorrespondentTargetGene().getName() + "\t"
                            + homologousTgString + "\t"
                            + homologousTgName + "\t"
                            // + regulatoryInteraction.getEvidence() + "\t"
                            // + regulatoryInteraction.getPmid() + "\t"
                            //+ bindingSite.getSequence() + "\t"
                            + homologousTgEvalue + "\t"
                            + homologousTgPvalue + "\t"
                            + homologousTgGenome
                    //  + ""
                    );

                }

            }

        }
    }

    public void process() {
        System.out.println("REGULATORY INTERACTION TEST");

        RegulatoryInteractionTest pt = new RegulatoryInteractionTest();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        List<RegulatoryInteraction> ri = new ArrayList<>();

//
        GeneDAO gDAO = new GeneDAO();
        Gene geneTF = gDAO.findByGenomeLocusTag(1028, "cg0350");
        System.out.println("TF " + geneTF);
        ri = riDAO.findByTF(geneTF.getId());
        System.out.println("RI SIZE: " + ri.size());
        System.out.println("TF\tHomologous TF\tTG\tTG name\tHomologous TG\tHomologous TG name\tCg Binding site\tCg RI Role\tPValue\tEvalue\tGenome");
        for (RegulatoryInteraction regulatoryInteraction : ri) {

            List<BindingSite> bsList = new ArrayList<BindingSite>();
            BindingSiteDAO bsDAO = new BindingSiteDAO();
            bsList = bsDAO.findByRegularotyInteraction(regulatoryInteraction.getId());

            Homologous homologous = new Homologous();
            List<Homologous> homologousTfList = new ArrayList<>();
            List<Homologous> homologousTgList = new ArrayList<>();
            HomologousDAO homologousDAO = new HomologousDAO();
            //homologousTfList = homologousDAO.findByGene(regulatoryInteraction.getCorrespondentTranscriptionFactor().getId());
            homologousTgList = homologousDAO.findByGene(regulatoryInteraction.getCorrespondentTargetGene().getId());

            String homologousTgString = "";

            String homologousTgName = "";
            String homologousTgGenome = "";
            String homologousTgEvalue = "";
            String homologousTgPvalue = "";

            if (homologousTgList.size() != 0) {

                for (Homologous homologous1 : homologousTgList) {
                    if (homologous1.getGeneOne().getLocusTag().equals(regulatoryInteraction.getCorrespondentTargetGene().getLocusTag())) {
                        homologousTgString = homologousTgString + ", " + homologous1.getGeneTwo().getLocusTag();
                        homologousTgName = homologous1.getGeneTwo().getName();
                        homologousTgGenome = homologous1.getGeneTwo().getGenome().getGenomeName();

                    } else {
                        homologousTgString = homologousTgString + ", " + homologous1.getGeneOne().getLocusTag();
                        homologousTgName = homologous1.getGeneOne().getName();
                        homologousTgGenome = homologous1.getGeneOne().getGenome().getGenomeName();
                    }

                }

                homologousTgEvalue = homologousTgList.get(0).getEvalue().toString();
                homologousTgPvalue = homologousTgList.get(0).getPvalue().toString();

                homologousTgString = homologousTgString.replaceFirst(",", "");

                if (!bsList.isEmpty()) {
                    for (BindingSite bindingSite : bsList) {
                        //Transcription factor	Transcription factor name	Type	
                        //Role	Target Gene	Target gene name	Evidence	
                        //PubmedIDs	Binding motif	Is CDS sigma factor	Source
                        System.out.println(
                                //regulatoryInteraction.getCorrespondentTranscriptionFactor().getLocusTag() + "\t"
                                "glxR" + "\t"
                                //+ homologousTfString +"\t"        
                                + "lcrpVH2" + "\t"
                                + regulatoryInteraction.getCorrespondentTargetGene().getLocusTag() + "\t"
                                + regulatoryInteraction.getCorrespondentTargetGene().getName() + "\t"
                                + homologousTgString + "\t"
                                + homologousTgName + "\t"
                                //+ regulatoryInteraction.getEvidence() + "\t"
                                //+ regulatoryInteraction.getPmid() + "\t"
                                + bindingSite.getSequence() + "\t"
                                + regulatoryInteraction.getRole() + "\t"
                                + homologousTgEvalue + "\t"
                                + homologousTgPvalue + "\t"
                                + homologousTgGenome
                        // + "" + "\t"
                        // + ""
                        );
                    }
                    //    System.out.println(bindingSite.getSequence());
                } else {
                    System.out.println("glxR" + "\t"
                            //+ homologousTfString +"\t"        
                            + "lcrpVH2" + "\t"
                            + regulatoryInteraction.getCorrespondentTargetGene().getLocusTag() + "\t"
                            + regulatoryInteraction.getCorrespondentTargetGene().getName() + "\t"
                            + homologousTgString + "\t"
                            + homologousTgName + "\t"
                            // + regulatoryInteraction.getEvidence() + "\t"
                            // + regulatoryInteraction.getPmid() + "\t"
                            //+ bindingSite.getSequence() + "\t"
                            + "" + "\t"
                            + regulatoryInteraction.getRole() + "\t"
                            + homologousTgEvalue + "\t"
                            + homologousTgPvalue + "\t"
                            + homologousTgGenome
                    //  + ""
                    );
                }
            }

        }
    }

}
