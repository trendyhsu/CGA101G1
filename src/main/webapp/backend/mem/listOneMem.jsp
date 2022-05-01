<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"  %>
<%@include file="/backend/share.jsp"%>

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO");//存入req的物件
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
<body>
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<div id="bid-content">
		
<table id="table-1">
	<tr><td>
		 <h3>會員資料</h3>
	</td></tr>
</table>

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
			<td>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/ChangeMemStatusServlet" style="margin-bottom: 0px;">
	<input type="hidden" name="memAccount"  value="${memVO.memAccount}">
			<input type="submit" value="修改">
	</FORM>
			</td>
	</tr>
</table>

		</div>
	</section>
</section>
	<!--main content end-->
</body>
</html>