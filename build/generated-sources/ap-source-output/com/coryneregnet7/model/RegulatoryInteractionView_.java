package com.coryneregnet7.model;

import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Organism;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-13T14:37:58")
@StaticMetamodel(RegulatoryInteractionView.class)
public class RegulatoryInteractionView_ { 

    public static volatile SingularAttribute<RegulatoryInteractionView, String> tfLocusTag;
    public static volatile SingularAttribute<RegulatoryInteractionView, String> role;
    public static volatile SingularAttribute<RegulatoryInteractionView, Organism> organism;
    public static volatile SingularAttribute<RegulatoryInteractionView, String> evidence;
    public static volatile SingularAttribute<RegulatoryInteractionView, String> pmid;
    public static volatile SingularAttribute<RegulatoryInteractionView, String> operon;
    public static volatile SingularAttribute<RegulatoryInteractionView, Genome> genome;
    public static volatile SingularAttribute<RegulatoryInteractionView, Gene> tf;
    public static volatile SingularAttribute<RegulatoryInteractionView, Gene> tg;
    public static volatile SingularAttribute<RegulatoryInteractionView, String> bindingSite;
    public static volatile SingularAttribute<RegulatoryInteractionView, String> pos;
    public static volatile SingularAttribute<RegulatoryInteractionView, Integer> id;
    public static volatile SingularAttribute<RegulatoryInteractionView, Integer> regulatorDensity;
    public static volatile SingularAttribute<RegulatoryInteractionView, String> tgLocusTag;

}