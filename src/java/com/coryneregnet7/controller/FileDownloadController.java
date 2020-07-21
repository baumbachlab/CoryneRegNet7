/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mariana
 */
@Controller
public class FileDownloadController {

    @RequestMapping("downloadFiles")
    public void downloadPDFResource(HttpServletRequest request,
            HttpServletResponse response, String fileName) {
        //If user is not authorized - he should be thrown out from here itself
        System.out.println("file =" + fileName);

        //Authorized user will download the file
        System.out.println("FileName: " + fileName);
        File file = new File(System.getProperty("user.dir"));
        String dataDirectory = file.getAbsolutePath() + "/";
        Path filePath = Paths.get(dataDirectory, fileName);
        if (Files.exists(filePath)) {
            response.setContentType("application/zip");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            System.out.println("filePath "+filePath);
            try {
                Files.copy(filePath, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @RequestMapping("downloadHmmProfile")
    public void downloadHmmProfile(HttpServletRequest request,
            HttpServletResponse response, String fileName) {
        //If user is not authorized - he should be thrown out from here itself
        System.out.println("file =" + fileName);

        String[] splitedFileName = fileName.split("/");
        String dataDirectory = fileName.replace(splitedFileName[splitedFileName.length - 1], "");
        //Authorized user will download the file
        System.out.println("FileName: " + fileName);
        //   File file = new File(fileName);
        //String dataDirectory = file.getAbsolutePath() + "/";
        Path filePath = Paths.get(fileName);
        if (Files.exists(filePath)) {
            response.setContentType("text/plain");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            try {
                Files.copy(filePath, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @RequestMapping("downloadTest")
    public String downloadTest(Model model) {

        return "download-test";
    }
    
    @RequestMapping("downloadRnaImage")
    public void downloadRnaImage(HttpServletRequest request,
            HttpServletResponse response, String fileName) {
        //If user is not authorized - he should be thrown out from here itself
        System.out.println("file =" + fileName);
        
        String imgType="";
        
        if(fileName.endsWith("png")){
            imgType="png";
        }else{
            imgType="ps";
        }

        //Authorized user will download the file
        System.out.println("FileName: " + fileName);
        //File file = new File(System.getProperty("user.dir"));
        //String dataDirectory = file.getAbsolutePath() + "/";
        Path filePath = Paths.get("/home/ubuntu/database/CoryneRegNet7/web/images/srnas/", fileName);
        if (Files.exists(filePath)) {
            response.setContentType("image/"+imgType);
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            try {
                Files.copy(filePath, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
