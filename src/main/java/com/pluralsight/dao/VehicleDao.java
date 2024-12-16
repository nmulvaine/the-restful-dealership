package com.pluralsight.dao;

import com.pluralsight.dataTables.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VehicleDao implements VehicleDaoInterface
{
    private final DataSource dataSource;

    @Autowired
    public VehicleDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public List<Vehicle> getAllVehicle()
    {
        List<Vehicle> vehicleList = new ArrayList<Vehicle>();
        String sql = "SELECT * FROM vehicles";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Vehicle vehicle = new Vehicle(
                        rs.getBoolean("available"),
                        rs.getString("color"),
                        rs.getString("condition"),
                        rs.getString("lease_status"),
                        rs.getString("make"),
                        rs.getInt("mileage"),
                        rs.getString("model"),
                        rs.getDouble("price"),
                        rs.getString("type"),
                        rs.getString("vin"),
                        rs.getInt("year"));
                vehicleList.add(vehicle);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return vehicleList;
    }

    @Override
    public void addVehicle(String vin, int year, String make, String model, String type, String color, int mileage, double price, boolean status)
    {
        String sql = "INSERT INTO vehicles (vin, year, make, model, type, color, mileage, price, available) VALUES (?,?,?,?,?,?,?,?,?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vin);
            stmt.setInt(2, year);
            stmt.setString(3, make);
            stmt.setString(4, model);
            stmt.setString(5, type);
            stmt.setString(6, color);
            stmt.setInt(7, mileage);
            stmt.setDouble(8, price);
            stmt.setBoolean(9, status);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeVehicle(String vin)
    {
        String sql = "DELETE FROM vehicles WHERE vin =?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vin);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


