/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.validation;

import com.coryneregnet7.dao.BindingSiteDAO;
import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GeneOperonDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.OperonDAO;
import com.coryneregnet7.dao.PredictedRegulatoryInteractionDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.model.BindingSite;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.GeneOperon;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Operon;
import com.coryneregnet7.model.RegulatoryInteraction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariana
 */
public class Validation {

    public static void main(String args[]) {
        Validation v = new Validation();
        //v.findTotalTfs(789);
        //System.out.println("\n\n");
        // v.findTfsWithBs(810);
        //System.out.println("\n\n");
        //
        //v.findTfsWithHmm(789);
        //v.findRegulationsWithProfile(789);

        v.getRi(1153);
        //v.getRiWithBsOperon(1028);
        // v.getFoundRegulations(807, 809);
        // v.getFoundRegulationsWithBs(810, 811);
        //v.getFoundRegulationsWithBsOperon(789, 790);
        //v.validationReport(789, 790);
    }

    public Integer findNumberTotalTfs(Integer genomeId) {
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();

        List<Gene> transcriptionFactors = riDAO.findByGenomeDistinct(genomeId);
        System.out.println("transcriptionFactors size: " + transcriptionFactors.size());

        return transcriptionFactors.size();
    }

    public List<Gene> findTotalTfs(Integer genomeId) {
        List<Gene> tfs = new ArrayList<>();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();

        List<Gene> transcriptionFactors = riDAO.findByGenomeDistinct(genomeId);
        System.out.println("transcriptionFactors size: " + transcriptionFactors.size());

        return tfs;
    }

    public List<Gene> findTfsWithHmm(Integer genomeId) {
        List<Gene> tfs = new ArrayList<>();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();

        List<Gene> transcriptionFactors = riDAO.findByGenomeDistinct(genomeId);
        System.out.println("transcriptionFactors size: " + transcriptionFactors.size());

        BindingSiteDAO bsDAO = new BindingSiteDAO();

        for (Gene transcriptionFactor : transcriptionFactors) {
            System.out.println(transcriptionFactor.toString());
//            List<BindingSite> bindingSites = new ArrayList<>();
//            bindingSites = bsDAO.findByRegularotyTF(transcriptionFactor.getId());
            //System.out.println("bindingSites.size() " + bindingSites.size());
            if (transcriptionFactor.getHmmProfile() != null) {
                if (!transcriptionFactor.getHmmProfile().isEmpty()) {
                    tfs.add(transcriptionFactor);
                }
            }
        }

        System.out.println("\n\n");
        System.out.println("TFS size: " + tfs.size());
//        for (Gene tf : tfs) {
//            System.out.println(tf.toString());
//        }

        return tfs;
    }

    public List<Gene> findTfsWithBs(Integer genomeId) {
        List<Gene> tfs = new ArrayList<>();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();

        List<Gene> transcriptionFactors = riDAO.findByGenomeDistinct(genomeId);
        System.out.println("transcriptionFactors size: " + transcriptionFactors.size());

        BindingSiteDAO bsDAO = new BindingSiteDAO();

        for (Gene transcriptionFactor : transcriptionFactors) {
            System.out.println(transcriptionFactor.toString());
            List<BindingSite> bindingSites = new ArrayList<>();
            bindingSites = bsDAO.findByRegularotyTF(transcriptionFactor.getId());
            System.out.println("tf: " + transcriptionFactor.getLocusTag());
            System.out.println("bindingSites.size() " + bindingSites.size() + " - ");
            //int one = 1;
            Integer size = new Integer(bindingSites.size());
            if (size == 1) {
                System.out.println("É UM");
                tfs.add(transcriptionFactor);
            }
        }

        System.out.println("\n\n");
        System.out.println("TFS size: " + tfs.size());
//        for (Gene tf : tfs) {
//            System.out.println(tf.toString());
//        }

        return tfs;
    }

    public Integer findNumberTfsWithBs(Integer genomeId) {
        List<Gene> tfs = new ArrayList<>();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();

        List<Gene> transcriptionFactors = riDAO.findByGenomeDistinct(genomeId);
        System.out.println("transcriptionFactors size: " + transcriptionFactors.size());

        BindingSiteDAO bsDAO = new BindingSiteDAO();

        for (Gene transcriptionFactor : transcriptionFactors) {
            System.out.println(transcriptionFactor.toString());
            List<BindingSite> bindingSites = new ArrayList<>();
            bindingSites = bsDAO.findByRegularotyTF(transcriptionFactor.getId());
            System.out.println("bindingSites.size() " + bindingSites.size());
            if (bindingSites.size() > 0) {
                tfs.add(transcriptionFactor);
            }
        }

        System.out.println("\n\n");
        System.out.println("TFS size: " + tfs.size());
//        for (Gene tf : tfs) {
//            System.out.println(tf.toString());
//        }

        return tfs.size();
    }

    public List<RegulatoryInteraction> findRegulationsWithProfile(Integer genomeId) {
        List<Gene> tfs = new ArrayList<>();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();

        List<Gene> transcriptionFactors = riDAO.findByGenomeDistinct(genomeId);
        System.out.println("transcriptionFactors size: " + transcriptionFactors.size());

        BindingSiteDAO bsDAO = new BindingSiteDAO();

        for (Gene transcriptionFactor : transcriptionFactors) {
            System.out.println(transcriptionFactor.toString());
            List<BindingSite> bindingSites = new ArrayList<>();
            bindingSites = bsDAO.findByRegularotyTF(transcriptionFactor.getId());
            // System.out.println("bindingSites.size() " + bindingSites.size());
            if (bindingSites.size() > 0) {
                tfs.add(transcriptionFactor);
            }
        }

        List<RegulatoryInteraction> regulatoryInteractions = new ArrayList<RegulatoryInteraction>();
        System.out.println("\n\n");
        System.out.println("TFS size: " + tfs.size());
        int count = 0;
        for (Gene tf : tfs) {
            System.out.println("\n\n");
            System.out.println(tf.toString());
            List<RegulatoryInteraction> regulatoryInteractionsTF = new ArrayList<RegulatoryInteraction>();
            regulatoryInteractionsTF = riDAO.findByTF(tf.getId());
            for (RegulatoryInteraction regulatoryInteraction : regulatoryInteractionsTF) {
                System.out.println("---" + regulatoryInteraction.toString());
            }
            count = count + regulatoryInteractionsTF.size();
            regulatoryInteractions.addAll(regulatoryInteractionsTF);

        }

        System.out.println("count size " + count);
        System.out.println("regulatoryInteractions size " + regulatoryInteractions.size());
        return regulatoryInteractions;
    }

    public List<RegulatoryInteraction> getFoundRegulations(Integer templateGenome, Integer targetGenome) {
        List<RegulatoryInteraction> foundRegulations = new ArrayList<>();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        foundRegulations = priDAO.findAllRIByModelTargetGenome(templateGenome, targetGenome);

        for (RegulatoryInteraction foundRegulation : foundRegulations) {
            System.out.println(foundRegulation.toString());
        }
        System.out.println("getFoundRegulations size: " + foundRegulations.size());
        return foundRegulations;

    }

    public List<RegulatoryInteraction> getFoundRegulationsWithBs(Integer templateGenome, Integer targetGenome) {
        List<RegulatoryInteraction> foundRegulations = new ArrayList<>();
        List<RegulatoryInteraction> foundRegulationsWithBs = new ArrayList<>();
        List<RegulatoryInteraction> foundRegulationsWithoutBs = new ArrayList<>();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        foundRegulations = priDAO.findAllRIByModelTargetGenome(templateGenome, targetGenome);

        BindingSiteDAO bsDAO = new BindingSiteDAO();
        List<BindingSite> bindingSites = new ArrayList<>();

        for (RegulatoryInteraction foundRegulation : foundRegulations) {
            System.out.println(foundRegulation.toString());
            bindingSites = bsDAO.findByRegularotyInteraction(foundRegulation.getId());
            if (bindingSites != null && bindingSites.size() != 0) {
                foundRegulationsWithBs.add(foundRegulation);
            } else {
                foundRegulationsWithoutBs.add(foundRegulation);
            }
        }
        System.out.println("foundRegulationsWithBs size: " + foundRegulationsWithBs.size());
        System.out.println("foundRegulationsWITHOUTBs size: " + foundRegulationsWithoutBs.size());
        return foundRegulationsWithBs;

    }

    public List<RegulatoryInteraction> getFoundRegulationsWithBsOperon(Integer templateGenome, Integer targetGenome) {
        List<RegulatoryInteraction> foundRegulations = new ArrayList<>();
        List<RegulatoryInteraction> foundRegulationsWithBs = new ArrayList<>();
        List<RegulatoryInteraction> foundRegulationsWithBsOperon = new ArrayList<>();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        foundRegulations = priDAO.findAllRIByModelTargetGenome(templateGenome, targetGenome);

        BindingSiteDAO bsDAO = new BindingSiteDAO();
        List<BindingSite> bindingSites = new ArrayList<>();
        GeneOperon geneOperon = new GeneOperon();
        List<GeneOperon> geneOperonList = new ArrayList<>();
        GeneOperonDAO geneOperonDAO = new GeneOperonDAO();
        Operon operon = new Operon();
        OperonDAO operonDAO = new OperonDAO();
        GeneDAO geneDAO = new GeneDAO();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();

        for (RegulatoryInteraction foundRegulation : foundRegulations) {
            System.out.println(foundRegulation.toString());
            bindingSites = bsDAO.findByRegularotyInteraction(foundRegulation.getId());

            if (bindingSites != null && bindingSites.size() != 0) {
                foundRegulationsWithBs.add(foundRegulation);
                foundRegulationsWithBsOperon.add(foundRegulation);
            } else {
                Gene targetGene = foundRegulation.getCorrespondentTargetGene();
                geneOperon = new GeneOperon();
                operon = new Operon();
                geneOperon = geneOperonDAO.findByGene(targetGene.getId());
                if (geneOperon != null) {
                    System.out.println("----has operon");
                    foundRegulationsWithBsOperon.add(foundRegulation);
                }

            }
        }
        System.out.println("foundRegulationsWithBs size: " + foundRegulationsWithBs.size());
        return foundRegulationsWithBs;

    }

    public Integer getRiWithBs(Integer genome) {
        BindingSiteDAO bsDAO = new BindingSiteDAO();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();

        List<RegulatoryInteraction> interactions = riDAO.findByGenome(genome);
        List<RegulatoryInteraction> interactionsBs = new ArrayList<>();
        List<BindingSite> bindingSites = new ArrayList<>();
        for (RegulatoryInteraction interaction : interactions) {
            bindingSites = bsDAO.findByRegularotyInteraction(interaction.getId());
            System.out.print(interaction.getCorrespondentTranscriptionFactor().getLocusTag() + "\t"
                    + interaction.getCorrespondentTargetGene().getLocusTag() + "\t");

            if (bindingSites != null && bindingSites.size() != 0) {
                for (BindingSite bindingSite : bindingSites) {
                    System.out.print(bindingSite.getSequence() + ", ");
                }
                interactionsBs.add(interaction);
            }
            System.out.println("");

        }
        System.out.println("ris " + interactions.size());
        System.out.println("ris with bs " + interactionsBs.size());
        return interactionsBs.size();
    }

    public Integer getRi(Integer genome) {
        BindingSiteDAO bsDAO = new BindingSiteDAO();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();

        List<RegulatoryInteraction> interactions = riDAO.findByGenome(genome);
        List<RegulatoryInteraction> interactionsBs = new ArrayList<>();
        List<BindingSite> bindingSites = new ArrayList<>();
        for (RegulatoryInteraction interaction : interactions) {
            bindingSites = bsDAO.findByRegularotyInteraction(interaction.getId());
            //System.out.print(interaction.getCorrespondentTranscriptionFactor().getLocusTag() + "\t"
            //        + interaction.getCorrespondentTargetGene().getLocusTag() + "\t");

            Gene geneTF = interaction.getCorrespondentTranscriptionFactor();
            Gene geneTG = interaction.getCorrespondentTargetGene();
            String bs = "";
            if (bindingSites != null && bindingSites.size() != 0) {
                for (BindingSite bindingSite : bindingSites) {
              //      System.out.print(bindingSite.getSequence() + ", ");
                    if (bs.isEmpty()) {
                        bs = bs + bindingSite.getSequence();
                    } else {
                        bs = bs + ";" + bindingSite.getSequence();
                    }
                }
                interactionsBs.add(interaction);
            }
          //  System.out.println("");

            String isSigma = "";
            if(geneTF.getName().contains("sig")){
                isSigma= "+";
            }
            
            String type="";
            String role = interaction.getRole();
            String pmid = interaction.getPmid();
            String evidence = interaction.getEvidence();
            //Transcription factor	Transcription factor name	Type	Role	Target Gene	
//        //Target gene name	Evidence	PubmedIDs	Binding motif	Is CDS sigma factor	Source
            System.out.println(geneTF.getLocusTag() + "\t" + geneTF.getName() + "\t" + type + "\t" + role
                    + "\t" + geneTG.getLocusTag() + "\t" + geneTG.getName()
                    + "\t" + evidence + "\t" + pmid
                    + "\t" + bs
                    //+"\t"+bsInfo.getStartPosition()
                    //+"\t"+bsInfo.getEndPosition()
                    + "\t" + isSigma + "\tCoryneRegNet");

        }

//
        System.out.println("ris " + interactions.size());
        System.out.println("ris with bs " + interactionsBs.size());
        return interactionsBs.size();
    }

    public Integer getRiWithBsOperon(Integer genome) {
        BindingSiteDAO bsDAO = new BindingSiteDAO();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        GeneOperonDAO geneOperonDAO = new GeneOperonDAO();
        GeneOperon geneOperon = new GeneOperon();
        List<GeneOperon> geneOperonList = new ArrayList<GeneOperon>();
        Operon operon = new Operon();
        OperonDAO operonDAO = new OperonDAO();
        Gene gene = new Gene();
        GeneDAO geneDAO = new GeneDAO();

        int countOp = 0;

        List<RegulatoryInteraction> interactions = riDAO.findByGenome(genome);
        List<RegulatoryInteraction> interactionsBs = new ArrayList<>();
        List<BindingSite> bindingSites = new ArrayList<>();
        for (RegulatoryInteraction interaction : interactions) {
            interaction.toStringShort();
            bindingSites = bsDAO.findByRegularotyInteraction(interaction.getId());
            if (bindingSites != null && bindingSites.size() != 0) {
                System.out.println("----has BS");
                interactionsBs.add(interaction);

                Gene targetGene = interaction.getCorrespondentTargetGene();
                geneOperon = new GeneOperon();
                operon = new Operon();
                geneOperon = geneOperonDAO.findByGene(targetGene.getId());
                if (geneOperon != null) {
                    System.out.println("----has operon");
                    operon = operonDAO.findById(geneOperon.getGeneOperonPK().getOperon());
                    geneOperonList = geneOperonDAO.findByOperon(operon.getId());
                    for (GeneOperon geneOp : geneOperonList) {
                        System.out.println("-----------geneOperon: " + geneOperon.toString());
                        //PEGAR A REGULACAO DESSE TF PRA ESSE TG
                        gene = geneDAO.findById(geneOp.getGeneOperonPK().getGene());
                        List<RegulatoryInteraction> regulations = new ArrayList<>();
                        regulations = riDAO.bringRisByTfTg(interaction.getCorrespondentTranscriptionFactor().getId(), gene.getId());
                        if (geneOp.getPos() != 1) {
                            System.out.println("NOT 1");
                            countOp++;
                        } else {
                            System.out.println("equal 1");

                        }
                        System.out.println(regulations.toString());
                    }
                    //countOp = countOp + geneOperonList.size()-1;
                    geneOperonList = new ArrayList<>();

                }

            }

        }
        System.out.println("ris " + interactions.size());
        System.out.println("ris with bs " + interactionsBs.size());
        System.out.println("countOp " + countOp);
        System.out.println("TOTAL: " + (countOp + interactionsBs.size()));
        return interactionsBs.size();
    }

    public void validationReport(Integer template, Integer target) {
        GenomeDAO genomeDAO = new GenomeDAO();
        Genome templateGenome = genomeDAO.findById(template);
        Genome targetGenome = genomeDAO.findById(target);

        Integer templateTotalTFs = findNumberTotalTfs(template);
        Integer templateTfWithBs = findNumberTfsWithBs(template);
        Integer templateTfWithoutBs = templateTotalTFs - templateTfWithBs;

        Integer regulationsWithBs = getRiWithBs(template);

        System.out.println("The template genome " + templateGenome.getOrganism().getGenera() + " " + templateGenome.getOrganism().getSpecies() + " " + templateGenome.getOrganism().getStrain() + " has:\n"
                + templateTotalTFs + " TFs in total \n"
                + " - " + templateTfWithBs + " TFs with at least one known binding site -> " + regulationsWithBs + " regulations\n"
                + " - " + templateTfWithoutBs + " TFs with known no binding site");

    }

//    public List<RegulatoryInteraction> getNotFoundRegulations(Integer templateGenome, Integer targetGenome){
//        List<RegulatoryInteraction> notFoundRegulations = new ArrayList<>();
//        List<RegulatoryInteraction> regulationsWithBs = findRegulationsWithBs(templateGenome);
//        List<RegulatoryInteraction> foundRegulations = getFoundRegulations(templateGenome, targetGenome);
//        
//        int countFound=0;
//        int countMissed=0;
//        
//        for (RegulatoryInteraction regulationWithBs : regulationsWithBs) {
//            if(foundRegulations.contains(regulationWithBs)){
//                System.out.println("was found :D");
//                countFound++;
//            }else{
//                System.out.println("NOT found :D");
//                countMissed++;
//            }
//        }
//        
//        System.out.println("countFound "+countFound);
//        System.out.println("countMissed "+countMissed);
//        return notFoundRegulations;
//    }
}
