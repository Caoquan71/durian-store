<%-- 
    Document   : Admin_OderList
    Created on : Jul 13, 2023, 1:07:41 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="model.Order"%>
<%@page import="model.Account"%>
<%@page import="dao.AccountDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Production List</title>
        <link
            href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
            rel="stylesheet"
            />
        <link rel="stylesheet" href="styles/admin-product.css" type="text/css"/>
        <link rel="stylesheet" href="styles/sidebar.css" type="text/css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
        <style>
            /* product */
            .product {
                position: relative;
                background-color: var(--body-color);
                left: 250px;
                height: 100vh;
                width: calc(100% - 250px);
                padding: 10px 14px;
                transition: var(--tran-05);
            }

            .product .top {
                position: fixed;
                top: 0;
                display: flex;
                left: 250px;
                width: calc(100% - 250px);
                justify-content: space-between;
                padding: 10px 14px;
                background-color: var(--siderbar-color);
                z-index: 10;
            }

            .sidebar.close ~ .product .top {
                left: 88px;
                width: calc(100% - 88px);
            }

            .product .top .search-box {
                position: relative;
                height: 45px;
                max-width: 600px;
                width: 100%;
                margin: 0 30px;
            }

            .product .top .search-box input {
                position: absolute;
                border: 1px solid var(--border-color);
                padding: 0 25px 0 50px;
                height: 100%;
                width: 100%;
                background-color: var(--siderbar-color);
                border-radius: 10px;
                color: var(--text-color);
                font-size: 15px;
                font-weight: 400;
                outline: none;
            }

            .product .top .search-box .icon {
                position: absolute;
                left: 15px;
                font-size: 22px;
                z-index: 10;
                top: 50%;
                transform: translateY(-50%);
            }

            .product .top .profile {
                transform: translateY(20%);
            }

            .product .product-content-container {
                margin-top: 60px;
                width: 100%;
                position: relative;
                left: 0;
            }

            .sidebar.close ~ .product .product-content-container {
                left: -150px;
                width: calc(100% + 150px);
            }

            .product-content-container .title {
                display: flex;
                align-items: center;
                margin: 100px 0 30px 0;
            }

            .product-content-container .title i {
                position: relative;
                height: 35px;
                width: 35px;
                color: var(--text-color);
                display: flex;
                align-items: center;
                justify-content: center;
                font-size: 24px;
            }

            .product-content-container .title .text {
                font-size: 24px;
                font-weight: 500;
                color: var(--text-color);
            }

            .product-content-container .boxes {
                display: flex;
                align-items: center;
                justify-content: space-between;
            }

            .product-content-container .boxes .recent-order {
                width: calc(100%);
                border-radius: 10px;
                padding: 15px 25px;
                background-color: var(--siderbar-color);
                margin-bottom: 10vh;
                position: relative;
            }

            .product-content-container .boxes .product-title {
                margin-bottom: 20px;
                font-weight: 500;
                font-size: 24px;
                color: var(--text-color);
                background-color: var(--siderbar-color);
            }

            .product-content-container .boxes .product-add {
                position: absolute;
                padding: 10px;
                background-color: var(--text-color);
                right: 10px;
                top: 10px;
                border-radius: 10px;
                color: red;
                text-decoration: none;
            }
            .product-content-container .boxes .product-add a {
                font-weight: 500;
                font-size: 16px;
                color: red;
                text-decoration: none;
            }

            .product-content-container .boxes .product-add:hover .product-content-container .boxes .product-add a {
                color: var(--siderbar-color) !important;
                text-decoration: none !important;
            }

            /* table */

            table {
                width: 100%;
                border-collapse: collapse;
            }

            thead th {
                background-color: #f2f2f2;
                color: #333333;
                font-weight: bold;
                padding: 10px;
                text-align: center;
            }
            tbody td{
                text-align: center;
            }
            thead th:nth-child(4) {
                width: 300px;
            }
            select {
                width: 300px;
                padding: 5px;
                outline: none;
            }
            
            select:hover {
                cursor: pointer;
            }

            tbody td {
                border-bottom: 1px solid #dddddd;
                padding: 10px;
            }

            /* Image Styles */
            table img {
                display: block;
                margin: auto;
                width: 300px;
                height: auto;
            }

            /* Alternate Row Color */
            tbody tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            body.dark tbody tr {
                background-color: var(--siderbar-color);
                color: var(--text-color);
            }

            /* Hover Effect */
            tbody tr:hover {
                background-color: #e6f7ff;
            }

            /* Responsive */
            @media (max-width: 1000px) {
                .sidebar {
                    width: 73px;
                }

                .sidebar .logo {
                    opacity: 0;
                    pointer-events: none;
                }

                .sidebar.close .logo {
                    opacity: 1;
                    pointer-events: auto;
                }

                .sidebar.close {
                    width: 250px;
                }
                .sidebar li a .nav-text,
                .sidebar li .admin-submenu-item .nav-text {
                    opacity: 0;
                    pointer-events: none;
                }
                .sidebar li .admin-submenu-item .admin-dropdown {
                    opacity: 0;
                }
                .sidebar.close li .admin-submenu-item .admin-dropdown {
                    opacity: 1;
                }

                .sidebar .sub-menu {
                    margin-left: 0;
                }

                .sidebar.close li a .nav-text,
                .sidebar.close li .admin-submenu-item .nav-text {
                    opacity: 1;
                    pointer-events: auto;
                }
                .sidebar ~ .product {
                    left: 73px;
                    width: calc(100% - 73px);
                }

                .sidebar ~ .product .top {
                    left: 73px;
                    width: calc(100% - 73px);
                }

                .sidebar.close ~ .product {
                    left: 250px;
                    width: calc(100% - 250px);
                }

                .sidebar.close ~ .product .top {
                    left: 250px;
                    width: calc(100% - 250px);
                }

                .bottom-content .mode-text {
                    opacity: 0;
                }

                .sidebar.close .bottom-content .mode-text {
                    opacity: 1;
                }

                .menu-bar .mode i {
                    position: absolute;
                    transition: var(--tran-03);
                }

                .product {
                    overflow-x: auto;
                }

                .sidebar header {
                    position: relative;
                }
                .sidebar .toggle {
                    position: absolute;
                    top: 50%;
                    right: 0px;
                    font-size: 25px;
                    transform: translateY(-50%);
                    display: flex;
                    justify-content: center;
                    cursor: pointer;
                }

                .sidebar.close .toggle {
                    position: absolute;
                    top: 50%;
                    font-size: 25px;
                    transform: translateY(-50%);
                    display: flex;
                    justify-content: center;
                    width: 100%;
                    right: -100px;
                }

                .sidebar .bottom-content .mode .moon-sun .i {
                    opacity: 0;
                }

                .product .product-content-container .boxes {
                    width: 300vw;
                }

            }
        </style>
    </head>
    <body>
        <%
           AccountDAO acd = new AccountDAO();
            
        %>  
        <jsp:include page="../screens/sideBarAdmin.jsp"></jsp:include>
            <section class="product">
                <div class="top">
                    <div class="search-box">
                        <i class="bx bx-search icon"></i>
                        <input type="text" placeholder="Search product by name here..." />
                    </div>
                    <h3 class="icon profile">Hi, Admin</h3>
                </div>
                <div class="product-content-container">
                    <div class="overview">
                        <div class="title">
                            <i class="bx bx-cart icon"></i>
                            <span class="text">Order Dashboard</span>
                        </div>

                        <div class="boxes">
                            <div class="recent-order">
                                <div class="product-title">
                                    <i class="bx bx-cart icon"></i>&nbsp;Order List
                                </div>
                                <div class="product-content">
                                    <table border="1px">
                                        <thead>
                                            <tr>
                                                <th>Client</th>
                                                <th>Total_Price</th>
                                                <th>Created Date</th>
                                                <th>Status</th>
                                                <th>Products</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${orderList}" var="order">
                                            <c:if test="${order.status != 1}">
                                            <c:set var="userId" value="${order.userId}" />
                                            <% 
                                                int id = Integer.parseInt(String.valueOf(pageContext.getAttribute("userId")));
                                                Account acc = acd.getAccountById(id);
                                            %> 
                                            <tr>
                                                <td><%= acc.getUsername()%></td>
                                                <td>$${order.totalPrice}</td>
                                                <td>
                                                    ${order.createdDate}
                                                </td>
                                                <td><select class="select" onchange="sendData(${order.id}, this.value)">
                                                        <c:forEach items="${statusList}" var="sta">
                                                            <c:if test="${sta.id != 1}">
                                                            <option value="${sta.id}" ${order.status == sta.id?"selected":""}>${sta.status}</option>
                                                            </c:if>
                                                        </c:forEach>
                                                    </select></td>
                                                    <td class="icon-show"><a href="admin-order-detail?userId=${order.userId}&orderId=${order.id}"><i class='bx bx-show-alt icon'></i></a></td>
                                            </tr>
                                            </c:if>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script src="js/dashboard.js"></script>
        <script>
            const select = document.querySelectorAll('.select');
            
            const sendData = (orderId, statusId) =>{
                if (confirm("Are you sure to update status of order?")){
                    window.location.href = "admin-change-order-status?orderId=" + orderId + "&statusId=" + statusId;
                }
                 
            };
        </script>
    </body>
</html>