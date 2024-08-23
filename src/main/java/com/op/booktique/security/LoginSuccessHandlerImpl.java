package com.op.booktique.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * 인증(로그인) 성공 후, 사용자가 접근을 시도한 URL로 리다이렉트하는 기능을 가진 핸들러.
 * 스프링 시큐리티에서 기본적으로 사용되는 구현 클래스.
 * 로그인 성공 시 사용자 정보를 세션에 저장하고, 로그를 남김.
 */
@Slf4j
public class LoginSuccessHandlerImpl extends SavedRequestAwareAuthenticationSuccessHandler {
    
    /**
     * 인증 성공 시 호출되는 메소드.
     * 요청 파라미터: {username=user, password=java}
     *
     * @param request HttpServletRequest 객체
     * @param response HttpServletResponse 객체
     * @param auth Authentication 객체
     * @throws ServletException 인증 처리 중 발생할 수 있는 예외
     * @throws IOException 입출력 처리 중 발생할 수 있는 예외
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
            throws ServletException, IOException {

        log.info("onAuthenticationSuccess");
        log.info("auth : {}", auth);

        // 인증된 사용자 정보 가져오기
        User customUser = (CustomUserPrincipal) auth.getPrincipal();

        // 사용자 아이디 로그
        log.info("username : {} ", customUser.getUsername()); // 사용자 아이디 로그 출력
        log.info("customUser : {}", customUser); // 사용자 정보 로그 출력

        // 사용자가 갖고 있는 권한(role) 목록 가져오기
        List<String> roleNames = new ArrayList<>();
        auth.getAuthorities().forEach(authority -> {
            roleNames.add(authority.getAuthority());
        });

        // 권한 목록 로그
        log.info("roleNames : " + roleNames);

        // 로그인 성공 시 사용자 정보를 세션에 저장
        HttpSession session = request.getSession();
        session.setAttribute("username", customUser.getUsername()); // 세션에 사용자 아이디 저장
        session.setAttribute("roleNames", roleNames); // 세션에 사용자 권한 목록 저장

        // 로그인 성공 로그 남기기
        log.info("User '{}' logged in with roles: {}", customUser.getUsername(), roleNames);

        // 모든 사용자가 ROLE_MEMBER 권한을 가지므로 바로 메인 페이지로 리다이렉트
        response.sendRedirect("/main");
    }
}