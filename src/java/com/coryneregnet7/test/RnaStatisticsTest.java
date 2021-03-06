/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.GenesRegulatedBySrnasViewDAO;
import com.coryneregnet7.dao.GenesRegulatedByTfSrnaViewDAO;
import com.coryneregnet7.dao.RnaCoregulatingViewDAO;
import com.coryneregnet7.dao.SmallRnaDAO;
import com.coryneregnet7.model.GenesRegulatedBySrnasView;
import com.coryneregnet7.model.GenesRegulatedByTfSrnaView;
import com.coryneregnet7.model.RnaCoregulatingView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariana
 */
public class RnaStatisticsTest {
    
    public static void main(String[] args){
        
        //quantities of rna types
        //experimental
        SmallRnaDAO sRnaDAO = new SmallRnaDAO();
        Long experimental = sRnaDAO.bringByType("experimental");
        System.out.println("experimental "+experimental);
        Long predicted = sRnaDAO.bringByNotType("experimental");
        System.out.println("predicted "+predicted);
        
        Long ncRnaExperimental = sRnaDAO.bringFunctionalByType("experimental", false);
        System.out.println("ncRnaExperimental "+ncRnaExperimental);
        Long ncRnaPredicted = sRnaDAO.bringFunctionalByNotType("experimental", false);
        System.out.println("ncRnaPredicted "+ncRnaPredicted);
        
        Long funcRnaExperimental = sRnaDAO.bringFunctionalByType("experimental", true);
        System.out.println("funcRnaExperimental "+funcRnaExperimental);
        Long funcRnaPredicted = sRnaDAO.bringFunctionalByNotType("experimental", true);
        System.out.println("funcRnaPredicted "+funcRnaPredicted);
        
        
        
        //rnas regulating TFs ---> saiu.
        
        
        System.out.println("\n\n---------------------------");
        //genes regulated by TFs and RNAs
        GenesRegulatedByTfSrnaViewDAO genesRegulatedByTfSrnaViewDAO = new GenesRegulatedByTfSrnaViewDAO();
        List<GenesRegulatedByTfSrnaView> genesRegulatedByTfSrnaViewList = new ArrayList<>();
        
        genesRegulatedByTfSrnaViewList = genesRegulatedByTfSrnaViewDAO.listAll();
        for (GenesRegulatedByTfSrnaView genesRegulatedByTfSrnaView : genesRegulatedByTfSrnaViewList) {
            System.out.println(genesRegulatedByTfSrnaView.toString());
        }
        
        System.out.println("\n\n---------------------------");
        //distribuition of smallRnas regulating a gene. 
        GenesRegulatedBySrnasViewDAO dao = new GenesRegulatedBySrnasViewDAO();
        List<GenesRegulatedBySrnasView> list = dao.findByGenome(0);
        for (GenesRegulatedBySrnasView genesRegulatedBySrnasView : list) {
            System.out.println(genesRegulatedBySrnasView.toString());
        }
        
        //coregulating srnas
        System.out.println("\n\n--------------------------");
        RnaCoregulatingViewDAO rnaCoregulatingDAO = new RnaCoregulatingViewDAO();
        List<RnaCoregulatingView> lisCoregRna = rnaCoregulatingDAO.findByGenome(0);
        for (RnaCoregulatingView rnaCoregulatingView : lisCoregRna) {
            System.out.println(rnaCoregulatingView);
        }
    }
    
}
