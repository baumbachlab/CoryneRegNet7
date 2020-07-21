/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.RnaInteractionDAO;
import com.coryneregnet7.dao.SmallRnaDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.RnaInteraction;
import com.coryneregnet7.model.SmallRna;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariana
 */
public class RnaInterationTest {

    public static void main(String[] args) {
       // RnaInterationTest riTest = new RnaInterationTest();
        RnaInteractionDAO rDAO = new RnaInteractionDAO();
        List<Gene> rnaInteraction = rDAO.findByGenomeInPosition(51828,83740, 1390);
        for (Gene rnaInteraction1 : rnaInteraction) {
            System.out.println(rnaInteraction1.getLocusTag());
        }
        //findByGenomeInPosition
        
        
    }

   

    public void findByMrna() {
        RnaInteractionDAO rDAO = new RnaInteractionDAO();
        List<RnaInteraction> rnaInteraction = rDAO.findByMrna(1821458);
        for (RnaInteraction rnaInteraction1 : rnaInteraction) {
            System.out.println(rnaInteraction1.getSrna());
        }
    }

    public void save() {
        RnaInteractionDAO rDAO = new RnaInteractionDAO();
        RnaInteraction rnaInteraction = new RnaInteraction();
        rnaInteraction.setCopraFdr(new BigDecimal("0.2"));
        rnaInteraction.setCopraPvalue(new BigDecimal("0.2"));
        rnaInteraction.setEnergy(new BigDecimal("0.2"));
        rnaInteraction.setHybridizationEnergy(new BigDecimal("0.2"));
        rnaInteraction.setIntaPvalue(new BigDecimal("0.2"));
        rnaInteraction.setInteraction("interaction");
//          rnaInteraction.setPositionMrna(123);
//          rnaInteraction.setPositionNcrna(132);
//           rnaInteraction.setPositionSeedMrna(123);
//          rnaInteraction.setPositionSeedNcrna(132);
        rnaInteraction.setRank(1);

        SmallRna sRNA = new SmallRna();
        SmallRnaDAO sRnaDAO = new SmallRnaDAO();
        sRNA = sRnaDAO.findById(1);
        rnaInteraction.setSrna(sRNA);

        Gene gene = new Gene();
        GeneDAO geneDAO = new GeneDAO();
        gene = geneDAO.findByAltLocusTag("cgtrna_rs08240s");
        rnaInteraction.setMrna(gene);

        rnaInteraction.setUnfoldingEnergyMrna(BigDecimal.ONE);
        rnaInteraction.setUnfoldingEnergyNcrna(BigDecimal.ONE);

        rDAO.save(rnaInteraction);
    }

    public void update() {
        RnaInteractionDAO rDAO = new RnaInteractionDAO();
        RnaInteraction rnaInteraction = rDAO.findById(1);
        //rnaInteraction.setBlast("20/25 genomes");
        rDAO.update(rnaInteraction);
    }

    public void delete() {
        RnaInteractionDAO rDAO = new RnaInteractionDAO();
        RnaInteraction rnaInteraction = rDAO.findById(1);
        rDAO.delete(rnaInteraction);
    }

}
