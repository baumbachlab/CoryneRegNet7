/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.RnaInteractionDAO;
import com.coryneregnet7.dao.SmallRnaDAO;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.SmallRna;
import com.coryneregnet7.processing.output.RnaFile;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mariana
 */
public class SmallRnaTest {

    public static void main(String[] args) {
       
            SmallRnaTest srTest = new SmallRnaTest();
            srTest.changek411Names();
        
    }
    
    
    //"Cj-K411=NCTC11915-sRNA-1" 1390
    //"Cj-K411-sRNA-1" 1390
    
    public void changek411Names(){
        SmallRnaDAO srnaDAO = new SmallRnaDAO();
                
        List<SmallRna> srnas = srnaDAO.findByGenome(1390);
        
        for (SmallRna srna : srnas) {
            System.out.println(srna.toString());
            if(srna.getLocusTag().startsWith("Cj-K411=NCTC11915-")){
                String locus = srna.getLocusTag();
                locus = locus.replace("Cj-K411=NCTC11915-", "Cj-K411-");
                System.out.println("locus: "+locus);
                srna.setLocusTag(locus);
                srnaDAO.update(srna);
            }
            
        }
    }
    
    public void changeFiguresNamesBSRD(){
        
    }

    public void changeFiguresNamesCgHomologous() throws InterruptedException {
        // /data/home/mariana/NetBeansProjects/CoryneRegNet7/web/images/srnas

        Hashtable<String, Integer> genomes
                = new Hashtable<String, Integer>();

        genomes.put("c-efficiens", 1243);
        genomes.put("c-diphtheriae", 1230);
        genomes.put("c-jeikeium", 1390);
        genomes.put("c-pseudotuberculosis", 1274);
        genomes.put("c-ulcerans", 1288);
        genomes.put("c-glutamicum", 1226);

        File folder = new File("/data/home/mariana/NetBeansProjects/CoryneRegNet7/web/images/srnas");
        File[] listOfFiles = folder.listFiles();
        int nullSrna = 0;

        String organismName = "";
        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
                if (file.getName().startsWith("c-")) {
                    System.out.println("--name: " + file.getName());
                    String[] splitedName = file.getName().split("-");
                    for (int i = 0; i < splitedName.length; i++) {
                        System.out.println("----------------------" + splitedName[i]);
                    }

                    organismName = splitedName[0] + "-" + splitedName[1];
                    System.out.println("organismName " + organismName);
                    Integer genome = genomes.get(organismName);
                    System.out.println("genome: " + genome);

                    System.out.println("splitedName[3] " + splitedName[3]);
                    String locusTag = splitedName[3].replace("_0001_dp.ps", "");
                    locusTag = locusTag.replace("_0001_aln.ps", "");
                    locusTag = locusTag.replace("_0001_ali.out", "");
                    locusTag = locusTag.replace("_0001_ss.ps", "");
                    locusTag = locusTag.replace("_0001_ss.ps", "");
                    locusTag = locusTag.replace("_sample", "");
                    System.out.println("locus tag: " + locusTag);

                    SmallRnaDAO srnaDAO = new SmallRnaDAO();
                    SmallRna srnaSource = srnaDAO.findByLocusTag(locusTag);
                    System.out.println("srna " + srnaSource);

                    try {
                        System.out.println("srnaSource.getId() " + srnaSource.getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Thread.sleep(50000);
                    }

                    SmallRna srna = srnaDAO.findByGenomeSourceRna(genome, srnaSource.getId());

                    if (srna != null) {
                        System.out.println(srna.getId());

                        System.out.println("locus tag: " + locusTag);
                        String suffix = splitedName[3].replace(locusTag, "");
                        System.out.println("suffix " + suffix);
                        File newFile = new File("/data/home/mariana/NetBeansProjects/CoryneRegNet7/web/images/srnas/" + srna.getLocusTag() + suffix);
                        System.out.println("====>" + "   /data/home/mariana/NetBeansProjects/CoryneRegNet7/web/images/srnas/" + srna.getLocusTag() + suffix);
//                    
                        rename(file, newFile);
                        //findByGenomeSourceRna
                    } else {
                        System.out.println("SRNA: " + srna);
                        nullSrna++;
                    }

                } else {
                    System.out.println("--NAO É!");
                }

            }
        }

        System.out.println("null: " + nullSrna);

    }

    public void rename(File file, File file2) {
        // File (or directory) with old name
        //File file = new File("oldname");

        // File (or directory) with new name
        //File file2 = new File("newname");
        if (file2.exists()) {
            System.out.println("FILE EXISTS");
        }

        // Rename file (or directory)
        boolean success = file.renameTo(file2);

        if (!success) {
            System.out.println("DIDN'T WORKED. " + file2.getName());
            // File was not successfully renamed
        }
    }

    public void statisticTest() {
        SmallRnaTest srTest = new SmallRnaTest();
        //srTest.getByGenome();

        SmallRnaDAO rDAO = new SmallRnaDAO();
        Long countSrnaExperimental = rDAO.bringByType("experimental");
        System.out.println("countSrnaExperimental: " + countSrnaExperimental);

        Long countFunctionalSrnaExperimental = rDAO.bringFunctionalByType("experimental", true);
        System.out.println("countFunctionalSrnaExperimental: " + countFunctionalSrnaExperimental);

        Long countSrnaPredicted = rDAO.bringByNotType("experimental");
        System.out.println("countSrnaPredicted: " + countSrnaPredicted);

        Long countFunctionalSrnaPredicted = rDAO.bringFunctionalByNotType("experimental", true);
        System.out.println("countFunctionalSrnaPredicted: " + countFunctionalSrnaPredicted);

        RnaInteractionDAO rnaInteractionDAO = new RnaInteractionDAO();
        Long countRnaRegulations = rnaInteractionDAO.bringAll();
        System.out.println("countRnaRegulations " + countRnaRegulations);

        //bringDistinctMrna
        Long countMrnas = rnaInteractionDAO.bringDistinctMrna();
        System.out.println("countMrnas " + countMrnas);
    }

    public void getByGenome() {
        SmallRnaDAO rDAO = new SmallRnaDAO();
        List<SmallRna> sRnas = rDAO.findByGenome(1226);
        System.out.println("size: " + sRnas);
        for (SmallRna sRna : sRnas) {
            System.out.println(sRna.toString());
        }
    }

    public void insertEvidence() {
        SmallRnaDAO rDAO = new SmallRnaDAO();
        List<SmallRna> sRnas = rDAO.listAll();
        Hashtable<Integer, Integer> genomeCount
                = new Hashtable<Integer, Integer>();

        for (SmallRna sRna : sRnas) {

            System.out.println(sRna);

            Genome genome = sRna.getGenome();

            if (sRna.getType().contains("RNAz") || sRna.getType().contains("RNAdetect") || sRna.getType().contains("cmsearch")) {
                System.out.println("GENOME ID:" + genome.getId());
                System.out.println("genome count " + genomeCount.contains(genome.getId()));
                System.out.println("genomeCount.get(genome.getId()) " + genomeCount.get(genome.getId()));

                if (!genomeCount.containsKey(genome.getId())) {
                    System.out.println("does not have! ");
                    genomeCount.put(genome.getId(), 1);
                } else {
                    System.out.println("has! ");
                    Integer count = genomeCount.get(genome.getId());
                    count++;
                    genomeCount.put(genome.getId(), count);
                }
            }

            String rnaEvidence = "";
            String locusTag = "";
            if (sRna.getType().contains("experimental")) {
                rnaEvidence = "experimental";
            } else {
                if (sRna.getType().contains("bsrd")) {
                    System.out.println("BSRD");
                    rnaEvidence = "BSRD(" + sRna.getLocusTag() + ")";
                } else {
                    rnaEvidence = "predicted - " + sRna.getType();
                    if (rnaEvidence.contains("RNAdetect") || rnaEvidence.contains("RNAz")) {
                        rnaEvidence = rnaEvidence + " (" + sRna.getSourceRna().getLocusTag() + ")";

                    }

                    locusTag = sRna.getGenome().getOrganism().getGenera().substring(0, 1) + sRna.getGenome().getOrganism().getSpecies().substring(0, 1)
                            + "-" + sRna.getGenome().getOrganism().getStrain().replace(" ", "").replace("-", "") + "-sRNA-" + genomeCount.get(genome.getId());
                    System.out.println("NEW LOCUS: " + locusTag);
                    sRna.setLocusTag(locusTag);
                }
            }

            sRna.setEvidence(rnaEvidence);
            System.out.println("NEW: " + sRna);

            System.out.println("\n\n");
            rDAO.update(sRna);

        }

    }

    public void insertSource() {
        SmallRnaDAO rDAO = new SmallRnaDAO();
        List<SmallRna> sRnas = rDAO.listAll();
        for (SmallRna sRna : sRnas) {
            if (sRna.getType().contains("RNAz")) {
                System.out.println(sRna);

                SmallRna sourceRna = new SmallRna();
                String[] splitedLocusTag = sRna.getLocusTag().split("-");
                String locusTagSourceRna = splitedLocusTag[splitedLocusTag.length - 1];
                System.out.println("locusTagSourceRna: " + locusTagSourceRna);
                sourceRna = rDAO.findByLocusTag(locusTagSourceRna);
                System.out.println(sourceRna);
                sRna.setSourceRna(sourceRna);
                System.out.println("\n\n");
                //rDAO.update(sRna);
            }
        }

    }

    public void save() {
        SmallRnaDAO rDAO = new SmallRnaDAO();
        SmallRna smallRNA = new SmallRna();
        smallRNA.setLocusTag("cgb_00025");
        smallRNA.setSequence("ACACTAATTAACCACAAG");
        smallRNA.setType("experimental");
        rDAO.save(smallRNA);

    }

    public void update() {
        SmallRnaDAO rDAO = new SmallRnaDAO();
        SmallRna smallRNA = rDAO.findById(2);
        smallRNA.setSequence("20/25 genomes");
        rDAO.update(smallRNA);
    }

    public void delete() {
        SmallRnaDAO rDAO = new SmallRnaDAO();
        SmallRna smallRNA = rDAO.findById(2);
        rDAO.delete(smallRNA);
    }

}
