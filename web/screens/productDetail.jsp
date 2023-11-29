<%-- 
    Document   : productDetail
    Created on : May 22, 2023, 2:54:53 AM
    Author     : kienk
--%>


<%@page import="model.Account"%>
<%@page import="model.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Durian Shop</title>
        <!--css-->
        <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>
        <link rel="stylesheet" href="styles/related-product.css" type="text/css"/>
        <link rel="stylesheet" href="styles/product-detail.css" type="text/css"/>
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
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
        <style>
            /* Utilities */
            .comment{
                width: 70%;
                margin-left: 15%;
            }
            .flex {
                display: flex;
            }
            .justify-between {
                justify-content: space-between;
            }
            .bordered {
                border: 1px solid #86868b;
                border-radius: 3px;
            }
            .relative {
                position: relative;
            }
            .absolute {
                position: absolute;
            }
            .z-10 {
                z-index: 10;
            }
            .hidden {
                display: none;
            }
            .bg-white {
                background: white;
            }
            .p-5 {
                padding: 5px;
            }
            /* Sizing */
            .comment-component {
                font-size: 12px;
                width: 100%;
                min-height: 10em;
                margin: 5px;
            }
            .comment-new {
                margin-bottom: 5px;
            }
            textarea {
                width: 100%;
                border: none;
                resize: vertical;
                height: 2em;
                min-height: 2em;
            }
            .comment-new button {
                border-width: 0 0 0 1px;
            }
            .img-container {
                width: 2em;
                height: 2em;
            }
            .comments {
                height: 32em;
                overflow-y: auto;
            }
            .comment-text{
                margin-left: 2%;
            }
        </style>
        <script>
            function toggleOptionsMenu(index) {
                const classList = document.getElementById('options-menu-' + index).classList;
                classList.remove('hidden');
            }

            function hideOptionsMenu(index) {
                const classList = document.getElementById('options-menu-' + index).classList;
                classList.add('hidden');
            }

            function editComment(index) {
                let para = 'content-' + index;
                const textarea = document.getElementById(para);
                const submitButton = document.getElementById('submit-' + index);
                if (textarea.disabled) {
                    console.log('hell no');
                    textarea.removeAttribute('disabled');
                    submitButton.classList.remove('hidden');
                    textarea.style.border = '1px solid red';
                } else {
                    console.log('co vao');
                    textarea.setAttribute('disabled', 'disabled');
                    submitButton.classList.add('hidden');
                    textarea.style.border = '1px solid black';
                }
            }
        </script>
    </head>
    <body>
        <%
            HashMap<Integer, String> map = (HashMap<Integer, String>) request.getAttribute("userMap");
            ArrayList<Comment> listComment = (ArrayList<Comment>) request.getAttribute("listComment");
            Account acc = (Account) request.getAttribute("acc");
        %>
        <div class="header-container">
            <jsp:include page="../screens/navbar.jsp"></jsp:include>
            </div>
        </div>
        <hr />
        <div class="product-container">
            <h1 class="product-name">Buy ${productName}</h1>
        <div class="product-image">
            <c:forEach items="${image}" var="img">
                <a><img class="mySlides" src="img/${img.url}" alt=""></a>
                </c:forEach>
            <button class="btn-slide" id="pre-slide" onclick="moveImage(-1)">&#10094;</button>
            <button class="btn-slide" id="next-slide" onclick="moveImage(1)">&#10095;</button>
        </div>
        <div class="product-pick">
            <div class="pick-color">
                <h2>Pick your favorite</h2>
                <h3 id="color-name" style="color: black;">Color<c:if test="${not empty colorName}">
                        - ${colorName}
                    </c:if></h3>
                    <c:forEach items="${color}" var="c">
                    <button class="btn-color" id="${c.name}" style="background-color: ${c.hexCode}; border: 1px solid grey; border-radius: 50%; height: 20px; width: 20px;"></button>
                </c:forEach>
            </div>
            <div class="pick-storage disabled" id="storage-options">
                <h2>How much space do you need?</h2>
                <c:forEach items="${storage}" var="s">
                    <button class="storage" id="storage${s.id}">${s.storageSize}</button>
                </c:forEach>
            </div>
        </div>
    </div>
    <div id="add-to-cart-container" class="hidden">
        <div id="your-phone">
            <h2>Your new</h2>
            <h2>${productName}</h2>
            <h2 style="color: #86868b;">Just the way you</h2>
            <h2 style="color: #86868b;">want it.</h2>
        </div>
        <div id="your-price">
            <h3>${productName} ${storageSize} ${colorName}</h3>
            <c:if test="${variant.saleId==1}"><h3>$${variant.variantPrice}</h3></c:if>
            <c:if test="${variant.saleId!=1}"><h3><span style="text-decoration: line-through;">$${variant.variantPrice}</span> <span style="font-size: larger">$${priceSale}</span></h3></c:if>
                <hr style="border:1px solid grey; margin-top: 0"/>
            <c:if test="${variant.quantity != 0}">
                <a href="add-to-cart?id=${product.id}&colorName=${colorName}&storageId=${storageId}"><button>Add to cart</button></a></c:if>
            <c:if test="${variant.quantity == 0}">
                <h3>This product is out of stock!</h3></c:if>
            </div>
            <div id="">
                <h3 style="margin-bottom: 2px"><i class="fa fa-truck"></i> Delivery:</h3>
                <p style="padding-left:15px; margin-top: 0px">${variant.quantity==0?"Out Stock":"In Stock"}<br/>
                Free Shipping<br/>
                <a href="#">Get delivery dates</a></p>
            <h3 style="margin-bottom: 2px"><i class="fa fa-shopping-bag"></i>Pick up:</h3>
            <p style="padding-left:15px; margin-top: 0px;">Availability: ${variant.quantity}</p>
        </div>
    </div>
    <hr />
    <div class="detail-container">
        <h2><span class="description">Description</span>&nbsp;<span class="review">Review</span></h2>
        <div class="des-container">
            <div class="description-content">
                <div class="description-text"> 
                    <h3 style="color: red; text-align: center;">Feature</h3>
                    <c:forEach items="${description}" var="d">
                        <p> <span style="font-size: 1.8em;">&middot;</span> ${d}</p>
                    </c:forEach>
                </div>
                <div class="specification">
                    <h3 style="color: red; text-align: center;">Specifications</h3>
                    <p style="background-color: #E7E9EB; padding: 5px; border-radius: 10px;"> <span style="font-size: 1.8em;">&middot;</span> Screen: ${product.screen}</p>
                    <p> <span style="font-size: 1.8em;">&middot;</span> Camera: ${product.camera}</p>
                    <p style="background-color: #E7E9EB; padding: 5px; border-radius: 10px;"> <span style="font-size: 1.8em;">&middot;</span> Ram: ${product.ram}</p>
                    <p> <span style="font-size: 1.8em;">&middot;</span> Operating system: IOS</p>
                    <p style="background-color: #E7E9EB; padding: 5px; border-radius: 10px;"> <span style="font-size: 1.8em;">&middot;</span> Pin: ${product.pin}</p>
                    <p> <span style="font-size: 1.8em;">&middot;</span> Chipset: ${product.chipset}</p>
                    <p style="background-color: #E7E9EB; padding: 5px; border-radius: 10px;"> <span style="font-size: 1.8em;">&middot;</span> Screen Resolution: ${product.screenResolution}</p>
                </div>
            </div>
        </div>
        <div class="review-content">
            <p>Review Màn hình Dynamic Island - Sự biến mất của màn hình tafdfdfi thỏ thay thế bằng thiết kế viên thuốc, OLED 6,7 inch, hỗ trợ always-on display</p>
        </div>
    </div>
    <hr />
    <jsp:include page="../screens/relatedProduct.jsp"></jsp:include>
        <hr />
        <div class="comment">
            <div class="comment-component bordered p-5">
                <p style="margin: 0; font-size: 18px; font-weight: 600">Comment</p>
                <div >
                    <form class="comment-new flex bordered" action="add-comment">
                        <input type="hidden" value="${productName}" name="productName">
                    <textarea name="contentComment"></textarea>
                    <button type="submit">
                        <span class="material-symbols-outlined">
                            keyboard_return
                        </span>
                    </button>
                </form>
            </div>
            <!--//List comment:-->        
            <div class="comments">
                <% for (Comment comment : listComment) {%>
                <div id="comment-<%=comment.getId()%>" class="bordered p-5" style="width: 94%; margin-left:2%; margin-bottom: 5px">
                    <div class="flex justify-between mb-5">
                        <!--Account and username-->
                        <div class="flex" style="align-items: center">
                            <div>
                                <span class="material-symbols-outlined">
                                    account_circle
                                </span>
                            </div>
                            <div style="font-weight: 600; font-size: 14px"><%= map.get(comment.getUserId())%> </div>
                        </div>
                        <!--More-->
                        <div class="relative">
                            <span class="material-symbols-outlined" onMouseOver="toggleOptionsMenu(<%= comment.getId()%>)" onMouseOut="hideOptionsMenu(<%= comment.getId()%>)" style="cursor: pointer">
                                more_horiz
                            </span>
                            <div id="options-menu-<%=comment.getId()%>" class="bordered bg-white absolute z-10 hidden" style="top: 70%;left: 50%;" onMouseOver="toggleOptionsMenu(<%= comment.getId()%>)" 
                                 onMouseOut="hideOptionsMenu(<%= comment.getId()%>)">
                            <!-- Nguoi tao comment khong the tu report chinh minh -->
                                <% if(acc == null || acc.getId() != comment.getUserId()){ %>
                                    <a href="report?id=<%=comment.getId()%>&&productId=<%=comment.getProdcutId()%>" class="p-5" style="cursor: pointer; color: red; text-decoration: none; line-height: 15px">Report</a>
                                <% } %>
                                <% if(acc != null){ %>
                            <!--Chi nguoi tao moi co the sua comment do-->
                                    <% if(acc.getId() == comment.getUserId()) {%>
                                    <div class="p-5" style="cursor: pointer; text-decoration: none; line-height: 15px" onclick="editComment(<%=comment.getId()%>)">Edit</div>
                                    <% } %>
                            <!--Ca admin va nguoi tao comment co the xoa comment do-->    
                                    <% if (Integer.parseInt(acc.getRole_id()) == 1 || acc.getId() == comment.getUserId()) { %>
                            <!--Khi delete se truyen id cua variant con id user se xac dinh o severlet user de xac dinh comment-->
                                    <a href="delete-comment?id=<%=comment.getId()%>&&productId=<%=comment.getProdcutId()%>" class="p-5" style="cursor: pointer; color: red;text-decoration: none; line-height: 15px"">Delete</a>
                                    <% } %>
                                <% } %>
                            </div>
                            
                        </div>
                        <!-- -End- -->
                    </div>
                    <div class="comment-text">
                        <form action="update-comment" method="get" class="flex bordered">
                            <input value="<%=comment.getId()%>" type="hidden" name="id">
                            <input value="<%=comment.getProdcutId()%>" type="hidden" name="productId">
                            <textarea type="text" name="content" id="content-<%=comment.getId()%>" style="width: 98%; height: auto" disabled><%=comment.getContent()%></textarea>
                            <button type="submit" id="submit-<%=comment.getId()%>" class="hidden">
                                <span class="material-symbols-outlined">
                                    keyboard_return
                                </span>
                            </button>
                        </form>
                    </div>
                </div>
                <% }%>
            </div>
        </div>
    </div>

    <jsp:include page="../screens/footer.jsp"></jsp:include>
    <script src="js/productDetail.js"></script>
</body>
</html>