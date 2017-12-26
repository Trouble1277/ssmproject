<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/20
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="/statics/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/statics/bootstrap/css/bootstrap-table.css" rel="stylesheet">
    <script src="/statics/bootstrap/js/jquery.min.js"></script>
    <script src="/statics/bootstrap/js/bootstrap.min.js"></script>
    <script src="/statics/bootstrap/js/bootstrap-table.js"></script>
    <script src="/statics/bootstrap/js/bootstrap-editable.js"></script>
    <script src="/statics/bootstrap/js/bootstrap-table-zh-CN.js"></script>
    <script src="/statics/bootstrap/js/bootstrap-table-editable.js"></script>

    <script src="/statics/definition/userManage.js"></script>
</head>
<style type="text/css">

    body{
        height: 70%;
        margin-top: 4%;
    }
    .rightInput{
        margin-left: 75%;
        margin-top: -3%;
    }

</style>


<body>
    <div id="toolBar">
        <button type="button" id="add" class="btn btn-default navbar-btn">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            增加
        </button>

        <button type="button" id="del" class="btn btn-default navbar-btn">
            <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
            删除
        </button>

        <button type="button" id="save" class="btn btn-default navbar-btn">
            <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
            保存
        </button>

        <button type="button" id="" class="btn btn-default navbar-btn">
            <span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>
            撤销
        </button>

        <%--<form class="form-inline" id="userForm">--%>
            <%--<div class="form-group rightInput">--%>
                <%--<label for="exampleInputName2">UserName</label>--%>
                <%--<input type="text" name="userName" class="form-control" id="exampleInputName2" placeholder=" Please input user name">--%>
            <%--</div>--%>
        <%--</form>--%>
        <%----%>
    </div>


<table id="userTable"></table>
</body>
</html>
