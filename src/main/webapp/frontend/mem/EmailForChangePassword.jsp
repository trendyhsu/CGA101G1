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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem/SendMailForgetPasswordServlet" name="form1">

 <div>
        <div>
            <label for="memEmail">請輸入您的信箱: </label> <input type="text" name=memEmail id="memEmail" />
        </div>
       
 </div>
    
<input type="hidden" name="action" value="update">
<input type="submit" value="送出"></FORM>
<div>${errorMsgs.memEmail}</div>

</body>
</html>