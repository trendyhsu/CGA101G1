<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/backend/share.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 匯入java -->
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productPic.model.*"%>

<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //EmpServlet.java (Concroller) 存入req的orderVO物件 (包括幫忙取出的orderVO, 也包括輸入資料錯誤時的orderVO物件)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
	crossorigin="anonymous"></script>
<title>POP.Game ServerSide</title>

<!-- **********************************************************************************************************************************************************
      MAIN CONTENT 內容寫在這裡面
      *********************************************************************************************************************************************************** -->
<!--main content start-->
<section id="main-content">
	<section class="wrapper">

		<div class="row">

			<div class="col-lg-9 main-chart">
				<h3>商品內容更新成功!!資料如下：</h3>



				<table class="table table-striped table-hover">
					<tr>
						<td style="width: 10%">遊戲編號:</td>
						<td>${productVO.productNo}</td>
					</tr>
					<tr>
						<td>遊戲名稱:</td>
						<td>${productVO.productName}</td>
					</tr>
					<tr>
						<td>商品金額:</td>
						<td>${productVO.productPrice}</td>
					</tr>
					<tr>
						<td>遊戲種類:</td>
						<td>${productVO.getOneGameType().gameTypeName}</td>
					</tr>
					<tr>
						<td>遊戲平台:</td>
						<td>${productVO.getOneGamePlatformType().gamePlatformName}</td>
					</tr>
					<tr>
						<td>遊戲公司:</td>
						<td>${productVO.getOneGameCompanyVO().gameCompanyName}</td>
					</tr>
					<tr>
						<td>upcNum:</td>
						<td>${productVO.upcNum}</td>
					</tr>
					<tr>
						<td>內容描述:</td>
						<td>${productVO.itemProdDescription}</td>
					</tr>
					<tr>
						<td>上架狀態:</td>
						<td><select size="1" name="PickupMethod" disabled>
								<option value="0"
									<c:if test="${productVO.productState == 0}"><c:out value="selected"></c:out></c:if>>未上架</option>
								<option value="1"
									<c:if test="${productVO.productState == 1}"><c:out value="selected"></c:out></c:if>>上架中</option>
						</select></td>
					</tr>
					<tr>
					<tr>
						<td>商品圖:</td>
						<td><c:forEach var="productPicVO"
								items="${productVO.getPicsNo()}">
								<img alt=""
									src="<%=request.getContextPath()%>/product/showPicByPicNo?productPicNo=${productPicVO.productPicNo}"
									width="200">
							</c:forEach>
					    </td>

					</tr>



				</table>
				<a href="/CGA101G1/backend/product/productMod.jsp"
					class="btn btn-secondary btn-lg" role="button">繼續修改其他產品</a>
			</div>
			<!-- /col-lg-9 END SECTION MIDDLE -->

		</div>
	</section>
</section>

<!--main content end-->

</section>
</body>