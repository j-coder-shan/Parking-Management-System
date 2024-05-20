package org.example.pms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class parkingStatus extends ParkingAttendantWindow {


    @FXML
    private void logout_PS(ActionEvent event) throws IOException {
        logout(event);
    }

    public void addVehicle_PS(ActionEvent event) throws IOException {
        addVehicle(event);
    }

    public void removeVehicle_PS(ActionEvent event) throws IOException {
        removeVehicle(event);
    }




}
