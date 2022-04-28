<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memCoupon.model.*"  %>

<%
List<MemCouponVO> list = (List<MemCouponVO>)request.getAttribute("list");//存入req的物件
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
		 <h3>這種優惠券有哪些會員擁有</h3>
	</td></tr>
</table>

<table>
	<tr>
		<th>優惠券種類編號</th>
		<th>會員編號(買家)</th>
<!-- 		<th>會員優惠券種類編號</th> -->
		<th>使用狀態</th>
		<th>優惠券時效</th>
<!-- 		<th>刪除此種優惠券</th> -->
	</tr>
<%-- 	<%@ include file="page1.file" %>  --%>
<c:forEach var="memCouponVO" items="${list}" >
<%-- 這行原本在上面 		begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" --%>
		<tr>
			<td>${memCouponVO.couponTypeNo}</td>
			<td>${memCouponVO.memNo}</td>
<%-- 			<td>${memCouponVO.memCouponNo}</td> --%>
			<td>${memCouponVO.couponState}</td>
			<td>${memCouponVO.couponTypeVO.couponDeadline}</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/couponType/DeleteCouponTypeServlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="memCouponNo"  value="${memCouponVO.memCouponNo}"> --%>
<%-- 			   	 <input type="hidden" name="couponTypeNo"  value="${memCouponVO.couponTypeNo}"> --%>
<%-- 				 <input type="hidden" name="memNo"  value="${memCouponVO.memNo}"> --%>
<!-- 			   </FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/memCoupon/DeleteMemCouponServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除此種優惠券">
			   	 <input type="hidden" name="couponTypeNo"  value="${couponTypeNo}">
			   </FORM>
<%-- <%@ include file="page2.file" %> --%>

</body>

</html>