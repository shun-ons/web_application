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

@Controller
public class SoldItemController {
	@Autowired
	ItemService itemService;
	@Autowired
	UserService userService;
	
	@PostMapping("/item-list")
	public String itemList(Model model, @RequestParam String userId) {
		List<Item> items = itemService.selectAll();
		List<Item> reverseItems = new ArrayList<Item>(items.size());
		for (int i = items.size() - 1; i >= 0; i--) {
			if (items.get(i).getIsSold()) {
				// 誰かがカートに入れている商品は表示しない.
				reverseItems.add(items.get(i));
			}
		}
		model.addAttribute("items", reverseItems);
		model.addAttribute("userId", userId);
		return "soldItem/itemList";
	}
	
	@PostMapping("/item-list/details")
	public String showItemDetails(@RequestParam("itemId") String itemId, @RequestParam String userId,Model model) {
		Item item = itemService.selectById(itemId);
		model.addAttribute("item", item);
		model.addAttribute("userId", userId);
		return "soldItem/itemDetails";
	}
	
	
	@PostMapping(value = "/mypage/update-item", params = "update")
	public String updateItemDetail(
			@RequestParam("userId") String userId, 
			@Validated ItemInput itemInput,
			BindingResult bindingResult,
			Model model) {
		if (!bindingResult.hasErrors()) {
			itemService.update(itemInput, userId);
		}
		MUser userDetailForm = userService.getUserOne(userId);
		List<Item> items = itemService.selectByOrnerId(userId);
		List<ItemInput> newItemInput = itemService.turnItemIntoItemInput(items);
		model.addAttribute("userDetailForm", userDetailForm);
		model.addAttribute("itemList", newItemInput);
		return "user/detail";
	}
	
	@PostMapping(value = "/mypage/update-item", params = "delete")
	public String deleteItem(@RequestParam String userId,@RequestParam String itemId, Model model) {
		MUser userDetailForm = userService.getUserOne(userId);
		itemService.delete(itemId);
		itemService.deleteImage(itemId);
		List<Item> items = itemService.selectByOrnerId(userId);
		List<ItemInput> newItemInput = itemService.turnItemIntoItemInput(items);
		model.addAttribute("userDetailForm", userDetailForm);
		model.addAttribute("itemList", newItemInput);
		return "user/detail";
	}
	
}
