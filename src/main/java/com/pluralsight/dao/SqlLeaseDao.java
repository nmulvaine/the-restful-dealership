package com.pluralsight.dao;

import com.pluralsight.model.LeaseContract;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SqlLeaseDao implements LeaseDao
{
    private DataSource dataSource;

    public SqlLeaseDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public void addLeaseContract(String vin, LocalDate date, String name, String contractId, double amount, double monthlyAmount, int termLength)
    {

        String query = """
                insert into lease_contracts (VIN, contractDate, customerName, customerId, amount, monthlyAmount, termLength)
                values (?,?,?,?,?,?,?)
                """;

        try (
                Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, vin);
            ps.setDate(2, java.sql.Date.valueOf(date));
            ps.setString(3, name);
            ps.setString(4, contractId);
            ps.setDouble(5, amount);
            ps.setDouble(6, monthlyAmount);
            ps.setInt(7, termLength);
            int rc = ps.executeUpdate();

            System.out.println("Congratulations on your vehicle!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double calculatePayment(double price, int leaseTerm)
    {
        return price / leaseTerm;
    }

    @Override
    public List<LeaseContract> getLeaseContracts()
    {
        List<LeaseContract> leaseContracts = new ArrayList<>();

        String query = """
                SELECT * FROM lease_contracts
                """;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String vin = rs.getString("VIN");
                LocalDate date = rs.getDate("contractDate").toLocalDate();
                String name = rs.getString("customerName");
                String contractId = rs.getString("contractId");
                double amount = rs.getDouble("amount");
                double monthlyAmount = rs.getDouble("monthlyAmount");
                int termLength = rs.getInt("termLength");

                LeaseContract leaseContract = new LeaseContract(vin, date, name, contractId, amount, monthlyAmount, termLength);
                leaseContracts.add(leaseContract);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return leaseContracts;
    }
}
