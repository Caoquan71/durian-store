<%@page import="java.util.ArrayList"%>
<%@page import="model.ProductVariantInfomation"%>
<%@page import="java.util.HashMap"%>
<%@page import="dao.UserDAO"%>
<%@page import="dao.SaleDAO"%>
<%@page import="dao.ProductVariantDAO"%>
<%@page import="model.User"%>
<%@page import="model.Account"%>
<%@page import="model.ProductVariant"%>
<%@page import="model.OrderDetail"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Shopping Cart</title>
        <link rel="stylesheet" href="styles/shopping-cart.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>
        <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    </head>
    <body>
        <%
            UserDAO userDao = new UserDAO();
            Account acc = (Account) session.getAttribute("account");
            User user = userDao.getUserById(acc.getId());

            ArrayList<OrderDetail> list = (ArrayList<OrderDetail>) request.getAttribute("list");
            int orderId = (int) request.getAttribute("orderId");
            int size =(int) request.getAttribute("size");
            HashMap<Integer, ProductVariantInfomation> map = (HashMap<Integer, ProductVariantInfomation>) request.getAttribute("map");
            HashMap<Integer, String> color = (HashMap<Integer, String>) request.getAttribute("color");
            HashMap<Integer, String> storage = (HashMap<Integer, String>) request.getAttribute("storage");
        //JS validation quantity and price.    
            HashMap<Integer, Integer> remain = (HashMap<Integer, Integer>) request.getAttribute("remain");
            HashMap<Integer, Double> price = (HashMap<Integer, Double>) request.getAttribute("price");
            ProductVariantDAO pvd = new ProductVariantDAO();
            SaleDAO sd = new SaleDAO();
        %>    
        <div class="header-container">
            <jsp:include page="../screens/navbar.jsp"></jsp:include>
            </div>
        </div>
        <hr />
        <!-- Include the navbar -->
        <div class="container">
            <div class="shopping-cart-container">
                <form action="checkout" method="POST">
                <div class="shopping-cart">
                    <input type="hidden" name="orderId" id="orderId" value="<%=orderId%>">
                    <h1 style="margin-bottom: 0">Cart - <%= list.size() %> items</h1>
                    <hr/>
                    <table>
                        <thead>
                            <tr>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                        <%
                              if(list.size() > 0){
                              for (OrderDetail order : list) {
                              float beforePrice = map.get(order.getId()).getVariantPrice()*(map.get(order.getId()).getQuantity()==0?0:order.getQuantity());
                              int saleId = (int)map.get(order.getId()).getSale();
                              float salePercent = sd.getSaleById(saleId).getPercent();
                              int quantity = map.get(order.getId()).getQuantity();
                              float afterPrice = beforePrice * (1 - salePercent/100);
                        %>
                        <tr>
                            <input type="hidden" id="salePercent[<%=list.indexOf(order)%>]" name="salePercent[<%=list.indexOf(order)%>]" value="<%=salePercent%>">
                    <input type="hidden" name="orderDetailId[<%=list.indexOf(order)%>]" id="orderDetailId" value="<%=order.getId()%>">
                    <td><img class="img" src="img/<%= pvd.getOneProductVariantImage(map.get(order.getId()).getId(), map.get(order.getId()).getColor()).getUrl()%>" alt="Product A"></td>
                    <td><div class="product-name"><span class="name"><%= map.get(order.getId()).getName()%></span><span class="color">Color: <%=color.get(Integer.parseInt(map.get(order.getId()).getColor()))%></span><span class="storage">Storage Size: <%=storage.get(Integer.parseInt(map.get(order.getId()).getStorage()))%></span></div></td>
                    <td>
                        <div class="quantity-buttons">
                            <div class="des">-</div>
                            <span class="quantity-value"><input class="q-value" data-value="<%=quantity%>" id="quantity[<%=list.indexOf(order)%>]" name="quantity[<%=list.indexOf(order)%>]" type="number" min="1" max="<%=quantity%>" value="1" readonly></span>
                            <div class="incre">+</div>
                        </div>
                    </td>
                    <td><span class="price" style="font-weight: 600; font-size: 20px" data-value="<%= String.format("%.2f", (salePercent == 0) ? beforePrice : afterPrice)%>"><% if (salePercent == 0){%><span>$<%=beforePrice%></span><%}else{%><span style="text-decoration: line-through">$<%=beforePrice%></span><span style="font-size: larger">$<%= String.format("%.2f", (salePercent == 0) ? beforePrice : afterPrice)%></span> <%}%></span></td>
                    <td><a href="delete-detail?id=<%=order.getId()%>"><i class="fas fa-trash-alt icon"></i></a></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    </tbody>
                </table> 
            </div>
            <div class="summary">
                <div class="summary-title">Summary</div><hr/>
                <div class="summary-total"><span class="text" style="width: 250px">Products:</span><span class="text" id="product-total"></span></div>
                <div class="summary-ship"><span class="text" style="width: 250px">Shipping:</span><span class="text" >Free Ship</span></div>
                <div class="summary-total"><span class="text" style="width: 250px">Total amount:</span><span class="text" id="total-amount"></span></div>
                 <input type="hidden" id="total-value" name="total-value" value="" />
            </div>
        </div>


        <div class="payment-container">
            <h1>Payment</h1>
            <div class="form-group">
                <label for="name">Name:</label>
                <%if(user.getFullName() != null){ %>
                <input type="text" id="name" name="name" placeholder="Your Name" value="<%= user.getFullName() %>" required>
                <%} else{ %>
                <input type="text" id="name" name="name" placeholder="Your Name" required>
                <%} %>    
            </div>
            <div class="form-group">
                <label for="phone">Phone:</label>
                <%if(user.getPhone() != null){ %>
                <input type="text" id="phone" name="phone" placeholder="Your Phone Number" value="<%= user.getPhone() %>" required>
                <%} else{ %>
                <input type="text" id="phone" name="phone" placeholder="Your Phone Number" required>
                <%} %>  
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <%if(user.getAddress() != null){ %>
                <input type="text" id="address" name="address" placeholder="Shipping Address" value="<%= user.getAddress() %>">
                <%} else{ %>
                <input type="text" id="address" name="address" placeholder="Shipping Address" required>
                <%} %> 
            </div>
            <div class="form-group">
                <label for="paymentMethod">Choose payment method:</label>
                <input type="radio" name="paymentMethod"  value="2" onclick="showPaymentForm('shipcod')" checked>Ship COD<br>
                <input type="radio" name="paymentMethod" value="3" onclick="showPaymentForm('chuyenkhoan')">Chuyển khoản QR<br>
            </div>
            <div class="form-group">
                <input type="submit" value="Checkout">
            </div>
            <div id="shipcodForm" style="display: none;">
                </div>

                <div id="chuyenkhoanForm" style="display: none;">
                    <img src="https://cdn.britannica.com/17/155017-050-9AC96FC8/Example-QR-code.jpg" alt="QR Code" width="300">
                </div>

            </form>
            


        </div>
            

    </div>
    <hr />
    <jsp:include page="../screens/footer.jsp"></jsp:include>
    <script>
        function showPaymentForm(paymentMethod) {
            var shipcodForm = document.getElementById("shipcodForm");
            var chuyenkhoanForm = document.getElementById("chuyenkhoanForm");

            if (paymentMethod === "shipcod") {
                shipcodForm.style.display = "block";
                chuyenkhoanForm.style.display = "none";
            } else if (paymentMethod === "chuyenkhoan") {
                shipcodForm.style.display = "none";
                chuyenkhoanForm.style.display = "block";
            }
        }

        // Get all the decrease buttons, increase buttons, and quantity inputs
        const productTotal = document.getElementById('product-total');
        const totalAmount = document.getElementById('total-amount');
        const decreaseButtons = document.querySelectorAll('.des');
        const increaseButtons = document.querySelectorAll('.incre');
        const quantityInputs = document.querySelectorAll('.q-value');
        const price = document.querySelectorAll('.price');
        let totalPrice = 0;
        const totalValue = document.getElementById('total-value');
        price.forEach((e, index) => {
            let pri = parseFloat(e.getAttribute('data-value'));
            let quantity = parseInt(quantityInputs[index].value);
            totalPrice += pri * quantity;
            totalPrice = parseFloat(totalPrice.toFixed(2));
            productTotal.innerHTML = "$" + totalPrice;
            totalAmount.innerHTML = "$" + totalPrice;
            totalValue.value = totalPrice;
        });

// Add event listeners to each button
        decreaseButtons.forEach((button, index) => {
            button.addEventListener('click', () => {
                let currentValue = parseInt(quantityInputs[index].value);
                if (currentValue > 1) {
                    quantityInputs[index].value = currentValue - 1;
                    let pri = price[index].getAttribute('data-value');
                    let priceDefault = parseFloat(pri);
                    price[index].innerHTML = "$" + parseFloat((priceDefault * quantityInputs[index].value).toFixed(2));
                    totalPrice = totalPrice - priceDefault;
                    totalPrice = parseFloat(totalPrice.toFixed(2));
                    productTotal.innerHTML = "$" + totalPrice;
            totalAmount.innerHTML = "$" + totalPrice;
            totalValue.value = totalPrice;
                }
            });
        });

        increaseButtons.forEach((button, index) => {
            button.addEventListener('click', () => {
                let currentValue = parseInt(quantityInputs[index].value);
                const currentMax = quantityInputs[index].getAttribute("data-value");
                if (currentValue <currentMax){
                     quantityInputs[index].value = currentValue + 1;
                let pri = price[index].getAttribute('data-value');
                let priceDefault = parseFloat(pri);
                price[index].innerHTML = "$" + parseFloat((priceDefault * quantityInputs[index].value).toFixed(2));
                totalPrice = totalPrice + priceDefault;
                totalPrice = parseFloat(totalPrice.toFixed(2));
                productTotal.innerHTML = "$" + totalPrice;
            totalAmount.innerHTML = "$" + totalPrice;
            totalValue.value = totalPrice;
                }
               
            });
        });
        
    </script>
</body>
</html>
