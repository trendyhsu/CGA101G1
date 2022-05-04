<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/backend/share.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
   
    <title>POP.Game ServerSide</title>

	<link href="<%=request.getContextPath()%>/backend/servicechatroom/assets/service-chatroom.css"
	rel="stylesheet">
  
</head>

<body>

        <!-- **********************************************************************************************************************************************************
      MAIN CONTENT 內容寫在這裡面
      *********************************************************************************************************************************************************** -->
        <!--main content start-->
        <section id="main-content">
            <section class="wrapper">
                <h3 class="funcTitle02"><i class="fa-solid fa-headset"></i> 線上客服聊天室</h3>
                <!-- <div class="row mt"> -->

                <div class="Service-chatroom">
                    <div class="userList">
                        <ul>
                            <li><i class="fa-solid fa-user"></i>侯宇陽33333333333333333333333</li>
                            <li><i class="fa-solid fa-user"></i>訪客1</li>
                            <li><i class="fa-solid fa-user"></i>訪客2</li>
                            <li><i class="fa-solid fa-user"></i>訪客3</li>
                            <li><i class="fa-solid fa-user"></i>訪客4</li>
                            <li><i class="fa-solid fa-user"></i>訪客5</li>
                            <li><i class="fa-solid fa-user"></i>許詠政</li>
                        </ul>

                    </div>
                    <div class="chatroom">
                        <div class="userName">
                            侯宇陽
                        </div>
                        <div class="chat-content">

                            <div class="messageLine">
                                <div class="oneMessage">
                                    HI!
                                </div>
                            </div>

                            <div class="messageLine">
                                <div class="oneAnswer">
                                    Bye!
                                </div>

                            </div>

         



                        </div>
                        <div class="answerBar">
                            <input type="text">
                            <i class="fa-solid fa-paper-plane"></i>
                        </div>
                    </div>
                </div>
                <!-- </div> -->
            </section>
        </section>

        <!--main content end-->

   

   <script src="<%=request.getContextPath()%>/backend/servicechatroom/assets/service-chatroom-js.js"></script>


</body>

</html>