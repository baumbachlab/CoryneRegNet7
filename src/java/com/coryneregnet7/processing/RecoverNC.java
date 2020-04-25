/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.coryneregnet7.dao.SmallRnaDAO;
import com.coryneregnet7.model.SmallRna;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author mariana
 */
public class RecoverNC {

    private final String strainName = "";
    private String prokaryotesFile = "/data/home/mariana/Dropbox/Doutorado/ncRNA/copra-test/CopraRNA-master/update_kegg2refseq/run/prokaryotes-small.txt";

    public static void main(String args[]) throws IOException {
        RecoverNC recoverNC = new RecoverNC();

        Hashtable<String, String> organismsNc = recoverNC.getOrganismsHash();

//        String currentStrain = "Corynebacterium ulcerans NCTC7908";
//        String currentNC = "NZ_LS483400.1";
        String currentStrain = "Corynebacterium glutamicum ATCC 13032";
        String currentNC = "NC_006958";
        //NC_004369.1

        File folder = new File("/data/home/mariana/Dropbox/Doutorado/ncRNA/six-strains-analysis/BSRD");
        File[] listOfFiles = folder.listFiles();

        //String folderToWrite = "/data/home/mariana/Dropbox/Doutorado/ncRNA/six-strains-analysis/srna-glutamicum-rnaz/";
        String folderToWrite = "/data/home/mariana/Dropbox/Doutorado/ncRNA/six-strains-analysis/BSRD";

        Integer countByStrainOfInterest = 0;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                //output-cgb_30685.fa
                //41 => /data/home/mariana/Dropbox/Doutorado/ncRNA/glutamicum/glass-go-run/sequences/output-cgb_10275.fa.sorted
                if (file.getName().startsWith("output-cg-") & file.getName().endsWith(".txt")) {
                    //if (file.getName().equals("output-cgb_02473_sample.fa")) {

                    System.out.println("file.getName() " + file.getName());
                    //RECOVER A SORTED LIST WITH THE HOMOLOGOUS.
                    ArrayList<HomologousRna> homologousList = recoverNC.run(file.getAbsolutePath(), organismsNc, currentNC, currentStrain);

                    System.out.println("HOMOLOGOUS LIST:" + homologousList.size());

                    //RECOVER A LIST WITH THE 2+ Doutput-cgb_31195.faISTANT HOMOLOGOUS WITH BIGGEST SIMILARITY. 
                    ArrayList<HomologousRna> homologousFile = recoverNC.selectHomologous(homologousList);

                    String sRnaLocus ="";
                    //---------IF FROM RNAz-----------------
                   // String sRnaLocus = file.getName().replace(".txt", "");
                   // System.out.println("sRNA locus= " + sRnaLocus);
                    //--------------------------------------

                    //---------IF FROM BSRD------------------
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line = br.readLine();
                    br.close();
                    String[] splitedLine = line.split("\\|");
                    sRnaLocus = splitedLine[0].replace(">", "");
                    //---------------------------------------
                    
                    
                    System.out.println("sRNA locus= " + sRnaLocus);
                    SmallRnaDAO smallRnaDAO = new SmallRnaDAO();
                    SmallRna smallRna = smallRnaDAO.findByLocusTag(sRnaLocus);
                    System.out.println("sRNA= " + smallRna.toString());
                    
                    homologousFile.add(0, new HomologousRna(currentNC, currentStrain, new Float(110), smallRna.getSequence()
                                                            , smallRna.getLocusTag()));
                    

                    //Corynebacterium ulcerans strain NCTC7910
//                    String interestStrain = "Corynebacterium diphtheriae NCTC13129";
//                    String interestSpecies = "Corynebacterium diphtheriae";
//                    ArrayList<HomologousRna> homologousFile = recoverNC.selectHomologousForRnaz(homologousList, interestStrain, interestSpecies);
//////
//////                    if (homologousFile != null) {
//////                        homologousFile.add(0, homologousList.get(0));
//////                        countByStrainOfInterest++;
//////                        //recoverNC.writeFile(homologousFile, folderToWrite+"c-diphtheriae-"+"copra-"+file.getName());
//////                        recoverNC.writeFile(homologousFile, folderToWrite+file.getName()+".sorted");
//////                        System.out.println("\n----FILE:");
//////                        for (HomologousRna homologousRna : homologousFile) {
//////                            System.out.println(homologousRna.toString());
//////                        }
//////                        System.out.println("----END FILE.");
//////                    } else {
//////                        // System.out.println("--NO HOMOLOGOUS FOR THIS SRNA.");
//////                    }
//
//////                    System.out.println("\n\n");
                    System.out.println("FINAL LIST:");
                    for (HomologousRna homologous : homologousFile) {
                        System.out.println(homologous);
                    }
                    System.out.println("\n");

                    File sortedFile = new File(file.getAbsolutePath() + ".sorted");
                    System.out.println("file: " + sortedFile);
                    recoverNC.writeFile(homologousFile, sortedFile.getAbsolutePath());
                }
            }
        }
        System.out.println("COUNT: " + countByStrainOfInterest);
        //recoverNC.run("/data/home/mariana/Dropbox/Doutorado/ncRNA/glutamicum/glass-go-run/sequences/output-cgb_09915.fa");
    }

    /*
     public static void main(String args[]) throws IOException {
        RecoverNC recoverNC = new RecoverNC();

        Hashtable<String, String> organismsNc = recoverNC.getOrganismsHash();

//        for (Map.Entry<String, String> entry : organismsNc.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//            System.out.println(key+"-> "+value);
//        }
        //File folder = new File("/data/home/mariana/Dropbox/Doutorado/ncRNA/glutamicum/glass-go-run/sequences/");
        
        String currentStrain="Corynebacterium diphtheriae NCTC 13129";
        String currentNC="NC_002935";
        
        File folder = new File("/data/home/mariana/Dropbox/Doutorado/ncRNA/six-strains-analysis/BSRD/");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                //output-cgb_30685.fa
                //41 => /data/home/mariana/Dropbox/Doutorado/ncRNA/glutamicum/glass-go-run/sequences/output-cgb_10275.fa.sorted
                if (file.getName().equals("output-cd-srna-5.txt")) {

                    Integer count = recoverNC.run(file.getAbsolutePath(), organismsNc, currentNC, currentStrain, new Float("80.00"));
                    if (count < 2) {
                        System.out.println("Again: ");
                        File sortedFile = new File(file.getAbsolutePath() + ".sorted");
                        sortedFile.delete();
                        count = recoverNC.run(file.getAbsolutePath(), organismsNc, currentNC, currentStrain, new Float("70.00"));

                        if (count < 2) {
                            sortedFile.delete();
                             System.out.println("Again: ");
                            count = recoverNC.run(file.getAbsolutePath(), organismsNc, currentNC, currentStrain, new Float("60.00"));
                        }
                    }
                }
            }
        }
        //recoverNC.run("/data/home/mariana/Dropbox/Doutorado/ncRNA/glutamicum/glass-go-run/sequences/output-cgb_09915.fa");
    }

     */
    public void writeFile(ArrayList<HomologousRna> homologousFile, String fileName) throws IOException {

        if (homologousFile.size() >= 3) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            for (HomologousRna homologousRna : homologousFile) {
                writer.write(">" + homologousRna.getNcId() + "\n" + homologousRna.getSequence() + "\n");
                //System.out.println(">" + homologousRna.getNcId() + "\n" + homologousRna.getSequence() + "\n");
            }

            writer.close();
        }

    }

    public ArrayList<HomologousRna> selectHomologousForRnaz(ArrayList<HomologousRna> homologousList, String strainOfInterest, String speciesOfInteres) {
        ArrayList<HomologousRna> homologousFile = new ArrayList<>();
        String mainNc = homologousList.get(0).getNcId();

        HomologousRna homologousStrainOfInterest = null;
        HomologousRna homologousSpeciesOfInterest = null;
        HomologousRna homologousMostSimilar = null;

        for (HomologousRna homologousRna : homologousList) {
            if (homologousRna.getStrainName().startsWith(strainOfInterest.trim())) {
                //   System.out.println("----------STRAIN OF INTEREST----------" + homologousRna.toString());
                homologousStrainOfInterest = homologousRna;

            } else if (homologousRna.getStrainName().startsWith(speciesOfInteres.trim()) && homologousSpeciesOfInterest == null) {
                // System.out.println("---------SPECIES OF INTEREST-----------" + homologousRna.toString());
                homologousSpeciesOfInterest = homologousRna;
            } else {

                if (homologousMostSimilar == null && homologousRna.getAlignment() != 110) {
                    homologousMostSimilar = homologousRna;
                    //    System.out.println("---------MOST SIMILAR---------" + homologousRna);

                }
            }

        }

        if (homologousStrainOfInterest == null) {
            return null;
        } else {
            homologousFile.add(homologousStrainOfInterest);
            if (homologousSpeciesOfInterest != null) {
                homologousFile.add(homologousSpeciesOfInterest);
            } else if (homologousMostSimilar != null) {
                homologousFile.add(homologousMostSimilar);
            } else {
                homologousFile.add(homologousStrainOfInterest);
            }

        }

        return homologousFile;
    }

    public ArrayList<HomologousRna> selectHomologous(ArrayList<HomologousRna> homologousList) {
        ArrayList<HomologousRna> homologousFile = new ArrayList<>();

        String mainNc = homologousList.get(0).getNcId();
        String mainSequence = homologousList.get(0).getSequence();
        String mainStrainName = homologousList.get(0).getStrainName();
        String[] genusSpecies = mainStrainName.split(" ");
        String mainAlignment = homologousList.get(0).getAlignment().toString();

        ArrayList<HomologousRna> sameSpecies = new ArrayList<>();
        ArrayList<HomologousRna> sameGenus = new ArrayList<>();
        ArrayList<HomologousRna> sameGenusDE = new ArrayList<>();
        ArrayList<HomologousRna> differentGenus = new ArrayList<>();

        ArrayList<String> sameSpeciesNC = new ArrayList<>();
        sameSpeciesNC.add(mainNc);
        ArrayList<String> sameGenusNC = new ArrayList<>();
        ArrayList<String> sameGenusDeNC = new ArrayList<>();
        ArrayList<String> sameGenusDeSpecies = new ArrayList<>();
        ArrayList<String> differentGenusNC = new ArrayList<>();

        int sameSpeciesCount = 0;
        int sameGenusCount = 0;
        int sameGenusDECount = 0;
        int differentGenusCount = 0;

        String distance = "";
        for (HomologousRna homologousRna : homologousList) {
            // System.out.println(homologousRna);

            //check for repeated NCs in all of them!
            //same species
            if (homologousRna.getStrainName().contains(genusSpecies[0]) && homologousRna.getStrainName().contains(genusSpecies[1])) {
                // System.out.println("same species");
                distance = "same species";
                if (!sameSpeciesNC.contains(homologousRna.getNcId())) {

                    if (sameSpeciesCount < 5) {
                        sameSpecies.add(homologousRna);
                        sameSpeciesNC.add(homologousRna.getNcId());
                        sameSpeciesCount++;
                    }
                }

                //same genus   
            } else if (homologousRna.getStrainName().contains(genusSpecies[0])) {
                //System.out.println("same genus");
                distance = "same genus";
                if (!sameGenusNC.contains(homologousRna.getNcId())) {
                    if (sameGenusCount < 5) {
                        sameGenus.add(homologousRna);
                        sameGenusNC.add(homologousRna.getNcId());
                        sameGenusCount++;
                    }
                }

                if (!sameGenusDeNC.contains(homologousRna.getNcId())) {
                    String[] currentGenusSpecies = homologousRna.getStrainName().split(" ");
//
                    //      System.out.println("same genus different species.");
                    //      System.out.println("Contais? " + (!sameGenusDeSpecies.contains(currentGenusSpecies[0] + " " + currentGenusSpecies[1])));
                    if (sameGenusDECount < 5 && !sameGenusDeSpecies.contains(currentGenusSpecies[0] + " " + currentGenusSpecies[1])
                            && !sameGenusDeNC.contains(homologousRna.getNcId())) {

                        sameGenusDE.add(homologousRna);
                        sameGenusDeNC.add(homologousRna.getNcId());
                        sameGenusDeSpecies.add(currentGenusSpecies[0] + " " + currentGenusSpecies[1]);
                        sameGenusDECount++;
                    }
                }

            } else {
                //  System.out.println("different genus.");
                distance = "different genus";
                if (!differentGenusNC.contains(homologousRna.getNcId())) {
                    if (differentGenusCount < 5) {
                        differentGenus.add(homologousRna);
                        differentGenusNC.add(homologousRna.getNcId());
                        differentGenusCount++;
                    }
                }
            }

            //   System.out.println("Alignment: " + homologousRna.getAlignment());
            //   System.out.println("\n");
        }

//        System.out.println("\nSAME SPECIES:");
//        for (HomologousRna sameS : sameSpecies) {
//            System.out.println(sameS);
//        }
//
//        System.out.println("\nSAME GENUS:");
//        for (HomologousRna sameG : sameGenus) {
//            System.out.println(sameG);
//        }
//
//        System.out.println("\nSAME GENUS DE:");
//        for (HomologousRna sameGDE : sameGenusDE) {
//            System.out.println(sameGDE);
//        }
//
//        System.out.println("\nDIFFERENT GENUS:");
//        for (HomologousRna differentG : differentGenus) {
//            System.out.println(differentG);
//        }
        //take whatever you  from sameGenusDE. 
        if (sameGenusDE.size() >= 2) {
            homologousFile.add(sameGenusDE.get(0));
            homologousFile.add(sameGenusDE.get(1));
            return homologousFile;

            //if you can take only one    
        } else if (sameGenusDE.size() >= 1) {
            homologousFile.add(sameGenusDE.get(0));

        }

        //if you are not done,
        if (homologousFile.size() < 2) {

            //if you have one from sameGenusDE, take the second from sameGenus.
            if (homologousFile.size() == 1 && sameGenus.size() >= 2) {

                homologousFile.add(sameGenus.get(1));
            }

            //if you have 0
            //try getting both from same genus. 
            if (homologousFile.size() == 0 && sameGenus.size() >= 2) {
                homologousFile.add(sameGenus.get(0));
                homologousFile.add(sameGenus.get(1));
                return homologousFile;

                //otherwise take one :)
            } else if (homologousFile.size() == 0 && sameGenus.size() >= 1) {
                homologousFile.add(sameGenus.get(0));
            }

        }

        //if you are not done,
        //take from same species
        if (homologousFile.size() < 2) {

            //if you already have one, than take the second from same species
            if (homologousFile.size() == 1 && sameSpecies.size() >= 1) {
                homologousFile.add(sameSpecies.get(0));
                return homologousFile;
            }

            //if it is empty, than try taking both from same species
            if (homologousFile.isEmpty() && sameSpecies.size() >= 2) {
                homologousFile.add(sameSpecies.get(0));
                homologousFile.add(sameSpecies.get(1));
                return homologousFile;

                //otherwise, take just one.
            } else if (homologousFile.isEmpty() && sameSpecies.size() >= 1) {
                homologousFile.add(sameSpecies.get(0));

            }
        }

        //if you are not done,
        //take from different genus
        if (homologousFile.size() < 2) {

            //if you have one, so take the other from different genus. 
            if (homologousFile.size() == 1 && differentGenus.size() >= 1) {
                homologousFile.add(differentGenus.get(0));
                return homologousFile;
            }

            //if it is empty, so try taking both from different genus.
            if (homologousFile.isEmpty() && differentGenus.size() >= 2) {
                homologousFile.add(differentGenus.get(0));
                homologousFile.add(differentGenus.get(1));
                return homologousFile;

                //otherwise take just one   
            } else if (homologousFile.isEmpty() && differentGenus.size() >= 1) {
                homologousFile.add(differentGenus.get(0));
            }
        }

        return homologousFile;
    }

    public Hashtable<String, String> getOrganismsHash() {
        String file = "/data/home/mariana/Dropbox/Doutorado/ncRNA/copra-test/CopraRNA-master/update_kegg2refseq/run/prokaryotes-small.txt";

        Hashtable<String, String> organismsNc = new Hashtable<String, String>();

        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                // System.out.println(sCurrentLine);
                String[] splitedLine = sCurrentLine.split("\t");
//                for (String string : splitedLine) {
//                    System.out.println("s0: " + string);
//                }
                if (splitedLine.length > 1) {
                    //System.out.println(splitedLine[0]+" "+splitedLine[1]);
                    organismsNc.put(splitedLine[0], splitedLine[1]);
                }
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
        return organismsNc;
    }

    public ArrayList<HomologousRna> run(String path, Hashtable<String, String> organismsNc, String currentNc, String currentStrain) throws IOException {
        Integer count = 0;
        BufferedReader br = null;
        FileReader fr = null;
        // BufferedWriter writer = new BufferedWriter(new FileWriter(path + ".sorted"));

        ArrayList<HomologousRna> homologous = new ArrayList<>();

        try {

            fr = new FileReader(path);
            br = new BufferedReader(fr);

            String sCurrentLine;

            boolean firstLine = true;

            count = 0;
            String ncId = "";
            String strainName = "";
            String label = "";
            Float alignV = new Float("0.0");
            while ((sCurrentLine = br.readLine()) != null) {
                //  System.out.println("\n" + sCurrentLine);

                if (sCurrentLine.contains(">") && !sCurrentLine.contains(":NC") && !sCurrentLine.contains(":NZ") && !firstLine) {
                    label = sCurrentLine;
                    //System.out.println("\n"+sCurrentLine);
                    int startAlign = sCurrentLine.indexOf("-p.c.VAL:") + 9;
                    int endAlign = sCurrentLine.indexOf("%-taxID");
                    String alignmentValue = sCurrentLine.substring(startAlign, endAlign).trim();
                    // System.out.println("alignmentValue: " + alignmentValue);
                    alignV = Float.valueOf(alignmentValue);

//                    if (alignV > cutoff) {
//                        System.out.println("BIGGER THAN "+cutoff);
//                    } else {
//                        System.out.println("SMALLER THAN "+cutoff);
//                    }
                }

                if (!firstLine) {

                    if (sCurrentLine.startsWith(">")) {
                        label = sCurrentLine;
                        //  System.out.println("count++");
                        count++;

                        sCurrentLine = sCurrentLine.replaceFirst(" ", "*");
                        sCurrentLine = sCurrentLine.replaceFirst(",", "*");
                        sCurrentLine = sCurrentLine.replaceFirst("complete genome", "*");
                        sCurrentLine = sCurrentLine.replaceFirst("SRP RNA for signal recognition particle", "*");

                        String[] splitedLine = sCurrentLine.split("\\*");
                        strainName = splitedLine[1].replace("chromosome", "");
                        strainName = strainName.replace("DNA", "");
                        strainName = strainName.replace("isolate ", "");
                        strainName = strainName.replace("complete genome", "");
                        strainName = strainName.replace("genome assembly", "");
                        strainName = strainName.replace("TPA: ", "");
                        strainName = strainName.replace("gravis ", "");
                        //strainName = strainName.replace("NCTC13129 ", "NCTC 13129");
                        // NCTC13129

                        //  System.out.println(strainName);
                        if (sCurrentLine.contains(", strain:")) {
                            //   System.out.println("--------------------");
                            int last = sCurrentLine.indexOf("-p.c.VAL:");
                            int first = sCurrentLine.indexOf(", strain:") + 9;

                            String substring = sCurrentLine.substring(first, last).trim();
                            //System.out.println(substring);
                            strainName = strainName.trim() + " " + substring;
                            //     System.out.println("strain name: " + strainName);
                        }

                        strainName = strainName.trim();
                        ncId = organismsNc.get(strainName);
                        //  System.out.println(strainName);
                        if (ncId == null) {
                            strainName = strainName.replace(" strain", "");
                            ncId = organismsNc.get(strainName);

                            if (ncId == null) {
                                final String name = strainName;

                                List<String> addresses = organismsNc.entrySet()
                                        .stream()
                                        .filter(entry -> entry.getKey().startsWith(name))
                                        .map(Map.Entry::getValue)
                                        .collect(Collectors.toList());

                                if (addresses != null && !addresses.isEmpty()) {
                                    ncId = addresses.get(0);
                                } else {
                                    System.out.println("NAO ACHOU!");

                                }
                                //System.out.println(sCurrentLine);
                                // System.out.println(strainName);
                                //System.out.println("addresses: " + ncId);
                            }

                        }
                        //            System.out.println("NC: " + ncId);

                        if (ncId != null) {
                            if (ncId.contains(" ")) {
                                ncId = ncId.substring(0, ncId.indexOf(" "));
                            }

                            if (ncId.contains(".")) {
                                ncId = ncId.substring(0, ncId.indexOf("."));
                            }

                            //    System.out.println(">" + ncId);
                            //             writer.write(">" + ncId + "\n");
                        } else {
                            count--;
                        }

                    }
                } else {
//                    String[] splitedLine = sCurrentLine.split(":");
//                    String ncId = splitedLine[2];
//                    if (ncId.contains(".")) {
//                        ncId = ncId.substring(0, ncId.indexOf("."));
//                    }
//                    ncId = ncId.substring(0, 2) + "_" + ncId.substring(2, ncId.length());
                    //   System.out.println(">" + ncId);

                    ncId = currentNc;
                    alignV = Float.valueOf("110");
                    strainName = currentStrain;
                    label = sCurrentLine;
                    //          System.out.println("GUARDEI O NC_ID.");
////////                    HomologousRna homologousRna = new HomologousRna(ncId, strainName, alignV, sCurrentLine);
////////                    homologous.add(homologousRna);
                    //         writer.write(">" + ncId + "\n");
                    firstLine = false;
                }

                //  System.out.println("COUNT: "+count);
                if (!sCurrentLine.contains(">") && ncId != null) {
                    //  System.out.println(sCurrentLine);
                    //         writer.write(sCurrentLine + "\n");
////                    System.out.println("*-----*\t>" + ncId);
////                    System.out.println("*-----*\t" + strainName);//alignV
////                    System.out.println("*-----*\t" + alignV);
////                    System.out.println("*-----*\t" + sCurrentLine);
////                    System.out.println("\n");

                    HomologousRna homologousRna = new HomologousRna(ncId, strainName, alignV, sCurrentLine, label);

                    if (homologous.size() == 0) {
                        homologous.add(homologousRna);
////                        System.out.println("ADDED FIRST!");
                    } else {
                        for (int i = 0; i < homologous.size(); i++) {
                            Float alignment = homologousRna.getAlignment();
                            Float alignmentList = homologous.get(i).getAlignment();

//                                System.out.println("EnergyCurrent: "+energy);
//                                System.out.println("EnergyList["+i+"]: "+energyList);
                            if (alignment > alignmentList) {
                                //  System.out.println("Energy current ("+energy+") is smaller than "+energyList+" energyList("+energyList+").");
                                int pos = i;

                                //         System.out.println("Insert in " + pos + " :) ");
                                homologous.add(pos, homologousRna);
                                //  System.out.println("------------------------------------");
//                                    for (RnaInteraction interaction1 : interactions) {
//                                        System.out.println("interactionList: "+interaction1.getsRNA()+" -> "+interaction1.getGene()+" "+interaction1.getInteractionEnergy());
//                                    }

                                break;
                            } else {
                                if (i == homologous.size() - 1) {
////                                    System.out.println("Isert at the end.");
                                    homologous.add(homologousRna);
                                    break;
                                }
                                //  System.out.println("Energy current ("+energy+") is bigger than "+energyList+" energyList("+energyList+").");
                            }
                        }
                    }

                }
            }
            System.out.println(count + " => " + path + ".sorted");
            //   writer.close();

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

        return homologous;

    }

    public Integer run(String path, Hashtable<String, String> organismsNc, Float cutoff) throws IOException {
        Integer count = 0;
        BufferedReader br = null;
        FileReader fr = null;

        //BufferedWriter writer = new BufferedWriter(new FileWriter(path + ".sorted"));
        try {

            fr = new FileReader(path);
            br = new BufferedReader(fr);

            String sCurrentLine;

            boolean firstLine = true;

            count = 0;
            boolean is100 = false;
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println("\n" + sCurrentLine);
                Float alignV = new Float("0.0");
                if (sCurrentLine.contains(">") && !sCurrentLine.contains(":NC") && !sCurrentLine.contains(":NZ") && !firstLine) {
                    System.out.println("\n" + sCurrentLine);
                    int startAlign = sCurrentLine.indexOf("-p.c.VAL:") + 9;
                    int endAlign = sCurrentLine.indexOf("%-taxID");
                    String alignmentValue = sCurrentLine.substring(startAlign, endAlign).trim();
                    System.out.println("alignmentValue: " + alignmentValue);
                    alignV = Float.valueOf(alignmentValue);
//                    if (alignV > cutoff) {
//                        System.out.println("BIGGER THAN "+cutoff);
//                    } else {
//                        System.out.println("SMALLER THAN "+cutoff);
//                    }
                }

                if (sCurrentLine.startsWith(">") && alignV >= cutoff) {
                    //  System.out.println("count++");
                    count++;
                    is100 = true;
                    sCurrentLine = sCurrentLine.replaceFirst(" ", "*");
                    sCurrentLine = sCurrentLine.replaceFirst(",", "*");

                    String[] splitedLine = sCurrentLine.split("\\*");
                    String strainName = splitedLine[1].replace("chromosome", "");
                    strainName = strainName.replace("DNA", "");
                    strainName = strainName.replace("isolate ", "");
                    strainName = strainName.replace("complete genome", "");
                    strainName = strainName.replace("genome assembly", "");

                    System.out.println(strainName);
                    if (sCurrentLine.contains(", strain:")) {
                        //   System.out.println("--------------------");
                        int last = sCurrentLine.indexOf("-p.c.VAL:");
                        int first = sCurrentLine.indexOf(", strain:") + 9;

                        String substring = sCurrentLine.substring(first, last).trim();
                        //   System.out.println(substring);
                        strainName = strainName.trim() + " " + substring;
                        //  System.out.println("strain name: " + strainName);
                    }
                    strainName = strainName.trim();
                    String ncId = organismsNc.get(strainName);
                    if (ncId == null) {
                        strainName = strainName.replace(" strain", "");
                        ncId = organismsNc.get(strainName);

                        if (ncId == null) {
                            final String name = strainName;

                            List<String> addresses = organismsNc.entrySet()
                                    .stream()
                                    .filter(entry -> entry.getKey().startsWith(name))
                                    .map(Map.Entry::getValue)
                                    .collect(Collectors.toList());

                            if (addresses != null && !addresses.isEmpty()) {
                                ncId = addresses.get(0);
                            } else {
                                //       System.out.println("NAO ACHOU!");

                            }
                            System.out.println(sCurrentLine);
                            System.out.println(strainName);
                            System.out.println("addresses: " + ncId);
                        }

                    }

                    if (ncId != null) {
                        if (ncId.contains(" ")) {
                            ncId = ncId.substring(0, ncId.indexOf(" "));
                        }

                        if (ncId.contains(".")) {
                            ncId = ncId.substring(0, ncId.indexOf("."));
                        }

                        //    System.out.println(">" + ncId);
                        //             writer.write(">" + ncId + "\n");
                    } else {
                        is100 = false;
                        count--;
                    }

                }

                if (firstLine) {
//                    String[] splitedLine = sCurrentLine.split(":");
//                    String ncId = splitedLine[2];
//                    if (ncId.contains(".")) {
//                        ncId = ncId.substring(0, ncId.indexOf("."));
//                    }
//                    ncId = ncId.substring(0, 2) + "_" + ncId.substring(2, ncId.length());
                    //   System.out.println(">" + ncId);
                    String ncId = "NC_006958";
                    //         writer.write(">" + ncId + "\n");
                    firstLine = false;
                    is100 = true;
                }

                //  System.out.println("COUNT: "+count);
                if (is100 == true && !sCurrentLine.contains(">")) {
                    //  System.out.println(sCurrentLine);
                    //         writer.write(sCurrentLine + "\n");
                    if (!sCurrentLine.contains(">")) {
                        is100 = false;
                        // System.out.println("count == 5 "+ (count >= 5) );
                        if (count >= 2) {
                            break;
                        }
                    }
                }
            }
            System.out.println(count + " => " + path + ".sorted");
            //   writer.close();

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

        return count;

    }

}
