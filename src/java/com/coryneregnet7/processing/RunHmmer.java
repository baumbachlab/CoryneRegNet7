/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing;

/**
 *
 * @author mariana
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author mariana
 */
public class RunHmmer {

    public static void main(String[] args) throws IOException {
        //String sshFile = "";
        String resultsfile = "CgTest_CAF18575.1_CAF19713.1_CAFT18575.1_CAFT19713.1.out";
        //String hmmerFolder = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/operon_random_test/TEST-HMM/";
        String hmmerFolder = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/sanity-test/test/CgATCC13032xCgTest/hmmer-CgATCC13032xCgTest/result";
        // String bindingSite = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/operon_random_test/TEST-HMM/NNMO00010.hmm";
        // String promoterRegion = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/operon_random_test/TEST-HMM/promoter_region.fasta";

        //String bindingSite = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/operon_random_test/TEST-HMM/NNMO00002.faasta";
        //String promoterRegion = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/operon_random_test/TEST-HMM/NNMOS1_00009-promoter-region.fna";
        String bindingSite = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/sanity-test/test/hmmer-test/cg0350.hmm";
        String promoterRegion = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/sanity-test/test/hmmer-test/cg_test1061-promoter-region.fna";
        RunHmmer c = new RunHmmer();
        //c.runOnce(bindingSite, promoterRegion, hmmerFolder, resultsfile);

        //c.runHmmBuilder();
        //c.run(hmmerFolder);
        // System.out.println(c.findMatch(resultsfile, hmmerFolder));
        // System.out.println("----------------");
        //c.createMatchesFolder(resultsFolder);
    }

    public void runHmmBuilder(String hmmProfileFile, String stoFile, String folder) throws IOException, InterruptedException {
        //public void runHmmBuilder() throws IOException {
        File directory = new File(folder);
        String hmmBuildCommand = "hmmbuild --dna " + hmmProfileFile + " " + stoFile;
  //      System.out.println(hmmBuildCommand);
        ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", hmmBuildCommand);
        pb.directory(directory);
        final Process p = pb.start();
        p.waitFor();

    }

    public void runOnce(String bindingSite, String promoterRegion, String hmmerFolder, String resultFile) throws IOException {
        String hmmerCommand = "nhmmer --dna --max " + bindingSite + " " + promoterRegion + " > result/" + resultFile;
//        System.out.println(hmmerCommand);

        try {
            ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", hmmerCommand);
            pb.directory(new File(hmmerFolder));
            final Process p = pb.start();
            p.waitFor();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void runOnce(String bindingSite, String promoterRegion, String hmmerFolder, String resultFile, String evalue) throws IOException {
        String hmmerCommand = "nhmmer --dna --max -E " + evalue + " " + bindingSite + " " + promoterRegion + " > result/" + resultFile;
        //System.out.println(hmmerCommand);

        try {
            ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", hmmerCommand);
            pb.directory(new File(hmmerFolder));
            final Process p = pb.start();
            p.waitFor();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void runOnceEvalue(String bindingSite, String promoterRegion, String hmmerFolder, String resultFile, String evalue) throws IOException {
        String hmmerCommand = "nhmmer --dna --max -E" + evalue + " " + bindingSite + " " + promoterRegion + " > result/" + resultFile;

        try {
            ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", hmmerCommand);
            pb.directory(new File(hmmerFolder));
            final Process p = pb.start();
            p.waitFor();
            //System.out.println("rodou hmmer "+resultFile);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public boolean findMatch(String resultFile, String resultsFolder) throws IOException {
        Set<String> files = new HashSet<>();
        boolean hasMatch = false;
        try {

            //System.out.println("FILE: " + resultFile);
            ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "grep \"score  bias    Evalue   hmmfrom    hmm to\" " + resultsFolder + "/" + resultFile);
            //        "grep \"score  bias    Evalue\" "+resultsFolder+"/"+resultFile);
            pb.directory(new File(resultsFolder));
            final Process p = pb.start();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            p.getInputStream()));
            String line;
            ArrayList resultFilesWithMatches = new ArrayList();

            while ((line = br.readLine()) != null) {
                String[] splitedLine = line.split(":");
                String fileName = splitedLine[0];
                //System.out.println(line);
                //System.out.println(fileName);
                resultFilesWithMatches.add(fileName);
                files.add(fileName);
                hasMatch = true;
            }

            //System.out.println("\n\nno dup");
//            for (String s : files) {
//                System.out.println(s);
//            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return hasMatch;

    }

    public BindingSitePredictionInfo getMatchInfo(String resultsFile, String geneOrientation, BigInteger promoterStart, BigInteger promoterEnd
    , BigInteger geneStart) {
        
       // System.out.println("CHEGUEI NO GET MATCH INFO");
        BufferedReader br = null;
        FileReader fr = null;

        boolean score = false;
        boolean newScore = false;
        int linesAfterScore = 0;
        BindingSitePredictionInfo bsPredictionInfo = new BindingSitePredictionInfo();

        try {

            fr = new FileReader(resultsFile);
            br = new BufferedReader(fr);

            String sCurrentLine;
            //Line line = new LineRegulationInfo();

            while ((sCurrentLine = br.readLine()) != null) {

                //System.out.println(sCurrentLine);
                if (sCurrentLine.contains("!") || sCurrentLine.contains("?")) {
                    String splitedLine[] = sCurrentLine.split("\\s+");
                     //System.out.println("*********** " + sCurrentLine);
                    int i = 0;
                    for (String splitedLine1 : splitedLine) {
                        //    System.out.println("splitedLine[" + i + "] " + splitedLine1);
                        i++;
                    }
                      //System.out.println("bitscore: " + splitedLine[2]);
                    bsPredictionInfo.setBitscore(splitedLine[2]);
                      //System.out.println("evalue: " + splitedLine[2]);
                    bsPredictionInfo.setEvalue(splitedLine[4]);
                     //System.out.println("startPosition: " + splitedLine[8]);

                    BigInteger bsIniPos;
                    BigInteger bsEndPos;
                    if (geneOrientation.equals("forward")) {

                        bsIniPos = promoterStart.add(new BigInteger(splitedLine[8])).subtract(BigInteger.ONE);
                        bsEndPos = promoterStart.add(new BigInteger(splitedLine[9])).subtract(BigInteger.ONE);

                    } else {

                        bsIniPos = promoterEnd.add(new BigInteger(splitedLine[8])).subtract(BigInteger.ONE);
                        bsEndPos = promoterEnd.add(new BigInteger(splitedLine[9])).subtract(BigInteger.ONE);

                    }

                    //System.out.println("gene start: "+geneStart);
                    //System.out.println("PROMOTER START "+promoterStart);
                    //System.out.println("PROMOTER END "+promoterEnd);
                    
                    
                   //   System.out.println("startPosition: " + splitedLine[8]);
                   //   System.out.println("new startPosition: "+bsIniPos.toString());
                    bsPredictionInfo.setStartPosition(bsIniPos.toString());
                    //  System.out.println("endPosition: " + splitedLine[9]);
                    //  System.out.println("new endPosition: "+bsEndPos.toString());
                    bsPredictionInfo.setEndPosition(bsEndPos.toString());
                    
                    Integer startSiteDistance; 
                    BigInteger endDistance = bsEndPos.subtract(geneStart);
                    BigInteger startDistance = bsIniPos.subtract(geneStart);
                   // System.out.println("endDistance: "+endDistance);
                   // System.out.println("startDistance: "+startDistance);
                    if(endDistance.compareTo(BigInteger.ZERO)<0){
                        endDistance = endDistance.multiply(new BigInteger("-1"));
                    }
                    if(startDistance.compareTo(BigInteger.ZERO)<0){
                        startDistance = startDistance.multiply(new BigInteger("-1"));
                    }
                    
                    if(endDistance.compareTo(startDistance) > 0){
                        startSiteDistance = startDistance.intValue();
                    }else{
                        startSiteDistance = endDistance.intValue();
                    }
                    
                    
                   
                    
                    //System.out.println("startSiteDistance "+startSiteDistance);
                    
                    if(bsIniPos.compareTo(bsEndPos) > 0){
                        BigInteger temp = bsIniPos;
                        bsIniPos = bsEndPos;
                        bsEndPos = temp;
                    }
                    
                    //System.out.println("bsIniPos: "+bsIniPos);
                    //System.out.println("bsEndPos: "+bsEndPos);
                    
                    if(geneStart.compareTo(bsIniPos) < 0 && geneStart.compareTo(bsEndPos)>0){
                        startSiteDistance = startSiteDistance * -1;
                      //  System.out.println("DISTANCIA NEGATIVA");
                        
                    }
                    //System.out.println("startSiteDistance II "+startSiteDistance);
                    
                   bsPredictionInfo.setStartSiteDistance(startSiteDistance);

                } else if (sCurrentLine.contains("score:")) {
                    score = true;
                    linesAfterScore = 0;
                    newScore = true;
                }

                if (score) {
                    if (newScore) {
                        newScore = false;
                    } else {
                        linesAfterScore++;
//                                System.out.println("LINE AFTER SCORE: "+linesAfterScore+"------->>>>> "+sCurrentLine);
                    }

                    if (linesAfterScore == 3) {
                        String splitedLine[] = sCurrentLine.split("\\s+");
//                                int i=0;
//                                for (String splitedLine1 : splitedLine) {
//                                    System.out.println("["+i+"] => "+splitedLine1);
//                                    i++;
//                                }
                        bsPredictionInfo.setFoundBindingSite(splitedLine[3]);
                        //  System.out.println("+++++ " + line.toString());
                        //  System.out.println("+++++ " + line.toLine());
                        score = false;
                        break;
                    }
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
        return bsPredictionInfo;
    }

}
