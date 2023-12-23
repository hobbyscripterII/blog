<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav>
    <ul>
        <li><a href="/">홈</a></li>
        <li><a>일기</a></li>
        <li><a>공부</a></li>
        <li><a>관심</a></li>
        <li><a>기타</a></li>
        <li><a>설정</a></li>
        <li><a href="sign-up">회원가입</a></li>
        <li><a data-bs-toggle="modal" data-bs-target="#modal-login">로그인</a></li>
    </ul>
</nav>

<div class="modal" id="modal-login">
    <div class="modal-dialog modal-dialog-centered" role="document">
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
                    <input type="text" class="form-control">
                </div>
                <div class="form-group">
                    <label class="form-label mt-4">비밀번호</label>
                    <input type="password" class="form-control" autocomplete="off">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">로그인</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
            </div>
        </div>
    </div>
</div>