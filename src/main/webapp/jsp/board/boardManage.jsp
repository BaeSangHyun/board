<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

    <script>
        $(function () {
            $('#create').on('click', function () {
                $('#frm').submit();
            });

            $('button').on('click', '.modify', function () {

            });
        });
    </script>
</head>

<body>
<%@include file="/commonJsp/header.jsp"%>

<div class="container-fluid">
    <div class="row">

        <div class="col-sm-3 col-md-2 sidebar">
            <%@include file="/commonJsp/left.jsp"%>
        </div><div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


        <div class="blog-header">
            <h1 class="blog-title">게시판 생성</h1>
        </div>

        <div class="row">
            <form action="${cp}/createBoard" method="post">
                <input type="hidden" value="${user.userid}">
                <div class="col-sm-8 blog-main">
                    <div>
                        <label for="boardNm">게시판 이름</label>
                        <input type="text" id="boardNm" name="boardNm">
                        <select name="useable">
                            <option value="T" selected>사용</option>
                            <option value="F">미사용</option>
                        </select>
                        <button type="button" id="create">생성</button>
                    </div>
                    <c:forEach items="${boardList}" var="board">
                        <div>
                            <label for="boardNm">게시판 이름</label>
                            <input type="text" name="boardNm" value="${board.BOARDNM}" readonly>
                            <select name="useable">
                                <option value="T" selected>사용</option>
                                <option value="F">미사용</option>
                            </select>
                            <button type="button" class="modify">수정</button>
                        </div>
                    </c:forEach>
                </div>
            </form>
            <!-- /.blog-main -->
        </div>	</div>
    </div>
</div>
</body>
</html>
