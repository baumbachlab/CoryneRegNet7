/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.model.Homologous;
import com.coryneregnet7.dao.HomologousDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Genome;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariana
 */
public class HomologousTest {

    HomologousDAO homologousDAO;

    public static void main(String[] args) {

        HomologousTest ht = new HomologousTest();
        HomologousDAO homologousDAO = new HomologousDAO();
        List<Homologous> homologous = homologousDAO.findByGenome(1292);
        for (Homologous homologou : homologous) {
            System.out.println("homologou "+homologou.getId());
            homologousDAO.delete(homologou);
            System.out.println("---------deleted.");
        }
        System.out.println("HOMOLOGOUS "+homologous.size());
      
    }

    public void discoverGenomesWithHomologous() {
        System.out.println("HI?");
        HomologousDAO homologousDAO = new HomologousDAO();
        GenomeDAO genomeDAO = new GenomeDAO();
        List<Genome> genomesTarget = genomeDAO.findByOrgnismType("target");
////        List<Genome> genomesTarget = new ArrayList<>();
////        genomesTarget.add(genomeDAO.findById(1317));
        for (Genome target : genomesTarget) {
            //for (int id = 1300; id < 1354; id++) {
            Long numHomologous = homologousDAO.bringByModelAndTargetGenomes(target.getId(), 1242);
           // if (numHomologous != 0) {
                System.out.println("genome " + target.getId() + " contains " + numHomologous + " homologous " + target.getGenomeName());
           // }
        }
    }
    //1338

    public void discoverAndListGenomesWithHomologousOne() {
        HomologousDAO homologousDAO = new HomologousDAO();
        GenomeDAO genomeDAO = new GenomeDAO();
        Long numHomologous = homologousDAO.bringByModelAndTargetGenomes(1440, 1321);
        System.out.println("genome " + 1338 + " contains " + numHomologous + " homologous ");
        List<Homologous> homologousList = homologousDAO.findByModelAndTargetGenomes(1440, 1321);
        for (Homologous homologous : homologousList) {
            System.out.println(homologous.toString());

        }
    }

    public void listHomologousMultiple() {
        HomologousDAO homologousDAO = new HomologousDAO();
        GenomeDAO genomeDAO = new GenomeDAO();
        List<Homologous> homologousList = homologousDAO.findByGenome(1290);
        for (Homologous homologous : homologousList) {
            System.out.println(homologous.getGeneTwo().getProteinId()
                    + "\t" + homologous.getGeneOne().getProteinId()
                    + "\t" + homologous.getEvalue()
                    + "\t" + homologous.getGeneTwo().getGenome().getGenomeName()
                    + "\t" + homologous.getGeneOne().getGenome().getGenomeName());

        }
    }

    public void save(Homologous homologous) {
        homologousDAO = new HomologousDAO();

        try {
            homologousDAO.save(homologous);
        } catch (Exception E) {
            System.out.println(E);
        }

    }

    public Homologous findById(Integer id) {
        homologousDAO = new HomologousDAO();
        Homologous homologous = homologousDAO.findById(id);
        return homologous;
    }

    public void update(Homologous homologous) {
        homologousDAO = new HomologousDAO();
        homologousDAO.update(homologous);
    }

    public void listAll() {
        homologousDAO = new HomologousDAO();
        List<Homologous> homologous = homologousDAO.listAll();
        for (Homologous homolog : homologous) {
            System.out.println("getGeneOne: " + homolog.getGeneOne().getLocusTag() + " getGeneTwo: " + homolog.getGeneTwo().getLocusTag()
                    + "getEvalue: " + homolog.getEvalue() + " getPvalue: " + homolog.getPvalue());
        }

    }

    public void delete(Homologous homologous) {
        homologousDAO = new HomologousDAO();
        homologousDAO.delete(homologous);
    }
}
