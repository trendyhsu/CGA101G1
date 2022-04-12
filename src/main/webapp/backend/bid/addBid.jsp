<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/backend/share.jsp"%>
<!DOCTYPE html>
<html>
<head>

</head>
<body>

	<section id="main-content">

		<form action="" method="post" enctype="multipart/form-data"></form>
		<table>
			<tr>
				<td>二手遊戲名稱:</td>
				<td><input type="text" name="bidName" value="馬力歐賽車8" /></td>
			</tr>
			<tr>
				<td>競標商品敘述:</td>
				<td><textArea name="bidProdDescription" id="bidProdDescription"
						rows="3" cols="30"></textArea></td>
			</tr>
			<tr>
				<td>起標價:</td>
				<td><input type="number" name="initialPrice" min="0"
					value="500" /></td>
			</tr>
			<tr>
				<td>最低增額:</td>
				<td><input type="NUMBER" name="bidPriceIncrement" min="0"
					value="70" /></td>
			</tr>
			<tr>
				<td>起標時間:</td>
				<td><input name="bidLaunchedTime" id="startTime" type="text"></td>
			</tr>
			<tr>
				<td>截標時間:</td>
				<td><input name="bidSoldTime" id="endTime" type="text"></td>
			</tr>
			<tr>
				<td><input type="file" name="upfile1"></td>
			</tr>
		</table>
		<input type="submit" value="新增競標商品">
		</FORM>

		<section class="wrapper">

			<div class="row">

				<div class="col-lg-9 main-chart">

					<!-- 內容寫在這裡面 -->

				</div>
				<!-- /col-lg-9 END SECTION MIDDLE -->

			</div>
		</section>
	</section>
	

</body>

</html>