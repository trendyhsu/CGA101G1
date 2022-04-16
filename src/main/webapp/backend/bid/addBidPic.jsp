<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/backend/share.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上傳競標圖片測試</title>
</head>
<body>
		<section id="main-content">
			<section class="wrapper">

				<div class="row">

					<div class="col-lg-9 main-chart">
					
					
	<form action="<%=request.getContextPath()%>/BidPicGetOne" method="post" enctype="multipart/form-data">
	<input type="text" value="31001" name="bidProductNo">
	<input type="file" name="file1">
	<input type="submit" value="上傳圖片">	
	</form>

					</div>
					<!-- /col-lg-9 END SECTION MIDDLE -->

				</div>
			</section>
		</section>

	
</body>
</html>