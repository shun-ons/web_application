package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.MUser;
import com.example.demo.service.UserService;

/**
 * ユーザー一覧を管理するコントローラークラス
 * @author 夏木翔吾
 */
@Controller
public class UserListController {

    @Autowired
    private UserService userService;

    /**
     * ユーザー一覧を取得する.
     *
     * @param model モデルオブジェクト
     * @return ユーザー一覧ページのビュー名
     */
    @GetMapping("/list")
    public String getUserList(Model model) {
        List<MUser> muserList = userService.getUsers();
        model.addAttribute("muserList", muserList);
        return "user/list";
    }

    /**
     * 指定したユーザーの詳細を表示する.
     *
     * @param userId ユーザーID
     * @param model モデルオブジェクト
     * @return ユーザー詳細ページのビュー名
     */
    @PostMapping("/list/details")
    public String userDetails(@RequestParam String userId, Model model) {
        MUser muser = userService.getUserOne(userId);
        model.addAttribute("muser", muser);
        return "user/detail";
    }
}
