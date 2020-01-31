/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.model.TableView;
import com.coryneregnet7.dao.TableViewDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.jboss.weld.util.collections.ArraySet;

/**
 *
 * @author mariana
 */
public class TableViewTest {

    TableViewDAO orgainsmDAO;

    public static void main(String[] args) {

        TableViewTest tableView = new TableViewTest();
        TableViewDAO tableViewDAO = new TableViewDAO();
        List<TableView> tableViews = tableViewDAO.findByGenome(1226);

//        ArraySet<TableView> tableViewsSet = new ArraySet<>(tableViews);
        //TreeMap<String, Table> table = new TreeMap();

        String geneNames = "";
        for (TableView tableViewsSet1 : tableViews) {
            System.out.println(tableViewsSet1);
       //     table.put(tableViewsSet1.getGeneName(), tableViewsSet1.getTable());
        }

////        //show operons:
////         System.out.println("\n\nOPERONS: ");
//        for (Map.Entry<String, Table> entry : table.entrySet()) {
//            String key = entry.getKey();
//            Table value = entry.getValue();
//            //System.out.println(key+" -> "+value.toString());
//            System.out.println(key + " -> " + value.getGene().getName() + " -> " + value.getGene().getRole());
////            System.out.println(key+" -> "+value.getGene().getRole());
//        }
    }

    public void listAll() {
        orgainsmDAO = new TableViewDAO();
        List<TableView> generics = orgainsmDAO.listAll();
        for (TableView generic : generics) {
            System.out.println(generic.toString());
        }

    }

    public void findByGenome(Integer genome) {
        orgainsmDAO = new TableViewDAO();
        List<TableView> generics = orgainsmDAO.findByGenome(genome);
        for (TableView generic : generics) {
            System.out.println(generic.toString());
        }

    }

}
