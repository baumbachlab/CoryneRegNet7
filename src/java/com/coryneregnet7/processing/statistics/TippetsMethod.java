/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.statistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author mariana
 */
public class TippetsMethod {

    public static void main(String[] args) throws IOException, InterruptedException {
        String folder = "/home/mariana";
        TippetsMethod tippets = new TippetsMethod();
        
        BigDecimal num1 = new BigDecimal("0.000000000000011");
        BigDecimal num2 = new BigDecimal("000000000000001688239");
        BigDecimal num3 = new BigDecimal("00000000333");
        //String[] p = {"0.000000000000011","000000000000001688239","00000000333"};
        String[] p = {num1.toString(),num2.toString(),num3.toString()};
        tippets.run(p);
        
    }
    
     public BigDecimal run(String pvalues[]) throws IOException, InterruptedException {
        //public void runHmmBuilder() throws IOException {
        String rCommand = "Rscript test-r.r "+pvalues[0].toString()+" "+pvalues[1].toString()+" "+pvalues[2].toString();
        //String rCommand = "Rscript test-r.r .11 .0222 .0333";

        ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", rCommand);
        final Process p = pb.start();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        p.getInputStream()));
        String line;
        BigDecimal jointPvalue=null;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
            if(line.contains("[1]")){
                line = line.replace("[1]", "").trim();
                System.out.println("line "+line+"-----");
                jointPvalue = new BigDecimal(line);
            }
        }

        p.waitFor();
        System.out.println("jointPvalue="+jointPvalue);
        return jointPvalue;
    }

    public BigDecimal runWithFolder(String folder) throws IOException, InterruptedException {
        //public void runHmmBuilder() throws IOException {
        File directory = new File(folder);
        String rCommand = "Rscript test-r.r .013 .022 .124 .278";

        ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", rCommand);
        pb.directory(directory);
        final Process p = pb.start();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        p.getInputStream()));
        String line;
        BigDecimal jointPvalue=null;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
            if(line.contains("Combined p-value =")){
                line = line.replace("Combined p-value =", "").trim();
                System.out.println("line "+line+"-----");
                //jointPvalue = new BigDecimal(line);
            }
        }

        p.waitFor();
        System.out.println("jointPvalue="+jointPvalue);
        return jointPvalue;
    }

}
