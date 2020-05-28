package com.coryneregnet7.model;

import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Regulation;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-28T11:06:03")
@StaticMetamodel(RegulatoryInteraction.class)
public class RegulatoryInteraction_ { 

    public static volatile SingularAttribute<RegulatoryInteraction, String> role;
    public static volatile SingularAttribute<RegulatoryInteraction, Regulation> regulation;
    public static volatile SingularAttribute<RegulatoryInteraction, String> evidence;
    public static volatile SingularAttribute<RegulatoryInteraction, Integer> id;
    public static volatile SingularAttribute<RegulatoryInteraction, String> source;
    public static volatile SingularAttribute<RegulatoryInteraction, Gene> correspondentTargetGene;
    public static volatile SingularAttribute<RegulatoryInteraction, String> pmid;
    public static volatile SingularAttribute<RegulatoryInteraction, Gene> correspondentTranscriptionFactor;

}