<%-- 
    Document   : Admin_Dashbord
    Created on : Jun 10, 2023, 2:22:26 PM
    Author     : 84834
--%>

<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dash Board</title>
        <link rel="stylesheet" href="styles/create-product.css" type="text/css"/>
        <link rel="stylesheet" href="styles/sidebar.css" type="text/css"/>
        <link
      href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
      rel="stylesheet"
    />
    </head>
    <jsp:include page="../screens/sideBarAdmin.jsp"></jsp:include>
    <body>
        <section class="product">
      <div class="top">
        <div class="search-box">
          <i class="bx bx-search icon"></i>
          <input type="text" placeholder="Search here..." />
        </div>
        <h3 class="icon profile">Hi, Admin</h3>
      </div>
      <div class="add-container">
        <div class="overview">
          <div class="title">
            <i class="bx bx-palette icon"></i>
            <span class="text">Color Dashboard</span>
          </div>
          <div class="boxes">
            <div class="add-form">
              <div class="product-title">
                <i class="bx bx-palette icon"></i>&nbsp;Add new color
              </div>
              <hr />
              <form class="add-product" action="create-color" method="POST">
                <label for="color">Color</label><br />
                <input type="text" id="color" name="color" required /><br />
                <label for="hex">Hex Code</label><br />
                <input type="text" id="hex" name="hex" required /><br />
                <label for="price">Price Bonus</label><br />
                <input
                  type="number"
                  id="price"
                  name="price"
                  step="0.01"
                  required style="margin-bottom: 7vh"
                /><br />
                <button type="submit" id="add-btn">Add</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
        
    <script src="js/dashboard.js"></script>
    
    </body>
</html>