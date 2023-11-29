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
        <link rel="stylesheet" href="styles/create-blog.css" type="text/css"/>
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
                <i class='bx bx-news icon'></i>&nbsp;Add new blog
              </div>
              <hr />
              <form class="add-product" action="add-new-blog" method="POST">
                  <input type="hidden" id="quantitySub" name="quantitySub" value="0" />
                  <label for="cate">Blog Category</label><br />
                  <select id="cate" name="cate" style="width: 100%; border-radius: 10px; outline: none; padding: 7px">
                  <c:forEach items="${listCate}" var="lc">
                      <option value="${lc.id}">${lc.name}</option>
                      </c:forEach>
                  </select>
                  <br />
                <label for="title">Title</label><br />
                <input type="text" id="title" name="title" required /><br />
                <label for="cover">Cover image</label><br />
                <input type="file" id="cover" name="cover" required /><br />
                <label for="price">Add Sub Content for Blog</label><br />
                <div id="sub-content">
                </div>
                <div class="plus-container">
                <span id="plus">+</span>
                </div>
                <button type="submit" id="add-btn">Add</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
        
    <script src="js/dashboard.js"></script>
    <script>
        const plus = document.getElementById("plus");
        const subContent = document.getElementById("sub-content");
        let content = ``;
        let index = 0;
        const loadSubContent = (text) =>{
            subContent.innerHTML = text;
        };
        plus.addEventListener("click", ()=>{
            index+=1;
            content+= `<label for="header`+index+`">Sub Content Header ` + index;
            content+=`</label><br />
                <input type="text" id="header`+index+`" name="header`+index+`" required /><br />
                <label for="content`+index+`">Sub Content `+index+`</label><br />
                <textarea id="content`+index+`" name="content`+index+`" ></textarea><br />
                <label for="img`+index+`">Sub image `+index+`</label><br />
                <input type="file" id="img`+index+`" name="img`+index+`"/><br />`;
        const quantitySub = document.getElementById("quantitySub");
        quantitySub.value = index;
        console.log(quantitySub.value);
        loadSubContent(content);
        });
        loadSubContent(content);
    </script>
    </body>
</html>