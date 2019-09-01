<%--
  Created by IntelliJ IDEA.
  User: PC-20
  Date: 2019-08-28
  Time: 오후 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath()%>/css/signin.css" rel="stylesheet">

    <script src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/js.cookie.js"></script>

    <script>
        $(function () {
           $('#signinBtn').on('click', function () {
               $('#frm').submit();
           });
        });
    </script>
</head>
<body>
<div class="container">
    <form id="frm" class="form-signin" action="${cp}/login" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="userId" class="sr-only">userId</label>
        <input type="text" id="userId" name="userId" class="form-control" placeholder="userId" required autofocus> <!-- value="userId%>"-->
        <label for="pass" class="sr-only">Password</label>
        <input type="password" id="pass" name="pass"
               class="form-control" placeholder="Password" required> <!-- value="brown1234" -->
        <div class="checkbox">
            <%--<label>
                <input id="rememberMe" name="rememberMe" type="checkbox" value="remember-me"> Remember me
            </label>--%>
        </div>
        <button id="signinBtn" class="btn btn-lg btn-primary btn-block" type="button">Sign in</button>
    </form>

</div> <!-- /container -->
</body>
</html>
