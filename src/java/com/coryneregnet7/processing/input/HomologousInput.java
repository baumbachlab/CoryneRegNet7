/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.input;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.HomologousDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Homologous;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ubuntu
 */
public class HomologousInput {

    public static void main(String[] args) {

        BufferedReader br = null;
        FileReader fr = null;

        HomologousDAO homologousDAO = new HomologousDAO();
        GeneDAO geneDAO = new GeneDAO();

        Hashtable<String, Integer> genomes = new Hashtable<>();
//    genomes.put("GCF_000227175.1_ASM22717v1", Integer.SIZE);
//        genomes.put("GCF_003813965.1_ASM381396v1", Integer.SIZE);
//        genomes.put("GCF_001447295.1_ASM144729v1", Integer.SIZE);
//        genomes.put("GCF_000828015.1_ASM82801v1", Integer.SIZE);
//        genomes.put("GCF_001922285.1_ASM192228v1", Integer.SIZE);
//        genomes.put("GCF_000255155.1_ASM25515v1", Integer.SIZE);
//        genomes.put("GCF_000550785.1_ASM55078v1", Integer.SIZE);
//        genomes.put("GCF_001021065.1_ASM102106v1", Integer.SIZE);
//        genomes.put("GCF_900637065.1_46941_D01", Integer.SIZE);
//        genomes.put("GCF_001889145.1_ASM188914v1", Integer.SIZE);
//        genomes.put("GCF_000215665.1_ASM21566v1", Integer.SIZE);
//        genomes.put("GCF_001941465.1_ASM194146v1", Integer.SIZE);
//        genomes.put("GCF_000144935.1_ASM14493v2", Integer.SIZE);
//        genomes.put("GCF_001941345.1_ASM194134v1", Integer.SIZE);
//        genomes.put("GCF_000241895.1_ASM24189v1", Integer.SIZE);
//        genomes.put("GCF_000590555.1_ASM59055v1", Integer.SIZE);
//        genomes.put("GCF_003065405.1_ASM306540v1", Integer.SIZE);
//        genomes.put("GCF_003194045.1_ASM319404v1", Integer.SIZE);
//        genomes.put("GCF_000221625.1_ASM22162v1", Integer.SIZE);
//        genomes.put("GCF_002843135.1_ASM284313v1", Integer.SIZE);
//        genomes.put("GCF_002355675.1_ASM235567v1", Integer.SIZE);
//        genomes.put("GCF_001017615.1_ASM101761v1", Integer.SIZE);
//        genomes.put("GCF_900478045.1_47555_C02", Integer.SIZE);
//        genomes.put("GCF_002073375.2_ASM207337v2", Integer.SIZE);
//        genomes.put("GCF_000835165.1_ASM83516v1", Integer.SIZE);
//        //genomes.put("GCF_000005845.2_ASM584v2", Integer.SIZE);
//        genomes.put("GCF_002869805.1_ASM286980v1", Integer.SIZE);
//        genomes.put("GCF_001969145.1_ASM196914v2", Integer.SIZE);
//        genomes.put("GCF_002005245.1_ASM200524v1", Integer.SIZE);
//        genomes.put("GCF_002847425.1_ASM284742v1", Integer.SIZE);
//        genomes.put("GCF_001941505.1_ASM194150v1", Integer.SIZE);
//        genomes.put("GCF_001866985.1_ASM186698v1", Integer.SIZE);
//        genomes.put("GCF_002009415.1_ASM200941v1", Integer.SIZE);
//       // genomes.put("GCF_000011325.1_ASM1132v1", Integer.SIZE);
       // genomes.put("GCF_001643035.1_ASM164303v1", Integer.SIZE);//VER SE TEM OS PRI DEPOIS.
        genomes.put("GCF_001579865.1_ASM157986v1", Integer.SIZE);
        genomes.put("GCF_002794435.1_ASM279443v1", Integer.SIZE);
        genomes.put("GCF_000265545.2_ASM26554v2", Integer.SIZE);
        genomes.put("GCF_004332375.1_ASM433237v1", Integer.SIZE);
        genomes.put("GCF_000968945.1_ASM96894v1", Integer.SIZE);
        genomes.put("GCF_001481715.1_ASM148171v1", Integer.SIZE);
        genomes.put("GCF_002243555.1_ASM224355v1", Integer.SIZE);
        genomes.put("GCF_000255935.1_ASM25593v1", Integer.SIZE);
        genomes.put("GCF_000306825.1_ASM30682v1", Integer.SIZE);
        genomes.put("GCF_000255275.1_ASM25527v1", Integer.SIZE);
        genomes.put("GCF_004941425.1_ASM494142v1", Integer.SIZE);
        genomes.put("GCF_000259155.3_ASM25915v3", Integer.SIZE);
        genomes.put("GCF_000241875.1_ASM24187v1", Integer.SIZE);
        genomes.put("GCF_001922265.1_ASM192226v1", Integer.SIZE);
        genomes.put("GCF_000404185.1_ASM40418v1", Integer.SIZE);
        genomes.put("GCF_002009385.1_ASM200938v1", Integer.SIZE);
        genomes.put("GCF_900637605.1_52121_H02", Integer.SIZE);
        genomes.put("GCF_001021045.1_ASM102104v1", Integer.SIZE);
        genomes.put("GCF_004193955.1_ASM419395v1", Integer.SIZE);
        genomes.put("GCF_000241935.1_ASM24193v1", Integer.SIZE);
        genomes.put("GCF_002072715.1_ASM207271v1", Integer.SIZE);
        genomes.put("GCF_001561975.1_ASM156197v1", Integer.SIZE);
        genomes.put("GCF_002277975.1_ASM227797v1", Integer.SIZE);
        genomes.put("GCF_000258385.1_ASM25838v1", Integer.SIZE);
        genomes.put("GCF_001433475.1_ASM143347v1", Integer.SIZE);
        genomes.put("GCF_001867125.1_ASM186712v1", Integer.SIZE);
        genomes.put("GCF_000814865.1_ASM81486v1", Integer.MIN_VALUE);


        GenomeDAO genomeDAO = new GenomeDAO();
        for (Map.Entry<String, Integer> entry : genomes.entrySet()) {
            String key = entry.getKey();
            //System.out.println("key "+key);
            Integer value = entry.getValue();
            Integer id = genomeDAO.findByGenomeName(key).getId();
            genomes.put(key, id);
           // System.out.println(key+" => "+genomes.get(key));

        }

        try {
            String sCurrentLine;

            br = new BufferedReader(new FileReader("/home/ubuntu/database/Bacilus_homologs_part.txt"));
            List<Gene> genesTarget = geneDAO.findByGenome(1280);
            List<Gene> genesModel = geneDAO.findByGenome(1227);
            String currentGenome = "GCF_000227175.1_ASM22717v1";

            //System.out.println("genesTarget.size(): " + genesTarget.size());
            while ((sCurrentLine = br.readLine()) != null) {
                Homologous homologous = new Homologous();
                System.out.println(sCurrentLine);
                String[] splitedLine = sCurrentLine.split("\t");
                if (splitedLine[4] != currentGenome) {
                    currentGenome = splitedLine[4];
                    genesTarget = geneDAO.findByGenome(genomes.get(currentGenome));
                }

                Gene geneTarget = new Gene();
                for (Gene gene : genesTarget) {
                    if (gene.getLocusTag().equals(splitedLine[1])) {
                        geneTarget = gene;
                        break;
                    }
                    //Gene target = geneDAO.findByGenomeLocusTag(1302, splitedLine[1]);
                }
                Gene geneModel = new Gene();
                for (Gene gene : genesModel) {
                    if (gene.getLocusTag().equals(splitedLine[0])) {
                        geneModel = gene;
                        break;
                    }
                    //Gene model = geneDAO.findByGenomeLocusTag(1228, splitedLine[0]);
                }
                System.out.println("geneModel -> " + geneModel.toString());
                System.out.println("geneTarget -> " + geneTarget.toString());

                if (geneModel == null) {
                    System.out.println("geneModel == null");
                    System.out.println(sCurrentLine);
                }

                if (geneTarget == null) {
                    System.out.println("geneTarget == null");
                    System.out.println(sCurrentLine);
                }

                if (geneModel != null && geneTarget != null) {
                    homologous.setEvalue(new BigDecimal(splitedLine[2]));
                   // homologous.setPvalue(new BigDecimal(splitedLine[3]));
                    homologous.setGeneOne(geneModel);
                    homologous.setGeneTwo(geneTarget);

                    homologous = (Homologous) homologousDAO.save(homologous);
                    System.out.println(homologous);
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

    }
}
