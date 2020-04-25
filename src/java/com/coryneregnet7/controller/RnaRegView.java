/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.controller;

import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Organism;
import com.coryneregnet7.model.SmallRna;
import java.math.BigDecimal;

/**
 *
 * @author mariana
 */
public class RnaRegView {
     private Integer id;
    private Gene targetGene;
    private SmallRna smallRna;
    private String role;
    private String evidence;
    private BigDecimal copraPvalue;
    
    public RnaRegView() {
        
    }

    public RnaRegView(Integer id, Gene targetGene, SmallRna smallRna, String role, String evidence, BigDecimal copraPvalue) {
        this.id = id;
        this.targetGene = targetGene;
        this.smallRna = smallRna;
        this.role = role;
        this.evidence = evidence;
        this.copraPvalue = copraPvalue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Gene getTargetGene() {
        return targetGene;
    }

    public void setTargetGene(Gene targetGene) {
        this.targetGene = targetGene;
    }

    public SmallRna getSmallRna() {
        return smallRna;
    }

    public void setSmallRna(SmallRna smallRna) {
        this.smallRna = smallRna;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public BigDecimal getCopraPvalue() {
        return copraPvalue;
    }

    public void setCopraPvalue(BigDecimal copraPvalue) {
        this.copraPvalue = copraPvalue;
    }
    
    
    
}
