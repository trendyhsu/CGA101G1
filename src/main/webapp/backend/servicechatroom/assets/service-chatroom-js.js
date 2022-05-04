let chatInput = document.querySelector('.answerBar input');
let sendBtn = document.querySelector('.answerBar i');
let chatContent = document.querySelector('.chat-content');
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

function sendMessage() {
    let newInput = document.querySelector('.chat-content .messageLine .oneAnswer').cloneNode(true);
    newInput.style.display = 'block';
    newInput.textContent = chatInput.value;
    webSocket.send(chatInput.value);
    chatInput.value = '';
    let messageLine = document.createElement('div');
    messageLine.classList.add('messageLine');
    messageLine.append(newInput);
    chatContent.append(messageLine);
}

function getMessage(message) {
    let newAnswer = document.querySelector('.chat-content .messageLine .oneMessage').cloneNode(true);
    newAnswer.style.display = 'block';
    newAnswer.textContent = message;
    let messageLine = document.createElement('div');
    messageLine.classList.add('messageLine');
    messageLine.append(newAnswer);
    chatContent.append(messageLine);
}

/*--------------------------WebSocket-------------------------*/

let MyPoint = "/servicews"; //JavaServlet
let host = window.location.host;
let path = window.location.pathname;
let webCtx = path.substring(0, path.indexOf('/', 1));
let endPointURL = "ws://" + host + webCtx + MyPoint;

let webSocket;
connect();
function connect() {
    webSocket = new WebSocket(endPointURL);
    webSocket.onopen = function (event) {
        console.log('Connect Success!');//----------------------------------1
    };
    webSocket.onmessage = function (event) { //接收後端來的訊息，顯示在畫面上
        getMessage(event.data);
    }
}







