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
                            <c:choose>
                            <c:when test="${null != vo.subjectId}">
                            <option value="${vo.subjectId}" label=" ">
                                    <c:out value="${vo.subjectName}" />
                                </c:when>
                                <c:otherwise>
                            <option value="null" label=" ">
                                </c:otherwise>
                                </c:choose>
                            </option>
                            <c:forEach var="s" items="${subject}">
                                <option id="subjectName" value="${s.subjectId}" label="${s.subjectName}"></option>
                            </c:forEach>
                        </select>
                    </th>
                </tr>
                <tr class="table">
                    <th scope="row">제목</th>
                    <th scope="row">
                        <c:choose>
                            <c:when test="${null != vo}">
                                <input type="text" class="form-control title" value="${vo.title}" />
                            </c:when>
                            <c:otherwise>
                                <input type="text" class="form-control title" />
                            </c:otherwise>
                        </c:choose>
                    </th>
                </tr>
                <!-- CKEditor5 document -->
                <tr class="table">
                    <th colspan="2" scope="row">
                        <div id="toolbar"></div>
                        <div id="editor" data-contents="${vo.contents}"></div>
                        <!-- CKEditor5 classic & markdown -->
                        <%--                        <textarea class="form-control contents" id="editor" rows="3" style="resize: none">--%>
                        <%--                            <c:out value="${vo.contents}"/>--%>
                        <%--                        </textarea>--%>
                    </th>
                </tr>
            </table>
            <div class="div-board-write-wrap">
                <c:choose>
                    <c:when test="${null != vo}">
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
    /** >>>>> CKEditor5 START **/
    let editorContainer;

    /** document **/
    DecoupledEditor.create(document.querySelector('#editor'), {
            language: 'ko'
        }).then(editor => {
            editorContainer = editor;
            const toolbarContainer = document.querySelector('#toolbar');
            toolbarContainer.appendChild(editor.ui.view.toolbar.element);
        }).catch(error => {
            console.log(error);
        });

    /** classic **/
    // .create(document.querySelector('#editor'), {
    //     language: 'ko'
    // })
    // .then(newEditor => {
    //     editor = newEditor;
    // } )
    // .catch(error => {
    //     console.error( error );
    // } );

    $(document).ready(function () {
        const contents = $('#editor').data('contents');
        console.log(contents);

        if (contents != null) {
            editorContainer.setData(`${vo.contents}`);
        }
    });
    /** >>>>> CKEditor5 END **/

    /** >>>>> 게시글 등록/수정 START **/
    const title = $('.title');
    // const contents = editorContainer; // CKEditor5
    // const contents = $('.contents'); // markdown
    const subjectId = $('#subject');

    $('#btn-board-write').click(function() {
        // CKEditor5
        console.log(editorContainer.getData());

        const data = {"subjectId" : subjectId.val(), "title" : title.val(), "contents" : editorContainer.getData()};
        const url = '/board/write?category_id=' + `${board.categoryId}`;
        const name = '등록';
        boardAjax(data, url, name);
    });

    $('#btn-board-update').click(function () {
        const data = {"boardId": `${vo.boardId}`, "subjectId" : subjectId.val(), "title" : title.val(), "contents" : editorContainer.getData()};
        const url = '/board/update';
        const name = '수정';
        console.log(data);
        boardAjax(data, url, name);
    });

    function boardAjax(data, url, name) {
        if (!data.title) {
            alert('제목을 입력해주세요.');
            title.focus();
        } else if(!data.contents) {
            alert('내용을 입력해주세요.');
            contents.focus();
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
                                location.href = path + `${vo.boardId}`;
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