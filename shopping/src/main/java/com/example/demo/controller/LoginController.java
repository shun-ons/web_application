package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.domain.service.UserService;

/**
 * ログイン処理を管理するコントローラークラス.
 * @author 夏木翔吾
 */
@Controller
public class LoginController {
    
    @Autowired
    UserService userService;

    /**
     * ログインページを表示する.
     * 
     * @return ログインページのビュー名
     */
    @GetMapping("/login")
    public String getLogin() {
        return "login/login";
    }
}
