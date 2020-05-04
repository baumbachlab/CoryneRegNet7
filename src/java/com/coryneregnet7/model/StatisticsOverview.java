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
@Table(name = "statistics_overview")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatisticsOverview.findAll", query = "SELECT s FROM StatisticsOverview s")
    , @NamedQuery(name = "StatisticsOverview.findById", query = "SELECT s FROM StatisticsOverview s WHERE s.id = :id")
    , @NamedQuery(name = "StatisticsOverview.findByGenes", query = "SELECT s FROM StatisticsOverview s WHERE s.genes = :genes")
    , @NamedQuery(name = "StatisticsOverview.findByProteins", query = "SELECT s FROM StatisticsOverview s WHERE s.proteins = :proteins")
    , @NamedQuery(name = "StatisticsOverview.findByRegulations", query = "SELECT s FROM StatisticsOverview s WHERE s.regulations = :regulations")
    , @NamedQuery(name = "StatisticsOverview.findByRegulators", query = "SELECT s FROM StatisticsOverview s WHERE s.regulators = :regulators")
    , @NamedQuery(name = "StatisticsOverview.findByRegulatedGenes", query = "SELECT s FROM StatisticsOverview s WHERE s.regulatedGenes = :regulatedGenes")
    , @NamedQuery(name = "StatisticsOverview.findByBindingMotifs", query = "SELECT s FROM StatisticsOverview s WHERE s.bindingMotifs = :bindingMotifs")
    , @NamedQuery(name = "StatisticsOverview.findByHmmProfiles", query = "SELECT s FROM StatisticsOverview s WHERE s.hmmProfiles = :hmmProfiles")
    , @NamedQuery(name = "StatisticsOverview.findByGenomes", query = "SELECT s FROM StatisticsOverview s WHERE s.genome.id = :genomes")
    , @NamedQuery(name = "StatisticsOverview.findByOrganism", query = "SELECT s FROM StatisticsOverview s WHERE s.genome.organism.id = :organismid")
    , @NamedQuery(name = "StatisticsOverview.findByDatabase", query = "SELECT s FROM StatisticsOverview s WHERE s.database = :database")
    , @NamedQuery(name = "StatisticsOverview.findByTypeAndDatabase", query = "SELECT s FROM StatisticsOverview s WHERE s.type = :type and s.database = :database")})
public class StatisticsOverview implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "genes")
    private Integer genes;
    @Column(name = "proteins")
    private Integer proteins;
    @Column(name = "regulations")
    private Integer regulations;
    @Column(name = "regulators")
    private Integer regulators;
    @Column(name = "regulated_genes")
    private Integer regulatedGenes;
    @Column(name = "binding_motifs")
    private Integer bindingMotifs;
    @Column(name = "hmm_profiles")
    private Integer hmmProfiles;
    @JoinColumn(name = "genome", referencedColumnName = "id")
    @ManyToOne
    private Genome genome;
    @Size(max = 2147483647)
    @Column(name = "database")
    private String database;
    @Size(max = 2147483647)
    @Column(name = "type")
    private String type;
    @Column(name = "num_genomes")
    private Integer numGenomes;
    @Column(name = "regulatory_rnas")
    private Integer regulatoryRnas;
    @Column(name = "small_rnas")
    private Integer smallRnas;
    @Column(name = "regulations_srna")
    private Integer regulationsSrna;
    @Column(name = "genes_by_rna")
    private Integer genesByRna;

    public StatisticsOverview() {
    }

    public StatisticsOverview(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGenes() {
        return genes;
    }

    public void setGenes(Integer genes) {
        this.genes = genes;
    }

    public Integer getProteins() {
        return proteins;
    }

    public void setProteins(Integer proteins) {
        this.proteins = proteins;
    }

    public Integer getRegulations() {
        return regulations;
    }

    public void setRegulations(Integer regulations) {
        this.regulations = regulations;
    }

    public Integer getRegulators() {
        return regulators;
    }

    public void setRegulators(Integer regulators) {
        this.regulators = regulators;
    }

    public Integer getRegulatedGenes() {
        return regulatedGenes;
    }

    public void setRegulatedGenes(Integer regulatedGenes) {
        this.regulatedGenes = regulatedGenes;
    }

    public Integer getBindingMotifs() {
        return bindingMotifs;
    }

    public void setBindingMotifs(Integer bindingMotifs) {
        this.bindingMotifs = bindingMotifs;
    }

    public Integer getHmmProfiles() {
        return hmmProfiles;
    }

    public void setHmmProfiles(Integer hmmProfiles) {
        this.hmmProfiles = hmmProfiles;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
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
        if (!(object instanceof StatisticsOverview)) {
            return false;
        }
        StatisticsOverview other = (StatisticsOverview) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StatisticsOverview{" + "id=" + id + ", genes=" + genes + ", proteins=" + proteins + ", regulations=" + regulations + ", regulators=" + regulators + ", regulatedGenes=" + regulatedGenes + ", bindingMotifs=" + bindingMotifs + ", hmmProfiles=" + hmmProfiles + ", genomes=" + getGenome() + ", database=" + database + '}';
    }

    public Genome getGenome() {
        return genome;
    }

    public void setGenome(Genome genome) {
        this.genome = genome;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumGenomes() {
        return numGenomes;
    }

    public void setNumGenomes(Integer numGenomes) {
        this.numGenomes = numGenomes;
    }

    /**
     * @return the regulatoryRnas
     */
    public Integer getRegulatoryRnas() {
        return regulatoryRnas;
    }

    /**
     * @param regulatoryRnas the regulatoryRnas to set
     */
    public void setRegulatoryRnas(Integer regulatoryRnas) {
        this.regulatoryRnas = regulatoryRnas;
    }

    /**
     * @return the smallRnas
     */
    public Integer getSmallRnas() {
        return smallRnas;
    }

    /**
     * @param smallRnas the smallRnas to set
     */
    public void setSmallRnas(Integer smallRnas) {
        this.smallRnas = smallRnas;
    }

    /**
     * @return the regulationsSrna
     */
    public Integer getRegulationsSrna() {
        return regulationsSrna;
    }

    /**
     * @param regulationsSrna the regulationsSrna to set
     */
    public void setRegulationsSrna(Integer regulationsSrna) {
        this.regulationsSrna = regulationsSrna;
    }

    /**
     * @return the genesByRna
     */
    public Integer getGenesByRna() {
        return genesByRna;
    }

    /**
     * @param genesByRna the genesByRna to set
     */
    public void setGenesByRna(Integer genesByRna) {
        this.genesByRna = genesByRna;
    }

}
