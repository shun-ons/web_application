package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.service.UserService;
import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;

/**
 * インデックスページ（トップページ）のコントローラークラス.
 */
@Controller
public class IndexController {
	
	@Autowired
	ItemService itemService;
	@Autowired
	UserService userService;
	
	/**
	 * postメソッドで"/item-list"にアクセスされた場合の処理を行うメソッド.
	 * 販売中の商品を表示.
	 * @param model Model型の変数.
	 * @param userId マイページなどに遷移するためのユーザID.
	 * @return itemList.htmlを表示する.
	 */
	@GetMapping("/index")
	public String indexItemList(Model model, @RequestParam Map<String, String> allParams) {
		List<Item> allItems = itemService.selectAll();
		List<Item> items = new ArrayList<Item>();
		for (int i = 0; i < allItems.size(); i++) {
			if (!allItems.get(i).getInCart()) {
				// 誰かがカートに入れている商品は表示しない.
				items.add(allItems.get(i));
			}
		}
		int itemSize = items.size();
		List<Item> itemList;
		// 現在のページ数を取得.
		Integer page = allParams.get("page") == null ? 1 : Integer.parseInt(allParams.get("page"));
		Integer allPage = itemSize / 10 + 1;
		// ページに表示する商品を取得.
		if (itemSize <= 10) {
			itemList = new ArrayList<Item>(items);
		} else {
			Integer tmp = (page - 1) * 10;
			System.out.println(page);
			if (allPage > page) {
				itemList = items.subList(tmp, tmp+10);
				System.out.println(itemList.size());
				for (Item item : itemList) {
					System.out.println(item.getItemName());
				}
			} else {
				itemList = items.subList(tmp, itemSize);
			}
		}
		model.addAttribute("keyword", "");
		model.addAttribute("items", itemList);
        model.addAttribute("page", page);
        model.addAttribute("allPage", allPage);
        return "index/index";
	}
	
	@PostMapping("/indexsearch-item")
	public String searchItem(Model model,@RequestParam Map<String, String> allParams) {
		String keyword = allParams.get("keyword");
		String upperKeyword = keyword.toUpperCase();
		List<Item> allItems = itemService.selectAll();
		List<Item> items = new ArrayList<Item>();
		for (int i = 0; i < allItems.size(); i++) {
			String upperItemName = allItems.get(i).getItemName().toUpperCase();
			if (upperItemName.contains(upperKeyword) && !allItems.get(i).getInCart()) {
					items.add(allItems.get(i));
			}
		}
		
		int itemSize = items.size();
		System.out.println(items.size());
		List<Item> itemList;
		// 現在のページ数を取得.
		Integer page = allParams.get("page") == null ? 1 : Integer.parseInt(allParams.get("page"));
		Integer allPage = itemSize / 10 + 1;
		// ページに表示する商品を取得.
		if (itemSize <= 10) {
			itemList = new ArrayList<Item>(items);
		} else {
			Integer tmp = (page - 1) * 10;
			System.out.println(page);
			if (allPage > page) {
				itemList = items.subList(tmp, tmp+10);
				System.out.println(itemList.size());
				for (Item item : itemList) {
					System.out.println(item.getItemName());
				}
			} else {
				itemList = items.subList(tmp, itemSize);
			}
		}
		
		System.out.println(itemList.size());
		model.addAttribute("keyword", keyword);
		model.addAttribute("items", itemList);
        model.addAttribute("allPage", allPage);
        return "index/index";
    }
}
