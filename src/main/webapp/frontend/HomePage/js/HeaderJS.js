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