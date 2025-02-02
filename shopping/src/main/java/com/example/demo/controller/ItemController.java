package com.example.demo.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.UserService;
import com.example.demo.input.ItemInput;
import com.example.demo.service.ItemService;

/**
 * 商品の販売を管理するコントローラークラス.
 */
@Controller
public class ItemController {
    
    @Autowired
    ItemService itemService;

    private final UserService userService;

    public ItemController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 販売フォームを表示する.
     * 
     * @param model モデルオブジェクト
     * @param userId ユーザーID
     * @return 販売フォームのビュー名
     */
    @PostMapping("/sales/sales-form")
    public String salesForm(Model model, @RequestParam String userId) {
        ItemInput itemInput = new ItemInput("", 0, "");
        model.addAttribute("itemInput", itemInput);
        MUser muser = userService.getUserOne(userId);
        model.addAttribute("muser", muser);
        return "sales/salesForm";
    }

    /**
     * 販売フォームの入力内容を確認する.
     * 
     * @param userId ユーザーID
     * @param image アップロードされた画像
     * @param itemInput 入力された商品情報
     * @param bindingResult バリデーション結果
     * @param model モデルオブジェクト
     * @return 確認ページまたはエラーページ
     */
    @PostMapping("sales/item-input")
    public String validateInput(
            String userId,
            @RequestParam("image") MultipartFile image,
            @Validated ItemInput itemInput,
            BindingResult bindingResult,
            Model model) {
        
        if (bindingResult.hasErrors()) {
            MUser muser = userService.getUserOne(userId);
            model.addAttribute("muser", muser);
            model.addAttribute("itemInput", itemInput);
            return "sales/salesForm";
        }

        if (itemInput.getItemId() == null) {
            itemInput.setItemId(UUID.randomUUID().toString());
        }

        try {
            String imageName = itemInput.getItemId() + ".png";
            String path = "src/main/resources/static/image/" + imageName;
            byte[] content = image.getBytes();
            Files.write(Paths.get(path), content);

            model.addAttribute("itemInput", itemInput);
            MUser muser = userService.getUserOne(userId);
            model.addAttribute("muser", muser);
            String imageUrl = "/uploaded-images/" + imageName;
            model.addAttribute("imageUrl", imageUrl);
            return "sales/salesConfirmation";
        } catch(Exception e) {
            model.addAttribute("error", e);
            model.addAttribute("status", "");
            return "error";
        }
    }
}
