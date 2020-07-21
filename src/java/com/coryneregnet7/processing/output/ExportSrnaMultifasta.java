/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.output;

import com.coryneregnet7.dao.RnaInteractionDAO;
import com.coryneregnet7.dao.SmallRnaDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.SmallRna;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mariana
 */
public class ExportSrnaMultifasta {

    public static void main(String args[]) {
        ExportSrnaMultifasta e = new ExportSrnaMultifasta();
        e.getSmallRnas("glutamicum", 1226);
        e.getSmallRnas("ulcerans", 1288);
        e.getSmallRnas("diphtheriae", 1230);
        e.getSmallRnas("pseudotuberculosis", 1274);
        e.getSmallRnas("jeikeium", 1390);
        e.getSmallRnas("efficiens", 1243);
        System.out.println("done :)");
    }

    public List<Gene> getSmallRnas(String name, Integer genome) {

        FileWriter fileWriter = null;
        String fileName = "/data/home/mariana/Dropbox/Doutorado/ncRNA/regulation-per-genome/multifasta-genes/" + name + ".fasta";
        List<Gene> genes = new ArrayList<Gene>();
        RnaInteractionDAO dao = new RnaInteractionDAO();
        genes = dao.bringDistinctRnaByGenome(genome);

        try {

            fileWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            System.out.println(genes.size());
            for (Gene gene : genes) {
                printWriter.println(">" + gene.getLocusTag());
                printWriter.println(gene.getNucleotideSequence());
            }
            printWriter.close();

        } catch (IOException ex) {
            Logger.getLogger(ExportSrnaMultifasta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(ExportSrnaMultifasta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return genes;
    }

    //findByGenome
    public List<SmallRna> findByGenome() {
        List<SmallRna> genes = new ArrayList<SmallRna>();
        SmallRnaDAO dao = new SmallRnaDAO();
        genes = dao.findByGenome(1226);

        System.out.println(genes.size());

        for (SmallRna gene : genes) {
            System.out.println(gene.getLocusTag());
            // System.out.println(gene.getNucleotideSequence());
        }

        return genes;
    }
}
