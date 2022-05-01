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
            <img src="/CGA101G1/frontend/mainCss/images/logo.png" alt=""> <a href="#" class="logo">帕Game</a>
        </div>


        <nav class="navbar">
            <!-- 上方標籤列 -->
            <a class="active" href="#home">首頁</a> <a href="#pd_review" class="nav-link">遊戲新聞</a> <a
                href="#pd_description" class="nav-link">遊戲商城</a>
            <a href="/CGA101G1/frontend/bid/listallbid.html" class="nav-link">二手競標商城</a>
            <a href="#pd_review" class="nav-link">遊戲討論區</a>
        </nav>

        <div class="icons">
            <!--右上方小icon區-->
            <i class="fas fa-bars" id="menu-bars"></i> <i class="fas fa-solid fa-user-astronaut" id="login-icon"></i> <i
                class="fas fa-search" id="search-icon"></i> <a href="#" class="fas fa-heart"></a> <a href="#"
                class="fas fa-shopping-cart" id="cart"></a>
        </div>

    </header>

    <!-- header section ends-->
    <!-- search form starts-->

    <form action="" id="search-form">
        <div class="search-group">

            <div>
                <input type="search" placeholder="search here..." name="" id="search-box"> <input type="button"
                    id="advanced" value="&#x21FE;Advanced search">
                <!-- <a href="#" id="advanced">&#x21FE;Advanced search</a> -->
            </div>
            <label for="search-box" class="fas fa-search"></label>

        </div>


        <i class="fas fa-times" id="search-close"></i>
    </form>

    <!-- search form ends-->

    <!-- advanced search form starts -->

    <form action="" id="advanced-search-form">

        <i class="fas fa-times" id="advanced-search-close"></i>

        <div id="search-select">
            <div class="search-type">
                <span class="list-title">Condition</span>
                <div class="checkbox-group">
                    <label> <input type="checkbox" id="check" class="check" value="PS5"> <span>New </span>
                    </label> <label> <input type="checkbox" id="check2" class="check" value="Nintendo Switch">
                        <span>Used </span>
                    </label>
                </div>
            </div>

            <div class="search-type">
                <span class="list-title">Platforms</span>
                <div class="checkbox-group">
                    <label> <input type="checkbox" id="check" class="check" value="PS5"> <span>PS5 </span>
                    </label> <label> <input type="checkbox" id="check2" class="check" value="Nintendo Switch">
                        <span>Nintendo Switch </span>
                    </label> <label> <input type="checkbox" id="check3" class="check" value="Nintendo Switch">
                        <span>Xbox </span>
                    </label> <label> <input type="checkbox" id="check4" class="check" value="PS5"> <span>PS4 </span>
                    </label>

                </div>
            </div>

            <div class="search-type">
                <span class="list-title">Items Type</span>
                <div class="checkbox-group">
                    <label> <input type="checkbox" id="check" class="check" value="PS5"> <span>Game Software </span>
                    </label> <label> <input type="checkbox" id="check2" class="check" value="Nintendo Switch">
                        <span>Hardware </span>
                    </label> <label> <input type="checkbox" id="check3" class="check" value="Nintendo Switch">
                        <span>Figures </span>
                    </label>

                </div>
            </div>

            <div class="search-type">
                <span class="list-title">Game Type</span>
                <div class="checkbox-group">
                    <label> <input type="checkbox" id="check" class="check" value="RPG"> <span>RPG </span>
                    </label> <label> <input type="checkbox" id="check2" class="check" value="Strategy">
                        <span>Strategy</span>
                    </label> <label> <input type="checkbox" id="check3" class="check" value="Gal-Game"> <span>Gal-Game
                        </span>
                    </label> <label> <input type="checkbox" id="check4" class="check" value="FPS"> <span>FPS </span>
                    </label> <label> <input type="checkbox" id="check9" class="check" value="Simulation">
                        <span>Simulation </span>
                    </label> <label> <input type="checkbox" id="check6" class="check" value="Action"> <span>Action
                        </span>
                    </label> <label> <input type="checkbox" id="check7" class="check" value="Adventure"> <span>Adventure
                        </span>
                    </label>

                </div>
            </div>

        </div>

        <div class="advanced-btn-group">
            <div>
                <input type="button" class="advanced-reset" value="&#x21BA;Reset">
            </div>
            <div>
                <!-- <i class="fas fa-solid fa-rocket"></i> -->
                <button type="submit">
                    <i class="fas fa-solid fa-rocket"></i>Submit
                </button>
                <!-- <input type="button" class="advanced-submit" value="&#x21C0;Submit"> -->
            </div>


        </div>



    </form>


    <!-- advanced search form ends -->

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