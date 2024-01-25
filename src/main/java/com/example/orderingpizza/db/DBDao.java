package com.example.orderingpizza.db;

import com.example.orderingpizza.model.Client;
import com.example.orderingpizza.model.Pizza;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDao {
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

    public List<Pizza> allPizzas() {
        String query = "SELECT * FROM pizzas;";
        try {
            ResultSet result = connection.createStatement().executeQuery(query);
            return getPizzaListFromResultSet(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Client add(Client client) throws SQLException {
        String query = String.format("INSERT INTO clients (name, phone, email, address) " +
                "VALUES ('%s', '%s', '%s', '%s')", client.getName(), client.getPhone(), client.getEmail(), client.getAddress());
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        return client;
    }

    public void addDataToOrderColumn(String pizzaId, String phone) {
        String query = String.format("INSERT INTO orders (clientid, pizzaid, createdat) \n" +
                "VALUES ('%s', '%s', 'NOW');", getClientByPhone(phone).getId(), pizzaId);
    }

    public List<String> allToppings() {
        String query = "SELECT * FROM ingredients;";
        try {
            ResultSet result = connection.createStatement().executeQuery(query);
            return getStringListFromResultSet(result, "title");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Pizza> getPizzaListFromResultSet(ResultSet result) throws SQLException {//получение списка блокнотов из результсета
        List<Pizza> all = new ArrayList<>();
        while (result.next()) {
            all.add(new Pizza(result));
        }
        return all;
    }

    private List<String> getStringListFromResultSet(ResultSet result, String column) throws SQLException {//получение списка строк из результсета
        List<String> resultAsString = new ArrayList<>();
        while (result.next()) {
            resultAsString.add(result.getString(column));
        }
        return resultAsString;
    }

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

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
