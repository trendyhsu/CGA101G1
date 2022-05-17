<%@page import="com.bidproduct.model.BidProductService"%>
<%@page import="com.member.model.MemVO"%>
<%@page import="com.bidproduct.model.BidProductVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/frontend/fronthead.jsp" %>

<%
MemVO memVO = (MemVO)request.getSession().getAttribute("memVO");
Integer memNo = memVO.getMemNo();
BidProductService bidProductSvc = new BidProductService();
List<BidProductVO> list = bidProductSvc.getAllBySellerNo(memNo);
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的上架中競標商品</title>
<style type="text/css">
table{
font-weight: bold;
}
table tr td,th{
border-bottom: solid;
}
th{
background-color: #b2cdcc;
}
#pageNumber, #dataNumber, #pageChange{
float: right;
}
</style>
</head>
<body>
                    <!-- End Profile Menu -->
                    <!-- Content 主要內容區 要修改的部分都塞在這個裡面 -->
					<!-- 內容直接寫在<div class="table-responsive fs-md mb-4">裡面 -->
                    <div class="col-lg-9 col-xxl-9">
                        <div class="table-responsive fs-md mb-4">

	<div id="bid-content">
		
		<table id="table-1">
			<tr>

			 		<h3>我的上架中競標商品</h3>

			</tr>
		</table>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<%-- 成功表列 --%>
		<p style="color: red;">${successMsg}</p>

		<table class="table table-striped table-hover">
		<thead>
			<tr align='center' valign="middle" style="background-color: #b2cdcc;color: black;">
				<th style="width: 10%">競標商品編號</th>
				<th style="width: 15%">商品名稱</th>
				<th>起標價</th>
				<th>最低增額</th>
				<th>起標時間</th>
				<th>截標時間</th>
				<th>得標會員</th>
				<th>得標價</th>
				<th>競標狀態</th>
				<th>商品狀態</th>
				<th>取回</th>
				<th>重新上架</th>
			</tr>
		</thead>
			<%@ include file="page1.file"%>
			<c:forEach var="bidProductVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr align='center' valign="middle" style="color: black;">
					<td>${bidProductVO.bidProductNo}</td>
					<td><a href="<%=request.getContextPath()%>/frontend/bid/listonebid.html?bidProductNo=${bidProductVO.bidProductNo}">${bidProductVO.bidName}</a></td>
					<td>${bidProductVO.initialPrice}</td>
					<td>${bidProductVO.bidPriceIncrement}</td>
					<td style="width:10%; word-wrap: break-word"><fmt:formatDate value="${bidProductVO.bidLaunchedTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td style="width:10%; word-wrap: break-word"><fmt:formatDate value="${bidProductVO.bidSoldTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${bidProductVO.getMemVOByBuyerNo().memName}</td>
					<td>${bidProductVO.bidWinnerPrice}</td>
					<td>
						<c:if test="${bidProductVO.bidState == 0}" var="condition">
							<c:out value="競標中" escapeXml="false"></c:out>
						</c:if> <c:if test="${bidProductVO.bidState == 1}" var="condition">
							<c:out value="截標" escapeXml="false"></c:out>
						</c:if> <c:if test="${bidProductVO.bidState == 2}" var="condition">
							<c:out value="流標" escapeXml="false"></c:out>
						</c:if> <c:if test="${bidProductVO.bidState == 3}" var="condition">
							<c:out value="棄標" escapeXml="false"></c:out>
						</c:if></td>
					<td>
						<c:if test="${bidProductVO.orderState == 0}">
							<c:out value="未出貨" escapeXml="false"></c:out>
						</c:if> <c:if test="${bidProductVO.orderState == 1}">
							<c:out value="訂單處理中" escapeXml="false"></c:out>
						</c:if> <c:if test="${bidProductVO.orderState == 2}">
							<c:out value="已出貨" escapeXml="false"></c:out>
						</c:if> <c:if test="${bidProductVO.orderState == 3}">
							<c:out value="取回處理中" escapeXml="false"></c:out>
						</c:if><c:if test="${bidProductVO.orderState == 4}">
							<c:out value="已重新申請上架" escapeXml="false"></c:out>
						</c:if><c:if test="${bidProductVO.orderState == 5}">
							<c:out value="已收貨" escapeXml="false"></c:out>
						</c:if><c:if test="${bidProductVO.orderState == 6}">
							<c:out value="已撥款" escapeXml="false"></c:out>
						</c:if>
					</td>
					<td>
						<c:if test="${bidProductVO.bidState == 2 || bidProductVO.bidState == 3}" var="condition">
							<c:if test="${bidProductVO.orderState != 3 && bidProductVO.orderState != 4}" var="condition">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/bid/bidProductGetBack"
									style="margin-bottom: 0px;" id="getBackForm${bidProductVO.bidProductNo}">
									<input class="btn btn-success btn-sm getBackBtn" style="margin: 0" type="button" value="取回" id="${bidProductVO.bidProductNo}">
									<input type="hidden"name="bidProductNo" value="${bidProductVO.bidProductNo}">
									<input type="hidden"name="orderState" value="${bidProductVO.orderState}">
								</FORM>
							</c:if>
						</c:if>
					</td>
					<td>
						<c:if test="${bidProductVO.bidState == 2 || bidProductVO.bidState == 3}" var="condition">
							<c:if test="${bidProductVO.orderState != 3 && bidProductVO.orderState != 4}" var="condition">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/bid/bidProductRelist" id="reListForm${bidProductVO.bidProductNo}"
									>
									<input class="btn btn-success btn-sm reListBtn" style="margin: 0" type="submit" value="重新上架" id="${bidProductVO.bidProductNo}">
									<input type="hidden"name="bidProductNo" value="${bidProductVO.bidProductNo}">
									<input type="hidden"name="orderState" value="${bidProductVO.orderState}">
								</FORM>
							</c:if>
						</c:if>
					</td>
				</tr>
			</c:forEach>
			
		</table>
		<%@ include file="page2.file"%>
			<c:if test="${list.size() == 0}" var="condition">
				<div style="text-align: center; color: red; display: block;">
					<p>目前無上架中競標商品</p>
				</div>
			</c:if>
	</div>

                        </div>
					</div>
                    <!-- End Content -->
               	</div>
            </div>
        </div>
        <!--Table -->
    </main>
    <!-- End Main -->
    
<script src="https://cdn.bootcdn.net/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
<script src="<%=request.getContextPath()%>/frontend/mainCss/assets/js/jquery-3.5.1.min.js"></script>
<script>
	// 取回
let returnBtn = $(".getBackBtn");
var targetNum = 0;
if(returnBtn){
	returnBtn.click(function(e){
		targetNum = e.target.id;
		swal({ 
			  title: "確定取回嗎？", 
			  text: "", 
			  type: "warning",
			  showCancelButton: true, 
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "確定取回！", 
			  cancelButtonText: "取消取回！",
			  closeOnConfirm: false, 
			  closeOnCancel: false  
			}).then(
			function(isConfirm){ 
			  if (isConfirm) {
			    swal("取回申請成功！", "請靜待商品寄出。","success")
			    .then(() => {
				setTimeout(returnGame, 100);
				function returnGame(){
					let getBackFormName = "#getBackForm"+targetNum;
					document.querySelector(getBackFormName).submit();
				}
			 }); 
			  } else { 
			    swal("取回取消！", "商品狀態未更新",
			"error"); 
			  } 
			});
	})
}
</script>
</body>
</html>
<%@include file="/frontend/frontfoot.jsp" %>