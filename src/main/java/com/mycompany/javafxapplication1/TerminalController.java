/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.javafxapplication1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author ntu-user
 */
public class TerminalController {


    @FXML
    private TextArea commandToRun;
    @FXML
    private Button runCommandBtn;
    @FXML
    private TextArea commandOutput;
    @FXML
    private Button backBtn;
    
    @FXML 
    private void runCommandBtnHandler(ActionEvent event) {
        // Get the command from the TextField
        String command = commandToRun.getText().trim();

        if (!command.isEmpty()) {
            try {
                // Execute the command using ProcessBuilder 
                Process process = new ProcessBuilder(command).start();

                // Read the output of the command
                InputStream inputStream = process.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }


                //System.out.println(output.toString());

                // Close the streams
                reader.close();
                inputStream.close();
                // Update the GUI with the command output
                commandOutput.setText(output.toString());  

            } catch (IOException e) {
                // Handle exceptions (e.g., command not found, etc.)
                e.printStackTrace();
            }
        } else {
            // Handle case where the command is empty
            System.out.println("Please enter a command.");
            commandOutput.setText("Please enter a command.");
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