<%@ page import="com.example.orderingpizza.model.Pizza" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>authorization</title>
    <link rel="stylesheet" href="../../styles/style.css">
</head>
<body>
<div class="container">
  <div class="modal">
    <form class="form" method="post" action="${pageContext.request.contextPath}/authorization">
      <%List<Pizza> pizzas = (List<Pizza>) request.getAttribute("allPizzas");%>
      <%List<String> toppings = (List<String>) request.getAttribute("allToppings");%>

      <h3>Выберите пиццу</h3>
      <select class="requestInput" name="pizza">
        <%for (Pizza pizza : pizzas) {%>
        <option value="<%=pizza.getId()%>"><%=pizza%></option>
        <%}%>
      </select>

      <h3>Выберите топпинг</h3>
      <select class="requestInput" name="topping">
        <%for (String topping : toppings) {%>
        <option><%=topping%></option>
        <%}%>
      </select>

      <h3>Укажите адресс доставки</h3>
      <input class="inputText" name="name" type="text" placeholder="name">
      <input class="inputText" name="phone" type="text" placeholder="phone">
      <input class="inputText" name="email" type="text" placeholder="email">
      <input class="inputText" name="town" type="text" placeholder="town">
      <input class="inputText" name="street" type="text" placeholder="street">
      <input class="inputText" name="building" type="text" placeholder="building">
      <input class="inputText" name="flat" type="text" placeholder="flat">
      <br>
      <input class="inputSubmit" type="submit" value="sign up">
    </form>
  </div>
</div>
</body>
</html>
