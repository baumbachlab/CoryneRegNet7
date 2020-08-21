/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "rna_interaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RnaInteraction.findAll", query = "SELECT r FROM RnaInteraction r")
    ,@NamedQuery(name = "RnaInteraction.bringAll", query = "SELECT count(r) FROM RnaInteraction r")
    , @NamedQuery(name = "RnaInteraction.findById", query = "SELECT r FROM RnaInteraction r WHERE r.id = :id")
    , @NamedQuery(name = "RnaInteraction.findByRank", query = "SELECT r FROM RnaInteraction r WHERE r.rank = :rank")
    , @NamedQuery(name = "RnaInteraction.findByMrna", query = "SELECT r FROM RnaInteraction r WHERE r.mrna = :mrna")
    , @NamedQuery(name = "RnaInteraction.findByGenome", query = "SELECT r FROM RnaInteraction r WHERE r.mrna in (SELECT g.id FROM Gene g WHERE g.genome = :genome)")
    , @NamedQuery(name = "RnaInteraction.bringDistinctRnaByGenome", query = "SELECT distinct r.mrna FROM RnaInteraction r WHERE r.mrna in (SELECT g.id FROM Gene g WHERE g.genome = :genome)")
    , @NamedQuery(name = "RnaInteraction.bringDistinctMrna", query = "SELECT count(distinct r.mrna) FROM RnaInteraction r")
    , @NamedQuery(name = "RnaInteraction.findBySrna", query = "SELECT r FROM RnaInteraction r WHERE r.srna = :srna")
    , @NamedQuery(name = "RnaInteraction.findByCopraPvalue", query = "SELECT r FROM RnaInteraction r WHERE r.copraPvalue = :copraPvalue")
    , @NamedQuery(name = "RnaInteraction.findByCopraFdr", query = "SELECT r FROM RnaInteraction r WHERE r.copraFdr = :copraFdr")
    , @NamedQuery(name = "RnaInteraction.findByEnergy", query = "SELECT r FROM RnaInteraction r WHERE r.energy = :energy")
    , @NamedQuery(name = "RnaInteraction.findByIntaPvalue", query = "SELECT r FROM RnaInteraction r WHERE r.intaPvalue = :intaPvalue")
    , @NamedQuery(name = "RnaInteraction.findByPositionMrna", query = "SELECT r FROM RnaInteraction r WHERE r.positionMrna = :positionMrna")
    , @NamedQuery(name = "RnaInteraction.findByPositionNcrna", query = "SELECT r FROM RnaInteraction r WHERE r.positionNcrna = :positionNcrna")
    , @NamedQuery(name = "RnaInteraction.findByInteraction", query = "SELECT r FROM RnaInteraction r WHERE r.interaction = :interaction")
    , @NamedQuery(name = "RnaInteraction.findByPositionSeedMrna", query = "SELECT r FROM RnaInteraction r WHERE r.positionSeedMrna = :positionSeedMrna")
    , @NamedQuery(name = "RnaInteraction.findByPositionSeedNcrna", query = "SELECT r FROM RnaInteraction r WHERE r.positionSeedNcrna = :positionSeedNcrna")
    , @NamedQuery(name = "RnaInteraction.findByHybridizationEnergy", query = "SELECT r FROM RnaInteraction r WHERE r.hybridizationEnergy = :hybridizationEnergy")
    , @NamedQuery(name = "RnaInteraction.findByUnfoldingEnergyMrna", query = "SELECT r FROM RnaInteraction r WHERE r.unfoldingEnergyMrna = :unfoldingEnergyMrna")
    , @NamedQuery(name = "RnaInteraction.findByUnfoldingEnergyNcrna", query = "SELECT r FROM RnaInteraction r WHERE r.unfoldingEnergyNcrna = :unfoldingEnergyNcrna")
    , @NamedQuery(name = "RnaInteraction.findByTfMrna", query = "SELECT count(r) FROM RnaInteraction r WHERE r.mrna in (SELECT g FROM Gene g WHERE g.role != '')")
    , @NamedQuery(name = "RnaInteraction.findByGenomeInPosition", query = "SELECT distinct r.mrna FROM RnaInteraction r WHERE r.mrna in (SELECT g.id FROM Gene g WHERE g.startPosition >= :startPosition and g.endPosition <= :endPosition and g.genome=:genome)")
    , @NamedQuery(name = "RnaInteraction.findByGenomeInPositionSrna", query = "SELECT distinct r.srna FROM RnaInteraction r WHERE r.srna in (SELECT s.id FROM SmallRna s WHERE s.startPosition >= :startPosition and s.endPosition <= :endPosition and s.genome=:genome)")
//start_position >= 8536 and end_position <=40429
})

public class RnaInteraction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "rank")
    private Integer rank;
    @Column(name = "copra_pvalue")
    private BigDecimal copraPvalue;
    @Column(name = "copra_fdr")
    private BigDecimal copraFdr;
    @Column(name = "energy")
    private BigDecimal energy;
    @Column(name = "inta_pvalue")
    private BigDecimal intaPvalue;
    @Size(max = 2147483647)
    @Column(name = "position_mrna")
    private String positionMrna;
    @Size(max = 2147483647)
    @Column(name = "position_ncrna")
    private String positionNcrna;
    @Size(max = 2147483647)
    @Column(name = "interaction")
    private String interaction;
    @Size(max = 2147483647)
    @Column(name = "position_seed_mrna")
    private String positionSeedMrna;
    @Size(max = 2147483647)
    @Column(name = "position_seed_ncrna")
    private String positionSeedNcrna;
    @Column(name = "hybridization_energy")
    private BigDecimal hybridizationEnergy;
    @Column(name = "unfolding_energy_mrna")
    private BigDecimal unfoldingEnergyMrna;
    @Column(name = "unfolding_energy_ncrna")
    private BigDecimal unfoldingEnergyNcrna;
    @JoinColumn(name = "srna", referencedColumnName = "id")
    @ManyToOne
    private SmallRna srna;
    @JoinColumn(name = "mrna", referencedColumnName = "id")
    @ManyToOne
    private Gene mrna;
    @Column(name = "part_of_trn")
    private Boolean partOfTrn;
    @Column(name = "ajusted_pvalue")
    private BigDecimal ajustedPvalue;

    public RnaInteraction() {
    }

    public RnaInteraction(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public BigDecimal getCopraPvalue() {
        return copraPvalue;
    }

    public void setCopraPvalue(BigDecimal copraPvalue) {
        this.copraPvalue = copraPvalue;
    }

    public BigDecimal getCopraFdr() {
        return copraFdr;
    }

    public void setCopraFdr(BigDecimal copraFdr) {
        this.copraFdr = copraFdr;
    }

    public BigDecimal getEnergy() {
        return energy;
    }

    public void setEnergy(BigDecimal energy) {
        this.energy = energy;
    }

    public BigDecimal getIntaPvalue() {
        return intaPvalue;
    }

    public void setIntaPvalue(BigDecimal intaPvalue) {
        this.intaPvalue = intaPvalue;
    }

    public String getPositionMrna() {
        return positionMrna;
    }

    public void setPositionMrna(String positionMrna) {
        this.positionMrna = positionMrna;
    }

    public String getPositionNcrna() {
        return positionNcrna;
    }

    public void setPositionNcrna(String positionNcrna) {
        this.positionNcrna = positionNcrna;
    }

    public String getInteraction() {
        return interaction;
    }

    public void setInteraction(String interaction) {
        this.interaction = interaction;
    }

    public String getPositionSeedMrna() {
        return positionSeedMrna;
    }

    public void setPositionSeedMrna(String positionSeedMrna) {
        this.positionSeedMrna = positionSeedMrna;
    }

    public String getPositionSeedNcrna() {
        return positionSeedNcrna;
    }

    public void setPositionSeedNcrna(String positionSeedNcrna) {
        this.positionSeedNcrna = positionSeedNcrna;
    }

    public BigDecimal getHybridizationEnergy() {
        return hybridizationEnergy;
    }

    public void setHybridizationEnergy(BigDecimal hybridizationEnergy) {
        this.hybridizationEnergy = hybridizationEnergy;
    }

    public BigDecimal getUnfoldingEnergyMrna() {
        return unfoldingEnergyMrna;
    }

    public void setUnfoldingEnergyMrna(BigDecimal unfoldingEnergyMrna) {
        this.unfoldingEnergyMrna = unfoldingEnergyMrna;
    }

    public BigDecimal getUnfoldingEnergyNcrna() {
        return unfoldingEnergyNcrna;
    }

    public void setUnfoldingEnergyNcrna(BigDecimal unfoldingEnergyNcrna) {
        this.unfoldingEnergyNcrna = unfoldingEnergyNcrna;
    }

    public SmallRna getSrna() {
        return srna;
    }

    public void setSrna(SmallRna srna) {
        this.srna = srna;
    }

    public Gene getMrna() {
        return mrna;
    }

    public void setMrna(Gene mrna) {
        this.mrna = mrna;
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
        if (!(object instanceof RnaInteraction)) {
            return false;
        }
        RnaInteraction other = (RnaInteraction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RnaInteraction{" + "id=" + id + ", rank=" + rank + ", copraPvalue=" + copraPvalue + ", copraFdr=" + copraFdr + ", energy=" + energy + ", intaPvalue=" + intaPvalue + ", positionMrna=" + positionMrna + ", positionNcrna=" + positionNcrna + ", interaction=" + interaction + ", positionSeedMrna=" + positionSeedMrna + ", positionSeedNcrna=" + positionSeedNcrna + ", hybridizationEnergy=" + hybridizationEnergy + ", unfoldingEnergyMrna=" + unfoldingEnergyMrna + ", unfoldingEnergyNcrna=" + unfoldingEnergyNcrna + ", srna=" + srna + ", mrna=" + mrna + '}';
    }

    public String toString2() {
        return "RnaInteraction{" + "id=" + id + ", rank=" + rank + ", copraPvalue=" + copraPvalue + ", copraFdr=" + copraFdr + ", energy=" + energy + ", intaPvalue=" + intaPvalue + ", positionMrna=" + positionMrna + ", positionNcrna=" + positionNcrna + ", positionSeedMrna=" + positionSeedMrna + ", positionSeedNcrna=" + positionSeedNcrna + ", hybridizationEnergy=" + hybridizationEnergy + ", unfoldingEnergyMrna=" + unfoldingEnergyMrna + ", unfoldingEnergyNcrna=" + unfoldingEnergyNcrna + ", srna=" + srna + ", mrna=" + mrna + '}';
    }

    public String toFile() {
        return srna.getLocusTag() + "\t" + mrna.getLocusTag() + "\t" + rank + "\t" + copraPvalue + "\t" + copraFdr + "\t" + energy
                + "\t" + intaPvalue + "\t" + positionMrna + "\t" + positionNcrna
                + "\t" + interaction + "\t" + positionSeedMrna
                + "\t" + positionSeedNcrna + "\t" + hybridizationEnergy
                + "\t" + unfoldingEnergyMrna + "\t" + unfoldingEnergyNcrna;
    }

    /**
     * @return the trn
     */
    public Boolean getTrn() {
        return partOfTrn;
    }

    /**
     * @param trn the trn to set
     */
    public void setTrn(Boolean partOfTrn) {
        this.partOfTrn = partOfTrn;
    }

    /**
     * @return the ajustedPvalue
     */
    public BigDecimal getAjustedPvalue() {
        return ajustedPvalue;
    }

    /**
     * @param ajustedPvalue the ajustedPvalue to set
     */
    public void setAjustedPvalue(BigDecimal ajustedPvalue) {
        this.ajustedPvalue = ajustedPvalue;
    }

}
