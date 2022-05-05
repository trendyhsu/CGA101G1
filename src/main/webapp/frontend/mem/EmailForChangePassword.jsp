<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page import="com.member.model.*" %>


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
                <link rel="stylesheet"
                    href="/CGA101G1/frontend/mainCss/assets/vendor/bootstrap/icons/bootstrap-icons.css">
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
                                    <ol
                                        class="breadcrumb dark-link m-0 small justify-content-center justify-content-lg-end">
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
                                            <h3 class="h4 mb-0" style="text-align: center; color: cadetblue;">會員信箱</h3>
                                        </div>
                                        <div class="card-body">

                                            <FORM METHOD="post"
                                                ACTION="<%=request.getContextPath()%>/mem/SendMailForgetPasswordServlet"
                                                name="form1" id="form1">

                                                <div>


                                                    <div class="form-group mb-3">
                                                        <div class="row align-items-center">
                                                            <label class="form-label col" for="memEmail" color:
                                                                cadetblue;>請輸入您的信箱:
                                                                <span class="text-danger">*</span></label>
                                                        </div>
                                                        <input type="email" class="form-control" id="memEmail"
                                                            placeholder="輸入信箱，系統將寄發重設密碼連結給您!"
                                                            style="text-transform: none;" name="memEmail" value="">
                                                    </div>
                                                    <%-- <label style="color: red; font-size: 15;">
                                                        ${errorMsgs.newPassword}</label> --%>



                                                </div>


                                                <div class="form-group text-center">
                                                    <input type="hidden" name="action" value="update">
                                                    <input type="button" class="btn btn-primary w-100" id="btn1"
                                                        value="送出">
                                            </FORM>
                                        </div>
                                        <div id="errorMessage">${errorMsgs.memEmail}</div>


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
                    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
                    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
                    <script>
                        $("#memEmail").blur(checkEmail);
                        function checkEmail() {
                            const r1 = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
                            if (r1.test($('#memEmail').val())) {
                                $('#errorMessage').text(`輸入完畢`)
                            } else if ($('#memEmail').val().length === 0) {
                                $('#errorMessage').text(`尚未輸入`);
                            } else
                                $('#errorMessage').text(`請輸入電子郵件格式`);
                        }

                        $("#btn1").click(() => {
                            const r1 = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
                            if ($("#errorMessage").text() == "輸入完畢") {
                                console.log("aaaaa");
                                swal("已寄出更改密碼連結", "請至您的信箱查收更改密碼", "success")
                                    .then(function () {
                                        $("#form1").submit();
                                    })
                            }
                        })
                    </script>
            </body>

            </html>
            <%@include file="/frontend/frontfoot.jsp" %>