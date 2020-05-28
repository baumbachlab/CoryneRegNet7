/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.controller;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.dao.SmallRnaDAO;
import com.coryneregnet7.model.Genome;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.Random;
 
/**
 * @author Crunchify.com
 * 
 */
 
@Controller
public class CrunchifySpringAjaxJQuery {
 
    @RequestMapping("ajax")
    public String helloAjaxTest() {
        return "internal/ajax";
    }
 
    @RequestMapping(value = "/ajaxtest", method = RequestMethod.GET)
    public @ResponseBody
    String getTime() {
 
        Random rand = new Random();
        float r = rand.nextFloat() * 100;
        String result = "<br>Next Random # is <b>" + r + "</b>. Generated on <b>" + new Date().toString() + "</b>";
        System.out.println("Debug Message from CrunchifySpringAjaxJQuery Controller.." + new Date().toString());
        return result;
    }
    
    @RequestMapping(value = "/ajaxList", method = RequestMethod.GET)
    public @ResponseBody
    String getList() {
        System.out.println("CREATING LIST! ");
        List list = new ArrayList();
        list.add(1);
        list.add(33);
        list.add(74);
        list.add(92);
        String str = list.toString();
        return str;
    }
    
    @RequestMapping(value = "/ajaxGeneList", method = RequestMethod.GET)
    public @ResponseBody
    String getGeneList(String id) {
        System.out.println("CREATING LIST! "+id);
        GenomeDAO genomeDAO = new GenomeDAO();
        Genome genome = genomeDAO.findByOrganism(Integer.parseInt(id));
        GeneDAO geneDAO = new GeneDAO();
        List<String> genes = geneDAO.bringLocusTagsByGenome(genome.getId());
       
        String str = genes.toString().replace("[", "");
        str = str.replace("]", "");
        return str;
    }
    
     @RequestMapping(value = "/ajaxRnaList", method = RequestMethod.GET)
    public @ResponseBody
    String getRnaList(String id) {
        System.out.println("CREATING LIST! "+id);
        GenomeDAO genomeDAO = new GenomeDAO();
        Genome genome = genomeDAO.findByOrganism(Integer.parseInt(id));
        SmallRnaDAO srnaDAO = new SmallRnaDAO();
        List<String> rnas = srnaDAO.bringLocusByGenome(genome.getId());
       
        String str = rnas.toString().replace("[", "");
        str = str.replace("]", "");
        return str;
    }
}