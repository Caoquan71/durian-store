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
            <i class='bx bx-news icon'></i>
            <span class="text">Blog Dashboard</span>
          </div>
          <div class="boxes">
            <div class="add-form">
              <div class="product-title">
                <i class='bx bx-news icon'></i>&nbsp;Update blog category
              </div>
              <hr />
              <form class="add-product" action="update-blog-category" method="POST">
                  <input type="hidden" id="id" name="id" value="${cate.id}" />
              <input type="hidden" id="status" name="status" value="${cate.status}" />
                <label for="color">Name</label><br />
                <input type="text" id="name" name="name" value="${cate.name}" style="margin-bottom: 7vh" required /><br />

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