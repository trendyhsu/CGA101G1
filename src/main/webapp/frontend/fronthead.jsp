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
        <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/mainCss/css/headerFunctionStyle.css">
    
    
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
            <img src="<%=request.getContextPath()%>/frontend/mainCss/images/logo.png" alt=""> <a href="<%=request.getContextPath()%>/frontend/HomePage/HomePage.html" class="logo">帕Game</a>
        </div>


        <nav class="navbar">
            <!-- 上方標籤列 -->
            <a class="active" href="<%=request.getContextPath()%>/frontend/HomePage/HomePage.html">首頁</a>
             <a href="<%=request.getContextPath()%>/frontend/Product/HomePageinshop.html">商品區</a>
            <!-- <a href="#news">遊戲新聞</a> -->
            <a href="<%=request.getContextPath()%>/frontend/bid/listallbid.html">競標區</a> 
            <a href="<%=request.getContextPath()%>/frontend/forum/forumHomePage.jsp">討論區</a>
        </nav>

        <div class="icons">
            <!--右上方小icon區-->
            <i class="fas fa-bars" id="menu-bars"></i> <a href="<%=request.getContextPath()%>/frontend/mem/updateMemberData.html" class="fas fa-solid fa-user-astronaut" id="login-icon"></a>
              <a href="<%=request.getContextPath()%>/frontend/Product/HomePageinProduct-wishlist.jsp" class="fas fa-heart"></a> 
              <a href="<%=request.getContextPath()%>/frontend/Product/shopping-cart.html" class="fas fa-shopping-cart" id="cart"></a>
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
                                        href="<%=request.getContextPath()%>/frontend/Product/account-order.jsp">
                                        <i class="bi bi-bag me-2"></i> 我的訂單
                                    </a>
                                </li>
                                <li class="border-bottom mb-0">
                                    <a class="nav-link-style d-flex align-items-center p-3" style="color:black"
                                        href="<%=request.getContextPath()%>/frontend/Product/HomePageinProduct-wishlist.jsp">
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
                                        href="<%=request.getContextPath()%>/forum/forumPostCollectionMemNo">
                                        <i class="bi bi-heart me-2"></i>我的文章收藏
                                    </a>
                                </li>
                                <li class="border-bottom mb-0">
                                    <a class="nav-link-style d-flex align-items-center p-3" style="color:black"
                                        href="<%=request.getContextPath()%>/forum/forumPostMyPostMemNo">
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
