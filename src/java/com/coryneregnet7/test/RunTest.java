/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.RunDAO;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Run;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mariana
 */
public class RunTest {

    public static void main(String[] args) {
        BigDecimal num1 = new BigDecimal(0.0005);
        BigDecimal num2 = new BigDecimal(0.000000000000000005);
        if (num1.compareTo(num2) == -1) {
            System.out.println("num1 is smallest");
        }else{
            System.out.println("num2 is smallest");
        }

//        RunTest r = new RunTest();
//        RunDAO rDAO = new RunDAO();
//        Run run = rDAO.findById(91);
//        System.out.println(run);
    }

    public void save() {
        RunDAO rDAO = new RunDAO();
        Run run = new Run();
        run.setBlastCutoff("0.2");
        run.setHmmerCutoff("10e-5");
        run.setBlast("1/24 genomes");
        run.setHmmer("2/25 genomes");
        run.setOperons("3/25 genomes");
        run.setProfilesTarget("4/25 genomes");
        run.setProfilesTemplate("4/25 genomes");
        //   run.setPromoterRegions("5/25 genomes");
        rDAO.save(run);
        // GenomeDAO gDAO = new GenomeDAO();
        // List<Genome>  modelGenomes = gDAO.findByOrgnismType("model");
        //  run.setRunGenomeList(modelGenomes);

    }

    public void update() {
        RunDAO rDAO = new RunDAO();
        Run run = rDAO.findById(1);
        run.setBlast("20/25 genomes");
        rDAO.update(run);
    }

    public void delete() {
        RunDAO rDAO = new RunDAO();
        Run run = rDAO.findById(1);
        rDAO.delete(run);
    }

}
