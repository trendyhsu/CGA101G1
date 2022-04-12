/*上方列表圖示點擊*/
let menu = document.querySelector('#menu-bars');
let navbar = document.querySelector('.navbar');

menu.onclick = () => {
    menu.classList.toggle('fa-times');
    navbar.classList.toggle('active');
}

let section = document.querySelectorAll('section');
let navLinks = document.querySelectorAll('header .navbar a');

window.onscroll = () => {

    menu.classList.remove('fa-times');
    navbar.classList.remove('active');

    /*上方列隨捲動滑動*/
    section.forEach(sec => {

        let top = window.scrollY; /*已滾動到的位子*/
        let height = sec.offsetHeight; /*該區塊的高度*/
        let offset = sec.offsetTop - 150; /*該區塊距離父元素頂部的距離-150*/
        let id = sec.getAttribute('id');

        if (top >= offset && top < offset + height) {
            navLinks.forEach(links => {
                links.classList.remove('active');
                document.querySelector('header .navbar a[href*=' + id + ']').classList.add('active');
            });
        };
    });
}
/*查詢頁面出現*/
document.querySelector('#search-icon').onclick = () => {
    document.querySelector('#search-form').classList.toggle('active');
}
/*查詢頁面關閉*/
document.querySelector('#search-close').onclick = () => {
    document.querySelector('#search-form').classList.remove('active');
}
/*進階查詢出現*/
document.querySelector('#advanced').onclick = () => {
    document.querySelector('#advanced-search-form').classList.toggle('active');
}
/*進階查詢關閉*/
document.querySelector('#advanced-search-close').onclick = () => {
    document.querySelector('#advanced-search-form').classList.remove('active');
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

/*--------------------*/
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

/*幻燈片2*/
var swiper = new Swiper(".news-slider", { /*變數名*/
    spaceBetween: 30,
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

