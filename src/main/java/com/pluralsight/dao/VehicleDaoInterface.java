package com.pluralsight.dao;

import com.pluralsight.dataTables.Vehicle;

import java.util.List;

public interface VehicleDaoInterface
{
    List<Vehicle> getAllVehicle();
    List<Vehicle> getVehiclesPrice(double min, double max);
    List<Vehicle> getByMakeAndModel(String make, String model);
    List<Vehicle> getByYear(int start, int end);
    List<Vehicle> getByColor(String color);
    List<Vehicle> getByMileage(int min, int max);
    List<Vehicle> getByType(String type);


    void addVehicle(String vin, int year, String make, String model, String type, String color, int mileage, double price, boolean status);
    void removeVehicle (String vin);
    boolean vehicleStatus (String vin);
}