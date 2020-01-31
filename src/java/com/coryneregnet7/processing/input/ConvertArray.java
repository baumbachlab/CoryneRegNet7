/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.input;

/**
 *
 * @author mariana
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ananddw
 *
 */
public class ConvertArray {

    public static void main(String myHelpers[]) {
        ConvertArray c = new ConvertArray();
        String jsonArrayString = "{\"fileName\": [{\"name\": \"Anand\",\"last\": \"Dwivedi\",\"place\": \"Bangalore\"}]}";

        JSONObject output;
        try {
            output = new JSONObject(jsonArrayString);

            JSONArray docs = output.getJSONArray("fileName");

            File file = new File("JSONSEPERATOR.csv");
            String csv = CDL.toString(docs);
            FileUtils.writeStringToFile(file, csv);
            System.out.println("Data has been Sucessfully Writeen to " + file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getJsonString() {
            BufferedReader br = null;
        FileReader fr = null;
        String json="";

        try {

            fr = new FileReader("/home/mariana/Dropbox/Doutorado/CoryneRegNet7/C-glutamicum-regulations/regNet-genes-modules-2016-17-weak-strong/regNetwork-just-edges.txt");
            br = new BufferedReader(fr);

            String sCurrentLine;
            
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                json = json+sCurrentLine;
                        
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
        return json;
    }

}
