<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.couponType.model.*"  %>

<%
CouponTypeVO couponTypeVO = (CouponTypeVO) request.getAttribute("couponTypeVO");//存入req的物件
%>

<%@include file="/backend/share.jsp"%>

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
		 <h3>優惠券</h3>
	</td></tr>
</table>

<table>
	<input type="hidden" name="couponName"  value="${couponTypeVO.couponName}">
	<tr>
		<th>優惠券種類編號</th>
		<th>優惠券名稱</th>
		<th>優惠券折價價格</th>
		<th>優惠券時效</th>
		<th>優惠券數量</th>
		<th>優惠券描述</th>
		<th>更改優惠券</th>
		<th>刪除優惠券</th>
	</tr>
		<input type="hidden" name="couponTypeNo"  value="${couponTypeVO.couponTypeNo}">
		<tr>
			<td>${couponTypeVO.couponTypeNo}</td>
			<td>${couponTypeVO.couponName}</td>
			<td>${couponTypeVO.discountPrice}</td>
			<td>${couponTypeVO.couponDeadline}</td>
			<td>${couponTypeVO.couponQuantity}</td>
			<td>${couponTypeVO.couponDescription}</td> 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/couponType/GetWhichOneUpdateServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="couponTypeNo"  value="${couponTypeVO.couponTypeNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/couponType/DeleteCouponTypeServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="couponTypeNo"  value="${couponTypeVO.couponTypeNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
</table>


</body>
</html>