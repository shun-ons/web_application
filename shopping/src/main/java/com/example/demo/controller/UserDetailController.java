package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.MUser;
import com.example.demo.input.UserDetailFormInput;
import com.example.demo.service.UserService;

/**
 * ユーザー詳細ページの処理を担当するコントローラークラス
 * @author 夏木翔吾
 */
@Controller
public class UserDetailController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 指定されたメールアドレスのユーザー詳細を取得する.
     * 
     * @param form ユーザー詳細フォーム
     * @param model モデルオブジェクト
     * @param mailAddress メールアドレス
     * @return ユーザー詳細ページのビュー名
     */
    @GetMapping("/detail/{mailAddress:.+}")
    public String getUser(UserDetailFormInput form, Model model, @PathVariable("mailAddress") String mailAddress) {
        
        MUser user = userService.getUserOne(mailAddress);
        user.setPassword(null);

        form = modelMapper.map(user, UserDetailFormInput.class);
        model.addAttribute("userDetailForm", form);

        return "user/detail";
    }

    /**
     * ユーザー情報を更新する.
     * 
     * @param form ユーザー詳細フォーム
     * @param model モデルオブジェクト
     * @param userId ユーザーID
     * @return マイページへのリダイレクト
     */
    @PostMapping(value = "/detail", params = "update")
    public String updateUser(UserDetailFormInput form, Model model, String userId) {

        userService.updateUserOne(form.getMailAddress(), form.getName());
        MUser muser = userService.getUserOne(userId);
        model.addAttribute("muser", muser);

        return "redirect:/mypage";
    }

    /**
     * ユーザー情報を削除する.
     * 
     * @param form ユーザー詳細フォーム
     * @param model モデルオブジェクト
     * @param userId ユーザーID
     * @return マイページへのリダイレクト
     */
    @PostMapping(value = "/detail", params = "delete")
    public String deleteUser(UserDetailFormInput form, Model model, String userId) {

        userService.deleteUserOne(form.getMailAddress());
        MUser muser = userService.getUserOne(userId);
        model.addAttribute("muser", muser);

        return "redirect:/mypage";
    }
}
