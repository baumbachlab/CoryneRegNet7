/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.RegulatoryInteractionView;
import org.hibernate.Query;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mariana
 */
public class RegulatoryInteractionViewDAO extends GenericDAO {

    public List<RegulatoryInteractionView> listAll() {
        try {
            RegulatoryInteractionView genome = new RegulatoryInteractionView();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteractionView.findAll");
            //Query query = session.createQuery("SELECT o FROM RegulatoryInteractionView o");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public List<RegulatoryInteractionView> findByGenome(Integer genome) {
        try {
            List list = new ArrayList<>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteractionView.findByGenome");
            query.setInteger("genome", genome);
            list = query.list();
            this.tx.commit();
            return list;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
    
    public List<RegulatoryInteractionView> findByGene(Integer gene) {
        try {
            List list = new ArrayList<>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("RegulatoryInteractionView.findByGene");
            query.setInteger("gene", gene);
            list = query.list();
            this.tx.commit();
            return list;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }


}
