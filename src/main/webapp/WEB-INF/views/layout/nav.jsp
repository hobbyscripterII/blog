<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav>
    <nav class="navbar navbar-expand-lg bg-dark" data-bs-theme="dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">my blog</a>
            <div class="navbar-collapse collapse" id="navbarColor02" style="">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="/">home
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">diary</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">study</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">setting</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">etc</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#">like</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#modal-login">sign in</a>
                            <a class="dropdown-item" href="/sign-up">sign up</a>
                        </div>
                    </li>
                </ul>
                <form class="d-flex">
                    <input class="form-control me-sm-2" type="search" placeholder="Search">
                    <button class="btn btn-secondary my-2 my-sm-0" type="submit">search</button>
                </form>
            </div>
        </div>
    </nav>
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