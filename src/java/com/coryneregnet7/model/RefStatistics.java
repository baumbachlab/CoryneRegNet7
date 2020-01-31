/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.model;

import com.coryneregnet7.model.Genome;
import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mariana
 */
@Entity
@Table(name = "ref_statistics")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RefStatistics.findAll", query = "SELECT r FROM RefStatistics r")
    , @NamedQuery(name = "RefStatistics.findById", query = "SELECT r FROM RefStatistics r WHERE r.id = :id")
    , @NamedQuery(name = "RefStatistics.findByType", query = "SELECT r FROM RefStatistics r WHERE r.type = :type")
    , @NamedQuery(name = "RefStatistics.findByDatabase", query = "SELECT r FROM RefStatistics r WHERE r.database = :database")
    , @NamedQuery(name = "RefStatistics.findByTypeAndDatabase", query = "SELECT r FROM RefStatistics r WHERE r.type = :type and r.database = :database")
    , @NamedQuery(name = "RefStatistics.findByOrganism", query = "SELECT r FROM RefStatistics r WHERE r.genome.organism.id = :organismid")})
public class RefStatistics implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "type")
    private String type;
    @Size(max = 2147483647)
    @Column(name = "database")
    private String database;
    @JoinColumn(name = "genome", referencedColumnName = "id")
    @ManyToOne
    private Genome genome;

    public RefStatistics() {
    }

    public RefStatistics(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof RefStatistics)) {
            return false;
        }
        RefStatistics other = (RefStatistics) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RefStatistics{" + "id=" + id + ", type=" + type + ", database=" + database + ", genome=" + genome + '}';
    }
}
