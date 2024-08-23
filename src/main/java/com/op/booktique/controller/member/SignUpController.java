package com.op.booktique.controller.member;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.op.booktique.service.member.SignUpService;
import com.op.booktique.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class SignUpController {
	
	@Inject
	BCryptPasswordEncoder bcryptPasswordEncoder;

	@Autowired
	SignUpService signUpService;
	
	@GetMapping("/signUp")
	public String signUp() {
		log.info("회원가입 폼");
		return "signUp";
	}
	
	
	/** 아이디 중복체크를 위한
	 * @param memId : 아이디 입력 창의 입력된 것을 받아 이미 있는 지 체크할 때 사용
	 * @return 이미 있는 아이디라면 1을 반환
	 */
	@PostMapping("/checkId")
	@ResponseBody
	public int checkId(@RequestBody String memId) {
		log.info("아이디 중복확인");
	
		int cnt = signUpService.checkId(memId);
		log.info("아이디 중복확인 : {}" , cnt);
		
		return cnt;
	}
	
	
	
	
	/** 회원가입 ajax
	 * @param memberVO : 가입 시 작성한 내용을 담을
	 * @return 완료 되면 1을 반환
	 */
	@PostMapping("/signUpAjax")
	@ResponseBody
	public int signUpAjax( @RequestBody MemberVO memberVO) {
		log.info("회원가입"); 
		
		// 비밀번호 암호화 처리
		String encodedPw = bcryptPasswordEncoder.encode(memberVO.getMemPw());
		memberVO.setMemPw(encodedPw);
		log.info("암호화 ??? {}" , encodedPw);
		
		int cnt = signUpService.signUpAjax(memberVO);
		log.info("회원가입 >> {}", cnt ); 
		
		return cnt;
	}
	
	
}
