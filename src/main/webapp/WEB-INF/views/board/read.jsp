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
    <h2 style="color: black; text-align: center">${board.boardCategoryName} 게시판</h2>

    <div class="div-board-list-wrap">
        <table class="table">
            <tr class="table">
                <th scope="row">제목</th>
                <th scope="row"><c:out value="${board.title}" /></th>
            </tr>
            <tr class="table">
                <th scope="row">작성자</th>
                <th scope="row"><c:out value="${board.writer}" /></th>
            </tr>
            <tr class="table">
                <th scope="row">작성일</th>
                <th scope="row"><c:out value="${board.createdAt}" /></th>
            </tr>
            <tr>
                <c:if test="${board.updatedAt != null}">
                <th scope="row">수정일</th>
                <th scope="row"><c:out value="${board.updatedAt}" /></th>
            </tr>
            </c:if>
            <tr class="table">
                <th colspan="2" scope="row">
                    <div id="th-board-contents" data-contents="${board.contents}"></div>
                </th>
            </tr>
        </table>
    </div>
</div>
</body>

<script>
    $(document).ready(function () {
        // >>>>> 렌더링 불가능
        <%--const contents = `${board.contents}`;--%>
        // >>>>> markdown 의존성 주입 후 이미지 url 없으면 에러 발생
        const contents = $('#th-board-contents').data('contents');
        // >>>>> 렌더링은 되는데 중간에 잘림 현상 발생
        document.getElementById('th-board-contents').innerHTML = marked.parse(contents);
        // document.getElementById('th-board-contents').innerHTML = contents;
    });
</script>
</html>