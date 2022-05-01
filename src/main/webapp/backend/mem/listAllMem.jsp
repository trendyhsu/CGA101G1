<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"  %>
<%@include file="/backend/share.jsp"%>

<%
    MemService memSvc = new MemService();
	List<MemVO> list = memSvc.listAllMem();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
th{
	border-bottom-style:solid;
	background-color: #b2cdcc;
	vertical-align: middle;
	text-align: center;
}

td{
	border-bottom-style:solid;
	  padding: 5px;
    text-align: center;
}

h3{
font-weight: bold;
color: #547492;
}

#pageNumber, #dataNumber, #pageChange{
float: right;
}
</style>
</head>
<body bgcolor='white'>

<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<div id="bid-content">

<!-- <h4>此頁練習採用 EL 的寫法取值:</h4> -->
<table id="table-1">
	<tr><td>
		 <h3>所有會員資料</h3>
	</td></tr>
</table>

<%-- <%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message.key} : ${message.value}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>
<!-- 單獨搜尋會員 -->
<li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/GetOneMemServlet" >
        <b>輸入會員手機查詢 (如:0912345678):</b>
        <input type="text" name="memMobile" value="">
        <input type="hidden" name="action" value="getOne_By_Mobile">
        <input type="submit" value="送出"><font color=red>${errorMsgs.memMobile}</font>
    </FORM>
</li>
  
 <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/GetOneMemServlet" >
        <b>輸入會員帳號查詢 (如:abc123):</b>
        <input type="text" name="memAccount" value="">
        <input type="hidden" name="action" value="getOne_By_MemAccount">
        <input type="submit" value="送出"><font color=red>${errorMsgs.memAccount}</font>
    </FORM>
 </li>
<table class="showPanel" style="table-layout: fixed; color: black;">
	<tr align='center' valign="middle">
		<th>會員編號</th>
		<th>會員帳號</th>
		<th>帳號狀態</th>
		<th>驗證狀態</th>
		<th>會員驗證時間</th>
		<th>姓名</th>
		<th>電話</th>
		<th>地址縣市</th>
		<th>地址區域</th>
		<th>地址</th>
		<th>E-Mail</th>
		<th>生日</th>
		<th>加入時間</th>
		<th>賣家功能</th>
		<th>修改送出</th>
	</tr>

	<%@ include file="page1.file" %> 
	<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/ChangeMemStatusServlet" style="margin-bottom: 0px;">
		<input type="hidden" name="memAccount"  value="${memVO.memAccount}">
		<tr>
			<td>${memVO.memNo}</td>
			<td>${memVO.memAccount}</td>
			<td>
				<select name="status">
					<option value="0" ${(memVO.memStatus == 0)? 'selected' : '' } >停權
					<option value="1" ${(memVO.memStatus == 1)? 'selected' : '' } >正常
				</select>
			</td>
			<td>
				${memVO.memVrfed}
			</td>
			<td>${memVO.memNoVrftime}</td>
			<td>${memVO.memName}</td>
			<td>${memVO.memMobile}</td>
			<td>${memVO.memCity}</td>
			<td>${memVO.memDist}</td>
			<td>${memVO.memAdd}</td>
			<td>${memVO.memEmail}</td>
			<td>${memVO.memBirth}</td>
			<td>${memVO.memJoinTime}</td>
			<td>${memVO.userStatus}</td>
			<td><input type="submit" value="修改"></td>
		</tr>
	</FORM>
	</c:forEach>
</table>
<%@ include file="page2.file" %>


		</div>
	</section>
</section>
	<!--main content end-->
</body>
</html>