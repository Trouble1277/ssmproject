<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/22
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="/statics/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/statics/bootstrap/css/bootstrap-table.css">

    <script src="/statics/bootstrap/js/jquery.min.js"></script>
    <script src="/statics/bootstrap/js/bootstrap.min.js"></script>
    <script src="/statics/bootstrap/js/bootstrap-table.js"></script>

    <script src="/statics/bootstrap/js/bootstrap-table-zh-CN.js"></script>
    <script src="/statics/bootstrap/js/bootstrap-table-editable.js"></script>
    <script src="/statics/definition/project.js"></script>
</head>
<body>
<div>
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
    <button type="button" id="revo" class="btn btn-default navbar-btn">
        <span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>
        撤销
    </button>

        <%--弹出框--%>
    <a id="example" tabindex="0" role="button" data-toggle="popover" data-trigger="focus"></a>

</div>
<table id="projectTable"></table>
</body>
</html>
