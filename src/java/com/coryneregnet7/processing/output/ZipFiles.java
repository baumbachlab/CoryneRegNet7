/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coryneregnet7.processing.output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author doglas
 */
public class ZipFiles {

    public void ZipFiles() {

    }

    public void zipFile(List<String> filesNamesToZip, String zipFilePath) {
        try {

            // Wrap a FileOutputStream around a ZipOutputStream
            // to store the zip stream to a file. Note that this is
            // not absolutely necessary
            // List<String> srcFiles = Arrays.asList("test1.txt", "test2.txt");
            System.out.println("zipFilePath: " + zipFilePath);
            File file;
            List<File> filesToZip = new ArrayList<>();
            for (String fileNameToZip : filesNamesToZip) {

                file = new File(System.getProperty("user.dir") + "/" + fileNameToZip);
                System.out.println("file: " + file);
                if (!filesToZip.contains(file)) {
                    filesToZip.add(file);
                }
            }

            FileOutputStream fos = new FileOutputStream(zipFilePath);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            for (File fileToZip : filesToZip) {
                FileInputStream fis = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                fis.close();
            }
            zipOut.close();
            fos.close();

            System.out.println("Regular file was zipped to archive :" + zipFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}