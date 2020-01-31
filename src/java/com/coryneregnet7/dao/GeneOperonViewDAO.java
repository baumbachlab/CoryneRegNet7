/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.GeneOperonView;
import com.coryneregnet7.test.GeneOperonViewTest;
import org.hibernate.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.jboss.weld.util.collections.ArraySet;

/**
 *
 * @author mariana
 */
public class GeneOperonViewDAO extends GenericDAO {

    public TreeMap<String, ArrayList<String>> getOperonsTreeByGenome(Integer genome) {
        GeneOperonViewDAO geneOperonViewDAO = new GeneOperonViewDAO();
        List<GeneOperonView> geneOperonViews = geneOperonViewDAO.findByGenome(genome);

        ArraySet<GeneOperonView> geneOperonViewsSet = new ArraySet<>(geneOperonViews);
        TreeMap<String, ArrayList<String>> operons = new TreeMap();

        String geneNames = "";
        ArrayList<String> genes = null;
        for (GeneOperonView geneOperonViewsSet1 : geneOperonViewsSet) {
          //  System.out.println(geneOperonViewsSet1);

            if (operons.containsKey(geneOperonViewsSet1.getOperonName())) {
               // System.out.println("------contains key :) " + geneOperonViewsSet1.getOperonName());
                geneNames = getGeneNames(geneOperonViewsSet1.getGeneName(), geneOperonViewsSet1.getLocusTag());
            //    System.out.println("-------geneNames: " + geneNames);
//
                genes.add(geneNames);
                operons.put(geneOperonViewsSet1.getOperonName(), genes);
            } else {
                genes = new ArrayList<String>();
                geneNames = getGeneNames(geneOperonViewsSet1.getGeneName(), geneOperonViewsSet1.getLocusTag());
            //    System.out.println("geneNames: " + geneNames);
//
                genes.add(geneNames);
                operons.put(geneOperonViewsSet1.getOperonName(), genes);
             //   System.out.println("------new key: " + geneOperonViewsSet1.getOperonName());
            }

        }
        
        
//        System.out.println("------------");
//        for (Map.Entry<String, ArrayList<String>> entry : operons.entrySet()) {
//            String key = entry.getKey();
//            ArrayList<String> value = entry.getValue();
//            System.out.println(key+" -> "+value);
//            
//        }
        return operons;
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

    public List<GeneOperonView> listAll() {
        try {
            GeneOperonView genome = new GeneOperonView();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("GeneOperonView.findAll");
            //Query query = session.createQuery("SELECT o FROM GeneOperonView o");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<GeneOperonView> findByGenome(Integer genome) {
        try {
            List list = new ArrayList<>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("GeneOperonView.findByGenome");
            query.setInteger("genome", genome);
            list = query.list();
            this.tx.commit();
            return list;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<GeneOperonView> findByOperonId(Integer operonId) {
        try {
            List list = new ArrayList<>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("GeneOperonView.findByOperonId");
            query.setInteger("operonId", operonId);
            list = query.list();
            this.tx.commit();
            return list;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

}
