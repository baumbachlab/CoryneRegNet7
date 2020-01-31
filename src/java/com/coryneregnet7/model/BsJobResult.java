/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.model;

import com.coryneregnet7.controller.BindingSiteRegulationPrediction;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mariana
 */
@Entity
@Table(name = "bs_job_result")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BsJobResult.findAll", query = "SELECT r FROM BsJobResult r")
    ,@NamedQuery(name = "BsJobResult.findById", query = "SELECT r FROM BsJobResult r WHERE r.id = :id")
    ,@NamedQuery(name = "BsJobResult.findByJob", query = "SELECT r FROM BsJobResult r WHERE r.job = :job")     
})
public class BsJobResult implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    // hmm_from integer,
    @JoinColumn(name = "hmm_from", referencedColumnName = "id")
    @ManyToOne
    private Gene hmmFrom;
    //target_gene integer,
    @JoinColumn(name = "target_gene", referencedColumnName = "id")
    @ManyToOne
    private Gene targetGene;
    //predicted_operon integer,
    
    @JoinColumn(name = "predicted_operon", referencedColumnName = "id")
    @ManyToOne    
    //@ManyToOne(cascade = CascadeType.ALL)
    private Operon predictedOperon;
    //evalue character varying,
    @Size(max = 2147483647)
    @Column(name = "evalue")
    private String evalue;
    //sequence character varying,
    @Size(max = 2147483647)
    @Column(name = "sequence")
    private String sequence;
    //homologous_tf_template character varying,
    @Size(max = 2147483647)
    @Column(name = "homologous_tf_template")
    private String homologousTfTemplate;
    //homologous_tg_target character varying,
    @Size(max = 2147483647)
    @Column(name = "homologous_tg_target")
    private String homologousTgTarget;
    //already_known character varying,
    @Size(max = 2147483647)
    @Column(name = "already_known")
    private String alreadyKnown;
     @JoinColumn(name = "job", referencedColumnName = "id")
    @ManyToOne
    private Job job;

    public BsJobResult() {
    }

    public BsJobResult(Integer id) {
        this.id = id;
    }
    
    public BsJobResult(BindingSiteRegulationPrediction bsRegulation){
        this.alreadyKnown = bsRegulation.getKnowedRegulation();
        this.evalue = bsRegulation.getEvalue();
        this.homologousTfTemplate = bsRegulation.getHomologousTFs();
        this.homologousTgTarget = bsRegulation.getHomologousTGs();
        if(bsRegulation.getOperon().getId()!=null){
            this.predictedOperon = bsRegulation.getOperon();
        }else{
            this.predictedOperon=null;
        }
        this.sequence = bsRegulation.getSequence();
        this.targetGene = bsRegulation.getTargetGene();
        this.hmmFrom = bsRegulation.getTranscriptionFactor();
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Gene getHmmFrom() {
        return hmmFrom;
    }

    public void setHmmFrom(Gene hmmFrom) {
        this.hmmFrom = hmmFrom;
    }

    public Gene getTargetGene() {
        return targetGene;
    }

    public void setTargetGene(Gene targetGene) {
        this.targetGene = targetGene;
    }

    public Operon getPredictedOperon() {
        return predictedOperon;
    }

    public void setPredictedOperon(Operon predictedOperon) {
        this.predictedOperon = predictedOperon;
    }

    public String getEvalue() {
        return evalue;
    }

    public void setEvalue(String evalue) {
        this.evalue = evalue;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getHomologousTfTemplate() {
        return homologousTfTemplate;
    }

    public void setHomologousTfTemplate(String homologousTfTemplate) {
        this.homologousTfTemplate = homologousTfTemplate;
    }

    public String getHomologousTgTarget() {
        return homologousTgTarget;
    }

    public void setHomologousTgTarget(String homologousTgTarget) {
        this.homologousTgTarget = homologousTgTarget;
    }

    public String getAlreadyKnown() {
        return alreadyKnown;
    }

    public void setAlreadyKnown(String alreadyKnown) {
        this.alreadyKnown = alreadyKnown;
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
        if (!(object instanceof BsJobResult)) {
            return false;
        }
        BsJobResult other = (BsJobResult) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BsJobResult{" + "id=" + id + ", hmmFrom=" + hmmFrom.getLocusTag() + ", targetGene=" + targetGene.getLocusTag() + ", predictedOperon=" + predictedOperon 
                + ", evalue=" + evalue + ", sequence=" + sequence + ", homologousTfTemplate=" + homologousTfTemplate + ", homologousTgTarget=" + homologousTgTarget 
                + ", alreadyKnown=" + alreadyKnown + ", job=" + job + '}';
    }

    /**
     * @return the job
     */
    public Job getJob() {
        return job;
    }

    /**
     * @param job the job to set
     */
    public void setJob(Job job) {
        this.job = job;
    }

    
}
