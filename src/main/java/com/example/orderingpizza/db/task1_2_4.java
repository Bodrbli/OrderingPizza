package com.example.orderingpizza.db;

import com.example.orderingpizza.model.Client;
import com.example.orderingpizza.model.Ingredient;
import com.example.orderingpizza.model.Order;
import com.example.orderingpizza.model.Pizza;

import java.sql.SQLException;
import java.util.List;

public interface task1_2_4 {
    List<Pizza> allPizzas();
    List<Ingredient> allIngredients();
    void add(Client client) throws SQLException;
    void addDataToOrderTable(String clientId, String pizzaId, String toppingId) throws SQLException;
    Order getOrderValues(String pizzaId, String phone, String toppingID);
    void closeConnection();
}
