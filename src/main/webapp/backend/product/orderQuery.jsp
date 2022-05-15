<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/backend/share.jsp"%>
<!-- 匯入java -->
<%@ page import="java.util.*"%>
<%@ page import="com.order.model.*"%>
<%@ page import="com.orderdetail.model.*"%>
<%


List<OrderVO> list = (List<OrderVO>)(request.getSession().getAttribute("CompositeQuery"));
pageContext.setAttribute("list", list);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<title>POP.Game ServerSide</title>

<!-- **********************************************************************************************************************************************************
      MAIN CONTENT 內容寫在這裡面
      *********************************************************************************************************************************************************** -->
<!--main content start-->
<section id="main-content">
	<section class="wrapper">

		<div class="row" style="width: 100%">
			<div class="accordion" id="accordionExample">
				<div class="col-lg-9 main-chart" style="width: 100%">
					<h1>訂單查詢結果</h1>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/orders/showOrdersBySearch"
						name="form1">
						<div style="text-align: right;" class="row">
							<div class="col">
								<h3>
									<span style="color: blue">訂單查詢:</span>
								</h3>
							</div>
							<div class="col">
								<b>訂單編號:</b> <input type="text" name="orderNo" value=""
									placeholder="24001">
							</div>
							<input type="text" name="memNo" value="" hidden>
							<div class="col" style="text-align: center;">
								<b>會員姓名:</b> <input type="text" name="memName" value=""
									placeholder="請填入完整性名">
							</div>
							<div class="col" style="text-align: center;">
								<b>訂單狀態:</b>
								<select name="orderState" id="orderState">
								<option value="">請選擇訂單狀態</option>
								<option value="0">未出貨</option>
								<option value="1">已出貨</option>
								<option value="2">完成訂單</option>
								<option value="3">退貨</option>
								<option value="4">作廢</option>
								</select>
							</div>
							<div class="col">
								<b>收件人姓名:</b> <input type="text" name="receiverName" value=""
									placeholder="可填入關鍵字"> <input type="submit" value="開始搜尋"
									class="btn btn-outline-secondary"> <input type="hidden"
									name="action" value="CompositeQuery">
							</div>
						</div>
					</FORM>

					<%@ include file="page1.file"%>
					<c:forEach var="orderVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						<table class="table table-striped table-hover">
							<tr>
								<th style="width: 6%">訂單編號</th>
								<th style="width: 6%">會員姓名</th>
								<th style="width: 10%">會員優惠券編號</th>
								<th  style="width: 6%">總價</th>
								<th style="width: 15%">下單日期時間</th>
								<th style="width: 6%">訂單狀態</th>
								<th style="width: 6%">收件人姓名</th>
								<th style="width: 20%">收件人地址</th>
								<th style="width: 6%">收件人電話</th>
								<th>修改訂單</th>
								<th>查看訂單明細</th>
							</tr>

							<tr>
								<td>${orderVO.orderNo}</td>
								<td>${orderVO.getMemVObyMemNo().memName}</td>
								<td>${orderVO.memCouponNo == 0 ? '沒有使用優惠券' : orderVO.memCouponNo}</td>
								<td><span class="badge bg-info m-0">${orderVO.orderTotalPrice}</td>
								<td><fmt:formatDate value="${(orderVO.tranTime)}"
										pattern="yyyy.MM.dd  '-' HH:mm:ss" /></td>
								<td><span class="badge bg-info m-0"><c:if
											test="${orderVO.orderState == 0}" var="condition">
											<c:out value="未出貨" escapeXml="false"></c:out>
										</c:if> <c:if test="${orderVO.orderState == 1}" var="condition">
											<c:out value="已出貨" escapeXml="false"></c:out>
										</c:if> <c:if test="${orderVO.orderState == 2}" var="condition">
											<c:out value="完成訂單" escapeXml="false"></c:out>
										</c:if> <c:if test="${orderVO.orderState == 3}" var="condition">
											<c:out value="退貨" escapeXml="false"></c:out>
										</c:if> <c:if test="${orderVO.orderState == 4}" var="condition">
											<c:out value="作廢" escapeXml="false"></c:out>
										</c:if></td>

								<td>${orderVO.receiverName}</td>
								<td>${orderVO.receiverAddress}</td>
								<td>${orderVO.receiverPhone}</td>
								<td><c:if test="${orderVO.orderState != 2 && orderVO.orderState != 4}" var="condition">
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/product/turnModOrder"
											style="margin-bottom: 0px;">
											<input type="submit" value="修改" type="button"
												class="btn btn-secondary"> <input type="hidden"
												name="orderNo" value="${orderVO.orderNo}"> <input
												type="hidden" name="action" value="getOne_For_Update">
										</FORM>
									</c:if></td>
								<td>
									<button class="btn btn-secondary" type="button"
										data-bs-toggle="collapse"
										data-bs-target="#collapseTwo${orderVO.orderNo}"
										aria-expanded="false"
										aria-controls="collapseTwo${orderVO.orderNo}">展開</button>
								</td>
							</tr>
						</table>

						<div class="accordion-item">
							<div style="width: 63%;" id="collapseTwo${orderVO.orderNo}"
								class="accordion-collapse collapse" aria-labelledby="headingTwo"
								data-bs-parent="#accordionExample">
								<table class="table table-bordered table-hover mb-0">
									<thead class="text-700 bg-gray-200">
										<tr>
											<th class="fw-600">遊戲編號</th>
											<th class="fw-600">遊戲名稱</th>
											<th class="fw-600">數量</th>
											<th class="fw-600">總價</th>
											<th class="fw-600">遊戲評論</th>
											<th class="fw-600">評論星星數</th>
										</tr>
									</thead>
									<c:forEach var="orderDetailVO"
										items="${orderVO.getAllDetailByOrderNo(orderVO.getOrderNo())}"
										begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
										<tbody>
											<tr>
												<td class="p-3">${orderDetailVO.productNo}</td>
												<td class="p-3">${orderDetailVO.getProductVO(orderDetailVO.productNo).productName}</td>
												<td class="p-3">${orderDetailVO.productSales}</td>
												<td class="p-3"><span class="badge bg-info m-0">${orderDetailVO.productTotalPrice}</span>
												</td>
												<td class="p-3">${orderDetailVO.commentCotent== null ? '尚未評論' : orderDetailVO.commentCotent}</td>
												<td class="p-3">${orderDetailVO.commentStar== null ? '尚未評論' : orderDetailVO.commentStar}</td>
											</tr>
										</tbody>
									</c:forEach>
								</table>
							</div>
						</div>

					</c:forEach>
					<%@ include file="page2.file"%>
				</div>
			</div>



			<!-- /col-lg-9 END SECTION MIDDLE -->

		</div>
	</section>
</section>

<!--main content end-->

</section>
</body>

</html>