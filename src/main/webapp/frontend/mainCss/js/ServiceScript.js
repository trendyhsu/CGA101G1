/*-----------------------------Service客服圖文選單----------------------------*/
let robot = document.querySelector('#robot');
let serviceMenu = document.querySelector('#service-menu');
let serviceForm = document.querySelector('#service .service-form');
serviceMenu.addEventListener('click', serviceTog)
function serviceTog() {
	serviceForm.classList.toggle('active');
}
let service = document.querySelector('#service');
robot.addEventListener('click', () => {
	service.classList.toggle('active');
	connect();
	$.ajax({
		url: `/CGA101G1/fqkeyword/all`,
		type: "Post",
		success: function (data) {
			saveKeyWord(data);
		}
	});
})
let keyWordAns = '';
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
$('.service-form').click(function (event) {

	
/*----------------------------------------*/
/*----------------------------------------*/
/*----------------------------------------*/
/*----------------------------------------*/
/*----------------------------------------*/
/*----------------------------------------*/
/*---- */console.log(event.target);/*---- */
/*----------------------------------------*/
/*----------------------------------------*/
/*----------------------------------------*/
/*----------------------------------------*/
/*----------------------------------------*/
/*----------------------------------------*/
/*----------------------------------------*/
})



let emailQ = document.querySelector('#emailQ');

emailQ.addEventListener('click', () => {
	let emailForm = document.querySelector('#service .mailForm');
	emailForm.style.display = 'flex';
	chatContent.style.display = 'none';
	serviceForm.classList.remove('active');
	chatInput.setAttribute('disabled', '');
	chatInput.setAttribute('placeholder', ' 寄信中...');
	serviceMenu.classList.toggle('fa-times');
	serviceMenu.removeEventListener('click', serviceTog);
	serviceMenu.addEventListener('click', () => {
		emailForm.style.display = 'none';
		chatContent.style.display = 'block';
		serviceMenu.addEventListener('click', serviceTog);
		serviceMenu.classList.remove('fa-times');
		chatInput.removeAttribute('disabled');
		chatInput.removeAttribute('placeholder');
	})
});
/*表單回報送出按鈕*/
let mailBtn = document.querySelector('#mailFormbtn');
mailBtn.addEventListener('click', () => {
	emailForm.style.display = 'none';
})

/*-----------------------------Service客服聊天室--------------------------------*/
let chatInput = document.querySelector("#service > div.chat > input[type=text]");
let chatContent = document.querySelector('#service .content');
let chatSend = document.querySelector('#service #send');

//點擊送出按鈕傳送訊息
chatSend.addEventListener('click', () => {
	if (chatInput.value.trim().length !== 0) {
		sendMessage();
		scroll();
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
let OneMessage = document.querySelector('div.OneMessage');

/*--------------------WebSocket--------------------------*/
let MyPoint = "/servicews"; //JavaServlet
let host = window.location.host;
let path = window.location.pathname;
let webCtx = path.substring(0, path.indexOf('/', 1));
let endPointURL = "ws://" + host + webCtx + MyPoint;

let webSocket;

function connect() {
	webSocket = new WebSocket(endPointURL);
	webSocket.onopen = function (event) {
		console.log('Connect Success!');//----------------------------------1
	};
	webSocket.onmessage = function (event) { //接收後端來的訊息，顯示在畫面上
		robotMessage(event.data);
	}
}


/*----------------------------*/
let badReq = 0;
$('#service .content').on('DOMSubtreeModified', function () {

	if ($(this).children().last().children().attr('class') === 'OneMessage') {

		//將客戶端送來的字拆成陣列
		let clientTextArr = $(this).children().last().text().trim().split("");
		//拿來判斷客戶端輸入的字有沒有在關鍵字群裡
		let result = true;
		//計算「不知道」的次數

		for (let keyWord of keyWordMap.keys()) {
			//計算比對結果用
			let count = 0;
			for (let letter of clientTextArr) {
				for (let keyWordletter of keyWord) {
					if (letter === keyWordletter) {
						count++;
					}
				}
			}
			if (count >= keyWord.length / 2) {
				robotMessage(keyWordMap.get(keyWord));
				result = false;
				break;
			}
		}

		if (badReq > 2) {
			robotMessage('需要尋求真人客服協助您嗎～?');
			result = false;
			badReq = 0;

		}
		if (result === true) {
			robotMessage('不太明白您的意思');
			badReq++;
		}


	}


});








//傳送訊息的方法
function sendMessage() {
	let newInput = document.querySelector('#service .OneMessage').cloneNode(true);
	let messageLine = document.createElement('div');
	messageLine.classList.add('messageLine');

	newInput.style.display = 'flex';
	newInput.lastChild.previousSibling.textContent = chatInput.value;
	messageLine.append(newInput);
	chatContent.append(messageLine);

	webSocket.send(chatInput.value);
	chatInput.value = '';
	serviceForm.classList.remove('active');
	scroll();

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
let box, boxHeight;
box = document.querySelector('#service .content');

//捲到底(公式): box.scrollTop + boxHeight = box.scrollHeight
//取得高度
boxHeight = window.getComputedStyle(box).getPropertyValue("height");
boxHeight = parseInt(boxHeight);

function scroll() {
	if (box.scrollTop + boxHeight < box.scrollHeight) {
		box.scrollTop = box.scrollHeight; //向下捲動至最底
	}
}
//測試視窗高度用
// setInterval(() => {
//     console.log("box.scrollTop= " + box.scrollTop);
//     console.log("boxHeight= " + boxHeight);
//     console.log("box.scrollHeight= " + box.scrollHeight);
//     console.log("-----------------------")
// }, 1000)
