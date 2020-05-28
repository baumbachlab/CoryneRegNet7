package com.coryneregnet7.model;

import com.coryneregnet7.model.Gene;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-28T11:06:03")
@StaticMetamodel(Job.class)
public class Job_ { 

    public static volatile SingularAttribute<Job, Integer> organismId;
    public static volatile SingularAttribute<Job, Gene> gene;
    public static volatile SingularAttribute<Job, Integer> bsSearchType;
    public static volatile SingularAttribute<Job, Integer> selectedOrganism;
    public static volatile SingularAttribute<Job, String> evalue;
    public static volatile SingularAttribute<Job, Integer> id;
    public static volatile SingularAttribute<Job, Integer> usedHmms;
    public static volatile SingularAttribute<Job, String> type;
    public static volatile SingularAttribute<Job, Integer> minHmm;
    public static volatile SingularAttribute<Job, String> status;

}