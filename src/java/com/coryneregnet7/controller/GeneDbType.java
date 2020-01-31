/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.controller;

import com.coryneregnet7.model.Gene;

/**
 *
 * @author doglas
 */
public class GeneDbType {

    private Gene gene;
    private String dbType;

    public GeneDbType() {
    }

    public GeneDbType(Gene gene, String dbType) {
        this.gene = gene;
        this.dbType = dbType;
    }

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }
}
