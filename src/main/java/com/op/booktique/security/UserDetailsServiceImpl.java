package com.op.booktique.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.op.booktique.mapper.member.MemberMapper;
import com.op.booktique.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 데이터베이스에서 사용자의 인증 정보를 조회하여 UserDetails 객체로 변환하는 서비스 클래스.
 * 사용자 인증을 처리하는 핵심 클래스.
 */
@Slf4j
@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 사용자 이름으로 사용자 정보를 로드하는 메소드
     *
     * @param username 사용자 이름
     * @return UserDetails 객체
     * @throws UsernameNotFoundException 사용자를 찾을 수 없을 때 발생하는 예외
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("UserDetailsServiceImpl->username : " + username); // 사용자 이름 로그 출력

        // 사용자 정보 조회
        MemberVO memberVO = this.memberMapper.detail(username);
        log.info("UserDetailsServiceImpl->memberVO : " + memberVO); // 사용자 정보 로그 출력
        
        if (memberVO == null) {
            throw new UsernameNotFoundException("User not found"); // 사용자 정보를 찾지 못했을 때 예외 발생
        }
        
        return memberVO == null ? null : new CustomUserPrincipal(memberVO); // 사용자 정보를 UserDetails 객체로 변환하여 반환
    }
}
