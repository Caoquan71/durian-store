<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Durian Shop</title>
        <!-- css -->
        <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>
        <link rel="stylesheet" href="styles/account-profile.css" type="text/css"/>
        <!-- Font family -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&family=Shadows+Into+Light&display=swap"
            rel="stylesheet"
            />
        <!-- Favicon -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
            />
    </head>
    <body>
        <div class="header-container">
            <jsp:include page="../screens/navbar.jsp"></jsp:include>
            </div>
            <div class="profile-container">
                <div class="profile">
                    <div class="left-container">
                        <a id="profile" class="left-line" href="/Durian_Shop/account-profile?status=profile"><i class='fa fa-user'></i> Profile</a><br/><br/>
                        <a id="setting" class="left-line" href="/Durian_Shop/account-profile?status=setting"><i class="fa fa-cog" aria-hidden="true"></i> Setting</a><br/><br/>
                        <a class="left-line" href="my-cart"><i class='fa fa-shopping-bag'></i> My cart</i></a><br/><br/>
                    </div>
                    <div class="right-container" id="right-content">
                        
                    </div>
                </div>
            </div>
        <jsp:include page="../screens/footer.jsp"></jsp:include>

            <script>
                const profile = `<form method="POST" action="account-profile?status=profile">
                            <p>Full name</p>
                            <input name="fullName" class="user-inf" value="${user.fullName}"/><br/>
                            <p>Gender</p>
                            <select name="gender" class="user-inf">
                                <c:choose>
            <c:when test="${user.gender == null}">
                <option value="#" selected>Gender</option>
            </c:when>
        </c:choose>
                            <option value="0" ${user.gender=="false"?"selected":""}>Male</option>
                            <option value="1" ${user.gender=="true"?"selected":""}>Female</option> 
                            </select><br/>
                            <p>Phone</p>
                            <input name="phone" class="user-inf" value="${user.phone}"/><br/>
                            <p>Address</p>
                            <input name="address" class="user-inf" value="${user.address}"/>
                            <input class="update" type="submit" value="Update">
                        </form>
            `;
                const setting = `<form method="POST" action="account-profile?status=setting">
                                <p>Current password</p>
                                <input type="password" name="current-password" class="user-inf" required/><br/>
                            <c:if test="${not empty error}"><h1 style="color: red; text-align: center; width: 100%; margin: auto">${error}</h1><br/></c:if>
                                <p>New password</p>
                                <input type="password" name="newPassword" class="user-inf" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&\*]{8,31}$" required/><br/>
                                <p>Verify new password</p>
                                <input type="password" name="rePassword" class="user-inf" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&\*]{8,31}$" required/>
                                <c:if test="${not empty error2}"><h1 style="color: red; text-align: center; width: 100%; margin: auto">${error2}</h1><br/></c:if>
                                <input class="update" type="submit" value="Update">
                            </form>`;
                
                const handleContentTwo = () =>{
                    const rightContent = document.getElementById('right-content');
                    var queryString = window.location.search;
                    var urlParams = new URLSearchParams(queryString);
                    var status = urlParams.get('status');
                    if (status.includes("profile")){
                        rightContent.innerHTML = profile;
                    }   
                    else if (status.includes("setting")){
                        rightContent.innerHTML = setting;
                    } 
                };
                handleContentTwo();
        </script>
    </body>
</html>