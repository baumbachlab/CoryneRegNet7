/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GeneOperonDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.model.Operon;
import com.coryneregnet7.dao.OperonDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.GeneOperon;
import com.coryneregnet7.model.Genome;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author mariana
 */
public class OperonTest {

    OperonDAO operonDAO;

    public static void main(String[] args) throws IOException {

        //OperonTest pt = new OperonTest();
//        pt.delete();
        //pt.listAll();

        //pt.deleteOperonsByGenome();
        GeneOperonDAO geneOperonDAO = new GeneOperonDAO();
        
        
        GenomeDAO genomeDAO = new GenomeDAO();
        List<Genome> genomes = new ArrayList<Genome>();
        
        genomes = genomeDAO.listAll();
        for (Genome genome : genomes) {
              Long numOp = geneOperonDAO.countOpByGenome(genome.getId());
              System.out.println("genome "+genome.getGenomeName()+" "+genome.getId());
              System.out.println("numOp "+numOp);
              if(numOp.intValue()==0){
                  System.out.println("-------------É ZERO!");
              }
        }
        

        //Operon operon = pt.findById(1);
//        Operon operon = new Operon();
//        operon.setName("OP_NnTargetT1_0002");
//        operon.setOrientation("foward");
//        pt.save(operon);
        //pt.delete(operon);
//        Operon o1 = pt.findById(1);
//        System.out.println(o1.toString());
//             Operon operon = new Operon();
//        operon.setSpecies("kakao");
//         pt.save(operon);
    }

    public void deleteOperonsByGenome() {
        Genome genome = new Genome();
        GenomeDAO genomeDAO = new GenomeDAO();
        genome = genomeDAO.findById(1358);
        OperonDAO operonDAO = new OperonDAO();
        GeneOperonDAO geneOperonDAO = new GeneOperonDAO();

        List<GeneOperon> geneOperonList = geneOperonDAO.findByOrganism(genome.getOrganism().getId());
        Hashtable<Operon, List<GeneOperon>> operons = new Hashtable();
        for (int i = 0; i < geneOperonList.size(); i++) {

            Operon operon = operonDAO.findById(geneOperonList.get(i).getGeneOperonPK().getOperon());

            if (operon != null) {
                List<GeneOperon> geneOperon = geneOperonDAO.findByOperon(operon.getId());
                for (GeneOperon geneOperon1 : geneOperon) {

                    if (!operons.containsKey(operon)) {
                        System.out.println(operon.toString());
                        operons.put(operon, geneOperon);
                        System.out.print(operon.getName() + "\t");

                        for (GeneOperon geneOperonSingle : geneOperon) {
                            System.out.println("---" + geneOperon1);
                            //geneOperonDAO.delete(geneOperonSingle);
                        }
                        //operonDAO.delete(operon);
                        //System.out.println("");

                    }
                }
            }
            //System.out.println(geneOperon.size());
        }
        System.out.println(operons.size());
    }

    public void saveOperonsByGenome() throws IOException {
        GenomeDAO genomeDAO = new GenomeDAO();
        List<Genome> genomes = genomeDAO.findByOrgnismType("target");

        // List<Genome> genomes = new ArrayList<>();
        //Genome genomeG = genomeDAO.findById(2465);
        // genomes.add(genomeG);
        OperonDAO operonDAO = new OperonDAO();
        GeneOperonDAO geneOperonDAO = new GeneOperonDAO();
        GeneDAO geneDAO = new GeneDAO();
        for (Genome genome : genomes) {
            System.out.println("genome -> " + genome.toString() + "\n");
            FileWriter fileWriter = new FileWriter(new File(genome.getGbffFile().replace("_genomic.gbff", ".op")));
            PrintWriter printWriter = new PrintWriter(fileWriter);

            List<GeneOperon> geneOperonList = geneOperonDAO.findByOrganism(genome.getOrganism().getId());
            Hashtable<Operon, List<GeneOperon>> operons = new Hashtable();
            for (int i = 0; i < geneOperonList.size(); i++) {

                Operon operon = operonDAO.findById(geneOperonList.get(i).getGeneOperonPK().getOperon());
                List<GeneOperon> goByOperon = geneOperonDAO.findByOperon(operon.getId());

                if (!operons.containsKey(operon)) {
                    operons.put(operon, goByOperon);
                    //System.out.print(operon.getName() + "\t");
                    String orientation = "";
                    if (operon.getOrientation().equals("reverse")) {
                        orientation = "-";
                    } else if (operon.getOrientation().equals("forward")) {
                        orientation = "+";
                    }
                    //System.out.print(">" + operon.getName() + "\t" + orientation);
                    printWriter.print(">" + operon.getName() + "\t" + orientation);

                    for (GeneOperon geneOperon : goByOperon) {
                        Gene gene = geneDAO.findById(geneOperon.getGeneOperonPK().getGene());
                        // System.out.print("\t" + gene.getLocusTag());
                        printWriter.print("\t" + gene.getLocusTag());
                    }
                    //System.out.println("");
                    printWriter.print("\n");
                }
            }

            printWriter.close();
            fileWriter.close();
        }

    }

    public void save(Operon operon) {
        operonDAO = new OperonDAO();

        try {
            operonDAO.save(operon);
        } catch (Exception E) {
            System.out.println(E);
        }

    }

    public Operon findById(Integer id) {
        operonDAO = new OperonDAO();
        Operon operon = operonDAO.findById(id);
        return operon;
    }

    public void update(Operon operon) {
        operonDAO = new OperonDAO();
        operonDAO.update(operon);
    }

    public void listAll() {
        operonDAO = new OperonDAO();
        List<Operon> operonrics = operonDAO.listAll();
        for (Operon operonric : operonrics) {
            System.out.println(operonric.toString());
        }

    }

    public void delete(Operon operon) {
        operonDAO = new OperonDAO();
        operonDAO.delete(operon);
    }
}
