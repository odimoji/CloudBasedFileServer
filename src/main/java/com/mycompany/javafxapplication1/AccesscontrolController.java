/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */



package com.mycompany.javafxapplication1;

import java.net.URL;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ntu-user
 */
public class AccesscontrolController {

    @FXML
    private ComboBox fileChoice;
    @FXML
    private ComboBox userChoice;
    
    // Variables to store user choices
    private String selectedFile;
    private String selectedUser;
    private String selectedPermission;
    private int FileId;
    private int UserId;
    @FXML
    private Button readBtn;
    @FXML
    private Button readwriteBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Button revokewriteBtn;
    @FXML
    private Button revokereadwriteBtn;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        try {
            DB db = new DB();
            List<String> filenames = db.getFilesFromTable();
            List<String> usernames = db.getUsersFromTable();
            
            // Populate ChoiceBox objects
            fileChoice.getItems().addAll(filenames);
            userChoice.getItems().addAll(usernames);
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesscontrolController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    
    // Method to retrieve selected file
    public String getSelectedFile() {
        return selectedFile;
    }
    
    // Method to retrieve selected user
    public String getSelectedUser() {
        return selectedUser;
    }
    
    // Method to retrieve selected permission
    public String getSelectedPermission() {
        return selectedPermission;
    }

    @FXML
    private void readBtnHandler(ActionEvent event) {
        try {
            DB myObj = new DB();
            myObj.updateReadAccess(UserId, FileId);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(AccesscontrolController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesscontrolController.class.getName()).log(Level.SEVERE, null, ex);
        }


        
    }

    @FXML
    private void readwriteBtnHandler(ActionEvent event) {
        try {
            DB myObj = new DB();
            myObj.updateReadWriteAccess(UserId, FileId);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(AccesscontrolController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesscontrolController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    
    @FXML
    private void switchBackToSecondary(){
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

    @FXML
    private void fileChoiceHandler(ActionEvent event) {
        try {
            selectedFile = fileChoice.getValue().toString();
            DB db = new DB();
            List<Integer> temp = db.getFileId(selectedFile);
            FileId = temp.get(0);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesscontrolController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void userChoiceHandler(ActionEvent event) {
        try {
            selectedUser = userChoice.getValue().toString();
            DB db = new DB();
            List<Integer> temp = db.getUserId(selectedUser);
            UserId = temp.get(0);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesscontrolController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void revokewriteBtnHandler(ActionEvent event) {
        try {
            DB myObj = new DB();
            myObj.revokeWriteAccess(UserId, FileId);
            
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(AccesscontrolController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesscontrolController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void revokereadwriteBtnHandler(ActionEvent event) {
        try {
            DB myObj = new DB();
            myObj.revokeReadWriteAccess(UserId, FileId);
            
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(AccesscontrolController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesscontrolController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}



