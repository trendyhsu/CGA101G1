<%@page import="com.managerauth.model.ManagerAuthVO"%>
<%@page import="java.util.List"%>
<%@page import="com.managerauth.model.ManagerAuthService"%>
<%@page import="com.manager.model.ManagerVO"%>
<%@page import="com.manager.model.ManagerService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/backend/share.jsp"%>

<%
ManagerService managerService = new ManagerService();
List<ManagerVO> list = managerService.getAll();
pageContext.setAttribute("list", list);

ManagerAuthService managerAuthService = new ManagerAuthService();
List<ManagerAuthVO> list1 = managerAuthService.getAll();
pageContext.setAttribute("list1", list1);
%>
<jsp:useBean id="managerFunction" scope="page"
	class="com.managerauthrizationfunction.model.ManagerAuthrizationFunctionService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理員權限</title>

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

						<h3>管理員權限</h3>

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
					<table class="table table-striped table-advance table-hover">
						<h4>
							<i class="fa fa-angle-right"></i>管理員權限
						</h4>
						</div>
						</h4>

						<hr>
						<thead>
							<tr>
								<th><i class="fa fa-camera"></i>照片</th>
								<th class=" hidden-phone"><i class="fa fa-barcode"></i> 編號</th>
								<th><i class="fa fa-bookmark"></i> 姓名</th>
								<th><i class=" fa fa-edit"></i>權限</th>
								<th></th>
							</tr>
						</thead>
						<%@ include file="page1.file"%>
						<c:forEach var="managerVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">

							<tbody>
								<tr>
									<td>${managerVO.myManagerPic}</td>
									<td class="hidden-phone">${managerVO.managerNo}</td>
									<td>${managerVO.managerName}</td>
									<td><c:forEach var="deptVO" items="${deptSvc.all}">
											<c:if test="${empVO.deptno==deptVO.deptno}">
	                   						 ${deptVO.deptno}【${deptVO.dname} - ${deptVO.loc}】
                  							  </c:if>
										</c:forEach></td>
							<!--  		<span class="label label-primary"
										style="margin: 1.5px; padding: 10px; font-size: 15px;">
										${managerAuthVO.managerAuthrizationFunctionNo}</span> -->
							</tbody>
						</c:forEach>
						<td sytle="">
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/backend/managerauth/addmanagerAuth.jsp"
								style="margin-bottom: 0px;">
								<input type="submit" value="新增"> <input type="hidden"
									name="managerAuthrizationFunctionNo"
									value="${managerAuthVO.managerAuthrizationFunctionNo}">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/backend/managerauth/editmanagerAuth.jsp"
								style="margin-bottom: 0px;">
								<input type="submit" value="修改"> <input type="hidden"
									name="managerAuthrizationFunctionNo"
									value="${managerAuthVO.managerAuthrizationFunctionNo}">
							</FORM>
						</td>
				</div>
				<%@ include file="page2.file"%>
			</div>
		</section>
		<!--/wrapper -->
	</section>
	<!-- /MAIN CONTENT -->

	<!--main content end-->
	</section>
</body>
</html>