/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.javafxapplication1;

import com.mycompany.javafxapplication1.DB;
import com.mycompany.javafxapplication1.UpdateuserController;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
/**
 * FXML Controller class
 *
 * @author ntu-user
 */
public class DeleteuserController implements Initializable {


    @FXML
    private Button deleteUserBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    String currentUser=null;
    @FXML
    private void deleteUserBtnHandler(ActionEvent event) {
        
        DB db = new DB();
        try {
            db.deleteUser(currentUser);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeleteuserController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    
    }

}
