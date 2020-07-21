/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.input;

import com.coryneregnet7.dao.OrganismDAO;
import com.coryneregnet7.model.Organism;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author mariana
 */
public class GetGenomesNCBI {

    public static void main(String[] args) throws Exception {
        GetGenomesNCBI getGenomes = new GetGenomesNCBI();

        ArrayList<String> genomes = new ArrayList<>();

      //  genomes.add("Anoxybacillus_amylolyticus");
      //  genomes.add("Anoxybacillus_ayderensis");
        genomes.add("Anoxybacillus_flavithermus");
//        genomes.add("Anoxybacillus_geothermalis");
//        genomes.add("Anoxybacillus_gonensis");
//        genomes.add("Anoxybacillus_kamchatkensis");
//        genomes.add("Anoxybacillus_mongoliensis");
//        genomes.add("Anoxybacillus_pushchinoensis");
//        genomes.add("Anoxybacillus_sp._103");
//        genomes.add("Anoxybacillus_sp._B2M1");
//        genomes.add("Anoxybacillus_sp._B7M1");
//        genomes.add("Anoxybacillus_sp._BCO1");
//        genomes.add("Anoxybacillus_sp._CHMUD");
//        genomes.add("Anoxybacillus_sp._EFIL");
//        genomes.add("Anoxybacillus_sp._KU2-6_11_");
//        genomes.add("Anoxybacillus_sp._MB8");
//        genomes.add("Anoxybacillus_sp._P3H1B");
//        genomes.add("Anoxybacillus_sp._PDR2");
//        genomes.add("Anoxybacillus_sp._UARK-01");
//        genomes.add("Anoxybacillus_suryakundensis");
//        genomes.add("Anoxybacillus_tepidamans");
//        genomes.add("Anoxybacillus_thermarum");
//        genomes.add("Anoxybacillus_vitaminiphilus");
        /*



         */

        for (String genome : genomes) {
            getGenomes.getGenomeFiles(genome,
                    "/data/home/mariana/Dropbox/Termofilas/genomes-refseq");
        }

    }

    public ArrayList<String> getGenomeNames() throws Exception {
        ArrayList<String> names = new ArrayList<>();
        URL url = new URL("ftp://ftp.ncbi.nih.gov/genomes/refseq/bacteria/");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;

        while ((line = reader.readLine()) != null) {
            // System.out.println(line);
            String[] splitedLine = line.split(" ");
            String bacteriaName = splitedLine[splitedLine.length - 1];
            bacteriaName = bacteriaName.replace("_", " ");
            //          System.out.println(bacteriaName);
            names.add(bacteriaName);
        }
        reader.close();
        return names;
    }

    public ArrayList<String[]> getCompleteGenomeFiles(String name, String directory) throws Exception {
        name = name.replace(" ", "_");
        URL url = new URL("ftp://ftp.ncbi.nih.gov/genomes/refseq/bacteria/" + name + "/assembly_summary.txt");
        System.out.println(url.getPath());
        System.out.println("AQUI 1");
        ArrayList<String[]> downloadedGenomes = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            System.out.println("AQUI 2");
            String line;
            String ftpPath = "";
            String assemblyLevel = "";
            String[] organismName = new String[3];

            //-------------------------
            //------DOWNLOAD FILES--------
            //-------------------------
            downloadedGenomes = new ArrayList<>();
            System.out.println("AQUI 3");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (!line.startsWith("#")) {
                    String[] splitedLine = line.split("\t");
                    System.out.println("-----------assembly_accession => " + splitedLine[0]);
                    System.out.println("-----------taxid => " + splitedLine[5]);
                    System.out.println("-----------organism_name => " + splitedLine[7]);
                    System.out.println("-----------infraspecific_name => " + splitedLine[8]);
                    System.out.println("-----------assembly_level => " + splitedLine[11]);
                    System.out.println("-----------asm_name => " + splitedLine[15]);
                    System.out.println("-----------ftp_path => " + splitedLine[19]);

                    assemblyLevel = splitedLine[11];
                    ftpPath = splitedLine[19];
                    organismName = new String[3];
                    organismName[0] = splitedLine[7] + " (" + splitedLine[8] + ")";

                    organismName[1] = splitedLine[0];

                    organismName[2] = ftpPath.substring(ftpPath.lastIndexOf("/") + 1, ftpPath.length());

                    String cdsFromGenomic = ftpPath
                            + ftpPath.substring(ftpPath.lastIndexOf("/"), ftpPath.length())
                            + "_cds_from_genomic.fna.gz";

                    String genomicFna = ftpPath
                            + ftpPath.substring(ftpPath.lastIndexOf("/"), ftpPath.length())
                            + "_genomic.fna.gz";

                    String genomicGbff = ftpPath
                            + ftpPath.substring(ftpPath.lastIndexOf("/"), ftpPath.length())
                            + "_genomic.gbff.gz";

                    String proteinFaa = ftpPath
                            + ftpPath.substring(ftpPath.lastIndexOf("/"), ftpPath.length())
                            + "_protein.faa.gz";
                    
                    System.out.println("NAME: "+ftpPath.substring(ftpPath.lastIndexOf("/"), ftpPath.length()));
                    //*_cds_from_genomic.fna.gz
                    //*_genomic.fna.gz
                    //*_genomic.gbff.gz
                    //*_protein.faa.gz
                    System.out.println("-----------cds_from_genomic => " + cdsFromGenomic);
                    System.out.println("-----------genomic.fna.gz => " + genomicFna);
                    System.out.println("-----------genomic.gbff.gz => " + genomicGbff);
                    System.out.println("-----------protein.faa.gz => " + proteinFaa);

                    if (assemblyLevel.equals("Complete Genome")) {
                        downloadedGenomes.add(organismName);
                        ArrayList<String> downloadCommands = new ArrayList<>();
                        String downloadCdsFromGenomic = "wget " + cdsFromGenomic;
                        String downloadGenomicFna = "wget " + genomicFna;
                        String downloadGenomicGbff = "wget " + genomicGbff;
                        String downloadProteinFaa = "wget " + proteinFaa;
                        
                        

                        downloadCommands.add(downloadCdsFromGenomic);
                        downloadCommands.add(downloadGenomicFna);
                        downloadCommands.add(downloadGenomicGbff);
                        downloadCommands.add(downloadProteinFaa);

//                        for (String downloadCommand : downloadCommands) {
//                            try {
//                                ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c",
//                                        downloadCommand);
//                                pb.directory(new File(directory));
//                                final Process p = pb.start();
//                                p.waitFor();
//                            } catch (Exception ex) {
//                                System.out.println(ex);
//                            }
//                        }
                    }

                }

            }
            reader.close();

            //-------------------------
            //------UNZIP FILES--------
            //-------------------------
            try {
                ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c",
                        "gunzip *.gz");
                pb.directory(new File(directory));
                final Process p = pb.start();
                p.waitFor();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } catch (Exception e) {
            System.out.println("NAO TEM ASSEMBLY.TXT");
        }

        return downloadedGenomes;

    }
    
    public void downloadFromFile(String file){
        
    }

    public ArrayList<String[]> getGenomeFiles(String name, String directory) throws Exception {
        name = name.replace(" ", "_");
        URL url = new URL("ftp://ftp.ncbi.nih.gov/genomes/refseq/bacteria/" + name + "/assembly_summary.txt");
        System.out.println("ftp://ftp.ncbi.nih.gov/genomes/refseq/bacteria/" + name + "/assembly_summary.txt");
        System.out.println("AQUI 1");
        ArrayList<String[]> downloadedGenomes = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            System.out.println("AQUI 2");
            String line;
            String ftpPath = "";
            String assemblyLevel = "";
            String[] organismName = new String[3];

            //-------------------------
            //------DOWNLOAD FILES--------
            //-------------------------
            downloadedGenomes = new ArrayList<>();
            System.out.println("AQUI 3");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (!line.startsWith("#")) {
                    String[] splitedLine = line.split("\t");
                    System.out.println("-----------assembly_accession => " + splitedLine[0]);
                    System.out.println("-----------taxid => " + splitedLine[5]);
                    System.out.println("-----------organism_name => " + splitedLine[7]);
                    System.out.println("-----------infraspecific_name => " + splitedLine[8]);
                    System.out.println("-----------assembly_level => " + splitedLine[11]);
                    System.out.println("-----------asm_name => " + splitedLine[15]);
                    System.out.println("-----------ftp_path => " + splitedLine[19]);

                    assemblyLevel = splitedLine[11];
                    ftpPath = splitedLine[19];
                    organismName = new String[3];
                    organismName[0] = splitedLine[7] + " (" + splitedLine[8] + ")";

                    organismName[1] = splitedLine[0];

                    organismName[2] = ftpPath.substring(ftpPath.lastIndexOf("/") + 1, ftpPath.length());

                    System.out.println("PATH: "+ftpPath.substring(ftpPath.lastIndexOf("/"), ftpPath.length()));
                    
                    String cdsFromGenomic = ftpPath
                            + ftpPath.substring(ftpPath.lastIndexOf("/"), ftpPath.length())
                            + "_cds_from_genomic.fna.gz";

                    String genomicFna = ftpPath
                            + ftpPath.substring(ftpPath.lastIndexOf("/"), ftpPath.length())
                            + "_genomic.fna.gz";

                    String genomicGbff = ftpPath
                            + ftpPath.substring(ftpPath.lastIndexOf("/"), ftpPath.length())
                            + "_genomic.gbff.gz";

                    String proteinFaa = ftpPath
                            + ftpPath.substring(ftpPath.lastIndexOf("/"), ftpPath.length())
                            + "_protein.faa.gz";
                    //*_cds_from_genomic.fna.gz
                    //*_genomic.fna.gz
                    //*_genomic.gbff.gz
                    //*_protein.faa.gz
                    System.out.println("-----------cds_from_genomic => " + cdsFromGenomic);
                    System.out.println("-----------genomic.fna.gz => " + genomicFna);
                    System.out.println("-----------genomic.gbff.gz => " + genomicGbff);
                    System.out.println("-----------protein.faa.gz => " + proteinFaa);

                    downloadedGenomes.add(organismName);
                    ArrayList<String> downloadCommands = new ArrayList<>();

                    String downloadCdsFromGenomic = "wget " + cdsFromGenomic;
                    String downloadGenomicFna = "wget " + genomicFna;
                    String downloadGenomicGbff = "wget " + genomicGbff;
                    String downloadProteinFaa = "wget " + proteinFaa;

                    downloadCommands.add(downloadCdsFromGenomic);
                    downloadCommands.add(downloadGenomicFna);
                    downloadCommands.add(downloadGenomicGbff);
                    downloadCommands.add(downloadProteinFaa);

//                    for (String downloadCommand : downloadCommands) {
//                        try {
//                            ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c",
//                                    downloadCommand);
//                            pb.directory(new File(directory));
//                            final Process p = pb.start();
//                            p.waitFor();
//                        } catch (Exception ex) {
//                            System.out.println(ex);
//                        }
//                    }

                }

            }
            reader.close();

            //-------------------------
            //------UNZIP FILES--------
            //-------------------------
            try {
                ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c",
                        "gunzip *.gz");
                pb.directory(new File(directory));
                final Process p = pb.start();
                p.waitFor();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("NAO TEM ASSEMBLY.TXT");
        }

        return downloadedGenomes;

    }

}
