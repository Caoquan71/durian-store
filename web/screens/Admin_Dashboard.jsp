<%-- 
    Document   : Admin_Dashbord
    Created on : Jun 10, 2023, 2:22:26 PM
    Author     : 84834
--%>

<%@page import="model.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dash Board</title>
        <link rel="stylesheet" href="styles/dashboard.css" type="text/css"/>
        <link rel="stylesheet" href="styles/sidebar.css" type="text/css"/>
        <link
            href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
            rel="stylesheet"
            />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    </head>
    <jsp:include page="../screens/sideBarAdmin.jsp"></jsp:include>
    <%
        HashMap<Integer, String> userMap = (HashMap<Integer, String>) request.getAttribute("listUser");
        HashMap<Integer, String> productMap = (HashMap<Integer, String>) request.getAttribute("listProduct");
        ArrayList<Comment> listComment = (ArrayList<Comment>) request.getAttribute("listComment");
    %>
    <body>
        <section class="dashboard">
            <div class="top">
                <div class="search-box">
                    <i class="bx bx-search icon"></i>
                    <input type="text" placeholder="Search here..." />
                </div>
                <i class='bx bx-bell icon notify' ></i>
                <h3 class="icon profile">Hi, Admin</h3>
            </div>
            <div class="dash-content">
                <div class="overview">
                    <div class="title">
                        <i class="bx bxs-dashboard"></i>
                        <span class="text">Dashboard</span>
                    </div>
                    <div class="boxes">
                        <div class="box box1">
                            <i class="bx bx-user icon"></i>
                            <span class="text">Users</span>
                            <span class="number">${totalUser}</span>
                        </div>
                        <div class="box box2">
                            <i class="bx bx-mobile-alt icon"></i>
                            <span class="text">Products</span>
                            <span class="number">${totalProduct}</span>
                        </div>
                        <div class="box box3">
                            <i class="bx bx-cart-alt icon"></i>
                            <span class="text">Sale</span>
                            <span class="number">${totalSale}</span>
                        </div>
                        <div class="box box4">
                            <i class="bx bx-cart icon"></i>
                            <span class="text">Orders</span>
                            <span class="number">${totalOrder}</span>
                        </div>
                    </div>

                    <div class="boxes" style="height: fit-content;">
                        <div class="recent-order">
                            <div class="recent-title">
                                <i class="bx bx-time icon"></i>Recent Order
                            </div>
                            <hr />
                            <div class="recent-content">
                                <div class="data client">
                                    <span class="data-title">Client</span>
                                    <span class="data-list">Kien Kieu</span>
                                    <span class="data-list">Kien Kieu</span>
                                    <span class="data-list">Kien Kieu</span>
                                    <span class="data-list">Kien Kieu</span>
                                </div>
                                <div class="data orderID">
                                    <span class="data-title">Order ID</span>
                                    <span class="data-list">1</span>
                                    <span class="data-list">1</span>
                                    <span class="data-list">1</span>
                                    <span class="data-list">1</span>
                                </div>
                                <div class="data order-date">
                                    <span class="data-title">Order date</span>
                                    <span class="data-list">20-06-2020</span>
                                    <span class="data-list">20-06-2020</span>
                                    <span class="data-list">20-06-2020</span>
                                    <span class="data-list">20-06-2020</span>
                                </div>
                                <div class="data order-status">
                                    <span class="data-title">Status</span>
                                    <span class="data-list">Paid</span>
                                    <span class="data-list">Paid</span>
                                    <span class="data-list">Paid</span>
                                    <span class="data-list">Paid</span>
                                </div>
                                <div class="data ship">
                                    <span class="data-title">Ship</span>
                                    <span class="data-list">Done</span>
                                    <span class="data-list">Done</span>
                                    <span class="data-list">Done</span>
                                    <span class="data-list">Done</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="boxes">
                        <div class="recent-order">
                            <div class="recent-title">
                                <span class="material-symbols-outlined" style="font-size: 22px">report</span>Comment Reported
                            </div>
                            <hr />
                            <table style="width: 100%">
                                <thead>
                                <td class="data-title">User</td>
                                <td class="data-title">Product</td>
                                <td class="data-title" colspan="4">Content</td>
                                <td class="data-title">Delete</td>
                                <td class="data-title">Ban</td>
                                </thead>
                                <tbody>
                                    <% for (Comment com : listComment) { %>
                                <td class="data-list"><%= userMap.get(com.getUserId()) %></td>
                                <td class="data-list"><%= productMap.get(com.getProdcutId()) %></td>
                                <td class="data-list" colspan="4"><%= com.getContent() %></td>
                                <td class="data-list" style="cursor: pointer"><a href="admin-delete-comment?id=<%=com.getId()%>"><span class="material-symbols-outlined" style="color: red;">delete</span></a></td>
                                <td class="data-list" style="cursor: pointer"><span class="material-symbols-outlined">chat_error</span></td>
                                <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- ---End------->
                </div>
            </div>
        </section>
        <script src="js/dashboard.js"></script>
    </body>
</html>