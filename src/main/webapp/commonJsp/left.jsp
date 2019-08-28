<%--
  Created by IntelliJ IDEA.
  User: PC-20
  Date: 2019-08-08
  Time: 오후 2:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="nav nav-sidebar">
    <li class="active"><a href="${cp}/boardManage">게시판 생성 <span class="sr-only">(current)</span></a></li>
<%--    <li class="active"><a href="${cp}/freeBoard">자유게시판</a></li>--%>
<%--    <li class="active"><a href="${cp}/QnABoard">Q&A 게시판</a></li>--%>
    <c:forEach items="${boardList}" var="board">
        <li class="active"><a href="${cp}/board?boardId=${board.BOARDID}">${board.BOARDNM}</a></li>
    </c:forEach>
</ul>