package com.example.orderingpizza.servlets;

import com.example.orderingpizza.db.DBDao;
import com.example.orderingpizza.model.Pizza;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/constructor")
public class PizzaConstructor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        completeRequest(request);
        RequestDispatcher dispatcher =
                request.getServletContext().getRequestDispatcher("/templates/task3/constructor.jsp");
        dispatcher.forward(request,response);
    }
    private void completeRequest(HttpServletRequest request) {
        DBDao dao = new DBDao();
        request.setAttribute("allPizzas", dao.allPizzas());
        request.setAttribute("allToppings", dao.allIngredients());
        dao.closeConnection();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("utf-8");
        DBDao dao = new DBDao();
        Pizza pizzaToAdd = dao.createClientPizza(req);
        System.out.println(pizzaToAdd);
        try {
            dao.addPizzaToPizzaTable(pizzaToAdd);
            dao.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
