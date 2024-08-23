package com.op.booktique.controller.musical;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/musical")
@Controller
public class MusicalController {
	
	@GetMapping("/main")
	public String test() {
		return "musical/main";
	}
}
