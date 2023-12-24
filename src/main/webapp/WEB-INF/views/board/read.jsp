<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Home</title>
    <jsp:include page="../layout/head.jsp"/>
</head>

<body>
<jsp:include page="../layout/header.jsp"/>
<jsp:include page="../layout/nav.jsp"/>
<div class="content">
<%--    <h2>${board.name} 게시판</h2>--%>

    <div class="div-board-list-wrap">
        <table class="table">
                <tr class="table-active">
                    <th colspan="2" scope="row">제목</th>
                    <th colspan="2" scope="row"><c:out value="${board.title}" /></th>
                </tr>
                <tr class="table-active">
                    <th colspan="2" scope="row">작성자</th>
                    <th colspan="2" scope="row"><c:out value="${board.nm}" /></th>
                </tr>
                <tr class="table-active">
                    <th scope="row">작성일</th>
                    <th scope="row"><c:out value="${board.createdAt}" /></th>
                    <th scope="row">수정일</th>
                    <th scope="row"><c:out value="${board.updatedAt}" /></th>
                </tr>
                <tr class="table-active">
                    <th colspan="2" scope="row">내용</th>
                    <th colspan="2" scope="row"><c:out value="${board.contents}" /></th>
                </tr>
        </table>
    </div>
</div>
</body>
</html>
