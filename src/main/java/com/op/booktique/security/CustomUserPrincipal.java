package com.op.booktique.security;

import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.op.booktique.vo.MemberVO;

/**
 * 사용자 세부 정보를 포함하는 사용자 Principal 클래스.
 * Spring Security에서 인증된 사용자 정보를 제공.
 * 모든 사용자는 ROLE_MEMBER 권한을 가짐.
 */
public class CustomUserPrincipal extends User {

    private MemberVO memberVO;

    /**
     * CustomUserPrincipal 생성자
     * 
     * @param memberVO MemberVO 객체
     */
    public CustomUserPrincipal(MemberVO memberVO) {
        super(memberVO.getMemId(), memberVO.getMemPw(),
              Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEMBER")));
        this.memberVO = memberVO;
    }

    /**
     * 사용자 정보를 반환하는 메소드
     *
     * @return MemberVO 객체
     */
    public MemberVO getMemberVO() {
        return memberVO;
    }

    /**
     * 사용자 정보를 설정하는 메소드
     *
     * @param memberVO MemberVO 객체
     */
    private void setMemberVO(MemberVO memberVO) {
        this.memberVO = memberVO;
    }
}
