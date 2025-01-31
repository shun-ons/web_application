package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.UserService;

@Controller
public class UserListController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/list")
	public String getUserList(Model model) {
		List<MUser> muserList = userService.getUsers();
		//System.out.println(userList);
		model.addAttribute("muserList",muserList);
		return "user/list";
	}
	
	@PostMapping("/list/details")
	public String uesrDetails(@RequestParam String userId, Model model) {
		MUser muser = userService.getUserOne(userId);
		model.addAttribute("muser", muser);
		return "user/detail";
	}
}
