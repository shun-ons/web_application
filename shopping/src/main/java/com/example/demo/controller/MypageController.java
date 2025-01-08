package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.UserService;
import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;

@Controller
public class MypageController {

	@Autowired
	private UserService userService;
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/mypage")
	public String mypage(Model model) {
	    // ログイン中のユーザー情報を取得
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String mailAddress = authentication.getName(); // メールアドレスまたはユーザー名

	    // ユーザー情報を取得
	    MUser muser = userService.getUserByMailAddress(mailAddress);

	    // ユーザー情報がnullの場合の対処
	    if (muser == null) {
	        throw new RuntimeException("ログインユーザーの情報が取得できません");
	    }

	    model.addAttribute("muser", muser);

	    return "mypage/mypage";
	}

	@PostMapping("/mypage")
	public String mypage(@RequestParam String userId, Model model) {
		MUser muser = userService.getUserOne(userId);
		model.addAttribute("muser",muser);
		return "mypage/mypage";
	}
	
	@PostMapping("/mypage/details")
	public String details(@RequestParam String userId, Model model) {
		MUser userDetailForm = userService.getUserOne(userId);
		List <Item> itemList = itemService.selectByOrnerId(userId);
		model.addAttribute("userDetailForm", userDetailForm);
		model.addAttribute("itemList", itemList);
		return "user/detail";
	}
}
