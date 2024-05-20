package org.example.pms;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public interface WindowManager {

    default void addVehicleM(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addVehicleM.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    default void removeVehicleM(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("removeVehicleM.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    default void parkingStatusM(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("parkingStatusM.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    default void previousDetailsM(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("previousDetailsM.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    default void logout(ActionEvent event) throws IOException {
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
}
