<%@page import="com.manager.model.ManagerVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Dashboard">
<meta name="keyword"
	content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

<title>POP.Game ServerSide</title>

<!-- Bootstrap core CSS -->
<link
	href="<%=request.getContextPath()%>/backend/assets/css/bootstrap.css"
	rel="stylesheet">
<!--external css-->
<link
	href="<%=request.getContextPath()%>/backend/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backend/assets/css/zabuto_calendar.css">
<!-- <link rel="stylesheet" type="text/css" href="assets/js/gritter/css/jquery.gritter.css" /> -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backend/assets/lineicons/style.css">

<!-- Custom styles for this template -->
<link href="<%=request.getContextPath()%>/backend/assets/css/style.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/backend/assets/css/style-responsive.css"
	rel="stylesheet">

<script
	src="<%=request.getContextPath()%>/backend/assets/js/chart-master/Chart.js"></script>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />


<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>

	<section id="container">
		<!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
		<!--header start-->
		<header class="header black-bg">
			<div class="sidebar-toggle-box">
				<div class="fa fa-bars tooltips" data-placement="right"
					data-original-title="Toggle Navigation"></div>
			</div>
			<!--logo start-->
			<a href="<%=request.getContextPath()%>/backend/index.jsp" class="logo"><b>POP.Game</b></a>
			<!--logo end-->
			<!--login/out start-->
			<div class="top-menu">
                <ul class="nav pull-right top-menu">
					<c:if test="${managerVO.managerNo != null}">
	                    <li><a class="logout" href="<%=request.getContextPath()%>/manager/managerLogout">登出</a></li>
					</c:if>
					<c:if test="${managerVO.managerNo == null}">
						<li><a class="logout" href="<%=request.getContextPath()%>/backend/managerlogin/managerlogin.jsp">登入 / 註冊</a></li>
					</c:if>
                </ul>
            </div>
			<!--login/out end-->
		</header>
		<!--header end-->

		<!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
		<!--sidebar start-->
		<aside>
			<div id="sidebar" class="nav-collapse ">
				<!-- sidebar menu start-->
				<ul class="sidebar-menu" id="nav-accordion">

					<p class="centered">

						<c:if test="${managerVO.managerNo == null}">
							<a href="<%=request.getContextPath()%>/backend/index.jsp"><img
								src="<%=request.getContextPath()%>/backend/assets/img/logo.png"
								class="img-circle" width="60"></a>
						</c:if>
						<c:if test="${managerVO.managerNo != null}">
							<a href="<%=request.getContextPath()%>/backend/index.jsp"><img
								src="<%=request.getContextPath()%>/manager/managerPic?managerNo=${managerVO.managerNo}"
								class="img-circle" width="60"></a>
						</c:if>

					</p>
					<h5 class="centered">${managerVO.managerName}</h5>

					<li class="mt"><a class="active"
						href="<%=request.getContextPath()%>/backend/index.jsp"> <i class="fa-solid fa-house-chimney"></i> <span>首頁</span>
					</a></li>

					<li class="sub-menu"><a href="javascript:void(0)" onclick="">
							<i class="fa-solid fa-user-astronaut"></i> <span>使用者管理</span>
					</a>
						<ul class="sub">
							<li><a href="<%=request.getContextPath()%>/backend/manager/getAllManager.jsp">管理員資料管理</a></li>
							<li><a href="<%=request.getContextPath()%>/backend/managerauthrizationfunction/getAllManagerAuthrizationFunction.jsp">管理員權限管理</a></li>
						</ul></li>

					<li class="sub-menu"><a href="javascript:;"> 
					<i class="fa-solid fa-tag"></i> <span>主要商城後臺系統</span>
					</a>
						<ul class="sub">
							<li><a href="<%=request.getContextPath()%>/backend/product/productAdd.jsp">新增商品</a></li>
							<li><a href="<%=request.getContextPath()%>/backend/product/productMod.jsp">修改商品</a></li>
							<li><a href="<%=request.getContextPath()%>/backend/product/order.jsp">訂單管理</a></li>
						</ul></li>

					<li class="sub-menu"><a href="javascript:;"> 
					<i class="fa-solid fa-gavel"></i> <span>競標管理系統</span>
					</a>
						<ul class="sub">
							<li><a
								href="<%=request.getContextPath()%>/backend/bid/listAllBidApplyList.jsp">二手遊戲驗收管理</a></li>
							<li><a
								href="<%=request.getContextPath()%>/backend/bid/listAllBid.jsp">查看競標商品</a></li>
							<li><a
								href="<%=request.getContextPath()%>/backend/bid/listAllBidOrder.jsp">競標訂單管理</a></li>
						</ul></li>

					<li class="sub-menu"><a href="javascript:;"> <i
							class="fa fa-desktop"></i> <span>前台網站管理系統</span>
					</a>
						<ul class="sub">
							<li><a href="<%=request.getContextPath()%>/backend/mem/listAllMem.jsp">會員資料管理</a></li>
							<li><a href="<%=request.getContextPath()%>/backend/forum/selectReportHome.jsp">討論區檢舉管理</a></li>
						</ul></li>

					<li class="sub-menu"><a href="javascript:;"> <i
							class="fa-solid fa-chalkboard-user"></i> <span>討論區管理系統</span>
					</a>
						<ul class="sub">
							<li><a href="<%=request.getContextPath()%>/backend/forum/listAllForum.jsp">討論區編輯</a></li>
							<li><a href="<%=request.getContextPath()%>/backend/forum/listAllForumMasterPost.jsp">發表管理員文章</a></li>
						</ul></li>

					<li class="sub-menu"><a href="javascript:;"> <i
							class="fa fa-solid fa-robot"></i> <span>FQ管理系統</span>
					</a>
						<ul class="sub">
							<li><a href="<%=request.getContextPath()%>/backend/servicechatroom/ServiceChatroom.jsp">真人文字客服</a></li>
							<li><a href="<%=request.getContextPath()%>/backend/fq/KeyWord-final.jsp">關鍵字設定</a></li>
						</ul></li>

					<li class="sub-menu"><a href="javascript:;"> <i
							class="fa fa-cogs"></i> <span>遊戲分類管理</span>
					</a>
						<ul class="sub">
							<li><a href="<%=request.getContextPath()%>/backend/gameplatformtype/GetAllGamePlatformType.jsp">遊戲平台管理</a></li>
							<li><a href="<%=request.getContextPath()%>/backend/gametype/GetAllGameType.jsp">遊戲種類管理</a></li>
							<li><a href="<%=request.getContextPath()%>/backend/gamecompany/GetAllGameCompany.jsp">遊戲公司管理</a></li>
						</ul></li>

					<li class="sub-menu"><a href="javascript:;"> <i
							class="fa-solid fa-newspaper"></i> <span>行銷管理</span>
					</a>
						<ul class="sub">
							<li><a href="<%=request.getContextPath()%>/backend/news/GameNews-final.jsp">編輯新聞</a></li>
							<li><a href="<%=request.getContextPath()%>/backend/couponType/listAllCouponType.jsp">優惠券管理</a></li>
							<li><a href="<%=request.getContextPath()%>/backend/couponType/addCouponType.jsp">新增優惠券</a></li>
							<li><a href="<%=request.getContextPath()%>/backend/memCoupon/showMemCoupon.jsp">已發放優惠券管理</a></li>
							<li><a href="<%=request.getContextPath()%>/backend/memCoupon/sendMemCoupon.jsp">發放優惠券</a></li>
						</ul></li>

					<!-- <li class="sub-menu">
                        <a href="javascript:;">
                            <i class="fa fa-tasks"></i>
                            <span>Forms</span>
                        </a>
                        <ul class="sub">
                            <li><a href="form_component.html">Form Components</a></li>
                        </ul>
                    </li>
                    <li class="sub-menu">
                        <a href="javascript:;">
                            <i class="fa fa-th"></i>
                            <span>Data Tables</span>
                        </a>
                        <ul class="sub">
                            <li><a href="basic_table.html">Basic Table</a></li>
                            <li><a href="responsive_table.html">Responsive Table</a></li>
                        </ul>
                    </li> -->


				</ul>
				<!-- sidebar menu end-->
			</div>
		</aside>
		<!--sidebar end-->

		<!-- **********************************************************************************************************************************************************
      MAIN CONTENT 內容寫在這裡面
      *********************************************************************************************************************************************************** -->
		<!--main content start-->
		<!-- 		<section id="main-content"> -->
		<!-- 			<section class="wrapper"> -->

		<!-- 				<div class="row"> -->

		<!-- 					<div class="col-lg-9 main-chart"> -->

		<!-- <!-- 						內容寫在這裡面 -->
		-->

		<!-- 					</div> -->
		<!-- <!-- 					/col-lg-9 END SECTION MIDDLE -->
		-->

		<!-- 				</div> -->
		<!-- 			</section> -->
		<!-- 		</section> -->

		<!--main content end-->

	</section>

	<!-- js placed at the end of the document so the pages load faster -->
	<script src="<%=request.getContextPath()%>/backend/assets/js/jquery.js"></script>
	<script
		src="<%=request.getContextPath()%>/backend/assets/js/jquery-1.8.3.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/backend/assets/js/bootstrap.min.js"></script>
	<script class="include" type="text/javascript"
		src="<%=request.getContextPath()%>/backend/assets/js/jquery.dcjqaccordion.2.7.js"></script>
	<script
		src="<%=request.getContextPath()%>/backend/assets/js/jquery.scrollTo.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/backend/assets/js/jquery.nicescroll.js"
		type="text/javascript"></script>
	<script
		src="<%=request.getContextPath()%>/backend/assets/js/jquery.sparkline.js"></script>



	<!-- <script type="text/javascript" src="assets/js/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="assets/js/gritter-conf.js"></script> -->

	<!--script for this page-->
	<script
		src="<%=request.getContextPath()%>/backend/assets/js/sparkline-chart.js"></script>
	<script
		src="<%=request.getContextPath()%>/backend/assets/js/zabuto_calendar.js"></script>
	<script
		src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

	<script type="application/javascript">
		
		
		
        $(document).ready(function () {
            $("#date-popover").popover({ html: true, trigger: "manual" });
            $("#date-popover").hide();
            $("#date-popover").click(function (e) {
                $(this).hide();
            });

            $("#my-calendar").zabuto_calendar({
                action: function () {
                    return myDateFunction(this.id, false);
                },
                action_nav: function () {
                    return myNavFunction(this.id);
                },
                ajax: {
                    url: "show_data.php?action=1",
                    modal: true
                },
                legend: [
                    { type: "text", label: "Special event", badge: "00" },
                    { type: "block", label: "Regular event", }
                ]
            });
        });


        function myNavFunction(id) {
            $("#date-popover").hide();
            var nav = $("#" + id).data("navigation");
            var to = $("#" + id).data("to");
            console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
        }
    
		/*---LEFT BAR ACCORDION----*/
$(function() {
    $('#nav-accordion').dcAccordion({
        eventType: 'click',
        autoClose: true,
        saveState: true,
        disableLink: true,
        speed: 'slow',
        showCount: false,
        autoExpand: true,
//        cookie: 'dcjq-accordion-1',
        classExpand: 'dcjq-current-parent'
    });
});

var Script = function () {


//    sidebar dropdown menu auto scrolling

    jQuery('#sidebar .sub-menu > a').click(function () {
    	console.log("hello");
        var o = ($(this).offset());
        console.log(o);
        diff = 250 - o.top;
        if(diff>0)
            $("#sidebar").scrollTo("-="+Math.abs(diff),500);
        else
            $("#sidebar").scrollTo("+="+Math.abs(diff),500);
    });

//    sidebar toggle

    $(function() {
        function responsiveView() {
            var wSize = $(window).width();
            if (wSize <= 768) {
                $('#container').addClass('sidebar-close');
                $('#sidebar > ul').hide();
            }

            if (wSize > 768) {
                $('#container').removeClass('sidebar-close');
                $('#sidebar > ul').show();
            }
        }
        $(window).on('load', responsiveView);
        $(window).on('resize', responsiveView);
    });

    $('.fa-bars').click(function () {
        if ($('#sidebar > ul').is(":visible") === true) {
            $('#main-content').css({
                'margin-left': '0px'
            });
            $('#sidebar').css({
                'margin-left': '-210px'
            });
            $('#sidebar > ul').hide();
            $("#container").addClass("sidebar-closed");
        } else {
            $('#main-content').css({
                'margin-left': '210px'
            });
            $('#sidebar > ul').show();
            $('#sidebar').css({
                'margin-left': '0'
            });
            $("#container").removeClass("sidebar-closed");
        }
    });

// custom scrollbar
    $("#sidebar").niceScroll({styler:"fb",cursorcolor:"#4ECDC4", cursorwidth: '3', cursorborderradius: '10px', background: '#404040', spacebarenabled:false, cursorborder: ''});

    $("html").niceScroll({styler:"fb",cursorcolor:"#4ECDC4", cursorwidth: '6', cursorborderradius: '10px', background: '#404040', spacebarenabled:false,  cursorborder: '', zindex: '1000'});

// widget tools

    jQuery('.panel .tools .fa-chevron-down').click(function () {
        var el = jQuery(this).parents(".panel").children(".panel-body");
        if (jQuery(this).hasClass("fa-chevron-down")) {
            jQuery(this).removeClass("fa-chevron-down").addClass("fa-chevron-up");
            el.slideUp(200);
        } else {
            jQuery(this).removeClass("fa-chevron-up").addClass("fa-chevron-down");
            el.slideDown(200);
        }
    });

    jQuery('.panel .tools .fa-times').click(function () {
        jQuery(this).parents(".panel").parent().remove();
    });


//    tool tips

    $('.tooltips').tooltip();

//    popovers

    $('.popovers').popover();



// custom bar chart

    if ($(".custom-bar-chart")) {
        $(".bar").each(function () {
            var i = $(this).find(".value").html();
            $(this).find(".value").html("");
            $(this).find(".value").animate({
                height: i
            }, 2000)
        })
    }


}();

	
	
	</script>


</body>

</html>