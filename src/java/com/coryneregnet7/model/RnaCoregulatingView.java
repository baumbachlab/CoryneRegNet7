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
@Table(name = "rna_coregulating_view")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RnaCoregulatingView.findAll", query = "SELECT o FROM RnaCoregulatingView o order by o.genome")
    , @NamedQuery(name = "RnaCoregulatingView.findByGenome", query = "SELECT o FROM RnaCoregulatingView o WHERE o.genome = :genome ORDER BY o.genome")
})
public class RnaCoregulatingView implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "count")
    private Integer count;
    @Column(name = "genome")
    private Integer genome;
    @Column(name = "co_reg_rnas")
    private Integer coRegRnas;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCoRegRnas() {
        return coRegRnas;
    }

    public void setCoRegRnas(Integer coRegRnas) {
        this.coRegRnas = coRegRnas;
    }

    @Override
    public String toString() {
        return "RnaCoregulatingView{" + "id=" + id + ", count=" + count + ", genome=" + genome + ", coRegRnas=" + coRegRnas + '}';
    }

}
