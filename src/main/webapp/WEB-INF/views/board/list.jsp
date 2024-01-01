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
    <h2 style="color: black; text-align: center">${board.boardName} 게시판</h2>

    <div class="div-board-list-wrap">
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col" style="width: 10%">글 번호</th>
                <th scope="col" style="width: 45%">제목</th>
                <th scope="col" style="width: 20%">작성자</th>
                <th scope="col" style="width: 25%">작성일</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="l" items="${list}">
                <tr class="table">
                    <th scope="row"><c:out value="${l.boardId}"/></th>
                    <th scope="row"><a class="a-board-title" href="<c:url value="/board/read?board_id=${l.boardId}" />"><c:out value="${l.title}"/></a></th>
                    <th scope="row"><c:out value="${l.nm}"/></th>
                    <th scope="row"><c:out value="${l.createdAt}"/></th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="div-pagination-wrap">
            <ul class="pagination pagination-sm">
                <li class="page-item">
                    <a class="page-link" href="/board/list?category_id=${board.categoryId}&page=1&amount=${pageNation.amount}">&laquo;</a>
                </li>
                <!-- begin: 반복에 사용할 첫번째 항목의 index, end: 반복에 사용할 마지막 항목의 index  -->
                <c:forEach var="num" begin="${pageNation.start}" end="${pageNation.end}">
                    <li class="page-item">
                        <a class="page-link" href="/board/list?category_id=${board.categoryId}&page=${num}&amount=${pageNation.amount}">${num}</a>
                    </li>
                </c:forEach>
                <li class="page-item">
                    <a class="page-link" href="/board/list?category_id=${board.categoryId}&page=${pageNation.realEnd}&amount=${pageNation.amount}">&raquo;</a>
                </li>
            </ul>
        </div>

        <c:if test="${null != sessionScope.USER_ID}">
            <div class="div-write-btn-wrap">
                <button type="button" class="btn btn-info" onclick="location.href='write?category_id=${board.categoryId}'">글 작성</button>
            </div>
        </c:if>

        <div style="display: flex; justify-content: center">
            <form class="d-flex" action="" style="width: 400px; margin-left: 90px">
                <input class="form-control me-sm-2" id="input-search" type="search" placeholder="검색어를 입력하세요.">
                <button class="btn btn-secondary my-2 my-sm-0" id="btn-search" type="button">Search</button>
            </form>
        </div>
    </div>
</div>
</body>

<script>
    $('#btn-search').click(function () {
        const search = $('#input-search').val();
        location.href = '/board/list?category_id=${board.categoryId}&keyword=' + search;
    });

    $('#input-search').keypress(function (e) {
        if(e.keyCode === 13) {
            const search = $('#input-search').val();
            alert(search);
        }
    });
</script>
</html>