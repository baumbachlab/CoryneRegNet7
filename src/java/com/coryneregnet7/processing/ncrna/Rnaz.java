/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.ncrna;

import com.coryneregnet7.dao.SmallRnaDAO;
import com.coryneregnet7.model.SmallRna;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author mariana
 */
public class Rnaz {

    public static void main(String args[]) {
        Rnaz rnaz = new Rnaz();
        rnaz.readDatFile();
    }

    public void readDatFile() {
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader("/data/home/mariana/Dropbox/Doutorado/ncRNA/six-strains-analysis/genomes-intergenic/fasta-files/alignments/aligned-cg-cg-windows-rnaz-filtered.dat");
            br = new BufferedReader(fr);

            String sCurrentLine;
            
            SmallRna sRna = new SmallRna();
            SmallRnaDAO sRnaDAO = new SmallRnaDAO();

            int count=0;
            while ((sCurrentLine = br.readLine()) != null) {
                if (sCurrentLine.startsWith("locus")) {
                    System.out.println(sCurrentLine);
                    String[] rnaInfo = sCurrentLine.split("\t");
                    String orientation = "forward";
                    Integer start=Integer.parseInt(rnaInfo[2])+1;
                    Integer end=Integer.parseInt(rnaInfo[3])+1;
                    if(start>end){
                        start = Integer.parseInt(rnaInfo[3])+1;
                        end = Integer.parseInt(rnaInfo[2])+1;
                        orientation = "reverse";
                    }
                    //sRna = sRnaDAO.findRepeated(start, end, orientation, 1226);
                    if(sRna != null){
                      count++;  
                    }
                    //System.out.println(sRna);
                    
                    
                }
            }
            
            System.out.println("count: "+count);

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
