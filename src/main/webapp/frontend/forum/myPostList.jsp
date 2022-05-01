<%@page import="com.forumpost.model.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/frontend/fronthead.jsp"%>

<%
List<ForumPostVO> list = (List<ForumPostVO>) request.getAttribute("forumPostVOs");
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的文章</title>
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

						<h3>我的文章</h3>

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
						<th style="width: 150px">討論區名稱</th>
						<th style="width: 60px">文章精選</th>
						<th style="width: 400px">文章標題</th>
						<th style="width: 60px">文章分類</th>
						<th style="width: 100px">發表時間</th>
						<th style="width: 25px"></th>
						<th>&ensp;</th>
						<th style="width: 25px"></th>
					</tr>
					<%@ include file="page3.file"%>
					<c:forEach var="forumPostVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">

						<tr align='center' valign="middle">
							<td><div
									style="width: 150px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">${forumPostVO.forumVO.forumName}</div></td>

							<td><c:if test="${forumPostVO.forumPostFeatured == 0}"
									var="condition">
									<c:out value="一般" escapeXml="false"></c:out>
								</c:if> <c:if test="${forumPostVO.forumPostFeatured == 1}"
									var="condition">
									<c:out value="精選" escapeXml="false"></c:out>
								</c:if> <c:if test="${forumPostVO.forumPostFeatured == 2}"
									var="condition">
									<c:out value="置頂" escapeXml="false"></c:out>
								</c:if>&emsp;</td>
							<td><div
									style="width: 400px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
									<a
										href="<%=request.getContextPath()%>/forum/forumPostOneEdit?forumPostNo=${forumPostNo}">${forumPostVO.forumPostTitle}</a>
								</div></td>

							<td><c:if test="${forumPostVO.forumPostType == 0}"
									var="condition">
									<c:out value="管理員文章" escapeXml="false"></c:out>
								</c:if> <c:if test="${forumPostVO.forumPostType == 1}" var="condition">
									<c:out value="版主文章" escapeXml="false"></c:out>
								</c:if> <c:if test="${forumPostVO.forumPostType == 2}" var="condition">
									<c:out value="攻略文章" escapeXml="false"></c:out>
								</c:if> <c:if test="${forumPostVO.forumPostType == 3}" var="condition">
									<c:out value="情報" escapeXml="false"></c:out>
								</c:if> <c:if test="${forumPostVO.forumPostType == 4}" var="condition">
									<c:out value="閒聊" escapeXml="false"></c:out>
								</c:if> <c:if test="${forumPostVO.forumPostType == 5}" var="condition">
									<c:out value="其他" escapeXml="false"></c:out>
								</c:if></td>
							<td><fmt:formatDate value="${forumPostVO.forumPostTime}"
									pattern="yyyy-MM-dd" />&emsp;</td>

							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/forum/XXXservlet"
									style="margin-bottom: 0px;">
									<input type="submit" value="修改"
										style="width: 37px; height: 37px;"><input
										type="hidden" name="forumPostNo"
										value="${forumPostVO.forumPostNo}">
								</FORM>
							</td>
							<td></td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/forum/XXXservlet"
									style="margin-bottom: 0px;">
									<input type="submit" value="刪除"
										style="width: 37px; height: 37px;"> <input
										type="hidden" name="forumPostNo"
										value="${forumPostVO.forumPostNo}">
								</FORM>
							</td>
						</tr>
					</c:forEach>

				</table>
				<%@ include file="page2.file"%>
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