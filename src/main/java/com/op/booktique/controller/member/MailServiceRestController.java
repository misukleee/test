package com.op.booktique.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.op.booktique.service.member.MailServiceInter;


@RestController
@RequestMapping(value = "/api/mail")
public class MailServiceRestController {

    @Autowired
    MailServiceInter registerMail;

    //127.0.0.1:8080/ROOT/api/mail/confirm.json?email
    @PostMapping(value = "/confirm.json")
    public String mailConfirm(@RequestParam(name = "email") String email) throws Exception{
        String code = registerMail.sendSimpleMessage(email);
        System.out.println("사용자에게 발송한 인증코드 ==> " + code);

        return code;
    }
    
    //127.0.0.1:8080/ROOT/api/mail/confirm.json?email
    @GetMapping(value = "/confirmText")
    public String confirmText() throws Exception{
    	String email = "rosy_h@naver.com";
    	
        String code = registerMail.sendSimpleMessage(email);
        System.out.println("사용자에게 발송한 인증코드 ==> " + code);

        return code;
    }
}
