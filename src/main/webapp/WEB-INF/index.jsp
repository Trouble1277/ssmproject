<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>

    <link href="../statics/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="../statics/bootstrap/js/jquery.min.js"></script>
    <script src="../statics/bootstrap/js/bootstrap.min.js"></script>
    <script src="../statics/js/jquerysession.js"></script>
    <script src="../statics/definition/index.js"></script>

</head>

<style type="text/css">

#Top{
    background-color: #2aabd2;
    height: 10%;

}
#TopTitle{
    text-align: center;

    font: 28px bold;
    margin-top: 1.5%;
}
#Left{
    background: #d1d6d8; height: 90%;
}
    #leftMenu{
        color: #5BC0DE;
    }
    #accordion{
        margin-top: 15%;
    }
    #UserName{
        margin-left: 5%;
        margin-top: 10%;
    }

    #TabStrip{
        margin-top: 1%;
    }
    #centerPanel{
        background: rgba(209, 214, 216, 0.53);
        height: 90%;
    }

    .AccordionPanel{
        color: #df4f11;
    }
    .panelIFrame{
        height: 90%;
        width: 99%
    }

</style>

<body>

    <div id="main">

            <div id="Top" class="col-xs-12 co-md-12 col-lg-12"><!--  start Top -->
                <div id="TopTitle"> <span> 尚缘基金</span></div>
            </div><!-- end Top-->


            <div class="col-xs-2 co-md-2 col-lg-2" id="Left"><!--  start Left -->

                <div id="UserName">用 户:&nbsp;&nbsp;&nbsp;<span id="TextUserName">${sessionScope.user.userName}</span></div>

                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true"></div><!-- leftMenu -->
            </div><!-- end Left-->


            <div class="col-xs-10 col-md-10 col-lg-10 " id="centerPanel"><!--中间面板布局-->

                <div><!--tab -->
                    <ul class="nav nav-pills" id="tab_content" role="tablist">
                        <li role="presentation" class="active">
                            <a href="#home" aria-controls="home" role="tab" data-toggle="tab" class="AccordionPanel" >首页</a>
                        </li>
                    </ul>


                    <div class="tab-content" id="panel_content"><!--面板-->
                        <div  role="tabpanel" class="tab-pane active " id="home"><!--role="tabpanel"-->
                            <iframe src="/home.xhtml"  class="panelIFrame"></iframe><!--页面嵌套-->
                        </div>

                    </div>

                </div><!--tab -->



            </div><!--中间面板-->

    </div>




</body>
</html>
