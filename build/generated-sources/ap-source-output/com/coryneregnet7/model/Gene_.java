package com.coryneregnet7.model;

import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.PromoterRegion;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-28T11:06:03")
@StaticMetamodel(Gene.class)
public class Gene_ { 

    public static volatile SingularAttribute<Gene, String> hmmProfile;
    public static volatile SingularAttribute<Gene, String> product;
    public static volatile SingularAttribute<Gene, String> orientation;
    public static volatile SingularAttribute<Gene, String> proteinId;
    public static volatile SingularAttribute<Gene, String> role;
    public static volatile SingularAttribute<Gene, Integer> bsNumber;
    public static volatile SingularAttribute<Gene, PromoterRegion> promoterRegion;
    public static volatile SingularAttribute<Gene, BigInteger> startPosition;
    public static volatile SingularAttribute<Gene, Integer> searchSpace;
    public static volatile SingularAttribute<Gene, Genome> genome;
    public static volatile SingularAttribute<Gene, String> nucleotideSequence;
    public static volatile SingularAttribute<Gene, String> locusTag;
    public static volatile SingularAttribute<Gene, BigInteger> endPosition;
    public static volatile SingularAttribute<Gene, Boolean> isSigma;
    public static volatile SingularAttribute<Gene, String> name;
    public static volatile SingularAttribute<Gene, String> proteinSequence;
    public static volatile SingularAttribute<Gene, String> alternativeLocusTag;
    public static volatile SingularAttribute<Gene, Integer> id;
    public static volatile SingularAttribute<Gene, Integer> profileLength;
    public static volatile SingularAttribute<Gene, String> hmmLogo;

}