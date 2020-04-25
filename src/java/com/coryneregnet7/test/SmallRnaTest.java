/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.SmallRnaDAO;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.SmallRna;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author mariana
 */
public class SmallRnaTest {

    public static void main(String[] args) {
        SmallRnaTest srTest = new SmallRnaTest();
        srTest.getByGenome();
    }

    public void getByGenome(){
         SmallRnaDAO rDAO = new SmallRnaDAO();
        List<SmallRna> sRnas = rDAO.findByGenome(1226);
        System.out.println("size: "+sRnas);
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
