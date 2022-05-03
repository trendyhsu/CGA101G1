<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forum.model.*"%>
<%@ page import="java.util.*"%>

<%
ForumService forumSvc = new ForumService();
List<ForumVO> list = forumSvc.getAllFrontend();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>POP.Game</title>

<link rel="stylesheet"
	href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
<!-- font awesome cdn link -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<!-- custom css file link -->
<link rel="stylesheet"
	href="/CGA101G1/frontend/mainCss/css/allStyle.css">
<link rel="stylesheet" href="/CGA101G1/frontend/mainCss/css/style.css">
<link rel="stylesheet"
	href="/CGA101G1/frontend/mainCss/css/headerStyle.css">
<link rel="stylesheet"
	href="/CGA101G1/frontend/mainCss/css/footerStyle.css">


<!-- Favicon -->
<link rel="shortcut icon"
	href="/CGA101G1/frontend/mainCss/images/logo.png">
<!-- Fonts CSS -->
<link rel="stylesheet"
	href="/CGA101G1/frontend/mainCss/assets/vendor/fonts/fonts.css">
<!-- Bootstrap-icons CSS -->
<link rel="stylesheet"
	href="/CGA101G1/frontend/mainCss/assets/vendor/bootstrap/icons/bootstrap-icons.css">
<!--Magnific-Popup CSS -->
<link rel="stylesheet"
	href="/CGA101G1/frontend/mainCss/assets/vendor/magnific/magnific-popup.css">
<!-- Slick CSS -->
<link rel="stylesheet"
	href="/CGA101G1/frontend/mainCss/assets/vendor/slick/slick.css">
<!-- Style CSS -->
<link rel="stylesheet"
	href="/CGA101G1/frontend/mainCss/assets/css/style01.css">
<style type="text/css">
.button1 {
	display: inline-block;
	padding: 10px 20px;
	font-size: 15px;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	outline: none;
	color: #fff;
	background-color: #547492;
	border: none;
	border-radius: 15px;
 	box-shadow: 0 5px #999;
}

.button1:hover {
	background-color: #A3C6C4;
}

.button1:active {
	background-color: #E0E7E9;
	box-shadow: 0 2px #666;
	transform: translateY(4px);
}
</style>
</head>
<body>
	<!-- header section starts-->

	<header>

		<div class="logo-div">
			<img src="/CGA101G1/frontend/mainCss/images/logo.png" alt=""> <a
				href="/CGA101G1/frontend/HomePage.html" class="logo">帕Game</a>
		</div>


		<nav class="navbar">
			<!-- 上方標籤列 -->
			<a href="/CGA101G1/frontend/HomePage.html">首頁</a> <a
				href="/CGA101G1/frontend/Product/HomePageinshop.html"
				class="nav-link">遊戲商城</a> <a
				href="/CGA101G1/frontend/bid/listallbid.html" class="nav-link">二手競標商城</a>
			<a class="active" href="/CGA101G1/frontend/forum/ForumAllHome.html"
				class="nav-link">遊戲討論區</a>
		</nav>

		<div class="icons">
			<!--右上方小icon區-->
			<i class="fas fa-bars" id="menu-bars"></i> <a
				href="/CGA101G1/frontend/memLogin/login.html"
				class="fas fa-solid fa-user-astronaut" id="login-icon"></a> <a
				href="#" class="fas fa-search" id="search-icon"></a> <a
				href="/CGA101G1/frontend/Product/HomePageinProduct-wishlist.html"
				class="fas fa-heart"></a> <a
				href="/CGA101G1/frontend/Product/shopping-cart.html"
				class="fas fa-shopping-cart" id="cart"></a>
		</div>

	</header>


	<!-- header section ends-->
	<!--Login starts-->

	<div action="" id="login-form">
		<i class="fas fa-times" id="login-close"></i>


		<div class="form-box">
			<div class="button-box">
				<div id="btn"></div>
				<button type="button" class="toggle-btn" onclick="login()">Log
					in</button>
				<button type="button" class="toggle-btn" onclick="register()">Register</button>
			</div>

			<form id="login" class="input-group">
				<div class="input-div">
					<i class="fas fa-solid fa-user-astronaut"></i> <input type="text"
						class="input-field" placeholder="Username" required>
				</div>
				<div class="input-div">
					<i class="fas fa-solid fa-lock"></i> <input type="password"
						class="input-field" placeholder="Password" required>
				</div>

				<div class="others">
					<input type="checkbox" class="check-box"> <span>Remeber
						Password</span>
				</div>

				<button type="submit" class="submit-btn">Log in</button>
			</form>

			<form id="register" class="input-group">
				<div class="input-div">
					<i class="fas fa-solid fa-user-astronaut"></i> <input type="text"
						class="input-field" placeholder="Username" required>
				</div>
				<div class="input-div">
					<i class="fas fa-solid fa-envelope"></i> <input type="email"
						class="input-field" placeholder="E-mail" required>
				</div>
				<div class="input-div">
					<i class="fas fa-solid fa-lock"></i> <input type="password"
						class="input-field" placeholder="Password" required>
				</div>

				<div class="others">
					<input type="checkbox" class="check-box"> <span>I
						agree to the terms & conditions</span>
				</div>

				<button type="submit" class="submit-btn">Register</button>
			</form>

		</div>

	</div>

	<!-- Login ends -->
	<!-- search form starts-->

	<form action="" id="search-form">
		<div class="search-group">

			<div>
				<input type="search" placeholder="search here..." name=""
					id="search-box"> <input type="button" id="advanced"
					value="&#x21FE;Advanced search">
				<!-- <a href="#" id="advanced">&#x21FE;Advanced search</a> -->
			</div>
			<label for="search-box" class="fas fa-search"></label>

		</div>


		<i class="fas fa-times" id="search-close"></i>
	</form>

	<!-- search form ends-->

	<!-- shopping-cart div starts -->
	<div id="shopping-cart">
		<span>Monopoly</span>
	</div>

	<!-- shopping-cart div ends -->

	<div style="height: 60px"></div>

	<!-- main 討論區 starts-->

	<section class="home" id="home">

		<div class="items" id="items">

			<div class="box-container">

				<c:forEach var="forumVO" items="${list}">

					<div class="box">

						<div class="item-img-container" id="picPreview">
							<img
								src="<%=request.getContextPath()%>/forum/forumPicGetByForumNo?forumNo=${forumVO.forumNo}"
								class="uploadedImg" style="width: 300px; border-radius: 10px;">
						</div>
						<div
							style="width: 280px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							<h3 style="color: #2F365F;">${forumVO.forumName}</h3>
						</div>
						<div>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/forum/selectOneForumAllPost"
								style="margin-bottom: 0px;" id="forma"></FORM>
							<button type="submit" form="forma" class="button1" name="forumNo"
								value="${forumVO.forumNo}">Enter</button>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>

	<!-- main ends-->
	<!-- Footer -->
	<footer class="bg-white footer">
		<div class="footer-top py-6">
			<div class="container">
				<div class="row">
					<section class="footer">
						<div class="box-container">
							<div class="box">
								<h3>會員專區</h3>
								<a href="#">會員登入</a> <a href="#">會員註冊</a> <a href="#">訂單管理</a> <a
									href="#">二手遊戲申請上架單管理</a>
							</div>

							<div class="box">
								<h3>快速連結</h3>
								<a href="#">首頁</a> <a href="#">遊戲商城</a> <a href="#">二手競標商城</a> <a
									href="#">遊戲討論區</a>
							</div>

							<div class="box">
								<h3>聯絡我們</h3>
								<a href="#">02-3456-7890</a> <a href="#">03-1111-2222</a> <a
									href="#">CGA101G1@gmail.com</a> <a href="#">anasbhai@gmail.com</a>
							</div>

							<div class="box">
								<h3>關於我們</h3>
								<a href="#">管理團隊</a> <a href="#">創始理念</a> <a href="#">insuagram</a>
								<a href="#">linkedin</a>
							</div>
						</div>

						<div class="credit">
							copyright@2022 by <span>CGA101G1</span>
						</div>

					</section>
				</div>
			</div>
		</div>

	</footer>
	<!-- End Footer -->
	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.js"></script>
	<script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
	<!-- 外來區 -->
	<!-- jquery -->
	<script src="/CGA101G1/frontend/mainCss/assets/js/jquery-3.5.1.min.js"></script>
	<!-- appear -->
	<script
		src="/CGA101G1/frontend/mainCss/assets/vendor/appear/jquery.appear.js"></script>
	<!--bootstrap-->
	<script
		src="/CGA101G1/frontend/mainCss/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- owl-carousel -->
	<script
		src="/CGA101G1/frontend/mainCss/assets/vendor/slick/slick.min.js"></script>
	<!-- magnific -->
	<script
		src="/CGA101G1/frontend/mainCss/assets/vendor/magnific/jquery.magnific-popup.min.js"></script>
	<!-- isotope -->
	<script
		src="/CGA101G1/frontend/mainCss/assets/vendor/isotope/isotope.pkgd.min.js"></script>
	<!-- count-down -->
	<script
		src="/CGA101G1/frontend/mainCss/assets/vendor/count-down/jquery.countdown.min.js"></script>
	<!-- Theme Js -->
	<script src="/CGA101G1/frontend/mainCss/assets/js/custom.js"></script>
	<!-- 外來區over -->
	<!-- custom js file link-->
	<script src="/CGA101G1/frontend/mainCss/js/script.js"></script>
	<script type="text/javascript">
		var filereader_support = typeof FileReader != 'undefined';

		if (!filereader_support) {
			alert("No FileReader support");
		}

		acceptedTypes = {
			'image/png' : true,
			'image/jpeg' : true,
			'image/gif' : true
		};

		let upfile = document.getElementById("upfile");
		upfile.addEventListener("change", function(event) {
			let files = event.target.files || event.dataTransfer.files;
			for (let i = 0; i < files.length; i++) {
				previewfile(files[i])
			}
		}, false);

		function previewfile(file) {
			if (filereader_support === true
					&& acceptedTypes[file.type] === true) {
				let reader = new FileReader();
				reader.onload = function(event) {
					let image = new Image();
					image.src = event.target.result;
					image.width = 300;
					picPreview.appendChild(image);
				};
				reader.readAsDataURL(file);
			} else {
				picPreview.innerHTML += "<p>" + "filename: <strong>"
						+ file.name + "</strong><br>" + "ContentTyp: <strong>"
						+ file.type + "</strong><br>" + "size: <strong>"
						+ file.size + "</strong> bytes</p>";
			}
		}
	</script>
</body>
</html>