package com.op.booktique.service.member;

/**
 * 이메일 서비스를 위한 인터페이스.
 */
public interface EmailService {
	
	// to:이메일주소, subject:이메일제목, text:이메일내용
    void sendSimpleMessage(String to, String subject, String text);
}
