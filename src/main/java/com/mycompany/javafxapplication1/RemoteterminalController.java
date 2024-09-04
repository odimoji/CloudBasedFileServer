/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.javafxapplication1;

import java.net.URL;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import com.jcraft.jsch.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ntu-user
 */
public class RemoteterminalController implements Initializable {

    @FXML
    private Button container1Btn;
    @FXML
    private Button container2Btn;
    @FXML
    private Button container3Btn;
    @FXML
    private Button container4Btn;
    @FXML
    private Button backBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void remoteterminal(String host){
    
    try {
            JSch jsch = new JSch();

            //jsch.setKnownHosts("/home/foo/.ssh/known_hosts");
            
            String user = "root";
           

            Session session = jsch.getSession(user, host, 22);

            String passwd = "ntu-user";
            session.setPassword(passwd);

            

            // It must not be recommended, but if you want to skip host-key check,
            // invoke following,
            session.setConfig("StrictHostKeyChecking", "no");
            //session.connect();
            session.connect(30000);   // making a connection with timeout.

            Channel channel = session.openChannel("shell");

            // Enable agent-forwarding.
            //((ChannelShell)channel).setAgentForwarding(true);
            channel.setInputStream(System.in);
            /*
      // a hack for MS-DOS prompt on Windows.
      channel.setInputStream(new FilterInputStream(System.in){
          public int read(byte[] b, int off, int len)throws IOException{
            return in.read(b, off, (len>1024?1024:len));
          }
        });
             */

            channel.setOutputStream(System.out);

            /*
      // Choose the pty-type "vt102".
      ((ChannelShell)channel).setPtyType("vt102");
             */

 /*
      // Set environment variable "LANG" as "ja_JP.eucJP".
      ((ChannelShell)channel).setEnv("LANG", "ja_JP.eucJP");
             */
            //channel.connect();
            channel.connect(3 * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    
}

    @FXML
    private void container1BtnHandler(ActionEvent event) {
        
        remoteterminal("comp20081-files-container1");
    }

    @FXML
    private void container2BtnHandler(ActionEvent event) {
        remoteterminal("comp20081-files-container2");
    }

    @FXML
    private void container3BtnHandler(ActionEvent event) {
        remoteterminal("comp20081-files-container3");
        
    }

    @FXML
    private void container4BtnHandler(ActionEvent event) {
        remoteterminal("comp20081-files-container4");
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
    
}
