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
@Table(name = "gene_operon_view")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneOperonView.findAll", query = "SELECT o FROM GeneOperonView o order by o.genome, o.locusTag")
    , @NamedQuery(name = "GeneOperonView.findByGenome", query = "SELECT o FROM GeneOperonView o WHERE o.genome = :genome ORDER BY o.genome, o.operonId")
    , @NamedQuery(name = "GeneOperonView.findByOperonId", query = "SELECT o FROM GeneOperonView o WHERE o.operonId = :operonId")
})
public class GeneOperonView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "locus_tag")
    private String locusTag;
    @Column(name = "gene_name")
    private String geneName;
    @Column(name = "genome")
    private String genome;
    @Column(name = "operon_name")
    private String operonName;
    @Column(name = "operon_id")
    private String operonId;
    @Column(name = "operon_size")
    private Integer operonSize;

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

    public String getGeneName() {
        return geneName;
    }

    public void setGeneName(String GeneName) {
        this.geneName = GeneName;
    }

    public String getGenome() {
        return genome;
    }

    public void setGenome(String genome) {
        this.genome = genome;
    }

    public String getOperonId() {
        return operonId;
    }

    public void setOperonId(String operonId) {
        this.operonId = operonId;
    }

    @Override
    public String toString() {
        return "GeneOperonView{" + "operonName=" + operonName + ", operonId=" + operonId + ", locusTag=" + locusTag + ", geneName=" + geneName + ", genome=" + genome + '}';
    }

    /**
     * @return the operonSize
     */
    public Integer getOperonSize() {
        return operonSize;
    }

    /**
     * @param operonSize the operonSize to set
     */
    public void setOperonSize(Integer operonSize) {
        this.operonSize = operonSize;
    }

}
