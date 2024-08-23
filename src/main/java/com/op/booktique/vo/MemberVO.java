package com.op.booktique.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	private String memId;      // 회원아이디
    private String memPw;      // 회원비밀번호
    private String memNm;      // 회원이름
    private String memBirth;   // 생년월일
    private String gender;     // 성별
    private String memPhone;   // 전화번호
    private String memEmail;   // 이메일
    private String memPost;    // 우편번호
    private String memAddr1;   // 주소
    private String memAddr2;   // 상세주소
    private Date regDate;      // 가입날짜
    private Date withdrawDate; // 탈퇴날짜
    private int memStat;       // 회원상태
}
