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

    <style>
        .form-control {
            width: 150px;
            display: inline-block;
            text-align: center;
        }
        select {
            text-align-last: center;
        }
        .formDiv {
            width: 500px;
        }
    </style>

    <script>
        $(function () {
            $('#create').on('click', function () {
                $('#createFrm').submit();
            });
        });

        function submitForm(idx) {
            $('#form'+idx).submit();
        }
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
                <div class="col-sm-8 blog-main">
                    <form id="createFrm" action="${cp}/createBoard" method="post">
                        <div class="formDiv">
                            <label for="boardNm">게시판 이름</label>
                            <input type="text" id="boardNm" name="boardNm" class="form-control">
                            <select name="useable" class="form-control">
                                <option value="T" selected>사용</option>
                                <option value="F">미사용</option>
                            </select>
                            <button type="submit" id="create" class="btn btn-primary">생성</button>
                        </div>
                    </form>
                    <c:forEach items="${boardList}" var="board">
                        <form id="form${board.BOARDID}" action="${cp}/modifyBoard" method="post">
                            <input type="hidden" id="boardId" name="boardId" value="${board.BOARDID}">
                            <div class="formDiv">
                                <label for="boardNm">게시판 이름</label>
                                <input type="text" name="boardNm" value="${board.BOARDNM}" class="form-control" readonly>
                                <select name="useable" class="form-control">
                                    <option value="T" <c:if test="${board.ABLE == 'T'}">selected</c:if>>사용</option>
                                    <option value="F" <c:if test="${board.ABLE == 'F'}">selected</c:if>>미사용</option>
                                </select>
                                <button type="button" class="modify btn btn-success" onclick="submitForm(${board.BOARDID})">수정</button>
                            </div>
                        </form>
                    </c:forEach>
                </div>
            <!-- /.blog-main -->
        </div>	</div>
    </div>
</div>
</body>
</html>
