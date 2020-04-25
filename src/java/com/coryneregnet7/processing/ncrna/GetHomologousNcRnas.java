/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.ncrna;

import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.processing.HomologyDetection;
import com.coryneregnet7.processing.statistics.EvalueToPvalue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariana
 */
public class GetHomologousNcRnas {
    public static void main(String args[]) throws IOException{
        GetHomologousNcRnas GetHomologousNcRnas = new GetHomologousNcRnas();
//        Genome genome1 = new Genome();
//        Genome genome2 = new Genome();
//        
//        GenomeDAO genomeDAO = new GenomeDAO();
//        genome
        

        List<String[]> bbhs = new ArrayList<>();
        bbhs = GetHomologousNcRnas.detectHomologyNoDatabase(null, null, "", 
                "/data/home/mariana/Dropbox/Doutorado/ncRNA/glutamicum/genomes-intergenic/c-diphteriae-x-c-glutamicum-ncRNAs.out",
                "/data/home/mariana/Dropbox/Doutorado/ncRNA/glutamicum/genomes-intergenic/c-glutamicum-ncRNAs-x-c-diphtheriae.out");
        for (String[] bbh : bbhs) {
            System.out.println(bbh.toString());
        }
    }
    
       public List<String[]> detectHomologyNoDatabase(Genome genome1, Genome genome2, String transferenceFolderPath, 
                                                         String blastFileFirstSide, String blastFileOtherSide) throws IOException {

         HomologyDetection hd = new HomologyDetection();
        List<String[]> bestHitsFirstSide = hd.findBestHits(genome1, genome2, blastFileFirstSide);
        List<String[]> bestHitsOtherSide = hd.findBestHits(genome1, genome2, blastFileOtherSide);
        List<String[]> bbhs = hd.findBidirectionalBestHits(bestHitsFirstSide, bestHitsOtherSide, genome1, genome2);
        return bbhs;
    }

    public List<String[]> findBestHits(Genome model, Genome target, String blastFile) throws FileNotFoundException, IOException {
        List<String[]> bestHits = new ArrayList<>();
        BufferedReader br = null;
        FileReader fr = null;
        Boolean downstreamLineContains = false;

        fr = new FileReader(blastFile);
        br = new BufferedReader(fr);

        String sCurrentLine;

        while ((sCurrentLine = br.readLine()) != null) {

            System.out.println(sCurrentLine);
            //VERIFY IF THE IT IS THE BEST HIT (IF THE LINE DOES NOT START WITH # AND THE DOWNSTREAM LINE DOES.
            if (!sCurrentLine.startsWith("#") && downstreamLineContains) {
                String[] line = sCurrentLine.split("\t");
                String[] foundTranscriptionFactors = new String[3];

                foundTranscriptionFactors[0] = line[0];//QUERY/ POSSIBLE TF (TRANSCRIPTION FACTOR)
                foundTranscriptionFactors[1] = line[1];//SUBJECT/POSSIBLE HTF (HOMOLOGOUS TRANSCRIPTION FACTOR)
                foundTranscriptionFactors[2] = line[10];//E-VALUE/POSSIBLE HTF (HOMOLOGOUS TRANSCRIPTION FACTOR)

                System.out.println("---QUERY/TF " + foundTranscriptionFactors[0]);
                System.out.println("---SUBJECT/HTF " + foundTranscriptionFactors[1]);
                System.out.println("---EVALUE " + foundTranscriptionFactors[2]);
                //check if the found protein is the model organisms TF list
                bestHits.add(foundTranscriptionFactors);

            }

            //VERIFY IF THIS LINE STARTS WITH #
            if (sCurrentLine.startsWith("#")) {
                downstreamLineContains = true;
            } else {
                downstreamLineContains = false;
            }
        }

        // System.out.println("found " + bestHits.size() + " best hits");
        return bestHits;

    }

    public List<String[]> findBidirectionalBestHits(List<String[]> bestHitsFirstSide, List<String[]> bestHitsOtherSide, Genome templateGenome, Genome targetGenome) {
        // System.out.println("\n\n");
        List<String[]> bbhs = new ArrayList<>();
        EvalueToPvalue evalueToPvalue = new EvalueToPvalue();
        String[] bestBlastHit = new String[4];

        for (int i = 0; i < bestHitsFirstSide.size(); i++) {
            for (int j = 0; j < bestHitsOtherSide.size(); j++) {

                if (bestHitsFirstSide.get(i)[0].equals(bestHitsOtherSide.get(j)[1])
                        && bestHitsFirstSide.get(i)[1].equals(bestHitsOtherSide.get(j)[0])) {

                    BigDecimal evalueFirstSide = new BigDecimal(bestHitsFirstSide.get(i)[2]);
                    BigDecimal evalueOtherSide = new BigDecimal(bestHitsOtherSide.get(j)[2]);

                    BigDecimal evalue = BigDecimal.ZERO;
                    BigDecimal pvalue = new BigDecimal("0");

                    //System.out.println(targetGenome.toString());
//                    if (evalueFirstSide.compareTo(evalueOtherSide) == -1) {
//                        evalue = evalueOtherSide;
//                        pvalue = evalueToPvalue.convert(evalueOtherSide, new BigDecimal(templateGenome.getSearchSpace()));
//                    } else {
//                        evalue = evalueFirstSide;
//                        pvalue = evalueToPvalue.convert(evalueFirstSide, new BigDecimal(targetGenome.getSearchSpace()));
//                    }

                    bestBlastHit[0] = bestHitsFirstSide.get(i)[0];
                    bestBlastHit[1] = bestHitsFirstSide.get(i)[1];
                    bestBlastHit[2] = evalue.toString();
                    bestBlastHit[3] = pvalue.toString();

                    bbhs.add(bestBlastHit);

                    //System.out.println("[0]= " + bestBlastHit[0] + "\t[1]= " + bestBlastHit[1] + "\t[2]= " + bestBlastHit[2] + "\t[3]= " + bestBlastHit[3]);
                    bestBlastHit = new String[4];
                    break;
                }

            }
        }
        //System.out.println("\nbbhs: " + bbhs.size());

        return bbhs;

    }

}
