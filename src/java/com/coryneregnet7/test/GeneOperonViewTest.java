/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.model.GeneOperonView;
import com.coryneregnet7.dao.GeneOperonViewDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.jboss.weld.util.collections.ArraySet;

/**
 *
 * @author mariana
 */
public class GeneOperonViewTest {

    GeneOperonViewDAO orgainsmDAO;

    public static void main(String[] args) {

        GeneOperonViewTest geneOperonView = new GeneOperonViewTest();
        GeneOperonViewDAO geneOperonViewDAO = new GeneOperonViewDAO();
        List<GeneOperonView> geneOperonViews = geneOperonViewDAO.findByGenome(1226);

        ArraySet<GeneOperonView> geneOperonViewsSet = new ArraySet<>(geneOperonViews);
        TreeMap<String, ArrayList<String>> operons = new TreeMap();
        
        String geneNames = "";
        ArrayList<String> genes = null;
        String key = null;
        String keyOld = "";
        Integer size=null;
        Integer sizeOld;
        for (GeneOperonView geneOperonViewsSet1 : geneOperonViewsSet) {
            System.out.println(geneOperonViewsSet1);

            if (operons.containsKey(geneOperonViewsSet1.getOperonName())) {
                System.out.println("------contains key :) " + geneOperonViewsSet1.getOperonName());
                geneNames = geneOperonView.getGeneNames(geneOperonViewsSet1.getGeneName(), geneOperonViewsSet1.getLocusTag());
                System.out.println("-------geneNames: " + geneNames);
////
                genes.add(geneNames);
//                operons.put(geneOperonViewsSet1.getOperonName(), genes);
            } else {

                if (key == null) {
                    key = geneOperonViewsSet1.getOperonName();
                    System.out.println("fist key: " + key);
                    genes = new ArrayList<String>();
                    geneNames = geneOperonView.getGeneNames(geneOperonViewsSet1.getGeneName(), geneOperonViewsSet1.getLocusTag());
                    genes.add(geneNames);
                    System.out.println("geneNames: "+geneNames);
                    size = geneOperonViewsSet1.getOperonSize();
                } else {
                    keyOld = key;
                    sizeOld = size;
                    System.out.println("operon size: "+sizeOld);
                    System.out.println("genes size: "+genes.size());
                    System.out.println(genes);
                    operons.put(keyOld, genes);
                    genes = new ArrayList<String>();
                    
                    size = geneOperonViewsSet1.getOperonSize();
                    key = geneOperonViewsSet1.getOperonName();
                    System.out.println("------new key: " + key);
                     geneNames = geneOperonView.getGeneNames(geneOperonViewsSet1.getGeneName(), geneOperonViewsSet1.getLocusTag());
                    genes.add(geneNames);

                }
//                
//                
//                
////
//                genes.add(geneNames);

                //
            }

        }

        //show operons:
//         System.out.println("\n\nOPERONS: ");
//        for (Map.Entry<String, ArrayList<String>> entry : operons.entrySet()) {
//            String key = entry.getKey();
//            ArrayList<String> value = entry.getValue();
//            
//            System.out.println(key+" -> "+value);
//        }
    }

    public String getGeneNames(String name, String locus) {
        String geneNames = "";
        if (name.isEmpty()) {
            geneNames = locus + "+" + locus;
        } else {
            geneNames = locus + "+" + name;
        }

        return geneNames;
    }

    public void listAll() {
        orgainsmDAO = new GeneOperonViewDAO();
        List<GeneOperonView> generics = orgainsmDAO.listAll();
        for (GeneOperonView generic : generics) {
            System.out.println(generic.toString());
        }

    }

    public void findByGenome(Integer genome) {
        orgainsmDAO = new GeneOperonViewDAO();
        List<GeneOperonView> generics = orgainsmDAO.findByGenome(genome);
        for (GeneOperonView generic : generics) {
            System.out.println(generic.toString());
        }

    }

}
