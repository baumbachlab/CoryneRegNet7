/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.JobDAO;
import com.coryneregnet7.model.Job;

/**
 *
 * @author mariana
 */
public class JobTest {
    
    
    public static void main(String[] args){
        JobDAO jobDAO = new JobDAO();
        Job job = new Job();
//        job.setStatus("in progress");
        job = jobDAO.findById(50);
        System.out.println(job);
        
    }
    
}
