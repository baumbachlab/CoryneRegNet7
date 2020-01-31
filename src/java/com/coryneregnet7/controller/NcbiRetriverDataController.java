/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.controller;

import com.coryneregnet7.processing.input.GbffReader;
import com.coryneregnet7.processing.input.GetGenomesNCBI;
import java.io.File;
import java.util.ArrayList;
import java.util.Set;
import org.jboss.weld.util.collections.ArraySet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mariana
 */
@Controller
public class NcbiRetriverDataController {

    private ArrayList<String> speciesNames;
    private GetGenomesNCBI getGenomesNCBI;

    @RequestMapping("ncbiDataRecovery")
    public String ncbiDataRecovery(Model model, String path) throws Exception {

        System.out.println("PATH: " + path);
        getGenomesNCBI = new GetGenomesNCBI();
        this.speciesNames = getGenomesNCBI.getGenomeNames();

        Set<String> generaNames = new ArraySet<>();

        for (String speciesName : speciesNames) {
            String genera[] = speciesName.split(" ");

            if (!genera[0].isEmpty() && Character.isUpperCase(genera[0].charAt(0))) {
                generaNames.add(genera[0]);
            }

        }

        model.addAttribute("path", path);
        model.addAttribute("generaNames", generaNames);
        model.addAttribute("speciesNames", speciesNames);
        return "internal/ncbiDataRecovery";

    }

    @RequestMapping("organismsToDownload")
    public String organismsToDownload(Model model, String group, String[] generaList, String[] speciesList, String path) throws Exception {

        getGenomesNCBI = new GetGenomesNCBI();
        this.speciesNames = getGenomesNCBI.getGenomeNames();
        ArrayList<String[]> downloaded = new ArrayList<>();
        ArrayList<String[]> downloadedGenus = new ArrayList<>();

        System.out.println("---------GROUP-----------");
        System.out.println("------------------" + group);

        if (group.equals("genus")) {
            System.out.println("-------GENERA-----LIST----------" + generaList.length);
            for (int i = 0; i < generaList.length; i++) {
                System.out.println("--------------genero:------------ " + generaList[i]);
                for (int j = 0; j < this.speciesNames.size(); j++) {
                    System.out.println("---ESPÉCIE --> " + this.speciesNames.get(j));

                    if (this.speciesNames.get(j).startsWith(generaList[i])) {
                        System.out.println("É DO GENERO " + generaList[i]);
                        downloadedGenus = getGenomesNCBI.getCompleteGenomeFiles(speciesNames.get(j), path);
                        for (String[] string : downloadedGenus) {
                            System.out.println("======================");
                            System.out.println("======" + string[0]);
                            System.out.println("======" + string[1]);
                            System.out.println("======" + string[2]);
                        }
                        downloaded.addAll(downloaded.size(), downloadedGenus);
                        System.out.println("+++++++++++");
                    }
                }
            }
        } else if (group.equals("species")) {
            GbffReader gbffReader = new GbffReader();
            System.out.println("-------SPECIES-----LIST----------" + speciesList.length);
            for (int i = 0; i < speciesList.length; i++) {
                System.out.println("--------------------------" + speciesList[i]);
                downloaded = getGenomesNCBI.getCompleteGenomeFiles(speciesList[i], path);
                for (String[] string : downloadedGenus) {
                    System.out.println("======================");
                    System.out.println("======" + string[0]);
                    System.out.println("======" + string[1]);
                    System.out.println("======" + string[2]);
                    String filePath = path + "/" + string[2] + "_genomic.gbff";
                    gbffReader.readFileIntoDatabase(new File(filePath), path);
                    //downloaded.addAll(downloaded.size(), downloadedGenus);
                }
                System.out.println("+++++++++++");

            }
        } else {
            System.out.println("DOWNLOAD ALL");
            for (int j = 0; j < 30; j++) {
                System.out.println("---ESPÉCIE --> " + this.speciesNames.get(j));

                downloadedGenus = getGenomesNCBI.getCompleteGenomeFiles(speciesNames.get(j), path);
                for (String[] string : downloadedGenus) {
                    System.out.println("======================");
                    System.out.println("======" + string[0]);
                    System.out.println("======" + string[1]);
                    System.out.println("======" + string[2]);
                    downloaded.addAll(downloaded.size(), downloadedGenus);
                }
                System.out.println("+++++++++++");
            }
        }

        model.addAttribute("group", group);
        model.addAttribute("downloaded", downloaded);
        return "internal/downloadedOrgansms";
    }
}
