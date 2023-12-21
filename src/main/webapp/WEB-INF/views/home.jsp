<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
	<link href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css" rel="stylesheet">
</head>

<style>
	@font-face {
		font-family: 'SUIT-Regular';
		src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_suit@1.0/SUIT-Regular.woff2') format('woff2');
		font-weight: normal;
		font-style: normal;
	}

	html {
		font-family: 'SUIT-Regular';
		font-weight: 600;
		background-image: url("https://images.unsplash.com/photo-1640040159521-8a1b5d323181?q=80&w=1173&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
		background-attachment: fixed;
		background-size: cover;
	}

	header {
		color: white;
		font-size: 1.5rem;
		display: block;
		text-align: center;
		padding-bottom: 20px;
	}

	nav {
		display: flex;
		justify-content: space-around;
	}

	nav > a {
		width: 100%;
		text-align: center;
		color: white;
	}

	body {
		box-sizing: border-box;
		border-radius: 30px;
		padding: 20px 20px 15px 0;
		margin: 40px;
		background-color: rgba(255,255,255,0.1);
		backdrop-filter: blur(10px);
	}
</style>

<body>
<header>
	my blog
</header>
<nav>
	<a>홈</a>
	<a>일기</a>
	<a>공부</a>
	<a>관심</a>
	<a>기타</a>
	<a>설정</a>
	<a>로그인</a>
</nav>
<div class="content">
	<%--<P>${user}</P>--%>
</div>
</body>
</html>
