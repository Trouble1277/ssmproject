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
        var rowTable=$('#enterpriseInformationTable').bootstrapTable('getData',true);
        var tempenterpriseInformationId=rowTable[rowTable.length-1].enterprise_id;
        if(tempenterpriseInformationId!=0&&updateoradd!=2){
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
            $('#enterpriseInformationTable').bootstrapTable('removeByUniqueId',0);
        }else if(updateoradd==2){
            refreshTable();
        }
        updateoradd=0;
    })

    //表格加载
    $('#enterpriseInformationTable').bootstrapTable({
        url: '/selectEnterprise_informationAll.xhtml',
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
        uniqueId:'enterprise_id',//唯一标示列
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
            delrow=row.enterprise_id;
        },
        columns: [
            {field: 'enterpriseInformation_check',radio:true},
            {field: 'enterprise_id',title: '企业编号',class:'info',},
            {field: 'ent_CN_fullname',title: '企业中文全称',width:120,},
            {field: 'ent_CN_abbreviation', title: '企业中文简称',width:120,},
            {field: 'ent_data_establishment', title: '成立时间', width:120,},
            {field: 'ent_entering_register_classify', title: '企业注册登记类型', width:120,},
            {field: 'ent_employees_number', title: '员工人数', width:120,},
            {field: 'legal_id', title: '法人代表编号', width:120,},
            {field: 'ent_organization_code', title: '组织机构代码', width:200,},
            {field: 'ent_industry', title: '行业', width:200,},
            {field: 'ent_related_products', title: '相关产品', width:200,},
            {field: 'contacts_id', title: '联系人编号', width:120,},
        ]
    });

});
//完成编辑
function save() {
    var rowTable=$('#enterpriseInformationTable').bootstrapTable('getData',true);
    var map="{";
    $.each(rowTable[0],function (k,v) {
        if(k=='enterprise_id'&&updataID!=null){
            map+='"'+k+'":"'+updataID+'",';
        }else {
            map+='"'+k+'":"'+$('#'+k+'').val()+'",';
        }
    });
    map=map.substring(0,map.length-1)+"}";
    if(updateoradd==1){//增加
        $.ajax({//增加的对象传入数据库
            url:'addOneEnterprise_information.xhtml',
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
            url:'/updateEnterprise_information.xhtml',
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
    $("#enterpriseInformationTable").bootstrapTable('refresh',{url:'/selectEnterprise_informationAll.xhtml'});
}

//删除
function del(index) {
    $.ajax({
        url:'delEnterprise_information.xhtml',
        method:'post',
        data:{enterprise_id:index},
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
    updataID=row.enterprise_id;
    var dropDownBoxOne;
    $.ajax({
        url:'',
        methods:"post",
        data:{'type':row.ent_CN_abbreviation},
        success:function (data) {
            dropDownBoxOne=dropDownBox(row.ent_CN_abbreviation,JSON.parse(data));
        }
    });
    $('#enterpriseInformationTable').bootstrapTable('updateRow',{index:index, row:{
        enterprise_id:row.enterprise_id,
        ent_CN_fullname:'<input type="text" class="form-control"  id="ent_CN_fullname" value="'+row.ent_CN_fullname+'"  style="width:100px">',
        ent_CN_abbreviation:'<input type="text" class="form-control"  id="ent_CN_abbreviation" value="'+row.ent_CN_abbreviation+'"  style="width:100px">',
        ent_data_establishment:'<input type="date" id="ent_data_establishment" value="'+row.ent_data_establishment+'" class="form-control"   style="width:100px">',
        ent_entering_register_classify:'<input type="text"  class="form-control" value="'+row.ent_entering_register_classify+'"  id="ent_entering_register_classify" style="width:100px" >',
        ent_employees_number:'<input type="text"  class="form-control"  value="'+row.ent_employees_number+'"  id="ent_employees_number" style="width:100px" >',
        legal_id:'<input type="text" class="form-control"  value="'+row.legal_id+'"  id="legal_id" style="width:100px" >',
        ent_organization_code:'<input type="text" class="form-control"  value="'+row.ent_organization_code+'"  id="ent_organization_code" style="width:180px" >',
        ent_industry:'<input type="text" class="form-control"  value="'+row.ent_industry+'"  id="ent_industry" style="width:180px" >',
        ent_related_products:'<input type="text" class="form-control"  value="'+row.ent_related_products+'"  id="ent_related_products" style="width:180px" >',
        contacts_id:'<input type="text" class="form-control"  value="'+row.contacts_id+'"  id="contacts_id" style="width:100px" >',
   }});





}

//增加一行
function add(index) {
    $('#enterpriseInformationTable').bootstrapTable('insertRow',{index:index, row:{
        enterprise_id:'0',
        ent_CN_fullname:'<input type="text" class="form-control"  id="ent_CN_fullname"  style="width:100px">',
        ent_CN_abbreviation: '<input type="text" class="form-control"  id="ent_CN_abbreviation"  style="width:100px">',
        ent_data_establishment:'<input type="date" id="ent_data_establishment"   class="form-control"   style="width:100px">',
        ent_entering_register_classify:'<input type="text"  class="form-control"  id="ent_entering_register_classify" style="width:100px" >',
        ent_employees_number:'<input type="text"  class="form-control"  id="ent_employees_number" style="width:100px" >',
        legal_id:'<input type="text" class="form-control"  id="legal_id" style="width:100px" >',
        ent_organization_code:'<input type="text" class="form-control"  id="ent_organization_code" style="width:180px" >',
        ent_industry:'<input type="text" class="form-control"  id="ent_industry" style="width:180px" >',
        ent_related_products:'<input type="text" class="form-control"  id="ent_related_products" style="width:180px" >',
        contacts_id:'<input type="text" class="form-control"  id="contacts_id" style="width:100px" >',
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
