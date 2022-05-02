<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forumpost.model.*"%>
<%@ page import="com.forummsg.model.*"%>
<%@ page import="java.util.*"%>

<%
List<ForumMsgVO> list = (List<ForumMsgVO>) request.getAttribute("forumMsgVOs");
ForumPostVO forumPostVO = (ForumPostVO) request.getAttribute("forumPostVO");
ForumMsgVO forumMsgVO = new ForumMsgVO();
pageContext.setAttribute("list", list);
pageContext.setAttribute("forumPostVO", forumPostVO);
pageContext.setAttribute("forumMsgVO", forumMsgVO);
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
	padding: 5px 10px;
	font-size: 15px;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	outline: none;
	color: #fff;
	background-color: #547492;
	border: none;
	border-radius: 7px;
}

.button1:hover {
	background-color: #A3C6C4
}

.button1:active {
	background-color: #E0E7E9;
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



	<!-- main 討論區 starts-->

	<!-- home section starts-->

	<section name="pd_information" id="pd_description"
		style="padding-top: 96px;">
		<!-- 標題 -->
		<div class="col-lg-6 ps-lg-5 pt-5 pt-lg-0">
			<div class="product-detail">

				<div class="avatar avatar-lg rounded-circle" id="Pinf"
					style="display: inline-block;">

					<img
						src="/CGA101G1/mem/MemSelfPicServlet?memNo=${forumPostVO.memNo}"
						title="" alt="" id="imgPinf">

				</div>
				<div class="products-title mb-2"
					style="display: inline-block; padding-left: 5px;">
					<h3 class="h3">${forumPostVO.memVO.memName}</h3>
				</div>
				<div class="products-brand pb-2" style="padding-top: 5px">
					<span><fmt:formatDate value="${forumPostVO.forumPostTime}"
							pattern="yyyy-MM-dd" /></span>
				</div>
				<div class="products-title mb-2" style="padding-top: 5px;">
					<h1 class="h1"
						style="width: 800px; color: #547492; font-size: 275%">${forumPostVO.forumPostTitle}</h1>
				</div>

			</div>
		</div>



		<div style="padding-left: 20px;">
			<div style="padding-top: 15px;">

				<p
					style="white-space: pre-line; color: #2F365F; font-size: 16px; width: 90%;">${forumPostVO.forumPostContent}</p>
			</div>

		</div>

		<div
			style="display: inline-block; padding-top: 15px; padding-left: 15px;">
			<a
				href="
			<%=request.getContextPath()%>/forum/forumPostCollectionInsert?forumPostNo=${forumPostVO.forumPostNo}">
				<button class="button1">❤加入我的收藏</button>
			</a>
		</div>
		<div style="display: inline-block;">
			<a
				href="
			<%=request.getContextPath()%>/frontend/forum/forumHomePage.jsp">
				<button class="button1">檢舉文章</button>
			</a>
		</div>
		<div style="display: inline-block;">
			<a
				href="
			<%=request.getContextPath()%>/frontend/forum/forumHomePage.jsp">
				<button class="button1">返回討論區首頁</button>
			</a>
		</div>
		<div style="display: inline-block;">
			<a
				href="
			<%=request.getContextPath()%>/forum/selectOneForumAllPost?forumNo=${forumPostVO.forumNo}">
				<button class="button1">返回討論區</button>
			</a>
		</div>

	</section>

	<section name="pd_information" id="pd_review">
		<div class="idpostion"></div>
		<div class="pd-collapse-row">
			<h3 class="sub-heading">留言區</h3>

			<div class="collapse show">
				<div class="pd-collapse-box">
					<div class="row align-items-end">
						<div class="col-sm-6"></div>
						<div class="col-sm-6 text-sm-end">
							<a href="javascript:void(0);" class="me-4"></a> <a
								data-bs-toggle="collapse" href="#pd_add_review" role="button"
								aria-expanded="false" aria-controls="pd_add_review">留言</a>
						</div>
						<div class="col-12 collapse" id="pd_add_review">
							<div class="my-4 p-3 bg-gray-100 border">
								<form method="post"
									action="<%=request.getContextPath()%>/forum/forumMsgInsert"
									name="form1" id="forumA">
									<div class="row g-2">
										<div class="col-sm-12">
											<label class="form-label">寫下你的留言</label>
											<textarea rows="5" class="form-control" name="forumMsg">${forumMsgVO.forumMsg}</textarea>
										</div>

										<div class="col-sm-12 pt-2">
											<button class="button1" form="forumA" type="submit"
												name="forumPostNo" value="${forumPostVO.forumPostNo}">送出</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
					<c:forEach var="forumMsgVO" items="${list}">
						<div class="d-flex review-box border-top mt-3 pt-3">
							<div>
								<div class="avatar avatar-lg rounded-circle" id="Pinf"
									style="display: inline-block;">

									<img
										src="/CGA101G1/mem/MemSelfPicServlet?memNo=${forumMsgVO.memNo}"
										title="" alt="" id="imgPinf">
								</div>
							</div>
							<div class="col ps-3">
								<div class="rating-star small" style="display: inline-block;">
									<h6 class="m-0">${forumMsgVO.memVO.memName}</h6>

									<span><fmt:formatDate value="${forumMsgVO.forumMsgTime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></span>
								</div>
								<div style="display: inline-block;">
									<p class="m-0 pt-2 small"
										style="color: #2F365F; font-weight: border; font-size: 16px; padding-left: 24px">${forumMsgVO.forumMsg}</p>
								</div>
								<div>
									<button>檢舉</button>
								</div>
							</div>
						</div>
					</c:forEach>

				</div>
			</div>
		</div>

	</section>


	<!-- End 文章區 -->

	<!-- home section ends-->

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
				picPreview.innerHTML += "_$t" + "filename: _$tag___"
						+ file.name + "_$tag_____$ta" + "ContentTyp: _$tag___"
						+ file.type + "_$tag_____$ta" + "size: _$tag___"
						+ file.size + "_$tag____ bytes_$ta";
			}
		}
	</script>
</body>
</html>