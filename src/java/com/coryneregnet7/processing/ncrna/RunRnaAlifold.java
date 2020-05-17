/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.ncrna;

import com.coryneregnet7.dao.GenomeDAO;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mariana
 */
public class RunRnaAlifold {

    public static void main(String[] args) {
        RunRnaAlifold rnaAlifold = new RunRnaAlifold();
        //String file = "output-c-ulcerans-nctc7908-sRNA-1.fa.sorted.out";
        //String directory = "/data/home/mariana/Dropbox/Doutorado/ncRNA/six-strains-analysis/srna-glutamicum-rnaz";
        //
        //String directory = "/data/home/mariana/Dropbox/Doutorado/ncRNA/six-strains-analysis/BSRD";
        String directory = "/data/home/mariana/Dropbox/Doutorado/ncRNA/six-strains-analysis/srna-glutamicum-rnaz/glutamicum/part2";
        
        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();

        int count=0;
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".sorted.out")) {
                System.out.println((++count)+" "+file.getName());
                
                try {
                 rnaAlifold.run(file.getName(), directory);

                } catch (Exception ex) {
                    System.out.println("Exception at RunRnaAlifold. File: " + file);
                    Logger.getLogger(RunRnaAlifold.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }

//

    }

    public void run(String file, String folder) throws IOException, InterruptedException {
        //public void runHmmBuilder() throws IOException {
        System.out.println("RUN ALIFOLD");
        File directory = new File(folder);
        String hmmBuildCommand = "/data/home/mariana/anaconda3/bin/RNAalifold --MEA --color --aln --aln-stk -f=C --id-prefix="
                + file.replace(".fa.out", "") + " " + file;
        // String hmmBuildCommand = "sh run-rnaalifold.sh";
        //      System.out.println(hmmBuildCommand);

        System.out.println(hmmBuildCommand);

        // Runtime.getRuntime().exec(hmmBuildCommand);
        ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", hmmBuildCommand);
        pb.directory(directory);
        final Process p = pb.start();
        p.waitFor();

    }

}
