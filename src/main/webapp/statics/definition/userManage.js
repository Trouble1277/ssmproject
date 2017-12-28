$.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);
$(function () {

    var MenuV=JSON.parse($.session.get("authorMenu"));
    var mm=MenuV.split(",") ;
    if (mm[0]==1||mm[1]==1||mm[2]==1){
        $("#add").show();
        $("#save").show();
        $("#cancel").show();
        $("#save").click(function () {
            console.log("click");
        })
        console.log("m ----")
    }
    if (mm[0]==2||mm[1]==2||mm[2]==2){
        $("#del").show();
        console.log("h ----")
    }
    if (mm[0]==3||mm[1]==3||mm[2]==3){
        $("#save").show();
        $("#cancel").show();
    }

    console.log(mm[0]+"mm");
    console.log(mm[1]+"mm");
    console.log(mm[2]+"mm");


    $('#userTable').bootstrapTable({
        url: '/queryUserAll.xhtml',
        method:"get",
        toolbar:"#toolBar",
        sidePagination:true,//设置分页
        showPaginationSwitch:true,//显示据条数选择框
        pagination:true,//格底部显示分页条
        //sortable:true,
        showRefresh:true,//刷新按钮
        showToggle:true,//切换试图
        showColumns:true,//内容列下拉框
        pageNumber:1,
        pageSize:5,
        pageList:[5,10,15,20],
        smartDisplay:true,//显示分页或卡视图
        search:true,//搜索框
        strictSearch:false,//模糊查询
        onDblClickRow:DoubleClick,
        //maintainSelected:true,
        singleSelect:true,//将禁止多选
        //editable:true,

        columns: [{
            field: 'userId',
            title: '编号',
            sortable:true

        }, {
            field: 'loginName',
            title: '登录名字',
            sortable:true,
            editable: {
                type: 'text',
                title: '用户名',
                validate: function (v) {
                    if (!v) return '用户名不能为空';
                },
                mode:"inline",
            }


        }, {
            field: 'password',
            title: '密码',
            sortable:true,
            editable: {
                type: 'text',
                title: '用户名',
                validate: function (v) {
                    if (!v) return '用户名不能为空';
                },
                mode:"inline",

            }

        }, {
            field: 'userName',
            title: '用户姓名',
            sortable:true,
            editable: {
                type: 'text',
                title: '用户名',
                validate: function (v) {
                    if (!v) return '用户名不能为空';
                },
                mode:"inline",
            }

        },{
            field: 'phoneNumber',
            title: '电话号码',
            sortable:true,
            editable: {
                type: 'text',
                title: '用户名',
                validate: function (v) {
                    if (!v) return '用户名不能为空';
                },
                mode:"inline",
            }

        },{
            field: 'email',
            title: '邮箱',
            sortable:true,
            editable: {
                type: 'text',
                title: '用户名',
                validate: function (v) {
                    if (!v) return '用户名不能为空';
                },
                mode:"inline",
            }

        },{
            field: 'loginTime',
            title: '登录时间',
            sortable:true,


        },{
            field: 'createTime',
            title: '创建时间',
            sortable:true,
            editable: {
                type: 'text',
                title: '用户名',
                validate: function (v) {
                    if (!v) return '用户名不能为空';
                },
                mode:"inline",
            }

        },],
    });






});


//当双击的时候
function DoubleClick(row, $element,filed) {
    console.log(row);
    console.log($element);

}

