/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.ncrna;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.RnaInteractionDAO;
import com.coryneregnet7.dao.SmallRnaDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.RnaInteraction;
import com.coryneregnet7.model.SmallRna;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

/**
 *
 * @author mariana
 */
public class ReadCopraOutput {

    public static void main(String arg[]) throws FileNotFoundException, IOException {

        //subfolder3 ok
        //subfolder5 ok
        //subfolder4 ok
        //subfolder2 ok
        //subfolder1 ok
        //subfolder7 ok
        File folder = new File("/data/home/mariana/Dropbox/Doutorado/ncRNA/six-strains-analysis/srna-glutamicum-rnadetect/results-to-copra/copra-rnadetect");
        File[] listOfFiles = folder.listFiles();
        String sRnaLocus = "";

        for (File file : listOfFiles) {
            if (file.getName().startsWith("c-") && !file.getName().endsWith(".txt") 
                    && !file.getName().endsWith(".fa") 
                    && !file.getName().endsWith(".fasta")
                    && !file.getName().endsWith(".sorted")) {
                System.out.println(file.getAbsolutePath());
                sRnaLocus = file.getName().replace("-copra", "-output");
                System.out.println(sRnaLocus);

//                IF BSRD GET THE SMALL RNA NAME FROM FILE. 
             //   String txtFile = "/data/home/mariana/Dropbox/Doutorado/ncRNA/six-strains-analysis/BSRD/" + file.getName() + ".txt";

//                BufferedReader br = new BufferedReader(new FileReader(txtFile));
//                String line = br.readLine();
//                br.close();
//                String[] splitedLine = line.split("\\|");
//                sRnaLocus = splitedLine[0].replace(">", "");
                System.out.println("sRNA locus= " + sRnaLocus.toString());
//
                SmallRnaDAO smallRnaDAO = new SmallRnaDAO();
                SmallRna smallRna = smallRnaDAO.findByLocusTag(sRnaLocus);
                System.out.println("sRNA= " + smallRna);

                ReadCopraOutput rco = new ReadCopraOutput();
                rco.readFile(file.getAbsolutePath() + "/coprarna_websrv_table.csv", smallRna);
            }
        }

    }

    public void readFile(String filename, SmallRna smallRna) {
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(filename);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(filename));
            int numLine = 0;
            RnaInteraction rnaInteraction = new RnaInteraction();
            RnaInteractionDAO rnaInteractionDAO = new RnaInteractionDAO();

            Gene gene = new Gene();
            GeneDAO geneDAO = new GeneDAO();

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                if (numLine < 16) {
                    String[] splitedLine = sCurrentLine.split(",");
                    for (int i = 0; i < splitedLine.length; i++) {

                        System.out.print(splitedLine[i] + "\t");

                    }
                    rnaInteraction = new RnaInteraction();
                    rnaInteraction.setSrna(smallRna);
                    System.out.println("\n");
                    if (numLine != 0) {
                        //System.out.println("rank: " + splitedLine[0]);
                        rnaInteraction.setRank(new Integer(splitedLine[0]));

                        //System.out.println("copra p-value: " + splitedLine[1]);
                        rnaInteraction.setCopraPvalue(new BigDecimal(splitedLine[1]));

                        //System.out.println("copra fdr: " + splitedLine[2]);
                        rnaInteraction.setCopraFdr(new BigDecimal(splitedLine[2]));

                        //System.out.println("locus tag: " + splitedLine[3]);
                        gene = geneDAO.findByLocusTag(splitedLine[3]);
                        if (gene == null) {
                            geneDAO.findByAltLocusTag(splitedLine[3]);
                        }
                        //System.out.println("Gene => "+gene);
                        rnaInteraction.setMrna(gene);

                        //System.out.println("energy: " + splitedLine[5]);
                        rnaInteraction.setEnergy(new BigDecimal(splitedLine[5]));

                        //System.out.println("inta pvalue: " + splitedLine[6]);
                        rnaInteraction.setIntaPvalue(new BigDecimal(splitedLine[6]));

                        //System.out.println("position mRNA: " + splitedLine[7]);
                        rnaInteraction.setPositionMrna(splitedLine[7]);

                        // System.out.println("position ncRNA: " + splitedLine[8]);
                        rnaInteraction.setPositionNcrna(splitedLine[8]);

                        //System.out.println("interaction: " + splitedLine[12]);
                        rnaInteraction.setInteraction(splitedLine[12]);

                        //System.out.println("position seed mRNA: " + splitedLine[13]);
                        rnaInteraction.setPositionSeedMrna(splitedLine[13]);

                        // System.out.println("position seed ncRNA: " + splitedLine[14]);
                        rnaInteraction.setPositionSeedNcrna(splitedLine[14]);

                        if (!splitedLine[15].isEmpty()) {
                            System.out.println("hybridization energy: " + splitedLine[15]);
                            rnaInteraction.setHybridizationEnergy(new BigDecimal(splitedLine[15].replace(" kcal/mol", "").trim()));
                        }

                        if (!splitedLine[16].isEmpty()) {
                            //System.out.println("unfolding energy mRNA: " + splitedLine[16]);
                            rnaInteraction.setUnfoldingEnergyMrna(new BigDecimal(splitedLine[16].replace(" kcal/mol", "").trim()));
                        }

                        if (!splitedLine[17].isEmpty()) {
                            //  System.out.println("unfolding energy ncRNA: " + splitedLine[17]);
                            rnaInteraction.setUnfoldingEnergyNcrna(new BigDecimal(splitedLine[17].replace(" kcal/mol", "").trim()));
                        }

                        System.out.println(rnaInteraction.toString2());

                        if (gene != null && !splitedLine[15].isEmpty() && !splitedLine[16].isEmpty() && !splitedLine[17].isEmpty()) {
                            rnaInteraction.setTrn(this.checkTfTg(gene));
                            rnaInteractionDAO.save(rnaInteraction);
                        } else {
                            System.out.println("GENE NULL");
                        }
                    }

                    numLine++;
                } else {
                    break;
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

    public boolean checkTfTg(Gene mRNA) {
        boolean regulated = false;

        GeneDAO geneDAO = new GeneDAO();
        // System.out.println("Gene " + mRNA.getLocusTag() + " is part of the predicted TRN? ");
        mRNA = geneDAO.findIfExperimentalTfTg(mRNA.getId());
        if (mRNA == null) {
            //System.out.println("No!");
        } else {
            //System.out.println("Yes!");
            regulated = true;
        }
        return regulated;
    }
}
