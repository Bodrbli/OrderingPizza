package com.example.orderingpizza.servlets;

import com.example.orderingpizza.db.DBDao;
import com.example.orderingpizza.model.Client;
import com.example.orderingpizza.model.Order;

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
        completeRequest(request);
        RequestDispatcher dispatcher =
                request.getServletContext().getRequestDispatcher("/templates/task1/authorization.jsp");
        dispatcher.forward(request,response);
    }
    private void completeRequest(HttpServletRequest request) {
        DBDao dao = new DBDao();
        request.setAttribute("allPizzas", dao.allPizzas());
        request.setAttribute("allToppings", dao.allIngredients());
        dao.closeConnection();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html");
        DBDao dao = new DBDao();
        Client clientToAdd = new Client(req);
        try {
            dao.add(clientToAdd);
            Order orderToAdd = dao.getOrderValues(req.getParameter("pizza"), req.getParameter("phone"), req.getParameter("topping"));
            dao.addDataToOrderTable(orderToAdd.getClientId(), orderToAdd.getPizzaId(), orderToAdd.getToppingId());
            dao.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
    }
}