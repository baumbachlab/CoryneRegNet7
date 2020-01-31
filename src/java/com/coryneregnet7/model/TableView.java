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
@Table(name = "table_view")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableView.findAll", query = "SELECT o FROM TableView o order by o.genomeId, o.locusTag")
    , @NamedQuery(name = "TableView.findByGenome", query = "SELECT o FROM TableView o WHERE o.genomeId = :genome ORDER BY o.genomeId, o.locusTag")
    , @NamedQuery(name = "TableView.findByLocusTagGeneNameModel", query = "SELECT o FROM TableView o WHERE (o.organismId =1239 or o.organismId =1240 or o.organismId =1241 or o.organismId =1242) and (lower(o.geneName) like :gene or lower(o.locusTag) like :gene or lower(o.alternativeLocusTag) like :gene)")
    , @NamedQuery(name = "TableView.findByLocusTagGeneName", query = "SELECT o FROM TableView o WHERE lower(o.geneName) like :gene or lower(o.locusTag) like :gene or lower(o.alternativeLocusTag) like :gene")
    , @NamedQuery(name = "TableView.findByLocusTagGeneNameGenome", query = "SELECT o FROM TableView o WHERE (lower(o.geneName) like :gene or lower(o.locusTag) like :gene or lower(o.alternativeLocusTag) like :gene) and o.genomeId=:genomeId")
})
public class TableView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "locus_tag")
    private String locusTag;
    @Column(name = "gene_name")
    private String geneName;
    @Column(name = "alternative_locus_tag")
    private String alternativeLocusTag;
    @Column(name = "protein_id")
    private String proteinId;
    @Column(name = "product")
    private String product;
    @Column(name = "operon_id")
    private String operonId;
    @Column(name = "operon_name")
    private String operonName;
    @Column(name = "genome_id")
    private String genomeId;
    @Column(name = "organism_id")
    private String organismId;
    @Column(name = "organism_name")
    private String organismName;
    @Column(name = "regulated_genes")
    private String regulatedGenes;
    @Column(name = "regulated_by")
    private String regulatedBy;

    public String getOperonName() {
        return operonName;
    }

    public void setOperonName(String operonName) {
        this.operonName = operonName;
    }

    public String getLocusTag() {
        return locusTag;
    }

    public void setLocusTag(String locusTag) {
        this.locusTag = locusTag;
    }

    public String getOperonId() {
        return operonId;
    }

    public void setOperonId(String operonId) {
        this.operonId = operonId;
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

    /**
     * @return the proteinId
     */
    public String getProteinId() {
        return proteinId;
    }

    /**
     * @param proteinId the proteinId to set
     */
    public void setProteinId(String proteinId) {
        this.proteinId = proteinId;
    }

    /**
     * @return the product
     */
    public String getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * @return the genomeId
     */
    public String getGenomeId() {
        return genomeId;
    }

    /**
     * @param genomeId the genomeId to set
     */
    public void setGenomeId(String genomeId) {
        this.genomeId = genomeId;
    }

    /**
     * @return the organismId
     */
    public String getOrganismId() {
        return organismId;
    }

    /**
     * @param organismId the organismId to set
     */
    public void setOrganismId(String organismId) {
        this.organismId = organismId;
    }

    /**
     * @return the organismName
     */
    public String getOrganismName() {
        return organismName;
    }

    /**
     * @param organismName the organismName to set
     */
    public void setOrganismName(String organismName) {
        this.organismName = organismName;
    }

    /**
     * @return the regulatedGenes
     */
    public String getRegulatedGenes() {
        return regulatedGenes;
    }

    /**
     * @param regulatedGenes the regulatedGenes to set
     */
    public void setRegulatedGenes(String regulatedGenes) {
        this.regulatedGenes = regulatedGenes;
    }

    /**
     * @return the regulatedBy
     */
    public String getRegulatedBy() {
        return regulatedBy;
    }

    /**
     * @param regulatedBy the regulatedBy to set
     */
    public void setRegulatedBy(String regulatedBy) {
        this.regulatedBy = regulatedBy;
    }

    @Override
    public String toString() {
        return "TableView{" + "id=" + id + ", locusTag=" + locusTag + ", proteinId=" + proteinId + ", product=" + product + ", operonId=" + operonId + ", operonName=" + operonName + ", genomeId=" + genomeId + ", organismId=" + organismId + ", organismName=" + organismName + ", regulatedGenes=" + regulatedGenes + ", regulatedBy=" + regulatedBy + '}';
    }

    /**
     * @return the alternativeLocusTag
     */
    public String getAlternativeLocusTag() {
        return alternativeLocusTag;
    }

    /**
     * @param alternativeLocusTag the alternativeLocusTag to set
     */
    public void setAlternativeLocusTag(String alternativeLocusTag) {
        this.alternativeLocusTag = alternativeLocusTag;
    }

    /**
     * @return the geneName
     */
    public String getGeneName() {
        return geneName;
    }

    /**
     * @param geneName the geneName to set
     */
    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }

}
