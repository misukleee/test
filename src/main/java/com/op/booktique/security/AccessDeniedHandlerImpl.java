package com.op.booktique.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * 사용자가 접근 권한이 없는 페이지에 접근할 때, 접근 거부 처리를 담당하는 핸들러.
 * 사용자를 특정 페이지로 리다이렉트.
 */
@Slf4j
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    
    /**
     * 접근 거부 처리
     * 요청URL : /accessError
     * 요청방식 : GET
     *
     * @param request HttpServletRequest 객체
     * @param response HttpServletResponse 객체
     * @param accessDeniedException 접근 거부 예외
     * @throws IOException 입출력 처리 중 발생할 수 있는 예외
     * @throws ServletException 서블릿 처리 중 발생할 수 있는 예외
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
    	
        log.info("AccessDeniedHandlerImpl->handle {}", accessDeniedException.getMessage()); // 접근 거부 예외 메시지 로그 출력

        // 사용자를 로그인 페이지로 리다이렉트
        response.sendRedirect("/login");
    }
}