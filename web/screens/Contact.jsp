<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Contact</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
  <style>
    body {
      font-family: Cambria, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
      
    }

    .container {
      max-width: 350px;
      margin: 20px auto;
      padding: 20px;
      background-color: #fff;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(68, 65, 65, 0.1);
      color: rgb(100, 78, 56);
    }
    #background {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                z-index: -1;
            }
    #background img {
                display: block;
                width: 100%;
                height: 100%;
                object-fit: cover;
            }
    h1 {
      text-align: center;
    }
    header {
                background-color: #F9F9F9;
                padding: 10px;
                text-align: center;
            }
    header h1 {
                margin: 0;
            }

    .contact-info {
      margin-bottom: 20px;
    }

    .contact-info label {
      display: flex;
      align-items: center;
      font-weight: bold;
    }

    .contact-info label .fa {
      margin-right: 10px;
    }

    .contact-info p {
      margin: 0;
    }

    .contact-info p:not(:last-child) {
      margin-bottom: 5px;
    }
    
    footer {
                background-color: #F9F9F9;
                text-align: center;
                position: fixed;
                bottom: 0;
                width: 100%;
            }

  </style>
</head>
<body>
    <div id="background">
        <img src="./img/banner.jpg" alt="Background">
    </div>

    <header>
        <a style="text-decoration: none; color: black" href="home"><h1>DURIAN STORE</h1></a>
    </header>
    
  <div class="container">
    <h1>CONTACT US</h1>
    <hr>
    <div class="contact-info">
      <label>
        <i class="fas fa-map-marker-alt"></i>
        
      </label>
      <p>123 Linh Duong, Hoang Liet, Hoang Mai, Ha Noi</p>
      
      <label>
        <i class="fas fa-phone"></i>
        
      </label>
      <p>068 6868 680</p>
      
      <label>
        <i class="fas fa-envelope"></i>
        
      </label>
      <p>Durian@store.com</p>
    </div>
  </div>

  <footer>
    <p>Copyright © 2023 DURIAN STORE</p>
</footer>
</body>
</html>