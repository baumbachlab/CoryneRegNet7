/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.output;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GeneOperonDAO;
import com.coryneregnet7.dao.OperonDAO;
import com.coryneregnet7.dao.OrganismDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.GeneOperon;
import com.coryneregnet7.model.Operon;
import com.coryneregnet7.model.Organism;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author doglas
 */
public class OperonsFile {

    public List operonsFileAllOrganisms(String type) {

        OperonDAO opDAO = new OperonDAO();
        Operon operon = new Operon();
        GeneOperonDAO goDAO = new GeneOperonDAO();
        List<GeneOperon> genesOperon = new ArrayList<>();
        OrganismDAO organismDAO = new OrganismDAO();
        List<Organism> organisms = new ArrayList();
        String operonsFileName = "";
        List<String> operonsFilesList = new ArrayList<>();
        File file;
        List<Integer> operons = new ArrayList<>();
        String orientation, locusTags;
        Gene gene = new Gene();
        GeneDAO geneDAO = new GeneDAO();

        if (type.equals("model")) {
            organisms = organismDAO.findByType("model");
        } else {
            organisms = organismDAO.listAll();
        }

        for (Organism organism : organisms) {
            System.out.println("organism: " + organism.getGenera() + " " + organism.getSpecies() + " " + organism.getStrain());
            operonsFileName = organism.getGenera().charAt(0) + "" + organism.getSpecies().charAt(0) + "" + organism.getStrain() + "_operons";
            operonsFileName = operonsFileName.replace("/", "-");
            operonsFileName = operonsFileName.replace(" ", "_");
            System.out.println(operonsFileName);
            file = new File(System.getProperty("user.dir") + "/" + operonsFileName + ".csv");
            System.out.println("Operons file:");
            System.out.println("file: " + file);
            operonsFilesList.add(operonsFileName);

            if (file.exists()) {
                System.out.println("File exists!!!!!!!!!!!!");
            } else {
                PrintWriter printWriter = null;

                try {
                    printWriter = new PrintWriter(file);
                    printWriter.println("Operon,Orientation,Genes");

                    operons = goDAO.findOperonsByOrganism(organism.getId());
                    //System.out.println("operons.size(): " + operons.size());
                    for (Integer op : operons) {
                        operon = opDAO.findById(op);
                        genesOperon = goDAO.findByOperon(op);

                        if (operon.getOrientation().equals("forward")) {
                            orientation = "+";
                        } else {
                            orientation = "-";
                        }

                        locusTags = "";
                        for (int i = 0; i < genesOperon.size(); i++) {
                            gene = geneDAO.findById(genesOperon.get(i).getGeneOperonPK().getGene());
                            if (i == genesOperon.size() - 1) {
                                locusTags += gene.getLocusTag();
                            } else {
                                locusTags += gene.getLocusTag() + ",";
                            }
                        }
                        printWriter.println(">" + operon.getName() + "," + orientation + "," + locusTags);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    if (printWriter != null) {
                        printWriter.close();
                    }
                }
            }
        }

        return operonsFilesList;
    }

    public List operonsFileByOrganism(int organismId) {

        OperonDAO opDAO = new OperonDAO();
        Operon operon = new Operon();
        GeneOperonDAO goDAO = new GeneOperonDAO();
        List<GeneOperon> genesOperon = new ArrayList<>();
        OrganismDAO organismDAO = new OrganismDAO();
        Organism organism = organismDAO.findById(organismId);
        String operonsFileName = "";
        List<String> operonsFilesList = new ArrayList<>();
        File file;
        List<Integer> operons = new ArrayList<>();
        String orientation, locusTags;
        Gene gene = new Gene();
        GeneDAO geneDAO = new GeneDAO();

        System.out.println("organism: " + organism.getGenera() + " " + organism.getSpecies() + " " + organism.getStrain());
        operonsFileName = organism.getGenera().charAt(0) + "_" + organism.getSpecies().charAt(0) + "_" + organism.getStrain() + "_operons";
        operonsFileName = operonsFileName.replace("/", "-");
        operonsFileName = operonsFileName.replace(" ", "_");
        System.out.println(operonsFileName);
        file = new File(System.getProperty("user.dir") + "/" + operonsFileName + ".csv");
        operonsFilesList.add(operonsFileName);

        if (file.exists()) {
            System.out.println("File exists!!!!!!!!!!!!");
        } else {
            PrintWriter printWriter = null;

            try {
                printWriter = new PrintWriter(file);
                printWriter.println("Operon,Orientation,Genes");

                operons = goDAO.findOperonsByOrganism(organism.getId());
                //System.out.println("operons.size(): " + operons.size());
                for (Integer op : operons) {
                    operon = opDAO.findById(op);
                    genesOperon = goDAO.findByOperon(op);

                    if (operon.getOrientation().equals("forward")) {
                        orientation = "+";
                    } else {
                        orientation = "-";
                    }

                    locusTags = "";
                    for (int i = 0; i < genesOperon.size(); i++) {
                        gene = geneDAO.findById(genesOperon.get(i).getGeneOperonPK().getGene());
                        if (i == genesOperon.size() - 1) {
                            locusTags += gene.getLocusTag();
                        } else {
                            locusTags += gene.getLocusTag() + ",";
                        }
                    }
                    printWriter.println(">" + operon.getName() + "," + orientation + "," + locusTags);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (printWriter != null) {
                    printWriter.close();
                }
            }
        }

        return operonsFilesList;
    }

}