<%--
  Created by IntelliJ IDEA.
  User: julia
  Date: 19.03.2023
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Order Page</title>
    <script>
        function viewConstruktor() {
            if (document.getElementById('myPizza').checked & document.getElementById('ingridientsForMyPizza').style.display === 'none') {
                document.getElementById('ingridientsForMyPizza').style.display = "block";
            } else {
                document.getElementById('ingridientsForMyPizza').style.display = "none";
            }
            return false;
        }

        function check() {
            if (document.getElementById('ul1').style.display === 'none') {
                document.getElementById('ul1').style.display = "block";
            } else {
                document.getElementById('ul1').style.display = "none";
            }
            return false;

        }
    </script>
</head>


<style>
    body {
        background-color: burlywood;
    }

    ul {
        list-style-type: none;
    }

    li {
        width: 400px;
        margin: 5px;
        height: 25px;
        border: brown;
        color: brown;
        border-radius: 10px;
        font-size: 20px;
        display: flex;
        justify-content: flex-start;
        align-items: center;
        border: 1px solid gray;
    }

    .base {
        font-size: 15px;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: flex-start;
        background: url("/images/pizza1.png");

        color: black;
        width: 100%;
        height: 100%;

    }

    input {
        margin: 10px;
        border-radius: 15px;
        width: 30px;
        height: 20px;
        font-size: 15px;
        background-color: burlywood;
    }

    .head {
        margin: 10px;
        font-size: 30px;
        font-weight: bold;
        font-family: "AGA Arabesque";
        color: brown;
    }

    .lastButton {
        height: 60px;
        margin-top: 60px;
        transition-duration: 1s;
    }

    .lastButton:hover {
        background: brown;
        transition-duration: 1s;
    }

    .big {
        width: 600px;
        height: 50px;
        font-size: 30px;
        font-weight: bolder;
    }

    .ul1 {
        display: block;
    }

    #ingridientsForMyPizza {
        display: none;
    }
    .map{
        position: absolute;
top:0px;
        font-weight: bold;
        font-size: 15px;
        left:500px;
    }
</style>
<body>
<div class="base">
    <div><h2> Здравствуйте <%=request.getAttribute("login")%>
        <%
            HttpSession httpSession = (HttpSession) request.getSession();
            httpSession.setAttribute("login", request.getAttribute("login"));
        %>

    </h2></div>

    <div class="head">Выберите пиццу :</div>

    <div>
        <form action="order" method="post">
            <ul>

                <c:forEach items="#{requestScope.pizzas}" begin="0" end="3" var="a">
                    <li> <input type="radio" name="pizza" id="${a}" value="${a}" onclick="viewConstruktor()">
                        ${a}
                            </li>

                    </c:forEach>






                <li>
                    <input type="radio" name="pizza" id="myPizza" value="myPizza" onclick="viewConstruktor()">
                    Пицца по собственному рецепту
                </li>

                <ul id="ingridientsForMyPizza">

                    <c:forEach items="#{requestScope.ingridients}" var="c">
                        <li><input type="checkbox" name="${c}" id="${c}" value="${c}">
                                ${c}
                        </li>

                    </c:forEach>

                </ul>


            </ul>


            <div class="head">Выбираем добавки ? <input type="checkbox"
                                                        checked="true" id="top" name="top" onchange="check()">
            </div>
            <div class="ul1" id="ul1">

                <ul>
                    <c:forEach items="#{requestScope.toppings}" var="b">
                        <li><input type="checkbox" name="${b}" id="${b}" value="${b}">
                                ${b}
                        </li>

                    </c:forEach>
                </ul>


            </div>
            <br>

            <span class="big" for="address">Введите адрес доставки : </span>
            <input class="big" type="text" name="address" id="address">


            <input class="lastButton" style="width: 100px;height: 30px" type="submit">

        </form>

        <div class="map">
            Зона доставки :
            <script type="text/javascript" charset="utf-8" async src="https://api-maps.yandex.ru/services/constructor/1.0/js/?um=constructor%3Ad9cf06abbc130e72d3fbdf345897b9280c31556e705ada781a818656a1f267f7&amp;width=692&amp;height=632&amp;lang=ru_RU&amp;scroll=true"></script>
        </div>
    </div>
</div>
</body>
</html>
