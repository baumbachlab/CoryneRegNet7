/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.ncrna;

import com.coryneregnet7.dao.RnaInteractionDAO;
import com.coryneregnet7.dao.SmallRnaDAO;
import com.coryneregnet7.model.RnaInteraction;
import com.coryneregnet7.model.SmallRna;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author mariana
 */
public class DeleteInteractions {

    public static void main(String[] args) {

        DeleteInteractions di = new DeleteInteractions();
        di.deleteForBSRD();
    }

    public void deleteForGlutamicum() {
        BufferedReader brRnaz = null;
        FileReader frRnaz = null;

        BufferedReader brRnadetect = null;
        FileReader frRnadetect = null;

        try {

            frRnadetect = new FileReader("/data/home/mariana/Downloads/RNAdetect-master-37a/RNAdetect-master/RNAdetect/example/results-rnadetect.txt");
            brRnadetect = new BufferedReader(frRnadetect);

            frRnaz = new FileReader("/data/home/mariana/Dropbox/Doutorado/ncRNA/six-strains-analysis/srna-glutamicum-rnaz/glutamicum/result.txt");
            brRnaz = new BufferedReader(frRnaz);

            String sCurrentLine;

            ArrayList<String> rnadetect = new ArrayList<>();
            ArrayList<String> rnaz = new ArrayList<>();
            ArrayList<String> onlyRnaz = new ArrayList<>();
            Set<String> functionalRna = new HashSet<String>();

            ArrayList<String> rnas = new ArrayList<>();
            while ((sCurrentLine = brRnaz.readLine()) != null) {

                rnaz.add(sCurrentLine.replace(".fa.sorted.out.rnaz: Prediction: RNA", "").replace("output-", ""));
                functionalRna.add(sCurrentLine.replace(".fa.sorted.out.rnaz: Prediction: RNA", "").replace("output-", ""));
            }

            int rnazUnique = 0;
            int rnadetectUnique = 0;
            int both = 0;

            String functionalEvidence = "";
            while ((sCurrentLine = brRnadetect.readLine()) != null) {
                String rna = sCurrentLine.replace(".fa.sorted.rnadetect", "").replace("output-", "") + " RNAdetect";
                // System.out.print(sCurrentLine.replace(".fa.sorted.rnadetect", "").replace("output-", "") + " rnaDetect");
                rnadetect.add(sCurrentLine.replace(".fa.sorted.rnadetect", "").replace("output-", ""));
                functionalRna.add(sCurrentLine.replace(".fa.sorted.rnadetect", "").replace("output-", ""));
                if (rnaz.contains(sCurrentLine.replace(".fa.sorted.rnadetect", "").replace("output-", ""))) {
                    rna = rna + "+RNAz";
                    //     System.out.println(" rnaz");
                    both++;
                } else {
                    //    System.out.println(" -");
                    rna = rna + " -";
                    rnadetectUnique++;
                }

                if (!rnas.contains(rna)) {
                    rnas.add(rna);
                }
            }

            for (String string : rnaz) {
                if (!rnadetect.contains(string)) {
                    // System.out.println(string + " - rnaz");
                    String rna = string + " - RNAz";
                    if (!rnas.contains(rna)) {
                        rnas.add(rna);
                    }
                    rnazUnique++;
                }

            }

            System.out.println("rnaz " + rnaz.size());
            System.out.println("rnadetect " + rnadetect.size());
            System.out.println("rnadetectUnique " + rnadetectUnique);
            System.out.println("rnazUnique " + rnazUnique);
            System.out.println("both " + both);
            System.out.println("functionalRna " + functionalRna.size());
            System.out.println("rnas " + rnas.size());

////            SET AS FUNCTIONAL
            for (String string : rnas) {
                SmallRnaDAO smallRnaDAO = new SmallRnaDAO();
                String rna[] = string.split("\\s+");
                System.out.println(rna[0]);
                String fe = string.replace(rna[0] + " ", "");
                fe = fe.replace("-", "").trim();
                System.out.println(fe);
                SmallRna sRna = smallRnaDAO.findByLocusTag(rna[0]);
                sRna.setEvidenceFunctional(fe);
                System.out.println(sRna);
//                sRna.setFunctionalRna(Boolean.TRUE);
                smallRnaDAO.update(sRna);
            }
//DELETE INTERACTIONS       
//            RnaInteractionDAO rnaInteractionDAO = new RnaInteractionDAO();
//            RnaInteraction rnaInteraction = new RnaInteraction();
//            List<RnaInteraction> interactions = rnaInteractionDAO.listAll();
////            
//            for (RnaInteraction interaction : interactions) {
//                
//                if(interaction.getSrna().getEvidence().equals("experimental")){
//                    System.out.println(interaction.getSrna().getFunctionalRna());
//                    System.out.println(interaction);
////                    System.out.println(interaction.getSrna().getLocusTag()+"\t"+interaction.getMrna().getLocusTag()
////                            +"\t"+interaction.getSrna().getFunctionalRna());
//                    if(interaction.getSrna().getFunctionalRna()==null){
//                        rnaInteractionDAO.delete(interaction);
//                    }
//                }
//            }
        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (brRnadetect != null) {
                    brRnadetect.close();
                }

                if (frRnadetect != null) {
                    frRnadetect.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

    }

    public void deleteForBSRD() {
        BufferedReader brRnaz = null;
        FileReader frRnaz = null;

        BufferedReader brRnadetect = null;
        FileReader frRnadetect = null;

        String[] notInRnaDetect = {"scef1963.1",
            "scef2209.1",
            "scgl1130.1",
            "scjk807.1"
        };

        ArrayList<String> notInRnaDetectArray = new ArrayList<String>(Arrays.asList(notInRnaDetect));

        String[] notInRnaz = {"scdi923.1",
            "scdi220.1",
            "scdi510.1",
            "scef1673.1",
            "scef2043.1",
            "scgl2611.1",
            "scgl1127.1",
            "scgl1543.1",
            "scjk2353.1",
            "scjk260.2",
            "scjk1019.1",
            "scjk830.1",
            "scjk1448.1",
            "scjk1451.1"
        };

        ArrayList<String> notInRnazArray = new ArrayList<String>(Arrays.asList(notInRnaz));

        try {

            SmallRnaDAO smallRnaDAO = new SmallRnaDAO();
            List<SmallRna> sRnaBSRD = smallRnaDAO.findByType("bsrd");

            for (SmallRna smallRna : sRnaBSRD) {
                System.out.println(smallRna);

                String functionalEvidence = "";

                Boolean notRNAd = false;
                if (notInRnaDetectArray.contains(smallRna.getLocusTag())) {
                    notRNAd = true;
                    System.out.println("NOT IN RNAdetect");
                } else {
                    System.out.println("IN RNAdetect");
                    functionalEvidence = "RNAdetect";
                }

                Boolean notRNAz = false;
                if (notInRnazArray.contains(smallRna.getLocusTag())) {
                    notRNAz = true;
                    System.out.println("NOT IN RNAz");
                } else {
                    System.out.println("IN RNAz");
                    if (functionalEvidence.isEmpty()) {
                        functionalEvidence = "RNAz";
                    } else {
                        functionalEvidence = functionalEvidence + "+RNAz";
                    }
                }
                System.out.println(functionalEvidence);
                

                smallRna.setEvidenceFunctional(functionalEvidence);
                if (functionalEvidence.isEmpty()) {
                    smallRna.setFunctionalRna(Boolean.FALSE);
                }else{
                    smallRna.setFunctionalRna(Boolean.TRUE);
                }
                smallRnaDAO.update(smallRna);
                System.out.println(smallRna);
                System.out.println("\n");
                     
            }

            //SET AS FUNCTIONAL
//            for (String string : functionalRna) {
//                SmallRnaDAO smallRnaDAO = new SmallRnaDAO();
//                SmallRna sRna = smallRnaDAO.findByLocusTag(string);
//                sRna.setFunctionalRna(Boolean.TRUE);
//                smallRnaDAO.update(sRna);
//            }
//DELETE INTERACTIONS       
//            RnaInteractionDAO rnaInteractionDAO = new RnaInteractionDAO();
//            RnaInteraction rnaInteraction = new RnaInteraction();
//            List<RnaInteraction> interactions = rnaInteractionDAO.listAll();
////            
//            for (RnaInteraction interaction : interactions) {
//                
//                if(interaction.getSrna().getEvidence().equals("experimental")){
//                    System.out.println(interaction.getSrna().getFunctionalRna());
//                    System.out.println(interaction);
////                    System.out.println(interaction.getSrna().getLocusTag()+"\t"+interaction.getMrna().getLocusTag()
////                            +"\t"+interaction.getSrna().getFunctionalRna());
//                    if(interaction.getSrna().getFunctionalRna()==null){
//                        rnaInteractionDAO.delete(interaction);
//                    }
//                }
//            }
        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (brRnadetect != null) {
                    brRnadetect.close();
                }

                if (frRnadetect != null) {
                    frRnadetect.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
    }
}
