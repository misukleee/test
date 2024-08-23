package com.op.booktique.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.op.booktique.service.member.EmailService;
import com.op.booktique.service.member.MailServiceInter;
import com.op.booktique.service.member.MemberService;
import com.op.booktique.vo.MemberVO;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

/**
 * 로그인 및 로그아웃 처리, 접근 거부 처리, 사용자 ID 및 비밀번호 찾기 기능 등을 담당하는 컨트롤러
 */
@Slf4j
@Controller
public class SecurityController {

    @Autowired
    private PasswordEncoder passwordEncoder; // 비밀번호를 암호화하거나 검증할 때 사용하는 객체

    @Autowired
    private MemberService memberService; // 서비스 계층 주입
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private MailServiceInter registerMail;

    /**
     * 접근 거부 처리
     * 요청URI : /accessError
     * 요청방식 : GET
     *
     * @param auth Authentication 객체
     * @param model Model 객체
     * @return 접근 거부 페이지로 이동
     */
    @GetMapping("/accessError")
    public String accessError(Authentication auth, Model model) {
        log.info("accessError->auth : " + auth); // 접근 거부 로그 출력
        model.addAttribute("msg", "Access Denied"); // 모델에 접근 거부 메시지 추가
        
        return "security/accessError"; // 접근 거부 페이지로 이동
    }

    /**
     * 로그인 페이지 처리
     * 요청URL : /login, /login?error=error, /login?logout=logout
     * 요청방식 : GET
     *
     * @param error 로그인 에러 메시지
     * @param logout 로그아웃 메시지
     * @param model Model 객체
     * @param authentication Authentication 객체
     * @return 로그인 페이지로 이동
     */
    @GetMapping("/login")
    public String login(String error, String logout, Model model, Authentication authentication) {
        
        // 이미 인증된 사용자일 경우 메인 페이지로 리다이렉트
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/main";
        }
        
        // 로그인 시 에러가 발생했을 때 에러 메시지를 모델에 추가
        if (error != null) {
            model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            log.info("로그인 에러: " + error);
        }
        
        // 로그아웃했을 때 로그아웃 메시지를 모델에 추가
        if (logout != null) {
            model.addAttribute("logout", "성공적으로 로그아웃되었습니다.");
            log.info("로그아웃 메시지: " + logout);
        }
        
        return "loginForm"; // 로그인 폼 페이지로 이동
    }

    /**
     * 사용자 ID 찾기 페이지
     * 요청URL : /searchId
     * 요청방식 : GET
     *
     * @param model Model 객체
     * @return ID 찾기 페이지로 이동
     */
    @GetMapping("/searchId")
    public String searchId(Model model, HttpSession session) {
        MemberVO foundMember = (MemberVO) session.getAttribute("foundMember");
        String message = (String) session.getAttribute("message");

        if (foundMember != null) {
            model.addAttribute("memberVO", foundMember);
            session.removeAttribute("foundMember");
        } else {
            model.addAttribute("memberVO", new MemberVO()); // 폼 초기화
        }

        if (message != null) {
            model.addAttribute("message", message);
            session.removeAttribute("message");
        }

        return "searchId"; // ID 찾기 페이지로 이동
    }

    /**
     * 사용자 ID 찾기 처리
     * 요청URL : /searchId
     * 요청방식 : POST
     *
     * @param memNm 회원 이름
     * @param memBirthStr 회원 생년월일
     * @param memPhone 회원 전화번호
     * @param model Model 객체
     * @return ID 찾기 페이지로 리다이렉트
     */
    @PostMapping("/searchId")
    public String searchIdPost(@RequestParam("memNm") String memNm, 
                               @RequestParam("memBirth") String memBirthStr, 
                               @RequestParam("memPhone") String memPhone, 
                               HttpSession session) {
        try {
            // 서비스 계층을 통해 비즈니스 로직을 처리
            List<MemberVO> memberVOList = memberService.findMemberByNameBirthdateAndPhone(memNm, memBirthStr, memPhone);
            
            // 사용자가 존재할 경우
            if (memberVOList != null && !memberVOList.isEmpty()) {
                MemberVO foundMember = memberVOList.get(0);
                log.info("searchIdPost -> {}", foundMember); // ID 찾기 요청에 대한 로그 출력
                session.setAttribute("foundMember", foundMember); // 세션에 사용자 정보 추가
            } else { // 존재하지 않을 경우
                session.setAttribute("message", "해당 정보와 일치하는 사용자를 찾을 수 없습니다"); // 세션에 에러 메시지 추가
            }
        } catch (Exception e) { // 기타 예외가 발생할 경우
            log.error("이름, 생년월일, 전화번호로 회원을 찾는 중 오류 발생", e); // 사용자 조회 오류 로그 출력
            session.setAttribute("message", "아이디를 검색하는 중 오류가 발생했습니다"); // 세션에 에러 메시지 추가
        }
        
        return "redirect:/searchId"; // PRG 패턴 적용: ID 찾기 페이지로 리다이렉트
    }

    /**
     * 비밀번호 찾기 페이지
     * 요청URL : /searchPw
     * 요청방식 : GET
     *
     * @return 비밀번호 찾기 페이지로 이동
     */
    @GetMapping("/searchPw")
    public String searchPw(Model model, HttpSession session) {
        MemberVO foundMember = (MemberVO) session.getAttribute("foundMember");
        String message = (String) session.getAttribute("message");

        if (foundMember != null) {
            model.addAttribute("memberVO", foundMember);
            session.removeAttribute("foundMember");
        } else {
            model.addAttribute("memberVO", new MemberVO()); // 폼 초기화
        }

        if (message != null) {
            model.addAttribute("message", message);
            session.removeAttribute("message");
        }

        return "searchPw"; // 비밀번호 찾기 페이지로 이동
    }

    /**
     * 비밀번호 찾기 처리
     * 요청URL : /searchPw
     * 요청방식 : POST
     *
     * @param memId 회원 아이디
     * @param memEmail 회원 이메일
     * @param model Model 객체
     * @return 비밀번호 찾기 페이지로 리다이렉트
     */
    @PostMapping("/searchPw")
    public String searchPwPost(@RequestParam("memId") String memId,
                               @RequestParam("memEmail") String memEmail,
                               HttpSession session) {
        log.info("searchPwPost->memId : " + memId + "searchPwPost->memEmail : " + memEmail); // 비밀번호 찾기 요청 로그 출력
        
        try {
            // 서비스 계층을 통해 비즈니스 로직을 처리
            MemberVO member = memberService.findMemberByIdAndEmail(memId, memEmail);
            
            // 사용자가 존재할 경우
            if (member != null) {
                String code = registerMail.sendSimpleMessage(memEmail); // 인증 코드 이메일 전송
                System.out.println("사용자에게 발송한 인증코드 ==> " + code); // 인증 코드 로그 출력
                
                // DB의 비밀번호 변경 시행
                member.setMemPw(passwordEncoder.encode(code)); // 비밀번호 인코딩 후 설정                
                memberService.updateMember(member); // 비밀번호 업데이트
                
                log.info("searchPwPost->member : " + member); // 비밀번호 변경된 사용자 로그 출력
                
                session.setAttribute("foundMember", member); // 세션에 사용자 정보 추가
            } else { // 사용자가 존재하지 않을 경우
                session.setAttribute("message", "해당 정보와 일치하는 사용자를 찾을 수 없습니다"); // 세션에 에러 메시지 추가
            }
        } catch (Exception e) { // 예외가 발생할 경우
            log.error("비밀번호 재설정 오류", e); // 비밀번호 재설정 오류 로그 출력
            session.setAttribute("message", "비밀번호 재설정 중 오류가 발생했습니다"); // 세션에 에러 메시지 추가
        }
        
        return "redirect:/searchPw"; // PRG 패턴 적용: 비밀번호 찾기 페이지로 리다이렉트
    }
}
