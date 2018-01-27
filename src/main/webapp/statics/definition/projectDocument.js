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
        var rowTable=$('#projectDocumentTable').bootstrapTable('getData',true);
        var templpprojectDocumentId=rowTable[rowTable.length-1].project_documents_id;
        if(templpprojectDocumentId!=0&&updateoradd!=2){
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
            $('#projectDocumentTable').bootstrapTable('removeByUniqueId',0);
        }else if(updateoradd==2){
            refreshTable();
        }
        updateoradd=0;
    })

    //表格加载
    $('#projectDocumentTable').bootstrapTable({
        url: '/selectProject_documentAll.xhtml',
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
        uniqueId:'project_documents_id',//唯一标示列
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
            delrow=row.project_documents_id;
        },
        columns: [
            {field: 'projectDocument_check',radio:true},
            {field: 'project_documents_id',title: '项目文档编号',},
            {field: 'project_documents_name',title: '项目文档名称',width:120,},
            {field: 'project_documents_class', title: '项目文档类型',width:120,},
            {field: 'project_id', title: '所属项目编号', width:120,},
            {field: 'pd_upload_name', title: '上传人', width:120,},
            {field: 'pd_upload_time', title: '上传时间', width:120,},
        ]
    });

});
//完成编辑
function save() {
    var rowTable=$('#projectDocumentTable').bootstrapTable('getData',true);
    var map="{";
    $.each(rowTable[0],function (k,v) {
        if(k=='project_documents_id'&&updataID!=null){
            map+='"'+k+'":"'+updataID+'",';
        }else {
            map+='"'+k+'":"'+$('#'+k+'').val()+'",';
        }
    });
    map=map.substring(0,map.length-1)+"}";
    if(updateoradd==1){//增加
        $.ajax({//增加的对象传入数据库
            url:'addOneProject_document.xhtml',
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
            url:'/updateProject_document.xhtml',
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
    $("#projectDocumentTable").bootstrapTable('refresh',{url:'/selectProject_documentAll.xhtml'});
}

//删除
function del(index) {
    $.ajax({
        url:'delProject_document.xhtml',
        method:'post',
        data:{project_documents_id:index},
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
    updataID=row.project_documents_id;
    var dropDownBoxOne;
    // $.ajax({
    //     url:'',
    //     methods:"post",
    //     data:{'type':row.Legal_type},
    //     success:function (data) {
    //         dropDownBoxOne=dropDownBox(row.Legal_type,JSON.parse(data));
    //     }
    // });
    $('#projectDocumentTable').bootstrapTable('updateRow',{index:index, row:{
        project_documents_id:row.project_documents_id,
        project_documents_name:'<input id="project_documents_name" value="'+row.project_documents_name+'"  type="text" class="form-control"  style="width:100px">',
        project_documents_class:'<input  id="project_documents_class" value="'+row.project_documents_class+'" type="text" class="form-control"  style="width:100px">',
        project_id:'<input  id="project_id" value="'+row.project_id+'" type="text" class="form-control"   style="width:100px">',
        pd_upload_name:'<input id="pd_upload_name"  value="'+row.pd_upload_name+'" type="text"   class="form-control" style="width:100px" >',
        pd_upload_time:'<input id="pd_upload_time"  value="'+row.pd_upload_time+'" type="date"  class="form-control"  style="width:100px" >',
     }});





}

//增加一行
function add(index) {
    $('#projectDocumentTable').bootstrapTable('insertRow',{index:index, row:{
        project_documents_id:'0',
        project_documents_name:'<input type="text" class="form-control"  id="project_documents_name"  style="width:100px">',
        project_documents_class:'<input type="text" id="project_documents_class"   class="form-control"   style="width:100px">',
        project_id:'<input type="text"  class="form-control"  id="project_id" style="width:100px" >',
        pd_upload_name:'<input type="text"  class="form-control"  id="pd_upload_name" style="width:100px" >',
        pd_upload_time:'<input type="date" class="form-control"  id="pd_upload_time" style="width:100px" >',
  }});




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
