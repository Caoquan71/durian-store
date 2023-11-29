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
            <i class="bx bx-mobile-alt icon"></i>
            <span class="text">Product Dashboard</span>
          </div>
          <div class="boxes">
            <div class="add-form">
              <div class="product-title">
                <i class="bx bx-mobile-alt icon"></i>&nbsp;Update product
              </div>
              <hr />
              <form class="add-product" action="admin-update-product" method="POST">
                <input type="hidden" name="id" id="id" value="${product.id}">
                <label for="name">Name</label><br />
                <input type="text" id="name" name="name" value="${product.name}" required /><br />
                <label for="price">Price:</label><br />
                <input
                  type="number"
                  id="price"
                  name="price"
                  step="0.01"
                  value="${product.price}"
                  required
                /><br />
                <label for="screen">Screen</label><br />
                <input type="text" id="screen" name="screen" value="${product.screen}" required /><br />
                <label for="resolution">Screen Resolution</label><br />
                <input
                  type="text"
                  id="resolution"
                  name="resolution"
                  required
                  value="${product.screenResolution}"
                /><br />
                <label for="camera">Camera</label><br />
                <input type="text" id="camera" name="camera" required value="${product.camera}"/><br />
                <label for="ram">Ram</label><br />
                <input type="text" id="ram" name="ram" required value="${product.ram}"/><br />
                <label for="pin">Pin</label><br />
                <input type="text" id="pin" name="pin" required value="${product.pin}"/><br />
                <label for="chipset">Chipset</label><br />
                <input type="text" id="chipset" name="chipset" required value="${product.chipset}"/><br />
                <label for="description">Description</label><br />
                <textarea id="text" name="description" rows="10" >${product.description}</textarea
                ><br />
                <label for="img">Image</label><br />
                <input type="hidden" name="current-image" value="${product.img}">
                <input
                  type="file"
                  id="img"
                  name="img"
                  style="margin-bottom: 70px"
                /><br />
                <button type="submit" id="add-btn">Update</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
        
    <script src="js/dashboard.js"></script>
    
    </body>
</html>