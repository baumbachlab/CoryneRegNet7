/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.input;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.OrganismDAO;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Organism;
import com.coryneregnet7.processing.OperonPrediction;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import static javax.script.ScriptEngine.FILENAME;

/**
 *
 * @author mariana
 */
public class AddGbkNames {

    public static void main(String[] args) throws InterruptedException {
        AddGbkNames g = new AddGbkNames();
        //g.readFileIntoDatabase(new File("/home/parise/Downloads/Cd_NCTC11397.gbff"), "target");
        //g.readIntoDatabase("/home/parise/Doutorado/Corynebacterium_genomes/genbank/", "target");
        g.readIntoDatabase("/data/home/mariana/Dropbox/Doutorado/CoryneRegNet7/Corynebacterium_genomes/", "target");
    }

    public void readIntoDatabase(String folderPath, String type) throws InterruptedException {

        //lê a pasta dos gbks
        File folder = new File(folderPath);
        File[] listOfFolders = folder.listFiles();

        System.out.println(folderPath);
        System.out.println(listOfFolders.length);
        System.out.println(folderPath);
        int count = 0;
        String genomesToUpdateFile = folderPath + "Genomes_to_update_pt2.tsv";
        File genomesToUpdate = new File(genomesToUpdateFile);
        BufferedReader br = null;
        FileReader fr = null;
        String[] splitedLine;
        String[] columnsInfo;
        String specieFolder;
        Integer genomeId;

        try {

            String sCurrentLine;

            /*
                    SAVE GENES FROM A GENOME
             */
            fr = new FileReader(genomesToUpdate);
            br = new BufferedReader(fr);

            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println(sCurrentLine);
                columnsInfo = sCurrentLine.split("\t");
//                for (int i = 0; i < splitedLine.length; i++) {
//                    System.out.println(splitedLine[i]);
//                }
                splitedLine = columnsInfo[2].split(" ");
                specieFolder = splitedLine[0] + "_" + splitedLine[1];
                //System.out.println(specieFolder);

                String genomesFolder = folderPath + "genbank/" + specieFolder + "/" + columnsInfo[0];
                File genomeFile = new File(genomesFolder);
                System.out.println(genomeFile.getName());
                genomeId = Integer.parseInt(columnsInfo[1]);
                saveGeneNamesIntoDatabaseTest(genomeFile, type, genomeId);
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

//        for (File newFolder : listOfFolders) {
//            //System.out.println(newFolder+"/");
//            File speciesFolder = new File(newFolder + "/");
//            File[] genomeFiles = speciesFolder.listFiles();
//            for (File genomeFile : genomeFiles) {
//                //System.out.println(genomeFile.getName());
//                if (genomeFile.getName().endsWith(".gbff")) {
//                    System.out.println(genomeFile.getName());
//                    readFileIntoDatabaseTest(genomeFile, type, count, genomeFile.getName());
//                }
//            }
//            //readFileIntoDatabase(file, type);
//        }
//        System.out.println("\n\n");
//        System.out.println("Number of genomes found: " + count);
    }

    public void saveGeneNamesIntoDatabaseTest(File file, String type, Integer genomeId) throws InterruptedException {

        //System.out.println("file " + file.getPath());
        if (file.isFile() && file.getAbsolutePath().endsWith(".gbff")) {
            //System.out.println("\n\n===========> " + file.getName() + "\n\n");
            BufferedReader br = null;
            FileReader fr = null;

            try {

                String sCurrentLine;

                /*
                    SAVE GENES FROM A GENOME
                 */
                fr = new FileReader(file);
                br = new BufferedReader(fr);

                sCurrentLine = "";
                boolean firstGene = true;
                boolean translation = false;
                Gene gene = new Gene();
                GeneDAO geneDAO = new GeneDAO();

                String locusTag = "";
                String alternativeLocusTag = "";
                String proteinId = "";
                String startPosition = ""; //OKAY
                String endPosition = ""; //OKAY
                String name = "";
                String product = "";
                String aminoAcidSequence = "";
                String orientation = "";
                String size = "";

                while ((sCurrentLine = br.readLine()) != null) {
                    //System.out.println(sCurrentLine);

                    if (!firstGene && sCurrentLine.contains("gene") && sCurrentLine.contains("..")) {
                        //salva o gene anterior e dá um novo gene.
                        //zera todas as variáveis temporárias depois de salvar o novo gene.

                        if (!startPosition.isEmpty()) {
//                            if (startPosition.contains("<")) {
//                                startPosition = startPosition.replace("<", "");
//                            }
//
//                            if (startPosition.contains(">")) {
//                                startPosition = startPosition.replace(">", "");
//                            }
//
//                            if (endPosition.contains("<")) {
//                                endPosition = endPosition.replace("<", "");
//                            }
//
//                            if (endPosition.contains(">")) {
//                                endPosition = endPosition.replace(">", "");
//                            }

                            //System.out.println("START POSITION: " + startPosition);
                            //System.out.println("END POSITION: " + endPosition);
                            gene.setStartPosition(new BigInteger(startPosition));

                            gene.setEndPosition(new BigInteger(endPosition));
                            gene.setLocusTag(locusTag);
                            gene.setName(name);
                            gene.setProteinSequence(aminoAcidSequence);
                            gene.setProduct(product);
                            gene.setProteinId(proteinId);
                            gene.setAlternativeLocusTag(alternativeLocusTag);
                            gene.setOrientation(orientation);
//                            if (!gene.getAlternativeLocusTag().isEmpty()) {
//                                System.out.println(gene.getLocusTag() + "\t" + gene.getAlternativeLocusTag());
//                                Gene currentGene = geneDAO.findByLocusTag(gene.getAlternativeLocusTag());
//                                currentGene.setAlternativeLocusTag(gene.getLocusTag());
//                                System.out.println("currentGene= " + currentGene.toString2());
//                                //geneDAO.update(currentGene);
//                            }


                            /*
                            UPDATE INSTEAD SAVING!
                             */
                            //geneDAO.save(gene);
                            //System.out.println(gene.toString());
                            Gene geneToUpdate = geneDAO.findByGenomeOldLocusTag(genomeId, gene.getLocusTag());
                            System.out.println("\n\n-----------------------------------------");
                            //System.out.println("geneLocus: " + geneToUpdate.getAlternativeLocusTag() + " organism: " + geneToUpdate.getGenome().getOrganism().getSpecies() + " " + geneToUpdate.getGenome().getOrganism().getStrain());
                            if (!gene.getName().isEmpty() || gene.getName() != null) {
                                if (geneToUpdate != null) {
                                    System.out.println("geneToUpdateAlterLocus: " + geneToUpdate.getAlternativeLocusTag() + "\tgeneName: " + geneToUpdate.getName());
                                    System.out.println("geneLocusTag: " + gene.getLocusTag() + "\tgeneName: " + gene.getName());
                                    geneToUpdate.setName(gene.getName());
                                    geneDAO.update(geneToUpdate);
                                } else {
                                    System.out.println("gene null in refseq");
                                }
                            } else {
                                System.out.println("There is no gene name for this gene!");
                            }
                            System.out.println("\n\n-----------------------------------------");
                            gene = new Gene();
                            System.out.println("\n\n");
                        }

                        locusTag = "";
                        proteinId = "";
                        startPosition = ""; //OKAY
                        endPosition = ""; //OKAY
                        name = "";
                        product = "";
                        aminoAcidSequence = "";
                        alternativeLocusTag = "";
                        orientation = "";

                        //System.out.println("=======> " + sCurrentLine);
                    }

                    if (sCurrentLine.contains("CDS") && sCurrentLine.contains("..")) {
                        firstGene = false;
                        String[] lineCDS = sCurrentLine.split("\\s+");
                        String[] positions = lineCDS[2].split("\\.\\.");

                        //in case join(1436518..1437422,1437422..1437710)
                        startPosition = positions[0];
                        endPosition = positions[positions.length - 1];
                        //System.out.println("------startPosition= " + startPosition);
                        // System.out.println("------endPosition= " + endPosition);

                        if (startPosition.contains("complement(")) {
                            startPosition = positions[positions.length - 1];
                            endPosition = positions[0];
                            endPosition = endPosition.replace("complement(", "");
                            startPosition = startPosition.replace(")", "");

                            if (endPosition.contains("join(")) {
                                endPosition = endPosition.replace("join(", "");
                                startPosition = startPosition.replace(")", "");
                            } else {
                                if (startPosition.contains(",1")) {
                                    startPosition = startPosition.replace(",1", "");
                                }
                            }

                            orientation = "reverse";
                        } else {
                            if (startPosition.contains("join(")) {
                                startPosition = startPosition.replace("join(", "");
                                endPosition = endPosition.replace(")", "");

                                if (startPosition.contains(",1")) {
                                    startPosition = startPosition.replace(",1", "");
                                }
                            } else {
                                if (startPosition.contains(",1")) {
                                    startPosition = startPosition.replace(",1", "");
                                }
                            }
                            orientation = "forward";
                        }

                        if (startPosition.contains("<")) {
                            startPosition = startPosition.replace("<", "");
                        }

                        if (startPosition.contains(">")) {
                            startPosition = startPosition.replace(">", "");
                        }

                        if (endPosition.contains("<")) {
                            endPosition = endPosition.replace("<", "");
                        }

                        if (endPosition.contains(">")) {
                            endPosition = endPosition.replace(">", "");
                        }

                        //System.out.println("START POSITION => " + startPosition);
                        //System.out.println("END POSITION => " + endPosition);
                    }

                    if (locusTag.contains("ncRNA")) {
                        locusTag = "";
                        name = "";
                    }

                    if (sCurrentLine.contains("/gene=\"") && name.isEmpty()) {
                        name = trimRemove(sCurrentLine, "/gene=\"");
                        //System.out.println("GENE NAME: " + name);
                    }

                    if (sCurrentLine.contains("/locus_tag=\"") && locusTag.isEmpty()) {
                        locusTag = trimRemove(sCurrentLine, "/locus_tag=\"");
                        //System.out.println("LOCUS TAG: " + locusTag);
                    }

                    if (sCurrentLine.contains("/old_locus_tag=\"")) {
                        alternativeLocusTag = trimRemove(sCurrentLine, "/old_locus_tag=\"");
                        //System.out.println("ALTERNATIVE LOCUS TAG: " + alternativeLocusTag);
                    }

                    if (sCurrentLine.contains("/product=")) {
                        product = trimRemove(sCurrentLine, "/product=");
                        //System.out.println("PRODUCT: " + product);
                    }

                    if (sCurrentLine.contains("/protein_id=\"")) {
                        proteinId = trimRemove(sCurrentLine, "/protein_id=\"");
                        //System.out.println("PROTEIN ID: " + proteinId);

                    }

                    if (sCurrentLine.contains("/translation=\"")) {
                        if (!aminoAcidSequence.endsWith("\"")) {
                            aminoAcidSequence = trimRemove(sCurrentLine, "/translation=\"");
                        } else {
                            aminoAcidSequence = trimRemove(sCurrentLine, "/translation=\"");
                            aminoAcidSequence = aminoAcidSequence.replace("\"", "");
                            translation = false;
                        }
                        translation = true;
                    } else if (translation) {
                        if (sCurrentLine.startsWith("CONTIG") || sCurrentLine.startsWith("ORIGIN") || sCurrentLine.startsWith("BASE COUNT")) {
                            translation = false;
                        } else {
                            aminoAcidSequence = aminoAcidSequence + sCurrentLine.trim();

                            if (aminoAcidSequence.contains("\"")) {
                                aminoAcidSequence = aminoAcidSequence.replace("\"", "");
                                translation = false;
                                //System.out.println("TRANSLATION: --------------------S");
                                //System.out.println(aminoAcidSequence);
                            }
                        }

                    }
                }

                //SAVE THE LAST GENE
                if (!startPosition.isEmpty()) {

                    if (startPosition.contains("<")) {
                        startPosition = startPosition.replace("<", "");
                    }

                    if (startPosition.contains(">")) {
                        startPosition = startPosition.replace(">", "");
                    }

                    if (endPosition.contains("<")) {
                        endPosition = endPosition.replace("<", "");
                    }

                    if (endPosition.contains(">")) {
                        endPosition = endPosition.replace(">", "");
                    }

                    gene.setStartPosition(new BigInteger(startPosition));
                    gene.setEndPosition(new BigInteger(endPosition));
                    gene.setLocusTag(locusTag);
                    gene.setName(name);
                    gene.setProteinSequence(aminoAcidSequence);
                    gene.setProduct(product);
                    gene.setProteinId(proteinId);
                    gene.setAlternativeLocusTag(alternativeLocusTag);
                    gene.setOrientation(orientation);

//                    if (!gene.getAlternativeLocusTag().isEmpty()) {
//                        //System.out.println(gene.getLocusTag() + "\t" + gene.getAlternativeLocusTag());
//                    }
                    /*
                            UPDATE INSTEAD SAVING!
                     */
                    //geneDAO.save(gene);
                    //System.out.println(gene.toString());
                    Gene geneToUpdate = geneDAO.findByAltLocusTag(gene.getLocusTag());
                    System.out.println("\n\n-----------------------------------------");
                    //System.out.println("geneLocus: " + gene.getLocusTag() + "\tgeneName: " + gene.getName());
                    if (!gene.getName().isEmpty() || gene.getName() != null) {
                        if (geneToUpdate != null) {
                            System.out.println("geneToUpdateAlterLocus: " + geneToUpdate.getAlternativeLocusTag() + "\tgeneName: " + geneToUpdate.getName());
                            System.out.println("geneLocusTag: " + gene.getLocusTag() + "\tgeneName: " + gene.getName());
                            geneToUpdate.setName(gene.getName());
                            geneDAO.update(geneToUpdate);
                        } else {
                            System.out.println("gene null in refseq");
                        }
                    } else {
                        System.out.println("There is no gene name for this gene!");
                    }
                    System.out.println("\n\n-----------------------------------------");
                    gene = new Gene();

                    //System.out.println("SIZE: " + size);
                }

            } catch (IOException e) {

                e.printStackTrace();

            } finally {

                try {

                    if (br != null) {
                        br.close();
                    }

                    if (fr != null) {
                        fr.close();
                    }

                } catch (IOException ex) {

                    ex.printStackTrace();

                }

            }

        }
    }

    public int readFileIntoDatabaseTest(File file, String type, int count, String genomeFile) throws InterruptedException {

        boolean genomeFound = false;
        //System.out.println("file " + file.getPath());
        if (file.isFile() && file.getAbsolutePath().endsWith(".gbff")) {
            //System.out.println("\n\n===========> " + file.getName() + "\n\n");
            BufferedReader br = null;
            FileReader fr = null;

            try {

                String sCurrentLine;

                /*
                    SAVE GENES FROM A GENOME
                 */
                fr = new FileReader(file);
                br = new BufferedReader(fr);

                sCurrentLine = "";
                boolean firstGene = true;
                boolean translation = false;
                Gene gene = new Gene();
                GeneDAO geneDAO = new GeneDAO();

                String locusTag = "";
                String alternativeLocusTag = "";
                String proteinId = "";
                String startPosition = ""; //OKAY
                String endPosition = ""; //OKAY
                String name = "";
                String product = "";
                String aminoAcidSequence = "";
                String orientation = "";
                String size = "";

                while ((sCurrentLine = br.readLine()) != null) {
                    // System.out.println(sCurrentLine);

                    if (!firstGene && sCurrentLine.contains("gene") && sCurrentLine.contains("..")) {
                        //salva o gene anterior e dá um novo gene.
                        //zera todas as variáveis temporárias depois de salvar o novo gene.

                        if (!startPosition.isEmpty()) {
//                            if (startPosition.contains("<")) {
//                                startPosition = startPosition.replace("<", "");
//                            }
//
//                            if (startPosition.contains(">")) {
//                                startPosition = startPosition.replace(">", "");
//                            }
//
//                            if (endPosition.contains("<")) {
//                                endPosition = endPosition.replace("<", "");
//                            }
//
//                            if (endPosition.contains(">")) {
//                                endPosition = endPosition.replace(">", "");
//                            }

                            //System.out.println("START POSITION: " + startPosition);
                            //System.out.println("END POSITION: " + endPosition);
                            gene.setStartPosition(new BigInteger(startPosition));

                            gene.setEndPosition(new BigInteger(endPosition));
                            gene.setLocusTag(locusTag);
                            gene.setName(name);
                            gene.setProteinSequence(aminoAcidSequence);
                            gene.setProduct(product);
                            gene.setProteinId(proteinId);
                            gene.setAlternativeLocusTag(alternativeLocusTag);
                            gene.setOrientation(orientation);
//                            if (!gene.getAlternativeLocusTag().isEmpty()) {
//                                System.out.println(gene.getLocusTag() + "\t" + gene.getAlternativeLocusTag());
//                                Gene currentGene = geneDAO.findByLocusTag(gene.getAlternativeLocusTag());
//                                currentGene.setAlternativeLocusTag(gene.getLocusTag());
//                                System.out.println("currentGene= " + currentGene.toString2());
//                                //geneDAO.update(currentGene);
//                            }


                            /*
                            UPDATE INSTEAD SAVING!
                             */
                            //geneDAO.save(gene);
                            //System.out.println(gene.toString());
                            Gene geneToUpdate = geneDAO.findByAltLocusTag(gene.getLocusTag());
                            //System.out.println("\n\n-----------------------------------------");
                            //System.out.println("geneLocus: " + gene.getLocusTag() + "\tgeneName: " + gene.getName());
                            if (!gene.getName().isEmpty() || gene.getName() != null) {
                                if (geneToUpdate != null) {
                                    //System.out.println("geneToUpdateAlterLocus: " + geneToUpdate.getAlternativeLocusTag() + "\tgeneName: " + geneToUpdate.getName());
                                    //geneToUpdate.setName(gene.getName());
                                    //geneDAO.update(geneToUpdate);
                                    if (genomeFound == false) {
                                        genomeFound = true;
                                        count++;
                                        System.out.println(genomeFile + "\t" + geneToUpdate.getGenome().getId() + "\t" + geneToUpdate.getGenome().getOrganism().getGenera() + " " + geneToUpdate.getGenome().getOrganism().getSpecies() + " " + geneToUpdate.getGenome().getOrganism().getStrain());
                                        break;
                                    }
                                } else {
                                    //System.out.println("gene null in refseq");
                                }
                            } else {
                                //System.out.println("There is no gene name for this gene!");
                            }
                            //System.out.println("\n\n-----------------------------------------");
                            gene = new Gene();
//                            System.out.println("\n\n");
                        }

                        locusTag = "";
                        proteinId = "";
                        startPosition = ""; //OKAY
                        endPosition = ""; //OKAY
                        name = "";
                        product = "";
                        aminoAcidSequence = "";
                        alternativeLocusTag = "";
                        orientation = "";

                        //System.out.println("=======> " + sCurrentLine);
                    }

                    if (sCurrentLine.contains("CDS") && sCurrentLine.contains("..")) {
                        firstGene = false;
                        String[] lineCDS = sCurrentLine.split("\\s+");
                        String[] positions = lineCDS[2].split("\\.\\.");

                        //in case join(1436518..1437422,1437422..1437710)
                        startPosition = positions[0];
                        endPosition = positions[positions.length - 1];
                        //System.out.println("------startPosition= " + startPosition);
                        // System.out.println("------endPosition= " + endPosition);

                        if (startPosition.contains("complement(")) {
                            startPosition = positions[positions.length - 1];
                            endPosition = positions[0];
                            endPosition = endPosition.replace("complement(", "");
                            startPosition = startPosition.replace(")", "");

                            if (endPosition.contains("join(")) {
                                endPosition = endPosition.replace("join(", "");
                                startPosition = startPosition.replace(")", "");
                            } else {
                                if (startPosition.contains(",1")) {
                                    startPosition = startPosition.replace(",1", "");
                                }
                            }

                            orientation = "reverse";
                        } else {
                            if (startPosition.contains("join(")) {
                                startPosition = startPosition.replace("join(", "");
                                endPosition = endPosition.replace(")", "");

                                if (startPosition.contains(",1")) {
                                    startPosition = startPosition.replace(",1", "");
                                }
                            } else {
                                if (startPosition.contains(",1")) {
                                    startPosition = startPosition.replace(",1", "");
                                }
                            }
                            orientation = "forward";
                        }

                        if (startPosition.contains("<")) {
                            startPosition = startPosition.replace("<", "");
                        }

                        if (startPosition.contains(">")) {
                            startPosition = startPosition.replace(">", "");
                        }

                        if (endPosition.contains("<")) {
                            endPosition = endPosition.replace("<", "");
                        }

                        if (endPosition.contains(">")) {
                            endPosition = endPosition.replace(">", "");
                        }

                        //System.out.println("START POSITION => " + startPosition);
                        //System.out.println("END POSITION => " + endPosition);
                    }

                    if (locusTag.contains("ncRNA")) {
                        locusTag = "";
                        name = "";
                    }

                    if (sCurrentLine.contains("/gene=\"") && name.isEmpty()) {
                        name = trimRemove(sCurrentLine, "/gene=\"");
                        //System.out.println("GENE NAME: " + name);
                    }

                    if (sCurrentLine.contains("/locus_tag=\"") && locusTag.isEmpty()) {
                        locusTag = trimRemove(sCurrentLine, "/locus_tag=\"");
                        //System.out.println("LOCUS TAG: " + locusTag);
                    }

                    if (sCurrentLine.contains("/old_locus_tag=\"")) {
                        alternativeLocusTag = trimRemove(sCurrentLine, "/old_locus_tag=\"");
                        //System.out.println("ALTERNATIVE LOCUS TAG: " + alternativeLocusTag);
                    }

                    if (sCurrentLine.contains("/product=")) {
                        product = trimRemove(sCurrentLine, "/product=");
                        //System.out.println("PRODUCT: " + product);
                    }

                    if (sCurrentLine.contains("/protein_id=\"")) {
                        proteinId = trimRemove(sCurrentLine, "/protein_id=\"");
                        //System.out.println("PROTEIN ID: " + proteinId);

                    }

                    if (sCurrentLine.contains("/translation=\"")) {
                        if (!aminoAcidSequence.endsWith("\"")) {
                            aminoAcidSequence = trimRemove(sCurrentLine, "/translation=\"");
                        } else {
                            aminoAcidSequence = trimRemove(sCurrentLine, "/translation=\"");
                            aminoAcidSequence = aminoAcidSequence.replace("\"", "");
                            translation = false;
                        }
                        translation = true;
                    } else if (translation) {
                        if (sCurrentLine.startsWith("CONTIG") || sCurrentLine.startsWith("ORIGIN") || sCurrentLine.startsWith("BASE COUNT")) {
                            translation = false;
                        } else {
                            aminoAcidSequence = aminoAcidSequence + sCurrentLine.trim();

                            if (aminoAcidSequence.contains("\"")) {
                                aminoAcidSequence = aminoAcidSequence.replace("\"", "");
                                translation = false;
                                //System.out.println("TRANSLATION: --------------------S");
                                //System.out.println(aminoAcidSequence);
                            }
                        }

                    }
                }

                //SAVE THE LAST GENE
                if (!startPosition.isEmpty()) {

                    if (startPosition.contains("<")) {
                        startPosition = startPosition.replace("<", "");
                    }

                    if (startPosition.contains(">")) {
                        startPosition = startPosition.replace(">", "");
                    }

                    if (endPosition.contains("<")) {
                        endPosition = endPosition.replace("<", "");
                    }

                    if (endPosition.contains(">")) {
                        endPosition = endPosition.replace(">", "");
                    }

                    gene.setStartPosition(new BigInteger(startPosition));
                    gene.setEndPosition(new BigInteger(endPosition));
                    gene.setLocusTag(locusTag);
                    gene.setName(name);
                    gene.setProteinSequence(aminoAcidSequence);
                    gene.setProduct(product);
                    gene.setProteinId(proteinId);
                    gene.setAlternativeLocusTag(alternativeLocusTag);
                    gene.setOrientation(orientation);

                    if (!gene.getAlternativeLocusTag().isEmpty()) {
                        //System.out.println(gene.getLocusTag() + "\t" + gene.getAlternativeLocusTag());
                    }
                    /*
                            UPDATE INSTEAD SAVING!
                     */
                    //geneDAO.save(gene);

                    //System.out.println(gene.toString());
                    Gene geneToUpdate = geneDAO.findByAltLocusTag(gene.getLocusTag());
                    //System.out.println("\n\n-----------------------------------------");
                    //System.out.println("geneLocus: " + gene.getLocusTag() + "\tgeneName: " + gene.getName());
                    if (!gene.getName().isEmpty() || gene.getName() != null) {
                        if (geneToUpdate != null) {
//                            System.out.println("geneToUpdateAlterLocus: " + geneToUpdate.getAlternativeLocusTag() + "\tgeneName: " + geneToUpdate.getName());
//                            geneToUpdate.setName(gene.getName());
//                            geneDAO.update(geneToUpdate);
                            if (genomeFound == false) {
                                genomeFound = true;
                                count++;
                                System.out.println(genomeFile);
                            }
                        } else {
                            //System.out.println("gene null in refseq");
                        }
                    } else {
                        //System.out.println("There is no gene name for this gene!");
                    }
                    //System.out.println("\n\n-----------------------------------------");
                    gene = new Gene();

                    //System.out.println("SIZE: " + size);
                }

            } catch (IOException e) {

                e.printStackTrace();

            } finally {

                try {

                    if (br != null) {
                        br.close();
                    }

                    if (fr != null) {
                        fr.close();
                    }

                } catch (IOException ex) {

                    ex.printStackTrace();

                }

            }

        }
        return count;
    }

    public void readFileIntoDatabase(File file, String type) throws InterruptedException {
        System.out.println("file " + file.getPath());
        if (file.isFile() && file.getAbsolutePath().endsWith(".gbff")) {
            System.out.println("\n\n===========> " + file.getName() + "\n\n");
            BufferedReader br = null;
            FileReader fr = null;

            try {

                /*
                    SAVE ORGANISM & GENOME IDENTIFICATION
                 */
                fr = new FileReader(file);
                br = new BufferedReader(fr);

                String sCurrentLine;

                /*
                    SAVE GENES FROM A GENOME
                 */
                fr = new FileReader(file);
                br = new BufferedReader(fr);

                sCurrentLine = "";
                boolean firstGene = true;
                boolean translation = false;
                Gene gene = new Gene();
                GeneDAO geneDAO = new GeneDAO();

                String locusTag = "";
                String alternativeLocusTag = "";
                String proteinId = "";
                String startPosition = ""; //OKAY
                String endPosition = ""; //OKAY
                String name = "";
                String product = "";
                String aminoAcidSequence = "";
                String orientation = "";
                String size = "";

                while ((sCurrentLine = br.readLine()) != null) {
                    // System.out.println(sCurrentLine);

                    if (!firstGene && sCurrentLine.contains("gene") && sCurrentLine.contains("..")) {
                        //salva o gene anterior e dá um novo gene.
                        //zera todas as variáveis temporárias depois de salvar o novo gene.

                        if (!startPosition.isEmpty()) {
//                            if (startPosition.contains("<")) {
//                                startPosition = startPosition.replace("<", "");
//                            }
//
//                            if (startPosition.contains(">")) {
//                                startPosition = startPosition.replace(">", "");
//                            }
//
//                            if (endPosition.contains("<")) {
//                                endPosition = endPosition.replace("<", "");
//                            }
//
//                            if (endPosition.contains(">")) {
//                                endPosition = endPosition.replace(">", "");
//                            }

                            System.out.println("START POSITION: " + startPosition);
                            System.out.println("END POSITION: " + endPosition);
                            gene.setStartPosition(new BigInteger(startPosition));

                            gene.setEndPosition(new BigInteger(endPosition));
                            gene.setLocusTag(locusTag);
                            gene.setName(name);
                            gene.setProteinSequence(aminoAcidSequence);
                            gene.setProduct(product);
                            gene.setProteinId(proteinId);
                            gene.setAlternativeLocusTag(alternativeLocusTag);
                            gene.setOrientation(orientation);
//                            if (!gene.getAlternativeLocusTag().isEmpty()) {
//                                System.out.println(gene.getLocusTag() + "\t" + gene.getAlternativeLocusTag());
//                                Gene currentGene = geneDAO.findByLocusTag(gene.getAlternativeLocusTag());
//                                currentGene.setAlternativeLocusTag(gene.getLocusTag());
//                                System.out.println("currentGene= " + currentGene.toString2());
//                                //geneDAO.update(currentGene);
//                            }


                            /*
                            UPDATE INSTEAD SAVING!
                             */
                            //geneDAO.save(gene);
                            //System.out.println(gene.toString());
                            Gene geneToUpdate = geneDAO.findByAltLocusTag(gene.getLocusTag());
                            System.out.println("\n\n-----------------------------------------");
                            System.out.println("geneLocus: " + gene.getLocusTag() + "\tgeneName: " + gene.getName());
                            if (!gene.getName().isEmpty() || gene.getName() != null) {
                                if (geneToUpdate != null) {
                                    System.out.println("geneToUpdateAlterLocus: " + geneToUpdate.getAlternativeLocusTag() + "\tgeneName: " + geneToUpdate.getName());
                                    geneToUpdate.setName(gene.getName());
                                    geneDAO.update(geneToUpdate);
                                } else {
                                    System.out.println("gene null in refseq");
                                }
                            } else {
                                System.out.println("There is no gene name for this gene!");
                            }
                            System.out.println("\n\n-----------------------------------------");
                            gene = new Gene();
//                            System.out.println("\n\n");
                        }

                        locusTag = "";
                        proteinId = "";
                        startPosition = ""; //OKAY
                        endPosition = ""; //OKAY
                        name = "";
                        product = "";
                        aminoAcidSequence = "";
                        alternativeLocusTag = "";
                        orientation = "";

                        System.out.println("=======> " + sCurrentLine);
                    }

                    if (sCurrentLine.contains("CDS") && sCurrentLine.contains("..")) {
                        firstGene = false;
                        String[] lineCDS = sCurrentLine.split("\\s+");
                        String[] positions = lineCDS[2].split("\\.\\.");

                        //in case join(1436518..1437422,1437422..1437710)
                        startPosition = positions[0];
                        endPosition = positions[positions.length - 1];
                        //System.out.println("------startPosition= " + startPosition);
                        // System.out.println("------endPosition= " + endPosition);

                        if (startPosition.contains("complement(")) {
                            startPosition = positions[positions.length - 1];
                            endPosition = positions[0];
                            endPosition = endPosition.replace("complement(", "");
                            startPosition = startPosition.replace(")", "");

                            if (endPosition.contains("join(")) {
                                endPosition = endPosition.replace("join(", "");
                                startPosition = startPosition.replace(")", "");
                            } else {
                                if (startPosition.contains(",1")) {
                                    startPosition = startPosition.replace(",1", "");
                                }
                            }

                            orientation = "reverse";
                        } else {
                            if (startPosition.contains("join(")) {
                                startPosition = startPosition.replace("join(", "");
                                endPosition = endPosition.replace(")", "");

                                if (startPosition.contains(",1")) {
                                    startPosition = startPosition.replace(",1", "");
                                }
                            } else {
                                if (startPosition.contains(",1")) {
                                    startPosition = startPosition.replace(",1", "");
                                }
                            }
                            orientation = "forward";
                        }

                        if (startPosition.contains("<")) {
                            startPosition = startPosition.replace("<", "");
                        }

                        if (startPosition.contains(">")) {
                            startPosition = startPosition.replace(">", "");
                        }

                        if (endPosition.contains("<")) {
                            endPosition = endPosition.replace("<", "");
                        }

                        if (endPosition.contains(">")) {
                            endPosition = endPosition.replace(">", "");
                        }

                        //System.out.println("START POSITION => " + startPosition);
                        //System.out.println("END POSITION => " + endPosition);
                    }

                    if (locusTag.contains("ncRNA")) {
                        locusTag = "";
                        name = "";
                    }

                    if (sCurrentLine.contains("/gene=\"") && name.isEmpty()) {
                        name = trimRemove(sCurrentLine, "/gene=\"");
                        System.out.println("GENE NAME: " + name);
                    }

                    if (sCurrentLine.contains("/locus_tag=\"") && locusTag.isEmpty()) {
                        locusTag = trimRemove(sCurrentLine, "/locus_tag=\"");
                        System.out.println("LOCUS TAG: " + locusTag);
                    }

                    if (sCurrentLine.contains("/old_locus_tag=\"")) {
                        alternativeLocusTag = trimRemove(sCurrentLine, "/old_locus_tag=\"");
                        System.out.println("ALTERNATIVE LOCUS TAG: " + alternativeLocusTag);
                    }

                    if (sCurrentLine.contains("/product=")) {
                        product = trimRemove(sCurrentLine, "/product=");
                        System.out.println("PRODUCT: " + product);
                    }

                    if (sCurrentLine.contains("/protein_id=\"")) {
                        proteinId = trimRemove(sCurrentLine, "/protein_id=\"");
                        System.out.println("PROTEIN ID: " + proteinId);

                    }

                    if (sCurrentLine.contains("/translation=\"")) {
                        if (!aminoAcidSequence.endsWith("\"")) {
                            aminoAcidSequence = trimRemove(sCurrentLine, "/translation=\"");
                        } else {
                            aminoAcidSequence = trimRemove(sCurrentLine, "/translation=\"");
                            aminoAcidSequence = aminoAcidSequence.replace("\"", "");
                            translation = false;
                        }
                        translation = true;
                    } else if (translation) {
                        if (sCurrentLine.startsWith("CONTIG") || sCurrentLine.startsWith("ORIGIN") || sCurrentLine.startsWith("BASE COUNT")) {
                            translation = false;
                        } else {
                            aminoAcidSequence = aminoAcidSequence + sCurrentLine.trim();

                            if (aminoAcidSequence.contains("\"")) {
                                aminoAcidSequence = aminoAcidSequence.replace("\"", "");
                                translation = false;
                                System.out.println("TRANSLATION: --------------------S");
                                System.out.println(aminoAcidSequence);
                            }
                        }

                    }
                }

                //SAVE THE LAST GENE
                if (!startPosition.isEmpty()) {

                    if (startPosition.contains("<")) {
                        startPosition = startPosition.replace("<", "");
                    }

                    if (startPosition.contains(">")) {
                        startPosition = startPosition.replace(">", "");
                    }

                    if (endPosition.contains("<")) {
                        endPosition = endPosition.replace("<", "");
                    }

                    if (endPosition.contains(">")) {
                        endPosition = endPosition.replace(">", "");
                    }

                    gene.setStartPosition(new BigInteger(startPosition));
                    gene.setEndPosition(new BigInteger(endPosition));
                    gene.setLocusTag(locusTag);
                    gene.setName(name);
                    gene.setProteinSequence(aminoAcidSequence);
                    gene.setProduct(product);
                    gene.setProteinId(proteinId);
                    gene.setAlternativeLocusTag(alternativeLocusTag);
                    gene.setOrientation(orientation);

                    if (!gene.getAlternativeLocusTag().isEmpty()) {
                        System.out.println(gene.getLocusTag() + "\t" + gene.getAlternativeLocusTag());
                    }
                    /*
                            UPDATE INSTEAD SAVING!
                     */
                    //geneDAO.save(gene);

                    //System.out.println(gene.toString());
                    Gene geneToUpdate = geneDAO.findByAltLocusTag(gene.getLocusTag());
                    System.out.println("\n\n-----------------------------------------");
                    System.out.println("geneLocus: " + gene.getLocusTag() + "\tgeneName: " + gene.getName());
                    if (!gene.getName().isEmpty() || gene.getName() != null) {
                        if (geneToUpdate != null) {
                            System.out.println("geneToUpdateAlterLocus: " + geneToUpdate.getAlternativeLocusTag() + "\tgeneName: " + geneToUpdate.getName());
                            geneToUpdate.setName(gene.getName());
                            geneDAO.update(geneToUpdate);
                        } else {
                            System.out.println("gene null in refseq");
                        }
                    } else {
                        System.out.println("There is no gene name for this gene!");
                    }
                    System.out.println("\n\n-----------------------------------------");
                    gene = new Gene();

                    System.out.println("SIZE: " + size);
                }

            } catch (IOException e) {

                e.printStackTrace();

            } finally {

                try {

                    if (br != null) {
                        br.close();
                    }

                    if (fr != null) {
                        fr.close();
                    }

                } catch (IOException ex) {

                    ex.printStackTrace();

                }

            }

        }
    }

    public String trimRemove(String string, String tag) {
        string = string.trim();
        string = string.replace(tag, "");
        string = string.replace("\"", "");
        string = string.replace("\t", "");
        string = string.replace("\'", "");
        string = string.trim();
        return string;

    }
}
