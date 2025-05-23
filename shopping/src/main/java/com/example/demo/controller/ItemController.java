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

import com.example.demo.entity.Item;
import com.example.demo.entity.MUser;
import com.example.demo.input.ItemInput;
import com.example.demo.service.ItemService;
import com.example.demo.service.UserService;

/**
 * 商品の出品を管理するコントローラ.
 * @author 大西竣介
 */
@Controller
public class ItemController {
	@Autowired
	ItemService itemService;
	
	private final UserService userService;

    public ItemController(UserService userService) {
        this.userService = userService;
    }
	
	// 販売フォームへのアクセスを管理.
	@PostMapping("/sales/sales-form")
	public String salesForm(Model model, @RequestParam String userId) {
		ItemInput itemInput = new ItemInput("", 0, "");
		model.addAttribute("itemInput", itemInput);
        MUser muser = userService.getUserOne(userId);
        model.addAttribute("muser", muser);
        return "sales/salesForm";
	}
	
	// 販売フォームでの入力を確認.
	@PostMapping("sales/item-input")
	public String validateInput(
			String userId,
			@RequestParam("image") MultipartFile image,
			@Validated ItemInput itemInput,
			BindingResult bindingResult,
			Model model) {
		
		// 入力ミスが起きた場合の処理.
		if (bindingResult.hasErrors()) {
	        MUser muser = userService.getUserOne(userId);
	        model.addAttribute("muser", muser);
	        model.addAttribute("itemInput", itemInput);
			return "sales/salesForm";
		}
		
		// 商品IDを設定.
		if (itemInput.getItemId() == null) {
			itemInput.setItemId(UUID.randomUUID().toString());
		}
		// 画像を保存.
		try {
			String imageName = itemInput.getItemId() + ".png";  // ファイル名を商品IDに設定.
			String path = "src/main/resources/static/image/" + imageName; // ファイルの保存先のパスを指定.
			byte[] content = image.getBytes();         // ファイルをバイナリデータとして取得.
			Files.write(Paths.get(path), content);    // 保存.
			
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
	
	// 入力を訂正.
	@PostMapping(value = "sales/confirm-input", params = "back")
	public String reenterInput(@Validated ItemInput itemInput, Model model, @RequestParam String userId) {
		boolean result = itemService.deleteImage(itemInput.getItemId());
		
		if (result) {
	        MUser muser = userService.getUserOne(userId);
	        model.addAttribute("muser", muser);
	        model.addAttribute("itemInput", itemInput);
    		return "sales/salesForm";
		} else {
			model.addAttribute("error", "delete image error");
			model.addAttribute("status", "");
            return "error";  // 削除中にエラーが発生
		}
    }

	
	// 販売を確定.
	@PostMapping(value = "sales/confirm-input", params = "confirm")
	public String confirmInput(@RequestParam String userId, @Validated ItemInput itemInput, Model model) {
		Item item = itemService.sell(itemInput, userId);
        MUser muser = userService.getUserOne(userId);
        model.addAttribute("muser", muser);
        return "sales/salesCompletion";
	}
}
