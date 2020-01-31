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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mariana
 */
@Entity
@Table(name = "coregulators_statistics")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoregulatorsStatistics.findAll", query = "SELECT c FROM CoregulatorsStatistics c")
    , @NamedQuery(name = "CoregulatorsStatistics.findById", query = "SELECT c FROM CoregulatorsStatistics c WHERE c.id = :id")
    , @NamedQuery(name = "CoregulatorsStatistics.findByNumTfs", query = "SELECT c FROM CoregulatorsStatistics c WHERE c.numTfs = :numTfs")
    , @NamedQuery(name = "CoregulatorsStatistics.findByNumCoregulators", query = "SELECT c FROM CoregulatorsStatistics c WHERE c.numCoregulators = :numCoregulators")
    , @NamedQuery(name = "CoregulatorsStatistics.findByTypeAndDatabase", query = "SELECT c FROM CoregulatorsStatistics c WHERE c.idRefStatistics.type = :type and c.idRefStatistics.database = :database")
    , @NamedQuery(name = "CoregulatorsStatistics.findByOrganism", query = "SELECT c FROM CoregulatorsStatistics c WHERE c.idRefStatistics.genome.organism.id = :organismid")
    , @NamedQuery(name = "CoregulatorsStatistics.findByRefStatistics", query = "SELECT c FROM CoregulatorsStatistics c WHERE c.idRefStatistics.id = :idRefStatistics")})
public class CoregulatorsStatistics implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "num_tfs")
    private Integer numTfs;
    @Column(name = "num_coregulators")
    private Integer numCoregulators;
    @JoinColumn(name = "id_ref_statistics", referencedColumnName = "id")
    @ManyToOne
    private RefStatistics idRefStatistics;

    public CoregulatorsStatistics() {
    }

    public CoregulatorsStatistics(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumTfs() {
        return numTfs;
    }

    public void setNumTfs(Integer numTfs) {
        this.numTfs = numTfs;
    }

    public Integer getNumCoregulators() {
        return numCoregulators;
    }

    public void setNumCoregulators(Integer numCoregulators) {
        this.numCoregulators = numCoregulators;
    }

    public RefStatistics getIdRefStatistics() {
        return idRefStatistics;
    }

    public void setIdRefStatistics(RefStatistics idRefStatistics) {
        this.idRefStatistics = idRefStatistics;
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
        if (!(object instanceof CoregulatorsStatistics)) {
            return false;
        }
        CoregulatorsStatistics other = (CoregulatorsStatistics) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CoregulatorsStatistics{" + "id=" + id + ", numTfs=" + numTfs + ", numCoregulators=" + numCoregulators + ", idRefStatistics=" + idRefStatistics + '}';
    }

}
