<%@page import="com.bidproduct.model.BidProductVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    

<body onload="connect();" onunload="disconnect();">

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



<div id = "xiaowu">
	<h1>CGA101G1聊天室</h1>

	<textarea id="messagesArea" class="panel message-area" readonly style="border: 2px solid gray ;width: 100% ;height: 200px"></textarea>
	<div class="panel input-area">
		<input id="userName" class="form-control" type="text" placeholder="User name" /> 
		<input id="message" class="form-control" type="text" placeholder="Message" onkeydown="if (event.keyCode == 13) sendMessage();" /> 
		<input type="submit" id="sendMessage" class="btn btn-success" value="送出" onclick="sendMessage();" /> 
		<input type="button" id="connect" class="btn btn-success" value="連線" onclick="connect();" /> 
		<input type="button" id="disconnect" class="btn btn-success" value="離線" onclick="disconnect();" />
	</div>
</div>


                    <!-- End Content -->
                </div>
            </div>
        </div>
<!--         Table -->
    </main>
<!--     End Main -->




</body>

<script>
	var MyPoint = "/chatroom/james";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

	var statusOutput = document.getElementById("statusOutput");
	var webSocket;

	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
			
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var messagesArea = document.getElementById("messagesArea");
			var jsonObj = JSON.parse(event.data);
			var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
			messagesArea.value = messagesArea.value + message;
			messagesArea.scrollTop = messagesArea.scrollHeight;
		};

		webSocket.onclose = function(event) {
			
		};
	}

	var inputUserName = document.getElementById("userName");
	inputUserName.focus();

	function sendMessage() {
		var userName = inputUserName.value.trim();
		if (userName === "") {
			alert("Input a user name");
			inputUserName.focus();
			return;
		}

		var inputMessage = document.getElementById("message");
		var message = inputMessage.value.trim();

		if (message === "") {
			alert("Input a message");
			inputMessage.focus();
		} else {
			var jsonObj = {
				"userName" : userName,
				"message" : message
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}

	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}
	
</script>
</html>
<%@include file="/frontend/frontfoot.jsp" %>