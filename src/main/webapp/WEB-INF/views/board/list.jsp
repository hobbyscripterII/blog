<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Home</title>
    <jsp:include page="../layout/head.jsp"/>
</head>

<style>
    .table>thead {
        text-align: center;
    }

    tbody > tr {
        text-align: center;
    }
</style>

<body>
<jsp:include page="../layout/header.jsp"/>
<jsp:include page="../layout/nav.jsp"/>
<div class="content">
    <h2 style="color: black; text-align: center">${board.name} 게시판</h2>

    <div class="div-board-list-wrap">
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col" style="width: 10%">글 번호</th>
                <th scope="col" style="width: 50%">제목</th>
                <th scope="col" style="width: 20%">작성자</th>
                <th scope="col" style="width: 20%">작성일</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="l" items="${list}">
                <tr class="table-active">
                    <th scope="row"><c:out value="${l.boardId}" /></th>
                    <th scope="row"><a class="a-board-title" href="<c:url value="/board/read?board_id=${l.boardId}" />"><c:out value="${l.title}" /></a></th>
                    <th scope="row"><c:out value="${l.nm}" /></th>
                    <th scope="row"><c:out value="${l.createdAt}" /></th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
