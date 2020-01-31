/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GeneOperonDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.OperonDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.GeneOperon;
import com.coryneregnet7.model.GeneOperonPK;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Operon;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author mariana
 */
public class OperonPrediction {

    public static void main(String[] args) throws InterruptedException {

        OperonPrediction op = new OperonPrediction();

        //String gbffFile = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/gordonias/target/Gordonia_polyisoprenivorans_VH2_plasmid_p174_genomic.gbff";
        //String pathProteinId = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/operon_random_test/model/operonProteinIdNNMO-S1.txt";
        //String pathLocusTag = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/operon_random_test/model/operonLocusTagNNMO-S1.txt";
        GenomeDAO genomeDAO = new GenomeDAO();
        Genome genome = genomeDAO.findById(2244);
        String gbffFile = genome.getGbffFile();

        op.predictOperon(gbffFile, genome);
    }

    public void predictOperon(String gbffFile, Genome genome) throws InterruptedException {

        //String gbffFile = "/home/mariana/Dropbox/Doutorado/CP13_T1/PhoP/Gbk/phoP.gbff";
        //String pathProteinId = "/home/mariana/Dropbox/Doutorado/CP13_T1/Regulation_network_transference_PhoP/Phop/Binding_Sites/operonProteinIdPhoP.txt";
        //String pathLocusTag = "/home/mariana/Dropbox/Doutorado/CP13_T1/Regulation_network_transference_PhoP/Phop/Binding_Sites/operonLocusTagPhoP.txt";
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(gbffFile);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(gbffFile));
            String cdsStart = "";
            String cdsEnd = "";

            String previusCdsStart = "";
            String previousCdsEnd = "";

            String previusProteinId = "";
            String proteinId = "";

            String previousLocusTag = "";
            String locusTag = "";

            Boolean isComplement = false;
            Boolean previousIsComplement = false;

            Boolean firstLine = true;
            Boolean operon = false;
            ArrayList<OperonAux> operonsList = new ArrayList<OperonAux>();
            //ArrayList<String> currentProteinIdGeneListOperon = null;
            ArrayList<String> currentLocusTagGeneListOperon = null;
            Character currentOperonOrientation = null;

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                if (!firstLine) {
                    previusCdsStart = cdsStart;
                    previousCdsEnd = cdsEnd;
                    previousIsComplement = isComplement;
                    previusProteinId = proteinId;

                }

                System.out.println(sCurrentLine);
                if (sCurrentLine.contains(" gene") && sCurrentLine.contains("..")) {
                    System.out.println("---------------------------------------------------");
                    System.out.println("previousLocusTag ==>" + previousLocusTag);
                    previousLocusTag = locusTag;

                    System.out.println("locusTag ==>" + locusTag);
                    System.out.println("previousLocusTag = locusTag ==>" + previousLocusTag);
                }

                if (sCurrentLine.contains("/locus_tag=")) {
                    locusTag = sCurrentLine.replace("/locus_tag=\"", "");
                    locusTag = locusTag.replace("\"", "").trim();

                    System.out.println("---locus-tag--->>>>" + locusTag);
                    System.out.println("---previus-tag--->>>>" + previousLocusTag);
                }

                if (sCurrentLine.contains("CDS") && sCurrentLine.contains("..")) {
                    String[] lineCDS = sCurrentLine.split("\\s+");
                    String[] positions = lineCDS[2].split("\\.\\.");

//                    cdsStart = positions[0];
//                    cdsEnd = positions[1];
                    cdsStart = positions[0];
                    cdsEnd = positions[positions.length - 1];

                    if (cdsStart.contains("complement(")) {
                        //cdsStart = positions[1];
                        cdsStart = cdsStart.replace("complement(", "");//

                        //cdsEnd = positions[0];
                        cdsEnd = cdsEnd.replace(")", "");

                        if (cdsStart.contains("join(")) {
                            cdsStart = cdsStart.replace("join(", "");
                            cdsEnd = cdsEnd.replace(")", "");
                        }

                        isComplement = true;
                    } else {

                        if (cdsStart.contains("join(")) {
                            cdsStart = cdsStart.replace("join(", "");
                            cdsEnd = cdsEnd.replace(")", "");
                        }
                        isComplement = false;
                    }

                    cdsStart = cdsStart.replace("<", "");
                    cdsStart = cdsStart.replace(">", "");
                    cdsEnd = cdsEnd.replace("<", "");
                    cdsEnd = cdsEnd.replace(">", "");

                    System.out.println("cdsStart => " + cdsStart);
                    cdsStart = cdsStart.trim();
                    System.out.println("cdsEnd => " + cdsEnd);
                    cdsEnd = cdsEnd.trim();
                    System.out.println("CDS-START EMPTY---" + cdsStart + "------");
                    System.out.println("CDS-END EMPTY---" + cdsEnd + "------");
                    try {

                        // System.out.println(Integer.parseInt(cdsStart) + " - " + Integer.parseInt(previousCdsEnd) + " = " + (Integer.parseInt(cdsStart) - Integer.parseInt(previousCdsEnd)));
                        if (!firstLine && ((Integer.parseInt(cdsStart) - Integer.parseInt(previousCdsEnd)) < 50) && (isComplement.equals(previousIsComplement))) {
                            System.out.println(Integer.parseInt(cdsStart) + " - " + Integer.parseInt(previousCdsEnd) + " = " + (Integer.parseInt(cdsStart) - Integer.parseInt(previousCdsEnd)));
                           System.out.println("********OperonAux!");

                            operon = true;
                            if (isComplement) {
                                currentOperonOrientation = '-';
                            } else {
                                currentOperonOrientation = '+';
                            }

                        } else {
                            operon = false;
                        }

                    } catch (Exception E) {
                        E.printStackTrace();
                        System.out.println("ERRO NO ARQUIVO: ");
                        System.out.println(gbffFile);
                        System.out.println("\n\n\n\n\n\n");
                        // Thread.sleep(5000000);
                    }

                    System.out.println("\n-----------------------------------------");
                    System.out.println("\npreviusCdsStart " + previusCdsStart);
                    System.out.println("previousCdsEnd " + previousCdsEnd);
                    System.out.println("cdsStart " + cdsStart);
                    System.out.println("cdsEnd " + cdsEnd);
                    System.out.println("isComplement " + isComplement.toString());
                    System.out.println("previousIsComplement " + previousIsComplement.toString());
                    System.out.println("locusTag " + locusTag);
                    System.out.println("previousLocusTag " + previousLocusTag);
                    System.out.println("-----------------------------------------\n");

                    if (operon) {
                        if (currentLocusTagGeneListOperon == null) {
                            //currentProteinIdGeneListOperon = new ArrayList<String>();
                            currentLocusTagGeneListOperon = new ArrayList<String>();

                            if (isComplement) {

                                currentLocusTagGeneListOperon.add(previousLocusTag);
                                currentLocusTagGeneListOperon.add(0, locusTag);
                            } else {

                                currentLocusTagGeneListOperon.add(previousLocusTag);
                                currentLocusTagGeneListOperon.add(locusTag);
                            }

                        } else {

                            if (isComplement) {
                                currentLocusTagGeneListOperon.add(0, locusTag);
                            } else {
                                currentLocusTagGeneListOperon.add(locusTag);
                            }

                        }

                    } else {
                        if (currentLocusTagGeneListOperon != null) {
                            OperonAux operonObj = new OperonAux();
                            operonObj.setOrientation(currentOperonOrientation);

                            operonObj.setNameLocusTag(currentLocusTagGeneListOperon.get(0));
                            operonObj.setGenesLocusTag(currentLocusTagGeneListOperon);

                            operonsList.add(operonObj);

//                            System.out.println("currentOperonOrientation => " + currentOperonOrientation);
//                            System.out.println("currentOperonOrientation => " + currentOperonOrientation);
//                            System.out.println("currentOperonOrientation => " + currentOperonOrientation);
//                            System.out.println("currentOperonOrientation => " + currentOperonOrientation);
//                            System.out.println("currentOperonOrientation => " + currentOperonOrientation);
//                            System.out.println("currentOperonOrientation => " + currentOperonOrientation);
                            for (Iterator iterator = currentLocusTagGeneListOperon.iterator(); iterator.hasNext();) {
                                String next = (String) iterator.next();
                                      System.out.print(next + "\t");
                            }
                            System.out.println("\n");
                            //currentProteinIdGeneListOperon = null;
                            currentLocusTagGeneListOperon = null;
                            currentOperonOrientation = null;
                        }
                    }

                    if (firstLine) {
                        firstLine = false;
                    }
                }

            }

            Gene gene;
            GeneDAO geneDAO = new GeneDAO();
            Operon operonObj;
            OperonDAO operonDAO = new OperonDAO();
            GeneOperon geneOperon;
            GeneOperonDAO geneOperonDAO = new GeneOperonDAO();
            GeneOperonPK goPK = new GeneOperonPK();

            try {

                System.out.println("OPERON LIST: " + operonsList.size());

                for (OperonAux op : operonsList) {
                   // System.out.println("op => " + op.toStringProteinId());
                    System.out.println("op => " + op.toStringLocusTag());
                }
                // Thread.sleep(10000);
                for (Iterator iterator = operonsList.iterator(); iterator.hasNext();) {
                    OperonAux op = (OperonAux) iterator.next();
                    //   System.out.println(op.toStringProteinId());
                    //   System.out.println(op.toStringLocusTag() + "\n");
                    operonObj = new Operon();
                    operonObj.setName("OP_" + op.getNameLocusTag());

                    if (op.getOrientation() == '-') {
                        operonObj.setOrientation("reverse");
                    } else if (op.getOrientation() == '+') {
                        operonObj.setOrientation("forward");
                    }

                    operonObj = (Operon) operonDAO.save(operonObj);

                    ArrayList<String> genes = op.getGenesLocusTag();

                    int i = 0;
                    for (String g : genes) {
                        System.out.println("genome.getId() => " + genome.getId());
                        System.out.println("G ==> " + g);
                        gene = geneDAO.findByGenomeLocusTagProteinId(genome.getId(), g);
                        if (gene != null) {
                            System.out.println("\n"+g);
                            System.out.println("gene => " + gene.toString());
                            System.out.println("operon => " + operonObj.toString() + "\n--------------");
                            goPK = new GeneOperonPK(gene.getId(), operonObj.getId());
                            geneOperon = new GeneOperon();
                            geneOperon.setGeneOperonPK(goPK);
                            i++;
                            geneOperon.setPos(i);
                            geneOperonDAO.save(geneOperon);
                        }

                    }

                }

            } catch (Exception e) {
                e.printStackTrace();

            } finally {

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
