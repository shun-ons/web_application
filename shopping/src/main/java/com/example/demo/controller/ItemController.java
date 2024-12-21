package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Item;
import com.example.demo.input.ItemInput;
import com.example.demo.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	ItemService itemService;
	
	@GetMapping("/sales/sales-form")
	public String salesForm(Model model) {
		ItemInput itemInput = new ItemInput();
		model.addAttribute("itemInput", itemInput);
		return "sales/salesForm";
	}
	
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
	
	@PostMapping(value = "sales/confirm-input", params = "back")
	public String reenterInput(@Validated ItemInput itemInput, Model model) {
		model.addAttribute("itemInput", itemInput);
		return "sales/salesForm";
	}
	
	@PostMapping(value = "sales/confirm-input", params = "confirm")
	public String confirmInput(@Validated ItemInput itemInput, Model model) {
		Item item = itemService.sell(itemInput);
		model.addAttribute("item", item);
		return "sales/salesCompletion";
	}
}
