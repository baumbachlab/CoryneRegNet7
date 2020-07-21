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
public class GbffReader {

    public static void main(String[] args) throws InterruptedException {
        GbffReader g = new GbffReader();
        g.readFileIntoDatabase(new File("/data/home/mariana/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/target-brucella/Bab2308_cro2_genomic.gbff"), "target");
    }

    public void readIntoDatabase(String folderPath, String type) throws InterruptedException {

        //lê a pasta dos templates ou dos modelos. 
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        System.out.println(folderPath);
        System.out.println(listOfFiles.length);
        System.out.println(folderPath);
        for (File file : listOfFiles) {
            readFileIntoDatabase(file, type);
        }

    }

    public void readFileIntoDatabase(File file, String type) throws InterruptedException {
        System.out.println("file " + file.getPath());
        if (file.isFile() && file.getAbsolutePath().endsWith("_genomic.gbff")) {
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
                Organism organism = new Organism();

                Genome genome = new Genome();
                genome.setGbffFile(file.getAbsolutePath());
                //subtituir por os nomes do NCBI

                genome.setFnaFile(file.getAbsolutePath().replace("_genomic.gbff", "_cds_from_genomic.fna"));
                genome.setFaaFile(file.getAbsolutePath().replace("_genomic.gbff", "_protein.faa"));
                genome.setFastaFile(file.getAbsolutePath().replace("_genomic.gbff", "_genomic.fna"));

                String fileName = file.getAbsolutePath().replace("_genomic.gbff", "");
                String[] names = fileName.split("\\/");
                fileName = names[names.length - 1];
                genome.setGenomeName(fileName);

                while ((sCurrentLine = br.readLine()) != null) {
                    System.out.println(sCurrentLine);
                    if (sCurrentLine.startsWith("VERSION")) {
                        genome.setNcbiAccessionNumber(sCurrentLine.replace("VERSION", "").trim());
                    }

                    if (sCurrentLine.contains("/db_xref=\"taxon:")) {
                        organism.setTaxonomyId(trimRemove(sCurrentLine, "/db_xref=\"taxon:"));
                    }

                    if (sCurrentLine.contains("/organism=\"")) {
                        String info = trimRemove(sCurrentLine, "/organism=\"");
                        String[] organismInfo = info.split(" ");
                        organism.setGenera(organismInfo[0]);
                        organism.setSpecies(organismInfo[1]);

                    }

                    if (sCurrentLine.contains("/strain=\"")) {
                        organism.setStrain(trimRemove(sCurrentLine, "/strain=\""));
                        organism.setStrain(organism.getStrain().replace("\"", ""));

                    }

                    if (sCurrentLine.contains("/gene=\"")) {
                        break;
                    }

                }

                OrganismDAO organismDAO = new OrganismDAO();
                organism.setType(type);
                organism = (Organism) organismDAO.save(organism);

                genome.setOrganism(organism);
                GenomeDAO genomeDAO = new GenomeDAO();
                genomeDAO.save(genome);

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

                            gene.setGenome(genome);
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
                            geneDAO.save(gene);
                            System.out.println("gene " + gene.toString());
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

                    if (sCurrentLine.contains("source") && sCurrentLine.contains("..")) {
                        size = trimRemove(sCurrentLine, "source          1..");
                        System.out.println("source: " + size);
                        genomeDAO = new GenomeDAO();
                        genome.setSize(new BigInteger(size));
                        genomeDAO.update(genome);
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
                    gene.setGenome(genome);

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
                    geneDAO.save(gene);
                    gene = new Gene();

                    System.out.println("SIZE: "+size);
                }

                /*
                    SAVE NUCLETIDE-SEQUENCE FOR EACH GENE
                 */
                FnaReader fnaReader = new FnaReader();
                //System.out.println(genome.getFnaFile());
                fnaReader.read(genome.getFnaFile(), genome.getId());

                /*
                    SAVE GENOME SEARCH SPACE
                 */
                //System.out.println("SAVE SEARCH SPACE");
                FaaReader faaReader = new FaaReader();
                faaReader.read(genome.getFaaFile(), genome.getId());

                /*
                    SAVE REGULATION & OPERON IF IT IS A MODEL GENOME
                 */
                System.out.println("SAVE REGULATION & OPERON IF IT IS A MODEL GENOME");
                System.out.println("type => " + organism.getType() + "************************************");
                System.out.println("file => " + file + "************************************");
                try {

                    if (organism.getType().equals("model")) {
                        RegulationInput regulationFileReader = new RegulationInput();

                        regulationFileReader.readIntoDatabase(file.getAbsolutePath().replace("_genomic.gbff", ".tsv.ctftg"), genome);
                        //
                        File chipDataConsensus = new File(file.getAbsolutePath().replace("_genomic.gbff", "_chip_consensus.csv"));
                        if (chipDataConsensus.exists()) {
                            System.out.println("Exists!!!!!! " + chipDataConsensus.getAbsolutePath());
                            GetMinchChipDataMTB get = new GetMinchChipDataMTB();
                            get.getMotifs(chipDataConsensus.getAbsolutePath(), genome.getId());
                        }
                        // }
                        OperonFileReader operonFileReader = new OperonFileReader();
                        operonFileReader.readOperonFile(file.getAbsolutePath().replace("_genomic.gbff", ".op"), genome);
                    } else {

//                    //
//                        boolean targetOpExists = new File(file.getAbsolutePath().replace("_genomic.gbff", ".op")).exists();
//                        if (organism.getType().equals("target") && targetOpExists) {
//                            OperonFileReader operonFileReader = new OperonFileReader();
//                            operonFileReader.readOperonFile(file.getAbsolutePath().replace("_genomic.gbff", ".op"), genome);
//                        }

                        OperonPrediction opPred = new OperonPrediction();
                        opPred.predictOperon(genome.getGbffFile(), genome);
                    }

                } catch (Exception e) {
                    //System.out.println("ERRO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    e.printStackTrace();
                    //Thread.sleep(5000000);
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
