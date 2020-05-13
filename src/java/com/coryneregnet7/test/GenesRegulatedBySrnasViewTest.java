/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.GenesRegulatedBySrnasViewDAO;
import com.coryneregnet7.model.GenesRegulatedBySrnasView;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariana
 */
public class GenesRegulatedBySrnasViewTest {
    public static void main (String[] args){
        GenesRegulatedBySrnasViewDAO dao = new GenesRegulatedBySrnasViewDAO();
        List<GenesRegulatedBySrnasView> list = dao.findByGenome(0);
        for (GenesRegulatedBySrnasView genesRegulatedBySrnasView : list) {
            System.out.println(genesRegulatedBySrnasView.toString());
        }
    }
}
