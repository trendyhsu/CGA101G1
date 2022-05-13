/*-----------------------------Service客服圖文選單----------------------------*/
let robot = document.querySelector('#robot');
let serviceMenu = document.querySelector('#service-menu');
let serviceForm = document.querySelector('#service .service-form');
let emailForm = document.querySelector('#service .mailForm');
let chatInput = document.querySelector("#service > div.chat > input[type=text]");
let chatContent = document.querySelector('#service .content');
let chatSend = document.querySelector('#service #send');
let firstMeet = true;

serviceMenu.addEventListener('click', serviceTog)
function serviceTog() {
	serviceForm.classList.toggle('active');
}
let service = document.querySelector('#service');
robot.addEventListener('click', () => {
	service.classList.toggle('active');
	$.ajax({
		url: `/CGA101G1/fqkeyword/all`,
		type: "Post",
		success: function(data) {
			saveKeyWord(data);
		}
	});

	if (firstMeet) {
		setTimeout(() => { robotMessage('您好我是阿帕～有問題就儘管問我吧！') }, 1000);
		firstMeet = false;
	}


});

let keyWordMap = null;
function saveKeyWord(data) {
	let keyWords = JSON.parse(data);
	keyWordMap = new Map();

	for (let keyWord of keyWords) {
		//處理String變成String[]
		let keyWordArr = keyWord.fqKeyWordContent.split("");
		//放入Map
		keyWordMap.set(keyWordArr, keyWord.answerContent);
	}
	//檢視用(全體)
	// for (let [key, value] of keyWordMap) {
	// 	console.log(`The value of ${key[0]} in Map is ${value}`);
	// }
	//獲取key值(的單個字)
	//	for (let key of keyWordMap.keys()) {
	//		// for (let i = 0; i < key.length; i++) {
	//		// console.log(key[i]);
	//		// }
	//		
	//		console.log(key)
	//	}
}
$('.service-form').click(function(event) {

	if (event.target.id !== 'emailQ') {

		if ($(event.target).attr('value') === undefined) {
			chatInput.value = $(event.target).parent().attr('value');
		} else {
			chatInput.value = $(event.target).attr('value');
		}
		sendMessage();

	} else {
		//將email的表單打開(更改display)

		emailForm.style.display = 'flex';
		//將聊天室隱藏(同樣以更改display的方式)
		chatContent.style.display = 'none';
		//將圖文選單關閉並且停用打字
		serviceForm.classList.remove('active');
		chatInput.setAttribute('disabled', '');
		chatInput.setAttribute('placeholder', '寄信中...');
		//圖文選單的按鈕圖案改成X，並移除圖文選單開關功能
		serviceMenu.classList.add('fa-times');
		serviceMenu.removeEventListener('click', serviceTog);
		//圖文選單變成X，點擊便會退回聊天室
		serviceMenu.addEventListener('click', emailBackToChat);
	}

	/*----------------------------------------*/
	/*----------------------------------------*/
	/*----------------------------------------*/
	/*----------------------------------------*/
	/*----------------------------------------*/
	/*----------------------------------------*/
})
function emailBackToChat() {
	emailForm.style.display = 'none';
	chatContent.style.display = 'flex';
	serviceMenu.addEventListener('click', serviceTog);
	serviceMenu.classList.remove('fa-times');
	chatInput.removeAttribute('disabled');
	chatInput.removeAttribute('placeholder');
	serviceMenu.removeEventListener('click', emailBackToChat);
}

/*表單回報送出按鈕*/
let mailBtn = document.querySelector('#mailFormBtn');
mailBtn.addEventListener('click', () => {
	let receiver = document.getElementById('from');
	let title = document.getElementById('title');
	let content = document.getElementById('content');

	$.ajax({
		url: `/CGA101G1/service/mailform?receiver=${receiver.value}&title=${title.value}&content=${content.value}`,
		type: 'post',
		success: function(data) {
			robotMessage(data);
			if(data=="阿帕已成功幫您寄出信件囉!"){
			document.getElementById('from').value = '';
			document.getElementById('title').value = '';
			document.getElementById('content').value = '';
			}
		}
	})
	emailBackToChat();
});

/*-----------------------------Service客服聊天室--------------------------------*/


//點擊打字的送出按鈕傳送訊息
chatSend.addEventListener('click', () => {
	if (chatInput.value.trim().length !== 0) {
		sendMessage();
		chatContent.scrollTop = chatContent.scrollHeight;
	}
});

//按enter也可以傳送訊息，但是空白不能傳送，並且在傳送時卷軸到最底部
chatInput.addEventListener('keydown', (event) => {
	if (event.keyCode == 13) {
		if (chatInput.value.trim().length !== 0) {
			sendMessage();
			chatContent.scrollTop = chatContent.scrollHeight;
		}
	};
});

//let OneMessage = document.querySelector('div.OneMessage');

/*--------------------WebSocket--------------------------*/
let MyPoint = "/servicews/" + Date.now() % 100000; //JavaServlet
let host = window.location.host;
let path = window.location.pathname;
let webCtx = path.substring(0, path.indexOf('/', 1));
let endPointURL = "ws://" + host + webCtx + MyPoint;
let self = 'guest' + Date.now() % 100000;
//console.log(MyPoint);
let webSocket = null;

function connect() {
	webSocket = new WebSocket(endPointURL);
	webSocket.onopen = function(event) {
		console.log('Connect Success!');//----------------------------------1
		chatContent.append('已連線!左下角X離開');//--------------------------2
		serviceMenu.classList.add('fa-times');
		serviceMenu.removeEventListener('click', serviceTog);
		serviceMenu.addEventListener('click', function() {
			if (webSocket.readyState === WebSocket.OPEN) {
				webSocket.close();
			}
		});
	};
	webSocket.onmessage = function(event) { //接收後端來的訊息，顯示在畫面上
		var jsonObj = JSON.parse(event.data);

		if ("chat" === jsonObj.type) {
			if (jsonObj.sender !== self) {
				robotMessage(jsonObj.message);
			}

			chatContent.scrollTop = chatContent.scrollHeight;
		}

		if ("picture" === jsonObj.type && jsonObj.sender !== self) {
			//			let newAnswer = document.querySelector('#service .OneService').cloneNode(true);
			//			let messageLine = document.createElement('div');
			//			messageLine.classList.add('messageLine');
			//			newAnswer.style.display = 'flex';
			//			newAnswer.firstChild.nextSibling.textContent = answer;
			//			messageLine.append(newAnswer);
			//			chatContent.append(messageLine);
		}
	};
	webSocket.onclose = function(event) {
		console.log("Disconnected!");
		$('#service .content').on('DOMSubtreeModified', robotApa);
		serviceMenu.classList.remove('fa-times');
		serviceMenu.addEventListener('click', serviceTog);
	};
}


/*----------------------------*/
//拿來累積「機器人不明白」的次數
let badReq = 0;
$('#service .content').on('DOMSubtreeModified', robotApa);

function robotApa() {

	if ($(this).children().last().children().attr('class') === 'OneMessage') {

		//將客戶端送來的字拆成陣列
		let clientTextArr = $(this).children().last().text().trim().split("");
		//拿來判斷客戶端輸入的字有沒有在關鍵字群裡
		let result = true;
		//拿來儲存目前最高機率的關鍵字，預設為不明白
		let mostLikeKeyWord = '不太明白您的意思';
		//機率，預設為0.3
		let approximation = 0.3;

		for (let keyWord of keyWordMap.keys()) {
			//儲存比對後相同的字數
			let count = 0;

			//計算客戶端輸入的文字 在 一個關鍵字之中 有幾個字相同
			for (let letter of clientTextArr) {
				for (let keyWordletter of keyWord) {
					if (letter === keyWordletter) {
						count++;
					}
				}
			}

			//如果 相同的字 / 關鍵字的總字數 > 0.3 
			//就替換掉目前最相似的詞 及 目前最符合機率
			//如果客戶端輸入的字比對下一個關鍵字 更加符合 再進行替換
			if (count / keyWord.length > approximation) {
				mostLikeKeyWord = keyWord;
				approximation = count / keyWord.length;
				console.log(approximation);
			}
		}

		//如果機器人聽不懂超過3次，便詢問需要協助嗎
		if (badReq > 2 && approximation <= 0.3) {
			robotMessage('3秒後客服抵達現場...');
			chatInput.setAttribute('disabled', '');
			chatInput.setAttribute('placeholder', '無法打字的狀態');
			serviceMenu.classList.add('fa-times');
			serviceMenu.removeEventListener('click', serviceTog);
			result = false;
			badReq = 0;
			window.setTimeout(callService, 3000);
		}


		if (result === true) {
			//如果機率從來沒改變則輸出原本的預設(我不明白您的意思)，並計點一次
			if (approximation <= 0.3) {
				robotMessage(mostLikeKeyWord);
				badReq++;
			} else {
				//如果機率有改變過，代表有更相似的關鍵字，並且來拿輸出
				robotMessage(keyWordMap.get(mostLikeKeyWord));
				badReq = 0;
			}
		}
	}
}

function callService() {
	serviceForm.classList.remove('active');
	let myPhone = document.querySelector('#service .callService');
	myPhone.style.display = 'flex';
	chatContent.style.display = 'none';
	chatInput.setAttribute('placeholder', '客服來電中...');
}



$('.phone-container').first().click(function() {
	console.log('已接聽客服');
	$('#service .callService').css('display', 'none');
	chatContent.innerHTML = '';
	connect();
	chatContent.style.display = 'flex';
	chatInput.removeAttribute('disabled');
	chatInput.removeAttribute('placeholder');
	$('#service .content').off('DOMSubtreeModified');
})

$('.phone-container').last().click(function() {
	console.log('已拒絕客服');
	$('#service .callService').css('display', 'none');
	chatContent.style.display = 'flex';
	serviceMenu.addEventListener('click', serviceTog);
	serviceMenu.classList.remove('fa-times');
	chatInput.removeAttribute('disabled');
	chatInput.removeAttribute('placeholder');
})

//傳送訊息的方法
function sendMessage() {
	let newInput = document.querySelector('#service .OneMessage').cloneNode(true);
	let messageLine = document.createElement('div');
	messageLine.classList.add('messageLine');
	newInput.style.display = 'flex';
	newInput.lastChild.previousSibling.textContent = chatInput.value;
	messageLine.append(newInput);
	chatContent.append(messageLine);
	//--------------------WebSocket傳送-----------
	if (webSocket !== null) {
		let jsonObj = {
			"type": "chat",
			"sender": self,
			"receiver": "manager",
			"message": chatInput.value
		};
		webSocket.send(JSON.stringify(jsonObj));
	}
	//-------------------------------------------
	chatInput.value = '';
	serviceForm.classList.remove('active');

}

function robotMessage(answer) {
	let newAnswer = document.querySelector('#service .OneService').cloneNode(true);
	let messageLine = document.createElement('div');
	messageLine.classList.add('messageLine');
	newAnswer.style.display = 'flex';
	newAnswer.firstChild.nextSibling.textContent = answer;
	messageLine.append(newAnswer);
	chatContent.append(messageLine);

}
/*-------------機器人點開後，聊天室的自動卷軸---------------*/
//let box, boxHeight;
//box = document.querySelector('#service .content');
//
////捲到底(公式): box.scrollTop + boxHeight = box.scrollHeight
////取得高度
//boxHeight = window.getComputedStyle(box).getPropertyValue("height");
//boxHeight = parseInt(boxHeight);
//
//function scroll() {
//	if (box.scrollTop + boxHeight < box.scrollHeight) {
//		box.scrollTop = box.scrollHeight; //向下捲動至最底
//	}
//}
//測試視窗高度用
// setInterval(() => {
//     console.log("box.scrollTop= " + box.scrollTop);
//     console.log("boxHeight= " + boxHeight);
//     console.log("box.scrollHeight= " + box.scrollHeight);
//     console.log("-----------------------")
// }, 1000)
/*-----------------------機器人視窗圖片-----------------------*/
function chooseFile() {
	let files = document.querySelector('.chat input[type="file"]').files;
	if (files.length > 0) {
		let fileReader = new FileReader();
		fileReader.readAsDataURL(files[0]);
		fileReader.onload = function(e) {
			if (webSocket !== null) {
				console.log(e.target.result);
				//				e.target.result.replace(replacements);
				let jsonObj = {
					"type": "picture",
					"sender": self,
					"receiver": "manager",
					"message": e.target.result,
				};
				webSocket.send(JSON.stringify(jsonObj));
			}
		}
	}
}

