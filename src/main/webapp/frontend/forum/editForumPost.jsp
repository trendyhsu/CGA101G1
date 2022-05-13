<%@page import="com.forumpost.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forum.model.*"%>
<%@ page import="com.forumpost.model.*"%>
<%@ page import="com.forumpostpic.model.*"%>
<%@ page import="java.util.*"%>

<%
ForumVO forumVO = (ForumVO) request.getAttribute("forumVO");

ForumPostVO forumPostVO = (ForumPostVO) request.getAttribute("forumPostVO");

List<ForumPostPicVO> forumPostPicVOs = (List<ForumPostPicVO>) request.getAttribute("forumPostPicVOs");
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

.imgCss {
	margin: 8px;
	border-radius: 5px;
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


	<div style="height: 60px"></div>
	<!-- 商品詳情 -->
	<section name="pd_information" id="pd_description">
		<FORM method="post"
			action="<%=request.getContextPath()%>/forum/forumMemPostEditUpdate"
			name="form1" enctype="multipart/form-data" onsubmit="return ">
			<div>
				<div>

					<div class="row g-2">
						<div class="col-sm-12" style="width: 90%">
							<label class="form-label" style="font-size: 18px;">標題${errorMsgs.forumPostTitle}</label>
							<input type="text" name="forumPostTitle"
								class="form-control form-control-sm"
								value="${forumPostVO.forumPostTitle}" style="font-size: 14px" />
						</div>
						<div>
							<c:if test="${memVO.memNo==forumVO.memNo}">
								<input type="radio" id="master" name="forumPostType" value="1"
									style="width: 16px; height: 16px" checked="checked">
								<label for="master" style="font-size: 15PX;">版主文章</label>

							</c:if>
							<c:if test="${forumPostVO.forumPostType==2}">
								<input type="radio" id="attack" name="forumPostType" value="2"
									style="width: 16px; height: 16px" checked="checked">
								<label for="attack" style="font-size: 15PX;">攻略文章</label>
							</c:if>
							<c:if test="${forumPostVO.forumPostType!=2}">
								<input type="radio" id="attack" name="forumPostType" value="2"
									style="width: 16px; height: 16px">
								<label for="attack" style="font-size: 15PX;">攻略文章</label>
							</c:if>
							<c:if test="${forumPostVO.forumPostType==3}">
								<input type="radio" id="message" name="forumPostType" value="3"
									style="width: 16px; height: 16px" checked="checked">
								<label for="message" style="font-size: 15PX;">情報</label>
							</c:if>
							<c:if test="${forumPostVO.forumPostType!=3}">
								<input type="radio" id="message" name="forumPostType" value="3"
									style="width: 16px; height: 16px">
								<label for="message" style="font-size: 15PX;">情報</label>
							</c:if>
							<c:if test="${forumPostVO.forumPostType==4}">

								<input type="radio" id="chat" name="forumPostType" value="4"
									style="width: 16px; height: 16px" checked="checked">
								<label for="chat" style="font-size: 15PX;">閒聊</label>
							</c:if>
							<c:if test="${forumPostVO.forumPostType!=4}">

								<input type="radio" id="chat" name="forumPostType" value="4"
									style="width: 16px; height: 16px">
								<label for="chat" style="font-size: 15PX;">閒聊</label>
							</c:if>
							<c:if test="${forumPostVO.forumPostType==5}">
								<input type="radio" id="other" name="forumPostType" value="5"
									style="width: 16px; height: 16px" checked="checked">
								<label for="other" style="font-size: 15PX;">其他</label>

							</c:if>
							<c:if test="${forumPostVO.forumPostType!=5}">
								<input type="radio" id="other" name="forumPostType" value="5"
									style="width: 16px; height: 16px">
								<label for="other" style="font-size: 15PX;">其他</label>

							</c:if>
						</div>
						<div class="col-sm-12" style="width: 90%">
							<label class="form-label" style="font-size: 18px;">內容${errorMsgs.forumPostContent}</label>
							<textarea rows="5" class="form-control"
								style="height: 500px; font-size: 16px" name="forumPostContent">${forumPostVO.forumPostContent}</textarea>
						</div>
					</div>
				</div>
			</div>
			<div style="height: 8px;"></div>
			<label class="form-label" style="font-size: 18px;">上傳圖片</label>
			<div></div>
			<input type="file" name="upfile1" onclick="previewImage()" multiple
				id="upfile"> <br> <br> <input class="acess"
				type="submit" value="送出"><input type="hidden" name="forumNo"
				value="${forumPostVO.forumNo}"> <input type="hidden"
				name="forumPostNo" value="${forumPostVO.forumPostNo}"><input
				class="acess" type="reset" value="重設">
			<div style="display: inline-block;">
				<a
					href="
			<%=request.getContextPath()%>/forum/forumPostMyPostMemNo">
					<button class="button1" form="fake">返回我的文章</button>
				</a> <a
					href="
			<%=request.getContextPath()%>/forum/selectOneForumAllPost?forumNo=${forumPostVO.forumNo}">
					<button class="button1" form="fake">返回討論區</button>
				</a>
			</div>
		</FORM>
		<div style="height: 8px"></div>


		<div id="delete-form"
			style="position: relative; height: auto; width: auto;">
			<form method="post"
				ACTION="<%=request.getContextPath()%>/forum/forumPostPicDelete"
				onsubmit="return checkConfirm();"
				style="display: flex; align-items: center;">
				<br>
				<c:if test="${forumPostPicVOs.size() != 0}">

					<c:forEach var="forumPostPicVO" items="${forumPostPicVOs}">
						<div
							style="border-style: solid; padding: 10px; border-radius: 10px;">
							<input class="form-check-input"
								style="width: 15px; height: 15px; margin-top: 55px;"
								type="checkbox" name="forumPostPicNos"
								value="${forumPostPicVO.forumPostPicNo}" class="delete_checkbox">
							&nbsp;<img
								src="<%=request.getContextPath()%>/forum/forumPostPicGetOneByPicNo?forumPostPicNo=${forumPostPicVO.forumPostPicNo}"
								height="128px" width="200px" class="uploadedImg"
								style="border-radius: 5px;">
						</div>
						&emsp;
					</c:forEach>
					<div style="display: block;">
						<input type="hidden" name="forumNo" value="${forumPostVO.forumNo}">
						<input type="hidden" name="forumPostNo"
							value="${forumPostVO.forumPostNo}"> <input type="hidden"
							name="forumPostType" value="${forumPostVO.forumPostType}">
						<input type="hidden" name="forumPostTitle"
							value="${forumPostVO.forumPostTitle}"> <input
							type="hidden" name="forumPostContent"
							value="${forumPostVO.forumPostContent}"> <input
							class="button1" type="submit" style="margin-left: 10px;"
							value="刪除">
					</div>
				</c:if>
			</FORM>
			<div style="margin-left: -7px">
				<div id="picPreview"
					style="display: flex; width: 100%; height: 100%; flex-wrap: wrap; position: relative;"></div>

			</div>
		</div>
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
					image.width = 200;
					image.classList.add("imgCss");
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
		// 當upload重新選擇 清空舊有資料
		$("#upload").change(function() {
			$("#picPreview").empty() // 清空當下預覽
			previewfile(this.files) // this即為<input>元素
		})
	</script>
</body>
</html>