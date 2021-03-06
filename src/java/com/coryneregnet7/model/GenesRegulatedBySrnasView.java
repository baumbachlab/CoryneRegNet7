/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 *
 * @author mariana
 */
@Entity
@Immutable
@Table(name = "genes_regulated_by_srnas_view")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GenesRegulatedBySrnasView.findAll", query = "SELECT o FROM GenesRegulatedBySrnasView o order by o.genome")
    , @NamedQuery(name = "GenesRegulatedBySrnasView.findByGenome", query = "SELECT o FROM GenesRegulatedBySrnasView o WHERE o.genome = :genome ORDER BY o.genome")

})
public class GenesRegulatedBySrnasView implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "num_srnas")
    private Integer numSrnas;
    @Column(name = "num_genes")
    private Integer numGenes;
    @Column(name = "genome")
    private Integer genome;

//reg_rna
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
     * @return the genome
     */
    public Integer getGenome() {
        return genome;
    }

    /**
     * @param genome the genome to set
     */
    public void setGenome(Integer genome) {
        this.genome = genome;
    }

    /**
     * @return the numSrnas
     */
    public Integer getNumSrnas() {
        return numSrnas;
    }

    /**
     * @param numSrnas the numSrnas to set
     */
    public void setNumSrnas(Integer numSrnas) {
        this.numSrnas = numSrnas;
    }

    /**
     * @return the numGenes
     */
    public Integer getNumGenes() {
        return numGenes;
    }

    /**
     * @param numGenes the numGenes to set
     */
    public void setNumGenes(Integer numGenes) {
        this.numGenes = numGenes;
    }

    @Override
    public String toString() {
        return "GenesRegulatedBySrnasView{" + "id=" + id + ", numSrnas=" + numSrnas + ", numGenes=" + numGenes + ", genome=" + genome + '}';
    }

}
