<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forum.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>

<%
// 取得來自 ForumMsgEditUpdateServlet 的 ForumMsgVO

ForumVO forumVO = (ForumVO) request.getAttribute("forumVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改討論區</title>

<style>
table {
	/* 	background-color: white; */
	margin-bottom: 5px;
	font-size: 13px;
	color: black;
}

table, th, td {
	border-bottom: 1px solid #CCC;
	font-weight: bold;
}

th, td {
	padding: 5px;
	text-align: left;
}

.uploadedImg {
	padding: 10px
}

h3 {
	font-weight: bold;
	color: #547492;
}
</style>

</head>
<body>
	<section id="main-content">
		<section class="wrapper">


			<div id="forum-content">

				<table id="table-1">
					<tr>
						<td>
							<h3>修改討論區資料</h3>
						</td>
					</tr>
				</table>
				<!-- main content -->
				<table>
					<tr>
						<td>討論區編號</td>
						<td>${forumVO.forumNo}</td>
					</tr>
					<tr>
						<td>討論區名稱</td>
						<td>${forumVO.forumName}</td>
					</tr>

					<tr>
						<td>討論區狀態</td>
						<td><c:if test="${forumVO.forumType == 0}" var="condition">
								<c:out value="0不顯示" escapeXml="false"></c:out>
							</c:if> <c:if test="${forumVO.forumType == 1}" var="condition">
								<c:out value="1顯示" escapeXml="false"></c:out>
							</c:if></td>
					</tr>
					<tr>
						<td>版主名稱</td>
						<td>${forumVO.memVO.memName}</td>
					</tr>
					<tr>
						<td>討論區圖片</td>
						<td>${forumVO.forumImg}</td>
					</tr>
				</table>

				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/forum/forumOneEditServlet"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改狀態"> <input type="hidden"
						name="forumNo" value="${forumVO.forumNo}">
				</FORM>
			</div>
		</section>

		<!--main content end-->
		<div style="display: inline-block; padding-left: 15px">
			<a
				href="<%=request.getContextPath()%>/backend/forum/listAllForum.jsp">
				<button>返回討論區列表</button>
			</a>
		</div>
		<div style="display: inline-block;">
			<a
				href="<%=request.getContextPath()%>/backend/forum/selectPowerHome.jsp">
				<button>返回權限編輯首頁</button>
			</a>
		</div>


	</section>
</body>
</html>
