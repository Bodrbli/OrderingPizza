package com.example.orderingpizza.model;

public class Order {
    private String Id;
    private String clientId;
    private String pizzaId;
    private String toppingId;

    public Order(String clientId, String pizzaId, String toppingId) {
        this.clientId = clientId;
        this.pizzaId = pizzaId;
        this.toppingId = toppingId;
    }

    public String getId() {
        return Id;
    }

    public String getClientId() {
        return clientId;
    }

    public String getPizzaId() {
        return pizzaId;
    }

    public String getToppingId() {
        return toppingId;
    }
}
