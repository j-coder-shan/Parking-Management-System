package org.example.pms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class removeVehiclePA extends ParkingAttendantWindow {
    public static int hours ;
    public static int minutes ;
    public static int seconds ;
    public static int payment ;
    public static String licensePlateNumber;
    public static String arrival_Time ;
    public static String departure_Time;

    @FXML
    private Label arrival;
    @FXML
    private Label departure;
    @FXML
    private Label plate;
    @FXML
    private Label timeSpent;
    @FXML
    private Label pay;


    @FXML
    public TextField licensePlateNumberField;

    @FXML
    private Label message;

    public void initialize() {
        // Initialize text fields or perform any setup if needed
    }

    @FXML
    public void handleSubmit() {
        // Call remove_Vehicle method to remove the vehicle
        remove_Vehicle();
        licensePlateNumberField.clear();

    }

    public static void printBill(String arrival_Time, String departure_Time, int hours, int minutes, int seconds, int paymentAmount, String licensePlateNumber) {

    Stage stage = new Stage();
    VBox vbox = new VBox();

    Label licensePlateNumberLabel = new Label("  License Plate Number: " + licensePlateNumber );
    Label arrivalLabel = new Label           ("  Arrival Time: " + arrival_Time );
    Label departureLabel = new Label         ("  Departure Time: " + departure_Time);
    Label timeSpentLabel = new Label         ("  Time Spent: " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds");
    Label paymentLabel = new Label           ("  Payment Amount:  RS. " + paymentAmount + " /=");

        // Apply CSS styling
        String labelStyle = "-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #ffcdcd;";
        String vboxStyle = "-fx-background-color: #14242B; -fx-border-color: white; -fx-border-width: 5";

        licensePlateNumberLabel.setStyle(labelStyle);
        arrivalLabel.setStyle(labelStyle);
        departureLabel.setStyle(labelStyle);
        timeSpentLabel.setStyle(labelStyle);
        paymentLabel.setStyle(labelStyle);
        vbox.setStyle(vboxStyle);

        // Set label size
        double labelWidth = 400;
        double labelHeight = 50;
        licensePlateNumberLabel.setPrefWidth(labelWidth);
        licensePlateNumberLabel.setPrefHeight(labelHeight);
        arrivalLabel.setPrefWidth(labelWidth);
        arrivalLabel.setPrefHeight(labelHeight);
        departureLabel.setPrefWidth(labelWidth);
        departureLabel.setPrefHeight(labelHeight);
        timeSpentLabel.setPrefWidth(labelWidth);
        timeSpentLabel.setPrefHeight(labelHeight);
        paymentLabel.setPrefWidth(labelWidth);
        paymentLabel.setPrefHeight(labelHeight);

    vbox.getChildren().addAll(licensePlateNumberLabel, arrivalLabel, departureLabel, timeSpentLabel, paymentLabel);
    stage.setScene(new Scene(vbox, 400, 260));
    stage.setResizable(false);
    stage.setTitle("Time and Payment Information");
    stage.show();
}
    @FXML
    public void remove_Vehicle() {
        licensePlateNumber = licensePlateNumberField.getText();
        String filename = "C:\\Users\\PRABO\\OneDrive\\Desktop\\java\\JAVAFX\\PMS\\src\\main\\resources\\org\\example\\pms\\existing_vehicles.txt";
        String removedFile = "C:\\Users\\PRABO\\OneDrive\\Desktop\\java\\JAVAFX\\PMS\\src\\main\\resources\\org\\example\\pms\\removed_Vehicles.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filename));
             BufferedWriter removedWriter = new BufferedWriter(new FileWriter(removedFile, true))) {

            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                int spaceIndex = line.indexOf(' ');
                String firstPart;
                if (spaceIndex != -1) {
                    firstPart = line.substring(0, spaceIndex);
                } else {
                    // Handle the case where the space character is not found
                    firstPart = line;
                }

                // Check if the line contains the license plate number
                if (licensePlateNumber.equals(firstPart)) {
                    found = true;
                    String departureTime = getCurrentTime();
                    // Write the line to the removed file
                    String line_ = line + " , " + departureTime ;
                    removedWriter.write(line_);
                    reader.close();
                    removedWriter.newLine();
                    removedWriter.close();
                    deleteLineFromFile(filename, line);
                    bill(line_);


                    // Break out of the loop once the line is found
                    break;
                }
            }

            if (found) {
                message.setText("Vehicle removed successfully.");
                System.out.println("Vehicle removed successfully.");
                licensePlateNumberField.clear(); // Clear the text field
                savePaymentAndDateToFile(licensePlateNumber,payment,arrival_Time ,departure_Time);


            } else {
                message.setText("License plate number not found.");
                System.out.println("License plate number not found.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteLineFromFile(String filePath, String dataToDelete) {
        // Create a temporary file
        File tempFile = new File(filePath + ".tmp");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;

            // Read each line from the original file and copy to the temporary file,
            // excluding the line that contains the dataToDelete
            while ((line = reader.readLine()) != null) {
                if (!line.equals(dataToDelete)) {
                    writer.write(line + System.getProperty("line.separator"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Delete the original file
        File originalFile = new File(filePath);
        if (originalFile.delete()) {
            // Rename the temporary file to the original file name
            tempFile.renameTo(originalFile);
        } else {
            System.out.println("Error deleting line.");
        }
    }

    private String getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentTime.format(formatter);
    }

    public void bill(String line) {

        // Split the line into its components
        String[] parts = line.split(",");

        // Extract the arrival and departure time strings
        String arrivalTimeString = parts[2].trim();
        String departureTimeString = parts[3].trim();

        // Define the date-time formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Parse the arrival and departure time strings into LocalDateTime objects
        LocalDateTime arrivalTime = LocalDateTime.parse(arrivalTimeString, formatter);
        LocalDateTime departureTime = LocalDateTime.parse(departureTimeString, formatter);

        // Calculate the time difference
        Duration duration = Duration.between(arrivalTime, departureTime);

        arrival_Time = arrivalTimeString;
        departure_Time = departureTimeString;

        // Get the time difference in terms of hours, minutes, and seconds
        hours = (int) duration.toHours();
        minutes = (int) (duration.toMinutes() % 60);
        seconds = (int) (duration.getSeconds() % 60);

        // Print the results
        System.out.println("Arrival Time: " + arrivalTime);
        System.out.println("Departure Time: " + departureTime);
        System.out.println("Time Spent: " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds");
        payment(hours , minutes );

    }
    void payment(int hours , int minutes){
        int fixedPay = 20 ;

        if(hours < 1){
            payment = fixedPay + minutes * 10 ; // 10 per minute
        } else if (hours < 5) {
            payment = fixedPay + (minutes + hours * 60) * 8 ; // 8 per minute
        } else if (hours < 12) {
            payment = fixedPay + (minutes + hours * 60) * 6 ; // 6 per minute
        } else{
            payment = fixedPay + (minutes + hours * 60 ) * 5 ; // 5 per minute
        }

    }
    public static void savePaymentAndDateToFile(String licensePlateNumber, int payment, String arrival_Time, String departure_Time) {
        String filename = "src\\main\\resources\\org\\example\\pms\\PaymentDetails.txt" ;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {

            // Write payment and date to the file
            writer.write("licensePlateNumber: " + licensePlateNumber + ", Payment: " + payment +", Arrival: "+ arrival_Time + ", Departure: " + departure_Time);
            writer.newLine(); // Add a new line for the next entry
            System.out.println("Payment and Date saved successfully.");
            printBill(arrival_Time, departure_Time, hours, minutes, seconds, payment, licensePlateNumber);


        } catch (IOException e) {
            System.err.println("Error writing payment and date to file: " + e.getMessage());
        }
    }

    @FXML
    public void logout_RV(ActionEvent event) throws IOException {
        logout(event);
    }

    public void addVehicle_RV(ActionEvent event) throws IOException {
        addVehicle(event);
    }

}
