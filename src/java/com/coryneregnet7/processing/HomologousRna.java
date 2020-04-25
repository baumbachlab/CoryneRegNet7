/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing;

/**
 *
 * @author mariana
 */
public class HomologousRna {
    
    private String ncId;
    private String strainName;
    private String label;
    private Float alignment;
    private String sequence;

    public HomologousRna() {
    }

    public HomologousRna(String ncId, String strainName, Float alignment, String sequence, String label) {
        this.ncId = ncId;
        this.strainName = strainName;
        this.alignment = alignment;
        this.sequence = sequence;
        this.label = label;
    }

    public String getNcId() {
        return ncId;
    }

    public void setNcId(String ncId) {
        this.ncId = ncId;
    }

    public String getStrainName() {
        return strainName;
    }

    public void setStrainName(String strainName) {
        this.strainName = strainName;
    }

    public Float getAlignment() {
        return alignment;
    }

    public void setAlignment(Float alignment) {
        this.alignment = alignment;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "HomologousRna{" + "ncId=" + ncId + ", strainName=" + strainName + ", label=" + label + ", alignment=" + alignment + ", sequence=" + sequence + '}';
    }

   
    
}
