package com.project.blog.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;
import static com.project.blog.cmmn.Const.*;

@Controller
@RequiredArgsConstructor
public class HomeController {
	private final HomeService service;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute(VO, service.getContentsCnt());
		return "home";
	}
}
