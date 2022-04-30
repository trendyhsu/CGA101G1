/*幻燈片*/
var swiper = new Swiper(".home-slider", { /*變數名*/
	spaceBetween: 20,
	centeredSlides: true,
	createElement:true,
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

var newsSwiper = new Swiper(".news-slider", { /*變數名*/
	on: {
		init: swiperDefaultNews(64004)
	},
	spaceBetween: 30,
	centeredSlides: true,
	createElement: true,
	autoplay: {
		delay: 5500,
		disableOnInteraction: false,
	},
	pagination: {
		el: ".swiper-pagination",
		clickable: true,
	},
	loop: true,
});


let newsPlatform = document.querySelector('.news .news-slider .news-type ul');
let newsMovingbar = newsPlatform.lastElementChild;
newsPlatform.firstElementChild.classList.add('is-now');
newsPlatform.addEventListener('click', (e) => {
	
	let targetStyle = getComputedStyle(e.target);
	e.target.classList.add('is-now');
	
//	newsMovingbar.style.width = targetStyle.width;
	if(e.target === newsPlatform.firstElementChild){
		newsMovingbar.style.marginLeft = ".5rem";
		newsMovingbar.style.width = "6rem";
	}else{
		newsMovingbar.style.marginLeft = targetStyle.width;
		newsMovingbar.style.width = targetStyle.width;
	}
	
})

newsPlatform.addEventListener('mouseover', (e) => {
	e.target.classList.remove('is-now');
	//	console.log(newsPlatform)
})
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


/*------------------------------News------------------------------ */

function swiperDefaultNews(nowType) {
	$.ajax({
		url: `/CGA101G1/gamenews/HomePageGetNews?gamePlatformNo=${nowType}`,
		type: "Post",
		success: function (data) {
			showNews(data);
		}
	});
}

function findNews(e) {
	//每次點擊另一個遊戲平台時，先清空wrapper容器
	document.querySelector('#slide-container').innerHTML = "";
	$.ajax({
		url: `/CGA101G1/gamenews/HomePageGetNews?gamePlatformNo=${e.target.value}`,
		type: "Post",
		success: function (data) {
			showNews(data);
		}
	});
}

function showNews(data) {
	let news = JSON.parse(data);
	let newsPageOne = "";
	let newsPageTwo = "";
	let count = 0;
	for (let aNews of news) {
		if (count < 6) {
			newsPageOne += ` <a href="/CGA101G1/frontend/HomePage/News.html?gameNewsNo=${aNews.gameNewsNo}">
                        <div class="img-container">
                            <img src="${aNews.ImgUrl}">
                        </div>
                        <div class="text-container">
                           ${aNews.gameNewsTitle}
                        </div>
                    </a>`;
		} else {
			newsPageTwo += ` <a href="/CGA101G1/frontend/HomePage/News.html?gameNewsNo=${aNews.gameNewsNo}">
                        <div class="img-container">
                            <img src="${aNews.ImgUrl}">
                        </div>
                        <div class="text-container">
                           ${aNews.gameNewsTitle}
                        </div>
                    </a>`;
		}
		count++;
	}
	//第一頁slide放入資料
	newsSwiper.addSlide(1, `<div class="swiper-slide slide">
					${newsPageOne}			
       				</div>`);
	//超過六筆放入第二頁
	if (count > 6) {
		newsSwiper.addSlide(2, `<div class="swiper-slide slide">
       				${newsPageTwo}	
       				</div>`);
	}

}

/*-------------------------Top9Items------------------------*/
$.ajax({
	url: `/CGA101G1/product/showTop9Product`  ,
	type: "post" ,
	success: function(data){
		showTop9Items(data);
	}
});

function showTop9Items(data){
	let items = JSON.parse(data);
	let count = 1;
	let num = 'One';
	let str = "";
	let boxContainer = document.querySelector('.box-container');
	for(let item of items){
		if(count <= 3){
			if(count == 2)
				num = 'Two';
			if(count == 3)
				num = 'Three';
			swiper.addSlide(count,` <div class="swiper-slide slide ${num}">
                    <div class="content">
                        <i class="fas fa-solid fa-crown"></i> <span>${count}</span>
                        <h3>${item.productName}</h3>
                        <p>銷售量：${item.productSales}萬</p>
                        <div class="buttons">
                            <a href="#" class="btn btn-3">Order Now</a>
                        </div>
                    </div>
                    <div class="image">
                        <img src="${item.imgURL}">
                    </div>

                </div>`)
		}else{
			str += `   <div class="box">
                    <div class="flag">${count}</div>
                    <div class="trangle"></div>
                    <a href="#" class="fas fa-heart"></a>
                    <div class="item-img-container">
                        <img src="${item.imgURL}" alt="">
                    </div>
                    <h3>${item.productName}</h3>
                    <div class="stars">
                        <i class="fas fa-star"></i> <i class="fas fa-star"></i> <i class="fas fa-star"></i> <i
                            class="fas fa-star"></i> <i class="fas fa-star-half-alt"></i>
                    </div>
                    <span>$${item.productPrice}</span>
                    <div class="buttons">
                        <a href="#" class="btn btn-3">add to cart</a>
                    </div>
                </div>`;
			
		}
		count++;
	}
	boxContainer.innerHTML = str;
}