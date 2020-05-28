package com.coryneregnet7.model;

import com.coryneregnet7.model.RegulatoryInteraction;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-28T11:06:03")
@StaticMetamodel(BindingSite.class)
public class BindingSite_ { 

    public static volatile SingularAttribute<BindingSite, RegulatoryInteraction> regulatoryInteraction;
    public static volatile SingularAttribute<BindingSite, String> sequence;
    public static volatile SingularAttribute<BindingSite, BigDecimal> bitscore;
    public static volatile SingularAttribute<BindingSite, BigInteger> endPosition;
    public static volatile SingularAttribute<BindingSite, Integer> startSiteDistance;
    public static volatile SingularAttribute<BindingSite, BigDecimal> evalue;
    public static volatile SingularAttribute<BindingSite, BigDecimal> pvalue;
    public static volatile SingularAttribute<BindingSite, Integer> id;
    public static volatile SingularAttribute<BindingSite, String> type;
    public static volatile SingularAttribute<BindingSite, BigInteger> startPosition;

}