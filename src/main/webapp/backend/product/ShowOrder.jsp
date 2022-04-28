<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/backend/share.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 匯入java -->
<%@ page import="java.util.*"%>
<%@ page import="com.order.model.OrderVO"%>
<%@ page import="com.order.model.*"%>
<%
OrderVO orderVO = (OrderVO) request.getAttribute("orderVO"); //EmpServlet.java (Concroller) 存入req的orderVO物件 (包括幫忙取出的orderVO, 也包括輸入資料錯誤時的orderVO物件)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
<title>POP.Game ServerSide</title>

<!-- **********************************************************************************************************************************************************
      MAIN CONTENT 內容寫在這裡面
      *********************************************************************************************************************************************************** -->
<!--main content start-->
<section id="main-content">
	<section class="wrapper">

		<div class="row">

			<div class="col-lg-9 main-chart">
				<h3>訂單更新成功!!資料如下：</h3>


				<table class="table table-striped table-hover">
					<tr>
						<td>訂單編號:<font color=red><b>*</b></font></td>
						<td>${orderVO.orderNo}</td>
					</tr>
					<tr>
						<td>訂購金額:</td>
						<td>${orderVO.orderTotalPrice}</td>
					</tr>
					<tr>
						<td>訂購日期:</td>
						<td><fmt:formatDate value="${(orderVO.tranTime)}"
								pattern="yyyy.MM.dd  '-' HH:mm:ss" /></td>
					</tr>
					<tr>
						<td>訂單狀態</td>
						<td>${orderVO.orderState}</td>
					</tr>
					<tr>
						<td>收件人姓名:</td>
						<td>${orderVO.receiverName}</td>
					</tr>
					<tr>
						<td>收件人地址:</td>
						<td>${orderVO.receiverAddress}</td>
					</tr>
					<tr>
						<td>收件人電話:</td>
						<td>${orderVO.receiverPhone}</td>
					</tr>
					<tr>
						<td>取貨方式:</td>
						<td><select size="1" name="PickupMethod" disabled>
								<option value="0"
									<c:if test="${orderVO.pickupMethod == 0}"><c:out value="selected"></c:out></c:if>>宅配</option>
								<option value="1"
									<c:if test="${orderVO.pickupMethod == 1}"><c:out value="selected"></c:out></c:if>>超商取貨</option>
						</select></td>
					</tr>
					<tr>
						<td>物流編號:</td>
						<td>${orderVO.trackingNum}</td>
					</tr>

				</table>
<a href="/CGA101G1/backend/product/order.jsp" class="btn btn-secondary btn-lg" role="button" >繼續修改其他訂單</a>
			</div>
			<!-- /col-lg-9 END SECTION MIDDLE -->

		</div>
	</section>
</section>

<!--main content end-->

</section>
</body>