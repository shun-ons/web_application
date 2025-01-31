package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.UserService;

/**
 * アプリケーション詳細画面のコントローラークラス。
 * @author 石井叶輝
 */
@Controller
public class AppDetailsController {
	
    private final UserService userService;
	
    /**
     * コンストラクタ。
     * 
     * @param userService ユーザーサービス
     */
    public AppDetailsController(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * アプリケーション詳細画面を表示する。
     * 
     * @param userId ユーザーID（オプション）
     * @param model モデル
     * @return アプリケーション詳細画面のビュー名
     */
    @PostMapping("/appDetails")
    public String showAppDetails(@RequestParam(required = false) String userId, Model model) {
        if (userId != null) {
            MUser muser = userService.getUserOne(userId);
            model.addAttribute("muser", muser);
        }
        return "appDetails/appDetails";
    }
}
