/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.model.Regulation;
import com.coryneregnet7.dao.RegulationDAO;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mariana
 */
public class RegulationTest {

    RegulationDAO regulationDAO;

    public static void main(String[] args) {

        RegulationTest pt = new RegulationTest();

        Regulation regulation = new Regulation();
     
    
    }

    public void save(Regulation regulation) {
        regulationDAO = new RegulationDAO();

        try {
            regulationDAO.save(regulation);
        } catch (Exception E) {
            System.out.println(E);
        }

    }

    public Regulation findById(Integer id) {
        regulationDAO = new RegulationDAO();
        Regulation regulation =  regulationDAO.findById(id);
        return regulation;
    }

    public void update(Regulation regulation) {
        regulationDAO = new RegulationDAO();
        regulationDAO.update(regulation);
    }

    public void listAll() {
        regulationDAO = new RegulationDAO();
        List<Regulation> regulationrics = regulationDAO.listAll();
        for (Regulation regulationric : regulationrics) {
            System.out.println(regulationric.toString());
        }

    }

    public void delete(Regulation regulation) {
        regulationDAO = new RegulationDAO();
        regulationDAO.delete(regulation);
    }
}
