/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.RnaTableViewDAO;
import com.coryneregnet7.model.RnaTableView;
import java.util.List;

/**
 *
 * @author mariana
 */
public class RnaTableViewTest {
    
    public static void main(String[] args){
        RnaTableViewTest test = new RnaTableViewTest();
        RnaTableViewDAO dao = new RnaTableViewDAO();
        List<RnaTableView> rnaTableView = dao.listAll();
        for (RnaTableView rnaTableView1 : rnaTableView) {
            System.out.println(rnaTableView1.toString());
        }
    }
    
    
    
}
