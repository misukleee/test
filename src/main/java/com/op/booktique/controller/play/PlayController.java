package com.op.booktique.controller.play;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PlayController {
	
	@GetMapping("/play/main")
	public String playMain() {
		log.info("연극 메인");

		
		return "play/playMain";
	}

}
