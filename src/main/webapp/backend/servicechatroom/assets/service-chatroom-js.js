let chatInput = document.querySelector('.answerBar input');
let sendBtn = document.querySelector('.answerBar i');
let chatContent = document.querySelector('.chat-content');
let userName = document.querySelector('.userName');
//點擊送出按鈕傳送訊息
sendBtn.addEventListener('click', () => {
	if (chatInput.value.trim().length !== 0) {
		sendMessage();
	}
});

//按enter也可以傳送訊息，但是空白不能傳送，並且在傳送時卷軸到最底部
chatInput.addEventListener('keydown', (event) => {
	if (event.keyCode == 13) {
		if (chatInput.value.trim().length !== 0) {
			sendMessage();
		}
	};
});

/*--------------------WebSocket--------------------------*/
let MyPoint = "/servicews/manager"; //JavaServlet
let host = window.location.host;
let path = window.location.pathname;
let webCtx = path.substring(0, path.indexOf('/', 1));
let endPointURL = "ws://" + host + webCtx + MyPoint;
let self = 'manager';

let webSocket = null;
connect();
function connect() {
	webSocket = new WebSocket(endPointURL);
	webSocket.onopen = function(event) {
		console.log('Connect Success!');//----------------------------------1

	};
	webSocket.onmessage = function(event) { //接收後端來的訊息，顯示在畫面上
		var jsonObj = JSON.parse(event.data);

		if ("open" === jsonObj.type) {
			refreshGuestList(jsonObj);
		} else if ("history" === jsonObj.type) {
			chatContent.innerHTML = '';

			// 這行的jsonObj.message是從redis撈出跟訪客的歷史訊息，再parse成JSON格式處理
			let messages = JSON.parse(jsonObj.message);
			for (let i = 0; i < messages.length; i++) {

				let historyData = JSON.parse(messages[i]);
				let showMsg = historyData.message;
				if (historyData.sender === self) {
					let newInput = document.querySelector('.oneAnswer').cloneNode(true);
					newInput.style.display = 'block';
					newInput.textContent = showMsg;
					let messageLine = document.createElement('div');
					messageLine.classList.add('messageLine');
					messageLine.append(newInput);
					chatContent.append(messageLine);
				} else {
					let newInput = document.querySelector('.oneMessage').cloneNode(true);
					newInput.style.display = 'block';
					newInput.textContent = showMsg;
					let messageLine = document.createElement('div');
					messageLine.classList.add('messageLine');
					messageLine.append(newInput);
					chatContent.append(messageLine);
				}
			}
			chatContent.scrollTop = chatContent.scrollHeight;
		} else if ("chat" === jsonObj.type) {

			if (jsonObj.sender !== self) {
			
				updateGuestName(jsonObj.sender);
				document.getElementById(jsonObj.sender).click();
			
				let newAnswer = document.querySelector('.oneMessage').cloneNode(true);
				newAnswer.style.display = 'block';
				newAnswer.textContent = jsonObj.message;
				let messageLine = document.createElement('div');
				messageLine.classList.add('messageLine');
				messageLine.append(newAnswer);
				chatContent.append(messageLine);
			}
			chatContent.scrollTop = chatContent.scrollHeight;
		} else if ("close" === jsonObj.type) {
			refreshGuestList(jsonObj);
			updateGuestName('');
			chatContent.innerHTML = '';
			chatContent.style.display = 'none';
		}
	};

	webSocket.onclose = function(event) {
		console.log("Disconnected!");
	};

}



function sendMessage() {
	//傳送訊息的時候先複製對話框樣式，將所輸入的文字送入對話框
	let newInput = document.querySelector('.oneAnswer').cloneNode(true);
	let guest = document.querySelector('.userName').textContent;
	newInput.style.display = 'block';
	newInput.textContent = chatInput.value;
	//如果有連線就傳送資料至webSocket
	if (webSocket !== null) {
		let jsonObj = {
			'type': 'chat',
			'sender': self,
			'receiver': guest,
			'message': chatInput.value
		};
		webSocket.send(JSON.stringify(jsonObj));
	}
	//清空輸入的文字，並繼續完成對話框格式，放入聊天室
	chatInput.value = '';
	let messageLine = document.createElement('div');
	messageLine.classList.add('messageLine');
	messageLine.append(newInput);
	chatContent.append(messageLine);
}


// 有訪客加入或離開就更新列表
function refreshGuestList(jsonObj) {
	let guests = jsonObj.users;
	let userList = document.querySelector('.userList ul');
	userList.innerHTML = '';
	for (let i = 0; i < guests.length; i++) {
		if (guests[i] === self) { continue; }
		userList.innerHTML += `<li id="${guests[i]}"><i class="fa-solid fa-user"></i>${guests[i]}</li>`;
	}
	addListener();
}

//點擊目標人物，會產生與對方的歷史訊息
function addListener() {
	let userList = document.querySelector('.userList ul');
	userList.addEventListener("click", function(e) {
		chatContent.innerHTML = '';
		chatContent.style.display = 'flex';
		chatInput.removeAttribute('disabled');
		chatInput.removeAttribute('placeholder');

		let guest = e.target.textContent;
		updateGuestName(guest);

		var jsonObj = {
			"type": "history",
			"sender": self,
			"receiver": guest,
			"message": ""
		};
		webSocket.send(JSON.stringify(jsonObj));
	});
}
//目前所處的聊天室
function updateGuestName(name) {
	let userName = document.querySelector('.userName');
	userName.textContent = name;
}