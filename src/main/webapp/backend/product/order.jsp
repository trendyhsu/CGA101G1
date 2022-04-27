<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/backend/share.jsp"%>
<!-- 匯入java -->
<%@ page import="java.util.*"%>
<%@ page import="com.order.model.*"%>
<%
OrderService orderService = new OrderService();
List<OrderVO> list = orderService.findAllOrders();
pageContext.setAttribute("list", list);
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
				我是首頁
				<table class="table table-striped table-hover">
					<tr>
						<th>訂單編號</th>
						<th>會員編號</th>
						<th>會員優惠券編號</th>
						<th>總價</th>
						<th>下單日期時間</th>
						<th>訂單狀態</th>
						<th>收件人姓名</th>
						<th>收件人地址</th>
						<th>收件人電話</th>
						<th>修改訂單</th>
						<th>查看訂單明細</th>
					</tr>
					<%@ include file="page1.file"%>
					<c:forEach var="orderVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">

						<tr>
							<td>${orderVO.orderNo}</td>
							<td>${orderVO.memNo}</td>
							<td>${orderVO.memCouponNo == null ? '沒有使用優惠券' : orderVO.memCouponNo}</td>
							<td>${orderVO.orderTotalPrice}</td>
							<td><fmt:formatDate value="${(orderVO.tranTime)}"
									pattern="yyyy.MM.dd  '-' HH:mm:ss" /></td>
							<td>${orderVO.orderState}</td>
							<td>${orderVO.receiverName}</td>
							<td>${orderVO.receiverAddress}</td>
							<td>${orderVO.receiverPhone}</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/product/turnModOrder"
									style="margin-bottom: 0px;">
									<input type="submit" value="修改"> <input type="hidden"
										name="orderNo" value="${orderVO.orderNo}"> <input
										type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/emp/emp.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="刪除"> <input type="hidden"
										name="orderNo" value="${orderVO.orderNo}"> <input
										type="hidden" name="action" value="delete">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="page2.file"%>
			</div>
			<!-- /col-lg-9 END SECTION MIDDLE -->

		</div>
	</section>
</section>

<!--main content end-->

</section>
</body>

</html>