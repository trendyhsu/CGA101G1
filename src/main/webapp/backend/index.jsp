<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/backend/share.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>POP.Game ServerSide</title>

		<!-- **********************************************************************************************************************************************************
      MAIN CONTENT 內容寫在這裡面
      *********************************************************************************************************************************************************** -->
		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">


					<div class="col-lg-9 main-chart">
						<div id="mainBackend" style="font-size: 3rem; text-align: center;">
						管理者您好，歡迎來到 POP GAME 管理後臺						
						</div>
						
					</div>
					<div>
					${message}
					</div>
					<!-- /col-lg-9 END SECTION MIDDLE -->

		</section>

		<!--main content end-->

	</section>

</body>

</html>