/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mariana
 */
@Entity
@Table(name = "predicted_regulatory_interaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PredictedRegulatoryInteraction.findAll", query = "SELECT p FROM PredictedRegulatoryInteraction p ORDER BY p.id")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findById", query = "SELECT p FROM PredictedRegulatoryInteraction p WHERE p.id = :id")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findByRegulatoryInteraction", query = "SELECT p FROM PredictedRegulatoryInteraction p WHERE p.regulatoryInteraction = :regulatoryInteraction")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findRI", query = "SELECT p.regulatoryInteraction FROM PredictedRegulatoryInteraction p WHERE p.regulatoryInteraction = :regulatoryInteraction")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findAllRIByModelTargetGenome", query = "SELECT p.regulatoryInteraction FROM PredictedRegulatoryInteraction p WHERE p.regulatoryInteraction.correspondentTargetGene.genome = :templateGenome and p.homologousTranscriptionFactor.genome =:targetGenome")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.bringNumByModelTargetGenome", query = "SELECT count(p) FROM PredictedRegulatoryInteraction p WHERE p.regulatoryInteraction.correspondentTranscriptionFactor.genome = :templateGenome and p.homologousTranscriptionFactor.genome =:targetGenome")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findNumByModelTargetGenome", query = "SELECT p FROM PredictedRegulatoryInteraction p WHERE p.regulatoryInteraction.correspondentTranscriptionFactor.genome = :templateGenome and p.homologousTranscriptionFactor.genome =:targetGenome")
        , @NamedQuery(name = "PredictedRegulatoryInteraction.findByHtgEvalue", query = "SELECT p FROM PredictedRegulatoryInteraction p WHERE p.htgEvalue = :htgEvalue")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findByHtfEvalue", query = "SELECT p FROM PredictedRegulatoryInteraction p WHERE p.htfEvalue = :htfEvalue")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.bringNumberOfPredictedRegulatoryInteractions", query = "SELECT count(pr) FROM PredictedRegulatoryInteraction pr")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.bringNumberOfBSs", query = "SELECT count(pr.predictedBindingSite) FROM PredictedRegulatoryInteraction pr WHERE pr.homologousTargetGene.genome.organism.id = :organismId")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.bringNumberOfRegulators", query = "SELECT count (distinct pr.homologousTranscriptionFactor) FROM PredictedRegulatoryInteraction pr")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.bringNumberOfPRIsOfOrganism", query = "SELECT count(pr) FROM PredictedRegulatoryInteraction pr WHERE pr.homologousTargetGene.genome.organism.id = :organismId")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.bringNumberOfRegulatorsOfOrganism", query = "SELECT count (distinct pr.homologousTranscriptionFactor) FROM PredictedRegulatoryInteraction pr WHERE pr.homologousTargetGene.genome.organism.id = :organismId")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.bringNumberOfRegulatedGenesOfOrganism", query = "SELECT count (distinct pr.homologousTargetGene) FROM PredictedRegulatoryInteraction pr WHERE pr.homologousTargetGene.genome.organism.id = :organismId")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.bringNumberOfPredictedRegulators", query = "SELECT count (distinct pr.homologousTranscriptionFactor) FROM PredictedRegulatoryInteraction pr")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.bringNumberOfPredictedRegulatedGenes", query = "SELECT count (distinct pr.homologousTargetGene) FROM PredictedRegulatoryInteraction pr")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findRegulates", query = "SELECT pr FROM PredictedRegulatoryInteraction pr WHERE pr.homologousTranscriptionFactor = :transcriptionFactor order by pr.homologousTargetGene.locusTag")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findRegulatedBy", query = "SELECT pr FROM PredictedRegulatoryInteraction pr WHERE pr.homologousTargetGene = :targetGene order by pr.homologousTranscriptionFactor.locusTag")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.bringNumberOfCoregulators", query = "SELECT count (distinct pr.homologousTranscriptionFactor) FROM PredictedRegulatoryInteraction pr WHERE pr.homologousTargetGene.locusTag = :locusTag")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findByOrganism", query = "SELECT pr FROM PredictedRegulatoryInteraction pr WHERE pr.homologousTranscriptionFactor.genome.organism =:organism order by pr.homologousTranscriptionFactor")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findByTfTgId", query = "SELECT pr FROM PredictedRegulatoryInteraction pr WHERE pr.homologousTranscriptionFactor.id = :tfId and pr.homologousTargetGene.id = :tgId")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findByGenomeDistinct", query = "SELECT distinct r.homologousTranscriptionFactor FROM PredictedRegulatoryInteraction r WHERE r.homologousTranscriptionFactor in (SELECT g from Gene g where g.genome=:genome)")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findByHtf", query = "SELECT p.predictedBindingSite FROM PredictedRegulatoryInteraction p WHERE p.homologousTranscriptionFactor = :homologousTranscriptionFactor")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.bringRoleByTf", query = "SELECT distinct p.predictedRole FROM PredictedRegulatoryInteraction p WHERE p.homologousTranscriptionFactor = :homologousTranscriptionFactor")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findByDistinctTG", query = "SELECT distinct pr.homologousTargetGene FROM PredictedRegulatoryInteraction pr")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findByOrganismAndDistinctTG", query = "SELECT distinct pr.homologousTargetGene FROM PredictedRegulatoryInteraction pr WHERE pr.homologousTargetGene.genome.organism.id = :organismId")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.bringNumberOfRegulationsOfTg", query = "SELECT count(pr) FROM PredictedRegulatoryInteraction pr WHERE pr.homologousTargetGene = :gene")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findByDistinctTF", query = "SELECT distinct pr.homologousTranscriptionFactor FROM PredictedRegulatoryInteraction pr")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findByOrganismDistinctTF", query = "SELECT distinct pr.homologousTranscriptionFactor FROM PredictedRegulatoryInteraction pr WHERE pr.homologousTranscriptionFactor.genome.organism.id = :organismId")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findBindingSitesOfOrganism", query = "SELECT  pr.predictedBindingSite FROM PredictedRegulatoryInteraction pr WHERE pr.homologousTranscriptionFactor.genome.organism.id = :organismId")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findPredictedBindingSites", query = "SELECT  pr.predictedBindingSite FROM PredictedRegulatoryInteraction pr WHERE pr.predictedRole='A' or pr.predictedRole='R'")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findActivationsAndRepressionsPRIsWithBS", query = "SELECT pr FROM PredictedRegulatoryInteraction pr WHERE (pr.predictedRole='A' or pr.predictedRole='R') and pr.predictedBindingSite is not null")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findByOrganismActivationsAndRepressionsPRIsWithBS", query = "SELECT pr FROM PredictedRegulatoryInteraction pr WHERE pr.homologousTargetGene.genome.organism.id = :organismId and (pr.predictedRole='A' or pr.predictedRole='R') and pr.predictedBindingSite is not null")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.bringNumberOfRegulationsOfTf", query = "SELECT count(pr) FROM PredictedRegulatoryInteraction pr WHERE pr.homologousTranscriptionFactor = :gene")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findTgsOfTf", query = "SELECT distinct pr.homologousTargetGene FROM PredictedRegulatoryInteraction pr WHERE pr.homologousTranscriptionFactor = :id")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findTFsOfTg", query = "SELECT distinct pr.homologousTranscriptionFactor FROM PredictedRegulatoryInteraction pr WHERE pr.homologousTargetGene = :id")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.bringNumberOfRIsByOrganism", query = "SELECT count(pr) FROM PredictedRegulatoryInteraction pr WHERE pr.homologousTranscriptionFactor.genome.organism.id = :organismId")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.bringNumberOfRegulatoryInteractions", query = "SELECT count(pr) FROM PredictedRegulatoryInteraction pr")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.bringNumberOfRIsByOrganismAndRole", query = "SELECT count(pr) FROM PredictedRegulatoryInteraction pr WHERE pr.predictedRole = :role and pr.homologousTargetGene.genome.organism.id = :organismId")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.bringNumberOfRIsByRole", query = "SELECT count(pr) FROM PredictedRegulatoryInteraction pr WHERE pr.predictedRole = :role")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findByOrganismTFAndRole", query = "SELECT distinct pr.homologousTargetGene FROM PredictedRegulatoryInteraction pr WHERE pr.homologousTranscriptionFactor.genome.organism.id = :organismId and pr.homologousTranscriptionFactor.id = :tf and pr.homologousTargetGene.genome.organism.id = :organismId and pr.predictedRole = :role")
    , @NamedQuery(name = "PredictedRegulatoryInteraction.findByOrganismAndGene", query = "SELECT pr FROM PredictedRegulatoryInteraction pr WHERE pr.homologousTranscriptionFactor.genome.organism.id = :organismId and (pr.homologousTranscriptionFactor.id = :id or pr.homologousTargetGene.id = :id) order by pr.homologousTranscriptionFactor")
})

public class PredictedRegulatoryInteraction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "regulatory_interaction", referencedColumnName = "id")
    @ManyToOne
    private RegulatoryInteraction regulatoryInteraction;
    @Column(name = "htg_evalue")
    private BigDecimal htgEvalue;
    @Column(name = "htf_evalue")
    private BigDecimal htfEvalue;
    @JoinColumn(name = "predicted_binding_site", referencedColumnName = "id")
    @ManyToOne
    private BindingSite predictedBindingSite;
    @JoinColumn(name = "homologous_target_gene", referencedColumnName = "id")
    @ManyToOne
    private Gene homologousTargetGene;
    @JoinColumn(name = "homologous_transcription_factor", referencedColumnName = "id")
    @ManyToOne
    private Gene homologousTranscriptionFactor;
    @Column(name = "htg_pvalue")
    private BigDecimal htgPvalue;
    @Column(name = "htf_pvalue")
    private BigDecimal htfPvalue;
    @Column(name = "predicted_role")
    private String predictedRole;
    @Column(name = "interaction_pvalue")
    private BigDecimal interactionPvalue;
    @JoinColumn(name = "operon_target_gene", referencedColumnName = "id")
    @ManyToOne
    private Gene operonTargetGene;

    public PredictedRegulatoryInteraction() {
    }

    public PredictedRegulatoryInteraction(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RegulatoryInteraction getRegulatoryInteraction() {
        return regulatoryInteraction;
    }

    public void setRegulatoryInteraction(RegulatoryInteraction regulatoryInteraction) {
        this.regulatoryInteraction = regulatoryInteraction;
    }

    public BigDecimal getHtgEvalue() {
        return htgEvalue;
    }

    public void setHtgEvalue(BigDecimal htgEvalue) {
        this.htgEvalue = htgEvalue;
    }

    public BigDecimal getHtfEvalue() {
        return htfEvalue;
    }

    public void setHtfEvalue(BigDecimal htfEvalue) {
        this.htfEvalue = htfEvalue;
    }

    public BindingSite getPredictedBindingSite() {
        return predictedBindingSite;
    }

    public void setPredictedBindingSite(BindingSite predictedBindingSite) {
        this.predictedBindingSite = predictedBindingSite;
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

    public void setHomologousTranscriptionFactor(Gene homologousTranscriptionFactor) {
        this.homologousTranscriptionFactor = homologousTranscriptionFactor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PredictedRegulatoryInteraction)) {
            return false;
        }
        PredictedRegulatoryInteraction other = (PredictedRegulatoryInteraction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public BigDecimal getHtgPvalue() {
        return htgPvalue;
    }

    public void setHtgPvalue(BigDecimal htgPvalue) {
        this.htgPvalue = htgPvalue;
    }

    public BigDecimal getHtfPvalue() {
        return htfPvalue;
    }

    public void setHtfPvalue(BigDecimal htfPvalue) {
        this.htfPvalue = htfPvalue;
    }

    public String getPredictedRole() {
        return predictedRole;
    }

    public void setPredictedRole(String role) {
        this.predictedRole = role;
    }

    public BigDecimal getInteractionPvalue() {
        return interactionPvalue;
    }

    public void setInteractionPvalue(BigDecimal interactionPvalue) {
        this.interactionPvalue = interactionPvalue;
    }

    public Gene getOperonTargetGene() {
        return operonTargetGene;
    }

    public void setOperonTargetGene(Gene operonTargetGene) {
        this.operonTargetGene = operonTargetGene;
    }

    @Override
    public String toString() {
        return "PredictedRegulatoryInteraction{" + "id=" + id + ", regulatoryInteraction=" + regulatoryInteraction + ", htgEvalue=" + htgEvalue + ", htfEvalue=" + htfEvalue + ", predictedBindingSite=" + predictedBindingSite + ", homologousTargetGene=" + homologousTargetGene + ", homologousTranscriptionFactor=" + homologousTranscriptionFactor + ", htgPvalue=" + htgPvalue + ", htfPvalue=" + htfPvalue + ", predictedRole=" + predictedRole + ", interactionPvalue=" + interactionPvalue + ", operonTargetGene=" + operonTargetGene + '}';
    }

}
