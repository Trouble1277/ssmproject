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
        var rowTable=$('#fundTable').bootstrapTable('getData',true);
        var tempFundId=rowTable[rowTable.length-1].fund_Id;
        if(tempFundId!=0&&updateoradd!=2){
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
            $('#fundTable').bootstrapTable('removeByUniqueId',0);
        }else if(updateoradd==2){
            refreshTable();
        }
        updateoradd=0;
    })

    //表格加载
    $('#fundTable').bootstrapTable({
        url: '/selectFundAll.xhtml',
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
        uniqueId:'fund_Id',//唯一标示列
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
            delrow=row.fund_Id;
        },
        columns: [
            {field: 'fund_check',radio:true},
            {field: 'fund_Id',title: '编号',class:'info',},
            {field: 'fund_name',title: '基金名称',width:120,},
            {field: 'fund_type', title: '基金类型',width:120,},
            {field: 'organization_form', title: '组织形式', width:120,},
            {field: 'capital_source', title: '资本来源', width:120,},
            {field: 'fund_status', title: '基金状态', width:120,},
            {field: 'fund_net', title: '基金净值', width:120,},
            {field: 'raise_start_time', title: '募集开始时间', width:200,},
            {field: 'raise_accomplish_time', title: '募集完成时间', width:200,},
            {field: 'fund_duration_deadline', title: '基金存续期截止日', width:200,},
            {field: 'ribution_scale', title: '募集规模', width:120,},
            {field: 'invest_currency', title: '投资币种', width:120,},
            {field: 'start_invest_year', title: '开始投资年度', width:200,},
        ]
    });

});
//完成编辑
function save() {
    var rowTable=$('#fundTable').bootstrapTable('getData',true);
    var map="{";
    $.each(rowTable[0],function (k,v) {
        if(k=='fund_Id'&&updataID!=null){
            map+='"'+k+'":"'+updataID+'",';
        }else {
            map+='"'+k+'":"'+$('#'+k+'').val()+'",';
        }
    });
    map=map.substring(0,map.length-1)+"}";
    if(updateoradd==1){//增加
        $.ajax({//增加的对象传入数据库
            url:'addOneFund.xhtml',
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
            url:'/updateFund.xhtml',
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
    $("#fundTable").bootstrapTable('refresh',{url:'/selectFundAll.xhtml'});
}

//删除
function del(index) {
    $.ajax({
        url:'delFund.xhtml',
        method:'post',
        data:{fund_Id:index},
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
    updataID=row.fund_Id;
    var dropDownBoxOne;
    $.ajax({
        url:'',
        methods:"post",
        data:{'type':row.fund_type},
        success:function (data) {
            dropDownBoxOne=dropDownBox(row.fund_type,JSON.parse(data));
        }
    });
    $('#fundTable').bootstrapTable('updateRow',{index:index, row:{
        fund_Id:row.fund_Id,
        fund_name:'<input type="text" class="form-control"  id="fund_name" value="'+row.fund_name+'"  style="width:100px">',
        fund_type:dropDownBoxOne,
        organization_form:'<input type="text" id="organization_form" value="'+row.organization_form+'" class="form-control"   style="width:100px">',
        capital_source:'<input type="text"  class="form-control" value="'+row.capital_source+'"  id="capital_source" style="width:100px" >',
        fund_status:'<input type="text"  class="form-control"  value="'+row.fund_status+'"  id="fund_status" style="width:100px" >',
        fund_net:'<input type="text" class="form-control"  value="'+row.fund_net+'"  id="fund_net" style="width:100px" >',
        raise_start_time:'<input type="date" class="form-control"  value="'+row.raise_start_time+'"  id="raise_start_time" style="width:180px" >',
        raise_accomplish_time:'<input type="date" class="form-control"  value="'+row.raise_accomplish_time+'"  id="raise_accomplish_time" style="width:180px" >',
        fund_duration_deadline:'<input type="date" class="form-control"  value="'+row.fund_duration_deadline+'"  id="fund_duration_deadline" style="width:180px" >',
        ribution_scale:'<input type="text" class="form-control"  value="'+row.ribution_scale+'"  id="ribution_scale" style="width:100px" >',
        invest_currency:'<input type="text" class="form-control"  value="'+row.invest_currency+'"  id="invest_currency" style="width:100px" >',
        start_invest_year:'<input type="date" class="form-control"  value="'+row.start_invest_year+'"  id="start_invest_year" style="width:180px" >'
    }});





}

//增加一行
function add(index) {
    $('#fundTable').bootstrapTable('insertRow',{index:index, row:{
        fund_Id:'0',
        fund_name:'<input type="text" class="form-control"  id="fund_name"  style="width:100px">',
        fund_type:'<div class="btn-group"  style="width:130px"> ' +
        '    <span type="text" class="form-control"  id="fund_type"  style="width:80px">请选择</span>'+
        '    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="height:34px;margin-top:-34px;margin-left:75px">' +
        '        <span class="caret"></span>' +
        '    </button>' +
        '    <ul class="dropdown-menu" role="menu">' +
        '        <li><a href="#">功能</a></li>' +
        '        <li><a href="#">另一个功能</a></li>' +
        '    </ul>' +
        '</div>',
        organization_form:'<input type="text" id="organization_form"   class="form-control"   style="width:100px">',
        capital_source:'<input type="text"  class="form-control"  id="capital_source" style="width:100px" >',
        fund_status:'<input type="text"  class="form-control"  id="fund_status" style="width:100px" >',
        fund_net:'<input type="text" class="form-control"  id="fund_net" style="width:100px" >',
        raise_start_time:'<input type="date" class="form-control"  id="raise_start_time" style="width:180px" >',
        raise_accomplish_time:'<input type="date" class="form-control"  id="raise_accomplish_time" style="width:180px" >',
        fund_duration_deadline:'<input type="date" class="form-control"  id="fund_duration_deadline" style="width:180px" >',
        ribution_scale:'<input type="text" class="form-control"  id="ribution_scale" style="width:100px" >',
        invest_currency:'<input type="text" class="form-control"  id="invest_currency" style="width:100px" >',
        start_invest_year:'<input type="date" class="form-control"  id="start_invest_year" style="width:180px" >'
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
