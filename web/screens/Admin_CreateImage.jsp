<%-- 
    Document   : Amin_CreateImage
    Created on : Jun 20, 2023, 1:19:06 AM
    Author     : kienk
--%>

<%@page import="model.Color"%>
<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="main">            
                <form action="admin-create-variant" method="POST">
                    <label for="product_name">Product Name</label>
                    <select name="product_name" id="product_name">
                        <c:forEach var="product" items="${list}">
                            <option value="${product.id}">${product.name}</option>
                        </c:forEach>
                    </select>

                    <label for="color">Color</label>
                    <select name="color" id="color">
                        <c:forEach var="col" items="${list_Color}">
                            <option value="${col.id}">${col.name}</option>
                        </c:forEach>
                    </select>

                    <label for="img">Image</label>
                    <input type="file" name="img" id="img"" required>

                    <button type="submit">Create</button>
                </form>
            </div>
    </body>
</html>