<%@page import="com.forumpostpic.model.ForumPostPicVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forumpost.model.*"%>
<%@ page import="com.forummsg.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>

<%
List<ForumMsgVO> list = (List<ForumMsgVO>) request.getAttribute("forumMsgVOs");
ForumPostVO forumPostVO = (ForumPostVO) request.getAttribute("forumPostVO");
ForumMsgVO forumMsgVO = new ForumMsgVO();
List<ForumPostPicVO> forumPostPicVOs = (List<ForumPostPicVO>) request.getAttribute("forumPostPicVOs");

pageContext.setAttribute("list", list);
pageContext.setAttribute("forumPostVO", forumPostVO);
pageContext.setAttribute("forumMsgVO", forumMsgVO);
pageContext.setAttribute("forumPostPicVOs", forumPostPicVOs);
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

.page {
	display: inline-block;
	padding: 3px 5px;
	font-size: 15px;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	outline: none;
	color: #fff;
	background-color: #547492;
	border: none;
	border-radius: 7px;
	font-size: 3px;
}

.page:hover {
	background-color: #A3C6C4
}

.page:active {
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
	<div style="height: 1px"></div>
	<!-- home section starts-->

	<section name="pd_information" id="pd_description"
		style="padding-top: 95px;">
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
				<div style="display: inline-block; padding-left: 10px;">
					<c:if test="${memVO.memNo==forumPostVO.memNo}">
						<a
							href="
			<%=request.getContextPath()%>/forum/forumMemPostOne?forumPostNo=${forumPostVO.forumPostNo}">
							<button class="page">修改文章</button>
						</a>
					</c:if>
				</div>
				<div style="display: inline-block;">
					<c:if test="${memVO.memNo==forumPostVO.memNo}">
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/forum/myPostDelete"
							style="margin-bottom: 0px;" id="formA">
							<input type="button" value="刪除文章" class="page" id="submitA">
							<input type="hidden" name="forumPostNo"
								value="${forumPostVO.forumPostNo}"> <input type="hidden"
								name="memNo" value="${memVO.memNo}">
						</FORM>
					</c:if>
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

		<div style="padding-top: 30px; padding-left: 20px;">
			<c:if test="${forumPostPicVOs.size() != 0}">
				<c:forEach var="forumPostPicVO" items="${forumPostPicVOs}">
					<img
						src="<%=request.getContextPath()%>/forum/forumPostPicGetOneByPicNo?forumPostPicNo=${forumPostPicVO.forumPostPicNo}"
						width="61%" class="uploadedImg"
						style="border-radius: 15px; box-shadow: 3px 3px 12px gray; padding: 2px;">
					<input type="hidden" name="forumPostPicNo"
						value="${forumPostPicVO.forumPostPicNo}">
					<div style="height: 10px"></div>
				</c:forEach>

			</c:if>
		</div>
		<div></div>
		<div
			style="display: inline-block; padding-top: 15px; padding-left: 15px;">


			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/forum/forumPostCollectionInsert"
				style="margin-bottom: 0px;" id="formLove">
				<input type="hidden" name="forumPostNo"
					value="${forumPostVO.forumPostNo}">
				<button class="button1" id="favouritePost" type="button">❤加入我的收藏</button>

			</FORM>
		</div>
		<div style="display: inline-block;">
			<c:if test="${memVO.memNo!=forumPostVO.memNo}">
				<a
					href="
			<%=request.getContextPath()%>/frontend/forum/editForumPostReport.jsp?forumPostNo=${forumPostVO.forumPostNo}">
					<button class="button1">檢舉文章</button>
				</a>
			</c:if>
		</div>
		<div style="display: inline-block;">
			<a
				href="
			<%=request.getContextPath()%>/forum/selectOneForumAllPost?forumNo=${forumPostVO.forumNo}">
				<button class="button1">返回討論區</button>
			</a>
		</div>
		<div style="display: inline-block;">
			<a
				href="
			<%=request.getContextPath()%>/frontend/forum/forumHomePage.jsp">
				<button class="button1">返回討論區首頁</button>
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
								aria-expanded="false" aria-controls="pd_add_review"
								style="font-size: 14px;">留言</a>
						</div>
						<div class="col-12 collapse" id="pd_add_review">
							<div class="my-4 p-3 bg-gray-100 border">
								<form method="post"
									action="<%=request.getContextPath()%>/forum/forumMsgInsert"
									name="msgform" id="forumMsgForm">
									<div class="row g-2">
										<div class="col-sm-12">
											<label class="form-label">寫下你的留言${errorMsgs.forumMsg}</label>
											<textarea rows="5" class="form-control" name="forumMsg">${forumMsgVO.forumMsg}</textarea>
										</div>
										<input type="hidden" name="forumPostNo"
											value="${forumPostVO.forumPostNo}">
										<div class="col-sm-12 pt-2">
											<button class="button1" form="forumA" type="button"
												id="msgSubmit">送出</button>
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
									<c:if test="${memVO.memNo!=forumMsgVO.memNo}">
										<a
											href="
									<%=request.getContextPath()%>/frontend/forum/editForumMsgReport.jsp?forumPostNo=${forumPostVO.forumPostNo}&forumMsgNo=${forumMsgVO.forumMsgNo}">
											<button>檢舉</button>
										</a>
									</c:if>
									<c:if test="${memVO.memNo==forumMsgVO.memNo}">
										<a
											href="
									<%=request.getContextPath()%>/forum/myMsgDelete?forumPostNo=${forumPostVO.forumPostNo}&forumMsgNo=${forumMsgVO.forumMsgNo}">
											<button>刪除</button>
										</a>

									</c:if>
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
	
	var memJS ="<c:out value='${memVO.memNo}'/>";
	let submitA = document.querySelector("#submitA");
	if(submitA){
		submitA.addEventListener("click",function(){
			swal({ 
				title: "刪除文章?",
				  text: "",
				  type: "warning",
				  showCancelButton: true,
				  confirmButtonColor: "#DD6B55",
				  confirmButtonText: "刪除",
				  cancelButtonText: "取消",
				  closeOnConfirm: false,
				  showLoaderOnConfirm: true,
				  closeOnCancel: true,
				  allowOutsideClick: true
				}).then(
				function(isConfirm){ 
				  if (isConfirm) {
				    swal("刪除成功！", "","success")
				    .then(() => {
					setTimeout(postDelete, 3000);
					function postDelete(){
						document.querySelector("#formA").submit();
					}
				 }); 
				  } else { 
				    swal("刪除取消！", "",
				"error"); 
				  } 
				});
		})
	}
	
	
	
	let favouritePost = document.querySelector("#favouritePost");
	favouritePost.addEventListener("click", function() {
		
     if(memJS.length == 0){
			swal("請先登錄!", "", "warning");
			setTimeout(3000);
			
		} else {
		
		swal("❤已加入文章收藏", "", "success");
		setTimeout(inLovePost, 3000);
		function inLovePost() {
			document.querySelector("#formLove").submit();
		}
		}
	});
	
	
	
	let msgSubmit = document.querySelector("#msgSubmit");
	msgSubmit.addEventListener("click", function() {
	
		if(memJS.length == 0){
			
			swal("請先登錄!", "", "warning");
			setTimeout(3000);
			
		} else if (msgform.forumMsg.value != "") {

			swal("留言成功！", "", "success");
			setTimeout(returnPost, 3000);
			function returnPost() {
				document.querySelector("#forumMsgForm").submit();
			}

		} else {

			swal("留言內容空白！", "", "warning");
			setTimeout(3000);
		}
	});
	
	
	
	
	
	
	</script>
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
					image.width = 500;
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