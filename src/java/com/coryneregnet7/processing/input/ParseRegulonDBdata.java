/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.input;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GeneOperonDAO;
import com.coryneregnet7.dao.OperonDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Operon;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author mariana
 */
public class ParseRegulonDBdata {

    public static void main(String[] args) {

        ParseRegulonDBdata p = new ParseRegulonDBdata();
        Hashtable<String, ArrayList<String>> operonsMap = p.getOperons();

        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/new regulatory data/regulon-db/E-coli-reguations/regulations-29-05.csv");
            br = new BufferedReader(fr);

            String sCurrentLine;

            GeneOperonDAO geneOperonDAO = new GeneOperonDAO();
            GeneDAO geneDAO = new GeneDAO();
            Gene geneTF = new Gene();
            Gene geneTG = new Gene();
            int count = 0;

            //CDS	CDS gene name	CDS gene module	Type	Mutant	Auto	Role	Target Gene	Target gene name	
            //Target gene module	Motif known	Evidence	PubmedIDs	Binding motif	Is CDS sigma factor
            /*
            splitedLine[0] = CDS
            splitedLine[1] = CDS gene name
            splitedLine[2] = CDS gene module
            splitedLine[3] = Type
            splitedLine[4] = Mutant
            splitedLine[5] = Auto
            splitedLine[6] = Role
            splitedLine[7] = Target Gene
            splitedLine[8] = Target gene name
            splitedLine[9] = Target gene module
            splitedLine[10] = Motif known
            splitedLine[11] = Evidence
            splitedLine[12] = PubmedIDs
            splitedLine[13] = Binding motif
            splitedLine[14] = Is CDS sigma factor
             */
            while ((sCurrentLine = br.readLine()) != null) {
             //   System.out.println(sCurrentLine);
                String[] splitedLine = sCurrentLine.split("\t");
//                for (int i = 0; i < splitedLine.length; i++) {
//                    System.out.println("splitedLine[" + i + "] = " + splitedLine[i]);
//                }

                Character firstLetter = splitedLine[1].charAt(0);
                String lowerCaseFirstLetter = firstLetter.toString().toLowerCase();
                String tfName = splitedLine[1].replaceFirst(firstLetter.toString(), lowerCaseFirstLetter);
                geneTF = geneDAO.findByGenomeNameUnique(1057, tfName);

                // System.out.println("splitedLine[1] " + tfName);
                if (geneTF != null) {
                    // System.out.println("geneTF: " + tfName + " => " + geneTF.getLocusTag());
                } else {
                    //  System.out.println("geneTF == null 1");
                    if (geneTF == null) {
                        geneTF = geneDAO.findByGenomeNameUnique(1057, tfName.toLowerCase());
                        if (geneTF != null) {
                            // System.out.println("geneTF: " + tfName + " => " + geneTF.getLocusTag());
                        } else {
                            //   System.out.println("geneTF == NULL 2");
                        }
                    }
                }

                //geneTG = geneDAO.findByGenomeNameUnique(1056, splitedLine[8]);
                //OperonDAO operonDAO = new OperonDAO();
                //Operon op = new Operon();
                //op = operonDAO.findByName(splitedLine[8]);
                //System.out.println("op => "+op.toString());
                if (geneTF != null) {
                    ArrayList<String> operons = operonsMap.get(splitedLine[8]);
                    // System.out.println("splitedLine[8] " + splitedLine[8]);
                    //Transcription factor	Transcription factor name	Type	Role	Target Gene	Target gene name	
                    //Evidence	PubmedIDs	Binding motif	Is CDS sigma factor	Source

                    String bs = "";
                    if (splitedLine.length >= 13) {
                        // bs = splitedLine[13];
                        for (int i = 0; i < splitedLine[13].length(); i++) {
                            if (Character.isUpperCase(splitedLine[13].charAt(i))) {
                                bs = bs+splitedLine[13].charAt(i);
                            }
                        }
                    }

                    if (operons != null && !operons.isEmpty()) {
                        //System.out.println("geneTG: " + splitedLine[8] + " => " + operons);

                        //for (String gene : operons) {
                        for (int j = 0;j<operons.size();j++) {
                            String gene = operons.get(j);
                            geneTG = geneDAO.findByGenomeLocusTag(1057, gene);
                            if(j>0){
                                bs="-";
                            }
                            System.out.println(geneTF.getLocusTag() + "\t" + geneTF.getName() + "\t-" + "\t" + splitedLine[6] + "\t" + geneTG.getLocusTag()
                                    + "\t" + geneTG.getName() + "\t" + "experimental" + "\t" + "pubmed_id" + "\t" + bs + "\t" + "-" + "\tRegulonDB");
                        }

                    } else {
                        // System.out.println("geneTG ==> nao é operon " + splitedLine[8]);
                        geneTG = geneDAO.findByGenomeNameUnique(1057, splitedLine[8]);
                        if (geneTG != null) {
                            //System.out.println("geneTG ==> " + geneTG.getLocusTag());
                            System.out.println(geneTF.getLocusTag() + "\t" + geneTF.getName() + "\t-" + "\t" + splitedLine[6] + "\t" + geneTG.getLocusTag()
                                    + "\t" + geneTG.getName() + "\t" + "experimental" + "\t" + "pubmed_id" + "\t" + bs + "\t" + "-" + "\tRegulonDB");
                        } else {
                            // System.out.println("geneTG == NULL =>" + splitedLine[8]);
                        }
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

    public Hashtable<String, ArrayList<String>> getOperons() {
        BufferedReader br = null;
        FileReader fr = null;

        Hashtable<String, ArrayList<String>> operons = new Hashtable<>();
        try {

            fr = new FileReader("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/new regulatory data/regulon-db/E-coli-reguations/operons-e-coli.txt");
            br = new BufferedReader(fr);

            String sCurrentLine;
            int count = 0;
            ArrayList<String> lost = new ArrayList<>();
            while ((sCurrentLine = br.readLine()) != null) {
                //  System.out.println(sCurrentLine);
                String[] splitedLine = sCurrentLine.split("\t");

                GeneDAO geneDAO = new GeneDAO();
                ArrayList<String> op = new ArrayList<String>();
                String orientation = "";
                for (int i = 0; i < splitedLine.length; i++) {
                    String genes = splitedLine[i];
                    // System.out.println("gene -> " + genes);

                    if (i != 0) {
                        if (genes.equals("+") || genes.equals("-")) {
                            //  System.out.println("\t" + genes);
                            orientation = genes;
                        } else {
                            Gene geneTF = new Gene();
                            geneTF = geneDAO.findByGenomeNameUnique(1057, genes);
                            if (geneTF != null) {
                                //  System.out.println("-------" + genes + " => " + geneTF.getLocusTag());
                                op.add(geneTF.getLocusTag());

                            } else {
                                if (genes.contains("_")) {
                                    String genePseudo[] = genes.split("_");
                                    geneTF = geneDAO.findByGenomeNameUnique(1057, genePseudo[0]);
                                }

                                if (geneTF != null) {
                                    //    System.out.println("-------PSEUDO-----" + genes + " => " + geneTF.getLocusTag());
                                    if (!op.contains(geneTF.getLocusTag())) {
                                        op.add(geneTF.getLocusTag());
                                    }
                                } else {
                                    count++;
                                    lost.add(genes);
                                    //System.out.println("NULL");
                                }
                            }
                        }
                    }

                }
                if (op != null && !op.isEmpty() && op.size() > 1) {
                    operons.put(splitedLine[0], op);
//                    System.out.print(">OP_" + op.get(0) + "\t" + orientation);
//                    for (String string : op) {
//                        System.out.print("\t" + string);
//                    }
//                    System.out.println("");
                }

            }

            for (Map.Entry<String, ArrayList<String>> entry : operons.entrySet()) {
                String key = entry.getKey();
                ArrayList<String> value = entry.getValue();
                System.out.println(key + " => " + value);
            }
            System.out.println("\n\n");

//            System.out.println("\n\n---------------------\nperdemos " + count);
//            for (String string : lost) {
//                System.out.println(string);
//            }
//            System.out.println("-----------------------\n\n");
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

        return operons;
    }

}
