package com.example.orderingpizza.db;

import com.example.orderingpizza.model.Client;
import com.example.orderingpizza.model.Ingredient;
import com.example.orderingpizza.model.Order;
import com.example.orderingpizza.model.Pizza;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDao implements task1_2_4, task3 {
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
    public List<Pizza> allPizzas() {
        String query = "SELECT * FROM pizzas;";
        try {
            ResultSet result = connection.createStatement().executeQuery(query);
            return getPizzaListFromResultSet(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Pizza> getPizzaListFromResultSet(ResultSet result) throws SQLException {
        List<Pizza> all = new ArrayList<>();
        while (result.next()) {
            all.add(new Pizza(result));
        }
        return all;
    }

    @Override
    public List<Ingredient> allIngredients() {
        String query = "SELECT * FROM ingredients;";
        try {
            ResultSet result = connection.createStatement().executeQuery(query);
            return getIngredientListFromResultSet(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Ingredient> getIngredientListFromResultSet(ResultSet result) throws SQLException {
        List<Ingredient> all = new ArrayList<>();
        while (result.next()) {
            all.add(new Ingredient(result));
        }
        return all;
    }

    @Override
    public void add(Client client) throws SQLException {
        String query = String.format("INSERT INTO clients (name, phone, email, address) " +
                "VALUES ('%s', '%s', '%s', '%s')", client.getName(), client.getPhone(), client.getEmail(), client.getAddress());
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    @Override
    public void addDataToOrderTable(String clientId, String pizzaId, String toppingId) throws SQLException {
        String query = String.format("INSERT INTO orders (clientid, pizzaid, createdat, toppingid) \n" +
                "VALUES ('%s', '%s', 'NOW', '%s');", clientId, pizzaId, toppingId);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    /*private List<String> getStringListFromResultSet(ResultSet result, String column) throws SQLException {//получение списка строк из результсета
        List<String> resultAsString = new ArrayList<>();
        while (result.next()) {
            resultAsString.add(result.getString(column));
        }
        return resultAsString;
    }*/

    @Override
    public Order getOrderValues(String pizzaId, String phone, String toppingID) {
        return new Order(getClientIdByPhone(phone), pizzaId, toppingID);
    }

    private String getClientIdByPhone(String phone) {
        try {
            String query = String.format("SELECT * FROM clients WHERE phone = '%s';", phone);
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            resultSet.next();
            System.out.println(resultSet.getString("id"));
            return resultSet.getString("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addPizzaToPizzaTable(Pizza pizzaToAdd) throws SQLException {
        String query = String.format("INSERT INTO pizzas (title, recipe) \n" +
                "VALUES ('%s', '%s');", pizzaToAdd.getTitle(), pizzaToAdd.getRecipe());
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    @Override
    public Pizza createClientPizza(HttpServletRequest request) {
        List<String> ingredientsFromRequest = getIngredientsFromRequest(request);
        StringBuilder sb = new StringBuilder();

        for (String ingredient : ingredientsFromRequest) {
            if (!ingredient.equals(ingredientsFromRequest.get(ingredientsFromRequest.size()-1))) {
                sb.append(ingredient).append(", ");
            } else {
                sb.append(ingredient);
            }
        }
        String recipe = sb.toString();
        return new Pizza(request.getParameter("title"), recipe);
    }

    private List<String> getIngredientsFromRequest(HttpServletRequest request) {
        List<String> createdPizza = new ArrayList<>();
        String id;
        String[] reqIngr = request.getParameterValues("topping");
        for (Ingredient ingredient : allIngredients()) {
            id = ingredient.getId();
            for (String ingr : reqIngr) {
                System.out.println(ingr);
                if (ingr.equals(id)) {
                    createdPizza.add(ingredient.getTitle());
                }
            }
        }
        return createdPizza;
    }

    public String checkClientAddress(String address) {
        return null;
    }

    public String getOrderInfo(Order order) {
        String query ="";
        return null;
    }

    @Override
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
