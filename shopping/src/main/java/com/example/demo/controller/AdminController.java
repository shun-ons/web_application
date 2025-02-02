package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 管理者用のコントローラークラス.
 */
@Controller
public class AdminController {

    /**
     * 管理者ページを表示する.
     * 
     * @return 管理者ページのビュー名
     */
    @GetMapping("/admin")
    public String getAdmin() {
        return "admin/admin";
    }
}
