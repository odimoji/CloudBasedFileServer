/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.javafxapplication1;

import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.util.logging.FileHandler;

/**
 * FXML Controller class
 *
 * @author ntu-user
 */
public class FilemanagerController implements Initializable {


    @FXML
    private TextField createFile;
    @FXML
    private Button createFileBtn;
    @FXML
    private TextField deleteFile;
    @FXML
    private Button deleteFileBtn;
    @FXML
    private TextArea createFileOutput;
    @FXML
    private TextField deleteFileOutput;
    @FXML
    private TextField updateFile;
    @FXML
    private TextArea updateFileContent;
    @FXML
    private TextArea updateFileOutput;
    @FXML
    private Button updateFileBtn;
    @FXML
    private TextField readFile;
    @FXML
    private TextArea readFileOutput;
    @FXML
    private Button readFileBtn;
    @FXML
    private Button backBtn;
    /**
     * Initializes the controller class.
     */
        
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    String owner = null;
    @FXML
    private void createFileBtnHandler(ActionEvent event) {
        
        String fileName = createFile.getText().trim();

        if (!fileName.isEmpty()) {
            try {
                DB db = new DB(); 
                // Specify the path where the file will be created
                String filePath = "/home/ntu-user/App/" + fileName;  // Change this path as needed

                // Create a new file
                File file = new File(filePath);

                // Check if the file already exists
                if (file.createNewFile()) {
                    System.out.println("File Created: " + fileName);
                    createFileOutput.setText("File Created: " + fileName);
                    
                    logUserActions(owner, "create", fileName, true);
                    //Encryption Decryption
                    EncDec encdec = new EncDec();
                    encdec.zipFiles(fileName);
                    file.delete();
                    
                    
                    
                   //Setting the filemetadata properties
                    String size = String.valueOf(file.length());
                    
                    String creationdate = java.time.LocalDate.now().toString();
                    String modificationdate = java.time.LocalDate.now().toString();
                    
                    db.addDataToFilemetadata(fileName, size, owner, creationdate, modificationdate);
                    
                    List<Integer> temp = db.getUserId(owner);
                    int UserId = temp.get(0);
                    List<Integer> temp2 = db.getFileId(fileName);
                    int FileId = temp2.get(0);
                    db.updateReadWriteAccess(UserId, FileId);
                    
                    System.out.println("File metadata added successfully.");
                    
                    

                    
             
              
                } else {
                    System.out.println("Error: File already exists.");
                    createFileOutput.setText("File already exists");
                }

            } catch (IOException e) {
                System.out.println("Error: Unable to create the file.");
                e.printStackTrace();
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(FilemanagerController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FilemanagerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Error: Please enter a valid file name.");
        }
    }

    @FXML
    private void deleteFileBtnHandler(ActionEvent event) {
        String fileName = deleteFile.getText().trim();

        if (!fileName.isEmpty()) {
            // Specify the path of the file to be deleted
            String filePath = "/home/ntu-user/App" + fileName;  // Change this path as needed

            // Create a file object
            File file = new File(filePath);

            if (file.exists()) {
                // Delete the file
                if (file.delete()) {
                    System.out.println("File Deleted: " + fileName);
                    deleteFileOutput.setText("File Deleted: " + fileName);
                } else {
                    System.out.println("Error: Unable to delete the file.");
                    deleteFileOutput.setText("Error: Unable to delete the file.");
                    
                }
                 logUserActions(owner, "delete", fileName, true);
            } else {
                System.out.println("Error: File does not exist.");
                deleteFileOutput.setText("Error: File does not exist.");
            }

        } else {
            System.out.println("Error: Please enter a valid file name.");
            deleteFileOutput.setText("Error: Please enter a valid file name.");
        }
    }
    
    @FXML
    private void updateFileBtnHandler(ActionEvent event) {
        String fileName = updateFile.getText().trim();
        String content = updateFileContent.getText();
        DB db = new DB();
        

        if (!fileName.isEmpty()) {
            EncDec encdec = new EncDec();
            encdec.unzipFiles(fileName);
            try (FileWriter writer = new FileWriter("/home/ntu-user/App/" + fileName)) {
                List<Integer> temp = db.getFileId(fileName);
                int fileId = temp.get(0);
                List<Integer> temp2 = db.getUserId(owner);
                int userId = temp2.get(0);
                if (!db.canReadWrite(userId, fileId)){
                    updateFileOutput.setText("You don't have permission!");
                    return;
                }
                // Write the new content to the file
                writer.write(content);
                writer.close();
                File file = new File("/home/ntu-user/App/" + fileName + ".zip");
                file.delete();
                encdec.zipFiles(fileName);
                File file1 = new File("/home/ntu-user/App/" + fileName);
                file1.delete();
                // Display success message in updateFileOutput
                updateFileOutput.setText("File Updated Successfully!");
                logUserActions(owner, "update", fileName, true);
                 
            } catch (IOException e) {
                // Handle file writing error
                updateFileOutput.setText("Error: Unable to update the file.");
                e.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FilemanagerController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(FilemanagerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // Display error message in updateFileOutput
            updateFileOutput.setText("Error: Please enter a valid file name.");
        }
    }
    
    @FXML
    private void readFileBtnHandler(ActionEvent event) {
    String fileName = readFile.getText().trim();
    DB db = new DB();

    if (!fileName.isEmpty()) {
        try {
            List<Integer> temp = db.getFileId(fileName);
            int fileId = temp.get(0);
            List<Integer> temp2 = db.getUserId(owner);
            int userId = temp2.get(0);
            
            if (!db.canRead(userId, fileId)) {
                // User does not have read access
                readFileOutput.setText("You don't have permission to read this file!");
                return;
            }
            EncDec encdec = new EncDec();
            encdec.unzipFiles(fileName);
            String filePath = "/home/ntu-user/App/" + fileName ;
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                readFileOutput.setText(content.toString());
                File file = new File(filePath);
                file.delete();
                logUserActions(owner, "read", fileName, true);
            } catch (IOException e) {
                // Handle file reading exception
                e.printStackTrace();
                readFileOutput.setText("Error reading file!");
            }
        } catch (Exception e) {
            // Handle DB query exception
            e.printStackTrace();
            readFileOutput.setText("Error accessing database!");
        }
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
    
    private void chunkFile(File file) throws IOException {
        int chunkSize = 1024 * 1024; // 1 MB chunk size
        byte[] buffer = new byte[chunkSize];
        String outputDir = "/home/ntu-user/App/chunks";  // Output directory for chunks

        try (FileInputStream inputStream = new FileInputStream(file)) {
            int bytesRead;
            int chunkNumber = 1;

            // Read from the input file and write chunks to separate files
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                // Create a new chunk file
                File chunkFile = new File(outputDir + File.separator + file.getName() + "_chunk" + chunkNumber);
                try (FileOutputStream outputStream = new FileOutputStream(chunkFile)) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                chunkNumber++;
            }
        }
    }

    
    private final Logger LOGGER = Logger.getLogger(FilemanagerController.class.getName());
    
    public void logUserActions(String user, String action, String fileName, boolean status) {
        try {
            FileHandler fileHandler = new FileHandler("Audit.log", true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            LOGGER.addHandler(fileHandler);
            if (status) {
                LOGGER.log(Level.INFO, "User: {0} successfully performed: {1} on: {2}", new Object[]{user, action, fileName});
            } else {
                LOGGER.log(Level.SEVERE, "User: {0} tried to do: {1} on: {2}, but failed!", new Object[]{user, action, fileName});
            }
            fileHandler.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
    

    




