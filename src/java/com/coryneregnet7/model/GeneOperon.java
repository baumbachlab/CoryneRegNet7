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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mariana
 */
@Entity
@Table(name = "gene_operon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneOperon.findAll", query = "SELECT g FROM GeneOperon g")
    , @NamedQuery(name = "GeneOperon.findModelOperons", query = "SELECT go FROM GeneOperon go WHERE go.geneOperonPK.gene in (SELECT g FROM Gene g WHERE g.genome in (SELECT genome FROM Genome genome WHERE genome.organism.type = :type))")
    , @NamedQuery(name = "GeneOperon.findByGene", query = "SELECT g FROM GeneOperon g WHERE g.geneOperonPK.gene = :gene")
    , @NamedQuery(name = "GeneOperon.countOpByGenome", query = "SELECT count(g) FROM GeneOperon g WHERE g.geneOperonPK.gene in (SELECT g FROM Gene g WHERE g.genome.id = :genomeId)")
    , @NamedQuery(name = "GeneOperon.findByOperon", query = "SELECT g FROM GeneOperon g WHERE g.geneOperonPK.operon = :operon order by g.pos")
    , @NamedQuery(name = "GeneOperon.findFirstGeneOfOperon", query = "SELECT g FROM GeneOperon g WHERE g.geneOperonPK.operon = :operon and g.pos =:pos")
    , @NamedQuery(name = "GeneOperon.findByPos", query = "SELECT g FROM GeneOperon g WHERE g.pos = :pos")
    //, @NamedQuery(name = "Gene.listAllGenesOfOrganism", query = "SELECT g FROM Gene g WHERE g.genome in (SELECT genome FROM Genome genome WHERE genome.organism =:id)")
    , @NamedQuery(name = "GeneOperon.findByOrganism", query = "SELECT go FROM GeneOperon go WHERE go.geneOperonPK.gene in (SELECT g FROM Gene g WHERE g.genome in (SELECT genome FROM Genome genome WHERE genome.organism =:organism))")
    , @NamedQuery(name = "GeneOperon.findOperonsByOrganism", query = "SELECT distinct(go.geneOperonPK.operon) FROM GeneOperon go WHERE go.geneOperonPK.gene in (SELECT g FROM Gene g WHERE g.genome in (SELECT genome FROM Genome genome WHERE genome.organism =:organism) ) ORDER BY go.geneOperonPK.operon")
    , @NamedQuery(name = "GeneOperon.findByGeneNameLocusTag", query = "SELECT go FROM GeneOperon go WHERE go.geneOperonPK.gene in (SELECT g FROM Gene g WHERE g.locusTag = :locusTag or g.name = :geneName or g.alternativeLocusTag = :alternativeLocusTag order by g.locusTag)")
    , @NamedQuery(name = "GeneOperon.findByOrganismGene", query = "SELECT go FROM GeneOperon go WHERE go.geneOperonPK.gene in (SELECT g FROM Gene g WHERE g.genome in (SELECT genome FROM Genome genome WHERE genome.organism =:organism) and (g.locusTag = :locusTag or g.name = :geneName or g.alternativeLocusTag = :alternativeLocusTag) order by g.locusTag)")
    , @NamedQuery(name = "GeneOperon.findByGeneNameLocusTagOfModelOrganism", query = "SELECT go FROM GeneOperon go WHERE go.geneOperonPK.gene in (SELECT g FROM Gene g WHERE g.locusTag = :locusTag or g.name = :geneName or g.alternativeLocusTag = :alternativeLocusTag) and go.geneOperonPK.gene in (SELECT g FROM Gene g WHERE g.genome in (SELECT genome FROM Genome genome WHERE genome.organism.type =:type))")

})

public class GeneOperon implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GeneOperonPK geneOperonPK;
    @Column(name = "pos")
    private Integer pos;

    public GeneOperon() {
    }

    public GeneOperon(GeneOperonPK geneOperonPK) {
        this.geneOperonPK = geneOperonPK;
    }

    public GeneOperon(int gene, int operon) {
        this.geneOperonPK = new GeneOperonPK(gene, operon);
    }

    public GeneOperonPK getGeneOperonPK() {
        return geneOperonPK;
    }

    public void setGeneOperonPK(GeneOperonPK geneOperonPK) {
        this.geneOperonPK = geneOperonPK;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (geneOperonPK != null ? geneOperonPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeneOperon)) {
            return false;
        }
        GeneOperon other = (GeneOperon) object;
        if ((this.geneOperonPK == null && other.geneOperonPK != null) || (this.geneOperonPK != null && !this.geneOperonPK.equals(other.geneOperonPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GeneOperon{" + "geneOperonPK=" + geneOperonPK + ", pos=" + pos + '}';
    }

}
