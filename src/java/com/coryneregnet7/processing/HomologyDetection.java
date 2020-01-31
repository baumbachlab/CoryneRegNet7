/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.HomologousDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Homologous;
import com.coryneregnet7.model.RegulatoryInteraction;
import com.coryneregnet7.processing.statistics.EvalueToPvalue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mariana
 */
public class HomologyDetection {

    public static void main(String args[]) throws FileNotFoundException, IOException, InterruptedException {

        // String blastFileFirstSide = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/sanity-test/test/CgATCC13032xCgTest/blast-bidirecional-CgATCC13032xCgTest/CgATCC13032xCgTest-1e-35-blast-result.txt";
        // String blastFileOtherSide = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/sanity-test/test/CgATCC13032xCgTest/blast-bidirecional-CgATCC13032xCgTest/CgTestxCgATCC13032-1e-35-blast-result.txt";
//        String blastFileFirstSide = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/ncbi-name-test/test/NNMO-S1xNNMO-T2/blast-bidirecional-NNMO-S1xNNMO-T2/NNMO-S1xNNMO-T2-1e-35-blast-result.txt";
//        String blastFileOtherSide = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/ncbi-name-test/test/NNMO-S1xNNMO-T2/blast-bidirecional-NNMO-S1xNNMO-T2/NNMO-T2xNNMO-S1-1e-35-blast-result.txt";
        // String blastFileFirstSide = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/test/CgATCC13032xGCF_002163915.1_ASM216391v1/blast-bidirecional-CgATCC13032xGCF_002163915.1_ASM216391v1/CgATCC13032xGCF_002163915.1_ASM216391v1-1e-10-blast-result.txt";
        // String blastFileOtherSide = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/test/CgATCC13032xGCF_002163915.1_ASM216391v1/blast-bidirecional-CgATCC13032xGCF_002163915.1_ASM216391v1/GCF_002163915.1_ASM216391v1xCgATCC13032-1e-10-blast-result.txt";
        HomologousDAO homologousDAO = new HomologousDAO();
        GenomeDAO genomeDAO = new GenomeDAO();
        Genome model = genomeDAO.findById(1226);
        Genome target = genomeDAO.findById(1357);

        //System.out.println("model: " + model);
        //System.out.println("target " + target);
        //Genome model = genomeDAO.findById(558);
        //Genome target = genomeDAO.findById(559);
        HomologyDetection hd = new HomologyDetection();
//        List<String[]> bestHitsFirstSide = hd.findBestHits(model, target, blastFileFirstSide);
//        List<String[]> bestHitsOtherSide = hd.findBestHits(model, target, blastFileOtherSide);
//        List<String[]> bbhs = hd.findBidirectionalBestHits(bestHitsFirstSide, bestHitsOtherSide, model, target);
//        List<Homologous> homologous = hd.saveHomologous(bbhs, model, target);

        //List<Homologous> homologous = homologousDAO.
//        homologous.forEach((homologou) -> {
//            System.out.println(homologou.getGeneOne().getLocusTag() + "\t" + homologou.getGeneTwo().getLocusTag()
//                    + "\t" + homologou.getEvalue() + "\t" + homologou.getPvalue());
//        });
//
//        System.out.println("homologous " + homologous.size() + "\n\n");
        List<TfTgPair> tfTgPairs = hd.bringTfTgPairs(model, target);
        for (TfTgPair tfTgPair : tfTgPairs) {
            System.out.println(tfTgPair.toString());
        }
        System.out.println(tfTgPairs.size());

        //System.out.println("tfTgPairs " + tfTgPairs.size());
//        
//        for (TfTgPair tfTgPair : tfTgPairs) {
//            //System.out.println(tfTgPair);
//        }
        //System.out.println("done :) ");
        // System.out.println("tfTgPairs " + tfTgPairs.size());
    }

    public void detectHomology(Genome genome1, Genome genome2, String transferenceFolderPath) throws IOException {
        String blastPath = transferenceFolderPath + "/blast";
        File blastFolder = new File(blastPath);
        createFolder(blastFolder);

//        String blastFileFirstSide="";
//        String blastFileOtherSide="";
//        
//         HomologyDetection hd = new HomologyDetection();
//        List<String[]> bestHitsFirstSide = hd.findBestHits(genome1, genome2, blastFileFirstSide);
//        List<String[]> bestHitsOtherSide = hd.findBestHits(genome1, genome2, blastFileOtherSide);
//        List<String[]> bbhs = hd.findBidirectionalBestHits(bestHitsFirstSide, bestHitsOtherSide, genome1, genome2);
//        List<Homologous> homologous = hd.saveHomologous(bbhs, genome1, genome2);
    }

    public List<String[]> findBestHits(Genome model, Genome target, String blastFile) throws FileNotFoundException, IOException {
        List<String[]> bestHits = new ArrayList<>();
        BufferedReader br = null;
        FileReader fr = null;
        Boolean downstreamLineContains = false;

        fr = new FileReader(blastFile);
        br = new BufferedReader(fr);

        String sCurrentLine;

        while ((sCurrentLine = br.readLine()) != null) {

            //System.out.println(sCurrentLine);
            //VERIFY IF THE IT IS THE BEST HIT (IF THE LINE DOES NOT START WITH # AND THE DOWNSTREAM LINE DOES.
            if (!sCurrentLine.startsWith("#") && downstreamLineContains) {
                String[] line = sCurrentLine.split("\t");
                String[] foundTranscriptionFactors = new String[3];

                foundTranscriptionFactors[0] = line[0];//QUERY/ POSSIBLE TF (TRANSCRIPTION FACTOR)
                foundTranscriptionFactors[1] = line[1];//SUBJECT/POSSIBLE HTF (HOMOLOGOUS TRANSCRIPTION FACTOR)
                foundTranscriptionFactors[2] = line[10];//E-VALUE/POSSIBLE HTF (HOMOLOGOUS TRANSCRIPTION FACTOR)

//                System.out.println("---QUERY/TF " + foundTranscriptionFactors[0]);
//                System.out.println("---SUBJECT/HTF " + foundTranscriptionFactors[1]);
//                System.out.println("---EVALUE " + foundTranscriptionFactors[2]);
                //check if the found protein is the model organisms TF list
                bestHits.add(foundTranscriptionFactors);

            }

            //VERIFY IF THIS LINE STARTS WITH #
            if (sCurrentLine.startsWith("#")) {
                downstreamLineContains = true;
            } else {
                downstreamLineContains = false;
            }
        }

        // System.out.println("found " + bestHits.size() + " best hits");
        return bestHits;

    }

    public List<String[]> findBidirectionalBestHits(List<String[]> bestHitsFirstSide, List<String[]> bestHitsOtherSide, Genome templateGenome, Genome targetGenome) {
        // System.out.println("\n\n");
        List<String[]> bbhs = new ArrayList<>();
        EvalueToPvalue evalueToPvalue = new EvalueToPvalue();
        String[] bestBlastHit = new String[4];

        for (int i = 0; i < bestHitsFirstSide.size(); i++) {
            for (int j = 0; j < bestHitsOtherSide.size(); j++) {

                if (bestHitsFirstSide.get(i)[0].equals(bestHitsOtherSide.get(j)[1])
                        && bestHitsFirstSide.get(i)[1].equals(bestHitsOtherSide.get(j)[0])) {

                    BigDecimal evalueFirstSide = new BigDecimal(bestHitsFirstSide.get(i)[2]);
                    BigDecimal evalueOtherSide = new BigDecimal(bestHitsOtherSide.get(j)[2]);

                    BigDecimal evalue = BigDecimal.ZERO;
                    BigDecimal pvalue = new BigDecimal("0");

                    //System.out.println(targetGenome.toString());
                    if (evalueFirstSide.compareTo(evalueOtherSide) == -1) {
                        evalue = evalueOtherSide;
                        pvalue = evalueToPvalue.convert(evalueOtherSide, new BigDecimal(templateGenome.getSearchSpace()));
                    } else {
                        evalue = evalueFirstSide;
                        pvalue = evalueToPvalue.convert(evalueFirstSide, new BigDecimal(targetGenome.getSearchSpace()));
                    }

                    bestBlastHit[0] = bestHitsFirstSide.get(i)[0];
                    bestBlastHit[1] = bestHitsFirstSide.get(i)[1];
                    bestBlastHit[2] = evalue.toString();
                    bestBlastHit[3] = pvalue.toString();

                    bbhs.add(bestBlastHit);

                    //System.out.println("[0]= " + bestBlastHit[0] + "\t[1]= " + bestBlastHit[1] + "\t[2]= " + bestBlastHit[2] + "\t[3]= " + bestBlastHit[3]);
                    bestBlastHit = new String[4];
                    break;
                }

            }
        }
        //System.out.println("\nbbhs: " + bbhs.size());

        return bbhs;

    }

    public List<Homologous> saveHomologous(List<String[]> bbhs, Genome model, Genome target) {
        List<Homologous> homologousList = new ArrayList<>();
        Homologous homologous = new Homologous();
        HomologousDAO homologousDAO = new HomologousDAO();
        //[0] -> model
        //[1] -> target
        GeneDAO geneDAO = new GeneDAO();
        System.out.println("save homologous model  -> " + model.toString());
        System.out.println("save homologous target  -> " + target.toString());

        for (int i = 0; i < bbhs.size(); i++) {

            //System.out.println("\n[0]= " + bbhs.get(i)[0] + "\t[1]= " + bbhs.get(i)[1] + "\t[2]= " + bbhs.get(i)[2] + "\t[3]= " + bbhs.get(i)[3]);
            String bbhs0 = bbhs.get(i)[0];
            String bbhs1 = bbhs.get(i)[1];

            // System.out.println("TEM _prot_ !");
            if (bbhs.get(i)[0].contains("_prot_")) {
                String[] splitedBbhZero = bbhs0.split("_prot_");
                bbhs0 = splitedBbhZero[1];
                int index = bbhs0.indexOf(".");
                bbhs0 = bbhs0.substring(0, index + 2);
                System.out.println(" bbhs0 =" + bbhs0);
            }

            if (bbhs.get(i)[1].contains("_prot_")) {
                String[] splitedBbhZero = bbhs1.split("_prot_");
                bbhs1 = splitedBbhZero[1];
                int index = bbhs1.indexOf(".");
                bbhs1 = bbhs1.substring(0, index + 2);
                System.out.println(" bbhs1 =" + bbhs1);
            }

            List<Gene> geneModels = geneDAO.findByGenomeLocusTagProteinIdMultiple(model.getId(), bbhs0);
            List<Gene> geneTargets = geneDAO.findByGenomeLocusTagProteinIdMultiple(target.getId(), bbhs1);

            for (Gene geneModel : geneModels) {
                for (Gene geneTarget : geneTargets) {
                    Homologous homologousTest = homologousDAO.findByPair(geneModel.getId(), geneTarget.getId());
                    //System.out.println("geneModel.getId() => " + geneModel.toString());
                    //System.out.println("geneTarget.getId() => " + geneTarget.toString());

                   // System.out.println("é igual? " + (Objects.equals(geneModel.getId(), geneTarget.getId())));
                    if (homologousTest == null && (!Objects.equals(geneModel.getId(), geneTarget.getId()))) {
                        BigDecimal evalue = new BigDecimal(bbhs.get(i)[2]);
                        BigDecimal pvalue = new BigDecimal(bbhs.get(i)[3]);

                        homologous.setEvalue(evalue);
                        homologous.setPvalue(pvalue);
                        homologous.setGeneOne(geneModel);
                        homologous.setGeneTwo(geneTarget);

                        homologousDAO.save(homologous);
                        homologousList.add(homologous);
                    }

                    homologous = new Homologous();
                }
            }

        }

        return homologousList;
    }

    public List<TfTgPair> bringTfTgPairs(Genome model, Genome target) {
        List<TfTgPair> pairs = new ArrayList<TfTgPair>();

        //buscar todas as regulações daquele modelo. 
        List<RegulatoryInteraction> regulatoryInteractions = new ArrayList<RegulatoryInteraction>();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        regulatoryInteractions = riDAO.findByGenome(model.getId());

        HomologousDAO homologousDAO = new HomologousDAO();
        //Homologous transcriptionFactor = new Homologous();
        Homologous targetGene = new Homologous();
        List<Homologous> targetGeneList = new ArrayList<>();

        for (RegulatoryInteraction regulatoryInteraction : regulatoryInteractions) {
            //System.out.println("-------------------------------------------------------------------------------------------------------");
            //System.out.println("RI " + regulatoryInteraction);
            // System.out.println("getCorrespondentTranscriptionFactor " + regulatoryInteraction.getCorrespondentTranscriptionFactor());
            // System.out.println("getCorrespondentTargetGene " + regulatoryInteraction.getCorrespondentTargetGene());

            // System.out.println("regulatoryInteraction.getCorrespondentTranscriptionFactor().getId() " + regulatoryInteraction.getCorrespondentTranscriptionFactor().getId());
            // System.out.println("target.getId() " + target.getId());
            List<Homologous> transcriptionFactorList = homologousDAO.findByGeneGenomeMultiple(regulatoryInteraction.getCorrespondentTranscriptionFactor().getId(), target.getId());
//            if (transcriptionFactorList != null) {
//                System.out.println("\n\ntranscriptionFactor = " + transcriptionFactorList);
//            } else {
//                System.out.println("NAO ACHOU o tf :(");
//            }

            for (Homologous transcriptionFactor : transcriptionFactorList) {

                //Homologous homologous = homologousDAO.findByGeneGenome(regulatoryInteraction.getCorrespondentTargetGene().getId(), target.getId());
                targetGeneList = homologousDAO.findByGeneGenomeMultiple(regulatoryInteraction.getCorrespondentTargetGene().getId(), target.getId());
                //if()
                for (int i = 0; i < targetGeneList.size(); i++) {
                    //  System.out.println("-------ANDOU NO FOR--------------");
                    targetGene = targetGeneList.get(i);
                    //   System.out.println("--------targetGene "+targetGene);
//                if (targetGene != null) {
//                    //    System.out.println("targetGene = " + targetGene + "\n\n");
//                } else {
//                    //    System.out.println("NAO ACHOU o tg :(");
//                }

                    if (transcriptionFactor != null && targetGene != null) {
                        //System.out.println("FOUND :D");
                        TfTgPair pair = new TfTgPair();

                        //System.out.println("regulatoryInteraction.getCorrespondentTranscriptionFactor() "+regulatoryInteraction.getCorrespondentTranscriptionFactor());
                        //  System.out.println("transcriptionFactor.getGeneOne().getId() "+transcriptionFactor.getGeneOne().getId());
                        if (Objects.equals(regulatoryInteraction.getCorrespondentTranscriptionFactor().getId(), transcriptionFactor.getGeneOne().getId())) {
                            pair.setHomologousTranscriptionFactor(transcriptionFactor.getGeneTwo());
                            pair.setTranscriptionFactor(transcriptionFactor.getGeneOne());
                        } else {
                            pair.setHomologousTranscriptionFactor(transcriptionFactor.getGeneOne());
                            pair.setTranscriptionFactor(transcriptionFactor.getGeneTwo());
                        }

                        pair.setHtfEvalue(transcriptionFactor.getEvalue());
                        pair.setHtfPvalue(transcriptionFactor.getPvalue());

                        if (Objects.equals(regulatoryInteraction.getCorrespondentTargetGene().getId(), targetGene.getGeneOne().getId())) {
                            pair.setHomologousTargetGene(targetGene.getGeneTwo());
                            pair.setTargetGene(targetGene.getGeneOne());
                        } else {
                            pair.setHomologousTargetGene(targetGene.getGeneOne());
                            pair.setTargetGene(targetGene.getGeneTwo());
                        }

                        pair.setHtgEvalue(targetGene.getEvalue());
                        pair.setHtgPvalue(targetGene.getPvalue());
                        pairs.add(pair);
                        //  System.out.println(pair.toString2());

                    }

                }
                transcriptionFactor = new Homologous();
                targetGene = new Homologous();
                //System.out.println("\n\n");
            }
        }
        return pairs;
    }

    public void createFolder(File folder) {
        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
    }

}
