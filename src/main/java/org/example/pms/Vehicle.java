package org.example.pms;

public class Vehicle {
    private String licensePlateNumber;
    private String vehicleType;
    private String entryTime;

    public Vehicle(String licensePlateNumber, String vehicleType, String entryTime) {
        this.licensePlateNumber = licensePlateNumber;
        this.vehicleType = vehicleType;
        this.entryTime = entryTime;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getEntryTime() {
        return entryTime;
    }
}
