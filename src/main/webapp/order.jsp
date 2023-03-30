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
    <script src="https://api-maps.yandex.ru/2.1/?apikey=08d4108c-ff62-4810-8e5a-6bca59ef5d40&lang=ru_RU" type="text/javascript"></script>
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
        width: 400px;
        margin: 20px;
        height: 25px;
        border: brown;
        border: 1px solid gray;
        color: brown;
        border-radius: 10px;
        font-size: 20px;
    }

    .ul1 {
        display: block;
    }

    #ingridientsForMyPizza {
        display: none;
    }
    .map{
        position: absolute;
top:50px;
        font-weight: bold;
        font-size: 15px;
        left:500px;
        width:500px;
        height:500px;
        background: black;
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
            <br>
            <input class="big" type="text" name="city" id="city" placeholder="Город">
            <br>
            <input class="big" type="text" name="street" id="street" placeholder="Улица">
            <br>
            <input class="big" type="text" name="house" id="house" placeholder="Дом">
            <br>
            <input class="big" type="text" name="korpus" id="korpus" placeholder="Корпус">
            <br>
            <input class="big" type="text" name="flate" id="flate" PLACEHOLDER="Квартира">
            <br>

            <input class="lastButton" style="width: 100px;height: 30px" type="submit">

        </form>

        <div id="map" class="map">
            Зона доставки :

        </div>

        <script type="text/javascript">
            ymaps.ready(init);
            function init (){
                var map=new ymaps.Map('map',
                    {center:[60.04309884925572,30.361214369988822],
                        zoom: 10});
            }
            var myGeoObject = new ymaps.GeoObject({
                // Описываем геометрию геообъекта.
                geometry: {
                    // Тип геометрии - "Многоугольник".
                    type: "Polygon",
                    // Указываем координаты вершин многоугольника.
                    coordinates: [
                        // Координаты вершин внешнего контура.
                        [
                            [60.09, 30.36],
                            [60.06, 30.19],
                            [60.02, 30.25],
                            [60.03, 30.43]

                        ],
                        // Координаты вершин внутреннего контура.
                        [
                            [60.09, 30.36],
                            [60.06, 30.19],
                            [60.02, 30.25],
                            [60.03, 30.43]
                        ]
                    ],
                    // Задаем правило заливки внутренних контуров по алгоритму "nonZero".
                    fillRule: "nonZero"
                },
                // Описываем свойства геообъекта.
                properties:{
                    // Содержимое балуна.
                    balloonContent: "Многоугольник"
                }
            }, {
                // Описываем опции геообъекта.
                // Цвет заливки.
                fillColor: '#00FF00',
                // Цвет обводки.
                strokeColor: '#0000FF',
                // Общая прозрачность (как для заливки, так и для обводки).
                opacity: 0.5,
                // Ширина обводки.
                strokeWidth: 5,
                // Стиль обводки.
                strokeStyle: 'shortdash'
            });

            // Добавляем многоугольник на карту.
            map.geoObjects.add(myGeoObject);


            var myPolygon = new ymaps.Polygon([
                // Указываем координаты вершин многоугольника.
                // Координаты вершин внешнего контура.
                [
                    [60.09, 30.36],
                    [60.06, 30.19],
                    [60.02, 30.25],
                    [60.03, 30.43]
                ],
                // Координаты вершин внутреннего контура.
                [
                    [60.09, 30.36],
                    [60.06, 30.19],
                    [60.02, 30.25],
                    [60.03, 30.43]
                ]
            ], {
                // Описываем свойства геообъекта.
                // Содержимое балуна.
                hintContent: "Многоугольник"
            }, {
                // Задаем опции геообъекта.
                // Цвет заливки.
                fillColor: '#00FF0088',
                // Ширина обводки.
                strokeWidth: 5
            });

            // Добавляем многоугольник на карту.
            map.geoObjects.add(myPolygon);












        </script>
    </div>
</div>
</body>
</html>
