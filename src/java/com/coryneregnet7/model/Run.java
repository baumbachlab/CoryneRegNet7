/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "run")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Run.findAll", query = "SELECT r FROM Run r")
    , @NamedQuery(name = "Run.findById", query = "SELECT r FROM Run r WHERE r.id = :id")
    , @NamedQuery(name = "Run.findByBlastCutoff", query = "SELECT r FROM Run r WHERE r.blastCutoff = :blastCutoff")
    , @NamedQuery(name = "Run.findByHmmerCutoff", query = "SELECT r FROM Run r WHERE r.hmmerCutoff = :hmmerCutoff")
    , @NamedQuery(name = "Run.findByOperons", query = "SELECT r FROM Run r WHERE r.operons = :operons")
    , @NamedQuery(name = "Run.findByProfilesTemplate", query = "SELECT r FROM Run r WHERE r.profilesTemplate = :profilesTemplate")
    , @NamedQuery(name = "Run.findByBlast", query = "SELECT r FROM Run r WHERE r.blast = :blast")
    , @NamedQuery(name = "Run.findByHmmer", query = "SELECT r FROM Run r WHERE r.hmmer = :hmmer")
    , @NamedQuery(name = "Run.findByProfilesTarget", query = "SELECT r FROM Run r WHERE r.profilesTarget = :profilesTarget")})
public class Run implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "blast_cutoff")
    private String blastCutoff;
    @Size(max = 2147483647)
    @Column(name = "hmmer_cutoff")
    private String hmmerCutoff;
    @Size(max = 2147483647)
    @Column(name = "operons")
    private String operons;
    @Size(max = 2147483647)
    @Column(name = "profiles_template")
    private String profilesTemplate;
    @Size(max = 2147483647)
    @Column(name = "promoter_template")
    private String promoterTemplate;
    @Size(max = 2147483647)
    @Column(name = "promoter_target")
    private String promoterTarget;
    @Size(max = 2147483647)
    @Column(name = "blast")
    private String blast;
    @Size(max = 2147483647)
    @Column(name = "blast_target")
    private String blastTarget;
    @Size(max = 2147483647)
    @Column(name = "blast_template")
    private String blastTemplate;
    @Size(max = 2147483647)
    @Column(name = "hmmer")
    private String hmmer;
    @Size(max = 2147483647)
    @Column(name = "profiles_target")
    private String profilesTarget;

    public Run() {
    }

    public Run(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlastCutoff() {
        return blastCutoff;
    }

    public void setBlastCutoff(String blastCutoff) {
        this.blastCutoff = blastCutoff;
    }

    public String getHmmerCutoff() {
        return hmmerCutoff;
    }

    public void setHmmerCutoff(String hmmerCutoff) {
        this.hmmerCutoff = hmmerCutoff;
    }

    public String getOperons() {
        return operons;
    }

    public void setOperons(String operons) {
        this.operons = operons;
    }

    public String getProfilesTemplate() {
        return profilesTemplate;
    }

    public void setProfilesTemplate(String profilesTemplate) {
        this.profilesTemplate = profilesTemplate;
    }

    public String getBlast() {
        return blast;
    }

    public void setBlast(String blast) {
        this.blast = blast;
    }

    public String getHmmer() {
        return hmmer;
    }

    public void setHmmer(String hmmer) {
        this.hmmer = hmmer;
    }

    public String getProfilesTarget() {
        return profilesTarget;
    }

    public void setProfilesTarget(String profilesTarget) {
        this.profilesTarget = profilesTarget;
    }

    public String getPromoterTemplate() {
        return promoterTemplate;
    }

    public void setPromoterTemplate(String promoterTemplate) {
        this.promoterTemplate = promoterTemplate;
    }

    public String getPromoterTarget() {
        return promoterTarget;
    }

    public void setPromoterTarget(String promoterTarget) {
        this.promoterTarget = promoterTarget;
    }

    public String getBlastTarget() {
        return blastTarget;
    }

    public void setBlastTarget(String blastTarget) {
        this.blastTarget = blastTarget;
    }

    public String getBlastTemplate() {
        return blastTemplate;
    }

    public void setBlastTemplate(String blastTemplate) {
        this.blastTemplate = blastTemplate;
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
        if (!(object instanceof Run)) {
            return false;
        }
        Run other = (Run) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Run{" + "id=" + id + ", blastCutoff=" + blastCutoff + ", hmmerCutoff=" + hmmerCutoff + ", operons=" + operons + ", profilesTemplate=" + profilesTemplate + ", promoterTemplate=" + promoterTemplate + ", promoterTarget=" + promoterTarget + ", blast=" + blast + ", blastTarget=" + blastTarget + ", blastTemplate=" + blastTemplate + ", hmmer=" + hmmer + ", profilesTarget=" + profilesTarget + '}';
    }

}
