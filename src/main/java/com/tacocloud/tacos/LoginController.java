package com.tacocloud.tacos;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

	
	@GetMapping
	public String handleLoggedInUser() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null && authentication.getClass() != AnonymousAuthenticationToken.class) {
			
			
			log.info("Authen Principal: {}", authentication.getPrincipal());
			
			
			return "redirect:/design";
		}
		
		
		return "login";
	}
}
