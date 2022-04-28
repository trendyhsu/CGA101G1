<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.memCoupon.model.*"  %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
  table#table-1 {
    width: 450px;
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
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>
</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>優惠券發放</h3></td><td>
	</td></tr>
</table>
<h3>優惠券發放:</h3>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/memCoupon/SendMemCouponServlet" name="form1">
<table>
	<tr>
		<td>發放數量:</td>
		<td><input type="TEXT" name="couponQuantity" size="45" 
			 value="${param.couponQuantity}"/></td><td>${errorMsgs.couponQuantity}</td>
	</tr>
	
	<jsp:useBean id="couponTypeService" scope="page" class="com.couponType.model.CouponTypeService" />
	<tr>
		<td>發放優惠券種類:<font color=red><b>*</b></font></td>
		<td><select size="1" name="couponTypeNo">
			<c:forEach var="couponTypeVO" items="${couponTypeService.showAllCouponType()}">
				<option value="${couponTypeVO.couponTypeNo}" ${(param.couponTypeNo==couponTypeVO.couponTypeNo)? 'selected':'' } >${couponTypeVO.couponName}
			</c:forEach>
		</select></td>
	</tr>

</table>

<br>
<input type="submit" value="發放"></FORM>
</body>
</html>