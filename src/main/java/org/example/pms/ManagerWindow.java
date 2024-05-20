package org.example.pms;

import javafx.event.ActionEvent;

import java.io.IOException;

public class ManagerWindow implements WindowManager{
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
    public void previousDetailsM(ActionEvent event) throws IOException {
        WindowManager.super.previousDetailsM(event);
    }

    @Override
    public void logout(ActionEvent event) throws IOException {
        WindowManager.super.logout(event);
    }
}
