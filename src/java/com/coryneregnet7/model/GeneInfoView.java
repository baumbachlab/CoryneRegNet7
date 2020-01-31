/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.model;

import com.coryneregnet7.controller.GeneInfo;
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
@Table(name = "gene_info_view")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneInfoView.findAll", query = "SELECT o FROM GeneInfoView o order by o.genome, o.locusTag")
    , @NamedQuery(name = "GeneInfoView.findByGenome", query = "SELECT o FROM GeneInfoView o WHERE o.genome = :genome ORDER BY o.genome, o.operonId")
    , @NamedQuery(name = "GeneInfoView.findByOperonId", query = "SELECT o FROM GeneInfoView o WHERE o.operonId = :operonId")
})
public class GeneInfoView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "locus_tag")
    private String locusTag;
    @Column(name = "gene_name")
    private String geneName;
    @JoinColumn(name = "gene", referencedColumnName = "id")
    @ManyToOne
    private Gene gene;
    @Column(name = "genome")
    private String genome;
    @Column(name = "operon_name")
    private String operonName;
    @Column(name = "operon_id")
    private String operonId;
    @Column(name = "operon_pos")
    private Integer operonPos;

    public GeneInfo getGeneInfo() {

        String opName = operonName;
        if(opName ==null){
            opName="";
        }
        
        Integer pos = 0;
        if(operonPos!=null){
            pos=operonPos;
        }
        GeneInfo geneInfo = new GeneInfo(gene, opName, pos);
        /*
            private Gene gene;
    private String operon;
    private Integer pos;
         */
        return geneInfo;

    }

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
        return "GeneInfoView{" + "locusTag=" + locusTag + ", geneName=" + geneName + ", gene=" + gene + ", genome=" + genome + ", operonName=" + operonName + ", operonId=" + operonId + ", operonPos=" + operonPos + '}';
    }

    /**
     * @return the gene
     */
    public Gene getGene() {
        return gene;
    }

    /**
     * @param gene the gene to set
     */
    public void setGene(Gene gene) {
        this.gene = gene;
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

}
