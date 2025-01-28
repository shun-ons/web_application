package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.UserService;
import com.example.demo.entity.Item;
import com.example.demo.input.ItemInput;
import com.example.demo.service.ItemService;

/**
 * 販売中の商品に関するコントローラクラス.
 * @author 大西竣介
 */
@Controller
public class SoldItemController {
	@Autowired
	ItemService itemService;
	@Autowired
	UserService userService;
	
    public SoldItemController(UserService userService) {
        this.userService = userService;
    }
	
	/**
	 * postメソッドで"/item-list"にアクセスされた場合の処理を行うメソッド.
	 * 販売中の商品を表示.
	 * @param model Model型の変数.
	 * @param userId マイページなどに遷移するためのユーザID.
	 * @return itemList.htmlを表示する.
	 */
	@PostMapping("/item-list")
	public String itemList(Model model, @RequestParam String userId) {
		List<Item> items = itemService.selectAll();
		List<Item> reverseItems = new ArrayList<Item>(items.size());
		for (int i = items.size() - 1; i >= 0; i--) {
			if (!items.get(i).getInCart()) {
				// 誰かがカートに入れている商品は表示しない.
				reverseItems.add(items.get(i));
			}
		}
		model.addAttribute("items", reverseItems);
        MUser muser = userService.getUserOne(userId);
        model.addAttribute("muser", muser);
        return "soldItem/itemList";
	}
	
	/**
	 * postメソッドで"/item-list"にアクセスされた場合の処理を行うメソッド.
	 * 選択された商品の詳細を表示する.
	 * @param itemId 選択された商品のID.
	 * @param userId マイページやカートに遷移するためのユーザID.
	 * @param model Model型の変数.
	 * @return itemDetails.htmlを表示する.
	 */
	@PostMapping("/item-list/details")
	public String showItemDetails(@RequestParam("itemId") String itemId, @RequestParam String userId,Model model) {
		Item item = itemService.selectById(itemId);
		model.addAttribute("item", item);
        MUser muser = userService.getUserOne(userId);
        model.addAttribute("muser", muser);
        return "soldItem/itemDetails";
	}
	
	/**
	 * postメソッドで"/mypage/update-item"にアクセスされた場合の処理を行うメソッド.
	 * 販売中の商品を更新する際に用いる.
	 * @param userId マイページへアクセスしているユーザのID.
	 * @param itemInput ユーザの入力を保持するオブジェクト.
	 * @param bindingResult バリデーションを行うための変数.
	 * @param model Model型の変数.
	 * @return detail.htmlを表示する.
	 */
	@PostMapping(value = "/mypage/update-item", params = "update")
	public String updateItemDetail(
			@RequestParam("userId") String userId, 
			@Validated ItemInput itemInput,
			BindingResult bindingResult,
			Model model) {
		if (!bindingResult.hasErrors()) {
			itemService.update(itemInput, userId);
		}
		MUser muser = userService.getUserOne(userId);
		List<Item> items = itemService.selectByOrnerId(userId);
		List<ItemInput> newItemInput = itemService.turnItemIntoItemInput(items);
		model.addAttribute("muser", muser);
		model.addAttribute("itemList", newItemInput);
		return "user/detail";
	}
	
	/**
	 * postメソッドで"/mypage/update-item"にアクセスした場合の処理を記述するメソッド.
	 * 販売中のっ商品を削除する場合に用いる.
	 * @param userId
	 * @param itemId
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/mypage/update-item", params = "delete")
	public String deleteItem(@RequestParam String userId,@RequestParam String itemId, Model model) {
		MUser muser = userService.getUserOne(userId);
		itemService.delete(itemId);
		itemService.deleteImage(itemId);
		List<Item> items = itemService.selectByOrnerId(userId);
		List<ItemInput> newItemInput = itemService.turnItemIntoItemInput(items);
		model.addAttribute("muser", muser);
		model.addAttribute("itemList", newItemInput);
		return "user/detail";
	}
	
}
