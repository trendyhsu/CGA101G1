<%@page import="com.manager.model.ManagerVO"%>
<%@page import="com.manager.model.ManagerService"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/backend/share.jsp"%>

<%
ManagerService managerService = new ManagerService();
List<ManagerVO> list = managerService.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理員管理</title>

<style type="text/css">
th {
	border-bottom-style: solid;
	background-color: #b2cdcc;
}

td {
	border-bottom-style: solid;
}

h3 {
	font-weight: bold;
	color: #547492;
}
</style>

</head>
<body>
	<!--main content start-->
	<section id="main-content">
		<section class="wrapper">

			<div id="bid-content">

				<table id="table-1">
					<tr>

						<h3>管理員管理</h3>

					</tr>
				</table>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<%-- 成功表列 --%>
				<p style="color: red;">${successMsg}</p>
				<div class="content-panel">
					<table class="table table-striped">
						<h4>
							<i class="fa fa-angle-right"></i> 管理員列表
							<div style="float:right;">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/backend/manager/addManager.jsp">
									<input type="submit" value="新增" style="background-color:lightgreen; color:black;"> 
									<input type="hidden"
										name="managerNo" value="${managerVO.managerNo}">
								</FORM>
							</div>
						</h4>

						<hr>
						<thead>
							<tr>
								<th><i class="fa fa-camera"></i>照片</th>
								<th class=" hidden-phone"><i class="fa fa-barcode"></i> 編號</th>
								<th><i class="fa fa-bookmark"></i> 姓名</th>
								<th><i class=" fa fa-edit"></i>手機</th>
								<th><i class=" fa fa-edit"></i>狀態</th>
								<th><i class=" fa fa-edit"></i></th>
								<th></th>
							</tr>
						</thead>
						<%@ include file="page1.file"%>
						<c:forEach var="managerVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							
								<tr>
									<td><img src="<%=request.getContextPath()%>/manager/managerPic?managerNo=${managerVO.managerNo}"
									style="height:100px; width:80px;"></td>
									<td class="hidden-phone" style="line-height:100px;">${managerVO.managerNo}</td>
									<td style="line-height:100px;">${managerVO.managerName}</td>
									<td style="line-height:100px;">${managerVO.managerPhone}</td>
									<td style="line-height:100px;"><span class="label label-info label-mini">
									<c:if test="${managerVO.managerState == 0}">在職</c:if>
									<c:if test="${managerVO.managerState == 1}">離職</c:if>
									</span>
									
									</td>
								
									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/manager/managerEdit"
											style="margin-top: 40px;">
											<input class="btn btn-primary btn-sm" type="submit" value="修改"> 
											<input type="hidden"
												name="managerNo" value="${managerVO.managerNo}">
										</FORM>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/backend/manager/authManager.jsp"
											style="margin-top: 40px;">
											<input class="btn btn-primary btn-sm" type="submit" value="權限"> 
											<input type="hidden"
												name="managerNo" value="${managerVO.managerNo}">
									</td>
								
								</tr>
							

						</c:forEach>

					</table>
					<%@ include file="page2.file"%>
				</div>
			</div>
		</section>
		<!--/wrapper -->
	</section>
	<!-- /MAIN CONTENT -->

	<!--main content end-->
	</section>
</body>
</html>