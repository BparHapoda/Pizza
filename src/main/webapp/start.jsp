<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pizza Hut v 0.9</title>
</head>
<style>
    body{
        background-color: burlywood;

    }
    .basement{
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;

    }

    .inputPizza1{width: 450px;
        height: 450px;
        margin: 20px;
        background: url("/images/pizza1.png") repeat-x scroll 0 0 transparent;
        padding: 0 0 0 25px !important;
        background-size: cover;
        cursor: pointer;
        border-radius: 50%;
        transition-duration: 1s;
        font-weight: bold;
        font-size: 40px;
        color: brown;
    }
    .inputPizza1:hover{
        margin: 20px;

        transition-duration: 1s;
        width: 540px;
        height: 540px;
        opacity: 85%;
    }

    .inputPizza2{
        font-weight: bold;
        font-size: 40px;
        color: brown;
        width: 450px;
        padding: 0 0 0 25px !important;
        height: 450px;
        margin: 20px;
        background: url("/images/pizza2.png") repeat-x scroll 0 0 transparent;
        padding: 0 0 0 25px !important;
        cursor: pointer;
        border-radius: 50%;
        background-size: cover;
        transition-duration: 1s;
    }
    .inputPizza2:hover{
        margin: 50px;
        transition-duration: 1s;
        width: 540px;
        height: 540px;
        opacity: 85%;
    }
    .buttons{
        display: flex;
        flex-direction: row;
    }
</style>
<body>
<div class="basement">
<h1><%= "PIzzA Online v0.9 " %>
</h1>
<br>
    <div class="buttons">

<form action="signUp" method="get">
<input class="inputPizza1" type="submit" value="РЕГИСТРАЦИЯ" ></form>

<form action="signIn" method="get">
    <input class="inputPizza2" type="submit" value="ВОЙТИ В СЕРВИС  ">

</form>
    </div>
</div>
</body>
</html>