/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.input;

import com.coryneregnet7.dao.RnaInteractionDAO;
import com.coryneregnet7.model.RnaInteraction;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

/**
 *
 * @author mariana
 */
public class ReadPajust {

    public static void main(String[] args) {
        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader("/data/home/mariana/Dropbox/Doutorado/ncRNA/pjust/ri_all.csv");
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                if (!sCurrentLine.startsWith("id")) {
                    String[] splitedLine = sCurrentLine.split(",");
                    String id = splitedLine[0];
                    String pajust = splitedLine[3];
                    System.out.println(id + " -> " + pajust);
                    RnaInteraction ri = new RnaInteraction();
                    RnaInteractionDAO riDAO = new RnaInteractionDAO();
                    ri = riDAO.findById(Integer.parseInt(id));
                    System.out.println(ri.toString2());
                    
                    ri.setAjustedPvalue(new BigDecimal(pajust));
                    riDAO.update(ri);
                    
                }
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
    }

}
