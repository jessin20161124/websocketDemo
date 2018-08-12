<%@ page import="java.net.InetAddress" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
    <%
        // 需要改为本地IP地址
        String ip = "localhost";
    %>
</head>
<body>
<script type="text/javascript" src="http://cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript" src="http://<%=ip%>:8080/static/sockjs.min.js"></script>
<script type="text/javascript">

    var websocket = null;
    if ('WebSocket' in window) {
        // new WebSocket也可以
        // SockJS应该是兜底的，先尝试使用WebSocket，可以使用则使用WebSocket，否则使用http等长轮询。
        websocket = new SockJS("http://<%=ip%>:8080/push/socketServer");
    }
    else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket("ws://<%=ip%>:8080/socketServer");
    }
    else {
        // 直接使用SockJS
        websocket = new SockJS("http://<%=ip%>:8080/push/sockjs/socketServer");
    }
    websocket.onopen = onOpen;
    websocket.onmessage = onMessage;
    websocket.onerror = onError;
    websocket.onclose = onClose;

    function onOpen(openEvt) {
        alert("websokcet连接创建成功");
    }

    function onMessage(evt) {
        alert("收到服务端数据：" + evt.data);
    }
    function onError() {
        alert("出错!");
    }
    function onClose() {
        alert("关闭")
    }

    function doSend() {
        if (websocket.readyState == WebSocket.OPEN) {
            var msg = document.getElementById("inputMsg").value;
            websocket.send(msg);//调用后台handleTextMessage方法
            alert("发送成功!");
        } else {
            alert("连接失败!连接原来状态：" + websocket.readyState + " ，希望为：" + websocket.OPEN);
        }
    }

    window.close = function () {
        websocket.onclose();
    }
</script>
请输入：<textarea rows="5" cols="10" id="inputMsg" name="inputMsg"></textarea>
<button onclick="doSend();">发送</button>
</body>
</html>