/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

import com.coryneregnet7.model.GenesRegulatedByTfSrnaView;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;


/**
 *
 * @author mariana
 */
public class GenesRegulatedByTfSrnaViewDAO extends GenericDAO {

    public List<GenesRegulatedByTfSrnaView> listAll() {
        try {
            GenesRegulatedByTfSrnaView gene = new GenesRegulatedByTfSrnaView();
            List lista = new ArrayList<Object>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("GenesRegulatedByTfSrnaView.findAll");
            lista = query.list();
            this.tx.commit();
            return lista;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }
    
        public List<GenesRegulatedByTfSrnaView> findByGenome(Integer genome) {
        try {
            List list = new ArrayList<>();
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            Query query = session.getNamedQuery("GenesRegulatedByTfSrnaView.findByGenome");
            query.setInteger("genome", genome);
            list = query.list();
            this.tx.commit();
            return list;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    
}
