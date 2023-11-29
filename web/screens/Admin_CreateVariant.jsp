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
        <link rel="stylesheet" href="styles/create-product-variant.css" type="text/css"/>
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
              <i class="bx bx-devices icon"></i>
            <span class="text">Product Variant Dashboard</span>
          </div>
          <div class="boxes">
            <div class="add-form">
              <div class="product-title">
                <i class="bx bx-devices icon"></i>&nbsp;Add new product variant
              </div>
              <hr />
              <form class="add-product" method="POST" action="admin-create-variant" id="form">
                <label for="name">Name</label><br />
                <select id="name" name="name">
                <c:forEach items="${list}" var="p">
                  <option value="${p.id}">${p.name}</option>
                  </c:forEach>
                </select>
                  <br />
                <label for="color">Color</label><br />
                <select id="color" name="color">
                  <c:forEach items="${list_Color}" var="c">
                  <option value="${c.id}">${c.name}</option>
                  </c:forEach></select
                ><br />
                <label for="storage">Storage</label><br />
                <select id="storage" name="storage">
                  <c:forEach items="${list_Storage}" var="s">
                  <option value="${s.id}">${s.storageSize}</option>
                  </c:forEach></select
                ><br />
                <label for="price">Price</label><br />
                <input type="text" id="price" name="price" disabled /><br />
                <label for="quantity">Quantity</label><br />
                <input
                  type="text"
                  id="quanity"
                  name="quantity"
                  required
                  style="margin-bottom: 40px"
                />
                <div class="add-img">
                    <label for="imgs">Add images<i id="plus" class="bx bx-plus icon plus"></i></label><div class="image-container"><div id="display-img"></div></div><br />
                <input type="file" name="imgs" id="imgs" style="display: none;" onchange="getImage(event.target.value)"/>
                <input type="hidden" id="imgList" name="imgList" />
                </div>
                <br />
                <button type="submit">Add</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
    <script src="js/dashboard.js"></script>
    <script>
        let imgList = []; //abs.jps, dcd.jsp
        
        const loadImage = (list) =>{
            const img = document.getElementById('display-img');
            let text = '';
            for (var i = 0; i < list.length; i++) {
                text+= `<span onclick="removeImage(`+i+`)" class="span-img" id='span-`+ (i+1) +`'><i id='icon-`+ (i+1) +`' class="bx bx-x icon"></i><img id='imgId`+ (i+1) +`'  src=\'img/` + list[i]+ `'/></span>`;
            }
            img.innerHTML = text;
        };
        const getImage = (imgUrl) =>{
            const img = document.getElementById('display-img');
            if (imgUrl !== ""){
            const newImg = imgUrl.replace(/^.*\\/,"");
            imgList.push(newImg);
            loadImage(imgList);
            }
        };
        
        const removeImage = (index) =>{
            const img = document.getElementById('display-img');
             imgList.splice(index, 1);
             let temp = '';
            imgList.forEach(e=>{
                temp+= e + ",";
            });
            temp = temp.slice(0, temp.lastIndexOf(','));
            const imgs = document.getElementById("imgList");
            imgs.value = temp;
              loadImage(imgList);
        };
        
        const imgInput = document.getElementById("imgs");
        imgInput.addEventListener('change', ()=>{
            let temp = '';
            imgList.forEach(e=>{
                temp+= e + ",";
            });
            temp = temp.slice(0, temp.lastIndexOf(','));
            const img = document.getElementById("imgList");
            img.value = temp;
            console.log(img.value);
            
        });
        loadImage(imgList);
    </script>
    </body>
</html>