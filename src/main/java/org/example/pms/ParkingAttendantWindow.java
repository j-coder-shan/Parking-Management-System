package org.example.pms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ParkingAttendantWindow {
    @FXML
    private Stage stage;
    public static Scene scene;

    @FXML
    public void logout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Auto_Park.class.getResource("MainWindowController.fxml"));
        Parent root = fxmlLoader.load();

        // Get the stage of the current event source
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Create a new stage for the MainWindow
        Stage mainWindowStage = new Stage();
        mainWindowStage.setTitle("Auto Park");
        mainWindowStage.setScene(new Scene(root, 1000, 800));
        mainWindowStage.setResizable(false);
        mainWindowStage.show();
        currentStage.close();
    }

    public void addVehicle(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addVehiclePA.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**/
    public void removeVehicle(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("removeVehiclePA.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
