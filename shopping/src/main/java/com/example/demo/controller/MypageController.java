package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	    // ログイン中のユーザー情報を取得
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String mailAddress = authentication.getName(); // メールアドレスまたはユーザー名

	    // ユーザー情報を取得
	    MUser muser = userService.getUserByMailAddress(mailAddress);

	    // ユーザー情報がnullの場合の対処
	    if (muser == null) {
	        throw new RuntimeException("ログインユーザーの情報が取得できません");
	    }

	    // ユーザー情報をモデルに追加
	    model.addAttribute("muser", muser);

	    return "mypage/mypage";
	}
}
