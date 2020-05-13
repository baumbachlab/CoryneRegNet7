/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.output;

import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.RnaInteractionDAO;
import com.coryneregnet7.dao.SmallRnaDAO;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Organism;
import com.coryneregnet7.model.RnaInteraction;
import com.coryneregnet7.model.SmallRna;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mariana
 */
public class RnaFile {

    public static void main(String[] args) {
        Integer[] genomes = {1226};
        //Integer[] genomes = {1230, 1288, 1226, 1274, 1390, 1243};

        GenomeDAO genomeDAO = new GenomeDAO();

        RnaFile rnaFile = new RnaFile();
        for (int i = 0; i < genomes.length; i++) {

            Genome genome = genomeDAO.findById(genomes[i]);
            Organism organism = genome.getOrganism();
            String fileName = "";
            System.out.println("organism: " + organism.getGenera() + " " + organism.getSpecies() + " " + organism.getStrain());
            fileName = organism.getGenera().charAt(0) + "_" + organism.getSpecies().charAt(0) + "_" + organism.getStrain();
            fileName = fileName.replace("/", "-");
            fileName = fileName.replace(" ", "_");
            //System.out.println(fileName);

            ///home/mariana/glassfish-4.1/glassfish/domains/domain1/config
            String rnaRegfile = "/home/mariana/glassfish-4.1/glassfish/domains/domain1/config" + "/" + fileName + "_rna_regulation.csv";
            String rnafile = "/home/mariana/glassfish-4.1/glassfish/domains/domain1/config" + "/" + fileName + "_rna.csv";
            String rnaExperimentalfile = "/home/mariana/glassfish-4.1/glassfish/domains/domain1/config" + "/" + fileName + "_rna_experimental.csv";

            try {

                File f = new File(rnaRegfile);
                if (!f.exists() && !f.isDirectory()) {
                    System.out.println("creating: " + rnaRegfile);
                    rnaFile.createRnaRegulationsFile(genomes[i], rnaRegfile);
                } else {
                    System.out.println("NOT creating: " + rnaRegfile);
                }

                f = new File(rnafile);
                if (!f.exists() && !f.isDirectory()) {
                    System.out.println("creating: " + rnafile);
                    rnaFile.createRnaFile(genomes[i], rnafile);
                } else {
                    System.out.println("NOT creating: " + rnafile);
                }

                f = new File(rnaExperimentalfile);
                if (!f.exists() && !f.isDirectory()) {
                    if (organism.getType().equals("model")) {
                        System.out.println("creating: " + rnaExperimentalfile);
                        rnaFile.createRnaFile(genomes[i], rnaExperimentalfile, "experimental");
                    }
                } else {
                    System.out.println("NOT creating: " + rnaExperimentalfile);
                }

            } catch (IOException ex) {
                Logger.getLogger(RnaFile.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("rnaRegfile: " + rnaRegfile);
            System.out.println("rnafile: " + rnafile);

        }

        //rnaFile.createRnaFile(1226);
    }

    public List<String> bringRnaFiles(String type) {
        List<String> files = new ArrayList<>();
        List<Genome> genomes = new ArrayList<>();
        SmallRnaDAO rDAO = new SmallRnaDAO();

        if (type.equals("all")) {

            //get all genomes with small rnas that are model genomes. 
            genomes = rDAO.bringGenomes();

        } else if (type.equals("model")) {
            //get all genomes with small rnas

            genomes = rDAO.bringGenomesByType("model");
        }

        for (Genome genome : genomes) {
            System.out.println(genome.toString());

            String rnaFile = bringRnaFile(genome.getId());
            System.out.println("rnaFile: " + rnaFile);
            String rnaRegulationFile = bringRnaRegFile(genome.getId());
            System.out.println("rnaRegulationFile: " + rnaRegulationFile);

            files.add(rnaFile);
            files.add(rnaRegulationFile);

        }

        return files;
    }
    //findByGenomeType

    public String bringRnaFile(Integer id) {
        GenomeDAO genomeDAO = new GenomeDAO();
        Genome genome = genomeDAO.findById(id);
        Organism organism = genome.getOrganism();
        String fileName = "";
        System.out.println("organism: " + organism.getGenera() + " " + organism.getSpecies() + " " + organism.getStrain());
        fileName = organism.getGenera().charAt(0) + "_" + organism.getSpecies().charAt(0) + "_" + organism.getStrain();
        fileName = fileName.replace("/", "-");
        fileName = fileName.replace(" ", "_");
        //System.out.println(fileName);

        ///home/mariana/glassfish-4.1/glassfish/domains/domain1/config
        //String rnaRegfile = "/home/mariana/glassfish-4.1/glassfish/domains/domain1/config" + "/" + fileName + "_rna_regulation.csv";
        //return "/home/mariana/glassfish-4.1/glassfish/domains/domain1/config" + "/" + fileName + "_rna.csv";
        return fileName + "_rna.csv";

    }

    public String bringRnaFileExperimental(Integer id) {
        GenomeDAO genomeDAO = new GenomeDAO();
        Genome genome = genomeDAO.findById(id);
        Organism organism = genome.getOrganism();
        String fileName = "";
        System.out.println("organism: " + organism.getGenera() + " " + organism.getSpecies() + " " + organism.getStrain());
        fileName = organism.getGenera().charAt(0) + "_" + organism.getSpecies().charAt(0) + "_" + organism.getStrain();
        fileName = fileName.replace("/", "-");
        fileName = fileName.replace(" ", "_");
        //System.out.println(fileName);

        ///home/mariana/glassfish-4.1/glassfish/domains/domain1/config
        //String rnaRegfile = "/home/mariana/glassfish-4.1/glassfish/domains/domain1/config" + "/" + fileName + "_rna_regulation.csv";
        //return "/home/mariana/glassfish-4.1/glassfish/domains/domain1/config" + "/" + fileName + "_rna.csv";
        return fileName + "_rna_experimental.csv";

    }

    public String bringRnaRegFile(Integer id) {
        GenomeDAO genomeDAO = new GenomeDAO();
        Genome genome = genomeDAO.findById(id);
        Organism organism = genome.getOrganism();
        String fileName = "";
        System.out.println("organism: " + organism.getGenera() + " " + organism.getSpecies() + " " + organism.getStrain());
        fileName = organism.getGenera().charAt(0) + "_" + organism.getSpecies().charAt(0) + "_" + organism.getStrain();
        fileName = fileName.replace("/", "-");
        fileName = fileName.replace(" ", "_");
        //System.out.println(fileName);

        ///home/mariana/glassfish-4.1/glassfish/domains/domain1/config
        //return "/home/mariana/glassfish-4.1/glassfish/domains/domain1/config" + "/" + fileName + "_rna_regulation.csv";
        return fileName + "_rna_regulation.csv";

    }

    public void createRnaFile(Integer genome, String path) throws IOException {

        SmallRnaDAO sRnaDAO = new SmallRnaDAO();
        List<SmallRna> smallRnaList = sRnaDAO.findByGenome(genome);

        FileWriter fileWriter = new FileWriter(path);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        String header = "locus_tag" + "\t" + "evidence" + "\t" + "srna_class" + "\t" + "start_position"
                + "\t" + "end_position" + "\t" + "genome"
                + "\t" + "orientation" + "\t"
                + "\t" + "evidence_functional" + "\t" + "functional_rna" + "\t" + "sequence";
        //System.out.println(header);
        printWriter.println(header);
        for (SmallRna smallRna : smallRnaList) {
            //System.out.println(smallRna.toFile());
            printWriter.println(smallRna.toFile());
        }

        printWriter.close();

    }

    public void createRnaFile(Integer genome, String path, String type) throws IOException {

        SmallRnaDAO sRnaDAO = new SmallRnaDAO();
        List<SmallRna> smallRnaList = sRnaDAO.findByGenomeType(genome, type);

        for (SmallRna smallRna : smallRnaList) {
            System.out.println(smallRna);
        }
        
        FileWriter fileWriter = new FileWriter(path);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        String header = "locus_tag" + "\t" + "evidence" + "\t" + "srna_class" + "\t" + "start_position"
                + "\t" + "end_position" + "\t" + "genome"
                + "\t" + "orientation" + "\t"
                + "\t" + "evidence_functional" + "\t" + "functional_rna" + "\t" + "sequence";
        //System.out.println(header);
        printWriter.println(header);
        for (SmallRna smallRna : smallRnaList) {
            //System.out.println(smallRna.toFile());
            printWriter.println(smallRna.toFile());
        }

        printWriter.close();

    }

    public void createRnaRegulationsFile(Integer genome, String path) throws IOException {
        RnaInteractionDAO rnaInteractionDAO = new RnaInteractionDAO();
        List<RnaInteraction> rnaRegulations = rnaInteractionDAO.findByGenome(genome);

        FileWriter fileWriter = new FileWriter(path);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        String header = "srna\t" + "mrna\t" + "rank\t" + "copra_pvalue\t"
                + "copra_fdr\t" + "energy\t"
                + "inta_pvalue\t" + "position_mrna\t" + "position_ncrna\t"
                + "interaction\t" + "position_seed_mrna\t"
                + "position_seed_ncrna\t" + "hybridization_energ\t"
                + "unfolding_energy_mrna\t" + "unfolding_energy_ncrna\t";

        printWriter.println(header);
        //System.out.println(header);
        System.out.println(rnaRegulations.size());
        for (RnaInteraction rnaRegulation : rnaRegulations) {
            // System.out.println(rnaRegulation.toFile());
            printWriter.println(rnaRegulation.toFile());
        }

        printWriter.close();
    }

}
