/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.controller.GeneInfo;
import com.coryneregnet7.model.GeneInfoView;
import com.coryneregnet7.test.GeneInfoViewTest;
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
public class GeneInfoViewDAO extends GenericDAO {

    public TreeMap<String, GeneInfo> getGeneInfoTreeByGenome(Integer genome) {
        GeneInfoViewTest geneInfoView = new GeneInfoViewTest();
        GeneInfoViewDAO geneInfoViewDAO = new GeneInfoViewDAO();
        List<GeneInfoView> geneInfoViews = geneInfoViewDAO.findByGenome(genome);

//        ArraySet<GeneInfoView> geneInfoViewsSet = new ArraySet<>(geneInfoViews);
        TreeMap<String, GeneInfo> geneInfo = new TreeMap();

        String geneNames = "";
        for (GeneInfoView geneInfoViewsSet1 : geneInfoViews) {
            //System.out.println(geneInfoViewsSet1);
            geneInfo.put(geneInfoViewsSet1.getGeneName(), geneInfoViewsSet1.getGeneInfo());
        }

//        //show operons:
//         System.out.println("\n\nOPERONS: ");
////        for (Map.Entry<String, GeneInfo> entry : geneInfo.entrySet()) {
////            String key = entry.getKey();
////            GeneInfo value = entry.getValue();
////            //System.out.println(key + " -> " + value.toString());
////        }

        return geneInfo;
    }

    public List<GeneInfoView> listAll() {
        try {
            GeneInfoView genome = new GeneInfoView();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("GeneInfoView.findAll");
            //Query query = session.createQuery("SELECT o FROM GeneInfoView o");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<GeneInfoView> findByGenome(Integer genome) {
        try {
            List list = new ArrayList<>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("GeneInfoView.findByGenome");
            query.setInteger("genome", genome);
            list = query.list();
            this.tx.commit();
            return list;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<GeneInfoView> findByOperonId(Integer operonId) {
        try {
            List list = new ArrayList<>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("GeneInfoView.findByOperonId");
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
