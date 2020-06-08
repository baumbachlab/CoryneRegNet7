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
@Table(name = "rna_regulation_view")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RnaRegulationView.findAll", query = "SELECT o FROM RnaRegulationView o order by o.genome")
    , @NamedQuery(name = "RnaRegulationView.findByGenome", query = "SELECT o FROM RnaRegulationView o WHERE o.genome = :genome ORDER BY o.genome")
    , @NamedQuery(name = "RnaRegulationView.findByGenomeRank", query = "SELECT o FROM RnaRegulationView o WHERE o.genome = :genome and o.rank < :rank ORDER BY o.genome")
    , @NamedQuery(name = "RnaRegulationView.findByTg", query = "SELECT o FROM RnaRegulationView o WHERE o.tg = :tg ORDER BY o.genome")
    , @NamedQuery(name = "RnaRegulationView.findBySrna", query = "SELECT o FROM RnaRegulationView o WHERE o.srna = :srna ORDER BY o.genome")
    , @NamedQuery(name = "RnaRegulationView.findByOperon", query = "SELECT o FROM RnaRegulationView o WHERE o.operon = :operon ORDER BY o.genome")
})
public class RnaRegulationView implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "srna", referencedColumnName = "id")
    @ManyToOne
    private SmallRna srna;
    @JoinColumn(name = "tg", referencedColumnName = "id")
    @ManyToOne
    private Gene tg;
    @Size(max = 2147483647)
    @Column(name = "role")
    private String role;
    @JoinColumn(name = "genome", referencedColumnName = "id")
    @ManyToOne
    private Genome genome;
    @Size(max = 2147483647)
    @Column(name = "operon")
    private String operon;
    @Column(name = "pvalue")
    private BigDecimal pvalue;
        @Column(name = "rank")
    private Integer rank;

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
     * @return the srna
     */
    public SmallRna getSrna() {
        return srna;
    }

    /**
     * @param srna the srna to set
     */
    public void setSrna(SmallRna srna) {
        this.srna = srna;
    }

    /**
     * @return the tg
     */
    public Gene getTg() {
        return tg;
    }

    /**
     * @param tg the tg to set
     */
    public void setTg(Gene tg) {
        this.tg = tg;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the genome
     */
    public Genome getGenome() {
        return genome;
    }

    /**
     * @param genome the genome to set
     */
    public void setGenome(Genome genome) {
        this.genome = genome;
    }

    /**
     * @return the operon
     */
    public String getOperon() {
        return operon;
    }

    /**
     * @param operon the operon to set
     */
    public void setOperon(String operon) {
        this.operon = operon;
    }

    /**
     * @return the pvalue
     */
    public BigDecimal getPvalue() {
        return pvalue;
    }

    /**
     * @param pvalue the pvalue to set
     */
    public void setPvalue(BigDecimal pvalue) {
        this.pvalue = pvalue;
    }

    @Override
    public String toString() {
        return "RnaRegulationView{" + "id=" + id + ", srna=" + srna + ", tg=" + tg + ", role=" + role + ", genome=" + genome + ", operon=" + operon + ", pvalue=" + pvalue + ", rank=" + rank + '}';
    }

    /**
     * @return the rank
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * @param rank the rank to set
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }

}
