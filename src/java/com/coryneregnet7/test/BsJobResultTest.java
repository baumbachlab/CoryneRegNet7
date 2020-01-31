/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.BsJobResultDAO;
import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.OperonDAO;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.BsJobResult;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Operon;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mariana
 */
public class BsJobResultTest {

    public static void main(String[] args) {
      
        BsJobResultTest r = new BsJobResultTest();
        //r.delete();
        BsJobResultDAO rDAO = new BsJobResultDAO();
        List<BsJobResult> run = rDAO.findByJob(83);
        for (BsJobResult bsJobResult : run) {
            System.out.println(bsJobResult.toString());
        }
    }

    public void save() {
        BsJobResultDAO rDAO = new BsJobResultDAO();
        BsJobResult run = new BsJobResult();
        run.setAlreadyKnown("yes");
        run.setEvalue("10e-35");
        
        GeneDAO geneDAO = new GeneDAO();
        Gene gene = geneDAO.findByLocusTag("cg0012");
        Gene gene1 = geneDAO.findByLocusTag("cg0350");
        run.setHmmFrom(gene);
        run.setTargetGene(gene1);
        
        run.setHomologousTfTemplate("genes tf homologos");
        run.setHomologousTgTarget("genes tg homologous");
        
        OperonDAO operonDAO = new OperonDAO();
        Operon operon = operonDAO.findByName("OP_cg0004");
        run.setPredictedOperon(operon);
        
        run.setSequence("AAATTCCCGGG");
        
        //   run.setPromoterRegions("5/25 genomes");
        rDAO.save(run);
        // GenomeDAO gDAO = new GenomeDAO();
        // List<Genome>  modelGenomes = gDAO.findByOrgnismType("model");
        //  run.setBsJobResultGenomeList(modelGenomes);

    }

    public void update() {
        BsJobResultDAO rDAO = new BsJobResultDAO();
        BsJobResult run = rDAO.findById(1);
        run.setAlreadyKnown("nao sei");
        rDAO.update(run);
    }

    public void delete() {
        BsJobResultDAO rDAO = new BsJobResultDAO();
        BsJobResult run = rDAO.findById(1);
        rDAO.delete(run);
    }

}
