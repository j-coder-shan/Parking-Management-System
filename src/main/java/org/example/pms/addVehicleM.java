package org.example.pms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class addVehicleM implements  WindowManager {

    @FXML
    private TextField licensePlateNumberField;
    @FXML
    private TextField vehicleTypeField;
    @FXML
    private Label messageLabel;
    private Stage stage;
    @FXML
    public void initialize() {
        // Initialize text fields or perform any setup if needed
    }

    @FXML
    private boolean isLicensePlateNumberExists(String licensePlateNumber) throws IOException {
        String filename = "src\\main\\resources\\org\\example\\pms\\existing_vehicles.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by commas
                String[] parts = line.split(",");
                // Check if the first part (license plate number) matches the given license plate number
                if (parts.length > 0 && parts[0].trim().equals(licensePlateNumber.trim())) {
                    return true; // License plate number exists
                }
            }
        }
        return false; // License plate number does not exist
    }

    @FXML
    public void handleSubmit() {
        String licensePlateNumber = licensePlateNumberField.getText();
        String vehicleType = vehicleTypeField.getText();
        String entryTime = getCurrentTime();

        try {
            if (!isLicensePlateNumberExists(licensePlateNumber)) {
                // License plate number does not exist, append new data to file
                Vehicle vehicle = new Vehicle(licensePlateNumber, vehicleType, entryTime);
                saveToFile(vehicle);

                // Clear the text fields
                licensePlateNumberField.setText("");
                vehicleTypeField.setText("");
            } else {
                // License plate number already exists
                licensePlateNumberField.setText("");
                vehicleTypeField.setText("");
                messageLabel.setText("License plate number already exists!");
                // You can display an error message to the user or handle it as needed
            }
        } catch (IOException e) {
            System.err.println("Error checking license plate number: " + e.getMessage());

        }
    }


    private String getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentTime.format(formatter);
    }

    private void saveToFile(Vehicle vehicle) {
        String filename = "src\\main\\resources\\org\\example\\pms\\existing_vehicles.txt";
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(vehicle.getLicensePlateNumber() + " , " );
            writer.write(vehicle.getVehicleType() + " , " );
            writer.write(vehicle.getEntryTime() + "\n"); // Add new line
            System.out.println("Vehicle information saved successfully. " );
            messageLabel.setText("Vehicle added successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void removeVehicleM(ActionEvent event) throws IOException {
        WindowManager.super.removeVehicleM(event);
    }

    @Override
    public void parkingStatusM(ActionEvent event) throws IOException {
        WindowManager.super.parkingStatusM(event);
    }

    @Override
    public void previousDetailsM(ActionEvent event) throws IOException {
        WindowManager.super.previousDetailsM(event);
    }

    @Override
    public void logout(ActionEvent event) throws IOException {
        WindowManager.super.logout(event);
    }


}
