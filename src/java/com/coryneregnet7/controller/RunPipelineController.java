/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.controller;

import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.OrganismDAO;
import com.coryneregnet7.dao.RunDAO;
import com.coryneregnet7.dao.RunGenomeDAO;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Organism;
import com.coryneregnet7.model.Run;
import com.coryneregnet7.model.RunGenome;
import com.coryneregnet7.model.RunGenomePK;
import com.coryneregnet7.processing.RunPipeline;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import org.jboss.weld.util.collections.ArraySet;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author mariana
 */
@EnableAsync
@Controller
public class RunPipelineController {

    @RequestMapping("run")
    public String run(Model model, String downloadMessage) throws Exception {
        //select distinct genera
        //select distinc species
        OrganismDAO organismDAO = new OrganismDAO();
        List<String> generaNamesTemplate = organismDAO.bringGenera("model");
//        System.out.println("generaNamesTemplate " + generaNamesTemplate);
//        for (String string : generaNamesTemplate) {
//            System.out.println(string);
//        }
        List<String> generaNamesTarget = organismDAO.bringGenera("target");

        List<Organism> allOrganisms = organismDAO.listAll();
        Set<String> speciesNamesTemplate = new ArraySet<>();
        Set<String[]> strainNamesTemplate = new ArraySet<>();

        Set<String> speciesNamesTarget = new ArraySet<>();
        Set<String[]> strainNamesTarget = new ArraySet<>();

        for (Organism organism : allOrganisms) {

            String species = organism.getGenera() + " " + organism.getSpecies();
            if (organism.getType().equals("model")) {
                speciesNamesTemplate.add(species);
            } else {
                speciesNamesTarget.add(species);
            }

            String[] strains = {organism.getGenera() + " " + organism.getSpecies(), organism.getStrain()};

            if (organism.getType().equals("model")) {
                strainNamesTemplate.add(strains);
            } else {
                strainNamesTarget.add(strains);
            }
        }

        String workspace = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/test";
        String targetfolder = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/target";
        String templateFolder = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/model";

        model.addAttribute("downloadMessage", downloadMessage);
        model.addAttribute("workspace", workspace);
        model.addAttribute("targetfolder", targetfolder);
        model.addAttribute("templateFolder", templateFolder);
        model.addAttribute("generaNamesTarget", generaNamesTarget);
        model.addAttribute("speciesNamesTarget", speciesNamesTarget);
        model.addAttribute("strainNamesTarget", strainNamesTarget);
        model.addAttribute("generaNamesTemplate", generaNamesTemplate);
        model.addAttribute("speciesNamesTemplate", speciesNamesTemplate);
        model.addAttribute("strainNamesTemplate", strainNamesTemplate);
        return "internal/setRunParameters";

    }

    @RequestMapping(value = "/getOperonStatus", method = RequestMethod.GET)
    public @ResponseBody
    String getOperonStatus(Integer id) {
        System.out.println("RUN ID: " + id);
        RunDAO runDAO = new RunDAO();
        Run run = runDAO.findById(id);

        String result = run.getOperons();
        if (run.getOperons() != null) {
            String[] splitedResult = result.split("\\/");
            if (splitedResult[0].equals(splitedResult[1])) {
                result = "Done (" + result + ")";

            }
        } else {
            result = "Not iniciated";
        }
        return result;
    }

    @RequestMapping(value = "/getHmmTemplateStatus", method = RequestMethod.GET)
    public @ResponseBody
    String getHmmTemplateStatus(Integer id) {
        System.out.println("RUN ID: " + id);
        RunDAO runDAO = new RunDAO();
        Run run = runDAO.findById(id);

        String result = run.getProfilesTemplate();
        if (run.getProfilesTemplate() != null) {
            String[] splitedResult = result.split("\\/");
            if (splitedResult[0].equals(splitedResult[1])) {
                result = "Done (" + result + ")";

            }
        } else {
            result = "Not iniciated";
        }
        return result;
    }

//getPromoterTemplateStatus.htm
    @RequestMapping(value = "/getPromoterTemplateStatus", method = RequestMethod.GET)
    public @ResponseBody
    String getPromoterTemplateStatus(Integer id) {
        System.out.println("RUN ID: " + id);
        RunDAO runDAO = new RunDAO();
        Run run = runDAO.findById(id);

        String result = run.getPromoterTemplate();
        if (run.getPromoterTemplate() != null) {
            String[] splitedResult = result.split("\\/");
            if (splitedResult[0].equals(splitedResult[1])) {
                result = "Done (" + result + ")";

            }
        } else {
            result = "Not iniciated";
        }
        return result;
    }

    //getPromoterTargetStatus
    @RequestMapping(value = "/getPromoterTargetStatus", method = RequestMethod.GET)
    public @ResponseBody
    String getPromoterTargetStatus(Integer id) {
        System.out.println("RUN ID: " + id);
        RunDAO runDAO = new RunDAO();
        Run run = runDAO.findById(id);

        String result = run.getPromoterTarget();
        if (run.getPromoterTarget() != null) {
            String[] splitedResult = result.split("\\/");
            if (splitedResult[0].equals(splitedResult[1])) {
                result = "Done (" + result + ")";

            }
        } else {
            result = "Not iniciated";
        }
        return result;
    }

    //getBlastStatus
    @RequestMapping(value = "/getBlastStatus", method = RequestMethod.GET)
    public @ResponseBody
    String getBlastStatus(Integer id) {
        System.out.println("RUN ID: " + id);
        RunDAO runDAO = new RunDAO();
        Run run = runDAO.findById(id);

        String result = run.getBlast();
        if (run.getBlast() != null) {
            String[] splitedResult = result.split("\\/");
            if (splitedResult[0].equals(splitedResult[1])) {
                result = "Done (" + result + ")";

            }
        } else {
            result = "Not iniciated";
        }
        return result;
    }

    //getBlastTemplateStatus
    @RequestMapping(value = "/getBlastTemplateStatus", method = RequestMethod.GET)
    public @ResponseBody
    String getBlastTemplateStatus(Integer id) {
        System.out.println("RUN ID: " + id);
        RunDAO runDAO = new RunDAO();
        Run run = runDAO.findById(id);

        String result = run.getBlastTemplate();
        if (run.getBlastTemplate() != null) {
            String[] splitedResult = result.split("\\/");
            if (splitedResult[0].equals(splitedResult[1])) {
                result = "Done (" + result + ")";

            }
        } else {
            result = "Not iniciated";
        }
        return result;
    }

    //getBlastTemplateStatus
    @RequestMapping(value = "/getBlastTargetStatus", method = RequestMethod.GET)
    public @ResponseBody
    String getBlastTargetStatus(Integer id) {
        System.out.println("RUN ID: " + id);
        RunDAO runDAO = new RunDAO();
        Run run = runDAO.findById(id);

        String result = run.getBlastTarget();
        if (run.getBlastTarget() != null) {
            String[] splitedResult = result.split("\\/");
            if (splitedResult[0].equals(splitedResult[1])) {
                result = "Done (" + result + ")";

            }
        } else {
            result = "Not iniciated";
        }
        return result;
    }

    //getBindingSiteStatus
    @RequestMapping(value = "/getBindingSiteStatus", method = RequestMethod.GET)
    public @ResponseBody
    String getBindingSiteStatus(Integer id) {
        System.out.println("RUN ID: " + id);
        RunDAO runDAO = new RunDAO();
        Run run = runDAO.findById(id);

        String result = run.getHmmer();
        if (run.getHmmer() != null) {
            String[] splitedResult = result.split("\\/");
            if (splitedResult[0].equals(splitedResult[1])) {
                result = "Done (" + result + ")";

            }
        } else {
            result = "Not iniciated";
        }
        return result;
    }

    //getHmmTargetStatus.htm
    @RequestMapping(value = "/getHmmTargetStatus", method = RequestMethod.GET)
    public @ResponseBody
    String getHmmTargetStatus(Integer id) {
        System.out.println("RUN ID: " + id);
        RunDAO runDAO = new RunDAO();
        Run run = runDAO.findById(id);

        String result = run.getProfilesTarget();
        if (run.getProfilesTarget() != null) {
            String[] splitedResult = result.split("\\/");
            if (splitedResult[0].equals(splitedResult[1])) {
                result = "Done (" + result + ")";

            }
        } else {
            result = "Not iniciated";
        }
        return result;
    }

    @Async
    @RequestMapping("testajax")
    @ResponseBody
    public void testAssync(Integer id, String transferFolder, String targetfolder, String templateFolder) throws InterruptedException, IOException {
        System.out.println("RECEBI ID: " + id);
        System.out.println("transferFolder = "+transferFolder);
        System.out.println("targetfolder = "+targetfolder);
        System.out.println("templateFolder = "+templateFolder);

        Run run;
        RunDAO runDAO = new RunDAO();
        run = runDAO.findById(id);
        System.out.println(run.toString());
        List<RunGenome> runGenomesTemplate = new ArrayList<RunGenome>();
        List<RunGenome> runGenomesTarget = new ArrayList<RunGenome>();
        List<Genome> templateGenomes = new ArrayList<>();
        List<Genome> targetGenomes = new ArrayList<>();
        RunGenomeDAO runGenomeDAO = new RunGenomeDAO();
        Genome genome = new Genome();
        GenomeDAO genomeDAO = new GenomeDAO();

        runGenomesTemplate = runGenomeDAO.findByRunRole(id, "template");
        for (RunGenome runGenome : runGenomesTemplate) {
            System.out.println("template " + runGenome.toString());
            genome = new Genome();
            genome = runGenome.getGenome();
            templateGenomes.add(genome);
            System.out.println("templateGenome: " + genome.toString());
        }

        runGenomesTarget = runGenomeDAO.findByRunRole(id, "target");
        for (RunGenome runGenome : runGenomesTarget) {
            System.out.println("target " + runGenome.toString());
            genome = new Genome();
            genome = runGenome.getGenome();
            targetGenomes.add(genome);
            System.out.println("targetGenome: " + genome.toString());
        }

//        transferFolder = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/test";
//        targetfolder = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/target";
//        templateFolder = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/model";
//        String transferFolder = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/ncbi-name-test/test";
//        String targetfolder = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/ncbi-name-test/target";
//        String templateFolder = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/ncbi-name-test/model";

        System.out.println("BLAST: " + run.getBlastCutoff());
        System.out.println("hmmer: " + run.getHmmerCutoff());

        RunPipeline runPipeline = new RunPipeline();
   //     runPipeline.runPipeline(transferFolder, targetfolder, templateFolder, run.getBlastCutoff(), run.getHmmerCutoff(), templateGenomes, targetGenomes, run);

    }

    @Async
    public void asyncTest() {

        for (int i = 0; i < 30; i++) {
            System.out.println("test --> " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                //  java.util.logging.Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @RequestMapping("setThresholds")
    public String setThresholds(Model model, String workspace, String templateFolder, String targetfolder, String[] targetList,
            String[] templateList, String blastCutoff, String hmmerCutoff) throws Exception {

        System.out.println("SET THRESOULDS ");
        System.out.println("workspace " + workspace);
        System.out.println("templateFolder " + templateFolder);
        System.out.println("targetfolder " + targetfolder);

        Run run = new Run();
        RunDAO runDAO = new RunDAO();
        run.setBlastCutoff(blastCutoff);
        run.setHmmerCutoff(hmmerCutoff);
        run = (Run) runDAO.save(run);
//
//        RunGenome runGenome = new RunGenome();
//        RunGenomePK runGenomePK = new RunGenomePK();
//        RunGenomeDAO runGenomeDAO = new RunGenomeDAO();
        Genome genome = new Genome();
        GenomeDAO genomeDAO = new GenomeDAO();

        OrganismDAO organismDAO = new OrganismDAO();
        List<Organism> targetOrganisms = new ArrayList<>();
        List<Organism> templateOrganisms = new ArrayList<>();
        List<Organism> targetOrganismsList = new ArrayList<>();
        List<Organism> templateOrganismsList = new ArrayList<>();
        List<Genome> targetGenomes = new ArrayList<Genome>();
        List<Genome> templateGenomes = new ArrayList<Genome>();
        Genome targetGenome = new Genome();
        Genome templateGenome = new Genome();

        for (String string : templateList) {

            if (!string.equals("all")) {
                String names[] = bringNames(string);

                templateOrganisms = organismDAO.findByName(names[0], names[1], names[2], "model");
                for (Organism templateOrganism : templateOrganisms) {
                    templateGenome = genomeDAO.findByOrganism(templateOrganism.getId());
                    templateGenomes.add(templateGenome);
                }

                templateOrganismsList.addAll(templateOrganisms);
                templateOrganisms = new ArrayList<>();
            } else {
                templateOrganismsList = organismDAO.findByType("model");
                targetGenomes = genomeDAO.findByOrgnismType("model");
            }

        }

        for (String string : targetList) {

            if (!string.equals("all")) {
                String names[] = bringNames(string);
                targetOrganisms = organismDAO.findByName(names[0], names[1], names[2], "target");

                for (Organism targetOrganism : targetOrganisms) {
                    targetGenome = genomeDAO.findByOrganism(targetOrganism.getId());
                    targetGenomes.add(targetGenome);
                }

                targetOrganismsList.addAll(targetOrganisms);
                targetOrganisms = new ArrayList<>();
            } else {
                //  System.out.println("é all?");
                targetOrganismsList = organismDAO.findByType("target");
                targetGenomes = genomeDAO.findByOrgnismType("target");
            }

        }

        System.out.println("\n\ncomplete templateOrganismsList");
        for (Genome template : templateGenomes) {
            System.out.println(template.toString());
            saveRunGenome(run.getId(), template.getId(), "template");
        }

        System.out.println("\n\ncomplete templateOrganismsList");
        for (Genome target : targetGenomes) {
            System.out.println(target.toString());
            saveRunGenome(run.getId(), target.getId(), "target");
        }

        if (run.getHmmerCutoff().isEmpty()) {
            hmmerCutoff = "default";
        }

        model.addAttribute("transferFolder", workspace);
        model.addAttribute("targetfolder", targetfolder);
        model.addAttribute("templateFolder", templateFolder);

        model.addAttribute("targetOrganismsList", targetOrganismsList);
        model.addAttribute("templateOrganismsList", templateOrganismsList);
        model.addAttribute("run", run);
        model.addAttribute("hmmerCutoff", hmmerCutoff);
        return "internal/pipelineResults";
    }

    public void saveRunGenome(int runId, int genomeId, String role) {
        RunGenome runGenome = new RunGenome();
        RunGenomePK runGenomePK = new RunGenomePK();
        RunGenomeDAO runGenomeDAO = new RunGenomeDAO();
        runGenome = new RunGenome();
        runGenomePK = new RunGenomePK(runId, genomeId);
        runGenome.setRole(role);
        runGenome.setRunGenomePK(runGenomePK);
        runGenome = (RunGenome) runGenomeDAO.save(runGenome);

    }

    @RequestMapping("transfer")
    public String transfer(Model model, String[] targetList, String[] templateList, String blastCutoff, String hmmerCutoff) throws Exception {

        OrganismDAO organismDAO = new OrganismDAO();
        List<Organism> targetOrganisms = new ArrayList<>();
        List<Organism> templateOrganisms = new ArrayList<>();
        List<Organism> targetOrganismsList = new ArrayList<>();
        List<Organism> templateOrganismsList = new ArrayList<>();
        GenomeDAO genomeDAO = new GenomeDAO();
        List<Genome> targetGenomes = new ArrayList<Genome>();
        List<Genome> templateGenomes = new ArrayList<Genome>();
        Genome targetGenome = new Genome();
        Genome templateGenome = new Genome();

        for (String string : templateList) {

            if (!string.equals("all")) {
                String names[] = bringNames(string);

                templateOrganisms = organismDAO.findByName(names[0], names[1], names[2], "model");
                for (Organism templateOrganism : templateOrganisms) {
                    templateGenome = genomeDAO.findByOrganism(templateOrganism.getId());
                    templateGenomes.add(templateGenome);
                }

                templateOrganismsList.addAll(templateOrganisms);
                templateOrganisms = new ArrayList<>();
            } else {
                templateOrganismsList = organismDAO.findByType("model");
            }

        }

        for (String string : targetList) {

            if (!string.equals("all")) {
                String names[] = bringNames(string);
                targetOrganisms = organismDAO.findByName(names[0], names[1], names[2], "target");

                for (Organism targetOrganism : targetOrganisms) {
                    targetGenome = genomeDAO.findByOrganism(targetOrganism.getId());
                    targetGenomes.add(targetGenome);
                }

                targetOrganismsList.addAll(targetOrganisms);
                targetOrganisms = new ArrayList<>();
            } else {
                //  System.out.println("é all?");
                targetOrganismsList = organismDAO.findByType("target");
            }

        }

        System.out.println("\n\ncomplete templateOrganismsList");
        for (Genome template : templateGenomes) {
            System.out.println(template.toString());
        }

        System.out.println("\n\ncomplete templateOrganismsList");
        for (Genome target : targetGenomes) {
            System.out.println(target.toString());
        }

//        String transferFolder = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/ncbi-name-test/test";
//        String targetfolder = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/ncbi-name-test/target";
//        String templateFolder = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/ncbi-name-test/model";
        String transferFolder = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/test";
        String targetfolder = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/target";
        String templateFolder = "/home/mariana/Dropbox/Doutorado/CoryneRegNet7/coryne-genus-test/model";

        System.out.println("BLAST: " + blastCutoff);
        System.out.println("hmmer: " + hmmerCutoff);

        RunPipeline run = new RunPipeline();
//        run.runPipeline(transferFolder, targetfolder, templateFolder, blastCutoff, hmmerCutoff, templateGenomes, targetGenomes, new Run());
        // Thread.sleep(10000);
        //testAssync();

        model.addAttribute("targetOrganismsList", targetOrganismsList);
        model.addAttribute("templateOrganismsList", templateOrganismsList);
        return "internal/pipelineResults";
    }

    public String[] bringNames(String string) {
        String[] splitedName = string.split(" ");
        String[] names = new String[3];
        names[0] = splitedName[0];
        names[1] = "%";
        names[2] = "%";
        if (splitedName.length >= 2) {
            names[1] = splitedName[1];
        }

        if (splitedName.length >= 3) {
            names[2] = "";
            for (int i = 2; i < splitedName.length; i++) {
                names[2] = names[2] + " " + splitedName[i];
            }
            names[2] = names[2].trim();
        }

        return names;
    }

}
