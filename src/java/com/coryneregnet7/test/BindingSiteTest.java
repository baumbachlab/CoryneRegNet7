/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.model.BindingSite;
import com.coryneregnet7.dao.BindingSiteDAO;
import com.coryneregnet7.dao.OrganismDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.model.Organism;
import com.coryneregnet7.model.RegulatoryInteraction;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author mariana
 */
public class BindingSiteTest {

    BindingSiteDAO bindingSiteDAO;

    public static void main(String[] args) {

        BindingSiteTest pt = new BindingSiteTest();
        //pt.findByRegulatoryTF();

        BindingSiteDAO bsDAO = new BindingSiteDAO();
        ArrayList<BindingSite> bsList = new ArrayList<>();
        ArrayList<BindingSite> bsAll = new ArrayList<>();
        //BindingSite bsList = new BindingSite();

//        OrganismDAO orgDAO = new OrganismDAO();
//        Organism organism = orgDAO.findById(735);
//        System.out.println(organism.toString());
//        bsList = (ArrayList<BindingSite>) bsDAO.findByModelRole("R", "A");
        // bsList = (ArrayList<BindingSite>) bsDAO.findByTypeAndRoleTest("model", "A");
        bsAll = (ArrayList<BindingSite>) bsDAO.findByType("model");
        //System.out.println("bsList.size(): " + bsList.size());
        System.out.println("bsAll.size(): " + bsAll.size());
        Random rand = new Random();

        int n;
// Obtain a number between [0 - 49].
        for (int i = 0; i < bsAll.size(); i++) {

            if (i < 200) {
                n = rand.nextInt(100) - 20;
            } else if (i < 300) {
                n = rand.nextInt(200) + 100;
            }else{
                n = rand.nextInt(350) + 200;
            }
            
            bsAll.get(i).setStartSiteDistance(n);
            BindingSite bs = new BindingSite();
            bs = bsAll.get(i);
            bsDAO.update(bs);
        }

        for (BindingSite bindingSite : bsAll) {
            System.out.println(bindingSite.toString());
        }

//        bsList = (ArrayList<BindingSite>) bsDAO.listAll();
//        System.out.println(bsList);
//        Integer startPositionDist;
//        Random rand = new Random();
////        startPositionDist = rand.nextInt(555) + 1;
////        bsList.setStartSiteDistance(startPositionDist);
////        bsDAO.update(bsList);
//       //System.out.println(bsList.toString());
//
//        for (int i = 0; i < bsList.size(); i++) {
//            startPositionDist = rand.nextInt(581) - 20;
//            bsList.get(i).setStartSiteDistance(startPositionDist);
//            bsDAO.update(bsList.get(i));
//        }
//        System.out.println("DONE!");
        //pt.findByTF("NnModelS1_0001");
//        BindingSite bindingSite = new BindingSite();
//        bindingSite.setEvalue(BigInteger.ONE);
//        
//        bindingSite.setBitscore(BigInteger.ONE);
//        bindingSite.setEndPosition(BigInteger.TEN);
//        bindingSite.setEvalue(BigInteger.ONE);
//        bindingSite.setEvidence("experimental");
//        bindingSite.setPmid("SS14321");
//        
//        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
//        RegulatoryInteraction regulatoryInteraction = riDAO.findById(1);
//        bindingSite.setRegulatoryInteraction(regulatoryInteraction);
//        bindingSite.setSequence("AAATTCGTGACTGTGAACGTA");
//        bindingSite.setStartPosition(new BigInteger("123344444"));
//        bindingSite.setType("model");
//        
//        pt.save(bindingSite);
//        pt.save(bindingSite);
//        pt.save(bindingSite);
//        System.out.println("\n");
//        pt.listAll();
//        System.out.println("\n");
//        bindingSite = pt.findById(3);
//        bindingSite.setPmid("TESTPMID");
//        pt.update(bindingSite);
//        System.out.println("\n");
//        pt.listAll();
//        System.out.println("\n");
//        pt.delete(bindingSite);
//        System.out.println("\n");
//        pt.listAll();
    }

    public void save(BindingSite bindingSite) {
        bindingSiteDAO = new BindingSiteDAO();

        try {
            bindingSiteDAO.save(bindingSite);
        } catch (Exception E) {
            System.out.println(E);
        }

    }

    public BindingSite findById(Integer id) {
        bindingSiteDAO = new BindingSiteDAO();
        BindingSite bindingSite = bindingSiteDAO.findById(id);
        return bindingSite;
    }

    public void update(BindingSite bindingSite) {
        bindingSiteDAO = new BindingSiteDAO();
        bindingSiteDAO.update(bindingSite);
    }

    public void listAll() {
        bindingSiteDAO = new BindingSiteDAO();
        List<BindingSite> bindingSites = bindingSiteDAO.listAll();
        for (BindingSite bindingSiteric : bindingSites) {
            System.out.println(bindingSiteric.toString());
        }

    }

    public void delete(BindingSite bindingSite) {
        bindingSiteDAO = new BindingSiteDAO();
        bindingSiteDAO.delete(bindingSite);
    }

    public void bringNumberOfBindSites() {
        BindingSiteDAO bindSitesDAO = new BindingSiteDAO();
        Long n = bindSitesDAO.bringNumberOfBindSites();
        System.out.println("Number of bind sites: " + n);
    }

//    public void findByTG(String targetGene) {
//        BindingSiteDAO bindSitesDAO = new BindingSiteDAO();
//        List<BindingSite> bindingSites = bindSitesDAO.findByTG(targetGene);
//        for (BindingSite bs : bindingSites) {
//            System.out.println("All of TG: " + bs.toString());
//        }
//
//    }
//
//    public void findByTF(String tf) {
//        BindingSiteDAO bindSitesDAO = new BindingSiteDAO();
//        List<BindingSite> bindingSites = bindSitesDAO.findByTF(tf);
//        for (BindingSite bs : bindingSites) {
//            System.out.println("All of TF: " + bs.toString());
//        }
//
//    }
    public void findByRegulatoryTF() {
        BindingSiteDAO bindSitesDAO = new BindingSiteDAO();
        List<BindingSite> bindingSites = bindSitesDAO.findByRegularotyTF(17701);
        for (BindingSite bs : bindingSites) {
            System.out.println("All of TF: " + bs.toString());
        }

    }
}
