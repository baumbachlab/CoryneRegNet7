/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.test;

import com.coryneregnet7.model.Organism;
import com.coryneregnet7.dao.OrganismDAO;
import java.util.List;

/**
 *
 * @author mariana
 */
public class OrganismTest {

    OrganismDAO orgainsmDAO;

    public static void main(String[] args) {

        OrganismTest pt = new OrganismTest();
        OrganismDAO oDAO = new OrganismDAO();
        List<Organism> organismos = oDAO.findByName("Nobacterium", "%", "%", "target");
        for (Organism organismo : organismos) {
            System.out.println(organismo);
        }
                


    }

    public Organism save(Organism organism) {
        orgainsmDAO = new OrganismDAO();
        Organism o = null;
        try {
            o = (Organism)orgainsmDAO.save(organism);
        } catch (Exception E) {
            System.out.println(E);
        }
        return o;
    }

    public Organism findById(Integer id) {
        orgainsmDAO = new OrganismDAO();
        Organism organism =  orgainsmDAO.findById(id);
        return organism;
    }

    public void update(Organism organism) {
        orgainsmDAO = new OrganismDAO();
        orgainsmDAO.update(organism);
    }

    public void listAll() {
        orgainsmDAO = new OrganismDAO();
        List<Organism> generics = orgainsmDAO.listAll();
        for (Organism generic : generics) {
            System.out.println(generic.toString());
        }

    }

    public void delete() {
        orgainsmDAO = new OrganismDAO();
        Organism generic = (Organism) orgainsmDAO.findById(1);
        orgainsmDAO.delete(generic);
    }
}
