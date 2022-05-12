<%@page import="com.chatroombanlist.model.ChatRoomBanListService"%>
<%@page import="com.member.model.MemVO"%>
<%@page import="com.chatroombanlist.model.ChatRoomBanListVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/frontend/fronthead.jsp" %>

<%
List<ChatRoomBanListVO> list = (List<ChatRoomBanListVO>) request.getAttribute("chatRoomBanListVOs");
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的忽略清單</title>
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
                    <!-- End Profile Menu -->
                    <!-- Content 主要內容區 要修改的部分都塞在這個裡面 -->
					<!-- 內容直接寫在<div class="table-responsive fs-md mb-4">裡面 -->
                    <div class="col-lg-9 col-xxl-9">
                        <div class="table-responsive fs-md mb-4">

	<div id="bid-content">
		
		<table id="table-1" >
			<tr>

			 		<h3 >我的忽略會員清單</h3>

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
		
		
			<table class="showPanel" style="table-layout: fixed; color: black; margin-left:auto; 
margin-right:auto;">
					<tr align='center' valign="middle">
						<th style="width: 125px">忽略編號&emsp;</th>
						<th style="width: 400px">已忽略會員&emsp;</th>
						<th></th>
						
					</tr>

					<%@ include file="page3.file"%>

					<c:forEach var="ChatRoomBanListVO" items="${list}"
						begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<div style="margin-top:20px;"></div>
						<tr align='center' valign="middle">
							<td><div
									style="width: 125px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">${ChatRoomBanListVO.banListNo}</div></td>
							<td><div
									style="width: 100px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">${ChatRoomBanListVO.getMemVOByMemNoBaned().memName}</div></td>

							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/chatroom/banListDelete"
									style="margin-bottom: 0px;">
									<input type="submit" value="刪除" style="width: 37px; height: 37px;">
										<input type="hidden" name="memNo" value="${ChatRoomBanListVO.memNo}">
										<input type="hidden" name="memNo_Baned" value="${ChatRoomBanListVO.memNo_Baned}">
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
<%@include file="/frontend/frontfoot.jsp" %>