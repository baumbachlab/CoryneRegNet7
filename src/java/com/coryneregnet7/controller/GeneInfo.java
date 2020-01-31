/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.controller;

import com.coryneregnet7.model.Gene;
import java.util.ArrayList;

/**
 *
 * @author doglas
 */
public class GeneInfo {

    private Gene gene;
    private String operon;
    private Integer pos;

    public GeneInfo() {

    }

    public GeneInfo(Gene gene, String operon, Integer pos) {        
        this.gene = gene;
        this.operon = operon;
        this.pos = pos;
    }

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    public String getOperon() {
        return operon;
    }

    public void setOperon(String operon) {
        this.operon = operon;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "GeneInfo{" + "gene=" + gene + ", operon=" + operon + ", pos=" + pos + '}';
    }



}
