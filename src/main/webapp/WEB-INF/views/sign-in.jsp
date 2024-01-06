<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Home</title>
    <jsp:include page="layout/head.jsp"/>
</head>

<body>
<div class="content-wrap">
    <jsp:include page="layout/header.jsp"/>
    <jsp:include page="layout/nav.jsp"/>
    <div class="content">
        <h2 style="color: black; text-align: center">로그인</h2>

        <div class="div-sign-up-form-wrap">
            <form:form modelAttribute="dto" id="form-sign-in" action="/sign-in" method="post" autocomplete="false">
                <div class="div-error-msg"><form:errors /></div>
                <div class="form-group">
                    <label class="form-label mt-2">아이디</label>
                    <form:input type="text" class="form-control" name="uid" path="uid" placeholder="아이디를 입력하세요." autocomplete="off" /> <!-- autocomplete="off": 자동 완성 기능 off -->
                </div>
                <div class="form-group">
                    <label class="form-label mt-4">비밀번호</label>
                    <form:input type="password" class="form-control" name="upw" path="upw" placeholder="비밀번호를 입력하세요." autocomplete="off" />
                </div>
                <input type="button" class="btn btn btn-success mt-4" value="로그인" id="btn-sign-up" style="width: 100%">
            </form:form>
        </div>
    </div>
</div>
</body>

<script>
    $('#btn-sign-up').click(function () {
        let uid = $('input[name=uid]');
        let upw = $('input[name=upw]');

        if (!uid.val()) {
            alert('아이디를 입력해주세요.');
            uid.focus();
        } else if(!upw.val()) {
            alert('비밀번호를 입력해주세요.');
            upw.focus();
        } else {
            $('#form-sign-in').submit();
        }
    });
</script>
</html>