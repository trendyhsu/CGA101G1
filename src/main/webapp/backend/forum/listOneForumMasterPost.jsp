<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forumpost.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>

<%
// 取得來自 ForumPostReportOneEditServlet 的 ForumPostReportVO
ForumPostVO forumPostVO = (ForumPostVO) request.getAttribute("forumPostVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理員文章存取成功</title>

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
							<h3>管理員文章-存取成功!</h3>
						</td>
					</tr>
				</table>
				<!-- main content -->
				<table>
					<tr>
						<td>文章編號</td>
						<td>${forumPostVO.forumPostNo}</td>
					</tr>
					<tr>
						<td>討論區名稱</td>
						<td>${forumPostVO.forumNo}-${forumPostVO.forumVO.forumName}</td>
					</tr>
					<tr>
						<td>文章狀態</td>
						<td><c:if test="${forumPostVO.forumPostState == 0}"
								var="condition">
								<c:out value="0不顯示" escapeXml="false"></c:out>
							</c:if> <c:if test="${forumPostVO.forumPostState == 1}" var="condition">
								<c:out value="1顯示" escapeXml="false"></c:out>
							</c:if></td>
					</tr>
					<tr>
						<td>管理員名稱</td>
						<td>${forumPostVO.managerNo}-${forumPostVO.managerVO.managerName}</td>
					</tr>

					<tr>
						<td>文章標題</td>
						<td>${forumPostVO.forumPostTitle}</td>
					</tr>
					<tr>
						<td>文章內容</td>
						<td><p style="white-space: pre-line">${forumPostVO.forumPostContent}</p></td>
					</tr>
					<tr>
						<td>發表時間</td>
						<td><fmt:formatDate value="${forumPostVO.forumPostTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</table>

				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/forum/forumMasterPostOneEdit"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改內容"> <input type="hidden"
						name="forumPostNo" value="${forumPostVO.forumPostNo}">
				</FORM>
			</div>
		</section>

		<!--main content end-->

		<div style="display: inline-block; padding-left: 15px">
			<a
				href="<%=request.getContextPath()%>/backend/forum/listAllForumMasterPost.jsp">
				<button>返回管理員文章列表</button>
			</a>
		</div>

	</section>
	<div style="height: 15px"></div>
</body>
</html>
