/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.RnaRegulationViewDAO;
import com.coryneregnet7.model.RnaRegulationView;
import java.util.List;

/**
 *
 * @author mariana
 */
public class RnaRegulationViewTest {
    
    public static void main(String[] args){
        RnaRegulationViewDAO rnaRegDAO = new RnaRegulationViewDAO();
        List<RnaRegulationView> rnaRegList;
                
        //rnaRegList = rnaRegDAO.listAll();
        
        //rnaRegList = rnaRegDAO.findByGenome(1390);
        //rnaRegList = rnaRegDAO.findByOperon("OP_JK_RS07875");
        //rnaRegList = rnaRegDAO.findByTg(2212731);
        //findBySrna-1707
        rnaRegList = rnaRegDAO.findBySrna(1707);
        
        for (RnaRegulationView rnaRegulationView : rnaRegList) {
            System.out.println(rnaRegulationView.toString());
        }
        
    }
    
    
}
