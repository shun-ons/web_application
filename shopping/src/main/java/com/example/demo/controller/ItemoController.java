package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.input.ItemInput;

@Controller
public class ItemoController {
	@GetMapping("/sales/sales-form")
	public String salesForm(@RequestParam String itemId, Model model) {
		ItemInput itemInput = new ItemInput();
		itemInput.setItemId(itemId);
		model.addAttribute("itemInput", itemInput);
		return "sales/salesForm";
	}
	
	@PostMapping("sales/item-input")
	public String validateInput(
			@Validated ItemInput itemInput,
			BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("itemInput", itemInput);
			return "sales/salesForm";
		}
		return "sales/salesConfirmation";
	}
}
