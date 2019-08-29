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
        .boardNum {
            width: 100px;
            text-align: center;
        }
        .cont {
            width: 500px;
        }
        .userId {
            width: 100px;
            text-align: center;
        }
        .reg_dt {
            width: 150px;
            text-align: center;
        }

        th {
            text-align: center;
        }
    </style>

    <script type="text/javascript">
        /*$(document).on('click', '.postData', function () {
            // var postId = $(this).children().eq(0).text();

        });*/
        $(function () {
            $('.postData').on('click', function () {
                $('#postId').val($(this).data("postid"));
                $('#frm').submit();
            });
        });
    </script>
</head>

<body>
<form action="${cp}/postDetail" method="get" id="frm">
    <input type="hidden" id="postId" name="postId">
</form>

<%@include file="/commonJsp/header.jsp"%>

<div class="container-fluid">
    <div class="row">

        <div class="col-sm-3 col-md-2 sidebar">
            <%@include file="/commonJsp/left.jsp"%>
        </div><div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


        <div class="blog-header">
            <h1 class="blog-title">게시판</h1>
        </div>

        <div class="row">

            <div class="col-sm-8 blog-main">
                <div class="container">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th class="boardNum">게시글 번호</th>
                                <th class="cont">제목</th>
                                <th class="userId">작성자 아이디</th>
                                <th class="reg_dt">작성일시</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${allPost}" var="post">
                            <tr class="postData" data-postId="${post.POSTID}">
                                <td class="boardNum">${post.POSTID}</td>
                                <td><c:forEach var="i" begin="1" end="${post.LEVEL-1}">&nbsp;&nbsp;&nbsp;</c:forEach>
                                        <c:if test="${post.LEVEL > 1}">└ </c:if> ${post.TITLE}</td>
                                <td class="userId">${post.USERID}</td>
                                <td class="reg_dt">${post.REG_DATE}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <%--게시글 등록 버튼--%>
                <a href="${cp}/createPost" class="btn btn-default pull-right">게시글 등록</a>

                <div class="text-center">
                    <ul class="pagination">
                        <c:choose>
                            <c:when test="${pageNum==1}">
                                <li class="disabled">
                                    <span aria-hidden="true">&laquo;</span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="${cp}/post?page=${pageNum-1}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:otherwise>
                        </c:choose>

                        <c:forEach begin="1" end="${paginationSize}" var="page">

                            <%--                            <li <c:if test="${page == pageNum}"> class="active"</c:if> ><a href="${cp}/userPagingList?page=${page}&pageSize=10">${page}</a></li>--%>
                            <c:choose>
                                <c:when test="${page==pageNum}">
                                    <li class="active"><span>${page}</span></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="${cp}/post?page=${page}">${page}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <%--                     다음 버튼 --%>
                        <c:choose>
                            <c:when test="${pageNum==paginationSize}">
                                <li class="disabled">
                                    <span aria-hidden="true">&raquo;</span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="${cp}/post?page=${pageNum+1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>

            </div>
            <!-- /.blog-main -->
        </div>	</div>
    </div>
</div>
</body>
</html>
