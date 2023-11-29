<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About Us</title>
        <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>        
        <link rel="stylesheet" href="styles/forgot-password.css" type="text/css"/>
        <link rel="stylesheet" href="styles/about-us.css" type="text/css"/>
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&family=Shadows+Into+Light&display=swap" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
   
  
</head>

<body>
    <div class="header-container">
            <jsp:include page="../screens/navbar.jsp"></jsp:include>
            </div>
    <div class="heading">
        <h1>About Us</h1>
        <p>Durian shop brings Iphone closer to you</p>

    </div>
    <div class="container">
        <section class="about">
            <div class="about-image">
                <img src="https://thesaigontimes.vn/wp-content/uploads/2022/11/Apple-iPhone-14-iPhone-14-Plus-hero-220907_Full-Bleed-Image_jpg_large.jpg">
            </div>
            <div class="about-content">
                <h1>Best place to sell iphones</h1>
                <p> Durian Store is an online store specializing in providing all kinds of Iphone running iOS operating system. A website-based application for users to purchase online, allowing customers to directly place orders and purchase goods at the website itself, while helping to improve the customer experience.</p>
                <a href="store" class="read-more">Shopping Now</a>
            </div>
        </section>
    </div>
            <hr/>
    <jsp:include page="../screens/footer.jsp"></jsp:include>

</body>

</html>