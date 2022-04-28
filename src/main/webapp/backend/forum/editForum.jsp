<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forum.model.*"%>
<%@ page import="java.util.*"%>

<%@include file="/backend/share.jsp"%>

<%
// 取得來自 ForumOneEditServlet 的 ForumVO
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

				<form method="post"
					action="<%=request.getContextPath()%>/forum/forumEditUpdate"
					name="form1" enctype="multipart/form-data">
					<table>
						<tr>
							<td>討論區編號</td>
							<td>${forumVO.forumNo}</td>
						</tr>
						<tr>
							<td>討論區名稱</td>
							<td><input type="text" name="forumName" size="45"
								value="${forumVO.forumName}" /></td>
							<td>${errorMsgs.forumName}</td>

						</tr>
						<tr>
							<td>討論區狀態</td>
							<td><select size="1" name="forumType">
									<option value="0"
										<c:if test="${forumVO.forumType == 0}"><c:out value="selected"></c:out></c:if>>0不顯示</option>
									<option value="1"
										<c:if test="${forumVO.forumType == 1}"><c:out value="selected"></c:out></c:if>>1顯示</option>
							</select></td>
						</tr>
						<tr>


							<jsp:useBean id="memJDBCDAO" scope="page"
								class="com.member.model.MemJDBCDAO" />
							<td>版主名稱</td>
							<td><select size="1" name="memNo">
									<option value="0"
										${(memVO.memNo != forumVO.memNo) ? "selected" : ""}>無版主
										<c:forEach var="memVO" items="${memJDBCDAO.getAll()}">
											<option value="${memVO.memNo}"
												${(forumVO.memNo==memVO.memNo) ? "selected" : ""}>
												${memVO.memName}
										</c:forEach>
							</select></td>
						</tr>
						<tr>
							<!-- 上傳圖片區 -->
							<td>討論區圖片</td>
							<td><input type="file" name="upfile1"
								onclick="previewImage()" multiple id="upfile"></td>
						</tr>
					</table>
					<input type="hidden" name="forumNo"
						value="${forumVO.forumNo}"> <input type="submit"
						value="修改">
				</form>

				<!-- 		圖片顯示區及刪除 -->

				<div style="height: 15px"></div>

				<div style="display: inline-block;">
					<a
						href="<%=request.getContextPath()%>/backend/forum/listAllForum.jsp">
						<button>返回討論區列表</button>
					</a>
				</div>
			</div>
		</section>

		<!--main content end-->

	</section>

</body>
</html>
