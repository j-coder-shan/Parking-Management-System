package org.example.pms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class login {
    private Scene scene;

    @FXML
    public void openMainWindow(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainWindowController.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}

    public void loginAsAParkingAttendant(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginAsAParkingAttendant.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}
    /**/

    public void loginAsAManager(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginAsAManager.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}
    }


