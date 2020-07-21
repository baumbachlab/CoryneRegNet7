/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author mariana
 */
public class GiProcessing {

    public static void main(String[] args) {

        String geneFile = "/data/home/mariana/Dropbox/Doutorado/ncRNA/gipsy/genes_islands_rna_cj.txt";
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(geneFile);
            br = new BufferedReader(fr);

            String sCurrentLine;

            Hashtable<String, String> geneIslands = new Hashtable<String, String>();

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                String[] splitedLine = sCurrentLine.split("\t");

                if (!splitedLine[1].contains("Symbiotic")) {

                    if (!geneIslands.containsKey(splitedLine[0])) {
                        geneIslands.put(splitedLine[0], splitedLine[1] + "(" + splitedLine[2] + ")");
                    } else {
                        String island = new String();
                        island = geneIslands.get(splitedLine[0]);
                        if (!island.contains(",")) {
                            island = "Miscellaneous-" + island;
                        }
                        island = island + "," + splitedLine[1] + "(" + splitedLine[2] + ")";
                        geneIslands.put(splitedLine[0], island);
                    }
                }
            }

            Integer countMetabolic = 0;
            Integer countPathogenicity = 0;
            Integer countResistance = 0;
            Integer countSymbiotic = 0;
            Integer countMisc = 0;

            FileWriter fileWriter = new FileWriter(geneFile.replace(".txt", "_miscellaneus.txt"));
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Map.Entry<String, String> entry : geneIslands.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                System.out.println(key + "\t" + value);
                printWriter.println(key + "\t" + value);

                if (value.startsWith("Pathogenicity")) {
                    countPathogenicity++;
                } else if (value.startsWith("Resistance")) {
                    countResistance++;
                } else if (value.startsWith("Symbiotic")) {
                    countSymbiotic++;
                } else if (value.startsWith("Metabolic")) {
                    countMetabolic++;
                } else if (value.startsWith("Miscellaneous")) {
                    countMisc++;
                }

            }
            printWriter.close();

            FileWriter fileWriterCount = new FileWriter(geneFile.replace(".txt", "_miscellaneuos_count.txt"));
            PrintWriter printWriterCount = new PrintWriter(fileWriterCount);
            printWriterCount.println("Island type" + "\t" + "Number of Genes");
            printWriterCount.println("Pathogenicity" + "\t" + countPathogenicity);
            printWriterCount.println("Resistance" + "\t" + countResistance);
            printWriterCount.println("Metabolic" + "\t" + countMetabolic);
            printWriterCount.println("Miscellaneous" + "\t" + countMisc);

            printWriterCount.close();

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
