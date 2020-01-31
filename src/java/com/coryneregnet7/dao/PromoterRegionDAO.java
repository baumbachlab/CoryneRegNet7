/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.PromoterRegion;
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
public class PromoterRegionDAO extends GenericDAO{
    
   
    public PromoterRegion findById(Integer id) {
        try {
            PromoterRegion promoterRegion = new PromoterRegion();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("PromoterRegion.findById");
            query.setInteger("id", id);
            promoterRegion = (PromoterRegion) query.uniqueResult();
            this.tx.commit();
            return promoterRegion;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }

      public List<PromoterRegion>  findByGenome(Integer genome) {
        try {
            PromoterRegion gene = new PromoterRegion();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("PromoterRegion.findByGenome");
            query.setInteger("genome", genome);
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }

    }
    
    public List<PromoterRegion> listAll() {
        try {
            PromoterRegion gene = new PromoterRegion();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("PromoterRegion.findAll");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    
}
