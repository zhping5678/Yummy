<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">-->

    <style>
        html{
            margin: 0;
            padding: 0;
            background: #fffbea;
        }
        body{
            margin: 0;
            padding: 0;
        }
        .container{
            font-family: 华文楷体,serif;

            width: 70%;
            margin-left: auto;
            margin-right: auto;
            margin-top: 120px;
        }
        .page-title{
            text-align: center;
            font-size: 60px;
            font-weight: bold;
        }
        .btn{
            outline: none;
            font-family: 华文楷体, serif;
            width: 280px;
            font-size: 25px;
            margin-bottom: 15px;
            border: none;
            cursor: pointer;

        }
    </style>
</head>
<body>
<div class="container">
    <div class="page-title">
        <p>欢迎来到Yummy!</p>
    </div>
    <div class="btns" style="text-align: center">
        <div>
            <button class="btn" id="login" onclick="location.href='/login'">登录</button>
        </div>
        <div>
            <button class="btn" id="register" onclick="location.href='/userRegister'">注册</button>
        </div>
        <div>
            <button class="btn" id="new-store" onclick="location.href='/storeRegister'">我要开店</button>
        </div>
    </div>
</div>
</body>
</html>