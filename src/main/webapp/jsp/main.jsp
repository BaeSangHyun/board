<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Jsp</title>

    <%@include file="/commonJsp/basicLib.jsp"%>
</head>

<body>
<%@include file="/commonJsp/header.jsp"%>

<div class="container-fluid">
    <div class="row">

        <div class="col-sm-3 col-md-2 sidebar">
            <%@include file="/commonJsp/left.jsp"%>
        </div><div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


        <div class="blog-header">
            <h1 class="blog-title">Board</h1>
            <p class="lead blog-description">2019.09.04 제출</p>
        </div>

        <div class="row">

            <div class="col-sm-8 blog-main">


            </div>
            <!-- /.blog-main -->
        </div>	</div>
    </div>
</div>
</body>
</html>
