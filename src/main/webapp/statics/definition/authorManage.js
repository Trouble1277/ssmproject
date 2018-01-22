//$.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);
$(function () {

    var MenuV=JSON.parse($.session.get("authorMenu"));
    var mm=MenuV.split(",") ;
    if (mm[0]==1||mm[1]==1||mm[2]==1){//显示增加
        $("#add").show();
        $("#add").on('click',function () {
            addClick();
        })
    }
    if (mm[0]==2||mm[1]==2||mm[2]==2){
        $("#del").show();
        $("#del").on('click',function () {
            DelClick();
        })
    }
    if (mm[0]==3||mm[1]==3||mm[2]==3){
        $("#update").show();
        $("#update").on('click',function () {
            UpdateClick();
        })
    }

    //保存权限
    saveAuthor();

    var authorComboBoxHtml="<option value=\"\">  </option>";
    $.ajax({
        url:"/queryAuthorAll.xhtml",
        dataType:'json',
        method:"post",
        success:function(data){
            $.each(data,function(index,authorComObj){
                authorComboBoxHtml+="<option value="+authorComObj.id+">"+authorComObj.name+"</option>";
            });
            $("#likeAuthor").html(authorComboBoxHtml);
        }
    });


    var page="";
    var num="";
    $("#likeAuthor").change(function(){
        console.log($(this).val());
        var options = $('#authorTable').bootstrapTable('refresh', {
            query:
                {
                    index:page,
                    size:num,
                    id:$(this).val(),
                }
        });
    });


    $('#authorTable').bootstrapTable({
        url: '/queryAuthorAllManage.xhtml',
        method:"get",
        toolbar:"#toolBar",
        sidePagination:'server',//设置分页
        showPaginationSwitch:true,//显示据条数选择框
        pagination:true,//格底部显示分页条
        showRefresh:true,//刷新按钮
        showToggle:true,//切换试图
        showColumns:true,//内容列下拉框
        pageNumber:1,
        pageSize:5,
        pageList:[5,10,15,20],
        smartDisplay:true,//显示分页或卡视图
        // search:true,//搜索框
        // strictSearch:false,//模糊查询
        singleSelect:true,//将禁止多选
        queryParams:function(params) {
            page=params.offset;
            num=params.limit;

            return {
                index:params.offset,
                size:params.limit,
            };
        },
        columns: [
            {
                checkbox: true
            },{
            field: 'id',
            title: '权限编号',
            sortable:true

        }, {
            field: 'name',
            title: '权限名字',
            sortable:true,

        }, {
            field: 'pid',
            title: '权限父编号',
            sortable:true,

        }, {
            field: 'isParent',
            title: '是否父节点',
            sortable:true,
        },{
            field: 'authorDescribe',
            title: '描述',
            sortable:true,
        },{
            field: 'authorUrl',
            title: '请求地址',
            sortable:true,
        }


        ],
    })

    //提示框的初始化
    toastr.options = {
        "positionClass": "toast-top-center",
    };



})




function loadZtree() {
    setting = {
        async:{
            enable:true,//异步加载
            type:"post",
            otherParam: {"pid":'0'},
            autoParam:["id","isParent","name"],
            url:"/queryAuthorAll.xhtml"
        },
        view: {
            dblClickExpand: false,

        },
        callback: {
            onClick: zTreeOnClick,

        },
        check: {
            enable: false//取消复选框
        }
    }
    $.fn.zTree.init($("#power"), setting);
}


function zTreeOnClick(event, treeId, treeNode) {
    var clickName=treeNode.name;
    $("#authorPname").val(clickName);
    $("#authorPid").val(treeNode.id);
};




//ztree控制
function ZtreeShowHide() {

    $("#authorPname").focus(function () {//父级编号得到焦点的时候出现ztree
        $("#power").show();
        loadZtree();//加载ztree
        $("#myModal").focus(function () {//当面板得到焦点的时候隐藏ztree
            $("#power").hide();
        })

    });

}


//点击增加按钮所改变的事件
function addClick() {
    selectContent = selectContent2;
    $("#myModalLabel").html("增加权限");
    // $("#authorId").attr("disabled","disabled");//设置不可编辑
    ZtreeShowHide();
}





//点击修改所改变的事件
function UpdateClick() {
    selectContent =$("#authorTable").bootstrapTable('getSelections')[0];//获取选中的行对象
    if (typeof (selectContent)=="undefined"){//如果没有选中的话，提示用户选中一行
        toastr.warning("请选择您要修改的行");
    }else{
        $("#myModalLabel").html("修改权限");
        $('#myModal').modal('show');
        FillVal(selectContent);//为文本框赋值
        ZtreeShowHide();//加载ztree
    }

}

//将文本框里的值全部清空
function cleanVal() {
    $("#authorName").val("");
    $("#authorurl").val("");
    $("#authordescribe").val("");
    $("#select").val("");
    $("#authorPname").val("");
    $("#authorPid").val("");
}

//修改时填充文本框的值
function FillVal(obj) {
    $("#authorName").val(obj.name);
    $("#authorurl").val(obj.authorUrl);
    $("#authordescribe").val(obj.authorDescribe);

    var isParent= obj.isParent;

    if (isParent==true){
        isParent=0;
    }else if (isParent==false){
        isParent=1;
    }
    $("#select").find("option[value='"+isParent+"']").attr("selected",true);
    $("#select").val(isParent);
    $.ajax({
        url:"/queryAuthorById.xhtml",
        data:"id="+obj.pid,
        dataType:'json',
        method:"post",
        success:function(data){
            if (data!=null){
                $("#authorPname").val(data.name);
            }

        }
    });
    $("#authorPid").val(obj.pid);
    $("#authorId").val(obj.id);

}



var selectContent;
var selectContent2;

//删除
function DelClick() {

    selectContent =$("#authorTable").bootstrapTable('getSelections')[0];//获取选中的行对象
    if (typeof (selectContent)=="undefined"){//如果没有选中的话，提示用户选中一行
        toastr.warning("请选择您要删除的行");
    }else{
        Ewin.confirm({ message: "你确定要删除吗？" }).on(function (e) {
            if (e) {//确定的话
                $.ajax({
                    url:"/delAuthor.xhtml",
                    data:"id="+selectContent.id,
                    dataType:'json',
                    method:"post",
                    success:function(data){
                        if(data.success=='success'){
                            selectContent = selectContent2;
                            toastr.success("祝贺你成功了");
                            $("#authorTable").bootstrapTable("refresh",{
                                url: '/queryAuthorAllManage.xhtml'
                            });
                        }else if(data.error=='error'){
                            toastr.error("删除权限失败");
                        }
                    }
                });
            }
        })
    }

}

//增加修改的保存
function saveAuthor() {
    $("#SubmitAuthor").click(function () {
        $("#saveAuthorForm").submit();
        var data = $("#saveAuthorForm").serializeArray();
        if (typeof (selectContent)=="undefined"){
            $.ajax({
                url:"/addAuthor.xhtml",
                data:data,
                dataType:'json',
                method:"post",
                success:function(data){
                    if(data.success=='success'){
                        selectContent = selectContent2;
                        toastr.success("祝贺你成功了");
                        cleanVal();
                        $("#authorTable").bootstrapTable("refresh",{
                            url: '/queryAuthorAllManage.xhtml'
                        });
                    }else if(data.error=='error'){
                        toastr.error("添加权限失败");
                    }
                }

            });

        }else{

            $.ajax({
                url:"/updateAuthor.xhtml",
                data:data,
                dataType:'json',
                method:"post",
                success:function(data){

                    if(data.success=='success'){
                        selectContent = selectContent2;
                        toastr.success("祝贺你修改成功了");
                        cleanVal();
                        $("#authorTable").bootstrapTable("refresh",{
                            url: '/queryAuthorAllManage.xhtml'
                        });
                    }else if(data.error=='error'){
                        toastr.error("修改权限失败");
                    }
                }

            });


        }
    })
}

