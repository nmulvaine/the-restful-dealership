package com.pluralsight.dao;

import com.pluralsight.model.Vehicle;

import java.util.List;

public interface VehicleDaoInterface
{
 List<Vehicle> getAllVehicle();

 void addVehicle(String vin, int year, String make, String model, String type, String color, int mileage, double price, boolean status);

 void removeVehicle(String vin);
}
