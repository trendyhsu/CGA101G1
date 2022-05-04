/*上方列表圖示點擊*/
let menu = document.querySelector('#menu-bars');
let navbar = document.querySelector('.navbar');

menu.onclick = () => {
    menu.classList.toggle('fa-times');
    navbar.classList.toggle('active');
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