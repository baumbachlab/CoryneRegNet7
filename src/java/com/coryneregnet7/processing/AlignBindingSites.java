/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author mariana
 */
public class AlignBindingSites {

    public static void main(String[] args) throws IOException, InterruptedException {
        AlignBindingSites align = new AlignBindingSites();
        align.runClustalOmega("/data/coryne-genus/target-brucella/Bab30_cro1-hmmer-profiles/PROKKA_01586.fasta",
                                 "/data/coryne-genus/target-brucella/Bab30_cro1-hmmer-profiles/test.sto",
                                 "/data/coryne-genus/target-brucella/Bab30_cro1-hmmer-profiles/");
        
    }

    public void runClustalOmega(String fastaFile, String stoFile, String directory) throws IOException, InterruptedException {
        //File directory = new File("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/clustalo-tests");
       
        String clustalCommand = "clustalo -i "+fastaFile+" -o "+stoFile+" --outfmt=st";
        System.out.println(clustalCommand);
        ProcessBuilder pb = new ProcessBuilder("/bin/sh","-c", clustalCommand);
        pb.directory(new File(directory));
        final Process p = pb.start();
        p.waitFor();
        System.out.println(p.getErrorStream());
        System.out.println(p.getOutputStream());
    }

}
