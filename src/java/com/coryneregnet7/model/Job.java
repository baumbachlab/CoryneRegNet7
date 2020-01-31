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
@Table(name = "job")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j")
    , @NamedQuery(name = "Job.findById", query = "SELECT j FROM Job j WHERE j.id = :id")})
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "gene", referencedColumnName = "id")
    @ManyToOne
    private Gene gene;
    @Size(max = 2147483647)
    @Column(name = "type")
    private String type;
    @Column(name = "selected_organism")
    private Integer selectedOrganism;
    //selected_organism integer,
    @Column(name = "organism_id")
    private Integer organismId;
    //organism_id integer,
    @Column(name = "min_hmm")
    private Integer minHmm;
    //min_hmm integer,
    @Size(max = 2147483647)
    @Column(name = "evalue")
    private String evalue;
    //evalue character varying,
    @Column(name = "bs_search_type")
    private Integer bsSearchType;
    //bs_search_type integer,
    @Column(name = "used_hmms")
    private Integer usedHmms;
    //used_hmms

    
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

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Job{" + "id=" + id + ", status=" + status + ", gene=" + gene.getLocusTag() + ", type=" + type + ", selectedOrganism=" + selectedOrganism + ", organismId=" + organismId + ", minHmm=" + minHmm + ", evalue=" + evalue + ", bsSearchType=" + bsSearchType + '}';
    }

    public Gene getGene() {
        return gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSelectedOrganism() {
        return selectedOrganism;
    }

    public void setSelectedOrganism(Integer selectedOrganism) {
        this.selectedOrganism = selectedOrganism;
    }

    public Integer getOrganismId() {
        return organismId;
    }

    public void setOrganismId(Integer organismId) {
        this.organismId = organismId;
    }

    public Integer getMinHmm() {
        return minHmm;
    }

    public void setMinHmm(Integer minHmm) {
        this.minHmm = minHmm;
    }

    public String getEvalue() {
        return evalue;
    }

    public void setEvalue(String evalue) {
        this.evalue = evalue;
    }

    public Integer getBsSearchType() {
        return bsSearchType;
    }

    public void setBsSearchType(Integer bsSearchType) {
        this.bsSearchType = bsSearchType;
    }

    /**
     * @return the usedHmms
     */
    public Integer getUsedHmms() {
        return usedHmms;
    }

    /**
     * @param usedHmms the usedHmms to set
     */
    public void setUsedHmms(Integer usedHmms) {
        this.usedHmms = usedHmms;
    }
    
    

}
