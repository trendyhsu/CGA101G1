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

/*_---------------------------新聞幻燈片News----------------------------*/

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


let newsPlatform = $('.news .news-slider .news-type ul');
let newsMovingbar = newsPlatform.children().last();
//網頁生成時，moving-bar預設屬性
$(document).ready(function(){
	$('.moving-bar').css('margin-left', $('.news .news-slider .news-type ul li').first().position().left);
	$('.moving-bar').css('width', $('.news .news-slider .news-type ul li').first().outerWidth(true)- (newsPlatform.children().first().position().left *2));
	newsPlatform.children().first().addClass('is-now');
})

$('.news .news-slider .news-type ul li').not('.moving-bar').mouseenter(function(){
	$(this).siblings().removeClass('is-now');
	$(this).addClass('is-now');

	newsMovingbar.stop().animate({
               marginLeft: $(this).position().left,
               width: $(this).outerWidth(true)- (newsPlatform.children().first().position().left *2),
               opacity: 1
            }, 200);
	
    findNews($(this).attr('value'));

})

newsPlatform.click(function(event){
	findNews($(event.target).attr('value'));
	$(event.target).addClass('is-now');
	$(event.target).siblings().removeClass('is-now');
})


function swiperDefaultNews(nowType) {
	$.ajax({
		url: `/CGA101G1/gamenews/HomePageGetNews?gamePlatformNo=${nowType}`,
		type: "Post",
		success: function (data) {
			showNews(data);
		}
	});
}
//此e為value="平台編號"
function findNews(e) {
	//每次點擊另一個遊戲平台時，先清空wrapper容器
	document.querySelector('#slide-container').innerHTML = "";
	$.ajax({
		url: `/CGA101G1/gamenews/HomePageGetNews?gamePlatformNo=${e}`,
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
/*-----------------------------載入動畫----------------------------*/
//function loader() {
//	document.querySelector('.loader-container').classList.add('fade-out');
//}
//
//function fadeOut() {
//	setInterval(loader, 2400);
//	let loaderImg = document.querySelector('.loader-container img');
//	loaderImg.setAttribute('src', '.\\images\\aliennn.gif');
//	runNum();
//
//}
//function runNum() {
//	let num = document.querySelector('#num');
//	let i = 0;
//	function myLoop() {
//		setTimeout(() => {
//			num.textContent = i;
//			i++;
//			if (i < 101) {
//				myLoop();
//			}
//		}, 12);
//	}
//	myLoop();
//}
//
//window.onload = fadeOut;


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
                        <div class="stars">
                        ${showStar(item.commentStar,item.countComment)}
                    	</div>
                        <p>銷售量：${item.productSales}萬</p>
                        <div class="buttons">
                            <a href="#" class="btn btn-3">Order Now</a>
                        </div>
                    </div>
                    <a class="image"  href="${item.productDetailPageURL}">
                        <img src="${item.imgURL}">
                    </a>

                </div>`)
		}else{
			str += `<div class="box">
                    <div class="flag">${count}</div>
                    <div class="trangle"></div>
                    <a href="#" class="fas fa-heart"></a>
                    <a class="item-img-container" href="${item.productDetailPageURL}">
                        <img src="${item.imgURL}" alt="">
                    </a>
                    <h3>${item.productName}</h3>
                    <div class="stars">
                    	${showStar(item.commentStar,item.countComment)}
     
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

function showStar(stars, count){
	let starAvg = (stars/count).toFixed(1);
	let starAvgInt = Math.floor(stars/count);
	let str = "";
	//整數星星
	for(let i = 0 ; i < starAvgInt ; i++){
		str += '<i class="fas fa-star"></i>';
	}
	//半顆星星 (尾數>=0.5，加半顆星)
	if(starAvg - starAvgInt >= 0.5){
		str+= '<i class="fa-solid fa-star-half-stroke"></i>';
	}
	//補滿至五顆星星
	for(let i = 0; i < 5-Math.round(stars/count); i++){
		str += '<i class="fa-regular fa-star"></i>';
	}
	
	return str;
}