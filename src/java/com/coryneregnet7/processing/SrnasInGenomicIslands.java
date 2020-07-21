/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing;

import com.coryneregnet7.dao.RnaInteractionDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.SmallRna;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mariana
 */
public class SrnasInGenomicIslands {

    public static void main(String[] args) {
        try {
            SrnasInGenomicIslands gi = new SrnasInGenomicIslands();

            //System.out.println("Metabolic: ");
            gi.getGenesFromIsland("/data/home/mariana/Dropbox/Doutorado/ncRNA/gipsy/JK/mi_island.txt", "Metabolic", 1390);
            //System.out.println("Pathogenicity:");
            gi.getGenesFromIsland("/data/home/mariana/Dropbox/Doutorado/ncRNA/gipsy/JK/pa_island.txt", "Pathogenicity", 1390);
            //System.out.println("Resistance:");
            gi.getGenesFromIsland("/data/home/mariana/Dropbox/Doutorado/ncRNA/gipsy/JK/ri_island.txt", "Resistance", 1390);
            //System.out.println("Sysmbiotic: ");
            gi.getGenesFromIsland("/data/home/mariana/Dropbox/Doutorado/ncRNA/gipsy/JK/si_island.txt", "Symbiotic", 1390);
        } catch (IOException ex) {
            Logger.getLogger(SrnasInGenomicIslands.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getIslandsFromGenome() {

    }

    public void getGenesFromIsland(String file, String type, Integer genome) throws IOException {

        String fileRNA = file.replace(".txt", "_rna_sRNAs.txt");
        FileWriter fileWriter = new FileWriter(fileRNA);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {

                //System.out.println(sCurrentLine);
                if (sCurrentLine.contains(type)) {
                    String[] splitedLine = sCurrentLine.split("\t");
                    String islandName = splitedLine[0];
                    String positions = splitedLine[6];
//                    System.out.println("islandName " + islandName);
                    //                  System.out.println("positions " + positions);
                    String[] pos = positions.split("\\..");

                    RnaInteractionDAO rDAO = new RnaInteractionDAO();
                    List<SmallRna> rnaInteraction = rDAO.findByGenomeInPositionSrna(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]), genome);
                    for (SmallRna rnaInteraction1 : rnaInteraction) {
                        System.out.println(rnaInteraction1.getLocusTag() + "\t" + type);
                        printWriter.println(rnaInteraction1.getLocusTag() + "\t" + type);
                    }
                    

                }
            }
            
            printWriter.close();


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
