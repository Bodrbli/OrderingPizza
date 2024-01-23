package com.example.orderingpizza.db;

import com.example.orderingpizza.model.Client;

import java.sql.SQLException;

public interface task1 {
    Client getClientByPhone(String phone);

    Client add(Client client) throws SQLException;
    void closeConnection() throws SQLException;
}
