package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.UserService;

@Controller
public class MypageController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/mypage")
	public String Mypage(@RequestParam String userId, Model model) {
		MUser muser = userService.getUserOne(userId);
		model.addAttribute("muser",muser);
		return "mypage/mypage";
	}
}
