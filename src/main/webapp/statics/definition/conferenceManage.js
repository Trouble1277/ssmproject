var delrow;
var updateoradd=0;//1增加，2修改
var updataID=0;
var fund_IdValue;
$(function () {
    //保存
    $('#save').on('click',function () {
        save();
    });
    //增加一行
    $('#add').on('click', function () {
        var rowTable=$('#conferenceTable').bootstrapTable('getData',true);
        var tempconferenceId=rowTable[rowTable.length-1].conference_Id;
        if(tempconferenceId!=0&&updateoradd!=2){
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
            $('#conferenceTable').bootstrapTable('removeByUniqueId',0);
        }else if(updateoradd==2){
            refreshTable();
        }
        updateoradd=0;
    })

    //表格加载
    $('#conferenceTable').bootstrapTable({
        url: '/QueryConferenceAllResult.xhtml',
        method:"post",
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
        uniqueId:'conference_Id',//唯一标示列
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
            delrow=row.conference_id;
        },
        columns: [
            {field: 'conference_check',radio:true},
            {field: 'conference_id',title: '会议编号',class:'info',width:120},
            {field: 'conference_main_topic',title: '主要议题',width:120,},
            {field: 'fund_Id', title: '涉及基金',width:120,},
            {field: 'conference_time', title: '会议时间', width:120,},
            {field: 'conference_emcee', title: '主持人', width:120,},
            {field: 'conference_recorder', title: '记录人', width:120,},
            {field: 'conference_document', title: '会议文档', width:120,},
        ]
    });

});
//完成编辑
function save() {
    var rowTable=$('#conferenceTable').bootstrapTable('getData',true);
    var map="{";
    $.each(rowTable[0],function (k,v) {
        if(k=='conference_id'&&updataID!=null){
            map+='"'+k+'":"'+updataID+'",';
        }else if(k=='fund_Id'&&fund_IdValue!=null){
            map+='"'+k+'":"'+fund_IdValue+'",';
        }else if(k=='conference_document'&&uploadName!=null){
            map+='"'+k+'":"'+uploadName+'",';
        }
            else{
            map+='"'+k+'":"'+$('#'+k+'').val()+'",';
        }
    });
    map=map.substring(0,map.length-1)+"}";
    console.log(map);
    console.log(JSON.parse(map));
    if(updateoradd==1){//增加
        $.ajax({//增加的对象传入数据库
            url:'AddConference.xhtml',
            method:'post',
            data:JSON.parse(map),
            success:function (data) {
                console.log(data);
                var tempdata=JSON.parse(data);
                console.log(tempdata);
                if(tempdata.success==''){
                    $("#example").onclick=cdanger("增加失败");
                }else if(tempdata.success=='sucess'){
                    $("#example").onclick=cdanger("增加成功");
                    updateoradd=0;
                    refreshTable();
                }
            }
        });
    }else if(updateoradd==2){//修改
        $.ajax({
            url:'/UpdateConference.xhtml',
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
    $("#conferenceTable").bootstrapTable('refresh',{url:'/QueryConferenceAllResult.xhtml'});
}

//删除
function del(index) {
    $.ajax({
        url:'DelConference.xhtml',
        method:'post',
        data:{conference_Id:index},
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
    updataID=row.conference_Id;
    var dropDownBoxOne;
    $.ajax({
        url:'UpdateConference.xhtml',
        methods:"post",
        data:{'type':row.fund_Id},
        success:function (data) {
            dropDownBoxOne=dropDownBox(row.fund_Id,JSON.parse(data));
        }
    });
    $('#conferenceTable').bootstrapTable('updateRow',{index:index, row:{
        conference_Id:row.conference_Id,
        conference_main_topic:'<input type="text" class="form-control"  id="conference_main_topic" value="'+row.conference_main_topic+'"  style="width:100px">',
        // conference_type:dropDownBoxOne,
        fund_Id:'<input type="text" id="fund_Id" value="'+row.fund_Id+'" class="form-control"   style="width:100px">',
        conference_time:'<input type="text"  class="form-control" value="'+row.conference_time+'"  id="conference_time" style="width:100px" >',
        conference_emcee:'<input type="text"  class="form-control"  value="'+row.conference_emcee+'"  id="conference_emcee" style="width:100px" >',
        conference_recorder:'<input type="text" class="form-control"  value="'+row.conference_recorder+'"  id="conference_recorder" style="width:100px" >',
        conference_document:'<input type="date" class="form-control"  value="'+row.conference_document+'"  id="conference_document" style="width:180px" >',
    }});





}

//增加一行
function add(index) {
    $('#conferenceTable').bootstrapTable('insertRow',{index:index, row:{
        conference_Id:'0',
        conference_main_topic:'<input type="text" class="form-control"  id="conference_main_topic"  style="width:100px">',
        fund_Id:'<div class="btn-group"  style="width:130px"> ' +
        '    <span type="text" class="form-control"  id="fund_Id"  style="width:200px">请选择</span>'+
        '    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="height:34px;margin-top:-34px;margin-left:175px">' +
        '        <span class="caret"></span>' +
        '    </button>' +
        '    <ul class="dropdown-menu" role="menu" id="addFundAll">' +
        '    </ul>' +
        '</div>',
        conference_time:'<input type="date"  class="form-control"  id="conference_time" style="width:100px" >',
        conference_emcee:'<input type="text"  class="form-control"  id="conference_emcee" style="width:100px" >',
        conference_recorder:'<input type="text" class="form-control"  id="conference_recorder" style="width:100px" >',

        conference_document:'<form id="formModelProgress" method="post" action="upload.xhtml" target="targer" enctype="multipart/form-data">' +
        '<input type="file" class="form-control" name="file"  id="conference_document" style="width:180px" >' +
        '</form>'
    }});
     seleul();
    conference_documentFile();
}

function seleul(){
    $.ajax({
        url:'ConferenceFundAll.xhtml',
        methods:"post",
        success:function (data) {
            var str="";
            $.each(JSON.parse(data),function (k,v) {
                var fundName = v.fund_name;
                str+='<li id="fundAddli'+k+'">';
                    str+='<a  href="javascript:fundOnClick('+k+','+v.fund_Id+');">';
                        str+=v.fund_name+'</a></li>';
            });
           $("#addFundAll").html(str);
        }
    });

}

function  fundOnClick(index,fundId) {
    // console.log(index);
    // console.log(fundId);
    // $("#fund_Id").html(fundName);
    fund_IdValue = fundId;
    console.log(fund_IdValue);

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
        str+='<li id="'+k+'"><a href="">'+v.fund_name+'</a></li>';
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

