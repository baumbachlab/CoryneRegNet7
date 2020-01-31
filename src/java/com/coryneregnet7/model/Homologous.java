/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "homologous")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Homologous.findAll", query = "SELECT h FROM Homologous h")
    , @NamedQuery(name = "Homologous.findById", query = "SELECT h FROM Homologous h WHERE h.id = :id")
    , @NamedQuery(name = "Homologous.findByEvalue", query = "SELECT h FROM Homologous h WHERE h.evalue = :evalue")
    , @NamedQuery(name = "Homologous.findByPvalue", query = "SELECT h FROM Homologous h WHERE h.pvalue = :pvalue")
    , @NamedQuery(name = "Homologous.findByGene", query = "SELECT h FROM Homologous h WHERE h.geneOne = :gene or h.geneTwo = :gene")
    , @NamedQuery(name = "Homologous.findByGeneOrganism", query = "SELECT h FROM Homologous h WHERE (h.geneOne = :gene and h.geneTwo.genome.organism.id = :organismId) or (h.geneTwo = :gene and h.geneOne.genome.organism.id = :organismId)")
    , @NamedQuery(name = "Homologous.findByGeneGenome", query = "SELECT h FROM Homologous h WHERE (h.geneOne = :gene and h.geneTwo.genome.id = :genomeId) or (h.geneTwo = :gene and h.geneOne.genome.id = :genomeId)")
    , @NamedQuery(name = "Homologous.findByGenome", query = "SELECT h FROM Homologous h WHERE h.geneTwo.genome.id = :genomeId or h.geneOne.genome.id = :genomeId order by h.id")
    , @NamedQuery(name = "Homologous.findByGeneGenomeNotParalogous", query = "SELECT h FROM Homologous h WHERE (h.geneOne = :gene and h.geneTwo.genome.id = :genomeId) or (h.geneOne = :gene and h.geneTwo.genome.id = :genomeId)")
    , @NamedQuery(name = "Homologous.bringByModelAndTargetGenomes", query = "SELECT count(h) FROM Homologous h WHERE h.geneOne.genome.id = :modelGenomeId and h.geneTwo.genome.id = :targetGenomeId")
    , @NamedQuery(name = "Homologous.findByModelAndTargetGenomes", query = "SELECT h FROM Homologous h WHERE h.geneOne.genome.id = :modelGenomeId and h.geneTwo.genome.id = :targetGenomeId order by geneOne")
    //, @NamedQuery(name = "Homologous.findByGeneGenomeNotParalogous", query = "SELECT h FROM Homologous h WHERE ((h.geneOne = :gene and h.geneTwo.genome.id = :genomeId) or (h.geneTwo = :gene and h.geneTwo.genome.id = :genomeId)) and (h.geneOne.genome.id is not :genomeId2)")    
    , @NamedQuery(name = "Homologous.findByPair", query = "SELECT h FROM Homologous h WHERE ((h.geneOne = :geneOne and h.geneTwo = :geneTwo) or (h.geneOne = :geneTwo and  h.geneTwo = :geneOne))")

})
public class Homologous implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "gene_one", referencedColumnName = "id")
    @ManyToOne
    private Gene geneOne;
    @JoinColumn(name = "gene_two", referencedColumnName = "id")
    @ManyToOne
    private Gene geneTwo;
    @Column(name = "evalue")
    private BigDecimal evalue;
    @Column(name = "pvalue")
    private BigDecimal pvalue;

    public Homologous() {
    }

    public Homologous(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof Homologous)) {
            return false;
        }
        Homologous other = (Homologous) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Gene getGeneOne() {
        return geneOne;
    }

    public void setGeneOne(Gene geneOne) {
        this.geneOne = geneOne;
    }

    public Gene getGeneTwo() {
        return geneTwo;
    }

    public void setGeneTwo(Gene geneTwo) {
        this.geneTwo = geneTwo;
    }

    public BigDecimal getEvalue() {
        return evalue;
    }

    public void setEvalue(BigDecimal evalue) {
        this.evalue = evalue;
    }

    public BigDecimal getPvalue() {
        return pvalue;
    }

    public void setPvalue(BigDecimal pvalue) {
        this.pvalue = pvalue;
    }

    @Override
    public String toString() {
        return "Homologous{" + "id=" + id + ", geneOne=" + geneOne.getLocusTag() + "(" + geneOne.getId() + ")" + ", geneTwo=" + geneTwo.getLocusTag() + "(" + geneTwo.getId() + ")" + ", evalue=" + evalue + ", pvalue=" + pvalue + '}';
    }

    public String toString2() {
        return "Homologous{" + "id=" + id + ", geneOne=" + geneOne + ", geneTwo=" + geneTwo + ", evalue=" + evalue + ", pvalue=" + pvalue + '}';
    }

}
