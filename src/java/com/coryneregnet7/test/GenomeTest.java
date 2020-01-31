/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.model.Genome;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.OrganismDAO;
import com.coryneregnet7.model.Organism;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariana
 */
public class GenomeTest {

    GenomeDAO genomeDAO;

    public static void main(String[] args) {

        GenomeTest pt = new GenomeTest();

        pt.listAll();

    }

    public void save(Genome genome) {
        genomeDAO = new GenomeDAO();

        try {
            genomeDAO.save(genome);
        } catch (Exception E) {
            System.out.println(E);
        }

    }

    public Genome findById(Integer id) {
        genomeDAO = new GenomeDAO();
        Genome genome = genomeDAO.findById(id);
        return genome;
    }

    public void findByOrganism(Integer organism) {
        genomeDAO = new GenomeDAO();
        Genome genome = genomeDAO.findById(organism);
        System.out.println(genome.getNcbiAccessionNumber());;
    }

    public void update(Genome genome) {
        genomeDAO = new GenomeDAO();
        genomeDAO.update(genome);
    }

    public void findByOrgnismType() {
        genomeDAO = new GenomeDAO();
        List<Genome> generics = genomeDAO.findByOrgnismType("model");
        for (Genome generic : generics) {
            System.out.println(generic.toString());
        }

    }

    public void listAll() {
        genomeDAO = new GenomeDAO();
        List<Genome> generics = genomeDAO.listAll();
        for (Genome generic : generics) {
            System.out.println(generic.toString());
        }

    }

    public void delete(int id) {
        genomeDAO = new GenomeDAO();
        Genome generic = (Genome) genomeDAO.findById(id);
        genomeDAO.delete(generic);
    }

//    public void bringNumberOfGenomes() {
//        genomeDAO = new GenomeDAO();
//        Long n = genomeDAO.bringNumberOfGenomes();
//        System.out.println("Number of genomes: " + n);
//    }
}
