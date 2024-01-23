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
      <input class="inputText" name="name" type="text" placeholder="name">
      <input class="inputText" name="phone" type="text" placeholder="phone">
      <input class="inputText" name="email" type="text" placeholder="email">
      <input class="inputText" name="town" type="text" placeholder="town">
      <input class="inputText" name="street" type="text" placeholder="street">
      <input class="inputText" name="building" type="text" placeholder="building">
      <input class="inputText" name="flat" type="text" placeholder="flat">
      <input class="inputSubmit" type="submit" value="sign up">
    </form>

  </div>
</div>
</body>
</html>
