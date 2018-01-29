var changeProgessTime = null;
var uploadName;
function conference_documentFile(fileId){
    $("#"+fileId).change(function () {
        submitForm();
        $("#formModelProgress").submit();
        var data = $("#formModelProgress").serialize();
        $.ajax({
            url:"/upload.xhtml",
            data:data,
            dataType:'json',
            method:"post",
            success:function(data){
                // console.log(data);
            }
        });

    });
}

function submitForm() {
    $("#modal").show();
    changeProgessTime = setInterval("queryProgress()", 30);
}


function queryProgress() {
    // console.log("进queryProgress");
    var total;
    $.ajax({
        url : "/queryProgress.xhtml",
        method : "post",
        dataType : "JSON",// 要求服务器返回的数据类型
        success : function(data) {// {succes:70}
            // console.log(data);
            var currentContent = data;
            // console.log(currentContent);

            var tempSize = currentContent.substring(0, 2);
            // console.log(tempSize);

            total = parseInt(currentContent) * 3;
            // console.log(total);

            if (total < 300) {
                $("#sizeTotal").text(tempSize + "%");
                $("#progess").css("width", total * 3);
            } else {
                hidelick();
            }

        }
    });
}

function hidelick(){
    $("#modal").hide(3000);
    $.ajax({
        url : "/queryPath.xhtml",
        method : "post",
        dataType : "JSON",// 要求服务器返回的数据类型
        success : function(data) {
            var path = "statics/upload/" + data;
            var html = "";
            // console.log(path + "path");
            if (data!= null) {
                uploadName = path;
            }
            console.log(uploadName);
            // console.log(uploadName + "upload_start");
        }
    });
    clearInterval(changeProgessTime);
}
