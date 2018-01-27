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
        var rowTable=$('#contactsTable').bootstrapTable('getData',true);
        var tempContactsId=rowTable[rowTable.length-1].contacts_Id;
        if(tempContactsId!=0&&updateoradd!=2){
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
            $('#contactsTable').bootstrapTable('removeByUniqueId',0);
        }else if(updateoradd==2){
            refreshTable();
        }
        updateoradd=0;
    })

    //表格加载
    $('#contactsTable').bootstrapTable({
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
            delrow=row.contacts_Id;
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

});
//完成编辑
function save() {
    var rowTable=$('#contactsTable').bootstrapTable('getData',true);
    var map="{";
    $.each(rowTable[0],function (k,v) {
        if(k=='contacts_Id'&&updataID!=null){
            map+='"'+k+'":"'+updataID+'",';
        }else {
            map+='"'+k+'":"'+$('#'+k+'').val()+'",';
        }
    });
    map=map.substring(0,map.length-1)+"}";

    if(updateoradd==1){//增加
        $.ajax({//增加的对象传入数据库
            url:'addOneContacts.xhtml',
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
            url:'/updateContacts.xhtml',
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
    $("#contactsTable").bootstrapTable('refresh',{url:'/selectContactsAll.xhtml'});
}

//删除
function del(index) {
    $.ajax({
        url:'delContacts.xhtml',
        method:'post',
        data:{contacts_Id:index},
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
    updataID=row.contacts_Id;
    var dropDownBoxOne;
    // $.ajax({
    //     url:'',
    //     methods:"post",
    //     data:{'type':row.fund_type},
    //     success:function (data) {
    //         dropDownBoxOne=dropDownBox(row.fund_type,JSON.parse(data));
    //     }
    // });
    $('#contactsTable').bootstrapTable('updateRow',{index:index, row:{
        contacts_Id:row.contacts_Id,
        contacts_name:'<input type="text" class="form-control"  id="contacts_name" value="'+row.contacts_name+'"  style="width:100px">',
        contacts_site:'<input type="text" id="contacts_site" value="'+row.contacts_site+'" class="form-control"   style="width:100px">',
        contacts_phone:'<input type="text"  class="form-control" value="'+row.contacts_phone+'"  id="contacts_phone" style="width:100px" >',
        contacts_postcode:'<input type="text"  class="form-control"  value="'+row.contacts_postcode+'"  id="contacts_postcode" style="width:100px" >',
        contacts_faxes:'<input type="text" class="form-control"  value="'+row.contacts_faxes+'"  id="contacts_faxes" style="width:100px" >',
        contacts_postbox:'<input type="text" class="form-control"  value="'+row.contacts_postbox+'"  id="contacts_postbox" style="width:180px" >',
    }});





}

//增加一行
function add(index) {
    $('#contactsTable').bootstrapTable('insertRow',{index:index, row:{
        contacts_Id:'0',
        contacts_name:'<input type="text" class="form-control"  id="contacts_name"  style="width:100px">',
        contacts_site:'<input type="text" id="contacts_site"   class="form-control"   style="width:100px">',
        contacts_phone:'<input type="text"  class="form-control"  id="contacts_phone" style="width:100px" >',
        contacts_postcode:'<input type="text"  class="form-control"  id="contacts_postcode" style="width:100px" >',
        contacts_faxes:'<input type="text" class="form-control"  id="contacts_faxes" style="width:100px" >',
        contacts_postbox:'<input type="text" class="form-control"  id="contacts_postbox" style="width:180px" >',
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
