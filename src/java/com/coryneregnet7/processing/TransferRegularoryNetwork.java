/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing;

import com.coryneregnet7.dao.BindingSiteDAO;
import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GeneOperonDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.HomologousDAO;
import com.coryneregnet7.dao.OperonDAO;
import com.coryneregnet7.dao.PredictedRegulatoryInteractionDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.dao.RunDAO;
import com.coryneregnet7.model.BindingSite;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.GeneOperon;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Homologous;
import com.coryneregnet7.model.Operon;
import com.coryneregnet7.model.PredictedRegulatoryInteraction;
import com.coryneregnet7.model.Regulation;
import com.coryneregnet7.model.RegulatoryInteraction;
import com.coryneregnet7.model.Run;
import com.coryneregnet7.processing.input.RegulationInput;
import com.coryneregnet7.processing.statistics.CombinePvalue;
import com.coryneregnet7.processing.statistics.EvalueToPvalue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mariana
 */
public class TransferRegularoryNetwork {

    public static void main(String[] args) throws IOException, InterruptedException {
        //String transferenceFolder = "/home/mariana/Dropbox/Doutorado/CP13_T1/Test_BLAST/";
        //String targetGenomesFolder = "/home/mariana/Dropbox/Doutorado/CP13_T1/target-genomes-test";
        //String templateGenomesFolder = "/home/mariana/Dropbox/Doutorado/CP13_T1/template-genomes-test";

        String transferFolder = "/home/doglas/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/test";
        String targetfolder = "/home/doglas/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/target";
        String templateFolder = "/home/doglas/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/model";

        String blastEvalue = "1e-10";
        String hmmerEvalue = "";

        ArrayList<Genome> genomesTarget = new ArrayList<>();
        ArrayList<Genome> genomesTemplate = new ArrayList<>();
        GenomeDAO genomeDAO = new GenomeDAO();
        Genome genome = genomeDAO.findById(2276);
        genomesTarget.add(genome);

        genome = new Genome();
        genome = genomeDAO.findById(2465);
        genomesTemplate.add(genome);

        Run run = new Run();
        RunDAO runDAO = new RunDAO();
        run = (Run) runDAO.save(run);

        TransferRegularoryNetwork blaster = new TransferRegularoryNetwork();
        //blaster.createBlastDatabase(targetGenomesFolder, targetGenomes);
        //blaster.createBlastDatabase(templateGenomesFolder, templateGenomes);
        blaster.run(transferFolder,
                targetfolder,
                templateFolder,
                genomesTarget,
                genomesTemplate,
                blastEvalue,
                hmmerEvalue,
                run);

    }

    public void run(String transferenceFolderPath, String targetGenomesFolderPath, String templateGenomesFolderPath,
            List<Genome> targetGenomes, List<Genome> templateGenomes, String blastEvalue, String hmmerEvalue, Run run) throws IOException, InterruptedException {

//        RunDAO runDAO = new RunDAO();
//        int total = (targetGenomes.size() * templateGenomes.size());
//        run.setBlast("0/" + total);
//        runDAO.update(run);
//        int countBlastRun = 0;
//        int countHmmerRun = 0;
        List<String> targetGenomesNames = bringNames(targetGenomes);
        List<String> templateGenomesNames = bringNames(templateGenomes);
        //----------------------------------------------------
        //create the tranference directory if it does not exist. 
        File transferenceFolder = new File(transferenceFolderPath);
        createFolder(transferenceFolder);

//        //------------------------------------------------------
//        //create the blast databases for the target and template genomes
//----TIRAR O COMENTÁRIO!!!!
        System.out.println("create blast db");
        TransferRegularoryNetwork blaster = new TransferRegularoryNetwork();
        blaster.createBlastDatabase(targetGenomesFolderPath, targetGenomesNames);
        blaster.createBlastDatabase(templateGenomesFolderPath, templateGenomesNames);
        System.out.println("created");

        //---------------------------------------------------------------
        //blast all template genomes against all target genomes
        for (String templateGenome : templateGenomesNames) {
            for (String targetGenome : targetGenomesNames) {
                System.out.println("template genome => " + templateGenome);
                System.out.println("target genome => " + targetGenome + "\n");

                //---------------------------------------------------
                //create TFxTG directory if it does not exist. 
                File blastFolder = new File(transferenceFolderPath + "/" + templateGenome + "x" + targetGenome);
                createFolder(blastFolder);

                //-----------------------------------------------------
                //create blast directory
                File blastBidirecionalFolder = new File(transferenceFolderPath + "/" + templateGenome + "x" + targetGenome + "/blast-bidirecional-" + templateGenome + "x" + targetGenome);
                createFolder(blastBidirecionalFolder);

                //-----------------------------------------------------
                //create hmmer directory
                File hmmerFolder = new File(transferenceFolderPath + "/" + templateGenome + "x" + targetGenome + "/hmmer-" + templateGenome + "x" + targetGenome);
                createFolder(hmmerFolder);

                //-----------------------------------------------------
                //create hmmer result directory
                File hmmerResultFolder = new File(transferenceFolderPath + "/" + templateGenome + "x" + targetGenome + "/hmmer-" + templateGenome + "x" + targetGenome + "/result");
                createFolder(hmmerResultFolder);

                //------------------------------------------------------------------------------------
                //-----------------------------RUN-BLAST-FOR-BOTH-SIDES-------------------------------
                //------------------------------------------------------------------------------------
                GenomeDAO genomeDAO = new GenomeDAO();
                Genome templateGenomeObj = genomeDAO.findByGbffFile(templateGenomesFolderPath + "/" + templateGenome + "_genomic.gbff");
                Genome targetGenomeObj = genomeDAO.findByGbffFile(targetGenomesFolderPath + "/" + targetGenome + "_genomic.gbff");
                HomologyDetection hd = new HomologyDetection();

                System.out.println("templateGenomeObj " + templateGenomeObj);
                System.out.println("targetGenomeObj " + targetGenomeObj);

                HomologousDAO homologousDAO = new HomologousDAO();
                Long homologousSize = homologousDAO.bringByModelAndTargetGenomes(templateGenomeObj.getId(), targetGenomeObj.getId());
                System.out.println("homologousSize.intValue() " + homologousSize.intValue());

                String blastFirstSide = "blastp -query '" + templateGenomesFolderPath + "/" + templateGenome + "_protein.faa' -db '" + targetGenomesFolderPath + "/" + targetGenome + "_protein.faa' -out " + templateGenome + "x" + targetGenome + "-" + blastEvalue + "-blast-result.txt -outfmt 7 -evalue " + blastEvalue + " -num_threads 4";
                String blastOtherSide = "blastp -query '" + targetGenomesFolderPath + "/" + targetGenome + "_protein.faa' -db '" + templateGenomesFolderPath + "/" + templateGenome + "_protein.faa' -out " + targetGenome + "x" + templateGenome + "-" + blastEvalue + "-blast-result.txt -outfmt 7 -evalue " + blastEvalue + " -num_threads 4";

                System.out.println("blastFirstSide: " + blastFirstSide);
                System.out.println("blastOtherSide: " + blastOtherSide);
                //TIRAR COMENTArio

                String blastFileFirstSide = transferenceFolderPath + "/" + templateGenome + "x" + targetGenome + "/blast-bidirecional-" + templateGenome + "x" + targetGenome + "/" + templateGenome + "x" + targetGenome + "-" + blastEvalue + "-blast-result.txt";
                String blastFileOtherSide = transferenceFolderPath + "/" + templateGenome + "x" + targetGenome + "/blast-bidirecional-" + templateGenome + "x" + targetGenome + "/" + targetGenome + "x" + templateGenome + "-" + blastEvalue + "-blast-result.txt";

                System.out.println("blastFileFirstSide " + blastFileFirstSide);
                System.out.println("blastFileOtherSide " + blastFileOtherSide);

                System.out.println("VOU FAZER O BLAST?");
                System.out.println("new File(blastFileFirstSide).exists() " + new File(blastFileFirstSide).exists());
                System.out.println("new File(blastFileOtherSide).exists() " + new File(blastFileOtherSide).exists());
                System.out.println("new File(blastFileFirstSide).exists() && new File(blastFileOtherSide).exists() " + (new File(blastFileFirstSide).exists() && new File(blastFileOtherSide).exists()));
                if (new File(blastFileFirstSide).exists() && new File(blastFileOtherSide).exists()) {
                    System.out.println("SKIPPING BLAST!");
                } else {
                    System.out.println("VOU SIM!");
                    runBlast(blastFirstSide, blastOtherSide, blastBidirecionalFolder);
                }

                //------------------------------------------------------------------------------------
                //----------------------GET-BLAST-RESULTS-&-SAVE-HOMOLOGOUS---------------------------
                //------------------------------------------------------------------------------------
                System.out.println("GET-BLAST-RESULTS-&-SAVE-HOMOLOGOUS");

                //transferenceFolderPath + "/blast/Blast-Bidirecional-" + templateGenome + "x" + targetGenome
                //String blastFileFirstSide = transferenceFolderPath + "/" + templateGenome + "x" + targetGenome + "/blast-bidirecional-" + templateGenome + "x" + targetGenome + "/" + templateGenome + "x" + targetGenome + "-" + blastEvalue + "-blast-result.txt";
                //String blastFileOtherSide = transferenceFolderPath + "/" + templateGenome + "x" + targetGenome + "/blast-bidirecional-" + templateGenome + "x" + targetGenome + "/" + targetGenome + "x" + templateGenome + "-" + blastEvalue + "-blast-result.txt";
                System.out.println("\nblastFileFirstSide: " + blastFileFirstSide);
                System.out.println("blastFileOthertSide: " + blastFileOtherSide);

                System.out.println("TEMPLATE GENOME OBJ: " + templateGenomeObj);
                System.out.println("TARGET GENOME OBJ: " + targetGenomeObj);

                //  Thread.sleep(5000);
                //System.out.println("1");
                List<String[]> bestHitsFirstSide = hd.findBestHits(templateGenomeObj, targetGenomeObj, blastFileFirstSide);
                //System.out.println("2");
                List<String[]> bestHitsOtherSide = hd.findBestHits(templateGenomeObj, targetGenomeObj, blastFileOtherSide);
                // System.out.println("3");
                List<String[]> bbhs = hd.findBidirectionalBestHits(bestHitsFirstSide, bestHitsOtherSide, templateGenomeObj, targetGenomeObj);
                //System.out.println("4");
                hd.saveHomologous(bbhs, templateGenomeObj, targetGenomeObj);

                System.out.println("RUN HMMER???");

                System.out.println("vou rodar o hmmer!");
                System.out.println("bring tf tg pairs");

                List<TfTgPair> homologousPairs = hd.bringTfTgPairs(templateGenomeObj, targetGenomeObj);

                System.out.println("\n\nMODEL: " + templateGenome);
                System.out.println("TARGET: " + targetGenome);

                runHmmer(homologousPairs, targetGenome, hmmerFolder, hmmerResultFolder, hmmerEvalue);

//                countHmmerRun++;
//                run.setHmmer(countHmmerRun + "/" + total);
//                runDAO.update(run);
                // System.out.println("\n\n");
            }
        }
        System.out.println("TRANSFERENCE DONE!");

    }

    public List<String> bringNames(List<Genome> genomes) {
        List<String> genomeNames = new ArrayList<>();

        // System.out.println("=====> targetGenomeFILES");
        String name = "";
        for (int i = 0; i < genomes.size(); i++) {
            name = genomes.get(i).getGbffFile();
            String[] split = name.split("\\/");
            genomeNames.add(split[split.length - 1].replace("_genomic.gbff", ""));
            //System.out.println("==>" + genomeNames.get(i));
            name = "";
        }

        return genomeNames;
    }

    public void createFolder(File folder) {
        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("NOT create directory!");
            }
        }
    }

    public void attributePredictedRole(Gene tf, PredictedRegulatoryInteraction pri) throws InterruptedException {
        //System.out.println("///////////////////////////////////");
        //System.out.println("---------------ROLE----------------");
        //System.out.println("///////////////////////////////////\n");
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();

        GeneDAO geneDAO = new GeneDAO();
        Gene homologousTf = pri.getHomologousTranscriptionFactor();
        //System.out.println(homologousTf.toString());
        try {
            List<String> role = priDAO.bringRoleByTf(homologousTf.getId());
            //System.out.println("ROLE = " + role);
            for (String string : role) {
                //   System.out.println("ROLE: " + string);
            }

            if (role.size() == 1) {
                tf.setRole(role.get(0));
            } else if (role.size() > 1) {
                tf.setRole("Dual");
            }
        } catch (Exception e) {
            System.out.println("ERRO 0002");
            e.printStackTrace();
            //Thread.sleep(10000);
        }
        geneDAO.update(tf);
        //System.out.println("\n//////////////////////////////////////");
        //System.out.println("----------END-----ROLE----------------");
        //System.out.println("//////////////////////////////////////");
    }

    public void createBlastDatabase(String path, List<String> genomeNames) throws IOException {
        //String shMakeDbFile = templateGenomesFolderPath+"/make-db-blast.sh";

        for (String genomeName : genomeNames) {
            //  System.out.println("PATH: " + path);
            //  System.out.println("genomeNames " + genomeName);

            String genomeFaa = path + "/" + genomeName + "_protein.faa";
            String database = path + "/" + genomeName + "_protein.faa.phr";

            File databaseFile = new File(database);
            if (!databaseFile.exists()) {

                String makeDb = "makeblastdb -in " + genomeFaa + " -dbtype prot";
                //    System.out.println(makeDb);
                try {
                    ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c",
                            makeDb);
                    pb.directory(new File(path));
                    final Process p = pb.start();
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(
                                    p.getInputStream()));
                    String line;
//                    while ((line = br.readLine()) != null) {
//                        //          System.out.println(line);
//                    }
                    p.waitFor();
                } catch (Exception ex) {
                    System.out.println(ex);
                }

            }
        }

    }

    public void runBlast(String blastFirstSide, String blastOtherSide, File blastBidirecionalFolder) {

        System.out.println("blastFirstSide: " + blastFirstSide);
        System.out.println("blastOtherSide: " + blastOtherSide);

        try {
            ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c",
                    blastFirstSide);
            pb.directory(blastBidirecionalFolder);
            final Process p = pb.start();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            p.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            p.waitFor();
        } catch (Exception ex) {
            System.out.println(ex);
        }

        try {
            ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c",
                    blastOtherSide);
            pb.directory(blastBidirecionalFolder);
            final Process p = pb.start();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            p.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            p.waitFor();
        } catch (Exception ex) {
            System.out.println(ex);
        }

        System.out.println("BLAST DONE");
    }

    public void runHmmer(List<TfTgPair> homologousPairs, String targetGenome, File hmmerFolder, File hmmerResultFolder, String hmmerEvalue) throws IOException, InterruptedException {
        Homologous homologous = new Homologous();
        HomologousDAO homologsDAO = new HomologousDAO();
        ArrayList<TfTgPair> noMatches = new ArrayList<>();

        List<TfTgPair> potentialOperonPairs = new ArrayList<>();
        List<Object[]> foundOperons = new ArrayList<>();

        RunHmmer runHmmer = new RunHmmer();
        GeneOperon geneOperon = new GeneOperon();
        GeneOperonDAO geneOperonDAO = new GeneOperonDAO();

        for (TfTgPair pair : homologousPairs) {

            System.out.println(pair.toString());
            //só faz isso tudo se for o 1 do operon
            //ou se nao for parte de um operon
            geneOperon = geneOperonDAO.findByGene(pair.getHomologousTargetGene().getId());
            Integer tgPosOp = null;
            if (geneOperon != null) {
                tgPosOp = geneOperon.getPos();
            }

            // System.out.println("tgPosOp " + tgPosOp);
            if ((geneOperon == null) || (tgPosOp == 1)) {
                /// System.out.println("CALCULOU O BS :) ");
                String promoterRegion = pair.getHomologousTargetGene().getPromoterRegion().getFile();
                // System.out.println("promoterRegion: " + promoterRegion);
                String bindingSite = "";

                //RECOVER THE BINDING SITE TO THE TF
                if (pair.getTranscriptionFactor().getHmmProfile() != null) {
                    bindingSite = pair.getTranscriptionFactor().getHmmProfile();

                    //    System.out.println("BINDING SITE: " + bindingSite);
                    String resultFile = targetGenome + "_" + pair.getTranscriptionFactor().getProteinId()
                            + "_" + pair.getTargetGene().getProteinId()
                            + "_" + pair.getHomologousTranscriptionFactor().getProteinId()
                            + "_" + pair.getHomologousTargetGene().getProteinId()
                            + ".out";

                    if (hmmerEvalue.isEmpty() || hmmerEvalue == null) {
                        runHmmer.runOnce(bindingSite, promoterRegion, hmmerFolder.getAbsolutePath(), resultFile);
                    } else {
                        runHmmer.runOnce(bindingSite, promoterRegion, hmmerFolder.getAbsolutePath(), resultFile, hmmerEvalue);
                    }

                    BindingSitePredictionInfo bsPredictionInfo = new BindingSitePredictionInfo();
                    boolean hasMatch = runHmmer.findMatch(resultFile, hmmerResultFolder.getAbsolutePath());
                    if (hasMatch) {
                        System.out.println("has match :D in " + pair.getHomologousTranscriptionFactor().getLocusTag() + " -> " + pair.getHomologousTargetGene().getLocusTag());
//                        System.out.println("hmmerResultFolder " + hmmerResultFolder);
//                        System.out.println("resultFile " + resultFile);
//                        System.out.println("pair.getTargetGene().getOrientation() " + pair.getHomologousTargetGene().getOrientation());
//                        System.out.println("pair.getTargetGene().getPromoterRegion().getInitialPosition() " + pair.getHomologousTargetGene().getPromoterRegion());
//                        System.out.println("tg: " + pair.getTargetGene());
//                        System.out.println("pair.getTargetGene().getPromoterRegion().getEndPosition() " + pair.getHomologousTargetGene().getPromoterRegion().getEndPosition());
//                        System.out.println("pair.getTargetGene().getStartPosition() " + pair.getHomologousTargetGene().getStartPosition());
                        bsPredictionInfo = runHmmer.getMatchInfo(hmmerResultFolder + "/" + resultFile, pair.getHomologousTargetGene().getOrientation(), pair.getHomologousTargetGene().getPromoterRegion().getInitialPosition(),
                                pair.getHomologousTargetGene().getPromoterRegion().getEndPosition(), pair.getHomologousTargetGene().getStartPosition());
                        System.out.println("----" + bsPredictionInfo.toString());
//                        System.out.println("\n");

                        PredictedRegulatoryInteraction predictedRI = new PredictedRegulatoryInteraction();
                        PredictedRegulatoryInteractionDAO predictedRIDAO = new PredictedRegulatoryInteractionDAO();
                        RegulatoryInteractionDAO regulatoryInteractionDAO = new RegulatoryInteractionDAO();
                        //RegulatoryInteraction regulatoryInteraction = new RegulatoryInteraction();

                        List<RegulatoryInteraction> regulatoryInteractions = regulatoryInteractionDAO.bringRisByTfTg(pair.getTranscriptionFactor().getId(),
                                pair.getTargetGene().getId());

                        for (RegulatoryInteraction regulatoryInteraction : regulatoryInteractions) {

                            predictedRI.setRegulatoryInteraction(regulatoryInteraction);
                            predictedRI.setHomologousTargetGene(pair.getHomologousTargetGene());
                            predictedRI.setHomologousTranscriptionFactor(pair.getHomologousTranscriptionFactor());
                            predictedRI.setHtfEvalue(pair.getHtfEvalue());
                            predictedRI.setHtfPvalue(pair.getHtfPvalue());
                            predictedRI.setHtgEvalue(pair.getHtgEvalue());
                            predictedRI.setHtgPvalue(pair.getHtgPvalue());
                            predictedRI.setPredictedRole(regulatoryInteraction.getRole());

                            BindingSiteDAO bindingSiteDAO = new BindingSiteDAO();
                            BindingSite predictedBindingSite = new BindingSite();
                            predictedBindingSite.setBitscore(new BigDecimal(bsPredictionInfo.getBitscore()));
                            predictedBindingSite.setStartPosition(new BigInteger(bsPredictionInfo.getStartPosition()));
                            predictedBindingSite.setEndPosition(new BigInteger(bsPredictionInfo.getEndPosition()));
                            predictedBindingSite.setEvalue(new BigDecimal(bsPredictionInfo.getEvalue()));
                            predictedBindingSite.setSequence(bsPredictionInfo.getFoundBindingSite());
                            predictedBindingSite.setStartSiteDistance(bsPredictionInfo.getStartSiteDistance());
                            predictedBindingSite.setType("predicted");

                            EvalueToPvalue convertEvalue = new EvalueToPvalue();
                            BigDecimal searchSpace = new BigDecimal(predictedRI.getHomologousTargetGene().getSearchSpace());
//                            System.out.println("searchSpace ->" + searchSpace);
                            BigDecimal bsPvalue = convertEvalue.convert(predictedBindingSite.getEvalue(), searchSpace);
//                            System.out.println("pvalue = " + bsPvalue + " = " + predictedBindingSite.getEvalue() + " (evalue) / (searchSpace) "
//                                    + predictedRI.getHomologousTargetGene().getSearchSpace());
                            predictedBindingSite.setPvalue(bsPvalue);
                            predictedBindingSite = (BindingSite) bindingSiteDAO.save(predictedBindingSite);

                            CombinePvalue combinePvalue = new CombinePvalue();
                            BigDecimal[] pvalues = {predictedBindingSite.getPvalue(), predictedRI.getHtfPvalue(), predictedRI.getHtgPvalue()};
                            BigDecimal predictedRiPvalue = combinePvalue.combine(pvalues);
                            predictedRI.setInteractionPvalue(predictedRiPvalue);

                            predictedRI.setPredictedBindingSite(predictedBindingSite);
                            predictedRI = (PredictedRegulatoryInteraction) predictedRIDAO.save(predictedRI);
//                            System.out.println("predicted regulation: " + predictedRI.getId());

                            if (geneOperon != null) {
                                GeneDAO geneDAO = new GeneDAO();

                                List<GeneOperon> geneOperons = geneOperonDAO.findByOperon(geneOperon.getGeneOperonPK().getOperon());
//                                System.out.println("GENES DO OPERON: ");
                                for (GeneOperon geneOp : geneOperons) {
                                    if (!geneOp.getPos().equals(1)) {

//                                        System.out.println("gene: " + geneOp.toString());
                                        Gene gene = geneDAO.findById(geneOp.getGeneOperonPK().getGene());
                                        //System.out.println("gene: " + gene.toString());

                                        PredictedRegulatoryInteraction predictedInteraction = new PredictedRegulatoryInteraction();
                                        predictedInteraction.setHomologousTargetGene(gene);
                                        try {

//                                            System.out.println("gene.getId() => " + gene.getId());
//                                            System.out.println("predictedRI.getHomologousTargetGene().getGenome().getId() => " + predictedRI.getHomologousTargetGene().getGenome().getId());
                                            homologous = new Homologous();
                                            homologous = homologsDAO.findByGeneGenomeNotParalogous(gene.getId(), predictedRI.getRegulatoryInteraction().getCorrespondentTargetGene().getGenome().getId());
                                        } catch (Exception e) {
                                            System.out.println("CATCH EXCEPTION E");
                                            //  Thread.sleep(5000000);
                                        }

                                        if (homologous != null) {
//                                            System.out.println("HOMOLOGO: " + homologous.toString());

                                            if (Objects.equals(homologous.getGeneOne().getId(), gene.getId())) {
//                                                System.out.println("É IGUAL AO homologous.getGeneOne().getId() == gene.getId()");
                                                predictedInteraction.setOperonTargetGene(homologous.getGeneTwo());
                                            } else if (Objects.equals(homologous.getGeneTwo().getId(), gene.getId())) {
//                                                System.out.println("É IGUAL AO homologous.getGeneTwo().getId() == gene.getId()");
                                                predictedInteraction.setOperonTargetGene(homologous.getGeneOne());
                                            } else {
//                                                System.out.println("NAO PEGOU");
                                            }

                                            predictedInteraction.setHtgEvalue(homologous.getEvalue());
                                            predictedInteraction.setHtgPvalue(homologous.getPvalue());
                                        }

                                        predictedInteraction.setRegulatoryInteraction(predictedRI.getRegulatoryInteraction());
                                        predictedInteraction.setPredictedRole(predictedRI.getPredictedRole());

                                        predictedInteraction.setHomologousTranscriptionFactor(predictedRI.getHomologousTranscriptionFactor());
                                        predictedInteraction.setHtfEvalue(predictedRI.getHtfEvalue());
                                        predictedInteraction.setHtfPvalue(predictedRI.getHtfPvalue());
                                        predictedInteraction.setInteractionPvalue(predictedRI.getInteractionPvalue());

                                        System.out.println(predictedInteraction);
                                        predictedRIDAO.save(predictedInteraction);
//                                        System.out.println("OP EXTENSION SAVED :D");
                                    }
                                }

                            } else {
                                //System.out.println("é operon!");
                                //System.out.println("geneoperon " + geneOperon);
                            }

                            //---------------------------------------
                            //------SAVE ROLE OF THE PREDICTED TF----
                            //---------------------------------------
                            attributePredictedRole(predictedRI.getHomologousTranscriptionFactor(), predictedRI);

                        }

                    } else {
                        System.out.println("no match!\n");
                        noMatches.add(pair);
                    }
                }
            }
        }

//        List<TfTgPair> noMatcheBs = new ArrayList<>();
//        List<String> noMatcheBsString = new ArrayList<>();
//        //System.out.println("\n\nNO MATCHES LIST: ");
//        for (TfTgPair noMatche : noMatches) {
//        //    System.out.println(noMatche.toString());
//            RegulatoryInteraction ri = new RegulatoryInteraction();
//        }
//
//        int countOp = 0;
//
//        //System.out.println("TF\tTG\tBS");
//        for (TfTgPair noMatch : noMatches) {
//            //System.out.println(noMatch.toString());
//            RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
//            List<RegulatoryInteraction> regulatoryInteractionsList = new ArrayList<>();
//
//            //aqui tem que ser multiple. 
//            regulatoryInteractionsList = riDAO.findByTfTgMultiple(noMatch.getTranscriptionFactor().getId(), noMatch.getTargetGene().getId());
//            for (RegulatoryInteraction regulatoryInteraction : regulatoryInteractionsList) {
//                BindingSiteDAO bsDAO = new BindingSiteDAO();
//                List<BindingSite> bs = bsDAO.findByRegularotyInteraction(regulatoryInteraction.getId());
//
//                String bindingSites = "";
//
//                if (bs != null) {
//                    for (int i = 0; i < bs.size(); i++) {
//
//                        if (i == 0) {
//                            bindingSites = bs.get(i).getSequence();
//                        } else {
//                            bindingSites = bindingSites + "," + bs.get(i).getSequence();
//                        }
//
//                    }
//
//                }
//
//                if (bindingSites.isEmpty()) {
//                    bindingSites = "-";
//                } else {
//                    noMatcheBs.add(noMatch);
//                    noMatcheBsString.add(regulatoryInteraction.getCorrespondentTranscriptionFactor().getLocusTag() + "\t" + regulatoryInteraction.getCorrespondentTargetGene().getLocusTag() + "\t" + bindingSites);
//                    Gene targetGene = regulatoryInteraction.getCorrespondentTargetGene();
//                    geneOperon = new GeneOperon();
//                    Operon operon = new Operon();
//                    OperonDAO operonDAO = new OperonDAO();
//                    geneOperon = geneOperonDAO.findByGene(targetGene.getId());
//                    List<GeneOperon> geneOperonList = new ArrayList<>();
//                    Gene gene = new Gene();
//                    GeneDAO geneDAO = new GeneDAO();
//
//                    if (geneOperon != null) {
//                        //System.out.println("----has operon");
//                        operon = operonDAO.findById(geneOperon.getGeneOperonPK().getOperon());
//                        geneOperonList = geneOperonDAO.findByOperon(operon.getId());
//                        for (GeneOperon geneOp : geneOperonList) {
//                            //System.out.println("-----------geneOperon: "+geneOperon.toString());
//                            //PEGAR A REGULACAO DESSE TF PRA ESSE TG
//                            gene = geneDAO.findById(geneOp.getGeneOperonPK().getGene());
//                            List<RegulatoryInteraction> regulations = new ArrayList<>();
//                            regulations = riDAO.bringRisByTfTg(regulatoryInteraction.getCorrespondentTranscriptionFactor().getId(), gene.getId());
//                            if (geneOp.getPos() != 1) {
//                                //System.out.println("NOT 1");
//                                countOp++;
//                                //System.out.println("-\t" + regulations);
//                            } else {
//                                //System.out.println("equal 1");
//
//                            }
//
//                        }
//                        //countOp = countOp + geneOperonList.size()-1;
//                        geneOperonList = new ArrayList<>();
//
//                    }
//
//                }
//
//                //System.out.println(regulatoryInteraction.getCorrespondentTranscriptionFactor().getLocusTag() + "\t" + regulatoryInteraction.getCorrespondentTargetGene().getLocusTag() + "\t" + bindingSites);
//            }
//        }
//        System.out.println("\n\n\nnoMatcheBsString");
//        for (String string : noMatcheBsString) {
//            System.out.println(string);
//        }
    }

}
