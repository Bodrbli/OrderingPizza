package com.example.orderingpizza.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Pizza {
    private String id;
    private String title;
    private String recipe;

    public Pizza(String title) {
        this.title = title;
    }

    public Pizza(ResultSet result) {
        try {
            this.id = result.getString("id");
            this.title = result.getString("title");
            this.recipe = result.getString("recipe");
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

    public String getRecipe() {
        return recipe;
    }

    @Override
    public String toString() {
        return "Pizza"  + title + '\'' +
                ", recipe'" + recipe + '\'';
    }
}
