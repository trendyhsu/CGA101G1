<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.couponType.model.*"  %>

<%
	CouponTypeService couponTypeSvc = new CouponTypeService();
	List<CouponTypeVO> list = couponTypeSvc.showAllCouponType();
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
		 <h3>所有優惠券</h3>
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
<!-- 搜尋優惠券 -->
<li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/couponType/GetOneCouponType" >
        <b>輸入優惠券編號查詢 (如:13001):</b>
        <input type="text" name="couponTypeNo" value=""><font color=red>${errorMsgs.couponTypeNo}</font>
        <input type="submit" value="送出">
    </FORM>
</li>
  
<table>
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
<%-- 	<%@ include file="page1.file" %>  --%>
	<c:forEach var="couponTypeVO" items="${list}" >
<%-- 這行原本在上面 		begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" --%>
		<input type="hidden" name="couponTypeNo"  value="${couponTypeVO.couponTypeNo}">
		<tr>
			<td>${couponTypeVO.couponTypeNo}</td>
			<td>${couponTypeVO.couponName}</td>
			<td>${couponTypeVO.discountPrice}</td>
			<td>${couponTypeVO.couponDeadline}</td>
			<td>${couponTypeVO.couponQuantity}</td>
			<td>${couponTypeVO.couponDescription}</td> 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/couponType/GetWhichOneUpdate" style="margin-bottom: 0px;">
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
	</c:forEach>
</table>

<%-- <%@ include file="page2.file" %> --%>

</body>
</html>