package com.coryneregnet7.model;

import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.Organism;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-01-08T14:32:28")
@StaticMetamodel(PredictedRegulatoryInteractionView.class)
public class PredictedRegulatoryInteractionView_ { 

    public static volatile SingularAttribute<PredictedRegulatoryInteractionView, String> tfLocusTag;
    public static volatile SingularAttribute<PredictedRegulatoryInteractionView, String> role;
    public static volatile SingularAttribute<PredictedRegulatoryInteractionView, Organism> organism;
    public static volatile SingularAttribute<PredictedRegulatoryInteractionView, String> pvalue;
    public static volatile SingularAttribute<PredictedRegulatoryInteractionView, String> operon;
    public static volatile SingularAttribute<PredictedRegulatoryInteractionView, Genome> genome;
    public static volatile SingularAttribute<PredictedRegulatoryInteractionView, Gene> tf;
    public static volatile SingularAttribute<PredictedRegulatoryInteractionView, Gene> tg;
    public static volatile SingularAttribute<PredictedRegulatoryInteractionView, String> bindingSite;
    public static volatile SingularAttribute<PredictedRegulatoryInteractionView, String> pos;
    public static volatile SingularAttribute<PredictedRegulatoryInteractionView, Gene> sourceTg;
    public static volatile SingularAttribute<PredictedRegulatoryInteractionView, Integer> id;
    public static volatile SingularAttribute<PredictedRegulatoryInteractionView, Gene> sourceTf;
    public static volatile SingularAttribute<PredictedRegulatoryInteractionView, String> tgLocusTag;
    public static volatile SingularAttribute<PredictedRegulatoryInteractionView, Integer> regulatorDensity;

}