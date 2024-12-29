package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.input.ItemInput;
import com.example.demo.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	ItemService itemService;
	
	// 販売フォームへのアクセスを管理.
	@PostMapping("/sales/sales-form")
	public String salesForm(Model model, @RequestParam String userId) {
		ItemInput itemInput = new ItemInput();
		model.addAttribute("itemInput", itemInput);
		model.addAttribute("userId", userId);
		return "sales/salesForm";
	}
	
	// 販売フォームでの入力を確認.
	@PostMapping("sales/item-input")
	public String validateInput(
			@Validated ItemInput itemInput,
			BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "sales/salesForm";
		}
		return "sales/salesConfirmation";
	}
	
	// 入力を訂正.
	@PostMapping(value = "sales/confirm-input", params = "back")
	public String reenterInput(@Validated ItemInput itemInput, Model model) {
		model.addAttribute("itemInput", itemInput);
		return "sales/salesForm";
	}
	
	// 販売を確定.
	@PostMapping(value = "sales/confirm-input", params = "confirm")
	public String confirmInput(@Validated ItemInput itemInput, Model model) {
		Item item = itemService.sell(itemInput);
		model.addAttribute("item", item);
		return "sales/salesCompletion";
	}
}
