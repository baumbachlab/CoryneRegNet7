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
import com.coryneregnet7.dao.PredictedRegulatoryInteractionDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.dao.RunDAO;
import com.coryneregnet7.model.BindingSite;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.GeneOperon;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.HmmProfilesLengths;
import com.coryneregnet7.model.Homologous;
import com.coryneregnet7.model.Run;
import com.coryneregnet7.model.TfsRegulating;
import com.coryneregnet7.processing.input.Input;
import com.coryneregnet7.processing.input.OperonFileReader;
import com.coryneregnet7.processing.statistics.CoregulatorsStatisticsCacl;
import com.coryneregnet7.processing.statistics.HmmProfilesLengthsCalc;
import com.coryneregnet7.processing.statistics.RegulatorsRegulationsCalc;
import com.coryneregnet7.processing.statistics.StatisticsOverviewCacl;
import com.coryneregnet7.processing.statistics.TfsRegulatingCacl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariana
 */
public class RunPipeline {

    public static void main(String[] args) throws IOException, InterruptedException {
        RunPipeline runTransfer = new RunPipeline();

        String transferFolder = "/data/coryne-genus/test";
        String targetfolder = "/data/coryne-genus/target";
        String templateFolder = "/data/coryne-genus/model";
//       
        String blastEvalue = "1e-10";
        String hmmerEvalue = "100";
        GenomeDAO genomeDAO = new GenomeDAO();

        List<Genome> targetGenomes = genomeDAO.findByOrgnismType("target");

        System.out.println("target genomes: ");
        for (Genome targetGenome : targetGenomes) {
            System.out.println(targetGenome.toString());
        }

        List<Genome> modelGenomes = genomeDAO.findByOrgnismType("model");
        //List<Genome> modelGenomes = new ArrayList<>();

        System.out.println("template genomes: ");
        for (Genome templateGenome : modelGenomes) {
            System.out.println(templateGenome.toString());
        }
        Run run = new Run();
        RunDAO runDAO = new RunDAO();
        run.setBlastCutoff(blastEvalue);
        run.setHmmerCutoff(hmmerEvalue);
        run = (Run) runDAO.save(run);
        runTransfer.runPipeline(transferFolder, targetfolder, templateFolder, blastEvalue, hmmerEvalue, modelGenomes, targetGenomes, run);
        //runTransfer.run(transferFolder, targetfolder, templateFolder, evalue);
    }

    public void runPipeline(String transferFolderPath, String targetGenomesFolderPath, String templateGenomesFolderPath,
            String blastEvalue, String hmmerEvalue,
            List<Genome> modelGenomes, List<Genome> targetGenomes,
            Run run) throws InterruptedException, IOException {

        /*
        PREDICT OPERONS TO EVERY TARGET GENOME
         */
          System.out.println("OPERONS PREDICTIONS FOR TARGET GENOMES DONE");
        predictOperons(targetGenomes, run);
          System.out.println("op prediction done");

        /*
        CREATE HMMER PROFILES TO EACH TRANSCRIPTION FACTOR OF EACH MODEL GENOME
         */
        System.out.println("HMM CREATE START");
         createModelHmmerProfiles(modelGenomes, run);
        System.out.println("hmm done ;D");

        /*
        PREDICT PROMOTER REGIONS FOR EVERY GENOME IN THE ANALYSIS
         */
        System.out.println("Predict promoter regions");
          predictPromoterRegion(modelGenomes, run, "template");
         predictPromoterRegion(targetGenomes, run, "target");
        System.out.println("PROMOTER REGION PREDICTION DONE");

        /*
        RUN MODEL-MODEL BLAST
         */
         runBlast(transferFolderPath, templateGenomesFolderPath,
          blastEvalue, modelGenomes, run, "template");
        /*
        RUN TARGET-TARGET BLAST
         */
        runBlast(transferFolderPath, targetGenomesFolderPath,
              blastEvalue, targetGenomes, run, "target");

        /*
        RUN BLAST, FIND THE HOMOLOGOUS TF/TG PAIRS of each target-model organism pairs
        AND RUN HMMER IN THE PROMOTER REGIONS OF THE SELECTED TARGET GENES.
         */
           TransferRegularoryNetwork transfer = new TransferRegularoryNetwork();
           transfer.run(transferFolderPath, targetGenomesFolderPath, templateGenomesFolderPath,
                   targetGenomes, modelGenomes, blastEvalue, hmmerEvalue, run);
        /*
        CREATE HMMER PROFILES TO EACH TRANSCRIPTION
        FACTOR OF EACH TARGET GENOME USING THE PREDICTED TRNs
         */
        System.out.println("CREATE HMMER PROFILE TARGET");
        createTargetHmmProfile(targetGenomes, run);
        calculateStatistics();
        System.out.println("DONE :) ");

    }

    public void calculateStatistics() {
        CoregulatorsStatisticsCacl coStatistics = new CoregulatorsStatisticsCacl();
        coStatistics.calculate();
        HmmProfilesLengthsCalc hmmOLStatistics = new HmmProfilesLengthsCalc();
        hmmOLStatistics.calculate();
        RegulatorsRegulationsCalc rrStatistics = new RegulatorsRegulationsCalc();
        rrStatistics.calculate();
        StatisticsOverviewCacl soStatistics = new StatisticsOverviewCacl();
        soStatistics.calculate();
        TfsRegulatingCacl tfsRefStatistics = new TfsRegulatingCacl();
        tfsRefStatistics.calculate();
    }

    public void runBlast(String transferFolderPath, String genomesFolderPath,
            String blastEvalue, List<Genome> genomes, Run run, String type) throws IOException {
        Integer total = calculateBlastRunSize(genomes.size(), 2);

        RunDAO runDAO = new RunDAO();
        if (type.equals("template")) {
            run.setBlastTemplate("0/" + total);
        } else {
            run.setBlastTarget("0/" + total);
        }
        runDAO.update(run);
        int countBlastRun = 0;

        String blastPath = transferFolderPath + "/blast";
        File blastFolder = new File(blastPath);
        createFolder(blastFolder);

        List<String> genomeNames = bringNames(genomes);

        TransferRegularoryNetwork blaster = new TransferRegularoryNetwork();
        blaster.createBlastDatabase(genomesFolderPath, genomeNames);

        for (int i = 0; i < genomes.size(); i++) {
            for (int j = i; j < genomes.size(); j++) {
                Genome genomeOne = genomes.get(i);
                Genome genometwo = genomes.get(j);
                String nameOne = genomeNames.get(i);
                String nameTwo = genomeNames.get(j);

                //-----------------------------------------------------
                //create blast directory
                File blastBidirecionalFolder = new File(blastPath + "/blast-bidirecional-" + nameOne + "x" + nameTwo);
                createFolder(blastBidirecionalFolder);

                String blastFirstSide = "blastp -query '" + genomesFolderPath + "/" + nameOne + "_protein.faa' -db '" + genomesFolderPath + "/" + nameTwo + "_protein.faa' -out " + nameOne + "x" + nameTwo + "-" + blastEvalue + "-blast-result.txt -outfmt 7 -evalue " + blastEvalue + " -num_threads 4";
                String blastOtherSide = "blastp -query '" + genomesFolderPath + "/" + nameTwo + "_protein.faa' -db '" + genomesFolderPath + "/" + nameOne + "_protein.faa' -out " + nameTwo + "x" + nameOne + "-" + blastEvalue + "-blast-result.txt -outfmt 7 -evalue " + blastEvalue + " -num_threads 4";

                System.out.println("blastFirstSide: " + blastFirstSide);
                System.out.println("blastOtherSide: " + blastOtherSide);

                blaster.runBlast(blastFirstSide, blastOtherSide, blastBidirecionalFolder);
                countBlastRun++;

                if (type.equals("template")) {
                    run.setBlastTemplate(countBlastRun + "/" + total);
                } else {
                    run.setBlastTarget(countBlastRun + "/" + total);
                }
                runDAO.update(run);

                GenomeDAO genomeDAO = new GenomeDAO();
                //String blastFileFirstSideOld = transferenceFolderPath + "/" + templateGenome + "x" + targetGenome + "/blast-bidirecional-" + templateGenome + "x" + targetGenome + "/" + templateGenome + "x" + targetGenome + "-" + blastEvalue + "-blast-result.txt";
                String blastFileFirstSide = blastBidirecionalFolder.getAbsolutePath() + "/" + nameOne + "x" + nameTwo + "-" + blastEvalue + "-blast-result.txt";
                //String blastFileOtherSideOld = transferenceFolderPath + "/" + templateGenome + "x" + targetGenome + "/blast-bidirecional-" + templateGenome + "x" + targetGenome + "/" + targetGenome + "x" + templateGenome + "-" + blastEvalue + "-blast-result.txt";
                String blastFileOtherSide = blastBidirecionalFolder.getAbsolutePath() + "/" + nameTwo + "x" + nameOne + "-" + blastEvalue + "-blast-result.txt";

                System.out.println("\nblastFileFirstSide: " + blastFileFirstSide);
                System.out.println("blastFileOthertSide: " + blastFileOtherSide);

                System.out.println("TEMPLATE GENOME OBJ: " + genomeOne);
                System.out.println("TARGET GENOME OBJ: " + genometwo);

                HomologyDetection hd = new HomologyDetection();
                List<String[]> bestHitsFirstSide = hd.findBestHits(genomeOne, genometwo, blastFileFirstSide);
                List<String[]> bestHitsOtherSide = hd.findBestHits(genomeOne, genometwo, blastFileOtherSide);
                List<String[]> bbhs = hd.findBidirectionalBestHits(bestHitsFirstSide, bestHitsOtherSide, genomeOne, genometwo);
                hd.saveHomologous(bbhs, genomeOne, genometwo);
                //System.out.println("homologous " + homologousList.size() + "\n\n");
                List<TfTgPair> homologousPairs = hd.bringTfTgPairs(genomeOne, genometwo);

                //      System.out.println(homologousPairs.size() + " HOMOLOGOUS PAIRS:");
                //    System.out.println("\n\nMODEL: " + genomeOne);
                //  System.out.println("TARGET: " + genometwo);

                /*
                /------------------------------------------------------------------------------------
                //----------------------GET-BLAST-RESULTS-&-SAVE-HOMOLOGOUS---------------------------
                //------------------------------------------------------------------------------------
                GenomeDAO genomeDAO = new GenomeDAO();
                Genome templateGenomeObj = genomeDAO.findByGbffFile(templateGenomesFolderPath + "/" + templateGenome + "_genomic.gbff");
                Genome targetGenomeObj = genomeDAO.findByGbffFile(targetGenomesFolderPath + "/" + targetGenome + "_genomic.gbff");

                //transferenceFolderPath + "/blast/Blast-Bidirecional-" + templateGenome + "x" + targetGenome
                String blastFileFirstSide = transferenceFolderPath + "/" + templateGenome + "x" + targetGenome + "/blast-bidirecional-" + templateGenome + "x" + targetGenome + "/" + templateGenome + "x" + targetGenome + "-" + blastEvalue + "-blast-result.txt";
                String blastFileOtherSide = transferenceFolderPath + "/" + templateGenome + "x" + targetGenome + "/blast-bidirecional-" + templateGenome + "x" + targetGenome + "/" + targetGenome + "x" + templateGenome + "-" + blastEvalue + "-blast-result.txt";

                System.out.println("\nblastFileFirstSide: " + blastFileFirstSide);
                System.out.println("blastFileOthertSide: " + blastFileOtherSide);

                System.out.println("TEMPLATE GENOME OBJ: " + templateGenomeObj);
                System.out.println("TARGET GENOME OBJ: " + targetGenomeObj);
                
              //  Thread.sleep(5000);
                HomologyDetection hd = new HomologyDetection();
                List<String[]> bestHitsFirstSide = hd.findBestHits(templateGenomeObj, targetGenomeObj, blastFileFirstSide);
                List<String[]> bestHitsOtherSide = hd.findBestHits(templateGenomeObj, targetGenomeObj, blastFileOtherSide);
                List<String[]> bbhs = hd.findBidirectionalBestHits(bestHitsFirstSide, bestHitsOtherSide, templateGenomeObj, targetGenomeObj);
                List<Homologous> homologousList = hd.saveHomologous(bbhs, templateGenomeObj, targetGenomeObj);
                System.out.println("homologous " + homologousList.size() + "\n\n");
                List<TfTgPair> homologousPairs = hd.bringTfTgPairs(templateGenomeObj, targetGenomeObj);

                System.out.println(homologousPairs.size() + " HOMOLOGOUS PAIRS:");

                System.out.println("\n\nMODEL: " + templateGenome);
                System.out.println("TARGET: " + targetGenome);
                 */
            }
        }

    }

    public Integer calculateBlastRunSize(Integer n, Integer r) {

        // CombinatoricsUtils.factorial(n)
        Integer result = (factorial(n + r - 1)) / (factorial(r) * factorial(n - 1));
        return result;
    }

    public Integer factorial(int n) {
        Integer fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }

    public List<String> bringNames(List<Genome> genomes) {
        List<String> genomeNames = new ArrayList<>();

        //System.out.println("=====> targetGenomeFILES");
        String name = "";
        for (int i = 0; i < genomes.size(); i++) {
            name = genomes.get(i).getGbffFile();
            String[] split = name.split("\\/");
            genomeNames.add(split[split.length - 1].replace("_genomic.gbff", ""));
            System.out.println("==>" + genomeNames.get(i));
            name = "";
        }

        return genomeNames;
    }

    public void predictOperons(List<Genome> targetGenomes, Run run) throws InterruptedException {
       
        GeneOperonDAO geneOperonDAO = new GeneOperonDAO();
        RunDAO runDAO = new RunDAO();
        run.setOperons("0/" + targetGenomes.size());
        runDAO.update(run);

        int count = 0;
        for (Genome targetGenome : targetGenomes) {
            //Thread.sleep(5000);
             System.out.println("\ntargetGenome "+targetGenome.getGenomeName()+" "+targetGenome.getId());
            Long numOp = geneOperonDAO.countOpByGenome(targetGenome.getId());
            System.out.println("numOp.compareTo(new Long(\"0\")) == " + numOp.compareTo(new Long("0")));
            if (numOp.compareTo(new Long("0")) == 0) {
                

                OperonPrediction op = new OperonPrediction();
                String gbffFile = targetGenome.getGbffFile();

//                boolean targetOpExists = new File(gbffFile.replace("_genomic.gbff", ".op")).exists();
//                if (targetOpExists) {
//                    System.out.println("LEU OPERON PARA " + targetGenome.getId());
//                    OperonFileReader operonFileReader = new OperonFileReader();
//                    operonFileReader.readOperonFile(gbffFile.replace("_genomic.gbff", ".op"), targetGenome);
//                } else {
                    System.out.println("PREDISSE OPERON PARA " + targetGenome.getId());
                    op.predictOperon(gbffFile, targetGenome);
//                }

            } else {
                System.out.println("NAO PREDISSE OPERON PARA " + targetGenome.getId());
            }
            count++;
            run.setOperons(count + "/" + targetGenomes.size());
            runDAO.update(run);

        }
    }

    public void input(String transferFolder, String templateFolder, String targetfolder) throws InterruptedException {
        Input input = new Input();
        input.input(transferFolder, templateFolder, targetfolder);
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

    public void predictPromoterRegion(List<Genome> genomes, Run run, String role) throws IOException, InterruptedException {
        System.out.println("predictPromoterRegion!!!");
        RunDAO runDAO = new RunDAO();

        if (role.equals("template")) {
            run.setPromoterTemplate("0/" + genomes.size());
        } else {
            run.setPromoterTarget("0/" + genomes.size());
        }
        runDAO.update(run);
        int countRun = 0;

        GeneDAO geneDAO = new GeneDAO();
        GeneOperonDAO geneOperonDAO = new GeneOperonDAO();

        PromoterRegionPrediction predictPromoterRegion = new PromoterRegionPrediction();

        for (Genome genome : genomes) {
            System.out.println("predicting promoter region => " + genome.getGenomeName() + " " + genome.getId());
            //Thread.sleep(5000);
            List<Gene> allGenes = geneDAO.findByGenome(genome.getId());

            for (Gene gene : allGenes) {
                System.out.println("gene " + gene.toString());
                geneOperonDAO = new GeneOperonDAO();
                GeneOperon geneOperon = geneOperonDAO.findByGene(gene.getId());
                System.out.println("busca gene_operon");
                geneOperon = geneOperonDAO.findByGene(gene.getId());
                System.out.println("gene-operon " + geneOperon);
                if (geneOperon != null) {

                    if (geneOperon.getPos() == 1) {
                        predictPromoterRegion.predict(gene, gene.getGenome().getFnaFile());
                        gene = geneDAO.findById(gene.getId());
                        List<GeneOperon> geneOperonsByOperon = geneOperonDAO.findByOperon(geneOperon.getGeneOperonPK().getOperon());
                        for (GeneOperon geneOperon1 : geneOperonsByOperon) {
                            if (geneOperon1.getPos() != 1) {
                                Gene operonGene = geneDAO.findById(geneOperon1.getGeneOperonPK().getGene());
                                operonGene.setPromoterRegion(gene.getPromoterRegion());
                                geneDAO.update(operonGene);
                            }
                        }
                    }

                } else {
                    predictPromoterRegion.predict(gene, gene.getGenome().getFnaFile());

                }
            }

            countRun++;
            if (role.equals("template")) {
                run.setPromoterTemplate(countRun + "/" + genomes.size());
            } else {
                run.setPromoterTarget(countRun + "/" + genomes.size());
            }
            runDAO.update(run);
        }
    }

    public void createModelHmmerProfiles(List<Genome> modelGenomes, Run run) throws IOException, InterruptedException {
        RunDAO runDAO = new RunDAO();
        run.setProfilesTemplate("0/" + modelGenomes.size());
        runDAO.update(run);
        int countRun = 0;

        for (Genome modelGenome : modelGenomes) {

            File chipDataConsensus = new File(modelGenome.getGbffFile().replace("_genomic.gbff", "_chip_consensus.csv"));
            if (!chipDataConsensus.exists()) {

                //Thread.sleep(5000);
                RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
                List<Gene> transcriptionFactors = riDAO.findByGenomeDistinct(modelGenome.getId());

                //**************************
                //create hmm profiles folder
                //**************************
                String hmmProfilesFolderPath = modelGenome.getFaaFile();
                String splited[] = hmmProfilesFolderPath.split("\\/");
                hmmProfilesFolderPath = hmmProfilesFolderPath.replace(splited[splited.length - 1], "");
                String genomeName = splited[splited.length - 1].replace("_protein.faa", "");
                hmmProfilesFolderPath = hmmProfilesFolderPath + genomeName + "-hmmer-profiles";

                File hmmProfilesFolder = new File(hmmProfilesFolderPath);
                createFolder(hmmProfilesFolder);

                BindingSiteDAO bsDAO = new BindingSiteDAO();
                int count = 0;
                for (Gene transcriptionFactor : transcriptionFactors) {

                    String fastaFile = hmmProfilesFolder.getAbsolutePath() + "/" + transcriptionFactor.getLocusTag() + ".fasta";
                    String stoFile = hmmProfilesFolder.getAbsolutePath() + "/" + transcriptionFactor.getLocusTag() + ".sto";
                    String hmmProfileFile = hmmProfilesFolder.getAbsolutePath() + "/" + transcriptionFactor.getLocusTag() + ".hmm";

                    System.out.println("transcriptionFactor => " + transcriptionFactor);

                    List<BindingSite> bindingSites = new ArrayList<>();
                    GeneDAO geneDAO = new GeneDAO();

                    bindingSites = bsDAO.findByRegularotyTF(transcriptionFactor.getId());

                    FileWriter fileWriter = null;
                    PrintWriter printWriter = null;

                    if (bindingSites.size() > 0) {
                        try {
                            fileWriter = new FileWriter(fastaFile);
                            printWriter = new PrintWriter(fileWriter);

                            transcriptionFactor.setBsNumber(bindingSites.size());
                            geneDAO.update(transcriptionFactor);

                            for (BindingSite bindingSite : bindingSites) {

                                String generatedString = "";

                                count++;
                                generatedString = transcriptionFactor.getLocusTag() + "bindingsite" + count;
                                printWriter.println(">" + generatedString);
                                printWriter.println(bindingSite.getSequence().toUpperCase());

                                if (bindingSites.size() == 1) {
                                    count++;
                                    generatedString = transcriptionFactor.getLocusTag() + "bindingsite" + count;
                                    printWriter.println(">" + generatedString);
                                    printWriter.println(bindingSite.getSequence().toUpperCase());
                                }

                            }

                            printWriter.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {

                            if (fileWriter != null) {
                                fileWriter.close();
                            }

                            if (printWriter != null) {
                                printWriter.close();
                            }
                        }
                    }

                    //*******************************************************
                    //RUN CLUSTAL OMEGA TO CREATE .STO FILES FOR THE HMMBUILD
                    //*******************************************************
                    if (bindingSites.size() > 0) {
                        AlignBindingSites align = new AlignBindingSites();
                        align.runClustalOmega(fastaFile, stoFile, hmmProfilesFolderPath);

                        //HMM PROFILE CREATING
                        RunHmmer runHmmer = new RunHmmer();
                        runHmmer.runHmmBuilder(hmmProfileFile, stoFile, hmmProfilesFolderPath);

                        //SAVE THE HMM PROFILE PATH IN THE DATABASE. 
                        transcriptionFactor.setHmmProfile(hmmProfileFile);
                        Integer profileLength = checkProfileLength(hmmProfileFile);
                        transcriptionFactor.setProfileLength(profileLength);
                        geneDAO.update(transcriptionFactor);

                    }

                }

                countRun++;
                run.setProfilesTemplate(countRun + "/" + modelGenomes.size());
                runDAO.update(run);
            }
        }
    }

    public void createTargetHmmProfile(List<Genome> targetGenomes, Run run) throws IOException, InterruptedException {
        RunDAO runDAO = new RunDAO();
        run.setProfilesTarget("0/" + targetGenomes.size());
        runDAO.update(run);
        int countRun = 0;

        GeneDAO geneDAO = new GeneDAO();

        for (Genome targetGenome : targetGenomes) {
            //Thread.sleep(5000);
            PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
            List<Gene> transcriptionFactors = priDAO.findByGenomeDistinct(targetGenome.getId());
            //        System.out.println("\n\n");
            for (Gene transcriptionFactor : transcriptionFactors) {
                //          System.out.println("=======>" + transcriptionFactor.toString());
            }

            //**************************
            //create hmm profiles folder
            //**************************
            String hmmProfilesFolderPath = targetGenome.getFaaFile();
            String splited[] = hmmProfilesFolderPath.split("\\/");
            hmmProfilesFolderPath = hmmProfilesFolderPath.replace(splited[splited.length - 1], "");
            String genomeName = splited[splited.length - 1].replace("_protein.faa", "");
            hmmProfilesFolderPath = hmmProfilesFolderPath + genomeName + "-hmmer-profiles";
            //    System.out.println("\n\n--------------------------------------");
            //   System.out.println(hmmProfilesFolderPath);
            //   System.out.println("---------------------------------------");

            File hmmProfilesFolder = new File(hmmProfilesFolderPath);
            createFolder(hmmProfilesFolder);
//
            BindingSiteDAO bsDAO = new BindingSiteDAO();
            int count = 0;
            for (Gene transcriptionFactor : transcriptionFactors) {

                String fastaFile = hmmProfilesFolder.getAbsolutePath() + "/" + transcriptionFactor.getLocusTag() + ".fasta";
                String stoFile = hmmProfilesFolder.getAbsolutePath() + "/" + transcriptionFactor.getLocusTag() + ".sto";
                String hmmProfileFile = hmmProfilesFolder.getAbsolutePath() + "/" + transcriptionFactor.getLocusTag() + ".hmm";

                FileWriter fileWriter = new FileWriter(fastaFile);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                List<BindingSite> bindingSites = new ArrayList<>();
                geneDAO = new GeneDAO();
                try {

                    //         System.out.println("=======>" + transcriptionFactor.toString());
                    //         System.out.println("-------> " + fastaFile);
                    priDAO = new PredictedRegulatoryInteractionDAO();
                    bindingSites = priDAO.findByHtf(transcriptionFactor.getId());

                    if (bindingSites.size() > 0) {
                        transcriptionFactor.setBsNumber(bindingSites.size());
                        geneDAO.update(transcriptionFactor);
                    }

                    for (BindingSite bindingSite : bindingSites) {

                        String generatedString = "";

                        count++;
                        generatedString = transcriptionFactor.getLocusTag() + "_binding_site" + count;
                        //           System.out.println(">" + generatedString);
                        printWriter.println(">" + generatedString);
                        printWriter.println(bindingSite.getSequence().toUpperCase());

                        if (bindingSites.size() == 1) {
                            count++;
                            generatedString = transcriptionFactor.getLocusTag() + "bindingsite" + count;
                            printWriter.println(">" + generatedString);
                            printWriter.println(bindingSite.getSequence().toUpperCase());
                        }

                    }

                } catch (Exception e) {

                } finally {

                    if (fileWriter != null) {
                        fileWriter.close();
                    }

                    if (printWriter != null) {
                        printWriter.close();
                    }

                }
                //*******************************************************
                //RUN CLUSTAL OMEGA TO CREATE .STO FILES FOR THE HMMBUILD
                //*******************************************************

                //IF THERE IS MORE THAN ONE BINDING SITE 
                //CREATE THE ALIGNMENT FILE FOR THE HMMPROFILE. 
                //CREATE THE HMMPROFILE
                if (bindingSites.size() > 0) {
                    AlignBindingSites align = new AlignBindingSites();
                    align.runClustalOmega(fastaFile, stoFile, hmmProfilesFolderPath);

                    RunHmmer runHmmer = new RunHmmer();
                    runHmmer.runHmmBuilder(hmmProfileFile, stoFile, hmmProfilesFolderPath);

                    //SAVE THE HMM PROFILE PATH IN THE DATABASE. 
                    transcriptionFactor.setHmmProfile(hmmProfileFile);

                    Integer profileLength = checkProfileLength(hmmProfileFile);
                    transcriptionFactor.setProfileLength(profileLength);
                    geneDAO.update(transcriptionFactor);

                }

            }
            //Thread.sleep(1000);
            countRun++;
            run.setProfilesTarget(countRun + "/" + targetGenomes.size());
            runDAO.update(run);
        }
    }

    public Integer checkProfileLength(String profile) {
        Integer length = 0;
        BufferedReader br = null;
        FileReader fr = null;

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader(profile));

            while ((sCurrentLine = br.readLine()) != null) {
                // System.out.println(sCurrentLine);

                if (sCurrentLine.startsWith("LEN")) {
                    sCurrentLine = sCurrentLine.trim();
                    String[] splitedLine = sCurrentLine.split("  ");
                    length = Integer.parseInt(splitedLine[1]);
                    return length;
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

        return length;
    }

}
