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
                            <span class="text">Sale Dashboard</span>
                        </div>
                        <div class="boxes">
                            <div class="add-form">
                                <div class="product-title">
                                    <i class="bx bx-palette icon"></i>&nbsp;Add sale for product
                                </div>
                                <hr />
                                <form class="add-product" action="sale" method="POST" id="form">
                                    <label for="color">Product: <c:forEach items="${name}" var="n" varStatus="status">
                                        ${n.name} ${n.color} ${n.storage}<c:if test="${!status.last}">,</c:if>
                                    </c:forEach></label><br />
                                <input type="hidden" id="id" name="id" value="${id}" data-value="${percentList}"/><br />
                                <label for="sale">Choose percent for sale</label><br />
                                <select id="sale" name="sale" style="width: 100%; outline: none; border: 1px solid var(--text-color); border-radius: 5px; padding: 5px; color: var(--text-color); background: var(--sidebar-color)">
                                    <c:forEach items="${saleList}" var="s"><option value="${s.id}" ${s.id == 1?"selected":""}>${s.percent == 0?"None":s.percent}${s.percent == 0?"":"%"}</option></c:forEach>
                                </select><br/>
                                <label for="newSale">Or type:</label><br />
                                <input type="number" step="0.01" id="newSale" name="newSale" placeholder="Type percent here..." style="margin-bottom: 7vh" min="0" max="100"/><br />

                                <button type="submit" id="add-btn">Add</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script src="js/dashboard.js"></script>
        <script>
            const form = document.getElementById("form");
            form.addEventListener("submit", (event) => {
                const newSale = document.getElementById("newSale");
                const percent = document.getElementById("id").getAttribute("data-value");
                const percentArray = percent.split(",").map(parseFloat);
                const value = parseFloat(newSale.value);


                if (percentArray.includes(value)) {
                    event.preventDefault();
                    alert("New percent already exists. Please type a different value or select above.");
                }


            });
        </script>

    </body>
</html>