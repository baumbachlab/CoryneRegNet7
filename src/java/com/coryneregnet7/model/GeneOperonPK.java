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
public class GeneOperonPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "gene")
    private int gene;
    @Basic(optional = false)
    @NotNull
    @Column(name = "operon")
    private int operon;

    public GeneOperonPK() {
    }

    public GeneOperonPK(int gene, int operon) {
        this.gene = gene;
        this.operon = operon;
    }

    public int getGene() {
        return gene;
    }

    public void setGene(int gene) {
        this.gene = gene;
    }

    public int getOperon() {
        return operon;
    }

    public void setOperon(int operon) {
        this.operon = operon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) gene;
        hash += (int) operon;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeneOperonPK)) {
            return false;
        }
        GeneOperonPK other = (GeneOperonPK) object;
        if (this.gene != other.gene) {
            return false;
        }
        if (this.operon != other.operon) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.coryneregnet7.model.GeneOperonPK[ gene=" + gene + ", operon=" + operon + " ]";
    }
    
}
