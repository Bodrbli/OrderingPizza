package com.example.orderingpizza.model;

import javax.servlet.http.HttpServletRequest;

public class Order {
    private String phone;
    private String clientId;
    private String pizzaId;
    private String toppingId;

    public Order(String clientId, String pizzaId, String toppingId) {
        this.clientId = clientId;
        this.pizzaId = pizzaId;
        this.toppingId = toppingId;
    }

    public String getPhone() {
        return phone;
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
