/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author mariana
 */
public class OperonAux {

    private String nameProteinId;
    private String nameLocusTag;
    private Character orientation;
    private ArrayList<String> genesProteinId;
    private ArrayList<String> genesLocusTag;

    public String getNameProteinId() {
        return nameProteinId;
    }

    public void setNameProteinId(String nameProteinId) {
        this.nameProteinId = nameProteinId;
    }

    public Character getOrientation() {
        return orientation;
    }

    public void setOrientation(Character orientation) {
        this.orientation = orientation;
    }

    public ArrayList<String> getGenesProteinId() {
        return genesProteinId;
    }

    public void setGenesProteinId(ArrayList<String> genesProteinId) {
        this.genesProteinId = genesProteinId;
    }

    public String getNameLocusTag() {
        return nameLocusTag;
    }

    public void setNameLocusTag(String nameLocusTag) {
        this.nameLocusTag = nameLocusTag;
    }

    public ArrayList<String> getGenesLocusTag() {
        return genesLocusTag;
    }

    public void setGenesLocusTag(ArrayList<String> genesLocusTag) {
        this.genesLocusTag = genesLocusTag;
    }

    public String toStringProteinId() {
        String line = "";
        line = ">OP_" + nameProteinId + "\t" + orientation;
        for (Iterator<String> iterator = genesProteinId.iterator(); iterator.hasNext();) {
            String next = iterator.next();
            line = line + "\t" + next;

        }
        return line;
    }

    public String toStringLocusTag() {
        String line = "";
        line = ">OP_" + nameLocusTag + "\t" + orientation;
        for (Iterator<String> iterator = genesLocusTag.iterator(); iterator.hasNext();) {
            String next = iterator.next();
            line = line + "\t" + next;

        }
        return line;
    }
}
