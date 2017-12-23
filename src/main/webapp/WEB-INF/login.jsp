<%--
  Created by IntelliJ IDEA.
  User: Boogie
  Date: 2017/12/18
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>用户登录</title>
    <link rel="stylesheet" href="../statics/bootstrap/css/bootstrap.min.css" />
    <script src="../statics/bootstrap/js/jquery.min.js"></script>
    <script src="../statics/bootstrap/js/bootstrap.js"></script>
    <script src="../statics/definition/login.js"></script>

    <style type="text/css">
        body {
            background:url(../statics/image/loginBG.jpg) #f8f6e9;
            background-size: 100% 100%;
            -moz-background-size:100% 100%;
        }
        .mycenter{
            margin-top: 100px;
            margin-left: auto;
            margin-right: auto;
            height: 350px;
            width:500px;
            padding: 5%;
            padding-left: 5%;
            padding-right: 5%;
        }
        .mycenter mysign{
            width: 440px;
        }
        .mycenter input,checkbox,button{
            margin-top:2%;
            margin-left: 10%;
            margin-right: 10%;
        }
        .mycheckbox{
            margin-top:10px;
            margin-left: 40px;
            margin-bottom: 10px;
            height: 10px;
        }


    </style>
</head>

<body>
<form  id="form1" action="/UserLogin.xhtml" target="frameResult" method="post">

    <div class="mycenter"><!-- start mycenter -->
        <div class="mysign"><!-- start mysign -->

            <div class="col-xs-11 col-lg-11  text-center"><!-- start loginText -->
                <h2 style="color: white">登录</h2>
            </div><!-- end loginText -->

            <div class="col-xs-10 col-lg-10">
                <input type="text" class="form-control" name="loginName" placeholder="请输入账户名" required autofocus/>
            </div>

            <div class="col-xs-10 col-lg-10"></div>
            <div class="col-xs-10 col-lg-10">
                <input type="password" class="form-control" name="password" placeholder="请输入密码" required autofocus/>
            </div>

            <div class="col-xs-10 col-lg-10"></div>
            <div class="col-xs-10 col-lg-10 mycheckbox checkbox">
                <input type="checkbox" class="col-lg-1">记住密码</input>
            </div>

            <div class="col-xs-10 col-lg-10"></div>
            <div class="col-xs-10 col-lg-10">
                <button type="button" class="btn btn-success col-xs-12 col-lg-12" id="Submit">登录</button>
            </div>

        </div><!-- end mysign -->
    </div><!-- end mycenter -->
</form>


<iframe name="frameResult" style="display:none">
</iframe>

</body>
</html>
