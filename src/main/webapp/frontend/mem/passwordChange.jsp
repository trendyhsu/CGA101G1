<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@include file="/frontend/fronthead.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>
<div id=passwordChange>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/MemPasswordEditServlet" name="form1">

 <div>
        <div>
            <label for="oldPassword">舊密碼: </label> <input type="text" name="oldPassword" id="oldPassword" />
        </div>
        <div>
            <label for="newPassword">新密碼: </label> <input type="password" name="newPassword" id="newPassword" />
        </div>
        <div>
            <label for="conNewPassword">確認新密碼: </label> <input type="password" name="conNewPassword" id="conNewPassword" />
        </div>
     
        <div id="errorMsgs" class="error"></div>
 </div>
    
<input type="hidden" name="action" value="update">
<input type="submit" value="送出"></FORM>
</div>
</body>
</html>
<%@include file="/frontend/frontfoot.jsp" %>