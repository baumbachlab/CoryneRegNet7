/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.analysis;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.HomologousDAO;
import com.coryneregnet7.dao.TableViewDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Homologous;
import com.coryneregnet7.model.TableView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mariana
 */
public class BringHomologous {

    public static void main(String args[]) {

        List<String> locuses = new ArrayList<>();

        //-----------------------
        //------inositol---------
//        locuses.add("DIP_RS16050");
//        locuses.add("CE_RS08870");
//        locuses.add("CE_RS13960");
//        locuses.add("Cp1002B_RS06230");
//        locuses.add("JK_RS01875");
        //-----------------------
        //.............................................
        //-----------------------
        //----- -inositol--pt2-----
//        locuses.add("cg0201");
//        locuses.add("cg0205");
//        locuses.add("cg0699");
//        locuses.add("cg0910");
//        locuses.add("cg1543");
//        locuses.add("cg2090");
//        locuses.add("cg0204");
//        locuses.add("cg2298");
//        locuses.add("cg2964");
//        locuses.add("cg3137");
//        locuses.add("cg3323");
//        locuses.add("cg3391");
//        locuses.add("cg3392");
        //-----------------------
        //.............................................
        //-----------------------
        //---------TFs-----------
//        locuses.add("Cp1002B_RS06745");
//        locuses.add("Cp1002B_RS05995");
//        locuses.add("DIP_RS19435");
//        locuses.add("DIP_RS18315");
//        locuses.add("DIP_RS16830");
//        locuses.add("DIP_RS21035");
//        locuses.add("CE_RS06390");
//        locuses.add("CE_RS04780");
//        locuses.add("CE_RS05955");
//        locuses.add("CE_RS14110");
//        locuses.add("CE_RS11375");
//        locuses.add("CE_RS00315");
//        locuses.add("CE_RS08405");
//        locuses.add("CKV68_RS04400");
//        locuses.add("JK_RS05100");
//        locuses.add("JK_RS05690");
//        locuses.add("JK_RS00525");
//        locuses.add("JK_RS01670");
//        locuses.add("cg0454");
//        locuses.add("cg3224");
//        locuses.add("cg0156");
//        locuses.add("cg1846");
//        locuses.add("cg0090");
//        locuses.add("cg3261");
//        locuses.add("cg0741");
//        locuses.add("cg3097");
//        locuses.add("cg1340");
//        locuses.add("cg3388");
//        locuses.add("cg0800");
//        locuses.add("cg2544");
//        locuses.add("cg0876");
        //-----------------------
        //.............................................
        //-----------------------
        //---------Oxidoreductases-----pt1------
//        locuses.add("Cp1002B_RS06805");
//        locuses.add("JK_RS01065");
//        locuses.add("JK_RS07565");
//        locuses.add("JK_RS00955");
//        locuses.add("JK_RS00390");
//        locuses.add("JK_RS01235");
//        locuses.add("JK_RS04760");
//        //;>;>;>
//        locuses.add("CE_RS01690");
//        locuses.add("CE_RS02845");
//        locuses.add("CE_RS03200");
//        locuses.add("CE_RS08030");
//        locuses.add("CE_RS08755");
//        locuses.add("CE_RS11030");
//        locuses.add("CE_RS12835");
//        locuses.add("CE_RS13095");
//        locuses.add("CE_RS13635");
        //-----------------------
        //.............................................
        //-----------------------
        //---------Oxidoreductases------pt2-----
//        locuses.add("CE_RS09315");
//        locuses.add("CE_RS10505");
//        locuses.add("CE_RS07915");
//        locuses.add("CE_RS02585");
//        locuses.add("CE_RS08750");
//        locuses.add("CE_RS10030");
//        locuses.add("CE_RS14215");
//        locuses.add("DIP_RS12010");
//        locuses.add("DIP_RS20525");
//        locuses.add("DIP_RS21440");
//        locuses.add("DIP_RS21675");
//        locuses.add("DIP_RS13455");
//        locuses.add("DIP_RS17725");
//        locuses.add("DIP_RS19825");
//        locuses.add("DIP_RS18345");
//        locuses.add("DIP_RS18350");
//        locuses.add("CKV68_RS00860");
//        locuses.add("CKV68_RS02025");
//        locuses.add("CKV68_RS02030");
//        locuses.add("CKV68_RS05125");
//        locuses.add("cg0557");
//        locuses.add("cg1027");
//        locuses.add("cg3327");
//        locuses.add("cg0129");
//        locuses.add("cg0535");
//        locuses.add("cg1069");
//        locuses.add("cg1791");
//        locuses.add("cg2953");
//        locuses.add("cg0445");
//        locuses.add("cg1153");
//        locuses.add("cg1442");
//        locuses.add("cg1769");
//        locuses.add("cg0291");
//        locuses.add("cg1151");
//        locuses.add("cg1376");
//        locuses.add("cg1848");
//        locuses.add("cg2538");
//        locuses.add("cg3256");
//        locuses.add("cg2404");
//        locuses.add("cg3339");
//        locuses.add("cg0181");
//        locuses.add("cg0207");
//        locuses.add("cg0211");
//        locuses.add("cg0251");
//        locuses.add("cg0387");
//        locuses.add("cg0446");
//        locuses.add("cg0612");
//        locuses.add("cg0616");
//        locuses.add("cg0639");
//        locuses.add("cg0662");
//        locuses.add("cg0700");
//        locuses.add("cg0720");
//        locuses.add("cg0767");
//        locuses.add("cg1043");
//        locuses.add("cg1080");
//        locuses.add("cg1300");
//        locuses.add("cg1642");
//        locuses.add("cg1656");
//        locuses.add("cg1677");
//        locuses.add("cg1680");
//        locuses.add("cg1703");
//        locuses.add("cg1781");
//        locuses.add("cg1850");
//        locuses.add("cg2342");
//        locuses.add("cg2417");
//        locuses.add("cg2543");
//        locuses.add("cg2638");
//        locuses.add("cg2640");
//        locuses.add("cg2714");
//        locuses.add("cg3092");
//        locuses.add("cg3136");
//        locuses.add("cg3332");
//        locuses.add("cg3340");
//        locuses.add("cg3344");
//        locuses.add("cg3370");
//        locuses.add("cg3374");
//        locuses.add("cg3389");
//        locuses.add("cg3391");
//        locuses.add("cg3392");
        //-----------------------
        //.............................................
        //-----------------------
        //---------Iron-related-genes------pt1-----
//        locuses.add("Cp1002B_RS03145");
//        locuses.add("Cp1002B_RS02900");
//        locuses.add("Cp1002B_RS02895");
//        locuses.add("Cp1002B_RS02890");
//        locuses.add("Cp1002B_RS02885");
//        locuses.add("Cp1002B_RS00145");
//        locuses.add("JK_RS05775");
//        locuses.add("JK_RS06305");
//        locuses.add("JK_RS07435");
//        locuses.add("JK_RS00910");
//        locuses.add("JK_RS09205");
//        locuses.add("JK_RS09210");
//        locuses.add("JK_RS09215");
//        locuses.add("JK_RS09625");
//        locuses.add("JK_RS09630");
//        locuses.add("JK_RS09635");
//        locuses.add("JK_RS09640");
//        locuses.add("CKV68_RS09390");
//        locuses.add("CKV68_RS09390");
//        locuses.add("CKV68_RS02995");
//        locuses.add("CKV68_RS03110");
//        locuses.add("CKV68_RS03115");
//        locuses.add("CKV68_RS03120");
//        locuses.add("CKV68_RS06775");
//        locuses.add("CKV68_RS09760");
        //-----------------------
        //.............................................
        //-----------------------
        //---------Iron-related-genes------pt2-----
//        locuses.add("DIP_RS13785");
//        locuses.add("DIP_RS16675");
//        locuses.add("DIP_RS13180");
//        locuses.add("DIP_RS14435");
//        locuses.add("DIP_RS16615");
//        locuses.add("DIP_RS19260");
//        locuses.add("CE_RS06050");
//        locuses.add("CE_RS03900");
//        locuses.add("CE_RS05495");
//        locuses.add("CE_RS06510");
//        locuses.add("CE_RS06835");
//        locuses.add("CE_RS07050");
//        locuses.add("CE_RS08030");
//        locuses.add("CE_RS09205");
//        locuses.add("CE_RS09495");
//        locuses.add("CE_RS09515");
//        locuses.add("CE_RS03900");
//        locuses.add("CE_RS03150");
//        locuses.add("CE_RS08380");
//        locuses.add("CE_RS09495");
//        locuses.add("CE_RS08380");
//        locuses.add("CE_RS11030");
//         locuses.add("CE_RS02415");
//        locuses.add("CE_RS03560");
//        locuses.add("CE_RS03565");
//         locuses.add("CE_RS04530");
//        locuses.add("CE_RS04535");
//        locuses.add("CE_RS04540");
//         locuses.add("CE_RS04545");
//        locuses.add("CE_RS13260");


        //----------------------
        //-----------------------
        //.............................................
        //-----------------------
        //---------Iron-related-genes------pt3-----
        //glutamicum --> to be done. 
        /*
Cp1002B_RS08590
         */
        locuses.add("Cp1002B_RS08590");
        locuses.add("Cp1002B_RS06805");
        //----------------------
        //-----------------------
        for (String locus : locuses) {
            System.out.println("\n" + locus);
            System.out.println("");
            BringHomologous bh = new BringHomologous();
            ArrayList<String[]> table = bh.getTable(locus);

            for (String[] strings : table) {
                for (String string : strings) {
                    System.out.print(string + "\t");
                }
                System.out.println("");
            }

            System.out.println("\n\n");
            System.out.println("----------------------------------");
        }

    }

    public ArrayList<String[]> getTable(String locus) {
        //String locus = "Cp1002B_RS00130";

        Gene gene = new Gene();
        GeneDAO geneDAO = new GeneDAO();
        gene = geneDAO.findByLocusTag(locus);

        Homologous homologous = new Homologous();
        HomologousDAO homologousDAO = new HomologousDAO();

        ArrayList<String[]> table = new ArrayList<>();
        String[] line = new String[6];
        line[0] = "genome";
        line[1] = "gene_name";
        line[2] = "locus_tag";
        line[3] = "regulated_by_srna";
        line[4] = "regulated_by_tf";
        line[5] = "regulates";
        table.add(line);

        List<TableView> tableView = new ArrayList<>();
        TableViewDAO tableViewDAO = new TableViewDAO();
        //System.out.println("GENE OF INTEREST: ");
        //System.out.println(gene.toString2());
        tableView = tableViewDAO.findByLocusTagGeneName(locus);
        for (TableView tableView1 : tableView) {
            //System.out.println("----regulated by TF: " + tableView1.getRegulatedBy());
            //System.out.println("----regulated by sRNA: " + tableView1.getRnasLocus());
            //System.out.println("----regulates: " + tableView1.getRegulatedGenes());
            line = new String[6];
            line[0] = "gene";
            line[1] = tableView1.getGeneName();
            line[2] = tableView1.getLocusTag();
            line[3] = (tableView1.getRnasLocus() == null) ? "no" : tableView1.getRnasLocus();
            line[4] = (tableView1.getRegulatedBy() == null) ? "no" : tableView1.getRegulatedBy();
            line[5] = (tableView1.getRegulatedGenes() == null) ? "no" : tableView1.getRegulatedGenes();
            table.add(line);
        }
        //System.out.println("\n");

        //System.out.println("HOMOLOGOUS:");
        List<Homologous> homologousCg = homologousDAO.findByGeneGenomeMultiple(gene.getId(), 1226);

        if (homologousCg.isEmpty()) {
            line = new String[6];
            line[0] = "CgATCC13032";
            line[1] = "-";
            line[2] = "-";
            line[3] = "-";
            line[4] = "-";
            line[5] = "-";
            table.add(line);
        }
        for (Homologous homologous1 : homologousCg) {
            Gene hGene = new Gene();
            if (homologous1.getGeneOne().getId() == gene.getId()) {
                hGene = homologous1.getGeneTwo();
            } else {
                hGene = homologous1.getGeneOne();
            }
            //System.out.println("homologousCg " + homologous1);
            tableView = tableViewDAO.findByLocusTagGeneName(hGene.getLocusTag());
            for (TableView tableView1 : tableView) {
                //System.out.println("----regulated by TF: " + tableView1.getRegulatedBy());
                //System.out.println("----regulated by sRNA: " + tableView1.getRnasLocus());
                //System.out.println("----regulates: " + tableView1.getRegulatedGenes());
                line = new String[6];
                line[0] = "CgATCC13032\t";
                line[1] = tableView1.getGeneName();
                line[2] = tableView1.getLocusTag();
                line[3] = (tableView1.getRnasLocus() == null) ? "no" : tableView1.getRnasLocus();
                line[4] = (tableView1.getRegulatedBy() == null) ? "no" : tableView1.getRegulatedBy();
                line[5] = (tableView1.getRegulatedGenes() == null) ? "no" : tableView1.getRegulatedGenes();
                table.add(line);
            }
        }

        //System.out.println("\n");
        List<Homologous> homologousCul = homologousDAO.findByGeneGenomeMultiple(gene.getId(), 1288);
        if (homologousCul.isEmpty()) {
            line = new String[6];
            line[0] = "CulNCTC7910";
            line[1] = "-";
            line[2] = "-";
            line[3] = "-";
            line[4] = "-";
            line[5] = "-";
            table.add(line);
        }
        for (Homologous homologous1 : homologousCul) {
            Gene hGene = new Gene();
            if (homologous1.getGeneOne().getId() == gene.getId()) {
                hGene = homologous1.getGeneTwo();
            } else {
                hGene = homologous1.getGeneOne();
            }
            //System.out.println("homologousCul " + homologous1);
            tableView = tableViewDAO.findByLocusTagGeneName(hGene.getLocusTag());
            for (TableView tableView1 : tableView) {
                //System.out.println("----regulated by TF: " + tableView1.getRegulatedBy());
                //System.out.println("----regulated by sRNA: " + tableView1.getRnasLocus());
                //System.out.println("----regulates: " + tableView1.getRegulatedGenes());
                line = new String[6];
                line[0] = "CulNCTC7910";
                line[1] = tableView1.getGeneName();
                line[2] = tableView1.getLocusTag();
                line[3] = (tableView1.getRnasLocus() == null) ? "no" : tableView1.getRnasLocus();
                line[4] = (tableView1.getRegulatedBy() == null) ? "no" : tableView1.getRegulatedBy();
                line[5] = (tableView1.getRegulatedGenes() == null) ? "no" : tableView1.getRegulatedGenes();
                table.add(line);
            }
        }
        //    //System.out.println("\n");

        List<Homologous> homologousCd = homologousDAO.findByGeneGenomeMultiple(gene.getId(), 1230);
        if (homologousCd.isEmpty()) {
            line = new String[6];
            line[0] = "CdNCTC13129";
            line[1] = "-";
            line[2] = "-";
            line[3] = "-";
            line[4] = "-";
            line[5] = "-";
            table.add(line);
        }
        for (Homologous homologous1 : homologousCd) {
            //System.out.println("homologousCd " + homologous1);
            Gene hGene = new Gene();
            if (Objects.equals(homologous1.getGeneOne().getId(), gene.getId())) {
                hGene = homologous1.getGeneTwo();
            } else {
                hGene = homologous1.getGeneOne();
            }
            tableView = tableViewDAO.findByLocusTagGeneName(hGene.getLocusTag());
            for (TableView tableView1 : tableView) {
                //System.out.println("----regulated by TF: " + tableView1.getRegulatedBy());
                //System.out.println("----regulated by sRNA: " + tableView1.getRnasLocus());
                //System.out.println("----regulates: " + tableView1.getRegulatedGenes());
                line = new String[6];
                line[0] = "CdNCTC13129";
                line[1] = tableView1.getGeneName();
                line[2] = tableView1.getLocusTag();
                line[3] = (tableView1.getRnasLocus() == null) ? "no" : tableView1.getRnasLocus();
                line[4] = (tableView1.getRegulatedBy() == null) ? "no" : tableView1.getRegulatedBy();
                line[5] = (tableView1.getRegulatedGenes() == null) ? "no" : tableView1.getRegulatedGenes();
                table.add(line);
            }
        }
        //System.out.println("\n");

        List<Homologous> homologousCp = homologousDAO.findByGeneGenomeMultiple(gene.getId(), 1274);
        if (homologousCp.isEmpty()) {
            line = new String[6];
            line[0] = "Cp1002B";
            line[1] = "-";
            line[2] = "-";
            line[3] = "-";
            line[4] = "-";
            line[5] = "-";
            table.add(line);
        }
        for (Homologous homologous1 : homologousCp) {
            Gene hGene = new Gene();
            if (Objects.equals(homologous1.getGeneOne().getId(), gene.getId())) {
                hGene = homologous1.getGeneTwo();
            } else {
                hGene = homologous1.getGeneOne();
            }

            //System.out.println("homologousCp " + homologous1);
            tableView = tableViewDAO.findByLocusTagGeneName(hGene.getLocusTag());
            for (TableView tableView1 : tableView) {
                //System.out.println("----regulated by TF: " + tableView1.getRegulatedBy());
                //System.out.println("----regulated by sRNA: " + tableView1.getRnasLocus());
                //System.out.println("----regulates: " + tableView1.getRegulatedGenes());
                line = new String[6];
                line[0] = "Cp1002B";
                line[1] = tableView1.getGeneName();
                line[2] = tableView1.getLocusTag();
                line[3] = (tableView1.getRnasLocus() == null) ? "no" : tableView1.getRnasLocus();
                line[4] = (tableView1.getRegulatedBy() == null) ? "no" : tableView1.getRegulatedBy();
                line[5] = (tableView1.getRegulatedGenes() == null) ? "no" : tableView1.getRegulatedGenes();
                table.add(line);
            }

        }
        //System.out.println("\n");

        List<Homologous> homologousCj = homologousDAO.findByGeneGenomeMultiple(gene.getId(), 1390);
        if (homologousCj.isEmpty()) {
            line = new String[6];
            line[0] = "CjK411";
            line[1] = "-";
            line[2] = "-";
            line[3] = "-";
            line[4] = "-";
            line[5] = "-";
            table.add(line);
        }
        for (Homologous homologous1 : homologousCj) {
            Gene hGene = new Gene();
            if (Objects.equals(homologous1.getGeneOne().getId(), gene.getId())) {
                hGene = homologous1.getGeneTwo();
            } else {
                hGene = homologous1.getGeneOne();
            }
            //System.out.println("homologousCj " + homologous1);
            tableView = tableViewDAO.findByLocusTagGeneName(hGene.getLocusTag());
            for (TableView tableView1 : tableView) {
                //System.out.println("----regulated by TF: " + tableView1.getRegulatedBy());
                //System.out.println("----regulated by sRNA: " + tableView1.getRnasLocus());
                //System.out.println("----regulates: " + tableView1.getRegulatedGenes());
                line = new String[6];
                line[0] = "CjK411";
                line[1] = tableView1.getGeneName();
                line[2] = tableView1.getLocusTag();
                line[3] = (tableView1.getRnasLocus() == null) ? "no" : tableView1.getRnasLocus();
                line[4] = (tableView1.getRegulatedBy() == null) ? "no" : tableView1.getRegulatedBy();
                line[5] = (tableView1.getRegulatedGenes() == null) ? "no" : tableView1.getRegulatedGenes();
                table.add(line);
            }
        }
        //System.out.println("\n");

        List<Homologous> homologousCe = homologousDAO.findByGeneGenomeMultiple(gene.getId(), 1243);

        if (homologousCe.isEmpty()) {
            line = new String[6];
            line[0] = "CeYS-314";
            line[1] = "-";
            line[2] = "-";
            line[3] = "-";
            line[4] = "-";
            line[5] = "-";
            table.add(line);
        }

        for (Homologous homologous1 : homologousCe) {
            Gene hGene = new Gene();
            if (Objects.equals(homologous1.getGeneOne().getId(), gene.getId())) {
                hGene = homologous1.getGeneTwo();
            } else {
                hGene = homologous1.getGeneOne();
            }
            //System.out.println("homologousCe " + homologous1);
            tableView = tableViewDAO.findByLocusTagGeneName(hGene.getLocusTag());
            for (TableView tableView1 : tableView) {
                //System.out.println("----regulated by TF: " + tableView1.getRegulatedBy());
                //System.out.println("----regulated by sRNA: " + tableView1.getRnasLocus());
                //System.out.println("----regulates: " + tableView1.getRegulatedGenes());
                line = new String[6];
                line[0] = "CeYS-314";
                line[1] = tableView1.getGeneName();
                line[2] = tableView1.getLocusTag();
                line[3] = (tableView1.getRnasLocus() == null) ? "no" : tableView1.getRnasLocus();
                line[4] = (tableView1.getRegulatedBy() == null) ? "no" : tableView1.getRegulatedBy();
                line[5] = (tableView1.getRegulatedGenes() == null) ? "no" : tableView1.getRegulatedGenes();
                table.add(line);
            }
        }
        //System.out.println("\n");
        //System.out.println("\n");

        return table;
    }

}
