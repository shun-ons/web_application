package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.MUser;
import com.example.demo.input.SignupFormInput;
import com.example.demo.service.UserService;

import ch.qos.logback.core.model.Model;
import lombok.extern.slf4j.Slf4j;

/**
 * ユーザー登録処理を担当するコントローラークラス
 * @author 夏木翔吾
 */
@Controller
@Slf4j
public class SignupController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * サインアップページを表示する.
     * 
     * @param form サインアップフォーム
     * @return サインアップページのビュー名
     */
    @GetMapping("/signup")
    public String getSignup(@ModelAttribute SignupFormInput form) {
        return "signup/signup";
    }

    /**
     * サインアップ処理を実行する.
     * 
     * @param model モデルオブジェクト
     * @param form サインアップフォーム
     * @param bindingResult バリデーション結果
     * @return ログインページへのリダイレクト
     */
    @PostMapping("/signup")
    public String postSignup(Model model, @ModelAttribute @Validated SignupFormInput form, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            return getSignup(form);
        }
        log.info(form.toString());

        MUser user = modelMapper.map(form, MUser.class);
        userService.signup(user);

        return "redirect:/login";
    }
}
