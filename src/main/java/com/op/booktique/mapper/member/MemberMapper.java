package com.op.booktique.mapper.member;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.op.booktique.vo.MemberVO;

/**
 * 회원 정보에 대한 데이터베이스 접근을 위한 매퍼 인터페이스.
 */
@Mapper
public interface MemberMapper {
    // 로그인
    public MemberVO detail(String memId);
    
    // 회원 정보 조회
    public MemberVO getMemberById(String memId);

    // 회원 정보 수정
    public int updateMember(MemberVO memberVO);

    // 아이디 찾기
    public List<MemberVO> findMemberByNameBirthdateAndPhone(
    		@Param("memNm") String memNm, 
            @Param("memBirth") String memBirth, 
            @Param("memPhone") String memPhone);
    
    // 비밀번호 찾기
    public MemberVO findMemberByIdAndEmail(
    		@Param("memId") String memId, 
    		@Param("memEmail") String memEmail);
}
