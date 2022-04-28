<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memCoupon.model.*"  %>

<%
	MemCouponService memCouponService = new MemCouponService();
	List<MemCouponVO> list = memCouponService.showAllMemCoupon();
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
		 <h3>已發放給所有會員的優惠券</h3>
	</td></tr>
</table>


<li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/memCoupon/ShowOneCouponServelet" >
        <b>輸入優惠券編號查詢那些會員擁有 (如:13001):</b>
<%--         <input type="hidden" name="couponTypeNo"  value="${memCouponVO.couponTypeNo}"> --%>
        <input type="text" name="couponTypeNo" value="${param.couponTypeNo}"><font color=red>${errorMsgs.couponTypeNo}</font>
        <input type="submit" value="送出">
    </FORM>
</li>
<li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/memCoupon/ShowOneMemCouponServlet" >
        <b>輸入會員編號查詢會員擁有那些優惠券 (如:11001):</b>
        <input type="text" name="memNo" value=""><font color=red>${errorMsgs.memNo}</font>
        <input type="submit" value="送出">
    </FORM>
</li>
  
<table>
	<tr>
<!-- 		<th>會員優惠券種類編號</th> -->
		<th>優惠券種類編號</th>
		<th>會員編號(買家)</th>
		<th>使用狀態</th>
		<th>優惠券時效</th>
<!-- 		<th>刪除優惠券</th> -->
	</tr>
<%-- 	<%@ include file="page1.file" %>  --%>
	<c:forEach var="memCouponVO" items="${list}" >
<%-- 這行原本在上面 		begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" --%>
		<input type="hidden" name="memCouponNo"  value="${memCouponVO.memCouponNo}">
		<input type="hidden" name="couponTypeNo"  value="${memCouponVO.couponTypeNo}">
		<input type="hidden" name="memNo"  value="${memCouponVO.memNo}">
		<tr>
<%-- 			<td>${MemCouponVO.memCouponNo}</td> --%>
			<td>${memCouponVO.couponTypeNo}</td>
			<td>${memCouponVO.memNo}</td>
			<td>${memCouponVO.couponState}</td>
			<td>${memCouponVO.couponTypeVO.couponDeadline}</td>
		</tr>
	</c:forEach>
</table>

<%-- <%@ include file="page2.file" %> --%>

</body>

</html>