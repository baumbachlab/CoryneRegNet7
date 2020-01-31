/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.model;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mariana
 */
@Entity
@Table(name = "regulatory_interaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegulatoryInteraction.findAll", query = "SELECT r FROM RegulatoryInteraction r")
    , @NamedQuery(name = "RegulatoryInteraction.findById", query = "SELECT r FROM RegulatoryInteraction r WHERE r.id = :id")
    , @NamedQuery(name = "RegulatoryInteraction.findByRegulation", query = "SELECT r FROM RegulatoryInteraction r WHERE r.regulation = :regulation")
    , @NamedQuery(name = "RegulatoryInteraction.findByEvidence", query = "SELECT r FROM RegulatoryInteraction r WHERE r.evidence = :evidence")
    , @NamedQuery(name = "RegulatoryInteraction.findBySource", query = "SELECT r FROM RegulatoryInteraction r WHERE r.source = :source")
    , @NamedQuery(name = "RegulatoryInteraction.findByPmid", query = "SELECT r FROM RegulatoryInteraction r WHERE r.pmid = :pmid")
    , @NamedQuery(name = "RegulatoryInteraction.bringNumberOfRegulatoryInteractions", query = "SELECT count(r) FROM RegulatoryInteraction r")
    , @NamedQuery(name = "RegulatoryInteraction.bringNumberOfRIsByOrganism", query = "SELECT count(r) FROM RegulatoryInteraction r WHERE r.correspondentTranscriptionFactor.genome.organism.id = :organismId")
    , @NamedQuery(name = "RegulatoryInteraction.bringNumberOfRIsByRole", query = "SELECT count(r) FROM RegulatoryInteraction r WHERE r.role = :role")
    , @NamedQuery(name = "RegulatoryInteraction.bringNumberOfRIsByOrganismAndRole", query = "SELECT count(r) FROM RegulatoryInteraction r WHERE r.role = :role and r.correspondentTargetGene.genome.organism.id = :organismId")
    , @NamedQuery(name = "RegulatoryInteraction.bringNumberOfRIsOfOrganism", query = "SELECT count(r) FROM RegulatoryInteraction r WHERE r.correspondentTargetGene.genome.organism.id = :organismId")
    , @NamedQuery(name = "RegulatoryInteraction.bringNumberOfRegulationsOfTg", query = "SELECT count(r) FROM RegulatoryInteraction r WHERE r.correspondentTargetGene = :gene")
    , @NamedQuery(name = "RegulatoryInteraction.bringNumberOfRegulators", query = "SELECT count (distinct r.correspondentTranscriptionFactor) FROM RegulatoryInteraction r")
    , @NamedQuery(name = "RegulatoryInteraction.bringNumberOfRegulatorsOfOrganism", query = "SELECT count (distinct r.correspondentTranscriptionFactor) FROM RegulatoryInteraction r WHERE r.correspondentTargetGene.genome.organism.id = :organismId")
    , @NamedQuery(name = "RegulatoryInteraction.bringNumberOfRegulatedGenes", query = "SELECT count (distinct r.correspondentTargetGene) FROM RegulatoryInteraction r")
    , @NamedQuery(name = "RegulatoryInteraction.bringNumberOfRegulatedGenesOfOrganism", query = "SELECT count (distinct r.correspondentTargetGene) FROM RegulatoryInteraction r WHERE r.correspondentTargetGene.genome.organism.id = :organismId")
    , @NamedQuery(name = "RegulatoryInteraction.findRegulates", query = "SELECT r FROM RegulatoryInteraction r WHERE r.correspondentTranscriptionFactor = :correspondentTranscriptionFactor order by r.correspondentTargetGene.locusTag")
    , @NamedQuery(name = "RegulatoryInteraction.findRegulatedBy", query = "SELECT r FROM RegulatoryInteraction r WHERE r.correspondentTargetGene = :correspondentTargetGene order by r.correspondentTranscriptionFactor.locusTag")
    , @NamedQuery(name = "RegulatoryInteraction.findByGenome", query = "SELECT r FROM RegulatoryInteraction r WHERE r.correspondentTranscriptionFactor in (SELECT g from Gene g where g.genome=:genome)")
    , @NamedQuery(name = "RegulatoryInteraction.findByOrganism", query = "SELECT r FROM RegulatoryInteraction r WHERE r.correspondentTranscriptionFactor.genome.organism=:organism")
    , @NamedQuery(name = "RegulatoryInteraction.findByGenomeDistinct", query = "SELECT distinct r.correspondentTranscriptionFactor FROM RegulatoryInteraction r WHERE r.correspondentTranscriptionFactor in (SELECT g from Gene g where g.genome=:genome)")
    //, @NamedQuery(name = "RegulatoryInteraction.findByGenomeDistinct", query = "SELECT distinct r.correspondentTargetGene FROM RegulatoryInteraction r WHERE r.correspondentTranscriptionFactor in (SELECT g from Gene g where g.genome=:genome)")
    , @NamedQuery(name = "RegulatoryInteraction.findByGenomeAndName", query = "SELECT r FROM RegulatoryInteraction r WHERE r.correspondentTranscriptionFactor in (SELECT g from Gene g where g.genome=:genome and (g.locusTag=:locusTag or g.alternativeLocusTag=:altLocusTag or g.proteinId=:proteinId))")
    , @NamedQuery(name = "RegulatoryInteraction.findByTFTG", query = "SELECT r FROM RegulatoryInteraction r WHERE r.correspondentTranscriptionFactor.locusTag = :tfLocusTag and r.correspondentTargetGene.locusTag = :tgLocusTag")
    , @NamedQuery(name = "RegulatoryInteraction.findByTF", query = "SELECT r FROM RegulatoryInteraction r WHERE r.correspondentTranscriptionFactor.id = :correspondentTranscriptionFactor")
    , @NamedQuery(name = "RegulatoryInteraction.findByDistinctTG", query = "SELECT distinct r.correspondentTargetGene FROM RegulatoryInteraction r")
    , @NamedQuery(name = "RegulatoryInteraction.findByOrganismAndDistinctTG", query = "SELECT distinct r.correspondentTargetGene FROM RegulatoryInteraction r WHERE r.correspondentTargetGene.genome.organism.id = :organismId")
    , @NamedQuery(name = "RegulatoryInteraction.findByTfTgId", query = "SELECT r FROM RegulatoryInteraction r WHERE r.correspondentTranscriptionFactor.id = :tfId and r.correspondentTargetGene.id = :tgId")
    , @NamedQuery(name = "RegulatoryInteraction.findByTfTgRole", query = "SELECT r FROM RegulatoryInteraction r WHERE r.correspondentTranscriptionFactor.locusTag = :tfId and r.correspondentTargetGene.locusTag = :tgId and r.role = :role")
    , @NamedQuery(name = "RegulatoryInteraction.bringNumberOfCoregulators", query = "SELECT count (distinct r.correspondentTranscriptionFactor) FROM RegulatoryInteraction r WHERE r.correspondentTargetGene.locusTag = :locusTag")
    , @NamedQuery(name = "RegulatoryInteraction.bringRoleByTf", query = "SELECT distinct r.role FROM RegulatoryInteraction r WHERE r.correspondentTranscriptionFactor =:transcriptionFactor")
    , @NamedQuery(name = "RegulatoryInteraction.findByDistinctTF", query = "SELECT distinct r.correspondentTranscriptionFactor FROM RegulatoryInteraction r")
    , @NamedQuery(name = "RegulatoryInteraction.findByOrganismDistinctTF", query = "SELECT distinct r.correspondentTranscriptionFactor FROM RegulatoryInteraction r WHERE r.correspondentTranscriptionFactor.genome.organism.id = :organismId and r.correspondentTargetGene.genome.organism.id = :organismId")
    , @NamedQuery(name = "RegulatoryInteraction.findByOrganismTFAndRole", query = "SELECT distinct r.correspondentTargetGene FROM RegulatoryInteraction r WHERE r.correspondentTranscriptionFactor.genome.organism.id = :organismId and r.correspondentTranscriptionFactor.id = :tf and r.correspondentTargetGene.genome.organism.id = :organismId and r.role = :role")
    , @NamedQuery(name = "RegulatoryInteraction.findByOrganismTF", query = "SELECT distinct r.correspondentTargetGene FROM RegulatoryInteraction r WHERE r.correspondentTranscriptionFactor.genome.organism.id = :organismId and r.correspondentTranscriptionFactor.id = :tf and r.correspondentTargetGene.genome.organism.id = :organismId")
    , @NamedQuery(name = "RegulatoryInteraction.bringNumberOfRegulationsOfTf", query = "SELECT count(r) FROM RegulatoryInteraction r WHERE r.correspondentTranscriptionFactor = :gene")
    , @NamedQuery(name = "RegulatoryInteraction.findTgsOfTf", query = "SELECT distinct r.correspondentTargetGene FROM RegulatoryInteraction r WHERE r.correspondentTranscriptionFactor = :id")
    , @NamedQuery(name = "RegulatoryInteraction.findTFsOfTg", query = "SELECT distinct r.correspondentTranscriptionFactor FROM RegulatoryInteraction r WHERE r.correspondentTargetGene = :id")
    , @NamedQuery(name = "RegulatoryInteraction.findByOrganismAndGene", query = "SELECT r FROM RegulatoryInteraction r WHERE r.correspondentTranscriptionFactor.genome.organism.id = :organismId and (r.correspondentTranscriptionFactor.id = :id or r.correspondentTargetGene.id = :id)")
})

public class RegulatoryInteraction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "regulation", referencedColumnName = "id")
    @ManyToOne
    private Regulation regulation;
    @Size(max = 2147483647)
    @Column(name = "evidence")
    private String evidence;
    @Size(max = 2147483647)
    @Column(name = "source")
    private String source;
    @Size(max = 2147483647)
    @Column(name = "pmid")
    private String pmid;
    @JoinColumn(name = "correspondent_target_gene", referencedColumnName = "id")
    @ManyToOne
    private Gene correspondentTargetGene;
    @JoinColumn(name = "correspondent_transcription_factor", referencedColumnName = "id")
    @ManyToOne
    private Gene correspondentTranscriptionFactor;
    @Column(name = "role")
    private String role;

    public RegulatoryInteraction() {
    }

    public RegulatoryInteraction(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Regulation getRegulation() {
        return regulation;
    }

    public void setRegulation(Regulation regulation) {
        this.regulation = regulation;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPmid() {
        return pmid;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public Gene getCorrespondentTargetGene() {
        return correspondentTargetGene;
    }

    public void setCorrespondentTargetGene(Gene correspondentTargetGene) {
        this.correspondentTargetGene = correspondentTargetGene;
    }

    public Gene getCorrespondentTranscriptionFactor() {
        return correspondentTranscriptionFactor;
    }

    public void setCorrespondentTranscriptionFactor(Gene correspondentTranscriptionFactor) {
        this.correspondentTranscriptionFactor = correspondentTranscriptionFactor;
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
        if (!(object instanceof RegulatoryInteraction)) {
            return false;
        }
        RegulatoryInteraction other = (RegulatoryInteraction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RegulatoryInteraction{" + "id=" + id + ", regulation=" + regulation + ", evidence=" + evidence + ", source=" + source + ", pmid=" + pmid + ", correspondentTargetGene=" + correspondentTargetGene + ", correspondentTranscriptionFactor=" + correspondentTranscriptionFactor + ", role=" + role + '}';
    }

    public String toStringShort() {
        return "RegulatoryInteraction{" + "id=" + id + ", regulation=" + regulation.getId() + ", correspondentTargetGene=" + correspondentTargetGene.getLocusTag() + ", correspondentTranscriptionFactor=" + correspondentTranscriptionFactor.getLocusTag() + ", evidence=" + evidence + ", source=" + source + ", pmid=" + pmid + ", role=" + role + '}';
    }

}
