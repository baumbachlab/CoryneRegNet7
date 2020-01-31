/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "binding_site")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BindingSite.findAll", query = "SELECT b FROM BindingSite b")
    , @NamedQuery(name = "BindingSite.findById", query = "SELECT b FROM BindingSite b WHERE b.id = :id")
    , @NamedQuery(name = "BindingSite.findBySequence", query = "SELECT b FROM BindingSite b WHERE b.sequence = :sequence")
    , @NamedQuery(name = "BindingSite.findByStartPosition", query = "SELECT b FROM BindingSite b WHERE b.startPosition = :startPosition")
    , @NamedQuery(name = "BindingSite.findByEndPosition", query = "SELECT b FROM BindingSite b WHERE b.endPosition = :endPosition")
    , @NamedQuery(name = "BindingSite.findByEvalue", query = "SELECT b FROM BindingSite b WHERE b.evalue = :evalue")
    , @NamedQuery(name = "BindingSite.findByBitscore", query = "SELECT b FROM BindingSite b WHERE b.bitscore = :bitscore")
    , @NamedQuery(name = "BindingSite.findByType", query = "SELECT b FROM BindingSite b WHERE b.type = :type")
    , @NamedQuery(name = "BindingSite.findByModelRole", query = "SELECT b FROM BindingSite b WHERE b.regulatoryInteraction.role = :role1 or b.regulatoryInteraction.role = :role2")
    , @NamedQuery(name = "BindingSite.findByTypeAndRoleTest", query = "SELECT b FROM BindingSite b WHERE b.type = :type and b.regulatoryInteraction.role = :role")
    , @NamedQuery(name = "BindingSite.findByRegularotyInteraction", query = "SELECT b FROM BindingSite b WHERE b.regulatoryInteraction = :ri")
    , @NamedQuery(name = "BindingSite.findByRegularotyTF", query = "SELECT b FROM BindingSite b WHERE b.regulatoryInteraction.correspondentTranscriptionFactor=:tf)")
    , @NamedQuery(name = "BindingSite.findByRegularotyInteractionGenome", query = "SELECT b FROM BindingSite b WHERE b.regulatoryInteraction.correspondentTranscriptionFactor in (SELECT distinct r.correspondentTranscriptionFactor FROM RegulatoryInteraction r WHERE r.correspondentTranscriptionFactor.genome=:genome)")
    , @NamedQuery(name = "BindingSite.bringNumberOfBindSites", query = "SELECT count(b) FROM BindingSite b")
    , @NamedQuery(name = "BindingSite.bringNumberOfExperimentalBindSites", query = "SELECT count(b) FROM BindingSite b WHERE b.type = 'model'")
    , @NamedQuery(name = "BindingSite.bringNumberOfExperimentalBindSitesOfOrganism", query = "SELECT count(b) FROM BindingSite b WHERE b.regulatoryInteraction.correspondentTargetGene.genome.organism.id = :organismId")
    , @NamedQuery(name = "BindingSite.findModelsByTG", query = "SELECT b FROM BindingSite b WHERE b.regulatoryInteraction.correspondentTargetGene.locusTag = :targetGene")
    , @NamedQuery(name = "BindingSite.findModelsByTF", query = "SELECT b FROM BindingSite b WHERE b.regulatoryInteraction.correspondentTranscriptionFactor.locusTag = :tf")
    , @NamedQuery(name = "BindingSite.findRiWithBsByGenome", query = "SELECT b.regulatoryInteraction FROM BindingSite b where b.regulatoryInteraction.correspondentTranscriptionFactor.genome = :genome")
    , @NamedQuery(name = "BindingSite.findByOrganism", query = "SELECT b FROM BindingSite b WHERE b.regulatoryInteraction.correspondentTargetGene.genome.organism.id = :organism")
})

public class BindingSite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "sequence")
    private String sequence;
    @Column(name = "start_position")
    private BigInteger startPosition;
    @Column(name = "end_position")
    private BigInteger endPosition;
    @Column(name = "evalue")
    private BigDecimal evalue;
    @Column(name = "bitscore")
    private BigDecimal bitscore;
    @Size(max = 2147483647)
    @Column(name = "type")
    private String type;
    @JoinColumn(name = "regulatory_interaction", referencedColumnName = "id")
    @ManyToOne
    private RegulatoryInteraction regulatoryInteraction;
    @Column(name = "pvalue")
    private BigDecimal pvalue;
    @Column(name = "start_site_distance")
    private Integer startSiteDistance;

    public BindingSite() {
    }

    public BindingSite(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public BigInteger getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(BigInteger startPosition) {
        this.startPosition = startPosition;
    }

    public BigInteger getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(BigInteger endPosition) {
        this.endPosition = endPosition;
    }

    public BigDecimal getEvalue() {
        return evalue;
    }

    public void setEvalue(BigDecimal evalue) {
        this.evalue = evalue;
    }

    public BigDecimal getBitscore() {
        return bitscore;
    }

    public void setBitscore(BigDecimal bitscore) {
        this.bitscore = bitscore;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RegulatoryInteraction getRegulatoryInteraction() {
        return regulatoryInteraction;
    }

    public void setRegulatoryInteraction(RegulatoryInteraction regulatoryInteraction) {
        this.regulatoryInteraction = regulatoryInteraction;
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
        if (!(object instanceof BindingSite)) {
            return false;
        }
        BindingSite other = (BindingSite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public BigDecimal getPvalue() {
        return pvalue;
    }

    public void setPvalue(BigDecimal pvalue) {
        this.pvalue = pvalue;
    }

    public Integer getStartSiteDistance() {
        return startSiteDistance;
    }

    public void setStartSiteDistance(Integer startSiteDistance) {
        this.startSiteDistance = startSiteDistance;
    }

    @Override
    public String toString() {
        return "BindingSite{" + "id=" + id + ", sequence=" + sequence + ", startPosition=" + startPosition + ", endPosition=" + endPosition + ", evalue=" + evalue + ", bitscore=" + bitscore + ", type=" + type + ", regulatoryInteraction=" + regulatoryInteraction + ", pvalue=" + pvalue + ", startSiteDistance=" + startSiteDistance + '}';
    }

}
