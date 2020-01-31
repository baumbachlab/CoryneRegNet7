/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.OrganismDAO;
import com.coryneregnet7.dao.PredictedRegulatoryInteractionDAO;
import com.coryneregnet7.dao.RefStatisticsDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.model.RefStatistics;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Organism;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author mariana
 */
public class RefStatisticsTest {

    public static void main(String[] args) {

        RefStatisticsTest rrTest = new RefStatisticsTest();
        rrTest.delete();
//        RefStatistics rDAO = new RefStatistics();
//        RefStatistics regulatorsRegulation = new RefStatistics();
//        OrganismDAO orgDAO = new OrganismDAO();
//        Organism org = orgDAO.findById(1185);
//        //regulatorsRegulation = rDAO.findByOrganism(org.getId());
//
//        System.out.println(regulatorsRegulation.toString());
//        //rrTest.save();
//        //        RefStatistics rDAO = new RefStatistics();
//        //        ArrayList<RefStatistics> refStatistics = new ArrayList<>();
//        //
//        //        refStatistics = (ArrayList<RefStatistics>) rDAO.listAll();
//        //        for (RefStatistics regulatorsRegulation : refStatistics) {
//        //            System.out.println(regulatorsRegulation.toString());
//        //        }
    }

    public void save() {
        RefStatistics refStatistics = new RefStatistics();
        RefStatisticsDAO rsDAO = new RefStatisticsDAO();
        GenomeDAO genomeDAO = new GenomeDAO();
//        Genome genome = genomeDAO.findById(1172);
//        refStatistics.setGenome(genome);
        refStatistics.setType("database");
        refStatistics.setDatabase("experimental");

        try {
            rsDAO.save(refStatistics);
        } catch (Exception E) {
            System.out.println(E);
        }
    }

    public void update() {
        RefStatisticsDAO rDAO = new RefStatisticsDAO();
        RefStatistics refStatistics = rDAO.findById(2);
        System.out.println("Before update: " + refStatistics);
        refStatistics.setDatabase("predicted");
        rDAO.update(refStatistics);
        refStatistics = rDAO.findById(1);
        System.out.println("After update: " + refStatistics.toString());
    }

    public void delete() {
        RefStatisticsDAO rDAO = new RefStatisticsDAO();
        ArrayList<RefStatistics> refStatistics = new ArrayList<>();

        refStatistics = (ArrayList<RefStatistics>) rDAO.listAll();
        for (RefStatistics refStatistic : refStatistics) {
            System.out.println("Before update: " + refStatistic.toString());
        }

        RefStatistics rAux = rDAO.findById(2);
        rDAO.delete(rAux);

        refStatistics = (ArrayList<RefStatistics>) rDAO.listAll();
        for (RefStatistics refStatistic : refStatistics) {
            System.out.println("After update: " + refStatistic.toString());
        }
    }

}
