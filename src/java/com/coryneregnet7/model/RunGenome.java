/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "run_genome")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RunGenome.findAll", query = "SELECT r FROM RunGenome r")
    , @NamedQuery(name = "RunGenome.findByIdRun", query = "SELECT r FROM RunGenome r WHERE r.runGenomePK.idRun = :idRun")
    , @NamedQuery(name = "RunGenome.findByRunRole", query = "SELECT r FROM RunGenome r WHERE r.runGenomePK.idRun = :idRun and r.role =:role")
    , @NamedQuery(name = "RunGenome.findByIdGenome", query = "SELECT r FROM RunGenome r WHERE r.runGenomePK.idGenome = :idGenome")
    , @NamedQuery(name = "RunGenome.findByIdRunGenome", query = "SELECT r FROM RunGenome r WHERE r.runGenomePK.idRun = :idRun and r.runGenomePK.idGenome = :idGenome")
    , @NamedQuery(name = "RunGenome.findByRole", query = "SELECT r FROM RunGenome r WHERE r.role = :role")})
public class RunGenome implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RunGenomePK runGenomePK;
    @Size(max = 2147483647)
    @Column(name = "role")
    private String role;
    @JoinColumn(name = "genome", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Genome genome;
    @JoinColumn(name = "run", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Run run;

    public RunGenome() {
    }

    public RunGenome(RunGenomePK runGenomePK) {
        this.runGenomePK = runGenomePK;
    }

    public RunGenome(int idRun, int idGenome) {
        this.runGenomePK = new RunGenomePK(idRun, idGenome);
    }

    public RunGenomePK getRunGenomePK() {
        return runGenomePK;
    }

    public void setRunGenomePK(RunGenomePK runGenomePK) {
        this.runGenomePK = runGenomePK;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Genome getGenome() {
        return genome;
    }

    public void setGenome(Genome genome) {
        this.genome = genome;
    }

    public Run getRun() {
        return run;
    }

    public void setRun(Run run) {
        this.run = run;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (runGenomePK != null ? runGenomePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RunGenome)) {
            return false;
        }
        RunGenome other = (RunGenome) object;
        if ((this.runGenomePK == null && other.runGenomePK != null) || (this.runGenomePK != null && !this.runGenomePK.equals(other.runGenomePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.coryneregnet7.model.RunGenome[ runGenomePK=" + runGenomePK + " ]";
    }

}
