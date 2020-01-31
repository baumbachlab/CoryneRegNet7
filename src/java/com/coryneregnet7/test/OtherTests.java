/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.dao.GenomeDAO;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.processing.HomologyDetection;
import com.coryneregnet7.processing.TfTgPair;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author doglas
 */
public class OtherTests {

    public static void main(String[] args) {
         HomologyDetection hd = new HomologyDetection();
        GenomeDAO genomeDAO = new GenomeDAO();
        Genome genomeOne = genomeDAO.findById(1010);
        Genome genomeTwo = genomeDAO.findById(1011);
       List<TfTgPair> homologousPairs = hd.bringTfTgPairs(genomeOne, genomeTwo);
        
    }
}
