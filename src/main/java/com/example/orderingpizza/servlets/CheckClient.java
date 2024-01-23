package com.example.orderingpizza.servlets;

import com.example.orderingpizza.db.DBDao;
import com.example.orderingpizza.db.task1;
import com.example.orderingpizza.model.Client;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/authorization")
public class CheckClient extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher =
                request.getServletContext().getRequestDispatcher("/templates/task1/authorization.jsp");
        dispatcher.forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html");
        task1 dao = new DBDao();
        Client clientToAdd = new Client(req);
        try {
            dao.add(clientToAdd);
            dao.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
    }
}