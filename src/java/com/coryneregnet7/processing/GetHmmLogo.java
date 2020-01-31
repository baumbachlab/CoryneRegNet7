/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.model.Gene;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 *
 * @author mariana
 */
public class GetHmmLogo {

    public static void main(String[] args) throws IOException, InterruptedException {
//        String directoryPAth = "/home/doglas/Desktop";
//        String stoFile = "/home/doglas/Dropbox/Doutorado/CoryneRegNet7/sanity-test/target/CgTest-hmmer-profiles/cg_test1340.sto";
//
//        GetHmmLogo getHmmLogo = new GetHmmLogo();
//        String path = getHmmLogo.getLogo(stoFile, directoryPAth);
//
//        System.out.println(path);

        GeneDAO geneDAO = new GeneDAO();
        String hmmLogo = "";
        String hmmProfile = "";
        String directoryPath = "";
        GetHmmLogo getHmmLogo = new GetHmmLogo();
        String[] stoDirectory;

        List<Gene> genes = geneDAO.findByHmmLogo();
        System.out.println("genes "+genes.size());
        for (Gene gene : genes) {
            System.out.println("");
            System.out.println(gene.toString2());
            if (gene != null && gene.getHmmLogo() != null && !gene.getHmmLogo().equals("")) {
                hmmLogo = gene.getHmmLogo();
                System.out.println("HMM Logo path: " + hmmLogo);
            } else if (gene != null && gene.getHmmProfile() != null && !gene.getHmmProfile().equals("")) {
                hmmProfile = gene.getHmmProfile();
                System.out.println("-------------------------" + hmmProfile);
                hmmProfile = hmmProfile.replace(".hmm", ".sto");
                //System.out.println("------------");
                //System.out.println("hmmProfile => " + hmmProfile);
                String startPath = System.getProperty("user.dir");
                stoDirectory = startPath.split("/");
                //directoryPath = ("/" + stoDirectory[stoDirectory.length - 7] + "/" + stoDirectory[stoDirectory.length - 6] + "/database/CoryneRegNet7/web/images");
                directoryPath = ("/home/ubuntu/database/CoryneRegNet7/web/images");
                System.out.println("hmmProfile => " + hmmProfile);
                System.out.println("directoryPath => " + directoryPath);
                hmmLogo = getHmmLogo.getLogo(hmmProfile, directoryPath);
                System.out.println("New HMM Logo path: " + hmmLogo);
                if (!hmmLogo.startsWith("Skyalign")) {
                    gene.setHmmLogo(hmmLogo);
                    geneDAO.update(gene);
                }
                Thread.sleep(90000);
            }

            System.out.println("hmmLogo: " + hmmLogo);

            //System.out.println("Gooooooooooooooooooooooooooooo View!!!!!!!!");
            String[] hmmLogoAux = hmmLogo.split("/");
            String hmmLogoName = hmmLogoAux[hmmLogoAux.length - 1];

        }

    }

    public String getLogo(String stoFile, String directoryPath) throws IOException, InterruptedException {

        //System.out.println("CHEGUEI");
        File directory = new File(directoryPath);
        String getUuidCommand = "curl -H 'Accept:application/json' -F file='@" + stoFile + "' http://skylign.org";
        //System.out.println("getUuidCommand "+getUuidCommand);

        ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", getUuidCommand);
        pb.directory(directory);
        final Process p = pb.start();
        //System.out.println("WAIT?");
        p.waitFor();
        //System.out.println("Ready");
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;

        String url = "";
        String uuid = "";
        int count = 0;
        while ((line = br.readLine()) != null) {
            count++;
            //System.out.println(line);
            if (line.contains("uuid")) {
                String[] splitedLine = line.split(",");
                //System.out.println("splitedLine[0] "+splitedLine[0]);
                //System.out.println("splitedLine[1] "+splitedLine[1]);

                url = splitedLine[0].replace("{\"url\":", "");
                url = url.replace("\"", "");
                url = url.trim();

                //System.out.println("url: "+url);
                uuid = splitedLine[1].replace("\"uuid\":", "");
                uuid = uuid.replace("\"", "");
                uuid = uuid.trim();
                //System.out.println("uuid: "+uuid);

            }

        }

        if (count == 0) {
            System.out.println("File not created!!!!!!!!!!");
        }

        if (uuid.isEmpty()) {
            return "Skyalign is not responding in this moment. Please try again later!!!!";
        } else {
            String getPng = "curl -H 'Accept:image/png' " + url + " > " + uuid + ".png";

            ProcessBuilder pb1 = new ProcessBuilder("/bin/sh", "-c", getPng);
            pb1.directory(directory);
            final Process p1 = pb1.start();
            p1.waitFor();

            BufferedReader br1 = new BufferedReader(new InputStreamReader(p1.getInputStream()));
            String line1;

//        while ((line1 = br.readLine()) != null) {
//            //System.out.println(line1);
//
//        }
            return directoryPath + "/" + uuid + ".png";

        }
    }

}
