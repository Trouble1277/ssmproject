//$.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);
$(function () {

    var MenuV=JSON.parse($.session.get("authorMenu"));
    var mm=MenuV.split(",") ;
    if (mm[0]==1||mm[1]==1||mm[2]==1){
        $("#add").show();

    }
    if (mm[0]==2||mm[1]==2||mm[2]==2){
        $("#del").show();
    }
    if (mm[0]==3||mm[1]==3||mm[2]==3){
    }
    $("#cancel").show();
    $("#save").show();






    $('#roleTable').bootstrapTable({
        url: '/queryRoleAllManage.xhtml',
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
        onDblClickRow:clickRow,
        singleSelect:true,//将禁止多选
        queryParams:function(params) {

            return {
                index:params.offset,
                size:params.limit,
            };
        },
        columns: [
            {
                checkbox: true
            },{
            field: 'roleId',
            title: '角色编号',
            sortable:true

        }, {
            field: 'roleName',
            title: '角色名字',
            sortable:true,

        },{
            field: 'name',
            title: '权限',
            sortable:true,
            formatter:function(data){
                if (data == ""){
                    data="<span style='color: red;'>无权限</span>"
                }
                return data;
            }

        },{
            field: 'roleDescribe',
            title: '角色描述',
            sortable:true,

        }, {
            field: 'createTime',
            title: '创建时间',
            sortable:true,

        }


        ],
    })




})



var flag=1;
var flag2=1;

var Updaterow="";
var  index="";
var beforRoleName="";
//当点击行的时候，获取row里的所有值
function clickRow(row, $element,filed) {
    Updaterow=row;
    index= $element.data('index');//获取到下标
    beforRoleName=Updaterow.roleName;
    if (flag2==1){
        var rows=$("#roleTable").bootstrapTable("updateRow",{
            index:index,
            row:{
                roleName:"<input type='text' class=\"form-control\"  value=\""+Updaterow.roleName+"\" id='roleName' style='width: 120px'>",

                name:"<input type='text' class=\"form-control\" id='updateAuthorValue' value=\""+Updaterow.name+"\">"
                +"<ul id='power' class='ztree' style='position:absolute;margin-top:0; width:320px;height: 200px;'></ul>",
                roleDescribe:"<input type='text' class=\"form-control\" value=\""+Updaterow.roleDescribe+"\" id='password' style='width: 80px'>",
                createTime:"<input type='text' class=\"form-control\" value=\""+Updaterow.createTime+"\" id='userName' style='width: 80px'>",

            }
        });

        //加载ztree
        loadZtree();
        flag2=2;
        flag=2;
    }else if(flag==2){
        Ewin.confirm({ message: "请完成当前的操作" }).on(function (e) {});
    }

}






//增加一行
var AddIndex="";
function AddClick() {
    if (flag2==1){
        var rowTable=$('#roleTable').bootstrapTable('getData',true);
        AddIndex=rowTable.length+1;//增加的行号
        add(AddIndex);

        flag=2;
        flag2=2;
    }else if(flag==2){
        Ewin.confirm({ message: "请完成当前的操作" }).on(function (e) {});
    }
}

//增加一行
function add(index) {
    $('#roleTable').bootstrapTable('insertRow', {
        index: index,
        row: {
            roleId: '0',
            roleName:"<input type='text' class=\"form-control\"  value=' ' id='roleName' style='width: 120px'>",
            name:"<input type='text' class=\"form-control\" id='updateAuthorValue' value=' '>"
            +"<ul id='power' class='ztree' style='position:absolute;margin-top:0; width:320px;height: 200px;'></ul>",
            roleDescribe: '<input type="text"  class="form-control"  id="loginName" style="width:100px" >',
            createTime:"<input type='text' class=\"form-control\" value=' ' id='password' style='width: 80px'>",

        }
    });

    loadZtree();

}



//修改的保存
function saveDate() {
    console.log(Updaterow);

    flag2 = 1;
    if (!Updaterow==""){
        var rowTable=$('#roleTable').bootstrapTable('getData',true);
        var map="{";
        $.each(rowTable[index],function (k,v) {
                map+='"'+k+'":"'+$('#'+k+'').val()+'",'
        });
        map=map.substring(0,map.length-1)+"}";
        $.ajax({
            url:"/updateRole.xhtml",
            data:{user:map,atuhorArray:JSON.stringify(getAuthor())},
            method:"post",
            success:function(data){
                var resultData= JSON.parse(data);
                if(resultData.success=='success'){
                    Updaterow="";
                    $("#roleTable").bootstrapTable("refresh",{
                        url: '/queryRoleAllManage.xhtml'
                    });
                }else if(resultData.error=='error'){
                    Ewin.confirm({ message: "修改失败" }).on(function (e) {

                    });
                }

            }
        })
    }else{//增加的保存


        var rowTable=$('#roleTable').bootstrapTable('getData',true);
        var AddMap="{";
        $.each(rowTable[0],function (k,v) {
            AddMap+='"'+k+'":"'+$('#'+k+'').val()+'",';
        });
        AddMap=AddMap.substring(0,AddMap.length-1)+"}";

        $.ajax({
            url:"/addRole.xhtml",
            data:{user:AddMap,atuhorArray:JSON.stringify(getAuthor())},
            method:"post",
            success:function (data) {
                var resultData= JSON.parse(data);
                if(resultData.success=='success'){
                    $("#updateAuthorValue").val("");//将角色的值设置为空
                    $("#roleTable").bootstrapTable("refresh",{
                        url: '/queryRoleAllManage.xhtml'
                    });
                }else if(resultData.error=='error'){
                    Ewin.confirm({ message: "增加失败" }).on(function (e) {

                    });
                }
            }

        })
    }

}



//当树加载完毕之后勾选父节点
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
    var treeObj = $.fn.zTree.getZTreeObj("power");// 获取树对象
    var nodes = treeObj.getNodes();  /** 获取所有树节点 */
        //获取到权限文本框的值
    var authorNameInput=$("#updateAuthorValue").val();
    var authorNames=authorNameInput.split(",");
    for (var x=0;x<nodes.length;x++){
        for (var i=0;i<authorNames.length;i++){
            if (nodes[x].name==authorNames[i]){
                treeObj.checkNode(nodes[x], true, false);//将父节点设置勾选子节点不勾选
            }
        }
    }
}

//当展开父节点的时候勾选子节点
function zTreeOnExpand(event, treeId, treeNode) {
    var treeObject = $.fn.zTree.getZTreeObj("power");
    var childNodes = treeObject.getNodesByParam("name", treeNode.name, null)[0].children;
    var str="";
    //获取到权限文本框的值
    var authorNameInput2=$("#updateAuthorValue").val();
    var authorNames2=authorNameInput2.split(",");
    for (var x=0;x<childNodes.length;x++){
        str=childNodes[x].name;
        for (var i=0;i<authorNames2.length;i++){
            if (str==authorNames2[i]){
                treeObject.checkNode(childNodes[x], true, true);//将父节点设置勾选子节点不勾选
            }
        }
    }
};


//获取选中的权限
function getAuthor() {
    var UpDateTreeObj = $.fn.zTree.getZTreeObj("power");
    if (UpDateTreeObj!=null){//如果这个对象不等于空
        var atuhorArray=new Array();
        var UpdateNodes = UpDateTreeObj.getCheckedNodes(true);//获取所有选中的节点
        for (var m=0;m<UpdateNodes.length;m++){
            var objs={};
            var authorMenuVal;
            if (!UpdateNodes[m].isParent){//如果他是子节点
                var MenuObjVal="";
                if ($("#AddMenu"+UpdateNodes[m].tId).is(':checked')){
                    MenuObjVal+=$("#AddMenu"+UpdateNodes[m].tId).val()+","//获取增加复选框的值
                }
                if ($("#DelMenu"+UpdateNodes[m].tId).is(':checked')){
                    MenuObjVal+=$("#DelMenu"+UpdateNodes[m].tId).val()+","//获取删复选框的值
                }
                if ($("#UpdateMenu"+UpdateNodes[m].tId).is(':checked')){
                    MenuObjVal+=$("#UpdateMenu"+UpdateNodes[m].tId).val()+","//获取该改加复选框的值
                }
                authorMenuVal=MenuObjVal.substr(0,MenuObjVal.length-1)
                objs.AuthorMenu=authorMenuVal;
                objs.Author=UpdateNodes[m].id;
                atuhorArray.push(objs);
            }else{
                objs.AuthorMenu="";
                objs.Author=UpdateNodes[m].id;
                atuhorArray.push(objs);
            }
        }
        return atuhorArray;
    }

}

//当节点被勾选的时候展示按钮选择
var checkVal="";
function zTreeOnCheck(event, treeId, treeNode) {
    checkVal+=treeNode.name+",";
    $("#updateAuthorValue").val(checkVal);

    if (!treeNode.isParent){//如果是子节点
        if (treeNode.checked){//如果子节点被勾选追加Menu
            var aObj = $("#" + treeNode.tId + "_a");
            var editStr ="<span class='MenuBorder' id='MenuBorder"+treeNode.tId+"'>"
                +"<label><input name='Menu' id='AddMenu"+treeNode.tId+"' type=\"checkbox\" value='1'>增加 </label> "
                +"<label><input name='Menu' id='DelMenu"+treeNode.tId+"' type=\"checkbox\" value='2'>删除 </label> "
                +"<label><input name='Menu' id='UpdateMenu"+treeNode.tId+"' type=\"checkbox\" value='3'>修改 </label> "
                +"</span>";
            aObj.append(editStr);
        }if (!treeNode.checked){//如果子节点取消勾选的话删除Menu
            $("#MenuBorder"+treeNode.tId).remove();

        }
    }

};


//加载ztree的
function loadZtree() {
    setting = {
        async:{
            enable:true,
            type:"post",
            otherParam: {"pid":'0'},
            autoParam:["id","isParent","name"],
            url:"/queryAuthorAll.xhtml"
        },
        view: {
            dblClickExpand: false,

        },
        callback: {

            onAsyncSuccess: zTreeOnAsyncSuccess,//当树加载完毕之后勾选父节点
            onExpand: zTreeOnExpand,//当展开父节点的时候勾选子节点
            onCheck: zTreeOnCheck//当节点被勾选的时候
        },
        check: {
            enable: true
        }
    }
    $.fn.zTree.init($("#power"), setting);
}










