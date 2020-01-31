/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.controller.GeneInfo;
import com.coryneregnet7.model.GeneInfoView;
import com.coryneregnet7.dao.GeneInfoViewDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.jboss.weld.util.collections.ArraySet;

/**
 *
 * @author mariana
 */
public class GeneInfoViewTest {

    GeneInfoViewDAO orgainsmDAO;

    public static void main(String[] args) {

        GeneInfoViewTest geneInfoView = new GeneInfoViewTest();
        GeneInfoViewDAO geneInfoViewDAO = new GeneInfoViewDAO();
        List<GeneInfoView> geneInfoViews = geneInfoViewDAO.findByGenome(1226);

//        ArraySet<GeneInfoView> geneInfoViewsSet = new ArraySet<>(geneInfoViews);
        TreeMap<String, GeneInfo> geneInfo = new TreeMap();

        String geneNames = "";
        for (GeneInfoView geneInfoViewsSet1 : geneInfoViews) {
            System.out.println(geneInfoViewsSet1);
            geneInfo.put(geneInfoViewsSet1.getGeneName(), geneInfoViewsSet1.getGeneInfo());
        }

//        //show operons:
//         System.out.println("\n\nOPERONS: ");
        for (Map.Entry<String, GeneInfo> entry : geneInfo.entrySet()) {
            String key = entry.getKey();
            GeneInfo value = entry.getValue();
            //System.out.println(key+" -> "+value.toString());
            System.out.println(key + " -> " + value.getGene().getName() + " -> " + value.getGene().getRole());
//            System.out.println(key+" -> "+value.getGene().getRole());
        }
    }

    public void listAll() {
        orgainsmDAO = new GeneInfoViewDAO();
        List<GeneInfoView> generics = orgainsmDAO.listAll();
        for (GeneInfoView generic : generics) {
            System.out.println(generic.toString());
        }

    }

    public void findByGenome(Integer genome) {
        orgainsmDAO = new GeneInfoViewDAO();
        List<GeneInfoView> generics = orgainsmDAO.findByGenome(genome);
        for (GeneInfoView generic : generics) {
            System.out.println(generic.toString());
        }

    }

}
