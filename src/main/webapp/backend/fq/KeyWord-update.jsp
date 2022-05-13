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
				<i class="fa-solid fa-gear"></i> 客服機器人關鍵字修改
			</h3>
			<div class="row mt">


				<form action="<%=request.getContextPath()%>/fqkeyword/fqkeyword.do"
					method="post" class="formQuery" id="form1">

					<ul>
						<li><label style="text-decoration: line-through;">關鍵字編號</label></li>
						<li><input type="text" disabled value="${fqKeyWordVO.fqKeyWordNo}"></li>
						<li><label>關鍵字</label></li>
						<li><input type="text" name="fqKeyWordContent" value="${fqKeyWordVO.fqKeyWordContent}" required></li>
						<li><label>回應內容</label></li>
						<li><input type="text" name="answerContent" value="${fqKeyWordVO.answerContent}" required></li>
					<li><input type="hidden" name="fqKeyWordNo" value="${fqKeyWordVO.fqKeyWordNo}"></li>
					<li><input type="hidden" name="requestURL" value="<%=request.getRequestURI()%>"></li>  
					<li></li><li>${errorMsgs.fqKeyWordContent}</li>
					<li></li><li>${errorMsgs.answerContent}</li>
						
					</ul>


					<button type="submit" class="cusBtn">
						<i class="fa-solid fa-pencil"></i>修改
					</button>
					<input type="hidden" name="action" value="update">
				</form>
				
			</div>
		</section>
	</section>

	<!--main content end-->


</body>

</html>