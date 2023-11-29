<%-- 
    Document   : signUp
    Created on : May 27, 2023, 1:06:46 AM
    Author     : 84834
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Durian Store | Sign Up</title>
        <!--css-->
        <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>        
        <link rel="stylesheet" href="styles/signup.css" type="text/css"/>
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&family=Shadows+Into+Light&display=swap" rel="stylesheet" />
        <!-- Favicon -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
    </head>
</head>
<body>
    <div class="header-container">
        <jsp:include page="../screens/navbar.jsp"></jsp:include>
        </div>
        <hr />
        <div class="body">
            <div class="content">
                <div class="SignUp_container">
                    <h1 style="text-align: center;font-size: 25px; margin-bottom: 7px;">Sign Up</h1>
                    <form id="InfomationForm" action="sign-up" method="post">
                        <label for="username" style="cursor: pointer; margin: 15px;">Username:</label><br/>
                        <input type="text" name="username" placeholder="Your Username" id="username" required/><br />
                        <span id="usernameerror" style="display: none; color: red;"> Username can not contain any special character</span>
                        <label for="password" style="cursor: pointer; margin: 15px;">Password:</label><br/>
                        <input  type="password" name="password" placeholder="Password:" id="password" required/><br />
                        <span id="passworderror" style="display: none; color: red;"> Password need contain at least 1 uppercase, 1 lowercase and 1 special character</span>
                        <label for="repassword" style="cursor: pointer; margin: 15px;">Re_password:</label><br/>
                        <input type="password" name="repassword" placeholder="Re_password" id="repassword" required/><br />
                        <span id="repassworderror" style="display: none; color: red;"> Repassword need the same as the password</span>
                        <label for="name" style="cursor: pointer; margin: 15px;">Name:</label><br/>
                        <input type="text" name="name" placeholder="Your name" id="name"/><br />
                        <label style="cursor: pointer; margin: 15px;">Question:</label><br/>
                            <select name="question" id="question">
                                <c:forEach items="${quest}" var="quest">
                                    <option value="${quest.id}">${quest.question}</option>
                                </c:forEach>
                            </select>
                    <input type="text" name="answer" placeholder="Your answer" id="answer" required/><br />
                    <span id="answererror" style="display: none; color: red;"> This can not be empty</span>
                    <label for="phone" style="cursor: pointer; margin: 15px;">Phone:</label><br/>
                    <input type="number" name="phone" placeholder="Your phone number" id="phone" required/><br />
                    <span id="phoneerror" style="display: none; color: red;"> Phone must be 10 number</span>  
                    <div class="submit" >
                        <input type="submit" value="Sign Up" id="submit" onclick="Clicked()"/> 
                    </div>
                </form>
                    <p>Already has account? <a href="sign-in">Sign in</a> now!</p>
                    <h4 style="color: red; margin-top: 10px; margin-bottom: 10px"><c:out value="${mess}" default=""/></h4>
            </div>
        </div>
    </div>
    <hr/>
    <div class="footer">
        <jsp:include page="../screens/footer.jsp"></jsp:include>
    </div>
    <script src="js/signUp.js"></script>
</body>
</html>
