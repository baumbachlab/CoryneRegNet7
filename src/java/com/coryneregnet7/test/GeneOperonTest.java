/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GeneOperonDAO;
import com.coryneregnet7.dao.OperonDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.GeneOperon;
import java.util.List;

/**
 *
 * @author doglas
 */
public class GeneOperonTest {

    GeneOperonDAO geneOperonDAO;
    OperonDAO opDAO;

    public static void main(String[] args) {

        GeneOperonTest go = new GeneOperonTest();
        GeneOperonDAO goDAO = new GeneOperonDAO();
//        Long num = goDAO.countOpByGenome(662);
//        System.out.println("num => "+num);
        List<GeneOperon> geneOperon = goDAO.findByOrganism(2466);
       // GeneOperon geneOperon = goDAO.findById(63560);
       Gene gene = new Gene();
       GeneDAO geneDAO = new GeneDAO();
       
       int i=0;
        for (GeneOperon geneOperon1 : geneOperon) {
            i++;
            System.out.println(i+" geneOperons: " + geneOperon1);
            gene = geneDAO.findById(geneOperon1.getGeneOperonPK().getGene());
            System.out.println("-------"+gene.toString());
            System.out.println("\n");
           //goDAO.delete(geneOperon1);
            
        }
        
    }

//    public void findByGene(Integer gene) {
//        geneOperonDAO = new GeneOperonDAO();
//        opDAO = new OperonDAO();
//        int op;
//        String opName;
//        //List<GeneOperon> geneOperons = geneOperonDAO.findByGene(gene);
//        for (GeneOperon geneOperon : geneOperons) {
//            op = geneOperon.getGeneOperonPK().getOperon();
//            opName = opDAO.findById(op).getName();
//            System.out.println("Operon: " + opName);
//        }
//
//    }
    public void findByOrganism(Integer organism) {
        geneOperonDAO = new GeneOperonDAO();
        opDAO = new OperonDAO();
        int op;
        String opName;
        List<GeneOperon> geneOperons = geneOperonDAO.findByOrganism(organism);

        System.out.println("oi");
        for (GeneOperon geneOperon : geneOperons) {
            op = geneOperon.getGeneOperonPK().getOperon();
            opName = opDAO.findById(op).getName();
            System.out.println("Operon: " + opName);
        }

    }

    public void findByGeneNameLocusTag(String gene) {
        geneOperonDAO = new GeneOperonDAO();
        List<GeneOperon> GeneOperons = geneOperonDAO.findByGeneNameLocusTag(gene);
        for (GeneOperon geneOperon : GeneOperons) {
            System.out.println("geneOperon: " + geneOperon.toString());
        }
    }

    public void findByOrganismGene(Integer organism, String gene) {
        geneOperonDAO = new GeneOperonDAO();
        List<GeneOperon> GeneOperons = geneOperonDAO.findByOrganismGene(organism, gene);
        for (GeneOperon geneOperon : GeneOperons) {
            System.out.println("geneOperon: " + geneOperon.toString());
        }
    }

    public void findByOperon(Integer operon) {
        geneOperonDAO = new GeneOperonDAO();
        List<GeneOperon> geneOperons = geneOperonDAO.findByOperon(operon);
        for (GeneOperon geneOperon : geneOperons) {
            System.out.println("geneOperons: " + geneOperon);
        }
    }

    public void findFirstGeneOfOperon(Integer operon, Integer pos) {
        geneOperonDAO = new GeneOperonDAO();
        GeneOperon geneOperon = geneOperonDAO.findFirstGeneOfOperon(operon, pos);
        System.out.println("geneOperons: " + geneOperon);
    }

    public void findModelOperons(String type) {
        geneOperonDAO = new GeneOperonDAO();
        List<GeneOperon> GeneOperons = geneOperonDAO.findModelOperons(type);
        for (GeneOperon geneOperon : GeneOperons) {
            System.out.println("geneOperon: " + geneOperon.toString());
        }
    }

    public void listAll() {
        geneOperonDAO = new GeneOperonDAO();
        opDAO = new OperonDAO();
        int op;
        String opName;
        List<GeneOperon> geneOperons = geneOperonDAO.listAll();
        for (GeneOperon geneOperon : geneOperons) {
            op = geneOperon.getGeneOperonPK().getOperon();
            opName = opDAO.findById(op).getName();
            System.out.println("Operon: " + opName);
        }

    }

    public void findByGeneNameLocusTagOfModelOrganism(String gene, String type) {
        geneOperonDAO = new GeneOperonDAO();
        List<GeneOperon> generics = geneOperonDAO.findByGeneNameLocusTagOfModelOrganism(gene, type);
        for (GeneOperon generic : generics) {
            System.out.println(generic.toString());
        }

    }

}
