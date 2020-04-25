package com.coryneregnet7.model;

import com.coryneregnet7.model.Gene;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-13T14:37:58")
@StaticMetamodel(Homologous.class)
public class Homologous_ { 

    public static volatile SingularAttribute<Homologous, Gene> geneOne;
    public static volatile SingularAttribute<Homologous, BigDecimal> evalue;
    public static volatile SingularAttribute<Homologous, BigDecimal> pvalue;
    public static volatile SingularAttribute<Homologous, Integer> id;
    public static volatile SingularAttribute<Homologous, Gene> geneTwo;

}