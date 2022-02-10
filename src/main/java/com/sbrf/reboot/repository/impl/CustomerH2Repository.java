package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;

import lombok.Cleanup;
import lombok.NonNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

public class CustomerH2Repository implements CustomerRepository {

    private static final String DB_URL = "jdbc:h2:~/my_db";

    private static final String USER = "sa";
    private static final String PASS = "";

    private Optional<Connection> getConnection() throws SQLException {
        return Optional.of(DriverManager.getConnection(DB_URL, USER, PASS));
    }

    @Override
    public void createTableCustomer() {
        try (Connection connection = this.getConnection().orElseThrow(SQLException::new)) {
            String sql =
                    "drop table if exists customer;" +
                    "create table customer (" +
                        "id int primary key auto_increment," +
                        "name varchar(255) not null," +
                        "eMail varchar(255)," +
                        "unique (name, eMail)" +
                    ");";
            @Cleanup PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();

        try (Connection connection = this.getConnection().orElseThrow(SQLException::new)) {
            @Cleanup PreparedStatement statement = connection.prepareStatement("select * from customer");
            @Cleanup ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customers.add(new Customer(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("eMail")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public boolean createCustomer(@NonNull String name, String eMail) {
        try (Connection connection = this.getConnection().orElseThrow(SQLException::new)) {
            @Cleanup PreparedStatement statement = connection.prepareStatement("insert into customer (name, eMail) values (?, ?)");
            statement.setString(1, name);
            statement.setString(2, eMail);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean customerWithNameExist(@NonNull String name) {
        try (Connection connection = this.getConnection().orElseThrow(SQLException::new)) {
            @Cleanup PreparedStatement statement = connection.prepareStatement("select count(*) as count_customer from customer where name = ?");
            statement.setString(1, name);
            @Cleanup ResultSet resultSet = statement.executeQuery();
            return resultSet.next() && resultSet.getInt("count_customer") > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}


