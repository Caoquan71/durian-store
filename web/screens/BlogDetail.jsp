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
        <link rel="stylesheet" href="styles/blog-detail.css" type="text/css"/>
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
                    <c:forEach items="${cate}" var="cate">
                        <a href="blog?id=${cate.id}">
                            <li class="link">${cate.name}</li>
                        </a>
                    </c:forEach>
                </ul>
            </div>
            <div class="blog-container">
                <div class="blog-title">
                    ${blog.title}
                </div>
                <c:set var="userId" value="${blog.userId}" />
                    <% 
                        int id = Integer.parseInt(String.valueOf(pageContext.getAttribute("userId")));
                        Account acc = acd.getAccountById(id);
                    %> 
                <div class="profile">
                    <span class="p-name"><i class='bx bx-user'>&nbsp;<span style="font-weight: 500"><%=acc.getUsername()%></span></i></span>
                    <span class="p-date"><i class='bx bx-calendar' >&nbsp;<span style="font-weight: 500">${blog.date}</span></i></span>
                </div>
                    <c:forEach items="${subBlog}" var="s">
                <div class="sub-blog">
                    <div class="sub-title">${s.title}</div>
                    <div class="sub-content">
                        <p>
                            ${s.content}
                            <c:set var="img" value="${s.img}" />
                            <% String img = String.valueOf(pageContext.getAttribute("img"));
if (!img.equals("")){%>
                        <div class="img">   
                            <img
                                src="img/${s.img}"
                                />   
                        </div>
                                <%}%>
                        </p>
                    </div>
                </div>
                </c:forEach>
            </div>
            <hr/>
        </section>


        <jsp:include page="../screens/footer.jsp"></jsp:include>


    </body>
</html>
