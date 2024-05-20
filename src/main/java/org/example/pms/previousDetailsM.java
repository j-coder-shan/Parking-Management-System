package org.example.pms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class previousDetailsM implements WindowManager {
    @FXML
    private Label totalRevenue;
    @FXML
    private Label paymentByYear;
    @FXML
    private Label paymentByYearMonth;
    @FXML
    private Label paymentByYearMonthDate;
    @FXML
    private Label arrivalLabel;
    @FXML
    private Label departureLabel;
    @FXML
    private Label setarrivalLabel;
    @FXML
    private Label setdepartureLabel;
    @FXML
    private TextField yearTextField;
    @FXML
    private TextField yearTextField1;
    @FXML
    private TextField yearTextField3;
    @FXML
    private TextField monthTextField1;
    @FXML
    private TextField monthTextField3;
    @FXML
    private TextField dateTextField3;
    @FXML
    private TextField licensePlateNumberField;

    public void initialize() {
        try {
            TotalRevenue();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    public void handleSubmit() {
        if(licensePlateNumberField.getText().isEmpty()){
            arrivalLabel.setText("ENTER NO !");
            departureLabel.setText("");
        }else {
            setarrivalLabel.setText("Arrival");
            setdepartureLabel.setText("Departure");
            String licensePlateNumber = licensePlateNumberField.getText();
            // Call the method to extract details and set them to labels
            extractDetails(licensePlateNumber, arrivalLabel, departureLabel);
        }

    }

    @FXML
    public void paymentByYear() {
        if (yearTextField.getText().isEmpty()) {
            paymentByYear.setText("Rs. 0");
            return; // Exit the method
        }
        // Get the year entered in the text field
        String yearText = yearTextField.getText();
        int targetYear = Integer.parseInt(yearText); // Convert the text to an integer

        // Call the calculateTotalPayment method
        PaymentByYear(targetYear);
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
    public void parkingStatusM(ActionEvent event) throws IOException {
        WindowManager.super.parkingStatusM(event);
    }

    @Override
    public void logout(ActionEvent event) throws IOException {
        WindowManager.super.logout(event);
    }

    void TotalRevenue() {
        double totalPayment = 0.0;
        String filename = "src\\main\\resources\\org\\example\\pms\\PaymentDetails.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                // Split the line by commas to extract payment information
                String[] parts = line.split(",");

                // Extract payment amount from the line
                for (String part : parts) {
                    if (part.trim().startsWith("Payment")) {
                        // Extract the payment value
                        String[] paymentInfo = part.split(":");
                        double paymentAmount = Double.parseDouble(paymentInfo[1].trim());
                        totalPayment += paymentAmount; // Add payment to total
                        break; // Move to the next line
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        totalRevenue.setText(String.valueOf("Rs."+totalPayment));
    }

    void PaymentByYear(int year) {
        double totalPayment = 0.0;
        String filename = "src\\main\\resources\\org\\example\\pms\\PaymentDetails.txt";


        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by comma and space
                String[] parts = line.split(",\\s+");

                // Extract the arrival date
                String arrivalString = parts[2].split(":\\s+")[1];

                // Parse the arrival date
                LocalDateTime arrival = LocalDateTime.parse(arrivalString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                // Check if the year matches
                if (arrival.getYear() == year) {
                    // Extract the payment
                    String paymentString = parts[1].split(":\\s+")[1];
                    double payment = Double.parseDouble(paymentString);

                    // Add the payment to totalPayment
                    totalPayment += payment;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        paymentByYear.setText(String.valueOf("Rs." + totalPayment));
    }

    @FXML
    public void PaymentByYearMonth() {
        if (yearTextField1.getText().isEmpty() || monthTextField1.getText().isEmpty()) {
            paymentByYearMonth.setText("Rs. 0");
            return; // Exit the method
        }
        String filename = "src\\main\\resources\\org\\example\\pms\\PaymentDetails.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int year = Integer.parseInt(yearTextField1.getText());
            int month = Integer.parseInt(monthTextField1.getText());
            String line;

            double totalPayment = 0.0;

            while ((line = reader.readLine()) != null) {
                // Extract year and month from the arrival time
                String[] parts = line.split(", ");
                String[] arrivalParts = parts[2].split(" ")[1].split("-");

                int entryYear = Integer.parseInt(arrivalParts[0]);
                int entryMonth = Integer.parseInt(arrivalParts[1]);

                // Check if the year and month match the given inputs
                if (entryYear == year && entryMonth == month) {
                    // Extract payment amount
                    double payment = Double.parseDouble(parts[1].split(": ")[1]);
                    totalPayment += payment;
                }
            }

            // Display the total payment
            paymentByYearMonth.setText(String.valueOf("Rs." + totalPayment));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void PaymentByYearMonthDate() {

        if (yearTextField3.getText().isEmpty() || monthTextField3.getText().isEmpty() || dateTextField3.getText().isEmpty()) {
            paymentByYearMonthDate.setText("Rs.0");
            return; // Exit the method
        }
        int year = Integer.parseInt(yearTextField3.getText());
        int month = Integer.parseInt(monthTextField3.getText());
        int date = Integer.parseInt(dateTextField3.getText());

        double totalPayment = 0.0;

        try (BufferedReader reader = new BufferedReader(new FileReader("src\\main\\resources\\org\\example\\pms\\PaymentDetails.txt"))) {
            String line;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                LocalDateTime arrival = LocalDateTime.parse(parts[2].substring(parts[2].indexOf(":") + 2), formatter);

                // Check if the arrival date matches the provided year, month, and date
                if (arrival.getYear() == year && arrival.getMonthValue() == month && arrival.getDayOfMonth() == date) {
                    // If it matches, accumulate the payment
                    String paymentStr = parts[1].substring(parts[1].indexOf(":") + 2);
                    totalPayment += Double.parseDouble(paymentStr);
                }
            }
            // Display the total payment
            paymentByYearMonthDate.setText("Rs." + totalPayment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public static void extractDetails(String licensePlateNumber, Label arrivalLabel, Label departureLabel) {
        // Define the filename where details are saved
        String filename = "src\\main\\resources\\org\\example\\pms\\PaymentDetails.txt";

        // Initialize lists to store arrival and departure times
        List<LocalDateTime> arrivals = new ArrayList<>();
        List<LocalDateTime> departures = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                // Split the line by comma
                String[] parts = line.split(",");
                // Iterate over the parts to extract relevant information
                for (String part : parts) {
                    // Split each part by colon to get key and value
                    String[] keyValue = part.trim().split(":");
                    if (keyValue.length == 2) {
                        String key = keyValue[0].trim();
                        String value = keyValue[1].trim();
                        // Check if the key is "licensePlateNumber" and its value matches the input
                        if (key.equals("licensePlateNumber") && value.equals(licensePlateNumber)) {
                            found = true;
                            // Extract Arrival and Departure times
                            String arrivalString = parts[2].trim().substring("Arrival: ".length()).trim();
                            String departureString = parts[3].trim().substring("Departure: ".length()).trim();
                            // Convert the strings to LocalDateTime objects
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            LocalDateTime arrival = LocalDateTime.parse(arrivalString, formatter);
                            LocalDateTime departure = LocalDateTime.parse(departureString, formatter);
                            // Add them to the respective lists
                            arrivals.add(arrival);
                            departures.add(departure);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the arrival and departure times to the labels
        if (found) {
            StringBuilder arrivalText = new StringBuilder();
            for (LocalDateTime arrival : arrivals) {
                arrivalText.append(arrival).append("\n");
            }
            arrivalLabel.setText(arrivalText.toString());

            StringBuilder departureText = new StringBuilder();
            for (LocalDateTime departure : departures) {
                departureText.append(departure).append("\n");
            }
            departureLabel.setText(departureText.toString());
        } else {
            arrivalLabel.setText("Not found");
            departureLabel.setText("");
        }
    }
}

