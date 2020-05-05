/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.controller;

import com.coryneregnet7.model.BindingSite;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Organism;
import com.coryneregnet7.model.PredictedRegulatoryInteraction;
import com.coryneregnet7.model.Regulation;
import com.coryneregnet7.model.RegulatoryInteraction;
import com.coryneregnet7.model.SmallRna;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

/**
 *
 * @author doglas
 */
public class RegulationView {

    private Integer id;//no
    private Gene targetGene;
    private Gene transcriptionFactor;
    private String role;
    private String evidence;
    private String bindingSite;//no
    private Long coregulators;//no
    private String pmid;//no
    private String operon;
    private Organism modelOrganism;//no
    private BigDecimal pValue;
    private Long regulatorsDensity;//non

    public RegulationView(Integer id, Gene targetGene, Gene transcriptionFactor, String role, String evidence, String bindingSite, Long coregulators, String pmid, String operon, Organism modelOrganism, BigDecimal pValue, Long regulatorsDensity) {
        this.id = id;
        this.targetGene = targetGene;
        this.transcriptionFactor = transcriptionFactor;
        this.role = role;
        this.evidence = evidence;
        this.bindingSite = bindingSite;
        this.coregulators = coregulators;
        this.pmid = pmid;
        this.operon = operon;
        this.modelOrganism = modelOrganism;
        this.pValue = pValue;
        this.regulatorsDensity = regulatorsDensity;
    }

    public RegulationView() {
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

    public Gene getTranscriptionFactor() {
        return transcriptionFactor;
    }

    public void setTranscriptionFactor(Gene transcriptionFactor) {
        this.transcriptionFactor = transcriptionFactor;
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

    public String getBindingSite() {
        return bindingSite;
    }

    public void setBindingSite(String bindingSite) {
        this.bindingSite = bindingSite;
    }

    public Long getCoregulators() {
        return coregulators;
    }

    public void setCoregulators(Long coregulators) {
        this.coregulators = coregulators;
    }

    public String getPmid() {
        return pmid;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public String getOperon() {
        return operon;
    }

    public void setOperon(String operon) {
        this.operon = operon;
    }

    public Organism getModelOrganism() {
        return modelOrganism;
    }

    public void setModelOrganism(Organism modelOrganism) {
        this.modelOrganism = modelOrganism;
    }

    public BigDecimal getpValue() {
        return pValue;
    }

    public void setpValue(BigDecimal pValue) {
        this.pValue = pValue;
    }

    public Long getRegulatorsDensity() {
        return regulatorsDensity;
    }

    public void setRegulatorsDensity(Long regulatorsDensity) {
        this.regulatorsDensity = regulatorsDensity;
    }

    @Override
    public String toString() {
        return "RegulationView{" + "id=" + id + ", targetGene=" + targetGene + ", transcriptionFactor=" + transcriptionFactor + ", role=" + role + ", evidence=" + evidence + ", bindingSite=" + bindingSite + ", coregulators=" + coregulators + ", pmid=" + pmid + ", operon=" + operon + ", modelOrganism=" + modelOrganism + ", pValue=" + pValue + ", regulatorsDensity=" + regulatorsDensity + '}';
    }

}
