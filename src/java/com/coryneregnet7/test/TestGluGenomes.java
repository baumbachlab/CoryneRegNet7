/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.model.Genome;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ubuntu
 */
public class TestGluGenomes {

    public static void main(String[] args) {
        List<Genome> targetGenomes = new ArrayList<>();
        GenomeDAO genomeDAO = new GenomeDAO();

        Hashtable<String, Integer> genomes = new Hashtable<>();
        genomes.put("GCF_000227175.1_ASM22717v1", Integer.SIZE);
        genomes.put("GCF_003813965.1_ASM381396v1", Integer.SIZE);
        genomes.put("GCF_001447295.1_ASM144729v1", Integer.SIZE);
        genomes.put("GCF_000828015.1_ASM82801v1", Integer.SIZE);
        genomes.put("GCF_900475775.1_46941_E01", Integer.SIZE);
        genomes.put("GCF_001867005.1_ASM186700v1", Integer.SIZE);
        genomes.put("GCF_000766885.2_ASM76688v2", Integer.SIZE);
        genomes.put("GCF_900637065.1_46941_D01", Integer.SIZE);
        genomes.put("GCF_001889145.1_ASM188914v1", Integer.SIZE);
        genomes.put("GCF_000833575.1_ASM83357v1", Integer.SIZE);
        genomes.put("GCF_000215665.1_ASM21566v1", Integer.SIZE);
        genomes.put("GCF_001941425.1_ASM194142v1", Integer.SIZE);
        genomes.put("GCF_001941465.1_ASM194146v1", Integer.SIZE);
        genomes.put("GCF_000248375.1_ASM24837v1", Integer.SIZE);
        genomes.put("GCF_000764045.2_ASM76404v2", Integer.SIZE);
        genomes.put("GCF_000263755.4_ASM26375v2", Integer.SIZE);
        genomes.put("GCF_004295625.1_ASM429562v1", Integer.SIZE);
        genomes.put("GCF_000767415.1_ASM76741v1", Integer.SIZE);
        genomes.put("GCF_000144935.1_ASM14493v2", Integer.SIZE);
        genomes.put("GCF_003814005.1_ASM381400v1", Integer.SIZE);
        genomes.put("GCF_900637825.1_52683_C03", Integer.SIZE);
        genomes.put("GCF_001579925.1_ASM157992v1", Integer.SIZE);
        genomes.put("GCF_004795735.1_ASM479573v1", Integer.SIZE);
        genomes.put("GCF_001867045.1_ASM186704v1", Integer.SIZE);
        genomes.put("GCF_901482545.1_45076_F01", Integer.SIZE);
        genomes.put("GCF_900478035.1_47555_E02", Integer.SIZE);
        genomes.put("GCF_000767645.1_ASM76764v1", Integer.SIZE);
        genomes.put("GCF_900187135.1_51485_C02", Integer.SIZE);
        genomes.put("GCF_000732945.1_ASM73294v1", Integer.SIZE);
        genomes.put("GCF_002843135.1_ASM284313v1", Integer.SIZE);
        genomes.put("GCF_000767055.1_ASM76705v1", Integer.SIZE);
        genomes.put("GCF_002163915.1_ASM216391v1", Integer.SIZE);
        genomes.put("GCF_003641245.1_ASM364124v1", Integer.SIZE);
        genomes.put("GCF_001457455.1_NCTC11397", Integer.SIZE);
        genomes.put("GCF_000338095.1_ASM33809v1", Integer.SIZE);
        genomes.put("GCF_004758745.1_ASM475874v1", Integer.SIZE);
        genomes.put("GCF_000525655.1_ASM52565v1", Integer.SIZE);
        genomes.put("GCF_004193675.1_ASM419367v1", Integer.SIZE);
        genomes.put("GCF_000344785.1_ASM34478v1", Integer.SIZE);
        genomes.put("GCF_004771435.1_ASM477143v1", Integer.SIZE);
        genomes.put("GCF_001481675.1_ASM148167v1", Integer.SIZE);
        genomes.put("GCF_001969085.2_ASM196908v2", Integer.SIZE);
        genomes.put("GCF_002953315.1_ASM295331v1", Integer.SIZE);
        genomes.put("GCF_000980815.1_ASM98081v1", Integer.SIZE);
        genomes.put("GCF_001586215.1_ASM158621v1", Integer.SIZE);
        genomes.put("GCF_000255255.1_ASM25525v1", Integer.SIZE);
        genomes.put("GCF_001481755.1_ASM148175v1", Integer.SIZE);
        genomes.put("GCF_000550805.1_ASM55080v1", Integer.SIZE);
        genomes.put("GCF_000747315.1_ASM74731v1", Integer.SIZE);
        genomes.put("GCF_000742735.1_ASM74273v1", Integer.SIZE);
        genomes.put("GCF_900187295.1_52191_E01", Integer.SIZE);
        genomes.put("GCF_004193655.1_ASM419365v1", Integer.SIZE);
        genomes.put("GCF_001865765.1_ASM186576v1", Integer.SIZE);
        genomes.put("GCF_001663495.1_ASM166349v1", Integer.SIZE);
        genomes.put("GCF_000418365.1_ASM41836v1", Integer.SIZE);
        genomes.put("GCF_001586235.1_ASM158623v1", Integer.SIZE);
        genomes.put("GCF_001936195.1_ASM193619v1", Integer.SIZE);
        genomes.put("GCF_001687645.1_ASM168764v1", Integer.SIZE);
        genomes.put("GCF_003070865.1_ASM307086v1", Integer.SIZE);
        genomes.put("GCF_000445015.1_ASM44501v1", Integer.SIZE);
        genomes.put("GCF_001682275.1_ASM168227v1", Integer.SIZE);
        genomes.put("GCF_002155265.1_ASM215526v1", Integer.SIZE);
        genomes.put("GCF_000022905.1_ASM2290v1", Integer.SIZE);
        genomes.put("GCF_000011305.1_ASM1130v1", Integer.SIZE);
        genomes.put("GCF_000767685.1_ASM76768v1", Integer.SIZE);
        genomes.put("GCF_900475635.1_45076_H01", Integer.SIZE);
        genomes.put("GCF_002073375.2_ASM207337v2", Integer.SIZE);
        genomes.put("GCF_001653295.1_ASM165329v1", Integer.SIZE);
        genomes.put("GCF_001274895.1_ASM127489v1", Integer.SIZE);
        genomes.put("GCF_003209395.1_ASM320939v1", Integer.SIZE);
        genomes.put("GCF_001579885.2_ASM157988v2", Integer.SIZE);
        genomes.put("GCF_003955885.1_ASM395588v1", Integer.SIZE);
        
        
        
        
        for (Map.Entry<String, Integer> entry : genomes.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            Genome target = genomeDAO.findByGenomeName(key);
            targetGenomes.add(target);
            System.out.println("genomes.put("+"\""+target.getGenomeName()+"\""+","+target.getId()+ ");");
        }
    }

}
