<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Home</title>
    <jsp:include page="layout/head.jsp"/>
</head>

<body>
<jsp:include page="layout/header.jsp"/>
<jsp:include page="layout/nav.jsp"/>
<div class="content">
    <h2 style="color: black; text-align: center">회원가입</h2>
        <h5 style="width: 100%; text-align: center">회원가입 기간이 아닙니다.</h5>

<%--    <div class="div-sign-up-form-wrap">--%>
<%--        <form id="form-sign-up">--%>
<%--            <div class="form-group">--%>
<%--                <label class="form-label mt-4">아이디</label>--%>
<%--                <input type="text" class="form-control" name="uid" placeholder="아이디를 입력하세요." autocomplete="off"> <!-- autocomplete="off": 자동 완성 기능 off -->--%>
<%--            </div>--%>
<%--            <div class="form-group">--%>
<%--                <label class="form-label mt-4">비밀번호</label>--%>
<%--                <input type="password" class="form-control" name="upw" placeholder="비밀번호를 입력하세요." autocomplete="off">--%>
<%--            </div>--%>
<%--            <div class="form-group">--%>
<%--                <label class="form-label mt-4">닉네임</label>--%>
<%--                <input type="text" class="form-control" name="unm" placeholder="닉네임을 입력하세요." autocomplete="off">--%>
<%--            </div>--%>
<%--            <button type="button" class="btn btn btn-success mt-4" id="btn-sign-up" style="width: 100%">회원가입</button>--%>
<%--        </form>--%>
<%--    </div>--%>
</div>
</body>

<script>
    $('#btn-sign-up').click(function () {
        let uid = $('input[name=uid]');
        let upw = $('input[name=upw]');
        let unm = $('input[name=unm]');

        if (!uid.val()) {
            alert('아이디를 입력해주세요.');
            uid.focus();
        } else if(!upw.val()) {
            alert('비밀번호를 입력해주세요.');
            upw.focus();
        } else if(!unm.val()) {
            alert('닉네임을 입력해주세요.');
        } else {
            let data = {"uid" : uid.val(), "upw" : upw.val(), "unm" : unm.val()};
            $.ajax({
                type: 'post',
                url: 'sign-up',
                dataType: 'json',
                contentType: 'application/json; charset:UTF-8',
                data: JSON.stringify(data),
                success: function(data) {
                    if(data == 0) {
                        alert('가입에 실패했습니다. 잠시 후 다시 이용해주세요.');
                    } else {
                        alert('가입에 성공했습니다. 메인 화면으로 이동합니다.');
                        location.href='/';
                    }
                }
            })
        }
    });
</script>
</html>