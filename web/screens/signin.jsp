<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <style>
            /* CSS styling for the layout */
            body,html {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                height: 100%;
                overflow: hidden;
            }
            #background {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                z-index: -1;
            }
            #background img {
                display: block;
                width: 100%;
                height: 100%;
                object-fit: cover;
            }
            header {
                background-color: #F9F9F9;
                padding: 10px;
                text-align: center;
            }
            header h1 {
                margin: 0;
            }
            .container {
                display: flex;
                justify-content: center;
                align-items: center;
                flex-direction: column;
                height: calc(100vh - 100px);
            }
            .login-box {
                width: 300px;
                padding: 20px;
                padding-right:40px ;
                border: 1px solid #ccc;
                background-color: #f9f9f9;
                margin-left: 900px;
                float: right ;
            }
            .login-box input[type="text"],
            .login-box input[type="password"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
            }
            .login-box input[type="submit"] {
                width: 321px;
                padding: 10px;
                background-color: #EE4E2E;
                color: #fff;
                border: none;
                cursor: pointer;
            }
            .login-box a {
                text-decoration: none;
                color: #777;
                font-size: 12px;
            }
            footer {
                background-color: #F9F9F9;
                text-align: center;
                position: fixed;
                bottom: 0;
                width: 100%;
            }
        </style>
    </head>

    <body>
        <div id="background">
            <img src="./img/banner.png" alt="Background">
        </div>

        <header>
            <a style="text-decoration: none; color: black" href="home"><h1>DURIAN STORE</h1></a>
        </header>

        <div class="container">
            <div class="login-box">
                <form action="sign-in" method="post">
                    <h2>Sign In</h2>
                    <%-- Display error message if available --%>
                    <c:if test="${not empty error}">
                        <p style="color: red;">${error}</p>
                    </c:if>
                    <input type="text" name="username" placeholder="Username" required>
                    <input type="password" name="password" placeholder="Password" required>
                    <input type="submit" value="Sign In">
                </form>
                <a href="forgot-password">Forgot Password?</a> |
                <a href="sign-up">Sign Up</a>
            </div>
        </div>

        <footer>
            <p>Copyright © 2023 DURIAN STORE</p>
        </footer>
    </body>

</html>