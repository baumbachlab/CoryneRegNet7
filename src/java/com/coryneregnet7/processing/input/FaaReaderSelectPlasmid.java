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
public class FaaReaderSelectPlasmid {

    public static void main(String[] args) {
        FaaReaderSelectPlasmid faaReader = new FaaReaderSelectPlasmid();
        faaReader.read("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/operon_random_test/model/NNMO-S1.faa", 106);
    }

    public void read(String fnaFile, Integer genome) {

        try {
            BufferedReader br = null;
            FileReader fr = null;
            fr = new FileReader(fnaFile);
            br = new BufferedReader(fr);

            String sCurrentLine = "";

            System.out.println("\nFaaReader\n");
            int count = 0;
            Integer searchSpace = 0;
            boolean sequence = false;
            String nucleotideSequence = "";

            while ((sCurrentLine = br.readLine()) != null) {

                if (sCurrentLine.startsWith(">") && sequence) {
                    count++;

                    searchSpace = searchSpace + nucleotideSequence.length();
                    if (sCurrentLine.contains("[locus_tag=")) {
                        //System.out.println("----MODELO DO NCBI");
                    }

                    //SAVE SEARCH SPACE
                    //System.out.println("*************DADOS DA SEQUENCIA:");
                    //System.out.println("-------------tamanho: " + nucleotideSequence.length() + "\n");
                    //System.out.println("-------------tamanho -1: " + (nucleotideSequence.length() - 1) + "\n");
                    //System.out.println("-------------searchSpace = " + searchSpace + "\n");
                    //System.out.println("-------------" + nucleotideSequence + "\n");

                    nucleotideSequence = "";

                } else if (!sCurrentLine.startsWith(">")) {
                    nucleotideSequence = nucleotideSequence + sCurrentLine;
                } else {
                    //System.out.println("-----PRIMEIRA LINHA");
                }
                //System.out.println(sCurrentLine);

                sequence = true;
            }

            //tem que salvar a ultima! 
            ////System.out.println("-------------" + nucleotideSequence);
            searchSpace = searchSpace + nucleotideSequence.length();
            count++;

            System.out.println("*************DADOS DA SEQUENCIA:");
            System.out.println("-------------tamanho: " + nucleotideSequence.length() + "\n");
            System.out.println("-------------tamanho -1: " + (nucleotideSequence.length() - 1) + "\n");
            System.out.println("-------------searchSpace = " + searchSpace + "\n");
            System.out.println("-------------" + nucleotideSequence + "\n");

            System.out.println("count " + count);
            System.out.println("search space= " + searchSpace);

            //SALVAR O SEARCH SPACE -> COUNT
            GenomeDAO genomeDAO = new GenomeDAO();
            Genome g = genomeDAO.findById(genome);
            g.setSearchSpace(searchSpace);
            genomeDAO.update(g);

            //System.out.println("\n");
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

}
