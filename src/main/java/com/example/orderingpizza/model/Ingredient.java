package com.example.orderingpizza.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ingredient {
    private String id;
    private String title;

    public Ingredient(ResultSet resultSet) {
        try {
            this.id = resultSet.getString("id");
            this.title = resultSet.getString("title");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
