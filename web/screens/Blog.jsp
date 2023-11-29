<%@page import="model.Account"%>
<%@page import="dao.AccountDAO"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DURIAN STORE</title>
        <!-- css -->
        <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>
        <link rel="stylesheet" href="styles/blog.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>
        <!-- Font family -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&family=Shadows+Into+Light&display=swap"
            rel="stylesheet"
            />
        <!-- Favicon -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
            />
        <link
            href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
            rel="stylesheet"
            />
    </head>
    <body>
        <%
          AccountDAO acd = new AccountDAO();
        %> 
        <div class="header-container">
            <jsp:include page="../screens/navbar.jsp"></jsp:include>
            </div>
            <section class="container">
                <hr/>
                <div class="side-bar">
                    <ul class="category">
                    <c:forEach items="${cateList}" var="cate">
                        <a href="blog?id=${cate.id}">
                            <li class="link">${cate.name}</li>
                        </a>
                    </c:forEach>
                </ul>
            </div>
            <div class="blog-container">
                <c:forEach items="${list}" var="c">
                    <c:set var="userId" value="${c.userId}" />
                    <% 
                        int id = Integer.parseInt(String.valueOf(pageContext.getAttribute("userId")));
                        Account acc = acd.getAccountById(id);
                    %> 
                    <div class="card">                       
                        <div class="img-container">
                            <img src="img/${c.cover}" alt="alt" width="300px" />
                        </div>
                        <div class="blog-title">
                            ${c.title}
                        </div>
                        <div class="profile"><i class='bx bx-user'>&nbsp;<span style="font-weight: 500"><%=acc.getUsername()%></span></i></div>
                        <div class="date"><i class='bx bx-calendar' >&nbsp;<span style="font-weight: 500">${c.date}</span></i></div>
                        <a href="blog-detail?id=${c.id}"><button>Read more</button></a>
                        
                    </div>
                        <hr/>
                </c:forEach>
            </div>
            <hr/>
        </section>


        <jsp:include page="../screens/footer.jsp"></jsp:include>


    </body>
</html>
