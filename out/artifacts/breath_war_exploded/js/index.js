$(document).ready(function () {
    var json = {"connection": "right"};
    sendInfo(json);
    /**
     * 闪光灯开关
     * */
    var flag = true;
    $("#light_on").click(function () {

        var data;
        if (flag) {
            this.src = "images/开关on.png";
            $("#body").animate({opacity: 1}, "slow");
            // $("#body").css("background", "#d0e2dc");
            $("#mask").css("background-image", "url(images/background.png)");
            data = {"flashLight": "open"};
            flag = !flag;
        } else {
            this.src = "images/开关off.png";
            //$("#body").css("background", "#242726");
            $("#body").animate({opacity: 0.8, backgroundColor: '#181918'}, "slow");
            $("#mask").css("background-image", "url(images/)");
            data = {"flashLight": "close"};
            flag = !flag;
        }

        sendInfo(data);
    });
    var level = 1;
    $("#change").click(function () {
        var data;
        if (level === 1) {
            this.src = "images/2.png";
            level++;
            data = {"lightLevel": "2"};
            $("#mask").css("opacity", 0.6);

        } else if (level === 2) {
            this.src = "images/3.png";
            level++;
            data = {"lightLevel": "3"};
            $("#mask").css("opacity", 0.8);

        } else if (level === 3) {
            this.src = "images/4.png";
            $("#mask").css("opacity", 1.0);
            level++;
            data = {"lightLevel": "4"};
        } else if (level === 4) {
            this.src = "images/1.png";
            data = {"lightLevel": "1"};
            level = 1;
            $("#mask").css("opacity", 0.4);
        }
        sendInfo(data);

    });

    $("#level1").click(function () {
        var data = {"lightLevel": "1"};

        sendInfo(data);
    });
    $("#level2").click(function () {
        var data = {"lightLevel": "2"};

        sendInfo(data);
    });
    $("#level3").click(function () {
        var data = {"lightLevel": "3"};

        sendInfo(data);
    });
    $("#level4").click(function () {
        var data = {"lightLevel": "4"};

        sendInfo(data);
    });

    /**
     *
     * 获取连接的设备
     * */

    $("#getclient").click(function () {
        $("#left").animate({left: 0}, "slow");
        var data = {"client": "0"};
        sendInfo(data);
    });

    $("#back").click(function () {
        $("#left").animate({left: '-22%'}, "slow");
        $("#ipshow").empty();
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
                var hostname = message.hostname;
                alert(hostname);
                if (hostname != null && hostname !== "") {
                    var mshow = $("#ipshow");
                    var client = message.hostname;
                    var clientArray = client.split(",");
                    mshow.append("<script>\n" +
                        "    function a(ip) {\n" +
                        "        var data = {\"hostname\":ip};\n" +
                        "        sendInfo(data);\n" +
                        "    }\n" +
                        "</script>");
                    for (var i = 0; i < clientArray.length; i++) {
                        var pi = "p" + i;
                        mshow.append("<p onclick=\"a(" + clientArray[i] + ")\" id=" + pi + ">" + clientArray[i] + "</p>");
                    }
                }

            },
            error: function (message) {
            }
        });
    }
});