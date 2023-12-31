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
                <th rowspan="2" scope="row">제목</th>
                <th scope="row"><input type="text" class="form-control title" /></th>
                <%--                <th scope="row"><c:out value="${board.title}" /></th>--%>
            </tr>
            <tr class="table">
                <th scope="row" style="display: flex">
                    <textarea class="form-control contents" id="textarea-board-form" rows="3" style="resize: none"></textarea>
                    <div id="div-markdown-contents"></div>
                </th>
            </tr>
        </table>
        <div class="div-board-write-wrap">
            <button type="button" id="btn-board-write" class="btn btn-info">글 등록</button>
        </div>
    </div>
</div>
</body>

<script>
    const boardWriteContents = $('#textarea-board-form');
    boardWriteContents.keyup(function() {
        document.getElementById('div-markdown-contents').innerHTML = marked.parse(boardWriteContents.val());
    });

    $('#btn-board-write').click(function() {
        let title = $('.title');
        let contents =  $('.contents');

        if (!title.val()) {
            alert('제목을 입력해주세요.');
            title.focus();
        } else if(!contents.val()) {
            alert('내용을 입력해주세요.');
            contents.focus();
        } else {
            let data = {"title" : title.val(), "contents" : contents.val()};
            $.ajax({
                type: 'post',
                url: '/board/write?category_id=' + ${categoryId},
                dataType: 'json',
                contentType: 'application/json; charset:UTF-8',
                data: JSON.stringify(data),
                success: function(data) {
                    if(data == 0) {
                        alert('글 등록에 실패했습니다. 잠시 후 다시 이용해주세요.');
                    } else {
                        if(confirm('글이 등록되었습니다. 등록된 글을 확인하러 가시겠습니까?')) {
                            location.href='/board/read?board_id=' + data;
                        } else {
                            alert('게시글 목록 화면으로 이동합니다.');
                            location.href='/board/list?category_id=2';
                        }
                    }
                }
            })
        }
    });
</script>
</html>