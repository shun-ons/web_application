package com.example.demo.controller;

import java.util.ArrayList;
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
import com.example.demo.input.ItemInput;
import com.example.demo.service.ItemService;

/**
 * マイページの処理を担当するコントローラークラス.
 * @author 夏木翔吾
 */
@Controller
public class MypageController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ItemService itemService;

    /**
     * マイページを表示する
     *
     * @param model モデルオブジェクト
     * @return マイページのビュー名
     */
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

        String userId = muser.getUserId();
        List<Item> items = itemService.selectByOrnerId(userId);  // ユーザーが出品中の商品を取得
        List<Item> isSoldItems = new ArrayList<>(); // 販売中の商品のみを格納
        for (Item item : items) {
            if (item.getIsSold()) {
                isSoldItems.add(item);
            }
        }
        List<ItemInput> itemList = itemService.turnItemIntoItemInput(isSoldItems); // Item → ItemInput へ変換
        model.addAttribute("muser", muser);
        model.addAttribute("itemList", itemList);
        return "user/mypage";
    }

    /**
     * 指定ユーザーのマイページを表示する
     *
     * @param userId ユーザーID
     * @param model モデルオブジェクト
     * @return マイページのビュー名
     */
    @PostMapping("/mypage")
    public String mypage(@RequestParam String userId, Model model) {
        MUser muser = userService.getUserOne(userId);
        List<Item> items = itemService.selectByOrnerId(userId);
        List<Item> isSoldItems = new ArrayList<>();
        for (Item item : items) {
            if (item.getIsSold()) {
                isSoldItems.add(item);
            }
        }
        List<ItemInput> itemList = itemService.turnItemIntoItemInput(isSoldItems);
        model.addAttribute("muser", muser);
        model.addAttribute("itemList", itemList);
        return "user/mypage";
    }

    /**
     * 商品詳細を更新する
     *
     * @param userId ユーザーID
     * @param model モデルオブジェクト
     * @return 商品詳細ページのビュー名
     */
    @PostMapping("/mypage/details")
    public String updateItemDetail(@RequestParam String userId, Model model) {
        MUser muser = userService.getUserOne(userId);
        List<Item> items = itemService.selectByOrnerId(userId);
        List<Item> soldItems = new ArrayList<>();
        for (Item item : items) {
            if (!item.getInCart()) {
                soldItems.add(item);
            }
        }
        List<ItemInput> itemList = itemService.turnItemIntoItemInput(soldItems);
        model.addAttribute("muser", muser);
        model.addAttribute("itemList", itemList);

        return "user/detail";
    }
}
