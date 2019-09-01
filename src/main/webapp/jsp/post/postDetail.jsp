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
        textarea.form-control {
            width: 400px;
        }

        .comment {
            display: inline-block;
        }

        div.row {
            width: 100%;
        }
        .fileList {
            display: inline-block;
        }
        td {
            padding: 10px;
        }

        .btnLine {
            display: inline-block;
        }
    </style>

    <script>
        $(function () {
            $('#regCom').on('click', function () {
                var postId = $('#postId').val();
                var userId = $('#userId').val();
                var cont = $("#cont").val();
                $.ajax({
                    url : '/regCom',
                    type : 'post',
                    data : {'postId' : postId, 'userId' : userId, 'cont' : cont},
                    dataType : 'json',
                    success : function (data) {
                        var code = "";
                        $.each(data, function (i, v) {
                            code += '<label id="post_com" class="control-label">' + v.CONT + ' [ ' + v.USERID + ' / ' + v.REG_DATE +' ]</label><br>'
                        });
                        $('#commentList').html(code);
                        $('#cont').val('');
                    }, error : function (xhr) {
                        alert("상태 : " + xhr.status)
                    }
                })
            });
        });
    </script>
</head>

<body>
<%@include file="/commonJsp/header.jsp"%>
<%--<form id="frm" action="${cp}/regCom" method="post">--%>
<input type="hidden" id="postId" name="postId" value="${post.POSTID}">
<input type="hidden" id="userId" name="userId" value="${user.userid}">

<div class="container-fluid">
    <div class="row">

        <div class="col-sm-3 col-md-2 sidebar">
            <%@include file="/commonJsp/left.jsp"%>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


        <%--<div class="blog-header">
            <h1 class="blog-title">Board</h1>
            <p class="lead blog-description">2019.09.04 제출</p>
        </div>--%>

            <div class="form-group">
                <label for="title" class="col-sm-1 control-label">제목</label>
                <div class="col-sm-10">
                    <label id="title" class="control-label">${post.TITLE}</label>
                </div>
            </div>
            <br>

            <div class="form-group">
                <label for="post_file" class="col-sm-10 control-label">내용</label>
                <div class="col-sm-10">
                    <label id="post_file" class="control-label">${post.CONT}</label>
                </div>
            </div>
            <br>

            <div class="form-group">
                <label for="post_cont" class="col-sm-10 control-label">첨부파일</label>
                <div id="fileList" class="col-sm-10 btnLine">
                    <c:forEach items="${postFile}" var="file">
<%--                        <label id="post_cont" class="control-label">${file.FILENM}</label><br>--%>
<%--                        <a href="${cp}/download?path=${file.REALFILEPATH}" download="${file.FILENM}">${file.FILENM}<br>--%>
                        <a href="${cp}/download?filename=${file.FILENM}&path=${file.REALFILEPATH}">${file.FILENM}<br>
                    </c:forEach>
                </div>
                <div class="col-sm-4 btnLine">
                    <%--<button type="button" class="modify btn btn-success">수정</button>
                    <button type="button" class="modify btn btn-success">삭제</button>
                    <button type="button" class="modify btn btn-success">답글</button>--%>
                    <c:if test="${post.USERID==user.userid}">
                        <a href="${cp}/postForm?boardId=${param.boardId}&postId=${param.postId}" class="modify btn btn-success pull-right">수정</a>
                        <a href="${cp}/delPost?boardId=${param.boardId}&postId=${param.postId}" class="modify btn btn-success pull-right">삭제</a>
                    </c:if>
                    <a href="${cp}/postForm?boardId=${param.boardId}&parentId=${param.postId}" class="modify btn btn-success pull-right">답글</a>
                </div>
            </div>
            <br><br><br>

            <div class="form-group">
                <label for="post_com" class="col-sm-10 control-label">댓글</label>
                <div id="commentList" class="col-sm-10">
                    <c:forEach items="${postCom}" var="com">
                        <label id="post_com" class="control-label">${com.CONT} [ ${com.USERID} / ${com.REG_DATE} ]</label><br>
                    </c:forEach>
                </div>
            </div>
            <br><br>

            <div class="form-group">
                <div class="col-sm-1"> </div>
                <div class="col-sm-4">
                    <textarea class="form-control" rows="3" id="cont" name="cont"></textarea>
                </div>
                <div class="col-sm-2">
                    <button id="regCom" type="button" class="modify btn btn-success">댓글 저장</button>
                </div>
            </div>
            <br>

       <%-- <div class="row">
            <div class="col-sm-8 blog-main">
                <div>
                    <label>제목</label>
                    <span >${post.TITLE}</span><br>
                </div>
                <div>
                    <label>제목</label>
                    <span>${post.CONT}</span><br>
                </div>
                <div>
                    <table border="0">
                        <tr>
                            <td>
                                <label>첨부파일</label>
                            </td>
                            <td>
                                <c:forEach items="${postFile}" var="file">
                                    <span>${file.FILENM}</span><br>
                                </c:forEach>
                            </td>
                            <td>
                                <button type="button" class="modify btn btn-success">수정</button>
                                <button type="button" class="modify btn btn-success">삭제</button>
                                <button type="button" class="modify btn btn-success">답글</button>
                            </td>
                        </tr>
                    </table>
                </div>
                <div>
                    <div id="comment" class="comment">
                        <label>댓글</label>
                        <div class="comList">
                            <c:forEach items="${postCom}" var="com">
                                <div>
                                    <span>${com.CONT}</span><span>[ ${com.USERID} / ${com.REG_DATE} ]</span>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="form-group" id="com">
                        <textarea class="form-control" rows="3" id="cont" name="cont"></textarea>
                    </div>
                    <div>
                        <button id="regCom" type="button" class="modify btn btn-success">댓글 저장</button>
                    </div>
                </div>
            </div>
        </div>--%>
    </div>
    </div>
</div>
</body>
</html>
