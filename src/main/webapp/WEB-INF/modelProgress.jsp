<%--
  Created by IntelliJ IDEA.
  User: Trouble
  Date: 2018/1/27/027
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="modal"
     style="display: none;  opacity: 0.8; position: absolute; left: 0; top: 0; z-index: 4; width: 100%; height: 100%; margin: 0 auto; background: #ccc;">
    <div class="progress progress-striped active"
         style="height: 20px; width: 300px; background: #ffffff; margin-left: 500px; margin-top: 300px; border-radius: 6px;" >
        <div id="progess" class="progress-bar progress-bar-success" role="progressbar"
             aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
             style="width: 0%;
                 border-radius: 6px; height: 19px;
                 ">
            <span id="sizeTotal" style="text-align: right" >0% 完成</span>
        </div>
    </div>
</div>
</body>
</html>
