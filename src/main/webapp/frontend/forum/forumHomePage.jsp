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
<html lang="zxx">

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
	href="<%=request.getContextPath()%>/frontend/mainCss/css/allStyle.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontend/mainCss/css/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontend/mainCss/css/headerStyle.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontend/mainCss/css/footerStyle.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontend/mainCss/css/headerFunctionStyle.css">


<!-- Favicon -->
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/frontend/mainCss/images/logo.png">
<!-- Fonts CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontend/mainCss/assets/vendor/fonts/fonts.css">
<!-- Bootstrap-icons CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontend/mainCss/assets/vendor/bootstrap/icons/bootstrap-icons.css">
<!--Magnific-Popup CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontend/mainCss/assets/vendor/magnific/magnific-popup.css">
<!-- Slick CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontend/mainCss/assets/vendor/slick/slick.css">
<!-- Style CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontend/mainCss/assets/css/style01.css">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
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
	box-shadow: 0 5px #737373;
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
			<img
				src="<%=request.getContextPath()%>/frontend/mainCss/images/logo.png"
				alt=""> <a
				href="<%=request.getContextPath()%>/frontend/HomePage/HomePage.html"
				class="logo">帕Game</a>
		</div>


		<nav class="navbar">
			<!-- 上方標籤列 -->
			<a class="active"
				href="<%=request.getContextPath()%>/frontend/HomePage/HomePage.html">首頁</a>
			<a
				href="<%=request.getContextPath()%>/frontend/Product/HomePageinshop.html">商品區</a>
			<!-- <a href="#news">遊戲新聞</a> -->
			<a href="<%=request.getContextPath()%>/frontend/bid/listallbid.html">競標區</a>
			<a
				href="<%=request.getContextPath()%>/frontend/forum/forumHomePage.jsp">討論區</a>
		</nav>

		<div class="icons">
			<!--右上方小icon區-->
			<i class="fas fa-bars" id="menu-bars"></i> <a
				href="<%=request.getContextPath()%>/frontend/mem/updateMemberData.html"
				class="fas fa-solid fa-user-astronaut" id="login-icon"></a> <a
				href="<%=request.getContextPath()%>/frontend/Product/HomePageinProduct-wishlist.jsp"
				class="fas fa-heart"></a> <a
				href="<%=request.getContextPath()%>/frontend/Product/shopping-cart.html"
				class="fas fa-shopping-cart" id="cart"></a>
		</div>

	</header>

	<!-- header section ends-->

	<!-- header section ends-->

	<div style="height: 33px"></div>

	<div style="height: 33px"></div>

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
	<%@include file="/frontend/frontfoot.jsp"%>
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