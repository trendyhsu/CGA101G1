<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"  %>

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
<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<!-- <h4>此頁練習採用 EL 的寫法取值:</h4> -->
<table id="table-1">
	<tr><td>
		 <h3>所有會員資料</h3>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.key} : ${message.value}</li>
		</c:forEach>
	</ul>
</c:if>
<!-- 單獨搜尋會員 -->
<li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/GetOneMemServlet" >
        <b>輸入會員手機查詢 (如:0912345678):</b>
        <input type="text" name="memMobile" value=""><font color=red>${errorMsgs.memNo}</font>
        <input type="hidden" name="action" value="getOne_By_Mobile">
        <input type="submit" value="送出">
    </FORM>
</li>
  
 <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/GetOneMemServlet" >
        <b>輸入會員帳號查詢 (如:abc123):</b>
        <input type="text" name="memAccount" value=""><font color=red>${errorMsgs.empno}</font>
        <input type="hidden" name="action" value="getOne_By_MemAccount">
        <input type="submit" value="送出">
    </FORM>
 </li>
<table>
	<tr>
		<th>會員編號</th>
		<th>會員帳號</th>
		<th>帳號狀態</th>
		<th>驗證狀態</th>
		<th>會員驗證完成時間</th>
		<th>姓名</th>
		<th>電話</th>
		<th>地址縣市</th>
		<th>地址區域</th>
		<th>地址</th>
		<th>E-Mail</th>
		<th>生日</th>
		<th>加入時間</th>
		<th>賣家功能狀態</th>
		<th>修改送出</th>
	</tr>
<%-- 	<%@ include file="page1.file" %>  --%>
	<c:forEach var="memVO" items="${list}" >
<%-- 這行原本在上面 		begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" --%>
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
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>