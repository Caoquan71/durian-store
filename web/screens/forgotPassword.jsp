<%@page import="java.util.ArrayList"%>
<%@page import="model.SecurityQuestion"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
        <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>        
        <link rel="stylesheet" href="styles/forgot-password.css" type="text/css"/>
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&family=Shadows+Into+Light&display=swap" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
    </head>
    <body>
        <div class="header-container">
            <jsp:include page="../screens/navbar.jsp"></jsp:include>
            </div>
            <hr />
            <div class="forgot_password">
                <form action="forgot-password" method="post" id="InfomationForm">
                    <label for="username">User Name:</label>
                    <input type="text" name="username" id="username" placeholder="Enter your username">
                    <label for="question">Security Question:</label>
                    <select name="question" id="question">
                    <% 
                        ArrayList<SecurityQuestion> list =(ArrayList<SecurityQuestion>) request.getAttribute("quest");
                        for (int i = 0; i < list.size(); i++) {
                    %>    
                    <option value=<%= list.get(i).getId() %> ><%= list.get(i).getQuestion()%></option>
                    <% } %>
                </select>    
                <input type="text" name="answer" id="answer" placeholder="Enter your answer">
                <label for="password">Password:</label>
                <input type="password" name="password" id="password" placeholder="Enter your password" required />
                <span id="passworderror" style="display: none; color: red;"> Password need contain at least 1 uppercase, 1 lowercase and 1 special character</span>
                <label for="Re_Password">Re_Password</label>
                <input type="password" name="Re_Password" id="Re_Password" placeholder="Enter your Re_Password" required />
                <span id="repassworderror" style="display: none; color: red;"> Repassword need the same as the password</span>
                <input type="submit" value="Submit">
                <h4 style="color: red; margin-top: 10px; margin-bottom: 10px"><c:out value="${mess}" default=""/></h4>
            </form>

            <hr />
        </div>
        <jsp:include page="../screens/footer.jsp"></jsp:include>
    </body>
    <script>
        const password = document.getElementById('password');
        const repassword = document.getElementById('repassword');
        const reg_password = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&\*]{8,31}$/;
        password.addEventListener('change', function () {
            checkValid(password);
        });

        function checkValid(Variable) {
            let VariableValue = Variable.value;
            if (!reg_password.test(VariableValue)) {
                document.getElementById("passworderror").style.display = 'block';
                return 0;
            } else {
                document.getElementById("repassworderror").style.display = 'none';
                return 1;
            }
        }
        ;

        repassword.addEventListener('change', function () {
            checkRepass(repassword, password);
        });

        function checkRepass(repassword, password) {
            let rePass = repassword.value;
            let Pass = password.value;
            if (rePass === Pass) {
                document.getElementById('repassworderror').style.display = 'none';
                return 1;
            } else {
                document.getElementById('repassworderror').style.display = 'block';
                return 0;
            }
        }
        SignUp.addEventListener('submit', function (event) {
            if (checkValid(password) && checkRepass(repassword, password)) {
                return;
            } else {
                event.preventDefault();
                alert('Opps, May be you have some issue');
            }
        });
    </script>
</html>