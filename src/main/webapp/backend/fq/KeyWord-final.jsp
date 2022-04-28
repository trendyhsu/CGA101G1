<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/backend/share.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">

<link
	href="<%=request.getContextPath()%>/backend/fq/assets/fq-keyword.css"
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
				<i class="fa-solid fa-gear"></i> 客服機器人關鍵字管理
			</h3>
			<div class="row mt">

				<div class="radio-group">
					<input type="radio" name="func" id="qu" class="myRadio2" checked>
					<label for="qu">查詢</label> <input type="radio" name="func" id="in"
						class="myRadio2"> <label for="in">新增</label>
				</div>

				<form action="<%=request.getContextPath()%>/fqkeyword/fqkeyword.do"
					method="post" class="formQuery" id="form1">

					<ul>
						<li><label>關鍵字編號</label></li>
						<li><input type="text" name="fqKeyWordNo" id="col1"></li>
						<li><label>關鍵字</label></li>
						<li><input type="text" name="fqKeyWordContent" id="col2"></li>
						<li><label>回應內容</label></li>
						<li><input type="text" name="answerContent" id="col3"></li>
					</ul>


					<button type="submit" class="cusBtn">
						<i class="fa-solid fa-magnifying-glass"></i>查詢
					</button>
					<input type="hidden" name="action" value="AllQuery">
				</form>
				<form action="<%=request.getContextPath()%>/fqkeyword/fqkeyword.do"
					method="post" class="formQuery" id="form2">

					<ul>
						<li><label style="text-decoration: line-through;">關鍵字編號</label></li>
						<li><input type="text" disabled></li>
						<li><label>關鍵字</label></li>
						<li><input type="text" name="fqKeyWordContent" required></li>
						<li><label>回應內容</label></li>
						<li><input type="text" name="answerContent"></li>
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
		src="<%=request.getContextPath()%>/backend/fq/assets/fqKeyWord-js.js"></script>


</body>

</html>