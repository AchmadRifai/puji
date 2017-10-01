<%@page contentType="text/html" pageEncoding="UTF-8"%><%
    if(null!=request.getSession().getAttribute("peg"))response.sendRedirect("dash.php");
%><!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <title>Login Karyawan | Ayam Bakar Wong Solo</title>
      <link rel="stylesheet" href="css/style.css">
</head>
<body>
  <div class="login-page">
  <div class="form">
    <form class="register-form">
      <input type="text" placeholder="name"/>
      <input type="password" placeholder="password"/>
      <input type="text" placeholder="email address"/>
      <button>create</button>
      <p class="message">Already registered? <a href="#">Sign In</a></p>
    </form>
      <form class="login-form" method="POST" action="login.php">
        <input type="text" placeholder="username" name="id"/>
        <input type="password" placeholder="password" name="pass"/>
      <button name="masuk">login</button>
      <%
          if(null!=request.getParameter("error"))
            out.print("<p>"+request.getParameter("error")+"</p>");
      %>
    </form>
  </div>
</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
    <script src="js/index.js"></script>
</body>
</html>