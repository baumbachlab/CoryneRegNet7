/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing;

import com.coryneregnet7.model.Gene;
import java.math.BigDecimal;

/**
 *
 * @author mariana
 */
public class TfTgPair {

    private Gene targetGene;
    private Gene transcriptionFactor;
    private Gene homologousTargetGene;
    private Gene homologousTranscriptionFactor;
    private BigDecimal htfEvalue;
    private BigDecimal htfPvalue;
    private BigDecimal htgEvalue;
    private BigDecimal htgPvalue;

    public TfTgPair() {
    }

    public Gene getTargetGene() {
        return targetGene;
    }

    public void setTargetGene(Gene targetGene) {
        this.targetGene = targetGene;
    }

    public Gene getTranscriptionFactor() {
        return transcriptionFactor;
    }

    public void setTranscriptionFactor(Gene trasncriptionFactor) {
        this.transcriptionFactor = trasncriptionFactor;
    }

    public Gene getHomologousTargetGene() {
        return homologousTargetGene;
    }

    public void setHomologousTargetGene(Gene homologousTargetGene) {
        this.homologousTargetGene = homologousTargetGene;
    }

    public Gene getHomologousTranscriptionFactor() {
        return homologousTranscriptionFactor;
    }

    public void setHomologousTranscriptionFactor(Gene homologousTrasncriptionFactor) {
        this.homologousTranscriptionFactor = homologousTrasncriptionFactor;
    }

    @Override
    public String toString() {
        return "TfTgPair{" + "targetGene=" + targetGene.getLocusTag() + ", transcriptionFactor=" + transcriptionFactor.getLocusTag() + ", homologousTargetGene=" + homologousTargetGene.getLocusTag() + ", homologousTranscriptionFactor=" + homologousTranscriptionFactor.getLocusTag() + ", htfEvalue=" + htfEvalue + ", htfPvalue=" + htfPvalue + ", htgEvalue=" + htgEvalue + ", htgPvalue=" + htgPvalue + '}';
    }
    
     public String toString2() {
        return "TfTgPair{" + "targetGene=" + targetGene + ", transcriptionFactor=" + transcriptionFactor + ", homologousTargetGene=" + homologousTargetGene + ", homologousTranscriptionFactor=" + homologousTranscriptionFactor + ", htfEvalue=" + htfEvalue + ", htfPvalue=" + htfPvalue + ", htgEvalue=" + htgEvalue + ", htgPvalue=" + htgPvalue + '}';
    }
    
    public BigDecimal getHtfEvalue() {
        return htfEvalue;
    }

    public void setHtfEvalue(BigDecimal htfEvalue) {
        this.htfEvalue = htfEvalue;
    }

    public BigDecimal getHtfPvalue() {
        return htfPvalue;
    }

    public void setHtfPvalue(BigDecimal htfPvalue) {
        this.htfPvalue = htfPvalue;
    }

    public BigDecimal getHtgEvalue() {
        return htgEvalue;
    }

    public void setHtgEvalue(BigDecimal htgEvalue) {
        this.htgEvalue = htgEvalue;
    }

    public BigDecimal getHtgPvalue() {
        return htgPvalue;
    }

    public void setHtgPvalue(BigDecimal htgPvalue) {
        this.htgPvalue = htgPvalue;
    }
    
    
    
}
