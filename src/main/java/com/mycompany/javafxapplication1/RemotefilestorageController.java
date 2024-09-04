/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.javafxapplication1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;

import com.jcraft.jsch.*;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
/**
 * FXML Controller class
 *
 * @author ntu-user
 */
public class RemotefilestorageController {


    @FXML
    private Button container1;
    @FXML
    private Button container2;
    @FXML
    private Button container3;
    @FXML
    private Button container4;
    @FXML
    private Button backBtn;
    @FXML 
    private TextField fileName;
    @FXML
    private AnchorPane fileToSave;
    /**
     * Initializes the controller class.
     */
 
 
    private static final String REMOTE_HOST = "comp20081-files-";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ntu-user";
    private static final int REMOTE_PORT = 22;
    private static final int SESSION_TIMEOUT = 10000;
    private static final int CHANNEL_TIMEOUT = 5000;

    
    public void rfs(String container){
        String localFile = "/home/ntu-user/App/" + fileName.getText();
        String remoteFile = fileName.getText();
        Session jschSession = null;
        try {
            
            JSch jsch = new JSch();
            jsch.setKnownHosts("/home/mkyong/.ssh/known_hosts");
            
            // Set the StrictHostKeyChecking option to "no" to automatically answer "yes" to the prompt
            jschSession = jsch.getSession(USERNAME, REMOTE_HOST + container, REMOTE_PORT);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            jschSession.setConfig(config);
            
            // authenticate using password
            jschSession.setPassword(PASSWORD);
            
            // 10 seconds session timeout
            jschSession.connect(SESSION_TIMEOUT);
            
            Channel sftp = jschSession.openChannel("sftp");
            
            // 5 seconds timeout
            sftp.connect(CHANNEL_TIMEOUT);
            
            ChannelSftp channelSftp = (ChannelSftp) sftp;
            
            // transfer file from local to remote server
            channelSftp.put(localFile, remoteFile);
            
            // Ask the user if they want to delete the remote file
            System.out.println("Successfully stored in the remote container");
            channelSftp.exit();
            
        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        } finally {
            if (jschSession != null) {
                jschSession.disconnect();
            }
        }
        System.out.println("Done");
    }

    
    
    
    
    
    @FXML
    private void container1BtnHandler(ActionEvent event){
        rfs("container1");
    }
    

    @FXML
    private void container2BtnHandler(ActionEvent event) {
        rfs("container2");
    
    }

    @FXML
    private void container3BtnHandler(ActionEvent event) {
        rfs("container3");
    }

    @FXML
    private void container4BtnHandler(ActionEvent event) {
        rfs("container4");
    
    }

    
    
    @FXML
    private void switchBackToSecondary(ActionEvent event) {
        Stage secondaryStage = new Stage();
        Stage primaryStage = (Stage) backBtn.getScene().getWindow();
        try {
            
        
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("secondary.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 640, 480);
            
            secondaryStage.setScene(scene);
            secondaryStage.setTitle("Secondary");
            secondaryStage.show();
            primaryStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

