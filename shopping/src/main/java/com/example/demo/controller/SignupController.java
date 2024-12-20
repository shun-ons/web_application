package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.UserService;
import com.example.demo.form.SignupForm;

import ch.qos.logback.core.model.Model;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SignupController {

	//@Autowired
	//rivate UserApplicationService userApplicationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/signup")
	public String getSignup(@ModelAttribute SignupForm form){
		return "signup/signup";
	}
	
	@PostMapping("/signup")
	public String postSignup(Model model,@ModelAttribute @Validated SignupForm form,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return getSignup(form);
		}
		log.info(form.toString());
		
		MUser user = modelMapper.map(form, MUser.class);
		userService.signup(user);
		
		return "redirect:/login";
	}
	
}
