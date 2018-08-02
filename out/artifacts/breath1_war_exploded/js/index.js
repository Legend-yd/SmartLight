$(document).ready(function () {
    var json = {"connection": "right"};
    sendInfo(json);
    /**
     * 闪光灯开关
     * */
    var flag = true;
    $("#flashLight").click(function () {
        var data;
        if (flag) {
            data = {"flashLight": "open"};
            flag = !flag;
        } else {
            data = {"flashLight": "close"};
            flag = !flag;
        }

        sendInfo(data);

    });
    $("#level1").click(function () {
        var data = {"lightLevel": "1"};
        sendInfo(data);
    });
    $("#level2").click(function () {
        var data = {"lightLevel": "1"};
        sendInfo(data);

    });
    $("#level3").click(function () {
        var data = {"lightLevel": "1"};
        sendInfo(data);

    });
    $("#level4").click(function () {
        var data = {"lightLevel": "1"};
        sendInfo(data);
    });

    /**
     * 发送数据给服务端
     * */
    function sendInfo(data) {
        $.ajax({
            type: "POST",
            url: "MyServlet",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (message) {

            },
            error: function (message) {
            }
        });
    }
});