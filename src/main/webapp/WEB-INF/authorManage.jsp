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
    <link href="/statics/toastr/toastr.css" rel="stylesheet">
    <script src="/statics/bootstrap/js/jquery.min.js"></script>
    <script src="/statics/bootstrap/js/bootstrap.min.js"></script>
    <script src="/statics/bootstrap/js/bootstrap-table.js"></script>
    <script src="/statics/ztree/js/jquery.ztree.core.js"></script>
    <script src="/statics/toastr/toastr.js"></script>
    <script src="../statics/js/jquerysession.js"></script>
    <script src="../statics/js/tooltip.js"></script>

    <script src="/statics/definition/authorManage.js"></script>
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

        查询父分类下: <select id="likeAuthor" class="btn btn-default "  name="author"></select>


        <button type="button" id="add" class="btn btn-default navbar-btn" style="display: none;margin-left: 15px;" data-toggle="modal" data-target="#myModal">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            增加
        </button>

        <button type="button" id="del" class="btn btn-default navbar-btn" style="display: none">
            <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
            删除
        </button>

        <button type="button" id="update" class="btn btn-default navbar-btn" style="display: none" data-toggle="modal" ><!--data-target="#myModal"-->
            <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
            修改
        </button>


    </div>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
            <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" id="modalColose">&times;</span></button>
            <h4 class="modal-title" id="myModalLabel">...</h4>
            </div>
            <div class="modal-body" style="height: 70%;">

                <form id="saveAuthorForm" target="frameResult" class="form-horizontal"><!-- action="/updateUser.xhtml" method="post"-->
                    <div class="modal-body">

                    <div class="form-group">
                     <label for="authorName" class="col-sm-4 control-label">权限名称</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="authorName"  name="name">
                        </div>
                    </div>



                    <div class="form-group" style="margin-top: 2%">
                     <label for="authorurl" class="col-sm-4 control-label">请求地址</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="authorurl"  name="authorUrl">
                        </div>
                    </div>



                    <div class="form-group" style="margin-top: 2%">
                     <label for="authordescribe" class="col-sm-4 control-label">权限描述</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="authordescribe"  name="authorDescribe">
                        </div>
                    </div>



                    <div class="form-group" style="margin-top: 2%;">
                        <label for="select" class="col-sm-4 control-label">是否父节点</label>
                        <div class="col-sm-8">
                            <select id="select" name="isParent">
                                <option value="xx">请选择</option>
                                <option value="0">true</option>
                                <option value="1">false</option>
                            </select>
                        </div>
                    </div>


                    <div class="form-group" style="margin-top: 2%">
                     <label for="authorPname" class="col-sm-4 control-label">父级编号</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="authorPname"  name="pname">
                            <ul id='power' class='ztree' style='position:absolute;margin-top:0; width:320px;height: 150px;display: none;'></ul>
                        </div>
                    </div>


                        <div class="form-group" style="margin-top: 2%;display: none;">
                            <label for="authorPid" class="col-sm-4 control-label"></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="authorPid"  name="pid">
                            </div>
                        </div>

                        <div class="form-group" style="margin-top: 2%;display: none;">
                            <label for="authorId" class="col-sm-4 control-label"></label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="authorId"  name="id">
                            </div>
                        </div>


                    </div>
                    <div class="modal-footer" >
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-primary" id="SubmitAuthor">提交</button>
                    </div>
                </form>


            </div><!--end modal-body-->

            </div><!-- end modal-content-->
        </div><!-- end modal-dialog-->
    </div><!-- end modal fade-->



<table id="authorTable"></table>

    <iframe name="frameResult" style="display:none">
    </iframe>
</body>
</html>
