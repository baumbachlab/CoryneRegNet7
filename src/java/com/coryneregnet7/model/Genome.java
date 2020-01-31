/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "genome")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Genome.findAll", query = "SELECT g FROM Genome g")
    , @NamedQuery(name = "Genome.findAllHash", query = "SELECT g.organism.id, g.ncbiAccessionNumber, g.organism.genera, g.organism.species, g.organism.strain FROM Genome g WHERE g.organism in (SELECT o FROM Organism o) ORDER BY g.organism.genera, g.organism.species, g.organism.strain")
    , @NamedQuery(name = "Genome.findById", query = "SELECT g FROM Genome g WHERE g.id = :id")
    , @NamedQuery(name = "Genome.findByNcbiAccessionNumber", query = "SELECT g FROM Genome g WHERE g.ncbiAccessionNumber = :ncbiAccessionNumber")
    , @NamedQuery(name = "Genome.findByFnaFile", query = "SELECT g FROM Genome g WHERE g.fnaFile = :fnaFile")
    , @NamedQuery(name = "Genome.findByFaaFile", query = "SELECT g FROM Genome g WHERE g.faaFile = :faaFile")
    , @NamedQuery(name = "Genome.findByGbffFile", query = "SELECT g FROM Genome g WHERE g.gbffFile = :gbffFile")
    , @NamedQuery(name = "Genome.findByGenomeName", query = "SELECT g FROM Genome g WHERE g.genomeName = :genomeName")
    //, @NamedQuery(name = "Genome.bringNumberOfGenomes", query = "SELECT count(g) FROM Genome g")
//Genome.bringNumberOfGenomes    
    , @NamedQuery(name = "Genome.bringNamesByOrganismType", query = "SELECT g.gbffFile FROM Genome g WHERE g.organism in (SELECT o FROM Organism o WHERE o.type =:type)")
    , @NamedQuery(name = "Genome.findByOrganismType", query = "SELECT g FROM Genome g WHERE g.organism in (SELECT o FROM Organism o WHERE o.type =:type)")
    , @NamedQuery(name = "Genome.findByOrganismTypeHash", query = "SELECT g.organism.id, g.ncbiAccessionNumber, g.organism.genera, g.organism.species, g.organism.strain FROM Genome g WHERE g.organism in (SELECT o FROM Organism o WHERE o.type =:type) ORDER BY g.organism.genera, g.organism.species, g.organism.strain")
    , @NamedQuery(name = "Genome.findByOrganism", query = "SELECT g FROM Genome g WHERE g.organism = :organism")})
//select * from genome where genome.organism in (select organism.id from organism where organism.type='model');
public class Genome implements Serializable {

    @OneToMany(mappedBy = "genome")
    private List<RefStatistics> refStatisticsList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "ncbi_accession_number")
    private String ncbiAccessionNumber;
    @Size(max = 2147483647)
    @Column(name = "fna_file")
    private String fnaFile;
    @Size(max = 2147483647)
    @Column(name = "faa_file")
    private String faaFile;
    @Size(max = 2147483647)
    @Column(name = "gbff_file")
    private String gbffFile;
    @JoinColumn(name = "organism", referencedColumnName = "id")
    @ManyToOne
    private Organism organism;
    @Column(name = "search_space")
    private Integer searchSpace;
    @Column(name = "size")
    private BigInteger size;
    @Size(max = 2147483647)
    @Column(name = "fasta_file")
    private String fastaFile;
    @Size(max = 2147483647)
    @Column(name = "genome_name")
    private String genomeName;

    public Genome() {
    }

    public Genome(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNcbiAccessionNumber() {
        return ncbiAccessionNumber;
    }

    public void setNcbiAccessionNumber(String ncbiAccessionNumber) {
        this.ncbiAccessionNumber = ncbiAccessionNumber;
    }

    public String getFnaFile() {
        return fnaFile;
    }

    public void setFnaFile(String fnaFile) {
        this.fnaFile = fnaFile;
    }

    public String getFaaFile() {
        return faaFile;
    }

    public void setFaaFile(String faaFile) {
        this.faaFile = faaFile;
    }

    public String getGbffFile() {
        return gbffFile;
    }

    public void setGbffFile(String gbffFile) {
        this.gbffFile = gbffFile;
    }

    public Organism getOrganism() {
        return organism;
    }

    public void setOrganism(Organism organism) {
        this.organism = organism;
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
        if (!(object instanceof Genome)) {
            return false;
        }
        Genome other = (Genome) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Integer getSearchSpace() {
        return searchSpace;
    }

    public void setSearchSpace(Integer searchSpace) {
        this.searchSpace = searchSpace;
    }

    public BigInteger getSize() {
        return size;
    }

    public void setSize(BigInteger size) {
        this.size = size;
    }

    public String getFastaFile() {
        return fastaFile;
    }

    public void setFastaFile(String fastaFile) {
        this.fastaFile = fastaFile;
    }

    @Override
    public String toString() {
        return "Genome{" + "id=" + id + ", ncbiAccessionNumber=" + ncbiAccessionNumber + ", genomeName=" + genomeName + ", fnaFile=" + fnaFile + ", faaFile=" + faaFile + ", gbffFile=" + gbffFile + ", organism=" + organism + ", searchSpace=" + searchSpace + ", size=" + size + ", fastaFile=" + fastaFile + '}';
    }

    public String getGenomeName() {
        return genomeName;
    }

    public void setGenomeName(String genomeName) {
        this.genomeName = genomeName;
    }

    @XmlTransient
    public List<RefStatistics> getRefStatisticsList() {
        return refStatisticsList;
    }

    public void setRefStatisticsList(List<RefStatistics> refStatisticsList) {
        this.refStatisticsList = refStatisticsList;
    }

}
