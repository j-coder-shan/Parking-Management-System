package org.example.pms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class existingEmployer {
    private static final String FILE_PATH = "src\\employerUsernamePassword.txt";

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Label messageLabel;

    private Stage stage;

    private int attempts = 3;

    // Method to set the stage
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        submitButton.setOnAction(event -> handleLogin());
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String storedUsername = parts[0].trim();
                    String storedPassword = parts[1].trim();
                    if (username.equals(storedUsername) && password.equals(storedPassword)) {
                        found = true;
                        break;
                    }
                }
            }

            if (found) {
                messageLabel.setText("Login successful");
                Stage currentStage = (Stage) submitButton.getScene().getWindow();
                currentStage.close();
                ParkingAttendantWindow();
            } else {
                attempts--;
                messageLabel.setText("Invalid username or password. Attempts left: " + attempts);
                passwordField.clear();

                if (attempts == 0) {
                    submitButton.setDisable(true);
                    messageLabel.setText("Maximum attempts reached. Please contact administrator.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ParkingAttendantWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ParkingAttendantWindow.fxml"));
            Parent root = fxmlLoader.load();
            Stage managerStage = new Stage();
            managerStage.setTitle("Parking Attendant Window");
            managerStage.setScene(new Scene(root, 1000, 800));
            managerStage.setResizable(false);
            managerStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loginWindow(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginAsAParkingAttendant.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}
}