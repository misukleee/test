package com.op.booktique.controller.concert;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.op.booktique.service.concert.ConcertService;
import com.op.booktique.vo.concert.ConcertVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/concert")
public class ConcertController {

	@Autowired
	private ConcertService concertService;
	
	@GetMapping("/main")
//	public String mainPage(Model model) {
	public String mainPage() {
		
		log.debug("콘서트 메인 페이지 이동");
		
//		List<ConcertVO> concertList = concertService.list();
		
//		model.addAttribute("allList",concertList);
		
		return "concert/main";
	}

}
