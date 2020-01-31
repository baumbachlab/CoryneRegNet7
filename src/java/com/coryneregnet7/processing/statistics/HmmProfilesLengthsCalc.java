/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.statistics;

import com.coryneregnet7.test.*;
import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.OrganismDAO;
import com.coryneregnet7.dao.PredictedRegulatoryInteractionDAO;
import com.coryneregnet7.dao.HmmProfilesLengthsDAO;
import com.coryneregnet7.dao.RefStatisticsDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.model.CoregulatorsStatistics;
import com.coryneregnet7.model.HmmProfilesLengths;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Organism;
import com.coryneregnet7.model.RefStatistics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author mariana
 */
public class HmmProfilesLengthsCalc {

    public static void main(String[] args) {
        HmmProfilesLengthsCalc hmmPLCalc = new HmmProfilesLengthsCalc();
        //hmmPLCalc.calculate();
        HmmProfilesLengths hmmProfilesLengths = new HmmProfilesLengths();
        hmmProfilesLengths.setIdRefStatistics(null);
        hmmProfilesLengths.setFrequency(111111);
        hmmProfilesLengths.setProfileLengthRange(6167166);
        HmmProfilesLengthsDAO hmmProfilesLengthsDAO = new HmmProfilesLengthsDAO();
        hmmProfilesLengthsDAO.save(hmmProfilesLengths);
    }

    public void calculate() {

        HmmProfilesLengthsCalc hplTest = new HmmProfilesLengthsCalc();

        ArrayList<Genome> genomes = new ArrayList<>();
        LinkedHashMap<String, Integer> organismsId = new LinkedHashMap<>();
        GenomeDAO genomeDAO = new GenomeDAO();
        LinkedHashMap<String, TreeMap<Integer, Integer>> hmmProfilesLen = new LinkedHashMap<>();
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        PredictedRegulatoryInteractionDAO priDAO = new PredictedRegulatoryInteractionDAO();
        List<Gene> tfGenes = new ArrayList<>();
        TreeMap<Integer, Integer> hmmProfilesLenOrganism = new TreeMap<>();
        Integer hmmLength = 0;
        HmmProfilesLengthsDAO hmmPLDAO = new HmmProfilesLengthsDAO();
        HmmProfilesLengths hmmProfilesLengths = new HmmProfilesLengths();
        ArrayList<HmmProfilesLengths> hmmProfilesLengthsAux = new ArrayList<>();
        RefStatisticsDAO rsDAO = new RefStatisticsDAO();
        RefStatistics refStatistics = new RefStatistics();

        //System.out.println("---------------------------------------");
        hmmProfilesLenOrganism = new TreeMap<>();
        tfGenes = riDAO.findByDistinctTF();
        for (int k = 0; k < tfGenes.size(); k++) {
            if (tfGenes.get(k).getProfileLength() != null && !tfGenes.get(k).getProfileLength().equals("")) {
                hmmLength = tfGenes.get(k).getProfileLength();
                hmmProfilesLenOrganism = hplTest.hmmProfLenObject(hmmProfilesLenOrganism, hmmLength);
            }
        }

        refStatistics = rsDAO.findByTypeAndDatabase("database", "experimental");
        if (refStatistics == null) {
            refStatistics = new RefStatistics();
            refStatistics.setType("database");
            refStatistics.setDatabase("experimental");
            try {
                rsDAO.save(refStatistics);
                System.out.println("Salvei 1 " + refStatistics);

            } catch (Exception E) {
                System.out.println(E);
            }
        }

        hmmProfilesLengthsAux = hmmPLDAO.findByRefStatistics(refStatistics.getId());
        if (hmmProfilesLengthsAux != null) {
            for (HmmProfilesLengths hmmProfilesLengths1 : hmmProfilesLengthsAux) {
                if (hmmProfilesLengths1 != null) {
                    hmmPLDAO.delete(hmmProfilesLengths1);
                }
            }
        }

        for (Map.Entry<Integer, Integer> hmmLen : hmmProfilesLenOrganism.entrySet()) {
            System.out.println("All experiemntal database:");
            System.out.println("Key: " + hmmLen.getKey());
            System.out.println("Value: " + hmmLen.getValue());

            hmmProfilesLengths.setFrequency(hmmLen.getValue());
            hmmProfilesLengths.setProfileLengthRange(hmmLen.getKey());
            hmmProfilesLengths.setIdRefStatistics(refStatistics);

            //hmmProfilesLengths.setDatabase(type);
            try {
                hmmPLDAO.save(hmmProfilesLengths);
            } catch (Exception E) {
                System.out.println(E);
            }
        }

        tfGenes = new ArrayList<>();
        tfGenes = priDAO.findByDistinctTF();
        for (int k = 0; k < tfGenes.size(); k++) {
            if (tfGenes.get(k).getProfileLength() != null && !tfGenes.get(k).getProfileLength().equals("")) {
                hmmLength = tfGenes.get(k).getProfileLength();
                hmmProfilesLenOrganism = hplTest.hmmProfLenObject(hmmProfilesLenOrganism, hmmLength);
            }
        }

        refStatistics = rsDAO.findByTypeAndDatabase("database", "predicted");
        if (refStatistics == null) {
            refStatistics = new RefStatistics();
            refStatistics.setType("database");
            refStatistics.setDatabase("predicted");
            try {
                rsDAO.save(refStatistics);
                System.out.println("Salvei 2 " + refStatistics);
            } catch (Exception E) {
                System.out.println(E);
            }
        }

        hmmProfilesLengthsAux = hmmPLDAO.findByRefStatistics(refStatistics.getId());
        if (hmmProfilesLengthsAux != null) {
            for (HmmProfilesLengths hmmProfilesLengths1 : hmmProfilesLengthsAux) {
                if (hmmProfilesLengths1 != null) {
                    hmmPLDAO.delete(hmmProfilesLengths1);
                }
            }
        }

        for (Map.Entry<Integer, Integer> hmmLen : hmmProfilesLenOrganism.entrySet()) {
            System.out.println("All experiemntal database:");
            System.out.println("Key: " + hmmLen.getKey());
            System.out.println("Value: " + hmmLen.getValue());

            hmmProfilesLengths.setFrequency(hmmLen.getValue());
            hmmProfilesLengths.setProfileLengthRange(hmmLen.getKey());
            hmmProfilesLengths.setIdRefStatistics(refStatistics);

            //hmmProfilesLengths.setDatabase(type);
            try {
                hmmPLDAO.save(hmmProfilesLengths);
            } catch (Exception E) {
                System.out.println(E);
            }
        }

        genomes = (ArrayList<Genome>) genomeDAO.listAll();

        if (genomes != null) {
            int count;
            for (int i = 0; i < genomes.size(); i++) {
                hmmProfilesLenOrganism = new TreeMap<>();
                refStatistics = rsDAO.findByOrganism(genomes.get(i).getOrganism().getId());
                System.out.println("refStatistics: " + refStatistics.toString());
                System.out.println("Organism: " + genomes.get(i).getOrganism().toString());
                if (refStatistics == null) {
                    refStatistics = new RefStatistics();
                    refStatistics.setType("strain");
                    if (genomes.get(i).getOrganism().getType().equals("model")) {
                        refStatistics.setDatabase("experimental");
                    } else {
                        refStatistics.setDatabase("predicted");
                    }
                    refStatistics.setGenome(genomes.get(i));
                    try {
                        rsDAO.save(refStatistics);
                        System.out.println("Salvei 3 " + refStatistics);
                    } catch (Exception E) {
                        System.out.println(E);
                    }
                }

                tfGenes = new ArrayList<>();

                if (genomes.get(i).getOrganism().getType().equals("model")) {
                    tfGenes = riDAO.findByOrganismDistinctTF(genomes.get(i).getOrganism().getId());
                } else if (genomes.get(i).getOrganism().getType().equals("target")) {
                    tfGenes = priDAO.findByOrganismDistinctTF(genomes.get(i).getOrganism().getId());
                }
                for (int k = 0; k < tfGenes.size(); k++) {
                    if (tfGenes.get(k).getProfileLength() != null && !tfGenes.get(k).getProfileLength().equals("")) {
                        hmmLength = tfGenes.get(k).getProfileLength();
                        hmmProfilesLenOrganism = hplTest.hmmProfLenObject(hmmProfilesLenOrganism, hmmLength);
                    }
                }

                hmmProfilesLengthsAux = hmmPLDAO.findByRefStatistics(refStatistics.getId());
                if (hmmProfilesLengthsAux != null) {
                    for (HmmProfilesLengths hmmProfilesLengths1 : hmmProfilesLengthsAux) {
                        if (hmmProfilesLengths1 != null) {
                            hmmPLDAO.delete(hmmProfilesLengths1);
                        }
                    }
                }

                if (!hmmProfilesLenOrganism.isEmpty()) {
                    for (Map.Entry<Integer, Integer> hmmLen : hmmProfilesLenOrganism.entrySet()) {
                        System.out.println("All experiemntal database:");
                        System.out.println("Key: " + hmmLen.getKey());
                        System.out.println("Value: " + hmmLen.getValue());

                        hmmProfilesLengths.setFrequency(hmmLen.getValue());
                        hmmProfilesLengths.setProfileLengthRange(hmmLen.getKey());
                        hmmProfilesLengths.setIdRefStatistics(refStatistics);

                        //hmmProfilesLengths.setDatabase(type);
                        try {
                            hmmPLDAO.save(hmmProfilesLengths);
                        } catch (Exception E) {
                            System.out.println(E);
                        }
                    }
                }
            }
        }
    }

    public TreeMap hmmProfLenObject(TreeMap<Integer, Integer> hmmProfilesLenOrganism, int hmmLength) {

        Integer freqHmmLen;
        Integer range;
        if (hmmLength < 13) {
            range = 0;
        } else if (hmmLength < 21) {
            range = 1;
        } else if (hmmLength < 29) {
            range = 2;
        } else if (hmmLength < 37) {
            range = 3;
        } else if (hmmLength < 46) {
            range = 4;
        } else if (hmmLength < 60) {
            range = 5;
        } else {
            range = 6;
            //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            //System.out.println("This is bigger (hmmLength): " + hmmLength);
        }

        if (!hmmProfilesLenOrganism.isEmpty() && hmmProfilesLenOrganism.containsKey(range)) {
            freqHmmLen = hmmProfilesLenOrganism.get(range) + 1;
        } else {
            freqHmmLen = 1;
        }

        hmmProfilesLenOrganism.put(range, freqHmmLen);

        return hmmProfilesLenOrganism;
    }

    public void save() {

        HmmProfilesLengthsDAO csDAO = new HmmProfilesLengthsDAO();
        HmmProfilesLengths hmmProfilesLengths = new HmmProfilesLengths();
        RefStatisticsDAO refStatisticsDAO = new RefStatisticsDAO();
        RefStatistics refStatistics = refStatisticsDAO.findById(131);

        hmmProfilesLengths.setFrequency(5);
        hmmProfilesLengths.setProfileLengthRange(1);
        hmmProfilesLengths.setIdRefStatistics(refStatistics);

        //hmmProfilesLengths.setDatabase(type);
        try {
            csDAO.save(hmmProfilesLengths);
        } catch (Exception E) {
            System.out.println(E);
        }
    }

    public void update() {
        HmmProfilesLengthsDAO rDAO = new HmmProfilesLengthsDAO();
        HmmProfilesLengths hmmProfilesLengths = rDAO.findById(1);
        System.out.println("Before update: " + hmmProfilesLengths);
        hmmProfilesLengths.setFrequency(22);
        rDAO.update(hmmProfilesLengths);
        hmmProfilesLengths = rDAO.findById(1);
        System.out.println("After update: " + hmmProfilesLengths);

    }

    public void delete() {
        HmmProfilesLengthsDAO rDAO = new HmmProfilesLengthsDAO();
        ArrayList<HmmProfilesLengths> hmmProfilesLengths = new ArrayList<>();

        hmmProfilesLengths = (ArrayList<HmmProfilesLengths>) rDAO.listAll();
        for (HmmProfilesLengths regulatorsRegulation : hmmProfilesLengths) {
            System.out.println("Before update: " + regulatorsRegulation.toString());
        }

        HmmProfilesLengths hmmLen = rDAO.findById(3);
        rDAO.delete(hmmLen);

        hmmProfilesLengths = (ArrayList<HmmProfilesLengths>) rDAO.listAll();
        for (Iterator<HmmProfilesLengths> it = hmmProfilesLengths.iterator(); it.hasNext();) {
            hmmLen = it.next();
            System.out.println("After update: " + hmmLen.toString());
        }
    }

}
