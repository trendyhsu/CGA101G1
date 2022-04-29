<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/backend/share.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">

<title>POP.Game ServerSide</title>

<link
	href="<%=request.getContextPath()%>/backend/news/assets/game-news.css"
	rel="stylesheet">

</head>

<body>
		<!-- **********************************************************************************************************************************************************
      MAIN CONTENT 內容寫在這裡面
      *********************************************************************************************************************************************************** -->
		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">
				<h3 class="funcTitle02">
					<i class="fa-solid fa-gear"></i> 新聞管理
				</h3>
				<div class="row mt">

					<div class="radio-group">
						<input type="radio" name="func" id="qu" class="myRadio2" checked>
						<label for="qu">查詢 </label> <input type="radio" name="func"
							id="in" class="myRadio2"> <label for="in">新增 </label>
					</div>
					
					<jsp:useBean id="mngSvc" scope="page" class="com.manager.model.ManagerService" />	
					<jsp:useBean id="gpftSvc" scope="page" class="com.gameplatformtype.model.GamePlatformTypeService" />	
					<form action="<%=request.getContextPath()%>/gamenews/gamenews.do"
						method="post" class="formQuery" id="form1">

						<ul>
							<li><label>新聞編號</label></li>
							<li><input type="text" name="gameNewsNo"></li>
							<li><label>遊戲平台</label></li>
							 <li><select size="1" name="gamePlatformNo">
							 		<option value="">請選擇
                                    <c:forEach var="platformVO" items="${gpftSvc.all}">
                                    	<option value="${platformVO.gamePlatformNo}">${platformVO.gamePlatformName}
                                    </c:forEach>
                                </select></li>
							<li><label>編輯者</label></li>
							 <li><select size="1" name="managerNo">
							 		<option value="">請選擇
                                    <c:forEach var="managerVO" items="${mngSvc.all}">
                                    	<option value="${managerVO.managerNo}">${managerVO.managerName}
                                    </c:forEach>
                                </select></li>
							<li><label>新聞標題</label></li>
							<li><input type="text" name="gameNewsTitle"></li>
							<li><label>新聞內容</label></li>
							<li><textarea name="gameNewsContent" id="" cols="30"
									rows="10"></textarea></li>
						</ul>


						<button type="submit" class="cusBtn">
							<i class="fa-solid fa-magnifying-glass"></i>查詢
						</button>
						<input type="hidden" name="action" value="AllQuery">
					</form>
					<form action="<%=request.getContextPath()%>/gamenews/gamenews.do" method="post" class="formQuery"
						id="form2" enctype="multipart/form-data">
						<ul>
							<li><label style="text-decoration: line-through;">新聞編號</label></li>
							<li><input type="text" name="gameNewsNo" disabled></li>
							<li><label>遊戲平台</label></li>
							<li><select size="1" name="gamePlatformNo" required>
							 		<option value="">請選擇
                                    <c:forEach var="platformVO" items="${gpftSvc.all}">
                                    	<option value="${platformVO.gamePlatformNo}">${platformVO.gamePlatformName}
                                    </c:forEach>
                                </select></li>
							<li><label>編輯者</label></li>
							<li><select size="1" name="managerNo" required>
							 		<option value="">請選擇
                                    <c:forEach var="managerVO" items="${mngSvc.all}">
                                    	<option value="${managerVO.managerNo}">${managerVO.managerName}
                                    </c:forEach>
                                </select></li>
							<li><label>新聞標題</label></li>
							<li><input type="text" name="gameNewsTitle" required></li>
							<li><label>新聞封面照片</label></li>
							<li><input type="file" name="gameNewsPic" required></li>
							<li><label>新聞內容</label></li>
							<li><textarea name="gameNewsContent" id="" cols="30"
									rows="10"></textarea></li>
						</ul>


						<button type="submit" class="cusBtn">
							<i class="fa-solid fa-circle-plus"></i>新增
						</button>
						<input type="hidden" name="action" value="Insert">
					</form>
				</div>
			</section>
		</section>

		<!--main content end-->


	<script
		src="<%=request.getContextPath()%>/backend/news/assets/gameNews-js.js"></script>



</body>

</html>