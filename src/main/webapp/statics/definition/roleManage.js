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
        url: '/queryAuthorAllManage.xhtml',
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
        // search:true,//搜索框
        // strictSearch:false,//模糊查询
        singleSelect:true,//将禁止多选
        queryParams:function(params) {
            page=params.offset;
            num=params.limit;

            return {
                index:params.offset,
                size:params.limit,
            };
        },
        columns: [
            {
                checkbox: true
            },{
            field: 'id',
            title: '权限编号',
            sortable:true

        }, {
            field: 'name',
            title: '权限名字',
            sortable:true,

        }, {
            field: 'pid',
            title: '权限父编号',
            sortable:true,

        }, {
            field: 'isParent',
            title: '是否父节点',
            sortable:true,
        },{
            field: 'authorDescribe',
            title: '描述',
            sortable:true,
        },{
            field: 'authorUrl',
            title: '请求地址',
            sortable:true,
        }


        ],
    })




})



