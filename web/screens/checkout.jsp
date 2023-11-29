<%-- 
    Document   : checkout
    Created on : Jul 8, 2023, 5:22:54 PM
    Author     : 84834
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.ProductVariantInfomation"%>
<%@page import="java.util.HashMap"%>
<%@page import="dao.UserDAO"%>
<%@page import="model.User"%>
<%@page import="model.Account"%>
<%@page import="model.OrderDetail"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Shopping Cart</title>
        <%
            UserDAO userDao = new UserDAO();
            Account acc = (Account) session.getAttribute("account");
            User user = userDao.getUserById(acc.getId());

            ArrayList<OrderDetail> list = (ArrayList<OrderDetail>) request.getAttribute("list");
            int orderId = (int) request.getAttribute("orderId");
            int size =(int) request.getAttribute("size");
            HashMap<Integer, ProductVariantInfomation> map = (HashMap<Integer, ProductVariantInfomation>) request.getAttribute("map");
        //JS validation quantity and price.    
            HashMap<Integer, Integer> remain = (HashMap<Integer, Integer>) request.getAttribute("remain");
            HashMap<Integer, Double> price = (HashMap<Integer, Double>) request.getAttribute("price");
        %>    
        <style>

            /* CSS styles for the container */
            .container {
                display: flex;
                justify-content: space-between;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
                overflow: hidden;
            }

            /* CSS styles for the left column (Shipping Cart) */
            .left-column {
                width: 60%;
                padding: 20px;
                background-color: #fff;
            }

            /* CSS styles for the right column (Payment) */
            .right-column {
                width: 40%;
                padding: 20px;
                background-color: #fff;
            }

            /* CSS styles for the table */
            table {
                width: 100%;
            }

            th, td {
                padding: 8px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            /* CSS styles for the form */
            form {
                margin-top: 20px;
            }

            .form-group {
                margin-bottom: 20px;
            }

            .form-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }

            .form-group input[type="text"],
            .form-group textarea {
                width: 100%;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 5px;
            }

            .form-group textarea {
                height: 80px;
            }

            .form-group input[type="submit"] {
                padding: 10px 20px;
                background-color: #4CAF50;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .form-group input[type="submit"]:hover {
                background-color: #45a049;
            }
        </style>
        <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    </head>
    <body>
        <!-- Include the navbar -->
        <jsp:include page="../screens/navbar.jsp"></jsp:include>
            <div class="container">
                <form action="checkout" method="POST">
                    <input type="hidden" name="orderId" id="orderId" value="<%=orderId%>">
                    <div class="left-column">
                        <h1>Shopping Cart</h1>
                        <h2>Order Details</h2>
                        <table>
                            <tr>
                                <th>Order No</th>
                                <th>Product</th>
                                <th>Image</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Action</th>
                            </tr>

                        <%
                           if(list.size() > 0){
                           for (OrderDetail order : list) {
                        %>
                        <tr>
                            <input type="hidden" name="orderDetailId[<%=list.indexOf(order)%>]" id="orderDetailId" value="<%=order.getId()%>">
                            <th><%= list.indexOf(order) + 1%></th>
                            <th><%= map.get(order.getId()).getName()%></th>
                            <th><img src="<%= map.get(order.getId()).getUrl()%>" alt="Product A" width="50"></th>
                            <th><input id="quantity[<%=list.indexOf(order)%>]" name="quantity[<%=list.indexOf(order)%>]" type="number" min="1" value="1"></th>
                            <th><%= map.get(order.getId()).getVariantPrice()%></th>
                            <!--  todo : them xoa controller -> done-->
                            <th><a href="delete-detail?id=<%=order.getId()%>"><i class="fas fa-trash-alt"></i></a></th>
                        </tr>
                        <%
                            }
                        }
                        %>
                    </table>
                    <h2><input name="totalPrice" id="totalPrice" value="30" readonly></h2>
                </div>
                <div class="right-column">
                    <h1>Payment</h1>

                    <div class="form-group">
                        <input type="radio" name="paymentMethod" value="shipcod" onclick="showPaymentForm('shipcod')">Ship COD<br>
                        <input type="radio" name="paymentMethod" value="chuyenkhoan" onclick="showPaymentForm('chuyenkhoan')">Chuyển khoản QR<br>
                    </div>
                    <div id="shipcodForm" style="display: none;">
                        <!-- Ship COD form elements -->
                        <h3>Ship COD</h3>
                        <div class="form-group">
                            <label for="name">Your Name</label><br>
                            <%if(user.getFullName() != null){ %>
                                <input type="text" id="name" name="name" placeholder="Your Name" value="<%= user.getFullName() %>" required>
                            <%} else{ %>
                                <input type="text" id="name" name="name" placeholder="Your Name" required>
                            <%} %>    
                        </div>
                        <div class="form-group">
                            <label for="phone">Your Phone Number</label><br>
                            <%if(user.getPhone() != null){ %>
                                <input type="text" id="phone" name="phone" placeholder="Your Phone Number" value="<%= user.getPhone() %>" required>
                            <%} else{ %>
                                <input type="text" id="phone" name="phone" placeholder="Your Phone Number" required>
                            <%} %>     
                        </div>
                        <div class="form-group">
                            <label for="address">Shipping Address</label><br>
                            <%if(user.getAddress() != null){ %>
                                <input type="text" id="address" name="address" placeholder="Shipping Address" value="<%= user.getAddress() %>">
                            <%} else{ %>
                                <input type="text" id="address" name="address" placeholder="Shipping Address" required>
                            <%} %> 
                        </div>
<!--                        <div class="form-group">
                            <textarea id="note" name="note" placeholder="Note"></textarea>
                        </div>-->
                        <div class="form-group">
                            <input type="submit" value="Checkout">
                        </div>
                    </div>
                    <div id="chuyenkhoanForm" style="display: none;">
                        <!-- Chuyển khoản QR form elements -->
                        <h3>Chuyển khoản QR</h3>
                        <img src="./img/qr.png" alt="QR Code" width="200">
                    </div>
                    <h3>Total Amount: $30</h3>
                    <h3>Free Shipping</h3>
                </div>
            </form>
        </div>

        <script>
            function showPaymentForm(paymentMethod) {
                var shipcodForm = document.getElementById("shipcodForm");
                var chuyenkhoanForm = document.getElementById("chuyenkhoanForm");

                if (paymentMethod === "shipcod") {
                    shipcodForm.style.display = "block";
                    chuyenkhoanForm.style.display = "none";
                } else if (paymentMethod === "chuyenkhoan") {
                    shipcodForm.style.display = "none";
                    chuyenkhoanForm.style.display = "block";
                }
            }
        </script>
    </body>
</html>