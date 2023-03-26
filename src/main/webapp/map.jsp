<%--
  Created by IntelliJ IDEA.
  User: julia
  Date: 25.03.2023
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Быстрый старт. Размещение интерактивной карты на странице</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <script src="https://api-maps.yandex.ru/3.0/?apikey=3bbf19ad-7217-4c79-a602-e16546554244&lang=ru_RU"></script>
  <script>
    ymaps3.ready.then(init);
    function init() {
      const map = new ymaps3.YMap(document.getElementById('map'), {
        location: {
          center: [37.64, 55.76],
          zoom: 7
        }
      });
    }
  </script>
</head>

<body>
<div id="map" style="width: 600px; height: 400px"></div>
</body>
</html>