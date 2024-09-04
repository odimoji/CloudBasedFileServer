
/**
 *
 * @author ntu-user
 */
package com.mycompany.javafxapplication1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.File;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.EncryptionMethod;

public class EncDec {

    public void zipFiles(String filename) {
     

        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setEncryptFiles(true);
        zipParameters.setCompressionLevel(CompressionLevel.MAXIMUM);
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);

        try {
            ZipFile zipFile = new ZipFile("/home/ntu-user/App/" + filename + ".zip", "password".toCharArray());
            //zipFile.addFolder(new File("/home/ntu-user/App"), zipParameters);
            zipFile.addFile(new File("/home/ntu-user/App/" + filename ), zipParameters);
            System.out.println("File zipped with success");
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }

    public void unzipFiles(String filename) {
        try {
            ZipFile zipFile = new ZipFile("/home/ntu-user/App/" + filename +".zip", "password".toCharArray());
            zipFile.extractAll("/home/ntu-user/App/");
            System.out.println("File unzipped with success");
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }
}
