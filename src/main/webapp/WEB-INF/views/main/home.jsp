<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page session="false" %>
<html>
<head>
	<title>BOOKTIQUE</title>
</head>
<body>
<h1>
	Booktique Main
</h1>


<!-- 로그인한 사용자의 아이디 표시 -->
<sec:authorize access="isAuthenticated()">
    <p>반가워요! <sec:authentication property="principal.username"/>님!</p>
    
    <!-- 로그아웃 폼 -->
    <form action="/logout" method="post">
        <button type="submit">로그아웃</button>
        <sec:csrfInput />
    </form>
</sec:authorize>

<!-- 로그인하지 않은 경우에만 '로그인하기' 링크를 보여줌 -->
<sec:authorize access="!isAuthenticated()">
    <a href="/login">로그인하기</a>
</sec:authorize>

</body>
</html>
