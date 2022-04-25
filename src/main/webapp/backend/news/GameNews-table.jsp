<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

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
<link
	href="<%=request.getContextPath()%>/backend/assets/css/style.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/backend/assets/css/style-responsive.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/backend/news/assets/game-news.css"
	rel="stylesheet">

<script
src="<%=request.getContextPath()%>/backend/assets/js/chart-master/Chart.js">
</script>

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
						<a href="profile.html"><img src="assets/img/logo.png"
							class="img-circle" width="60"></a>
					</p>
					<h5 class="centered">侯宇陽</h5>

					<li class="mt"><a href="index.html"> <i
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
							<li><a href="#">二手遊戲驗收管理</a></li>
							<li><a href="#">查看競標商品</a></li>
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

					<li class="sub-menu"><a class="active" href="javascript:;">
							<i class="fa fa-solid fa-robot"></i> <span>FQ管理系統</span>
					</a>
						<ul class="sub">
							<li><a href="#">真人文字客服</a></li>
							<li class="active"><a href="KeyWord-final.html">關鍵字設定</a></li>
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
		<section id="main-content">
			<section class="wrapper">

				<h3 class="funcTitle02">
					<i class="fa-solid fa-gear"></i> 新聞管理
				</h3>


				<div class="row">
					<div class="col-md-12">
						<div class="content-panel">
							<table class="table table-striped table-advance table-hover"
								id="myTable">
								<h4 class="funcTitle02">
									<i class="fa fa-angle-right"></i> 查詢結果
								</h4>
								<hr>
								<thead>
                                    <tr>
                                        <th><i class="fa fa-solid fa-hashtag"></i> 新聞編號</th>
                                        <th><i class="fa fa-solid fa-hashtag"></i> 平台編號
                                        </th>
                                        <th><i class="fa fa-solid fa-hashtag"></i> 編輯者編號
                                        </th>
                                        <th class="hidden-phone"><i class="fa fa-question-circle"></i> 新聞標題</th>
                                        <th><i class="fa fa-bookmark"></i> 新聞內容</th>
                                        <th class="hidden-phone"><i class="fa-solid fa-image"></i>新聞首圖</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="gameNews" items="${AllQuery}">
                                    <tr>
                                    
											<%!int count = 1;%>
											<td>${gameNews.gameNewsNo}</td>
											<td>${gameNews.gamePlatformNo}</td>
											<td>${gameNews.managerNo}</td>
											<td>${gameNews.gameNewsTitle}</td>
											<td>${gameNews.gameNewsContent}</td>
											<td>
												<c:if test="${gameNews.gameNewsPic.length != 0}">
												<a href="<%=request.getContextPath()%>/gameNews/gameNewsPic?gameNewsNo=${gameNews.gameNewsNo}" title="點擊查看" target="_blank">有</a>
												</c:if>
											</td>
											<td>
												<button class="btn btn-success btn-xs">
													<i class="fa fa-check"></i>
												</button>
												<button class="btn btn-primary btn-xs edit">
													<i class="fa fa-pencil"></i>
												</button>
												<form method="post" action="<%=request.getContextPath()%>/fqkeyword/fqkeyword.do">
													<button class="btn btn-danger btn-xs" id="trash<%=count%>"
														type="submit">
														<i class="fa fa-trash-o "></i>
													</button>
													<input type="hidden" name="fqKeyWordNo" value="${keyWord.fqKeyWordNo}">
													<input type="hidden" name="fqKeyWordNo" value="${keyWord.fqKeyWordContent}">
													<input type="hidden" name="fqKeyWordNo" value="${keyWord.answerContent}">
													<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> <!--送出本網頁的路徑給Controller-->
													<input type="hidden" name="action" value="delete"> 
												</form>

											</td>
											
                                    </tr>
                                    </c:forEach>
                                </tbody>
							</table>
						</div>
						<!-- /content-panel -->
					</div>
					<!-- /col-md-12 -->
				</div>
				<!-- /row -->
			</section>
		</section>

		<!--main content end-->

	</section>

	<!-- js placed at the end of the document so the pages load faster -->
	<script
		src="<%=request.getContextPath()%>/frontend/Test02/assets/js/jquery.js"></script>
	<script
		src="<%=request.getContextPath()%>/frontend/Test02/assets/js/jquery-1.8.3.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/frontend/Test02/assets/js/bootstrap.min.js"></script>
	<script class="include" type="text/javascript"
		src="<%=request.getContextPath()%>/frontend/Test02/assets/js/jquery.dcjqaccordion.2.7.js"></script>
	<script
		src="<%=request.getContextPath()%>/frontend/Test02/assets/js/jquery.scrollTo.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/frontend/Test02/assets/js/jquery.nicescroll.js"
		type="text/javascript"></script>
	<script
		src="<%=request.getContextPath()%>/frontend/Test02/assets/js/jquery.sparkline.js"></script>


	<!--common script for all pages-->
	<script
		src="<%=request.getContextPath()%>/frontend/Test02/assets/js/common-scripts.js"></script>

	<!-- <script type="text/javascript" src="assets/js/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="assets/js/gritter-conf.js"></script> -->

	<!--script for this page-->
	<script
		src="<%=request.getContextPath()%>/frontend/Test02/assets/js/sparkline-chart.js"></script>
	<script
		src="<%=request.getContextPath()%>/frontend/Test02/assets/js/zabuto_calendar.js"></script>

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