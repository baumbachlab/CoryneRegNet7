package com.coryneregnet7.model;

import com.coryneregnet7.model.Organism;
import com.coryneregnet7.model.RefStatistics;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-02-13T14:37:58")
@StaticMetamodel(Genome.class)
public class Genome_ { 

    public static volatile SingularAttribute<Genome, String> ncbiAccessionNumber;
    public static volatile SingularAttribute<Genome, Organism> organism;
    public static volatile SingularAttribute<Genome, BigInteger> size;
    public static volatile ListAttribute<Genome, RefStatistics> refStatisticsList;
    public static volatile SingularAttribute<Genome, String> genomeName;
    public static volatile SingularAttribute<Genome, String> faaFile;
    public static volatile SingularAttribute<Genome, Integer> id;
    public static volatile SingularAttribute<Genome, String> fnaFile;
    public static volatile SingularAttribute<Genome, String> fastaFile;
    public static volatile SingularAttribute<Genome, Integer> searchSpace;
    public static volatile SingularAttribute<Genome, String> gbffFile;

}