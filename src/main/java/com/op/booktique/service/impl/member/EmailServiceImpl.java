package com.op.booktique.service.impl.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.op.booktique.service.member.EmailService;

import lombok.extern.slf4j.Slf4j;

/**
 * 이메일 서비스 구현 클래스.
 */
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;	// JavaMailSender는 Spring Framework에서 이메일을 보내기 위한 인터페이스

    /**
     * 간단한 이메일 메시지를 보내는 메소드
     *
     * @param to 수신자 이메일 주소
     * @param subject 이메일 제목
     * @param text 이메일 본문
     */
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        log.info("to : " + to + ", subject : " + subject + ", text : " + text); // 이메일 정보 로그 출력
        
        SimpleMailMessage message = new SimpleMailMessage();
        
        //message.setFrom("your_naver_email@naver.com");
        message.setTo(to); // 수신자 설정
        message.setSubject(subject); // 제목 설정
        message.setText(text); // 본문 설정
        
        emailSender.send(message); // 이메일 전송
    }
}
