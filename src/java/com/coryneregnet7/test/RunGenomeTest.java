/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.RunDAO;
import com.coryneregnet7.dao.RunGenomeDAO;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Run;
import com.coryneregnet7.model.RunGenome;
import com.coryneregnet7.model.RunGenomePK;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mariana
 */
public class RunGenomeTest {
    
    public static void main(String[] args){
        RunGenomeTest r = new RunGenomeTest();
        RunGenomeDAO rgDAO = new RunGenomeDAO();
        List<RunGenome> runGenomeList = rgDAO.findByRunRole(91, "template");
        for (RunGenome runGenome : runGenomeList) {
            System.out.println("RUN GENOME: "+runGenome.toString());
        }
    }
    
    public void save(){
        
        RunGenome runGenome = new RunGenome();
        RunGenomePK runGenomePK = new RunGenomePK(2,753);
        runGenome.setRunGenomePK(runGenomePK);
        RunGenomeDAO rgDAO = new RunGenomeDAO();
        rgDAO.save(runGenome);
        
       // GenomeDAO gDAO = new GenomeDAO();
       // List<Genome>  modelGenomes = gDAO.findByOrgnismType("model");
      //  run.setRunGenomeList(modelGenomes);
        
        
    }
    
    public void update(){
        RunGenomeDAO rDAO = new RunGenomeDAO();
        RunGenome runGenome = rDAO.findByIdRunGenome(2,753);
        runGenome.setRole("template");
        rDAO.update(runGenome);
    }
    
    public void delete(){
           RunGenomeDAO rDAO = new RunGenomeDAO();
        RunGenome runGenome = rDAO.findByIdRunGenome(2,753);
        rDAO.delete(runGenome);
    }
    
}
