package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.UserService;

@Controller
public class UserListController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/list")
	public String getUserList(Model model) {
		
		List<MUser> userList = userService.getUsers();
		model.addAttribute("userList",userList);
		return "user/list";
	}
}
