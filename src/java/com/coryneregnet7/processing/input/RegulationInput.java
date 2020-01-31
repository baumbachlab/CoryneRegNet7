/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.input;

import com.coryneregnet7.dao.BindingSiteDAO;
import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.RegulationDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.model.BindingSite;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Regulation;
import com.coryneregnet7.model.RegulatoryInteraction;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariana
 */
public class RegulationInput {

    public static void main(String[] args) throws InterruptedException {
        RegulationInput rfr = new RegulationInput();
        GenomeDAO genomeDAO = new GenomeDAO();
        Genome genome = genomeDAO.findById(1169);
        rfr.readIntoDatabase("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/model/GCF_000009045.1_ASM904v1.tsv.ctftg", genome);
    }

    public void readIntoDatabase(String path, Genome genome) throws InterruptedException {
        System.out.println("regulation input");
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(path);
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                if (!sCurrentLine.startsWith("Transcription factor")) {

                    String[] line = sCurrentLine.split("\t");
                    String tfId = line[0];
                    String tgId = line[4];
                    String regulatorType = line[2];
                    String role = line[3];
                    String evidence = line[6];
                    String pubmedId = line[7];
                    String bindingSite = line[8];
                    String isSigma = line[9];
                    String source = line[10];

//                    System.out.println("tfId = " + tfId);
//                    System.out.println("tgId = " + tgId);
//                    System.out.println("regulatorType = " + regulatorType);
//                    System.out.println("evidence = " + evidence);
//                    System.out.println("pubmedId = " + pubmedId);
//                    System.out.println("bindingSite = " + bindingSite);
//                    System.out.println("source = " + source);
//                    System.out.println("isSigma = " + isSigma);
//                    System.out.println("---------------------\n\n");

                    RegulatoryInteraction regulatoryInteraction = new RegulatoryInteraction();
                    regulatoryInteraction.setEvidence(evidence);
                    regulatoryInteraction.setPmid(pubmedId);
                    regulatoryInteraction.setSource(source);

                    GeneDAO geneDAO = new GeneDAO();
                    Gene targetGene = geneDAO.findByGenomeLocusTagProteinId(genome.getId(), tgId);
                    if (targetGene == null) {
                        //System.out.println("TARGET GENE É NULO!!!!!!!!!!!!!!!!!!!!!!!!!");
                    }
                    //System.out.println("targetGene= " + targetGene.toString());
                    regulatoryInteraction.setCorrespondentTargetGene(targetGene);

                    Gene transcriptionFactor = geneDAO.findByGenomeLocusTagProteinId(genome.getId(), tfId);

                    if (isSigma.contains("+")) {
                        System.out.println("SIGMA !!!");
                        transcriptionFactor.setIsSigma(true);
                        geneDAO.update(transcriptionFactor);
                    }

                    System.out.println("transcriptionFactor= " + transcriptionFactor.toString());
                    //System.out.println("\n\n");
                    regulatoryInteraction.setCorrespondentTranscriptionFactor(transcriptionFactor);

                    Regulation regulation = new Regulation();

                    RegulationDAO regulationDAO = new RegulationDAO();
                    regulation = (Regulation) regulationDAO.save(regulation);

                    /*
                    ver aqui se já tem a regulação, se tiver é só fazer o update do bs, se nao é salvar nova
                    procurar por: tf, tg, role
                     */
                    RegulatoryInteractionDAO regulatoryInteractionDAO = new RegulatoryInteractionDAO();
                    RegulatoryInteraction oldRi = regulatoryInteractionDAO.findByTfTgRole(transcriptionFactor.getLocusTag(), targetGene.getLocusTag(), role);

                    BindingSiteDAO bsDAO = new BindingSiteDAO();
                    BindingSite bs = new BindingSite();

                    if (oldRi != null) {
                       regulatoryInteraction = oldRi;
                       //
                       if (!bindingSite.isEmpty() && !bindingSite.equals("-")
                                && !bindingSite.startsWith(" ")) {

                            if (!bindingSite.contains(";")) {
                                bs.setSequence(bindingSite);
                                bs.setRegulatoryInteraction(regulatoryInteraction);
                                bs.setType("model");
                                bsDAO.save(bs);
                            } else {
                                String[] bindingSites = bindingSite.split(";");
                                //System.out.println(bindingSites.length);
                                for (int i = 0; i < bindingSites.length; i++) {

                                    try {
                                        //System.out.println(i);
                                        //System.out.println("bs => [" + i + "] " + bindingSites[i]);
                                        bs = new BindingSite();
                                        bsDAO = new BindingSiteDAO();
                                        bs.setSequence(bindingSites[i]);
                                        bs.setRegulatoryInteraction(regulatoryInteraction);
                                        bs.setType("model");
                                        bsDAO.save(bs);
                                    } catch (Exception e) {
                                        System.out.println("ERRO :( :( :(");
                                        e.printStackTrace();
                                     //   Thread.sleep(500000000);
                                    }

                                }
                            }

                        }
                       
                    } else {

                        regulatoryInteraction.setRegulation(regulation);
                        regulatoryInteraction.setRole(role);

                        regulatoryInteraction = (RegulatoryInteraction) regulatoryInteractionDAO.save(regulatoryInteraction);

                        if (!bindingSite.isEmpty() && !bindingSite.equals("-")
                                && !bindingSite.startsWith(" ")) {

                            if (!bindingSite.contains(";")) {
                                bs.setSequence(bindingSite);
                                bs.setRegulatoryInteraction(regulatoryInteraction);
                                bs.setType("model");
                                bsDAO.save(bs);
                            } else {
                                String[] bindingSites = bindingSite.split(";");
                                //System.out.println(bindingSites.length);
                                for (int i = 0; i < bindingSites.length; i++) {

                                    try {
                                        //System.out.println(i);
                                        //System.out.println("bs => [" + i + "] " + bindingSites[i]);
                                        bs = new BindingSite();
                                        bsDAO = new BindingSiteDAO();
                                        bs.setSequence(bindingSites[i]);
                                        bs.setRegulatoryInteraction(regulatoryInteraction);
                                        bs.setType("model");
                                        bsDAO.save(bs);
                                    } catch (Exception e) {
                                        System.out.println("ERRO :( :( :(");
                                        e.printStackTrace();
                                     //   Thread.sleep(500000000);
                                    }

                                }
                            }

                        }
                    }

                }

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

        attributeRole(genome);

    }

    public void attributeRole(Genome genome) {
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        List<Gene> tfs = new ArrayList<Gene>();
        tfs = riDAO.findByGenomeDistinct(genome.getId());
        GeneDAO geneDAO = new GeneDAO();

        for (Gene tf : tfs) {
            List<String> role = riDAO.bringRoleByTf(tf.getId());
            if (role.size() == 1) {
                tf.setRole(role.get(0));
            } else if (role.size() > 1) {
                tf.setRole("Dual");
            }

            geneDAO.update(tf);
        }
    }

    public void attributeRole(Gene tf, RegulatoryInteraction ri) {
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();

        GeneDAO geneDAO = new GeneDAO();
        Gene ctf = ri.getCorrespondentTranscriptionFactor();

        List<String> role = riDAO.bringRoleByTf(ctf.getId());
        if (role.size() == 1) {
            tf.setRole(role.get(0));
        } else if (role.size() > 1) {
            tf.setRole("Dual");
        }

        geneDAO.update(tf);
    }

}
