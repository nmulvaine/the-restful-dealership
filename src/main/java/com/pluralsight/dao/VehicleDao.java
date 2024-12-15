package com.pluralsight.dao;

import com.pluralsight.dataTables.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleDao implements VehicleDaoInterface
{
    // Implementing the methods for interacting with the Vehicle database
    // TODO: Implement the database interactions
    private DataSource dataSource;

    @Autowired
    public VehicleDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
    };


    public List<Vehicle> getAllVehicleInfo()
    {
        List<Vehicle> vehicleList = new ArrayList<>();
        String query = "SELECT * FROM Vehicle";

        try (Connection conn = dataSource.getConnection())
        {
            PreparedStatement prepStatement = conn.prepareStatement(query);
            ResultSet rs = prepStatement.executeQuery();
            while (rs.next())
            {
                String vin = rs.getString("vin");
                String make = rs.getString("make");
                String model = rs.getString("model");
                String type = rs.getString("type");
                int year = rs.getInt("year");
                double price = rs.getDouble("price");
                String color = rs.getString("color");
                int mileage = rs.getInt("mileage");
                String condition = rs.getString("condition");
                String leaseStatus = rs.getString("leaseStatus");
                boolean available = rs.getBoolean("available");

                Vehicle v = new Vehicle(available, color, condition, leaseStatus, make, mileage, model, price, type, vin, year);
                vehicleList.add(v);
            }
            return vehicleList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vehicle> getAllVehicle()
    {
        return List.of();
    }

    @Override
    public List<Vehicle> getVehiclesPrice(double min, double max)
    {
        return List.of();
    }

    @Override
    public List<Vehicle> getByMakeAndModel(String make, String model)
    {
        return List.of();
    }

    @Override
    public List<Vehicle> getByYear(int start, int end)
    {
        return List.of();
    }

    @Override
    public List<Vehicle> getByColor(String color)
    {
        return List.of();
    }

    @Override
    public List<Vehicle> getByMileage(int min, int max)
    {
        return List.of();
    }

    @Override
    public List<Vehicle> getByType(String type)
    {
        return List.of();
    }

    @Override
    public void addVehicle(String vin, int year, String make, String model, String type, String color, int mileage, double price, boolean status)
    {

    }

    @Override
    public void removeVehicle(String vin)
    {

    }

    @Override
    public boolean vehicleStatus(String vin)
    {
        return false;
    }
}

