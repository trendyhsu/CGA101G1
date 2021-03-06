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

				<form method="post"
					action="<%=request.getContextPath()%>/forum/forumPostEditUpdate"
					name="form1">
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
							<td><select size="1" name="forumPostType">
									<option value="1"
										<c:if test="${forumPostVO.forumPostType == 1}"><c:out value="selected"></c:out></c:if>>1版主文章</option>
									<option value="2"
										<c:if test="${forumPostVO.forumPostType == 2}"><c:out value="selected"></c:out></c:if>>2攻略文章</option>
									<option value="3"
										<c:if test="${forumPostVO.forumPostType == 3}"><c:out value="selected"></c:out></c:if>>3情報</option>
									<option value="4"
										<c:if test="${forumPostVO.forumPostType == 4}"><c:out value="selected"></c:out></c:if>>4閒聊</option>
									<option value="5"
										<c:if test="${forumPostVO.forumPostType == 5}"><c:out value="selected"></c:out></c:if>>5其他</option>

							</select></td>
						</tr>
						<tr>
							<td>會員編號</td>
							<td>${forumPostVO.memNo}</td>
						</tr>
						<tr>
							<td>文章狀態</td>
							<td><select size="1" name="forumPostState">
									<option value="0"
										<c:if test="${forumPostVO.forumPostState == 0}"><c:out value="selected"></c:out></c:if>>0不顯示</option>
									<option value="1"
										<c:if test="${forumPostVO.forumPostState == 1}"><c:out value="selected"></c:out></c:if>>1顯示</option>
							</select></td>
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

					<input type="hidden" name="forumPostNo"
						value="${forumPostVO.forumPostNo}"><input type="submit"
						value="確認修改">
				</form>

				<div style="height: 15px"></div>

				<div style="display: inline-block;">
					<a
						href="<%=request.getContextPath()%>/backend/forum/listAllForumPost.jsp">
						<button>返回文章列表</button>
					</a>
				</div>
			</div>
		</section>

		<!--main content end-->

	</section>
</body>
</html>
