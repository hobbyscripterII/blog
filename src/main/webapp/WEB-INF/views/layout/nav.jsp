<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
    .li-nav {
        list-style-type: none;
    }
</style>

<nav>
                    <li class="li-nav">
                        <a class="" href="/">home
                        </a>
                    </li>
                    <li class="li-nav">
                        <a class="" href="/board/list?category_id=1">diary</a>
                    </li>
                    <li class="li-nav">
                        <a class="" href="/board/list?category_id=2">study</a>
                    </li>
                    <li class="li-nav">
                        <a class="" href="#">setting</a>
                    </li>

                    <c:choose>
                        <c:when test="${sessionScope.IUSER == null}">
                            <li class="li-nav">
                                <a class="nav-link" data-bs-toggle="modal" data-bs-target="#modal-login" style="cursor:pointer">sign in</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="li-nav">
                                <a class="nav-link" id="a-logout" style="cursor: pointer">logout</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
</nav>

<%--<nav class="navbar navbar-expand-lg bg-dark" data-bs-theme="dark">--%>
<%--    <div class="container-fluid">--%>
<%--        <a class="navbar-brand" href="/">my blog</a>--%>
<%--        <div class="navbar-collapse collapse" id="navbarColor02" style="">--%>
<%--            <ul class="navbar-nav me-auto">--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link active" href="/">home--%>
<%--                    </a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" href="/board/list?category_id=1">diary</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" href="/board/list?category_id=2">study</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" href="#">setting</a>--%>
<%--                </li>--%>

<%--                <c:choose>--%>
<%--                    <c:when test="${sessionScope.IUSER == null}">--%>
<%--                        <li class="nav-item">--%>
<%--                            <a class="nav-link" data-bs-toggle="modal" data-bs-target="#modal-login" style="cursor:pointer">sign in</a>--%>
<%--                        </li>--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        <li class="nav-item">--%>
<%--                            <a class="nav-link" id="a-logout" style="cursor: pointer">logout</a>--%>
<%--                        </li>--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>


<%--                <li class="nav-item dropdown">--%>
<%--                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">etc</a>--%>
<%--                    <div class="dropdown-menu">--%>
<%--                        <a class="dropdown-item" href="/board/list?category_id=3">like</a>--%>
<%--                        <div class="dropdown-divider"></div>--%>
<%--                        &lt;%&ndash;                            <a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#modal-login">sign in</a>&ndash;%&gt;--%>
<%--                        <a class="dropdown-item" href="/sign-up">sign up</a>--%>
<%--                    </div>--%>
<%--                </li>--%>
<%--            </ul>--%>
<%--            <form class="d-flex">--%>
<%--                <input class="form-control me-sm-2" type="search" placeholder="Search">--%>
<%--                <button class="btn btn-secondary my-2 my-sm-0" type="submit">search</button>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</nav>--%>

<div class="modal" id="modal-login">
    <div class="modal-dialog modal-dialog-centered" role="document"> <!-- modal-dialog-centered: 모달 세로 중간 정렬 -->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">로그인</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"></span>
                </button>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label class="form-label">아이디</label>
                    <input type="text" id="uid" class="form-control" autocomplete="off">
                </div>
                <div class="form-group">
                    <label class="form-label mt-4">비밀번호</label>
                    <input type="password" id="upw" class="form-control" autocomplete="off">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="btn-sign-in" class="btn btn-info">로그인</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
            </div>
        </div>
    </div>
</div>

<script>
    $('#btn-sign-in').click(function () {
        let uid = $('#uid');
        let upw = $('#upw');

        if (!uid.val()) {
            alert('아이디를 입력해주세요.');
            uid.focus();
        } else if(!upw.val()) {
            alert('비밀번호를 입력해주세요.');
            upw.focus();
        } else {
            let data = {"uid" : uid.val(), "upw" : upw.val()};
            $.ajax({
                type: 'post',
                url: 'sign-in',
                dataType: 'json',
                contentType: 'application/json; charset:UTF-8',
                data: JSON.stringify(data),
                success: function(data) {
                    if(data == 0) {
                        alert('아이디 혹은 비밀번호를 확인해주세요.');
                    } else {
                        alert('로그인에 성공했습니다. 메인 화면으로 이동합니다.');
                        location.href='/';
                    }
                }
            })
        }
    });

    $('#a-logout').click(function() {
        if(confirm('로그아웃하시겠습니까?')) {
            alert('로그아웃이 완료되었습니다.');
            location.href = '/logout';
        }
    });
</script>