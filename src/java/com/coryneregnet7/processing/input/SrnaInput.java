/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.input;

import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.SmallRnaDAO;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.SmallRna;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

/**
 *
 * @author mariana
 */
public class SrnaInput {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        SrnaInput input = new SrnaInput();
        //input.getSrnaRnazGlutamicum("/data/home/mariana/Dropbox/Doutorado/ncRNA/six-strains-analysis/cmsearch/sequences/c-ulcerans-nctc7908-sequences.out");

        File folder = new File("/data/home/mariana/Dropbox/Doutorado/ncRNA/six-strains-analysis/srna-glutamicum-rnaz/results");
        File[] listOfFiles = folder.listFiles();
        String sRnaLocus = "";

        Hashtable<String, Integer> ncIds
                = new Hashtable<String, Integer>();

        ncIds.put("c-ulcerans", 1288);
        ncIds.put("c-pseudotuberculosis", 1274);
        ncIds.put("c-jeikeium", 1390);
        ncIds.put("c-efficiens", 1243);
        ncIds.put("c-diphtheriae", 1230);

        for (File file : listOfFiles) {
            if (file.getName().startsWith("c") && file.getName().endsWith(".fa") && !file.getName().contains("copra")) {
                System.out.println(file.getAbsolutePath());
                sRnaLocus = file.getName().replace(".fa","");
                System.out.println(sRnaLocus.substring(0, sRnaLocus.indexOf("-o")));
                String genomeLabel = sRnaLocus.substring(0, sRnaLocus.indexOf("-o"));
                System.out.println(genomeLabel + " -> " + ncIds.get(genomeLabel));

                input.getSrnaRnazGlutamicum(file.getAbsolutePath(), sRnaLocus, ncIds.get(genomeLabel));

//                BufferedReader br = new BufferedReader(new FileReader(file));
//                String line = br.readLine();
//                br.close();
//                String[] splitedLine = line.split("\\|");
//                sRnaLocus = splitedLine[0].replace(">", "");
//                System.out.println("sRNA locus= " + sRnaLocus);
//                SmallRnaDAO smallRnaDAO = new SmallRnaDAO();
//                SmallRna smallRna = smallRnaDAO.findByLocusTag(sRnaLocus);
//                System.out.println("sRNA= " + smallRna.toString());
            }
        }
    }

    //>LT906443.1:1945387-1945436 Corynebacterium ulcerans strain NCTC7910 genome assembly, chromosome: 1-p.c.VAL:85.19%-taxID:N/A
    public void getSrnaRnazGlutamicum(String fileName, String locus, Integer genomeId) {
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(fileName));
            SmallRna sRna = new SmallRna();
            SmallRnaDAO sRnaDAO = new SmallRnaDAO();
            Genome genome = new Genome();
            GenomeDAO genomeDAO = new GenomeDAO();
            genome = genomeDAO.findById(genomeId);
            System.out.println(genome);

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);

                if (sCurrentLine.startsWith(">") && !sCurrentLine.startsWith(">cgb")) {
                    sRna = new SmallRna();
                    String splitedLine[] = sCurrentLine.split(":|\\s+");

                    for (String string : splitedLine) {
                        System.out.println("----" + string);
                    }

                    String srnaClass = "";
                    String positions[] = new String[2];
                    String orientation = "";
                    if (splitedLine[1].contains("c")) {
                        orientation = "reverse";
                        splitedLine[1] = splitedLine[1].replace("c", "");
                    } else {
                        orientation = "forward";
                    }
                    
                    String startEnd[] = splitedLine[1].split("-");

                    if (Integer.parseInt(startEnd[0]) > Integer.parseInt(startEnd[1])) {
                        positions[0] = startEnd[1];
                        positions[1] = startEnd[0];

                    } else {
                        positions[0] = startEnd[0];
                        positions[1] = startEnd[1];
                    }

                    Integer startPos = Integer.parseInt(positions[0]);
                    Integer endPos = Integer.parseInt(positions[1]);

                    System.out.println("locus: " + locus);
                    System.out.println("srnaClass: " + srnaClass);
                    System.out.println("startPos: " + startPos);
                    if (orientation.equals("(+)")) {
                        orientation = "forward";
                    } else {
                        orientation = "reverse";
                    }
                    System.out.println("orientation: " + orientation);
                    System.out.println("endPos: " + endPos);

                    sRna.setGenome(genome);
                    sRna.setLocusTag(locus);
                    sRna.setOrientation(orientation);
                    sRna.setSrnaClass(srnaClass);
                    sRna.setStartPosition(startPos);
                    sRna.setEndPosition(endPos);
                    sRna.setType("RNAz-glutamicum");

                } else {
                    System.out.println("sequence " + sCurrentLine);
                    if (!sCurrentLine.startsWith(">cgb") && sRna != null && sRna.getLocusTag() != null) {
                        sRna.setSequence(sCurrentLine);

                        System.out.println(sRna.toString());
                        SmallRna repeated = sRnaDAO.findRepeated(sRna.getStartPosition(), sRna.getEndPosition(), sRna.getOrientation(), genome.getId());
                        System.out.println("Repeated? " + repeated);
                        if (repeated == null) {
                            sRnaDAO.save(sRna);
                            break;
                        } else {
                            System.out.println("It is repeated!");
                        }
                    }
                    System.out.println("\n");
                }

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

    public void getSrnaCmSearch(String fileName) {
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(fileName));
            SmallRna sRna = new SmallRna();
            SmallRnaDAO sRnaDAO = new SmallRnaDAO();
            Genome genome = new Genome();
            GenomeDAO genomeDAO = new GenomeDAO();
            genome = genomeDAO.findById(1288);
            System.out.println(genome);

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);

                if (sCurrentLine.startsWith(">")) {
                    sRna = new SmallRna();
                    String splitedLine[] = sCurrentLine.split(":");
                    String locus = splitedLine[0];
                    locus = locus.replace(">", "");
                    String srnaClass = "";
                    String positions[] = splitedLine[3].split("-");
                    Integer startPos = Integer.parseInt(positions[0]);
                    String orientation = positions[1].substring(positions[1].length() - 3, positions[1].length());
                    positions[1] = positions[1].replace(orientation, "");
                    Integer endPos = Integer.parseInt(positions[1]);

                    System.out.println("locus: " + locus);
                    System.out.println("srnaClass: " + srnaClass);
                    System.out.println("startPos: " + startPos);
                    if (orientation.equals("(+)")) {
                        orientation = "forward";
                    } else {
                        orientation = "reverse";
                    }
                    System.out.println("orientation: " + orientation);
                    System.out.println("endPos: " + endPos);

                    sRna.setGenome(genome);
                    sRna.setLocusTag(locus);
                    sRna.setOrientation(orientation);
                    sRna.setSrnaClass(srnaClass);
                    sRna.setStartPosition(startPos);
                    sRna.setEndPosition(endPos);
                    sRna.setType("cmsearch");

                } else {
                    System.out.println("sequence " + sCurrentLine);
                    sRna.setSequence(sCurrentLine);

                    System.out.println(sRna.toString());
                    SmallRna repeated = sRnaDAO.findRepeated(sRna.getStartPosition(), sRna.getEndPosition(), sRna.getOrientation(), genome.getId());
                    System.out.println("Repeated? " + repeated);
                    if (repeated == null) {
                        // sRnaDAO.save(sRna);
                    } else {
                        System.out.println("It is repeated!");
                    }

                    System.out.println("\n");
                }

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

    public void getSrnaBSRD(String fileName) {
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(fileName));
            SmallRna sRna = new SmallRna();
            SmallRnaDAO sRnaDAO = new SmallRnaDAO();
            Genome genome = new Genome();
            GenomeDAO genomeDAO = new GenomeDAO();
            genome = genomeDAO.findById(1226);
            System.out.println(genome);
            String sequence = "";

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);

                if (sCurrentLine.startsWith(">")) {

                    sRna = new SmallRna();
                    String splitedLine[] = sCurrentLine.split("\\|");

                    for (String string : splitedLine) {
                        System.out.println(string);
                    }

                    String locus = splitedLine[0];
                    locus = locus.replace(">", "");
                    String srnaClass = "";
                    String positions[] = new String[2];
                    if (Integer.parseInt(splitedLine[3]) > Integer.parseInt(splitedLine[4])) {
                        positions[0] = splitedLine[4];
                        positions[1] = splitedLine[3];
                    } else {
                        positions[0] = splitedLine[3];
                        positions[1] = splitedLine[4];
                    }

                    Integer startPos = Integer.parseInt(positions[0]);
                    Integer endPos = Integer.parseInt(positions[1]);

                    String orientation = splitedLine[splitedLine.length - 1];

                    System.out.println("locus: " + locus);
                    System.out.println("srnaClass: " + srnaClass);
                    System.out.println("startPos: " + startPos);
                    System.out.println("orientation: " + orientation);
                    System.out.println("endPos: " + endPos);

                    sRna.setGenome(genome);
                    sRna.setLocusTag(locus);
                    sRna.setOrientation(orientation);
                    sRna.setSrnaClass(srnaClass);
                    sRna.setStartPosition(startPos);
                    sRna.setEndPosition(endPos);
                    sRna.setType("bsrd");

                } else if (!sCurrentLine.isEmpty()) {
                    sequence = sequence + sCurrentLine.replace("\n", "").trim();
                } else {
                    sRna.setSequence(sequence);

                    System.out.println("SEQUENCE: " + sequence);
                    System.out.println(sRna.toString());
                    SmallRna repeated = sRnaDAO.findRepeated(sRna.getStartPosition(), sRna.getEndPosition(), sRna.getOrientation(), genome.getId());
                    System.out.println("Repeated? " + repeated);
                    if (repeated == null) {
                        sRnaDAO.save(sRna);
                    } else {
                        System.out.println("It is repeated!");
                    }
                    sequence = "";
                    System.out.println("\n");
                }

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

    public void getSrna(String fileName) {
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(fileName));
            SmallRna sRna = new SmallRna();
            SmallRnaDAO sRnaDAO = new SmallRnaDAO();
            Genome genome = new Genome();
            GenomeDAO genomeDAO = new GenomeDAO();
            genome = genomeDAO.findById(1226);
            System.out.println(genome);

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);

                if (sCurrentLine.startsWith(">")) {
                    sRna = new SmallRna();
                    String splitedLine[] = sCurrentLine.split("_|:");
                    String locus = splitedLine[0] + "_" + splitedLine[1];
                    locus = locus.replace(">", "");
                    String srnaClass = splitedLine[2];
                    String positions[] = splitedLine[5].split("-");
                    Integer startPos = Integer.parseInt(positions[0]);
                    String orientation = positions[1].substring(positions[1].length() - 3, positions[1].length());
                    positions[1] = positions[1].replace(orientation, "");
                    Integer endPos = Integer.parseInt(positions[1]);

                    System.out.println("locus: " + locus);
                    System.out.println("srnaClass: " + srnaClass);
                    System.out.println("startPos: " + startPos);
                    if (orientation.equals("(f)")) {
                        orientation = "forward";
                    } else {
                        orientation = "reverse";
                    }
                    System.out.println("orientation: " + orientation);
                    System.out.println("endPos: " + endPos);

                    sRna.setGenome(genome);
                    sRna.setLocusTag(locus);
                    sRna.setOrientation(orientation);
                    sRna.setSrnaClass(srnaClass);
                    sRna.setStartPosition(startPos);
                    sRna.setEndPosition(endPos);
                    sRna.setType("experimental");

                } else {
                    System.out.println("sequence " + sCurrentLine);
                    sRna.setSequence(sCurrentLine);
                    //sRnaDAO.save(sRna);
                    System.out.println("\n");
                }

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
