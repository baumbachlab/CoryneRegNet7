package com.coryneregnet7.model;

import com.coryneregnet7.model.Genome;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-13T14:37:58")
@StaticMetamodel(RegulatorsRegulations.class)
public class RegulatorsRegulations_ { 

    public static volatile SingularAttribute<RegulatorsRegulations, Genome> genome;
    public static volatile SingularAttribute<RegulatorsRegulations, String> database;
    public static volatile SingularAttribute<RegulatorsRegulations, Integer> numActivations;
    public static volatile SingularAttribute<RegulatorsRegulations, Integer> numRepressors;
    public static volatile SingularAttribute<RegulatorsRegulations, Integer> numDual;
    public static volatile SingularAttribute<RegulatorsRegulations, Integer> numRepressions;
    public static volatile SingularAttribute<RegulatorsRegulations, Integer> id;
    public static volatile SingularAttribute<RegulatorsRegulations, String> type;
    public static volatile SingularAttribute<RegulatorsRegulations, Integer> numActivators;

}