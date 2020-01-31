package com.coryneregnet7.model;

import com.coryneregnet7.model.Genome;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-01-08T14:32:28")
@StaticMetamodel(Organism.class)
public class Organism_ { 

    public static volatile SingularAttribute<Organism, String> strain;
    public static volatile ListAttribute<Organism, Genome> genomeList;
    public static volatile SingularAttribute<Organism, String> genera;
    public static volatile SingularAttribute<Organism, String> species;
    public static volatile SingularAttribute<Organism, String> taxonomyId;
    public static volatile SingularAttribute<Organism, Integer> id;
    public static volatile SingularAttribute<Organism, String> type;

}