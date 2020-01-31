/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.controller.RegulationView;
import com.coryneregnet7.model.PredictedRegulatoryInteractionView;
import com.coryneregnet7.dao.PredictedRegulatoryInteractionViewDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.jboss.weld.util.collections.ArraySet;

/**
 *
 * @author mariana
 */
public class PredictedRegulatoryInteractionViewTest {

    PredictedRegulatoryInteractionViewDAO regulatoryInteractionViewDAO;

    public static void main(String[] args) {
        
        PredictedRegulatoryInteractionViewTest predictedRegulatoryInteractionView = new PredictedRegulatoryInteractionViewTest();
        predictedRegulatoryInteractionView.findByGenome(1230);
        
        Integer genome = 1230;
        
        PredictedRegulatoryInteractionViewDAO regulatoryInteractionViewDAO = new PredictedRegulatoryInteractionViewDAO();
        List<PredictedRegulatoryInteractionView> generics = regulatoryInteractionViewDAO.findByGenome(genome);
        
        
//        PredictedRegulatoryInteractionViewTest regulatoryInteractionView = new PredictedRegulatoryInteractionViewTest();
//        PredictedRegulatoryInteractionViewDAO regulatoryInteractionViewDAO = new PredictedRegulatoryInteractionViewDAO();
//        List<PredictedRegulatoryInteractionView> regulatoryInteractionViews = regulatoryInteractionViewDAO.findByGenome(1226);
//
//        PredictedRegulatoryInteractionView riView = new PredictedRegulatoryInteractionView();
//        ArrayList<RegulationView> regViews = riView.getRegulationViewList(regulatoryInteractionViews);
//        
//        
//        for (RegulationView regView : regViews) {
//            System.out.println(regView.toString());
//        }
//        
//        System.out.println("regulatoryInteractionViews size: "+regulatoryInteractionViews.size());
//        System.out.println("size: "+regViews.size());
//       

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
        regulatoryInteractionViewDAO = new PredictedRegulatoryInteractionViewDAO();
        List<PredictedRegulatoryInteractionView> generics = regulatoryInteractionViewDAO.listAll();
        for (PredictedRegulatoryInteractionView generic : generics) {
            System.out.println(generic.toString());
        }

    }

    public void findByGenome(Integer genome) {
        regulatoryInteractionViewDAO = new PredictedRegulatoryInteractionViewDAO();
        List<PredictedRegulatoryInteractionView> generics = regulatoryInteractionViewDAO.findByGenome(genome);
        for (PredictedRegulatoryInteractionView generic : generics) {
            System.out.println(generic.toString());
        }

    }

}
