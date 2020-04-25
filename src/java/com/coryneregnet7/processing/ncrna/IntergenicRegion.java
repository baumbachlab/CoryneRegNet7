/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.ncrna;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author mariana
 */
public class IntergenicRegion {

    public static void main(String[] args) {
        IntergenicRegion intergenicRegion = new IntergenicRegion();
        intergenicRegion.writeNumbers();
    }

    public void writeNumbers() {
        BufferedReader br = null;
        FileReader fr = null;

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader("/data/home/mariana/Dropbox/Doutorado/ncRNA/six-strains-analysis/genomes-intergenic/fasta-files/c-glutamicum-intergenic.fna"));
            int count = 1;
            
            String fileName="/data/home/mariana/Dropbox/Doutorado/ncRNA/six-strains-analysis/genomes-intergenic/fasta-files/c-glutamicum-intergenic-numbers.fna";
            
              FileWriter fileWriter = new FileWriter(fileName);
    PrintWriter printWriter = new PrintWriter(fileWriter);
            
            while ((sCurrentLine = br.readLine()) != null) {
                if (sCurrentLine.contains(">")) {
                  //  System.out.println("----" + sCurrentLine);
                    String[] splitedLine = sCurrentLine.split("\\s+");
                    for (int i = 0; i < splitedLine.length; i++) {
                        if (i == 0) {
                            printWriter.print(splitedLine[i] + count + " ");
                        }else if(i==splitedLine.length-1){
                            printWriter.print(splitedLine[i]);
                        }else{
                            printWriter.print(splitedLine[i] + " ");
                        }
                        
                    }
                    printWriter.println("");
                    count++;

                }else{
                    printWriter.println(sCurrentLine);
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
