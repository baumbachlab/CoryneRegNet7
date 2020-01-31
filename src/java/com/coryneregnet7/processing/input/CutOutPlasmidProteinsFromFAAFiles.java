/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.input;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Organism;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author doglas
 */
public class CutOutPlasmidProteinsFromFAAFiles {

    public static void main(String[] args) throws InterruptedException, IOException {
        String faaData = "/home/doglas/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/genus-data/protein_fasta/ncbi-genomes-2019-06-01/without_plasmid/";
        CutOutPlasmidProteinsFromFAAFiles coppfff = new CutOutPlasmidProteinsFromFAAFiles();
        coppfff.readFaaData(faaData);
    }

    public void readFaaData(String folderPath) throws InterruptedException, IOException, IOException {

        //lê a pasta dos templates ou dos modelos. 
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        //System.out.println(folderPath);
        //System.out.println(listOfFiles.length);
        //System.out.println(folderPath);
        for (File file : listOfFiles) {
            readFaaFile(file);
        }
    }

    public void readFaaFile(File file) throws InterruptedException, IOException {
        System.out.println("file " + file.getPath());
        String[] fileNameAux = file.getPath().split("/");
        String fileName = fileNameAux[fileNameAux.length - 1].replace(".faa", "") + "_without_plasmidInfo.faa";
        System.out.println("fileName: " + fileName);
        //fileNameAux[fileNameAux.length - 1] = fileName;
        String newPath = file.getAbsolutePath().replace(fileNameAux[fileNameAux.length - 1], fileName);
        //_protein_without_plasmidInfo
        FileWriter fileWriter = new FileWriter(newPath);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        String plasmidName = fileName.replace("_without_plasmidInfo.faa", "_") + "plasmidInfo.faa";
        System.out.println("plasmidName: " + plasmidName);
        String plasmidFile = file.getAbsolutePath().replace(fileNameAux[fileNameAux.length - 1], plasmidName);
        System.out.println("file.getAbsolutePath(): " + file.getAbsolutePath());
        System.out.println("plasmidFile: " + file.getAbsolutePath().replace(fileNameAux[fileNameAux.length - 1], plasmidName));
        FileWriter filePlasmidWriter = new FileWriter(newPath);
        PrintWriter printPlasmidWriter = new PrintWriter(filePlasmidWriter);

        fileNameAux = fileName.split("_");
        //System.out.println("\n\n===========> " + file.getName() + "\n\n");
        BufferedReader br = null;
        FileReader fr = null;
        String[] line;
        boolean isPartOfGenome = false;
        GeneDAO geneDAO = new GeneDAO();
        ArrayList<Gene> genes = new ArrayList<>();
        String proteinId;
        GenomeDAO genomeDAO = new GenomeDAO();
        Genome genome = genomeDAO.findByGenomeName(fileNameAux[0] + "_" + fileNameAux[1] + "_" + fileNameAux[2]);
        System.out.println(genome.toString());
        List<String> proteinGenomeInfo = new ArrayList<>();
        List<String> proteinPlasmidInfo = new ArrayList<>();

        try {

            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println(sCurrentLine);
                if (sCurrentLine.startsWith(">")) {
                    if (!proteinGenomeInfo.isEmpty()) {
                        //System.out.println("proteinGenomeInfo: " + proteinGenomeInfo);
                        for (int i = 0; i < proteinGenomeInfo.size(); i++) {
                            printWriter.println(proteinGenomeInfo.get(i));
                        }
                    } else if (!proteinPlasmidInfo.isEmpty()) {
                        //System.out.println("proteinPlasmidInfo: " + proteinPlasmidInfo);
                        for (int i = 0; i < proteinPlasmidInfo.size(); i++) {
                            printPlasmidWriter.println(proteinPlasmidInfo.get(i));
                        }
                    }
                    line = sCurrentLine.split(" ");
                    //System.out.println("line[0]: " + line[0]);
                    proteinId = line[0].replace(">", "");
                    genes = (ArrayList<Gene>) geneDAO.findByGenomeLocusTagProteinIdMultiple(genome.getId(), proteinId);
                    if (!genes.isEmpty()) {
                        // System.out.println("proteinId: " + proteinId);
                        isPartOfGenome = true;
                        proteinGenomeInfo = new ArrayList<>();
                        proteinPlasmidInfo = new ArrayList<>();
                        proteinGenomeInfo.add(sCurrentLine);
                    } else {
                        //System.out.println("Plasmid gene!!!!!!!!!!");
                        //Thread.sleep(5000);
                        isPartOfGenome = false;
                        proteinPlasmidInfo = new ArrayList<>();
                        proteinGenomeInfo = new ArrayList<>();
                        proteinPlasmidInfo.add(sCurrentLine);
                    }
                } else {
                    if (!proteinGenomeInfo.isEmpty()) {
                        proteinGenomeInfo.add(sCurrentLine);
                    } else if (!proteinPlasmidInfo.isEmpty()) {
                        proteinPlasmidInfo.add(sCurrentLine);
                    }
                }
            }

            if (!proteinGenomeInfo.isEmpty()) {
                //System.out.println("proteinGenomeInfo: " + proteinGenomeInfo);
                for (int i = 0; i < proteinGenomeInfo.size(); i++) {
                    printWriter.println(proteinGenomeInfo.get(i));
                }
            } else if (!proteinPlasmidInfo.isEmpty()) {
                //System.out.println("proteinPlasmidInfo: " + proteinPlasmidInfo);
                for (int i = 0; i < proteinPlasmidInfo.size(); i++) {
                    printPlasmidWriter.println(proteinPlasmidInfo.get(i));
                }
            }

            printWriter.close();
            printPlasmidWriter.close();
        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
    }
}
