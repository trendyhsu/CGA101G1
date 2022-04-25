<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link href="<%=request.getContextPath()%>/frontend/Test02/assets/css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="<%=request.getContextPath()%>/frontend/Test02/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/frontend/Test02/assets/css/zabuto_calendar.css">
<!-- <link rel="stylesheet" type="text/css" href="assets/js/gritter/css/jquery.gritter.css" /> -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/Test02/assets/lineicons/style.css">

<!-- Custom styles for this template -->
<link href="<%=request.getContextPath()%>/frontend/Test02/assets/css/style.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/frontend/Test02/assets/css/style-responsive.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/frontend/Test02/assets/css/fq-keyword.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/frontend/Test02/assets/js/chart-master/Chart.js"></script>
 <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript" src="/path/to/jquery.msgbox.min.js"></script>
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
							<i class="fa fa-cogs"></i> <span>FQ管理系統</span>
					</a>
						<ul class="sub">
							<li><a href="#">真人文字客服</a></li>
							<li class="active"><a href="#">關鍵字設定</a></li>
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
					<i class="fa-solid fa-gear"></i> 客服機器人關鍵字管理
				</h3>
				<div class="row mt">

					<form action="<%=request.getContextPath()%>/fqkeyword/fqkeyword.do" method="post" id="formQuery">

						<ul>
							<li><label>關鍵字編號</label></li>
							<li><input type="text" name="fqKeyWordNo" id="col1"></li>
							<li><label>關鍵字</label></li>
							<li><input type="text" name="fqKeyWordContent" id="col2"></li>
							<li><label>回應內容</label></li>
							<li><input type="text" name="answerContent" id="col3"></li>
						</ul>


						<button type="button" class="cusBtn" onclick="sub();">
							<i class="fa-solid fa-magnifying-glass"></i>查詢
						</button>
						 <input type="hidden" name="action" value="AllQuery">
					</form>
                    <form action="<%=request.getContextPath()%>/fqkeyword/fqkeyword.do" method="post" id="formInsert">

                        <ul>
                            <li><label style="text-decoration: line-through;">關鍵字編號</label></li>
                            <li><input type="text" disabled></li>
                            <li> <label>關鍵字</label></li>
                            <li><input type="text" name="fqKeyWordContent"></li>
                            <li> <label>回應內容</label></li>
                            <li><input type="text" name="answerContent"></li>
                        </ul>


                        <button type="button" class="cusBtn" onclick="subForInsert();"><i class="fa-solid fa-circle-plus"></i>新增</button>
                        	<input type="hidden" name="action" value="Insert">
                    </form>
				</div>
			</section>
		</section>

		<!--main content end-->

	</section>

	<!-- js placed at the end of the document so the pages load faster -->
	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/jquery-1.8.3.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script class="include" type="text/javascript"
		src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
	<script src="assets/js/jquery.scrollTo.min.js"></script>
	<script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="assets/js/jquery.sparkline.js"></script>


	<!--common script for all pages-->
	<script src="assets/js/common-scripts.js"></script>

	<!-- <script type="text/javascript" src="assets/js/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="assets/js/gritter-conf.js"></script> -->

	<!--script for this page-->
	<script src="assets/js/sparkline-chart.js"></script>
	<script src="assets/js/zabuto_calendar.js"></script>



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
	<script>
	function sub(){
// 		let xhr = new XMLHttpRequest();
// 		url="/CGA101G1/FQKeyWordServlet";
// 		xhr.open("post",url,true);
// 		xhr.setRequestHeader("content-Type","text/plain");
// 		let col1 = document.querySelector("#col1");
// 		let col2 = document.querySelector("#col2");
// 		let col3 = document.querySelector("#col3");
// 		xhr.send("?fqKeyWordNo="+col1.value+"&fqKeyWordContent="+col2.value+"&answerContent="+col3.value);
		document.getElementById("formQuery").submit();
	}
	function subForInsert(){
		document.getElementById("formInsert").submit();
	}
	
	
	

    (function () {
        $.MsgBox = {
            Alert: function (title, msg) {
                GenerateHtml("alert", title, msg);
                btnOk();  //alert只是彈出訊息，因此沒必要用到回撥函式callback
                btnNo();
            },
            Confirm: function (title, msg, callback) {
                GenerateHtml("confirm", title, msg);
                btnOk(callback);
                btnNo();
            }
        }
        //生成Html
        var GenerateHtml = function (type, title, msg) {
            var _html = "";
            _html += '<div id="mb_box"></div><div id="mb_con"><span id="mb_tit">' + title + '</span>';
            _html += '<a id="mb_ico">x</a><div id="mb_msg">' + msg + '</div><div id="mb_btnbox">';
            if (type == "alert") {
                _html += '<input id="mb_btn_ok" type="button" value="確定" />';
            }
            if (type == "confirm") {
                _html += '<input id="mb_btn_ok" type="button" value="確定" />';
                _html += '<input id="mb_btn_no" type="button" value="取消" />';
            }
            _html += '</div></div>';
            //必須先將_html新增到body，再設定Css樣式
            $("body").append(_html);
            //生成Css
            GenerateCss();
        }

        //生成Css
        var GenerateCss = function () {
            $("#mb_box").css({
                width: '100%', height: '100%', zIndex: '99999', position: 'fixed',
                filter: 'Alpha(opacity=60)', backgroundColor: 'black', top: '0', left: '0', opacity: '0.6'
            });
            $("#mb_con").css({
                zIndex: '999999', width: '400px', position: 'fixed',
                backgroundColor: 'White', borderRadius: '15px'
            });
            $("#mb_tit").css({
                display: 'block', fontSize: '14px', color: '#444', padding: '10px 15px',
                backgroundColor: '#DDD', borderRadius: '15px 15px 0 0',
                borderBottom: '3px solid #009BFE', fontWeight: 'bold'
            });
            $("#mb_msg").css({
                padding: '20px', lineHeight: '20px',
                borderBottom: '1px dashed #DDD', fontSize: '13px'
            });
            $("#mb_ico").css({
                display: 'block', position: 'absolute', right: '10px', top: '9px',
                border: '1px solid Gray', width: '18px', height: '18px', textAlign: 'center',
                lineHeight: '16px', cursor: 'pointer', borderRadius: '12px', fontFamily: '微軟雅黑'
            });
            $("#mb_btnbox").css({ margin: '15px 0 10px 0', textAlign: 'center' });
            $("#mb_btn_ok,#mb_btn_no").css({ width: '85px', height: '30px', color: 'white', border: 'none' });
            $("#mb_btn_ok").css({ backgroundColor: '#168bbb' });
            $("#mb_btn_no").css({ backgroundColor: 'gray', marginLeft: '20px' });
            //右上角關閉按鈕hover樣式
            $("#mb_ico").hover(function () {
                $(this).css({ backgroundColor: 'Red', color: 'White' });
            }, function () {
                $(this).css({ backgroundColor: '#DDD', color: 'black' });
            });
            var _widht = document.documentElement.clientWidth;  //螢幕寬
            var _height = document.documentElement.clientHeight; //螢幕高
            var boxWidth = $("#mb_con").width();
            var boxHeight = $("#mb_con").height();
            //讓提示框居中
            $("#mb_con").css({ top: (_height - boxHeight) / 2 + "px", left: (_widht - boxWidth) / 2 + "px" });
        }
        //確定按鈕事件
        var btnOk = function (callback) {
            $("#mb_btn_ok").click(function () {
                $("#mb_box,#mb_con").remove();
                if (typeof (callback) == 'function') {
                    callback();
                }
            });
        }
        //取消按鈕事件
        var btnNo = function () {
            $("#mb_btn_no,#mb_ico").click(function () {
                $("#mb_box,#mb_con").remove();
            });
        }
    })();
	
    $.MsgBox.Alert("訊息", "哈哈，新增成功！");
	</script>

</body>

</html>