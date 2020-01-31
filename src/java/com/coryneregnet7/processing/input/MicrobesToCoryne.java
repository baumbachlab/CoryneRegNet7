/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.input;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.model.Gene;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author doglas
 */
public class MicrobesToCoryne {

    public static void main(String[] args) {
        MicrobesToCoryne mtc = new MicrobesToCoryne();
        mtc.readOpFile();
    }

    public void readOpFile() {
        File folder = new File("/home/ubuntu/sanity-test/microbes-online");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
                BufferedReader br = null;
                FileReader fr = null;

                if (file.getName().contains(".op")) {

                    try {
                        fr = new FileReader(folder + "/" + file.getName());
                        br = new BufferedReader(fr);

                        String sCurrentLine;

                        List<String> operon = new ArrayList();
                        //List<ArrayList<String>> operons=new ArrayList();

//                        File opFile = new File(folder + "/" + file.getName().replace(".named", "_coryne_.op"));
//                        FileWriter fileWriter = new FileWriter(opFile);
//                        PrintWriter printWriter = new PrintWriter(fileWriter);
                        GeneDAO geneDAO = new GeneDAO();

                        File newOpFile = new File(folder + "/final/" + file.getName());
                        FileWriter fileWriter = new FileWriter(newOpFile);
                        PrintWriter printWriter = new PrintWriter(fileWriter);

                        while ((sCurrentLine = br.readLine()) != null) {
                            //System.out.println(sCurrentLine);

                            String[] splitedLine = sCurrentLine.split("\t");
                            String operonOrientation = "";
                            List<String> operonList = new ArrayList<>();
                            for (int i = 0; i < splitedLine.length; i++) {
                                String string = splitedLine[i];
                                //System.out.println("----------string gene = " + string);
                                Gene gene = new Gene();
                                gene = geneDAO.findByAltLocusTag(string);
                                if (gene == null) {
                                    gene = geneDAO.findByAltLocusTag(string);
//                                    if (gene != null) {
//                                        System.out.println("----------gene " + gene.getLocusTag() + " " + gene.getOrientation());
//                                    } else {
//                                        System.out.println("----------gene null");
//                                    }

                                }
//                                else {
//                                    System.out.println("----------gene " + gene.getLocusTag() + " " + gene.getOrientation());
//                                }

                                if (gene != null) {
                                    if (operonOrientation.isEmpty()) {
                                        operonOrientation = gene.getOrientation();
                                        operonList.add(gene.getLocusTag());
                                        //System.out.println(" operonList.add(gene.getLocusTag()); "+gene.getLocusTag());
                                    } else {
                                        if (operonOrientation.equals(gene.getOrientation())) {
                                            //System.out.println("----------mesma orientacao, okay");
                                            if (operonOrientation.equals("forward")) {
                                                operonList.add(gene.getLocusTag());
                                            } else {
                                                // System.out.println("reverse!");
                                                operonList.add(0, gene.getLocusTag());
                                            }

                                        } else {
                                            // System.out.println("**************8-OUTRA ORIENTACAO");
                                            operonOrientation = gene.getOrientation();

                                            printWriter.print(">OP_" + operonList.get(0));
                                            //printWriter.print("");
                                            if (operonOrientation.equals("forward")) {
                                                printWriter.print("\t+");
                                            } else {
                                                printWriter.print("\t-");
                                            }
                                            for (String opString : operonList) {
                                                printWriter.print("\t" + opString);
                                            }
                                            printWriter.print("\n");
                                            operonList = new ArrayList<>();
                                            operonList.add(gene.getLocusTag());
                                            //System.out.println("ADICIONEI "+gene.getLocusTag());
                                        }
                                    }

                                }

                            }

                            if (!operonList.isEmpty()) {
                                printWriter.print(">OP_" + operonList.get(0));
                                if (operonOrientation.equals("forward")) {
                                    printWriter.print("\t+");
                                } else {
                                    printWriter.print("\t-");
                                }
                                for (String opString : operonList) {
                                    printWriter.print("\t" + opString);
                                }
                                printWriter.print("\n");
                            }

                            // System.out.println("");
                            //System.out.println(splitedLine[2]);
                            //System.out.println(splitedLine[3]);
                            //System.out.println(splitedLine[6]);
                        }
                        // System.out.println("\n\n");
                        printWriter.close();
                        fileWriter.close();

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
        }
    }

    public void createOpFile() {

        File folder = new File("/home/doglas/Dropbox/Doutorado/CoryneRegNet7/operons/microbes-online");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
                BufferedReader br = null;
                FileReader fr = null;

                try {
                    System.out.println(folder + "/" + file.getName());
                    fr = new FileReader(folder + "/" + file.getName());
                    br = new BufferedReader(fr);

                    String sCurrentLine;

                    List<String> operon = new ArrayList();
                    //List<ArrayList<String>> operons=new ArrayList();

                    File opFile = new File(folder + "/" + file.getName().replace(".named", ".op"));
                    FileWriter fileWriter = new FileWriter(opFile);
                    PrintWriter printWriter = new PrintWriter(fileWriter);

                    while ((sCurrentLine = br.readLine()) != null) {
                        // System.out.println(sCurrentLine);
                        if (!sCurrentLine.startsWith("Gene1")) {
                            String[] splitedLine = sCurrentLine.split("\t");
                            //System.out.println(splitedLine[2]);
                            //System.out.println(splitedLine[3]);
                            //System.out.println(splitedLine[6]);

                            Boolean isOp = new Boolean(splitedLine[6]);

                            if (isOp) {
                                if (operon.isEmpty()) {
                                    operon.add(splitedLine[2]);
                                    //System.out.println("splitedLine[2] " + splitedLine[2]);
                                }
                                operon.add(splitedLine[3]);
                                //System.out.println("splitedLine[3] " + splitedLine[3]);
                            }

                            if (!operon.isEmpty() && !isOp) {
                                for (String string : operon) {
                                    System.out.print(string + "\t");
                                    printWriter.print(string + "\t");
                                }
                                System.out.println("");
                                printWriter.print("\n");
                                //    System.out.println("op vazio e false\n\n");
                                operon = new ArrayList<>();
                            }
                        }

                    }
                    printWriter.close();
                    fileWriter.close();

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
    }

}