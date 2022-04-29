<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
</head>
<body>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/NewPasswordServlet" name="form1">

			<input type="hidden" name="memEmail" id="memEmail" />
 <div>
        <div>
            <label for="memNewPassword">請輸入您的新密碼: </label>
            <input type="password" name="memNewPassword" id="memNewPassword" />
        </div>
        <label>${errorMsgs.memNewPassword}</label>
         <div>
            <label for="confNewPassword">新密碼確認: </label>
            <input type="password" name="confNewPassword" id="confNewPassword" />
        </div>
        <label>${errorMsgs.confNewPassword}</label>
 </div>
		<input type="hidden" name="action" value="update">
		<input type="submit" value="送出">
</FORM>


</body>
</html>