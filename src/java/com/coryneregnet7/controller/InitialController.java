/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.controller;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.RegulatoryInteractionDAO;
import com.coryneregnet7.dao.BindingSiteDAO;
import com.coryneregnet7.dao.OrganismDAO;
import com.coryneregnet7.dao.PredictedRegulatoryInteractionDAO;
import com.coryneregnet7.model.BindingSite;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Organism;
import com.coryneregnet7.model.PredictedRegulatoryInteraction;
import com.coryneregnet7.model.Regulation;
import com.coryneregnet7.model.RegulatoryInteraction;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mariana & Doglas
 */
@Controller
public class InitialController {

    @RequestMapping("requirements")
    public String requirements(Model model) {

        return "requirements";
    }

    @RequestMapping("workflow")
    public String workflow(Model model) {
        //  model.addAttribute("testString", "blablabla");
        return "workflow";
    }

    @RequestMapping("docs&help")
    public String docsHelp(Model model) {
        //  model.addAttribute("testString", "blablabla");
        return "docs&help";
    }

    @RequestMapping("d3Test")
    public String d3Test(Model model) {
        //  model.addAttribute("testString", "blablabla");
        return "d3Test";
    }

    @RequestMapping("basic-add-edge")
    public String basicAddEdge(Model model) {
        //  model.addAttribute("testString", "blablabla");
        return "basic-add-edge";
    }

    @RequestMapping("newjsp")
    public String newjsp(Model model) {
        //  model.addAttribute("testString", "blablabla");
        return "newjsp";
    }

    @RequestMapping("teste")
    public String nova3(Model model) {
        //  model.addAttribute("testString", "blablabla");
        RegulatoryInteractionDAO riDAO = new RegulatoryInteractionDAO();
        ArrayList<RegulationView> regulationsView = new ArrayList<>();
        ArrayList<RegulatoryInteraction> ris = new ArrayList<>();
        RegulationView regView = new RegulationView();
        BindingSiteDAO bindingSiteDAO = new BindingSiteDAO();
        ArrayList<BindingSite> bss = new ArrayList<>();

        ris = riDAO.findByOrganism(56);
        for (RegulatoryInteraction ri : ris) {
            //System.out.println("###############################################model");
            Regulation regulation = ri.getRegulation();
            ri.setRegulation(regulation);
            regView = new RegulationView();
            regView.setId(ri.getId());
            regView.setTargetGene(ri.getCorrespondentTargetGene());
            regView.setTranscriptionFactor(ri.getCorrespondentTranscriptionFactor());
            regView.setRole(ri.getRole());
            regView.setEvidence(ri.getEvidence());
            regView.setPmid(ri.getPmid());
            // System.out.println("--------------------- Model regulatedby: " + ri.toString());
            bss = (ArrayList<BindingSite>) bindingSiteDAO.findByRegularotyInteraction(ri.getId());
            String bssRI = "";
            for (BindingSite bs : bss) {
                if (bssRI.equals("")) {
                    bssRI = bs.getSequence();
                } else {
                    bssRI += ", " + bs.getSequence();
                }
            }
            regView.setBindingSite(bssRI);
            regulationsView.add(regView);
        }

        for (RegulationView rv : regulationsView) {
            System.out.println("============ " + rv.toString());
        }

        System.out.println("----------------------------- go to view");
        model.addAttribute("regulationsView", regulationsView);
        return "testelista";
    }
}
