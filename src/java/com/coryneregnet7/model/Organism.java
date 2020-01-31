/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "organism")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Organism.findAll", query = "SELECT o FROM Organism o ORDER BY o.genera, o.species, o.strain")
    , @NamedQuery(name = "Organism.findById", query = "SELECT o FROM Organism o WHERE o.id = :id")
    , @NamedQuery(name = "Organism.findByGenera", query = "SELECT o FROM Organism o WHERE o.genera = :genera")
    , @NamedQuery(name = "Organism.findByName", query = "SELECT o FROM Organism o WHERE o.genera like :genera and o.species like :species and o.strain like :strain and o.type = :type")
    , @NamedQuery(name = "Organism.findBySpecies", query = "SELECT o FROM Organism o WHERE o.species = :species")
    , @NamedQuery(name = "Organism.findByStrain", query = "SELECT o FROM Organism o WHERE o.strain = :strain")
    , @NamedQuery(name = "Organism.findByTaxonomyId", query = "SELECT o FROM Organism o WHERE o.taxonomyId = :taxonomyId")
    , @NamedQuery(name = "Organism.findByType", query = "SELECT o FROM Organism o WHERE o.type = :type ORDER BY o.genera, o.species, o.strain")
    , @NamedQuery(name = "Genome.bringNumberOfOrganisms", query = "SELECT count(o) FROM Organism o")
    , @NamedQuery(name = "Organism.bringGeneraByType", query = "SELECT distinct(o.genera) FROM Organism o WHERE o.type = :type ORDER BY o.genera")
    , @NamedQuery(name = "Genome.bringNumberOfModelOrganisms", query = "SELECT count(o) FROM Organism o WHERE o.type = :type")})
public class Organism implements Serializable {

    @OneToMany(mappedBy = "organism")
    private List<Genome> genomeList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "genera")
    private String genera;
    @Size(max = 2147483647)
    @Column(name = "species")
    private String species;
    @Size(max = 2147483647)
    @Column(name = "strain")
    private String strain;
    @Size(max = 2147483647)
    @Column(name = "taxonomy_id")
    private String taxonomyId;
    @Size(max = 2147483647)
    @Column(name = "type")
    private String type;

    public Organism() {
    }

    public Organism(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenera() {
        return genera;
    }

    public void setGenera(String genera) {
        this.genera = genera;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getStrain() {
        return strain;
    }

    public void setStrain(String strain) {
        this.strain = strain;
    }

    public String getTaxonomyId() {
        return taxonomyId;
    }

    public void setTaxonomyId(String taxonomyId) {
        this.taxonomyId = taxonomyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        if (!(object instanceof Organism)) {
            return false;
        }
        Organism other = (Organism) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Organism{" + "id=" + id + ", genera=" + genera + ", species=" + species + ", strain=" + strain + ", taxonomyId=" + taxonomyId + ", type=" + type + '}';
    }

    @XmlTransient
    public List<Genome> getGenomeList() {
        return genomeList;
    }

    public void setGenomeList(List<Genome> genomeList) {
        this.genomeList = genomeList;
    }

}
