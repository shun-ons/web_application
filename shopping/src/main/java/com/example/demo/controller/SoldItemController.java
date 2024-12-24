package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;

@Controller
public class SoldItemController {
	@Autowired
	ItemService itemService;
	
	@GetMapping("/item-list")
	public String itemList(Model model) {
		List<Item> items = itemService.selectAll();
		List<Item> reverseItems = new ArrayList<Item>(items.size());
		for (int i = items.size() - 1; i >= 0; i--) {
			reverseItems.add(items.get(i));
		}
		model.addAttribute("items", reverseItems);
		return "soldItem/itemList";
	}
	
	@GetMapping("/item-list/details")
	public String showItemDetails(@RequestParam("itemId") String itemId, Model model) {
		Item item = itemService.selectById(itemId);
		model.addAttribute("item", item);
		return "soldItem/itemDetails";
	}
}
