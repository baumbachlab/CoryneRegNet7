/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.model;

import java.io.Serializable;
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
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 *
 * @author mariana
 */
@Entity
@Immutable
@Table(name = "rna_table_view")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RnaTableView.findAll", query = "SELECT o FROM RnaTableView o order by o.genome, o.locusTag")
    , @NamedQuery(name = "RnaTableView.findByGenome", query = "SELECT o FROM RnaTableView o WHERE o.genome = :genome ORDER BY o.genome, o.locusTag")
    , @NamedQuery(name = "RnaTableView.findByGenomeLocusTag", query = "SELECT o FROM RnaTableView o WHERE o.genome = :genome and lower(o.locusTag) like :locusTag ORDER BY o.genome, o.locusTag")
    , @NamedQuery(name = "RnaTableView.findByLocusTag", query = "SELECT o FROM RnaTableView o WHERE lower(o.locusTag) like :locusTag ORDER BY o.genome, o.locusTag")
    , @NamedQuery(name = "RnaTableView.findByLocusTagType", query = "SELECT o FROM RnaTableView o WHERE lower(o.locusTag) like :locusTag and o.type = :type ORDER BY o.genome, o.locusTag")
    , @NamedQuery(name = "RnaTableView.findByGenomeType", query = "SELECT o FROM RnaTableView o WHERE o.genome = :genome and o.type = :type ORDER BY o.genome, o.locusTag")
    , @NamedQuery(name = "RnaTableView.findByType", query = "SELECT o FROM RnaTableView o WHERE o.type = :type ORDER BY o.type, o.type")
    , @NamedQuery(name = "RnaTableView.findPredicted", query = "SELECT o FROM RnaTableView o WHERE o.type != :type ORDER BY o.genome")
})
public class RnaTableView implements Serializable {

    private static long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "locus_tag")
    private String locusTag;
    @Column(name = "genome")
    private String genome;
    @Column(name = "sequence")
    private String sequence;
    @Column(name = "type")
    private String type;
    @Column(name = "srna_class")
    private String srnaClass;
    @Column(name = "start_position")
    private String startPosition;
    @Column(name = "end_position")
    private String endPosition;
    @Column(name = "orientation")
    private String orientation;
    @Column(name = "source_rna")
    private String sourceRna;
    @Column(name = "evidence")
    private String evidence;
    @Column(name = "mrnas")
    private String mrnas;
    @Column(name = "evidence_functional")
    private String evidenceFunctional;
    @Column(name = "functional_rna")
    private Boolean functionalRna;
    @Column(name = "count_mrnas")
    private Integer countMrnas;
    

//reg_rna
    public String getLocusTag() {
        return locusTag;
    }

    public void setLocusTag(String locusTag) {
        this.locusTag = locusTag;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenome() {
        return genome;
    }

    public void setGenome(String genome) {
        this.genome = genome;
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

    public String getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(String startPosition) {
        this.startPosition = startPosition;
    }

    public String getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(String endPosition) {
        this.endPosition = endPosition;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getSourceRna() {
        return sourceRna;
    }

    public void setSourceRna(String sourceRna) {
        this.sourceRna = sourceRna;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public String getMrnas() {
        return mrnas;
    }

    public void setMrnas(String mrnas) {
        this.mrnas = mrnas;
    }

    @Override
    public String toString() {
        return "RnaTableView{" + "id=" + id + ", locusTag=" + locusTag + ", genome=" + genome + ", sequence=" + sequence + ", type=" + type + ", srnaClass=" + srnaClass + ", startPosition=" + startPosition + ", endPosition=" + endPosition + ", orientation=" + orientation + ", sourceRna=" + sourceRna + ", evidence=" + evidence + ", mrnas=" + mrnas + ", evidenceFunctional=" + evidenceFunctional + ", functionalRna=" + functionalRna + ", countMrnas=" + countMrnas + '}';
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
     * @return the countMrnas
     */
    public Integer getCountMrnas() {
        return countMrnas;
    }

    /**
     * @param countMrnas the countMrnas to set
     */
    public void setCountMrnas(Integer countMrnas) {
        this.countMrnas = countMrnas;
    }

}
