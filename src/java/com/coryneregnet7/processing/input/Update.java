/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.input;

import com.coryneregnet7.dao.BindingSiteDAO;
import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.RegulationDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.model.BindingSite;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Regulation;
import com.coryneregnet7.model.RegulatoryInteraction;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariana
 */
public class Update {

    public static void main(String[] args) throws InterruptedException {

        Update upReg = new Update();
        GenomeDAO genomeDAO = new GenomeDAO();
        Genome genome = genomeDAO.findById(1153);
        System.out.println(genome);
        upReg.update(genome);
    }

    public void update(Genome genome) throws InterruptedException {

        String file = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/sanity-test/model/CgATCC13032-new.tsv.ctftg";
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(file));

            RegulatoryInteraction regulatoryInteraction = new RegulatoryInteraction();
            RegulatoryInteractionDAO regulatoryInteractionDAO = new RegulatoryInteractionDAO();
            List<RegulatoryInteraction> regulatoryInteractionList = new ArrayList<>();

            Gene gene = new Gene();
            GeneDAO geneDAO = new GeneDAO();
            Gene transcriptionFactor = new Gene();
            Gene targetGene = new Gene();

            int newRegulatoryInteractions = 0;
            int overlappedRegulatoryInteractions = 0;
            int updatedRegulatoryInteractions = 0;
            
            int c1=0;
            int c2=0;
            while ((sCurrentLine = br.readLine()) != null) {

                //System.out.println(sCurrentLine);
                if (!sCurrentLine.startsWith("Transcription factor")) {
                    String splitedLine[] = sCurrentLine.split("\t");
//                for (int i = 0; i < splitedLine.length; i++) {
//                    //System.out.println("splitedLine["+i+"] = "+splitedLine[i]);
//                }

                    transcriptionFactor = geneDAO.findByGenomeLocusTag(genome.getId(), splitedLine[0]);
                    targetGene = geneDAO.findByGenomeLocusTag(genome.getId(), splitedLine[4]);
                    if (transcriptionFactor != null && targetGene != null) {
                        //System.out.println("transcription factor: " + transcriptionFactor.toString());
                        //System.out.println("targetGene: " + targetGene.toString());
                        regulatoryInteractionList = regulatoryInteractionDAO.bringRisByTfTg(transcriptionFactor.getId(), targetGene.getId());
                        //System.out.println("Regulations: ");

                        if (regulatoryInteractionList.size() == 0) {
                            newRegulatoryInteractions++;
                            //System.out.println("++ACHOU");
                            String regulatorType = splitedLine[2];
                            String role = splitedLine[3];
                            String evidence = splitedLine[6];
                            String pubmedId = splitedLine[7];
                            String bindingSite = splitedLine[8];
                            String source = splitedLine[10];

                            regulatoryInteraction = new RegulatoryInteraction();
                            regulatoryInteraction.setEvidence(evidence);
                            regulatoryInteraction.setPmid(pubmedId);
                            regulatoryInteraction.setSource(source);

                            regulatoryInteraction.setCorrespondentTargetGene(targetGene);
                            regulatoryInteraction.setCorrespondentTranscriptionFactor(transcriptionFactor);

                            Regulation regulation = new Regulation();

                            RegulationDAO regulationDAO = new RegulationDAO();
                            regulation = (Regulation) regulationDAO.save(regulation);

                            regulatoryInteraction.setRegulation(regulation);
                            regulatoryInteraction.setRole(role);
                            regulatoryInteractionDAO = new RegulatoryInteractionDAO();
                            regulatoryInteraction = (RegulatoryInteraction) regulatoryInteractionDAO.save(regulatoryInteraction);

                            BindingSiteDAO bsDAO = new BindingSiteDAO();
                            BindingSite bs = new BindingSite();

                            if (!bindingSite.isEmpty() && !bindingSite.equals("-")
                                    && !bindingSite.startsWith(" ")) {

                                if (!bindingSite.contains(";")) {
                                    bs.setSequence(bindingSite);
                                    bs.setRegulatoryInteraction(regulatoryInteraction);
                                    bs.setType("model");
                                    bsDAO.save(bs);
                                } else {
                                    String[] bindingSites = bindingSite.split(";");
                                    ////System.out.println(bindingSites.length);
                                    for (int i = 0; i < bindingSites.length; i++) {

                                        try {
                                            ////System.out.println(i);
                                            ////System.out.println("bs => [" + i + "] " + bindingSites[i]);
                                            bs = new BindingSite();
                                            bsDAO = new BindingSiteDAO();
                                            bs.setSequence(bindingSites[i]);
                                            bs.setRegulatoryInteraction(regulatoryInteraction);
                                            bs.setType("model");
                                            bsDAO.save(bs);
                                        } catch (Exception e) {
                                            ////System.out.println("ERRO :( :( :(");
                                            e.printStackTrace();
                                         //   Thread.sleep(500000000);
                                        }

                                    }
                                }

                            }

                        } else {
                            //CHANGE
                            //overlappedRegulatoryInteractions++;
                            //System.out.println("regulatoryInteractionList size= " + regulatoryInteractionList.size());
                            for (int i = 0; i < regulatoryInteractionList.size(); i++) {
                                String regulatorType = splitedLine[2];
                                String role = splitedLine[3];
                                String evidence = splitedLine[6];
                                String pubmedId = splitedLine[7];
                                String bindingSite = splitedLine[8];
                                String source = splitedLine[10];
                                BindingSiteDAO bsDAO = new BindingSiteDAO();
                                boolean update = false;

                                RegulatoryInteraction ri = regulatoryInteractionList.get(i);
                                //System.out.println("\n\n" + ri.toString());
                                //System.out.println("------" + ri.toStringShort());
                                if (role.equals(ri.getRole())) {
                                    //System.out.println("ROLE IS EQUAL");
                                } else {
                                    //System.out.println("ROLE IS DIFFERENT 1");
                                    newRegulatoryInteractions++;
                                    //SE A ROLE É DIFERENTE, SALVA COMO NOVA
                                    //SAI
                                    //System.out.println("ROLE IS DIFFERENT 2");

                                    regulatoryInteraction = new RegulatoryInteraction();
                                    regulatoryInteraction.setEvidence(evidence);
                                    regulatoryInteraction.setPmid(pubmedId);
                                    regulatoryInteraction.setSource(source);

                                    regulatoryInteraction.setCorrespondentTargetGene(targetGene);
                                    regulatoryInteraction.setCorrespondentTranscriptionFactor(transcriptionFactor);

                                    Regulation regulation = new Regulation();

                                    RegulationDAO regulationDAO = new RegulationDAO();
                                    regulation = (Regulation) regulationDAO.save(regulation);

                                    regulatoryInteraction.setRegulation(regulation);
                                    regulatoryInteraction.setRole(role);
                                    regulatoryInteractionDAO = new RegulatoryInteractionDAO();
                                    regulatoryInteraction = (RegulatoryInteraction) regulatoryInteractionDAO.save(regulatoryInteraction);

                                    BindingSite bs = new BindingSite();

                                    if (!bindingSite.isEmpty() && !bindingSite.equals("-")
                                            && !bindingSite.startsWith(" ")) {

                                        if (!bindingSite.contains(";")) {
                                            bs.setSequence(bindingSite);
                                            bs.setRegulatoryInteraction(regulatoryInteraction);
                                            bs.setType("model");
                                            bsDAO.save(bs);
                                        } else {
                                            String[] bindingSites = bindingSite.split(";");
                                            ////System.out.println(bindingSites.length);
                                            for (int j = 0; j < bindingSites.length; j++) {

                                                try {
                                                    ////System.out.println(i);
                                                    ////System.out.println("bs => [" + i + "] " + bindingSites[i]);
                                                    bs = new BindingSite();
                                                    bsDAO = new BindingSiteDAO();
                                                    bs.setSequence(bindingSites[j]);
                                                    bs.setRegulatoryInteraction(regulatoryInteraction);
                                                    bs.setType("model");
                                                    bsDAO.save(bs);
                                                } catch (Exception e) {
                                                    //System.out.println("ERRO :( :( :(");
                                                    e.printStackTrace();
                                                  //  Thread.sleep(500000000);
                                                }

                                            }
                                        }

                                    }
                                    //Thread.sleep(50000);
                                    //System.out.println("SAVED NEW REGULATION WITH NEW ROLE");
                                    break;
                                }

                                if (evidence.equals(ri.getEvidence())) {
                                    //System.out.println("EVIDENCE IS EQUAL");
                                } else {
                                    //System.out.println("EVIDENCE IS DIFFERENT 1");
                                    //Thread.sleep(10000);
                                    //System.out.println("EVIDENCE IS DIFFERENT 2");
                                    ri.setEvidence("experimental + predicted");
                                    update = true;
                                }

                                if (pubmedId.equals(ri.getPmid())) {
                                    //System.out.println("pubmedId É IGUAL");
                                } else {
                                    //System.out.println("pubmedId É DIFERENTE 1");
                                    //System.out.println("ri.getPmid():" + ri.getPmid() + "----");
                                    //System.out.println("pubmedId:" + pubmedId + "----");

                                    String newPmid = combinePmid(ri.getPmid(), pubmedId);
                                    ri.setPmid(newPmid);

                                    //System.out.println("NOVO PMID: " + ri.getPmid());
                                    //Thread.sleep(1000);
                                    //System.out.println("pubmedId É DIFERENTE 2");
                                    update = true;
                                }

                                List<BindingSite> oldBss = bsDAO.findByRegularotyInteraction(ri.getId());
                                for (BindingSite oldBs : oldBss) {
                                    //System.out.println("OLD BSs = " + oldBs);
                                }
                                //System.out.println("NEW BSs =" + bindingSite);
                                if (!bindingSite.isEmpty() && !bindingSite.equals("-")
                                        && !bindingSite.startsWith(" ")) {

                                    String[] bindingSites = bindingSite.split(";");
                                    for (int j = 0; j < bindingSites.length; j++) {
                                        String bSite = bindingSites[j];

                                        // List<String> newBss = new ArrayList<>();
                                        boolean isInOldBs = false;

                                        for (BindingSite oldBs : oldBss) {
                                            if (oldBs.getSequence().equals(bSite)) {
                                                isInOldBs = true;
                                            }
                                        }

                                        if (isInOldBs == false) {
                                            // newBss.add(bSite);

                                            try {
                                                ////System.out.println(i);
                                                ////System.out.println("bs => [" + i + "] " + bindingSites[i]);
                                                BindingSite bs = new BindingSite();
                                                bsDAO = new BindingSiteDAO();
                                                bs.setSequence(bindingSites[j]);
                                                bs.setRegulatoryInteraction(ri);
                                                bs.setType("model");
                                                bsDAO.save(bs);
                                                update = true;
                                            } catch (Exception e) {
                                                //System.out.println("ERRO :( :( :(");
                                                e.printStackTrace();
                                               // Thread.sleep(500000000);
                                            }
                                        }

                                    }
                                }

                                List<BindingSite> updatedBss = bsDAO.findByRegularotyInteraction(ri.getId());
                                for (BindingSite upBs : updatedBss) {
                                    //System.out.println("Updated BSs = " + upBs);
                                }

                                if (update) {
                                    updatedRegulatoryInteractions++;
                                    regulatoryInteractionDAO.update(ri);
                                    //System.out.println("UPDATED: ");
                                    RegulatoryInteraction r = regulatoryInteractionDAO.findById(ri.getId());
                                    //System.out.println(r.toStringShort());
                                    //System.out.println("\n");
                                   // Thread.sleep(1000);
                                }else{
                                    overlappedRegulatoryInteractions++;
                                }
                            }
                            //aqui ver se tem algo novo

                            //ri.UPDATE!!!!
                        }

                        for (RegulatoryInteraction regulatoryInteraction1 : regulatoryInteractionList) {
                            //System.out.println(regulatoryInteraction1.toStringShort());

                        }

                        //System.out.println("\n\n");
                    }
                }
            }

            System.out.println("newRegulatoryInteractions: " + newRegulatoryInteractions);
            System.out.println("overlappedRegulatoryInteractions: " + overlappedRegulatoryInteractions);
            System.out.println("updatedRegulatoryInteractions: " + updatedRegulatoryInteractions);

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

    public String combinePmid(String oldPmid, String newPmid) {
        String combinedPmid = "";
        ArrayList<String> newIds = new ArrayList<>();
        //old
        if (oldPmid.isEmpty()) {
            combinedPmid = newPmid;
            return combinedPmid;
        } else {
            String[] oldPm = oldPmid.split(";");
            String[] newPm = newPmid.split(";");

            //achar o que nao tem no velho e tem novo
            for (String n : newPm) {
                boolean isInOldPmid = false;

                for (String o : oldPm) {
                    if (n.equals(o)) {
                        isInOldPmid = true;
                    }
                }

                if (isInOldPmid == false) {
                    newIds.add(n);
                }
            }

            for (int j = 0; j < oldPm.length; j++) {
                if (j == 0) {
                    combinedPmid = oldPm[j];
                } else {
                    combinedPmid = combinedPmid + ";" + oldPm[j];
                }
            }

            for (int j = 0; j < newIds.size(); j++) {
                combinedPmid = combinedPmid + ";" + newIds.get(j);
            }
        }

        return combinedPmid;
    }
}
