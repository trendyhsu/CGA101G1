<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"  %>

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO");//存入req的物件
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
	width: 600px;
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
<body>

<table id="table-1">
	<tr><td>
		 <h3>會員資料</h3>
	</td></tr>
</table>

<table>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/ChangeMemStatus" style="margin-bottom: 0px;">
	<input type="hidden" name="memAccount"  value="${memVO.memAccount}">
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
</table>


</body>
</html>