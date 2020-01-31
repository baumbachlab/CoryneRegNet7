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
@Table(name = "regulators_regulations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegulatorsRegulations.findAll", query = "SELECT r FROM RegulatorsRegulations r")
    , @NamedQuery(name = "RegulatorsRegulations.findByTypeAndDatabase", query = "SELECT r FROM RegulatorsRegulations r WHERE r.type = :type and r.database = :database")
    , @NamedQuery(name = "RegulatorsRegulations.findById", query = "SELECT r FROM RegulatorsRegulations r WHERE r.id = :id")
    , @NamedQuery(name = "RegulatorsRegulations.findByNumActivators", query = "SELECT r FROM RegulatorsRegulations r WHERE r.numActivators = :numActivators")
    , @NamedQuery(name = "RegulatorsRegulations.findByNumRepressors", query = "SELECT r FROM RegulatorsRegulations r WHERE r.numRepressors = :numRepressors")
    , @NamedQuery(name = "RegulatorsRegulations.findByNumDual", query = "SELECT r FROM RegulatorsRegulations r WHERE r.numDual = :numDual")
    , @NamedQuery(name = "RegulatorsRegulations.findByNumActivations", query = "SELECT r FROM RegulatorsRegulations r WHERE r.numActivations = :numActivations")
    , @NamedQuery(name = "RegulatorsRegulations.findByNumRepressions", query = "SELECT r FROM RegulatorsRegulations r WHERE r.numRepressions = :numRepressions")
    , @NamedQuery(name = "RegulatorsRegulations.findByType", query = "SELECT r FROM RegulatorsRegulations r WHERE r.type = :type")
    , @NamedQuery(name = "RegulatorsRegulations.findByDatabase", query = "SELECT r FROM RegulatorsRegulations r WHERE r.database = :database")
    , @NamedQuery(name = "RegulatorsRegulations.findByOrganism", query = "SELECT r FROM RegulatorsRegulations r WHERE r.genome.organism.id = :organismid")})
public class RegulatorsRegulations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "num_activators")
    private Integer numActivators;
    @Column(name = "num_repressors")
    private Integer numRepressors;
    @Column(name = "num_dual")
    private Integer numDual;
    @Column(name = "num_activations")
    private Integer numActivations;
    @Column(name = "num_repressions")
    private Integer numRepressions;
    @Size(max = 2147483647)
    @Column(name = "type")
    private String type;
    @Size(max = 2147483647)
    @Column(name = "database")
    private String database;
    @JoinColumn(name = "genome", referencedColumnName = "id")
    @ManyToOne
    private Genome genome;

    public RegulatorsRegulations() {
    }

    public RegulatorsRegulations(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumActivators() {
        return numActivators;
    }

    public void setNumActivators(Integer numActivators) {
        this.numActivators = numActivators;
    }

    public Integer getNumRepressors() {
        return numRepressors;
    }

    public void setNumRepressors(Integer numRepressors) {
        this.numRepressors = numRepressors;
    }

    public Integer getNumDual() {
        return numDual;
    }

    public void setNumDual(Integer numDual) {
        this.numDual = numDual;
    }

    public Integer getNumActivations() {
        return numActivations;
    }

    public void setNumActivations(Integer numActivations) {
        this.numActivations = numActivations;
    }

    public Integer getNumRepressions() {
        return numRepressions;
    }

    public void setNumRepressions(Integer numRepressions) {
        this.numRepressions = numRepressions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public Genome getGenome() {
        return genome;
    }

    public void setGenome(Genome genome) {
        this.genome = genome;
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
        if (!(object instanceof RegulatorsRegulations)) {
            return false;
        }
        RegulatorsRegulations other = (RegulatorsRegulations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RegulatorsRegulations{" + "id=" + id + ", numActivators=" + numActivators + ", numRepressors=" + numRepressors + ", numDual=" + numDual + ", numActivations=" + numActivations + ", numRepressions=" + numRepressions + ", type=" + type + ", database=" + database + ", genome=" + genome + '}';
    }

}
