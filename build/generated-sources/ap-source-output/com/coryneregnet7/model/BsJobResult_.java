package com.coryneregnet7.model;

import com.coryneregnet7.model.Gene;
import com.coryneregnet7.model.Job;
import com.coryneregnet7.model.Operon;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-13T14:37:58")
@StaticMetamodel(BsJobResult.class)
public class BsJobResult_ { 

    public static volatile SingularAttribute<BsJobResult, String> sequence;
    public static volatile SingularAttribute<BsJobResult, String> homologousTfTemplate;
    public static volatile SingularAttribute<BsJobResult, String> homologousTgTarget;
    public static volatile SingularAttribute<BsJobResult, String> alreadyKnown;
    public static volatile SingularAttribute<BsJobResult, Operon> predictedOperon;
    public static volatile SingularAttribute<BsJobResult, String> evalue;
    public static volatile SingularAttribute<BsJobResult, Integer> id;
    public static volatile SingularAttribute<BsJobResult, Gene> hmmFrom;
    public static volatile SingularAttribute<BsJobResult, Job> job;
    public static volatile SingularAttribute<BsJobResult, Gene> targetGene;

}