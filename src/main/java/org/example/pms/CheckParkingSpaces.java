package org.example.pms;

public class CheckParkingSpaces {/*
@FXML
    public void CheckParkingSpaces(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("CheckParkingSpaces.fxml"));
        Parent root = fxmlLoader.load();

        // Create a new stage
        Stage mainWindowStage = new Stage();
        mainWindowStage.setTitle("Parking Spaces");
        mainWindowStage.setScene(new Scene(root, 1000, 800));
        mainWindowStage.setResizable(false);
        mainWindowStage.show();

    }


    public static int lineNumber;
    public static String str;
    private static Label parkingSpaces;
    public static Label availableSpaces;
    public static Label reservedSpaces;
    private static Label vehiclePlates;

    public static void setReservedSpaces(Label reservedSpaces) {
        CheckParkingSpaces.reservedSpaces = reservedSpaces;
    }

    public static void setAvailableSpaces(Label availableSpaces) {
        CheckParkingSpaces.availableSpaces = availableSpaces;
    }
    public static void setLineNumber(int lineNumber){
        CheckParkingSpaces.lineNumber = lineNumber;
    }

    @FXML
    public void initialize() {
        // Call existVehicles method to populate labels
        existVehicles();
    }


    @FXML
    public void existVehicles(){
        String filename = "C:\\Users\\PRABO\\OneDrive\\Desktop\\java\\JAVAFX\\PMS\\src\\main\\resources\\org\\example\\pms\\removed_Vehicles.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            lineNumber = 0;
            int totalLinesRead = 0;

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

            // Update label texts
            reservedSpaces.setText(String.valueOf(lineNumber));
            availableSpaces.setText(String.valueOf(100 - lineNumber));
            System.out.println("Total lines read (excluding the first line): " + totalLinesRead);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/
    }


