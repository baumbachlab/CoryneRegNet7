/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.input;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.model.Gene;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author mariana
 */
public class ParseMTBNetworkPortal {

    public static void main(String[] args) {
        ParseMTBNetworkPortal p = new ParseMTBNetworkPortal();

        p.readTable3DeGenes();

    }

    public void getOperons() {
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/new regulatory data/mycobacterium tuberculosis/operons-myco-microbes-online.named");
            br = new BufferedReader(fr);

            String sCurrentLine;

            boolean operon = false;
            ArrayList<ArrayList<String>> array = new ArrayList<>();
            ArrayList<String> operonsList = new ArrayList<>();
            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println(sCurrentLine);
                String[] splitedLine = sCurrentLine.split("\t");
                System.out.println("\ngene1 " + splitedLine[2]);
                String gene1 = splitedLine[2];
                System.out.println("gene2 " + splitedLine[3]);
                String gene2 = splitedLine[3];
                System.out.println("isOp " + splitedLine[6]);
                String isOperon = splitedLine[6];
                if (isOperon.equals("TRUE")) {
                    if (!operonsList.contains(gene1)) {
                        operonsList.add(gene1);
                    }
                    operonsList.add(gene2);
                } else if (isOperon.equals("FALSE")) {
                    for (String string : operonsList) {
                        System.out.println("--------operonsList " + string);
                    }

                    if (!operonsList.isEmpty()) {
                        array.add(operonsList);
                    }
                    operonsList = new ArrayList<>();
                }
            }

            for (ArrayList<String> listOp : array) {
                for (int i =0; i<listOp.size(); i++) {
                    String string = listOp.get(i);
                    if(i==0){
                        System.out.print(">OP_"+string+"\t");
                    }
                    System.out.print(string + "\t");
                }
                System.out.println("");
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

    public Hashtable<String, String[]> readBioclusters() {

        BufferedReader br = null;
        FileReader fr = null;
        Hashtable<String, String[]> bioclusters = new Hashtable<>();
        try {

            fr = new FileReader("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/new regulatory data/mycobacterium tuberculosis/peterson-bioclusters.tsv");
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                if (!sCurrentLine.startsWith("Supplemental") && !sCurrentLine.startsWith("Bicluster")) {
                    //System.out.println(sCurrentLine);
                    String[] splitedLine = sCurrentLine.split("\t");
                    for (int i = 0; i < splitedLine.length; i++) {
                        //    System.out.println("splitedLine[" + i + "] = " + splitedLine[i]);
                    }
                    // System.out.println("ID: " + splitedLine[0] + "\tmotif1 : " + splitedLine[4] + "\tmotif1 : " + splitedLine[6]);
                    String[] motifs = {splitedLine[4], splitedLine[6]};
                    bioclusters.put(splitedLine[0], motifs);
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

        return bioclusters;
    }

    public ArrayList<Object[]> readTable3Bioclusters() {
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/new regulatory data/mycobacterium tuberculosis/Copy of Table3-1-june-csv.csv");
            br = new BufferedReader(fr);

            String sCurrentLine;
            String header = "Transcription Factor,Target Gene,Chip Center,Distance,Strand,Genomic Position,Type,Expression,Expression p-value,Operon,Differential Expression,Bicluster, q.Bicluster,q.ChIPSeq,q.ChIPSeq.genes,q.DE,q.DE.genes,";

            ArrayList<Object[]> array = new ArrayList<>();
            int count = 0;
            while ((sCurrentLine = br.readLine()) != null) {
                /*
                splitedLine[0] => Transcription Factor
                splitedLine[1] => Target Gene
                splitedLine[2] => Chip Center
                splitedLine[3] => Distance
                splitedLine[4] => Strand
                splitedLine[5] => Genomic Position
                splitedLine[6] => Type
                splitedLine[7] => Expression
                splitedLine[8] => Expression p-value
                splitedLine[9] => Operon
                splitedLine[10] => Differential Expression
                splitedLine[11] => Bicluster
                splitedLine[12] =>  q.Bicluster
                splitedLine[13] => q.ChIPSeq
                splitedLine[14] => q.ChIPSeq.genes
                splitedLine[15] => q.DE
                splitedLine[16] => q.DE.genes
                 */

                String[] splitedLine = sCurrentLine.split(",");
                if (!sCurrentLine.endsWith(",,,,,,,") && !sCurrentLine.equals(header)) {
                    // System.out.println(sCurrentLine);
                    for (int i = 0; i < splitedLine.length; i++) {
                        // System.out.println("splitedLine[" + i + "] => " + splitedLine[i]);

                        if (i == 11) {
                            //        System.out.println("Bioclusters: " + splitedLine[11]);
                        }

                        if (i == 14) {
                            //         System.out.println("q.ChIPSeq.genes: " + splitedLine[14]);
                        }

                        if (i == 16) {
                            //         System.out.println("q.DE.genes: " + splitedLine[16]);
                        }
                    }

                    String[] bioclusters = splitedLine[11].split(":");
                    String[] chipGenes = splitedLine[14].split(":");
                    String[] deGenes = splitedLine[16].split(":");

                    Object[] bioObjects = new Object[4];

                    for (int i = 0; i < bioclusters.length; i++) {
                        //      System.out.println("\n-------TF " + splitedLine[0]);
                        bioObjects[0] = splitedLine[0];
                        //    System.out.println("-------bioclusters[" + i + "] " + bioclusters[i]);
                        bioObjects[1] = bioclusters[i];
                        //    System.out.println("-------chipGenes[" + i + "] " + chipGenes[i]);
                        bioObjects[2] = chipGenes[i];
                        //    System.out.println("-------deGenes[" + i + "] " + deGenes[i]);
                        bioObjects[3] = deGenes[i];

                        array.add(bioObjects);
                        bioObjects = new Object[4];
                        count++;
                    }
                }
            }
            //  System.out.println("count = " + count);
            return array;

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

        return null;

    }

    public void readTable3DeGenes() {
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/new regulatory data/mycobacterium tuberculosis/Copy of Table3-1-june-csv.csv");
            br = new BufferedReader(fr);

            String sCurrentLine;
            String header = "Transcription Factor,Target Gene,Chip Center,Distance,Strand,Genomic Position,Type,Expression,Expression p-value,Operon,Differential Expression,Bicluster, q.Bicluster,q.ChIPSeq,q.ChIPSeq.genes,q.DE,q.DE.genes,";

            ArrayList<Object[]> bioObj = readTable3Bioclusters();
            Hashtable<String, String[]> bioclusters = readBioclusters();
            // System.out.println("bioObj " + bioObj.size());

            while ((sCurrentLine = br.readLine()) != null) {
                /*
                splitedLine[0] => Transcription Factor
                splitedLine[1] => Target Gene
                splitedLine[2] => Chip Center
                splitedLine[3] => Distance
                splitedLine[4] => Strand
                splitedLine[5] => Genomic Position
                splitedLine[6] => Type
                splitedLine[7] => Expression
                splitedLine[8] => Expression p-value
                splitedLine[9] => Operon
                splitedLine[10] => Differential Expression
                splitedLine[11] => Bicluster
                splitedLine[12] =>  q.Bicluster
                splitedLine[13] => q.ChIPSeq
                splitedLine[14] => q.ChIPSeq.genes
                splitedLine[15] => q.DE
                splitedLine[16] => q.DE.genes
                 */

                String[] splitedLine = sCurrentLine.split(",");
                if (!sCurrentLine.equals(header) && !splitedLine[10].equals("no") && !splitedLine[10].equals("NA")) {

                    String tf = splitedLine[0];
                    String tg = splitedLine[1];
                    String de = splitedLine[10];

                    boolean found = false;
                    String role = "";
                    if (splitedLine[10].equals("REP")) {
                        role = "R";
                    } else if (splitedLine[10].equals("IND")) {
                        role = "A";
                    }

                    GeneDAO geneDAO = new GeneDAO();

                    for (int i = 0; i < bioObj.size(); i++) {
                        // System.out.println("\n");
                        // System.out.println("bioObj[" + i + "][0] = " + bioObj.get(i)[0]);
                        String bioTF = (String) bioObj.get(i)[0];
                        // System.out.println("bioObj[" + i + "][1] = " + bioObj.get(i)[1]);
                        String bioMotifs = (String) bioObj.get(i)[1];
                        //System.out.println("bioObj[" + i + "][2] chip = " + bioObj.get(i)[2]);
                        String bioChipGenes = (String) bioObj.get(i)[2];
                        //  System.out.println("bioObj[" + i + "][3] DE = " + bioObj.get(i)[3]);
                        String bioDEGenes = (String) bioObj.get(i)[3];

                        if (tf.equals(bioTF)) {
                            // System.out.println("TFs iguais!!!");
                            //System.out.println("bioObj[" + i + "][0] = " + bioObj.get(i)[0]);

                            if (bioChipGenes.contains(tg)) {
                                // System.out.println("TGs iguais!!");
                                //System.out.println("bioObj[" + i + "][2] chip = " + bioObj.get(i)[2]);
                                //System.out.println("bioObj[" + i + "][1] cc = " + bioObj.get(i)[1]);

                                //Transcription factor	Transcription factor name	Type	Role	Target Gene	
                                //Target gene name	Evidence	PubmedIDs	Binding motif	Is CDS sigma factor	
                                //Source
                                String motif1 = bioclusters.get(bioObj.get(i)[1])[0].toUpperCase();
                                String motif2 = bioclusters.get(bioObj.get(i)[1])[1].toUpperCase();

                                Gene geneTF = geneDAO.findByGenomeLocusTag(1068, tf);
                                Gene geneTG = geneDAO.findByGenomeLocusTag(1068, tg);

                                String isSigma = "";
                                if (geneTF.getName().contains("sig")) {
                                    isSigma = "+";
                                }

                                System.out.println(tf + "\t" + geneTF.getName() + "\t-\t" + role + "\t" + tg + "\t" + geneTG.getName()
                                        + "\t" + "experimental" + "\t25977815" + "\t" + motif1 + ";" + motif2 + "\t" + isSigma+"\t"+"MTB Network Portal");
                                found = true;
                                break;
                            }
                        }

                    }

                    if (!found) {

                        Gene geneTF = geneDAO.findByGenomeLocusTag(1068, tf);
                        Gene geneTG = geneDAO.findByGenomeLocusTag(1068, tg);
                        String isSigma = "";
                        if (geneTF.getName().contains("sig")) {
                            isSigma = "+";
                        }

                        System.out.println(tf + "\t" + geneTF.getName() + "\t-\t" + role + "\t" + tg + "\t" + geneTG.getName()
                                + "\t" + "experimental" + "\t25977815" + "\t" + "\t" + isSigma+"\t"+"MTB Network Portal");
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
    }
}
