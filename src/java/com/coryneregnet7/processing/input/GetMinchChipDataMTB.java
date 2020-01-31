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
import com.coryneregnet7.processing.AlignBindingSites;
import com.coryneregnet7.processing.BindingSitePredictionInfo;
import com.coryneregnet7.processing.RunHmmer;
import com.coryneregnet7.processing.RunPipeline;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author mariana
 */
public class GetMinchChipDataMTB {

    public static void main(String[] args) throws InterruptedException {
        GetMinchChipDataMTB g = new GetMinchChipDataMTB();
        //g.joinIndividualBs();
        //g.joinPredictions();

        GetMinchChipDataMTB get = new GetMinchChipDataMTB();
        get.getMotifs("/home/doglas/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/model/GCA_000195955.2_ASM19595v2_chip_consensus.csv", 2463);
    }

    public void joinPredictions() {
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/new_regulatory_data/mycobacterium_tuberculosis/minch/binding-sites/regulations.tsv");
            br = new BufferedReader(fr);

            String sCurrentLine;

            ArrayList<String[]> newMotifs = new ArrayList<>();

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                String[] splitedLine = sCurrentLine.split("\t");
//                for (int i = 0; i < splitedLine.length; i++) {
//                    System.out.println("splitedLine["+i+"] = "+splitedLine[i]);
//                }
                String[] pairBs = new String[3];
                if (!splitedLine[8].isEmpty()) {
                    pairBs[0] = splitedLine[0];
                    pairBs[1] = splitedLine[4];
                    pairBs[2] = splitedLine[8];
                    System.out.println(pairBs[0] + "\t" + pairBs[1] + "\t" + pairBs[2]);
                    newMotifs.add(pairBs);
                    pairBs = new String[3];
                }

            }

            System.out.println("----------------------");
            System.out.println("----------------------");
            System.out.println("----------------------\n\n");
            for (String[] newMotif : newMotifs) {
                System.out.println(newMotif[0] + "\t" + newMotif[1] + "\t" + newMotif[2]);
            }
            System.out.println("----------------------");
            System.out.println("----------------------");
            System.out.println("----------------------\n\n");
            fr = new FileReader("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/new_regulatory_data/mycobacterium_tuberculosis/minch/binding-sites-all/differencially-expressed-with-binding-sites.tsv.ctftg");
            br = new BufferedReader(fr);

            sCurrentLine = new String();

            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println(sCurrentLine);
                String[] splitedLine = sCurrentLine.split("\t");
//                for (int i = 0; i < splitedLine.length; i++) {
//                    System.out.println("splitedLine["+i+"] = "+splitedLine[i]);
//                }

                boolean achou = false;
                for (String[] newMotif : newMotifs) {
                    if (newMotif[0].equals(splitedLine[0])
                            && newMotif[1].equals(splitedLine[4])) {
                        // System.out.println("--------" + "ACHEI");
                        //System.out.println("--------" + newMotif[0] + "\t" + newMotif[1] + "\t" + newMotif[2]);
                        if (splitedLine[8].isEmpty()) {
                            // System.out.println("splitedLine[4]-" + splitedLine[8] + "-----------");
                            // System.out.println("splitedLine[4].isEmpty() " + splitedLine[8].isEmpty());
                            // System.out.println("tem num e nap tem no outro");
                            splitedLine[8] = newMotif[2];
                            System.out.println(splitedLine[0] + "\t" + splitedLine[1] + "\t" + splitedLine[2] + "\t" + splitedLine[3] + "\t"
                                    + splitedLine[4] + "\t" + splitedLine[5] + "\t" + splitedLine[6] + "\t" + splitedLine[7] + "\t"
                                    + splitedLine[8] + "\t" + splitedLine[9] + "\t" + splitedLine[10]);
                            achou = true;
                        }
                    } else {
                        // System.out.println("--------NAO ACHEI");
                        // System.out.println("--------"+newMotif[0]+"\t"+newMotif[1]+"\t"+newMotif[2]);
                    }
                }
                if (!achou) {
                    System.out.println(sCurrentLine);
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

    public void joinIndividualBs() {
        File individualMotifs = new File("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/new_regulatory_data/mycobacterium_tuberculosis/minch/minch-test/found_bs.tsv");
        File regulations = new File("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/new_regulatory_data/mycobacterium_tuberculosis/minch/minch-test/model/GCA_000195955.2_ASM19595v2.tsv.ctftg");

        BufferedReader br = null;
        FileReader fr = null;

        ArrayList<String[]> array = new ArrayList<>();

        try {

            fr = new FileReader(individualMotifs);
            br = new BufferedReader(fr);

            String sCurrentLine;

            String[] motifs = new String[3];

            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println(sCurrentLine);
                String splitedLine[] = sCurrentLine.split("\t");
                if (splitedLine.length > 2) {
                    motifs[0] = splitedLine[0];
                    motifs[1] = splitedLine[1];
                    motifs[2] = splitedLine[2];

                    array.add(motifs);
                    motifs = new String[3];
                }

            }

            fr = new FileReader(regulations);
            br = new BufferedReader(fr);

            String sCurrentLineReg = "";

            /*
            splitedLine[0] = Transcription factor
            splitedLine[1] = Transcription factor name
            splitedLine[2] = Type
            splitedLine[3] = Role
            splitedLine[4] = Target Gene
            splitedLine[5] = Target gene name
            splitedLine[6] = Evidence
            splitedLine[7] = PubmedIDs
            splitedLine[8] = Binding motif
            splitedLine[9] = Is CDS sigma factor
            splitedLine[10] = Source
             */
            GeneDAO geneDAO = new GeneDAO();
            while ((sCurrentLineReg = br.readLine()) != null) {
                // System.out.println(sCurrentLineReg);
                String splitedLine[] = sCurrentLineReg.split("\t");
//                for (int i = 0; i < splitedLine.length; i++) {
//                    System.out.println("splitedLine[" + i + "] = " + splitedLine[i]);
//
//                    }

                boolean found = false;
                if (!sCurrentLineReg.startsWith("Transcription factor")) {
                    Gene geneTF = new Gene();
                    Gene geneTG = new Gene();
                    geneTF = geneDAO.findByGenomeLocusTag(1088, splitedLine[0]);
                    splitedLine[1] = geneTF.getName();
                    geneTG = geneDAO.findByGenomeLocusTag(1088, splitedLine[4]);
                    splitedLine[5] = geneTG.getName();

                    if (splitedLine[1].contains("sig")) {
                        splitedLine[9] = "+";
                    }

                    String bs = "";
                    for (String[] motifsS : array) {
                        if (motifsS[0].equals(splitedLine[0])
                                && motifsS[1].equals(splitedLine[4])) {
                            // System.out.println("----------ACHEI!");
                            splitedLine[8] = motifsS[2];
                            System.out.println(splitedLine[0] + "\t" + splitedLine[1] + "\t" + splitedLine[2] + "\t" + splitedLine[3] + "\t"
                                    + splitedLine[4] + "\t" + splitedLine[5] + "\t" + splitedLine[6] + "\t" + splitedLine[7] + "\t"
                                    + splitedLine[8] + "\t" + splitedLine[9] + "\t" + splitedLine[10]);
                            found = true;
                        }
                    }

                }

                if (!found) {
                    System.out.println(splitedLine[0] + "\t" + splitedLine[1] + "\t" + splitedLine[2] + "\t" + splitedLine[3] + "\t"
                            + splitedLine[4] + "\t" + splitedLine[5] + "\t" + splitedLine[6] + "\t" + splitedLine[7] + "\t"
                            + splitedLine[8] + "\t" + splitedLine[9] + "\t" + splitedLine[10]);
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

    public Hashtable<String, ArrayList<String>> getMotifs(String file, Integer genome) throws InterruptedException {

        Hashtable<String, ArrayList<String>> motifs = new Hashtable<>();
        BufferedReader br = null;
        FileReader fr = null;

        try {

            //fr = new FileReader("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/new_regulatory_data/mycobacterium_tuberculosis/minch/ncomms6829-s5.csv");
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                if (sCurrentLine.startsWith("Rv")) {
                    String[] splitedLine = sCurrentLine.split("\t");
                    System.out.print(splitedLine[0] + "\t");
                    System.out.print(splitedLine[6] + "\t");
                    System.out.println(splitedLine[11]);
                    //if (splitedLine[1].contains("in")) {
                    String[] motifsS = {splitedLine[6], splitedLine[11]};
                    ArrayList<String> array = new ArrayList<String>();
                    if (motifs.get(splitedLine[0]) == null) {
                        System.out.println("é == null");
                        array.add(splitedLine[6]);
                        array.add(splitedLine[11]);
                        motifs.put(splitedLine[0], array);
                    } else {
                        System.out.println("já tem");
                        array = motifs.get(splitedLine[0]);
                        System.out.println("array " + array);
                        array.add(splitedLine[6]);
                        array.add(splitedLine[11]);
                        System.out.println("array " + array);
                        motifs.put(splitedLine[0], array);
                    }

                    System.out.println(splitedLine[0] + "\t" + array);
                    //}

                }

//                for (int i = 0; i < splitedLine.length; i++) {
//                    System.out.println("splitedLine["+i+"] = "+splitedLine[i]);
//                }
            }

            AlignBindingSites align = new AlignBindingSites();
            RunHmmer runHmmer = new RunHmmer();
            RunPipeline runPipeline = new RunPipeline();
            GenomeDAO genomeDAO = new GenomeDAO();
            Genome genomeObj = genomeDAO.findById(genome);
            GeneDAO geneDAO = new GeneDAO();
            
            String hmmProfilesFolderPath = genomeObj.getFaaFile();
            String splited[] = hmmProfilesFolderPath.split("\\/");
            hmmProfilesFolderPath = hmmProfilesFolderPath.replace(splited[splited.length - 1], "");
            String genomeName = splited[splited.length - 1].replace("_protein.faa", "");
            hmmProfilesFolderPath = hmmProfilesFolderPath + genomeName + "-hmmer-profiles";

            File hmmProfilesFolder = new File(hmmProfilesFolderPath);
            createFolder(hmmProfilesFolder);

            for (Map.Entry<String, ArrayList<String>> entry : motifs.entrySet()) {
                String key = entry.getKey();
                ArrayList<String> value = entry.getValue();

                String fastaFile = hmmProfilesFolder.getAbsolutePath() + "/" + key + ".fasta";
                String stoFile = hmmProfilesFolder.getAbsolutePath() + "/" + key + ".sto";
                String hmmerFile = hmmProfilesFolder.getAbsolutePath() + "/" + key + ".hmm";
                String directory = hmmProfilesFolder.getAbsolutePath() + "/";
                
                System.out.println(fastaFile);
                System.out.println(stoFile);
                System.out.println(hmmerFile);
                System.out.println(directory);
                
                ///home/mariana/Dropbox/Doutorado/CoryneRegNet7/new_regulatory_data/mycobacterium_tuberculosis/minch/binding-sites/minch-test/model/GCA_000195955.2_ASM19595v2-hmmer-profiles
                FileWriter fileWriter = new FileWriter(fastaFile);
                PrintWriter printWriter = new PrintWriter(fileWriter);

                Gene geneTF = new Gene();
                geneTF = geneDAO.findByGenomeLocusTag(genome, key);

                for (int i = 0; i < value.size(); i++) {
                    printWriter.println(">bindingsite" + i);
                    printWriter.println(value.get(i));
                }

                printWriter.close();

                align.runClustalOmega(fastaFile, stoFile, directory);
                runHmmer.runHmmBuilder(hmmerFile, stoFile, directory);

                geneTF.setHmmProfile(hmmerFile);
                Integer profileLength = runPipeline.checkProfileLength(hmmerFile);
                geneTF.setProfileLength(profileLength);
                geneDAO.update(geneTF);

            }

//            br = null;
//            fr = null;
//
//            fr = new FileReader("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/new_regulatory_data/mycobacterium_tuberculosis/minch/ncomms6829-s4.csv");
//            br = new BufferedReader(fr);
//
//            String sCurrentLine = "";
//
//            GeneDAO geneDAO = new GeneDAO();
//            int count = 0;
//            ArrayList<String> foundTFs = new ArrayList<String>();
//            while ((sCurrentLine = br.readLine()) != null) {
//                //System.out.println(sCurrentLine);
//                if (sCurrentLine.startsWith("Rv")) {
//                    String[] splitedLine = sCurrentLine.split("\t");
//                    if (!splitedLine[10].equals("no") && !splitedLine[10].equals("NA")) {
//                        // System.out.println(sCurrentLine);
//                        String tf = splitedLine[0];
//                        String tg = splitedLine[1];
//                        File hmmProfile = new File("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/new_regulatory_data/mycobacterium_tuberculosis/minch/binding-sites/hmm-profiles/" + tf + ".hmm");
//
//                        //String genePromoterRegion=tg;
//                        Gene geneTG = geneDAO.findByGenomeLocusTag(1136, tg);
//                        Gene geneTF = geneDAO.findByGenomeLocusTag(1136, tf);
//                        //File promoterRegion = new File("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/new_regulatory_data/mycobacterium_tuberculosis/minch/binding-sites/hmm-profiles/" + genePromoterRegion + ".fna"); 
//                        File promoterRegion = new File(geneTG.getPromoterRegion().getFile());
//                        String hmmerFolder = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/new_regulatory_data/mycobacterium_tuberculosis/minch/binding-sites";
//                        String resultFile = tf + "_" + tg + ".out";
//                        runHmmer.runOnce(hmmProfile.getAbsolutePath(),
//                                promoterRegion.getAbsolutePath(),
//                                hmmerFolder,
//                                resultFile);
//                        String type = "";
//                        String role = "";
//                        if (splitedLine[10].equals("IND")) {
//                            role = "A";
//                        } else if (splitedLine[10].equals("REP")) {
//                            role = "R";
//                        }
//                        String isSigma = "";
//                        if (geneTF.getName().contains("sig")) {
//                            isSigma = "+";
//                        }
//                        if (runHmmer.findMatch(resultFile, hmmerFolder + "/result")) {
//                            // System.out.println("--------------achou!");
//                            // System.out.println("--------------"+hmmerFolder+"/result/"+resultFile);
//
//                            BindingSitePredictionInfo bsInfo = runHmmer.getMatchInfo(hmmerFolder + "/result/" + resultFile,
//                                    geneTG.getOrientation(),
//                                    geneTG.getPromoterRegion().getInitialPosition(),
//                                    geneTG.getPromoterRegion().getEndPosition(),
//                                    geneTG.getStartPosition());
//
//                            //Transcription factor	Transcription factor name	Type	Role	Target Gene	
//                            //Target gene name	Evidence	PubmedIDs	Binding motif	Is CDS sigma factor	Source
//                            System.out.println(geneTF.getLocusTag() + "\t" + geneTF.getName() + "\t" + type + "\t" + role
//                                    + "\t" + geneTG.getLocusTag() + "\t" + geneTG.getName()
//                                    + "\t" + "experimental" + "\t" + "25581030"
//                                    + "\t" + bsInfo.getFoundBindingSite()
//                                    //+"\t"+bsInfo.getStartPosition()
//                                    //+"\t"+bsInfo.getEndPosition()
//                                    + "\t" + isSigma + "\tMinch et al. 2015");
//
//                            count++;
//                        } else {
//                            System.out.println(geneTF.getLocusTag() + "\t" + geneTF.getName() + "\t" + type + "\t" + role
//                                    + "\t" + geneTG.getLocusTag() + "\t" + geneTG.getName() + " \t" + "experimental"
//                                    + "\t" + "experimental" + "\t" + "25581030"
//                                    + "\t" + ""
//                                    //+"\t"+bsInfo.getStartPosition()
//                                    //+"\t"+bsInfo.getEndPosition()
//                                    + "\t" + isSigma + "\tMinch et al. 2015");
//                        }
//                    }
//                }
//            }
//            System.out.println("count => " + count);
        } catch (Exception e) {

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
        return motifs;
    }

    public void createFolder(File folder) {
        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
    }

    /*
    public void get() {
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/new_regulatory_data/mycobacterium_tuberculosis/minch/ncomms6829-s4.csv");
            br = new BufferedReader(fr);

            String sCurrentLine;
            System.out.println("Transcription factor	Transcription factor name	Type	Role	Target Gene	Target gene name	Evidence	PubmedIDs	Binding motif	Is CDS sigma factor	Source");
            Hashtable<String, String[]> motifs = getMotifs();
            ArrayList<String> foundTFs = new ArrayList<String>();
            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println(sCurrentLine);
                if (sCurrentLine.startsWith("Rv")) {
                    String[] splitedLine = sCurrentLine.split("\t");
                    if (!splitedLine[10].equals("no") && !splitedLine[10].equals("NA")) {

                        //Transcription factor	Transcription factor name	Type	Role	
                        //Target Gene	Target gene name	Evidence	PubmedIDs	Binding motif	Is CDS sigma factor	Source
                        String tf = splitedLine[0];

                        String tfName = "";
                        String type = "";
                        String tg = splitedLine[1];
                        String tgName = "";
                        String evidence = "experimental";
                        String pmid = "25581030";
                        String isSigma = "";
                        String source = "Minch et al. 2015";

                        String motifString = "";
                        if (motifs.get(splitedLine[0]) != null) {
                            if (!foundTFs.contains(tf)) {
                                motifString = motifs.get(splitedLine[0])[0] + ";" + motifs.get(splitedLine[0])[1];
                                foundTFs.add(tf);
                            }

                        }

                        String role = "";
                        if (splitedLine[10].equals("REP")) {
                            role = "R";
                        } else if (splitedLine[10].equals("IND")) {
                            role = "A";
                        }

                        System.out.println(tf + "\t" + tfName + "\t" + type + "\t" + role + "\t" + tg + "\t" + tgName
                                + "\t" + evidence + "\t" + pmid + "\t" + motifString
                                + "\t" + isSigma + "\t" + source);
                    }
                }
//                
//                for (int i = 0; i < splitedLine.length; i++) {
//                    
//                }
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
     */
}
