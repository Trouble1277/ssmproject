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
        var rowTable=$('#lpfundTable').bootstrapTable('getData',true);
        var templpfundId=rowTable[rowTable.length-1].lp_fund_ribution_Id;
        if(templpfundId!=0&&updateoradd!=2){
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
            $('#lpfundTable').bootstrapTable('removeByUniqueId',0);
        }else if(updateoradd==2){
            refreshTable();
        }
        updateoradd=0;
    })

    //表格加载
    $('#lpfundTable').bootstrapTable({
        url: '/selectLp_fundAll.xhtml',
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
        uniqueId:'lp_fund_ribution_Id',//唯一标示列
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
            delrow=row.lp_fund_ribution_Id;
        },
        columns: [
            {field: 'lpfund_check',radio:true},
            {field: 'lp_fund_ribution_Id',title: 'LP基金募集编号',},
            {field: 'lp_type',title: 'LP类型',width:120,},
            {field: 'lp_Id', title: 'LP编号',width:120,},
            {field: 'commitment_sum', title: '承诺金额', width:120,},
            {field: 'reality_contributive', title: '实际出资', width:120,},
            {field: 'fund_Id', title: '基金编号', width:120,},
            {field: 'remark', title: '备注', width:200,}
        ]
    });

});
//完成编辑
function save() {
    var rowTable=$('#lpfundTable').bootstrapTable('getData',true);
    var map="{";
    $.each(rowTable[0],function (k,v) {
        if(k=='lp_fund_ribution_Id'&&updataID!=null){
            map+='"'+k+'":"'+updataID+'",';
        }else {
            map+='"'+k+'":"'+$('#'+k+'').val()+'",';
        }
    });
    map=map.substring(0,map.length-1)+"}";
    if(updateoradd==1){//增加
        $.ajax({//增加的对象传入数据库
            url:'addOneLp_fund.xhtml',
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
            url:'/updateLp_fund.xhtml',
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
    $("#lpfundTable").bootstrapTable('refresh',{url:'/selectLp_fundAll.xhtml'});
}

//删除
function del(index) {
    $.ajax({
        url:'delLp_fund.xhtml',
        method:'post',
        data:{lp_fund_ribution_Id:index},
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
    updataID=row.lp_fund_ribution_Id;
    var dropDownBoxOne;
    // $.ajax({
    //     url:'',
    //     methods:"post",
    //     data:{'type':row.Legal_type},
    //     success:function (data) {
    //         dropDownBoxOne=dropDownBox(row.Legal_type,JSON.parse(data));
    //     }
    // });
    $('#lpfundTable').bootstrapTable('updateRow',{index:index, row:{
        lp_fund_ribution_Id:row.lp_fund_ribution_Id,
        lp_type:'<input id="lp_type" value="'+row.lp_type+'"  type="text" class="form-control"  style="width:100px">',
        lp_Id:'<input  id="lp_Id" value="'+row.lp_Id+'" type="text" class="form-control"  style="width:100px">',
        commitment_sum:'<input  id="commitment_sum" value="'+row.commitment_sum+'" type="text" class="form-control"   style="width:100px">',
        reality_contributive:'<input id="reality_contributive"  value="'+row.reality_contributive+'" type="text"   class="form-control" style="width:100px" >',
        fund_Id:'<input id="fund_Id"  value="'+row.fund_Id+'" type="text"  class="form-control"  style="width:100px" >',
        remark:'<input id="remark" class="form-control"  value="'+row.remark+'"  type="text" style="width:100px" >',
     }});





}

//增加一行
function add(index) {
    $('#lpfundTable').bootstrapTable('insertRow',{index:index, row:{
        lp_fund_ribution_Id:'0',
        lp_type:'<input type="text" class="form-control"  id="lp_type"  style="width:100px">',
        lp_Id:'<input type="text" id="lp_Id"   class="form-control"   style="width:100px">',
        commitment_sum:'<input type="text"  class="form-control"  id="commitment_sum" style="width:100px" >',
        reality_contributive:'<input type="text"  class="form-control"  id="reality_contributive" style="width:100px" >',
        fund_Id:'<input type="text" class="form-control"  id="fund_Id" style="width:100px" >',
        remark:'<input type="text" class="form-control"  id="remark" style="width:180px" >',
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
