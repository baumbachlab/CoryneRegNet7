/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing;

import com.coryneregnet7.model.Gene;

/**
 *
 * @author mariana
 */
public class BindingSitePredictionInfo {

    private String evalue;
    private String bitscore;
    private String foundBindingSite;
    private String startPosition;
    private String endPosition;
    private Gene putativeTF;
    private Gene putativeTG;
    private Integer startSiteDistance;


    public BindingSitePredictionInfo() {
    }

    public String getEvalue() {
        return evalue;
    }

    public void setEvalue(String evalue) {
        this.evalue = evalue;
    }

    public String getBitscore() {
        return bitscore;
    }

    public void setBitscore(String bitscore) {
        this.bitscore = bitscore;
    }

    public String getFoundBindingSite() {
        return foundBindingSite;
    }

    public void setFoundBindingSite(String foundBindingSite) {
        this.foundBindingSite = foundBindingSite;
    }

    public String getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(String startPosition) {
        this.startPosition = startPosition;
    }

    public String getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(String endPosition) {
        this.endPosition = endPosition;
    }

    public Gene getPutativeTF() {
        return putativeTF;
    }

    public void setPutativeTF(Gene putativeTF) {
        this.putativeTF = putativeTF;
    }

    public Gene getPutativeTG() {
        return putativeTG;
    }

    public void setPutativeTG(Gene putativeTG) {
        this.putativeTG = putativeTG;
    }

    @Override
    public String toString() {
        return "BindingSitePredictionInfo{" + "evalue=" + evalue + ", bitscore=" + bitscore + ", foundBindingSite=" + foundBindingSite + ", startPosition=" + startPosition + ", endPosition=" + endPosition + ", putativeTF=" + putativeTF + ", putativeTG=" + putativeTG + '}';
    }

    public Integer getStartSiteDistance() {
        return startSiteDistance;
    }

    public void setStartSiteDistance(Integer startSiteDistance) {
        this.startSiteDistance = startSiteDistance;
    }
    
}
