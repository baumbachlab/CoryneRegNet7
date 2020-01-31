/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.controller.RegulationView;
import com.coryneregnet7.model.RegulatoryInteractionView;
import com.coryneregnet7.dao.RegulatoryInteractionViewDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.jboss.weld.util.collections.ArraySet;

/**
 *
 * @author mariana
 */
public class RegulatoryInteractionViewTest {

    RegulatoryInteractionViewDAO regulatoryInteractionViewDAO;

    public static void main(String[] args) {

        RegulatoryInteractionViewTest regulatoryInteractionView = new RegulatoryInteractionViewTest();
        RegulatoryInteractionViewDAO regulatoryInteractionViewDAO = new RegulatoryInteractionViewDAO();
        List<RegulatoryInteractionView> regulatoryInteractionViews = regulatoryInteractionViewDAO.findByGenome(1226);

        RegulatoryInteractionView riView = new RegulatoryInteractionView();
        ArrayList<RegulationView> regViews = riView.getRegulationViewList(regulatoryInteractionViews);
        
        
        for (RegulationView regView : regViews) {
            System.out.println(regView.toString());
        }
        
        System.out.println("regulatoryInteractionViews size: "+regulatoryInteractionViews.size());
        System.out.println("size: "+regViews.size());
       

        //show operons:
        // System.out.println("\n\nOPERONS: ");
//        for (Map.Entry<String, ArrayList<String>> entry : operons.entrySet()) {
//            String key = entry.getKey();
//            ArrayList<String> value = entry.getValue();
//            
//            System.out.println(key+" -> "+value);
//        }
    }

    public void listAll() {
        regulatoryInteractionViewDAO = new RegulatoryInteractionViewDAO();
        List<RegulatoryInteractionView> generics = regulatoryInteractionViewDAO.listAll();
        for (RegulatoryInteractionView generic : generics) {
            System.out.println(generic.toString());
        }

    }

    public void findByGenome(Integer genome) {
        regulatoryInteractionViewDAO = new RegulatoryInteractionViewDAO();
        List<RegulatoryInteractionView> generics = regulatoryInteractionViewDAO.findByGenome(genome);
        for (RegulatoryInteractionView generic : generics) {
            System.out.println(generic.toString());
        }

    }

}
