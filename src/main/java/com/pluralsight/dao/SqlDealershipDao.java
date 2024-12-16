package com.pluralsight.dao;

import com.pluralsight.model.Dealership;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlDealershipDao
{
    private final DataSource dataSource;

    @Autowired
    public SqlDealershipDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public Dealership addDealer(Dealership dealership)
    {
        String query = "INSERT INTO Dealerships (name, address, phone)" +
                       "VALUES (?,?,?,)";
        String getInsertId = "SELECT last_insert_id() as ID";

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement preparedStatement = conn.preparedStatement(query);
            preparedStatement.setString(1, dealership.getName());
            preparedStatement.setString(2, dealership.getAddress());
            preparedStatement.setString(3, dealership.getPhone());

            int rows = preparedStatement.executeUpdate();
            System.out.println("rows = " + rows + "have been updated");

            preparedStatement = conn.prepareStatement(getInsertId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                dealership.setId(resultSet.getString("ID"));
                return findDealerById(newDealerId);
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }return null;
    } 
}




