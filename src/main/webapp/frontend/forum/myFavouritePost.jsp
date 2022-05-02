<%@page import="com.forumpostcollection.model.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/frontend/fronthead.jsp"%>

<%
List<ForumPostCollectionVO> list = (List<ForumPostCollectionVO>) request.getAttribute("forumPostCollectionVOs");
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的文章收藏</title>
<style type="text/css">
table {
	font-weight: bold;
}

table tr td, th {
	border-bottom: solid;
}

th {
	background-color: #b2cdcc;
}

#pageNumber, #dataNumber {
	float: right;
}

input:hover {
	background-color: #b2cdcc;
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
	<!-- End Profile Menu -->
	<!-- Content 主要內容區 要修改的部分都塞在這個裡面 -->
	<!-- 內容直接寫在<div class="table-responsive fs-md mb-4">裡面 -->
	<div class="col-lg-9 col-xxl-9">
		<div class="table-responsive fs-md mb-4">

			<div id="bid-content">

				<table id="table-1">
					<tr>

						<h3>我的文章收藏</h3>

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

				<table class="showPanel" style="table-layout: fixed; color: black;">
					<tr align='center' valign="middle">
						<th style="width: 150px">討論區名稱&emsp;</th>
						<th style="width: 425px">文章標題&emsp;</th>
						<th style="width: 100px">發文者&emsp;</th>
						<th style="width: 100px">收藏時間&emsp;</th>
						<th style="width: 50px"></th>
					</tr>
					<%@ include file="page3.file"%>
					<c:forEach var="forumPostCollectionVO" items="${list}"
						begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

						<tr align='center' valign="middle">
							<td><div
									style="width: 150px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">${forumPostCollectionVO.forumPostVO.forumVO.forumName}</div></td>
							<td><div
									style="width: 425px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
									<a
										href="<%=request.getContextPath()%>/forum/forumPostOneEdit?forumPostNo=${forumPostCollectionVO.forumPostNo}">${forumPostCollectionVO.forumPostVO.forumPostTitle}</a>
								</div></td>
							<td><div
									style="width: 100px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">${forumPostCollectionVO.forumPostVO.memVO.memName}</div></td>
							<td><fmt:formatDate
									value="${forumPostCollectionVO.forumPostCollectionTime}"
									pattern="yyyy-MM-dd" />&emsp;</td>

							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/forum/forumPostCollectionDelete"
									style="margin-bottom: 0px;">
									<input type="submit" value="刪除"
										style="width: 37px; height: 37px;"> <input
										type="hidden" name="memNo"
										value="${forumPostCollectionVO.memNo}"> <input
										type="hidden" name="forumPostNo"
										value="${forumPostCollectionVO.forumPostNo}">
								</FORM>
							</td>
						</tr>
					</c:forEach>

				</table>
				<%@ include file="page2.file"%>

				<div style="display: inline-block;">
					<a
						href="
			<%=request.getContextPath()%>/frontend/forum/forumHomePage.jsp">
						<button class="button1">討論區首頁</button>
					</a>
				</div>
			</div>

		</div>
	</div>
	<!-- End Content -->
	</div>
	</div>
	</div>
	<!--Table -->
	</main>
	<!-- End Main -->
</body>
</html>
<%@include file="/frontend/frontfoot.jsp"%>