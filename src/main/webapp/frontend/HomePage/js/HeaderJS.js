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
let shoppingCart = document.querySelector('#shopping-cart');
document.querySelector('#cart').onclick = () => {
	$.ajax({
		url: '/CGA101G1/product/showCart',
		type: 'post',
		success: function(data) {
			showCart(data);
		}
	});
	shoppingCart.classList.toggle('active');
}

function showCart(data) {
	let cartItems = JSON.parse(data);
	let total = 0;
	let shoppingCartBox = document.querySelector('#shopping-cart .box-group');
	shoppingCartBox.innerHTML = '';
	for (let cartItem of cartItems) {
		shoppingCartBox.innerHTML += `<div class="box">
            <i class="fas fa-trash" productno="${cartItem.productNo}" productname="${cartItem.productName}" productsales="${cartItem.productSales}" producttotalprice="${cartItem.productTotalPrice}"></i> <img src="/CGA101G1/product/showOneCover?ProductNO=${cartItem.productNo}">
            <div class="content">
                <h3>${cartItem.productName}</h3>
                <span class="price">$${cartItem.productTotalPrice/cartItem.productSales}</span><span class="quantity">x ${cartItem.productSales}</span> 
            </div>
        </div>`;
        total += cartItem.productTotalPrice;
	}
	shoppingCart.prepend(shoppingCartBox);
	let debt = document.querySelector('#shopping-cart .total');
	debt.textContent=`total : $${total}`;

	
    let trashs = document.querySelectorAll('#shopping-cart i');
	trashs.forEach(function(trash){
	trash.addEventListener('click',(e)=>{
		let productNo = e.target.getAttribute('productno');
		let productSales = e.target.getAttribute('productsales');
		let productName = e.target.getAttribute('productname');
		let productTotalPrice = e.target.getAttribute('producttotalprice');

		$.ajax({
			url:`/CGA101G1/product/shoppingCartRemoveAll?ProductNo=${productNo}&ProductSales=${productSales}&ProductTotalPrice=${productTotalPrice}&ProductName=${productName}`,
			type:`post`,
		});
		$.ajax({
		url: '/CGA101G1/product/showCart',
		type: 'post',
		success: function(data) {
			showCart(data);
			}
		});

	})
})

}
