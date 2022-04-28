/*上方列表圖示點擊*/
let menu = document.querySelector('#menu-bars');
let navbar = document.querySelector('.navbar');

menu.onclick = () => {
    menu.classList.toggle('fa-times');
    navbar.classList.toggle('active');
}

/*查詢頁面出現*/
document.querySelector('#search-icon').onclick = () => {
    document.querySelector('#search-form').classList.toggle('active');
}
/*查詢頁面關閉*/
document.querySelector('#search-close').onclick = () => {
    document.querySelector('#search-form').classList.remove('active');
}

/*LOGIN頁面跳出*/
document.querySelector('#login-icon').onclick = () => {
    document.querySelector('#login-form').classList.toggle('active');
}
/*LOGIN頁面關閉 */
document.querySelector('#login-close').onclick = () => {
    document.querySelector('#login-form').classList.remove('active');
}
/*LOGIN上BTN條*/
let x = document.querySelector('#login');
let y = document.querySelector('#register');
let z = document.querySelector('#btn');
function register() {
    x.style.left = '-50%';
    y.style.left = '50%';
    z.style.left = "11rem";
    z.style.width = "60%";
}
function login() {
    x.style.left = '50%';
    y.style.left = '150%';
    z.style.left = "0rem";
    z.style.width = "45%";
}
/*ShoppingCart*/
document.querySelector('#cart').onclick = () => {
    document.querySelector('#shopping-cart').classList.toggle('active');
}

/*幻燈片*/
var swiper = new Swiper(".home-slider", { /*變數名*/
    spaceBetween: 20,
    centeredSlides: true,
    autoplay: {
        delay: 7500,
        disableOnInteraction: false,
    },
    pagination: {
        el: ".swiper-pagination",
        clickable: true,
    },
    loop: true,
});

/*_---------------------------新聞幻燈片----------------------------*/
var swiper = new Swiper(".news-slider", { /*變數名*/
    spaceBetween: 30,
    centeredSlides: true,
    // autoplay: {
    //     delay: 7500,
    //     disableOnInteraction: false,
    // },
    pagination: {
        el: ".swiper-pagination",
        clickable: true,
    },
    loop: true,
});

let newsPlatform = document.querySelector('.news .news-slider .news-type ul');
console.log(newsPlatform.firstElementChild);
newsPlatform.firstElementChild.classList.add('is-now');
/*-----------------------------載入動畫----------------------------*/
function loader() {
    document.querySelector('.loader-container').classList.add('fade-out');
}

function fadeOut() {
    setInterval(loader, 2400);
    let loaderImg = document.querySelector('.loader-container img');
    loaderImg.setAttribute('src', '.\\images\\aliennn.gif');
    runNum();

}
function runNum() {
    let num = document.querySelector('#num');
    let i = 0;
    function myLoop() {
        setTimeout(() => {
            num.textContent = i;
            i++;
            if (i < 101) {
                myLoop();
            }
        }, 12);
    }
    myLoop();
}

window.onload = fadeOut;

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
})


let orderQ = document.querySelector('#orderQ');
let productQ = document.querySelector('#productQ');
let accountQ = document.querySelector('#accountQ');
let forumQ = document.querySelector('#forumQ');
let chatroomQ = document.querySelector('#chatroomQ');
let emailQ = document.querySelector('#emailQ');

orderQ.addEventListener('click', (event) => {
    chatInput.value = event.target.id;
    sendMessage();
});
productQ.addEventListener('click', (event) => {
    chatInput.value = event.target.id;
    sendMessage();
});
accountQ.addEventListener('click', (event) => {
    chatInput.value = event.target.id;
    sendMessage();
});
forumQ.addEventListener('click', (event) => {
    chatInput.value = event.target.id;
    sendMessage();
});
chatroomQ.addEventListener('click', (event) => {
    chatInput.value = event.target.id;
    sendMessage();
});
emailQ.addEventListener('click', (event) => {
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

            setTimeout(() => {
                robotMessage('我是死的');
                // scroll();
            }, 500)
            // scroll();
        }
    };
});
let OneMessage = document.querySelector('div.OneMessage');

//傳送訊息的方法
function sendMessage() {
    let newInput = document.querySelector('#service .OneMessage').cloneNode(true);
    let br = document.createElement('br');
    newInput.style.display = 'flex';
    newInput.lastChild.previousSibling.textContent = chatInput.value;
    chatContent.append(newInput);
    chatContent.append(br);
    chatInput.value = '';
    serviceForm.classList.remove('active');
    scroll();
}

function robotMessage(answer) {
    let newAnswer = document.querySelector('#service .OneService').cloneNode(true);
    let br = document.createElement('br');
    newAnswer.style.display = 'flex';
    newAnswer.firstChild.nextSibling.textContent = answer;
    chatContent.append(newAnswer);
    chatContent.append(br);
    //無效:<
    // scroll();
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


