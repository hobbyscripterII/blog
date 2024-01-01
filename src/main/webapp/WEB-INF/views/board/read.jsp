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
                    <div id="th-board-contents"></div>
                </th>
            </tr>
        </table>
        <div>
            <button type="button" class="btn btn-light" onclick="location.href='/board/list?category_id=${board.categoryId}'">목록</button>
            <button type="button" id="" class="btn btn-success">수정</button>
            <button type="button" id="btn-board-del" class="btn btn-danger">삭제</button>
        </div>
    </div>
</div>
</body>

<script>
    $('#btn-board-del').onclick(function() {
        if(confirm('게시글을 삭제하시겠습니까? 삭제된 게시글은 복구할 수 없습니다.')) {
            $.ajax({
                type: 'post',
                url: '/board/delete',
                data: {"boardId" : ${board.boardId}},
                success: function(data) {
                    if(data == 0) {
                        alert('삭제에 실패했습니다. 잠시 후 시도해주세요.');
                    } else {
                        alert('게시글 삭제가 완료되었습니다. 목록 화면으로 이동합니다.');
                        location.href = '/board/list?category_id=' + ${board.categoryId};
                    }
                }
            })
        }
    });

    $(document).ready(function () {
        document.getElementById('th-board-contents').innerHTML = `${board.contents}`;
    });
</script>
</html>