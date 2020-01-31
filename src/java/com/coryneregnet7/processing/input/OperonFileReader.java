/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.input;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GeneOperonDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.OperonDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.GeneOperon;
import com.coryneregnet7.model.GeneOperonPK;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Operon;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author mariana
 */
public class OperonFileReader {

    public static void main(String[] args) {
        OperonFileReader op = new OperonFileReader();
        GenomeDAO genomeDAO = new GenomeDAO();
        Genome genome = genomeDAO.findById(1169);
        String filename = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/model/GCF_000009045.1_ASM904v1.op";

        op.readOperonFile(filename, genome);
    }

    public void readOperonFile(String file, Genome genome) {
        System.out.println("OPERON FILE READER "+file);
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(fr);
            Hashtable<String, ArrayList<String>> numbers = new Hashtable<String, ArrayList<String>>();

            OperonDAO operonDAO = new OperonDAO();
            Operon operon;

            GeneDAO geneDAO = new GeneDAO();
            Gene gene = new Gene();
            
            GeneOperonDAO geneOperonDAO = new GeneOperonDAO();
            GeneOperon geneOperon;

            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println(sCurrentLine);
                String[] splitedLine = sCurrentLine.split("\t");
                String operonName = splitedLine[0];
                operonName = operonName.replace(">", "");
                String orientation = splitedLine[1];

                //System.out.println("operon name: " + operonName);
                //System.out.println("orientation: " + orientation);

                operon = new Operon();
                operon.setName(operonName);
                if (orientation.contains("+")) {
                    operon.setOrientation("forward");
                } else {
                    operon.setOrientation("reverse");
                }

                operon = (Operon) operonDAO.save(operon);

                for (int i = 2; i < splitedLine.length; i++) {
                  //  System.out.println(">>>>"+splitedLine[i]+"<<<<<<<<<");
                //    System.out.println(genome.getId());
                    gene = geneDAO.findByGenomeLocusTagProteinId(genome.getId(), splitedLine[i]);
             //       System.out.println(gene);
                  //  System.out.println(gene.toString());
                  //  System.out.println(operon.toString());
                    geneOperon = new GeneOperon();
                    
                    GeneOperonPK geneOperonPK = new GeneOperonPK();
                    geneOperonPK.setGene(gene.getId());
                    geneOperonPK.setOperon(operon.getId());
                    
                    geneOperon.setGeneOperonPK(geneOperonPK);
                    geneOperon.setPos(i-1);
                    geneOperonDAO.save(geneOperon);
                 //   System.out.println("--------\n");
                    
                }
                //System.out.println("///========================///");
                //System.out.println("\n\n");

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
