package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String getLogin(){
		return "login/login";
	}
	
	@PostMapping("/login")
	 public String login(@RequestParam String mailAddress, @RequestParam String password) {
	        
	            return "redirect:/user/detail/"+ mailAddress;
	}
}
