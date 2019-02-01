<%--
  Created by IntelliJ IDEA.
  User: ENVY 13-ad110tu
  Date: 2019/2/1
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>激活成功</title>
    <script type="text/javascript" src="../../js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="../../js/jquery-3.2.1.js"></script>
    <style>
        html{
            background: #fffbea;
        }
        .container{
            font-family: 华文楷体,serif;

            width: 70%;
            margin-left: auto;
            margin-right: auto;
            margin-top: 235px;
        }
        .page-title{
            text-align: center;
            font-size: 30px;
            font-weight: bold;
        }
        .btn{
            outline: none;
            font-family: 华文楷体, serif;
            width: 150px;
            font-size: 25px;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="page-title">
        <p>激活成功，点击下方按钮登录</p>
    </div>
    <div style="text-align: center">
        <button class="btn" id="toLogin">我要登录</button>
    </div>
</div>
<script>
    $("#toLogin").click(function () {
        window.location.href="/login";
    })
</script>
</body>
</html>