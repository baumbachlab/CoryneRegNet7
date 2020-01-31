/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.RunGenome;
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
public class RunGenomeDAO extends GenericDAO {

    public RunGenome findByIdRunGenome(Integer idRun, Integer idGenome) {
        try {
            RunGenome runGenome = new RunGenome();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RunGenome.findByIdRunGenome");
            query.setInteger("idRun", idRun);
            query.setInteger("idGenome", idGenome);

            runGenome = (RunGenome) query.uniqueResult();
            this.tx.commit();
            return runGenome;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

    public List<RunGenome> listAll() {
        try {
            RunGenome gene = new RunGenome();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RunGenome.findAll");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
    
    public List<RunGenome> findByRunRole(Integer idRun, String role) {
        try {
            RunGenome gene = new RunGenome();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RunGenome.findByRunRole");
             query.setInteger("idRun", idRun);
            query.setString("role", role);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

}
