$(function () {


    // 判断用户是否为空
    var UserText= $("#TextUserName").text()
    if (UserText==''){
        window.location.href = "/login.xhtml";
    }else{
        //手风琴

        $.ajax({
            url:'/MenuQueryLeft.xhtml',
            method:'get',
            dataType:'JSON',
            success:function (data) {
                var html="";

                $.each(data,function (authorId,x) {
                    if (x.isParent){
                        html+="<div class='panel panel-default'>";
                        html+= "<div class='panel-heading' role='tab' id='heading"+x.authorId+"'>";
                        html+= "<h4 class='panel-title'>" ;
                        html+="<a role='button' data-toggle='collapse' data-parent='#accordion' href='#collapse"+x.authorId+"' aria-expanded='true' aria-controls='collapse"+x.authorId+"'>";
                        html+=x.authorName+"</a></h4></div>";

                        html+= "<div id='collapse"+x.authorId+"' class='panel-collapse collapse' role='tabpanel' aria-labelledby='heading"+x.authorId+"'>";
                        html+="<div class='panel-body'>";
                        html+="<nav class='nav nav-pills nav-stacked'>";
                        html+="<li>";
                        $.each(data,function (authorPid,y) {
                            if (x.authorId==y.authorPid){
                                html+="<a role=\"button\" class=\"AccordionPanel\">"+y.authorName+"</a>";//将其变为一个按钮
                            }
                        });
                        html+="</li></nav></div></div></div>";
                    }
                });
                $("#accordion").html(html);





                //在节点加载完之后执行节点事件
                $(".AccordionPanel").click(function () {
                    var TabText=$(this).text();//获取到手风琴点击到tab值



                    var TabElement= $("#tab_content").children()//获取到标签页tab里面的所有子节点
                    var panel=$("#panel_content").children();//获取面板div里面所有的面板
                    var Flag=false;
                    $.each(panel,function (i,PanelObj) {//面板的遍历
                        $(PanelObj).removeClass("active");//移出默认选中的面板
                    })

                    $.each(TabElement,function (index,TabObj) {//遍历便签页的所有tab
                        $(TabObj).removeClass("active");
                        var TabObjText=$(TabObj).text();
                        if(TabObjText==TabText){
                            Flag=true;
                            $(TabObj).tab('show');//选中打开的页面
                            $("#"+TabText).addClass("active")//设置选中的tab为优先
                            return;
                        }

                    })
                    //当没有相同的tab的时候
                    if (!Flag){
                        //获取与之相应的url
                        var UrlText="";
                        $.each(data,function (pageUrl,val) {
                            if (val.authorName==TabText){
                                UrlText=val.authorUrl;
                            }

                        })

                        if (TabText=="首页"){


                        }else{
                            var tabHtml="<li role=\"presentation\" class='active'>";
                            tabHtml+="<a href=\"#"+TabText+"\" aria-controls=\"profile\" role=\"tab\" data-toggle=\"tab\" aria-expanded='true' >"+TabText+"<span class=\"glyphicon glyphicon-remove tab_close\" aria-hidden=\"true\" ></span></a>"
                            tabHtml+="</li>";

                            var PanelHtml="<div role=\"tabpanel\" class=\"tab-pane active\" id=\""+TabText+"\"> <iframe src=\""+UrlText+"\" class=\"panelIFrame\" ></iframe></div>";
                            $("#tab_content").append(tabHtml);//将点击的手风琴追加到标签页中
                            // $("#PanelIframe").attr("src",UrlText);
                            $("#panel_content").append(PanelHtml);//将页面嵌套到面板中去
                        }


                        //tab的关闭事件
                        $(".tab_close").click(function(){
                            var parent=$(this).parent();//得到这个关闭图标的父元素也就是tab

                            // parent.remove();//将点击的tab关闭
                            $(parent).parent().remove();
                            var targetHref=parent.attr("href");
                            var tabContent=$(targetHref);
                            tabContent.remove();//将面板也移除掉
                            parent.previousSibling

                        })

                    }

                });
            }

        });





    }
});



