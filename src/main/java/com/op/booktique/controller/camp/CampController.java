package com.op.booktique.controller.camp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.op.booktique.service.camp.CampService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CampController {
	
	@Autowired
	private CampService campService;

	//캠핑메인
	@GetMapping("/camp/main")
	public String campList() {
		log.info("camp main");
		
		return "camp/main";
	}
	
	//크롤링
	@GetMapping("/camp/insert")
	public String insertCamp() {
		// 크롤링 URL 페이지
		int startPage = 1;
		int endPage = 10;
		campService.crawlingData(startPage, endPage);
		
		return "camp/insert";
	}
}
