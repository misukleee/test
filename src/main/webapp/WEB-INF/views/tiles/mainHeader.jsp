<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script>
$(function(){
	// 현재 url 경로 가져오기
	const currentPath = window.location.pathname;
	
	// 모든 메뉴 항목을 선택
    const menuItems = document.querySelectorAll('.menu-item');
	
 	// 각 메뉴 항목을 순회하며 현재 URL 경로와 일치하는 항목을 활성화
    menuItems.forEach(item => {
        const itemPath = item.getAttribute('data-url');
        
        if (currentPath.includes(itemPath)) {
            item.classList.add('header_active__8615c');
        } else {
            item.classList.remove('header_active__8615c');
        }
    });
});
</script>

<div class="layout_app__hk6sN">
	<header>
		<div id="accessibility" class="header_accessibility__6acdc">
			<ul>
				<li><a href="#contents">본문 바로가기</a></li>
			</ul>
		</div>
		<div class="header_wrap__abfca">
			<div class="header_navi__b5313">
				<div class="header_naviWrapper__6fe56">
					<div class="header_biSearch__0c77a">
						<div class="Ticket_Ci_PC header_desktop__9c75d">
							<div class="header_bi__42395">
								<h1>
									<a class="main_logo" href="/main">
									<img class="main_logo_img" src="/resources/img/logo.png">
									Booktique_
									</a>
								</h1>
							</div>
						</div>
						<div class="search_wrap__370af">
							<div class="Ticket_SrcBar search_searchInput__e52e4">
								<input gtm-label="검색창" type="text"
									placeholder="인터파크 투어, 티켓에서 알려드립니다" value="">
									<a role="button" tabindex="0" gtm-label="검색아이콘" aria-label="검색아이콘"
									data-link-title="인터파크 투어, 티켓에서 알려드립니다"
									data-link-link="#"
									class="search-button search_searchBtn__297be">
									<svg
										xmlns="http://www.w3.org/2000/svg" width="20" height="20"
										fill="none" viewBox="0 0 20 20">
										<path stroke="#3A3A3A" stroke-linecap="round"
											stroke-miterlimit="10" stroke-width="1.6"
											d="m17.875 17.877-4.607-4.607c-.462-.462-1.198-.56-1.729-.197-1.345.943-3.084 1.356-4.92.943-2.26-.5-4.087-2.328-4.588-4.587A6.157 6.157 0 0 1 8.23 1.876c3.045.098 5.638 2.534 5.923 5.56.079.844-.02 1.66-.245 2.416l-.295.726"></path></svg></a>
							</div>
						</div>
					</div>
					
					<div class="Ticket_Account header_userMenu__c408a">
					    <!-- 로그인하지 않은 경우에만 '로그인', '회원가입' 링크를 보여줌 -->
					    <sec:authorize access="!isAuthenticated()">
					        <a class="header_menu__720a4" href="/login" title="로그인">로그인</a>
					        <a class="header_menu__720a4" href="/signUp" title="회원가입">회원가입</a>
					    </sec:authorize>
					    
					    <!-- 로그인한 경우에만 '로그아웃' 링크를 보여줌 -->
					    <sec:authorize access="isAuthenticated()">
					        <a class="header_menu__720a4" href="#" title="로그아웃" onclick="event.preventDefault(); document.getElementById('logout-form').submit();">로그아웃</a>
					        <form id="logout-form" action="/logout" method="post" style="display: none;">
					            <sec:csrfInput />
					        </form>
					    </sec:authorize>
					
					    <a class="header_menu__720a4" href="#" title="마이페이지">마이페이지</a>
					</div>

				</div>
			</div>
			<div role="presentation" class="header_menu__720a4">
				<menu class="Ticket_GNB_PC header_wrapMenu__02b29">
					<ul class="header_genreMenu__34716">
						<li class="menu-item" data-url="musical">
							<a class="header_link__5ddf2" href="/musical/main">뮤지컬</a>
						</li>
						<li class="menu-item" data-url="concert">
							<a class="header_link__5ddf2" href="/concert/main">콘서트</a>
						</li>
						<li class="menu-item" data-url="sports">
							<a class="header_link__5ddf2" href="/sports/main">스포츠</a>
						</li>
						<li class="menu-item" data-url="play">
							<a class="header_link__5ddf2" href="/play/main">연극</a>
						</li>
						<li class="menu-item" data-url="camp">
							<a class="header_link__5ddf2" href="/camp/main">캠핑</a>
						</li>
						<li class="menu-item" data-url="book">
							<a class="header_link__5ddf2" href="/book/main">도서</a>
						</li>
					</ul>
<!-- 					<div class="header_menuDivider__01d75"></div> -->
				</menu>
			</div>
		</div>
	</header>
</div>
