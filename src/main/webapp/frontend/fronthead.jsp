<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>POP.Game</title>
    
        <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
        <!-- font awesome cdn link -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <!-- custom css file link -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/mainCss/css/allStyle.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/mainCss/css/style.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/mainCss/css/headerStyle.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/mainCss/css/footerStyle.css">
    
    
        <!-- Favicon -->
        <link rel="shortcut icon" href="<%=request.getContextPath()%>/frontend/mainCss/images/logo.png">
        <!-- Fonts CSS -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/mainCss/assets/vendor/fonts/fonts.css">
        <!-- Bootstrap-icons CSS -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/mainCss/assets/vendor/bootstrap/icons/bootstrap-icons.css">
        <!--Magnific-Popup CSS -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/mainCss/assets/vendor/magnific/magnific-popup.css">
        <!-- Slick CSS -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/mainCss/assets/vendor/slick/slick.css">
        <!-- Style CSS -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/mainCss/assets/css/style01.css">
        
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
        
    </head>
    

<body>

    <!-- header section starts-->

    <header>

        <div class="logo-div">
            <img src="<%=request.getContextPath()%>/frontend/mainCss/images/logo.png" alt="">
            <a href="<%=request.getContextPath()%>/frontend/HomePage.html" class="logo">帕Game</a>
        </div>


        <nav class="navbar">
            <!-- 上方標籤列 -->
            <a class="active" href="<%=request.getContextPath()%>/frontend/HomePage.html">首頁</a>
            <a href="<%=request.getContextPath()%>/frontend/Product/HomePageinshop.html" class="nav-link">遊戲商城</a>
            <a href="<%=request.getContextPath()%>/frontend/bid/listallbid.html" class="nav-link">二手競標商城</a>
            <a href="<%=request.getContextPath()%>/frontend/forum/ForumAllHome.html" class="nav-link">遊戲討論區</a>
        </nav>

        <div class="icons">
            <!--右上方小icon區-->
            <i class="fas fa-bars" id="menu-bars"></i>
            <a href="<%=request.getContextPath()%>/frontend/memLogin/login.html" class="fas fa-solid fa-user-astronaut" id="login-icon"></a>
            <a href="#" class="fas fa-search" id="search-icon"></a>
            <a href="<%=request.getContextPath()%>/frontend/Product/HomePageinProduct-wishlist.html" class="fas fa-heart"></a>
            <a href="<%=request.getContextPath()%>/frontend/Product/shopping-cart.html" class="fas fa-shopping-cart" id="cart"></a>
        </div>

    </header>

    <!-- header section ends-->

    <!--Login starts-->

    <div action="" id="login-form">
        <i class="fas fa-times" id="login-close"></i>


        <div class="form-box">
            <div class="button-box">
                <div id="btn"></div>
                <button type="button" class="toggle-btn" onclick="login()">Log in</button>
                <button type="button" class="toggle-btn" onclick="register()">Register</button>
            </div>

            <form id="login" class="input-group">
                <div class="input-div">
                    <i class="fas fa-solid fa-user-astronaut"></i>
                    <input type="text" class="input-field" placeholder="Username" required>
                </div>
                <div class="input-div">
                    <i class="fas fa-solid fa-lock"></i>
                    <input type="password" class="input-field" placeholder="Password" required>
                </div>

                <div class="others">
                    <input type="checkbox" class="check-box">
                    <span>Remeber Password</span>
                </div>

                <button type="submit" class="submit-btn">Log in</button>
            </form>

            <form id="register" class="input-group">
                <div class="input-div">
                    <i class="fas fa-solid fa-user-astronaut"></i>
                    <input type="text" class="input-field" placeholder="Username" required>
                </div>
                <div class="input-div">
                    <i class="fas fa-solid fa-envelope"></i>
                    <input type="email" class="input-field" placeholder="E-mail" required>
                </div>
                <div class="input-div">
                    <i class="fas fa-solid fa-lock"></i>
                    <input type="password" class="input-field" placeholder="Password" required>
                </div>

                <div class="others">
                    <input type="checkbox" class="check-box">
                    <span>I agree to the terms & conditions</span>
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
                <input type="search" placeholder="search here..." name="" id="search-box">
                <input type="button" id="advanced" value="&#x21FE;Advanced search">
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
                    <label>
                        <input type="checkbox" id="check" class="check" value="PS5">
                        <span>New </span>
                    </label>
                    <label>
                        <input type="checkbox" id="check2" class="check" value="Nintendo Switch">
                        <span>Used </span>
                    </label>
                </div>
            </div>

            <div class="search-type">
                <span class="list-title">Platforms</span>
                <div class="checkbox-group">
                    <label>
                        <input type="checkbox" id="check" class="check" value="PS5">
                        <span>PS5 </span>
                    </label>

                    <label>
                        <input type="checkbox" id="check2" class="check" value="Nintendo Switch">
                        <span>Nintendo Switch </span>
                    </label>

                    <label>
                        <input type="checkbox" id="check3" class="check" value="Nintendo Switch">
                        <span>Xbox </span>
                    </label>

                    <label>
                        <input type="checkbox" id="check4" class="check" value="PS5">
                        <span>PS4 </span>
                    </label>

                </div>
            </div>

            <div class="search-type">
                <span class="list-title">Items Type</span>
                <div class="checkbox-group">
                    <label>
                        <input type="checkbox" id="check" class="check" value="PS5">
                        <span>Game Software </span>
                    </label>
                    <label>
                        <input type="checkbox" id="check2" class="check" value="Nintendo Switch">
                        <span>Hardware </span>
                    </label>
                    <label>
                        <input type="checkbox" id="check3" class="check" value="Nintendo Switch">
                        <span>Figures </span>
                    </label>

                </div>
            </div>

            <div class="search-type">
                <span class="list-title">Game Type</span>
                <div class="checkbox-group">
                    <label>
                        <input type="checkbox" id="check" class="check" value="RPG">
                        <span>RPG </span>
                    </label>
                    <label>
                        <input type="checkbox" id="check2" class="check" value="Strategy">
                        <span>Strategy</span>
                    </label>
                    <label>
                        <input type="checkbox" id="check3" class="check" value="Gal-Game">
                        <span>Gal-Game </span>
                    </label>

                    <label>
                        <input type="checkbox" id="check4" class="check" value="FPS">
                        <span>FPS </span>
                    </label>
                    <label>
                        <input type="checkbox" id="check9" class="check" value="Simulation">
                        <span>Simulation </span>
                    </label>

                    <label>
                        <input type="checkbox" id="check6" class="check" value="Action">
                        <span>Action </span>
                    </label>

                    <label>
                        <input type="checkbox" id="check7" class="check" value="Adventure">
                        <span>Adventure </span>
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
                <button type="submit"><i class="fas fa-solid fa-rocket"></i>Submit</button>
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
                        <h1 class="m-0 h4 text-center text-lg-start">會員中心</h1>
                    </div>
                    <div class="col-lg-6 my-2">
                        <ol class="breadcrumb dark-link m-0 small justify-content-center justify-content-lg-end">
                            <li class="breadcrumb-item"><a class="text-nowrap" href="index.html"><i
                                        class="bi bi-home"></i>首頁</a></li>
                            <li class="breadcrumb-item text-nowrap active" aria-current="page">我的帳戶</li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Breadcrumb -->

        <!-- Table -->
        <div class="py-6">
            <div class="container">
                <div class="row">
                    <!-- Profile Menu -->
                    <div class="col-lg-3 pb-4 pb-lg-0 col-xxl-3  pe-xxl-5" style="font-weight: bold; font-size: 16px">
                        <div class="bg-white border border-bottom-0 shadow-lg">
                            <div class="d-flex p-3 align-items-center">
                                <div class="avatar avatar-lg rounded-circle">
                                    <img src="<%=request.getContextPath()%>/mem/MemSelfPicServlet?memNo=${memVO.memNo}" title="" alt="">
                                </div>
                                <div class="col ps-3">
                                    <h6 class="m-0">${memVO.memName}</h6>
                                    <small><a href="javascript:void(0);">${memVO.memEmail}</a></small>
                                </div>
                            </div>
                            <div class="bg-gray-200 p-3 border-bottom border-top">
                                <h6 class="m-0">會員中心</h6>
                            </div>
                            <ul class="list-unstyled mb-0 theme-link">
                                <li class="border-bottom mb-0">
                                    <a class="nav-link-style d-flex align-items-center active p-3" style="color:black"
                                        href="<%=request.getContextPath()%>/frontend/Product/account-order.html">
                                        <i class="bi bi-bag me-2"></i> 我的訂單
                                    </a>
                                </li>
                                <li class="border-bottom mb-0">
                                    <a class="nav-link-style d-flex align-items-center p-3" style="color:black"
                                        href="<%=request.getContextPath()%>/frontend/Product/HomePageinProduct-wishlist.html">
                                        <i class="bi bi-heart me-2"></i>我的最愛
                                    </a>
                                </li>
                                <li class="border-bottom mb-0">
                                    <a class="nav-link-style d-flex align-items-center p-3" style="color:black"
                                        href="<%=request.getContextPath()%>/bid/bidApplyListMemNo">
                                        <i class="bi bi-alarm me-2"></i>出價商品追蹤
                                    </a>
                                </li>
                                <li class="border-bottom mb-0">
                                    <a class="nav-link-style d-flex align-items-center p-3" style="color:black"
                                        href="<%=request.getContextPath()%>/bid/bidProductWonByMemNo">
                                        <i class="bi bi-bag me-2"></i>得標商品查詢
                                    </a>
                                </li>
                                <li class="border-bottom mb-0">
                                    <a class="nav-link-style d-flex align-items-center p-3" style="color:black"
                                        href="<%=request.getContextPath()%>/frontend/bid/addbidapplylist.jsp">
                                        <i class="bi bi-card-checklist me-2"></i>競標商品申請
                                    </a>
                                </li>
                                <li class="border-bottom mb-0">
                                    <a class="nav-link-style d-flex align-items-center p-3" style="color:black"
                                        href="<%=request.getContextPath()%>/bid/bidApplyListSeller">
                                        <i class="bi bi-card-checklist me-2"></i>我的申請單
                                    </a>
                                </li>
                                <li class="border-bottom mb-0">
                                    <a class="nav-link-style d-flex align-items-center p-3" style="color:black"
                                        href="<%=request.getContextPath()%>/bid/bidProductSeller">
                                        <i class="bi bi-bell me-2"></i>我的上架中競標商品
                                    </a>
                                </li>
                                <li class="border-bottom mb-0">
                                    <a class="nav-link-style d-flex align-items-center p-3" style="color:black"
                                        href="<%=request.getContextPath()%>/frontend/forum/">
                                        <i class="bi bi-heart me-2"></i>我的文章收藏
                                    </a>
                                </li>
                                <li class="border-bottom mb-0">
                                    <a class="nav-link-style d-flex align-items-center p-3" style="color:black"
                                        href="<%=request.getContextPath()%>/frontend/forum/">
                                        <i class="bi bi-heart me-2"></i>我的文章
                                    </a>
                                </li>
                                <li class="border-bottom mb-0">
                                    <a class="nav-link-style d-flex align-items-center p-3" style="color:black"
                                        href="<%=request.getContextPath()%>/frontend/chat/">
                                        <i class="bi bi-emoji-angry me-2"></i>我的忽略會員清單
                                    </a>
                                </li>

                                <li class="border-bottom mb-0">
                                    <a class="nav-link-style d-flex align-items-center p-3" style="color:black" href="<%=request.getContextPath()%>/frontend/mem/lookUpOneMemCoupon.jsp">
                                        <i class="bi bi-bookmark me-2"></i>我的優惠卷
                                    </a>
                                </li>
                            </ul>
                            <div class="bg-gray-200 p-3 border-bottom">
                                <h6 class="m-0">帳戶設定</h6>
                            </div>
                            <ul class="list-unstyled mb-0 theme-link">
                                <li class="border-bottom mb-0">
                                    <a class="nav-link-style d-flex align-items-center p-3" style="color:black" href="<%=request.getContextPath()%>/frontend/mem/updateMemberData.html">
                                        <i class="bi bi-person-circle me-2"></i>個人資訊
                                    </a>
                                </li>
                                <li class="border-bottom mb-0">
                                    <a class="nav-link-style d-flex align-items-center p-3" style="color:black" href="<%=request.getContextPath()%>/mem/logoutServlet">
                                        <i class="bi bi-box-arrow-left me-2"></i>登出
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- End Profile Menu -->
                    <!-- Content 主要內容區 要修改的部分都塞在這個裡面 -->
                    <!-- <div class="col-lg-9 col-xxl-9">
                        <div class="table-responsive fs-md mb-4">
                            <table class="table table-bordered table-hover mb-0">
                                <thead class="text-700 bg-gray-200">
                                    <tr>
                                        <th class="fw-600">Order #</th>
                                        <th class="fw-600">Date Purchased</th>
                                        <th class="fw-600">Status</th>
                                        <th class="fw-600 text-end">Total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td class="p-3"><a class="link-dark" href="#order-details"
                                                data-bs-toggle="modal">#8560</a></td>
                                        <td class="p-3">May 21, 2021</td>
                                        <td class="p-3"><span class="badge bg-info m-0">In Progress</span></td>
                                        <td class="p-3 text-end">$150</td>
                                    </tr>
                                    <tr>
                                        <td class="p-3"><a class="link-dark" href="#order-details"
                                                data-bs-toggle="modal">#8560</a></td>
                                        <td class="p-3">May 21, 2021</td>
                                        <td class="p-3"><span class="badge bg-danger m-0">Cancel</span></td>
                                        <td class="p-3 text-end">$150</td>
                                    </tr>
                                    <tr>
                                        <td class="p-3"><a class="link-dark" href="#order-details"
                                                data-bs-toggle="modal">#8560</a></td>
                                        <td class="p-3">May 21, 2021</td>
                                        <td class="p-3"><span class="badge bg-success m-0">Delivered</span></td>
                                        <td class="p-3 text-end">$150</td>
                                    </tr>
                                    <tr>
                                        <td class="p-3"><a class="link-dark" href="#order-details"
                                                data-bs-toggle="modal">#8560</a></td>
                                        <td class="p-3">May 21, 2021</td>
                                        <td class="p-3"><span class="badge bg-warning m-0">On Hold</span></td>
                                        <td class="p-3 text-end">$150</td>
                                    </tr>
                                    <tr>
                                        <td class="p-3"><a class="link-dark" href="#order-details"
                                                data-bs-toggle="modal">#8560</a></td>
                                        <td class="p-3">May 21, 2021</td>
                                        <td class="p-3"><span class="badge bg-info m-0">In Progress</span></td>
                                        <td class="p-3 text-end">$150</td>
                                    </tr>
                                    <tr>
                                        <td class="p-3"><a class="link-dark" href="#order-details"
                                                data-bs-toggle="modal">#8560</a></td>
                                        <td class="p-3">May 21, 2021</td>
                                        <td class="p-3"><span class="badge bg-info m-0">In Progress</span></td>
                                        <td class="p-3 text-end">$150</td>
                                    </tr>
                                    <tr>
                                        <td class="p-3"><a class="link-dark" href="#order-details"
                                                data-bs-toggle="modal">#8560</a></td>
                                        <td class="p-3">May 21, 2021</td>
                                        <td class="p-3"><span class="badge bg-info m-0">In Progress</span></td>
                                        <td class="p-3 text-end">$150</td>
                                    </tr>

                                </tbody>
                            </table>
                        </div> -->
                        <!-- 換頁部分 用不到 -->
                        <!-- <div class="d-flex align-items-center mt-3">
                            <div class="d-none d-lg-block">Showing: 1 - 12 of 17</div>
                            <div class="ms-lg-auto">
                                <ul class="pagination m-0">
                                    <li class="page-item">
                                        <a class="page-link" href="javascript:void(0);" aria-label="Previous">
                                            <span aria-hidden="true">«</span>
                                        </a>
                                    </li>
                                    <li class="page-item active"><a class="page-link" href="javascript:void(0);">1</a>
                                    </li>
                                    <li class="page-item"><a class="page-link" href="javascript:void(0);">2</a></li>
                                    <li class="page-item"><a class="page-link" href="javascript:void(0);">3</a></li>
                                    <li class="page-item">
                                        <a class="page-link" href="javascript:void(0);" aria-label="Next">
                                            <span aria-hidden="true">»</span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div> -->
                    <!-- End Content -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
        <!--Table -->
<!--     </main> -->
    <!-- End Main -->
