/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.ncrna;

import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.SmallRnaDAO;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.SmallRna;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

/**
 *
 * @author mariana
 */
public class RnaDetect {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */ public static void main(String[] args) {
        //misc_feature107.fa.rnadetect
        RnaDetect rnaDetect = new RnaDetect();
        //File folder = new File("/data/home/mariana/Downloads/RNAdetect-master-37a/RNAdetect-master/RNAdetect/glutamicum");
        File folder = new File("/data/home/mariana/Dropbox/Doutorado/ncRNA/six-strains-analysis/srna-glutamicum-rnadetect");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".out.rnadetect")) {
                System.out.println(file.getName());
                rnaDetect.readResultFile(file.getAbsolutePath(), file.getName());
            }
        }
    }

    public void readResultFile(String path, String fileName) {

        Hashtable<String, Integer> strains
                = new Hashtable<String, Integer>();
        strains.put("c-efficiens", 1243);
        strains.put("c-diphtheriae", 1230);
        strains.put("c-jeikeium", 1390);
        strains.put("c-pseudotuberculosis", 1274);
        strains.put("c-ulcerans", 1288);
        

        BufferedReader br = null;
        FileReader fr = null;

        BufferedReader brFa = null;
        FileReader frFa = null;

        try {

            fr = new FileReader(path);
            br = new BufferedReader(fr);

            frFa = new FileReader(path.replace(".out.rnadetect", ""));
            brFa = new BufferedReader(frFa);

            String locusTag = fileName.replace(".fa.out.rnadetect", "");

            // String sCurrentLine;
            String sCurrentLineFa;
//
//            String start = "";
//            String end = "";
//            String splitedLine[] = fileName.split("_");
//            String positions[] = splitedLine[2].replace(".fa.rnadetect", "").split("-");
//            System.out.println(positions[0] + " " + positions[1]);
////
            SmallRna sRna = new SmallRna();
            SmallRnaDAO sRnaDAO = new SmallRnaDAO();

            sRna = sRnaDAO.findByLocusTag(locusTag);

            System.out.println(sRna);

            if (sRna == null) {

                sRna = new SmallRna();
                int count = 0;
                String header = "";
                String sequence = "";
                String orientation = "";
                while ((sCurrentLineFa = brFa.readLine()) != null) {

                    System.out.println(sCurrentLineFa);
                    if (sCurrentLineFa.contains(">")) {
                        count++;
                        if (count == 3) {
                            break;
                        }
                        header = sCurrentLineFa;
                    } else {
                        sequence = sCurrentLineFa;
                    }
                }

                System.out.println("HEADER= " + header);
                String[] splitedHeader = header.split(":|\\s+");

                System.out.println("SEQUENCE= " + sequence + "\n");

                String pos[] = splitedHeader[1].split("-");
                Integer start;
                Integer end;
                if (splitedHeader[1].contains("c")) {
                    orientation = "reverse";
                    start = Integer.parseInt(pos[1]);
                    end = Integer.parseInt(pos[0].replace("c", ""));

                } else {
                    orientation = "forward";
                    start = Integer.parseInt(pos[0]);
                    end = Integer.parseInt(pos[1]);
                }

                System.out.println("START: " + start);
                System.out.println("END: " + end);

                sRna.setEndPosition(end);
                sRna.setStartPosition(start);
                sRna.setType("RNAdetect");
                sRna.setLocusTag(locusTag);

                SmallRna sourceRna = new SmallRna();
                String[] splitedLocusTag = locusTag.split("-");
                String locusTagSourceRna = splitedLocusTag[splitedLocusTag.length - 1];
                System.out.println("locusTagSourceRna: " + locusTagSourceRna);
                sourceRna = sRnaDAO.findByLocusTag(locusTagSourceRna);

                sRna.setSourceRna(sourceRna);
                sRna.setOrientation(orientation);
                sRna.setSequence(sequence);
                
                String genomeS=splitedLocusTag[0]+"-"+splitedLocusTag[1];
                System.out.println("genome "+strains.get(genomeS));
                Genome genome;
                GenomeDAO genomeDAO = new GenomeDAO();
                genome = genomeDAO.findById(strains.get(genomeS));
                sRna.setGenome(genome);
                
                sRnaDAO.save(sRna);
                //System.out.println(sRna.toString());

            } else {
                sRna.setType("RNAz+RNAdetect");
                sRnaDAO.update(sRna);
            }

            System.out.println("\n\n");
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
