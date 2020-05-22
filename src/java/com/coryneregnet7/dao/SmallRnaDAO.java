/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.Genome;
import com.coryneregnet7.model.SmallRna;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariana
 */
public class SmallRnaDAO extends GenericDAO {

    public SmallRna findById(Integer id) {
        try {
            SmallRna run = new SmallRna();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.findById");
            query.setInteger("id", id);
            run = (SmallRna) query.uniqueResult();
            this.tx.commit();
            return run;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<SmallRna> listAll() {
        try {
            SmallRna gene = new SmallRna();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.findAll");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    //bringGenomes
    public List<Genome> bringGenomes() {
        try {
            SmallRna gene = new SmallRna();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.bringGenomes");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    //bringGenomes
    public List<Genome> bringGenomesByType(String type) {
        try {
            SmallRna gene = new SmallRna();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.bringGenomesByType");
            query.setString("type", type);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<SmallRna> findByGenome(Integer genome) {
        try {
            SmallRna gene = new SmallRna();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.findByGenome");
            query.setInteger("genome", genome);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    //findByGenomeSourceRna
    public SmallRna findByGenomeSourceRna(Integer genome, Integer sourceRna) {
        try {
            SmallRna run = new SmallRna();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.findByGenomeSourceRna");
            query.setInteger("genome", genome);
            query.setInteger("sourceRna", sourceRna);
            run = (SmallRna) query.uniqueResult();
            this.tx.commit();
            return run;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    //findForRNAalifold
    public List<SmallRna> findForRNAalifold(String sequence, String evidence, Integer genome) {
        try {
            SmallRna gene = new SmallRna();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.findForRNAalifold");
            query.setString("sequence", sequence);
            query.setString("evidence", evidence);
            query.setInteger("genome", genome);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    //findByGenomeType
    public List<SmallRna> findByGenomeType(Integer genome, String type) {
        try {
            SmallRna gene = new SmallRna();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.findByGenomeType");
            query.setInteger("genome", genome);
            query.setString("type", type);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<SmallRna> findByType(String type) {
        try {
            SmallRna gene = new SmallRna();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.findByType");
            query.setString("type", type);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    //findByLocusTag
    public SmallRna findByLocusTag(String locusTag) {
        try {
            SmallRna run = new SmallRna();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.findByLocusTag");
            query.setString("locusTag", locusTag);
            run = (SmallRna) query.uniqueResult();
            this.tx.commit();
            return run;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    //findByLocusTagAndOrganism
    public SmallRna findByLocusTagAndOrganism(Integer organism, String locusTag) {
        try {
            SmallRna run = new SmallRna();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.findByLocusTagAndOrganism");
            query.setString("locusTag", locusTag);
            query.setInteger("organism", organism);
            run = (SmallRna) query.uniqueResult();
            this.tx.commit();
            return run;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public SmallRna findRepeated(Integer startPosition, Integer endPosition, String orientation, Integer genome) {
        try {
            SmallRna run = new SmallRna();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.findRepeated");
            query.setInteger("startPosition", startPosition);
            query.setInteger("endPosition", endPosition);
            query.setString("orientation", orientation);
            query.setInteger("genome", genome);
            run = (SmallRna) query.uniqueResult();
            this.tx.commit();
            return run;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public SmallRna findRepeatedPos(Integer startPosition, Integer endPosition, Integer genome) {
        try {
            SmallRna run = new SmallRna();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.findRepeatedPos");
            query.setInteger("startPosition", startPosition);
            query.setInteger("endPosition", endPosition);
            query.setInteger("genome", genome);
            run = (SmallRna) query.uniqueResult();
            this.tx.commit();
            return run;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    //bringByType
    public Long bringByType(String type) {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.bringByType");
            query.setString("type", type);
            Long num = (Long) query.uniqueResult();
            this.tx.commit();
            return num;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    //bringByNotType
    public Long bringByNotType(String type) {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.bringByNotType");
            query.setString("type", type);
            Long num = (Long) query.uniqueResult();
            this.tx.commit();
            return num;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    //bringFunctionalByType
    public Long bringFunctionalByType(String type, Boolean functionalRna) {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.bringFunctionalByType");
            query.setString("type", type);
            query.setBoolean("functionalRna", functionalRna);
            Long num = (Long) query.uniqueResult();
            this.tx.commit();
            return num;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    //bringFunctionalByTypeGenome
    public Long bringFunctionalByTypeGenome(String type, Boolean functionalRna, Integer genome) {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.bringFunctionalByTypeGenome");
            query.setString("type", type);
            query.setBoolean("functionalRna", functionalRna);
            query.setInteger("genome", genome);
            Long num = (Long) query.uniqueResult();
            this.tx.commit();
            return num;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    //bringFunctionalByNotTypeGenome
    public Long bringFunctionalByNotType(String type, Boolean functionalRna) {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.bringFunctionalByNotType");
            query.setString("type", type);
            query.setBoolean("functionalRna", functionalRna);
            Long num = (Long) query.uniqueResult();
            this.tx.commit();
            return num;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    //bringFunctionalByNotTypeGenome
    public Long bringFunctionalByNotTypeGenome(String type, Boolean functionalRna, Integer genome) {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("SmallRna.bringFunctionalByNotTypeGenome");
            query.setString("type", type);
            query.setBoolean("functionalRna", functionalRna);
            query.setInteger("genome", genome);
            Long num = (Long) query.uniqueResult();
            this.tx.commit();
            return num;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
}
