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
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author mariana
 */
public class FnaReader {

    public static void main(String[] args) {
        FnaReader fnaReader = new FnaReader();
        // fnaReader.read("/data/home/mariana/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/target-brucella/Bab30_cro1_cds_from_genomic.fna");
        fnaReader.read("/data/home/mariana/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/target-brucella/Bab30_cro1_cds_from_genomic.fna", 1458);
    }

    //public void read(String fnaFile) {
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
            String type = "";
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println("------------------" + sCurrentLine);

                System.out.println("sequence " + sequence);

                //if it is a gene but not the fist gene.
                if (sCurrentLine.startsWith(">") && sequence) {
                    count++;
                    nucleotideSequence = nucleotideSequence.toUpperCase();
                    System.out.println("-------------" + nucleotideSequence + "\n");

                    if (sCurrentLine.contains("[locus_tag=")) {
                        // System.out.println("----MODELO DO NCBI");
                        type = "NCBI";
                    }

                    if (sCurrentLine.contains(">PROKKA")) {
                        //System.out.println("----MODELO DO PROKKA!");
                        type = "PROKKA";
                    }

                    gene = geneDAO.findByGenomeLocusTag(genome, locusTag);
                    if (gene == null) {
                        System.out.println("gene null");
                        System.out.println("genome => " + genome);
                        System.out.println("locusTag => " + locusTag);
                    } else {
                        System.out.println(gene.toString());
                        gene.setNucleotideSequence(nucleotideSequence);
                        geneDAO.update(gene);
                    }
                    
                    //System.out.println("****************" + nucleotideSequence + "\n");
                    

                    nucleotideSequence = "";

                    //SAVE SEQUENCE
                    System.out.println("--**---PRIMEIRA LINHA : " + sCurrentLine);
                    if (type.equals("NCBI")) {

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
                    } else if (type.equals("PROKKA")) {
                        String[] splitedLine = sCurrentLine.split("\\s+");
                        locusTag = splitedLine[0].trim().replace(">", "");
                        System.out.println("locus: " + locusTag);

                    }
                    //System.out.println("CHEGOU AQU?");

                } else if (!sCurrentLine.startsWith(">")) {
                    nucleotideSequence = nucleotideSequence + sCurrentLine;

                //first line    
                } else {
                    System.out.println("-----PRIMEIRA LINHA : " + sCurrentLine);
                    if (sCurrentLine.contains("[locus_tag=")) {
                        // System.out.println("----MODELO DO NCBI");
                        type = "NCBI";
                    }

                    if (sCurrentLine.contains(">PROKKA")) {
                        //System.out.println("----MODELO DO PROKKA!");
                        type = "PROKKA";
                    }

                    if (type.equals("NCBI")) {
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
                    } else if (type.equals("PROKKA")) {
                        String[] splitedLine = sCurrentLine.split("\\s+");
                        locusTag = splitedLine[0].trim().replace(">", "");
                        System.out.println("locus: " + locusTag);

                    }

                }
                //System.out.println(sCurrentLine);

                sequence = true;
            }

            //tem que salvar a ultima! 
            count++;

            System.out.println("locus_tag " + locusTag);
            System.out.println("-------------" + nucleotideSequence);
            System.out.println("count cds " + count);
            gene = geneDAO.findByGenomeLocusTag(genome, locusTag);
            if (gene == null) {
                // Thread.sleep(500000000);
                System.out.println("ERROR!!!!!!!!!");
            }
            //System.out.println(gene.toString());
            gene.setNucleotideSequence(nucleotideSequence);
            geneDAO.update(gene);

            System.out.println("\n");
        } catch (Exception E) {
            E.printStackTrace();
        }

    }
}
