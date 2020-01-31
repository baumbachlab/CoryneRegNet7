package com.coryneregnet7.model;

import com.coryneregnet7.model.BindingSite;
import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.RegulatoryInteraction;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-01-08T14:32:28")
@StaticMetamodel(PredictedRegulatoryInteraction.class)
public class PredictedRegulatoryInteraction_ { 

    public static volatile SingularAttribute<PredictedRegulatoryInteraction, RegulatoryInteraction> regulatoryInteraction;
    public static volatile SingularAttribute<PredictedRegulatoryInteraction, BigDecimal> htgEvalue;
    public static volatile SingularAttribute<PredictedRegulatoryInteraction, BindingSite> predictedBindingSite;
    public static volatile SingularAttribute<PredictedRegulatoryInteraction, BigDecimal> htfPvalue;
    public static volatile SingularAttribute<PredictedRegulatoryInteraction, BigDecimal> interactionPvalue;
    public static volatile SingularAttribute<PredictedRegulatoryInteraction, Integer> id;
    public static volatile SingularAttribute<PredictedRegulatoryInteraction, Gene> homologousTranscriptionFactor;
    public static volatile SingularAttribute<PredictedRegulatoryInteraction, BigDecimal> htgPvalue;
    public static volatile SingularAttribute<PredictedRegulatoryInteraction, Gene> operonTargetGene;
    public static volatile SingularAttribute<PredictedRegulatoryInteraction, BigDecimal> htfEvalue;
    public static volatile SingularAttribute<PredictedRegulatoryInteraction, String> predictedRole;
    public static volatile SingularAttribute<PredictedRegulatoryInteraction, Gene> homologousTargetGene;

}