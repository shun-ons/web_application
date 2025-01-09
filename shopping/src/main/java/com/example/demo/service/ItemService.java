package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
	
	// あるユーザが出品している商品を取得.
	public List<Item> selectByOrnerId(String ornerId) {
		return itemMapper.selectByOrnerId(ornerId);
	}
	
	// userIdからuserNameを取得.
	public String getUserName(String userId) {
		return itemMapper.getUserName(userId);
	}
	
	// 登録と更新のうち、共通する動作
	private Item edit(ItemInput itemInput, String userId) {
		Item item = new Item();
		if (itemInput.getItemId() == null) {
			item.setId(UUID.randomUUID().toString());
		} else {
			item.setId(itemInput.getItemId());
		}
		item.setItemName(itemInput.getItemName());
		item.setItemPrice(itemInput.getItemPrice());
		item.setOrnerName(this.getUserName(userId));
		item.setOrnerId(userId);
		item.setComment(itemInput.getComment());
		LocalDateTime now = LocalDateTime.now();
		//DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		item.setSalesDateTime(now);
		
		return item;
	}
	
	// 登録
	public Item sell(ItemInput itemInput, String userId) {
		Item item = this.edit(itemInput, userId);
		itemMapper.insert(item);
		return item;
	}
	
	// 更新
	public ItemInput update(ItemInput itemInput, String userId) {
		Item item = this.edit(itemInput, userId);
		itemMapper.update(item);
		return itemInput;
	}
	
	// 削除
	public void delete(String itemId) {
		itemMapper.delete(itemId);
	}
	
	// 商品の販売状況を変更.
	public void  updateIsSold(String itemId, boolean isSold) {
		itemMapper.updateIsSold(itemId, isSold);
	}
	
	// ItemのリストをItemInputのリストに変換.
	public List<ItemInput> turnItemIntoItemInput(List<Item> items) {
		List<ItemInput> itemInputList = new ArrayList<ItemInput>();
		for (int i = 0; i < items.size(); i++) {
			Item itemTmp = items.get(i);
			ItemInput itemInputTmp = new ItemInput(itemTmp.getItemName(), itemTmp.getItemPrice(), itemTmp.getComment());
			itemInputTmp.setItemId(itemTmp.getItemId());
			itemInputList.add(itemInputTmp);
		}
		return itemInputList;
	}
}
