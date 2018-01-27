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
        var rowTable=$('#legalTable').bootstrapTable('getData',true);
        var tempLegalId=rowTable[rowTable.length-1].legal_id;
        if(tempLegalId!=0&&updateoradd!=2){
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
            $('#legalTable').bootstrapTable('removeByUniqueId',0);
        }else if(updateoradd==2){
            refreshTable();
        }
        updateoradd=0;
    })

    //表格加载
    $('#legalTable').bootstrapTable({
        url: '/selectLegalAll.xhtml',
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
        uniqueId:'legal_id',//唯一标示列
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
            delrow=row.legal_id;
        },
        columns: [
            {field: 'Legal_check',radio:true},
            {field: 'legal_id',title: '法人编号',class:'info',},
            {field: 'lP_shortened',title: 'LP简称',width:120,},
            {field: 'english_shortened', title: '英文简称',width:120,},
            {field: 'lP_full', title: 'LP全称', width:120,},
            {field: 'english_full', title: '英文全称', width:120,},
            {field: 'capital_source', title: '资本来源', width:120,},
            {field: 'lP_type', title: 'LP类型', width:120,},
            {field: 'establish_time', title: '成立时间', width:200,},
            {field: 'administration_fund', title: '管理资金', width:200,},
            {field: 'whether_cast', title: '是否已投', width:200,},
            {field: 'contacts_Id', title: '联系人编号', width:120,},
            {field: 'url', title: '网址', width:120,},
            {field: 'headquarters_location', title: '总部所在地', width:200,},
        ]
    });

});
//完成编辑
function save() {
    var rowTable=$('#legalTable').bootstrapTable('getData',true);
    var map="{";
    $.each(rowTable[0],function (k,v) {
        if(k=='legal_id'&&updataID!=null){
            map+='"'+k+'":"'+updataID+'",';
        }else {
            map+='"'+k+'":"'+$('#'+k+'').val()+'",';
        }
    });
    map=map.substring(0,map.length-1)+"}";
    if(updateoradd==1){//增加
        $.ajax({//增加的对象传入数据库
            url:'addOneLegal.xhtml',
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
            url:'/updateLegal.xhtml',
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
    $("#legalTable").bootstrapTable('refresh',{url:'/selectLegalAll.xhtml'});
}

//删除
function del(index) {
    $.ajax({
        url:'delLegal.xhtml',
        method:'post',
        data:{legal_id:index},
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
    updataID=row.legal_id;
    var dropDownBoxOne;
    // $.ajax({
    //     url:'',
    //     methods:"post",
    //     data:{'type':row.Legal_type},
    //     success:function (data) {
    //         dropDownBoxOne=dropDownBox(row.Legal_type,JSON.parse(data));
    //     }
    // });
    $('#legalTable').bootstrapTable('updateRow',{index:index, row:{
        legal_id:row.legal_id,
        lP_shortened:'<input id="lP_shortened" value="'+row.lP_shortened+'"  type="text" class="form-control"  style="width:100px">',
        english_shortened:'<input  id="english_shortened" value="'+row.english_shortened+'" type="text" class="form-control"  style="width:100px">',
        lP_full:'<input  id="lP_full" value="'+row.lP_full+'" type="text" class="form-control"   style="width:100px">',
        english_full:'<input id="english_full"  value="'+row.english_full+'" type="text"   class="form-control" style="width:100px" >',
        capital_source:'<input id="capital_source"  value="'+row.capital_source+'" type="text"  class="form-control"  style="width:100px" >',
        lP_type:'<input id="lP_type" class="form-control"  value="'+row.lP_type+'"  type="text" style="width:100px" >',
        establish_time:'<input  id="establish_time" value="'+row.establish_time+'" type="date" class="form-control"   style="width:180px" >',
        administration_fund:'<input id="administration_fund" value="'+row.administration_fund+'" type="text" class="form-control"   style="width:180px" >',
        whether_cast:'<input  id="whether_cast"  value="'+row.whether_cast+'" type="text" class="form-control" style="width:180px" >',
        contacts_Id:'<input  id="contacts_Id" value="'+row.contacts_Id+'" type="text" class="form-control"   style="width:100px" >',
        url:'<input  id="url" value="'+row.url+'" type="text" class="form-control"  style="width:100px" >',
        headquarters_location:'<input id="headquarters_location"value="'+row.headquarters_location+'"  type="text" class="form-control"   style="width:180px" >'
    }});





}

//增加一行
function add(index) {
    $('#legalTable').bootstrapTable('insertRow',{index:index, row:{
        legal_id:'0',
        lP_shortened:'<input type="text" class="form-control"  id="lP_shortened"  style="width:100px">',
        english_shortened:'<div class="btn-group"  style="width:130px"> ' +
        '    <span type="text" class="form-control"  id="Legal_type"  style="width:80px">请选择</span>'+
        '    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="height:34px;margin-top:-34px;margin-left:75px">' +
        '        <span class="caret"></span>' +
        '    </button>' +
        '    <ul class="dropdown-menu" role="menu">' +
        '        <li><a href="#">功能</a></li>' +
        '        <li><a href="#">另一个功能</a></li>' +
        '    </ul>' +
        '</div>',
        lP_full:'<input type="text" id="lP_full"   class="form-control"   style="width:100px">',
        english_full:'<input type="text"  class="form-control"  id="english_full" style="width:100px" >',
        capital_source:'<input type="text"  class="form-control"  id="capital_source" style="width:100px" >',
        lP_type:'<input type="text" class="form-control"  id="lP_type" style="width:100px" >',
        establish_time:'<input type="date" class="form-control"  id="establish_time" style="width:180px" >',
        administration_fund:'<input type="text" class="form-control"  id="administration_fund" style="width:180px" >',
        whether_cast:'<input type="text" class="form-control"  id="whether_cast" style="width:180px" >',
        contacts_Id:'<input type="text" class="form-control"  id="contacts_Id" style="width:100px" >',
        url:'<input type="text" class="form-control"  id="url" style="width:100px" >',
        headquarters_location:'<input type="text" class="form-control"  id="headquarters_location" style="width:180px" >'
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
