package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

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
     * 商品一覧を表示する.
     * 
     * @param model モデルオブジェクト
     * @return インデックスページのビュー名
     */
    @GetMapping("/index")
    public String indexItemList(Model model) {
        List<Item> items = itemService.selectAll();
        List<Item> reverseItems = new ArrayList<Item>(items.size());
        for (int i = items.size() - 1; i >= 0; i--) {
            if (!items.get(i).getInCart()) {
                reverseItems.add(items.get(i));
            }
        }
        model.addAttribute("keyword", "");
        model.addAttribute("items", reverseItems);
        return "index/index";
    }

    /**
     * 商品検索を実行する.
     * 
     * @param model モデルオブジェクト
     * @param keyword 検索キーワード
     * @return 検索結果のビュー名
     */
    @PostMapping("indexsearch-item")
    public String searchItem(Model model, @RequestParam String keyword) {
        List<Item> items = itemService.selectAll();
        List<Item> reverseItems = new ArrayList<Item>();
        String upperKeyword = keyword.toUpperCase();
        for (int i = items.size() - 1; i >= 0; i--) {
            String upperItemName = items.get(i).getItemName().toUpperCase();
            if (upperItemName.contains(upperKeyword) && !items.get(i).getInCart()) {
                reverseItems.add(items.get(i));
            }
        }
        model.addAttribute("keyword", keyword);
        model.addAttribute("items", reverseItems);
        return "index/index";
    }
}
