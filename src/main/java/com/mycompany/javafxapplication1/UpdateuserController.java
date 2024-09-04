/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.javafxapplication1;

import java.net.URL;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ntu-user
 */
public class UpdateuserController {

    @FXML
    private TextField newPassword;
    @FXML
    private TextField reenterPassword;
    @FXML
    private Button updatePasswordBtn;
    @FXML
    private TextField userName;
    @FXML
    private TextField userPass;

    /**
     * Initializes the controller class.
     */
        
/*
   @FXML
   private void updatePasswordBtnHandler(ActionEvent event) {
    try {
        String username = userName.getText();
        String oldPassword = userPass.getText();
        String newPasswordText = newPassword.getText();
        String reenterPasswordText = reenterPassword.getText();

        DB myobj = new DB();

        if (myobj.validateUser(username, oldPassword)) {
            if (newPasswordText.equals(reenterPasswordText)) {
                myobj.updatePassword(username, newPasswordText);

                // Provide feedback to the user about the success
                System.out.println("Password updated successfully");

                // Clear text fields after successful update
                userName.clear();
                userPass.clear();
                newPassword.clear();
                reenterPassword.clear();
            } else {
                // Handle the case where passwords do not match
                System.out.println("Passwords do not match");
            }
        } else {
            // Handle the case where user validation fails
            System.out.println("Invalid username or password");
        }
    } catch (InvalidKeySpecException | ClassNotFoundException ex) {
        Logger.getLogger(UpdateuserController.class.getName()).log(Level.SEVERE, null, ex);
        // Handle or display the exception appropriately
    }
}*/
   
    
    String username = null;
    @FXML
    private Button backBtn;
    
    
   

    @FXML
    private void updatePasswordBtnHandler(ActionEvent event) {
        

        try {
            String newPasswordText = newPassword.getText();
            String reenterPasswordText = reenterPassword.getText();
            DB db = new DB();            
                if (newPasswordText.equals(reenterPasswordText)) {
                    db.updatePassword(username, newPasswordText);

                    // Provide feedback to the user about the success
                    System.out.println("Password updated successfully");

                    // Clear text fields after successful update
                    userName.clear();
                    userPass.clear();
                    newPassword.clear();
                    reenterPassword.clear();
                } else {
                    // Handle the case where passwords do not match
                    System.out.println("Passwords do not match");
                }
            }
         catch (InvalidKeySpecException | ClassNotFoundException ex) {
            Logger.getLogger(UpdateuserController.class.getName()).log(Level.SEVERE, null, ex);
            // Handle or display the exception appropriately
        }
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



 /*   // Assuming you have a method to get or set the current user information
    //User currentUser = getUser();

    // Ensure currentUser is not null before proceeding
    //if (currentUser != null) {
        String newPasswordText = newPassword.getText();
        String reenterPasswordText = reenterPassword.getText();

        // Check if newPassword is equal to reenterPassword
        if (newPasswordText.equals(reenterPasswordText)) {
            // Update the user's password
            updatePassword(username, newPasswordText);

            // You may also want to persist the updated user information to a database or other storage
            // For example: DB.updateUser(currentUser);

            // Display a success message or perform any other necessary actions
            System.out.println("Password updated successfully");
        } else {
            // Handle the case where passwords do not match (display an error, log a message, etc.)
            System.out.println("Passwords do not match");
        }
    //} else {
        // Handle the case where currentUser is null (display an error, log a message, etc.)
      //  System.out.println("User information not available");
    //}
}

// Assuming you have a method to get or set the current user information
private User getCurrentUser() {
    // Implement this method to return the current user information
    // For example, you might have a login/authentication system that sets the current user
    // Return an instance of the User class with the current user's information
    return new User("exampleUser", "currentPassword");
    
    
    
}
    String username = null;
    String password = null;
    public void initialise(String[] currentUser) {
         username = currentUser[0];
         password = currentUser[1];
    }*/


    

