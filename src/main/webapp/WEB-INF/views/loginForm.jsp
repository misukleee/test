<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />
    <meta name="description" content="" />
    
    <title>로그인 - BOOKTIQUE</title>
	
	<link href="/resources/css/fdfa608d464f1bb9.css" rel="stylesheet" type="text/css">
	<link href="/resources/css/d9f4e75c2121ddb0.css" rel="stylesheet" type="text/css">
	<link href="/resources/css/mf.min.css" rel="stylesheet" type="text/css">
	<link href="/resources/css/7b65a34d28470be3.css" rel="stylesheet" type="text/css">
	<link href="/resources/css/0c7ff060f4519b3e.css" rel="stylesheet" type="text/css">
	<link href="/resources/css/common.css" rel="stylesheet" type="text/css">
	<link href="/resources/css/login.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/resources/css/booktique.css" />
    
	<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
</head>
<body>
<div id="container" class="container login">

	<div class="loginWrap">
		<div class="loginLogo">
			<a class="main_logo" href="/main" style="font-weight: 700">
				<img class="main_logo_img" src="/resources/img/logo.png">
				Booktique
			</a>
		</div>
		
		<form id="formAuthentication" action="/login" method="post">
			<!-- CSRF Token -->
			<sec:csrfInput/>
		
			<div class="loginInner">
				<div class="loginForm">
					<div class="inputBox">
					
						<!-- 아이디 입력 필드 -->
						<div class="inputStyle inputId">
						    <label>
						        <input value="${param.username}" autofocus="" type="text" class="inputText id" id="username" name="username" placeholder="아이디">
						        <span class="focusLine"></span>
						        <i>
						            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none">
						                <path stroke="#999" stroke-linecap="round" d="M7.789 9.867A4.444 4.444 0 0 1 12 4a4.444 4.444 0 1 1 0 8.885c-.947 0-1.945-.167-2.667-.672"></path>
						                <path fill="#999" d="M9.489 12.685a.5.5 0 1 0-.311-.95l.31.95Zm10.378 5.822.492-.088-.492.088ZM5.5 20.5h13v-1h-13v1Zm-.885-1.912c.463-2.81 2.293-5.058 4.874-5.903l-.311-.95c-2.965.97-5.031 3.545-5.55 6.69l.987.163Zm9.671-5.903c2.597.85 4.584 3.113 5.089 5.911l.984-.177c-.57-3.156-2.811-5.718-5.762-6.684l-.31.95ZM5.5 19.5c-.58 0-.96-.456-.885-.912l-.987-.163C3.434 19.603 4.423 20.5 5.5 20.5v-1Zm13 1c1.075 0 2.073-.898 1.86-2.081l-.985.177c.08.448-.293.904-.875.904v1Z"></path>
						            </svg>
						        </i>
							    <button type="button" class="inputBtn btnDel">
							        <span class="blind">삭제</span>
							    </button>
						    </label>
						</div>
						
						<!-- 비밀번호 입력 필드 -->
						<div class="inputStyle inputPw">
						    <label>
						        <input type="password" class="inputText pw" id="password" name="password" placeholder="비밀번호">
						        <span class="focusLine"></span>
						        <i>
						            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none">
						                <path stroke="#999" stroke-linecap="round" d="M19.5 15.6V11A1.5 1.5 0 0 0 18 9.5H6A1.5 1.5 0 0 0 4.5 11v8A1.5 1.5 0 0 0 6 20.5h12a1.5 1.5 0 0 0 1.5-1.5v-.75"></path>
						                <circle cx="12" cy="14" r="1" fill="#999"></circle>
						                <path fill="#999" d="M11.5 14h1l.5 3h-2l.5-3Z" class="pathFill"></path>
						                <path stroke="#999" d="M16.5 9.5V8a4.5 4.5 0 0 0-9 0v1.5"></path>
						            </svg>
						        </i>
						        <button type="button" class="inputBtn btnShowToggle">
						            <span class="blind">비밀번호 노출상태 변경버튼</span>
						        </button>
						    </label>
						</div>
					</div>
					<div class="errorMessage">
						<div class="message">
						</div>
					</div>
				</div>
				
				<!-- 로그인 상태 유지 체크박스 -->
				<div class="signinStay">
				    <div class="checkbox">
				        <input type="checkbox" id="remember-me" name="remember-me" value="true">
				        <label for="remember-me">로그인 상태 유지</label>
				    </div>
				</div>
				
				<!-- 로그인 버튼 -->
				<div class="loginButtonBox">
					<button type="submit" class="loginBtn" id="btn_login">
						<span>로그인</span>
					</button>
				</div>
				
				
				<div class="findList">
					<ul>
						<li>
							<a id="findId" class="findId" href="/searchId">아이디 찾기</a>
						</li>
						<li>
							<a id="findPwd" href="/searchPw">비밀번호 찾기</a>
						</li>
						<li>
							<a id="join" href="/signUp">회원가입</a>
						</li>
					</ul>
				</div>
				<div class="snsLogin">
					<ul>
						<li id="kakao_li">
							<a href="#" class="kakao" id="openid_kakao">
								<span>카카오톡</span>
							</a>
						</li>
						<li id="naver_li">
							<a href="#" class="naver" id="openid_naver">
								<span>네이버</span>
							</a>
						</li>
						<li id="apple_li">
							<a href="#" class="apple" id="openid_apple">
								<span>애플</span>
							</a>
						</li>
					</ul>
				</div>
			</div>
		</form>
	</div>
</div>


<script type="text/javascript" src="/resources/js/login.common.js"></script>
<script type="text/javascript" src="/resources/js/login.js"></script>
<script type="text/javascript" src="/resources/js/jquery.ajaxSubmit.min.js"></script>

</body>
</html>
