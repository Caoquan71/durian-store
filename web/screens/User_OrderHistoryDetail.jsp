<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.OrderDetail"%>
<%@page import="dao.ProductVariantDAO"%>
<%@page import="dao.OrderDetailDAO"%>
<%@page import="model.ProductVariant"%>
<%@page import="model.Sale"%>
<%@page import="model.Color"%>
<%@page import="model.Product"%>
<%@page import="model.Storage"%>
<%@page import="dao.SaleDAO"%>
<%@page import="dao.ColorDAO"%>
<%@page import="dao.ProductDAO"%>
<%@page import="dao.StorageDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Durian Shop</title>
        <!-- css -->
        <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>
        <link rel="stylesheet" href="styles/order-history.css" type="text/css"/>
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
    </head>
    <body>
        <div class="header-container">
            <jsp:include page="../screens/navbar.jsp"></jsp:include>
            </div>
            <div class="profile-container">
                <div class="profile">
                    <div class="left-container">
                        <a id="profile" class="left-line" href="/Durian_Shop/account-profile?status=profile"><i class='fa fa-user'></i> Profile</a><br/><br/>
                        <a id="setting" class="left-line" href="/Durian_Shop/account-profile?status=setting"><i class="fa fa-cog" aria-hidden="true"></i> Setting</a><br/><br/>
                        <a class="left-line" href="my-cart"><i class='fa fa-shopping-bag'></i> My cart</i></a><br/><br/>
                    </div>
                    <div class="right-container" id="right-content">
                        <div class="history-container">
                            <div class="address">
                                <div class="address-title">
                                    Address
                                </div>
                                <div class="address-content">
                                    <div class="name">
                                        ${order.name}
                                    </div>
                                    <div class="phone">
                                        (+84) ${order.phone}
                                    </div>
                                    <div class="add">
                                        ${order.address}
                                    </div>
                                </div>
                            </div>
                            <div class="history">
                                <c:forEach items="${ohList}" var="oh">
                                <div class="state">
                                    <i class="fa fa-circle" aria-hidden="true"></i> <span class="date">${oh.date}</span><span class="text">${oh.description}</span>
                                </div>
                                </c:forEach>
                            </div>
                        </div>

                        <div class="card">
                            <div class="card-content">
                                <table>
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% 
                                            int id = Integer.parseInt((String)request.getAttribute("orderId"));
                                                OrderDetailDAO odd = new OrderDetailDAO();
                                                List<OrderDetail> od = odd.getAllOrderDetail(id);
                                                ColorDAO colorDao = new ColorDAO();
                                                StorageDAO stoDao = new StorageDAO();
                                                ProductDAO proDao = new ProductDAO();
                                                SaleDAO saleDao = new SaleDAO();
                                                ProductVariantDAO pvd = new ProductVariantDAO();
                                                for (OrderDetail order : od){
                                                    ProductVariant pv = pvd.getProductVariantByID(order.getProductId());
                                                    Color c = colorDao.getColorbyId(pv.getColorId());
                                                    Storage s = stoDao.getStoragebyId(pv.getStorageId() + "");
                                                    Product d = proDao.getProductbyId(pv.getProductId() + "");
                                                    Sale sale = saleDao.getSaleById(pv.getSaleId());
                                                    float priceSale = pv.getVariantPrice() * (1-sale.getPercent()/100);
                                                    String url = pvd.getOneProductVariantImage(d.getId(), c.getId() + "").getUrl();
                                            %> 
                                        
                                           
                                        <tr>
                                            <td><img class="card-img" src="img/<%=url%>" alt="alt"/></td>
                                            <td><span class="product-name"><%=d.getName()%> </span><span class="product-color"><%=c.getName()%></span>
                                            <span class="product-storage"><%=s.getStorageSize()%></span>
                                            <div class="product-quantity">x<%=order.getQuantity()%></div></td>
                                            <td><%if (pv.getSaleId() != 1){%>
                                                <span style="text-decoration: line-through;">$<%=pv.getVariantPrice()%></span>
                                                <span style="font-size: larger">$<%=priceSale%></span>
                                                <%}else{%>
                                                $<%=pv.getVariantPrice()%>
                                               <% }%>
                                                </td>
                                        </tr>
                                        <%}%>
                                        
                                    </tbody>
                                </table>
                            </div>
                            <div class="total-amount">
                                <p>Total amount: $${order.totalPrice}</p>

                            </div>
                        </div>
                        
                    </div>
                </div>
            </div>
    </body>
</html>