/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.BindingSiteDAO;
import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.PredictedRegulatoryInteractionDAO;
import com.coryneregnet7.model.RegulatoryInteraction;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.model.BindingSite;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.PredictedRegulatoryInteraction;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author mariana
 */
public class RegulatoryInteractionTest {

    RegulatoryInteractionDAO regulatoryInteractionDAO;

    public static void main(String[] args) {
        /*
        
Info:   organismIdForTG: 733
Info:   homologousTF.getGeneTwo().getLocusTag(): cg_test0012
Info:   TF: cg0012
Info:   homologousTG.getGeneOne().getLocusTag(): cg2186
Info:   ri TF GENE2 => null
Info:   pri TF GENE2 => null
         */

        RegulatoryInteractionTest pt = new RegulatoryInteractionTest();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        List<RegulatoryInteraction> ri = new ArrayList<>();


//
        ri = riDAO.findByGenome(2462);
        System.out.println("RI SIZE: " + ri.size());
        for (RegulatoryInteraction regulatoryInteraction : ri) {
            System.out.println("regulatory interaction  "+regulatoryInteraction.getCorrespondentTargetGene().getGenome().getOrganism().getGenera());
//            
            if(regulatoryInteraction.getRole().equals("A")){
                regulatoryInteraction.setRole("Activator");
                riDAO.save(regulatoryInteraction);
            }else if(regulatoryInteraction.getRole().equals("R")){
                regulatoryInteraction.setRole("Repressor");
                riDAO.save(regulatoryInteraction);
            }
            

//            List<BindingSite> bsList = new ArrayList<BindingSite>();
//            BindingSiteDAO bsDAO = new BindingSiteDAO();
//            bsList = bsDAO.findByRegularotyInteraction(regulatoryInteraction.getId());

//            if (!bsList.isEmpty()) {
//                for (BindingSite bindingSite : bsList) {
//                    //Transcription factor	Transcription factor name	Type	
//                    //Role	Target Gene	Target gene name	Evidence	
//                    //PubmedIDs	Binding motif	Is CDS sigma factor	Source
//                    System.out.println(regulatoryInteraction.getCorrespondentTranscriptionFactor().getLocusTag() + "\t"
//                            + regulatoryInteraction.getCorrespondentTranscriptionFactor().getName() + "\t"
//                            + "glxR" + "\t"
//                            + regulatoryInteraction.getRole() + "\t"
//                            + regulatoryInteraction.getCorrespondentTargetGene().getLocusTag() + "\t"
//                            + regulatoryInteraction.getCorrespondentTargetGene().getName() + "\t"
//                            + regulatoryInteraction.getEvidence() + "\t"
//                            + regulatoryInteraction.getPmid() + "\t"
//                            + bindingSite.getSequence() + "\t"
//                            + "" + "\t"
//                            + ""
//                    );
//                }
//                //    System.out.println(bindingSite.getSequence());
//            }else{
//                    System.out.println(regulatoryInteraction.getCorrespondentTranscriptionFactor().getLocusTag() + "\t"
//                            + regulatoryInteraction.getCorrespondentTranscriptionFactor().getName() + "\t"
//                            + "glxR" + "\t"
//                            + regulatoryInteraction.getRole() + "\t"
//                            + regulatoryInteraction.getCorrespondentTargetGene().getLocusTag() + "\t"
//                            + regulatoryInteraction.getCorrespondentTargetGene().getName() + "\t"
//                            + regulatoryInteraction.getEvidence() + "\t"
//                            + regulatoryInteraction.getPmid() + "\t"
//                            + "" + "\t"
//                            + "" + "\t"
//                            + ""
//                    );
//            }

        }

    }

    public void save(RegulatoryInteraction regulatoryInteraction) {
        regulatoryInteractionDAO = new RegulatoryInteractionDAO();

        try {
            regulatoryInteractionDAO.save(regulatoryInteraction);
        } catch (Exception E) {
            System.out.println(E);
        }

    }

    public RegulatoryInteraction findById(Integer id) {
        regulatoryInteractionDAO = new RegulatoryInteractionDAO();
        RegulatoryInteraction regulatoryInteraction = regulatoryInteractionDAO.findById(id);
        return regulatoryInteraction;
    }

    public void findByOrganism(Integer organism) {
        regulatoryInteractionDAO = new RegulatoryInteractionDAO();
        ArrayList<RegulatoryInteraction> ris = regulatoryInteractionDAO.findByOrganism(organism);
        for (RegulatoryInteraction ri : ris) {
            System.out.println(ri.toString());
        }
    }

    public void update(RegulatoryInteraction regulatoryInteraction) {
        regulatoryInteractionDAO = new RegulatoryInteractionDAO();
        regulatoryInteractionDAO.update(regulatoryInteraction);
    }

    public void listAll() {
        regulatoryInteractionDAO = new RegulatoryInteractionDAO();
        List<RegulatoryInteraction> regulatoryInteractionrics = regulatoryInteractionDAO.listAll();
        for (RegulatoryInteraction regulatoryInteractionric : regulatoryInteractionrics) {
            System.out.println(regulatoryInteractionric.toString());
        }

    }

    public void delete(RegulatoryInteraction regulatoryInteraction) {
        regulatoryInteractionDAO = new RegulatoryInteractionDAO();
        regulatoryInteractionDAO.delete(regulatoryInteraction);
    }

    public void bringNumberOfRegulatoryInteraction() {
        RegulatoryInteractionDAO regulatoryInteractionDAO = new RegulatoryInteractionDAO();
        Long n = regulatoryInteractionDAO.bringNumberOfRegulatoryInteractions();
        System.out.println("Number of regulatoryInteraction: " + n);
    }

    public void bringNumberOfRegulators() {
        RegulatoryInteractionDAO regulatoryInteractionDAO = new RegulatoryInteractionDAO();
        Long n = regulatoryInteractionDAO.bringNumberOfRegulators();
        System.out.println("Number of regulators: " + n);
    }

    public void bringNumberOfRegulatedGenes() {
        RegulatoryInteractionDAO regulatoryInteractionDAO = new RegulatoryInteractionDAO();
        Long n = regulatoryInteractionDAO.bringNumberOfRegulatedGenes();
        System.out.println("Number of regulated genes: " + n);
    }

//    public void findRegulates() {
//        RegulatoryInteractionDAO regulatoryInteractionDAO = new RegulatoryInteractionDAO();
//        List<RegulatoryInteraction> genes = new ArrayList<RegulatoryInteraction>();
//        genes = regulatoryInteractionDAO.findByTF(17240);
//        for (RegulatoryInteraction gene : genes) {
//            System.out.println("regulate: " + gene.getCorrespondentTargetGene().getName());
//        }
//    }
    public void findRegulatedBy() {
        RegulatoryInteractionDAO regulatoryInteractionDAO = new RegulatoryInteractionDAO();
        List<RegulatoryInteraction> genes = new ArrayList<RegulatoryInteraction>();
        genes = regulatoryInteractionDAO.findByTG(17241);
        for (RegulatoryInteraction gene : genes) {
            System.out.println("regulated by: " + gene.getCorrespondentTranscriptionFactor().getName());
        }
    }

    public void findTFbyGenome() {
        RegulatoryInteractionDAO regulatoryInteractionDAO = new RegulatoryInteractionDAO();
        List<Gene> tfs = regulatoryInteractionDAO.findTFbyGenome(79);
        for (Gene tf : tfs) {
            System.out.println(tf.toString());
        }
    }

    public void bringNumberOfCoregulators(String locusTag) {
        RegulatoryInteractionDAO regulatoryInteractionDAO = new RegulatoryInteractionDAO();
        Long n = regulatoryInteractionDAO.bringNumberOfCoregulators(locusTag) - 1;
        System.out.println(n.toString());
    }
}
