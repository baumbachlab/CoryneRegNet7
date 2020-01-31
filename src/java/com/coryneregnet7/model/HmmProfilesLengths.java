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
@Table(name = "hmm_profiles_lengths")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HmmProfilesLengths.findAll", query = "SELECT h FROM HmmProfilesLengths h")
    , @NamedQuery(name = "HmmProfilesLengths.findById", query = "SELECT h FROM HmmProfilesLengths h WHERE h.id = :id")
    , @NamedQuery(name = "HmmProfilesLengths.findByProfileLengthRange", query = "SELECT h FROM HmmProfilesLengths h WHERE h.profileLengthRange = :profileLengthRange")
    , @NamedQuery(name = "HmmProfilesLengths.findByFrequency", query = "SELECT h FROM HmmProfilesLengths h WHERE h.frequency = :frequency")
    , @NamedQuery(name = "HmmProfilesLengths.findByTypeAndDatabase", query = "SELECT h FROM HmmProfilesLengths h WHERE h.idRefStatistics.type = :type and h.idRefStatistics.database = :database")
    , @NamedQuery(name = "HmmProfilesLengths.findByOrganism", query = "SELECT h FROM HmmProfilesLengths h WHERE h.idRefStatistics.genome.organism.id = :organismid")
    , @NamedQuery(name = "HmmProfilesLengths.findByRefStatistics", query = "SELECT h FROM HmmProfilesLengths h WHERE h.idRefStatistics.id = :idRefStatistics")})
public class HmmProfilesLengths implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "profile_length_range")
    private Integer profileLengthRange;
    @Column(name = "frequency")
    private Integer frequency;
    @JoinColumn(name = "id_ref_statistics", referencedColumnName = "id")
    @ManyToOne
    private RefStatistics idRefStatistics;

    public HmmProfilesLengths() {
    }

    public HmmProfilesLengths(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProfileLengthRange() {
        return profileLengthRange;
    }

    public void setProfileLengthRange(Integer profileLengthRange) {
        this.profileLengthRange = profileLengthRange;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
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
        if (!(object instanceof HmmProfilesLengths)) {
            return false;
        }
        HmmProfilesLengths other = (HmmProfilesLengths) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HmmProfilesLengths{" + "id=" + id + ", profileLengthRange=" + profileLengthRange + ", frequency=" + frequency + ", idRefStatistics=" + idRefStatistics + '}';
    }
}
