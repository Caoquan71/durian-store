<%-- 
    Document   : Admin_ProductionList
    Created on : Jun 16, 2023, 4:51:03 PM
    Author     : 84834
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="model.Product"%>
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
        <link rel="stylesheet" href="styles/admin-product-variant.css" type="text/css"/>
        <link rel="stylesheet" href="styles/sidebar.css" type="text/css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
    </head>
    <body>
        <jsp:include page="../screens/sideBarAdmin.jsp"></jsp:include>
            <section class="product">
                <div class="top">
                    <div class="search-box">
                        <i class="bx bx-search icon" id="search-icon"></i>
                        <input id="search-input" value="${sr}" type="text" placeholder="Search product variant by name here..." />
                </div>
                <h3 class="icon profile">Hi, Admin</h3>
            </div>
            <div class="product-content-container">
                <div class="overview">
                    <div class="title">
                        <i class="bx bx-devices icon"></i>
                        <span class="text">Product Variant Dashboard</span>
                    </div>

                    <div class="boxes">
                        <div class="recent-order">
                            <div class="product-title">
                                <div class="filter" id="1" data-value="&filter=1">All</div><div class="filter" id="2" data-value="&filter=2">Active</div><div class="filter" id="3" data-value="&filter=3">Non-Active</div><div class="filter" id="4" data-value="&filter=4">Sale</div>
                            </div>
                            <div class="product-add-sale" id="select-sale"><a>Edit Sale</a></div>
                            <div class="product-add"><a href="admin-create-variant">Add new Product</a></div>
                            <div class="product-content">
                                <table>
                                    <thead>
                                        <tr>
                                            <th><input type="checkbox" id="check-all"/></th>
                                            <th>Image</th>
                                            <th>Name</th>
                                            <th>Color</th>
                                            <th>Storage</th>
                                            <th>Quantity</th>
                                            <th>Price</th>
                                            <th>Sale</th>
                                            <th>Price after sale</th>
                                            <th>Edit</th>
                                            <th>Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${product}" var="p">
                                            <tr>
                                                <td><input type="checkbox" value="${p.id}" onclick="handleCheckboxClick(this)"/></td>
                                                <td>
                                                    <img
                                                        src="img/${p.url}"
                                                        alt="iphone"
                                                        width="200px"
                                                        />
                                                </td>
                                                <td>${p.name}</td>
                                                <td>${p.color}</td>
                                                <td>${p.storage}</td>
                                                <td>${p.quantity}</td>
                                                <td>${p.variantPrice}$</td>
                                                <td>${p.sale == 0?"None":p.sale}${p.sale == 0?"":"%"}</td>
                                                <td>${p.variantPrice - (p.variantPrice*p.sale)/100}$</td>
                                                <td><a href="admin-update-variant?id=${p.id}"><i class="bx bxs-edit-alt icon"></i></a></td>
                                                <td><c:if test="${p.status == 1}"><a href="admin-delete-variant?id=${p.id}&sta=2"><i class='bx bx-show-alt icon'></i></a></c:if>
                                                    <c:if test="${p.status != 1}"><a href="admin-delete-variant?id=${p.id}&sta=1"><i class='bx bx-low-vision icon' ></i></a></c:if></td>
                                                </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>
                                <div class="pagination" id="pagination" style="margin-bottom: 5vh; margin-top: 2vh">
                                    <a href="admin-variant-list?o=${tag==1?"1":tag-1}&f=${f}&tag=${tag==1?"1":tag-1}&sr=${sr}" class="${tag==1?"disabled":""}">&laquo;</a> 
                                    <c:forEach begin="1" end="${totalPage}" var="page">
                                        <c:if test="${page==1||page==2||page==totalPage-1||page==totalPage}">
                                            <a href="admin-variant-list?o=${page}&f=${f}&tag=${page}&sr=${sr}" class="${tag==page?"active":""}">${page}</a> </c:if>
                                        <c:if test="${page>2&&page<totalPage-1&&(page>tag+1||page<tag-1)&&(page==tag-2||page==tag+2)}"><a>...</a></c:if>
                                        <c:if test="${page>2&&page<totalPage-1&&(page==tag||page==tag-1||page==tag+1 )}">
                                            <a href="admin-variant-list?o=${page}&f=${f}&tag=${page}&sr=${sr}" class="${tag==page?"active":""}">${page}</a> </c:if>

                                    </c:forEach>
                                    <a href="admin-variant-list?o=${tag==totalPage?totalPage:tag+1}&f=${f}&tag=${tag==totalPage?totalPage:tag+1}&sr=${sr}" class="${tag==totalPage?"disabled":""}">&raquo;</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script src="js/dashboard.js"></script>
        <script>
            const searchIcon = document.getElementById("search-icon");
            const searchInput = document.getElementById("search-input");

            searchIcon.addEventListener("click", function () {
                submitSearchForm();
            });

            searchInput.addEventListener("keydown", function (event) {
                if (event.key === "Enter") {
                    submitSearchForm();
                }
            });

            function submitSearchForm() {
                const searchQuery = searchInput.value;
                const servletUrl = "admin-variant-list";
                window.location.href = `${servletUrl}?o=1&f=6&tag=1&sr=` + searchQuery;
            }

            const filter = document.querySelectorAll(".filter");
            filter.forEach(items => {
                items.addEventListener("click", () => {
                    const value = items.getAttribute("data-value");
                    const searchQuery = searchInput.value;
                    const servletUrl = "admin-variant-list";
                    if (searchQuery) {
                        window.location.href = `${servletUrl}?o=1&f=6&tag=1&sr=` + searchQuery + value;
                    } else {
                        window.location.href = `${servletUrl}?o=1&f=6&tag=1` + value;
                    }
                });
            });

            const addActive = () => {
                var queryString = window.location.search;
                var urlParams = new URLSearchParams(queryString);
                var filter = urlParams.get('filter');
                if (filter){
                const border = document.getElementById(filter);
                border.classList.add("active");
                }else{
                const border = document.getElementById("1");
                border.classList.add("active");
                }
            };
            addActive();

            let listId = [];
            const handleCheckboxClick = (checkbox) => {
                const id = checkbox.value;
                if (checkbox.checked) {
                    listId.push(id);
                } else {
                    const index = listId.indexOf(id);
                    if (index > -1) {
                        listId.splice(index, 1);
                    }
                }
            };
            
            const selectSale = document.getElementById("select-sale");
            selectSale.addEventListener("click", ()=>{
                if (listId.length === 0){
                    alert("Please select product!");
                }else{
                    let temp = '';
                    listId.forEach(e => {
                        temp += e + ",";
                    });
                    temp = temp.slice(0, temp.lastIndexOf(','));
                    console.log(temp);
                    window.location.href = "sale?id=" + temp;
                }
            });

        </script>
    </body>
</html>