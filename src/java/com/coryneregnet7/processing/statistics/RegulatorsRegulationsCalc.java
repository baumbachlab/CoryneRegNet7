/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.statistics;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.OrganismDAO;
import com.coryneregnet7.dao.PredictedRegulatoryInteractionDAO;
import com.coryneregnet7.dao.RegulatorsRegulationsDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.model.RegulatorsRegulations;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Organism;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author mariana
 */
public class RegulatorsRegulationsCalc {
    
    public void calculate() {

        RegulatorsRegulationsCalc rrTest = new RegulatorsRegulationsCalc();
        rrTest.save();
//        RegulatorsRegulationsDAO rDAO = new RegulatorsRegulationsDAO();
//        RegulatorsRegulations regulatorsRegulation = new RegulatorsRegulations();
//        OrganismDAO orgDAO = new OrganismDAO();
//        Organism org = orgDAO.findById(1185);
//        regulatorsRegulation = rDAO.findByOrganism(org.getId());
//
//        System.out.println(regulatorsRegulation.toString());
        //rrTest.save();
        //        RegulatorsRegulationsDAO rDAO = new RegulatorsRegulationsDAO();
        //        ArrayList<RegulatorsRegulations> regulatorsRegulations = new ArrayList<>();
        //
        //        regulatorsRegulations = (ArrayList<RegulatorsRegulations>) rDAO.listAll();
        //        for (RegulatorsRegulations regulatorsRegulation : regulatorsRegulations) {
        //            System.out.println(regulatorsRegulation.toString());
        //        }
    }

    public void save() {
//        RegulatorsRegulationsDAO rDAO = new RegulatorsRegulationsDAO();
//        RegulatorsRegulations regulatorsRegulations = new RegulatorsRegulations();
//        regulatorsRegulations.setNumActivators(50);
//        regulatorsRegulations.setNumRepressors(60);
//        regulatorsRegulations.setNumDual(30);
//        regulatorsRegulations.setNumActivations(60);
//        regulatorsRegulations.setNumRepressions(80);
//        regulatorsRegulations.setType("strain");
//        Genome genome = new Genome(1172);
//        regulatorsRegulations.setGenome(genome);
//        regulatorsRegulations.setDatabase("experimental");

        GeneDAO geneDAO = new GeneDAO();
        Integer numOfActivators;
        Integer numOfRepressors;
        Integer numOfDuals;
        Integer activations;
        Integer repressions;
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        ArrayList<Genome> genomes = new ArrayList<>();
        GenomeDAO genomeDAO = new GenomeDAO();
        RegulatorsRegulationsDAO rrDAO = new RegulatorsRegulationsDAO();
        RegulatorsRegulations regulatorsRegulations = new RegulatorsRegulations();
        RegulatorsRegulations regulatorsRegulationsAux = new RegulatorsRegulations();
        String type = "";
//        for (int i = 0; i < tfsRegAGeneModel.size(); i++) {
//            System.out.println("tfsRegAGeneModel.get(i): " + tfsRegAGeneModel.get(i));
//        }

//      Total in experimental database
        numOfActivators = 0;
        numOfDuals = 0;
        numOfRepressors = 0;
        numOfActivators = geneDAO.findNumberOfTFsModelOrganismsByRole("A") == null ? null : Math.toIntExact(geneDAO.findNumberOfTFsModelOrganismsByRole("A"));
        System.out.println("numOfActivators: " + numOfActivators);
        numOfDuals = geneDAO.findNumberOfTFsModelOrganismsByRole("Dual") == null ? null : Math.toIntExact(geneDAO.findNumberOfTFsModelOrganismsByRole("Dual"));
        System.out.println("numOfDuals: " + numOfDuals);
        numOfRepressors = geneDAO.findNumberOfTFsModelOrganismsByRole("R") == null ? null : Math.toIntExact(geneDAO.findNumberOfTFsModelOrganismsByRole("R"));
        System.out.println("numOfRepressors: " + numOfRepressors);

        activations = 0;
        repressions = 0;
        activations = riDAO.bringNumberOfRIsByRole("A") == null ? null : Math.toIntExact(riDAO.bringNumberOfRIsByRole("A"));
        repressions = riDAO.bringNumberOfRIsByRole("R") == null ? null : Math.toIntExact(riDAO.bringNumberOfRIsByRole("R"));
//                    System.out.println("numOfRis: " + numOfRis);
//                    System.out.println("activations: " + activations);
//                    System.out.println("repressions: " + repressions);

        regulatorsRegulations.setNumActivators(numOfActivators);
        regulatorsRegulations.setNumRepressors(numOfRepressors);
        regulatorsRegulations.setNumDual(numOfDuals);
        regulatorsRegulations.setNumActivations(activations);
        regulatorsRegulations.setNumRepressions(repressions);
        regulatorsRegulations.setType("database");
        regulatorsRegulations.setDatabase("experimental");

        regulatorsRegulationsAux = rrDAO.findByTypeAndDatabase(regulatorsRegulations.getType(), regulatorsRegulations.getDatabase());
        if (regulatorsRegulationsAux != null) {
            rrDAO.delete(regulatorsRegulationsAux);
        }
        try {
            rrDAO.save(regulatorsRegulations);
        } catch (Exception E) {
            System.out.println(E);
        }

//      Total in predicted database
        numOfActivators = 0;
        numOfDuals = 0;
        numOfRepressors = 0;
        numOfActivators = geneDAO.findNumberOfTFsByRole("A") == null ? null : Math.toIntExact(geneDAO.findNumberOfTFsByRole("A"));
        System.out.println("numOfActivators: " + numOfActivators);
        numOfDuals = geneDAO.findNumberOfTFsByRole("Dual") == null ? null : Math.toIntExact(geneDAO.findNumberOfTFsByRole("Dual"));
        System.out.println("numOfDuals: " + numOfDuals);
        numOfRepressors = geneDAO.findNumberOfTFsByRole("R") == null ? null : Math.toIntExact(geneDAO.findNumberOfTFsByRole("R"));
        System.out.println("numOfRepressors: " + numOfRepressors);

        activations = 0;
        repressions = 0;
        activations = riDAO.bringNumberOfRIsByRole("A") == null ? null : Math.toIntExact(riDAO.bringNumberOfRIsByRole("A"));
        repressions = riDAO.bringNumberOfRIsByRole("R") == null ? null : Math.toIntExact(riDAO.bringNumberOfRIsByRole("R"));
        activations += priDAO.bringNumberOfRIsByRole("A") == null ? null : Math.toIntExact(priDAO.bringNumberOfRIsByRole("A"));
        repressions += priDAO.bringNumberOfRIsByRole("R") == null ? null : Math.toIntExact(priDAO.bringNumberOfRIsByRole("R"));

        regulatorsRegulations.setNumActivators(numOfActivators);
        regulatorsRegulations.setNumRepressors(numOfRepressors);
        regulatorsRegulations.setNumDual(numOfDuals);
        regulatorsRegulations.setNumActivations(activations);
        regulatorsRegulations.setNumRepressions(repressions);
        regulatorsRegulations.setType("database");
        regulatorsRegulations.setDatabase("predicted");

        regulatorsRegulationsAux = new RegulatorsRegulations();
        regulatorsRegulationsAux = rrDAO.findByTypeAndDatabase(regulatorsRegulations.getType(), regulatorsRegulations.getDatabase());
        if (regulatorsRegulationsAux != null) {
            rrDAO.delete(regulatorsRegulationsAux);
        }
        try {
            rrDAO.save(regulatorsRegulations);
        } catch (Exception E) {
            System.out.println(E);
        }

        genomes = (ArrayList<Genome>) genomeDAO.listAll();
        //Retrive the role of each TF
        if (genomes != null) {
            for (int i = 0; i < genomes.size(); i++) {

                numOfActivators = geneDAO.findNumberOfTFsByOrganismAndRole("A", genomes.get(i).getOrganism().getId()) == null ? null : Math.toIntExact(geneDAO.findNumberOfTFsByOrganismAndRole("A", genomes.get(i).getOrganism().getId()));
//                System.out.println("numOfActivators: " + numOfActivators + " of "
//                        + organisms.get(i).getGenera() + " " + organisms.get(i).getSpecies()
//                        + " " + organisms.get(i).getStrain());
                numOfDuals = geneDAO.findNumberOfTFsByOrganismAndRole("Dual", genomes.get(i).getOrganism().getId()) == null ? null : Math.toIntExact(geneDAO.findNumberOfTFsByOrganismAndRole("Dual", genomes.get(i).getOrganism().getId()));
//                System.out.println("numOfDuals: " + numOfDuals + " of "
//                        + organisms.get(i).getGenera() + " " + organisms.get(i).getSpecies()
//                        + " " + organisms.get(i).getStrain());
                numOfRepressors = geneDAO.findNumberOfTFsByOrganismAndRole("R", genomes.get(i).getOrganism().getId()) == null ? null : Math.toIntExact(geneDAO.findNumberOfTFsByOrganismAndRole("R", genomes.get(i).getOrganism().getId()));
//                System.out.println("numOfRepressors: " + numOfRepressors + " of "
//                        + organisms.get(i).getGenera() + " " + organisms.get(i).getSpecies()
//                        + " " + organisms.get(i).getStrain());

                activations = 0;
                repressions = 0;
//                System.out.println("--------------------------------");
//                System.out.println(organisms.get(i).getGenera() + " " + organisms.get(i).getSpecies()
//                        + " " + organisms.get(i).getStrain());
//                System.out.println("numOfRis: " + numOfRis);
//                System.out.println("activations: " + activations);
//                System.out.println("repressions: " + repressions);

                if (genomes.get(i).getOrganism().getType().equals("model")) {
                    activations = riDAO.bringNumberOfRIsByOrganismAndRole("A", genomes.get(i).getOrganism().getId()) == null ? null : Math.toIntExact(riDAO.bringNumberOfRIsByOrganismAndRole("A", genomes.get(i).getOrganism().getId()));
                    repressions = riDAO.bringNumberOfRIsByOrganismAndRole("R", genomes.get(i).getOrganism().getId()) == null ? null : Math.toIntExact(riDAO.bringNumberOfRIsByOrganismAndRole("R", genomes.get(i).getOrganism().getId()));
//                System.out.println("numOfRis: " + numOfRis);
//                System.out.println("activations: " + activations);
//                System.out.println("repressions: " + repressions);    
                } else {
                    activations += priDAO.bringNumberOfRIsByOrganismAndRole("A", genomes.get(i).getOrganism().getId()) == null ? null : Math.toIntExact(priDAO.bringNumberOfRIsByOrganismAndRole("A", genomes.get(i).getOrganism().getId()));
                    repressions += priDAO.bringNumberOfRIsByOrganismAndRole("R", genomes.get(i).getOrganism().getId()) == null ? null : Math.toIntExact(priDAO.bringNumberOfRIsByOrganismAndRole("R", genomes.get(i).getOrganism().getId()));
//                    System.out.println("numOfRis: " + numOfRis);
//                    System.out.println("activations: " + activations);
//                    System.out.println("repressions: " + repressions);
                }

                if (numOfActivators != 0 && numOfDuals != 0 && numOfRepressors != null) {
                    regulatorsRegulations.setNumActivators(numOfActivators);
                    regulatorsRegulations.setNumRepressors(numOfRepressors);
                    regulatorsRegulations.setNumDual(numOfDuals);
                    regulatorsRegulations.setNumActivations(activations);
                    regulatorsRegulations.setNumRepressions(repressions);
                    regulatorsRegulations.setType("strain");
                    regulatorsRegulations.setGenome(genomes.get(i));
                    type = "";
                    if (genomes.get(i).getOrganism().getType().equals("target")) {
                        type = "predicted";
                    } else {
                        type = "experimental";
                    }
                    regulatorsRegulations.setDatabase(type);

                    regulatorsRegulationsAux = new RegulatorsRegulations();
                    regulatorsRegulationsAux = rrDAO.findByOrganism(regulatorsRegulations.getGenome().getOrganism().getId());
                    System.out.println("Found: " + regulatorsRegulationsAux);
                    if (regulatorsRegulationsAux != null) {
                        rrDAO.delete(regulatorsRegulationsAux);
                        System.out.println("Deleted: " + regulatorsRegulationsAux);
                    }
                    try {
                        rrDAO.save(regulatorsRegulations);
                        System.out.println("Saved: " + regulatorsRegulations);
                    } catch (Exception E) {
                        System.out.println(E);
                    }
                }
            }
        }
    }

    public void update() {
        RegulatorsRegulationsDAO rDAO = new RegulatorsRegulationsDAO();
        RegulatorsRegulations regulatorsRegulations = rDAO.findById(1);
        System.out.println("Before update: " + regulatorsRegulations);
        regulatorsRegulations.setDatabase("predicted");
        rDAO.update(regulatorsRegulations);
        regulatorsRegulations = rDAO.findById(1);
        System.out.println("After update: " + regulatorsRegulations);

    }

    public void delete() {
        RegulatorsRegulationsDAO rDAO = new RegulatorsRegulationsDAO();
        ArrayList<RegulatorsRegulations> regulatorsRegulations = new ArrayList<>();

        regulatorsRegulations = (ArrayList<RegulatorsRegulations>) rDAO.listAll();
        for (RegulatorsRegulations regulatorsRegulation : regulatorsRegulations) {
            System.out.println("Before update: " + regulatorsRegulation.toString());
        }

        RegulatorsRegulations regulatorsRegulation = rDAO.findById(1);
        regulatorsRegulation.setDatabase("predicted");
        rDAO.update(regulatorsRegulations);
        regulatorsRegulation = rDAO.findById(1);

        regulatorsRegulations = (ArrayList<RegulatorsRegulations>) rDAO.listAll();
        for (Iterator<RegulatorsRegulations> it = regulatorsRegulations.iterator(); it.hasNext();) {
            regulatorsRegulation = it.next();
            System.out.println("After update: " + regulatorsRegulation.toString());
        }
    }

}
