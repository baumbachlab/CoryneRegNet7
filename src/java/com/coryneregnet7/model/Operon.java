/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "operon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operon.findAll", query = "SELECT o FROM Operon o")
    , @NamedQuery(name = "Operon.findById", query = "SELECT o FROM Operon o WHERE o.id = :id")
    , @NamedQuery(name = "Operon.findByOrientation", query = "SELECT o FROM Operon o WHERE o.orientation = :orientation")
    , @NamedQuery(name = "Operon.findByName", query = "SELECT o FROM Operon o WHERE o.name = :operonName")
})
public class Operon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "orientation")
    private String orientation;
    @Size(max = 2147483647)
    @Column(name = "name")
    private String name;

    public Operon() {
    }

    public Operon(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof Operon)) {
            return false;
        }
        Operon other = (Operon) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Operon{" + "id=" + id + ", orientation=" + orientation + ", name=" + name + '}';
    }

}
