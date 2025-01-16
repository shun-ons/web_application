package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.service.UserService;

@Controller
@RequestMapping("/deposit")
public class DepositController {

    @Autowired
    private UserService userService;

    // 入金フォームを表示
    @PostMapping("/deposit-form")
    public String depositForm(@RequestParam String userId, Model model) {
        model.addAttribute("userId", userId);
        return "deposit/deposit"; // 入金フォームページ
    }

 // 入金確認ページを表示
    @PostMapping("/confirm-input")
    public String confirmInput(@RequestParam String userId, 
                                @RequestParam int amount, 
                                //@RequestParam String confirm,  // confirm パラメータの受け取り
                                Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("amount", amount);
        return "deposit/confirm"; // 入金確認ページ
    }

    // 入金処理を実行
    @PostMapping("/complete")
    public String completeDeposit(@RequestParam String userId, 
                                   @RequestParam int amount, 
                                   Model model) {
        try {
            userService.addPoint(userId, amount); // ポイントを追加
            model.addAttribute("message", "入金が完了しました。");
        } catch (Exception e) {
            model.addAttribute("message", "入金処理中にエラーが発生しました。");
            return "deposit/error"; // エラーページ
        }
        return "deposit/completion"; // 入金完了ページ
    }
}