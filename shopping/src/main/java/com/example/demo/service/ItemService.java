package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private ItemMapper itemMapper;
	
	
	// 全取得
	public List<Item> selectAll() {
		return itemMapper.selectAll();
	}
	
	// 1件取得
	public Item selectById(String itemId) {
		return itemMapper.selectById(itemId);
	}
	
	// 登録と更新のうち、共通する動作
	private Item edit(ItemInput itemInput) {
		Item item = new Item();
		item.setId(UUID.randomUUID().toString());
		item.setItemName(itemInput.getItemName());
		item.setItemPrice(itemInput.getItemPrice());
		item.setOrnerName(itemInput.getOrnerName());
		item.setComment(itemInput.getComment());
		LocalDateTime now = LocalDateTime.now();
		//DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		item.setSalesDateTime(now);
		
		return item;
	}
	
	// 登録
	public Item sell(ItemInput itemInput) {
		Item item = this.edit(itemInput);
		itemMapper.insert(item);
		return item;
	}
	
	// 更新
	public Item update(ItemInput itemInput) {
		Item item = this.edit(itemInput);
		itemMapper.update(item);
		return item;
	}
	
	// 削除
	public void delete(String itemId) {
		itemMapper.delete(itemId);
	}
}
