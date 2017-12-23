
$(function () {

    $("#Submit").click(function () {
        $("#form1").submit();
       var data = $("#form1").serializeArray();
       console.log(data);
        $.ajax({
            url:"/UserLogin.xhtml",
            data:data,
            dataType:'json',
            method:"post",
            success:function(data){
                if(data.success=='success'){
                    window.location.href = "/index.xhtml";
                }else if(data.error=='error'){
                    console.log('错误')
                }
            }

        });

    })


})
