<%@ page import="com.example.orderingpizza.model.Ingredient" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.orderingpizza.model.Pizza" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Constructor</title>
    <link rel="stylesheet" href="../../styles/style.css">
</head>
<body>
<div class="container">
  <div class="modal">
    <form class="form" method="post" action="${pageContext.request.contextPath}/constructor">
      <%List<Pizza> pizzas = (List<Pizza>) request.getAttribute("allPizzas");%>
      <%List<Ingredient> toppings = (List<Ingredient>) request.getAttribute("allToppings");%>

      <h3>Введите название</h3>
      <p>название должно быть уникальным</p>
      <input class="inputText" name="title" type="text" placeholder="title">

      <h3>Выберите состав</h3>
      <%for (Ingredient topping : toppings) {%>
            <p>
            <input type="checkbox" name="topping" value="<%=topping.getId()%>"> <%=topping.getTitle()%>
            </p>
      <%}%>
      <input class="inputSubmit" type="submit" value="create">
    </form>
  </div>
</div>
</body>
</html>
