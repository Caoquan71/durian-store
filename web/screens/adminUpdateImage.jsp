<%-- 
    Document   : adminUpdateImage
    Created on : Jun 20, 2023, 12:40:52 AM
    Author     : kienk
--%>

<%@page import="model.ProductImage"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.Product"%>
<%@page import="model.Color"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="main">
            <%
                ProductImage proImg = (ProductImage) request.getAttribute("proImg");
                List<Product> list = (List<Product>) request.getAttribute("list");
                List<Color> list_Color = (List<Color>) request.getAttribute("list_Color");
                HashMap<Integer, String> colorMap = (HashMap<Integer, String>) request.getAttribute("colorMap");
                HashMap<Integer, String> productMap = (HashMap<Integer, String>) request.getAttribute("productMap");
            %>
            <form action="admin-update-image" method="POST" style="margin-top: 5vh">
                <input type="hidden" name="id" id="id" value="<%= proImg.getId() %>">

                <label for="product_name">Product Name</label>
                <input type="text" name="product_name" id="product_name" value="<%= productMap.get(proImg.getProductId()) %>" disabled>

                <label for="color">Color</label>
                <select name="color" id="color">
                    <% for (Color col : list_Color) { %>
                    <% if (proImg.getColorId() == col.getId()) {%>
                    <option value="<%= col.getId()%>" selected><%= colorMap.get(proImg.getColorId())%></option>
                    <%} else {%>
                    <option value="<%= col.getId()%>"><%= colorMap.get(col.getId())%></option>
                    <%       }
                        }   
                    %>    
                </select>

                <label for="img">Image</label>
                <input type="hidden" name="current_image" value="<%= proImg.getUrl() %>">
                <input type="file" name="img" id="img" value="<%= proImg.getUrl() %>" >

                <button type="submit">Update</button>
            </form>
        </div>
    </body>
</html>