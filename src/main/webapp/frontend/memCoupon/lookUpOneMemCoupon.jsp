<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memCoupon.model.*"  %>
<%@ page import="com.member.model.*"  %>
<%@ page import="java.util.*"  %>
<%@include file="/frontend/fronthead.jsp" %>

<%
	MemVO memVO=(MemVO)session.getAttribute("memVO");
	MemCouponService memCouponService = new MemCouponService();
	List<MemCouponVO> list = memCouponService.listOneMemCoupon(memVO.getMemNo());
    pageContext.setAttribute("list",list);
%>    
        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
  table#table-1 {
	background-color: #f6f9fc;
    border: 1px solid #aeb4be;
    text-align: center;
  }
  h3{
  color: cadetblue;
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
    border: 1px solid #aeb4be;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

				<!-- 內容直接寫在<div class="table-responsive fs-md mb-4">裡面 -->
                    <div class="col-lg-9 col-xxl-9">
                        <div class="table-responsive fs-md mb-4">
                        
<!-- <h4>此頁練習採用 EL 的寫法取值:</h4> -->
<table id="table-1">
	<tr><td>
		 <h3>${memVO.memName}的優惠券 (*´∀`)~♥</h3>
	</td></tr>
</table>

<table>
	<tr>
<!-- 		<th>會員編號(買家)</th> -->
<!-- 		<th>會員優惠券種類編號</th> -->
<!-- 		<th>優惠券種類編號</th> -->
		<th>優惠券名稱</th>
		<th>可折抵金額</th>
		<th>使用狀態</th>
		<th>優惠券時效</th>
	</tr>
<%-- 	<%@ include file="page1.file" %>  --%>
<c:forEach var="memCouponVO" items="${list}" >
<%-- 這行原本在上面 		begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" --%>
		
		<c:if  test="${memCouponVO.couponState == 0 }" var="true">
		<tr>
<%-- 			<td>${memCouponVO.memNo}</td> --%>
<%-- 			<td>${memCouponVO.memCouponNo}</td> --%>
			<td>${memCouponVO.couponTypeVO.couponName}</td>
			<td>${memCouponVO.couponTypeVO.discountPrice}</td>
			<td>
			<c:if test="${memCouponVO.couponState == 0 }" var="true">
			未使用
			</c:if>
			<c:if test="${memCouponVO.couponState == 1}" var="true">
			已使用
			</c:if>
			</td>
			<td>${memCouponVO.couponTypeVO.couponDeadline}</td>			
		</tr>
		</c:if>
	</c:forEach>
<%-- 	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/memCoupon/DeleteMemCouponServlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="memCouponNo"  value="${memCouponVO.memCouponNo}"> --%>
<%-- 			   	 <input type="hidden" name="couponTypeNo"  value="${memCouponVO.couponTypeNo}"> --%>
<%-- 				 <input type="hidden" name="memNo"  value="${memCouponVO.memNo}"> --%>
<!-- 	</FORM> -->

<%-- 		<td><c:if test="${memCouponVO.couponState == 0}" var="condition"> --%>
<%-- 				<c:out value="未使用" escapeXml="false"></c:out> --%>
<%-- 			</c:if> <c:if test="${memCouponVO.couponState == 1}" var="condition"> --%>
<%-- 				<c:out value="已使用" escapeXml="false"></c:out> --%>
<%-- 			</c:if></td> --%>
	</table>
<%-- <%@ include file="page1.file"%> --%>
<%-- <%@ include file="page2.file" %> --%>

</div>
					</div>
                    <!-- End Content -->
</body>

</html>
<%@include file="/frontend/frontfoot.jsp" %>