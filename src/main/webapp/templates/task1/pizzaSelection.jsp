<%@ page import="com.example.orderingpizza.model.Pizza" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/pizzaSelection">
</head>
<body>
<div class="container">
    <div class="modal">
    <%List<Pizza> pizzas = (List<Pizza>) request.getAttribute("allPizzas");%>

        <h3>Выберите пиццу</h3>
        <form class="requestForm" method="get" action="${pageContext.request.contextPath}/pizzaSelection">
            <select class="requestInput" name="pizza">
                <%for (Pizza pizza : pizzas) {%>
                <option><%=pizza%></option>
                <%}%>
            </select>
            <input class="inputSubmit" type="submit" value="sign up">
        </form>
    </div>
</div>
</body>
</html>
