package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.model.DepositInput;
import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.UserService;

@Controller
@RequestMapping("/deposit")
public class DepositController {

    @Autowired
    private UserService userService;
   
    @GetMapping("/deposit")
    public String showDepositPage(Model model) {
        MUser muser = new MUser();  
        model.addAttribute("muser", muser);
        model.addAttribute("depositInput", new DepositInput());
        return "deposit/deposit"; 
    }
    
    // 入金フォームを表示
    @PostMapping("/deposit-form")
    public String depositForm(@RequestParam String userId, Model model) {	
    	MUser muser = userService.getUserOne(userId);
        model.addAttribute("muser", muser);
        model.addAttribute("depositInput", new DepositInput());
        return "deposit/deposit";
    }

    // 入金確認ページを表示
    @PostMapping("/confirm-input")
    public String confirmInput(@ModelAttribute DepositInput depositInput,String userId,Model model) {
        model.addAttribute("depositInput", depositInput);
        model.addAttribute("userId", userId);
        return "deposit/confirm";
   
    }

    // 入金処理を実行
    @PostMapping("/complete")
    public String completeDeposit(@RequestParam int amount, @RequestParam String userId, Model model) {
        userService.addPoint(userId, amount); // ポイントを追加
        model.addAttribute("userId", userId);
        model.addAttribute("amount",amount);
        return "deposit/completion"; // 入金完了ページ
    }
}
