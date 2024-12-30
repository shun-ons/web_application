package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.UserService;

@Controller
public class LoginController {
	@Autowired
	 UserService userService;

	@GetMapping("/login")
	public String getLogin(){
		return "login/login";
	}
	
	
	
	@PostMapping("/login")           
	public String confirm(@RequestParam("mailAdress") String mailAddress, @RequestParam("password") String password, Model model) {
		MUser muser = userService.getUserByMailAddress(mailAddress);
		String truePass = muser.getPassword();
		if (truePass.equals(password)) {
			model.addAttribute("muser", muser);
			return "mypage/mypage";
		}
		return "redirect:/login";
	}
}
