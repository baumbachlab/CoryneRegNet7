/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 *
 * @author mariana
 */
public class readFastaFileTest {

    public static void main(String args[]) throws IOException {
        
        String genomicfnaFile = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/sanity-test/model/CgATCC13032_genomic.fna";

        BigInteger start = new BigInteger("1");
        BigInteger end = new BigInteger("3");
        
        for (int j = start.intValue(); j <= end.intValue(); j++) {
            
            
            try (Stream<String> lines = Files.lines(Paths.get(genomicfnaFile))) {
                String selectedLine = lines.skip(j).findFirst().get();
               
                System.out.println(selectedLine);
            
        
            }
        }
     
    }
}
