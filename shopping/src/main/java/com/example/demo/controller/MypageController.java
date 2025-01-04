package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.UserService;

@Controller
public class MypageController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/mypage")
	public String Mypage(Model model) {
		
		List<MUser> userList = userService.getUsers();
		model.addAttribute("userList",userList);
		return "user/mypage";
	}
}
