<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Home</title>
    <jsp:include page="../layout/head.jsp"/>
</head>

<body>
<div class="content-wrap">
    <jsp:include page="../layout/header.jsp"/>
    <jsp:include page="../layout/nav.jsp"/>
    <div class="content">
        <h2 style="color: black; text-align: center">${board.boardName} 게시판</h2>

        <div class="div-board-list-wrap">
            <table class="table">
                <tr>
                    <th scope="row">
                        <c:choose>
                            <c:when test="${board.boardName == '일기'}">
                                기분
                            </c:when>
                            <c:otherwise>
                                말머리
                            </c:otherwise>
                        </c:choose>
                    </th>
                    <th scope="row">
                        <select class="form-select" name="subject" id="subject" style="width: 200px">
                            <option value="null" label=" "></option>
                            <c:forEach var="s" items="${subject}">
                                <option id="subjectName" value="${s.subjectId}" label="${s.subjectName}"></option>
                            </c:forEach>
                        </select>
                    </th>
                </tr>
                <tr class="table">
                    <th rowspan="2" scope="row">제목</th>
                    <th scope="row">
                        <c:choose>
                            <c:when test="${null != dto}">
                                <input type="text" class="form-control title" value="${dto.title}" />
                            </c:when>
                            <c:otherwise>
                                <input type="text" class="form-control title" />
                            </c:otherwise>
                        </c:choose>
                    </th>
                </tr>
                <tr class="table">
                    <th scope="row" style="display: flex">
                    <textarea class="form-control contents" id="textarea-board-form" rows="3" style="resize: none">
                        <c:out value="${dto.contents}"/>
                    </textarea>
                        <div id="div-markdown-contents"></div>
                    </th>
                </tr>
            </table>
            <div class="div-board-write-wrap">
                <c:choose>
                    <c:when test="${null != dto}">
                        <button type="button" id="btn-board-update" class="btn btn-success">글 수정</button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" id="btn-board-write" class="btn btn-info">글 등록</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
</body>

<script>
    const boardWriteContents = $('#textarea-board-form');
    boardWriteContents.keyup(function() {
        document.getElementById('div-markdown-contents').innerHTML = marked.parse(boardWriteContents.val());
    });

    const title = $('.title');
    const contents = $('.contents');
    const subjectId = $('#subject');

    $('#btn-board-write').click(function() {
        const data = {"subjectId" : subjectId.val(), "title" : title.val(), "contents" : contents.val()};
        const url = '/board/write?category_id=' + `${board.categoryId}`;
        const name = '등록';
        boardAjax(data, url, name);
    });

    $('#btn-board-update').click(function () {
        const data = {"boardId": `${dto.boardId}`, "subjectId" : subjectId.val(), "subjectId" : subjectId.val(), "title" : title.val(), "contents" : contents.val()};
        const url = '/board/update';
        const name = '수정';
        boardAjax(data, url, name);
    });

    function boardAjax(data, url, name) {
        if (!data.title) {
            alert('제목을 입력해주세요.');
            data.title.focus();
        } else if(!data.contents) {
            alert('내용을 입력해주세요.');
            data.contents.focus();
        } else {
            $.ajax({
                type: 'post',
                url: url,
                dataType: 'json',
                contentType: 'application/json; charset:UTF-8',
                data: JSON.stringify(data),
                success: function(data) {
                    if(data == 0) {
                        alert(name +'에 실패했습니다. 잠시 후 다시 이용해주세요.');
                    } else {
                        if (confirm('글이 정상적으로 ' + name + '되었습니다. ' + name + '된 글을 확인하러 가시겠습니까?')) {
                            let path = '/board/read?board_id=';

                            if (name == '등록') {
                                location.href = path + data;
                            } else {
                                location.href = path + `${dto.boardId}`;
                            }
                        } else {
                            alert('게시글 목록 화면으로 이동합니다.');
                            location.href = '/board/list?category_id=' + `${board.categoryId}`;
                        }
                    }
                }
            })
        }
    }
</script>
</html>