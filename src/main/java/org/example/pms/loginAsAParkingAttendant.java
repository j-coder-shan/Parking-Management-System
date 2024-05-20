package org.example.pms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class loginAsAParkingAttendant {
    @FXML
    private Stage stage;
    private Scene scene;
    @FXML
    public void loginPage(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}

    public void existingEmployer(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("existingEmployer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}
    public void newEmployer(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("newEmployer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}
}