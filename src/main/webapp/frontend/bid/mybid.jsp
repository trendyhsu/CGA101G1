<%@page import="com.bidrecord.model.BidRecordVO"%>
<%@page import="com.member.model.MemVO"%>
<%@page import="com.bidproduct.model.BidProductVO"%>
<%@page import="java.util.List"%>
<%@page import="com.bidproduct.model.BidProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/frontend/fronthead.jsp" %>

<%
MemVO memVO = (MemVO)request.getSession().getAttribute("memVO");
Integer memNo = memVO.getMemNo();
List<BidProductVO> list = (List<BidProductVO>)request.getAttribute("bidProductVOs");
List<BidRecordVO> bidRecordVOByProductNos = (List<BidRecordVO>)request.getAttribute("bidRecordVOByProductNos");

if(list == null){
	list = (List<BidProductVO>)request.getSession().getAttribute("bidProductVOs");
}
if(bidRecordVOByProductNos == null){
	bidRecordVOByProductNos = (List<BidRecordVO>)request.getSession().getAttribute("bidRecordVOByProductNos");
}
request.getSession().setAttribute("bidProductVOs", list);
request.getSession().setAttribute("bidRecordVOByProductNos", bidRecordVOByProductNos);
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出價商品追蹤</title>
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

			 		<h3>我的競標商品</h3>

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

		<table class="table table-striped table-hover">
		<thead>
			<tr align='center' valign="middle" style="background-color: #b2cdcc;color: black;">
				<th style="width: 10%">競標商品編號</th>
				<th style="width: 20%">商品名稱</th>
				<th>賣家</th>
				<th>起標價</th>
				<th>最低增額</th>
				<th>最高出價</th>
				<th>起標時間</th>
				<th>截標時間</th>
				<th style="width: 10%">競標狀態</th>
			</tr>
		
		</thead>
			<%@ include file="page1.file"%>
			<c:forEach var="bidProductVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr align='center' valign="middle" style="color: black;">
					<td>${bidProductVO.bidProductNo}</td>
					<td><a href="<%=request.getContextPath()%>/frontend/bid/listonebid.html?bidProductNo=${bidProductVO.bidProductNo}">${bidProductVO.bidName}</a></td>
					<td>${bidProductVO.getMemVOBySellerNo().memName}</td>
					<td>${bidProductVO.initialPrice}</td>
					<td>${bidProductVO.bidPriceIncrement}</td>
					<td>
						<c:forEach var="bidRecordVO" items="${bidRecordVOByProductNos}">
                    		<c:if test="${bidProductVO.bidProductNo==bidRecordVO.bidProductNo}">
	                    		${bidRecordVO.bidPrice}
                    		</c:if>
                		</c:forEach>
					</td>
					<td style="width:10%; word-wrap: break-word"><fmt:formatDate value="${bidProductVO.bidLaunchedTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td style="width:10%; word-wrap: break-word"><fmt:formatDate value="${bidProductVO.bidSoldTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><c:if test="${bidProductVO.bidState == 0}" var="condition">
							<c:out value="競標中" escapeXml="false"></c:out>
						</c:if> <c:if test="${bidProductVO.bidState == 1}" var="condition">
							<c:out value="截標" escapeXml="false"></c:out>
						</c:if> <c:if test="${bidProductVO.bidState == 2}" var="condition">
							<c:out value="流標" escapeXml="false"></c:out>
						</c:if> <c:if test="${bidProductVO.bidState == 3}" var="condition">
							<c:out value="棄標" escapeXml="false"></c:out>
						</c:if></td>

				</tr>
			</c:forEach>
			
		</table>
		<%@ include file="page2.file"%>
			<c:if test="${list.size() == 0}" var="condition">
				<div style="text-align: center; color: red; display: block;">
					<p>目前無出價商品</p>
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
</body>
</html>
<%@include file="/frontend/frontfoot.jsp" %>