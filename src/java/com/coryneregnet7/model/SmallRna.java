/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.model;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mariana
 */
@Entity
@Table(name = "small_rna")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmallRna.findAll", query = "SELECT s FROM SmallRna s")
    , @NamedQuery(name = "SmallRna.findById", query = "SELECT s FROM SmallRna s WHERE s.id = :id")
    , @NamedQuery(name = "SmallRna.findRepeated", query = "SELECT s FROM SmallRna s WHERE s.startPosition= :startPosition and s.endPosition =:endPosition and s.orientation =:orientation and s.genome =:genome")
    , @NamedQuery(name = "SmallRna.findRepeatedPos", query = "SELECT s FROM SmallRna s WHERE s.startPosition= :startPosition and s.endPosition =:endPosition and s.genome =:genome")
    , @NamedQuery(name = "SmallRna.findByLocusTag", query = "SELECT s FROM SmallRna s WHERE s.locusTag = :locusTag")
    , @NamedQuery(name = "SmallRna.findBySequence", query = "SELECT s FROM SmallRna s WHERE s.sequence = :sequence")
    , @NamedQuery(name = "SmallRna.findByGenome", query = "SELECT s FROM SmallRna s WHERE s.genome = :genome")
    , @NamedQuery(name = "SmallRna.findByType", query = "SELECT s FROM SmallRna s WHERE s.type = :type")})
public class SmallRna implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "locus_tag")
    private String locusTag;
    @Size(max = 2147483647)
    @Column(name = "sequence")
    private String sequence;
    @Size(max = 2147483647)
    @Column(name = "type")
    private String type;
    @Size(max = 2147483647)
    @Column(name = "srna_class")
    private String srnaClass;
    @Column(name = "start_position")
    private Integer startPosition;
    @Column(name = "end_position")
    private Integer endPosition;
    @JoinColumn(name = "genome", referencedColumnName = "id")
    @ManyToOne
    private Genome genome;
    @Size(max = 2147483647)
    @Column(name = "orientation")
    private String orientation;
    @JoinColumn(name = "source_rna", referencedColumnName = "id")
    @ManyToOne
    private SmallRna sourceRna;
    @Size(max = 2147483647)
    @Column(name = "evidence")
    private String evidence;
     @Size(max = 2147483647)
    @Column(name = "evidence_functional")
    private String evidenceFunctional;
    @Column(name = "functional_rna")
    private Boolean functionalRna;

    public SmallRna() {
    }

    public SmallRna(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocusTag() {
        return locusTag;
    }

    public void setLocusTag(String locusTag) {
        this.locusTag = locusTag;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSrnaClass() {
        return srnaClass;
    }

    public void setSrnaClass(String srnaClass) {
        this.srnaClass = srnaClass;
    }

    public Integer getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Integer startPosition) {
        this.startPosition = startPosition;
    }

    public Integer getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Integer endPosition) {
        this.endPosition = endPosition;
    }

    public Genome getGenome() {
        return genome;
    }

    public void setGenome(Genome genome) {
        this.genome = genome;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public SmallRna getSourceRna() {
        return sourceRna;
    }

    public void setSourceRna(SmallRna sourceRna) {
        this.sourceRna = sourceRna;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SmallRna)) {
            return false;
        }
        SmallRna other = (SmallRna) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SmallRna{" + "id=" + id + ", locusTag=" + locusTag + ", sequence=" + sequence + ", type=" + type + ", srnaClass=" + srnaClass + ", startPosition=" + startPosition + ", endPosition=" + endPosition + ", genome=" + genome + ", orientation=" + orientation + ", sourceRna=" + sourceRna + ", evidence=" + evidence + ", evidenceFunctional=" + evidenceFunctional + ", functionalRna=" + functionalRna + '}';
    }

//    @Override
//    public String toString() {
//        return "SmallRna{" + "id=" + id + ", locusTag=" + locusTag + ", evidence=" + evidence + ", sequence=" + sequence + ", type=" + type + ", srnaClass=" + srnaClass + ", startPosition=" + startPosition + ", endPosition=" + endPosition + ", genome=" + genome + ", orientation=" + orientation + ", sourceRna=" + sourceRna + '}';
//    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    /**
     * @return the functionalRna
     */
    public Boolean getFunctionalRna() {
        return functionalRna;
    }

    /**
     * @param functionalRna the functionalRna to set
     */
    public void setFunctionalRna(Boolean functionalRna) {
        this.functionalRna = functionalRna;
    }

    /**
     * @return the evidenceFunctional
     */
    public String getEvidenceFunctional() {
        return evidenceFunctional;
    }

    /**
     * @param evidenceFunctional the evidenceFunctional to set
     */
    public void setEvidenceFunctional(String evidenceFunctional) {
        this.evidenceFunctional = evidenceFunctional;
    }

}
