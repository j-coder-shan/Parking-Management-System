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

import java.io.IOException;

public class loginAsAManager {
    private static final String CORRECT_USERNAME = "admin";
    private static final String CORRECT_PASSWORD = "admin123";

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

        if (username.equals(CORRECT_USERNAME) && password.equals(CORRECT_PASSWORD)) {
            messageLabel.setText("Login successful");

            // Get the current stage and close it
            Stage currentStage = (Stage) submitButton.getScene().getWindow();
            currentStage.close();

            // Open the manager window
            openManagerWindow();
        } else {
            attempts--;
            messageLabel.setText("Invalid username or password. Attempts left: " + attempts);
            passwordField.clear();

            if (attempts == 0) {
                submitButton.setDisable(true);
                messageLabel.setText("Maximum attempts reached. Please contact administrator.");
            }
        }
    }


    private void openManagerWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("managerWindow.fxml"));
            Parent root = fxmlLoader.load();
            Stage managerStage = new Stage();
            managerStage.setTitle("Manager Window");
            managerStage.setScene(new Scene(root, 1000, 800));
            managerStage.setResizable(false);
            managerStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loginWindow(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}
}
