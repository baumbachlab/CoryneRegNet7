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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author doglas
 */
@Entity
@Table(name = "gene")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gene.findAll", query = "SELECT g FROM Gene g order by g.locusTag")
    , @NamedQuery(name = "Gene.findModels", query = "SELECT g FROM Gene g WHERE g.genome in (SELECT genome FROM Genome genome WHERE genome.organism in (SELECT o FROM Organism o WHERE o.type = :type)) order by g.locusTag")
    , @NamedQuery(name = "Gene.findGenesOfModelsByLocusTagGeneName", query = "SELECT g FROM Gene g WHERE (g.locusTag = :locusTag or g.name = :geneName or g.alternativeLocusTag = :alternativeLocusTag) and g.genome in (SELECT genome FROM Genome genome WHERE genome.organism in (SELECT o FROM Organism o WHERE o.type = :type)) order by g.locusTag")
    , @NamedQuery(name = "Gene.findGeneByLocusTagOrGeneName", query = "SELECT g FROM Gene g WHERE (g.locusTag = :gene or g.name = :gene or g.alternativeLocusTag = :gene) and g.genome.organism.id = :organismId")
    , @NamedQuery(name = "Gene.findById", query = "SELECT g FROM Gene g WHERE g.id = :id")
    , @NamedQuery(name = "Gene.findByLocusTag", query = "SELECT g FROM Gene g WHERE lower(g.locusTag) = lower(:locusTag)")
    , @NamedQuery(name = "Gene.findByAltLocusTag", query = "SELECT g FROM Gene g WHERE lower(g.alternativeLocusTag) = lower(:alternativeLocusTag)")
    , @NamedQuery(name = "Gene.findByProteinId", query = "SELECT g FROM Gene g WHERE g.proteinId = :proteinId")
    , @NamedQuery(name = "Gene.findByStartPosition", query = "SELECT g FROM Gene g WHERE g.startPosition = :startPosition")
    , @NamedQuery(name = "Gene.findByEndPosition", query = "SELECT g FROM Gene g WHERE g.endPosition = :endPosition")
    , @NamedQuery(name = "Gene.findByName", query = "SELECT g FROM Gene g WHERE g.name = :name")
    , @NamedQuery(name = "Gene.findByPromoterRegion", query = "SELECT g FROM Gene g WHERE g.promoterRegion = :promoterRegion")
    , @NamedQuery(name = "Gene.bringPromoterRegionSize", query = "SELECT count(promoterRegion) FROM Gene g WHERE g.genome = :genome")
    , @NamedQuery(name = "Gene.findByGenome", query = "SELECT g FROM Gene g WHERE g.genome = :genome order by g.locusTag")
    , @NamedQuery(name = "Gene.findByGenomeName", query = "SELECT g FROM Gene g WHERE g.genome = :genome and g.name= :name order by g.locusTag")
    , @NamedQuery(name = "Gene.findByOrganism", query = "SELECT g FROM Gene g WHERE g.genome.organism = :organism")
    , @NamedQuery(name = "Gene.findByGenomeRoleMinBsNum", query = "SELECT g FROM Gene g WHERE g.genome = :genome and g.role != '' and g.bsNumber >= :minNOfHMMs")
    , @NamedQuery(name = "Gene.findByOrganismRoleMinBsNum", query = "SELECT g FROM Gene g WHERE g.genome.organism = :organism and g.role != '' and g.bsNumber >= :minNOfHMMs")
    , @NamedQuery(name = "Gene.findByProduct", query = "SELECT g FROM Gene g WHERE g.product = :product")
    , @NamedQuery(name = "Gene.findByProteinSequence", query = "SELECT g FROM Gene g WHERE g.proteinSequence = :proteinSequence")
    , @NamedQuery(name = "Gene.findTranscriptionFactors", query = "SELECT g FROM Gene g WHERE g.role != ''")
    , @NamedQuery(name = "Gene.findTranscriptionFactorsModelOrganisms", query = "SELECT g FROM Gene g WHERE g.role != '' and g.genome.organism.type = :model")
    , @NamedQuery(name = "Gene.findNumberOfTFsModelOrganisms", query = "SELECT count(g) FROM Gene g WHERE g.role != '' and g.genome.organism.type = :model")
    , @NamedQuery(name = "Gene.findNumberOfTFs", query = "SELECT count(g) FROM Gene g WHERE g.role != ''")
    , @NamedQuery(name = "Gene.findNumberOfTFsByOrganism", query = "SELECT count(g) FROM Gene g WHERE g.role != '' and g.genome.organism.id = :organismId")
    , @NamedQuery(name = "Gene.findNumberOfTFsModelOrganismsByRole", query = "SELECT count(g) FROM Gene g WHERE g.role = :role and g.genome.organism.type = :model")
    , @NamedQuery(name = "Gene.findNumberOfTFsByRole", query = "SELECT count(g) FROM Gene g WHERE g.role = :role")
    , @NamedQuery(name = "Gene.findNumberOfTFsByOrganismAndRole", query = "SELECT count(g) FROM Gene g WHERE g.role = :role and g.genome.organism.id = :organismId")
    , @NamedQuery(name = "Gene.bringNumberOfGenes", query = "SELECT count(g) FROM Gene g WHERE g.locusTag !=''")
    , @NamedQuery(name = "Gene.bringNumberOfModelGenes", query = "SELECT count(g) FROM Gene g WHERE g.genome.organism.type = :type and g.locusTag !=''")
    , @NamedQuery(name = "Gene.bringNumberOfGenesOfOrganism", query = "SELECT count(g) FROM Gene g WHERE g.genome.organism.id = :id")
    , @NamedQuery(name = "Gene.findByGenomeLocusTagProteinId", query = "SELECT g FROM Gene g WHERE g.genome = :genome and (g.locusTag = :locusTag or g.proteinId = :proteinId)")
    , @NamedQuery(name = "Gene.findByGenomeLocusTag", query = "SELECT g FROM Gene g WHERE g.genome = :genome and g.locusTag = :locusTag")
    , @NamedQuery(name = "Gene.findByGenomeOldLocusTag", query = "SELECT g FROM Gene g WHERE g.genome = :genome and g.alternativeLocusTag = :altLocusTag")
    , @NamedQuery(name = "Gene.bringNumberOfProteins", query = "SELECT count(g) FROM Gene g WHERE g.proteinId !=''")
    , @NamedQuery(name = "Gene.bringNumberOfModelProteins", query = "SELECT count(g) FROM Gene g WHERE g.proteinId !='' and g.genome.organism.type = :type")
    , @NamedQuery(name = "Gene.bringNumberOfProteinsOfOrganism", query = "SELECT count(g) FROM Gene g WHERE g.proteinId !='' and g.genome.organism.id = :id")
    , @NamedQuery(name = "Gene.bringNumberOfModelHmmProfiles", query = "SELECT count(g) FROM Gene g WHERE g.hmmProfile !='' and g.genome.organism.type = :type")
    , @NamedQuery(name = "Gene.bringNumberOfHmmProfiles", query = "SELECT count(g) FROM Gene g WHERE g.hmmProfile !=''")
    , @NamedQuery(name = "Gene.bringNumberOfHmmProfilesOfOrganism", query = "SELECT count(g) FROM Gene g WHERE g.hmmProfile !='' and g.genome.organism =:organismId")
    //, @NamedQuery(name = "Gene.listAllOfOrganism", query = "SELECT g FROM Gene g where g.genome.organism = :genome.organism")
    //, @NamedQuery(name = "Genome.bringNamesByOrganismType", query = "SELECT g.gbkFile FROM Genome g WHERE g.organism in (SELECT o FROM Organism o WHERE o.type =:type)")
    , @NamedQuery(name = "Gene.listAllGenesOfOrganism", query = "SELECT g FROM Gene g WHERE g.genome in (SELECT genome FROM Genome genome WHERE genome.organism =:id) order by g.locusTag")
    , @NamedQuery(name = "Gene.findAllByLocusTagGeneName", query = "SELECT g FROM Gene g WHERE g.locusTag = :locusTag or g.name = :geneName or g.alternativeLocusTag = :alternativeLocusTag order by g.locusTag")
    , @NamedQuery(name = "Gene.findByOrganismGene", query = "SELECT g FROM Gene g WHERE g.genome in (SELECT genome FROM Genome genome WHERE genome.organism =:organism) and (g.locusTag = :locusTag or g.name = :geneName or g.alternativeLocusTag = :alternativeLocusTag) order by g.locusTag")
    , @NamedQuery(name = "Gene.findByGenomeLocusTagEndPositionReverse", query = "SELECT g FROM Gene g WHERE (g.genome = :genome and g.locusTag != :locusTag) and (g.endPosition < :promoterStart and g.endPosition > :promoterEnd)")
    //SELECT * FROM GENE WHERE GENOME=X AND LOCUS_TAG!=Y AND END_POSITION>P_START AND END_POSITION<P_END    
    , @NamedQuery(name = "Gene.findByGenomeLocusTagStartPositionReverse", query = "SELECT g FROM Gene g WHERE (g.genome = :genome and g.locusTag != :locusTag) and (g.startPosition > :promoterStart and g.startPosition < :promoterEnd)")
    , @NamedQuery(name = "Gene.findByGenomeLocusTagStartPositionForward", query = "SELECT g FROM Gene g WHERE (g.genome = :genome and g.locusTag != :locusTag) and (g.startPosition < :promoterStart and g.startPosition > :promoterEnd)")
    , @NamedQuery(name = "Gene.findByGenomeLocusTagEndPositionForward", query = "SELECT g FROM Gene g WHERE (g.genome = :genome and g.locusTag != :locusTag) and (g.endPosition > :promoterStart and g.endPosition < :promoterEnd)")
    , @NamedQuery(name = "Gene.findByGenomeMax", query = "SELECT max(g.id) FROM Gene g WHERE g.genome = :genome")
    , @NamedQuery(name = "Gene.findByGenomeMin", query = "SELECT min(g.id) FROM Gene g WHERE g.genome = :genome")
    , @NamedQuery(name = "Gene.findByHmmLogo", query = "SELECT g FROM Gene g WHERE g.hmmLogo not like '/home/ubuntu/database/CoryneRegNet7/web/images/%'")
    , @NamedQuery(name = "Gene.findByHmmLogoDotPng", query = "SELECT g FROM Gene g WHERE g.hmmLogo like '%/.png'")
    , @NamedQuery(name = "Gene.findIfPredictedTfTg", query = "SELECT g FROM Gene g WHERE g.id = :geneId and (g.id in (SELECT preg.homologousTargetGene from PredictedRegulatoryInteraction preg) or g.id in (SELECT preg1.homologousTranscriptionFactor from PredictedRegulatoryInteraction preg1))")
    , @NamedQuery(name = "Gene.findIfExperimentalTfTg", query = "SELECT g FROM Gene g WHERE g.id = :geneId and (g.id in (SELECT reg.correspondentTargetGene from RegulatoryInteraction reg) or g.id in (SELECT reg1.correspondentTranscriptionFactor from RegulatoryInteraction reg1))")
})
public class Gene implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "locus_tag")
    private String locusTag;
    @Size(max = 2147483647)
    @Column(name = "protein_id")
    private String proteinId;
    @Column(name = "start_position")
    private BigInteger startPosition;
    @Column(name = "end_position")
    private BigInteger endPosition;
    @Size(max = 2147483647)
    @Column(name = "name")
    private String name;
    @Size(max = 2147483647)
    @Column(name = "product")
    private String product;
    @Size(max = 2147483647)
    @Column(name = "protein_sequence")
    private String proteinSequence;
    @Size(max = 2147483647)
    @Column(name = "nucleotide_sequence")
    private String nucleotideSequence;
    @JoinColumn(name = "genome", referencedColumnName = "id")
    @ManyToOne
    private Genome genome;
    @Size(max = 2147483647)
    @Column(name = "alternative_locus_tag")
    private String alternativeLocusTag;
    @Size(max = 2147483647)
    @Column(name = "hmm_profile")
    private String hmmProfile;
    @Column(name = "search_space")
    private Integer searchSpace;
    @Size(max = 2147483647)
    @Column(name = "orientation")
    private String orientation;
    @JoinColumn(name = "promoter_region", referencedColumnName = "id")
    @ManyToOne
    private PromoterRegion promoterRegion;
    @Size(max = 2147483647)
    @Column(name = "role")
    private String role;
    @Size(max = 2147483647)
    @Column(name = "hmm_logo")
    private String hmmLogo;
    @Column(name = "bs_number")
    private Integer bsNumber;
    @Column(name = "profile_length")
    private Integer profileLength;
    @Column(name = "is_sigma")
    private Boolean isSigma;

    public Gene() {
    }

    public Gene(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocusTag() {
        return locusTag;
    }

    public void setLocusTag(String locusTag) {
        this.locusTag = locusTag;
    }

    public String getProteinId() {
        return proteinId;
    }

    public void setProteinId(String proteinId) {
        this.proteinId = proteinId;
    }

    public BigInteger getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(BigInteger startPosition) {
        this.startPosition = startPosition;
    }

    public BigInteger getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(BigInteger endPosition) {
        this.endPosition = endPosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProteinSequence() {
        return proteinSequence;
    }

    public void setProteinSequence(String proteinSequence) {
        this.proteinSequence = proteinSequence;
    }

    public String getNucleotideSequence() {
        return nucleotideSequence;
    }

    public void setNucleotideSequence(String nucleotideSequence) {
        this.nucleotideSequence = nucleotideSequence;
    }

    public Integer getBsNumber() {
        return bsNumber;
    }

    public void setBsNumber(Integer bsNumber) {
        this.bsNumber = bsNumber;
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
        if (!(object instanceof Gene)) {
            return false;
        }
        Gene other = (Gene) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Genome getGenome() {
        return genome;
    }

    public void setGenome(Genome genome) {
        this.genome = genome;
    }

    public String getAlternativeLocusTag() {
        return alternativeLocusTag;
    }

    public void setAlternativeLocusTag(String alternativeLocusTag) {
        this.alternativeLocusTag = alternativeLocusTag;
    }

    public String getHmmProfile() {
        return hmmProfile;
    }

    public void setHmmProfile(String hmmProfile) {
        this.hmmProfile = hmmProfile;
    }

    public Integer getSearchSpace() {
        return searchSpace;
    }

    public void setSearchSpace(Integer searchSpace) {
        this.searchSpace = searchSpace;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public PromoterRegion getPromoterRegion() {
        return promoterRegion;
    }

    public void setPromoterRegion(PromoterRegion promoterRegion) {
        this.promoterRegion = promoterRegion;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHmmLogo() {
        return hmmLogo;
    }

    public void setHmmLogo(String hmmLogo) {
        this.hmmLogo = hmmLogo;
    }

    @Override
    public String toString() {
        return "Gene{" + "id=" + id + ", locusTag=" + locusTag + ", proteinId=" + proteinId + ", startPosition=" + startPosition + ", endPosition=" + endPosition + ", name=" + name + ", product=" + product + ", proteinSequence=" + proteinSequence + ", nucleotideSequence=" + nucleotideSequence + ", genome=" + genome + ", alternativeLocusTag=" + alternativeLocusTag + ", hmmProfile=" + hmmProfile + ", searchSpace=" + searchSpace + ", orientation=" + orientation + ", promoterRegion=" + promoterRegion + ", role=" + role + ", hmmLogo=" + hmmLogo + ", bsNumber=" + bsNumber + ", profileLength=" + profileLength + ", isSigma=" + isSigma + '}';
    }

    public String toString2() {
        return "Gene{" + "id=" + id + ", locusTag=" + locusTag + ", alternativeLocusTag=" + alternativeLocusTag + ", role=" + role + ", proteinId=" + proteinId + ", startPosition=" + startPosition + ", endPosition=" + endPosition + ", name=" + name + ", product=" + product + ", alternativeLocusTag=" + alternativeLocusTag + ", hmmProfile=" + hmmProfile + ", searchSpace=" + searchSpace + ", orientation=" + orientation + ", hmmLogo=" + hmmLogo + ", bsNumber=" + bsNumber + ", profileLength=" + profileLength + ", isSigma=" + isSigma + '}';
    }

    public Integer getProfileLength() {
        return profileLength;
    }

    public void setProfileLength(Integer profileLength) {
        this.profileLength = profileLength;
    }

    public Boolean getIsSigma() {
        return isSigma;
    }

    public void setIsSigma(Boolean isSigma) {
        this.isSigma = isSigma;
    }

}
