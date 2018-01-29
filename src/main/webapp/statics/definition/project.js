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
        var rowTable=$('#projectTable').bootstrapTable('getData',true);
        var tempprojectId=rowTable[rowTable.length-1].project_id;
        if(tempprojectId!=0&&updateoradd!=2){
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
            $('#projectTable').bootstrapTable('removeByUniqueId',0);
        }else if(updateoradd==2){
            refreshTable();
        }
        updateoradd=0;
    })

    //表格加载
    $('#projectTable').bootstrapTable({
        url: '/selectProjectAll.xhtml',
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
        uniqueId:'project_id',//唯一标示列
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
            delrow=row.project_id;
        },
        columns: [
            {field: 'project_check',radio:true},
            {field: 'project_id',title: '项目编号',class:'info',},
            {field: 'project_name',title: '项目名称',width:120,},
            {field: 'project_phases', title: '项目阶段',width:120,},
            {field: 'principal_id', title: '负责人编号', width:120,},
            {field: 'contacts_id', title: '投资人编号', width:120,},
            {field: 'project_total_amount', title: '总出资额', width:120,},
            {field: 'project_privately_fund', title: '其中自有资金', width:120,},
            {field: 'project_leverage_fund', title: '杠杆资金', width:200,},
            {field: 'project_shareholding_ratio', title: '持股比例', width:200,},
            {field: 'fund_Id', title: '所属基金编号', width:200,},
            {field: 'enterprise_id', title: '所属企业编号', width:120,},
            {field: 'project_remark', title: '备注', width:120,},
        ]
    });

});
//完成编辑
function save() {
    var rowTable=$('#projectTable').bootstrapTable('getData',true);
    var map="{";
    $.each(rowTable[0],function (k,v) {
        if(k=='project_id'&&updataID!=null){
            map+='"'+k+'":"'+updataID+'",';
        }else if(k=='project_phases'){
            if(v!=null){
                map+='"'+k+'":"'+10+'",';
            }else {
                map+='"'+k+'":"'+$('#'+k+'').val()+'",';
            }
        }
        else {
            map+='"'+k+'":"'+$('#'+k+'').val()+'",';
        }
    });
    map=map.substring(0,map.length-1)+"}";
    if(updateoradd==1){//增加
        $.ajax({//增加的对象传入数据库
            url:'addOneProject.xhtml',
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
            url:'/updateProject.xhtml',
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
    $("#projectTable").bootstrapTable('refresh',{url:'/selectProjectAll.xhtml'});
}

//删除
function del(index) {
    $.ajax({
        url:'delProject.xhtml',
        method:'post',
        data:{project_id:index},
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
    updataID=row.project_id;
    var dropDownBoxOne;
    // $.ajax({
    //     url:'',
    //     methods:"post",
    //     data:{'type':row.project_phases},
    //     success:function (data) {
    //         dropDownBoxOne=dropDownBox(row.project_phases,JSON.parse(data));
    //     }
    // });
    $('#projectTable').bootstrapTable('updateRow',{index:index, row:{
        project_id:row.project_id,
        project_name:'<input type="text" class="form-control"  id="project_name" value="'+row.project_name+'"  style="width:100px">',
        project_phases:'<input type="text" class="form-control"  id="project_phases" value="'+row.project_phases+'"  style="width:100px">',
        principal_id:'<input type="text" id="principal_id" value="'+row.principal_id+'" class="form-control"   style="width:100px">',
        contacts_id:'<input type="text"  class="form-control" value="'+row.contacts_id+'"  id="contacts_id" style="width:100px" >',
        project_total_amount:'<input type="text"  class="form-control"  value="'+row.project_total_amount+'"  id="project_total_amount" style="width:100px" >',
        project_privately_fund:'<input type="text" class="form-control"  value="'+row.project_privately_fund+'"  id="project_privately_fund" style="width:100px" >',
        project_leverage_fund:'<input type="text" class="form-control"  value="'+row.project_leverage_fund+'"  id="project_leverage_fund" style="width:180px" >',
        project_shareholding_ratio:'<input type="text" class="form-control"  value="'+row.project_shareholding_ratio+'"  id="project_shareholding_ratio" style="width:180px" >',
        fund_Id:'<input type="text" class="form-control"  value="'+row.fund_Id+'"  id="fund_Id" style="width:180px" >',
        enterprise_id:'<input type="text" class="form-control"  value="'+row.enterprise_id+'"  id="enterprise_id" style="width:100px" >',
        project_remark:'<input type="text" class="form-control"  value="'+row.project_remark+'"  id="project_remark" style="width:100px" >',
  }});





}

//增加一行
function add(index) {
    var dropDownBoxOne;
    $.ajax({
        url:'/selectDataDictionaryOneSon.xhtml',
        methods:"post",
        data:{'ddid':'4'},
        async: false,
        success:function (data) {
           dropDownBoxOne=dropDownBox('project_phases',JSON.parse(data));
        }
    });

    
    $('#projectTable').bootstrapTable('insertRow',{index:index, row:{
        project_id:'0',
        project_name:'<input type="text" class="form-control"  id="project_name" style="width:100px">',
        project_phases:'<input type="text" class="form-control" id="project_phases" value="审批中"  style="width:100px" readonly="true" >',
        principal_id:'<div class="btn-group"  style="width:150px"> '+
        '<span type="text" class="form-control"  id="principal_id" style="width:100px"></span>'+
        '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="height:34px;margin-top:-34px;margin-left:98px">' +
        '<span class="caret"></span>' +
        '</button>' +
        '<ul class="dropdown-menu" role="menu"><table id="principalTable2"></table></ul></div>',

        contacts_id:'<div class="btn-group"  style="width:150px"> '+
        '<span type="text" class="form-control"  id="contacts_id" style="width:100px"></span>'+
        '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="height:34px;margin-top:-34px;margin-left:98px">' +
        '<span class="caret"></span>' +
        '</button>' +
        '<ul class="dropdown-menu" role="menu"><table id="contactsTable2"></table></ul></div>',
        project_total_amount:'<input type="text"  class="form-control"  id="project_total_amount" style="width:100px" >',
        project_privately_fund:'<input type="text" class="form-control"  id="project_privately_fund" style="width:100px" >',
        project_leverage_fund:'<input type="text" class="form-control"  id="project_leverage_fund" style="width:180px" >',
        project_shareholding_ratio:'<input type="text" class="form-control"  id="project_shareholding_ratio" style="width:180px" >',
        fund_Id:'<input type="text" class="form-control"  id="fund_Id" style="width:180px" >',
        enterprise_id:'<input type="text" class="form-control"  id="enterprise_id" style="width:100px" >',
        project_remark:'<input type="text" class="form-control"  id="project_remark" style="width:100px" >',
  }});


    contactsTable2Load();
    principalTable2Load();


}


//下拉框选项
function dropDownBox(idName,dropListObject) {
    var str='<div class="btn-group"  style="width:150px"> ' +
        '    <span type="text" class="form-control"  id="'+idName+'"  style="width:100px">'+dropListObject.ddName+'：</span>'+
        '    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="height:34px;margin-top:-34px;margin-left:98px">' +
        '        <span class="caret"></span>' +
        '    </button>' +
        '    <ul class="dropdown-menu" role="menu">';
    $.each(dropListObject.list,function (k,v) {
      str+='<li ><a href="#" id="'+idName+v.ddsonid+'" onclick="updataDropDownBox(\''+idName+'\',\''+v.ddsonid+'\')">'+v.ddsonName+'</a></li>';
    });
    str+= '</ul></div>';
    return str;
}

//下拉框赋值
function updataDropDownBox(idName,boxTitle) {
    $("#"+idName+"").html($("#"+idName+boxTitle+"").html());
    $("#"+idName+"").val(''+boxTitle+'');//设置下拉坐标

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

function addTableSon() {

}


function contactsTable2Load() {

    $('#contactsTable2').bootstrapTable({
        url: '/selectContactsAll.xhtml',
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
        uniqueId:'contacts_Id',//唯一标示列
        queryParams:function(params) {//传递额外参数
            return {
                index:params.offset,
                size:params.limit,
                search:params.search,
            };
        },
        onClickRow:function (row,$element) {
            $("#contacts_id").html(row.contacts_name);
            $("#contacts_id").val(row.contacts_Id);//赋id的值
        },
        columns: [
            {field: 'contacts_check',radio:true},
            {field: 'contacts_Id',title: '编号'},
            {field: 'contacts_name',title: '姓名'},
            {field: 'contacts_site', title: '地址',},
            {field: 'contacts_phone', title: '电话',},
            {field: 'contacts_postcode', title: '邮编',},
            {field: 'contacts_faxes', title: '传真', },
            {field: 'contacts_postbox', title: '邮箱',},
        ]
    });

}


function principalTable2Load() {
    $('#principalTable2').bootstrapTable({
        url: '/selectProject_principleAll.xhtml',
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
        uniqueId:'pp_id',//唯一标示列
        queryParams:function(params) {//传递额外参数
            return {
                index:params.offset,
                size:params.limit,
                search:params.search,
            };
        },
        onClickRow:function (row,$element) {
            $("#principal_id").html(row.pp_id);
            $("#principal_id").val(row.pp_id);//赋id的值
        },
        columns: [
            {field: 'Project_principle_check',radio:true},
            {field: 'pp_id',title: '项目负责人编号',class:'info',},
            {field: 'contacts_id',title: '联系人编号',width:120,},
            {field: 'enterprise_id', title: '所属企业编号',width:120,},
        ]
    });
}

//下拉框表格
// function dropDownTable() {
//     var str='<div class="btn-group"  style="width:150px"> '+
//             '<span type="text" class="form-control"  id="contacts_Id" style="width:100px">666</span>'+
//             '<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="height:34px;margin-top:-34px;margin-left:98px">' +
//             '<span class="caret"></span>' +
//             '</button>' +
//             '<ul class="dropdown-menu" role="menu"><table id="contactsTable2"></table></ul></div>';
//     return str;
// }