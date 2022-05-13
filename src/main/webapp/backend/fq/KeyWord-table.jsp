<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


			<div class="row">
				<div class="col-md-12">
					<div class="content-panel">
						<div>
							<h4 class="funcTitle02">
								<i class="fa fa-angle-right"></i> 查詢結果
							</h4>
							<h4 class="funcTitle02">
								<a
									href="<%=request.getContextPath()%>/backend/fq/KeyWord-final.jsp"><i
									class="fa-solid fa-circle-chevron-left">返回</i></a>
							</h4>
						</div>
							
						<hr>
						<table class="table table-striped table-advance table-hover"
							id="KeyWordTable">
							<thead>
								<tr>
									<th><i class="fa fa-solid fa-hashtag"></i> 編號</th>
									<th class="hidden-phone"><i class="fa fa-question-circle"></i>
										關鍵字</th>
									<th><i class="fa fa-bookmark"></i> 回應內容</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="keyWord" items="${AllQuery}">							<tr>

										<td>${keyWord.fqKeyWordNo}</td>
										<td>${keyWord.fqKeyWordContent}</td>
										<td>${keyWord.answerContent}</td>
										<td>
											<form method="post" action="<%=request.getContextPath()%>/fqkeyword/fqkeyword.do">
												<button class="btn btn-primary btn-xs edit" type="submit">
													<i class="fa fa-pencil"></i>
												</button>
												<input type="hidden" name="fqKeyWordNo" value="${keyWord.fqKeyWordNo}"> 
												<input type="hidden" name="fqKeyWordContent" value="${keyWord.fqKeyWordContent}">
												<input type="hidden" name="answerContent" value="${keyWord.answerContent}" >
												<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
												<!--送出本網頁的路徑給Controller-->
												<input type="hidden" name="action" value="get_one_to_update">
											</form>
											<form method="post"
												action="<%=request.getContextPath()%>/fqkeyword/fqkeyword.do">
												<button class="btn btn-danger btn-xs" type="submit">
													<i class="fa fa-trash-o "></i>
												</button>
												<input type="hidden" name="fqKeyWordNo"
													value="${keyWord.fqKeyWordNo}"> <input
													type="hidden" name="requestURL"
													value="<%=request.getServletPath()%>">
												<!--送出本網頁的路徑給Controller-->
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


</body>

</html>