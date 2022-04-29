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