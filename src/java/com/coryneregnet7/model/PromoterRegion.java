/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "promoter_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PromoterRegion.findAll", query = "SELECT p FROM PromoterRegion p")
    , @NamedQuery(name = "PromoterRegion.findById", query = "SELECT p FROM PromoterRegion p WHERE p.id = :id")
    , @NamedQuery(name = "PromoterRegion.findByGenome", query = "SELECT g.promoterRegion FROM Gene g WHERE g.genome = :genome")
    , @NamedQuery(name = "PromoterRegion.findByInitialPosition", query = "SELECT p FROM PromoterRegion p WHERE p.initialPosition = :initialPosition")
    , @NamedQuery(name = "PromoterRegion.findByEndPosition", query = "SELECT p FROM PromoterRegion p WHERE p.endPosition = :endPosition")
    , @NamedQuery(name = "PromoterRegion.findBySequence", query = "SELECT p FROM PromoterRegion p WHERE p.sequence = :sequence")})
public class PromoterRegion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "initial_position")
    private BigInteger initialPosition;
    @Column(name = "end_position")
    private BigInteger endPosition;
    @Size(max = 2147483647)
    @Column(name = "sequence")
    private String sequence;
    @Size(max = 2147483647)
    @Column(name = "file")
    private String file;

    public PromoterRegion() {
    }

    public PromoterRegion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(BigInteger initialPosition) {
        this.initialPosition = initialPosition;
    }

    public BigInteger getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(BigInteger endPosition) {
        this.endPosition = endPosition;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
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
        if (!(object instanceof PromoterRegion)) {
            return false;
        }
        PromoterRegion other = (PromoterRegion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "PromoterRegion{" + "id=" + id + ", initialPosition=" + initialPosition + ", endPosition=" + endPosition + ", sequence=" + sequence + ", file=" + file + '}';
    }

}
