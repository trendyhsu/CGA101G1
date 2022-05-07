<%@page import="com.bidpic.model.BidPicVO"%>
<%@page import="com.bidpic.model.BidPicService"%>
<%@page import="com.bidproduct.model.BidProductService"%>
<%@page import="com.bidproduct.model.BidProductVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/frontend/fronthead.jsp" %>
<%
Integer bidProductNo = Integer.valueOf(request.getParameter("bidProductNo"));
BidProductService bidProductSvc = new BidProductService();
BidProductVO bidProductVO = bidProductSvc.getOneBid(bidProductNo);
BidPicService bidPicSvc = new BidPicService();
List<BidPicVO> bidPicVOs = bidPicSvc.getAllBidPicByBidProductNo(bidProductNo);
pageContext.setAttribute("bidProductVO", bidProductVO);
pageContext.setAttribute("bidPicVOs", bidPicVOs);
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>得標商品結帳</title>
<style type="text/css">
table{
font-weight: bold;
}
td{
width: 120px;
}
#showmain{
display: flex;
}
#checkout{
font-weight: bold;
color: black;
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

			 		<h3>得標商品結帳</h3>

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
		
		<div id="showmain">
			<div id="showmainLeft">
				<table class="showPanel" style="table-layout: fixed; color: black;line-height: 30px; font-size: 1rem;">
					<tr>
						<td>商品資訊</td>
					</tr>
					<tr>
						<td>競標商品編號：</td>
						<td>${bidProductVO.bidProductNo}</td>
					</tr>
					<tr>
						<td>商品名稱：</td>
						<td>${bidProductVO.bidName}</td>
					</tr>
					<tr>
						<td>得標價格：</td>
						<td>$ ${bidProductVO.bidWinnerPrice}</td>
					</tr>
					<tr>
						<td>起標時間：</td>
						<td><fmt:formatDate value="${bidProductVO.bidLaunchedTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
					<tr>
						<td>截標時間：</td>
						<td><fmt:formatDate value="${bidProductVO.bidSoldTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</table>
			</div>

			<div id="showmainRight"style="margin-left: auto;">
				<c:forEach var="bidPicVO" items="${bidPicVOs}">
					<img src="<%=request.getContextPath()%>/bid/bidPicGetOneByProdPicNo?bidProdPicNo=${bidPicVO.bidProdPicNo}" style="width: 160px">
				</c:forEach>
			</div>
		</div>
		
		<div id="checkout">
			<form action="<%=request.getContextPath()%>/bid/bidProductCheckout"id="qqq">
				<div class="mb-3">
					<label for="receiverName"><span class="info">收件人姓名</span></label>
					<input type="text" class="form-control" id="receiverName" name="receiverName"
							placeholder="" value="${memVO.memName}" required>
				</div>
				<input class="js-demeter-tw-zipcode-selector" data-city="#city" data-dist="#dist" placeholder="請輸入郵遞區號" />
				<select id="city" placeholder="請選擇縣市" name="receiverAddressCity" required>
				</select>
				<select id="dist" placeholder="請選擇鄉鎮區" name="receiverAddressDist" required>
				</select>
				<div class="mb-3">
					<label for="receiverAddressDetail"><span class="info">收件人住址</span></label>
					<input type="text" class="form-control" id="receiverName" name="receiverAddressDetail"
							placeholder="" value="${memVO.memAdd}" required>
				</div>
				
				<div class="mb-3">
					<label for="receiverPhone"><span class="info">收件人電話</span></label>
					<input class="form-control" type="text" id="receiverPhone" name="receiverPhone"
							placeholder="" value="${memVO.memMobile}" required>
				</div>
				<div class="mb-3">
					<label for="creditcardNum"><span class="info">信用卡號碼</span></label>
					<input pattern="[0-9]{13,16}" class="form-control" type="text" id="creditcardNum" name="creditcardNum"
						placeholder="${memVO.creditcardNo}" required>
				</div>
				<input type="hidden" name="bidProductNo" value="${bidProductVO.bidProductNo}">

				<button class="btn btn-primary btn-lg btn-block" id="btnConfirm" type="button">確認結帳</button>
				
			</form>
		</div>
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
    <script src="<%=request.getContextPath()%>/frontend/mainCss/assets/js/jquery-3.5.1.min.js"></script>
    <script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>
    <script src="https://cdn.bootcdn.net/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
    <script type="text/javascript"></script>
    
        <script type="text/javascript">
    
    $("#btnConfirm").click(function(){
        swal({
            title: "信用卡授權中",
            text: "正在聯絡信用卡公司 請稍候", 
            icon: "info",
            timer: 2000
        })
        .then(() => {
        	swal("刷卡成功，感謝參與競標！", {
              icon: "success",
              timer: 2000
            })
        })
        .then(() => {
            setTimeout(checkout, 1000);
         })
	    function checkout() {
	    	document.querySelector("#qqq").submit();
		}
    });
    
    
    </script>
</body>
</html>
<%@include file="/frontend/frontfoot.jsp" %>