package com.coryneregnet7.model;

import com.coryneregnet7.model.Gene;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-01-08T14:32:28")
@StaticMetamodel(GeneInfoView.class)
public class GeneInfoView_ { 

    public static volatile SingularAttribute<GeneInfoView, String> genome;
    public static volatile SingularAttribute<GeneInfoView, String> locusTag;
    public static volatile SingularAttribute<GeneInfoView, Gene> gene;
    public static volatile SingularAttribute<GeneInfoView, String> geneName;
    public static volatile SingularAttribute<GeneInfoView, String> operonId;
    public static volatile SingularAttribute<GeneInfoView, String> operonName;
    public static volatile SingularAttribute<GeneInfoView, Integer> operonPos;
    public static volatile SingularAttribute<GeneInfoView, Integer> id;

}