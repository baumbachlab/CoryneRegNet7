/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.GenesRegulatedBySrnasViewDAO;
import com.coryneregnet7.dao.SmallRnaDAO;
import com.coryneregnet7.model.GenesRegulatedBySrnasView;
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
        
        
        
        //genes regulated by TFs and RNAs
        
        
        //distribuition of smallRnas regulating a gene. 
        GenesRegulatedBySrnasViewDAO dao = new GenesRegulatedBySrnasViewDAO();
        List<GenesRegulatedBySrnasView> list = dao.findByGenome(0);
        for (GenesRegulatedBySrnasView genesRegulatedBySrnasView : list) {
            System.out.println(genesRegulatedBySrnasView.toString());
        }
    }
    
}
