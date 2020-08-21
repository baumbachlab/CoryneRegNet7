/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GeneOperonDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.HomologousDAO;
import com.coryneregnet7.dao.OrganismDAO;
import com.coryneregnet7.dao.PredictedRegulatoryInteractionDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.dao.StatisticsOverviewDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.GeneOperon;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Homologous;
import com.coryneregnet7.model.Organism;
import com.coryneregnet7.model.PredictedRegulatoryInteraction;
import com.coryneregnet7.model.RegulatoryInteraction;
import com.coryneregnet7.model.StatisticsOverview;
import com.coryneregnet7.processing.RunPipeline;
import com.coryneregnet7.processing.TfTgPair;
import com.coryneregnet7.processing.TransferRegularoryNetwork;
import com.coryneregnet7.processing.output.CytoscapeFile;
import com.coryneregnet7.processing.output.OperonsFile;
import com.coryneregnet7.processing.output.RegulationsFile;
import com.coryneregnet7.processing.output.ZipFiles;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.ui.Model;

/**
 *
 * @author mariana
 */
public class FreeTest {

    public static void main(String[] args) throws IOException {

       /// usingFileChannel();

//        
//        Homologous homologous = new Homologous();
//        HomologousDAO homologousDAO = new HomologousDAO();
//        
//        homologous = homologousDAO.findByPair(2077283, 2212941);
//        
//////        RunPipeline runPipeline = new RunPipeline();
//////        runPipeline.calculateStatistics();
//        FreeTest free = new FreeTest();
//        free.download();
//        HomologousDAO homologousDAO = new HomologousDAO();
//        List<Homologous> transcriptionFactor;
//
//        transcriptionFactor = homologousDAO.findByGeneGenomeMultiple(2512696, 2239);
//        if (transcriptionFactor != null) {
//            for (Homologous homologous : transcriptionFactor) {
//                System.out.println("\n\ntranscriptionFactor = " + homologous.toString2());
//
//            }
//        } else {
//            System.out.println("NAO ACHOU o tf :(");
//        }
///////////
//        OrganismDAO organismDAO = new OrganismDAO();
//        Organism organism = new Organism();
//        
//        Gene gene = new Gene();
//        GeneDAO geneDAO = new GeneDAO();
//        gene = geneDAO.findByLocusTag("CU_RS00470");
//        System.out.println(gene.getGenome());
/////////////
//        
//        for (Organism organism1 : organisms) {
//            System.out.println(organism1);
//            
//            GenomeDAO genomeDAO = new GenomeDAO();
//            Genome genome = new Genome();
//            
//            genome = genomeDAO.findByOrganism(organism1.getId());
//            System.out.println(genome);
//            System.out.println("\n");
//        }
//        List<Organism> organisms = organismDAO.findByType("model");
//        StatisticsOverviewDAO soDAO = new StatisticsOverviewDAO();
//        StatisticsOverview so = soDAO.findByDatabase("experimental");
//
//        Integer numberOfGenes = so.getGenes();
//        //System.out.println("Total number of genes: " + numberOfGenes);
//        Integer numberOfGenomes = so.getGenomes();
//        //System.out.println("Total number of genomes: " + numberOfGenomes);
//
//        System.out.println("numberOfGenes "+numberOfGenes);
//        System.out.println("numberOfGenomes "+numberOfGenomes);
//        geneOperon = geneOperonDAO.findByGene(gene.getId());
//        System.out.println("gene-operon " + geneOperon);
//        /*
//        gene start: 1065405
//        PROMOTER START 1064845
//        PROMOTER END 1065424
//        startPosition: 501
//        new startPosition: 1065345
//        endPosition: 518
//        new endPosition: 1065362
//         */
//        BigInteger endDistance = new BigInteger("-60");
//        System.out.println("endDistance: "+endDistance);
//        System.out.println("endDistance.compareTo(BigInteger.ZERO) < 0 "+(endDistance.compareTo(BigInteger.ZERO) < 0));
//        
//        endDistance = endDistance.multiply(new BigInteger("-1"));
//        System.out.println("endDistance: "+endDistance);
    }

    public static void usingFileChannel() throws IOException {
        RandomAccessFile stream = new RandomAccessFile("samplefile6.txt", "rw");
        FileChannel channel = stream.getChannel();

        String fileContent = "Hello Learner !! Welcome to howtodoinjava.com.";
        for (int i = 0; i < 10; i++) {
            fileContent = i + "\n";
            byte[] strBytes = fileContent.getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
            buffer.put(strBytes);
            buffer.flip();
            channel.write(buffer);
        }

        stream.close();
        channel.close();
    }

    public void download() throws FileNotFoundException, IOException {

        int searchType = 1;
        Integer organismPred = 0;
        Integer organismExp = 0;
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

                zipFileName = "TemplateOrganismsFiles.zip";
                zipFile += zipFileName;
                zipFiles.zipFile(filesToZip, zipFile);
            } else {
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

                String[] aux = operonsFilesNames.get(0).split("_");
                for (int i = 0; i < aux.length - 1; i++) {
                    zipFileName += aux[i];
                }
                zipFileName += ".zip";
                zipFile += zipFileName;
                zipFiles.zipFile(filesToZip, zipFile);
            }
        } else {
            if (organismPred == 0) {
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

                zipFileName = "AllOrganismsFiles.zip";
                zipFile += zipFileName;
                zipFiles.zipFile(filesToZip, zipFile);
            } else {
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

                Organism organism = orgDAO.findById(organismPred);
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

                String[] aux = operonsFilesNames.get(0).split("_");
                for (int i = 0; i < aux.length - 1; i++) {
                    zipFileName += aux[i];
                }
                zipFileName += ".zip";
                zipFile += zipFileName;
                zipFiles.zipFile(filesToZip, zipFile);
            }
        }
    }

    public List<TfTgPair> bringTfTgPairs(Genome model, Genome target) {
        List<TfTgPair> pairs = new ArrayList<TfTgPair>();

        //buscar todas as regulações daquele modelo. 
        List<RegulatoryInteraction> regulatoryInteractions = new ArrayList<RegulatoryInteraction>();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        regulatoryInteractions = riDAO.findByGenome(model.getId());

        HomologousDAO homologousDAO = new HomologousDAO();
        Homologous transcriptionFactor = new Homologous();
        Homologous targetGene = new Homologous();
        List<Homologous> targetGeneList = new ArrayList<>();

        for (RegulatoryInteraction regulatoryInteraction : regulatoryInteractions) {
            System.out.println("-------------------------------------------------------------------------------------------------------");
            System.out.println("RI " + regulatoryInteraction);
            System.out.println("getCorrespondentTranscriptionFactor " + regulatoryInteraction.getCorrespondentTranscriptionFactor());
            System.out.println("getCorrespondentTargetGene " + regulatoryInteraction.getCorrespondentTargetGene());

            transcriptionFactor = homologousDAO.findByGeneGenome(regulatoryInteraction.getCorrespondentTranscriptionFactor().getId(), target.getId());
            if (transcriptionFactor != null) {
                System.out.println("\n\ntranscriptionFactor = " + transcriptionFactor);
            } else {
                System.out.println("NAO ACHOU o tf :(");
            }

            //Homologous homologous = homologousDAO.findByGeneGenome(regulatoryInteraction.getCorrespondentTargetGene().getId(), target.getId());
            targetGeneList = homologousDAO.findByGeneGenomeMultiple(regulatoryInteraction.getCorrespondentTargetGene().getId(), target.getId());
            //if()
            for (int i = 0; i < targetGeneList.size(); i++) {
                System.out.println("-------ANDOU NO FOR--------------");
                targetGene = targetGeneList.get(i);
                System.out.println("--------targetGene " + targetGene);
                if (targetGene != null) {
                    System.out.println("targetGene = " + targetGene + "\n\n");
                } else {
                    System.out.println("NAO ACHOU o tg :(");
                }

                if (transcriptionFactor != null && targetGene != null) {
                    System.out.println("FOUND :D");
                    TfTgPair pair = new TfTgPair();

                    System.out.println("regulatoryInteraction.getCorrespondentTranscriptionFactor() " + regulatoryInteraction.getCorrespondentTranscriptionFactor());
                    System.out.println("transcriptionFactor.getGeneOne().getId() " + transcriptionFactor.getGeneOne().getId());

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
                    System.out.println(pair.toString2());

                }

            }
            transcriptionFactor = new Homologous();
            targetGene = new Homologous();
            System.out.println("\n\n");

        }
        return pairs;
    }

}
