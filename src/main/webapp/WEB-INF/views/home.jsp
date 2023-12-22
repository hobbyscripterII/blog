<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Home</title>
    <jsp:include page="layout/head.jsp"/>
</head>

<style>
    .img-cat {

    }
</style>

<body>
<jsp:include page="layout/header.jsp"/>
<jsp:include page="layout/nav.jsp"/>
<div class="content">
    <%--<P>${user}</P>--%>
    <img class="img-cat" src="/resources/img/img-cat.png">
</div>
</body>
</html>
