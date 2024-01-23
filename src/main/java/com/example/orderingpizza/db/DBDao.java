package com.example.orderingpizza.db;

import com.example.orderingpizza.model.Client;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

public class DBDao implements task1 {
    private final String DB_URL = "jdbc:postgresql://localhost:5432/Pizza";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "postgres";
    private final String DB_DRIVER = "org.postgresql.Driver";
    private final Connection connection;

    public DBDao() {
        try {
            Class.forName(DB_DRIVER);
            this.connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("подключение невозможно");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("драйвер не распокован");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Client getClientByPhone(String phone) {
        String query = String.format("SELECT * FROM clients WHERE phone = '%s';", phone);
        ResultSet resultSet = null;
        try {
            resultSet = connection.createStatement().executeQuery(query);
            resultSet.next();
            return new Client(resultSet.getString("name"), resultSet.getString("phone"),
                    resultSet.getString("email"), resultSet.getString("address"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Client add(Client client) throws SQLException {
        String query = String.format("INSERT INTO clients (name, phone, email, address) " +
                "VALUES ('%s', '%s', '%s', '%s')", client.getName(), client.getPhone(), client.getEmail(), client.getAddress());
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        return client;
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
