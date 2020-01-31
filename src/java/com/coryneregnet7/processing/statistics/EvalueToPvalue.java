/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.statistics;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *
 * @author mariana
 */
public class EvalueToPvalue {
    
    public static void main(String args[]){
        
    }
    
    public BigDecimal convert(BigDecimal evalue, BigDecimal searchSpace){
        BigDecimal pvalue = evalue.divide(searchSpace, MathContext.DECIMAL32);
        //(b, 2, RoundingMode.HALF_UP)
        return pvalue;
    }
    
}
