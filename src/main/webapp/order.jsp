<%--
  Created by IntelliJ IDEA.
  User: julia
  Date: 19.03.2023
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Page</title>
  <h2>  Здравствуйте <%=request.getAttribute("login")%> </h2>

  <h2>Выберите пиццу : </h2>
    <form action="order" method="post">
        <div>
        <Label for="margarita">Маргарита</Label>
        <input type="radio" name="pizza" id="margarita" value="margarita">
            <br>
            <Label for="capr">Капричоза</Label>
        <input type="radio" name="pizza" id="capr">
            <br>
            <Label for="four">Четыре сыра</Label>
        <input type="radio" name="pizza" id="four">
            <br>
            <Label for="hawaji">Гавайская</Label>
        <input type="radio" name="pizza" id="hawaji">
            <br><br>
            <Label for="address">Введите адрес доставки : </Label>
        <input type="text" name="address" id="address">
            <br> <input type="submit">
        </div>
    </form>
</head>
<body>

</body>
</html>
