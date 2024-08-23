package com.op.booktique.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.op.booktique.mapper.member.MemberMapper;
import com.op.booktique.vo.MemberVO;

@Service
public class MemberService {
	
	@Autowired
    private MemberMapper memberMapper;

	// 아이디 찾기
    public List<MemberVO> findMemberByNameBirthdateAndPhone(String memNm, String memBirthStr, String memPhone) {
        return memberMapper.findMemberByNameBirthdateAndPhone(memNm, memBirthStr, memPhone);
    }
    
    // 비밀번호 찾기
    public MemberVO findMemberByIdAndEmail(String memId, String memEmail) {
        return memberMapper.findMemberByIdAndEmail(memId, memEmail);
    }

    // 회원 정보 수정
    public void updateMember(MemberVO memberVO) {
        memberMapper.updateMember(memberVO);
    }
}
