package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Item;
import com.example.demo.input.ItemInput;
import com.example.demo.repository.ItemMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ItemService {
	private final ItemMapper itemMapper;
	
	// 全取得
	public List<Item> selectAll() {
		return itemMapper.selectAll();
	}
	
	// 1件取得
	public Item selectById(String itemId) {
		return itemMapper.selectById(itemId);
	}
	
	// 登録
	public void sales(ItemInput itemInput) {
		
	}
}
