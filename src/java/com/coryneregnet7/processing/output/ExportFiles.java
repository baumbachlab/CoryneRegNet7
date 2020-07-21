/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.output;

import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.HomologousDAO;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Homologous;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author mariana
 */
public class ExportFiles {

    public static void main(String[] args) throws IOException {
        ExportFiles export = new ExportFiles();
        export.writeHomologs();
    }

    public void writeHomologs() throws IOException {
        HomologousDAO homologousDAO = new HomologousDAO();

        GenomeDAO genomeDAO = new GenomeDAO();
        List<Genome> models = genomeDAO.findByOrgnismType("model");
        List<Genome> targets = genomeDAO.findByOrgnismType("target");

        for (Genome model : models) {
            for (Genome target : targets) {

                /*
                BLAST FIRST SIDE:
                 */
                List<Homologous> homologousList = homologousDAO.findByModelAndTargetGenomes(model.getId(), target.getId());

                String firstSideName = model.getOrganism().getGenera().charAt(0) + "_"
                        + model.getOrganism().getSpecies().charAt(0) + "_"
                        + model.getOrganism().getStrain()
                        + "-x-"
                        + "BA_" + target.getGenomeName();

                FileWriter fileWriter = new FileWriter("/data/coryne-genus/target-brucella/export-files/BLAST-" + firstSideName + ".txt");
                PrintWriter printWriter = new PrintWriter(fileWriter);

                System.out.println("Gene\tHomologous\tevalue\tpvalue\tHomologous genome");
                for (Homologous homologous : homologousList) {

                    printWriter.print(homologous.getGeneTwo().getLocusTag() + "\t");
                    printWriter.print(homologous.getGeneTwo().getGenome().getOrganism().getGenera()
                            + " " + homologous.getGeneTwo().getGenome().getOrganism().getSpecies()
                            + " " + homologous.getGeneTwo().getGenome().getGenomeName() + "\t");
                    printWriter.print(homologous.getEvalue() + "\t");
                    printWriter.print(homologous.getPvalue() + "\t");
                    printWriter.print(homologous.getGeneOne().getLocusTag() + "\t");

                    printWriter.print(homologous.getGeneOne().getGenome().getOrganism().getGenera()
                            + " " + homologous.getGeneOne().getGenome().getOrganism().getSpecies() + "\t");
                    printWriter.println("");
                }
                printWriter.close();
            }
        }
    }

}
