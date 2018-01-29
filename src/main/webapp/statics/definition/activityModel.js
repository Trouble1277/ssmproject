var delrow;
var updateoradd=0;//1增加，2修改
var updataID=0;
$(function () {
    //保存
    $('#save').on('click',function () {
        save();
    });
    //增加一行
    $('#add').on('click', function () {
        var rowTable=$('#activityModelTable').bootstrapTable('getData',true);
        var tempactivity_modelId=rowTable[rowTable.length-1].activity_model_id;
        if(tempactivity_modelId!=0&&updateoradd!=2){
            var index=rowTable.length+1;//增加的行号
            updateoradd=1;
            add(index);
        }else{
            $("#example").onclick=cdanger("编辑未完成，请完成编辑！！！");//警告框
        }
    });
    //删除
    $('#del').on('click',function () {
        if(updateoradd==0){
            del(delrow);
        }else{
            $("#example").onclick=cdanger("编辑未完成，请完成编辑！！！");//警告框
        }
    });
    //撤销
    $('#revo').on('click',function () {
        if(updateoradd==1){
            $('#activityModelTable').bootstrapTable('removeByUniqueId',0);
        }else if(updateoradd==2){
            refreshTable();
        }
        updateoradd=0;
    })

    //表格加载
    $('#activityModelTable').bootstrapTable({
        url: '/selectActivityModelAll.xhtml',
        method:"get",
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
        search:true,//搜索框
        strictSearch:false,//模糊查询
        clickToSelect:true,
        uniqueId:'activity_model_id',//唯一标示列
        queryParams:function(params) {//传递额外参数
            return {
                index:params.offset,
                size:params.limit,
                search:params.search,
            };
        },
        onDblClickRow:function (row,$element) {
            if(updateoradd!=1){
                updateoradd=2;
                var index=$element.data('index');
                updata(row,index);//双击编辑
            }else {
                $("#example").onclick=cdanger("编辑未完成，请完成编辑！！！");//警告框
            }
        },
        onClickRow:function (row,$element) {
            delrow=row.activity_model_id;
        },
        columns: [
            {field: 'activity_model_check',radio:true},
            {field: 'activity_model_id',title: '流程模板编号',},
            {field: 'activity_model_url',title: '流程模板地址',width:120,},
            {field: 'activity_model_remark',title: '备注',width:120,},
        ]
    });

});
//完成编辑
function save() {
    var rowTable=$('#activityModelTable').bootstrapTable('getData',true);
    var map="{";
    $.each(rowTable[0],function (k,v) {
        if(k=='activity_model_id'&&updataID!=null){
            map+='"'+k+'":"'+updataID+'",';

        }else if(k=='activity_model_url'&&uploadName!=null) {
            map+='"'+k+'":"'+uploadName+'",';
        }else{
            map+='"'+k+'":"'+$('#'+k+'').val()+'",';
        }
    });
    map=map.substring(0,map.length-1)+"}";
    if(updateoradd==1){//增加
        $.ajax({//增加的对象传入数据库
            url:'addOneActivityModel.xhtml',
            method:'post',
            data:JSON.parse(map),
            success:function (data) {
                var tempdata=JSON.parse(data);
                if(tempdata.success=='defeated'){
                    $("#example").onclick=cdanger("增加失败");
                }else if(tempdata.success=='session'){
                    $("#example").onclick=cdanger("增加成功");
                    updateoradd=0;
                    refreshTable();
                }
            }
        });
    }else if(updateoradd==2){//修改
        $.ajax({
            url:'/updateActivityModel.xhtml',
            method:'post',
            data:JSON.parse(map),
            success:function (data) {
                var tempda=JSON.parse(data);
                if(tempda.success=='defeated'){
                    $("#example").onclick=cdanger("修改失败");
                }else if(tempda.success=='session'){
                    $("#example").onclick=cdanger("修改成功");
                    updateoradd=0;
                    refreshTable();
                }
            }
        });


    }
}

//刷新表格
function refreshTable() {
    $("#activityModelTable").bootstrapTable('refresh',{url:'/selectActivityModelAll.xhtml'});
}

//删除
function del(index) {
    $.ajax({
        url:'delActivityModel.xhtml',
        method:'post',
        data:{activity_model_id:index},
        success:function (data) {
            var tempdata=JSON.parse(data);
            if(tempdata.success=='defeated'){
                $("#example").onclick=cdanger("删除失败");
            }else if(tempdata.success=='session'){
                $("#example").onclick=cdanger("删除成功");
            }
            refreshTable();
        }
    });
}

//修改
function updata(row,index) {
    updataID=row.activity_model_id;
    var dropDownBoxOne;
    // $.ajax({
    //     url:'',
    //     methods:"post",
    //     data:{'type':row.Legal_type},
    //     success:function (data) {
    //         dropDownBoxOne=dropDownBox(row.Legal_type,JSON.parse(data));
    //     }
    // });
    $('#activityModelTable').bootstrapTable('updateRow',{index:index, row:{
        activity_model_id:row.activity_model_id,
        activity_model_url:'<input id="activity_model_url" value="'+row.activity_model_url+'"  type="text" class="form-control"  style="width:100px">',
        activity_model_remark:'<input  id="activity_model_remark" value="'+row.activity_model_remark+'" type="text" class="form-control"  style="width:100px">',
    }});





}

//增加一行
function add(index) {
    $('#activityModelTable').bootstrapTable('insertRow',{index:index, row:{
        activity_model_id:'0',
        activity_model_url:'<form id="formModelProgress" method="post" action="upload.xhtml" target="targer" enctype="multipart/form-data">' +
        '<input type="file" name="file" class="form-control"  id="activity_model_url"  style="width:100px">' +
        '</form>',
        activity_model_remark:'<input type="text" id="activity_model_remark"   class="form-control"   style="width:100px">',
 }

    }
    );

    conference_documentFile("activity_model_url");


}
//下拉框
function dropDownBox(idName,dropListObject) {
    var str='<div class="btn-group"  style="width:130px"> ' +
        '    <span type="text" class="form-control"  id="'+idName+'"  style="width:80px">请选择</span>'+
        '    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="height:34px;margin-top:-34px;margin-left:75px">' +
        '        <span class="caret"></span>' +
        '    </button>' +
        '    <ul class="dropdown-menu" role="menu">';
    $.each(dropListObject,function (k,v) {
        str+='<li id="'+k+'"><a href="#">'+v+'</a></li>';
    });
    str+= '</ul></div>';
    return str;
}

//弹出框
function cdanger(promptContent) {
    $('#example').attr("title","请注意");
    $('#example').attr("data-content",promptContent);
    $('#example').popover('show');
    window.setTimeout(function(){
        $('#example').popover('hide');
    },2000);
}
