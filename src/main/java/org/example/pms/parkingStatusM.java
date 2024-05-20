package org.example.pms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class parkingStatusM implements WindowManager {
    @FXML
    private Label available_spaces;
    @FXML
    private Label reserved_spaces;
    @FXML
    private Label plateNumbers;



    public void initialize() {
        // Call existVehicles method to populate labels
        try {
            existVehicles();
        }catch (Exception e) {
            System.out.println(e);
        }
    }


    @Override
    public void addVehicleM(ActionEvent event) throws IOException {
        WindowManager.super.addVehicleM(event);
    }

    @Override
    public void removeVehicleM(ActionEvent event) throws IOException {
        WindowManager.super.removeVehicleM(event);
    }

    @Override
    public void previousDetailsM(ActionEvent event) throws IOException {
        WindowManager.super.previousDetailsM(event);
    }

    @Override
    public void logout(ActionEvent event) throws IOException {
        WindowManager.super.logout(event);
    }
    void existVehicles(){
        String filename = "src\\main\\resources\\org\\example\\pms\\existing_vehicles.txt";
        int totalLinesRead = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;

            // Skip the first line
            reader.readLine();
            StringBuilder strBuilder = new StringBuilder(); // Use StringBuilder for efficient string concatenation

            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                totalLinesRead++;
                lineNumber++;

                // Split the line by whitespace
                String[] parts = line.split("\\s+");

                // Extract the number before the first whitespace
                if (parts.length > 0) {
                    String firstPart = parts[0]; // Get the first part
                    try {
                        // Parse the number as an integer
                        int number = Integer.parseInt(firstPart);
                        strBuilder.append(number).append("\n"); // Append the number to StringBuilder
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing number in line " + lineNumber);
                    }
                }
            }
            System.out.println("Total lines read (excluding the first line): " + totalLinesRead);
            available_spaces.setText(String.valueOf(100 - totalLinesRead));
            reserved_spaces.setText(String.valueOf(totalLinesRead) );
            plateNumbers.setText(String.valueOf(strBuilder));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
