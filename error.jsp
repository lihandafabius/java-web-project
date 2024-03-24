<%-- 
    Document   : error
    Created on : Mar 24, 2024, 1:01:43?PM
    Author     : User
--%>
 <%@page isErrorPage="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error 404</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
 <header>
    <img src="images/logo1.jpg" alt="Logo" class="logo-img">
    <h1> 404 Error </h1>
</header>
    <div class="center">
<p>The server was not able to find the file you requested</p>
<p>To continue, click the Back button.</p>
</div>
<br>

<footer id="footer">
        <p>&copy; Copyright. <span id="currentYear"></span> Egerton Lost and Found System. All rights reserved.</p>
    </footer>

    <script>
        document.getElementById("currentYear").textContent = new Date().getFullYear();
    </script>
    
</body>
</html>