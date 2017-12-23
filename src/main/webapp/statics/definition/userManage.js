$.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);
$(function () {
    $('#fundTable').bootstrapTable({
        url: '/queryUserAll.xhtml',
        columns: [{
            field: 'userId',
            title: '编号'
        }, {
            field: 'loginName',
            title: '登录名字'
        }, {
            field: 'password',
            title: '密码'
        }, {
            field: 'userName',
            title: '用户姓名'
        },{
            field: 'phoneNumber',
            title: '电话号码'
        },{
            field: 'email',
            title: '邮箱'
        },{
            field: 'loginTime',
            title: '登录时间'
        },{
            field: 'createTime',
            title: '创建时间'
        },],
    });

});