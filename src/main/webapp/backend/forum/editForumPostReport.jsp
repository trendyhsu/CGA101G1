<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forumpostreport.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>

<%
// 取得來自 ForumPostReportOneEditServlet 的 ForumPostReportVO
ForumPostReportVO forumPostReportVO = (ForumPostReportVO) request.getAttribute("forumPostReportVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改文章檢舉</title>

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
							<h3>修改文章檢舉資料</h3>
						</td>
					</tr>
				</table>
				<!-- main content -->

				<form method="post"
					action="<%=request.getContextPath()%>/forum/forumPostReportEditUpdate"
					name="form1">
					<table>
						<tr>
							<td>文章檢舉編號</td>
							<td>${forumPostReportVO.forumPostReportNo}</td>
						</tr>
						<tr>
							<td>文章編號</td>
							<td>${forumPostReportVO.forumPostNo}</td>
						</tr>
						<tr>
							<td>文章標題</td>
							<td>${forumPostReportVO.forumPostVO.forumPostTitle}</td>
						</tr>

						<tr>
							<td>文章內容</td>
							<td>${forumPostReportVO.forumPostVO.forumPostContent}</td>
						</tr>

						<tr>
							<td>檢舉會員編號</td>
							<td>${forumPostReportVO.memNo}</td>
						</tr>
						<tr>
							<td>文章檢舉狀態</td>
							<td><select size="1" name="forumPostReportType">
									<option value="0"
										<c:if test="${forumPostReportVO.forumPostReportType == 0}"><c:out value="selected"></c:out></c:if>>0未處理</option>
									<option value="1"
										<c:if test="${forumPostReportVO.forumPostReportType == 1}"><c:out value="selected"></c:out></c:if>>1檢舉成功</option>
									<option value="2"
										<c:if test="${forumPostReportVO.forumPostReportType == 2}"><c:out value="selected"></c:out></c:if>>2檢舉失敗</option>
							</select></td>
						</tr>
						<tr>
							<td>檢舉原因</td>
							<td>${forumPostReportVO.forumPostReportWhy}</td>
						</tr>
						<tr>
							<td>檢舉時間</td>
							<td><fmt:formatDate
									value="${forumPostReportVO.forumPostReportTime}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>

					</table>

					<input type="hidden" name="forumPostReportNo"
						value="${forumPostReportVO.forumPostReportNo}"><input
						type="submit" value="確認修改">


				</form>

				<div style="height: 15px"></div>

				<div style="display: inline-block;">
					<a
						href="<%=request.getContextPath()%>/backend/forum/listAllForumPostReport.jsp">
						<button>返回文章檢舉列表</button>
					</a>
				</div>
			</div>
		</section>

		<!--main content end-->

	</section>
</body>
</html>
