<%-- 
    Document   : Admin_ProductionList
    Created on : Jun 16, 2023, 4:51:03 PM
    Author     : 84834
--%>

<%@page import="model.ProductImage"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Image List</title>
        <link rel="stylesheet" href="../styles/navbar.css" type="text/css"/>
        <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&family=Shadows+Into+Light&display=swap" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
        <link rel="stylesheet" href="styles/navbar.css" type="text/css"/>
        <style>
            .body{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                background-color: #ccc;
                overflow-x: hidden;
            }
            .content {
                display: flex;
            }
            .side-bar {
                position: sticky;
                top: 0px;
                width: 20%;
                height: 100%;
                background-color: #333;
            }
            .side-bar ul {
                width: 100%;
                height: 100%;
                list-style: none;
            }
            .side-bar ul li {
                width: 100%;
                height: 10vh;
                display: flex;
                align-items: center;
                justify-content: center;
            }
            .side-bar ul li a {
                text-decoration: none;
                color: #fff;
                font-size: 1.5rem;
            }
            .side-bar ul li a:hover {
                color: #ccc;
            }
            .main {
                width: 80%;
                height: 100%;

            }
            .main h2{
                text-align: center;
                margin: 1rem;
            }
            .main table{
                width: 100%;
                height: 100%;
                border-collapse: collapse;
            }
            .main table tr th{
                border: 1px solid #333;
                padding: 1rem;
            }
            .main table tr td{
                border: 1px solid #333;
                padding: 1rem;
            }
            .main table tr td img{
                width: 100px;
                height: 100px;
            }
            .setUp{
                display: flex;
                justify-content: space-around;
                align-items: center;
            }
        </style>
    </head>
    <body>
        <% 
            HashMap<Integer, String> colorMap = (HashMap<Integer, String>) request.getAttribute("colorMap");
            HashMap<Integer, String> productMap = (HashMap<Integer, String>) request.getAttribute("productMap");
            List<ProductImage> imgList =(List<ProductImage>) request.getAttribute("imgList");
        %>   
        <div class="header-container">
            <jsp:include page="../screens/navbar.jsp"></jsp:include>
            </div>
            <div class="body">
                <div class="content">
                <jsp:include page="../screens/sideBarAdmin.jsp"></jsp:include>
                    <div class="main">
                        <div class="setUp">
                            <a style="text-decoration: none; color: white; margin-top: 2vh; position: absolute; right: 5vw; top: 9vh" href="admin-add-img"><button style="background-color: #74a84a; border: 1px solid black; border-radius: 5px; padding: 5px; color:white">Add new Product</button></a>
                        </div>
                        <h2>Product Images in Store</h2>
                        <table>
                            <tr>
                                <th>#</th>
                                <th> Product Name</th>
                                <th>Color</th>
                                <th>Image</th>  
                                <th colspan="2">Action</th> 
                            </tr>
                        <% for (ProductImage e : imgList) { %>
                            <tr>
                                <td><%= e.getId() %></td>      
                                <td><%= productMap.get(e.getProductId()) %></td>                            
                                <td><%= colorMap.get(e.getColorId()) %></td>
                                <td><img src="img/<%= e.getUrl() %>"/></td> 
                                <td><a style="text-decoration: none; color: white; background-color: #74a84a; border: 1px solid black; border-radius: 5px; padding: 5px" href="admin-add-img?id=<%= e.getId() %>"   <button style="background-color: #74a84a; color: white">Edit</button></a>
                                <td><a style="text-decoration: none; color: white; background-color: red; border: 1px solid black; border-radius: 5px; padding: 5px"   <button style="background-color: #74a84a; color: white">Delete</button></a>
                            </tr>
                        <% } %>
                    </table>
                </div>
            </div>
        </div>
        <hr />
    </body>
</html>