/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.input;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.model.Gene;
import java.io.BufferedReader;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.w3c.dom.NamedNodeMap;

public class ReadDbtbsXMLFile {

    public static void main(String argv[]) {

        ReadDbtbsXMLFile read = new ReadDbtbsXMLFile();
        read.getRegulations();
        //read.bringSubtiwikiNames();

//        try {
//
//            File fXmlFile = new File("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/new regulatory data/bacilus subtulis/dbtbs/dbtbs.xml");
//            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
//                    .newDocumentBuilder();
//
//            Document doc = dBuilder.parse(fXmlFile);
//
//            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
//
//            if (doc.hasChildNodes()) {
//
//                printNote(doc.getChildNodes());
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void getRegulations() {
        try {

            File fXmlFile = new File("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/new regulatory data/bacilus subtulis/dbtbs/dbtbs.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            // System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("promoter");

            Hashtable<String, String> synomyms = bringSynomyms();
            Hashtable<String, String> subtiwikiNames = bringSubtiwikiNames();

            // System.out.println("----------------------------");
            String tg = "";
            String tf = "";
            String role = "";
            String bs = "";
            String pmid = "";

            //System.out.println("gene\ttfac\tregulation\tsequene\tpmid");
            System.out.println("Transcription factor\tTranscription factor name\tType\tRole\tTarget Gene\tTarget gene name"
                    + "\tEvidence\tPubmedIDs\tBinding motif\tIs CDS sigma factor\tSource");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                // System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    NodeList nodeList = nNode.getChildNodes();
                    int nodeListSize = nodeList.getLength();

                    tg = "";
                    tf = "";
                    role = "";
                    bs = "";
                    pmid = "";
                    for (int i = 0; i < nodeListSize; i++) {
                        Node node = nodeList.item(i);
                        // System.out.println("getNodeName: " + node.getNodeName());
                        // System.out.println("getTextContent: " + node.getTextContent());

                        if (node.getNodeName().equals("gene")) {

                            tg = node.getTextContent();
                            //   System.out.println("tg " + tg);
                        }

                        if (node.getNodeName().equals("tfac")) {

                            tf = node.getTextContent();
                            //      System.out.println("tf " + tf);
                        }

                        if (node.getNodeName().equals("regulation")) {

                            role = node.getTextContent();
                            //      System.out.println("role " + role);
                        }

                        if (node.getNodeName().equals("sequence")) {

                            bs = node.getTextContent();
                            //        System.out.println("bs " + bs);
                        }

                        if (node.getChildNodes() != null) {
                            NodeList childs = node.getChildNodes();
                            for (int j = 0; j < childs.getLength(); j++) {
                                Node nodeChild = childs.item(j);
                                //   System.out.println("\tCHILD: getNodeName: " + nodeChild.getNodeName());
                                // System.out.println("getNodeValue: "+node.getNodeValue());
                                //    System.out.println("\tCHILD:getTextContent: " + nodeChild.getTextContent());

                                if (nodeChild.getNodeName().equals("pubmed")) {
                                    //    System.out.println("pubmed = " + nodeChild.getTextContent());

                                    if (pmid.isEmpty()) {
                                        pmid = nodeChild.getTextContent();
                                    } else {
                                        pmid = pmid + ", " + nodeChild.getTextContent();
                                    }
                                    // System.out.println("pmid 3: " + pmid);
                                    //  System.out.println("pmid 1:"+pmid);
                                }

                                // System.out.println("pmid 2:"+pmid);
                            }

                        }

                        //pmid = "";
                        //  System.out.println("------------------------");
                    }
                    //String opName = eElement.getElementsByTagName("name").item(0).getTextContent();
                    //String gene = eElement.getElementsByTagName("gene").item(0).getTextContent();

                    //System.out.println(gene);
                }

                // System.out.println(tg + "\t" + tf + "\t" + role + "\t" + bs + "\t" + pmid);
                if (role.equals("Negative")) {
                    role = "Repressor";
                } else if (role.equals("Positive")) {
                    role = "Activator";
                } else {
                    role = "-";
                }

                if (!tg.isEmpty() && !tf.isEmpty()) {

                    GeneDAO geneDAO = new GeneDAO();
                    Gene geneTF = new Gene();

                    Character firstLetter = tf.charAt(0);
                    String lowerCaseFirstLetter = firstLetter.toString().toLowerCase();
                    String tfName = tf.replaceFirst(firstLetter.toString(), lowerCaseFirstLetter);
                    // System.out.println("TF " + tf);

                    // System.out.println("SYNOMYMS: " + tfName + " => " + synomyms.get(tfName) + "----");
                    if (synomyms.get(tfName) != null) {
                        tfName = synomyms.get(tfName);
                        //     System.out.println("SYNONYM FOUND");
                    }

                    geneTF = geneDAO.findByGenomeNameUnique(1066, tfName);
                    if (geneTF == null) {
                        geneTF = geneDAO.findByGenomeNameUnique(1067, tfName);

                        if (geneTF == null && subtiwikiNames.get(tfName) != null) {

                            geneTF = geneDAO.findByGenomeOldLocusTag(1066, subtiwikiNames.get(tfName));
                          //  System.out.println("subtiwikiNames "+geneTF.toString());
                        }

                        if (geneTF != null) {
                            geneTF = geneDAO.findByGenomeOldLocusTag(1066, geneTF.getLocusTag());
                        }
                    }

                    // System.out.println("tfName " + tfName);
                    // System.out.println("geneTF = " + geneTF);
                    List<Gene> geneTG = new ArrayList<Gene>();
                    //    System.out.println("TG " + tg);
                    geneTG = geneDAO.findByGenomeName(1066, tg);

                    if (geneTG == null) {
                        geneTG = geneDAO.findByGenomeName(1067, tg);
                        
                        if (geneTG == null && subtiwikiNames.get(tfName) != null) {

                            geneTG = geneDAO.findByGenomeOldLocusTagMultiple(1066, subtiwikiNames.get(tfName));
                          //  System.out.println("subtiwikiNames "+geneTG.toString());
                        }
                    }
                    //    System.out.println("geneTG = " + geneTG);

                    for (int i = 0; i < geneTG.size(); i++) {
                        String isSigma = "";
                        if (role.equals("-")) {
                            isSigma = "+";
                        }

                        if (!bs.isEmpty()) {
                            bs = bs.replace("{", "");
                            bs = bs.replace("}", "");
                            bs = bs.replace("/", "");
                        }

                        if (geneTG != null && geneTF != null) {
                            System.out.println(geneTF.getLocusTag() + "\t" + tf + "\t - \t" + role + "\t" + geneTG.get(i).getLocusTag() + "\t" + tg
                                    + "\texperimental" + "\t" + pmid + "\t" + bs + "\t" + isSigma + "\tDBTBS");
                        }
                    }
                }

                //  System.out.println("\n\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printNote(NodeList nodeList) {

        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);

            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                // get node name and value
                System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
                System.out.println("Node Value =" + tempNode.getTextContent());

                if (tempNode.hasAttributes()) {

                    // get attributes names and values
                    NamedNodeMap nodeMap = tempNode.getAttributes();

                    for (int i = 0; i < nodeMap.getLength(); i++) {

                        Node node = nodeMap.item(i);
                        System.out.println("attr name : " + node.getNodeName());
                        System.out.println("attr value : " + node.getNodeValue());

                    }

                }

                if (tempNode.hasChildNodes()) {

                    // loop again if has child nodes
                    printNote(tempNode.getChildNodes());

                }

                System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

            }

        }

    }

    public void getOperons() {
        try {

            File fXmlFile = new File("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/new regulatory data/bacilus subtulis/dbtbs/dbtbs.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("operon");

            System.out.println("----------------------------");
            int count = 0;
            int countOutra = 0;

            Hashtable<String, String> synonyms = bringSynomyms();
            Hashtable<String, String> subtiwikiNames = bringSubtiwikiNames();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                //System.out.println("New regulation: ");
                Node nNode = nList.item(temp);

                // System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    String opName = eElement.getElementsByTagName("name").item(0).getTextContent();
                    String opGenes = eElement.getElementsByTagName("genes").item(0).getTextContent();
                    String[] genes = opGenes.split(",");
                    //  System.out.print(opName);
                    GeneDAO geneDAO = new GeneDAO();
                    List<Gene> geneOpList = new ArrayList<Gene>();
                    for (String gene : genes) {
                        // System.out.print("\t" + gene);
                    }

                    String printLine = "";
                    String opNameLine = "";
                    String orientation = "0";
                    // System.out.println("");
                    if (genes.length > 1) {

                        for (String gene : genes) {
                            //    System.out.println("------- nome: " + gene);

                            //   System.out.println(" " + gene + " gene synonym => " + synonyms.get(gene));
                            if (synonyms.get(gene) != null) {
                                //       System.out.println(" " + gene + " ACHOU SYNONYM: " + synonyms.get(gene));
                                gene = synonyms.get(gene);

                            }

                            geneOpList = geneDAO.findByGenomeName(1066, gene);
                            //   System.out.println("gene op list " + geneOpList.size());
                            if (geneOpList.size() == 0) {
                                count++;
                                //System.out.println("--nao achou " + gene);
                                geneOpList = geneDAO.findByGenomeName(1067, gene);
                                //  System.out.println("gene op list from NC " + geneOpList.size());
                                if (geneOpList.size() == 0) {

                                    //  System.out.println(gene + " subtiwikiNames.get(gene) " + subtiwikiNames.get(gene));
                                    if (subtiwikiNames.get(gene) != null) {

                                        Gene g1 = geneDAO.findByGenomeOldLocusTag(1066, subtiwikiNames.get(gene));
                                        geneOpList.add(g1);
                                        // System.out.println("subtiwikiNames "+g1.toString());
                                    }

                                    if (geneOpList.size() == 0) {
                                        // System.out.println("--nao achou mesmo " + gene);
                                        countOutra++;
                                    }
                                }

                            }
                            for (Gene gene1 : geneOpList) {
                                // System.out.println("----------- gene " + gene1);

                                if (gene1 != null) {

                                    // System.out.println("----------- " + gene + " => " + gene1.getLocusTag());
                                    if (!gene1.getLocusTag().contains("_")) {
                                        gene1 = geneDAO.findByGenomeOldLocusTag(1066, gene1.getLocusTag());

                                    }

                                    //    System.out.println("----------- " + gene + " => " + gene1.getLocusTag());
                                    orientation = gene1.getOrientation();
                                    if (printLine.isEmpty()) {
                                        printLine = gene1.getLocusTag();
                                    } else {
                                        printLine = printLine + "\t" + gene1.getLocusTag();
                                    }

                                } else {
                                    //  System.out.println("GENE NULL");
                                }
                            }

                        }
                        if (!printLine.isEmpty()) {
                            String opNameLineArray[] = printLine.split("\t");

                            if (opNameLineArray.length > 1) {

//                        String orientation = geneOpList.get(0).getOrientation();
                                if (orientation.equals("forward")) {
                                    orientation = "+";
                                } else {
                                    orientation = "-";
                                }
                                printLine = ">OP_" + opNameLineArray[0] + "\t" + orientation + "\t" + printLine;
                                System.out.println(printLine);
                            }
                        }
                    }

                }

            }

//            System.out.println("count " + count);
//            System.out.println("countOutra " + countOutra);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Hashtable<String, String> bringSubtiwikiNames() {
        Hashtable<String, String> subtiwikiNames = new Hashtable();
        BufferedReader br = null;
        FileReader fr = null;

        String[] genesDeInteresse = {"yaaJ", "gcaD", "yacF", "yacM", "yacN", "yazC", "yacO", "skfD", "ycbE", "ycbF", "ycbH", "rtpLP", "ycdH", "ycdI",
            "yceA", "ycsK", "rsbR", "yflM", "yfkB", "glvR", "yfiD", "yfiE", "yfiW", "yfiX", "yfhG", "yhdO", "yirY", "appA", "goxB", "yjmB", "ylxA",
            "ylnD", "ylnE", "ylnF", "ylxG", "csfD", "ypuG", "ypuH", "ypuE", "ribT", "spoVAE", "punA", "yqiQ", "yqfY", "yqeT", "yqeV", "spoIVCB",
            "spoIIIC", "yrhM", "fatR", "yrhJ", "yrvE", "obg", "ysxC", "yurP", "yurO", "yurN", "yurM", "yurL", "yvfK", "yvfL", "yvfM", "lacA",
            "yvfO", "yveB", "tuaA", "ywsC", "ywtA", "ywtB", "ywtC", "ywpH", "nrgA", "nrgB", "ywjF", "ywbL", "ywbM", "ywbN", "dra", "idh", "fbaB",
            "yxaG", "yxaA", "rocF", "yycF", "yycG", "yycH", "yyxA", "ssb", "soj", "spo0J", "thdF", "yyaA", "yhcP", "ytlB", "yusM", "yusL", "yusJ",
            "sdpC", "yrhA", "yvrI", "yrvO", "trmU", "ylaO"};

        ArrayList<String> array = new ArrayList<>(Arrays.asList(genesDeInteresse));

        try {

            fr = new FileReader("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/new regulatory data/bacilus subtulis/dbtbs/gene_info_subtiwiki.txt");
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                //  System.out.println(sCurrentLine);
                String[] splitedLine = sCurrentLine.split("\t");
                // System.out.println("array.contains(splitedLine[0]) "+array.contains(splitedLine[0]));

                if (array.contains(splitedLine[1])) {
                    subtiwikiNames.put(splitedLine[1].trim(), splitedLine[0].trim());
                }

            }
//            
            for (Map.Entry<String, String> entry : subtiwikiNames.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                // System.out.println("key:-" + key + "-=>-" + value + "-");
            }

            // System.out.println("  -===> " + subtiwikiNames.get("yuaF"));
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

        return subtiwikiNames;

    }

    public Hashtable<String, String> bringSynomyms() {
        Hashtable synomyms = new Hashtable();
        synomyms.put("yvaZ", "sdpI");
        synomyms.put("yvbA", "sdpR");
        synomyms.put("ywkC", "racA");
        synomyms.put("ymaA", "nrdI");
        synomyms.put("yjbD", "spx");
        synomyms.put("yjbD", "spxA");
        synomyms.put("yrhI", "fatR");
        synomyms.put("yvaN", "rghR");
        synomyms.put("xynP", "xynC");
        synomyms.put("yvtA", "htrB");
        synomyms.put("yvgR", "cysJ");
        synomyms.put("yvgQ", "cysI");
        synomyms.put("ywfK", "cysL");
        synomyms.put("ywcJ", "nirC");
        synomyms.put("ypbR", "mrgC");
        synomyms.put("yoaN", "oxdD");
        synomyms.put("ydjK", "iolT");
        synomyms.put("ykvW", "zosA");
        synomyms.put("yurH", "pucF");
        synomyms.put("yurG", "pucG");
        synomyms.put("ywoE", "pucI");
        synomyms.put("ytiP", "pbuO");
        synomyms.put("yxjA", "nupG");
        synomyms.put("ydhL", "pbuE");
        synomyms.put("yvaW", "sdpA");
        synomyms.put("yvaX", "sdpB");
        synomyms.put("yvaY", "sdpC");
        synomyms.put("ybcO", "skfA");
        synomyms.put("ybcP", "skfB");
        synomyms.put("ybcS", "skfC");
        synomyms.put("ybcT", "skfD");
        synomyms.put("ybdA", "skfE");
        synomyms.put("ybdB", "skfF");
        synomyms.put("ybdD", "skfG");
        synomyms.put("ybdE", "skfH");
        synomyms.put("bglC", "gluB");
        synomyms.put("yccC", "ansZ");
        synomyms.put("yjcI", "metI");
        synomyms.put("yjcJ", "metC");
        synomyms.put("yveK", "epsA");
        synomyms.put("yveL", "epsB");
        synomyms.put("yveM", "epsC");
        synomyms.put("yveN", "epsD");
        synomyms.put("yveO", "epsE");
        synomyms.put("yveP", "epsF");
        synomyms.put("yveQ", "epsG");
        synomyms.put("yveR", "epsH");
        synomyms.put("yveS", "epsI");
        synomyms.put("yveT", "epsJ");
        synomyms.put("yvfA", "epsK");
        synomyms.put("yvfB", "epsK");
        synomyms.put("yvfC", "epsL");
        synomyms.put("yvfD", "epsM");
        synomyms.put("yvfE", "epsN");
        synomyms.put("yvfF", "epsO");
        synomyms.put("yjbX", "cotO");
        synomyms.put("ylbO", "gerR");
        synomyms.put("ywhE", "pbpG");
        synomyms.put("yrrR", "pbpI");
        synomyms.put("ywdL", "gerQ");
        synomyms.put("yphA", "csfD");
        synomyms.put("yfjS", "pdaA");
        synomyms.put("ykvV", "spoIVH");
        synomyms.put("ykvV", "stoA");
        synomyms.put("yvdP", "cotQ");
        synomyms.put("yvjB", "ctpB");
        synomyms.put("yjcC", "spoVIF");
        synomyms.put("ytaA", "cotI");
        synomyms.put("ywoA", "bcrC");
        synomyms.put("yndN", "fosB");
        synomyms.put("ywkA", "maeA");
        synomyms.put("yufL", "malK");
        synomyms.put("yufM", "malR");
        synomyms.put("ykrT", "mtnK");
        synomyms.put("ykrS", "mtnS");
        synomyms.put("ykrS", "mtnA");
        synomyms.put("ykrU", "mtnU");
        synomyms.put("ykrV", "mtnV");
        synomyms.put("ykrW", "mtnW");
        synomyms.put("ykrX", "mtnX");
        synomyms.put("ykrY", "mtnY");
        synomyms.put("ykrY", "mtnB");
        synomyms.put("ykrZ", "mtnZ");
        synomyms.put("ykrZ", "mtnD");
        synomyms.put("yugE", "mtnE");
        synomyms.put("mtn", "mtnN");
        synomyms.put("yhcL", "tcyP");
        synomyms.put("ytmJ", "tcyJ");
        synomyms.put("ytmK", "tcyK");
        synomyms.put("ytmL", "tcyL");
        synomyms.put("ytmM", "tcyM");
        synomyms.put("ytmN", "tcyN");
        synomyms.put("yckK", "tcyA");
        synomyms.put("yckJ", "tcyB");
        synomyms.put("yckI", "tcyC");
        synomyms.put("yocF", "desK");
        synomyms.put("yocG", "desR");
        synomyms.put("ybgJ", "glsA");
        synomyms.put("ybgH", "glnT");
        synomyms.put("ycbA", "glnK");
        synomyms.put("ycbB", "glnL");
        synomyms.put("yfiA", "glvR");
        synomyms.put("yklA", "ohrA");
        synomyms.put("ykzA", "ohrB");
        synomyms.put("ykmA", "ohrR");
        synomyms.put("mtrB", "TRAP");
        synomyms.put("yrxA", "nadR");
        synomyms.put("yueK", "pncB");
        synomyms.put("yrzC", "cymR");
        synomyms.put("ybbM", "rsiW");
        synomyms.put("yoaW", "G4");
        synomyms.put("ytxD", "motP");
        synomyms.put("ytxE", "motS");
        synomyms.put("yqzB", "ccpN");
        synomyms.put("ydiH", "rex");
        synomyms.put("ybaL", "salA");
        synomyms.put("yqfS", "nfo");
        synomyms.put("yabP", "spoVUA");
        synomyms.put("yabQ", "spoVUB");
        synomyms.put("trnS-Leu1", "leuF");
        synomyms.put("trnS-Leu2", "leuG");
        synomyms.put("yczA", "rtpA");
        synomyms.put("yhaG", "trpP");
        synomyms.put("yvaB", "acpD");
        synomyms.put("citG", "fumC");
        synomyms.put("metS", "metG");
        synomyms.put("yuiE", "pepA");
        synomyms.put("yqdB", "txpA");
        synomyms.put("ylpC", "fapR");
        synomyms.put("yusC", "metN");
        synomyms.put("yusB", "metP");
        synomyms.put("yusA", "metQ");
        synomyms.put("ykrI", "rsgI");
        synomyms.put("yhjM", "ntdR");
        synomyms.put("yhjL", "ntdA");
        synomyms.put("yhjK", "ntdB");
        synomyms.put("yhjJ", "ntdC");
        synomyms.put("ftsW", "ylaO");
        synomyms.put("yhdE", "nsrR");
        synomyms.put("ytsC", "bceA");
        synomyms.put("ytsD", "bceB");
        synomyms.put("ytsA", "bceR");
        synomyms.put("ytsB", "bceS");
        synomyms.put("yvqC", "liaR");
        synomyms.put("yvqE", "liaS");
        synomyms.put("yvqI", "liaI");
        synomyms.put("yvqH", "liaH");
        synomyms.put("yvqG", "liaG");
        synomyms.put("yvqF", "liaF");
        synomyms.put("yozR", "gerT");
        synomyms.put("ysiA", "fadR");
        return synomyms;
    }

}
