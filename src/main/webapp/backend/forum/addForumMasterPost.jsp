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
<!--MemVO memVO = (MemVO) request.getSession().getAttribute("memVO"); -->
<!--	Integer memNo = memVO.getMemNo(); -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增管理員文章</title>

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
							<h3>新增管理員文章</h3>
						</td>
					</tr>
				</table>
				<!-- main content -->

				<form method="post"
					action="<%=request.getContextPath()%>/forum/forumMasterPostInsert"
					name="form1">

					<table>
						<tr>

							<jsp:useBean id="managerSvc" scope="page"
								class="com.manager.model.ManagerService" />
							<td>管理員名稱</td>
							<td><select size="1" name="managerNo">
									<c:forEach var="managerVO" items="${managerSvc.getAll()}">
										<option value="${managerVO.managerNo}"
											${(managerVO.managerNo==forumPostVO.managerNo) ? "selected" : ""}>
											${managerVO.managerName}
									</c:forEach>
							</select></td>
						</tr>

						<tr>
							<jsp:useBean id="forumSvc" scope="page"
								class="com.forum.model.ForumService" />
							<td>討論區</td>
							<td><select size="1" name="forumNo">
									<option value="0"
										${(forumVO.forumNo != forumPostVO.forumNo) ? "selected" : ""}>-請選擇討論區-
										<c:forEach var="forumVO" items="${forumSvc.getAll()}">
											<option value="${forumVO.forumNo}"
												${(forumVO.forumNo==forumPostVO.forumNo) ? "selected" : ""}>
												${forumVO.forumName}
										</c:forEach>
							</select></td>
							<td>${errorMsgs.forumNo}</td>
						</tr>
						<tr>
							<td>文章狀態</td>
							<td><select size="1" name="forumPostState">
									<option value="1"
										<c:if test="${forumPostVO.forumPostState == 1}"><c:out value="selected"></c:out></c:if>>1顯示</option>
									<option value="0"
										<c:if test="${forumPostVO.forumPostState == 0}"><c:out value="selected"></c:out></c:if>>0不顯示</option>
							</select></td>
						</tr>

						<tr>
							<td>文章標題</td>
							<td><input type="text" name="forumPostTitle" size="112"
								value="${forumPostVO.forumPostTitle}" /></td>
							<td>${errorMsgs.forumPostTitle}</td>
						</tr>
						<tr>
							<td>文章內容</td>
							<td><textArea name="forumPostContent" id="forumPostContent"
									rows="25" cols="115" style="resize: none;">${forumPostVO.forumPostContent}</textArea></td>
							<td>${errorMsgs.forumPostContent}</td>
						</tr>
					</table>

					<input type="hidden" name="forumPostType" value="0"><input
						type="hidden" name="forumPostFeatured" value="2">
					<div style="display: inline-block; width: 77px;"></div>
					<input type="submit" value="新增"> <input type="reset"
						value="重設">
				</form>

				<div style="height: 15px"></div>
			<div style="display: inline-block; width: 77px;"></div>
			<div style="display: inline-block;">
				<a
					href="<%=request.getContextPath()%>/backend/forum/listAllForumMasterPost.jsp">
					<button>返回管理員文章列表</button>
				</a>
			</div>
			</div>
		</section>

		<!--main content end-->

	</section>
</body>
</html>
