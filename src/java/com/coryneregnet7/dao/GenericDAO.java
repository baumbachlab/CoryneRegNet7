/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.dao;

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
public class GenericDAO {
    
    Transaction tx;
    private ConnectionFactory connectionFactory;
    Session session;

    public Session getSession() throws HibernateException {
        this.connectionFactory = new ConnectionFactory();
        this.session = this.connectionFactory.getSessionFactory();
        return session;
    }
    
    public Object save(Object object) {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            this.session.save(object);
            tx.commit();
            return object;
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
            return null;
        }
    }

    public void update(Object object) {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            this.session.update(object);
            this.tx.commit();
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
        }
    }

    

    public void delete(Object object) {
        try {
            this.session = getSession();
            this.tx = this.session.beginTransaction();
            this.session.delete(object);
            this.tx.commit();
        } catch (Exception E) {
            System.out.println("Exception = " + E.toString());
        }
    }
    
}
