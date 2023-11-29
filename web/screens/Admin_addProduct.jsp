<%-- 
    Document   : PhamNgocHoa
    Created on : Jun 13, 2023, 1:19:39 PM
    Author     : 84834
--%>

<%@page import="model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>
        <title>Admin | Edit Product</title>
        <style>
            .content {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            .content {
                width: 100%;
                height: 100vh;
                display: flex;
            }
            .side-bar {
                position: sticky;
                width: 20%;
                height: 100%;
                background-color: #333;
            }
            .side-bar ul {
                width: 100%;
                height: 100%;
                list-style: none;
            }
            .side-bar ul li {
                width: 100%;
                height: 10vh;
                display: flex;
                align-items: center;
                justify-content: center;
            }
            .side-bar ul li a {
                text-decoration: none;
                color: #fff;
                font-size: 1.5rem;
            }
            .side-bar ul li a:hover {
                color: #ccc;
            }
            .main {
                width: 80%;
                height: 100%;
                background-color: #ccc;
                display: block;
            }
            .main h2 {
                text-align: center;
                margin: 1rem;
            }
            form{
                height: 100%;
                margin-left: 20%;
                display: flex;
                flex-direction: column;
                align-items: flex-start;
                justify-content: left;
            }
            form input{
                width: 50%;
                height: 40px;
                margin: 5px;
                border: 1px solid #333;
                border-radius: 5px;
                padding: 0 1rem;
            }
            form input:focus{
                outline: none;
            }
            form button{
                width: 30%;
                height: 5vh;
                margin: 5px;
                margin-left: 20%;
                border: 1px solid #333;
                border-radius: 5px;
                padding: 0 1rem;
                background-color: #333;
                color: #fff;
                font-size: 1.5rem;
                cursor: pointer;
            }
            form button:hover{
                background-color: #ccc;
                color: #333;
            }
    </style>
</head>

<body>
    <div class="header-container">
        <jsp:include page="../screens/navbar.jsp"></jsp:include>
    </div>
    <div class="content">
        <div class="side-bar">
            <ul>
                <li>
                    <a href="dashboard.html">Dashboard</a>
                </li>
                <li>
                    <a href="Order.html">Order</a>
                </li>
                <li>
                    <a href="add-product.html">Product</a>
                </li>
                <li>
                    <a href="sales.html">Sales</a>
                </li>
                <li>
                    <a href="account.html">Account</a>
                </li>
                <li>
                    <a href="#">Shipping</a>
                </li>
            </ul>
        </div>

        <div class="main">
            <form action="update" method="post">
                <label for="product_name">Product Name</label>
                <input type="text" name="product_name" id="product_name">

                <label for="product_price">Product Price</label>
                <input type="text" name="product_price" id="product_price" >

                <label for="product_description">Product Description</label>
                <input type="text" name="product_description" id="product_description" >

                <label for="product_screen">Product Screen</label>
                <input type="number" min="0" step="0.1" name="product_screen" id="product_screen" >

                <label for="product_camera">Product Camera</label>
                <input type="number" min="0" name="product_camera" id="product_camera" >

                <label for="product_ram">Product Ram</label>
                <input type="number" min="0" name="product_ram" id="product_ram" >

                <label for="product_pin">Product pin</label>
                <input type="number" min="0" name="product_pin" id="product_pin" >

                <label for="product_Chipset">Product Chipset</label>
                <input type="text" name="product_Chipset" id="product_Chipset" >

                <label for="Screen_resolution">Screen_resolution</label>
                <input type="text" name="Screen_resolution" id="Screen_resolution" >

                <label for="product_image">Product Image</label>
                <input type="file" name="product_image" id="product_image" v>

                <button type="submit">Add Product</button>
            </form>
        </div>
    </div>
    <jsp:include page="../screens/footer.jsp"></jsp:include>s
</body>
</html>