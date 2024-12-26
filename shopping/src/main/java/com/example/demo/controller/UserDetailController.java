package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.UserService;
import com.example.demo.form.UserDetailForm;

@Controller
public class UserDetailController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//正規表現のURLに遷移
	@GetMapping("/detail/{mailAddress:.+}")
	public String getUser(UserDetailForm form,Model model,@PathVariable("mailAddress")String mailAdress) {
		
		//ユーザを一軒取得
		MUser user = userService.getUserOne(mailAdress);
		user.setPassword(null);
		
		//MUserをformに変換
		form = modelMapper.map(user,  UserDetailForm.class);
		
		//Modelに登録
		model.addAttribute("userDetailForm",form);
		
		return "user/detail";
	}
	
	@PostMapping(value = "/detail",params = "update")
	public String updateUser(UserDetailForm form,Model model) {
		
		System.out.println("aaa");
		userService.updateUserOne(form.getMailAddress(),form.getPassword() , form.getName());
		
		return "redirect:/list";
	}
	
	
	@PostMapping(value = "/detail",params = "delete")
	public String deleteUser(UserDetailForm form,Model model) {
		
		userService.deleteUserOne(form.getMailAddress());
		
		return "redirect:/list";
	}
}
