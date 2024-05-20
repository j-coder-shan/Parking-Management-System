package org.example.pms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class newEmployer {
    @FXML
    private TextField nameField;

    @FXML
    private TextField mobileField;

    @FXML
    private TextField nicField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;
    @FXML
    private Stage stage;
    private Scene scene;

    public void initialize() {
        submitButton.setOnAction(event -> handleSubmit(event));
    }


    @FXML
    public void handleSubmit(ActionEvent event) {
        String name = nameField.getText();
        String mobile = mobileField.getText();
        String nic = nicField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();



        // Save the inputs to  files
        employerInfo(name, mobile, nic, username, password);
        usernamePassword(username, password);


        try {
            loginAsAParkingAttendant(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void usernamePassword(String username , String password){
        try (PrintWriter writer = new PrintWriter(new FileWriter("src\\employerUsernamePassword.txt", true))) {
            writer.print(username + ",");
            writer.print(password);
            writer.println();
            System.out.println("usernamePassword information saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void employerInfo(String name, String mobile, String nic, String username, String password) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src\\employerInfo.txt", true))) {
            writer.print("Name:" + name + " ");
            writer.print("Mobile:" + mobile + " ");
            writer.print("NIC:" + nic + " ");
            writer.print("Username:" + username + " ");
            writer.print("Password:" + password + " ");
            writer.println();
            System.out.println("Employer information saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loginAsAParkingAttendant(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginAsAParkingAttendant.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}
    public void loginAsAParkingAttendantWindow(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginAsAParkingAttendant.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}

}
