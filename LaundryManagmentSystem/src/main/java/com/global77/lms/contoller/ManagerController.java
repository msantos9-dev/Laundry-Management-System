package com.global77.lms.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@GetMapping("/")
	public String home() {
		return "manager/index";
	}

}
