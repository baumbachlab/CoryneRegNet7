/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 *
 * @author mariana
 */
@Entity
@Immutable
@Table(name = "genes_regulated_by_tf_srna_view")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GenesRegulatedByTfSrnaView.findAll", query = "SELECT o FROM GenesRegulatedByTfSrnaView o order by o.genome")
    , @NamedQuery(name = "GenesRegulatedByTfSrnaView.findByGenome", query = "SELECT o FROM GenesRegulatedByTfSrnaView o WHERE o.genome = :genome ORDER BY o.genome")

})
public class GenesRegulatedByTfSrnaView implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "tf")
    private Integer tf;
    @Column(name = "srna")
    private Integer srna;
    @Column(name = "genome")
    private Integer genome;

//reg_rna
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the genome
     */
    public Integer getGenome() {
        return genome;
    }

    /**
     * @param genome the genome to set
     */
    public void setGenome(Integer genome) {
        this.genome = genome;
    }

    /**
     * @return the tf
     */
    public Integer getTf() {
        return tf;
    }

    /**
     * @param tf the tf to set
     */
    public void setTf(Integer tf) {
        this.tf = tf;
    }

    /**
     * @return the srna
     */
    public Integer getSrna() {
        return srna;
    }

    /**
     * @param srna the srna to set
     */
    public void setSrna(Integer srna) {
        this.srna = srna;
    }


}
