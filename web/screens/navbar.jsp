<%-- 
    Document   : navbar
    Created on : May 22, 2023, 2:36:30 AM
    Author     : kienk
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="navbar-container">
    <ul class="nav-left">
        <li class="logo"><a href="/Durian_Shop/home">DURIAN STORE</a></li>
    </ul>
    <ul class="nav-right">
        <li class="nav-link"><a href="/Durian_Shop/home">HOME</a></li>
        <li class="nav-link store-link"><a href="/Durian_Shop/store">STORE</a>
            <li class="nav-link"><a href="blog?id=1">BLOG</a></li>
        <li class="nav-link"><a href="about-us">ABOUT</a></li>
        <li class="nav-link"><a href="contact">CONTACT</a></li>
        <li class="nav-link">
            <a href="checkout"><i class='fa fa-shopping-bag bag-icon'></i></i></a>
        </li>
        <c:if test="${not empty sessionScope.userName}">
            <li class="nav-link">
                <a href="/cart"><i class='fa fa-bell'></i></a>
            </li>
            <li class="nav-link dropdown">
                <a href="#" class="dropdown-toggle" id="dropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${userName}</a>
                <div class="dropdown-menu" aria-labelledby="dropdownMenu">
                    <c:if test="${sessionScope.role == 1}"><a class="dropdown-item" href="dashboard"><i class='fa fa-user'></i> Admin</a> </c:if>
                    <a class="dropdown-item" href="account-profile?status=profile"><i class='fa fa-user' aria-hidden="true"></i> Profile</a>
                    <a class="dropdown-item" href="account-profile?status=setting"><i class="fa fa-cog" aria-hidden="true"></i> Setting</a>
                    <a class="dropdown-item" href="my-cart"><i class="fa fa-shopping-bag" aria-hidden="true"></i> My Cart</a>
                    <a class="dropdown-item" href="sign-out"><i class="fa fa-sign-out" aria-hidden="true"></i> Log out</a>
                </div>
            </li>
        </c:if>
        <c:if test="${empty sessionScope.userName}">
            <li class="sign"><a href="/Durian_Shop/sign-in">Sign In</a><a href="/Durian_Shop/sign-up">/Sign Up</a></li>
            </c:if>
    </ul>
</div>

