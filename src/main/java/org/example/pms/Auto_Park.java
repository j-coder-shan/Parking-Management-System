package org.example.pms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Auto_Park extends Application {
    @Override
    public void start(Stage mainStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Auto_Park.class.getResource("MainWindowController.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);

        mainStage.setTitle("Auto Park");
        mainStage.setResizable(false);

        mainStage.setScene(scene);
        mainStage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}