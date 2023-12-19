package com.my.blog;

import com.my.blog.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
	private final UserService service;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("user", service.getUser());
		return "home";
	}
}
