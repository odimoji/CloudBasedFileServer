package com.mycompany.javafxapplication1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;



public class SecondaryController {
    
    @FXML
    private Label userTextField;
    
    @FXML
    private TableView dataTableView;

    @FXML
    private Button secondaryButton;
    
    @FXML
    private Button refreshBtn;
    
    @FXML
    private TextField customTextField;
    
    @FXML
    private Button terminalBtn;
    
    @FXML
    private Button fileManagerBtn;
    
    @FXML
    private Button deleteUserBtn;
    
    @FXML
    private Button updatePasswordBtn;
    
    @FXML
    private Button RemoteTerminalBtn;
    
    @FXML
    private Button AccessControlBtn;
    
    @FXML
    private Button filestorageBtn;
    
   

    
    @FXML
    private void RefreshBtnHandler(ActionEvent event){
        Stage primaryStage = (Stage) customTextField.getScene().getWindow();
        customTextField.setText((String)primaryStage.getUserData());
    }
        
    @FXML
    private void switchToPrimary(){
        Stage secondaryStage = new Stage();
        Stage primaryStage = (Stage) secondaryButton.getScene().getWindow();
        try {
            
        
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("primary.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 640, 480);
            secondaryStage.setScene(scene);
            secondaryStage.setTitle("Login");
            secondaryStage.show();
            primaryStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void switchToTerminal(){
        Stage secondaryStage = new Stage();
        Stage primaryStage = (Stage) terminalBtn.getScene().getWindow();
        try {
            
        
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("terminal.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 640, 480);
            secondaryStage.setScene(scene);
            secondaryStage.setTitle("Terminal");
            secondaryStage.show();
            primaryStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void switchToFileManager(){
        Stage secondaryStage = new Stage();
        Stage primaryStage = (Stage) fileManagerBtn.getScene().getWindow();
        try {
            
        
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("filemanager.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 640, 480);
            FilemanagerController controller =loader.getController();
            controller.owner = userTextField.getText();
            secondaryStage.setScene(scene);
            
            secondaryStage.setTitle("File Manager");
            secondaryStage.show();
            primaryStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void switchToUpdatePassword(){
        Stage secondaryStage = new Stage();
        Stage primaryStage = (Stage) updatePasswordBtn.getScene().getWindow();
        try {
            
        
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("updateuser.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 640, 480);
            UpdateuserController controller =loader.getController();
            controller.username = userTextField.getText();
     
            secondaryStage.setScene(scene);
            secondaryStage.setTitle("Update Password");
            secondaryStage.show();
            primaryStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @FXML
    private void switchToRemoteTerminal(){
        Stage secondaryStage = new Stage();
        Stage primaryStage = (Stage) RemoteTerminalBtn.getScene().getWindow();
        try {
            
        
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("remoteterminal.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 640, 480);
            secondaryStage.setScene(scene);
            secondaryStage.setTitle("Remote Terminal");
            secondaryStage.show();
            primaryStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void switchToAccessControl(){
        Stage secondaryStage = new Stage();
        Stage primaryStage = (Stage) AccessControlBtn.getScene().getWindow();
        try {
            
        
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("accesscontrol.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 640, 480);
            secondaryStage.setScene(scene);
            secondaryStage.setTitle("Access Control");
            secondaryStage.show();
            primaryStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void switchToDeleteUser(){
        Stage secondaryStage = new Stage();
        Stage primaryStage = (Stage) deleteUserBtn.getScene().getWindow();
        try {
            
        
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("deleteuser.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 640, 480);
            DeleteuserController controller =loader.getController();
            controller.currentUser=userTextField.getText();
            secondaryStage.setScene(scene);
            secondaryStage.setTitle("File Manager");
            secondaryStage.show();
            primaryStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void filestorageBtnHandler(){
        Stage secondaryStage = new Stage();
        Stage primaryStage = (Stage) filestorageBtn.getScene().getWindow();
        try {
            
        
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("remotefilestorage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 640, 480);
            secondaryStage.setScene(scene);
            secondaryStage.setTitle("Remote File Storage");
            secondaryStage.show();
            primaryStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
String loggedUserPassword=null;

    public void initialise(String[] credentials) {
        userTextField.setText(credentials[0]);
        loggedUserPassword=credentials[1];
        
        
        DB myObj = new DB();
        ObservableList<User> data;
        try {
            data = myObj.getDataFromTable();
            TableColumn user = new TableColumn("User");
        user.setCellValueFactory(
        new PropertyValueFactory<>("user"));

        TableColumn pass = new TableColumn("Pass");
        pass.setCellValueFactory(
            new PropertyValueFactory<>("pass"));
        dataTableView.setItems(data);
        dataTableView.getColumns().addAll(user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}



