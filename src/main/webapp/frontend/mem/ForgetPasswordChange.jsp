<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>POP.Game</title>

    <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
    <!-- font awesome cdn link -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <!-- custom css file link -->
    <link rel="stylesheet" href="/CGA101G1/frontend/mainCss/css/allStyle.css">
    <link rel="stylesheet" href="/CGA101G1/frontend/mainCss/css/style.css">
    <link rel="stylesheet" href="/CGA101G1/frontend/mainCss/css/headerStyle.css">
    <link rel="stylesheet" href="/CGA101G1/frontend/mainCss/css/footerStyle.css">


    <!-- Favicon -->
    <link rel="shortcut icon" href="/CGA101G1/frontend/mainCss/images/logo.png">
    <!-- Fonts CSS -->
    <link rel="stylesheet" href="/CGA101G1/frontend/mainCss/assets/vendor/fonts/fonts.css">
    <!-- Bootstrap-icons CSS -->
    <link rel="stylesheet" href="/CGA101G1/frontend/mainCss/assets/vendor/bootstrap/icons/bootstrap-icons.css">
    <!--Magnific-Popup CSS -->
    <link rel="stylesheet" href="/CGA101G1/frontend/mainCss/assets/vendor/magnific/magnific-popup.css">
    <!-- Slick CSS -->
    <link rel="stylesheet" href="/CGA101G1/frontend/mainCss/assets/vendor/slick/slick.css">
    <!-- Style CSS -->
    <link rel="stylesheet" href="/CGA101G1/frontend/mainCss/assets/css/style01.css">
</head>

<body>

   <!-- header section starts-->

    <header>

        <div class="logo-div">
            <img src="/CGA101G1/frontend/mainCss/images/logo.png" alt="">
            <a href="/CGA101G1/frontend/HomePage/HomePage.html" class="logo">帕Game</a>
        </div>


        <nav class="navbar">
            <!-- 上方標籤列 -->
            <a class="active" href="/CGA101G1/frontend/HomePage/HomePage.html">首頁</a>
            <a href="/CGA101G1/frontend/Product/HomePageinshop.html" class="nav-link">商品區</a>
            <a href="/CGA101G1/frontend/bid/listallbid.html" class="nav-link">競標區</a>
            <a href="/CGA101G1/frontend/forum/forumHomePage.jsp" class="nav-link">討論區</a>
        </nav>

        <div class="icons">
            <!--右上方小icon區-->
            <i class="fas fa-bars" id="menu-bars"></i>
            <a href="/CGA101G1/frontend/mem/updateMemberData.html" class="fas fa-solid fa-user-astronaut" id="login-icon"></a>
            <a href="/CGA101G1/frontend/Product/HomePageinProduct-wishlist.jsp" class="fas fa-heart"></a>
            <a href="/CGA101G1/frontend/Product/shopping-cart.html" class="fas fa-shopping-cart" id="cart"></a>
        </div>

    </header>


    <!-- header section ends-->


    <!-- shopping-cart div starts -->
    <div id="shopping-cart">
        <span>Monopoly</span>
    </div>
    <!-- shopping-cart div ends -->

    <!-- home section starts-->

    <!-- Main -->
    <main>
        <!-- Breadcrumb -->
        <div class="py-3 bg-gray-100">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-lg-6 my-2">
                        <h1 class="m-0 h4 text-center text-lg-start">Shop</h1>
                    </div>
                    <div class="col-lg-6 my-2">
                        <ol class="breadcrumb dark-link m-0 small justify-content-center justify-content-lg-end">
                            <li class="breadcrumb-item"><a class="text-nowrap" href="index.html"><i
                                        class="bi bi-home"></i>Home</a></li>
                            <li class="breadcrumb-item text-nowrap active" aria-current="page">Product</li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Breadcrumb -->
                    <!-- Content 主要內容區 要修改的部分都塞在這個裡面 -->
					<!-- 內容直接寫在<div class="table-responsive fs-md mb-4">裡面 -->
<!--                     <div class="col-lg-9 col-xxl-9"> -->
<!--                         <div class="table-responsive fs-md mb-4"> -->

<!-- login page -->
        <div class="section">
            <div class="container">
                <div class="justify-content-center row">
                    <div class="col-lg-5 col-xxl-4">
                        <div class="card" style="border: 1px solid #aeb4be;">
                            <div class="card-header bg-transparent py-3">
                                <h3 class="h4 mb-0" style="text-align: center;  color: cadetblue;">新密碼設定</h3>
                            </div>
                            <div class="card-body">


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/NewPasswordServlet" name="form1">

			<input type="hidden" name="memEmail" id="memEmail" />
 <div>
 
 
 <div class="form-group mb-3">
             <div class="row align-items-center">
                <label class="form-label col" for="memNewPassword">請輸入您的新密碼: 
                <span class="text-danger">*</span></label>
                  </div>
					<input type="text" class="form-control" id="memNewPassword" placeholder="輸入新密碼"
                         style="text-transform: none;" name="memNewPassword"> 
                  </div>
 
<!--         <div> -->
<!--             <label for="memNewPassword">請輸入您的新密碼: </label> -->
<!--             <input type="password" name="memNewPassword" id="memNewPassword" /> -->
<!--         </div> -->
        <label>${errorMsgs.memNewPassword}</label>
        
        <div class="form-group mb-3">
             <div class="row align-items-center">
                <label class="form-label col" for="confNewPassword">新密碼確認:
                <span class="text-danger">*</span></label>
                  </div>
					<input type="text" class="form-control" id="confNewPassword" placeholder="輸入新密碼"
                         style="text-transform: none;" name="confNewPassword"> 
                  </div>
        
<!--          <div> -->
<!--             <label for="confNewPassword">新密碼確認: </label> -->
<!--             <input type="password" name="confNewPassword" id="confNewPassword" /> -->
<!--         </div> -->
        <label>${errorMsgs.confNewPassword}</label>
        
        
 </div>
 
		<div class="form-group text-center">
			<input type="submit" class="btn btn-primary w-100" id="btn1" value="送出"></FORM>
	 	</div>
		<div>${errorMsgs.memEmail}</div> 
 
</FORM>



</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end login -->



<!-- </div> -->
<!-- 					</div> -->
                    <!-- End Content -->

</body>
</html>
<%@include file="/frontend/frontfoot.jsp" %>