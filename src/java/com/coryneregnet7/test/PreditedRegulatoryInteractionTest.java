/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.BindingSiteDAO;
import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.HomologousDAO;
import com.coryneregnet7.dao.OrganismDAO;
import com.coryneregnet7.model.PredictedRegulatoryInteraction;
import com.coryneregnet7.dao.PredictedRegulatoryInteractionDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.model.BindingSite;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Organism;
import com.coryneregnet7.model.RegulatoryInteraction;
import com.coryneregnet7.processing.HomologyDetection;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariana
 */
public class PreditedRegulatoryInteractionTest {

    PredictedRegulatoryInteractionDAO predictedRegulatoryInteractionDAO;

    public static void main(String[] args) {
        PreditedRegulatoryInteractionTest pt = new PreditedRegulatoryInteractionTest();
        pt.findByGenomes();
        
        //  pt.getFoundInteractions();
//        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
//        ArrayList<BindingSite> bss = new ArrayList<>();
//        ArrayList<BindingSite> bssAll = new ArrayList<>();
//        OrganismDAO orgDAO = new OrganismDAO();
//        Organism organism = orgDAO.findById(736);
//        System.out.println(organism.toString());
//        bss = (ArrayList<BindingSite>) priDAO.findPredictedBindingSites();
//        bssAll = (ArrayList<BindingSite>) priDAO.findBindingSitesOfOrganism(organism.getId());
//        ArrayList<PredictedRegulatoryInteraction> pris = (ArrayList<PredictedRegulatoryInteraction>) priDAO.findActivationsAndRepressionsPRIsWithBS();
//        ArrayList<PredictedRegulatoryInteraction> prisOrganism = (ArrayList<PredictedRegulatoryInteraction>) priDAO.findByOrganismActivationsAndRepressionsPRIsWithBS(organism.getId());
//        System.out.println("bss.size(): " + bss.size());
//        System.out.println("bssAll.size(): " + bssAll.size());
//        System.out.println("pris.size(): " + pris.size());
//        System.out.println("prisOrganism.size(): " + prisOrganism.size());
//        for (BindingSite bs : bss) {
//            System.out.println(bs.toString());
//        }
        //pt.showAllPredictedRegulationsWithBs();
//        pt.findByOrganism(153);
//
////        PredictedRegulatoryInteraction predictedRegulatoryInteraction = new PredictedRegulatoryInteraction();
////        GeneDAO geneDAO = new GeneDAO();
////        Gene gene = geneDAO.findById(2);
////        predictedRegulatoryInteraction.setHomologousTargetGene(gene);
////        predictedRegulatoryInteraction.setHomologousTranscriptionFactor(gene);
////
////        predictedRegulatoryInteraction.setHtfEvalue(new BigDecimal("0.12554"));
////        predictedRegulatoryInteraction.setHtgEvalue(new BigDecimal("0.02148"));
////
////        BindingSiteDAO bDAO = new BindingSiteDAO();
////        BindingSite bindingSite = bDAO.findById(2);
////        predictedRegulatoryInteraction.setPredictedBindingSite(bindingSite);
////
////        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
////        RegulatoryInteraction regulatoryInteraction = riDAO.findById(4);
////        predictedRegulatoryInteraction.setRegulatoryInteraction(Integer.MIN_VALUE);
////
////        pt.save(predictedRegulatoryInteraction);
////        pt.save(predictedRegulatoryInteraction);
////        pt.save(predictedRegulatoryInteraction);
////        System.out.println("\n");
////        pt.listAll();
////        System.out.println("\n");
////        predictedRegulatoryInteraction = pt.findById(17);
////        predictedRegulatoryInteraction.setHtfEvalue(new BigDecimal("0.0000000097"));
////        pt.update(predictedRegulatoryInteraction);
////        System.out.println("\n");
////        pt.listAll();
////        System.out.println("\n");
////        pt.delete(predictedRegulatoryInteraction);
////        System.out.println("\n");
    }

    public void findByGenomes() {
        GenomeDAO genomeDAO = new GenomeDAO();
        Genome templateGenomeObj = genomeDAO.findById(1227);
        Genome targetGenomeObj = genomeDAO.findById(1399);
        HomologyDetection hd = new HomologyDetection();

        HomologousDAO homologousDAO = new HomologousDAO();
        Long homologousSize = homologousDAO.bringByModelAndTargetGenomes(templateGenomeObj.getId(), targetGenomeObj.getId());
        System.out.println("homologousSize.intValue() " + homologousSize.intValue());

        PredictedRegulatoryInteractionDAO priLookUpDAO = new PredictedRegulatoryInteractionDAO();
        Long priSize = priLookUpDAO.bringNumByModelTargetGenome(templateGenomeObj.getId(), targetGenomeObj.getId());
        System.out.println("priSize " + priSize);
        
        List<PredictedRegulatoryInteraction> pris = priLookUpDAO.findNumByModelTargetGenome(templateGenomeObj.getId(), targetGenomeObj.getId());
        for (PredictedRegulatoryInteraction pri : pris) {
            System.out.println(pri.getHomologousTranscriptionFactor().getLocusTag()+"\t"
                        +pri.getRegulatoryInteraction().getCorrespondentTranscriptionFactor().getLocusTag()+"\t"
                               +pri.getHomologousTargetGene().getLocusTag()+"\t"
                               +pri.getRegulatoryInteraction().getCorrespondentTargetGene().getLocusTag()+"\t");
        }
    }

    public void showAllPredictedRegulations() {

        PreditedRegulatoryInteractionTest pt = new PreditedRegulatoryInteractionTest();
        PredictedRegulatoryInteractionDAO ptDAO = new PredictedRegulatoryInteractionDAO();
        List<PredictedRegulatoryInteraction> list = ptDAO.listAll();
        System.out.println("TF\tTG\tHTF\tHTG\tOpTG\tBs\tHtf pvalue\tHtg pvalue\tBs pvalue\tInteraction pvalue");
        for (PredictedRegulatoryInteraction predictedRegulatoryInteraction : list) {

            String opTg = "-";
            if (predictedRegulatoryInteraction.getOperonTargetGene() != null) {
                opTg = predictedRegulatoryInteraction.getOperonTargetGene().getLocusTag();
            }

            String bs = "null";
            String bsPvalue = "null";
            if (predictedRegulatoryInteraction.getPredictedBindingSite() != null) {
                bs = predictedRegulatoryInteraction.getPredictedBindingSite().getSequence();
                bsPvalue = predictedRegulatoryInteraction.getPredictedBindingSite().getPvalue().toString();
            }

            System.out.println("TF: " + predictedRegulatoryInteraction.getRegulatoryInteraction().getCorrespondentTranscriptionFactor().getLocusTag()
                    + "\tTG: " + predictedRegulatoryInteraction.getRegulatoryInteraction().getCorrespondentTargetGene().getLocusTag()
                    + "\tHTF: " + predictedRegulatoryInteraction.getHomologousTranscriptionFactor().getLocusTag()
                    + "\tHTG: " + predictedRegulatoryInteraction.getHomologousTargetGene().getLocusTag()
                    + "\tOpTG: " + opTg
                    + "\tBS: " + bs
                    + "\tHtf pvalue: " + predictedRegulatoryInteraction.getHtfPvalue()
                    + "\tHtg pvalue: " + predictedRegulatoryInteraction.getHtgPvalue()
                    + "\tBs pvalue: " + bsPvalue
                    + "\tInteraction pvalue: " + predictedRegulatoryInteraction.getInteractionPvalue()
            );

        }
    }

    public void showAllPredictedRegulationsWithBs() {

        PreditedRegulatoryInteractionTest pt = new PreditedRegulatoryInteractionTest();
        PredictedRegulatoryInteractionDAO ptDAO = new PredictedRegulatoryInteractionDAO();
        List<PredictedRegulatoryInteraction> list = ptDAO.listAll();
        System.out.println("TF\tTG\tHTF\tHTG\tOpTG\tBs\tHtf pvalue\tHtg pvalue\tBs pvalue\tInteraction pvalue");
        List<String> listWithBS = new ArrayList<>();
        for (PredictedRegulatoryInteraction predictedRegulatoryInteraction : list) {

            if (predictedRegulatoryInteraction.getPredictedBindingSite() != null) {

                String opTg = "-";
                if (predictedRegulatoryInteraction.getOperonTargetGene() != null) {
                    opTg = predictedRegulatoryInteraction.getOperonTargetGene().getLocusTag();
                }

                String bs = "null";
                String bsPvalue = "null";
                if (predictedRegulatoryInteraction.getPredictedBindingSite() != null) {
                    bs = predictedRegulatoryInteraction.getPredictedBindingSite().getSequence();
                    bsPvalue = predictedRegulatoryInteraction.getPredictedBindingSite().getPvalue().toString();
                }

                String line = "TF: " + predictedRegulatoryInteraction.getRegulatoryInteraction().getCorrespondentTranscriptionFactor().getLocusTag()
                        + "\tTG: " + predictedRegulatoryInteraction.getRegulatoryInteraction().getCorrespondentTargetGene().getLocusTag()
                        + "\tHTF: " + predictedRegulatoryInteraction.getHomologousTranscriptionFactor().getLocusTag()
                        + "\tHTG: " + predictedRegulatoryInteraction.getHomologousTargetGene().getLocusTag()
                        + "\tOpTG: " + opTg
                        + "\tBS: " + bs
                        + "\tHtf pvalue: " + predictedRegulatoryInteraction.getHtfPvalue()
                        + "\tHtg pvalue: " + predictedRegulatoryInteraction.getHtgPvalue()
                        + "\tBs pvalue: " + bsPvalue
                        + "\tInteraction pvalue: " + predictedRegulatoryInteraction.getInteractionPvalue();

                System.out.println(line);

                listWithBS.add(line);
            }

        }

        System.out.println("PREDICTED RI: " + list.size());
        System.out.println("PREDICTED RI WITH BS: " + listWithBS.size());
    }

    public List<RegulatoryInteraction> getFoundInteractions() {
        List<RegulatoryInteraction> ris = new ArrayList<RegulatoryInteraction>();
        PreditedRegulatoryInteractionTest pt = new PreditedRegulatoryInteractionTest();
        PredictedRegulatoryInteractionDAO ptDAO = new PredictedRegulatoryInteractionDAO();
        List<PredictedRegulatoryInteraction> list = ptDAO.listAll();

        for (PredictedRegulatoryInteraction predictedRegulatoryInteraction : list) {
            ris.add(predictedRegulatoryInteraction.getRegulatoryInteraction());

            String bs = "";
            String bsPvalue = "";
            String bsIni = "";
            String bsEnd = "";
            if (predictedRegulatoryInteraction.getPredictedBindingSite() != null) {
                bs = predictedRegulatoryInteraction.getPredictedBindingSite().getSequence();
//                if(bs.contains("---")){
//                    bs.replace("---", "");
//                }
                bsPvalue = predictedRegulatoryInteraction.getPredictedBindingSite().getPvalue().toString();
                bsIni = predictedRegulatoryInteraction.getPredictedBindingSite().getStartPosition().toString();
                bsEnd = predictedRegulatoryInteraction.getPredictedBindingSite().getEndPosition().toString();
            }

            System.out.println(predictedRegulatoryInteraction.getHomologousTranscriptionFactor().getGenome().getId()
                    + "\t" + predictedRegulatoryInteraction.getHomologousTranscriptionFactor().getLocusTag()
                    + "\t" + predictedRegulatoryInteraction.getRegulatoryInteraction().getCorrespondentTranscriptionFactor().getLocusTag()
                    + "\t" + predictedRegulatoryInteraction.getHomologousTargetGene().getLocusTag()
                    + "\t" + predictedRegulatoryInteraction.getRegulatoryInteraction().getCorrespondentTargetGene().getLocusTag()
                    + "\t" + bs
                    + "\t" + bsIni
                    + "\t" + bsEnd
            );
            //System.out.println(predictedRegulatoryInteraction.getRegulatoryInteraction().toString());
        }

        System.out.println("ris size: " + ris.size());
        return ris;
    }

    public List<RegulatoryInteraction> getNotFoundInteractions() {
        List<RegulatoryInteraction> ris = new ArrayList<RegulatoryInteraction>();
        PreditedRegulatoryInteractionTest pt = new PreditedRegulatoryInteractionTest();
        PredictedRegulatoryInteractionDAO ptDAO = new PredictedRegulatoryInteractionDAO();
        List<PredictedRegulatoryInteraction> list = ptDAO.listAll();

        for (PredictedRegulatoryInteraction predictedRegulatoryInteraction : list) {
            ris.add(predictedRegulatoryInteraction.getRegulatoryInteraction());
            System.out.println(predictedRegulatoryInteraction.getRegulatoryInteraction().toString());
        }

        System.out.println("ris size: " + ris.size());
        return ris;
    }

    public void save(PredictedRegulatoryInteraction predictedRegulatoryInteraction) {
        predictedRegulatoryInteractionDAO = new PredictedRegulatoryInteractionDAO();

        try {
            predictedRegulatoryInteractionDAO.save(predictedRegulatoryInteraction);
        } catch (Exception E) {
            System.out.println(E);
        }

    }

    public PredictedRegulatoryInteraction findById(Integer id) {
        predictedRegulatoryInteractionDAO = new PredictedRegulatoryInteractionDAO();
        PredictedRegulatoryInteraction predictedRegulatoryInteraction = predictedRegulatoryInteractionDAO.findById(id);
        return predictedRegulatoryInteraction;
    }

    public void update(PredictedRegulatoryInteraction predictedRegulatoryInteraction) {
        predictedRegulatoryInteractionDAO = new PredictedRegulatoryInteractionDAO();
        predictedRegulatoryInteractionDAO.update(predictedRegulatoryInteraction);
    }

    public void listAll() {
        predictedRegulatoryInteractionDAO = new PredictedRegulatoryInteractionDAO();
        List<PredictedRegulatoryInteraction> predictedRegulatoryInteractionrics = predictedRegulatoryInteractionDAO.listAll();
        for (PredictedRegulatoryInteraction predictedRegulatoryInteractionric : predictedRegulatoryInteractionrics) {
            System.out.println(predictedRegulatoryInteractionric.toString());
        }

    }

    public void delete(PredictedRegulatoryInteraction predictedRegulatoryInteraction) {
        predictedRegulatoryInteractionDAO = new PredictedRegulatoryInteractionDAO();
        predictedRegulatoryInteractionDAO.delete(predictedRegulatoryInteraction);
    }

    public void bringNumberOfPredictedRegulatoryInteractions() {
        PredictedRegulatoryInteractionDAO pRegulatoryInteractionDAO = new PredictedRegulatoryInteractionDAO();
        Long n = pRegulatoryInteractionDAO.bringNumberOfPredictedRegulatoryInteractions();
        System.out.println("Number of predicted regulatory interactions: " + n);
    }

    public void bringNumberOfPredictedRegulators() {
        PredictedRegulatoryInteractionDAO pRegulatoryInteractionDAO = new PredictedRegulatoryInteractionDAO();
        Long n = pRegulatoryInteractionDAO.bringNumberOfPredictedRegulators();
        System.out.println("Number of regulators: " + n);
    }

    public void findByOrganism(Integer organism) {
        PredictedRegulatoryInteractionDAO pRegulatoryInteractionDAO = new PredictedRegulatoryInteractionDAO();
        ArrayList<PredictedRegulatoryInteraction> pris = pRegulatoryInteractionDAO.findByOrganism(organism);
        for (PredictedRegulatoryInteraction pri : pris) {
            System.out.println(pri.toString());
        }
    }

}
