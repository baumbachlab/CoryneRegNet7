/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing;

import com.coryneregnet7.controller.BindingSiteRegulationPrediction;
import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GeneOperonDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.HomologousDAO;
import com.coryneregnet7.dao.OperonDAO;
import com.coryneregnet7.dao.PredictedRegulatoryInteractionDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.GeneOperon;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Homologous;
import com.coryneregnet7.model.Operon;
import com.coryneregnet7.model.Organism;
import com.coryneregnet7.model.PredictedRegulatoryInteraction;
import com.coryneregnet7.model.RegulatoryInteraction;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author doglas
 */
public class SearchBindingSites {

    public static void main(String[] args) throws IOException {
        String transferenceFolder = "/home/doglas/NetBeansProjects/CoryneRegNet7";
        ArrayList<Genome> genomes = new ArrayList<>();
        GenomeDAO genomeDAO = new GenomeDAO();
        genomes = (ArrayList<Genome>) genomeDAO.listAll();
        GeneDAO geneDAO = new GeneDAO();
        Gene targetGene = geneDAO.findById(61231);
        String evalue = "1e-35";
//        ArrayList<String> targetGenomes = new ArrayList<>();
//        targetGenomes.add("Cp258");
//        targetGenomes.add("CpT1");

        SearchBindingSites searchBSs = new SearchBindingSites();
        //System.out.println(searchBSs.callHMMERThisGene(transferenceFolder, targetGene, genomes, evalue));

    }

    public Object[] callHMMERThisGene(String transferenceFolderPath, Gene targetGene, ArrayList<Organism> organisms, String evalue, Integer minNOfHMMs) throws IOException {
        RunHmmer runHmmer = new RunHmmer();
        boolean findMatch = false;
        BindingSitePredictionInfo bsPredictionInfo = new BindingSitePredictionInfo();
        int usedHMMs = 0;
        Object[] bsPredictedInfo = new Object[2];
        String homologousGenesAux = new String();
        List<String> homologousGenesList = new ArrayList<>();
        HomologousDAO homologDAO = new HomologousDAO();
        List<Homologous> homologs = new ArrayList<>();
        String[] homologousGenes = new String[2];
        ArrayList<BindingSiteRegulationPrediction> bsRegPredictionsAux = new ArrayList<>();
        ArrayList<BindingSiteRegulationPrediction> bsRegPredictions = new ArrayList<>();

        //System.out.println("\n\nMODEL: " + templateGenome);
        //System.out.println("TARGET: " + targetSequence);
        //-----------------------------------------------------
        //create hmmer directory
        //CRIAR O HMMER FOLDER PARA ESSA ANALISE NA RAIZ DO PROJETO. 
        //DENTRO DESTE HMMER FOLDER TEM QUE TER UMA PASTA RESULT. 
        System.out.println("Target: " + targetGene.getLocusTag());
        File hmmerFolder = new File(transferenceFolderPath + "/hmmer");
        if (!hmmerFolder.exists()) {
            if (hmmerFolder.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }

        File hmmerResultFolder = new File(hmmerFolder.getAbsolutePath() + "/result");
        if (!hmmerResultFolder.exists()) {
            if (hmmerResultFolder.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }

        ArrayList<Gene> genes = new ArrayList<>();
        GeneDAO geneDAO = new GeneDAO();
        for (Organism organism : organisms) {
            genes = (ArrayList<Gene>) geneDAO.findByOrganismRoleMinBsNum(organism.getId(), minNOfHMMs);
            usedHMMs += genes.size();
            for (Gene gene : genes) {

                //System.out.println("oi");
                //System.out.println("promoterRegion: " + targetGene.getPromoterRegion().getFile());
                //System.out.println(pair.toString());
                //hmmerFolder
                //System.out.println("promoterRegion: " + promoterRegion);
                String bindingSite = "";

                if (gene.getHmmProfile() != null) {
                    bindingSite = gene.getHmmProfile();
                } else {
                    String hmmProfilesFolderPath = gene.getGenome().getFaaFile();
                    String splited[] = hmmProfilesFolderPath.split("\\/");
                    hmmProfilesFolderPath = hmmProfilesFolderPath.replace(splited[splited.length - 1], "");
                    String targetGenomeName = splited[splited.length - 1].replace("_protein.faa", "");
                    hmmProfilesFolderPath = hmmProfilesFolderPath + targetGenomeName + "-hmmer-profiles";

                    bindingSite = hmmProfilesFolderPath + "/" + gene.getProteinId() + ".fasta";
                }

//                System.out.println("BINDING SITE: " + bindingSite);
                //              System.out.println("targetGene.getPromoterRegion().getFile() " + targetGene.getPromoterRegion().getFile());
                String resultFile = gene.getLocusTag() + "_" + targetGene.getLocusTag() + ".out";

                if (evalue.equals("default")) {
                    //                System.out.println("Default evalue");
                    runHmmer.runOnce(bindingSite, targetGene.getPromoterRegion().getFile(), hmmerFolder.getAbsolutePath(), resultFile);
                } else {
                    //              System.out.println("Evalue: " + evalue);
                    runHmmer.runOnceEvalue(bindingSite, targetGene.getPromoterRegion().getFile(), hmmerFolder.getAbsolutePath(), resultFile, evalue);
                }
                //        System.out.println("resultFile: " + resultFile);
                //      System.out.println("hmmerResultFolder.getAbsolutePath(): " + hmmerResultFolder.getAbsolutePath());
                findMatch = runHmmer.findMatch(resultFile, hmmerResultFolder.getAbsolutePath());
                //System.out.println(findMatch);
                if (findMatch) {
                    bsPredictionInfo = runHmmer.getMatchInfo(hmmerResultFolder + "/" + resultFile, targetGene.getOrientation(),
                            targetGene.getPromoterRegion().getInitialPosition(), targetGene.getPromoterRegion().getInitialPosition(),
                            targetGene.getStartPosition());
                    bsPredictionInfo.setPutativeTF(gene);
                    bsPredictionInfo.setPutativeTG(targetGene);
                    //System.out.println(bsPredictionInfo.toString());
                    //(bsPredictionInfo.getPutativeTG());

                    //Check if the backgound and the choosed organisms are the same
                    if (targetGene.getGenome().getOrganism().getId().equals(organism.getId())) {
                        //System.out.println("Same Organism!!!!!!");
                        bsRegPredictionsAux = new ArrayList<>();
                        bsRegPredictionsAux = bsViewObjectSameOrganism(bsPredictionInfo, 1);
                        for (int i = 0; i < bsRegPredictionsAux.size(); i++) {
                            bsRegPredictions.add(bsRegPredictionsAux.get(i));
                        }

                        //If the backgound and the choosed organisms are not the same
                    } else {

                        homologousGenes = verifyHomologousGenes(gene, targetGene.getGenome().getOrganism().getId(), targetGene, gene.getGenome().getOrganism().getId());
                        if (homologousGenes == null) {
                            continue;
                        } else {
                            //Create the object to send to the bindingsite screen
                            bsRegPredictionsAux = bsViewObjectOtherOrganism(bsPredictionInfo, homologousGenes[0], homologousGenes[1], 1);
                            for (int i = 0; i < bsRegPredictionsAux.size(); i++) {
                                bsRegPredictions.add(bsRegPredictionsAux.get(i));
                            }
                        }
                    }
                }
            }
            //System.out.println("\n\n");
        }
        //System.out.println("usedHMMs: " + usedHMMs);
        //System.out.println("DONE!");
        ///ArrayList<BindingSiteRegulationPrediction> bsRegPredictions = bsViewObject(predictionInfoBSs, homologousGenesList, 0);
        bsPredictedInfo[0] = usedHMMs;
        bsPredictedInfo[1] = bsRegPredictions;
        return bsPredictedInfo;
    }

//Find genes that could be regulated by the interestGene
    public ArrayList<BindingSiteRegulationPrediction> callHMMERThisRegulator(String transferenceFolderPath, Gene interestGene, String evalue, Integer organismId) throws IOException {
        RunHmmer runHmmer = new RunHmmer();
        boolean findMatch = false;
        BindingSitePredictionInfo bsPredictionInfo = new BindingSitePredictionInfo();
        GeneOperon geneOperon = new GeneOperon();
        GeneOperonDAO geneOperonDAO = new GeneOperonDAO();
        String homologousGenesAux = new String();
        HomologousDAO homologDAO = new HomologousDAO();
        Homologous homologousTF = new Homologous();
        Homologous homologousTG = new Homologous();
        String homologousTFString = new String();
        String homologousTGString = new String();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        RegulatoryInteraction ri = new RegulatoryInteraction();
        ArrayList<BindingSiteRegulationPrediction> bsRegPredictions = new ArrayList<>();
        ArrayList<BindingSiteRegulationPrediction> bsRegPredictionsAux = new ArrayList<>();
        BindingSiteRegulationPrediction bsRegPred = new BindingSiteRegulationPrediction();
        boolean alreadyPredicted;
        GeneOperon geneOp = new GeneOperon();
        GeneOperonDAO goDAO = new GeneOperonDAO();
        String[] homologousGenes = new String[2];

        System.out.println("Fun starting!!!!!!!!!");
        System.out.println("OrganismId: " + organismId);
        File hmmerFolder = new File(transferenceFolderPath + "/hmmer");
        if (!hmmerFolder.exists()) {
            if (hmmerFolder.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }

        File hmmerResultFolder = new File(hmmerFolder.getAbsolutePath() + "/result");
        if (!hmmerResultFolder.exists()) {
            if (hmmerResultFolder.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }

        ArrayList<Gene> genes = new ArrayList<>();
        GeneDAO geneDAO = new GeneDAO();
        genes = (ArrayList<Gene>) geneDAO.findByOrganism(organismId);
        String bindingSite = "";

        if (interestGene.getHmmProfile() != null) {
            bindingSite = interestGene.getHmmProfile();
        } else {
            String hmmProfilesFolderPath = interestGene.getGenome().getFaaFile();
            String splited[] = hmmProfilesFolderPath.split("\\/");
            hmmProfilesFolderPath = hmmProfilesFolderPath.replace(splited[splited.length - 1], "");
            String targetGenomeName = splited[splited.length - 1].replace("_protein.faa", "");
            hmmProfilesFolderPath = hmmProfilesFolderPath + targetGenomeName + "-hmmer-profiles";

            bindingSite = hmmProfilesFolderPath + "/" + interestGene.getProteinId() + ".fa";
        }

        //for each gene of the choosed organisms
        for (Gene gene : genes) {
            geneOp = new GeneOperon();
            geneOp = goDAO.findByGene(gene.getId());
            if (geneOp == null || geneOp.getPos() == 1) {
                String resultFile = interestGene.getLocusTag() + "_" + gene.getLocusTag() + ".out";

                if (evalue.equals("default")) {
                    runHmmer.runOnce(bindingSite, gene.getPromoterRegion().getFile(), hmmerFolder.getAbsolutePath(), resultFile);
                } else {
                    runHmmer.runOnceEvalue(bindingSite, gene.getPromoterRegion().getFile(), hmmerFolder.getAbsolutePath(), resultFile, evalue);
                }

                //Check the HMMER results
                findMatch = runHmmer.findMatch(resultFile, hmmerResultFolder.getAbsolutePath());
                //System.out.println(findMatch);

                //If HMMER finds something 
                if (findMatch) {
                    //System.out.println("YESSSSSSSSSSSSS!!!!!!!!!!!!!");
                    bsPredictionInfo = runHmmer.getMatchInfo(hmmerResultFolder + "/" + resultFile, gene.getOrientation(),
                            gene.getPromoterRegion().getInitialPosition(), gene.getPromoterRegion().getInitialPosition(),
                            gene.getStartPosition());
                    bsPredictionInfo.setPutativeTF(interestGene);
                    bsPredictionInfo.setPutativeTG(gene);
                    //System.out.println(bsPredictionInfo.toString());
                    //System.out.println("interestGene.getGenome().getOrganism().getId(): " + interestGene.getGenome().getOrganism().getId() + " == " + " organismId: " + organismId);

                    //Check if the backgound and the choosed organisms are the same
                    if (interestGene.getGenome().getOrganism().getId().equals(organismId)) {
                        //System.out.println("Same Organism!!!!!!");
                        bsRegPredictionsAux = new ArrayList<>();
                        bsRegPredictionsAux = bsViewObjectSameOrganism(bsPredictionInfo, 1);
                        for (int i = 0; i < bsRegPredictionsAux.size(); i++) {
                            //System.out.println(bsRegPredictionsAux.get(i));
                            bsRegPredictions.add(bsRegPredictionsAux.get(i));
                        }

                        //If the backgound and the choosed organisms are not the same
                    } else {
                        //Check for homologous TF(it need to be a regulator) and homologous genes regulated by the TF 
                        homologousGenes = verifyHomologousGenes(interestGene, organismId, gene, interestGene.getGenome().getOrganism().getId());
                        if (homologousGenes == null) {
                            continue;
                        } else {
                            //Create the object to send to the bindingsite screen
                            bsRegPredictionsAux = bsViewObjectOtherOrganism(bsPredictionInfo, homologousGenes[0], homologousGenes[1], 1);
                            for (int i = 0; i < bsRegPredictionsAux.size(); i++) {
                                bsRegPredictions.add(bsRegPredictionsAux.get(i));
                            }
                        }
                    }
                }
            }
        }
        return bsRegPredictions;
    }

    //Creates the object to send to the bindingsite screen when the background and the choosed organim are the same
    public ArrayList<BindingSiteRegulationPrediction> bsViewObjectSameOrganism(BindingSitePredictionInfo predictionInfoBS, int option) {
        ArrayList<BindingSiteRegulationPrediction> bsRegPredictions = new ArrayList<>();
        BindingSiteRegulationPrediction bsRegPred = new BindingSiteRegulationPrediction();
        ArrayList<GeneOperon> genesOperon = new ArrayList<>();
        Operon operon = new Operon();
        OperonDAO operonDAO = new OperonDAO();
        GeneOperon geneOperon = new GeneOperon();
        GeneOperonDAO geneOperonDAO = new GeneOperonDAO();
        RegulatoryInteraction ri = new RegulatoryInteraction();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        PredictedRegulatoryInteraction pri = new PredictedRegulatoryInteraction();
        Gene geneOpAux = new Gene();
        GeneDAO geneDAO = new GeneDAO();
        List<Gene> genes = new ArrayList<>();

        //System.out.println("bsViewObjectSameOrganism Starts!!!!!!!!");
        //check for operons
        geneOperon = geneOperonDAO.findByGene(predictionInfoBS.getPutativeTG().getId());
        bsRegPred = new BindingSiteRegulationPrediction();
        ri = new RegulatoryInteraction();
        pri = new PredictedRegulatoryInteraction();
        ri = riDAO.findByTfTg(predictionInfoBS.getPutativeTF().getId(), predictionInfoBS.getPutativeTG().getId());
        pri = priDAO.findByTfTg(predictionInfoBS.getPutativeTF().getId(), predictionInfoBS.getPutativeTG().getId());

//        //If the target gene belongs to an operon
//        if (geneOperon != null) {
//            //System.out.println("GenesOperon: " + geneOperon.toString());
//            genesOperon = (ArrayList<GeneOperon>) geneOperonDAO.findByOperon(geneOperon.getGeneOperonPK().getOperon());
//            operon = operonDAO.findById(geneOperon.getGeneOperonPK().getOperon());
//            //System.out.println(operon.getName());
//            //System.out.println("Operon Extention Starts");
//            //System.out.println("predictionInfoBS.getPutativeTG().getLocusTag(): " + predictionInfoBS.getPutativeTG().getLocusTag());
//            for (int k = 0; k < genesOperon.size(); k++) {
//                bsRegPred = new BindingSiteRegulationPrediction();
//                if (genesOperon.get(k).getPos() == 1) {
//                    bsRegPred.setEvalue(predictionInfoBS.getEvalue());
//                    bsRegPred.setSequence(predictionInfoBS.getFoundBindingSite());
//                }
//                if ((ri != null && ri.getCorrespondentTranscriptionFactor().equals(predictionInfoBS.getPutativeTF())) || (pri != null && pri.getHomologousTranscriptionFactor().equals(predictionInfoBS.getPutativeTF()))) {
//                    bsRegPred.setKnowedRegulation("Yes");
//                } else {
//                    bsRegPred.setKnowedRegulation("No");
//                }
//                bsRegPred.setOperon(operon);
//                bsRegPred.setTranscriptionFactor(predictionInfoBS.getPutativeTF());
//
//                geneOpAux = geneDAO.findById(genesOperon.get(k).getGeneOperonPK().getGene());
//                bsRegPred.setTargetGene(geneOpAux);
//                bsRegPred.setHomologousTFs("-");
//                bsRegPred.setHomologousTGs("-");
//                //System.out.println(bsRegPred.toString());
//                bsRegPredictions.add(bsRegPred);
//            }
//            //System.out.println("Operon Extention Ends!!!!");
//
//            //If the TG not belongs to a operon
//        } else {
        if (geneOperon != null) {
            //System.out.println("GenesOperon: " + geneOperon.toString());
            genesOperon = (ArrayList<GeneOperon>) geneOperonDAO.findByOperon(geneOperon.getGeneOperonPK().getOperon());
            operon = operonDAO.findById(geneOperon.getGeneOperonPK().getOperon());
        }

        bsRegPred = new BindingSiteRegulationPrediction();
        bsRegPred.setEvalue(predictionInfoBS.getEvalue());
        bsRegPred.setSequence(predictionInfoBS.getFoundBindingSite());
        if ((ri != null && ri.getCorrespondentTranscriptionFactor().equals(predictionInfoBS.getPutativeTF())) || (pri != null && pri.getHomologousTranscriptionFactor().equals(predictionInfoBS.getPutativeTF()))) {
            bsRegPred.setKnowedRegulation("Yes");
        } else {
            bsRegPred.setKnowedRegulation("No");
        }
        bsRegPred.setTranscriptionFactor(predictionInfoBS.getPutativeTF());
        bsRegPred.setTargetGene(predictionInfoBS.getPutativeTG());
        // System.out.println("________________bsRegPred ->" + bsRegPred.getHomologousGenes());
        bsRegPred.setHomologousTFs("-");
        bsRegPred.setHomologousTGs("-");
        bsRegPred.setOperon(operon);
        bsRegPredictions.add(bsRegPred);
        //}
        return bsRegPredictions;
    }

    //Creates the object to the binding sitescreen if the background and the choosed organisms are not the same
    public ArrayList<BindingSiteRegulationPrediction> bsViewObjectOtherOrganism(BindingSitePredictionInfo predictionInfoBS, String homologousTF, String homologousTG, int option) {
        ArrayList<BindingSiteRegulationPrediction> bsRegPredictions = new ArrayList<>();
        BindingSiteRegulationPrediction bsRegPred = new BindingSiteRegulationPrediction();
        ArrayList<GeneOperon> genesOperon = new ArrayList<>();
        Operon operon = new Operon();
        OperonDAO operonDAO = new OperonDAO();
        GeneOperon geneOperon = new GeneOperon();
        GeneOperonDAO geneOperonDAO = new GeneOperonDAO();
        Gene geneOpAux = new Gene();
        GeneDAO geneDAO = new GeneDAO();
        String homologousGenesAux = new String();
        HomologousDAO homologDAO = new HomologousDAO();
        List<Homologous> homologs = new ArrayList<>();
        String[] genesID;
        String homologousLocusTags = new String();
        Gene geneAux = new Gene();
        Integer id;
        Homologous operonGenesHomologous = new Homologous();
        String[] homologousOpGenes;
        Gene geneInOp = new Gene();

        System.out.println("AQUI1");
        //Check if Tg belongs to an operon
        geneOperon = geneOperonDAO.findByGene(predictionInfoBS.getPutativeTG().getId());
        //System.out.println(geneOperon);
        bsRegPred = new BindingSiteRegulationPrediction();
        //If the Tg belongs to an operon
//        if (geneOperon != null) {
//            //System.out.println(geneOperon.toString());
//            genesOperon = (ArrayList<GeneOperon>) geneOperonDAO.findByOperon(geneOperon.getGeneOperonPK().getOperon());
//            operon = operonDAO.findById(geneOperon.getGeneOperonPK().getOperon());
//            //System.out.println(operon.getName());
//
//            //For each gene that belongs to the operon
//            for (int k = 0; k < genesOperon.size(); k++) {
//                bsRegPred = new BindingSiteRegulationPrediction();
//                if (genesOperon.get(k).getPos() == 1) {
//                    bsRegPred.setEvalue(predictionInfoBS.getEvalue());
//                    bsRegPred.setSequence(predictionInfoBS.getFoundBindingSite());
//                }
//                bsRegPred.setKnowedRegulation("-");
//                bsRegPred.setOperon(operon);
//                bsRegPred.setTranscriptionFactor(predictionInfoBS.getPutativeTF());
//                //System.out.println(go.toString());
//
//                geneOpAux = geneDAO.findById(genesOperon.get(k).getGeneOperonPK().getGene());
//                bsRegPred.setTargetGene(geneOpAux);
//
//                //If the TG and the gene recovered of the operon are the same
//                //We previosly have the information about the homolgous gene. Then it don't need to search again 
//                if (predictionInfoBS.getPutativeTG().getId().equals(geneOpAux.getId())) {
//                    if (homologousTF != null && !homologousTF.equals("-")) {
//                        //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                        //System.out.println("homologousTF= " + homologousTF);
//                        geneAux = new Gene();
//                        id = Integer.parseInt(homologousTF);
//                        geneAux = geneDAO.findById(id);
//                        homologousLocusTags = "";
//                        homologousLocusTags = geneAux.getLocusTag();
//                        bsRegPred.setHomologousTFs(homologousLocusTags);
//                    } else {
//                        // System.out.println("bsRegPred.getTargetGene(): " + bsRegPred.getTargetGene());
//                        bsRegPred.setHomologousTFs("-");
//                    }
//
//                    if (homologousTG != null && !homologousTG.equals("-")) {
//                        geneAux = new Gene();
//                        id = Integer.parseInt(homologousTG);
//                        geneAux = geneDAO.findById(id);
//                        homologousLocusTags = "";
//                        homologousLocusTags = geneAux.getLocusTag();
//                        bsRegPred.setHomologousTGs(homologousLocusTags);
//                    } else {
//                        bsRegPred.setHomologousTGs("-");
//                    }
//
//                    //If the TG and the gene recovered of the operon are not the same
//                    //Search for homolgous genes
//                } else {
//                    homologousOpGenes = verifyHomologousGenes(predictionInfoBS.getPutativeTF(), predictionInfoBS.getPutativeTG().getGenome().getOrganism().getId(), geneOpAux, predictionInfoBS.getPutativeTF().getGenome().getOrganism().getId());
//                    if (homologousOpGenes != null) {
//                        if (homologousOpGenes[0] != null && homologousOpGenes[0] != "-") {
//                            //System.out.println("It isn't null!!!!!!! YEAHHHHHHHHHHHHHHHHHHHHHHHHH");
//                            //Tf part
//                            geneAux = new Gene();
//                            id = Integer.parseInt(homologousOpGenes[0]);
//                            geneAux = geneDAO.findById(id);
//                            homologousLocusTags = geneAux.getLocusTag();
//                            bsRegPred.setHomologousTFs(homologousLocusTags);
//                        } else {
//                            bsRegPred.setHomologousTFs("-");
//                        }
//
//                        if (homologousOpGenes[1] != null && homologousOpGenes[1] != "-") {
//                            //Tg part
//                            geneAux = new Gene();
//                            id = Integer.parseInt(homologousOpGenes[1]);
//                            geneAux = geneDAO.findById(id);
//                            homologousLocusTags = geneAux.getLocusTag();
//                            bsRegPred.setHomologousTGs(homologousLocusTags);
//                        } else {
//                            //System.out.println("It is nulllllllllllllllllllllllllllll!!!!!!!!! WHY????????????????????????????");
//                            bsRegPred.setHomologousTGs("-");
//                        }
//                    } else if (genesOperon.get(k).getPos().equals(1)) {
//                        break;
//                    } else {
//                        bsRegPred.setHomologousTFs("-");
//                        bsRegPred.setHomologousTGs("-");
//                    }
//                }
//                bsRegPredictions.add(bsRegPred);
//            }
//
//            //If the TG not Tg belongs to an operon
//        } else {

        if (geneOperon != null) {
            System.out.println(geneOperon.toString());
            genesOperon = (ArrayList<GeneOperon>) geneOperonDAO.findByOperon(geneOperon.getGeneOperonPK().getOperon());
            operon = operonDAO.findById(geneOperon.getGeneOperonPK().getOperon());
        }

        bsRegPred = new BindingSiteRegulationPrediction();
        bsRegPred.setEvalue(predictionInfoBS.getEvalue());
        bsRegPred.setSequence(predictionInfoBS.getFoundBindingSite());
        bsRegPred.setKnowedRegulation("-");
        bsRegPred.setTranscriptionFactor(predictionInfoBS.getPutativeTF());
        bsRegPred.setTargetGene(predictionInfoBS.getPutativeTG());
        bsRegPred.setOperon(operon);

        if (homologousTF != null && !homologousTF.equals("-")) {
            //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            //System.out.println("homologousTF= " + homologousTF);
            geneAux = new Gene();
            System.out.println("1");
            id = Integer.parseInt(homologousTF);
            System.out.println("2");
            geneAux = geneDAO.findById(id);
            System.out.println("3");
            homologousLocusTags = "";
            System.out.println("4");
            homologousLocusTags = geneAux.getLocusTag();
            System.out.println("5");
            bsRegPred.setHomologousTFs(homologousLocusTags);
            System.out.println("6");
        } else {
            //System.out.println("bsRegPred.getTargetGene(): " + bsRegPred.getTargetGene());
            bsRegPred.setHomologousTFs("-");
        }

        if (homologousTG != null && !homologousTG.equals("-")) {
            geneAux = new Gene();
            id = Integer.parseInt(homologousTG);
            geneAux = geneDAO.findById(id);
            homologousLocusTags = "";
            homologousLocusTags = geneAux.getLocusTag();
            bsRegPred.setHomologousTGs(homologousLocusTags);
        } else {
            bsRegPred.setHomologousTGs("-");
        }

        // System.out.println("________________bsRegPred ->" + bsRegPred.getHomologousGenes());
        bsRegPredictions.add(bsRegPred);
        //}
        return bsRegPredictions;
    }
    //Check for putative homologous genes

    private String[] verifyHomologousGenes(Gene tf, Integer organismIdForTF, Gene tg, Integer organismIdForTG) {
        //System.out.println("Homologous Genes identification starts");
        //System.out.println("tf: " + tf);
        //System.out.println("organismIdForTF: " + organismIdForTF);
        //System.out.println("tg: " + tg);
        //System.out.println("organismIdForTG: " + organismIdForTG);
        HomologousDAO homologDAO = new HomologousDAO();
        Homologous homologousTF = homologDAO.findByGeneOrganism(tf.getId(), organismIdForTF);
        Homologous homologousTG = homologDAO.findByGeneOrganism(tg.getId(), organismIdForTG);
        String[] homologousGenes = new String[2];
        RegulatoryInteraction ri = new RegulatoryInteraction();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        PredictedRegulatoryInteraction pri = new PredictedRegulatoryInteraction();

//        System.out.println("homologousTG: " + homologousTG.toString());
        if (homologousTF == null && homologousTG == null) {
            homologousGenes[0] = "-";
            homologousGenes[1] = "-";
        } else {
            if (homologousTF != null) {
                if (homologousTF.getGeneOne().getId().equals(tf.getId())) {
                    //System.out.println("homologousTF.getGeneTwo().getLocusTag(): " + homologousTF.getGeneTwo().getLocusTag());
                    //Check if the homologousTF is knowed to be a regulator
                    if (homologousTF.getGeneTwo().getRole() != null && (homologousTF.getGeneTwo().getRole().equals("A") || homologousTF.getGeneTwo().getRole().equals("R") || homologousTF.getGeneTwo().getRole().equals("Dual"))) {
                        //System.out.println("homologousTF: " + homologousTF.getGeneTwo().getLocusTag());
                        //System.out.println("TG.getLocusTag(): " + tg.getLocusTag());
                        ri = riDAO.findByTfTg(homologousTF.getGeneTwo().getId(), tg.getId());
                        pri = priDAO.findByTfTg(homologousTF.getGeneTwo().getId(), tg.getId());
                        //System.out.println("ri TF GENE1 => " + ri);
                        //System.out.println("pri TF GENE1 => " + pri);
                        if (ri != null || pri != null) {
                            //System.out.println("Findend!!!!!!!!!!!!!");
                            homologousGenes[0] = "" + homologousTF.getGeneTwo().getId();
                        } else {
                            homologousGenes[0] = "-";
                        }
                    } else {
                        homologousGenes[0] = "-";
                    }
                } else {
                    //System.out.println("homologousTF.getGeneOne().getLocusTag(): " + homologousTF.getGeneOne().getLocusTag());
                    //Check if the homologousTF is knowed to be a regulator
                    if (homologousTF.getGeneOne().getRole() != null && (homologousTF.getGeneOne().getRole().equals("A") || homologousTF.getGeneOne().getRole().equals("R") || homologousTF.getGeneOne().getRole().equals("Dual"))) {
                        //System.out.println("homologousTF: " + homologousTF.getGeneOne().getLocusTag());
                        // System.out.println("TG.getLocusTag(): " + tg.getLocusTag());
                        ri = riDAO.findByTfTg(homologousTF.getGeneOne().getId(), tg.getId());
                        pri = priDAO.findByTfTg(homologousTF.getGeneOne().getId(), tg.getId());
                        //System.out.println("ri TF GENE1 => " + ri);
                        //System.out.println("pri TF GENE1 => " + pri);
                        if (ri != null || pri != null) {
                            //System.out.println("Findend!!!!!!!!!!!!!");
                            homologousGenes[0] = "" + homologousTF.getGeneOne().getId();
                        } else {
                            homologousGenes[0] = "-";
                        }
                    } else {
                        homologousGenes[0] = "-";
                    }
                }
            }

            if (homologousTG != null) {
                //Check if the TG has a homologousTG regulated by the TF of interest
                if (homologousTG.getGeneOne().getId().equals(tg.getId())) {
                    //System.out.println("TF: " + tf.getLocusTag());
                    //System.out.println("homologousTG.getGeneTwo().getLocusTag(): " + homologousTG.getGeneTwo().getLocusTag());
                    ri = riDAO.findByTfTg(tf.getId(), homologousTG.getGeneTwo().getId());
                    pri = priDAO.findByTfTg(tf.getId(), homologousTG.getGeneTwo().getId());
                    //System.out.println("ri TF GENE1 => " + ri);
                    //System.out.println("pri TF GENE1 => " + pri);
                    if (ri != null || pri != null) {
                        //System.out.println("Findend!!!!!!!!!!!!!");
                        homologousGenes[1] = "" + homologousTG.getGeneTwo().getId();
                    } else {
                        homologousGenes[1] = "-";
                    }
                } else {
                    //System.out.println("TF: " + tf.getLocusTag());
                    //System.out.println("homologousTG.getGeneOne().getLocusTag(): " + homologousTG.getGeneOne().getLocusTag());
                    ri = riDAO.findByTfTg(tf.getId(), homologousTG.getGeneOne().getId());
                    pri = priDAO.findByTfTg(tf.getId(), homologousTG.getGeneOne().getId());
                    //System.out.println("ri TF GENE2 => " + ri);
                    //System.out.println("pri TF GENE2 => " + pri);
                    if (ri != null || pri != null) {
                        //System.out.println("Findend!!!!!!!!!!!!!");
                        homologousGenes[1] = "" + homologousTG.getGeneOne().getId();
                    } else {
                        homologousGenes[1] = "-";
                    }
                }
            }
        }

        //System.out.println("homologousGenes[0]: " + homologousGenes[0]);
        //System.out.println("homologousGenes[1]: " + homologousGenes[1]);
        return homologousGenes;
    }
}
