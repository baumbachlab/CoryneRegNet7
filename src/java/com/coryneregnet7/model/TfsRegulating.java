/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.model;

import com.coryneregnet7.model.RefStatistics;
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
@Table(name = "tfs_regulating")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TfsRegulating.findAll", query = "SELECT t FROM TfsRegulating t")
    , @NamedQuery(name = "TfsRegulating.findById", query = "SELECT t FROM TfsRegulating t WHERE t.id = :id")
    , @NamedQuery(name = "TfsRegulating.findByNumTf", query = "SELECT t FROM TfsRegulating t WHERE t.numTf = :numTf")
    , @NamedQuery(name = "TfsRegulating.findByNumTg", query = "SELECT t FROM TfsRegulating t WHERE t.numTg = :numTg")
    , @NamedQuery(name = "TfsRegulating.findByTypeAndDatabase", query = "SELECT t FROM TfsRegulating t WHERE t.idRefStatistics.type = :type and t.idRefStatistics.database = :database")
    , @NamedQuery(name = "TfsRegulating.findByOrganism", query = "SELECT t FROM TfsRegulating t WHERE t.idRefStatistics.genome.organism.id = :organismid")
    , @NamedQuery(name = "TfsRegulating.findByRefStatistics", query = "SELECT t FROM TfsRegulating t WHERE t.idRefStatistics.id = :idRefStatistics")})
public class TfsRegulating implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "num_tf")
    private Integer numTf;
    @Column(name = "num_tg")
    private Integer numTg;
    @JoinColumn(name = "id_ref_statistics", referencedColumnName = "id")
    @ManyToOne
    private RefStatistics idRefStatistics;

    public TfsRegulating() {
    }

    public TfsRegulating(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumTf() {
        return numTf;
    }

    public void setNumTf(Integer numTf) {
        System.out.println("vou setar o num");
        this.numTf = numTf;
    }

    public Integer getNumTg() {
        return numTg;
    }

    public void setNumTg(Integer numTg) {
        this.numTg = numTg;
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
        if (!(object instanceof TfsRegulating)) {
            return false;
        }
        TfsRegulating other = (TfsRegulating) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TfsRegulating{" + "id=" + id + ", numTf=" + numTf + ", numTg=" + numTg + ", idRefStatistics=" + idRefStatistics + '}';
    }

}
