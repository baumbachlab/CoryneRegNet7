/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.model.Gene;
import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.model.Genome;
//import com.oracle.util.Checksums;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariana
 */
public class GeneTest {

    GeneDAO geneDAO;

    public static void main(String[] args) {

        GeneDAO geneDAO = new GeneDAO();
//        List<Gene> genes = geneDAO.findByGenome(1456);
//        for (Gene gene : genes) {
//            System.out.println(gene.toString());
//            geneDAO.delete(gene);
//        }
        
        
////////        Gene gene = geneDAO.findById(1821465);
////////        System.out.println(gene.toString2());
////////        ///home/doglas/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/model
////////        gene.setHmmProfile("/data/coryne-genus/model/CgATCC13032-hmmer-profiles/cg0012.hmm");
////////           gene.setHmmLogo("");
////////         geneDAO.update(gene);
         
        
//        List<Gene> genes = geneDAO.findByGenome(1169);
//        for (Gene gene1 : genes) {
//            System.out.println("gene " + gene1);
//            if (gene1.getRole() != null && !gene1.getRole().isEmpty()) {
//                if (gene1.getRole().equals("Activator")) {
//                    System.out.println("-------------É ACTIVATOR");
//                    gene1.setRole("A");
//                    geneDAO.update(gene1);
//                } else if (gene1.getRole().equals("Repressor")) {
//                    System.out.println("-------------É Repressor");
//                    gene1.setRole("R");
//                    geneDAO.update(gene1);
//                }
//            }
//
//        }
        // System.out.println("GENE: "+gene.getHmmLogo());
//  
//        Long numberOfGenes = geneDAO.bringNumberOfModelGenes("model");
//        System.out.println("numberOfGenes: " + numberOfGenes);

        //Integer gene = geneDAO.findByGenomeMax(204);
        //System.out.println("gene max: " + gene.toString());
        //Integer gene1 = geneDAO.findByGenomeMin(204);
        //System.out.println("gene mix: " + gene1.toString());
//        ArrayList<Gene> tfs = (ArrayList<Gene>) geneDAO.findTranscriptionFactors();
//        for (Gene tf : tfs) {
//            System.out.println(tf.getRole());
//        }
        //System.out.println("gene mix: " + gene1.toString());

        /*
        GENE 16 - forward forward -gene15-> -gene16->
         */
//        List<Gene> genes1 = geneDAO.findByGenomeLocusTagEndPositionForward(126, "NNMOS1_00016", new BigInteger("99010"), new BigInteger("99590"));
//        for (Gene gene : genes1) {
//            System.out.println("findByGenomeLocusTagEndPositionForward:");
//            System.out.println(gene.toString());
//        }
//        List<Gene> genes = geneDAO.findByGenomeLocusTagStartPositionForward(126, "NNMOS1_00016", new BigInteger("99010"), new BigInteger("99590"));
//        for (Gene gene : genes) {
//            System.out.println("findByGenomeLocusTagStartPositionForward:");
//            System.out.println(gene.toString());
//        }
//        

        /*
        GENE 15
         */
//        pt.findByGenomeLocusTagEndPositionReverse(123, "NNMOS1_00015", new BigInteger("98647"), new BigInteger("99227"));
//       List<Gene> genes = geneDAO.findByGenomeLocusTagStartPositionReverse(123, "NNMOS1_00015", new BigInteger("98647"), new BigInteger("99227"));
//        for (Gene gene : genes) {
//            System.out.println(gene.toString());
//        }
        /*
        GENE 10
         */
        //pt.findByGenomeLocusTagEndPositionReverse(123, "NNMOS1_00010", new BigInteger("91433"), new BigInteger("90853"));
////"SELECT g FROM Gene g WHERE g.genome = :genome and g.locusTag not equals :locusTag and (g.endPosition > :promoterEnd and g.endPosition < :promoterStart)")
//        //reverse
//        List<Gene> genes = geneDAO.findByGenomeLocusTagStartPositionReverse(114, "NNMOS1_00010", new BigInteger("91037"), new BigInteger("91617"));
//        for (Gene gene : genes) {
//            System.out.println(gene.toString());
//        }
        /*
        GENE 14
         */
//         List<Gene> genes1 = geneDAO.findByGenomeLocusTagEndPositionForward(123, "NNMOS1_00014", new BigInteger("99484"), new BigInteger("98904"));
//         for (Gene gene : genes1) {
//            System.out.println(gene.toString());
//        }
//"SELECT g FROM Gene g WHERE g.genome = :genome and g.locusTag not equals :locusTag and (g.endPosition > :promoterEnd and g.endPosition < :promoterStart)")
        //work 
//        List<Gene> genes = geneDAO.findByGenomeLocusTagStartPositionForward(123, "NNMOS1_00014", new BigInteger("99484"), new BigInteger("98904"));
//        for (Gene gene : genes) {
//            System.out.println(gene.toString());
//        }
    }

    public void save(Gene gene) {
        geneDAO = new GeneDAO();

        try {
            geneDAO.save(gene);
        } catch (Exception E) {
            System.out.println(E);
        }

    }

    public Gene findById(Integer id) {
        geneDAO = new GeneDAO();
        Gene gene = geneDAO.findById(id);
        return gene;
    }

    public Gene findByGenomeLocusTag(Integer genome, String locusTag) {
        geneDAO = new GeneDAO();
        Gene gene = geneDAO.findByGenomeLocusTag(genome, locusTag);
        return gene;
    }

    public void findByOrganismGene(Integer organism, String gene) {
        geneDAO = new GeneDAO();
        List<Gene> genes = geneDAO.findByOrganismGene(organism, gene);
        for (Gene g : genes) {
            System.out.println(g.toString());
            System.out.println(g.getAlternativeLocusTag());
        }
    }

    public void findAllByLocusTag(String gene) {
        geneDAO = new GeneDAO();
        List<Gene> genes = geneDAO.findAllByLocusTagGeneName(gene);
        for (Gene g : genes) {
            System.out.println(g.toString());
            System.out.println(g.getAlternativeLocusTag());
        }
    }

    public void update(Gene gene) {
        geneDAO = new GeneDAO();
        geneDAO.update(gene);
    }

    public void listAll() {
        geneDAO = new GeneDAO();
        List<Gene> generics = geneDAO.listAll();
        for (Gene generic : generics) {
            System.out.println(generic.toString());
        }

    }

    public void findModels(String type) {
        geneDAO = new GeneDAO();
        List<Gene> generics = geneDAO.findModels(type);
        for (Gene generic : generics) {
            System.out.println(generic.toString());
        }

    }

    public void bringNumberOfModelProteins(String type) {
        geneDAO = new GeneDAO();
        Long n = geneDAO.bringNumberOfModelProteins(type);
        System.out.println("Number of proteins: " + n);
    }

    public void bringNumberOfModelHmmProfiles(String type) {
        geneDAO = new GeneDAO();
        Long n = geneDAO.bringNumberOfModelHmmProfiles(type);
        System.out.println("Number of model HMM Profiles: " + n);
    }

    public void bringNumberOfHmmProfiles() {
        geneDAO = new GeneDAO();
        Long n = geneDAO.bringNumberOfHmmProfiles();
        System.out.println("Number of HMM Profiles: " + n);
    }

    public void findGenesOfModelsByLocusTagGeneName(String gene, String type) {
        geneDAO = new GeneDAO();
        List<Gene> generics = geneDAO.findGenesOfModelsByLocusTagGeneName(gene, type);
        for (Gene generic : generics) {
            System.out.println(generic.toString());
        }

    }

    public void listAllOfOrganism(Integer id) {
        geneDAO = new GeneDAO();
        List<Gene> generics = geneDAO.listAllGenesOfOrganism(id);
        for (Gene generic : generics) {
            System.out.println(generic.toString());
        }

    }

    public void delete(Gene gene) {
        geneDAO = new GeneDAO();
        geneDAO.delete(gene);
    }

    public void bringNumberOfGenes() {
        geneDAO = new GeneDAO();
        Long n = geneDAO.bringNumberOfGenes();
        System.out.println("Number of genes: " + n);
    }

    public void bringNumberOfProteins() {
        geneDAO = new GeneDAO();
        Long n = geneDAO.bringNumberOfProteins();
        System.out.println("Number of proteins: " + n);
    }

    public void findByGenomeLocusTagEndPosition(Integer genome, String locusTag, BigInteger promoterStart,
            BigInteger promoterEnd) {
        geneDAO = new GeneDAO();
        List<Gene> genes = geneDAO.findByGenomeLocusTagEndPositionReverse(genome, locusTag, promoterStart, promoterEnd);
        for (Gene gene : genes) {
            System.out.println(gene.toString());
        }
    }
}
