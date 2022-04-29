<%@page import="com.manager.model.ManagerVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>POP.Game 後臺管理系統</title>

    <!-- font awesome cdn link -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/backend/assets/weiwei/css/allStyle.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/backend/assets/weiwei/css/headerFunctionStyle.css">
    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/backend/assets/weiwei/css/loginStyle.css">
</head>

<body>


    <!--Login starts-->

    <section id="login-form">


        <div class="form-box">
            <div class="button-box">
                <div id="btn"></div>
                <button type="button" class="toggle-btn" onclick="login()">login</button>
                <button type="button" class="toggle-btn" onclick="register()">register</button>
            </div>

            <form id="login" class="input-group">
                <div class="input-div">
                    <div class="icon-div"><i class="fas fa-solid fa-user-astronaut"></i></div>
                    <input type="text" class="input-field" placeholder="Username" required>
                </div>
                <div class="input-div">
                    <div class="icon-div"> <i class="fas fa-solid fa-lock"></i></div>
                    <input type="password" class="input-field" placeholder="Password" required>
                </div>

                <div class="others">
                    <input type="checkbox" class="check-box">
                    <span>Remeber Password</span>
                </div>

                <button type="submit" class="submit-btn">Log in</button>
            </form>

            <form id="register" class="input-group">
                <div class="input-div">
                    <div class="icon-div"><i class="fas fa-solid fa-user-astronaut"></i></div>

                    <input type="text" class="input-field" placeholder="Account" required>
                </div>
                <div class="input-div">
                    <div class="icon-div"><i class="fas fa-solid fa-lock"></i></div>
                    <input type="password" class="input-field" placeholder="Password" required>
                </div>

                <div class="input-div">
                    <div class="icon-div"><i class="fa-solid fa-id-card"></i></div>

                    <input type="text" class="input-field" placeholder="Name" required>
                </div>

                <!-- <div class="others">
                    <input type="checkbox" class="check-box">
                    <span>I agree to the terms & conditions</span>
                </div> -->

                <button type="submit" class="submit-btn">Register</button>
            </form>

        </div>

    </section>


    <!-- Login ends -->



    <script src="<%=request.getContextPath()%>/backend/assets/weiwei/js/loginScript.js"></script>
</body>

</html>