/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.PromoterRegionDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.PromoterRegion;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 *
 * @author mariana
 */
public class PromoterRegionPrediction {

    public static void main(String[] args) throws IOException {
        GeneDAO geneDAO = new GeneDAO();
        //Gene gene14 = geneDAO.findByGenomeLocusTagProteinId(123, "");
        //Gene gene10 = geneDAO.findByGenomeLocusTagProteinId(123, "");
        //Gene gene15 = geneDAO.findByGenomeLocusTagProteinId(123, "");
        //Gene gene16 = geneDAO.findByGenomeLocusTagProteinId(123, "");
        Gene gene = geneDAO.findById(2136670);
        //  System.out.println("GENE: " + gene.toString());

        PromoterRegionPrediction predictor = new PromoterRegionPrediction();
        predictor.predict(gene, gene.getGenome().getFnaFile());

    }

    public void predict(Gene gene, String genomicfnaFile) throws IOException {

        BigInteger cdsStart = gene.getStartPosition();
        genomicfnaFile = genomicfnaFile.replace("_cds_from_genomic.fna", "_genomic.fna");

        //System.out.println("cdsStart = " + cdsStart);
        // System.out.println("genomicfnaFile = " + genomicfnaFile);
        //System.out.println("GENE *: " + gene.toString());
        //System.out.println("\n\n");

        // ***************************************************************
        // *****DISCOVER THE NUM OF CHARACTERS OF THE .FNA FILE LINES*****
        // ***************************************************************
        BigInteger numCharacterLine;
        try (Stream<String> lines = Files.lines(Paths.get(genomicfnaFile))) {
            String selectedLine = lines.skip(2).findFirst().get();
            // System.out.println("\n*********NUM OF CHARACTERS OF THE .FNA FILE LINES***************");
            //  System.out.println(selectedLine);
            numCharacterLine = BigInteger.valueOf(selectedLine.length());
            // System.out.println("numCharacterLine ===>>>> " + numCharacterLine);
            // System.out.println("********************************");

        }

        // *******************************************************************************
        // ****CALCULATE THE START AND END POSITION OF THE PROMOTER REGIONS [-20,+560]****
        // *******************************************************************************
        BigInteger promoterStart;
        BigInteger promoterEnd;

        //FIND THE [-560,+20] VALUES. 
        if (gene.getOrientation().equals("reverse")) {
            //System.out.println("REVERSE = [-20,+560]");
            promoterEnd = cdsStart.subtract(new BigInteger("19"));
            promoterStart = cdsStart.add(new BigInteger("560"));
        } else {
            // System.out.println("forward = [-560,+20]");
            promoterStart = cdsStart.subtract(new BigInteger("560"));
            promoterEnd = cdsStart.add(new BigInteger("19"));
        }

        // System.out.println("promoterStart -> " + promoterStart);
        // System.out.println("promoterEnd -> " + promoterEnd);
        /*
        AQUI TEM QUE CONFERIR ONDE TERMINA O GENE ANTERIOR. 
        OU VER SE NO GENOMA EM QUESTAO TEM ALGUM GENE NESTE GENOMA
        QUE NÀO SEJA ESTE QUE ESTAMOS USANDO E QUE TERMINA DENTRO 
        DESSE RANGE [-560,+20]

        testa os 4 códigos e se algum deles retornar alguma coisa usar. 
        
         */
        //***************************************************************************
        //************CHECK IF THE NEXT/PREVIOUS GENE IS THE SAME GENOME*************
        //***************************************************************************
        GeneDAO geneDAO = new GeneDAO();

        Gene geneInPromoterRegion = null;
        Integer missingPromoterRegionSize = null;

        if (gene.getOrientation().equals("forward")) {
            //   System.out.println("----FORWARD!");
            geneInPromoterRegion = geneDAO.findById(gene.getId() - 1);
            if ((geneInPromoterRegion != null)) {

                //   System.out.println(geneInPromoterRegion.getId() + " " + geneInPromoterRegion.getLocusTag());
                if (!Objects.equals(geneInPromoterRegion.getGenome().getId(), gene.getGenome().getId())) {
                    // System.out.println("nao estao no mesmo genoma :)");
                    //é o primeiro gene do genoma
                    //System.out.println("É O PRIMEIRO GENE DO GENOMA.");

                    //System.out.println("gene.getEndPosition().compareTo(gene.getStartPosition())==1  => "
                    //        +gene.getEndPosition().compareTo(gene.getStartPosition())
                    //           );
                    if (gene.getEndPosition().compareTo(gene.getStartPosition()) == -1) {
                        //System.out.println("gene.getEndPosition().compareTo(gene.getStartPosition())==1  => "
                        //       +gene.getEndPosition().compareTo(gene.getStartPosition()));
                        geneInPromoterRegion = null;
                        promoterEnd = gene.getGenome().getSize();
                        // System.out.println("PROMOTER END: "+promoterEnd);
                    }

                } else {
                    //System.out.println("estão no mesmo genoma");

                }

            } else {
                // System.out.println("É O PRIMEIRO DO GENOMA DO DB");

                //não tem gene anterior
            }
        } else {
            // System.out.println("----REVERSE!");
            geneInPromoterRegion = geneDAO.findById(gene.getId() + 1);
            if ((geneInPromoterRegion != null)) {
                //   System.out.println(geneInPromoterRegion.getId() + " " + geneInPromoterRegion.getLocusTag());
                //   System.out.println("---geneInPromoterRegion.getGenome().getId() " + geneInPromoterRegion.getGenome().getId());
                //  System.out.println("---gene.getGenome().getId() " + gene.getGenome().getId());

                if (!Objects.equals(geneInPromoterRegion.getGenome().getId(), gene.getGenome().getId())) {
                    //          System.out.println("nao estao no mesmo genoma :)");
                    geneInPromoterRegion = null;
                    //é o ultimo gene do genoma
                } else {
                    //         System.out.println("estão no mesmo genoma");
                }
            } else {
                //  System.out.println("É O ULTIMO GENOMA DO DB");
                //não tem gene posterior
            }
        }

        //  *************************************************************************
        //  *************CHECK IF THERE IS A GENE IN THE PROMOTER REGION*************
        //  *************************************************************************
        // VERIFICA SE A O INICIO / FINAL DO geneInPromoterRegion ESTÁ DENTRO DA REGIÃO PROMOTORA. 
        //SE O GENE DE INTERESSE FOR FORWARD
        //se nao for um dos genes das pontas
        if (geneInPromoterRegion != null) {
            //     System.out.println("geneInPromoterRegion *: " + geneInPromoterRegion.toString());

            if (gene.getOrientation().equals("forward")) {
                //    System.out.println("----FORWARD!");

                //SE O GENE NA REGIAO PROMOTORA FOR FORWARD // -> *-> 
                if (geneInPromoterRegion.getOrientation().equals("forward")) {
                    //se o inicio do gene na regiao promotora está dentro da regiao promota   
                    //     System.out.println("geneInPromoterRegion.getEndPosition().intValue() " + geneInPromoterRegion.getEndPosition().intValue());
                    //    System.out.println("promoterEnd.intValue() " + promoterEnd.intValue());
                    //    System.out.println("geneInPromoterRegion.getEndPosition().intValue() < promoterEnd.intValue() " + (geneInPromoterRegion.getEndPosition().intValue() < promoterEnd.intValue()));

                    //      System.out.println("geneInPromoterRegion.getEndPosition().intValue() " + geneInPromoterRegion.getEndPosition().intValue());
                    //      System.out.println("promoterStart.intValue() " + promoterStart.intValue());
                    //      System.out.println("(geneInPromoterRegion.getEndPosition().intValue() > promoterStart.intValue()) " + (geneInPromoterRegion.getEndPosition().intValue() > promoterStart.intValue()));
                    //     System.out.println("os dois: " + ((geneInPromoterRegion.getEndPosition().intValue() < promoterEnd.intValue())
                    //            && (geneInPromoterRegion.getEndPosition().intValue() > promoterStart.intValue())));
                    if ((geneInPromoterRegion.getEndPosition().intValue() < promoterEnd.intValue())
                            && (geneInPromoterRegion.getEndPosition().intValue() > promoterStart.intValue())) {

                        //         System.out.println("ESTÁ NA REGIÃO PROMOTORA :D forward forward");
                        promoterStart = geneInPromoterRegion.getEndPosition().add(BigInteger.ONE);
                    }

                    //SE O GENE NA REGIAO PROMOTORA FOR REVERSE //<- *->
                } else {
//                    //se o final do gene na regiao promotora está dentro da regiao promota    
//                    if ((geneInPromoterRegion.getStartPosition().intValue() < promoterEnd.intValue())
//                            && (geneInPromoterRegion.getStartPosition().intValue() > promoterStart.intValue())) {
//
//                       System.out.println("ESTÁ NA REGIÃO PROMOTORA :D forward reverse");
//                        promoterStart = geneInPromoterRegion.getStartPosition().add(BigInteger.ONE);
//
//                    }
                    geneInPromoterRegion = null;

                }

                //SE O GENE DE INTERESSE FOR REVERSE    
            } else {
                //   System.out.println("----REVERSE!");

                //SE O GENE NA REGIAO PROMOTORA FOR FORWARD // <-* ->
                if (geneInPromoterRegion.getOrientation().equals("forward")) {
//                    //se o inicio do gene na regiao promotora está dentro da regiao promota   
//                    if ((geneInPromoterRegion.getStartPosition().intValue() > promoterEnd.intValue())
//                            && (geneInPromoterRegion.getStartPosition().intValue() < promoterStart.intValue())) {
//
//                       System.out.println("ESTÁ NA REGIÃO PROMOTORA :D REVERSE forward");
//                        promoterStart = geneInPromoterRegion.getStartPosition().subtract(BigInteger.ONE);
//                    }
//
//                    //SE O GENE NA REGIAO PROMOTORA FOR REVERSE // <-* <-
                    geneInPromoterRegion = null;
                } else {
                    //se o final do gene na regiao promotora está dentro da regiao promota   
                    if ((geneInPromoterRegion.getEndPosition().intValue() > promoterEnd.intValue())
                            && (geneInPromoterRegion.getEndPosition().intValue() < promoterStart.intValue())) {

                        //    System.out.println("ESTÁ NA REGIÃO PROMOTORA :D REVERSE REVERSE");
                        promoterStart = geneInPromoterRegion.getEndPosition().subtract(BigInteger.ONE);
                    }
                }
            }
        }

        //    System.out.println("\n\n");
        //System.out.println("\n\n");
        // *****************************************************************
        // *********DISCOVER IF THERE IS A MISSING PROMOTER REGION**********
        // *****************************************************************
        String missingPromoterRegion = "";
        BigInteger missingRegionStartInFile = BigInteger.ZERO;
        BigInteger missingRegionEndInFile = BigInteger.ZERO;

        // System.out.println("PROMOTER START: " + promoterStart);
        // System.out.println("PROMOTER END: " + promoterEnd);
        //if the gene is forward, is the first of the genome and it is missing nucleotides to fill the promoter region. 
        //set the size of the missing region and set the promoter start to zero
        if (promoterStart.intValue() < 0) {
            //      System.out.println("promoterStart.intValue() < 0");
            missingPromoterRegionSize = promoterStart.intValue() * -1;
            promoterStart = BigInteger.ZERO;

            //      System.out.println("missingPromoterRegionSize " + missingPromoterRegionSize);
            //     System.out.println("promoterStart " + promoterStart);
            missingRegionStartInFile = gene.getGenome().getSize().subtract(new BigInteger("" + missingPromoterRegionSize.toString()));
            missingRegionEndInFile = gene.getGenome().getSize();

            //CALCULA A PRIMEIRA REGIÃO PEGANDO NO FINAL DO GENOMA PEGANDO OS ULTIMOS X NUCLEOTIDEOS. 
        }

        //if the gene is reverse, is the last of the genome and it is missing nucleotides to fill the promoter region. 
        //set the size of the promoter region and set the promoter end to the end of the genome
        //  System.out.println("promoterStart.intValue() =>  " + promoterStart.intValue());
        //   System.out.println("gene.getGenome().getSize().intValue() =>  " + gene.getGenome().getSize().intValue());
        //    System.out.println("promoterStart.compareTo(gene.getGenome().getSize())==1 " + (promoterStart.compareTo(gene.getGenome().getSize()) == 1));
        //System.out.println("gene.getGenome().getSize() " + gene.getGenome().getSize());

        if (promoterStart.compareTo(gene.getGenome().getSize()) == 1) {
            //if (promoterStart.intValue() > gene.getGenome().getSize().intValue()) {
            //  System.out.println("promoterStart.intValue() > gene.getGenome().getSize().intValue() ENTROU");
            missingPromoterRegionSize = promoterStart.intValue() - gene.getGenome().getSize().intValue();
            promoterStart = gene.getGenome().getSize();

            //     System.out.println("missingPromoterRegionSize " + missingPromoterRegionSize);
            //    System.out.println("promoterEnd " + promoterEnd);
            missingRegionStartInFile = BigInteger.ZERO;
            missingRegionEndInFile = new BigInteger("" + missingPromoterRegionSize);
            //CALCULA A PRIMEIRA REGIÃO PEGANDO DO INICIO DO GENOMA OS PRIMEIROS X NUCLEOTIDEOS. 
        } else {
            //     System.out.println("NAO ENTROU");
        }

        //  ****************************************************************
        //  *************SET PROMOTER START AND END IN THE FILE*************
        //  ****************************************************************
        BigInteger promoterStartInFile = BigInteger.ZERO;
        BigInteger promoterEndInFile = BigInteger.ZERO;

        if (promoterStart.compareTo(promoterEnd) == 1) {
            promoterStartInFile = promoterEnd;
            promoterEndInFile = promoterStart;
        } else {
            promoterStartInFile = promoterStart;
            promoterEndInFile = promoterEnd;
        }

        //***********************************************************************
        //***CHECK IF THERE IS A GENE IN THE MISSSING PROMOTER REGION************
        //***********************************************************************
        //get the previous gene
        Gene geneInMissingRegion = null;
        if (missingPromoterRegionSize != null) {

            //get the first gene of the genome
            Integer geneInMissingRegionId = null;

            //if the gene is the first of the genome -> it is forward
            //the geneInMissingRegion is the last of the genome
            if (gene.getOrientation().equals("forward")) {
                //  System.out.println("----FORWARD!");
                geneInMissingRegionId = geneDAO.findByGenomeMax(gene.getGenome().getId());
                //  System.out.println("1");
                geneInMissingRegion = geneDAO.findById(geneInMissingRegionId);
                // System.out.println("2");

                if (geneInMissingRegion != null) {
                    //    System.out.println("3");
                    //SE O GENE NA REGIAO PROMOTORA FOR FORWARD // -> *-> 
                    if (geneInMissingRegion.getOrientation().equals("forward")) {
                        //se o inicio do gene na regiao promotora está dentro da regiao promota   
                        if (geneInMissingRegion.getEndPosition().intValue() > missingRegionStartInFile.intValue()) {

                            //   System.out.println("ESTÁ NA REGIÃO PROMOTORA :D forward forward");
                            missingRegionStartInFile = geneInMissingRegion.getEndPosition().add(BigInteger.ONE);
                        }

                        //SE O GENE NA REGIAO PROMOTORA FOR REVERSE //<- *->
                    } else {
//                        //se o final do gene na regiao promotora está dentro da regiao promota    
//                        if (geneInMissingRegion.getStartPosition().intValue() > missingRegionStartInFile.intValue()) {
//
//                           System.out.println("ESTÁ NA REGIÃO PROMOTORA :D forward reverse");
//                            missingRegionStartInFile = geneInMissingRegion.getStartPosition().add(BigInteger.ONE);
//
//                        }
                        geneInMissingRegion = null;

                    }
                }

            } else {
                //if the gene is the last of the genome -> it is reverse
                //the geneInMissingRegion is the fist of the genome    
                //System.out.println("----REVERSE!");

                geneInMissingRegionId = geneDAO.findByGenomeMin(gene.getGenome().getId());
                // System.out.println("4");
                geneInMissingRegion = geneDAO.findById(geneInMissingRegionId);
                // System.out.println("5");
                // System.out.println(geneInMissingRegion);

                if (geneInMissingRegion != null) {
                    //SE O GENE NA REGIAO PROMOTORA FOR FORWARD // <-* ->
                    //System.out.println("geneInMissingRegion not null");
                    if (geneInMissingRegion.getOrientation().equals("forward")) {
//                        //se o inicio do gene na regiao promotora está dentro da regiao promota   
//                        if (geneInMissingRegion.getStartPosition().intValue() < missingRegionEndInFile.intValue()) {
//
//                           System.out.println("ESTÁ NA REGIÃO PROMOTORA :D REVERSE forward");
//                            missingRegionEndInFile = geneInMissingRegion.getStartPosition().subtract(BigInteger.ONE);
//                        }
//
//                        //SE O GENE NA REGIAO PROMOTORA FOR REVERSE // <-* <-
                        geneInMissingRegion = null;
                    } else {
                        //se o final do gene na regiao promotora está dentro da regiao promota   
                        if (geneInMissingRegion.getEndPosition().intValue() < missingRegionEndInFile.intValue()) {

                            //    System.out.println("ESTÁ NA REGIÃO PROMOTORA :D REVERSE REVERSE");
                            missingRegionEndInFile = geneInMissingRegion.getEndPosition().subtract(BigInteger.ONE);
                        }
                    }
                }
            }

            //---------
            //check if this gene is in the missing promoter region
            //if there is: set the new start of the missing region :) 
        }

        //*************************************************************
        //***********CALCULATE THE MISSING PROMOTER REGION*************
        //*************************************************************
        if (missingRegionEndInFile.compareTo(BigInteger.ZERO) != 0) {
            //System.out.println("*******TEM MISSING REGION");

            BigInteger characPromoterStartMissingRegion = missingRegionStartInFile.remainder(numCharacterLine);
            BigInteger characPromoterEndMissingRegion = missingRegionEndInFile.remainder(numCharacterLine);

            //System.out.println("characPromoterStartMissingRegion " + characPromoterStartMissingRegion + " = " + missingRegionStartInFile + " % " + numCharacterLine);
            //System.out.println("characPromoterEndMissingRegion " + characPromoterEndMissingRegion + " = " + missingRegionEndInFile + " % " + numCharacterLine);
            BigInteger lineMissingRegionPromoterStart = BigInteger.ZERO;
            BigInteger lineMissingRegionPromoterEnd = BigInteger.ZERO;

            if (characPromoterStartMissingRegion.compareTo(BigInteger.ZERO) == 0) {
                lineMissingRegionPromoterStart = missingRegionStartInFile.divide(numCharacterLine);
            } else {
                lineMissingRegionPromoterStart = missingRegionStartInFile.divide(numCharacterLine).add(BigInteger.ONE);
            }

            lineMissingRegionPromoterEnd = missingRegionEndInFile.divide(numCharacterLine).add(BigInteger.ONE);

            //System.out.println("linePromoterStart  " + lineMissingRegionPromoterStart + " = (" + missingRegionStartInFile + " / " + numCharacterLine + ") + 1");
            //System.out.println("linePromoterEnd " + lineMissingRegionPromoterEnd + " = (" + missingRegionEndInFile + " / " + numCharacterLine + ") + 1");
            missingPromoterRegion = "";

            if (lineMissingRegionPromoterStart.compareTo(BigInteger.ZERO) == 0) {
                try (Stream<String> linesV = Files.lines(Paths.get(genomicfnaFile))) {
                    String selectedLineV = linesV.skip(lineMissingRegionPromoterStart.intValue()).findFirst().orElse(null);
                    if (selectedLineV != null && selectedLineV.contains(">")) {
                        lineMissingRegionPromoterStart = lineMissingRegionPromoterStart.add(BigInteger.ONE);
                    }
                }
            }

            for (int j = lineMissingRegionPromoterStart.intValue(); j <= lineMissingRegionPromoterEnd.intValue(); j++) {

                try (Stream<String> lines = Files.lines(Paths.get(genomicfnaFile))) {
                    String selectedLine = lines.skip(j).findFirst().orElse(null);

                    if (selectedLine != null) {

                        if (lineMissingRegionPromoterStart.intValue() == lineMissingRegionPromoterEnd.intValue()) {
                            // if (characPromoterStartMissingRegion.intValue() == 0) {
                            selectedLine = selectedLine.substring(characPromoterStartMissingRegion.intValue(), characPromoterEndMissingRegion.intValue());
                            // } else {
                            //     selectedLine = selectedLine.substring(characPromoterStartMissingRegion.intValue() - 1, characPromoterEndMissingRegion.intValue());
                            // }
                            missingPromoterRegion = missingPromoterRegion + selectedLine;
                            break;
                        }

                        if (j == lineMissingRegionPromoterStart.intValue()) {
                            //System.out.println("selectedLine " + selectedLine);
                            if (characPromoterStartMissingRegion.intValue() == 0) {
                                //     System.out.println("É ZERO");
                                selectedLine = selectedLine.substring(numCharacterLine.intValue() - 1);
                            } else {
                                selectedLine = selectedLine.substring(characPromoterStartMissingRegion.intValue() - 1);
                            }
                        } else if (j == lineMissingRegionPromoterEnd.intValue()) {
                            selectedLine = selectedLine.substring(0, characPromoterEndMissingRegion.intValue());
                        }
                        missingPromoterRegion = missingPromoterRegion + selectedLine;
                        // System.out.println(selectedLine);
                    }
                }

            }

            //   System.out.println("MISSSING PROMOTER REGION: " + missingPromoterRegion);
        }
        // System.out.println("\n\n");

        //**************************************************************
        //*******FIND WHERE IN THE LINE THE PROMOTER WILL START*********
        //**************************************************************
        //REMAINDER => %
        //   promoterStartInFile = new BigInteger("195040");
        // System.out.println("PROMOTER START: " + promoterStart);
        // System.out.println("PROMOTER END: " + promoterEnd);
        // System.out.println("PROMOTER START: " + promoterStartInFile);
        // System.out.println("PROMOTER END: " + promoterEndInFile);
        BigInteger characPromoterStart = promoterStartInFile.remainder(numCharacterLine);
        BigInteger characPromoterEnd = promoterEndInFile.remainder(numCharacterLine);

        //    System.out.println("characPromoterStart " + characPromoterStart + " = " + promoterStartInFile + " % " + numCharacterLine);
        //    System.out.println("characPromoterEnd " + characPromoterEnd + " = " + promoterEndInFile + " % " + numCharacterLine);
        //    System.out.println("characPromoterStart " + characPromoterStart + " = " + promoterStart.intValue() + " % " + numCharacterLine.intValue() + " = " + promoterStart.intValue() % numCharacterLine.intValue());
        //    System.out.println("characPromoterEnd " + characPromoterEnd + " = " + promoterEnd + " % " + numCharacterLine);
        BigInteger linePromoterStart = BigInteger.ZERO;
        BigInteger linePromoterEnd = BigInteger.ZERO;

        //System.out.println("characPromoterStart.equals(0) " + characPromoterStart.compareTo(BigInteger.ZERO));
        //VER AQUI NA HORA DE CALCULAR O AS LINHAS, PQ ELE COMEÇA 
        //NA 1 E NAO NA ZERO PQ A ZERO É O CABEÇALHO! 
        if (characPromoterStart.compareTo(BigInteger.ZERO) == 0) {
            //        System.out.println("linePromoterInFileStart = promoterStartInFile.divide(numCharacterLine);");
            linePromoterStart = promoterStartInFile.divide(numCharacterLine);
        } else {
            // System.out.println("linePromoterStart = promoterStartInFile.divide(numCharacterLine).add(BigInteger.ONE);");
            linePromoterStart = promoterStartInFile.divide(numCharacterLine).add(BigInteger.ONE);
        }

        linePromoterEnd = promoterEndInFile.divide(numCharacterLine).add(BigInteger.ONE);

        // System.out.println("linePromoterStart  " + linePromoterStart + " = (" + promoterStartInFile + " / " + numCharacterLine + ") + 1");
        // System.out.println("linePromoterEnd " + linePromoterEnd + " = (" + promoterEndInFile + " / " + numCharacterLine + ") + 1");
        String promoterRegion = "";

        if (linePromoterStart.compareTo(BigInteger.ZERO) == 0) {
            try (Stream<String> linesV = Files.lines(Paths.get(genomicfnaFile))) {
                String selectedLineV = linesV.skip(linePromoterStart.intValue()).findFirst().orElse(null);

                if (selectedLineV != null && selectedLineV.contains(">")) {
                    linePromoterStart = linePromoterStart.add(BigInteger.ONE);
                }
            }
        }

        //**************************************************************************
        //*******WALKS THROUGH THE PROMOTER LINES IN THE GENOMIC .FNA FILE,*********
        //*********READS IT AND SAVE THE PROMOTER REGION IN THE VARIABLE************
        //**************************************************************************
        //System.out.println("genomicfnaFile " + genomicfnaFile);
        for (int j = linePromoterStart.intValue(); j <= linePromoterEnd.intValue(); j++) {
            // System.out.println("j " + j);
            try (Stream<String> lines = Files.lines(Paths.get(genomicfnaFile))) {

                String selectedLine = lines.skip(j).findFirst().orElse(null);
                //  System.out.println(selectedLine);
                if (selectedLine != null) {
                    if (linePromoterStart.intValue() == linePromoterEnd.intValue()) {

                        selectedLine = selectedLine.substring(characPromoterStart.intValue(), characPromoterEnd.intValue());

                        promoterRegion = promoterRegion + selectedLine;
                        break;
                    }
                    if (j == linePromoterStart.intValue()) {
                        if (characPromoterStart.intValue() == 0) {
                            //    System.out.println("É ZERO");
                            //    System.out.println("selectedLine " + selectedLine);
                            //    System.out.println("selectedLine lenght " + selectedLine.length());
                            //    System.out.println("numCharacterLine.intValue() - 1 " + (numCharacterLine.intValue() - 1));
                            selectedLine = selectedLine.substring(numCharacterLine.intValue() - 1);
                        } else {
                            selectedLine = selectedLine.substring(characPromoterStart.intValue() - 1);
                        }
                    } else if (j == linePromoterEnd.intValue()) {
                        //   System.out.println("selectedLine " + selectedLine);
                        selectedLine = selectedLine.substring(0, characPromoterEnd.intValue());
                    }
                    promoterRegion = promoterRegion + selectedLine;
                    //    System.out.println(selectedLine);
                }
            }

        }

        //System.out.println("PROMOTER REGION: " + promoterRegion);
        //**************************************************************
        //********CHECK IF THERE IS A GENE IN THE MISSING REGION********
        //**************************************************************
        //fazer e atualizar o inicio da missing region se for o caso :D 
        //* ********************************************
        //* IF THERE IS MISSING REGION, ADD THE MISSING 
        //* REGION TO THE PROMOTER REGION 
        //* *******************************************
        if (!missingPromoterRegion.isEmpty()) {
            //System.out.println("tem missing region :D ");
            if (missingRegionEndInFile.compareTo(gene.getGenome().getSize()) == 0) {
                //System.out.println("É O primeiro GENE DO GENOMA");
                promoterRegion = promoterRegion + missingPromoterRegion;
                promoterStart = missingRegionStartInFile;

            } else {

                //System.out.println("É O último GENE DO GENOMA");
                promoterRegion = missingPromoterRegion + promoterRegion;
                promoterStart = missingRegionEndInFile;
            }
            //System.out.println("NEW PROMOTER REGION: " + promoterRegion);
            //System.out.println("NEW promoterStart " + promoterStart);
            //System.out.println("NEW promoterEnd " + promoterEnd);

        } else {
            //System.out.println("NAO TEM MISSING REGION");
        }

        //* ********************************
        //* **CREATE PROMOTER REGION FILES**
        //* ********************************
        //dentro de target/nomedogenoma/gene-promoter-region.fasta
        String promoterFolder = gene.getGenome().getFaaFile();
        String splited[] = promoterFolder.split("\\/");
        promoterFolder = promoterFolder.replace(splited[splited.length - 1], "");
        String genomeName = splited[splited.length - 1].replace("_protein.faa", "");
        promoterFolder = promoterFolder + genomeName + "-promoter-region";

        //System.out.println("\n\n--------------------------------------");
        //System.out.println(promoterFolder);
        //System.out.println("---------------------------------------");
        File promoterDirectory = new File(promoterFolder);
        if (!promoterDirectory.exists()) {
            if (promoterDirectory.mkdir()) {
                // System.out.println("Directory is created!");
            } else {
                //  System.out.println("Failed to create directory!");
            }
        }
        FileWriter fileWriterFna = null;
        PrintWriter printWriterFna = null;

        String promoterFile = promoterFolder + "/" + gene.getLocusTag() + "-promoter-region.fna";
        try {

            fileWriterFna = new FileWriter(promoterFile);
            printWriterFna = new PrintWriter(fileWriterFna);
            printWriterFna.println(">" + gene.getLocusTag() + "-promoter-region");
            printWriterFna.println(promoterRegion.toUpperCase());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (fileWriterFna != null) {
                    fileWriterFna.close();
                }

                if (printWriterFna != null) {
                    printWriterFna.close();
                }
            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }

        //* ********************************************
        //* **SAVE THE PROMOTER REGION IN THE DATABASE** 
        //* ********************************************
        PromoterRegionDAO promoterRegionDAO = new PromoterRegionDAO();
        PromoterRegion promoterRegionObj = new PromoterRegion();
        promoterRegionObj.setInitialPosition(promoterStart);
        promoterRegionObj.setEndPosition(promoterEnd);
        promoterRegionObj.setSequence(promoterRegion);
        //System.out.println(promoterRegion);
        promoterRegionObj.setFile(promoterFile);

        promoterRegionObj = (PromoterRegion) promoterRegionDAO.save(promoterRegionObj);
        gene.setPromoterRegion(promoterRegionObj);
        gene.setSearchSpace(promoterRegion.length());
        geneDAO.update(gene);

    }

}
