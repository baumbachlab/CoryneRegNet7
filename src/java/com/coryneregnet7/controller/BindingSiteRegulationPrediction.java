/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.controller;

import com.coryneregnet7.dao.GeneDAO;
import com.coryneregnet7.model.BsJobResult;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Operon;
import java.util.List;

/**
 *
 * @author doglas
 */
public class BindingSiteRegulationPrediction {

    private Gene targetGene;
    private Gene transcriptionFactor;
    private Operon operon;
    private String evalue;
    private String sequence;
    private String knowedRegulation;
    private String homologousTFs;
    private String homologousTGs;

    public BindingSiteRegulationPrediction(Gene targetGene, Gene transcriptionFactor, Operon operon, String evalue, String sequence, String knowedRegulation, String homologousTFs, String homologousTGs) {
        this.targetGene = targetGene;
        this.transcriptionFactor = transcriptionFactor;
        this.operon = operon;
        this.evalue = evalue;
        this.sequence = sequence;
        this.knowedRegulation = knowedRegulation;
        this.homologousTFs = homologousTFs;
        this.homologousTGs = homologousTGs;
    }
    
     public BindingSiteRegulationPrediction(BsJobResult bsJobResult) {
        
        this.targetGene = bsJobResult.getTargetGene();
        this.transcriptionFactor = bsJobResult.getHmmFrom();
        this.operon = bsJobResult.getPredictedOperon();
        this.evalue = bsJobResult.getEvalue();
        this.sequence = bsJobResult.getSequence();
        this.knowedRegulation = bsJobResult.getAlreadyKnown();
        this.homologousTFs = bsJobResult.getHomologousTfTemplate();
        this.homologousTGs = bsJobResult.getHomologousTgTarget();
    }


    public BindingSiteRegulationPrediction() {

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

    public void setTranscriptionFactor(Gene transcriptionFactor) {
        this.transcriptionFactor = transcriptionFactor;
    }

    public Operon getOperon() {
        return operon;
    }

    public void setOperon(Operon operon) {
        this.operon = operon;
    }

    public String getEvalue() {
        return evalue;
    }

    public void setEvalue(String evalue) {
        this.evalue = evalue;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getKnowedRegulation() {
        return knowedRegulation;
    }

    public void setKnowedRegulation(String knowedRegulation) {
        this.knowedRegulation = knowedRegulation;
    }

    public String getHomologousTFs() {
        return homologousTFs;
    }

    public void setHomologousTFs(String homologousTFs) {
        this.homologousTFs = homologousTFs;
    }

    public String getHomologousTGs() {
        return homologousTGs;
    }

    public void setHomologousTGs(String homologousTGs) {
        this.homologousTGs = homologousTGs;
    }


    @Override
    public String toString() {
        return "BindingSiteRegulationPrediction{" + "targetGene=" + targetGene.getLocusTag() + ", transcriptionFactor=" + transcriptionFactor.getLocusTag() + ", operon=" + operon + ", evalue=" + evalue + ", sequence=" + sequence + ", knowedRegulation=" + knowedRegulation + ", homologousTFs=" + homologousTFs + ", homologousTGs=" + homologousTGs + '}';
    }
}
