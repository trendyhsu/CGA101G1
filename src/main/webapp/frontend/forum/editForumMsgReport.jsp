<%@page import="com.forummsg.model.ForumMsgVO"%>
<%@page import="com.forummsg.model.ForumMsgService"%>
<%@page import="com.forummsgreport.model.ForumMsgReportVO"%>
<%@page import="com.forummsgreport.model.ForumMsgReportService"%>
<%@page import="com.forumpost.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="com.forum.model.*"%>
<%@ page import="java.util.*"%>

<%
Integer forumPostNo = Integer.valueOf(request.getParameter("forumPostNo"));
ForumPostService forumPostSvc = new ForumPostService();
ForumPostVO forumPostVO = forumPostSvc.getOneForumPost(forumPostNo);
pageContext.setAttribute("forumPostVO", forumPostVO);

Integer forumMsgNo = Integer.valueOf(request.getParameter("forumMsgNo"));
ForumMsgService forumMsgSvc = new ForumMsgService();
ForumMsgVO forumMsgVO = forumMsgSvc.getOneForumMsg(forumMsgNo);
pageContext.setAttribute("forumMsgVO", forumMsgVO);

ForumMsgReportVO forumMsgReportVO = new ForumMsgReportVO();
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
.button1, .acess {
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

.button1:hover, .acess:hover {
	background-color: #A3C6C4
}

.button1:active, .acess:active {
	background-color: #E0E7E9;
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


	<!-- main 討論區 starts-->

	<!-- home section starts-->

	<div style="height: 66px"></div>
	<!-- 商品詳情 -->
	<section name="pd_information" id="pd_description">
		<FORM method="post"
			action="<%=request.getContextPath()%>/forum/forumMemMsgReportInsert"
			name="form1" id="form1">
			<div>
				<div>

					<div class="row g-2">
						<div class="col-sm-12" style="width: 90%">
							<label class="form-label" style="font-size: 14px;">留言檢舉原因${errorMsgs.forumMsgReportWhy}</label>
							<textarea rows="5" class="form-control"
								style="height: 360px; font-size: 16px" name="forumMsgReportWhy">${forumMsgReportVO.forumMsgReportWhy}</textarea>
						</div>
					</div>
				</div>
			</div>
			<div style="height: 8px;"></div>
			<input type="hidden" name="forumMsgReportType" value="0"> <input
				class="acess" type="button" value="送出" id="submit1"><input
				type="hidden" name="forumPostNo" value="${forumPostVO.forumPostNo}">
			<input type="hidden" name="forumMsgNo"
				value="${forumMsgVO.forumMsgNo}"> <input class="acess"
				type="reset" value="重設">
			<div style="display: inline-block;">
				<a
					href="
			<%=request.getContextPath()%>/forum/selectOnePostAllMsg?forumPostNo=${forumPostVO.forumPostNo}">
					<button class="button1" form="fake">返回文章</button>
				</a>
			</div>
		</FORM>
		<div style="height: 39px;"></div>
	</section>


	<!-- End 文章區 -->

	<!-- home section ends-->

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

	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script type="text/javascript">
		let submit1 = document.querySelector("#submit1");
		submit1.addEventListener("click", function() {
			if (form1.forumMsgReportWhy.value != "") {

				swal("已收到您的留言檢舉！", "我們會盡快處理", "success");
				setTimeout(returnPost, 3000);
				function returnPost() {
					document.querySelector("#form1").submit();
				}

			} else {

				swal("檢舉內容空白！", "", "warning");
				setTimeout(3000);
			}
		});
	</script>
</body>
</html>