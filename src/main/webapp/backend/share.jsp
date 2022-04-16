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

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
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
			<a href="index.html" class="logo"><b>POP.Game</b></a>
			<!--logo end-->

			<div class="top-menu">
				<ul class="nav pull-right top-menu">
					<li><a class="logout" href="login.html">Logout</a></li>
				</ul>
			</div>
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
						<a href="profile.html"><img src="<%=request.getContextPath()%>/backend/assets/img/logo.png"
							class="img-circle" width="60"></a>
					</p>
					<h5 class="centered">侯宇陽</h5>

					<li class="mt"><a class="active" href="<%=request.getContextPath()%>/backend/index.jsp"> <i
							class="fa fa-dashboard"></i> <span>首頁</span>
					</a></li>

					<li class="sub-menu"><a href="javascript:;"> <i
							class="fa fa-desktop"></i> <span>使用者管理</span>
					</a>
						<ul class="sub">
							<li><a href=" blank.html">管理員資料管理</a></li>
							<li><a href=" blank.html">管理員權限管理</a></li>
						</ul></li>

					<li class="sub-menu"><a href="javascript:;"> <i
							class="fa fa-cogs"></i> <span>主要商城後臺系統</span>
					</a>
						<ul class="sub">
							<li><a href="#">商品管理</a></li>
							<li><a href="#">主要訂單管理</a></li>
						</ul></li>

					<li class="sub-menu"><a href="javascript:;"> <i
							class="fa fa-cogs"></i> <span>競標管理系統</span>
					</a>
						<ul class="sub">
							<li><a href="<%=request.getContextPath()%>/backend/bid/listAllBidApplyList">二手遊戲驗收管理</a></li>
							<li><a href="<%=request.getContextPath()%>/backend/bid/addBid.jsp">競標上架</a></li>
							<li><a href="<%=request.getContextPath()%>/backend/bid/listAllBid.jsp">查看競標商品</a></li>
							<li><a href="#">競標訂單管理</a></li>
						</ul></li>

					<li class="sub-menu"><a href="javascript:;"> <i
							class="fa fa-cogs"></i> <span>前台網站管理系統</span>
					</a>
						<ul class="sub">
							<li><a href="#">會員資料管理</a></li>
							<li><a href="#">討論區檢舉管理</a></li>
						</ul></li>

					<li class="sub-menu"><a href="javascript:;"> <i
							class="fa fa-cogs"></i> <span>討論區管理系統</span>
					</a>
						<ul class="sub">
							<li><a href="#">討論區權限編輯</a></li>
							<li><a href="#">新增或關閉討論區</a></li>
							<li><a href="#">發表管理員文章</a></li>
						</ul></li>

					<li class="sub-menu"><a href="javascript:;"> <i
							class="fa fa-cogs"></i> <span>FQ管理系統</span>
					</a>
						<ul class="sub">
							<li><a href="#">真人文字客服</a></li>
							<li><a href="#">關鍵字設定</a></li>
						</ul></li>

					<li class="sub-menu"><a href="javascript:;"> <i
							class="fa fa-cogs"></i> <span>遊戲平台及種類管理</span>
					</a>
						<ul class="sub">
							<li><a href="#">遊戲平台管理</a></li>
							<li><a href="#">遊戲種類管理</a></li>
						</ul></li>

					<li class="sub-menu"><a href="javascript:;"> <i
							class="fa fa-cogs"></i> <span>行銷管理</span>
					</a>
						<ul class="sub">
							<li><a href="#">編輯新聞</a></li>
							<li><a href="#">優惠券管理</a></li>
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

<!-- <!-- 						內容寫在這裡面 --> -->

<!-- 					</div> -->
<!-- <!-- 					/col-lg-9 END SECTION MIDDLE --> -->

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


	<!--common script for all pages-->
	<script
		src="<%=request.getContextPath()%>/backend/assets/js/common-scripts.js"></script>

	<!-- <script type="text/javascript" src="assets/js/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="assets/js/gritter-conf.js"></script> -->

	<!--script for this page-->
	<script
		src="<%=request.getContextPath()%>/backend/assets/js/sparkline-chart.js"></script>
	<script
		src="<%=request.getContextPath()%>/backend/assets/js/zabuto_calendar.js"></script>

	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							var unique_id = $.gritter
									.add({
										// (string | mandatory) the heading of the notification
										title : 'Welcome to Dashgum!',
										// (string | mandatory) the text inside the notification
										text : 'Hover me to enable the Close Button. You can hide the left sidebar clicking on the button next to the logo. Free version for <a href="http://blacktie.co" target="_blank" style="color:#ffd777">BlackTie.co</a>.',
										// (string | optional) the image to display on the left
										image : 'assets/img/ui-sam.jpg',
										// (bool | optional) if you want it to fade out on its own or just sit there
										sticky : true,
										// (int | optional) the time you want it to be alive for before fading out
										time : '',
										// (string | optional) the class name you want to apply to that specific message
										class_name : 'my-sticky-class'
									});

							return false;
						});
	</script>

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
    
	</script>


</body>

</html>