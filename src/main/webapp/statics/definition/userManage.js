$.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);
$(function () {
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
                }
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
                }
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
                }
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
                }
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
                }
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
                }
            }

        },],
    });

});


//当双击的时候
function DoubleClick(row, $element,filed) {
    console.log(row);
    console.log($element);

}