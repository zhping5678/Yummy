<%--
  Created by IntelliJ IDEA.
  User: ENVY 13-ad110tu
  Date: 2019/1/31
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>UserRegister</title>
    <script type="text/javascript" src="../../js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="../../js/jquery-3.2.1.js"></script>
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
        .input-box{
            width: 675px;
            height: 40px;
            text-align: center;
            margin-bottom: 15px;
        }
        .btn{
            outline: none;
            font-family: 华文楷体, serif;
            width: 150px;
            font-size: 25px;
            margin-top: 20px;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="page-title">
        <p>欢迎注册成为Yummy用户!</p>
    </div>
    <div style="text-align: center">
        <input class="input-box" id="email-input" type="text" placeholder="请输入邮箱账号">
        <div></div>
        <input class="input-box" id="password-input" type="password" placeholder="请输入8-12位密码">
        <div></div>
        <input class="input-box" id="confirm-input" type="password" placeholder="请确认密码">
    </div>
    <div style="text-align: center">
        <button class="btn" id="signup">确认注册</button>
    </div>
</div>

<script>
    $("#signup").click(function () {
        var email = $("#email-input").val();
        var pass = $("#password-input").val();
        var confirm = $("#confirm-input").val();
        var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
        if(email === "" || pass === "" || confirm === ""){
            alert("请将邮箱和密码填写完全")
        }else if(!reg.test(email)){
            alert("邮箱格式不符, 请检查")
        }else if(pass.length < 8 || pass.length > 12 ){
            alert("请输入长度为8-12位的密码")
        }else if(pass !== confirm) {
            alert("两次输入密码不一致")
        }else {
            console.log("ajax读取到的数据："+email+" ,"+pass+", "+confirm);
            $.ajax({
                url: "/userSignUp",
                type: "POST",
                cache: false,
                async: false,
                data:{
                    email: email,
                    password: pass
                },
                success: function (data) {
                    console.log(data.toString());
                    var parsed = JSON.parse(data);
                    console.log("parse后的data："+parsed);
                    if(parsed === "SUCCESS"){
                        alert("已向您的邮箱发送邮件，请激活")
                    }else if(parsed === "EXIST"){
                        alert("该邮箱已被注册，请重新注册")
                    }else{
                        alert("出现未知错误，请重试")
                    }
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    alert("错误状态码："+XMLHttpRequest.status+"\n错误信息："+textStatus+"\n"+errorThrown);
                }
            })
        }
    })
</script>
</body>
</html>