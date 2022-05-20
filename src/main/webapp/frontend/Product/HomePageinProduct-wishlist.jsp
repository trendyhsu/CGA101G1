<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/frontend/fronthead.jsp"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<!-- Title of The Page -->
<title>Fancy Shopping E-commerce HTML Template</title>
<!-- Meta Informations -->
<meta charset="utf-8">
<meta name="description"
	content="Bootstrap Responsive  Fancy Shopping E-commerce HTML Template">
<meta name="keywords"
	content="Fancy Shopping E-commerce HTML Template,template,bootstrap 5,ui template kit,envato templates,fancy shopping html templates,html,css">
<meta name="author" content="Fancy Shopping E-commerce HTML Template">
<meta name="viewport"
	content="width=device-width,height=device-height,initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">




<script
	src="https://cdnjs.cloudflare.com/ajax/libs/vue/3.0.2/vue.global.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>
<link rel="stylesheet"
	href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
<!-- font awesome cdn link -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<!-- custom css file link -->
<link rel="stylesheet" href="../mainCss/css/allStyle.css">
<link rel="stylesheet" href="../mainCss/css/style.css">
<link rel="stylesheet" href="../mainCss/css/headerStyle.css">
<link rel="stylesheet" href="../mainCss/css/footerStyle.css">


<!-- Favicon -->
<link rel="shortcut icon"
	href="/CGA101G1/frontend/mainCss/images/logo.png">
<!-- Fonts CSS -->
<link rel="stylesheet" href="../mainCss/assets/vendor/fonts/fonts.css">
<!-- Bootstrap-icons CSS -->
<link rel="stylesheet"
	href="../mainCss/assets/vendor/bootstrap/icons/bootstrap-icons.css">
<!--Magnific-Popup CSS -->
<link rel="stylesheet"
	href="../mainCss/assets/vendor/magnific/magnific-popup.css">
<!-- Slick CSS -->
<link rel="stylesheet" href="../mainCss/assets/vendor/slick/slick.css">
<!-- Style CSS -->
<link rel="stylesheet" href="../mainCss/assets/css/style01.css">
<style>
.shoppingCartimg {
	position: fixed;
	left: 95%;
	top: 50%;
}
</style>
</head>

<body>

	<!-- End Breadcrumb -->
	<!-- Table -->

	<!-- Profile Menu -->

	<!-- End Profile Menu -->
	<!-- Content -->
	<div class="col-lg-8 col-xxl-9" id="app">
		<div class="box-container">
			<div class="product-name"
				style="font-size: large; text-align: center;">我的最愛列表</div>
		</div>
		<div class="container" id="app">
			<!-- Cart Table -->
			<div class="d-flex justify-content-center" id="productBox">
				<div class="spinner-border" role="status" v-show="loading">
					<span class="visually-hidden">Loading...</span>
				</div>
			</div>
			<div class="d-flex justify-content-center" id="productBox">
				<div v-show="((this.jsonData.length == 0)&&(!loading))"
					style="font-size: 40px; padding-top: 20%">
					<i class="far fa-grin-beam-sweat"></i> <span> 目前您的最愛清單內還沒有商品唷 </span><i
						class="far fa-grin-beam-sweat"></i>
				</div>
			</div>
			<div v-show="(this.jsonData.length != 0)"  class="table-content table-responsive cart-table-content">
				<table class="table table-bordered align-middle">
					<thead>
						<tr class="text-uppercase text-nowrap">
							<th style="width: 80px;" class="text-dark fw-500"></th>
							<th class="text-dark fw-500">遊戲名稱</th>
							<th style="width: 120px;" class="text-dark fw-500">價錢</th>
							<!-- <th style="width: 120px;" class="text-dark fw-500">Qty</th>
                                <th style="width: 120px;" class="text-dark fw-500">Subtotal</th> -->
							<th style="width: 230px;" class="text-dark text-center fw-500 ">移出我的最愛清單 / 加入購物車</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="(item, index) in jsonData" :key="index">
							<td class="product-thumbnail"><a class="text-reset"
								:href="`/CGA101G1/frontend/Product/HomePageinProduct.html?ProductNo=`+item.productNo"><img
									:src=item.imageUrl class="img-fluid" width="60"
									alt=""></a></td>
							<td class="product-name"><a class="text-reset"
								:href="`/CGA101G1/frontend/Product/HomePageinProduct.html?ProductNo=`+item.productNo">{{item.productName}}</a>
							</td>
							<td class="product-price-cart"><span class="amount">{{item.productPrice}}</span></td>
							<td class="product-remove text-end text-nowrap"><a
								:href="`/CGA101G1/product/deleteMemFavor?ProductNo=`+item.productNo"
								class="btn btn-sm btn-outline-dark text-nowrap px-3"><i
									class="bi bi-x lh-1"></i></a>
								<button @click="webcamSendRequestButton($event)"
									class="btn btn-sm btn-outline-dark text-nowrap px-3"
									:value="item.productNo+a+item.productPrice+a+item.productName"
									id="liveToastBtn">
									<i class="bi bi-cart"></i> 加入購物車
								</button></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="shoppingCartimg">
				<a href="/CGA101G1/frontend/Product/shopping-cart.html">
					<p  style="margin-bottom: 0; padding-left: 15px;">
						<span v-if="this.totalItemOfCart<10"
							style="color: #CC6D3D; font-weight: 1000; font-size: 2em; position: absolute; bottom: 70%; left: 12%;">{{this.totalItemOfCart}}</span>
						<span v-else
							style="color: #CC6D3D; font-weight: 1000; font-size: 2em; position: absolute; bottom: 70%; left: 6%;">{{this.totalItemOfCart}}</span>
					</p> 


					<img src="../mainCss/assets/img/shoppingCart2.png"
					style="width: 50%; height: 50%;" alt="">
					<p>$:{{this.totalPriceOfCart}}</p>
				</a>

			</div>

		</div>
	</div>
	</div>
	<!-- End Content -->
	</div>
	</div>
	</div>
	<!--Table -->
	<!-- 跳出加入購物車的視窗 -->
	</main>
	<!-- End Main -->

	<!-- jquery -->
	<script src="../mainCss/assets/js/jquery-3.5.1.min.js"></script>
	<!-- appear -->
	<script src="../mainCss/assets/vendor/appear/jquery.appear.js"></script>
	<!--bootstrap-->
	<script
		src="../mainCss/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- owl-carousel -->
	<script src="../mainCss/assets/vendor/slick/slick.min.js"></script>
	<!-- magnific -->
	<script
		src="../mainCss/assets/vendor/magnific/jquery.magnific-popup.min.js"></script>
	<!-- isotope -->
	<script src="../mainCss/assets/vendor/isotope/isotope.pkgd.min.js"></script>
	<!-- count-down -->
	<script
		src="../mainCss/assets/vendor/count-down/jquery.countdown.min.js"></script>
	<!-- Theme Js -->
	<script src="../mainCss/assets/js/custom.js"></script>





	<script>

        const App = {
            data() {
                return {
                	loading: true,
                    jsonData: [], // 初始資料
                    picData: [],
                    a: ";",
                    totalItemOfCart: 0,
                    totalPriceOfCart: 0,

                }
            },
            methods: {
                showShoppingCartSatus() {
                    const apiUrl = '/CGA101G1/product/showCart';
                    axios.get(apiUrl)
                        .then((response) => {
                            console.log(response.data);
                            for (item of response.data) {
                                this.totalItemOfCart = this.totalItemOfCart + item.productSales;
                                this.totalPriceOfCart = this.totalPriceOfCart + item.productTotalPrice;
                            }
                        });
                },

                getProductData() {
                    // const apiUrl = 'https://api.kcg.gov.tw/api/service/Get/9c8e1450-e833-499c-8320-29b36b7ace5c';
                    const apiUrl = '/CGA101G1/product/ShowMemFavoritelist';
                    axios.get(apiUrl)
                        // 一定要用箭頭函示!!!!
                        .then((response) => {
                            console.log("取ProductData的狀態：" + response.status); //狀態碼
                            // console.log(response.data);
                            // console.log(response.data.data.XML_Head.Infos.Info);
                            this.jsonData = response.data;//塞進去
                            console.log("datastore讀取資料ok");
                            this.loading=false;
                        });
                },
                webcamSendRequestButton(e) {
                    this.totalItemOfCart = 0;
                    this.totalPriceOfCart = 0; 
                	let toastLiveExample1 = document.getElementById('liveToast1');
                    // 取編號跟錢，分離商品編號跟價錢再傳出去

                    proNo = e.target.value.split(';')[0];

                    proPrice = e.target.value.split(';')[1];

                    proName = e.target.value.split(';')[2];
                    sendURL = `/CGA101G1/product/add2ShoppingCart?ProductNo=` + proNo + `&ProductSales=1&ProductTotalPrice=` + proPrice + `&ProductName=` + proName;

                    axios({
                        method: 'post',
                        url: sendURL,
                    })
                        .then((response) => {
                        	console.log(response.data);
                            for (item of response.data) {
                                this.totalItemOfCart = this.totalItemOfCart + item.productSales;
                                this.totalPriceOfCart = this.totalPriceOfCart + item.productTotalPrice;
                            }
                        	});
                    
                }


            },

            created() {
                this.getProductData();
                this.showShoppingCartSatus();
            }
        };

        Vue.createApp(App).mount('#app');



    </script>
</body>
<!-- end body -->

</html>
<%@include file="/frontend/frontfoot.jsp"%>