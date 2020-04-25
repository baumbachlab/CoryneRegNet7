package com.coryneregnet7.model;

import com.coryneregnet7.model.Genome;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-13T14:37:58")
@StaticMetamodel(StatisticsOverview.class)
public class StatisticsOverview_ { 

    public static volatile SingularAttribute<StatisticsOverview, Genome> genome;
    public static volatile SingularAttribute<StatisticsOverview, String> database;
    public static volatile SingularAttribute<StatisticsOverview, Integer> genes;
    public static volatile SingularAttribute<StatisticsOverview, Integer> proteins;
    public static volatile SingularAttribute<StatisticsOverview, Integer> regulations;
    public static volatile SingularAttribute<StatisticsOverview, Integer> regulatedGenes;
    public static volatile SingularAttribute<StatisticsOverview, Integer> id;
    public static volatile SingularAttribute<StatisticsOverview, String> type;
    public static volatile SingularAttribute<StatisticsOverview, Integer> hmmProfiles;
    public static volatile SingularAttribute<StatisticsOverview, Integer> regulators;
    public static volatile SingularAttribute<StatisticsOverview, Integer> numGenomes;
    public static volatile SingularAttribute<StatisticsOverview, Integer> bindingMotifs;

}