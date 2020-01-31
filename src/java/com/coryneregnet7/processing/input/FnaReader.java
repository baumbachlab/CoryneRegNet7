/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.input;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Genome;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author mariana
 */
public class FnaReader {

    public static void main(String[] args) {
        FnaReader fnaReader = new FnaReader();
        //fnaReader.read("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/test-real-data/template-genomes-test/CgATCC13032.fna");
        fnaReader.read("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/ecoli-test/target/GCF_000005845.2_ASM584v2_cds_from_genomic.fna", 1045);
    }

    public void read(String fnaFile, Integer genome) {

        try {
            BufferedReader br = null;
            FileReader fr = null;
            fr = new FileReader(fnaFile);
            br = new BufferedReader(fr);

            String sCurrentLine = "";

            System.out.println("\nFnaReader\n");
            int count = 0;
            boolean sequence = false;
            String nucleotideSequence = "";
            String locusTag = "";
            GeneDAO geneDAO = new GeneDAO();
            Gene gene = new Gene();
            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println("---------->>>>>>>"+sCurrentLine);

                if (sCurrentLine.startsWith(">") && sequence) {
                    count++;
                    nucleotideSequence = nucleotideSequence.toUpperCase();
                   //System.out.println("-------------" + nucleotideSequence + "\n");

                    if (sCurrentLine.contains("[locus_tag=")) {
                       //System.out.println("----MODELO DO NCBI");
                    }
                    
                     gene = geneDAO.findByGenomeLocusTag(genome, locusTag);
                    if (gene == null) {
                       System.out.println("genome => " + genome);
                       System.out.println("locusTag => " + locusTag);
                    }
                   //System.out.println(gene.toString());
                    gene.setNucleotideSequence(nucleotideSequence);
                   //System.out.println("****************" + nucleotideSequence + "\n");
                    geneDAO.update(gene);

                    nucleotideSequence = "";

                    //SAVE SEQUENCE
                   //System.out.println("-----PRIMEIRA LINHA : " + sCurrentLine);
                    String[] splitedLine = sCurrentLine.split("\\[");
                    locusTag = "";
                    for (String s : splitedLine) {
                        if (s.contains("locus_tag")) {
                           //System.out.println(s);
                            locusTag = s.trim();
                            locusTag = locusTag.replace("locus_tag=", "");
                            locusTag = locusTag.replace("]", "");
                            locusTag = locusTag.trim();
                           //System.out.println("--" + locusTag);

                            //COLOCAR A SEQUENCIA NO GENE E SALVAR. 
                            break;
                        }
                    }
                   //System.out.println("CHEGOU AQU?");
                   

                } else if (!sCurrentLine.startsWith(">")) {
                    nucleotideSequence = nucleotideSequence + sCurrentLine;
                } else {
                   //System.out.println("-----PRIMEIRA LINHA : " + sCurrentLine);
                    String[] splitedLine = sCurrentLine.split("\\[");
                    locusTag = "";
                    for (String s : splitedLine) {
                        if (s.contains("locus_tag")) {
                           //System.out.println(s);
                            locusTag = s.trim();
                            locusTag = locusTag.replace("locus_tag=", "");
                            locusTag = locusTag.replace("]", "");
                           //System.out.println("--" + locusTag);

                            //COLOCAR A SEQUENCIA NO GENE E SALVAR. 
                            break;
                        }
                    }

                }
               //System.out.println(sCurrentLine);

                sequence = true;
            }

            //tem que salvar a ultima! 
           //System.out.println("-------------" + nucleotideSequence);
            count++;
           //System.out.println("count " + count);
           //System.out.println("locus_tag " + locusTag);
            gene = geneDAO.findByGenomeLocusTag(genome, locusTag);
            if (gene == null) {
               // Thread.sleep(500000000);
               //System.out.println("ERROR!!!!!!!!!");
            }
           //System.out.println(gene.toString());
            gene.setNucleotideSequence(nucleotideSequence);
            geneDAO.update(gene);

           //System.out.println("\n");
        } catch (Exception E) {
            E.printStackTrace();
        }

    }
}
