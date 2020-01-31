/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.statistics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author mariana
 */
public class CombinePvalue {

    public static void main(String[] args) throws IOException, InterruptedException {
        /*
        Htf pvalue: 0.0	
Htg pvalue: 3.479155E-186 
Bs pvalue: 0.00002986111	
Interaction pvalue: 0.0
         */
        
        BigDecimal p1 = new BigDecimal("0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001");
        BigDecimal p2 = new BigDecimal("0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000003");
        BigDecimal p3 = new BigDecimal("0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000003");

        BigDecimal[] pvalues = {p1, p2, p3};

        CombinePvalue c = new CombinePvalue();
        c.combine(pvalues);
    }
    
     public BigDecimal combine(BigDecimal[] pvalues) throws IOException, InterruptedException {
         int i=1;
         for (BigDecimal pvalue : pvalues) {
             System.out.println("P-VALUE["+i+"] = "+pvalue);
         }
         String[] pvaluesString =  {pvalues[0].toString(),pvalues[1].toString(),pvalues[2].toString()};
         
        BigDecimal combinedPvalue = tippets(pvaluesString);
        return combinedPvalue;
    }
     
    public BigDecimal tippets(String pvalues[]) throws IOException, InterruptedException {
        //public void runHmmBuilder() throws IOException {
        String rCommand = "Rscript combine.r "+pvalues[0].toString()+" "+pvalues[1].toString()+" "+pvalues[2].toString();
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
            if(line.contains("p =")){
                //p =  1.4997e-72  using minimum p
                line = line.replace("p =  ", "").trim();
                line = line.replace("  using minimum p", "").trim();
                System.out.println("line "+line+"-----");
                jointPvalue = new BigDecimal(line);
            }
        }

        p.waitFor();
        System.out.println("jointPvalue="+jointPvalue);
        return jointPvalue;
    }
     

    public BigDecimal combineMinimum(BigDecimal[] pvalues) {
        BigDecimal min = pvalues[0];
        if (min.equals(BigDecimal.ZERO)) {
            for (int i = 0; i < pvalues.length; i++) {
                if (pvalues[i].compareTo(BigDecimal.ZERO)==0) {
                    min = pvalues[i];
                }
            }
        }

        BigDecimal combinedPvalue = new BigDecimal(BigInteger.ZERO);

        for (int i = 0; i < pvalues.length; i++) {
            System.out.println("pvalues[" + i + "] = " + pvalues[i]);
            System.out.println("min: " + min);
            if ((pvalues[i].compareTo(min) < 0) && !(pvalues[i].compareTo(BigDecimal.ZERO)==0)) {
                min = pvalues[i];
                
                System.out.println("É MENOR QUE O MIN");
                System.out.println("NOVO MIN: " + min);
            }
        }

        System.out.println("MIN: " + min);
         System.out.println("(pvalues[i].compareTo(BigDecimal.ZERO)==0)" + (min.compareTo(BigDecimal.ZERO)==0));

        combinedPvalue = min.multiply(new BigDecimal(pvalues.length));
        System.out.println("combinedPvalue " + combinedPvalue);

        return combinedPvalue;
    }

}
