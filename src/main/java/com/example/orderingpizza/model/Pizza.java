package com.example.orderingpizza.model;

public class Pizza {
    private String title;
    private String recipe;

    public Pizza(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getRecipe() {
        return recipe;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "title='" + title + '\'' +
                ", recipe='" + recipe + '\'' +
                '}';
    }
}
