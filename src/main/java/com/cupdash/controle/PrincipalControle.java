package com.cupdash.controle;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PrincipalControle {	
	@GetMapping("/")
	public String homeAdm(Model model) {
		return "index";
	}
	
	@GetMapping("/login")
	public String viewLoginPage(Model model) {
		return "login";
	}
	

}
