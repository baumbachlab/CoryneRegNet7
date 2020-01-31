/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mariana
 */
@Embeddable
public class RunGenomePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "run")
    private int idRun;
    @Basic(optional = false)
    @NotNull
    @Column(name = "genome")
    private int idGenome;

    public RunGenomePK() {
    }

    public RunGenomePK(int idRun, int idGenome) {
        this.idRun = idRun;
        this.idGenome = idGenome;
    }

    public int getIdRun() {
       
        return idRun;
    }

    public void setIdRun(int idRun) {
        this.idRun = idRun;
    }

    public int getIdGenome() {
        return idGenome;
    }

    public void setIdGenome(int idGenome) {
        this.idGenome = idGenome;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRun;
        hash += (int) idGenome;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RunGenomePK)) {
            return false;
        }
        RunGenomePK other = (RunGenomePK) object;
        if (this.idRun != other.idRun) {
            return false;
        }
        if (this.idGenome != other.idGenome) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.coryneregnet7.model.RunGenomePK[ idRun=" + idRun + ", idGenome=" + idGenome + " ]";
    }
    
}
