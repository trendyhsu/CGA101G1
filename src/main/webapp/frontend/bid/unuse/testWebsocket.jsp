<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Java後端WebSocket的Tomcat實現</title>
</head>
<body>
	<div id="test"></div>
	Welcome
	<br />
	<input id="text" type="number" />
	<button onclick="send()">出價</button>
	<hr />
	<button onclick="closeWebSocket()">關閉WebSocket連線</button>
	<hr />
	<div id="message"></div>
</body>
<script type="text/javascript">

	let bidProductNo = 31001;

	var websocket = null;
	//判斷當前瀏覽器是否支援WebSocket
	if ('WebSocket' in window) {
		websocket = new WebSocket(`ws://localhost:8081/CGA101G1/bid/webSocket/31001`);
	} else {
		alert('當前瀏覽器 Not support websocket')
	}
	//連線發生錯誤的回撥方法
	websocket.onerror = function() {
		setMessageInnerHTML("WebSocket連線發生錯誤");
	};
	//連線成功建立的回撥方法
	websocket.onopen = function() {
		setMessageInnerHTML("WebSocket連線成功");
	}
	//接收到訊息的回撥方法
	websocket.onmessage = function(event) {
		setMessageInnerHTML(event.data);
		let test = document.querySelector("#test");
		test.innerHTML=event.data;
	}
	//連線關閉的回撥方法
	websocket.onclose = function() {
		setMessageInnerHTML("WebSocket連線關閉");
	}
	//監聽視窗關閉事件，當視窗關閉時，主動去關閉websocket連線，防止連線還沒斷開就關閉視窗，server端會拋異常。
	window.onbeforeunload = function() {
		closeWebSocket();
	}
	//將訊息顯示在網頁上
	function setMessageInnerHTML(innerHTML) {
		document.getElementById('message').innerHTML = innerHTML + '<br/>';
	}
	//關閉WebSocket連線
	function closeWebSocket() {
		websocket.close();
	}
	//傳送訊息
	function send() {
		var message = document.getElementById('text').value;
		websocket.send(message);
	}
</script>
</html>