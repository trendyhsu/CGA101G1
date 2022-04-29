<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forumpost.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>

<%
// 取得來自 ForumPostEditUpdateServlet 的 ForumPostVO
ForumPostVO forumPostVO = (ForumPostVO) request.getAttribute("forumPostVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改文章</title>

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
							<h3>修改文章資料</h3>
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
						<td>文章精選</td>
						<td><c:if test="${forumPostVO.forumPostFeatured == 0}"
								var="condition">
								<c:out value="0一般" escapeXml="false"></c:out>
							</c:if> <c:if test="${forumPostVO.forumPostFeatured == 1}"
								var="condition">
								<c:out value="1精選" escapeXml="false"></c:out>
							</c:if> <c:if test="${forumPostVO.forumPostFeatured == 2}"
								var="condition">
								<c:out value="2置頂" escapeXml="false"></c:out>
							</c:if></td>
					</tr>
					<tr>
						<td>文章分類</td>
						<td><c:if test="${forumPostVO.forumPostType == 1}"
								var="condition">
								<c:out value="1版主文章" escapeXml="false"></c:out>
							</c:if> <c:if test="${forumPostVO.forumPostType == 2}" var="condition">
								<c:out value="2攻略文章" escapeXml="false"></c:out>
							</c:if> <c:if test="${forumPostVO.forumPostType == 3}" var="condition">
								<c:out value="3情報" escapeXml="false"></c:out>
							</c:if> <c:if test="${forumPostVO.forumPostType == 4}" var="condition">
								<c:out value="4閒聊" escapeXml="false"></c:out>
							</c:if> <c:if test="${forumPostVO.forumPostType == 5}" var="condition">
								<c:out value="5其他" escapeXml="false"></c:out>
							</c:if></td>
					</tr>
					<tr>
						<td>會員編號</td>
						<td>${forumPostVO.memNo}</td>
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
						<td>文章標題</td>
						<td>${forumPostVO.forumPostTitle}</td>
					</tr>
					<tr>
						<td>文章內容</td>
						<td>${forumPostVO.forumPostContent}</td>
					</tr>
					<tr>
						<td>發表時間</td>
						<td><fmt:formatDate value="${forumPostVO.forumPostTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>

				</table>

				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/forum/forumPostOneEditServlet"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改狀態"> <input type="hidden"
						name="forumPostNo" value="${forumPostVO.forumPostNo}">
				</FORM>
			</div>
		</section>

		<!--main content end-->
		<div style="display: inline-block; padding-left: 15px">
			<a
				href="<%=request.getContextPath()%>/backend/forum/listAllForumPost.jsp">
				<button>返回文章列表</button>
			</a>
		</div>

		<div style="display: inline-block;">
			<a
				href="<%=request.getContextPath()%>/backend/forum/listAllForumPostReport.jsp">
				<button>返回文章檢舉列表</button>
			</a>
		</div>

		<div style="display: inline-block;">
			<a
				href="<%=request.getContextPath()%>/backend/forum/selectReportHome.jsp">
				<button>返回檢舉首頁</button>
			</a>
		</div>

	</section>
</body>
</html>
