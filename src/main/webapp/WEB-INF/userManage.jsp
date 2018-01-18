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
    <link href="/statics/ztree/css/demo.css" rel="stylesheet">
    <link href="/statics/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">

    <script src="/statics/bootstrap/js/jquery.min.js"></script>
    <script src="/statics/bootstrap/js/bootstrap.min.js"></script>
    <script src="/statics/bootstrap/js/bootstrap-table.js"></script>
    <script src="/statics/bootstrap/js/bootstrap-editable.js"></script>
    <script src="/statics/bootstrap/js/bootstrap-table-zh-CN.js"></script>
    <script src="/statics/bootstrap/js/bootstrap-table-editable.js"></script>
    <script src="/statics/ztree/js/jquery.ztree.core.js"></script>
    <script src="/statics/ztree/js/jquery.ztree.excheck.js"></script>

    <script src="../statics/js/jquerysession.js"></script>
    <script src="../statics/js/meseger.js"></script>
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
    .MenuBorder{
        /*width: 100px;*/
        position: absolute;
        margin-left: 10px;
        color: red;
        /*!*background-color: white;*!*/
    }

</style>


<body>
    <div id="toolBar">
        <button type="button" id="add" class="btn btn-default navbar-btn" style="display: none">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            增加
        </button>

        <button type="button" id="del" class="btn btn-default navbar-btn" style="display: none">
            <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
            删除
        </button>

        <button type="button" id="save"  class="btn btn-default navbar-btn" style="display: none"  ><!--data-toggle="modal" data-target="#myModal" onclick="UpdateData()" -->
            <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
            保存
        </button>

        <button type="button" id="cancel" class="btn btn-default navbar-btn" style="display: none">
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



    <%--修改的时候弹出的模态框--%>
    <%--<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">--%>
        <%--<div class="modal-dialog" role="document">--%>
            <%--<div class="modal-content">--%>
                <%--<div class="modal-header">--%>
                    <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" id="modalColose">&times;</span></button>--%>
                    <%--<h4 class="modal-title" id="myModalLabel">修改</h4>--%>
                <%--</div>--%>
                <%--<div class="modal-body">--%>

                    <%--<form id="updateUserForm" target="frameResult"><!-- action="/updateUser.xhtml" method="post"-->--%>
                        <%--<div class="modal-body">--%>

                            <%--<div class="form-group">--%>
                                <%--<label for="UserId" class="col-sm-3 control-label">serial</label>--%>
                                <%--<div class="col-sm-9">--%>
                                    <%--<input type="text" class="form-control" id="UserId" placeholder="userId" name="userId">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="LoginName" class="col-sm-3 control-label">Login Name</label>--%>
                                <%--<div class="col-sm-9">--%>
                                    <%--<input type="number" class="form-control" id="LoginName" placeholder="loginName" name="loginName">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="Password" class="col-sm-3 control-label">password</label>--%>
                                <%--<div class="col-sm-9">--%>
                                    <%--<input type="password" class="form-control" id="Password" placeholder="password" name="password">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="UserName" class="col-sm-3 control-label">userName</label>--%>
                                <%--<div class="col-sm-9">--%>
                                    <%--<input type="text" class="form-control" id="UserName" placeholder="userName" name="userName">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="PhoneNumber" class="col-sm-3 control-label">phoneNumber</label>--%>
                                <%--<div class="col-sm-9">--%>
                                    <%--<input type="number" class="form-control" id="PhoneNumber" placeholder="phoneNumber" name="phoneNumber">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="Email" class="col-sm-3 control-label">Email address</label>--%>
                                <%--<div class="col-sm-9">--%>
                                    <%--<input type="email" class="form-control" id="Email" placeholder="email" name="email">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="col-sm-3 control-label">role</label>--%>
                                <%--<div class="col-sm-9">--%>
                                    <%--<select id="select"  name="roleName"></select>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                        <%--</div>--%>
                        <%--<div class="modal-footer">--%>
                            <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
                            <%--<button type="submit" class="btn btn-primary" id="updateSubmit">提交</button>--%>
                        <%--</div>--%>
                    <%--</form>--%>


                <%--</div><!--end modal-body-->--%>



            <%--</div><!-- end modal-content-->--%>
        <%--</div><!-- end modal-dialog-->--%>
    <%--</div><!-- end modal fade-->--%>


<table id="userTable"></table>

    <iframe name="frameResult" style="display:none">
    </iframe>
</body>
</html>
