package com.example.orderingpizza.db;

import com.example.orderingpizza.model.Ingredient;
import com.example.orderingpizza.model.Pizza;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public interface task3 {
    List<Ingredient> allIngredients();
    List<Pizza> allPizzas();
    void addPizzaToPizzaTable(Pizza pizzaToAdd) throws SQLException;
    Pizza createClientPizza(HttpServletRequest request);
    void closeConnection();
}
