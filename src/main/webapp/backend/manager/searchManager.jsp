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
<title>管理員搜尋</title>

<style type="text/css">
table {
	border-collapse: collapse;
}

th {
	font-size: larger;
	margin: 1em auto;
}

th, td {
	padding: .65em;
}

th {
	background: rgb(84,117,147);
	color: #fff;
}

th:first-child {
	border-radius: 9px 0 0 0;
}

th:last-child {
	border-radius: 0 9px 0 0;
}

tr:last-child td:first-child {
	border-radius: 0 0 0 9px;
}

tr:last-child td:last-child {
	border-radius: 0 0 9px 0;
}

tbody tr:hover {
	background: linear-gradient(#E0E7E9, #B2CDCC);
}

#pageNumber, #dataNumber {
	float: right;
}

input {
	background-color: #E0E7E9;
	border-radius: 5px
}

input:active {
	background-color: #fff;
}

.button1 {
	display: inline-block;
	padding: 3px 7px;
	font-size: 10px;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	outline: none;
	color: #fff;
	background-color: #547492;
	border: none;
	border-radius: 7px;
}

.button1:hover {
	background-color: #A3C6C4
}

.button1:active {
	background-color: #E0E7E9;
}
</style>

</head>
<body>
	<!--main content start-->
	<section id="main-content">
		<section class="">

			<div id="bid-content">

				<table id="table-1">
					<tr>

						<h3><i class="fa-solid fa-people-roof"></i>管理員搜尋結果</h3>

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
							<i class="fa fa-user-circle"></i> 管理員列表
							
						</h4>

						<hr>
						<thead>		
							<tr>
								<th><i class="fa fa-camera"></i>照片</th>
								<th class=" hidden-phone"><i class="fa fa-barcode"></i> 編號</th>
								<th><i class="fa fa-bookmark"></i> 姓名</th>
								<th><i class=" fa fa-phone"></i>手機</th>
								<th><i class=""></i>狀態</th>
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
									<td style="line-height:100px;"><span class="label label-info">
									<c:if test="${managerVO.managerState == 0}">在職</c:if>
									<c:if test="${managerVO.managerState == 1}">離職</c:if>
									</span>
									
									</td>
								
									<td>
										<div sytle="">
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/manager/managerEdit"
											style="">
											<input class="btn btn-primary btn-sm" type="submit" value="修改" 
											style="float:left;margin-top:35px;"> 
											<input type="hidden"
												name="managerNo" value="${managerVO.managerNo}">
										</FORM>
										</div>
										<div sytle="float:left;">
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/managerauth/managerEditAuth"
											style="">
											<input class="btn btn-primary btn-sm" type="submit" value="權限" 
											style="float:left;margin-top:35px;margin-left:6px;"> 
											<input type="hidden"
												name="managerNo" value="${managerVO.managerNo}">
												</FORM>
										</div>
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